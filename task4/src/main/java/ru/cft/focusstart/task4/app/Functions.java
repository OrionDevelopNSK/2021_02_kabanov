package ru.cft.focusstart.task4.app;

public class Functions {
    public static double functionOne(long start, long end){
        double number = 0;
        for (long n = start; n < end; n++) {
            number += 1 / Math.pow(2, n);
        }
        return number;
    }

    public static double functionTwo(long start, long end){
        double number = 0;
        for (long n = start; n < end; n++) {
            if (n == 0) continue;
            number += 1 / Math.pow(n, 2);
        }
        return number;
    }

    public static double functionThree(long start, long end){
        double number = 0;
        for (long n = start; n < end; n++) {
            if (n == 0) continue;
            number = number + 1 / (double)(n * (n + 1));
        }
        return number;
    }

    public static double functionFour(long start, long end){
        double number = 0;
        final double q = 0.5;   //так как в формуле итерации q не происходит, то q указана как константа

        for (long n = start; n < end; n++) {
            number += Math.pow(q, n);
        }
        return number;
    }



}
