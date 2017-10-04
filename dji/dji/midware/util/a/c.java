package dji.midware.util.a;

import android.os.Environment;
import android.util.Log;
import dji.midware.media.e;
import dji.pilot.usercenter.protocol.d;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class c {
    public static boolean a = false;
    public static final String b = "dji_video_codecqueuein";
    public static boolean c = false;
    public static final String d = "dji_video_usbaccessary";
    public static final String e = "dji_video_wifi";
    public static boolean f = false;
    public static final String g = "dji_video_datareceiver";
    public static boolean h = false;
    public static final String i = "dji_usbHybridDataStream";
    public static String j = "StreamSaver";
    private static HashMap<String, c> k = new HashMap();
    private final String l;
    private final String m;
    private FileOutputStream n = null;
    private FileOutputStream o = null;
    private int p = 0;
    private int q = 0;
    private DateFormat r = new SimpleDateFormat("mm_ss:SSS");

    public static c getInstance(String str) {
        c cVar = (c) k.get(str);
        if (cVar != null) {
            return cVar;
        }
        cVar = new c(str);
        k.put(str, cVar);
        return cVar;
    }

    public c(String str) {
        this.l = str;
        this.m = Environment.getExternalStorageDirectory().getPath() + d.t + str;
        Log.e(j, "creating StreamSaver[" + str + "]: " + this.m);
    }

    public void a(byte[] bArr, int i, int i2) {
        int i3 = 16;
        try {
            if (this.n == null) {
                this.n = new FileOutputStream(new File(this.m));
            }
            if (this.o == null) {
                this.o = new FileOutputStream(new File(this.m + ".index"));
            }
            if (this.n != null && this.o != null) {
                this.n.write(bArr, i, i2);
                StringBuilder stringBuilder = new StringBuilder();
                String str = "time=%s size=%d offset=%x first=[%s]";
                Object[] objArr = new Object[4];
                objArr[0] = this.r.format(new Date());
                objArr[1] = Integer.valueOf(i2);
                objArr[2] = Integer.valueOf(this.q);
                if (i2 < 16) {
                    i3 = i2;
                }
                objArr[3] = dji.midware.util.c.i(Arrays.copyOfRange(bArr, i, i3));
                this.o.write(stringBuilder.append(String.format(str, objArr)).append("\n").toString().getBytes());
                this.p++;
                this.q += i2;
                this.n.flush();
                this.o.flush();
            }
        } catch (Exception e) {
            e.a(b(), e);
        }
    }

    private String b() {
        return j + "_" + this.l;
    }

    public void a() {
        try {
            if (this.n != null) {
                this.n.flush();
                this.n.close();
                this.n = null;
            }
            if (this.o != null) {
                this.o.flush();
                this.o.close();
                this.o = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        k.remove(this.l);
    }

    public void finalize() {
        a();
    }
}
