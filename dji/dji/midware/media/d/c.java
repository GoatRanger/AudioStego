package dji.midware.media.d;

import android.media.MediaFormat;
import dji.pilot.usercenter.protocol.d;

public abstract class c {
    protected int b = -1;
    protected int c = -1;

    public abstract MediaFormat a(int i);

    public abstract int b();

    public int b(String str) {
        for (int i = 0; i < b(); i++) {
            String string = a(i).getString("mime");
            if (string != null && string.equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public int g() {
        if (this.b < 0) {
            for (int i = 0; i < b(); i++) {
                String string = a(i).getString("mime");
                if (string != null) {
                    string = string.split(d.t)[0];
                    if (string != null && string.equalsIgnoreCase("audio")) {
                        this.b = i;
                        break;
                    }
                }
            }
        }
        return this.b;
    }

    public int h() {
        if (this.c < 0) {
            for (int i = 0; i < b(); i++) {
                String string = a(i).getString("mime");
                if (string != null) {
                    string = string.split(d.t)[0];
                    if (string != null && string.equalsIgnoreCase("video")) {
                        this.c = i;
                        break;
                    }
                }
            }
        }
        return this.c;
    }
}
