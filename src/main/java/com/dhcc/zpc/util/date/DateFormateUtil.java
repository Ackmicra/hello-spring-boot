package com.dhcc.zpc.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormateUtil {

    public static String  datetime(String b){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(b);
        Date date = new Date();
        return  simpleDateFormat.format(date);

    }
}
