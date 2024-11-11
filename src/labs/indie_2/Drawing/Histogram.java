package labs.indie_2.Drawing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Histogram {
    private class Column {
        int height;
        String name;

        Column(int height, String name) {
            this.height = height;
            this.name = name;
        }
    }

    private int maxHeight;
    private ArrayList<Column> columns = new ArrayList<>();
    private int maxNameLength;

    public Histogram(Map<String, List<Integer>> map) {
        maxHeight = 0;
        maxNameLength = 0;

        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            int height = 0;
            for (Number number : entry.getValue()) {
                height = Math.max(height, number.intValue());
            }
            maxHeight = Math.max(maxHeight, height);
            columns.add(new Column(height, entry.getKey()));
            maxNameLength = Math.max(maxNameLength, entry.getKey().length());
        }
    }

    public void printHistogram(boolean vertical) {
        final String SEPARATOR = " ";
        final String LINE = "#";
        if (vertical) {
            for (int i = maxHeight; i > 0; i--) {
                for (Column column : columns) {
                    if (column.height >= i) {
                        System.out.print(LINE);
                    } else {
                        System.out.print(SEPARATOR);
                    }
                    System.out.print(String.format("%" + (maxNameLength - 1) + "s", "") + SEPARATOR);
                }
                System.out.println();
            }
            for (Column column : columns) {
                System.out.print(String.format("%-" + maxNameLength + "s", column.name) + SEPARATOR);
            }
            System.out.println();
        } else {
            for (Column column : columns) {
                System.out.print(String.format("%-" + maxNameLength + "s", column.name) + ": ");
                for (int i = 0; i < column.height; i++) {
                    System.out.print(LINE);
                }
                System.out.println();
            }
        }
    }

    public void printHistogram() {
        printHistogram(false);
    }
}

