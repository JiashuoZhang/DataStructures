package db;

import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;

import static db.Parser.*;
import static org.junit.Assert.assertEquals;

public class Database {
    Map<String, Table> map;

    public Database() {
        // YOUR CODE HERE
        map = new HashMap<>();
    }

    public String transact(String query) {
        Matcher m;
        String output = "";
        if ((m = CREATE_CMD.matcher(query)).matches()) {
            output = createTable(m.group(1));
        } else if ((m = LOAD_CMD.matcher(query)).matches()) {
            output = loadTable(m.group(1));
        } else if ((m = STORE_CMD.matcher(query)).matches()) {
            output = storeTable(m.group(1));
        } else if ((m = DROP_CMD.matcher(query)).matches()) {
            output = dropTable(m.group(1));
        } else if ((m = INSERT_CMD.matcher(query)).matches()) {
            output = insertRow(m.group(1));
        } else if ((m = PRINT_CMD.matcher(query)).matches()) {
            output = printTable(m.group(1));
        } else if ((m = SELECT_CMD.matcher(query)).matches()) {
            output = select(m.group(1));
        } else {
            output = "Error: malformed query " + query;
        }
        return output;
    }

    /**
     * query 1: create table <table name> (<column0 name> <type0>, <column1 name> <type1>, ...)
     *       2: create table <table name> as <select clause>
     * Errors:  1. create a table with no columns
     *          2. create a table that already exists.
     * @param expr <table name> ...
     * return empty string on success, otherwise error message
     */
    private String createTable(String expr) {
        Matcher m;
        String output = "";
        if ((m = CREATE_NEW.matcher(expr)).matches()) {
            if (map.containsKey(m.group(1))) return "Error: table already exists";
            return createNewTable(m.group(1), m.group(2).split(COMMA));
        } else if ((m = CREATE_SEL.matcher(expr)).matches()) {
//            createSelectedTable(m.group(1), m.group(2), m.group(3), m.group(4));
        } else {
            output = "Error: malformed <create> query " + expr;
        }
        return output;
    }

    /**
     * query: create table t1 (x int, y int, z int)
     * @param tableName String <table name>
     * @param columns String[] {<column0 name> <type0>, <column1 name> <type1>, ...}
     */
    private String createNewTable(String tableName, String[] columns) {
        Table newTable;
        try {
            newTable = new Table(tableName);
            newTable.setAttributes(columns);
        } catch (Exception e) {
            return e.getMessage();
        }
        map.put(tableName, newTable);
        return "";
    }

    /**
     * Load <table name>.tbl into database named as <table name>
     * query: load <table name>, load fans
     * If table already exists, replace it
     * Error: table file contains invalid table
     * @param fileName
     * @return empty String on success, otherwise error message
     */
    private String loadTable(String fileName) {
        try {
            TableReader tableReader = new TableReader(fileName);
            if (tableReader.getTable() == null) return "Error: failed to load table " + fileName;
            map.put(fileName, tableReader.getTable());
            return "";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * @param tableName
     * @return empty String on success, or error message otherwise
     */
    private String storeTable(String tableName) {
        if (!map.containsKey(tableName)) return "Error: " + tableName + " does not exist";
        try {
            File file = new File(tableName + ".tbl");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(map.get(tableName).toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            return e.getMessage();
        }
        return "";
    }

    /**
     * @param tableName
     * @return empty string if successfully dropped the table
     */
    private String dropTable(String tableName) {
        if (!map.containsKey(tableName)) return "Error: " + tableName + " does not exist";
        map.remove(tableName);
        return "";
    }

    /**
     * query: insert into <table name> values <literal0>,<literal1>,...
     * @param expr <table name> values <literal0>,<literal1>,...
     * @return empty string, or error message otherwise
     */
    private String insertRow(String expr) {
        Matcher m = INSERT_CLS.matcher(expr);
        if (!m.matches()) return "Malformed insert: " + expr;
        if (!map.containsKey(m.group(1))) return "Error: " + m.group(1) + " does not exist";

        try {
            map.get(m.group(1)).insertRow(m.group(2)); // m.group(1) = table name; m.group(2) = vale1, value2, value3
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    /**
     * Print the table
     * @param tableName
     * @return String representation of the table, or error message otherwise
     */
    private String printTable(String tableName) {
        if (!map.containsKey(tableName)) return "Error: " + tableName + " does not exist.";
        return map.get(tableName).toString();
    }

    /**
     * select <column expr0>,<column expr1>,... from <table0>,<table1>,... where <cond0> and <cond1> and ...
     *
     */

    private String select(String expr) {
        Matcher m = SELECT_CLS.matcher(expr);
        if (!m.matches()) {
            return "Error: malformed query select: " + expr;
        }
        return select(m.group(1), m.group(2), m.group(3));
    }

    private String select(String exprs, String tables, String conds) {
        String[] exprsArr = exprs.split(COMMA);
        String[] tableArr = tables.split(COMMA);
        String[] condsArr = conds == null ? null : conds.split("\\s+and\\s+");
        for (String tableName : tableArr) if (!map.containsKey(tableName)) return tableName + "does not exist.";
        Table resultTable = null;
        for (String tableName : tableArr) {
            resultTable = Table.join(resultTable, map.get(tableName));
        }
        System.out.println(resultTable.toString());
        System.out.printf("You are trying to select these expressions:" +
                " '%s' from the join of these tables: '%s', filtered by these conditions: '%s'\n", exprs, tables, conds);
        return "";
    }

    public static class TestTheOuterClassPrivateMethods {
        Database db = new Database();

        @Test
        public void testCreateTable() {
            System.out.println(db.transact("create table t1 (x int, y float, z string)"));
            System.out.println(db.transact("print t1"));
            System.out.println(db.transact("create table t2 (x int, x float)"));
            System.out.println(db.transact("print t2"));
        }

        @Test
        public void testInsertRow() {
            System.out.println(db.transact("create table t1 (x int, y float, z string"));
            db.createTable("t1 (x int, y float, z string)");
            db.insertRow("t1 values 1, 2.0, 'insert this row'");
            assertEquals("x int,y float,z string\n1,2.000,'insert this row'", db.map.get("t1").toString());
        }

        @Test
        public void testPrintTable() {
            db.createTable("t1 (x int, y float, z string)");
            db.transact("insert into t1 values 1, 2.123, 'firstRow'");
            db.transact("insert into t1 values 3, 4.0, 'secondRow'");
            System.out.println(db.transact("print t1"));
        }

        @Test
        public void testLoadTable() {
            System.out.println(db.transact("load badtable"));
            System.out.println(db.transact("print badtable"));
        }

        @Test
        public void testStoreTable() {
            System.out.println(db.transact("load fans"));
            System.out.println(db.transact("store fans"));
        }

        @Test
        public void testDropTable() {
            System.out.println(db.transact("load fans"));
            System.out.println(db.transact("print fans"));
            System.out.println(db.transact("drop table fans"));
            System.out.println(db.transact("print fans"));
        }

        @Test
        public void testSelect() {
            db.transact("select * from t1, t2 where x > 10 and y < 5 and z > 5");
        }
    }
}
