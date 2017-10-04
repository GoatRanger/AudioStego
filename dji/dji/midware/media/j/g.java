package dji.midware.media.j;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;
import com.alipay.sdk.j.i;
import dji.midware.stat.StatService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

public class g {
    public static long a = 1073741824;
    public static long b = StatService.BYTES_IN_MEGA;
    public static int c;
    public static a d = null;
    private static String e = "RecorderManager";
    private static boolean f = false;
    private static long g = (2 * a);
    private static long h = (50 * b);
    private static boolean i;
    private static f j = null;

    public enum a {
        GDR_OFFLINE,
        GDR_ONLINE,
        GOP
    }

    public enum b {
        START_RECORD,
        END_RECORD
    }

    public static class c {
        private String a;

        public c(String str) {
            this.a = str;
        }

        public String a() {
            return this.a;
        }
    }

    public enum d {
        RELEASE_CACHE_DONE
    }

    public enum e {
        NO_SPACE
    }

    static {
        c = 10000;
        if (dji.midware.media.d.a(f)) {
            c = 5000;
            dji.midware.media.e.a("Buffer space test is started");
        }
    }

    public static void a(long j) {
        g = j;
    }

    public static void a(boolean z) {
        i = z;
    }

    public static boolean a() {
        return i;
    }

    public static void a(a aVar) {
        d = aVar;
        switch (aVar) {
            case GDR_ONLINE:
                j = h.getInstance();
                return;
            case GDR_OFFLINE:
                j = e.getInstance();
                return;
            case GOP:
                j = d.getInstance();
                return;
            default:
                return;
        }
    }

    public static void b() {
        if (d != null) {
            switch (d) {
                case GDR_ONLINE:
                    h.m();
                    break;
                case GDR_OFFLINE:
                    e.m();
                    break;
                case GOP:
                    d.m();
                    break;
            }
            j = null;
        }
    }

    public static boolean c() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean g = g();
        if (f) {
            dji.midware.media.e.a("checkAndReleaseBuffer consumes time (ms): " + (System.currentTimeMillis() - currentTimeMillis));
        }
        return g;
    }

    private static boolean g() {
        try {
            long d = d();
            if (d >= h) {
                return true;
            }
            if (!i) {
                return false;
            }
            String a = dji.midware.media.e.e.a();
            if (a == null) {
                return false;
            }
            File[] listFiles = new File(a).listFiles(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    if (str.endsWith(".mp4")) {
                        return true;
                    }
                    return false;
                }
            });
            if (listFiles == null || listFiles.length == 0) {
                return false;
            }
            Arrays.sort(listFiles, new Comparator<File>() {
                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((File) obj, (File) obj2);
                }

                public int a(File file, File file2) {
                    long lastModified = file.lastModified() - file2.lastModified();
                    if (lastModified < 0) {
                        return -1;
                    }
                    if (lastModified > 0) {
                        return 1;
                    }
                    return 0;
                }
            });
            HashSet h = h();
            long j = d;
            for (File absolutePath : listFiles) {
                String absolutePath2 = absolutePath.getAbsolutePath();
                String substring = absolutePath2.substring(0, absolutePath2.length() - (absolutePath2.endsWith(".mp4") ? ".mp4" : ".h264").length());
                j = ((((j + a(substring + ".mp4", h)) + a(substring + ".h264", h)) + a(substring + dji.pilot2.main.a.a.n, h)) + a(substring + ".info", h)) + a(substring + ".m4a", h);
                if (j > h) {
                    dji.thirdparty.a.c.a().e(d.RELEASE_CACHE_DONE);
                    return true;
                }
            }
            if (d() < h) {
                return false;
            }
            return true;
        } catch (Exception e) {
            dji.midware.media.e.a(e);
            return true;
        }
    }

    private static long a(String str, HashSet<String> hashSet) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            stringBuilder.append(((String) it.next()) + i.b);
        }
        if (f) {
            dji.midware.media.e.a("try to delete: " + str + " filter: " + stringBuilder.toString());
        }
        if (hashSet == null || !hashSet.contains(str)) {
            File file = new File(str);
            long length = file.length();
            if (!file.delete()) {
                if (f) {
                    dji.midware.media.e.a("NOT EXIST");
                }
                return 0;
            } else if (!f) {
                return length;
            } else {
                dji.midware.media.e.a("SUCCESS");
                return length;
            }
        }
        if (f) {
            dji.midware.media.e.a("REJECT");
        }
        return 0;
    }

    public static long d() {
        long j = 0;
        long usableSpace = new File(dji.midware.media.e.e.a()).getUsableSpace();
        long a = g - a(new File(dji.midware.media.e.e.a()));
        if (usableSpace >= a) {
            usableSpace = a;
        }
        if (usableSpace >= 0) {
            j = usableSpace;
        }
        if (f) {
            dji.midware.media.e.a("Available space: " + j);
        }
        return j;
    }

    private static long a(File file) {
        long j = 0;
        if (file == null || !file.exists()) {
            return 0;
        }
        if (!file.isDirectory()) {
            return file.length();
        }
        for (File file2 : file.listFiles()) {
            if (file2.isFile()) {
                j += file2.length();
            } else {
                j += a(file2);
            }
        }
        return j;
    }

    private static HashSet<String> h() {
        String a = dji.midware.media.e.e.a();
        if (a == null) {
            return null;
        }
        HashSet<String> hashSet = new HashSet();
        String f = f();
        if (f == null) {
            return hashSet;
        }
        hashSet.add(a + f + ".h264");
        Log.i(e, "filter: " + a + f + ".h264");
        hashSet.add(a + f + ".mp4");
        Log.i(e, "filter: " + a + f + ".mp4");
        hashSet.add(a + f + ".info");
        Log.i(e, "filter: " + a + f + ".info");
        hashSet.add(a + f + dji.pilot2.main.a.a.n);
        Log.i(e, "filter: " + a + f + dji.pilot2.main.a.a.n);
        hashSet.add(a + f + ".aac");
        Log.i(e, "filter: " + a + f + ".aac");
        hashSet.add(a + f + ".m4a");
        Log.i(e, "filter: " + a + f + ".m4a");
        return hashSet;
    }

    public static void e() {
        if (f) {
            dji.midware.media.e.a("clearBuffer()");
        }
        String a = dji.midware.media.e.e.a();
        if (a != null) {
            b(a, h());
        }
    }

    private static void b(String str, HashSet<String> hashSet) {
        File file = new File(str);
        if (file.exists()) {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory()) {
                    if (!hashSet.contains(file2.getAbsolutePath())) {
                        b(file2.getAbsolutePath(), hashSet);
                        try {
                            if (f) {
                                dji.midware.media.e.a("delete directory:" + file2.getAbsolutePath());
                            }
                            file2.delete();
                            Log.i(e, "deleted " + file2.getAbsolutePath());
                        } catch (Exception e) {
                            Log.i(e, "failed to delete " + file2.getAbsolutePath());
                        }
                    }
                } else if (!hashSet.contains(file2.getAbsolutePath())) {
                    try {
                        if (f) {
                            dji.midware.media.e.a("delete a file:" + file2.getAbsolutePath());
                        }
                        file2.delete();
                        Log.i(e, "deleted " + file2.getAbsolutePath());
                    } catch (Exception e2) {
                        Log.i(e, "failed to delete " + file2.getAbsolutePath());
                    }
                }
            }
        }
    }

    public static String f() {
        if (j == null) {
            return null;
        }
        return j.l();
    }

    public static void a(Bitmap bitmap, String str) {
        Log.i(e, "is now saving a bitmap to a file");
        try {
            OutputStream fileOutputStream = new FileOutputStream(new File(str));
            if (fileOutputStream != null) {
                bitmap.compress(CompressFormat.JPEG, 85, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e(e, "error in saving thumb 1");
            e.printStackTrace();
        } catch (IOException e2) {
            Log.e(e, "error in saving thumb 2");
            e2.printStackTrace();
        }
    }
}
