package ru.cft.focusstart.task3.view;

public interface HighScoresWindowInterface {
    void setNoviceRecord(String winnerName, int timeValue);

    void setMediumRecord(String winnerName, int timeValue);

    void setExpertRecord(String winnerName, int timeValue);

    String createRecordText(String winnerName, int timeValue);
}
