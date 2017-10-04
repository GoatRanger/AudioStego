package com.dji.a.f;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class b {
    FileWriter a;
    private Context b;

    private static final class a {
        public static b a = new b();
    }

    public static b a() {
        return a.a;
    }

    private b() {
        this.b = null;
    }

    public synchronized void a(Context context) {
        if (this.b == null) {
            com.dji.a.a.c.a(com.dji.a.a.a, "DebugUtils is start init.");
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory.exists()) {
                com.dji.a.a.c.a(com.dji.a.a.a, "getExternalStorageDirectory = " + externalStorageDirectory.getAbsolutePath());
                File file = new File(externalStorageDirectory.getPath() + "/djia.test.data.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                try {
                    this.a = new FileWriter(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.b = context;
            } else {
                throw new FileNotFoundException("can not open storage.");
            }
        }
    }

    public synchronized void a(String str) {
        if (this.a != null) {
            try {
                this.a.write(System.currentTimeMillis() + ":" + str + "\n");
                this.a.flush();
                com.dji.a.a.c.a(com.dji.a.a.a, " write content = " + str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
