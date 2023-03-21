package com.shulha.builder;

import com.shulha.model.Detail;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class DetailBuilder {
    private final Detail detail = new Detail();

    public DetailBuilder setId(final String id) {
        Objects.requireNonNull(id);

        Optional.of(id)
                .filter(id1 -> !id1.isBlank())
                .orElseThrow(() -> new IllegalArgumentException("ID should not be empty!"));

        detail.setId(id);

        return this;
    }

    public DetailBuilder setBeginningTime(final LocalDateTime beginningDate) {
        Objects.requireNonNull(beginningDate);

        detail.setBeginningTime(beginningDate);

        return this;
    }

    public DetailBuilder setEndingTime(final LocalDateTime endingDate) {
        Objects.requireNonNull(endingDate);

        detail.setEndingTime(endingDate);

        return this;
    }

    public DetailBuilder setSpentTime(final long spentTime) {
        Optional.of(spentTime)
                .filter(time -> time >= 0)
                .orElseThrow(() -> new IllegalArgumentException("Spent time should be more than or equals 0!"));

        detail.setSpentTime(spentTime);

        return this;
    }

    public DetailBuilder setSpentFuel(final long spentFuel) {
        Optional.of(spentFuel)
                .filter(fuel -> fuel > 0)
                .orElseThrow(() -> new IllegalArgumentException("Spent fuel should be more than 0!"));

        detail.setSpentFuel(spentFuel);

        return this;
    }

    public DetailBuilder setMinedFuel(final long minedFuel) {
        Optional.of(minedFuel)
                .filter(fuel -> fuel > 0)
                .orElseThrow(() -> new IllegalArgumentException("Mined fuel should be more than 0!"));

        detail.setMinedFuel(minedFuel);

        return this;
    }

    public DetailBuilder setAmountOfBrokenChips(final long amountOfBrokenChips) {
        Optional.of(amountOfBrokenChips)
                .filter(fuel -> fuel >= 0)
                .orElseThrow(
                        () -> new IllegalArgumentException("Amount of broken chips should be more than or equals 0!")
                );

        detail.setAmountOfBrokenChips(amountOfBrokenChips);

        return this;
    }

    @SneakyThrows
    public Detail getDetail() {
        return detail.clone();
    }
}
