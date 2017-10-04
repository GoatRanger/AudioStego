package dji.logic.album.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.LruCache;
import com.dji.frame.c.d;
import com.dji.frame.c.f;
import dji.pilot2.media.activity.DJIPhotoPreveiwActivity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class b {
    private static b a;
    private LruCache<String, Bitmap> b;
    private String c = "";
    private Options d;
    private RandomAccessFile e;
    private String f = null;

    public static synchronized b getInstance(Context context) {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                a = new b(context);
            }
            bVar = a;
        }
        return bVar;
    }

    public static synchronized b getInstance() {
        b bVar;
        synchronized (b.class) {
            bVar = a;
        }
        return bVar;
    }

    public b(Context context) {
        this.c = d.a(context, "/CACHE_IMAGE/");
        File file = new File(this.c);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.d = new Options();
        this.d.inPreferredConfig = Config.RGB_565;
        this.b = new LruCache<String, Bitmap>(this, ((int) (Runtime.getRuntime().maxMemory() / 1024)) / 16) {
            final /* synthetic */ b a;

            protected /* synthetic */ int sizeOf(Object obj, Object obj2) {
                return a((String) obj, (Bitmap) obj2);
            }

            protected int a(String str, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public void a(String str, Bitmap bitmap) {
        if (!b(str)) {
            this.b.put(str, bitmap);
        }
    }

    public void a(String str, byte[] bArr, int i, int i2) {
        this.b.put(str, BitmapFactory.decodeByteArray(bArr, i, i2, this.d));
    }

    public Bitmap a(String str) {
        return (Bitmap) this.b.get(str);
    }

    public boolean b(String str) {
        return a(str) != null;
    }

    public void a() {
        this.b.evictAll();
    }

    public void b(String str, Bitmap bitmap) {
        if (!d(str)) {
            File file = new File(h(str));
            try {
                file.createNewFile();
                OutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void c(String str, Bitmap bitmap) {
        File file = new File(h(str));
        try {
            file.createNewFile();
            OutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bitmap c(String str) {
        return BitmapFactory.decodeFile(h(str), this.d);
    }

    public boolean d(String str) {
        if (str.contains(DJIPhotoPreveiwActivity.E) && this.f != null && new File(a(this.f, str)).exists()) {
            return true;
        }
        return new File(h(str)).exists();
    }

    public void e(String str) {
        File file = new File(h(str));
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            this.e = new RandomAccessFile(file, "rws");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void f(String str) {
        File file = new File(h(str));
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.e = new RandomAccessFile(file, "rws");
            this.e.seek(this.e.length());
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public void b() {
        if (this.e != null) {
            try {
                this.e.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.e = null;
        }
    }

    public void a(long j) {
        if (this.e != null) {
            try {
                this.e.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.e = null;
        }
    }

    public void a(byte[] bArr, int i, int i2) {
        try {
            this.e.write(bArr, i, i2);
            this.e.getFD().sync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long g(String str) {
        File file = new File(h(str));
        if (file.exists()) {
            return file.length();
        }
        return 0;
    }

    public String h(String str) {
        File file = new File(this.c);
        if (!file.exists()) {
            file.mkdirs();
        }
        return this.c + str;
    }

    public String a(String str, String str2) {
        return str + str2;
    }

    public void c() {
        try {
            f.e(new File(this.c));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void i(String str) {
        this.f = str;
    }
}
