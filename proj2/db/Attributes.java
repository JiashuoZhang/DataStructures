package db;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

class Attributes {
    String[] names, types;
    Map<String, Integer> map;

    Attributes(String[] attr) {
        if (containsDuplicate(attr)) {
            throw new IllegalArgumentException("Error: contains duplicate attributes");
        }
    }

    int size() {
        return names.length;
    }

    /* Return the type of the ith column/attribute. */
    String getType(int i) {
        return types[i];
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < names.length; i+=1) {
            joiner.add(names[i] + " " + types[i]);
        }
        return joiner.toString();
    }

    private boolean containsDuplicate(String[] attributes) {
        this.map = new HashMap<>();
        this.names = new String[attributes.length];
        this.types = new String[attributes.length];
        for (int i = 0; i < attributes.length; i++) {
            String[] temp = attributes[i].split("\\s+");
            if (this.map.containsKey(temp[0])) {
                return true;
            }
            this.map.put(temp[0], i);
            this.names[i] = temp[0];
            this.types[i] = temp[1];
        }
        return false;
    }
}
