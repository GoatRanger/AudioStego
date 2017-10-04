package dji.setting.ui.gimbal.ronin;

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
        public int a = 12;
        public int b = 12;
        public int c = 12;
        public int d = 20;
        public int e = 40;
        public int f = 20;
        public int g = 0;
        public int h = 0;
        public int i = 0;
        public int j = 20;
        public int k = 60;
        public int l = 60;

        public boolean a() {
            return this.a == 12 || this.b == 12 || this.c == 12 || this.d == 20 || this.e == 40 || this.f == 20 || this.g == 0 || this.h == 0 || this.j == 0 || this.j == 20 || this.k == 60 || this.l == 60;
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
