package android.databinding;

import android.view.View;
import dji.pilot.R;

class i {
    static final int a = 16;

    private static class a {
        static String[] a = new String[]{"_all"};

        private a() {
        }
    }

    public ab a(j jVar, View view, int i) {
        switch (i) {
            case R.layout.activity_language_setting:
                return dji.pilot.e.a.a(view, jVar);
            default:
                return null;
        }
    }

    ab a(j jVar, View[] viewArr, int i) {
        return null;
    }

    int a(String str) {
        if (str == null) {
            return 0;
        }
        switch (str.hashCode()) {
            case -1106158683:
                if (str.equals("layout/activity_language_setting_0")) {
                    return R.layout.activity_language_setting;
                }
                return 0;
            default:
                return 0;
        }
    }

    String a(int i) {
        if (i < 0 || i >= a.a.length) {
            return null;
        }
        return a.a[i];
    }
}
