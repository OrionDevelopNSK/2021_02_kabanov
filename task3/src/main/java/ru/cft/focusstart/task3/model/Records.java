package ru.cft.focusstart.task3.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task3.view.GameType;

import java.util.List;

public final class Records {
    private static final Logger log = LoggerFactory.getLogger(Records.class);

    private final HighScoresTables highScoresTables;
    private RecordListener recordListenerNovice;
    private RecordListener recordListenerMedium;
    private RecordListener recordListenerExpert;

    public Records(HighScoresTables highScoresTables) {
        this.highScoresTables = highScoresTables;
    }

    public void setRecordListenerNovice(RecordListener listener) {
        this.recordListenerNovice = listener;
    }

    public void setRecordListenerMedium(RecordListener listener) {
        this.recordListenerMedium = listener;
    }

    public void setRecordListenerExpert(RecordListener listener) {
        this.recordListenerExpert = listener;
    }

    public void initialize(){
        log.debug("Подписки результатов рекордов и имен рекордсменов для обновления View");
        recordListenerNovice.onRecordChanged(loadNamePlayer(GameType.NOVICE), getScore(GameType.NOVICE));
        recordListenerMedium.onRecordChanged(loadNamePlayer(GameType.MEDIUM), getScore(GameType.MEDIUM));
        recordListenerExpert.onRecordChanged(loadNamePlayer(GameType.EXPERT), getScore(GameType.EXPERT));
    }

    public int getScore(GameType gameType) {
        log.debug("Получение результатов поставленного рекорда для сложности {}", gameType);
        List<String> table = highScoresTables.getTableData(gameType);
        try {
            return Integer.parseInt(table.get(0).split(" ")[0]);
        } catch (NumberFormatException e) {
            log.error("Не удалось преобразовать данные из файла {} в число", gameType);
            return 0;
        }
    }

    public void setScoreAndName(GameType gameType, String name, int time){
        log.debug("Установка рекордсмена {} для сложности {}, время {}", name, gameType, time);
        highScoresTables.setTableData(gameType, List.of(time + " " + name));
    }

    private String loadNamePlayer(GameType gameType) {
        log.debug("Получение имени рекордсмена для сложности {}", gameType);
        return highScoresTables.getTableData(gameType).get(0).split(" ")[1];
    }

}
