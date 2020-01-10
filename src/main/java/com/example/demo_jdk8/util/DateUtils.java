package com.example.demo_jdk8.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * date util
 */
public class DateUtils {

    public static final String DEFAULT_PATTERN = "yyyy-MM-d";

    public static final String DATE_PATTERN = "yyyyMMdd";

    /**
     * 获取两个日期之间的所有日期
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2019, 9, 5);
        LocalDate endDate = LocalDate.of(2019, 9, 6);
        List<LocalDate> list = getDatesBetween(startDate, endDate);
        list.forEach(date -> {
            System.out.println("date：" + date.format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
        });
    }

}
