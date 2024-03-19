package com.maneira.mongoproject.demo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

    public static String decodeParam(String text) {
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static Date convertDate(String textDate, Date defaultValue) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            return sdf.parse(textDate);
        } catch (ParseException e) {
            return defaultValue;
        }
    }

    public static Double convertDouble(String textDouble, Double defaultValue) {
        try {
            return Double.parseDouble(textDouble);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    public static Integer convertInteger(String textInteger, Integer defaultValue) {
        try {
            return Integer.parseInt(textInteger);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


}