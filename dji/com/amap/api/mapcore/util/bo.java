package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class bo {
    private Context a;

    public bo(Context context) {
        this.a = context;
    }

    public void a(bg bgVar) {
        b(bgVar);
    }

    private boolean b(bg bgVar) {
        if (bgVar == null) {
            return false;
        }
        boolean a = a(bgVar.getAdcode(), this.a);
        if (a) {
            bgVar.h();
            return a;
        }
        bgVar.g();
        return false;
    }

    private boolean a(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        List<String> b = bx.a(context).b(str);
        String a = dj.a(context);
        for (String str2 : b) {
            try {
                File file = new File(a + "vmap/" + str2);
                if (file.exists() && !cf.b(file)) {
                    cf.a("deleteDownload delete some thing wrong!");
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        try {
            cf.c(a + "vmap/");
            cf.a(str, context);
            return true;
        } catch (IOException e3) {
            e3.printStackTrace();
            return false;
        } catch (Exception e22) {
            e22.printStackTrace();
            return false;
        }
    }
}
