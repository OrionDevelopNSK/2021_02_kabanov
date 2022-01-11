package ru.cft.focusstart.task4.app;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleReader {
    private long numberOfIterations;
    private int functionNumber;

    public ConsoleReader() {
        read();
    }

    public long getNumberOfIterations() {
        return numberOfIterations;
    }

    public int getFunctionNumber() {
        return functionNumber;
    }

    private void read() {
        try (Scanner in = new Scanner(System.in)) {
            inputNumber(in);
            inputFunctionNumber(in);
        }
    }

    private void inputNumber(Scanner in) {
        try {
            System.out.println("Введите число: ");
            numberOfIterations = Long.parseLong(in.nextLine());
        } catch (NoSuchElementException | NumberFormatException e) {
            System.err.println("Вы указали не число, либо число слишком большого размера\n");
            inputNumber(in);
        }
    }

    private void inputFunctionNumber(Scanner in) {
        System.out.println("Введите номер функции от 1 до 4: ");
        try {
            functionNumber = Integer.parseInt(in.nextLine());
            if (functionNumber < 1 || functionNumber > 4) {
                System.err.println("Вы указали некорректное число\n");
                inputFunctionNumber(in);
            }
        } catch (NoSuchElementException | NumberFormatException e) {
            System.err.println("Вы указали не число, либо число слишком большого размера\n");
            inputFunctionNumber(in);
        }
    }




}
