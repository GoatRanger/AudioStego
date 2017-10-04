package dji.pilot2.media;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.LruCache;
import dji.log.DJILogHelper;

public class e {
    private static e a;
    private LruCache<String, Bitmap> b;
    private Options c;

    public static synchronized e getInstance() {
        e eVar;
        synchronized (e.class) {
            if (a == null) {
                a = new e();
            }
            eVar = a;
        }
        return eVar;
    }

    public e() {
        this.c = new Options();
        this.c.inPreferredConfig = Config.RGB_565;
        this.b = new LruCache<String, Bitmap>(this, ((int) (Runtime.getRuntime().maxMemory() / 1024)) / 8) {
            long a = 0;
            final /* synthetic */ e b;

            protected /* synthetic */ void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
                a(z, (String) obj, (Bitmap) obj2, (Bitmap) obj3);
            }

            protected /* synthetic */ int sizeOf(Object obj, Object obj2) {
                return a((String) obj, (Bitmap) obj2);
            }

            protected int a(String str, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }

            protected void a(boolean z, String str, Bitmap bitmap, Bitmap bitmap2) {
                this.a += (long) bitmap.getByteCount();
                if (this.a > 204800) {
                    this.a = 0;
                    DJILogHelper.getInstance().LOGE("wwwwwww", "DJIVeCacheManager entryRemoved");
                }
            }
        };
    }

    public void a(String str, Bitmap bitmap) {
        if (!b(str)) {
            this.b.put(str, bitmap);
        }
    }

    public void a(String str, byte[] bArr, int i, int i2) {
        this.b.put(str, BitmapFactory.decodeByteArray(bArr, i, i2, this.c));
    }

    public Bitmap a(String str) {
        if (str == null) {
            return null;
        }
        return (Bitmap) this.b.get(str);
    }

    public boolean b(String str) {
        return a(str) != null;
    }

    public void a() {
        this.b.evictAll();
    }
}
