package com.example.security2.util;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import sun.misc.BASE64Encoder;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CommonUtil {
    static ExpiringMap<String, String> map = ExpiringMap.builder().expiration(5000, TimeUnit.MILLISECONDS)
            .expirationPolicy(ExpirationPolicy.ACCESSED)
            .build();

    public static boolean containUser(String name, String pw) {
        return map.containsKey(name + ":" + pw);
    }

    public static String addUser(String name, String pw) {
        String token = CommonUtil.base64Encode((name + ":" + pw).getBytes());
        map.put(token, "");
        return token;
    }

    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

    public static String base64Encode(byte[] bstr) {
        String strEncode = new BASE64Encoder().encode(bstr);
        return strEncode;
    }

    public static void main(String[] args) {
        System.out.println(get32UUID());
    }
}

