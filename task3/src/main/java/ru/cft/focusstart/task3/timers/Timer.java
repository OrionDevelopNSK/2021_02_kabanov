package ru.cft.focusstart.task3.timers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

public class Timer extends AbstractTimer {
    private static final Logger log = LoggerFactory.getLogger(Timer.class);

    private int seconds;
    private TimerTask timerTask;

    @Override
    public void startTimer() {
        seconds = 0;
        log.debug("Запуск таймера");
        timerTask = new TimerTask() {
            @Override
            public void run() {
                seconds++;
                timerTickListener.onTick(seconds);
            }
        };
        java.util.Timer timer = new java.util.Timer("Timer");
        timer.scheduleAtFixedRate(timerTask,1000, 1000);
    }

    @Override
    public void stopTimer(){
        log.debug("Остановка таймера");
        if (timerTask != null) timerTask.cancel();
        timerTask = null;
    }

    @Override
    public int getTime(){
        return seconds;
    }
}
