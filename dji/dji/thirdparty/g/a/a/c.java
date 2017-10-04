package dji.thirdparty.g.a.a;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class c extends a {
    private final File l;

    public c(File file) {
        super(file.getName());
        this.l = file;
    }

    public InputStream a() throws IOException {
        return new BufferedInputStream(new FileInputStream(this.l));
    }

    public byte[] c(int i, int i2) throws IOException {
        Throwable th;
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(this.l, "r");
            try {
                byte[] a = a(randomAccessFile, (long) i, i2, "Could not read value from file");
                try {
                    randomAccessFile.close();
                } catch (Throwable e) {
                    dji.thirdparty.g.c.c.a(e);
                }
                return a;
            } catch (Throwable th2) {
                th = th2;
                try {
                    randomAccessFile.close();
                } catch (Throwable e2) {
                    dji.thirdparty.g.c.c.a(e2);
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile = null;
            randomAccessFile.close();
            throw th;
        }
    }

    public long c() {
        return this.l.length();
    }

    public byte[] b() throws IOException {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream inputStream = null;
        try {
            InputStream fileInputStream = new FileInputStream(this.l);
            try {
                inputStream = new BufferedInputStream(fileInputStream);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                    }
                }
                return toByteArray;
            } catch (Throwable th2) {
                th = th2;
                inputStream = fileInputStream;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public String d() {
        return "File: '" + this.l.getAbsolutePath() + "'";
    }
}
