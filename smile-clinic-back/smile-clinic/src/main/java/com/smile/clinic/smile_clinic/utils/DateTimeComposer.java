package com.smile.clinic.smile_clinic.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;

@Component
public class DateTimeComposer {

    public LocalDateTime composeDateTime(String date, String time) {
        LocalDate localDate = LocalDate.parse(date); // e.g., "2025-05-27"

        LocalTime localTime = LocalTime.parse(time);

        return LocalDateTime.of(localDate, localTime);
    }
}
