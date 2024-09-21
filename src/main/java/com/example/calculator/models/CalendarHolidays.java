package com.example.calculator.models;

import java.util.*;


public class CalendarHolidays {
    private static final List<Calendar> holidays;

    static {
        holidays = new ArrayList<>();
        for (int day = 1; day <= 8; day++) {
            holidays.add(new GregorianCalendar(2024, Calendar.JANUARY, day)); // Новогодние праздники
        }
        holidays.add(new GregorianCalendar(2024, Calendar.FEBRUARY, 23));
        holidays.add(new GregorianCalendar(2024, Calendar.MARCH, 8));
        holidays.add(new GregorianCalendar(2024, Calendar.MAY, 1));
        holidays.add(new GregorianCalendar(2024, Calendar.MAY, 9));
        holidays.add(new GregorianCalendar(2024, Calendar.JUNE, 12));
        holidays.add(new GregorianCalendar(2024, Calendar.NOVEMBER, 4));
    }


    public static boolean contains(Calendar someDay) {
        return holidays.contains(someDay);

    }


}

