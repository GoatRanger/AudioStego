package com.tencent.android.tpush.stat.b;

import android.content.Context;
import android.os.Environment;
import com.tencent.android.tpush.stat.a.h;
import dji.pilot.usercenter.protocol.d;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class b extends g {
    b(Context context) {
        super(context);
    }

    protected boolean a() {
        return h.a(this.a, "android.permission.WRITE_EXTERNAL_STORAGE") && Environment.getExternalStorageState().equals("mounted");
    }

    protected String b() {
        String str = null;
        synchronized (this) {
            File file = new File(Environment.getExternalStorageDirectory(), g.d());
            if (file != null) {
                try {
                    String str2;
                    for (String str22 : a.a(file)) {
                        String[] split = str22.split(",");
                        if (split.length == 2 && split[0].equals(f())) {
                            str22 = split[1];
                            break;
                        }
                    }
                    str22 = null;
                    str = str22;
                } catch (IOException e) {
                }
            }
        }
        return str;
    }

    protected void a(String str) {
        synchronized (this) {
            a.a(Environment.getExternalStorageDirectory() + d.t + g.c());
            File file = new File(Environment.getExternalStorageDirectory(), g.d());
            if (file != null) {
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    bufferedWriter.write(f() + "," + str);
                    bufferedWriter.write("\n");
                    bufferedWriter.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
