package com.shulha.service;

import com.shulha.model.Robot1;
import lombok.SneakyThrows;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FactoryService {
    private static final AtomicInteger FUEL = new AtomicInteger(0);
    private static volatile FactoryService instance;
    private final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    private FactoryService() {
    }

    public static FactoryService getInstance() {
        FactoryService localInstance = instance;

        if (localInstance == null) {
            synchronized (FactoryService.class) {
                localInstance = instance;

                if (localInstance == null) {
                    instance = localInstance = new FactoryService();
                }
            }
        }

        return instance;
    }

    @SneakyThrows
    public AtomicInteger getFuel() {
        final Callable<AtomicInteger> robot1 = new Robot1(FUEL);
        final Future<AtomicInteger> future = fixedThreadPool.submit(robot1);
        final AtomicInteger transportedFuel;

        while (true) {
            if (future.isDone()) {
                transportedFuel = future.get();
                break;
            }
            TimeUnit.SECONDS.sleep(1);
        }

        return transportedFuel;
    }

    public void getDetail() {

    }

    public static void main(String[] args) {
        final FactoryService factoryService = FactoryService.getInstance();
        final AtomicInteger fuel = factoryService.getFuel();
        System.out.println("Amount of fuel: " + fuel + " liters");
    }
}
