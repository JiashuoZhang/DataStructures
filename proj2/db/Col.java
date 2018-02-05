package db;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Col implements Cloneable {
    private int index;
    private List<Value> values;

    Col() {
        values = new ArrayList<>();
    }

    void add(Value val) {
        values.add(val);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Col copy = (Col) super.clone();
        copy.values = new ArrayList<>();
        for (Value val : this.values) {
            copy.values.add((Value)val.clone());
        }
        return copy;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < values.size() - 1; i++) {
            joiner.add(values.get(i).toString());
        }
        joiner.add(values.get(values.size() - 1).toString());
        return joiner.toString();
    }
}
