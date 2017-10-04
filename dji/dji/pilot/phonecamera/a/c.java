package dji.pilot.phonecamera.a;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources.NotFoundException;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.media.CamcorderProfile;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Log;
import dji.midware.usb.P3.DJIUsbAccessoryReceiver;
import dji.pilot.phonecamera.R;
import dji.pilot.phonecamera.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

public class c {
    public static final String A = "720P";
    public static final float B = 0.0f;
    public static final String C = "720 x 480";
    public static final int D = 75;
    public static final String E = "auto";
    public static final String F = "off";
    public static final String G = "off";
    public static final String H = "auto";
    public static final String I = "auto";
    public static final int J = 0;
    public static final boolean K = true;
    public static final boolean L = false;
    public static final boolean M = false;
    public static final String N = "0";
    public static final int O = 0;
    public static final int P = 1;
    public static final int Q = 1;
    public static final int R = 0;
    public static final int S = 1;
    public static final String T = "storage_dir";
    public static final String U = "storage_dir_id";
    public static final String V = "preference_using_saf";
    public static final String W = "preference_save_location_saf";
    public static final String X = "preference_save_external_location_saf";
    public static final int Y = 0;
    public static final int Z = 1;
    public static final String a = "pref_version_key";
    public static final int aa = 2;
    public static final int ab = 3;
    private static final int ac = -1;
    private static final String ah = "CameraSettings";
    private static c ao = null;
    public static final String b = "pref_local_version_key";
    public static final String c = "pref_video_quality_key";
    public static final String d = "pref_video_quality_key_id";
    public static final String e = "pref_video_time_lapse_frame_interval_key";
    public static final String f = "pref_camera_picturesize_key";
    public static final String g = "pref_camera_jpegquality_key";
    public static final String h = "pref_camera_focusmode_key";
    public static final String i = "flashmode";
    public static final String j = "pref_camera_video_flashmode_key";
    public static final String k = "whitebalance";
    public static final String l = "pref_camera_scenemode_key";
    public static final String m = "pref_camera_exposure_key";
    public static final String n = "pref_camera_timer_key";
    public static final String o = "pref_camera_timer_sound_key";
    public static final String p = "pref_camera_id_key";
    public static final String q = "pref_camera_hdr_key";
    public static final String r = "pref_camera_hdr_plus_key";
    public static final String s = "camera_startup_module";
    public static final String t = "max_video_duration";
    public static final String u = "photo_type";
    public static final String v = "grid_type";
    public static final String w = "long_exposure_timer";
    public static final String x = "single";
    public static final String y = "long_exposure";
    public static final String z = "pano";
    private boolean ad = false;
    private int ae;
    private int af;
    private boolean ag = true;
    private CopyOnWriteArrayList<b> ai = new CopyOnWriteArrayList();
    private Parameters aj;
    private b ak = null;
    private final LinkedHashMap<String, Integer> al = new LinkedHashMap(64);
    private final LinkedHashMap<String, Integer> am = new LinkedHashMap(64);
    private LinkedHashMap<String, Integer> an;

    public enum a {
        GRID_NONE(0),
        GRID_LINE(1),
        GRID_DIAGONAL(2),
        GRID_CENTER_POINT(3);
        
        private int e;

        private a(int i) {
            this.e = 0;
            this.e = i;
        }

        public static a valueOf(int i) {
            switch (i) {
                case 0:
                    return GRID_NONE;
                case 1:
                    return GRID_LINE;
                case 2:
                    return GRID_DIAGONAL;
                case 3:
                    return GRID_CENTER_POINT;
                default:
                    return GRID_NONE;
            }
        }

        public int a() {
            return this.e;
        }
    }

    public interface b {
        void l();
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (ao == null) {
                ao = new c();
            }
            cVar = ao;
        }
        return cVar;
    }

    private c() {
    }

    public void a(Parameters parameters) {
        this.aj = parameters;
        if (s() == d.a().i()) {
            this.an = this.am;
        } else if (s() == d.a().j()) {
            this.an = this.al;
        } else {
            this.an = this.am;
        }
    }

    public void a(boolean z) {
        if (VERSION.SDK_INT >= 21) {
            Log.d(ah, "setKeyUsingSAF: " + z);
            Editor edit = this.ak.edit();
            edit.putBoolean(V, z);
            edit.commit();
        }
    }

    public boolean b() {
        if (VERSION.SDK_INT < 21) {
            return false;
        }
        Log.d(ah, "isUsingSAF: " + this.ak.getBoolean(V, false));
        return this.ak.getBoolean(V, false);
    }

    public void a(String str) {
        if (VERSION.SDK_INT >= 21) {
            Editor edit = this.ak.edit();
            edit.putString(W, str);
            edit.commit();
        }
    }

    public void b(String str) {
        if (VERSION.SDK_INT >= 21) {
            Editor edit = this.ak.edit();
            edit.putString(X, str);
            edit.commit();
        }
    }

    public void a(Context context, String str) {
        if (VERSION.SDK_INT >= 21) {
            context.getSharedPreferences(context.getPackageName(), 0).edit().putString(X, str).commit();
        }
    }

    public Uri a(Context context) {
        Uri parse = Uri.parse(context.getSharedPreferences(context.getPackageName(), 0).getString(X, ""));
        Log.d(ah, "getGlobalTreeUriSAF: " + parse.toString());
        return parse;
    }

    public Uri c() {
        if (this.ak == null) {
            return null;
        }
        return Uri.parse(this.ak.getString(X, ""));
    }

    public Uri d() {
        Uri parse = Uri.parse(this.ak.getString(W, ""));
        Log.d(ah, "getTreeUriSAF: " + parse);
        return parse;
    }

    public void c(String str) {
        Editor edit = this.ak.edit();
        edit.putString(T, str);
        edit.commit();
    }

    public String b(Context context) {
        return this.ak.getString(T, context.getText(R.string.lp_phone_camera_storage_internal) + ":" + Environment.getExternalStoragePublicDirectory(DJIUsbAccessoryReceiver.c).toString() + "/Camera");
    }

    public void a(int i) {
        Editor edit = this.ak.edit();
        edit.putInt(U, i);
        edit.commit();
    }

    public int e() {
        return this.ak.getInt(U, 0);
    }

    public void a(b bVar) {
        this.ak = bVar;
        bVar.a(s());
    }

    public void a(Parameters parameters, int i) {
        this.aj = parameters;
        m(i);
        this.ak.a(i);
        if (i == d.a().i()) {
            this.an = this.am;
        } else if (i == d.a().j()) {
            this.an = this.al;
        } else {
            this.an = this.am;
        }
    }

    public synchronized void b(int i) {
        Log.d(ah, "setWhiteBalanceId: wbindex = " + i);
        this.an.put("whitebalance", new Integer(i));
    }

    public int f() {
        Log.d(ah, "getWhiteBalanceId: " + this.an.get("whitebalance"));
        Integer num = (Integer) this.an.get("whitebalance");
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public String c(int i) {
        switch (i) {
            case 0:
                return "auto";
            case 1:
                return "incandescent";
            case 2:
                return "fluorescent";
            case 3:
                return "daylight";
            case 4:
                return "cloudy-daylight";
            case 5:
                return "shade";
            case 6:
                return "twilight";
            default:
                return "auto";
        }
    }

    public int g() {
        Log.d(ah, "getFlashModeId: " + this.an.get(i));
        Integer num = (Integer) this.an.get(i);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public synchronized void d(int i) {
        Log.d(ah, "setFlashModeId: " + i);
        this.an.put(i, new Integer(i));
    }

    public String e(int i) {
        if (i == 0) {
            return "off";
        }
        if (i == 1) {
            return "on";
        }
        if (i == 2) {
            return "auto";
        }
        if (i == 3) {
            return "torch";
        }
        return "off";
    }

    public synchronized void b(boolean z) {
        this.ad = z;
    }

    public boolean h() {
        return this.ad;
    }

    public synchronized void f(int i) {
        this.ae = i;
    }

    public synchronized void g(int i) {
        this.af = i;
    }

    public int i() {
        return this.ae;
    }

    public int j() {
        return this.af;
    }

    public c h(int i) {
        if (4 >= i || i >= 8) {
            d(true);
        }
        Log.d(ah, "setVideoQualityId: " + i);
        Editor edit = this.ak.edit();
        edit.putInt(d, i);
        edit.commit();
        return a();
    }

    public c i(int i) {
        if (i < 0 || i > 3) {
            d(true);
        }
        Editor edit = this.ak.edit();
        edit.putInt(v, i);
        edit.commit();
        return a();
    }

    public int k() {
        return this.ak.getInt(v, 0);
    }

    public int l() {
        return this.ak.getInt(d, 5);
    }

    public c d(String str) {
        if (!p(s()).contains(str)) {
            d(true);
        }
        Editor edit = this.ak.edit();
        edit.putString(c, str);
        edit.commit();
        return a();
    }

    public String m() {
        return this.ak.getString(c, A);
    }

    public c j(int i) {
        if (!(i == 0 || i == 1)) {
            d(true);
        }
        Editor edit = this.ak.edit();
        edit.putInt(s, i);
        edit.commit();
        return a();
    }

    public c k(int i) {
        Editor edit = this.ak.edit();
        edit.putInt(w, i);
        edit.commit();
        return a();
    }

    public int n() {
        return this.ak.getInt(w, 0);
    }

    public int o() {
        return this.ak.getInt(s, 0);
    }

    public c a(float f) {
        Editor edit = this.ak.edit();
        edit.putFloat(e, f);
        edit.commit();
        return a();
    }

    public float p() {
        return this.ak.getFloat(e, 0.0f);
    }

    public c l(int i) {
        Editor edit = this.ak.edit();
        edit.putInt(n, i);
        edit.commit();
        return a();
    }

    public int q() {
        int i = this.ak.getInt(n, 0);
        Log.d(ah, "getTimer: timer = " + i);
        return i;
    }

    public c c(boolean z) {
        Editor edit = this.ak.edit();
        edit.putBoolean(o, z);
        edit.commit();
        return a();
    }

    public boolean r() {
        return this.ak.getBoolean(o, true);
    }

    public c m(int i) {
        Editor edit = this.ak.edit();
        edit.putInt(p, i);
        edit.commit();
        return a();
    }

    public int s() {
        return this.ak.getInt(p, d.a().i());
    }

    public synchronized c e(String str) {
        Editor edit = this.ak.edit();
        edit.putString(u, str);
        edit.commit();
        return a();
    }

    public String t() {
        return this.ak.getString(u, x);
    }

    public c f(String str) {
        int indexOf = str.indexOf(120);
        if (indexOf == -1) {
            d(true);
        }
        int parseInt = Integer.parseInt(str.substring(0, indexOf));
        int parseInt2 = Integer.parseInt(str.substring(indexOf + 1));
        for (Size size : this.aj.getSupportedPictureSizes()) {
            if (size.width == parseInt && size.height == parseInt2) {
                Editor edit = this.ak.edit();
                edit.putString(f, str);
                edit.apply();
            }
        }
        return a();
    }

    public String u() {
        return this.ak.getString(f, C);
    }

    public c n(int i) {
        Editor edit = this.ak.edit();
        edit.putInt(t, i);
        edit.commit();
        return a();
    }

    public int v() {
        int i = 0;
        try {
            i = this.ak.getInt(t, 0);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        Log.d(ah, "getMaxVideoDuration: duration = " + i);
        return i;
    }

    private List<String> a(List<Size> list) {
        List arrayList = new ArrayList();
        for (Size size : list) {
            arrayList.add(String.format(Locale.ENGLISH, "%dx%d", new Object[]{Integer.valueOf(size.width), Integer.valueOf(size.height)}));
        }
        return arrayList;
    }

    public boolean o(int i) {
        Log.d(ah, "isForceShow4KUHD: ");
        return s() == d.a().i() && (Build.MODEL.contains("SM-G9250") || Build.MODEL.contains("SM-G935") || Build.MODEL.contains("SM-G930") || Build.MODEL.contains("SM-N930") || Build.MODEL.contains("E6883"));
    }

    public boolean w() {
        return this.ag;
    }

    public ArrayList<String> p(int i) {
        ArrayList<String> arrayList = new ArrayList();
        if (CamcorderProfile.hasProfile(i, 8)) {
            arrayList.add("4K");
            this.ag = false;
        } else if (o(i)) {
            arrayList.add("4K");
            this.ag = true;
        }
        if (CamcorderProfile.hasProfile(i, 6)) {
            arrayList.add("1080P");
        }
        if (CamcorderProfile.hasProfile(i, 5)) {
            arrayList.add(A);
        }
        if (CamcorderProfile.hasProfile(i, 4)) {
            arrayList.add("480P");
        }
        return arrayList;
    }

    public boolean x() {
        if (this.ak != null) {
            this.ak.edit().clear().commit();
            this.am.clear();
            this.al.clear();
            Log.d(ah, "clear: ");
            Iterator it = this.ai.iterator();
            while (it.hasNext()) {
                ((b) it.next()).l();
            }
            return true;
        }
        d(true);
        return false;
    }

    private void d(boolean z) {
        if (!z) {
            Log.e(ah, "Camera don't open!!");
            throw new AssertionError();
        }
    }

    public void a(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        if (this.ak != null) {
            this.ak.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        }
    }

    public void b(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        if (this.ak != null) {
            this.ak.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        }
    }

    public void a(b bVar) {
        this.ai.add(bVar);
    }

    public void b(b bVar) {
        this.ai.remove(bVar);
    }

    public void y() {
        l(0);
        k(0);
        e(x);
        this.aj = null;
        if (this.ak != null) {
            this.ak.c();
            this.ak = null;
        }
        this.am.clear();
        this.al.clear();
    }
}
