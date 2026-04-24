package com.example.postcommentapi.resources.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

    public static String decodeParam(String text) {
        try {
            return java.net.URLDecoder.decode(text, java.nio.charset.StandardCharsets.UTF_8.name());
        } catch (java.io.UnsupportedEncodingException e) {
            return "";
        }
    }

    public static Date convertDate(String textDate, Date defaultValue) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            return sdf.parse(textDate);
        } catch (ParseException e ) {
            return defaultValue;
        }
    }
}
