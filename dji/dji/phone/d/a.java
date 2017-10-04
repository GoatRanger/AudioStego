package dji.phone.d;

import android.util.Log;
import com.alipay.sdk.j.i;
import java.util.LinkedHashMap;

public class a implements b {
    private static final Object h = new Object();
    private final String g = a.class.getSimpleName();
    private LinkedHashMap<String, Integer> i = new LinkedHashMap(64);
    private LinkedHashMap<String, Integer> j = new LinkedHashMap(64);
    private LinkedHashMap<String, Integer> k = null;
    private b l;
    private c m;
    private a n;

    public enum a {
        SINGLE_0s,
        SINGLE_2s,
        SINGLE_5s,
        SINGLE_10s,
        SINGLE_HDR,
        PANO_180,
        PANO_330,
        PANO_WIDE,
        LONGEXPOSURE_4s,
        LONGEXPOSURE_8s,
        LONGEXPOSURE_16s,
        LONGEXPOSURE_32s,
        LONGEXPOSURE_INFINITY
    }

    public enum b {
        NOT_PHOTOING,
        SINGLE,
        PANO,
        LONGEXPOSURE
    }

    public enum c {
        NOT_VEDIO,
        AUTO,
        TIMELAPSE_STATIONARY,
        TIMELAPSE_MOTION
    }

    public void a() {
        synchronized (h) {
            this.k = this.i;
        }
    }

    public void b() {
        synchronized (h) {
            this.k = this.j;
        }
    }

    public int a(String str) {
        int intValue;
        synchronized (h) {
            intValue = ((Integer) this.k.get(str)).intValue();
        }
        return intValue;
    }

    private void b(String str, int i) {
        synchronized (h) {
            this.k.remove(str);
            this.k.put(str, Integer.valueOf(i));
        }
    }

    public void a(int i, boolean z) {
        Log.d(this.g, "setWhiteBalanceId: " + i);
        a("whitebalance", i);
        dji.pilot.phonecamera.a.c.a().b(i);
        if (z) {
            j();
        }
    }

    public int c() {
        return a("whitebalance");
    }

    public void b(int i, boolean z) {
        Log.d(this.g, "setFlashModeId: " + i);
        a(b.b, i);
        dji.pilot.phonecamera.a.c.a().d(i);
        if (z) {
            j();
        }
    }

    public int d() {
        int a = a(b.b);
        Log.d(this.g, "getFlashModeId: " + a);
        return a;
    }

    public void c(int i, boolean z) {
        Log.d(this.g, "setZoom: value = " + i);
        a(b.d, i);
        if (z) {
            j();
        }
    }

    public int e() {
        int a = a(b.d);
        Log.d(this.g, "getZoom: " + a);
        return a;
    }

    public void a(String str, int i) {
        b(str, i);
    }

    public void d(int i, boolean z) {
        a(b.c, i);
        if (z) {
            j();
        }
    }

    public int f() {
        Log.d(this.g, "getVideoQualityId: ");
        return Integer.valueOf(a(b.c)).intValue();
    }

    private void j() {
        dji.thirdparty.a.c.a().e(this.k);
    }

    private void a(c cVar) {
        dji.thirdparty.a.c.a().e(cVar);
    }

    private void a(b bVar) {
        dji.thirdparty.a.c.a().e(bVar);
    }

    private void a(a aVar) {
        dji.thirdparty.a.c.a().e(aVar);
    }

    public synchronized void a(b bVar, boolean z) {
        this.l = bVar;
        switch (bVar) {
            case SINGLE:
                dji.pilot.phonecamera.a.c.a().e(dji.pilot.phonecamera.a.c.x);
                break;
            case LONGEXPOSURE:
                dji.pilot.phonecamera.a.c.a().e(dji.pilot.phonecamera.a.c.y);
                break;
            case PANO:
                dji.pilot.phonecamera.a.c.a().e("pano");
                break;
        }
        if (z) {
            a(bVar);
        }
    }

    public synchronized b g() {
        return this.l;
    }

    public synchronized void a(c cVar, boolean z) {
        Log.d(this.g, "setVideoType: type = " + cVar.name());
        this.m = cVar;
        if (z) {
            a(cVar);
        }
    }

    public synchronized c h() {
        Log.d(this.g, "getVideoType: type = " + this.m.name());
        return this.m;
    }

    public synchronized void a(a aVar, boolean z) {
        Log.d(this.g, "setPhotoDetailType: " + aVar);
        this.n = aVar;
        switch (aVar) {
            case SINGLE_0s:
                dji.pilot.phonecamera.a.c.a().l(0);
                break;
            case SINGLE_2s:
                dji.pilot.phonecamera.a.c.a().l(2);
                break;
            case SINGLE_5s:
                dji.pilot.phonecamera.a.c.a().l(5);
                break;
            case SINGLE_10s:
                dji.pilot.phonecamera.a.c.a().l(10);
                break;
            case SINGLE_HDR:
                dji.pilot.phonecamera.a.c.a().l(0);
                break;
            case LONGEXPOSURE_4s:
                dji.pilot.phonecamera.a.c.a().k(4);
                break;
            case LONGEXPOSURE_8s:
                dji.pilot.phonecamera.a.c.a().k(8);
                break;
            case LONGEXPOSURE_16s:
                dji.pilot.phonecamera.a.c.a().k(16);
                break;
            case LONGEXPOSURE_32s:
                dji.pilot.phonecamera.a.c.a().k(32);
                break;
            case LONGEXPOSURE_INFINITY:
                dji.pilot.phonecamera.a.c.a().k(0);
                break;
        }
        if (z) {
            a(aVar);
        }
    }

    public synchronized a i() {
        return this.n;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ photo type = ").append(this.l).append(",video type = ").append(this.m).append(", photo_detail_type = ").append(this.n).append(", flash-mode=").append(this.k.get(b.b)).append(", whitebalance=").append(this.k.get("whitebalance"));
        stringBuilder.append(i.d);
        return stringBuilder.toString();
    }
}
