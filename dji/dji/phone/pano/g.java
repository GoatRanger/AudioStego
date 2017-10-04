package dji.phone.pano;

import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import dji.common.utils.BitmapUtil;
import dji.device.pano.Stitch;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataGimbalSetAngle;
import dji.midware.e.d;
import dji.thirdparty.g.b.b.a.b;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class g {
    public static final String a = (dji.pilot.storage.a.c + "/PANO");
    private static final String e = "PanoTask";
    ArrayList<String> b;
    Thread c = new Thread(new Runnable(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void run() {
            DJILogHelper.getInstance().LOGD(g.e, "DJIMethod : run (124)start pano", false, true);
            int a = this.a.a(this.a.b);
            DJILogHelper.getInstance().LOGD(g.e, "DJIMethod : run (126)pano finish:" + a, false, true);
            if (a == 0) {
                File file = new File(this.a.m);
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                HashMap hashMap = new HashMap();
                hashMap.put(b.aL, this.a.k.toString());
                dji.b.a.a.a.a(file, hashMap);
                if (this.a.i != null) {
                    this.a.i.a(this.a.m);
                }
                if (dji.pilot.storage.a.c(this.a.p.c())) {
                    try {
                        dji.pilot.storage.a.a(this.a.p.c(), dji.pilot.storage.a.f, "jpeg", file);
                        file.delete();
                    } catch (IOException e) {
                        DJILogHelper.getInstance().LOGE(g.e, "DJIMethod : run (151)copy pano result exception:" + e.getMessage(), true, false);
                        e.printStackTrace();
                    }
                }
            } else {
                this.a.i.a(a);
            }
            ArrayList a2 = this.a.g;
            a2.addAll(this.a.h);
            Iterator it = a2.iterator();
            while (it.hasNext()) {
                dji.pilot.storage.a.b(this.a.p.c(), (String) it.next());
            }
        }
    });
    d d = new d(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
            DJILogHelper.getInstance().LOGD(g.e, "DJIMethod : onSuccess", false, true);
            if (dji.phone.c.a.b()) {
                dji.phone.c.a.c().d();
                if (this.a.i != null) {
                    this.a.i.a(1, (this.a.j - this.a.f.size()) + 1, this.a.j);
                }
            } else {
                DJILogHelper.getInstance().LOGE(g.e, "DJIMethod : onSuccess ->Camera Error", true, false);
            }
            this.a.l = 0;
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
            DJILogHelper.getInstance().LOGE(g.e, "DJIMethod : onFailure ->" + aVar, true, false);
            this.a.l = this.a.l + 1;
            if (this.a.l <= 3) {
                this.a.a((e) this.a.f.peekFirst());
            } else if (this.a.i != null) {
                this.a.i.a(aVar.a());
            }
        }
    };
    private LinkedList<e> f;
    private ArrayList<String> g;
    private ArrayList<String> h;
    private a i;
    private int j;
    private d k;
    private int l;
    private String m;
    private int n = 4;
    private String o;
    private a p;

    public interface a {
        void a(int i);

        void a(int i, int i2, int i3);

        void a(String str);

        void f();

        void g();
    }

    public g(a aVar, d dVar, e[] eVarArr, int i) {
        this.p = aVar;
        this.k = dVar;
        this.f = new LinkedList();
        this.h = new ArrayList();
        this.g = new ArrayList();
        this.b = new ArrayList();
        this.b.add("-i");
        for (Object add : eVarArr) {
            this.f.add(add);
        }
        this.j = eVarArr.length;
        this.n = i;
    }

    public void a(a aVar) {
        this.i = aVar;
    }

    public void a(final String str) {
        if (!this.f.isEmpty()) {
            this.f.removeFirst();
            a((e) this.f.peekFirst());
            DJILogHelper.getInstance().LOGD(e, "DJIMethod : feedOriImage" + str + ">>>num:" + this.f.size(), false, true);
            new Thread(new Runnable(this) {
                final /* synthetic */ g b;

                public void run() {
                    this.b.g.add(str);
                    com.dji.frame.c.d.a(g.a);
                    String str = g.a + "/DJI_" + System.currentTimeMillis() + dji.pilot2.main.a.a.n;
                    BitmapUtil.scaleSrc(str, (float) this.b.a(this.b.n), str);
                    this.b.h.add(str);
                    if (this.b.h.size() == this.b.j) {
                        if (this.b.i != null) {
                            this.b.i.a(2, 0, this.b.j);
                        }
                        this.b.b.addAll(this.b.h);
                        this.b.b.add("-o");
                        this.b.m = g.a + dji.pilot.usercenter.protocol.d.t + g.a(System.currentTimeMillis()) + dji.pilot2.main.a.a.n;
                        this.b.b.add(this.b.m);
                        this.b.b.addAll(this.b.e());
                        this.b.c.start();
                        this.b.d();
                    }
                }
            }).start();
        }
    }

    private void a(e eVar) {
        DJILogHelper.getInstance().LOGD(e, "DJIMethod : exeutePoint", false, true);
        if (eVar != null) {
            int i = eVar.a;
            DataGimbalSetAngle.getInstance().a(i).b(eVar.b).d(10).e(4).a(5000, 1, this.d);
        }
    }

    public void a() {
        a((e) this.f.peekFirst());
        if (this.i != null) {
            this.i.f();
        }
    }

    public void b() {
        this.f.clear();
        if (this.i != null) {
            this.i.g();
            this.i = null;
        }
    }

    private int a(ArrayList<String> arrayList) {
        return Stitch.stitchingM((String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    private void d() {
        this.l = 0;
    }

    private ArrayList<String> e() {
        ArrayList<String> arrayList = new ArrayList();
        float horizontalViewAngle = dji.phone.c.a.c().m().getHorizontalViewAngle();
        DJILogHelper.getInstance().LOGD(e, "DJIMethod : getParams (182)h fov:" + horizontalViewAngle + "v fov:" + dji.phone.c.a.c().m().getVerticalViewAngle(), false, true);
        if (horizontalViewAngle < 50.0f) {
            DJILogHelper.getInstance().LOGE(e, "DJIMethod : getParams (185)fov:" + horizontalViewAngle, true, false);
        }
        arrayList.add("-f");
        arrayList.add(String.valueOf(horizontalViewAngle));
        arrayList.add("-m");
        arrayList.add(String.valueOf(this.k.ordinal()));
        arrayList.add("-y");
        arrayList.add("45");
        return arrayList;
    }

    public static void c() {
    }

    public static String a(long j) {
        return new SimpleDateFormat("'DJI_PANO'_yyyyMMdd_HHmmss").format(new Date(j));
    }

    private int a(int i) {
        switch (this.k) {
            case P_WIDE:
                if (i == 1 || i == 2) {
                    return i * 2;
                }
                if (i == 4) {
                    return 6;
                }
                return i;
            default:
                return i;
        }
    }
}
