package dji.pilot.gallery.entryActivity;

import dji.pilot.gallery.entryActivity.d.b;
import java.io.Serializable;

public class g implements Serializable {
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
        g gVar = (g) obj;
        if (!(this.a == gVar.a && this.c == gVar.c)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
