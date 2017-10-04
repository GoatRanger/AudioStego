package dji.pilot2.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import com.dji.frame.c.d;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.usercenter.b.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class g {
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
    private static final String n = "FileUtils";
    private static final String o = "ve_templates";
    private static final String p = "segmentLibrary";
    private static final String q = "segmentLibrary_hd";
    private static final String r = "production";
    private static final String s = "ve_temp";

    private g() {
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

    public static String a(Context context, int i) throws FileNotFoundException, IOException {
        String str;
        Throwable th;
        Object obj;
        FileOutputStream fileOutputStream = null;
        switch (i) {
            case R.raw.mask_contour:
                str = "mask_countour.jpg";
                break;
            case R.raw.mask_diagonal:
                str = "mask_diagonal.jpg";
                break;
            default:
                throw new IllegalArgumentException("Invalid mask raw resource id");
        }
        File file = new File(context.getFilesDir(), str);
        if (!file.exists()) {
            InputStream openRawResource;
            Bitmap decodeStream;
            try {
                openRawResource = context.getResources().openRawResource(i);
                try {
                    decodeStream = BitmapFactory.decodeStream(openRawResource);
                    if (decodeStream == null) {
                        try {
                            throw new IllegalStateException("Cannot decode raw resource mask");
                        } catch (Throwable th2) {
                            th = th2;
                            if (openRawResource != null) {
                                openRawResource.close();
                            }
                            if (decodeStream != null) {
                                decodeStream.recycle();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    }
                    OutputStream openFileOutput = context.openFileOutput(str, 1);
                    if (decodeStream.compress(CompressFormat.JPEG, 100, openFileOutput)) {
                        if (openRawResource != null) {
                            openRawResource.close();
                        }
                        if (decodeStream != null) {
                            decodeStream.recycle();
                        }
                        if (openFileOutput != null) {
                            openFileOutput.flush();
                            openFileOutput.close();
                        }
                    } else {
                        throw new IllegalStateException("Cannot compress bitmap");
                    }
                } catch (Throwable th3) {
                    th = th3;
                    obj = fileOutputStream;
                    if (openRawResource != null) {
                        openRawResource.close();
                    }
                    if (decodeStream != null) {
                        decodeStream.recycle();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                Object obj2 = fileOutputStream;
                obj = fileOutputStream;
                if (openRawResource != null) {
                    openRawResource.close();
                }
                if (decodeStream != null) {
                    decodeStream.recycle();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
                throw th;
            }
        }
        return file.getAbsolutePath();
    }

    public static int a(String str) {
        String name = new File(str).getName();
        if (name.equals("mask_countour.jpg")) {
            return R.raw.mask_contour;
        }
        if (name.equals("mask_diagonal.jpg")) {
            return R.raw.mask_diagonal;
        }
        throw new IllegalArgumentException("Unknown file: " + str);
    }

    public static String b(Context context) throws FileNotFoundException, IOException {
        File file = new File(a(context), m.a(10));
        if (o.a(n, 3)) {
            o.c("New project: " + file.getAbsolutePath());
        }
        return file.getAbsolutePath();
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
            file = new File(n.a(context), "localMusic");
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
            file = new File(n.a(context), p);
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
            file = new File(n.a(context), q);
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
            file = new File(n.a(context), r);
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
            file = new File(n.a(context), s);
            try {
                if (file.exists()) {
                    d(file.getAbsolutePath());
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
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(o);
        if (!externalStoragePublicDirectory.exists()) {
            externalStoragePublicDirectory.mkdirs();
        }
        return new File(externalStoragePublicDirectory, "").getAbsolutePath();
    }

    public static boolean a(File file) {
        if (file.isDirectory()) {
            String[] list = file.list();
            for (String file2 : list) {
                if (!a(new File(file, file2))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static String b(String str) {
        int lastIndexOf = str.lastIndexOf(47);
        return lastIndexOf == -1 ? str : str.substring(lastIndexOf + 1);
    }

    public static String h(Context context) {
        File file;
        Exception e;
        try {
            file = new File(n.a(context), "multi_music");
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
        File file;
        Exception e;
        try {
            file = new File(n.a(context), "video");
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

    public static String j(Context context) {
        File file;
        Exception e;
        try {
            file = new File(n.a(context), "multi_template_img");
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

    public static String k(Context context) {
        File file;
        Exception e;
        try {
            file = new File(n.a(context), "video_filter_img");
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

    public static String l(Context context) {
        File file;
        Exception e;
        try {
            file = new File(n.a(context), "single_music");
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
            file = new File(n.a(context), "single_template_img");
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
            file = new File(n.a(context), "template_cache");
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
        try {
            file = new File(n.a(context), "multi_template_cfg");
        } catch (Exception e) {
            e.printStackTrace();
            file = null;
        }
        return file.getPath() + dji.pilot.usercenter.protocol.d.t;
    }

    public static String p(Context context) {
        File file;
        try {
            file = new File(n.a(context), "single_template_cfg");
        } catch (Exception e) {
            e.printStackTrace();
            file = null;
        }
        return file.getPath() + dji.pilot.usercenter.protocol.d.t;
    }

    public static String c(String str) {
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

    public static void a(Context context, String str) {
        MediaScannerConnection.scanFile(context, new String[]{str}, null, new OnScanCompletedListener() {
            public void onScanCompleted(String str, Uri uri) {
                Log.v(g.n, "file " + str + " was scanned seccessfully: " + uri);
            }
        });
        a.getInstance().b(new File(str));
    }

    public static boolean d(String str) {
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
                d(str + dji.pilot.usercenter.protocol.d.t + list[i]);
                e(str + dji.pilot.usercenter.protocol.d.t + list[i]);
                z2 = true;
            } else {
                z2 = z;
            }
            i++;
            z = z2;
        }
        return z;
    }

    public static void e(String str) {
        try {
            d(str);
            new File(str.toString()).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void f(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static void g(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                file.deleteOnExit();
            }
        }
    }
}
