package com.tencent.android.tpush.a;

import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

final class b implements Runnable {
    final /* synthetic */ ArrayList a;

    b(ArrayList arrayList) {
        this.a = arrayList;
    }

    public void run() {
        Throwable e;
        BufferedWriter bufferedWriter = null;
        try {
            String a = a.a();
            if (a == null) {
                try {
                    a.b().clear();
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                        return;
                    }
                    return;
                } catch (Throwable e2) {
                    Log.e("XGLogger", "close file stream error", e2);
                    return;
                }
            }
            String str = a + File.separator + "log";
            String str2 = str + "-" + com.tencent.android.tpush.service.d.b.a() + "_1.txt";
            File file = new File(str2);
            file.getParentFile().mkdirs();
            File file2 = file;
            String str3 = str2;
            int i = 2;
            while (file2.exists()) {
                str3 = str + "-" + com.tencent.android.tpush.service.d.b.a() + "_" + i + ".txt";
                file2 = new File(str3);
                if (i > 10) {
                    Log.w("XGLogger", "Unexpected error here, so many existed error file.");
                    str2 = str3;
                    break;
                }
                i++;
            }
            str2 = str3;
            Log.v("XGLogger", "Write log file: " + file2.getName());
            BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(str2));
            try {
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    bufferedWriter2.write(((String) it.next()) + "\n");
                }
                try {
                    a.b().clear();
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                    }
                } catch (Throwable e22) {
                    Log.e("XGLogger", "close file stream error", e22);
                }
            } catch (Exception e3) {
                e22 = e3;
                bufferedWriter = bufferedWriter2;
                try {
                    Log.e("XGLogger", "write logs to file error", e22);
                    try {
                        a.b().clear();
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                    } catch (Throwable e222) {
                        Log.e("XGLogger", "close file stream error", e222);
                    }
                    a.c();
                } catch (Throwable th) {
                    e222 = th;
                    try {
                        a.b().clear();
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                    } catch (Throwable e4) {
                        Log.e("XGLogger", "close file stream error", e4);
                    }
                    throw e222;
                }
            } catch (Throwable th2) {
                e222 = th2;
                bufferedWriter = bufferedWriter2;
                a.b().clear();
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                throw e222;
            }
            a.c();
        } catch (Exception e5) {
            e222 = e5;
            Log.e("XGLogger", "write logs to file error", e222);
            a.b().clear();
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            a.c();
        }
    }
}
