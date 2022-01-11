package ru.cft.focusstart.task4.app;

public class ProcessorCoreController {
    private final int coreCount;

    public ProcessorCoreController() {
        coreCount = Runtime.getRuntime().availableProcessors();
    }

    public int getProcessorCoreCount(){
        return coreCount;
    }
}
