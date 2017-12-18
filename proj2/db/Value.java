package db;

public class Value {
    private String type;
    private int i;
    private float f;
    private String str;

    Value(String str) {
        type = "string";
        this.str = str;
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

    @Override
    public String toString() {
        if (type.equals("string")) return "'" + str + "'";
        if (type.equals("float")) return String.format("%.3f", f);
        return str;
    }
}
