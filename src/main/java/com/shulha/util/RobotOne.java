package com.shulha.util;

import com.shulha.model.FuelDepartment;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class RobotOne implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(RobotOne.class);
    private final Random random = new Random();
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
            final int randomFuelAmount = random.nextInt(501) + 500;
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
