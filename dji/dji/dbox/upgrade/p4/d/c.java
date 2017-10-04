package dji.dbox.upgrade.p4.d;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xeustechnologies.jtar.TarEntry;
import org.xeustechnologies.jtar.TarOutputStream;

public class c {
    public static void a(String str, String str2) throws IOException {
        TarOutputStream tarOutputStream = new TarOutputStream(new BufferedOutputStream(new FileOutputStream(str)));
        for (File file : new File(str2).listFiles()) {
            tarOutputStream.putNextEntry(new TarEntry(file, file.getName()));
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] bArr = new byte[2048];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                tarOutputStream.write(bArr, 0, read);
            }
            tarOutputStream.flush();
            bufferedInputStream.close();
        }
        tarOutputStream.close();
    }

    public static void a(String str, String str2, ArrayList<String> arrayList) throws IOException {
        TarOutputStream tarOutputStream = new TarOutputStream(new BufferedOutputStream(new FileOutputStream(str)));
        File file = new File(str2);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            File file2 = new File(str2, (String) it.next());
            tarOutputStream.putNextEntry(new TarEntry(file2, file2.getName()));
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file2));
            byte[] bArr = new byte[2048];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                tarOutputStream.write(bArr, 0, read);
            }
            tarOutputStream.flush();
            bufferedInputStream.close();
        }
        tarOutputStream.close();
    }
}
