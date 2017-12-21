package db;

import edu.princeton.cs.introcs.In;

import java.util.regex.Matcher;

import static db.Parser.*;

public class TableReader {
    String fileName;
    Table table;

    TableReader(String fileName) {
        this.fileName = fileName;
        In in = new In( "examples/" + fileName + ".tbl");
        String line;
        if ((line = in.readLine()) != null) {
            if (ATTRIBUTES.matcher(line).matches()) {
                table = new Table(line.split(COMMA));
            }
        }

        while ((line = in.readLine()) != null) {
            if (table == null) {
                break;
            }
            if (VALUES.matcher(line).matches()) {
                table.addRow(line);
            }
        }
    }

    Table getTable() {
        return table;
    }
}
