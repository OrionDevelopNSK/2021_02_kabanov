package ru.cft.focusstart.task4.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.*;

public class TasksController {
    private final static Logger log = LoggerFactory.getLogger(TasksController.class);

    private double result;
    private final long[] partsNumberOfIterations;
    private final int processorCoreCount;
    private final ArrayList<Future<Double>> tasks = new ArrayList<>();

    public TasksController(long[] partsNumberOfIterations, int processorCoreCount) {
        this.partsNumberOfIterations = partsNumberOfIterations;
        this.processorCoreCount = processorCoreCount;
    }

    private void startAllTasks(Function function) throws InterruptedException, ExecutionException {
        log.info("Запуск всех задач");
        ExecutorService pool = Executors.newFixedThreadPool(processorCoreCount);
        for (int i = 0; i < partsNumberOfIterations.length - 1; i++) {
            startTask(pool, function, partsNumberOfIterations[i], partsNumberOfIterations[i + 1]);
        }
        pool.shutdown();
        calculateFinalResult();
    }

    private void calculateFinalResult() throws InterruptedException, ExecutionException {
        for(Future<Double> f : tasks){
            sumResult(f.get());
        }
    }

    private void startTask(ExecutorService pool, Function function, long start, long end) {
        Callable<Double> task = () -> {
            log.info("Запуск части вычислений в потоке {}", Thread.currentThread().getName());
            return function.function(start, end);
        };
        tasks.add(pool.submit(task));
    }

    private synchronized void sumResult(double sum) {
        result += sum;
    }

    public void functionSelection(int numberOfFunction) throws InterruptedException, ExecutionException {
        switch (numberOfFunction) {
            case 1 -> {
                startAllTasks(Functions::functionOne);
                log.info("Ожидаемый результат выполнения первой функции должен быть равен 2, фактически {} ",
                        result);
            }
            case 2 -> {
                startAllTasks(Functions::functionTwo);
                log.info("Ожидаемый результат выполнения второй функции должен стремиться к 1.64, фактически {} ",
                        result);
            }
            case 3 -> {
                startAllTasks(Functions::functionThree);
                log.info("Ожидаемый результат выполнения третьей функции должен стремиться к 1, фактически {} ",
                        result);
            }
            case 4 -> {
                startAllTasks(Functions::functionFour);
                log.info("Ожидаемый результат выполнения четвертой функции функции, при q = 0,5 должен быть равен 2, фактически {} ",
                        result);
            }
        }
    }
}
