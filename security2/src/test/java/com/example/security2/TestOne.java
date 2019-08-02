package com.example.security2;

import com.example.security2.filter.BeforeLoginFilter;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import java.util.concurrent.TimeUnit;

public class TestOne {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(BeforeLoginFilter.base64Encode("399393:1111".getBytes()));

        ExpiringMap<String, String> map = ExpiringMap.builder().expiration(5000, TimeUnit.MILLISECONDS)
                .expirationPolicy(ExpirationPolicy.ACCESSED)
                .build();
        map.put("key","value");
       // System.out.println("key:"+map.get("key"));
        Thread.sleep(3001);
        System.out.println(map.containsKey("key"));
        //等待5秒
        Thread.sleep(3001);
        System.out.println(map.get("key"));
    }
}
