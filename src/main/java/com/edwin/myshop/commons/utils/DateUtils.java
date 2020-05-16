package com.edwin.myshop.commons.utils;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DateUtils {

    /**
     * 日期格式
     */
    public static final String FORMAT1 = "yyyyMMddHHmmss";
    public static final String FORMAT2 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT3 = "yyyy-MM-dd";
    public static final String FORMAT4 = "yyyy/MM/dd";
    public static final String FORMAT5 = "yyyy-MM";
    public static final String TIMEZONE = "GMT+8";

    public static final String PATTERNFORMAT3 = "\\d{4}(\\-|\\/|.)\\d{2}\\1\\d{2}";
    public static final String PATTERNFORMAT2 = "\\d{4}(\\-|\\/|.)\\d{2}\\1\\d{2} \\d{2}:\\d{2}:\\d{2}";

    /**
     * 获取当前unix时间戳
     *
     * @return
     */
    public static long getNowTimeUnix() {
        return System.currentTimeMillis() / 1000L;
    }

    /**
     * 获取当前年月日时分秒
     *
     * @return
     */
    public static String getNowDateTime() {
        Date date = new Date();
        String nowDate = new SimpleDateFormat(FORMAT2).format(date);
        return nowDate;
    }

    /**
     * 自定义格式获取当前时间
     *
     * @param format
     * @return
     */
    public static String getNowDate(String format) {
        Date date = new Date();
        String nowDate = new SimpleDateFormat(format).format(date);
        return nowDate;
    }

    /**
     * 返回当前日期，去除时分秒
     *
     * @return
     */
    public static Date getNowOnlyDay() {
        String now = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(now, "yyyy-MM-dd");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 获取任意时间的下一个月
     * 描述:<描述函数实现的功能>.
     *
     * @param repeatDate
     * @return
     */
    public static String getPreMonth(String repeatDate) {
        String lastMonth = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        int year = Integer.parseInt(repeatDate.substring(0, 4));
        String monthsString = repeatDate.substring(5, 7);
        int month;
        if ("0".equals(monthsString.substring(0, 1))) {
            month = Integer.parseInt(monthsString.substring(1, 2));
        } else {
            month = Integer.parseInt(monthsString.substring(0, 2));
        }
        cal.set(year, month, Calendar.DATE);
        lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    /**
     * yyyy-MM-dd 格式的时间补齐 为  yyyy-MM-dd HH:mm:ss
     *
     * @param format2time
     * @return
     */
    public static String Format3ConvertFormat2(String format2time) {

        Preconditions.checkArgument(Util.isNotEmpty(format2time), "转换的时间不能为空");
        StringBuffer stringBuffer = new StringBuffer(format2time);
        stringBuffer.append(" 00:00:00");
        return stringBuffer.toString();
    }

    /**
     * 是否是周末
     *
     * @return
     */
    public static String idWeek(String datetime) {
        final String dayNames[] = {"true", "星期一", "星期二", "星期三", "星期四", "星期五", "true"};
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        try {
            date = sdfInput.parse(datetime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayNames[dayOfWeek - 1];
    }

    /**
     * 添加日
     * 时间格式为 yyyy-MM-dd HH:mm:ss
     *
     * @param datetime
     * @return
     */
    public static String plusDay(String datetime, int days) {
        String format = "";
        Pattern p2 = Pattern.compile(PATTERNFORMAT2);
        Matcher m2 = p2.matcher(datetime);
        Pattern p3 = Pattern.compile(PATTERNFORMAT3);
        Matcher m3 = p3.matcher(datetime);
        if (m2.matches()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DateUtils.FORMAT2);
            LocalDateTime parse = LocalDateTime.parse(datetime, dtf);
            format = parse.plusDays(days).format(dtf);
        } else if (m3.matches()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DateUtils.FORMAT3);
            LocalDate parse = LocalDate.parse(datetime, dtf);
            format = parse.plusDays(days).format(dtf);
        }
        return format;
    }

    /**
     * 添加月
     * 时间格式为 yyyy-MM-dd HH:mm:ss
     *
     * @param datetime
     * @param months
     * @return
     */
    public static String plusMonth(String datetime, int months) {
        String format = "";
        Pattern p2 = Pattern.compile(PATTERNFORMAT2);
        Matcher m2 = p2.matcher(datetime);
        Pattern p3 = Pattern.compile(PATTERNFORMAT3);
        Matcher m3 = p3.matcher(datetime);
        if (m2.matches()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DateUtils.FORMAT2);
            LocalDateTime parse = LocalDateTime.parse(datetime, dtf);
            format = parse.plusMonths(months).format(dtf);
        } else if (m3.matches()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DateUtils.FORMAT3);
            LocalDate parse = LocalDate.parse(datetime, dtf);
            format = parse.plusMonths(months).format(dtf);
        }

        return format;
    }

    /**
     * 添加分
     * 时间格式为 yyyy-MM-dd HH:mm:ss
     *
     * @param datetime
     * @param minutes
     * @return
     */
    public static String plusMinute(String datetime, int minutes) {
        String format = "";
        Pattern p2 = Pattern.compile(PATTERNFORMAT2);
        Matcher m2 = p2.matcher(datetime);
        if (m2.matches()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DateUtils.FORMAT2);
            LocalDateTime parse = LocalDateTime.parse(datetime, dtf);

            return parse.plusMinutes(minutes).format(dtf);
        }
        return "";

    }

    /**
     * 添加小时
     * 时间格式为 yyyy-MM-dd HH:mm:ss
     *
     * @param datetime
     * @param hours
     * @return
     */
    public static String plusHour(String datetime, int hours) {
        String format = "";
        Pattern p2 = Pattern.compile(PATTERNFORMAT2);
        Matcher m2 = p2.matcher(datetime);
        if (m2.matches()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DateUtils.FORMAT2);
            LocalDateTime parse = LocalDateTime.parse(datetime, dtf);
            format = parse.plusHours(hours).format(dtf);
        }

        return format;
    }

    public static String convertDateFormat(String datetime, String oldFormat, String newFormat) {
        Date date = null;
        try {
            date = new SimpleDateFormat(oldFormat).parse(datetime);
        } catch (ParseException e) {
            date = new Date();
        }
        String nowDate = new SimpleDateFormat(newFormat).format(date);
        return nowDate;
    }

    /**
     * 比较俩个日期大小
     *
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compare_date(String DATE1, String DATE2) {

        int num = 0;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() >= dt2.getTime()) {
                num = 1;
 	            /*} else if (dt1.getTime() <= dt2.getTime()) {
 	                System.out.println("dt1在dt2后");
 	                return -1; */
            } else {
                num = 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return num;
    }

    /**
     * 参数是Date类型 比较大小
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compare_date(Date date1, Date date2) {
        int num = 0;
        if (date1.getTime() >= date2.getTime()) {
            num = 1;
        }
        return num;
    }

    /**
     * 参数是Date类型
     * 比较两个date的大小 用于清洁单同步和佣金计算时使用
     * 例如 submitDate和resignDate的比较
     *
     * @param date1
     * @param date2
     */
    public static int compareDateForSubjectionQuery(Date date1, Date date2) {
        int num = 0;
        if (date1.getTime() > date2.getTime()) {
            num = 1;
        }
        return num;
    }

    /**
     * 比较两个date的大小 用于清洁单同步和佣金计算时使用
     * 例如 submitDate和resignDate的比较
     *
     * @param date1
     * @param date2
     */
    public static int compareDateForSubjectionQuery(String date1, String date2) {
        int num = 0;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                num = 1;
            }
        } catch (Exception e) {
            num = 0;
        }
        return num;
    }

    /**
     * 字符串转日期
     *
     * @param dateStr
     * @return
     */
    public static Date StrToDate(String dateStr) {
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdfInput.parse(dateStr);
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    /**
     * 字符串转日期
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date StrToDate(String dateStr, String pattern) {
        SimpleDateFormat sdfInput = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = sdfInput.parse(dateStr);
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToStr(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateStr = null;
        try {
            dateStr = simpleDateFormat.format(date);
        } catch (Exception e) {
            dateStr = "";
        }
        return dateStr;
    }

    /**
     * 获取指定日期减去一天
     *
     * @param nowDate
     * @return
     */
    public static String beforeDate(String nowDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal;
        try {
            Date d = df.parse(nowDate);
            cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.DATE, -1);  //减1天
        } catch (Exception e) {
            return null;
        }
        return df.format(cal.getTime());
    }

    /**
     * 获取指定日期减去一天的date类型的数据
     *
     * @param nowDate
     * @return
     */
    public static Date beforeSpecialDate(String nowDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(beforeDate(nowDate));
        } catch (Exception e) {
            return null;
        }

    }


    /**
     *
     * @param nowDate 以这个时间为基准
     * @return judgeDate 需要判断的日期
     */
    public static boolean isLastWeek(Date nowDate,String judgeDate){
        Date date2=StrToDate(judgeDate,"yyyy-MM-dd");
        Calendar calendar1=Calendar.getInstance();
        calendar1.setTime(nowDate);
        int lastWeek=calendar1.get(Calendar.WEEK_OF_YEAR);
        Calendar calendar2=Calendar.getInstance();
        calendar2.setTime(date2);
        int judgeWeek=calendar2.get(Calendar.WEEK_OF_YEAR);
        if(lastWeek==judgeWeek){
            return true;
        }
        return false;
    }
    /**
     * 在原日期字符串表示的日期上加减天数
     *
     * @param dateString 日期字符串
     * @param format     日期格式
     * @param days       加为正数，减为负数
     * @return
     */
    public static String addDays(String dateString, String format, int days) {
        if (StringUtils.isBlank(dateString)) {
            return "";
        }

        try {
            Date date = org.apache.commons.lang3.time.DateUtils.parseDate(dateString, format);
            date = org.apache.commons.lang3.time.DateUtils.addDays(date, days);

            return DateFormatUtils.format(date, format);
        } catch (ParseException e) {
            throw new RuntimeException(String.format("日期格式转换错误！值[%s]", dateString));
        }
    }


    public static Date str2Date(String datetime, String format) {
        if (Util.isNotEmpty(datetime)) {

            SimpleDateFormat df = new SimpleDateFormat(format);
            if (Util.checkChinese(datetime)) {
                return null;
            } else if (datetime.matches("^\\d{5}$")) {
                String s = plusDay(plusDay("1900-01-01", Integer.valueOf(datetime)), -2);
                try {
                    if (format.equals(FORMAT2)) {
                        s += " 00:00:00";
                    }
                    return df.parse(s);
                } catch (ParseException e) {
                    log.error("时间转换异常");
                }
            } else if (datetime.matches(PATTERNFORMAT3) || datetime.matches(PATTERNFORMAT2)) {
                try {
                    if (datetime.matches(PATTERNFORMAT3) && format.equals(FORMAT2)) {
                        datetime += " 00:00:00";
                    }

                    if (datetime.matches(PATTERNFORMAT2) && format.equals(FORMAT3)) {
                        datetime = datetime.substring(0, 10);
                    }


                    return df.parse(datetime);
                } catch (ParseException e) {
                    log.error("时间转换异常");
                }
            } else {
                return null;
            }


        } else {
            return null;
        }
        return null;
    }


    public static void main(String args[]) throws Exception {
        System.out.println(dateToStr(str2Date("2018-08-08 12:12:12", FORMAT3), FORMAT2));
    }


}
