package com.e;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import java.lang.reflect.Method;

public class bq {
    private static Method a;

    public static void a(Context context, String str, String str2, int i) {
        try {
            Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putInt(str2, i);
            a(edit);
        } catch (Throwable th) {
            bc.a(th, "SPUtil", "setPrefsInt");
        }
    }

    public static void a(Context context, String str, String str2, long j) {
        try {
            Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putLong(str2, j);
            a(edit);
        } catch (Throwable th) {
            bc.a(th, "SPUtil", "setPrefsLong");
        }
    }

    public static void a(Context context, String str, String str2, boolean z) {
        try {
            Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putBoolean(str2, z);
            a(edit);
        } catch (Throwable th) {
            bc.a(th, "SPUtil", "updatePrefsBoolean");
        }
    }

    public static void a(Editor editor) {
        if (editor != null) {
            if (VERSION.SDK_INT >= 9) {
                try {
                    if (a == null) {
                        a = Editor.class.getDeclaredMethod("apply", new Class[0]);
                    }
                    a.invoke(editor, new Object[0]);
                    return;
                } catch (Throwable th) {
                    bc.a(th, "SPUtil", "applySharedPreference");
                    b(editor);
                    return;
                }
            }
            b(editor);
        }
    }

    public static int b(Context context, String str, String str2, int i) {
        try {
            i = context.getSharedPreferences(str, 0).getInt(str2, i);
        } catch (Throwable th) {
            bc.a(th, "SPUtil", "getPrefsInt");
        }
        return i;
    }

    public static long b(Context context, String str, String str2, long j) {
        try {
            j = context.getSharedPreferences(str, 0).getLong(str2, j);
        } catch (Throwable th) {
            bc.a(th, "SPUtil", "getPrefsLong");
        }
        return j;
    }

    private static void b(final Editor editor) {
        try {
            new AsyncTask<Void, Void, Void>() {
                protected Void a(Void... voidArr) {
                    try {
                        if (editor != null) {
                            editor.commit();
                        }
                    } catch (Throwable th) {
                        bc.a(th, "SPUtil", "commit");
                    }
                    return null;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((Void[]) objArr);
                }
            }.execute(new Void[]{null, null, null});
        } catch (Throwable th) {
            bc.a(th, "SPUtil", "commit1");
        }
    }

    public static boolean b(Context context, String str, String str2, boolean z) {
        try {
            z = context.getSharedPreferences(str, 0).getBoolean(str2, z);
        } catch (Throwable th) {
            bc.a(th, "SPUtil", "getPrefsBoolean");
        }
        return z;
    }
}
