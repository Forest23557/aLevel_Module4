package com.shulha.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "details")
public class Detail implements Cloneable {
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
    private long spentTime;
    @Column(name = "spent_fuel")
    private long spentFuel;
    @Column(name = "mined_fuel")
    private long minedFuel;
    @Column(name = "broken_chips")
    private long amountOfBrokenChips;

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

    public long getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(final long spentTime) {
        this.spentTime = spentTime;
    }

    public long getSpentFuel() {
        return spentFuel;
    }

    public void setSpentFuel(final long spentFuel) {
        this.spentFuel = spentFuel;
    }

    public long getMinedFuel() {
        return minedFuel;
    }

    public void setMinedFuel(final long minedFuel) {
        this.minedFuel = minedFuel;
    }

    public long getAmountOfBrokenChips() {
        return amountOfBrokenChips;
    }

    public void setAmountOfBrokenChips(final long amountOfBrokenChips) {
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

    @Override
    public Detail clone() throws CloneNotSupportedException {
        return (Detail) super.clone();
    }
}
