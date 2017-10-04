package dji.midware.media.e;

import android.util.Log;
import com.dji.frame.c.d;
import dji.midware.data.manager.P3.ServiceManager;
import java.io.File;
import java.util.Locale;

public class e {
    private static String a = "BufferedVideoDatabase";

    public static f a(String str) {
        f fVar = new f();
        String str2 = a() + str + ".info";
        if (!new File(str2).exists()) {
            return null;
        }
        try {
            fVar.g(str2);
            return fVar;
        } catch (Exception e) {
            return null;
        }
    }

    public static String a() {
        ServiceManager.getInstance();
        String a = d.a(ServiceManager.getContext(), "DJI_RECORD/");
        dji.midware.media.d.b(a);
        return a;
    }

    public static String b() {
        ServiceManager.getInstance();
        String a = d.a(ServiceManager.getContext(), "VideoDatabase/MomentInfo/");
        dji.midware.media.d.b(a);
        return a;
    }

    private static String g(String str) {
        if (str == null || str.length() < 4) {
            dji.midware.media.e.b(a, "unrecognised video file path");
            return str;
        }
        String toLowerCase = str.toLowerCase(Locale.US);
        if (toLowerCase.endsWith(".mp4") || toLowerCase.endsWith(".mov")) {
            return toLowerCase.substring(0, toLowerCase.length() - 4) + ".info";
        }
        dji.midware.media.e.b(a, "unrecognised video file path");
        return str;
    }

    public static f a(String str, String str2, int i, int i2) {
        Log.i(a, "create a moment info: momentName=" + str + "; sourceFile=" + str2 + " startTime=" + i + " endTime=" + i2);
        f fVar = new f();
        if (str2 != null) {
            String g = g(str2);
            if (new File(g).exists()) {
                try {
                    fVar.g(g);
                } catch (Exception e) {
                }
            } else if (a(fVar, str2)) {
                fVar.c(Integer.valueOf(2));
            }
            fVar.m(i);
            fVar.l(i2);
            fVar.e(str);
            fVar.h(str2);
            fVar.f(h(str));
        }
        return fVar;
    }

    public static void b(String str) {
        new File(h(str)).delete();
    }

    public static boolean c(String str) {
        return new File(h(str)).exists();
    }

    public static boolean d(String str) {
        return new File(a() + str + ".info").exists();
    }

    private static String h(String str) {
        return b() + "moment_" + str + ".info";
    }

    private static String i(String str) {
        String name = new File(str).getName();
        if (name == null || name.length() < 4 || !name.toLowerCase(Locale.US).endsWith(".mp4")) {
            return null;
        }
        return h(name.substring(0, name.length() - 4));
    }

    public static void e(String str) {
        String i = i(str);
        if (i != null) {
            File file = new File(i);
            dji.midware.media.e.d(a, "Delete " + file.getAbsolutePath());
            file.delete();
        }
    }

    public static f f(String str) {
        Log.i(a, "Request a moment info with name " + str);
        f fVar = new f();
        String h = h(str);
        if (new File(h).exists()) {
            try {
                fVar.g(h);
                if (fVar.a() == null && a(fVar, fVar.H())) {
                    fVar.f(h);
                }
            } catch (Exception e) {
            }
        } else if (a(fVar, fVar.H())) {
            fVar.f(h);
        }
        return fVar;
    }

    private static boolean a(f fVar, String str) {
        if (!new File(str).exists()) {
            return false;
        }
        b bVar = new b();
        try {
            bVar.a(str);
            bVar.d();
            double[] b = bVar.b();
            if (!(b[0] == 0.0d || b[1] == 0.0d)) {
                fVar.a(b[0]);
                fVar.b(b[1]);
            }
            fVar.a(dji.logic.f.d.a(bVar.c()));
            fVar.a(bVar.e());
            dji.midware.media.e.d(a, "From drone. CaptureDate = " + fVar.m());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
