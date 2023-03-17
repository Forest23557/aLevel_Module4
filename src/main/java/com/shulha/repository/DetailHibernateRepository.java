package com.shulha.repository;

import com.shulha.config.FlywayUtil;
import com.shulha.config.HibernateFactoryUtil;
import com.shulha.model.Detail;
import lombok.SneakyThrows;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DetailHibernateRepository implements Repository<String, Detail> {
    private static final EntityManager ENTITY_MANAGER = HibernateFactoryUtil.getEntityManager();
    private static volatile DetailHibernateRepository instance;

    private DetailHibernateRepository() {
    }

    public static DetailHibernateRepository getInstance() {
        DetailHibernateRepository localInstance = instance;

        if (localInstance == null) {
            synchronized (DetailHibernateRepository.class) {
                localInstance = instance;

                if (localInstance == null) {
                    instance = localInstance = new DetailHibernateRepository();
                }
            }
        }

        FlywayUtil.getFlyway()
                .migrate();

        return instance;
    }

    @Override
    public void save(final Detail detail) {
        ENTITY_MANAGER.getTransaction().begin();
        ENTITY_MANAGER.persist(detail);
        ENTITY_MANAGER.flush();
        ENTITY_MANAGER.getTransaction().commit();
    }

    @Override
    public List<Detail> getAll() {
        return null;
    }

    @Override
    public Detail getById(final String id) {
        return null;
    }

    @Override
    public void delete(final String id) {

    }

    @Override
    public void removeAll() {

    }

    @SneakyThrows
    public static void main(String[] args) {
        final DetailHibernateRepository detailHibernateRepository = DetailHibernateRepository.getInstance();

        final Detail detail = new Detail();
        detail.setBeginningTime(LocalDateTime.now());
        TimeUnit.SECONDS.sleep(3);
        detail.setEndingTime(LocalDateTime.now());
        detail.setSpentTime(3);
        detail.setSpentFuel(560);
        detail.setMinedFuel(1450);
        detail.setAmountOfBrokenChips(8);

        detailHibernateRepository.save(detail);
    }
}
