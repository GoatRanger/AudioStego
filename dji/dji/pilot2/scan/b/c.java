package dji.pilot2.scan.b;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.a.a;
import com.google.a.e;
import com.google.a.u;
import dji.pilot2.scan.android.CaptureActivity;
import dji.pilot2.scan.android.PreferencesActivity;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public final class c extends Thread {
    public static final String a = "barcode_bitmap";
    public static final String b = "barcode_scaled_factor";
    private final CaptureActivity c;
    private final Map<e, Object> d = new EnumMap(e.class);
    private Handler e;
    private final CountDownLatch f = new CountDownLatch(1);

    public c(CaptureActivity captureActivity, Collection<a> collection, Map<e, ?> map, String str, u uVar) {
        this.c = captureActivity;
        if (map != null) {
            this.d.putAll(map);
        }
        if (collection == null || collection.isEmpty()) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(captureActivity);
            collection = EnumSet.noneOf(a.class);
            if (defaultSharedPreferences.getBoolean(PreferencesActivity.a, true)) {
                collection.addAll(a.a);
            }
            if (defaultSharedPreferences.getBoolean(PreferencesActivity.b, true)) {
                collection.addAll(a.b);
            }
            if (defaultSharedPreferences.getBoolean(PreferencesActivity.c, true)) {
                collection.addAll(a.c);
            }
            if (defaultSharedPreferences.getBoolean(PreferencesActivity.d, true)) {
                collection.addAll(a.d);
            }
            if (defaultSharedPreferences.getBoolean(PreferencesActivity.e, false)) {
                collection.addAll(a.e);
            }
            if (defaultSharedPreferences.getBoolean(PreferencesActivity.f, false)) {
                collection.addAll(a.f);
            }
        }
        this.d.put(e.c, collection);
        if (str != null) {
            this.d.put(e.e, str);
        }
        this.d.put(e.j, uVar);
        Log.i("DecodeThread", "Hints: " + this.d);
    }

    public Handler a() {
        try {
            this.f.await();
        } catch (InterruptedException e) {
        }
        return this.e;
    }

    public void run() {
        Looper.prepare();
        this.e = new b(this.c, this.d);
        this.f.countDown();
        Looper.loop();
    }
}
