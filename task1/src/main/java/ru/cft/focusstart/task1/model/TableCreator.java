package ru.cft.focusstart.task1.model;

public class TableCreator {
    private final String HORIZONTAL_LINE = "-";
    private final String VERTICAL_LINE = "|";
    private final String PLUS = "+";
    private final String SPACE = " ";
    private final String NEW_LINE = "\n";
    private final int TABLE_SIZE;
    private final int  WIDTH_FIRST_COLUMN ;
    private final int WIDTH_ALL_COLUMNS_WITHOUT_FIRST;
    private StringBuilder table;

    public TableCreator(int tableSize) {
        this.TABLE_SIZE = tableSize;
        WIDTH_FIRST_COLUMN = getWidthFirstColumn();
        WIDTH_ALL_COLUMNS_WITHOUT_FIRST = getWidthAllColumnsWithoutFirst();
        createdTable();
    }

    private int getWidthFirstColumn(){
        return (int)Math.log10(TABLE_SIZE) + 1;
    }

    private int getWidthAllColumnsWithoutFirst(){
        return (int)Math.log10(TABLE_SIZE * TABLE_SIZE) + 1;
    }

    private int getSizeStringBuilder(){
        int countSeparatorPlus = TABLE_SIZE;
        int tableHeight = TABLE_SIZE * 2 + 2;
        int countNewLines = TABLE_SIZE * 2 + 2;
        return  (TABLE_SIZE * getWidthAllColumnsWithoutFirst() + countSeparatorPlus + getWidthFirstColumn())
                * tableHeight + countNewLines;
    }

    public StringBuilder getTable() {
        return table;
    }

    private void createdTable() {
        table = new StringBuilder(getSizeStringBuilder());
        createFirstLine();
        for (int i = 1; i < TABLE_SIZE + 1; i++) {
            createFirstColumnOnLine(i);
            table.append(VERTICAL_LINE);
            for (int j = 1; j < TABLE_SIZE + 1; j++) {
                createColumnOnLineWithoutFirst(i * j);
                table.append(VERTICAL_LINE);
            }
            table.delete(table.length() - 1, table.length());
            table.append(NEW_LINE);
            createHorizontalSeparator();
            table.append(NEW_LINE);
        }
    }

    private void createFirstLine() {
        createFirstColumnOnLine(0);
        table.append(VERTICAL_LINE);
        for (int i = 1; i < TABLE_SIZE + 1; i++) {
            createColumnOnLineWithoutFirst(i);
            table.append(VERTICAL_LINE);
        }
        table.delete(table.length() - 1, table.length());
        table.append(NEW_LINE);
        createHorizontalSeparator();
        table.append(NEW_LINE);
    }

    private void createHorizontalSeparator(){
        table.append(HORIZONTAL_LINE.repeat(Math.max(0, WIDTH_FIRST_COLUMN)));
        table.append(PLUS);
        for (int i = 1; i < TABLE_SIZE + 1; i++) {
            table.append(HORIZONTAL_LINE.repeat(Math.max(0, WIDTH_ALL_COLUMNS_WITHOUT_FIRST)));
            table.append(PLUS);
        }
        table.delete(table.length() - 1, table.length());
    }

    private void createFirstColumnOnLine(int number){
        if (number == 0) {
            table.append(SPACE.repeat(Math.max(0, WIDTH_FIRST_COLUMN)));
        }else{
            table.append(SPACE.repeat(Math.max(0, WIDTH_FIRST_COLUMN - (int) Math.log10(number) - 1)));
            table.append(number);
        }
    }

    private void createColumnOnLineWithoutFirst(int number){
        table.append(SPACE.repeat(Math.max(0, WIDTH_ALL_COLUMNS_WITHOUT_FIRST - (int) Math.log10(number) - 1)));
        table.append(number);
    }
}
