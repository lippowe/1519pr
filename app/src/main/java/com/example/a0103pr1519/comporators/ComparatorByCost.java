package com.example.a0103pr1519.comporators;

import com.example.a0103pr1519.data.Services;

import java.util.Comparator;

public class ComparatorByCost implements Comparator<Services> {
    @Override
    public int compare(Services o1, Services o2) {
        return Integer.compare(o1.getCost(), o2.getCost());
    }
}
