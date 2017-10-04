package dji.thirdparty.b.a.c;

import dji.thirdparty.c.p;
import dji.thirdparty.c.v;
import dji.thirdparty.c.w;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface a {
    public static final a a = new a() {
        public w a(File file) throws FileNotFoundException {
            return p.a(file);
        }

        public v b(File file) throws FileNotFoundException {
            try {
                return p.b(file);
            } catch (FileNotFoundException e) {
                file.getParentFile().mkdirs();
                return p.b(file);
            }
        }

        public v c(File file) throws FileNotFoundException {
            try {
                return p.c(file);
            } catch (FileNotFoundException e) {
                file.getParentFile().mkdirs();
                return p.c(file);
            }
        }

        public void d(File file) throws IOException {
            if (!file.delete() && file.exists()) {
                throw new IOException("failed to delete " + file);
            }
        }

        public boolean e(File file) {
            return file.exists();
        }

        public long f(File file) {
            return file.length();
        }

        public void a(File file, File file2) throws IOException {
            d(file2);
            if (!file.renameTo(file2)) {
                throw new IOException("failed to rename " + file + " to " + file2);
            }
        }

        public void g(File file) throws IOException {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                throw new IOException("not a readable directory: " + file);
            }
            int length = listFiles.length;
            int i = 0;
            while (i < length) {
                File file2 = listFiles[i];
                if (file2.isDirectory()) {
                    g(file2);
                }
                if (file2.delete()) {
                    i++;
                } else {
                    throw new IOException("failed to delete " + file2);
                }
            }
        }
    };

    w a(File file) throws FileNotFoundException;

    void a(File file, File file2) throws IOException;

    v b(File file) throws FileNotFoundException;

    v c(File file) throws FileNotFoundException;

    void d(File file) throws IOException;

    boolean e(File file);

    long f(File file);

    void g(File file) throws IOException;
}
