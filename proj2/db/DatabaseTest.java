package db;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {
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
        System.out.println(db.transact("create table t1 (x int, y float, z string)"));
        System.out.println(db.transact("insert into t1 values 1, 2, 'seattle'"));
        System.out.println(db.transact("print t1"));
        System.out.println(db.transact("insert into t1 values 'seattle', 4, 'new york'"));
        System.out.println(db.transact("insert into t1 values 3, 4, '"));
        System.out.println(db.transact("insert into t2 values 3, 4, '"));
    }

    @Test
    public void testPrintTable() {
        db.transact("create table t1 (x int, y float, z string)");
        db.transact("insert into t1 values 1, 2.123, 'firstRow'");
        db.transact("insert into t1 values 3, 4.0, 'secondRow'");
        System.out.println(db.transact("print t1"));
    }

    @Test
    public void testLoadTable() {
        System.out.println(db.transact("load fans"));
        System.out.println(db.transact("print fans"));
        System.out.println(db.transact("load badtable"));
        System.out.println(db.transact("print badtable"));
    }

    @Test
    public void testStoreTable() {
        System.out.println(db.transact("create table mytable (x int, y float, z string)"));
        System.out.println(db.transact("insert into mytable values 1, 2.123, 'firstRow'"));
        System.out.println(db.transact("insert into mytable values 3, 4.0, 'secondRow'"));
        System.out.println(db.transact("store mytable"));
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
        System.out.println(db.transact("load fans"));
        System.out.println(db.transact("load records"));
        System.out.println(db.transact("select * from fans, records where x > 10 and y > 10"));
    }
}