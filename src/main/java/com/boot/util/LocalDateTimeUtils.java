package com.boot.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @Classname LocalDateTimeUtils
 * @Description TODO
 * @Date 2020/6/22 3:52 下午
 * @Created by hly
 */
public class LocalDateTimeUtils {

    /**
     * yyyy-MM-dd
     */
    public static final String DAY_FORMAT = "yyyy-MM-dd";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String FULL_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获得当前时间的yyyy-MM-dd格式字符串
     *
     * @return String
     */
    public static String getCurrentDate() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DAY_FORMAT);
        LocalDate today = LocalDate.now();
        String nowDate = today.format(df);
        return nowDate;
    }

    /**
     * LocalDate转化为指定格式字符串
     *
     * @param fromDate
     * @param dateFormat
     * @return
     */
    public static String getLocalDate(LocalDate fromDate, String dateFormat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
        if (fromDate != null) {
            String dateStr = fromDate.format(df);
            return dateStr;
        }
        return null;

    }

    public static String getLocalDateTime(LocalDateTime fromDateTime, String dateTimeFotmat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateTimeFotmat);
        if (fromDateTime != null) {
            String localTime = fromDateTime.format(df);
            return localTime;
        }
        return null;

    }

    /**
     * 获得一年后的日期格式字符串
     */
    public static String getOneYearLaterDate(String beginDate, String dateFormat) {
        LocalDate fromDate = fromString2LocalDate(beginDate, dateFormat);
        if (fromDate != null) {
            LocalDate toDate = fromDate.plus(1, ChronoUnit.YEARS);
            return getLocalDate(toDate, dateFormat);
        }
        return null;

    }

    /**
     * 时间格式字符串转化为指定格式的时间
     */
    public static LocalDate fromString2LocalDate(String beginDate, String dateFormat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDate fromDate = LocalDate.parse(beginDate, df);
            return fromDate;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 时间格式字符串转化为指定格式的时间
     *
     * @return
     */
    public static LocalDateTime fromString2LocalDateTime(String beginDateTime, String dateFormat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(beginDateTime, df);
            return fromDateTime;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 获得毫秒数
     */
    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }


    /**
     * 根据出生日期(yyyy-MM-dd)字符串计算年龄
     */
    public static Integer getAgeByBirthDay(String birthDay) {
        LocalDate birthDate = fromString2LocalDate(birthDay, DAY_FORMAT);
        LocalDate currentDate = LocalDate.now();
        if (birthDate != null) {
            //判断birthDay是否合法
            if (currentDate.isBefore(birthDate)) {
                return 0;
            } else {
                int age = birthDate.until(currentDate).getYears();
                return age;
            }

        } else {
            return null;
        }

    }

    /**
     * Long类型时间戳转化为LocalDateTime
     */
    public static LocalDateTime fromLong2LocalDateTime(Long dateTimeLong) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateTimeLong), ZoneId.systemDefault());
        return dateTime;
    }

    /**
     * 获取本月第一天
     */
    public static LocalDate getFirstDayOfCurrentMonth() {
        LocalDate currentDay = LocalDate.now();
        return currentDay.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取本月最后一天
     */
    public static LocalDate getLastDayOfCurrentMonth() {
        LocalDate currentDay = LocalDate.now();
        return currentDay.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取当天开始时间 2019-06-12 00:00:00
     */
    public static LocalDateTime getTodayBeginTime() {
        LocalDate currentDay = LocalDate.now();
        return LocalDateTime.of(currentDay, LocalTime.MIN);
    }

    /**
     * 获取当天结束时间 2019-06-12 23:59:59
     */
    public static LocalDateTime getTodayEndTime() {
        LocalDate currentDay = LocalDate.now();
        return LocalDateTime.of(currentDay, LocalTime.MAX);
    }

    /**
     * 获取本周开始时间 2019-06-10 00:00:00
     */
    public static LocalDateTime getWeekBeginTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currentOrdinal = currentDateTime.getDayOfWeek().ordinal();
        return currentDateTime.minusDays(currentOrdinal)
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * 获取本周开始时间 2019-06-10 00:00:00
     */
    public static String getWeekBeginTimeString() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currentOrdinal = currentDateTime.getDayOfWeek().ordinal();
        LocalDateTime weekBeginDateTime = currentDateTime.minusDays(currentOrdinal)
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
        return getLocalDateTime(weekBeginDateTime, FULL_FORMAT);
    }

    /**
     * 获取本周结束时间 2019-06-16 23:59:59
     */
    public static LocalDateTime getWeekEndTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currentOrdinal = currentDateTime.getDayOfWeek().ordinal();
        return currentDateTime.plusDays(6 - currentOrdinal)
                .withHour(23).withMinute(59).withSecond(59).withNano(999999999);
    }

    /**
     * 获取本周结束时间字符串 2019-06-16 23:59:59
     */
    public static String getWeekEndTimeString() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currentOrdinal = currentDateTime.getDayOfWeek().ordinal();
        LocalDateTime weekEndDateTime = currentDateTime.plusDays(6 - currentOrdinal)
                .withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        return getLocalDateTime(weekEndDateTime, FULL_FORMAT);
    }


    public static void main(String[] args) {
        //当前时间
        String localDate = getLocalDate(LocalDate.now(), DAY_FORMAT);
        String localDateTime = getLocalDateTime(LocalDateTime.now(), FULL_FORMAT);
        // 获取当前时间  yyyy-MM-dd
        System.out.println("当前时间：" + getCurrentDate());
        // 获取指定格式的时间
        System.out.println("指定格式的时间：" + getLocalDate(LocalDate.now(), DAY_FORMAT));
        System.out.println("指定格式的时间：" + getLocalDateTime(LocalDateTime.now(), FULL_FORMAT));
        // 获取一年后的时间
        System.out.println("一年后的时间：" + getOneYearLaterDate(localDate, DAY_FORMAT));
        // 时间格式字符串转化为指定格式的时间
        System.out.println("时间格式字符串转化为指定格式的时间：" + fromString2LocalDate(localDate, DAY_FORMAT));
        // 时间格式字符串转化为指定格式的时间
        System.out.println("时间格式字符串转化为指定格式的时间：" + fromString2LocalDateTime(localDateTime, FULL_FORMAT));
        // 获得毫秒数
        System.out.println("获得毫秒数：" + getTimestampOfDateTime(LocalDateTime.now()));
        // 根据出生日期(yyyy-MM-dd)字符串计算年龄
        String birthday = "2001-06-21";
        System.out.println("根据出生日期(yyyy-MM-dd)字符串计算年龄：" + getAgeByBirthDay(birthday));
        // 获取本月第一天
        System.out.println("获取本月第一天：" + getFirstDayOfCurrentMonth());
        // 获取本月最后一天
        System.out.println("获取本月最后一天：" + getLastDayOfCurrentMonth());
        // 获取当天开始时间
        System.out.println("获取当天开始时间：" + getTodayBeginTime());
        // 获取当天结束时间
        System.out.println("获取当天结束时间：" + getTodayEndTime());
        // 获取本周开始时间
        System.out.println("获取本周开始时间：" + getWeekBeginTime());
        // 获取本周开始时间字符串
        System.out.println("获取本周开始时间字符串：" + getWeekBeginTimeString());
        //获取本周结束时间
        System.out.println("获取本周结束时间：" + getWeekEndTime());
        // 获取本周结束时间字符串
        System.out.println("获取本周结束时间字符串：" + getWeekEndTimeString());


    }

}
