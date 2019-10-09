package com.zdd;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * https://howtodoinjava.com/java/date-time/java-time-localdate-class/
 *
 * https://www.cnblogs.com/theRhyme/p/9756154.html
 */
public class java8DateTimeUtil {

    public static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    //将LocalDateTime转为自定义的时间格式的字符串
    public static String formatDateTime(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

    //将long类型的timestamp转为LocalDateTime
    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    //将LocalDateTime转为long类型的timestamp
    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    //将某时间字符串转为自定义时间格式的LocalDateTime
    public static LocalDateTime parseStringToDateTime(String time, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, df);
    }

    //将date转为LocalDateTime
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    //将LocalDateTime转为date
    public static Date localDateTimeToDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String getDayEnd(LocalDateTime dateTime) {
        return formatDateTime(dateTime.with(LocalTime.MAX),DATE_TIME_PATTERN);
    }

    public static String getDayStart(LocalDateTime dateTime) {
        return formatDateTime(dateTime.with(LocalTime.MIN),DATE_TIME_PATTERN);
    }

    public static String getFirstDayOfMonth(LocalDateTime dateTime) {
        return formatDateTime(dateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN),DATE_TIME_PATTERN);
    }

    public static String getLastDayOfMonth(LocalDateTime dateTime) {
        return formatDateTime(dateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX),DATE_TIME_PATTERN);
    }

    public long localDateDiff(LocalDate date1,LocalDate date2){
        return date2.toEpochDay() - date1.toEpochDay();
    }


    public static void main(String[] args) {
        System.out.println(getDayStart(LocalDateTime.now()));
        System.out.println(getDayEnd(LocalDateTime.now()));
        System.out.println(getFirstDayOfMonth(LocalDateTime.now()));
        System.out.println(getLastDayOfMonth(LocalDateTime.now()));
    }

}
