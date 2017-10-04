package dji.pilot.playback.litchi;

import dji.logic.album.model.DJIAlbumFileInfo;
import dji.pilot.usercenter.mode.g;
import dji.pilot2.library.model.DJISycAlbumModel;
import java.util.ArrayList;
import java.util.List;

public class a {
    public List<g> a = new ArrayList();
    public List<DJIAlbumFileInfo> b = new ArrayList();
    public List<DJISycAlbumModel> c = new ArrayList();
    private String d;
    private int e;
    private int f;

    public a(String str, int i, int i2) {
        this.d = str;
        this.e = i;
        this.f = i2;
    }

    public String a() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.f;
    }

    public List<g> d() {
        return this.a;
    }

    public List<DJIAlbumFileInfo> e() {
        return this.b;
    }

    public List<DJISycAlbumModel> f() {
        return this.c;
    }

    public void a(String str) {
        this.d = str;
    }

    public void a(int i) {
        this.e = i;
    }

    public void b(int i) {
        this.f = i;
    }

    public void a(List<g> list) {
        this.a.addAll(list);
    }

    public void b(List<DJIAlbumFileInfo> list) {
        this.b.addAll(list);
    }

    public void c(List<DJISycAlbumModel> list) {
        this.c.addAll(list);
    }
}
