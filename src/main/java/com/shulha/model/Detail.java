package com.shulha.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "details")
public class Detail {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "detail_id")
    private String id;
    @Column(name = "beginning_time")
    private LocalDateTime beginningTime;
    @Column(name = "ending_time")
    private LocalDateTime endingTime;
    @Column(name = "spent_time")
    private int spentTime;
    @Column(name = "spent_fuel")
    private int spentFuel;
    @Column(name = "mined_fuel")
    private int minedFuel;
    @Column(name = "broken_chips")
    private int amountOfBrokenChips;

    public Detail() {
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public LocalDateTime getBeginningTime() {
        return beginningTime;
    }

    public void setBeginningTime(final LocalDateTime beginningTime) {
        this.beginningTime = beginningTime;
    }

    public LocalDateTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(final LocalDateTime endingTime) {
        this.endingTime = endingTime;
    }

    public int getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(final int spentTime) {
        this.spentTime = spentTime;
    }

    public int getSpentFuel() {
        return spentFuel;
    }

    public void setSpentFuel(final int spentFuel) {
        this.spentFuel = spentFuel;
    }

    public int getMinedFuel() {
        return minedFuel;
    }

    public void setMinedFuel(final int minedFuel) {
        this.minedFuel = minedFuel;
    }

    public int getAmountOfBrokenChips() {
        return amountOfBrokenChips;
    }

    public void setAmountOfBrokenChips(final int amountOfBrokenChips) {
        this.amountOfBrokenChips = amountOfBrokenChips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Detail detail = (Detail) o;
        return Objects.equals(id, detail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Detail{" +
                "id='" + id + '\'' +
                ", beginningTime=" + beginningTime.format(DATE_TIME_FORMATTER) +
                ", endingTime=" + endingTime.format(DATE_TIME_FORMATTER) +
                ", spentTime=" + spentTime +
                ", spentFuel=" + spentFuel +
                ", minedFuel=" + minedFuel +
                ", amountOfBrokenChips=" + amountOfBrokenChips +
                '}';
    }
}
