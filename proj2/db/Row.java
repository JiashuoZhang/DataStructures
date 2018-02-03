package db;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Row {
    /* One row stores a fixed number of values corresponding to each type. */
    private List<Value> values;

    Row() {
        this.values = new ArrayList<>();
    }

    /**
     * @exception IllegalArgumentException Number of values doesn't match or invalid values
     */

    void add(Value val) {
        values.add(val);
    }

    // check boundary!
    Value get(int i) {
        return values.get(i);
    }

    int size() {
        return values.size();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < values.size(); i++) {
            joiner.add(values.get(i).toString());
        }
        return joiner.toString();
    }
}
