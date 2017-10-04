package dji.thirdparty.a.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import dji.thirdparty.a.c;

public class e {
    public static c<?> a = null;
    protected static final String b = "de.greenrobot.eventbus.error_dialog";
    protected static final String c = "de.greenrobot.eventbus.error_dialog_manager";
    public static final String d = "de.greenrobot.eventbus.errordialog.title";
    public static final String e = "de.greenrobot.eventbus.errordialog.message";
    public static final String f = "de.greenrobot.eventbus.errordialog.finish_after_dialog";
    public static final String g = "de.greenrobot.eventbus.errordialog.icon_id";
    public static final String h = "de.greenrobot.eventbus.errordialog.event_type_on_close";

    @TargetApi(11)
    public static class a extends Fragment {
        protected boolean a;
        protected Bundle b;
        private c c;
        private Object d;

        public void onResume() {
            super.onResume();
            this.c = e.a.a.b();
            this.c.a(this);
        }

        public void onPause() {
            this.c.d(this);
            super.onPause();
        }

        public void onEventMainThread(h hVar) {
            if (e.b(this.d, hVar)) {
                e.a(hVar);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.executePendingTransactions();
                DialogFragment dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag(e.b);
                if (dialogFragment != null) {
                    dialogFragment.dismiss();
                }
                dialogFragment = (DialogFragment) e.a.a(hVar, this.a, this.b);
                if (dialogFragment != null) {
                    dialogFragment.show(fragmentManager, e.b);
                }
            }
        }

        public static void a(Activity activity, Object obj, boolean z, Bundle bundle) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            a aVar = (a) fragmentManager.findFragmentByTag(e.c);
            if (aVar == null) {
                aVar = new a();
                fragmentManager.beginTransaction().add(aVar, e.c).commit();
                fragmentManager.executePendingTransactions();
            }
            aVar.a = z;
            aVar.b = bundle;
            aVar.d = obj;
        }
    }

    public static class b extends Fragment {
        protected boolean a;
        protected Bundle b;
        private c c;
        private boolean d;
        private Object e;

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.c = e.a.a.b();
            this.c.a(this);
            this.d = true;
        }

        public void onResume() {
            super.onResume();
            if (this.d) {
                this.d = false;
                return;
            }
            this.c = e.a.a.b();
            this.c.a(this);
        }

        public void onPause() {
            this.c.d(this);
            super.onPause();
        }

        public void onEventMainThread(h hVar) {
            if (e.b(this.e, hVar)) {
                e.a(hVar);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.executePendingTransactions();
                DialogFragment dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag(e.b);
                if (dialogFragment != null) {
                    dialogFragment.dismiss();
                }
                dialogFragment = (DialogFragment) e.a.a(hVar, this.a, this.b);
                if (dialogFragment != null) {
                    dialogFragment.show(fragmentManager, e.b);
                }
            }
        }

        public static void a(Activity activity, Object obj, boolean z, Bundle bundle) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            b bVar = (b) fragmentManager.findFragmentByTag(e.c);
            if (bVar == null) {
                bVar = new b();
                fragmentManager.beginTransaction().add(bVar, e.c).commit();
                fragmentManager.executePendingTransactions();
            }
            bVar.a = z;
            bVar.b = bundle;
            bVar.e = obj;
        }
    }

    public static void a(Activity activity) {
        a(activity, false, null);
    }

    public static void a(Activity activity, boolean z) {
        a(activity, z, null);
    }

    public static void a(Activity activity, boolean z, Bundle bundle) {
        a(activity, activity.getClass(), z, bundle);
    }

    public static void a(Activity activity, Object obj, boolean z, Bundle bundle) {
        if (a == null) {
            throw new RuntimeException("You must set the static factory field to configure error dialogs for your app.");
        } else if (b(activity)) {
            b.a(activity, obj, z, bundle);
        } else {
            a.a(activity, obj, z, bundle);
        }
    }

    private static boolean b(Activity activity) {
        Class superclass = activity.getClass().getSuperclass();
        while (superclass != null) {
            String name = superclass.getName();
            if (name.equals("android.support.v4.app.FragmentActivity")) {
                return true;
            }
            if (name.startsWith("com.actionbarsherlock.app") && (name.endsWith(".SherlockActivity") || name.endsWith(".SherlockListActivity") || name.endsWith(".SherlockPreferenceActivity"))) {
                throw new RuntimeException("Please use SherlockFragmentActivity. Illegal activity: " + name);
            } else if (!name.equals("android.app.Activity")) {
                superclass = superclass.getSuperclass();
            } else if (VERSION.SDK_INT >= 11) {
                return false;
            } else {
                throw new RuntimeException("Illegal activity without fragment support. Either use Android 3.0+ or android.support.v4.app.FragmentActivity.");
            }
        }
        throw new RuntimeException("Illegal activity type: " + activity.getClass());
    }

    protected static void a(h hVar) {
        if (a.a.f) {
            String str = a.a.g;
            if (str == null) {
                str = c.b;
            }
            Log.i(str, "Error dialog manager received exception", hVar.a);
        }
    }

    private static boolean b(Object obj, h hVar) {
        if (obj != null && (hVar instanceof g)) {
            Object a = hVar.a();
            if (!(a == null || a.equals(obj))) {
                return false;
            }
        }
        return true;
    }
}
