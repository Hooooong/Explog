package com.hongsup.explog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Hong on 2017-12-10.
 */

public class DateUtil {

    private final static String DATE_PATTERN = "yyyy.MM.dd";

    /**
     * 현재 날짜를 String Type(YYYY.MM.dd) 으로 변환하는 메소드
     * @return
     */
    public static String currentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        return sdf.format(System.currentTimeMillis());
    }

    /**
     * String Type(YYYY.MM.dd) 을 long Type 으로 변환하는 날짜 메소드
     * @param string_date
     * @return
     */
    public static long getConvertDate(String string_date) {
        long long_date = 0;
        SimpleDateFormat f = new SimpleDateFormat(DATE_PATTERN);
        try {
            Date d = f.parse(string_date);
            long_date = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return long_date;
    }

    /**
     * long Type 을 String Type(YYYY.MM.dd) 으로 변환하는 날짜 메소드
     * @param long_date
     * @return
     */
    public static String getConvertDate(long long_date) {
        String string_date;
        Date date = new Date(long_date);
        SimpleDateFormat df2 = new SimpleDateFormat(DATE_PATTERN);
        string_date = df2.format(date);
        return string_date;
    }


    public static String getConvertDate(String start_date, String end_date){
        String startData = start_date.substring(0,10).replace("-",".");
        String endData ="";
        if(end_date != null){
            endData = "-"+end_date.substring(0,10).replace("-",".");
        }
        return startData+endData;
    }
}
