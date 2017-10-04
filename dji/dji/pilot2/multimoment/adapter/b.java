package dji.pilot2.multimoment.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.MediaMetadataRetriever;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot2.media.f;
import dji.pilot2.multimoment.view.FineCutHorizonalScrollView;
import dji.pilot2.utils.o;
import dji.pilot2.videolib.VideoLibWrapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class b {
    public long a;
    public long b;
    public int c;
    public int d;
    public int e;
    public int f;
    public FineCutHorizonalScrollView g;
    private Context h;
    private LayoutInflater i;
    private Map<Integer, b> j;
    private String k;
    private int l = 1;
    private long m;
    private Handler n = new Handler(Looper.getMainLooper());
    private a o;
    private volatile boolean p = false;
    private MediaMetadataRetriever q;

    private class a extends Thread {
        final /* synthetic */ b a;
        private final BlockingQueue<Intent> b = new LinkedBlockingQueue();

        public a(b bVar, String str) {
            this.a = bVar;
            super("LoadBitmapProcessor-" + str);
        }

        public void run() {
            while (!Thread.interrupted() && !this.a.p) {
                try {
                    this.a.a((Intent) this.b.take());
                } catch (InterruptedException e) {
                    return;
                }
            }
        }

        public void a(Intent intent) {
            if (isAlive()) {
                this.b.add(intent);
            }
        }

        public boolean b(Intent intent) {
            return this.b.remove(intent);
        }

        public Iterator<Intent> a() {
            return this.b.iterator();
        }

        public void b() {
            if (this.b.size() > 0) {
                this.b.clear();
            }
            interrupt();
        }
    }

    private class b {
        public boolean a;
        public Bitmap b;
        final /* synthetic */ b c;

        private b(b bVar) {
            this.c = bVar;
            this.a = false;
        }
    }

    public class c {
        public ImageView a;
        final /* synthetic */ b b;

        public c(b bVar) {
            this.b = bVar;
        }
    }

    public b(Context context) {
        this.h = context;
        this.i = LayoutInflater.from(context);
    }

    public long a() {
        return this.a;
    }

    public boolean a(int i, long j, String str, FineCutHorizonalScrollView fineCutHorizonalScrollView) {
        this.g = fineCutHorizonalScrollView;
        this.a = j;
        this.d = i;
        this.k = str;
        this.q = new MediaMetadataRetriever();
        this.o = new a(this, "load");
        if (str != null) {
            this.q.setDataSource(this.k);
        }
        this.j = new HashMap();
        WindowManager windowManager = (WindowManager) this.h.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i2 = displayMetrics.widthPixels;
        String extractMetadata = this.q.extractMetadata(9);
        if (extractMetadata == null || extractMetadata == "" || extractMetadata == "0") {
            this.b = (long) f.a(str).a();
        } else {
            this.b = (long) Integer.valueOf(extractMetadata).intValue();
        }
        this.c = (this.d * 16) / 9;
        this.e = dji.pilot.fpv.model.b.a(this.h, R.dimen.b8);
        long j2 = (long) (i2 - this.e);
        long j3 = (2 * j2) / 5;
        this.m = ((this.b * j2) / 30000) / ((long) this.c);
        if (this.m == 0) {
            this.m = 1;
        }
        long j4 = ((this.a * this.m) * ((long) this.c)) / this.b;
        int i3 = (int) (this.b / this.m);
        if (this.g.getIsSingleMoment() == 1 || this.g.getIsSingleMoment() == 2) {
            this.f = (int) j2;
        } else {
            this.f = (int) (j2 - j4);
            if (this.f <= 0) {
                this.f = (int) j2;
            }
        }
        this.l = i3;
        this.p = false;
        this.o.start();
        return true;
    }

    public void b() {
        o.a("WB myhscrolViewAdapter unInit");
        if (this.o != null) {
            this.p = true;
            this.o.b();
            this.o = null;
        }
        if (this.q != null) {
            this.q.release();
            this.q = null;
        }
        for (Entry value : this.j.entrySet()) {
            b bVar = (b) value.getValue();
            if (!(bVar.b == null || bVar.b.isRecycled())) {
                o.a("WB Recycle bitmap");
                bVar.b.recycle();
                bVar.b = null;
            }
        }
        o.a("WB  end myhscrolViewAdapter unInit");
        this.j.clear();
    }

    public int c() {
        return (int) this.m;
    }

    public double d() {
        return (((double) this.m) * ((double) this.c)) / ((double) this.b);
    }

    public void a(String str) {
        this.k = str;
    }

    public Bitmap a(int i) {
        Bitmap bitmap;
        synchronized (this.j) {
            b bVar = (b) this.j.get(Integer.valueOf(i));
            if (bVar != null) {
                bitmap = bVar.b;
            } else {
                bitmap = null;
            }
        }
        return bitmap;
    }

    public long b(int i) {
        return (long) i;
    }

    public View a(int i, View view, ViewGroup viewGroup) {
        View inflate;
        c cVar;
        if (view == null) {
            c cVar2 = new c(this);
            inflate = this.i.inflate(R.layout.v2_multimoment_fine_horizonal_view, viewGroup, false);
            cVar2.a = (ImageView) inflate;
            inflate.setTag(cVar2);
            cVar = cVar2;
        } else {
            cVar = (c) view.getTag();
            inflate = view;
        }
        cVar.a.setImageBitmap(c(i));
        return inflate;
    }

    public Bitmap c(int i) {
        Bitmap bitmap;
        synchronized (this.j) {
            boolean containsKey = this.j.containsKey(Integer.valueOf(i));
        }
        if (containsKey) {
            synchronized (this.j) {
                bitmap = ((b) this.j.get(Integer.valueOf(i))).b;
            }
        } else if (i == 0) {
            bitmap = d(i);
            r1 = new b();
            r1.b = bitmap;
            r1.a = true;
            synchronized (this.j) {
                this.j.put(Integer.valueOf(i), r1);
            }
        } else {
            int i2 = i;
            bitmap = null;
            while (bitmap == null) {
                int i3 = i2 - 1;
                int i4 = i3;
                bitmap = c(i3);
                i2 = i4;
            }
            r1 = new b();
            r1.b = bitmap;
            r1.a = false;
            synchronized (this.j) {
                this.j.put(Integer.valueOf(i), r1);
            }
            Intent intent = new Intent();
            intent.putExtra("TIME", i);
            this.o.a(intent);
        }
        return bitmap;
    }

    private Bitmap d(int i) {
        Bitmap frameAtTime;
        DJILogHelper.getInstance().LOGI("jjj", "time = " + i + "mInterval = " + this.l + "duration = " + this.b);
        long j = (long) (this.l * i);
        if (j > this.b) {
            j = this.b;
        }
        if (j == 0) {
            j = this.b;
            if (j > 1000) {
                j = 1000;
            }
        }
        Bitmap frameAtTime2 = this.q.getFrameAtTime(j * 1000, 2);
        if (frameAtTime2 == null) {
            frameAtTime = VideoLibWrapper.getFrameAtTime(this.k, j * 1000, 0);
        } else {
            frameAtTime = frameAtTime2;
        }
        Bitmap a = a(frameAtTime, this.c, this.d);
        if (!(frameAtTime == null || frameAtTime.isRecycled())) {
            frameAtTime.recycle();
        }
        return a;
    }

    private static Bitmap a(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i) / ((float) width), ((float) i2) / ((float) height));
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public void a(Intent intent) {
        final int intExtra = intent.getIntExtra("TIME", 0);
        final Bitmap d = d(intExtra);
        this.n.post(new Runnable(this) {
            final /* synthetic */ b c;

            public void run() {
                this.c.g.updateChildView(intExtra, d);
            }
        });
        synchronized (this.j) {
            b bVar = (b) this.j.get(Integer.valueOf(intExtra));
            if (bVar != null) {
                bVar.b = d;
                bVar.a = true;
            }
        }
    }
}
