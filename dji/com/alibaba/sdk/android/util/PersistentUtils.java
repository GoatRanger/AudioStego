package com.alibaba.sdk.android.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import com.alibaba.sdk.android.b.a;
import com.alibaba.sdk.android.trace.AliSDKLogger;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersistentUtils {

    public static class ObjectStore<T> {
        private final String a;
        private final String b;

        public ObjectStore(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public T readItem() throws IOException, ClassNotFoundException {
            Throwable th;
            Closeable closeable = null;
            if (a.a == null) {
                return null;
            }
            File[] listFiles = a.a.getDir(this.a, 0).listFiles();
            if (listFiles.length == 0) {
                return null;
            }
            long parseLong;
            long j = 0;
            File file = null;
            for (File file2 : listFiles) {
                if (file2.isFile() && file2.getName().endsWith(this.b)) {
                    String name = file2.getName();
                    try {
                        File file3;
                        parseLong = Long.parseLong(name.substring(0, name.length() - this.b.length()));
                        if (parseLong > j) {
                            file3 = file2;
                        } else {
                            parseLong = j;
                            file3 = file;
                        }
                        file = file3;
                        j = parseLong;
                    } catch (Exception e) {
                    }
                }
            }
            if (file == null) {
                return null;
            }
            parseLong = a();
            if (parseLong == 0) {
                AliSDKLogger.w("ObjectStore", "Fail to read the apk update time, skip the persistent data");
                return null;
            } else if (j < parseLong) {
                if (!file.delete()) {
                    file.deleteOnExit();
                }
                return null;
            } else {
                try {
                    Closeable objectInputStream = new ObjectInputStream(new FileInputStream(file));
                    try {
                        T readObject = objectInputStream.readObject();
                        IOUtils.closeQuietly(objectInputStream);
                        return readObject;
                    } catch (Throwable th2) {
                        th = th2;
                        closeable = objectInputStream;
                        IOUtils.closeQuietly(closeable);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    IOUtils.closeQuietly(closeable);
                    throw th;
                }
            }
        }

        public void writeItem(T t) throws IOException {
            Throwable th;
            Closeable closeable = null;
            if (VERSION.SDK_INT >= 9 && CommonUtils.isApplicationDefaultProcess() == 1) {
                try {
                    File dir = a.a.getDir(this.a, 0);
                    long a = a();
                    if (a == 0) {
                        AliSDKLogger.w("ObjectStore", "Fail to read the last apk update time, skip persistent data writing");
                        IOUtils.closeQuietly(null);
                        return;
                    }
                    Closeable objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(dir, a + this.b)));
                    try {
                        objectOutputStream.writeObject(t);
                        IOUtils.closeQuietly(objectOutputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        closeable = objectOutputStream;
                        IOUtils.closeQuietly(closeable);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    IOUtils.closeQuietly(closeable);
                    throw th;
                }
            }
        }

        @TargetApi(9)
        private static long a() {
            long j = 0;
            Context context = a.a;
            if (context != null) {
                try {
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 128);
                    if (packageInfo != null && VERSION.SDK_INT >= 9) {
                        j = packageInfo.lastUpdateTime;
                    }
                } catch (Throwable e) {
                    AliSDKLogger.e("ObjectStore", e);
                }
            }
            return j;
        }
    }
}
