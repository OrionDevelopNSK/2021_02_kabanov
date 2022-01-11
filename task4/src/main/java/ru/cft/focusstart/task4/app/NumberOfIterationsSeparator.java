package ru.cft.focusstart.task4.app;



public class NumberOfIterationsSeparator {

    private final int processorCoreCount;
    private final long numberOfIterations;

    public NumberOfIterationsSeparator(int processorCoreCount, long numberOfIterations) {
        this.processorCoreCount = processorCoreCount;
        this.numberOfIterations = numberOfIterations;
    }

    public long[] getPartsNumberOfIterations() {
        long numberOfIterations = this.numberOfIterations;

        //  +1 указано для обеспечения хранения нуля в первом индексе массива
        long[] partsNumberOfIterations = new long[processorCoreCount + 1];
        long partsOfNumber = numberOfIterations / processorCoreCount;
        long tmp = 0;
        for (int i = 1; i < processorCoreCount; i++) {
            tmp += partsOfNumber;
            partsNumberOfIterations[i] = tmp;
        }

        //  в случае разбиения большого числа не кратного числу ядер процессора - гарантирует захват правой границы
        //  +1 указано для обеспечения захвата правой границы диапазона итераций, после передачи в методы класса Functions
        partsNumberOfIterations[processorCoreCount] = numberOfIterations + 1;
        return partsNumberOfIterations;
    }
}