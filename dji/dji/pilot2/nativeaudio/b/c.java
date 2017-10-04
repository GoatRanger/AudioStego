package dji.pilot2.nativeaudio.b;

import android.content.Context;
import android.os.Environment;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.a.c.l;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.pilot.fpv.d.c.k;
import dji.pilot.fpv.d.e;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.utils.n;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class c implements k {
    protected b K;
    private dji.thirdparty.afinal.f.c<File> L = null;
    private a M;
    private dji.pilot2.multimoment.template.c N;
    private String O;

    public interface b {
        void a(int i);

        void a(String str);

        void a(Throwable th, int i, String str);

        void a(boolean z);
    }

    public enum a {
        Muti,
        BigFilm
    }

    public c(a aVar, String str) {
        this.M = aVar;
        this.N = dji.pilot2.multimoment.template.c.getInstance();
        this.O = str;
    }

    public void a(b bVar) {
        this.K = bVar;
    }

    public Boolean a(String str, final Context context) {
        String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/template.zip";
        File file = new File(str2);
        if (file.exists()) {
            file.delete();
        }
        this.L = com.dji.frame.c.c.b(context).a(str, str2, false, new dji.thirdparty.afinal.f.a<File>(this) {
            final /* synthetic */ c b;

            public void a(boolean z) {
                DJILogHelper.getInstance().LOGI("bob", "download template start ！ isResume = " + String.valueOf(z));
                if (this.b.K != null) {
                    this.b.K.a(z);
                }
            }

            public void a(long j, long j2) {
                DJILogHelper.getInstance().LOGI("bob", "download template onLoading " + j2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + j);
                if (this.b.K != null) {
                    this.b.K.a((int) ((((float) j2) / ((float) j)) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity));
                }
            }

            public void a(File file) {
                e.b(k.G);
                DJILogHelper.getInstance().LOGI("bob", "download template onSuccess " + file.getAbsoluteFile().toString());
                String str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aaa";
                String str2 = null;
                try {
                    str2 = this.b.a(file.getAbsoluteFile().toString(), str, context);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (this.b.K != null) {
                    this.b.K.a(str2);
                }
            }

            public void a(Throwable th, int i, String str) {
                DJILogHelper.getInstance().LOGI("bob", "download template onFailure " + str + " errorNo= " + i);
                if (this.b.K != null) {
                    this.b.K.a(th, i, str);
                }
            }
        });
        return Boolean.valueOf(true);
    }

    protected String a(String str, String str2, Context context) throws IOException {
        DJILogHelper.getInstance().LOGI("upZipFile", "zipFilePath = " + str);
        ZipFile zipFile = new ZipFile(str);
        Enumeration entries = zipFile.entries();
        byte[] bArr = new byte[1024];
        String str3 = null;
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            if (zipEntry.isDirectory()) {
                DJILogHelper.getInstance().LOGI("upZipFile", "ze.getName() = " + zipEntry.getName());
            } else {
                DJILogHelper.getInstance().LOGI("upZipFile", "ze.getName() = " + zipEntry.getName());
                String name = zipEntry.getName();
                if (!name.contains("__MACOSX")) {
                    if (name.endsWith("cfg")) {
                        if (this.M == a.Muti) {
                            name = n.a(context).getAbsolutePath() + d.t + "multi_template_cfg" + d.t + name;
                        } else {
                            name = n.a(context).getAbsolutePath() + d.t + "single_template_cfg" + d.t + name.substring(0, name.indexOf(".")) + "-" + this.O + ".cfg";
                        }
                        str3 = name;
                    } else if (!name.endsWith("m4a")) {
                        name = n.a(context).getAbsolutePath() + d.t + "multi_template_img" + d.t + name;
                    } else if (this.M == a.Muti) {
                        name = n.a(context).getAbsolutePath() + d.t + "multi_music" + d.t + name;
                    } else {
                        name = n.a(context).getAbsolutePath() + d.t + "single_music" + d.t + name.substring(0, name.indexOf(".")) + "-" + this.O + ".m4a";
                    }
                    DJILogHelper.getInstance().LOGI("upZipFile", "outFileNameString = " + name);
                    if (name != null) {
                        OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(name));
                        InputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                        while (true) {
                            int read = bufferedInputStream.read(bArr, 0, 1024);
                            if (read == -1) {
                                break;
                            }
                            bufferedOutputStream.write(bArr, 0, read);
                        }
                        bufferedInputStream.close();
                        bufferedOutputStream.close();
                    }
                }
            }
        }
        zipFile.close();
        DJILogHelper.getInstance().LOGI("upZipFile", "finishssssssssssssssssssss return " + str3);
        return str3;
    }

    public void a() {
        if (this.L != null) {
            this.L.a(true);
        }
    }

    private static File a(String str, String str2) {
        String str3;
        UnsupportedEncodingException e;
        String[] split = str2.split(d.t);
        File file = new File(str);
        if (split.length <= 1) {
            return file;
        }
        String str4;
        int i = 0;
        File file2 = file;
        while (i < split.length - 1) {
            str3 = split[i];
            try {
                str4 = new String(str3.getBytes("8859_1"), l.b);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                str4 = str3;
            }
            i++;
            file2 = new File(file2, str4);
        }
        DJILogHelper.getInstance().LOGI("upZipFile", "1ret = " + file2.getAbsoluteFile().toString() + " getAbsolutePath = " + file2.getAbsolutePath());
        if (!file2.exists()) {
            file2.mkdirs();
        }
        str3 = split[split.length - 1];
        try {
            str4 = new String(str3.getBytes("8859_1"), l.b);
            try {
                DJILogHelper.getInstance().LOGI("upZipFile", "substr = " + str4);
            } catch (UnsupportedEncodingException e3) {
                e = e3;
                e.printStackTrace();
                file = new File(file2, str4);
                DJILogHelper.getInstance().LOGI("upZipFile", "2ret = " + file);
                return file;
            }
        } catch (UnsupportedEncodingException e22) {
            UnsupportedEncodingException unsupportedEncodingException = e22;
            str4 = str3;
            e = unsupportedEncodingException;
            e.printStackTrace();
            file = new File(file2, str4);
            DJILogHelper.getInstance().LOGI("upZipFile", "2ret = " + file);
            return file;
        }
        file = new File(file2, str4);
        DJILogHelper.getInstance().LOGI("upZipFile", "2ret = " + file);
        return file;
    }
}
