package db;

public class Value {
    private String type;
    private int i;
    private float f;
    private String str;

    Value(String type) {
        this.type = type;
    }

    Value(int i) {
        type = "int";
        this.i = i;
        str = "" + i;
    }

    Value(float f) {
        type = "float";
        this.f = f;
        str = "" + f;
    }

    void assignVal(String val) {
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

    private String parseString(String val) {
        if (val.length() < 2 || val.charAt(0) != '\'' || val.charAt(val.length() - 1) != '\'') {
            throw new IllegalArgumentException("Error: invalid string value");
        }
        return val;
    }

    @Override
    public String toString() {
        return str;
    }
}
