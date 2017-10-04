package dji.playback.entryActivity;

import dji.playback.entryActivity.d.b;
import java.io.Serializable;

public class h implements Serializable {
    public int a;
    public String b;
    public String c;
    public int d;
    public String e;
    public String f;
    public String g;
    public b h;
    public int i;
    public int j;
    public boolean k = false;

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        h hVar = (h) obj;
        if (!(this.a == hVar.a && this.c == hVar.c)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
