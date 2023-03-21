package com.shulha.service;

import com.shulha.builder.DetailBuilder;
import com.shulha.model.*;
import com.shulha.repository.DetailHibernateRepository;
import com.shulha.repository.Repository;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class DetailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetailService.class);
    private static volatile DetailService instance;
    private final Repository<String, Detail> detailRepository;

    private DetailService(final Repository<String, Detail> detailRepository) {
        this.detailRepository = detailRepository;
    }

    public static DetailService getInstance() {
        DetailService localInstance = instance;

        if (localInstance == null) {
            synchronized (DetailService.class) {
                localInstance = instance;

                if (localInstance == null) {
                    instance = localInstance = new DetailService(DetailHibernateRepository.getInstance());
                }
            }
        }

        return instance;
    }

    public void save(final Detail detail) {
        detailRepository.save(detail);
    }

    public Detail createAndSave() {
        final Detail detail = create();
        detailRepository.save(detail);

        return detail;
    }

    public List<Detail> getAll() {
        return detailRepository.getAll();
    }

    public Detail getById(final String id) {
        Objects.requireNonNull(id);

        return detailRepository.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Don't have any details with id " + id));
    }

    public void delete(final String id) {
        Objects.requireNonNull(id);
        detailRepository.delete(id);
    }

    public void removeAll() {
        detailRepository.removeAll();
    }

    @SneakyThrows
    public Detail create() {
        final AtomicLong brokenChips = new AtomicLong(0);
        final TimeManager timeManager = new TimeManager();
        final AtomicBoolean isDetailDone = new AtomicBoolean(false);
        final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        final FuelDepartment fuelDepartment = new FuelDepartment();
        final Phaser phaser = new Phaser(1);
        int phase;

        timeManager.setCurrentBeginningTime();
        LOGGER.info("Factory is starting to mine fuel");
        mineFuel(fuelDepartment, isDetailDone, fixedThreadPool);

        LOGGER.info("Factory is starting to pick up a base construction");
        pickUpBaseConstruction(fixedThreadPool, phaser);
        phase = phaser.getPhase() + 1;
        phaser.arriveAndAwaitAdvance();
        LOGGER.info("Phase {} has been ended", phase);

        LOGGER.info("Factory is starting to program a chip");
        programChip(fixedThreadPool, phaser, brokenChips);
        phase = phaser.getPhase() + 1;
        phaser.arriveAndAwaitAdvance();
        LOGGER.info("Phase {} has been ended", phase);

        LOGGER.info("Factory is starting to form a detail");
        formDetail(fixedThreadPool, phaser, fuelDepartment);
        phase = phaser.getPhase() + 1;
        phaser.arriveAndAwaitAdvance();
        LOGGER.info("Phase {} has been ended", phase);

        isDetailDone.set(true);
        timeManager.setCurrentEndingTime();

        phaser.arriveAndDeregister();
        LOGGER.info("All phases has been ended");

        fixedThreadPool.shutdown();

        return buildDetail(timeManager, brokenChips, fuelDepartment);
    }

    private void mineFuel(final FuelDepartment fuelDepartment, final AtomicBoolean isDetailDone,
                         final ExecutorService fixedThreadPool) {
        final Runnable robot1 = new RobotOne(fuelDepartment, isDetailDone);
        fixedThreadPool.execute(robot1);
    }

    private void pickUpBaseConstruction(final ExecutorService fixedThreadPool, final Phaser phaser) {
        final AtomicLong percentage = new AtomicLong(0);

        for (int i = 0; i < 2; i++) {
            final Runnable runnable = new RobotTwoOrThree(percentage, phaser);
            fixedThreadPool.execute(runnable);
        }
    }

    private void programChip(final ExecutorService fixedThreadPool, final Phaser phaser,
                             final AtomicLong brokenChips) {
        final AtomicLong percentage = new AtomicLong(0);
        final Runnable runnable = new RobotFour(phaser, brokenChips, percentage);

        fixedThreadPool.execute(runnable);
    }

    private void formDetail(final ExecutorService fixedThreadPool, final Phaser phaser,
                            final FuelDepartment fuelDepartment) {
        final AtomicLong percentage = new AtomicLong(0);
        final Runnable runnable = new RobotFive(phaser, percentage, fuelDepartment);

        fixedThreadPool.execute(runnable);
    }

    private Detail buildDetail(final TimeManager timeManager, final AtomicLong brokenChips,
                             final FuelDepartment fuelDepartment) {
        final DetailBuilder detailBuilder = new DetailBuilder();

        return detailBuilder.setBeginningTime(timeManager.getBeginningTime())
                .setEndingTime(timeManager.getEndingTime())
                .setSpentTime(timeManager.getSpentTime())
                .setAmountOfBrokenChips(brokenChips.get())
                .setMinedFuel(fuelDepartment.getFuel())
                .setSpentFuel(fuelDepartment.getSpentFuel())
                .getDetail();
    }

    @SneakyThrows
    public static void main(String[] args) {
        final DetailService detailService = DetailService.getInstance();
        String id = detailService.createAndSave().getId();
        System.out.println(id);

        System.out.println(detailService.getAll());
    }
}
