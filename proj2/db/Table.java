package db;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private List<Col> cols; // Attributes of the table, size should be immutable
    private List<Row> rows;
    private final Attributes attr;

    /**
     * @exception IllegalArgumentException duplicate attributes
     * @param attributes
     */
    Table(String[] attributes) {
        this.cols = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.attr = new Attributes(attributes);
        for (int i = 0; i < attributes.length; i++) {
            cols.add(new Col());
        }
    }

    /**
     * @exception IllegalArgumentException invalid values
     * @param values
     */
    void addRow(String values) {
        Row newRow = new Row(attr);
        newRow.add(values);
        for (int i = 0; i < newRow.size(); i++) {
            cols.get(i).add(newRow.get(i));
        }
        rows.add(newRow);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(attr.toString());
        for (int i = 0; i < rows.size() - 1; i++) {
            sb.append("\n" + rows.get(i).toString());
        }
        if (!rows.isEmpty()) sb.append("\n" + rows.get(rows.size() - 1));
        return sb.toString();
    }

    void printCol(int index) {
        System.out.println(cols.get(index).toString());
    }
}
