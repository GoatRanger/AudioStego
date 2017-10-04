package dji.pilot.fpv.c;

import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.util.EncodingUtils;

public class a {
    private static a b = null;
    public List<a> a;
    private String c = "";
    private String d = "";

    public static class a {
        public int a = -1;
        public int b = -1;
        public int c = -1;
        public int d = -1;
        public int e = -1;
        public int f = -1;
        public int g = -1;
        public int h = -1;
        public int i = -1;
        public int j = -1;
        public int k = -1;
        public int l = -1;

        public boolean a() {
            return this.a == -1 || this.b == -1 || this.c == -1 || this.d == -1 || this.e == -1 || this.f == -1 || this.g == -1 || this.h == -1 || this.j == -1 || this.j == -1 || this.k == -1 || this.l == -1;
        }
    }

    public static a a(String str, String str2) {
        if (b == null) {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            b = (a) h.b(a(str + str2), a.class);
            if (b == null) {
                b = new a();
            }
            if (b.a == null) {
                b.a = new ArrayList();
            }
            for (int size = b.a.size(); size < 3; size++) {
                b.a.add(new a());
            }
            b.c = str2;
            b.d = str;
        }
        return b;
    }

    public static a getInstance() {
        return b;
    }

    public void a() {
        File file = new File(this.d + this.c);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                DJILogHelper.getInstance().LOGD("", e.toString(), false, true);
                return;
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(EncodingUtils.getBytes(h.a(this), "utf-8"));
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (IOException e22) {
                e22.printStackTrace();
            }
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        }
    }

    private static String a(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return EncodingUtils.getString(bArr, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
