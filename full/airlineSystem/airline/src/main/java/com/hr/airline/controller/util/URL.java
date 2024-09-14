package com.hr.airline.controller.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URL {
     public static String decodeParam(String text) {
        try {
            return URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
