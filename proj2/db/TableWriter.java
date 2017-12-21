package db;

import edu.princeton.cs.introcs.Out;

public class TableWriter {
    Out out;

    void writeTable(String fileName, Table table) {
        out = new Out(fileName + ".tbl");
        out.print(table.toString());
    }
}
