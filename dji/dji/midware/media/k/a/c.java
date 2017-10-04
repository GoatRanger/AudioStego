package dji.midware.media.k.a;

import android.util.Log;
import dji.midware.media.e;
import dji.midware.natives.FPVController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class c implements Runnable {
    private static final int a = 102400;
    private static String b = "H264FileLoader";
    private String c = "";
    private boolean d;
    private boolean e;
    private long f;
    private long g;
    private a h = null;
    private Thread i = null;

    public interface a {
        void a(int i);
    }

    public void a(String str) {
        this.d = false;
        this.e = false;
        this.f = 0;
        this.g = 0;
        this.c = str;
        this.i = new Thread(this);
        this.i.start();
    }

    public void run() {
        Object e;
        Throwable th;
        FileInputStream fileInputStream = null;
        try {
            File file = new File(this.c);
            this.f = file.length();
            Log.i(b, "file length=" + this.f);
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                byte[] bArr = new byte[102400];
                long j = -1;
                int i = -1;
                while (true) {
                    int read = fileInputStream2.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    this.g += (long) read;
                    if (this.e) {
                        break;
                    }
                    while (true) {
                        if (!this.d && FPVController.native_getQueueSize() <= 20) {
                            break;
                        }
                        Thread.sleep(10);
                    }
                    FPVController.native_transferVideoData(bArr, read);
                    if (this.h != null) {
                        int i2;
                        int e2 = e();
                        long currentTimeMillis = System.currentTimeMillis();
                        if (e2 == i || e2 == 100 || currentTimeMillis - j <= 500) {
                            currentTimeMillis = j;
                            i2 = i;
                        } else {
                            this.h.a(e2);
                            i2 = e2;
                        }
                        i = i2;
                        j = currentTimeMillis;
                    }
                }
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Exception e3) {
                        Log.e(b, "Error while closing stream: " + e3);
                    }
                }
            } catch (FileNotFoundException e4) {
                e = e4;
                fileInputStream = fileInputStream2;
            } catch (Exception e5) {
                e = e5;
                fileInputStream = fileInputStream2;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = fileInputStream2;
            }
        } catch (FileNotFoundException e6) {
            e = e6;
            try {
                Log.e(b, "Fine not found" + e);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e32) {
                        Log.e(b, "Error while closing stream: " + e32);
                    }
                }
                if (this.e) {
                    Log.i(b, "All data has been loaded");
                } else {
                    Log.i(b, "is stopped");
                }
            } catch (Throwable th3) {
                th = th3;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e7) {
                        Log.e(b, "Error while closing stream: " + e7);
                    }
                }
                throw th;
            }
        } catch (Exception e8) {
            e = e8;
            Log.e(b, "Exception while reading file " + e);
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e322) {
                    Log.e(b, "Error while closing stream: " + e322);
                }
            }
            if (this.e) {
                Log.i(b, "is stopped");
            } else {
                Log.i(b, "All data has been loaded");
            }
        }
        try {
            if (this.e) {
                Log.i(b, "is stopped");
            } else {
                Log.i(b, "All data has been loaded");
            }
        } catch (Exception e3222) {
            Writer stringWriter = new StringWriter();
            e3222.printStackTrace(new PrintWriter(stringWriter));
            Log.e(b, stringWriter.toString());
        }
    }

    public void a() {
        try {
            this.i.join();
        } catch (Exception e) {
            e.a(b, e);
        }
    }

    public void b() {
        this.d = true;
    }

    public void c() {
        this.d = false;
    }

    public void d() {
        this.e = true;
        a();
        e.a("", "H264FileLoader thread ends");
    }

    private int e() {
        int i = 100;
        int i2 = this.f == 0 ? 0 : (int) ((100.0d * ((double) this.g)) / ((double) this.f));
        if (i2 > 100) {
            Log.e(b, "progress>100, is " + i2);
        } else {
            i = i2;
        }
        if (i >= 0) {
            return i;
        }
        Log.e(b, "progress<0, is " + i);
        return 0;
    }

    public void a(a aVar) {
        this.h = aVar;
    }
}
