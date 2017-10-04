package dji.b.a.a;

import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ExifInterface;
import dji.pilot.fpv.control.f;
import dji.thirdparty.g.a.i;
import dji.thirdparty.g.b.b.a.b;
import dji.thirdparty.g.b.b.a.e;
import dji.thirdparty.g.b.b.c.h;
import dji.thirdparty.g.b.b.g;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class a {
    public static void a(File file, Float f, String str, Integer num, Integer num2, Integer num3, String str2, String str3) {
        try {
            ExifInterface exifInterface = new ExifInterface(file.getAbsolutePath());
            exifInterface.setAttribute("ExposureTime", str);
            exifInterface.saveAttributes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        HashMap hashMap = new HashMap();
        hashMap.put(b.gI, Integer.valueOf(options.outWidth));
        hashMap.put(b.gJ, Integer.valueOf(options.outHeight));
        hashMap.put(b.hq, num);
        hashMap.put(b.ht, num2);
        hashMap.put(b.ex, num3);
        hashMap.put(b.dy, f);
        hashMap.put(b.aN, str2);
        hashMap.put(b.eH, str3);
        hashMap.put(b.eI, str3);
        a(file, hashMap);
    }

    public static void a(File file, HashMap<e, Object> hashMap) {
        File file2 = new File(file.getAbsolutePath() + f.b);
        try {
            dji.thirdparty.g.b.b.c.e eVar;
            h a = a(file, 73);
            List a2 = a.a();
            if (a2.size() > 0) {
                eVar = (dji.thirdparty.g.b.b.c.e) a2.get(0);
            } else {
                eVar = a.e();
            }
            for (Entry entry : hashMap.entrySet()) {
                eVar.a((e) entry.getKey());
                if (entry.getValue() instanceof Number) {
                    eVar.a(dji.thirdparty.g.b.b.c.f.a((e) entry.getKey(), a.j, (Number) entry.getValue()));
                } else if (entry.getValue() instanceof String) {
                    eVar.a(dji.thirdparty.g.b.b.c.f.a((e) entry.getKey(), a.j, (String) entry.getValue()));
                } else {
                    eVar.a(dji.thirdparty.g.b.b.c.f.a((e) entry.getKey(), a.j, (Number[]) entry.getValue()));
                }
            }
            OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
            new dji.thirdparty.g.b.a.a.a().a(file, bufferedOutputStream, a);
            bufferedOutputStream.close();
            if (file.delete()) {
                file2.renameTo(file);
            }
        } catch (dji.thirdparty.g.f e) {
            e.printStackTrace();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (dji.thirdparty.g.e e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    public static boolean a(File file, File file2, List<e> list) {
        File file3;
        dji.thirdparty.g.e e;
        Throwable th;
        dji.thirdparty.g.f e2;
        IOException e3;
        String str = file2.getAbsolutePath() + f.b;
        File file4 = null;
        OutputStream outputStream = null;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file2.getAbsolutePath(), options);
        try {
            file3 = new File(str);
            try {
                h a = a(file, 73);
                h a2 = a(file2, a.j);
                if (a.j != a2.j) {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    if (file3 == null || !file3.exists()) {
                        return false;
                    }
                    file3.delete();
                    return false;
                }
                a2.e();
                List a3 = a.a();
                for (int i = 0; i < a3.size(); i++) {
                    dji.thirdparty.g.b.b.c.e eVar = (dji.thirdparty.g.b.b.c.e) a3.get(i);
                    dji.thirdparty.g.b.b.c.e a4 = a(a2, eVar);
                    if (a4 != null) {
                        a4.a(dji.thirdparty.g.b.b.c.f.a(b.gI, a.j, Integer.valueOf(options.outWidth)));
                        a4.a(dji.thirdparty.g.b.b.c.f.a(b.gJ, a.j, Integer.valueOf(options.outHeight)));
                        List a5 = eVar.a();
                        for (int i2 = 0; i2 < a5.size(); i2++) {
                            dji.thirdparty.g.b.b.c.f fVar = (dji.thirdparty.g.b.b.c.f) a5.get(i2);
                            if (list == null || !list.contains(fVar.k)) {
                                a4.a(fVar.k);
                                a4.a(fVar);
                            } else {
                                a4.a(fVar.k);
                            }
                        }
                    }
                }
                OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file3));
                try {
                    new dji.thirdparty.g.b.a.a.a().a(file2, bufferedOutputStream, a2);
                    bufferedOutputStream.close();
                    if (file2.delete()) {
                        file3.renameTo(file2);
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e5) {
                        }
                    }
                    if (file3 == null || !file3.exists()) {
                        return true;
                    }
                    file3.delete();
                    return true;
                } catch (dji.thirdparty.g.e e6) {
                    e = e6;
                    outputStream = bufferedOutputStream;
                    file4 = file3;
                    try {
                        e.printStackTrace();
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e7) {
                            }
                        }
                        file4.delete();
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        file3 = file4;
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e8) {
                            }
                        }
                        file3.delete();
                        throw th;
                    }
                } catch (dji.thirdparty.g.f e9) {
                    e2 = e9;
                    outputStream = bufferedOutputStream;
                    try {
                        e2.printStackTrace();
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e10) {
                            }
                        }
                        file3.delete();
                        return false;
                    } catch (Throwable th3) {
                        th = th3;
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (file3 != null && file3.exists()) {
                            file3.delete();
                        }
                        throw th;
                    }
                } catch (IOException e11) {
                    e3 = e11;
                    outputStream = bufferedOutputStream;
                    e3.printStackTrace();
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e12) {
                        }
                    }
                    file3.delete();
                    return false;
                } catch (Throwable th4) {
                    th = th4;
                    outputStream = bufferedOutputStream;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    file3.delete();
                    throw th;
                }
            } catch (dji.thirdparty.g.e e13) {
                e = e13;
                file4 = file3;
                e.printStackTrace();
                if (outputStream != null) {
                    outputStream.close();
                }
                if (file4 != null && file4.exists()) {
                    file4.delete();
                }
                return false;
            } catch (dji.thirdparty.g.f e14) {
                e2 = e14;
                e2.printStackTrace();
                if (outputStream != null) {
                    outputStream.close();
                }
                if (file3 != null && file3.exists()) {
                    file3.delete();
                }
                return false;
            } catch (IOException e15) {
                e3 = e15;
                e3.printStackTrace();
                if (outputStream != null) {
                    outputStream.close();
                }
                if (file3 != null && file3.exists()) {
                    file3.delete();
                }
                return false;
            }
        } catch (dji.thirdparty.g.e e16) {
            e = e16;
            e.printStackTrace();
            if (outputStream != null) {
                outputStream.close();
            }
            file4.delete();
            return false;
        } catch (dji.thirdparty.g.f e17) {
            e2 = e17;
            file3 = null;
            e2.printStackTrace();
            if (outputStream != null) {
                outputStream.close();
            }
            file3.delete();
            return false;
        } catch (IOException e18) {
            e3 = e18;
            file3 = null;
            e3.printStackTrace();
            if (outputStream != null) {
                outputStream.close();
            }
            file3.delete();
            return false;
        } catch (Throwable th5) {
            th = th5;
            file3 = null;
            if (outputStream != null) {
                outputStream.close();
            }
            file3.delete();
            throw th;
        }
    }

    private static h a(File file, int i) throws IOException, dji.thirdparty.g.e, dji.thirdparty.g.f {
        h c;
        g gVar = null;
        i a = dji.thirdparty.g.g.a(file);
        if (a instanceof dji.thirdparty.g.b.a.b) {
            dji.thirdparty.g.b.a.b bVar = (dji.thirdparty.g.b.a.b) a;
            if (bVar != null) {
                g b = bVar.b();
                if (b != null) {
                    c = b.c();
                    gVar = b;
                } else {
                    c = null;
                    gVar = b;
                }
            } else {
                c = null;
            }
        } else {
            g gVar2 = (g) a;
            if (gVar2 != null) {
                gVar = gVar2;
                c = gVar2.c();
            } else {
                gVar = gVar2;
                Object obj = null;
            }
        }
        if (c == null) {
            if (gVar != null) {
                i = gVar.b.a.a;
            }
            c = new h(i);
        }
        return c;
    }

    private static dji.thirdparty.g.b.b.c.e a(h hVar, dji.thirdparty.g.b.b.c.e eVar) {
        dji.thirdparty.g.b.b.c.e a = hVar.a(eVar.j);
        if (a != null) {
            return a;
        }
        a = new dji.thirdparty.g.b.b.c.e(eVar.j);
        try {
            hVar.a(a);
            return a;
        } catch (dji.thirdparty.g.f e) {
            return null;
        }
    }
}
