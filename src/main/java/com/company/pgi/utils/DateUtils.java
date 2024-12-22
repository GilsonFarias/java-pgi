package com.company.pgi.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateUtils {
    public static String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
