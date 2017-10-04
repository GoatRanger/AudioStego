package dji.pilot2.utils.a;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import dji.pilot.publics.objects.g;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.utils.n;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class a {
    public static final String a = "multi_music";
    public static final String b = "multi_video";
    public static final String c = "multi_template_cfg";
    public static final String d = "multi_template_img";
    public static final String e = "music";
    public static final String f = "video";
    public static final String g = "template_cfg";
    public static final String h = "template_img";
    public static final String i = "single_template_cfg";
    public static final String j = "single_music";
    public static final String k = "single_template_img";
    public static final String l = "video_filter_img";
    public static final String m = "Model";
    public static final String n = "bigfilm";
    public static final String o = "music";
    public static final String p = "video";
    public static final String q = "conf";
    public static final String r = "image";
    private static a t = new a();
    public final List<a> s = new ArrayList();
    private boolean u = false;

    public interface a {
        void a();

        void a(String str, float f);

        void b();
    }

    private a() {
    }

    public static a getInstance() {
        return t;
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.s.remove(aVar);
        }
    }

    public void b(a aVar) {
        if (aVar != null && !this.s.contains(aVar)) {
            this.s.add(aVar);
        }
    }

    private void a(File file) {
        if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                file.delete();
                return;
            }
            for (File a : listFiles) {
                a(a);
            }
            file.delete();
        }
    }

    private void a(Context context, String str) throws FileNotFoundException, IOException {
        byte[] bArr = new byte[1024];
        File file = new File(n.b(context), str);
        if (!(file == null || file.exists())) {
            file.mkdir();
        }
        String str2 = "bigfilm/" + str;
        String[] list = context.getAssets().list(str2);
        for (String str3 : list) {
            File file2 = new File(file, str3);
            InputStream open;
            int read;
            if (file2 == null || !file2.exists()) {
                open = context.getAssets().open(str2 + d.t + str3);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                while (true) {
                    read = open.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.flush();
                open.close();
                fileOutputStream.close();
            } else {
                open = context.getAssets().open(str2 + d.t + str3);
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file2));
                if (bufferedReader.readLine() == null) {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file2, false);
                    while (true) {
                        read = open.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream2.write(bArr, 0, read);
                    }
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                    open.close();
                } else {
                    FileInputStream fileInputStream = new FileInputStream(file2);
                    file2.delete();
                    FileOutputStream fileOutputStream3 = new FileOutputStream(file2, false);
                    while (true) {
                        read = open.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream3.write(bArr, 0, read);
                    }
                    fileOutputStream3.flush();
                    fileOutputStream3.close();
                    open.close();
                    fileInputStream.close();
                }
                bufferedReader.close();
            }
        }
    }

    private void a(Context context, String str, boolean z) throws IOException {
        if (str.equals(b)) {
            File file = new File(n.a(context), str);
            if (file == null || file.exists()) {
                a(file);
                return;
            }
            return;
        }
        if ((str.equals("single_template_img") || str.equals("single_template_cfg") || str.equals("single_music") || str.equals("multi_music") || str.equals("multi_template_cfg") || str.equals("multi_template_img")) && z) {
            File[] listFiles = new File(n.a(context), str).listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.toString().endsWith(dji.pilot2.main.a.a.n) || file2.toString().endsWith(".cfg") || file2.toString().endsWith(".m4a")) {
                        file2.delete();
                    }
                }
            }
        }
        byte[] bArr = new byte[1024];
        File file3 = new File(n.a(context), str);
        if (!(file3 == null || file3.exists())) {
            file3.mkdir();
        }
        String[] list = context.getAssets().list(str);
        for (int i = 0; i < list.length; i++) {
            String str2 = list[i];
            if (!(str2 == null || str2.equals("") || str2.isEmpty())) {
                File file4 = new File(file3, str2);
                InputStream open;
                int read;
                a aVar;
                if (file4 == null || !file4.exists()) {
                    open = context.getAssets().open(str + d.t + str2);
                    FileOutputStream fileOutputStream = new FileOutputStream(file4);
                    while (true) {
                        read = open.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    open.close();
                    fileOutputStream.close();
                    for (read = 0; read < this.s.size(); read++) {
                        aVar = (a) this.s.get(read);
                        if (aVar != null) {
                            aVar.a(str, (((float) (i + 1)) * 1.0f) / ((float) list.length));
                        }
                    }
                } else {
                    open = context.getAssets().open(str + d.t + str2);
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file4));
                    if (bufferedReader.readLine() == null) {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file4, false);
                        while (true) {
                            read = open.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream2.write(bArr, 0, read);
                        }
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                        open.close();
                        for (read = 0; read < this.s.size(); read++) {
                            aVar = (a) this.s.get(read);
                            if (aVar != null) {
                                aVar.a(str, (((float) (i + 1)) * 1.0f) / ((float) list.length));
                            }
                        }
                    } else {
                        FileInputStream fileInputStream = new FileInputStream(file4);
                        file4.delete();
                        FileOutputStream fileOutputStream3 = new FileOutputStream(file4, false);
                        while (true) {
                            read = open.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream3.write(bArr, 0, read);
                        }
                        fileOutputStream3.flush();
                        fileOutputStream3.close();
                        open.close();
                        fileInputStream.close();
                    }
                    bufferedReader.close();
                }
            }
        }
    }

    public static int a(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return i;
        }
    }

    public void b(final Context context) {
        new Thread(this) {
            final /* synthetic */ a b;

            public void run() {
                int i = 0;
                try {
                    a aVar;
                    this.b.u = true;
                    for (int i2 = 0; i2 < this.b.s.size(); i2++) {
                        aVar = (a) this.b.s.get(i2);
                        if (aVar != null) {
                            aVar.b();
                        }
                    }
                    boolean b = g.b(context, "key_bigfilm", false);
                    boolean b2 = g.b(context, "key_newyear", false);
                    boolean b3 = g.b(context, "key_multi", false);
                    int b4 = g.b(context, "key_multi_versioncode", 0);
                    if (b3 && b4 == a.a(context)) {
                        this.b.a(context, "multi_template_cfg", false);
                        this.b.a(context, "multi_music", false);
                        this.b.a(context, "video", false);
                        this.b.a(context, "multi_template_img", false);
                    } else {
                        this.b.a(context, "multi_template_cfg", true);
                        this.b.a(context, "multi_music", true);
                        this.b.a(context, "video", true);
                        this.b.a(context, "multi_template_img", true);
                        g.a(context, "key_multi", true);
                        g.a(context, "key_multi_versioncode", a.a(context));
                    }
                    if (!(b && b2)) {
                        b = (b2 || b) ? false : true;
                        this.b.a(context, "single_template_cfg", b);
                        this.b.a(context, "single_template_img", b);
                        this.b.a(context, "single_music", b);
                        g.a(context, "key_bigfilm", true);
                        g.a(context, "key_newyear", true);
                    }
                    this.b.a(context, "video_filter_img", true);
                    this.b.a(context, "music");
                    this.b.a(context, "video");
                    this.b.a(context, a.q);
                    this.b.a(context, "image");
                    while (i < this.b.s.size()) {
                        aVar = (a) this.b.s.get(i);
                        if (aVar != null) {
                            aVar.a();
                        }
                        i++;
                    }
                    this.b.u = false;
                } catch (Exception e) {
                    e.printStackTrace();
                    g.a(context, "key_multi", true);
                }
            }
        }.start();
    }
}
