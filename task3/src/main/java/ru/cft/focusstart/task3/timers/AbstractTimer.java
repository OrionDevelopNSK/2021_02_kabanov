package ru.cft.focusstart.task3.timers;

public abstract class AbstractTimer {
    protected TimerTickListener timerTickListener;
    public abstract void startTimer();
    public abstract void stopTimer();
    public abstract int getTime();

    public void setTimerTickListener(TimerTickListener listener) {this.timerTickListener = listener;}



}
