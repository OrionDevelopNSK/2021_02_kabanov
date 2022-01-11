package ru.cft.focusstart.task1.controller;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Validator {

    private int sizeTable;
    private final int MAX_TABLE_SIZE = 32;    //  можно сделать локальной, но тогда при смене значения придется корректировать метод

    public Validator() {
        inputTableSize();
    }

    public int getSizeTable() {
        return sizeTable;
    }

    private void inputTableSize(){
        System.out.println("Введите размер таблицы от 1 до " + MAX_TABLE_SIZE + ": ");
        Scanner in = new Scanner(System.in);
        //  не используется try-с ресурсами для возможности повторного запуска метода без повторного запуска программы
        //  в случае некорректного введенного числа
        try{
            //  сделано через parseInt специально чтобы можно было отслеживать невведенные значения (пустые строки)
            sizeTable = Integer.parseInt(in.nextLine());
            if (sizeTable > MAX_TABLE_SIZE){
                System.out.println("Вы указали слишком большое число\n");
                inputTableSize();
            }
            if (sizeTable < 0){
                System.out.println("Размер таблицы не может быть отрицательным\n");
                inputTableSize();
            }
            if (sizeTable == 0){
                System.out.println("Размер таблицы не может быть нулевым\n");
                inputTableSize();
            }
        }catch (NoSuchElementException | NumberFormatException e){
            System.out.println("Вы указали не число, либо число слишком большого размера\n");
            inputTableSize();
        }finally {
            in.close();
        }
    }
}
