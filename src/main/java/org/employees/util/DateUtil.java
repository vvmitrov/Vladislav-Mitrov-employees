package org.employees.util;

import com.github.sisyphsu.dateparser.DateParserUtils;
import org.employees.model.EmployeeProject;
import org.springframework.stereotype.Component;

import java.util.Date;

public class DateUtil {
    public static Date parseDate(String date) {
        if (isDateEmpty(date)) {
            throw new IllegalArgumentException("Date cannot be empty!");
        }

        return DateParserUtils.parseDate(date);
    }

    public static Date parseNullableDate(String date) {
        if (isDateEmpty(date)) {
            return new Date();
        }

        return DateParserUtils.parseDate(date);
    }

    public static int findCommonDuration(EmployeeProject ep1, EmployeeProject ep2) {
        Date commonFromDate = ep1.getDateFrom().after(ep2.getDateFrom()) ? ep1.getDateFrom() : ep2.getDateFrom();
        Date commonToDate = ep1.getDateTo().after(ep2.getDateTo())? ep2.getDateTo() : ep1.getDateTo();

        return calculateDurationInDays(commonFromDate, commonToDate);
    }

    private static Boolean isDateEmpty(String date) {
        return date == null || date.isEmpty() || date.equals("NULL");
    }

    private static int calculateDurationInDays(Date startDate, Date endDate) {
        long diffInMilliseconds = endDate.getTime() - startDate.getTime();
        return (int) (diffInMilliseconds / (24 * 60 * 60 * 1000));
    }
}
