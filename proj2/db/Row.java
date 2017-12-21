package db;

import java.util.StringJoiner;

public class Row {
    /* One row stores a fixed number of values corresponding to each type. */
    private Value[] values;
    /* Keep track of types. */
    private Attributes attr;

    Row(Attributes attributes) {
        this.attr = attributes;
        this.values = new Value[attributes.size()];
    }

    /**
     * @exception IllegalArgumentException Number of values doesn't match or invalid values
     * @param values
     */
    void add(String values) {
        /* Split values by spaces. */
        String[] valuesArr = values.split(Parser.COMMA);
        if (valuesArr.length != attr.size())
            throw new IllegalArgumentException("Error: Values do not match columns.");
        /* Add each value for each attribute. */
        for (int i = 0; i < attr.size(); i++) {
            Value val = new Value(attr.types[i]);
            val.assignVal(valuesArr[i]);
            this.values[i] = val;
        }
    }

    // check boundary!!
    Value get(int i) {
        return values[i];
    }

    int size() {
        return values.length;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < values.length; i++) {
            joiner.add(values[i].toString());
        }
        return joiner.toString();
    }
}
