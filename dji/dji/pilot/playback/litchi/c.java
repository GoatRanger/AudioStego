package dji.pilot.playback.litchi;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import dji.log.DJILogHelper;
import dji.logic.album.a.b.f;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.main.a.a;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class c {
    protected static final String a = Environment.getExternalStorageDirectory().getPath();
    private static File[] b;

    public static void a() {
        File file = new File(a + h.d);
        if (file.isDirectory()) {
            b = file.listFiles();
        }
    }

    public static boolean a(String str) {
        if (b == null) {
            return false;
        }
        for (File name : b) {
            if (str.equals(name.getName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(String str) {
        return new File(a + h.d + d.t + str).exists();
    }

    public static void a(String str, Bitmap bitmap, f fVar) {
        if (fVar == f.a) {
            OutputStream fileOutputStream;
            File file = new File(a + h.d + d.t + str + a.n);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
                fileOutputStream = null;
            }
            bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
            try {
                fileOutputStream.flush();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            try {
                fileOutputStream.close();
                return;
            } catch (IOException e4) {
                e4.printStackTrace();
                return;
            }
        }
        DJILogHelper.getInstance().LOGD("Save", "Format disable", false, true);
    }

    public static void a(String str, String str2) {
        try {
            File file = new File(str);
            if (file.exists()) {
                file.renameTo(new File(str2));
            }
        } catch (Exception e) {
            DJILogHelper.getInstance().LOGD("Copy", "复制单个文件操作出错", false, true);
            e.printStackTrace();
        }
    }
}
