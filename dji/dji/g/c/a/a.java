package dji.g.c.a;

import dji.midware.media.e;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class a extends c implements d {
    private double h = 0.0d;
    private int i = 0;

    public a(String str, int i) {
        super(str);
        this.i = i;
    }

    public void a(double d) {
        this.h = d;
    }

    protected String a() {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.i; i++) {
            stringBuilder.append(String.format("[in_%d] ", new Object[]{Integer.valueOf(i)}));
        }
        stringBuilder.append(String.format(Locale.US, "amix=inputs=%d:dropout_transition=%1.2f [result]", new Object[]{Integer.valueOf(this.i), Double.valueOf(this.h)}));
        String str2 = "";
        try {
            str = new String(stringBuilder.toString().getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str = str2;
        }
        e.d(d(), "FilterDescr: " + str);
        return str;
    }
}
