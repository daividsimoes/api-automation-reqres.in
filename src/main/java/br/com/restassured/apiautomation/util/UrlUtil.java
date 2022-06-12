package br.com.restassured.apiautomation.util;

import java.text.MessageFormat;

public class UrlUtil {

    private static final String HOST = "https://reqres.in";

    public static String buildUrl(String endpoint, Object... args) {

        return HOST.concat(MessageFormat.format(endpoint, args));
    }
}
