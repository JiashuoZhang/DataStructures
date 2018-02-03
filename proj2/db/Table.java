package db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Table {

//    List<Col> cols;
    private String tableName;
    private List<Row> rows;
    private List<Col> cols;
    private List<String> columnNames;
    private List<String> columnTypes;
    private final List<String> types = new ArrayList<>(Arrays.asList("string", "int", "float"));

    Table(String tableName) {
        this.tableName = tableName;
        this.rows = new ArrayList<>();
        this.cols = new ArrayList<>();
        this.columnNames = new ArrayList<>();
        this.columnTypes = new ArrayList<>();
    }

    void setAttributes(String[] col) {
        for (String eachAttribute : col) {
            String[] curr = eachAttribute.split("\\s+");
            if (columnNames.contains(curr[0]) || !types.contains(curr[1])) {
                throw new IllegalArgumentException("Invalid data types or duplicate column names.");
            } else {
                columnNames.add(curr[0]);
                columnTypes.add(curr[1]);
            }
        }
    }

    /**
     * @exception IllegalArgumentException invalid values
     * @param values
     */
    void insertRow(String values) {
        String[] valArr = values.split(Parser.COMMA);
        if (valArr.length != columnNames.size()) {
            throw new IllegalArgumentException("Mismatch between attributes and values.");
        }

        Row newRow = new Row();
        for (int i = 0; i < valArr.length; i++) {
            switch (columnTypes.get(i)) {
                case "int":
                    newRow.add(new Value("int", valArr[i]));
                    break;
                case "float":
                    newRow.add(new Value("float", valArr[i]));
                    break;
                case "string":
                    newRow.add(new Value("string", valArr[i]));
                    break;
            }
        }
        rows.add(newRow);
    }

    String getTableName() {
        return this.tableName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < columnNames.size(); i++) {
            joiner.add(columnNames.get(i) + " " + columnTypes.get(i));
        }
        sb.append(joiner.toString());
        for (int i = 0; i < rows.size() - 1; i++) {
            sb.append("\n" + rows.get(i).toString());
        }
        if (!rows.isEmpty()) sb.append("\n" + rows.get(rows.size() - 1));
        return sb.toString();
    }
}
