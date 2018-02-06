package db;

import java.util.*;

public class Table implements Cloneable {

//    List<Col> cols;
    private String tableName;
    private List<Row> rows;
    private List<Col> cols;
    private List<String> columnNames;
    private List<String> columnTypes;
    private Map<String, Integer> index_cache;
    private final List<String> types = new ArrayList<>(Arrays.asList("string", "int", "float"));

    Table(String tableName) {
        this.tableName = tableName;
        this.rows = new ArrayList<>();
        this.cols = new ArrayList<>();
        this.columnNames = new ArrayList<>();
        this.columnTypes = new ArrayList<>();
        index_cache = new HashMap<>();
    }

    void setAttributes(String[] col) {
        for (String eachAttribute : col) {
            String[] curr = eachAttribute.split("\\s+");
            if (columnNames.contains(curr[0]) || !types.contains(curr[1])) {
                throw new IllegalArgumentException("Invalid data types or duplicate column names.");
            } else {
                columnNames.add(curr[0]);
                columnTypes.add(curr[1]);
                index_cache.put(curr[0], columnTypes.size() - 1);
            }
        }
    }

    void setAttributes(List<String> names, List<String> types) {
        this.columnNames = names;
        this.columnTypes = types;
        for (int i = 0; i < columnNames.size(); i++) {
            index_cache.put(columnNames.get(i), i);
        }
    }

    /**
     * @exception IllegalArgumentException invalid values
     * @param values
     */
    void insertRow(String values) {
        String[] valArr = values.split(Parser.COMMA);
        if (valArr.length != this.columnNames.size()) {
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

    private void insertRow(Row newRow) {
        if (newRow.size() != this.columnNames.size()) {
            throw new IllegalArgumentException("Wrong number of elements in the row.");
        }
        for (int i = 0; i < newRow.size(); i++) {
            if (!newRow.get(i).getType().equals(columnTypes.get(i))) {
                throw new IllegalArgumentException("Data type mismatch.");
            }
        }
        rows.add(newRow);
    }

    static Table join(Table x, Table y) {
        if (x == null && y == null) return null;
        try {
            if (x == null) return (Table)y.clone();
            if (y == null) return (Table)x.clone();
            List<String> commonAttributes = new ArrayList<>();
            /* Extract shared column names and types. */
            for (int i = 0; i < x.colSize(); i++) {
                if (y.containsColName(x.getColName(i))) {
                    if (x.getColType(i).equals(y.getColType(y.indexOf(x.getColName(i))))) {
                        commonAttributes.add(x.getColName(i));
                    }
                }
            }

            Table resultTable = new Table("resultTable");
            /* Combine column names and types. */
            List<String> colNames = new ArrayList<>();
            List<String> colTypes = new ArrayList<>();
            colNames.addAll(commonAttributes);
            for (String commonAttr : commonAttributes) {
                colTypes.add(x.getColType(x.indexOf(commonAttr)));
            }
            for (String name : x.columnNames) {
                if (!commonAttributes.contains(name)) {
                    colNames.add(name);
                    colTypes.add(x.getColType(x.indexOf(name)));
                }
            }
            for (String name : y.columnNames) {
                if (!commonAttributes.contains(name)) {
                    colNames.add(name);
                    colTypes.add(y.getColType(y.indexOf(name)));
                }
            }
            resultTable.setAttributes(colNames, colTypes);

            /* Natural join or Cartesian product. */
            if (!commonAttributes.isEmpty()) {

                for (Row xRow : x.rows) {
                    for (Row yRow : y.rows) {
                        boolean match = true;
                        for (String commonAttr : commonAttributes) {
                            if (!xRow.get(x.indexOf(commonAttr)).equals(yRow.get(y.indexOf(commonAttr)))) {
                                match = false;
                                break;
                            }
                        }
                        if (match) {
                            resultTable.insertRow(Row.combine(commonAttributes, x, y, xRow, yRow));
                        }
                    }
                }
                return resultTable;
            } else {
                System.out.println("hello" + resultTable.toString());
                for (Row xRow : x.rows) {
                    for (Row yRow : y.rows) {
                        resultTable.insertRow(Row.combine(new ArrayList<>(), x, y, xRow, yRow));
                    }
                }
                return resultTable;
            }
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    int indexOf(String colName) {
        return index_cache.get(colName);
    }

    boolean containsColName(String colName) {
        return index_cache.containsKey(colName);
    }

    String getColName(int index) {
        return columnNames.get(index);
    }

    String getColType(int index) {
        return columnTypes.get(index);
    }

    public int colSize() {
        return columnNames.size();
    }

    public int rowSize() {
        return rows.size();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Table copy = (Table) super.clone();
        copy.rows = new ArrayList<>();
        copy.cols = new ArrayList<>();
        copy.columnTypes = new ArrayList<>(this.columnTypes);
        copy.columnNames = new ArrayList<>(this.columnNames);
        for (Row row : this.rows) copy.rows.add((Row)row.clone());
        for (Col col : this.cols) copy.cols.add((Col)col.clone());
        return copy;
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
