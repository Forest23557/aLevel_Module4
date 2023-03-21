package com.shulha.model;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class RobotOne implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(RobotOne.class);
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private final FuelDepartment fuelDepartment;
    private final AtomicBoolean isDetailDone;

    public RobotOne(final FuelDepartment fuelDepartment, final AtomicBoolean isDetailDone) {
        this.fuelDepartment = fuelDepartment;
        this.isDetailDone = isDetailDone;
    }

    @SneakyThrows
    @Override
    public void run() {
        LOGGER.info("It started mining fuel");

        while (!isDetailDone.get()) {
            final int randomFuelAmount = random.nextInt(500, 1001);
            fuelDepartment.addFuel(randomFuelAmount);
            LOGGER.info("There was created {} liters of fuel!", fuelDepartment.getFuel());

            LOGGER.info("It is starting to transport the fuel!");
            if (!Thread.currentThread().isInterrupted() && !isDetailDone.get()) {
                TimeUnit.SECONDS.sleep(3);
            }
            LOGGER.info("It ended transporting!");
        }

        LOGGER.info("It stopped mining fuel");
    }
}