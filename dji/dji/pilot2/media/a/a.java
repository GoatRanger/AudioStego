package dji.pilot2.media.a;

import android.content.Context;
import android.graphics.BitmapFactory;
import dji.log.DJILogHelper;
import dji.pilot.R;
import jp.co.cyberagent.android.gpuimage.n;
import jp.co.cyberagent.android.gpuimage.o;
import jp.co.cyberagent.android.gpuimage.p;
import jp.co.cyberagent.android.gpuimage.q;
import jp.co.cyberagent.android.gpuimage.r;
import jp.co.cyberagent.android.gpuimage.s;
import jp.co.cyberagent.android.gpuimage.t;
import jp.co.cyberagent.android.gpuimage.u;
import jp.co.cyberagent.android.gpuimage.v;
import jp.co.cyberagent.android.gpuimage.w;
import jp.co.cyberagent.android.gpuimage.x;

public class a {
    private static int[] a = new int[]{R.string.v2_photo_editor_filter_original, R.string.v2_photo_editor_filter_amaro, R.string.v2_photo_editor_filter_hudsun, R.string.v2_photo_editor_filter_rise, R.string.v2_photo_editor_filter_sierra, R.string.v2_photo_editor_filter_valencia, R.string.v2_photo_editor_filter_walden, R.string.v2_photo_editor_filter_earlybird, R.string.v2_photo_editor_filter_hefe, R.string.v2_photo_editor_filter_inkwell, R.string.v2_photo_editor_filter_nashville};
    private static int[] b = new int[]{R.drawable.v2_photo_editor_filter_photo_normal, R.drawable.v2_photo_editor_filter_photo_amaro, R.drawable.v2_photo_editor_filter_photo_hudson, R.drawable.v2_photo_editor_filter_photo_rise, R.drawable.v2_photo_editor_filter_photo_sierra, R.drawable.v2_photo_editor_filter_photo_valencia, R.drawable.v2_photo_editor_filter_photo_walden, R.drawable.v2_photo_editor_filter_photo_earlybird, R.drawable.v2_photo_editor_filter_photo_hefe, R.drawable.v2_photo_editor_filter_photo_inkwell, R.drawable.v2_photo_editor_filter_photo_nashville};
    private static n c = null;
    private static t d = null;
    private static x e = null;
    private static r f = null;
    private static s g = null;
    private static p h = null;
    private static v i = null;
    private static q j = null;
    private static o k = null;
    private static w l = null;
    private static final String m = "explore";

    public static int a() {
        return a.length;
    }

    public static String a(Context context, int i) {
        if (i <= a.length) {
            return context.getResources().getString(a[i]);
        }
        DJILogHelper.getInstance().LOGD("explore", "getFilterNameAt out of index");
        return context.getResources().getString(a[0]);
    }

    public static int a(int i) {
        if (i <= a.length) {
            return a[i];
        }
        DJILogHelper.getInstance().LOGD("explore", "getFilterNameAt out of index");
        return a[0];
    }

    public static int b(int i) {
        if (i <= b.length) {
            return b[i];
        }
        DJILogHelper.getInstance().LOGD("explore", "getFilterDrawableAt out of index");
        return b[0];
    }

    public static u b(Context context, int i) {
        switch (i) {
            case 1:
                if (c == null) {
                    c = new n();
                    c.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_blackboard1024), 2);
                    c.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_overlay_map), 3);
                    c.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_amaro_map), 4);
                }
                return c;
            case 2:
                if (d == null) {
                    d = new t();
                    d.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_hudson_background), 2);
                    d.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_overlay_map), 3);
                    d.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_hudson_map), 4);
                }
                return d;
            case 3:
                if (e == null) {
                    e = new x();
                    e.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_blackboard1024), 2);
                    e.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_overlay_map), 3);
                    e.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_rise_map), 4);
                }
                return e;
            case 4:
                if (f == null) {
                    f = new r();
                    f.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_sierra_vignette), 2);
                    f.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_overlay_map), 3);
                    f.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_sierra_map), 4);
                }
                return f;
            case 5:
                if (g == null) {
                    g = new s();
                    g.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_valencia_map), 2);
                    g.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_valencia_gradient_map), 3);
                }
                return g;
            case 6:
                if (h == null) {
                    h = new p();
                    h.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_walden_map), 2);
                    h.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_vignette_map), 3);
                }
                return h;
            case 7:
                if (i == null) {
                    i = new v();
                    i.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_earlybird_curves), 2);
                    i.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_earlybird_overlay_map), 3);
                    i.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_vignette_map), 4);
                    i.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_earlybird_blowout), 5);
                    i.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_earlybird_map), 6);
                }
                return i;
            case 8:
                if (j == null) {
                    j = new q();
                    j.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_edge_burn), 2);
                    j.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_hefe_map), 3);
                    j.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_hefe_gradient_map), 4);
                    j.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_hefe_soft_light), 5);
                    j.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_hefe_metal), 6);
                }
                return j;
            case 9:
                if (k == null) {
                    k = new o();
                    k.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_inkwell_map), 2);
                }
                return k;
            case 10:
                if (l == null) {
                    l = new w();
                    l.a(BitmapFactory.decodeResource(context.getResources(), R.drawable.v2_photo_editor_filter_nashville_map), 2);
                }
                return l;
            default:
                return null;
        }
    }

    private static void a(u uVar) {
        if (uVar != null) {
            uVar.c();
        }
    }

    public static void b() {
        a(c);
        c = null;
        a(d);
        d = null;
        a(e);
        e = null;
        a(f);
        f = null;
        a(g);
        g = null;
        a(h);
        h = null;
        a(i);
        i = null;
        a(j);
        j = null;
        a(k);
        k = null;
        a(l);
        l = null;
    }
}
