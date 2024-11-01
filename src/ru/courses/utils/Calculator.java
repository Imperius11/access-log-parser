package ru.courses.utils;

import ru.courses.math.Measurable;

public class Calculator {
    public static double sumAll(Measurable... items) {
        double sum = 0;
        for (Measurable item : items) {
            sum += item.getLength();
        }
        return sum;
    }
}
