package com.shulha.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeManager {
    private LocalDateTime beginningTime;
    private LocalDateTime endingTime;
    private long spentTime;

    public TimeManager() {
        this.beginningTime = LocalDateTime.now();
        this.endingTime = LocalDateTime.now();
    }

    public void setCurrentBeginningTime() {
        beginningTime = LocalDateTime.now();
    }

    public void setCurrentEndingTime() {
        endingTime = LocalDateTime.now();
    }

    public LocalDateTime getBeginningTime() {
        return beginningTime;
    }

    public LocalDateTime getEndingTime() {
        return endingTime;
    }

    public long getSpentTime() {
        final Duration endingDuration = Duration.ofHours(endingTime.getHour())
                .plusMinutes(endingTime.getMinute())
                .plusSeconds(endingTime.getSecond());
        final Duration beginningDuration = Duration.ofHours(beginningTime.getHour())
                .plusMinutes(beginningTime.getMinute())
                .plusSeconds(beginningTime.getSecond());

        spentTime = endingDuration.toSeconds() - beginningDuration.toSeconds();

        if (spentTime < 0) {
            spentTime = 0;
        }

        return spentTime;
    }
}
