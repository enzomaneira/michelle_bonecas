package com.maneira.mongoproject.demo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {
    public static String decodeParam(String text) {
        if (text == null) {
            return null;
        }
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

    public static Double convertDouble(String in, Double defaultValue) {
        System.out.println("Valor de entrada: " + in);
        if (in == null) {
            System.out.println("Valor de entrada nulo. Usando valor padrão: " + defaultValue);
            return defaultValue;
        }

        String trimmedValue = in.trim();
        System.out.println("Valor após trim: " + trimmedValue);
        if (trimmedValue.isEmpty()) {
            System.out.println("Valor de entrada vazio. Usando valor padrão: " + defaultValue);
            return defaultValue;
        }

        try {
            return Double.parseDouble(trimmedValue);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter valor para Double: " + trimmedValue);
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