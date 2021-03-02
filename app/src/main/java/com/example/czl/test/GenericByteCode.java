package com.example.czl.test;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GenericByteCode {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        String element = list.get(0);
        Log.e("GenericByteCode", "main: " + element);
    }
}
