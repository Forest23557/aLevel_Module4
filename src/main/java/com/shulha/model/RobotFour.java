package com.shulha.model;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class RobotFour implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(RobotFour.class);
    private final Random random = new Random();
    private final Phaser phaser;
    private final AtomicLong brokenChips;
    private final AtomicLong percentage;

    public RobotFour(final Phaser phaser, final AtomicLong brokenChips, final AtomicLong percentage) {
        this.phaser = phaser;
        this.brokenChips = brokenChips;
        this.percentage = percentage;

        phaser.register();
    }

    @SneakyThrows
    @Override
    public void run() {
        LOGGER.info("It started programming chip");

        while (percentage.get() < 100) {
            final long currentPercentage = random.nextInt(26) + 10;
            final int chanceToBrokeChip = random.nextInt(101);

            if (chanceToBrokeChip <= 30) {
                LOGGER.info("It has broken a chip");
                brokenChips.getAndIncrement();
                percentage.set(0);
            } else {
                percentage.getAndAdd(currentPercentage);
                LOGGER.info("It has done its work by {}%", percentage.get());
            }

            LOGGER.info("Number of broken chips: {}", brokenChips.get());

            LOGGER.info("It is being reloaded");
            TimeUnit.SECONDS.sleep(1);
            LOGGER.info("It was reloaded");
        }

        phaser.arriveAndDeregister();
        LOGGER.info("Chip programming was completed");
    }
}
