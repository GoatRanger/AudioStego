package dji.g.b;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.g.c.a.e;

public class f {
    public e[][] a;
    public g b;
    public Object c;
    public e d;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.a == null) {
            stringBuilder.append("inputFile=null ");
        } else {
            for (int i = 0; i < this.a.length; i++) {
                if (this.a[i] != null) {
                    for (int i2 = 0; i2 < this.a[i].length; i2++) {
                        stringBuilder.append(String.format("inputs[%d][%d]=%s, ", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), this.a[i][i2].toString()}));
                    }
                }
            }
        }
        if (this.b == null) {
            stringBuilder.append(" cb==null ");
        } else {
            stringBuilder.append(" cb=" + this.b.getClass().getCanonicalName() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
        stringBuilder.append("filterWrapper=" + this.d + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        stringBuilder.append("window=" + this.c + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        return stringBuilder.toString();
    }
}
