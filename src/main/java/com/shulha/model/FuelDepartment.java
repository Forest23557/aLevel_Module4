package com.shulha.model;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FuelDepartment {
    private static final Logger LOGGER = LoggerFactory.getLogger(FuelDepartment.class);
    private static final long MAX_SPENT_FUEL = 700;
    private final AtomicLong fuel;
    private final AtomicLong spentFuel;
    private final ReentrantLock locker;
    private final Condition condition;

    public FuelDepartment() {
        fuel = new AtomicLong(0);
        spentFuel = new AtomicLong(0);
        this.locker = new ReentrantLock();
        this.condition = locker.newCondition();
    }

    @SneakyThrows
    public void addFuel(final long fuel) {
        locker.lock();
        this.fuel.getAndAdd(fuel);

        if (this.fuel.get() > spentFuel.get() + MAX_SPENT_FUEL) {
            LOGGER.info("It is going to signal that it has enough fuel");
            condition.signal();
        }
        locker.unlock();
    }

    @SneakyThrows
    public void addSpentFuel(final long spentFuel) {
        locker.lock();
        while (this.spentFuel.get() + spentFuel > this.fuel.get()) {
            condition.await();
        }
        this.spentFuel.getAndAdd(spentFuel);

        locker.unlock();
    }

    public long getFuel() {
        return fuel.get();
    }

    public long getSpentFuel() {
        return spentFuel.get();
    }
}
