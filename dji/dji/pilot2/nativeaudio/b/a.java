package dji.pilot2.nativeaudio.b;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import dji.pilot2.nativeaudio.model.b;
import java.util.ArrayList;

public class a {
    private Context a;
    private b b;
    private final String c = "DJIAudioManager";
    private boolean d = false;
    private final int e = 100;
    private final int f = 101;
    private final int g = 102;
    private ArrayList<b> h;
    private ArrayList<dji.pilot2.nativeaudio.model.a> i = new ArrayList();
    private ArrayList<dji.pilot2.nativeaudio.model.a> j = new ArrayList();
    private ArrayList<dji.pilot2.nativeaudio.model.a> k = new ArrayList();
    private a l;
    private Handler m = new Handler(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 100:
                    if (!this.a.d) {
                        this.a.b.a();
                        this.a.d = true;
                        return;
                    }
                    return;
                case 101:
                    this.a.l.a();
                    return;
                default:
                    return;
            }
        }
    };
    private dji.pilot2.nativeaudio.b.b.a n = new dji.pilot2.nativeaudio.b.b.a(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(ArrayList<b> arrayList) {
            Log.i("DJIAudioManager", "scan finish!");
            this.a.h = arrayList;
            this.a.m.sendEmptyMessage(101);
            this.a.d = false;
        }

        public void a() {
            Log.i("DJIAudioManager", "scan failed!");
            this.a.m.sendEmptyMessage(102);
            this.a.d = false;
        }
    };

    public interface a {
        void a();
    }

    public a(Context context) {
        this.a = context;
        this.b = b.getInstance(this.a);
        this.b.a(this.n);
    }

    public void a(a aVar) {
        this.l = aVar;
    }

    public void a() {
        if (!this.d) {
            this.m.sendEmptyMessage(100);
        }
    }

    private ArrayList<dji.pilot2.nativeaudio.model.a> a(ArrayList<b> arrayList) {
        if (this.h != null && this.h.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                Object obj;
                b bVar = (b) arrayList.get(i);
                if (this.i != null && this.i.size() > 0) {
                    for (int i2 = 0; i2 < this.i.size(); i2++) {
                        if (((dji.pilot2.nativeaudio.model.a) this.i.get(i2)).c(bVar)) {
                            ((dji.pilot2.nativeaudio.model.a) this.i.get(i2)).a(bVar);
                            obj = 1;
                            break;
                        }
                    }
                }
                obj = null;
                if (obj == null) {
                    dji.pilot2.nativeaudio.model.a aVar = new dji.pilot2.nativeaudio.model.a(dji.pilot2.nativeaudio.model.a.a.DIR);
                    aVar.a(bVar.a);
                    aVar.a(bVar);
                    this.i.add(aVar);
                }
            }
        }
        return this.i;
    }

    private ArrayList<dji.pilot2.nativeaudio.model.a> b(ArrayList<b> arrayList) {
        if (this.h != null && this.h.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                Object obj;
                b bVar = (b) arrayList.get(i);
                if (this.j != null && this.j.size() > 0) {
                    for (int i2 = 0; i2 < this.j.size(); i2++) {
                        if (((dji.pilot2.nativeaudio.model.a) this.j.get(i2)).c(bVar)) {
                            ((dji.pilot2.nativeaudio.model.a) this.j.get(i2)).a(bVar);
                            obj = 1;
                            break;
                        }
                    }
                }
                obj = null;
                if (obj == null) {
                    dji.pilot2.nativeaudio.model.a aVar = new dji.pilot2.nativeaudio.model.a(dji.pilot2.nativeaudio.model.a.a.ARTIST);
                    aVar.a(bVar.h);
                    aVar.a(bVar);
                    this.j.add(aVar);
                }
            }
        }
        return this.j;
    }

    private ArrayList<dji.pilot2.nativeaudio.model.a> c(ArrayList<b> arrayList) {
        if (this.h != null && this.h.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                Object obj;
                b bVar = (b) arrayList.get(i);
                if (this.k != null && this.k.size() > 0) {
                    for (int i2 = 0; i2 < this.k.size(); i2++) {
                        Log.i("zhang", "info:" + bVar.a);
                        Log.i("zhang", "mAlbumgroupBeans:" + this.k.get(i2));
                        if (((dji.pilot2.nativeaudio.model.a) this.k.get(i2)).c(bVar)) {
                            ((dji.pilot2.nativeaudio.model.a) this.k.get(i2)).a(bVar);
                            obj = 1;
                            break;
                        }
                    }
                }
                obj = null;
                if (obj == null) {
                    dji.pilot2.nativeaudio.model.a aVar = new dji.pilot2.nativeaudio.model.a(dji.pilot2.nativeaudio.model.a.a.ALBUM);
                    aVar.a(bVar.f);
                    aVar.a(bVar.i);
                    aVar.a(bVar);
                    this.k.add(aVar);
                }
            }
        }
        return this.k;
    }

    public String a(int i) {
        return this.b.a(i);
    }

    public ArrayList<b> b() {
        return this.h;
    }

    public ArrayList<dji.pilot2.nativeaudio.model.a> c() {
        if (this.h == null || this.h.size() == 0) {
            this.m.sendEmptyMessage(100);
        }
        return a(this.h);
    }

    public ArrayList<dji.pilot2.nativeaudio.model.a> d() {
        if (this.h == null || this.h.size() == 0) {
            this.m.sendEmptyMessage(100);
        }
        return b(this.h);
    }

    public ArrayList<dji.pilot2.nativeaudio.model.a> e() {
        if (this.h == null || this.h.size() == 0) {
            this.m.sendEmptyMessage(100);
        }
        return c(this.h);
    }
}
