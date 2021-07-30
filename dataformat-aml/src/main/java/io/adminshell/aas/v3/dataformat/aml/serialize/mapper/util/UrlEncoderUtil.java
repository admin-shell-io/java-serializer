package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UrlEncoderUtil {

    public static String encode(String url) {
        return URLEncoder.encode(url, StandardCharsets.UTF_8);
    }
}
