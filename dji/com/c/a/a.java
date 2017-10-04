package com.c.a;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.View;
import com.dji.frame.c.d;

@SuppressLint({"NewApi"})
public class a {
    private static final String a = "KEY_CACHE_BLURRED_BACKGROUND_IMAGE";
    private static final int b = 12;
    private static final int c = 100;
    private static final String d = "DJI_Temp/tmp_blur_bg.png";
    private static final LruCache<String, Bitmap> e = new LruCache(1);
    private static a f;
    private static a l;
    private int g = 100;
    private int h = -1;
    private int i = 12;
    private boolean j = false;
    private b k = b.READY;

    private class a extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ a a;
        private Activity b;
        private b c;
        private View d;
        private Bitmap e;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Void) obj);
        }

        public a(a aVar, Activity activity, b bVar) {
            this.a = aVar;
            this.b = activity;
            this.c = bVar;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("zhangchen", "cut");
            if (this.a.j) {
                Options options = new Options();
                options.inPreferredConfig = Config.ARGB_8888;
                this.e = BitmapFactory.decodeFile(d.a(this.b, a.d), options);
            }
            if (!this.a.j || this.e == null) {
                this.d = this.b.getWindow().getDecorView();
                this.d.setDrawingCacheQuality(524288);
                this.d.setDrawingCacheEnabled(true);
                this.d.buildDrawingCache();
                this.e = this.d.getDrawingCache();
                Log.e("zhangchen", "pa:" + this.b.getPackageName());
                this.a.j = false;
            }
        }

        protected Void a(Void... voidArr) {
            if (this.a.j) {
                a.e.put(a.a, this.e);
            } else {
                Bitmap a = com.c.a.a.a.a(this.b, this.e, this.a.i);
                a.e.put(a.a, a);
                dji.pilot.usercenter.f.a.a(a, d.a(this.b, a.d));
            }
            return null;
        }

        protected void a(Void voidR) {
            super.onPostExecute(voidR);
            if (this.d != null) {
                this.d.destroyDrawingCache();
                this.d.setDrawingCacheEnabled(false);
            }
            this.b = null;
            this.c.a();
            this.a.k = b.READY;
        }
    }

    private enum b {
        READY,
        EXECUTING
    }

    public static a getInstance() {
        if (l == null) {
            l = new a();
        }
        return l;
    }

    public void a(Activity activity, boolean z, b bVar) {
        a();
        if (this.k.equals(b.READY)) {
            this.k = b.EXECUTING;
            this.j = z;
            f = new a(this, activity, bVar);
            f.execute(new Void[0]);
        }
    }

    public a a(int i) {
        this.g = i;
        return this;
    }

    public a b(int i) {
        this.h = i;
        return this;
    }

    public a c(int i) {
        if (i <= 0) {
            i = 1;
        } else if (i >= 25) {
            i = 25;
        }
        this.i = i;
        return this;
    }

    public void a(Activity activity) {
        if (e.size() != 0) {
            Drawable bitmapDrawable = new BitmapDrawable(activity.getResources(), (Bitmap) e.get(a));
            bitmapDrawable.setAlpha(this.g);
            if (this.h != -1) {
                bitmapDrawable.setColorFilter(this.h, Mode.DST_ATOP);
            }
            activity.getWindow().setBackgroundDrawable(bitmapDrawable);
            this.j = false;
            e.remove(a);
            f = null;
        }
    }

    public void b(Activity activity) {
        if (e.size() != 0) {
            Drawable bitmapDrawable = new BitmapDrawable(activity.getResources(), (Bitmap) e.get(a));
            bitmapDrawable.setAlpha(this.g);
            if (this.h != -1) {
                bitmapDrawable.setColorFilter(this.h, Mode.DST_ATOP);
            }
            activity.getWindow().setBackgroundDrawable(bitmapDrawable);
        }
    }

    public void a() {
        this.j = false;
        e.remove(a);
        f = null;
    }
}
