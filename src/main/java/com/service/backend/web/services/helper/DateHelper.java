package com.service.backend.web.services.helper;

import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateHelper {


    private DateHelper() {
        throw new UnsupportedOperationException("Don't instantiate this  Utility class");
    }

    public static LocalDateTime convertStringToEndDateTime(String date) {
        LocalDateTime dateTime = null;

        try {
            dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyy")).atTime(23, 59, 59);
        } catch (Exception ex) {
            throw new FunctionalException(
                    new FunctionalExceptionDto("Invalid date range. Please ensure the dates are properly formatted and logical.", HttpStatus.BAD_REQUEST)
            );
        }
        return dateTime;
    }

    public static LocalDateTime convertStringToStartDateTime(String date) {
        LocalDateTime dateTime = null;

        try {
            dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyy")).atStartOfDay();
        } catch (Exception ex) {
            throw new FunctionalException(
                    new FunctionalExceptionDto("Invalid date range. Please ensure the dates are properly formatted and logical.", HttpStatus.BAD_REQUEST)
            );
        }
        return dateTime;
    }
}
