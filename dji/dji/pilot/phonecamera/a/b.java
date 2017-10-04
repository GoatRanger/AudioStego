package dji.pilot.phonecamera.a;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.util.Log;
import dji.pilot.phonecamera.d;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class b implements SharedPreferences, OnSharedPreferenceChangeListener {
    private static final String a = "CameraPreferences";
    private SharedPreferences b;
    private SharedPreferences c;
    private String d;
    private CopyOnWriteArrayList<OnSharedPreferenceChangeListener> e = new CopyOnWriteArrayList();
    private Context f;

    private class a implements Editor {
        final /* synthetic */ b a;
        private Editor b;
        private Editor c;

        a(b bVar) {
            this.a = bVar;
            this.b = bVar.b.edit();
            this.c = bVar.c.edit();
        }

        public boolean commit() {
            Log.d(b.a, "commit: ");
            return this.b.commit() && this.c.commit();
        }

        public void apply() {
            this.b.apply();
            this.c.apply();
        }

        public Editor clear() {
            this.b.clear();
            this.c.clear();
            int i = this.a.b.getInt(c.p, d.a().k());
            if (i == d.a().i()) {
                Editor edit = this.a.f.getSharedPreferences(b.b(this.a.f, d.a().j()), 0).edit();
                edit.clear();
                edit.commit();
            }
            if (i == d.a().j()) {
                Editor edit2 = this.a.f.getSharedPreferences(b.b(this.a.f, d.a().i()), 0).edit();
                edit2.clear();
                edit2.commit();
            }
            return this;
        }

        public Editor remove(String str) {
            this.b.remove(str);
            this.c.remove(str);
            return this;
        }

        public Editor putString(String str, String str2) {
            if (b.b(str)) {
                this.b.putString(str, str2);
            } else {
                this.c.putString(str, str2);
            }
            return this;
        }

        public Editor putInt(String str, int i) {
            Log.d(b.a, "putInt: " + str + " value = " + i);
            if (b.b(str)) {
                this.b.putInt(str, i);
            } else {
                this.c.putInt(str, i);
            }
            return this;
        }

        public Editor putLong(String str, long j) {
            if (b.b(str)) {
                this.b.putLong(str, j);
            } else {
                this.c.putLong(str, j);
            }
            return this;
        }

        public Editor putFloat(String str, float f) {
            if (b.b(str)) {
                this.b.putFloat(str, f);
            } else {
                this.c.putFloat(str, f);
            }
            return this;
        }

        public Editor putBoolean(String str, boolean z) {
            if (b.b(str)) {
                this.b.putBoolean(str, z);
            } else {
                this.c.putBoolean(str, z);
            }
            return this;
        }

        public Editor putStringSet(String str, Set<String> set) {
            throw new UnsupportedOperationException();
        }
    }

    public b(Context context) {
        this.f = context.getApplicationContext();
        this.d = context.getPackageName();
        this.b = context.getSharedPreferences(b(context), 0);
        this.b.registerOnSharedPreferenceChangeListener(this);
    }

    private static String b(Context context, int i) {
        return context.getPackageName() + "_preferences_phonecamera_" + i;
    }

    private static String b(Context context) {
        return context.getPackageName() + "_preferences_phonecamera";
    }

    public static String[] a(Context context) {
        int i = 0;
        int d = d.a().d();
        String[] strArr = new String[(d + 1)];
        strArr[0] = b(context);
        while (i < d) {
            strArr[i + 1] = b(context, i);
            i++;
        }
        return strArr;
    }

    public void a(int i) {
        Log.d(a, "setLocalId: cameraId = " + i);
        String b = b(this.f, i);
        Log.d(a, "setLocalId: prefName = " + b);
        if (this.c != null) {
            this.c.unregisterOnSharedPreferenceChangeListener(this);
        }
        this.c = this.f.getSharedPreferences(b, 0);
        this.c.registerOnSharedPreferenceChangeListener(this);
    }

    public SharedPreferences a() {
        return this.b;
    }

    public SharedPreferences b() {
        return this.c;
    }

    public Map<String, ?> getAll() {
        throw new UnsupportedOperationException();
    }

    private static boolean b(String str) {
        return str.equals(c.e) || str.equals(c.p) || str.equals(c.n) || str.equals(c.w) || str.equals(c.o) || str.equals(c.t) || str.equals(c.v) || str.equals(c.T) || str.equals(c.U) || str.equals(c.V) || str.equals(c.W) || str.equals(c.s) || str.equals(c.u);
    }

    public String getString(String str, String str2) {
        if (b(str) || !this.c.contains(str)) {
            return this.b.getString(str, str2);
        }
        return this.c.getString(str, str2);
    }

    public int getInt(String str, int i) {
        if (b(str) || !this.c.contains(str)) {
            return this.b.getInt(str, i);
        }
        return this.c.getInt(str, i);
    }

    public long getLong(String str, long j) {
        if (b(str) || !this.c.contains(str)) {
            return this.b.getLong(str, j);
        }
        return this.c.getLong(str, j);
    }

    public float getFloat(String str, float f) {
        if (b(str) || !this.c.contains(str)) {
            return this.b.getFloat(str, f);
        }
        return this.c.getFloat(str, f);
    }

    public boolean getBoolean(String str, boolean z) {
        if (b(str) || !this.c.contains(str)) {
            return this.b.getBoolean(str, z);
        }
        return this.c.getBoolean(str, z);
    }

    public Set<String> getStringSet(String str, Set<String> set) {
        throw new UnsupportedOperationException();
    }

    public boolean contains(String str) {
        return this.c.contains(str) || this.b.contains(str);
    }

    public Editor edit() {
        return new a(this);
    }

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.e.add(onSharedPreferenceChangeListener);
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.e.remove(onSharedPreferenceChangeListener);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = (OnSharedPreferenceChangeListener) it.next();
            Log.d(a, "onSharedPreferenceChanged: listener");
            onSharedPreferenceChangeListener.onSharedPreferenceChanged(this, str);
        }
        BackupManager.dataChanged(this.d);
    }

    public void c() {
        if (this.b != null) {
            this.b.unregisterOnSharedPreferenceChangeListener(this);
        }
        if (this.c != null) {
            this.c.unregisterOnSharedPreferenceChangeListener(this);
        }
    }
}
