package dji.midware.media.f;

import java.text.SimpleDateFormat;

public class d {
    private static final String n = d.class.getName();
    public int a;
    public int b;
    public int c;
    public int[] d;
    public int[] e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public byte[] l;
    public byte[] m;
    private final int o = 1000;

    public d(e eVar) {
        this.a = eVar.j.i.x;
        this.b = eVar.j.i.w;
        this.c = eVar.h.n;
        this.f = eVar.h.o;
        this.g = eVar.h.l;
        this.h = eVar.h.m;
        this.d = eVar.j.k.k.k.m.m;
        this.e = eVar.j.k.k.k.n.l;
        this.l = eVar.j.k.k.k.i.i.m.j;
        this.m = eVar.j.k.k.k.i.i.m.i;
        this.i = this.f / this.c;
        this.j = this.c / 1000;
        this.k = this.d.length;
    }

    public String toString() {
        return "MP4 Info\nheight: " + this.a + "\nwidth: " + this.b + "\ntime_scale: " + this.c + "\nduration: " + this.f + "\nduration_time: " + this.i + "\ncreate_time: " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Integer.valueOf(this.g)) + "\nmodification_time: " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Integer.valueOf(this.h)) + "\npps: " + c.i(this.l) + "\nsps: " + c.i(this.m);
    }
}
