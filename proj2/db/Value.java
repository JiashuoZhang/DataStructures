package db;

public class Value implements Cloneable {
    private String type;
    private int i;
    private float f;
    private String str;

    Value(String type, String val) {
        this.type = type;
        if (val.equals("NaN") || val.equals("NOVALUE")) {
            this.str = val;
        } else if (type.equals("int")) {
            this.i = Integer.parseInt(val);
            str = "" + i;
        } else if (type.equals("float")) {
            this.f = Float.parseFloat(val);
            str = String.format("%.3f", f);
        } else if (type.equals("string")) {
            this.str = parseString(val);
        }
    }

    Value(int i) {
        type = "int";
        this.i = i;
        str = "" + i;
    }

    Value(float f) {
        type = "float";
        this.f = f;
        str = String.format("%.3f", f);
    }

    Value(String str) {
        type = "string";
        this.str = parseString(str);
    }

    private String parseString(String val) {
        if (val.length() < 2 || val.charAt(0) != '\'' || val.charAt(val.length() - 1) != '\'') {
            throw new IllegalArgumentException("Error: invalid string value");
        }
        return val;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Value) {
            Value anotherValue = (Value) obj;
            return this.type.equals(anotherValue.type) && this.str.equals(anotherValue.str);
        }
        return false;
    }
}
