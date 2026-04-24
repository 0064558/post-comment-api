package com.example.postcommentapi.resources.util;

public class URL {
    public static String decodeParam(String text) {
        try {
            return java.net.URLDecoder.decode(text, java.nio.charset.StandardCharsets.UTF_8.name());
        } catch (java.io.UnsupportedEncodingException e) {
            return "";
        }
    }
}
