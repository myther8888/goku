package com.yongle.goku.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author weinh
 */
public class DateUtils {

    public static String getDateTime10() {
        return getDateTime10(null);
    }

    public static String getDateTime10(Date date) {
        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
