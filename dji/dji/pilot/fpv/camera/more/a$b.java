package dji.pilot.fpv.camera.more;

import android.content.Context;
import android.content.res.Resources;
import dji.pilot.R;

public class a$b {
    public final int[] a = new int[]{R.drawable.advanced_more_photoformat_jpeg};
    public final int[] b = new int[]{R.drawable.advanced_more_videostoreformat_mp4};
    public final int[] c = new int[]{R.drawable.advanced_more_antifliker_60hz, R.drawable.advanced_more_antifliker_50hz};
    final /* synthetic */ a d;
    private String[] e = null;
    private int[] f = null;
    private String[] g = null;
    private int[] h = null;
    private String[] i = null;
    private int[] j = null;

    public a$b(a aVar) {
        this.d = aVar;
    }

    public void a(Context context) {
        Resources resources = context.getResources();
        this.e = resources.getStringArray(R.array.ah);
        this.f = resources.getIntArray(R.array.am);
        this.g = resources.getStringArray(R.array.bl);
        this.h = resources.getIntArray(R.array.bo);
        this.i = resources.getStringArray(R.array.b);
        this.j = resources.getIntArray(R.array.f);
    }

    public String[] a() {
        return this.e;
    }

    public int a(int i) {
        return a.a(this.f, i, 0);
    }

    public int[] b() {
        return this.f;
    }

    public String[] c() {
        return this.g;
    }

    public int b(int i) {
        return a.a(this.h, i, 0);
    }

    public int[] d() {
        return this.h;
    }

    public String[] e() {
        return this.i;
    }

    public int[] f() {
        return this.j;
    }
}
