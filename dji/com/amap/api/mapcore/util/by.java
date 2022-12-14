package com.amap.api.mapcore.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

class by {
    RandomAccessFile a;

    public by() throws IOException {
        this("", 0);
    }

    public by(String str, long j) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (Throwable e) {
                ee.a(e, "FileAccessI", "create");
                e.printStackTrace();
            }
        }
        this.a = new RandomAccessFile(str, "rw");
        this.a.seek(j);
    }

    public synchronized int a(byte[] bArr) throws IOException {
        this.a.write(bArr);
        return bArr.length;
    }

    public void a() {
        if (this.a != null) {
            try {
                this.a.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.a = null;
        }
    }
}
