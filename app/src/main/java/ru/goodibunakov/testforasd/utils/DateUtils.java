package ru.goodibunakov.testforasd.utils;

public class DateUtils {

    public static String convertDateToFrandlyFormat(String input) {
//        Fri, 15 Feb 2019 22:07:00 +0300
        return input.split(", ")[1].substring(0, input.length() - 20);
    }
}
