package dji.thirdparty.afinal.f.a;

import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;

public class b {
    private boolean a = false;

    public boolean a() {
        return this.a;
    }

    public void a(boolean z) {
        this.a = z;
    }

    public Object a(HttpEntity httpEntity, a aVar, String str, boolean z, boolean z2) throws IOException {
        if (TextUtils.isEmpty(str) || str.trim().length() == 0) {
            return null;
        }
        File file = new File(str);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        if (this.a) {
            return file;
        }
        FileOutputStream fileOutputStream;
        long j;
        if (z) {
            long length = file.length();
            fileOutputStream = new FileOutputStream(str, true);
            j = length;
        } else {
            fileOutputStream = new FileOutputStream(str);
            j = 0;
        }
        if (this.a) {
            fileOutputStream.close();
            return file;
        }
        length = httpEntity.getContentLength();
        if (!z2 || length >= 0) {
            InputStream content = httpEntity.getContent();
            length += j;
            if ((!z2 || j < length) && !this.a) {
                byte[] bArr = new byte[1024];
                while (!this.a && (!z2 || j < length)) {
                    int read = content.read(bArr, 0, 1024);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                    j += (long) read;
                    aVar.a(length, j, false);
                }
                fileOutputStream.close();
                content.close();
                aVar.a(length, j, true);
                if (!this.a || j >= length) {
                    return file;
                }
                throw new IOException("user stop download thread");
            }
            fileOutputStream.close();
            content.close();
            return file;
        }
        fileOutputStream.close();
        return "getContentLength " + length;
    }
}
