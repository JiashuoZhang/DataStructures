package db;

import java.util.Arrays;
import java.util.StringJoiner;

class Attributes {
    String[] attr;

    Attributes(String[] attributes) {
        attr = attributes;
    }

    int size() {
        return attr.length;
    }

    /* Return the type of the ith column/attribute. */
    String get(int i) {
        return attr[i].split("\\s+")[1];
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < attr.length; i+=1) {
            String[] type = attr[i].split("\\s+");
            joiner.add(type[0] + " " + type[1]);
        }
        return joiner.toString();
    }
}
