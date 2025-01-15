package com.wang.common.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期的工具类
 * @author Zjx
 */
@Component
public class DateUtil {
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_ALL_FORMAT = "yyyyMMddHHmmssS";
    public static final String DATE_MONTH_FORMAT = "yyyy-MM";

    public Date strToDateTime(String now) {
        if (now != null && !"".equalsIgnoreCase(now)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
                return sdf.parse(now);
            } catch (ParseException e) {
            }
        }
        return null;
    }

    public Date strToDate(String now) {
        if (now != null && !"".equalsIgnoreCase(now)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                return sdf.parse(now);
            } catch (ParseException e) {
            }
        }
        return null;
    }

    public Date strToDate(String now, String pattern) {
        if (now != null && !"".equalsIgnoreCase(now)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                return sdf.parse(now);
            } catch (ParseException e) {
            }
        }
        return null;
    }

    public String dateTimeToStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
        return sdf.format(date);
    }

    public String dateToStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    public String dateToStr(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
