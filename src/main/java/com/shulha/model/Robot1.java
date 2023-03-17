package com.shulha.model;

import com.shulha.Main;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Robot1 implements Callable<AtomicInteger> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Robot1.class);
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private final AtomicInteger fuel;

    public Robot1(final AtomicInteger fuel) {
        this.fuel = fuel;
    }

    @SneakyThrows
    @Override
    public AtomicInteger call() {
        final int randomFuelAmount = random.nextInt(500, 1001);
        fuel.getAndAdd(randomFuelAmount);
        LOGGER.info("{} created {} liters of fuel!", Robot1.class.getName(), fuel.get());

        LOGGER.info("{} is starting to transport the fuel!", Robot1.class.getName());
        TimeUnit.SECONDS.sleep(3);
        LOGGER.info("{} ended transporting!", Robot1.class.getName());

        return fuel;
    }
}
