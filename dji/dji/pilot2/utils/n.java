package dji.pilot2.utils;

import android.content.Context;
import android.os.Environment;
import com.dji.frame.c.d;
import dji.log.DJILogHelper;
import dji.pilot2.utils.a.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class n {
    public static final String a = "multi_music";
    public static final String b = "video";
    public static final String c = "multi_template_img";
    public static final String d = "video_filter_img";
    public static final String e = "multi_template_cfg";
    public static final String f = "single_music";
    public static final String g = "single_template_img";
    public static final String h = "template_cache";
    public static final String i = "single_template_cfg";
    public static final String j = ".mp4";
    public static final String k = ".bak";
    public static final String l = ".mp3";
    public static final String m = "localMusic";
    public static String n = a.n;
    public static String o = "music";
    public static String p = "video";
    public static String q = a.q;
    public static String r = "image";
    private static final String s = "FileUtils";
    private static final String t = "ve_templates";
    private static final String u = "segmentLibrary";
    private static final String v = "segmentLibrary_hd";
    private static final String w = "production";
    private static final String x = "ve_temp";

    private n() {
    }

    public static File a(Context context) throws FileNotFoundException, IOException {
        File file = new File(d.a(context, "VideoEditor"));
        if (file == null || file.exists()) {
            File file2 = new File(file + dji.pilot.usercenter.protocol.d.t + ".nomedia");
            if (file2.exists()) {
                file2.delete();
            }
        } else if (!file.mkdirs()) {
            throw new FileNotFoundException("Cannot create folder: " + file.getAbsolutePath());
        }
        return file;
    }

    public static void a(File file) {
        if (file.getParentFile().exists()) {
            file.mkdir();
            return;
        }
        a(file.getParentFile());
        file.mkdir();
    }

    public static File b(Context context) throws FileNotFoundException, IOException {
        File file = new File(d.a(context, "VideoEditor/" + n));
        a(file);
        return file;
    }

    public static String a() {
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        if (!externalStoragePublicDirectory.exists()) {
            externalStoragePublicDirectory.mkdirs();
        }
        return new File(externalStoragePublicDirectory, "").getAbsolutePath();
    }

    public static String b() {
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        if (!externalStoragePublicDirectory.exists()) {
            externalStoragePublicDirectory.mkdirs();
        }
        return new File(externalStoragePublicDirectory, "").getAbsolutePath();
    }

    public static String c(Context context) {
        File file;
        Exception e;
        try {
            file = new File(a(context), "localMusic");
            try {
                if (!file.exists()) {
                    file.mkdirs();
                }
                new File(file, ".nomedia").createNewFile();
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            Exception exception = e4;
            file = null;
            e = exception;
            e.printStackTrace();
            return file.getPath() + dji.pilot.usercenter.protocol.d.t;
        }
        return file.getPath() + dji.pilot.usercenter.protocol.d.t;
    }

    public static String d(Context context) {
        File file;
        Exception e;
        try {
            file = new File(a(context), u);
            try {
                if (!file.exists()) {
                    file.mkdirs();
                }
                new File(file, ".nomedia").createNewFile();
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            Exception exception = e4;
            file = null;
            e = exception;
            e.printStackTrace();
            return file.getPath() + dji.pilot.usercenter.protocol.d.t;
        }
        return file.getPath() + dji.pilot.usercenter.protocol.d.t;
    }

    public static String e(Context context) {
        File file;
        Exception e;
        try {
            file = new File(a(context), v);
            try {
                if (!file.exists()) {
                    file.mkdirs();
                }
                new File(file, ".nomedia").createNewFile();
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            Exception exception = e4;
            file = null;
            e = exception;
            e.printStackTrace();
            return file.getPath() + dji.pilot.usercenter.protocol.d.t;
        }
        return file.getPath() + dji.pilot.usercenter.protocol.d.t;
    }

    public static String f(Context context) {
        File file;
        Exception e;
        try {
            file = new File(a(context), w);
            try {
                if (!file.exists()) {
                    file.mkdirs();
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return file.getPath() + dji.pilot.usercenter.protocol.d.t;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            file = null;
            e = exception;
            e.printStackTrace();
            return file.getPath() + dji.pilot.usercenter.protocol.d.t;
        }
        return file.getPath() + dji.pilot.usercenter.protocol.d.t;
    }

    public static String g(Context context) {
        File file;
        Exception e;
        try {
            file = new File(a(context), x);
            try {
                if (file.exists()) {
                    c(file.getAbsolutePath());
                } else {
                    file.mkdirs();
                }
                new File(file, ".nomedia").createNewFile();
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            Exception exception = e4;
            file = null;
            e = exception;
            e.printStackTrace();
            return file.getPath() + dji.pilot.usercenter.protocol.d.t;
        }
        return file.getPath() + dji.pilot.usercenter.protocol.d.t;
    }

    public static String c() {
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(t);
        if (!externalStoragePublicDirectory.exists()) {
            externalStoragePublicDirectory.mkdirs();
        }
        return new File(externalStoragePublicDirectory, "").getAbsolutePath();
    }

    public static boolean b(File file) {
        if (file.isDirectory()) {
            String[] list = file.list();
            for (String file2 : list) {
                if (!b(new File(file, file2))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static String a(String str) {
        int lastIndexOf = str.lastIndexOf(47);
        return lastIndexOf == -1 ? str : str.substring(lastIndexOf + 1);
    }

    public static String h(Context context) {
        File file;
        Exception e;
        try {
            file = new File(a(context), "multi_music");
            try {
                new File(file, ".nomedia").createNewFile();
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            Exception exception = e4;
            file = null;
            e = exception;
            e.printStackTrace();
            return file.getPath() + dji.pilot.usercenter.protocol.d.t;
        }
        return file.getPath() + dji.pilot.usercenter.protocol.d.t;
    }

    public static String i(Context context) {
        return a(context, p);
    }

    public static String j(Context context) {
        return a(context, o);
    }

    public static String k(Context context) {
        return a(context, r);
    }

    public static String l(Context context) {
        return a(context, q);
    }

    public static String a(Context context, String str) {
        File file;
        Exception e;
        try {
            file = new File(b(context), str);
            try {
                new File(file, ".nomedia").createNewFile();
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            Exception exception = e4;
            file = null;
            e = exception;
            e.printStackTrace();
            return file.getPath() + dji.pilot.usercenter.protocol.d.t;
        }
        return file.getPath() + dji.pilot.usercenter.protocol.d.t;
    }

    public static String m(Context context) {
        File file;
        Exception e;
        try {
            file = new File(a(context), "video");
            try {
                new File(file, ".nomedia").createNewFile();
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            Exception exception = e4;
            file = null;
            e = exception;
            e.printStackTrace();
            return file.getPath() + dji.pilot.usercenter.protocol.d.t;
        }
        return file.getPath() + dji.pilot.usercenter.protocol.d.t;
    }

    public static String n(Context context) {
        File file;
        Exception e;
        try {
            file = new File(a(context), "multi_template_img");
            try {
                new File(file, ".nomedia").createNewFile();
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            Exception exception = e4;
            file = null;
            e = exception;
            e.printStackTrace();
            return file.getPath() + dji.pilot.usercenter.protocol.d.t;
        }
        return file.getPath() + dji.pilot.usercenter.protocol.d.t;
    }

    public static String o(Context context) {
        File file;
        Exception e;
        try {
            file = new File(a(context), "video_filter_img");
            try {
                new File(file, ".nomedia").createNewFile();
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            Exception exception = e4;
            file = null;
            e = exception;
            e.printStackTrace();
            return file.getPath() + dji.pilot.usercenter.protocol.d.t;
        }
        return file.getPath() + dji.pilot.usercenter.protocol.d.t;
    }

    public static String p(Context context) {
        File file;
        Exception e;
        try {
            file = new File(a(context), "single_music");
            try {
                new File(file, ".nomedia").createNewFile();
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            Exception exception = e4;
            file = null;
            e = exception;
            e.printStackTrace();
            return file.getPath() + dji.pilot.usercenter.protocol.d.t;
        }
        return file.getPath() + dji.pilot.usercenter.protocol.d.t;
    }

    public static String q(Context context) {
        File file;
        Exception e;
        try {
            file = new File(a(context), "single_template_img");
            try {
                new File(file, ".nomedia").createNewFile();
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            Exception exception = e4;
            file = null;
            e = exception;
            e.printStackTrace();
            return file.getPath() + dji.pilot.usercenter.protocol.d.t;
        }
        return file.getPath() + dji.pilot.usercenter.protocol.d.t;
    }

    public static String r(Context context) {
        File file;
        Exception e;
        try {
            file = new File(a(context), "template_cache");
            try {
                new File(file, ".nomedia").createNewFile();
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            Exception exception = e4;
            file = null;
            e = exception;
            e.printStackTrace();
            return file.getPath() + dji.pilot.usercenter.protocol.d.t;
        }
        return file.getPath() + dji.pilot.usercenter.protocol.d.t;
    }

    public static String s(Context context) {
        File file;
        try {
            file = new File(a(context), "multi_template_cfg");
        } catch (Exception e) {
            e.printStackTrace();
            file = null;
        }
        return file.getPath() + dji.pilot.usercenter.protocol.d.t;
    }

    public static String t(Context context) {
        File file;
        try {
            file = new File(a(context), "single_template_cfg");
        } catch (Exception e) {
            e.printStackTrace();
            file = null;
        }
        return file.getPath() + dji.pilot.usercenter.protocol.d.t;
    }

    public static String b(String str) {
        return new File(str).getName();
    }

    public static void a(File file, File file2, Boolean bool) {
        if (file.exists() && file.isFile() && file.canRead()) {
            if (!file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }
            if (file2.exists() && bool.booleanValue()) {
                file2.delete();
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read > 0) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileInputStream.close();
                        fileOutputStream.close();
                        return;
                    }
                }
            } catch (Exception e) {
                DJILogHelper.getInstance().LOGI("bob", e.getMessage());
            }
        }
    }

    public static boolean c(String str) {
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        String[] list = file.list();
        int i = 0;
        boolean z = false;
        while (i < list.length) {
            File file2;
            boolean z2;
            if (str.endsWith(File.separator)) {
                file2 = new File(str + list[i]);
            } else {
                file2 = new File(str + File.separator + list[i]);
            }
            if (file2.isFile()) {
                file2.delete();
            }
            if (file2.isDirectory()) {
                c(str + dji.pilot.usercenter.protocol.d.t + list[i]);
                d(str + dji.pilot.usercenter.protocol.d.t + list[i]);
                z2 = true;
            } else {
                z2 = z;
            }
            i++;
            z = z2;
        }
        return z;
    }

    public static void d(String str) {
        try {
            c(str);
            new File(str.toString()).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
