package com.ut.mini.d;

import android.annotation.TargetApi;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.ut.mini.b.a;

public class m {
    @TargetApi(9)
    private static void b(Editor editor) {
        editor.apply();
    }

    public static void a(Editor editor) {
        a.b(2, "4.3.8 cacheLog [fastCommit]", "");
        if (editor == null) {
            return;
        }
        if (VERSION.SDK_INT >= 9) {
            b(editor);
        } else {
            editor.commit();
        }
    }
}
