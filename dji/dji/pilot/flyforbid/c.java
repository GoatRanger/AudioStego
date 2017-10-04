package dji.pilot.flyforbid;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class c {
    public static void a(File file) throws ZipException, IOException {
        ZipFile zipFile = new ZipFile(file);
        Enumeration entries = zipFile.entries();
        byte[] bArr = new byte[1024];
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            if (!zipEntry.isDirectory()) {
                InputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                String str = "";
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 1024);
                    if (read == -1) {
                        break;
                    }
                    str = str + new String(bArr, 0, read, Charset.forName("UTF-8"));
                }
                Log.d("fly_unlimit", str);
                bufferedInputStream.close();
            }
        }
        zipFile.close();
    }
}
