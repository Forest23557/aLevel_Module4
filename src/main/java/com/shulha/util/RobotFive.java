package com.shulha.util;

import com.shulha.model.FuelDepartment;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class RobotFive implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(RobotFive.class);
    private final Random random = new Random();
    private final Phaser phaser;
    private final AtomicLong percentage;
    private final FuelDepartment fuelDepartment;

    public RobotFive(final Phaser phaser, final AtomicLong percentage, final FuelDepartment fuelDepartment) {
        this.phaser = phaser;
        this.percentage = percentage;
        this.fuelDepartment = fuelDepartment;

        phaser.register();
    }

    @SneakyThrows
    @Override
    public void run() {
        LOGGER.info("It started forming a detail");

        while (percentage.get() < 100) {
            final long amountOfFuel = random.nextInt(351) + 350;
            fuelDepartment.addSpentFuel(amountOfFuel);

            percentage.getAndAdd(10);

            LOGGER.info("It has done its work by {}%", percentage.get());
            LOGGER.info("It spent {} liters of fuel", fuelDepartment.getSpentFuel());

            LOGGER.info("It is being reloaded");
            TimeUnit.SECONDS.sleep(1);
            LOGGER.info("It was reloaded");
        }

        phaser.arriveAndDeregister();
        LOGGER.info("Detail forming has been ended");
    }
}
