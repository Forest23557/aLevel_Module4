package com.shulha.model;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class RobotTwoOrThree implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(RobotTwoOrThree.class);
    private final Phaser phaser;
    private final AtomicLong percentage;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    public RobotTwoOrThree(final AtomicLong percentage, final Phaser phaser) {
        this.percentage = percentage;
        this.phaser = phaser;

        phaser.register();
    }

    @SneakyThrows
    @Override
    public void run() {
        LOGGER.info("It started working");

        while (percentage.get() < 100) {
            final long currentPercentage = random.nextInt(10, 21);
            percentage.getAndAdd(currentPercentage);
            LOGGER.info("It has done work by {}%", percentage.get());

            LOGGER.info("It is being reloaded");
            TimeUnit.SECONDS.sleep(2);
            LOGGER.info("It was reloaded");
        }

        phaser.arriveAndDeregister();
        LOGGER.info("It ended working");
    }
}
