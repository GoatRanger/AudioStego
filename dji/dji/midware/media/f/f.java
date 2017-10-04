package dji.midware.media.f;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import dji.midware.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class f {
    private static final String b = f.class.getName();
    private static f c = null;
    private static final int d = 100;
    private static final int e = 4;
    private static final int f = 4;
    public d a;
    private boolean g = false;
    private String h;
    private byte[] i;
    private int j = -1;
    private RandomAccessFile k;
    private boolean l = false;
    private RandomAccessFile m;
    private File n;

    public static synchronized f getInstance() {
        f fVar;
        synchronized (f.class) {
            if (c == null) {
                c = new f();
            }
            fVar = c;
        }
        return fVar;
    }

    public void a(String str) {
        this.h = str;
    }

    public void a() {
        File file = new File(this.h);
        if (file.exists()) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                byte[] bArr = new byte[100];
                randomAccessFile.read(bArr);
                int i = 0;
                while (i < 100) {
                    int b = c.b(bArr, i);
                    i += 4;
                    String b2 = c.b(bArr, i, 4);
                    i += b - 4;
                    Log.d(b, "Box size: " + b + " Box type:" + b2);
                    if (b.mdat.a(b2)) {
                        this.g = true;
                        break;
                    }
                }
                randomAccessFile.close();
                if (this.g) {
                    this.j = i;
                    b();
                    return;
                }
                Log.e(b, "Box moov not find");
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(b, "File not exist");
    }

    public int a(byte[] bArr) {
        Log.d(b, c.d(bArr, 0, 30));
        int i = 0;
        while (i < 100) {
            int b = c.b(bArr, i);
            i += 4;
            String b2 = c.b(bArr, i, 4);
            i += b - 4;
            Log.d(b, "Box size: " + b + " Box type:" + b2);
            if (b.mdat.a(b2)) {
                this.g = true;
                break;
            }
        }
        if (this.g) {
            this.j = i;
            return i;
        }
        Log.e(b, "Box moov not find");
        return 0;
    }

    public void b(byte[] bArr) {
        int b = c.b(bArr, 0);
        String a = c.a(bArr, 4, 4);
        Log.e(b, c.d(bArr, 0, 10) + "\nbox_size: " + b + " box_type: " + a);
        if (b.moov.a(a)) {
            this.i = new byte[b];
            System.arraycopy(bArr, 0, this.i, 0, b);
            d();
            return;
        }
        Log.e(b, "not find box moov");
    }

    public void b() throws IOException {
        File file = new File(this.h);
        if (file.exists()) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek((long) this.j);
            byte[] bArr = new byte[8];
            randomAccessFile.read(bArr);
            int b = c.b(bArr, 0);
            if (b.moov.a(c.a(bArr, 4, 4))) {
                this.i = new byte[b];
                randomAccessFile.seek((long) this.j);
                randomAccessFile.read(this.i);
                d();
                return;
            }
            Log.e(b, "not find box moov");
            return;
        }
        Log.e(b, "File not exist");
    }

    private boolean d() {
        e eVar = new e();
        if (!eVar.a(this.i, this.j)) {
            return false;
        }
        Log.d(b, "parse finish");
        this.a = new d(eVar);
        return true;
    }

    public void c() {
        File file = new File(this.h);
        if (file.exists()) {
            try {
                this.k = new RandomAccessFile(file, "rw");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (this.l) {
                this.n = new File(Environment.getExternalStorageDirectory().getPath() + "/video.264");
                try {
                    this.m = new RandomAccessFile(this.n, "rw");
                    return;
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            return;
        }
        Log.e(b, "File not exist");
    }

    public byte[] a(Context context) {
        InputStream openRawResource = context.getResources().openRawResource(R.raw.iframe_p4p_720_16x9);
        try {
            byte[] bArr = new byte[openRawResource.available()];
            openRawResource.read(bArr);
            openRawResource.close();
            return bArr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] a(int i, int i2) {
        int i3 = 0;
        byte[] bArr = new byte[i2];
        try {
            this.k.seek((long) i);
            this.k.read(bArr);
            while (i3 < i2) {
                int b = c.b(bArr, i3);
                bArr[i3] = (byte) 0;
                i3++;
                bArr[i3] = (byte) 0;
                i3++;
                bArr[i3] = (byte) 0;
                i3++;
                bArr[i3] = (byte) 1;
                i3 = (i3 + 1) + b;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.l) {
            try {
                this.m.write(bArr);
                if (i == this.a.e.length) {
                    this.m.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return bArr;
    }
}
