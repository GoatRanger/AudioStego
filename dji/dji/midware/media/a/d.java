package dji.midware.media.a;

import java.util.ArrayList;

public class d {
    public int a;
    public int b;
    public int c;
    public ArrayList<c> d = new ArrayList();

    public String toString() {
        return String.format("clipCount=%d dataLength=%d commitNo=%d", new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c)}) + ". " + this.d.toString();
    }
}
