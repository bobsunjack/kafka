package com.example.security2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListSort {
    public static void main(String[] args) {
        Long one = new Long(10);
        long two = one;
        two = two - 10;
        System.out.println(one);
        System.out.println(two);

        List<String> data = new ArrayList<>();
        data.add("234");
        data.add("23");
        data.add("4545");
        Collections.sort(data,new Comparator<String>() {
            public int compare(String arg0, String arg1) {
                return arg0.compareTo(arg1);
            }
        });
        for (String item : data) {
            System.out.println(item);
        }
    }
}
