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

    void add(String values) {
        /* Split values by spaces. */
        String[] valuesArr = values.split(Parser.COMMA);
        /* Add each value for each attribute. */
        for (int i = 0; i < attr.size(); i++) {
            String type = attr.get(i);
            if (type.equals("int")) {
                this.values[i] = new Value(Integer.parseInt(valuesArr[i]));
            } else if (type.equals("string")) {
                this.values[i] = new Value(valuesArr[i]);
            } else if (type.equals("float")) {
                this.values[i] = new Value(Float.parseFloat(valuesArr[i]));
            }
        }
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
