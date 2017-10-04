package dji.pilot2.nativeaudio.model;

import android.util.Log;
import dji.pilot.usercenter.protocol.d;
import java.util.ArrayList;

public class a {
    private String a;
    private int b;
    private a c;
    private ArrayList<b> d;

    public enum a {
        DIR,
        ALBUM,
        ARTIST
    }

    public a(a aVar) {
        this.c = aVar;
    }

    public a a() {
        return this.c;
    }

    public ArrayList<b> b() {
        return this.d;
    }

    public int c() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public void a(ArrayList<b> arrayList) {
        this.d = arrayList;
    }

    public void a(b bVar) {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        this.d.add(bVar);
    }

    public void b(b bVar) {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        this.d.remove(bVar);
    }

    public String d() {
        return this.a;
    }

    public void a(String str) {
        if (this.c == a.DIR) {
            this.a = b(str);
        } else {
            this.a = str;
        }
    }

    public boolean c(b bVar) {
        switch (this.c) {
            case DIR:
                if (this.a != null && this.a.equals(b(bVar.a))) {
                    return true;
                }
            case ALBUM:
                if (this.a != null && this.a.equals(bVar.f)) {
                    return true;
                }
            case ARTIST:
                if (this.a != null && this.a.equals(bVar.h)) {
                    return true;
                }
        }
        return false;
    }

    public String b(String str) {
        if (str == null) {
            return null;
        }
        Log.i("zhang", "adb:" + str);
        Log.i("zhang", "index:" + str.lastIndexOf(d.t));
        return str.substring(0, str.lastIndexOf(d.t));
    }
}
