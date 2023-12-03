package com.example.a0103pr1519.comporators;

import android.annotation.SuppressLint;

import com.example.a0103pr1519.data.Orders;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class ComparatorByDate implements Comparator<Orders> {
    @Override
    @SuppressLint("SimpleDateFormat")
    public int compare(Orders o1, Orders o2) {
//        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
//        try {
//            Date date1 = format.parse(o1.getDate());
//            Date date2 = format.parse(o2.getDate());
//            return date2 - date1;
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }

        return Integer.compare(Integer.parseInt(o1.getDate().split("\\.")[2]), Integer.parseInt(o2.getDate().split("\\.")[2]));
    }
}
