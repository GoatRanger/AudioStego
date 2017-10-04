package dji.midware.media.e;

import com.alipay.sdk.f.d;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.media.e;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;

public class f {
    private static final String K = "VideoRecordInfo";
    private static final boolean L = false;
    private static final double M = -100.0d;
    private static final long N = -100;
    private static final String O = "__UNDEFINED__";
    private static final String P = "1.0";
    private static final SimpleDateFormat Q = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
    public static final int a = -100;
    @a(a = "FileID_Drone")
    public Integer A = Integer.valueOf(-100);
    @a(a = "FolderID_Drone")
    public Integer B = Integer.valueOf(-100);
    @a(a = "StartTimeMsec")
    public Integer C = Integer.valueOf(-100);
    @a(a = "EndTimeMsec")
    public Integer D = Integer.valueOf(-100);
    @a(a = "FrameJumpped")
    public Integer E = Integer.valueOf(-100);
    @a(a = "Sync_Local_Time")
    @b(a = Integer.class)
    public Vector<Integer> F = null;
    @a(a = "Sync_Drone_Time")
    @b(a = Integer.class)
    public Vector<Integer> G = null;
    @a(a = "Is_HD")
    public Boolean H = Boolean.valueOf(false);
    @a(a = "Source_File_Path")
    public String I = "";
    @a(a = "File_Source_Type")
    public Integer J = Integer.valueOf(-100);
    @a(a = "Version")
    public final String b = "1.0";
    @a(a = "ProductType")
    public ProductType c;
    @a(a = "CameraType")
    public CameraType d;
    @a(a = "ApertureSize")
    public Integer e = Integer.valueOf(-100);
    @a(a = "ShutterSpeed")
    public String f = O;
    @a(a = "WhiteBalance")
    public Integer g = Integer.valueOf(-100);
    @a(a = "ExposureMode")
    public ExposureMode h;
    @a(a = "ISO")
    public Integer i = Integer.valueOf(-100);
    @a(a = "ImageDescription")
    public String j = O;
    @a(a = "DeviceMaker")
    public String k = O;
    @a(a = "PixelXDimension_Local")
    public Integer l = Integer.valueOf(-100);
    @a(a = "PixelYDimension_Local")
    public Integer m = Integer.valueOf(-100);
    @a(a = "CaptureDate")
    public Date n = new Date(0);
    @a(a = "FPS_local")
    public Integer o = Integer.valueOf(-100);
    @a(a = "PositionGPSLng")
    public Double p = Double.valueOf(M);
    @a(a = "PositionGPSLat")
    public Double q = Double.valueOf(M);
    @a(a = "PositionGPSAlt")
    public Double r = Double.valueOf(M);
    @a(a = "PositionRelativeAlt")
    public Double s = Double.valueOf(M);
    @a(a = "LocationString")
    public String t = O;
    @a(a = "LocalFileName")
    public String u = O;
    @a(a = "PixelXDimension_Drone")
    public Integer v = Integer.valueOf(-100);
    @a(a = "PixelYDimension_Drone")
    public Integer w = Integer.valueOf(-100);
    @a(a = "Video_Resolution_Enum_Drone")
    public Integer x = Integer.valueOf(-100);
    @a(a = "FPS_Drone")
    public Integer y = Integer.valueOf(-100);
    @a(a = "UUID_Drone")
    public Long z = Long.valueOf(N);

    @Retention(RetentionPolicy.RUNTIME)
    public @interface a {
        String a();
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface b {
        Class<?> a();
    }

    public ProductType a() {
        return this.c;
    }

    public void a(ProductType productType) {
        this.c = productType;
    }

    public CameraType b() {
        return this.d;
    }

    public void a(CameraType cameraType) {
        this.d = cameraType;
    }

    public int c() {
        return this.e.intValue();
    }

    public void a(int i) {
        this.e = Integer.valueOf(i);
    }

    public String d() {
        return this.f;
    }

    public void a(String str) {
        this.f = str;
    }

    public int e() {
        return this.g.intValue();
    }

    public void b(int i) {
        this.g = Integer.valueOf(i);
    }

    public ExposureMode f() {
        return this.h;
    }

    public void a(ExposureMode exposureMode) {
        this.h = exposureMode;
    }

    public int g() {
        return this.i.intValue();
    }

    public void c(int i) {
        this.i = Integer.valueOf(i);
    }

    public String h() {
        return this.j;
    }

    public void b(String str) {
        this.j = str;
    }

    public int i() {
        return this.v.intValue();
    }

    public void d(int i) {
        this.v = Integer.valueOf(i);
    }

    public int j() {
        return this.w.intValue();
    }

    public void e(int i) {
        this.w = Integer.valueOf(i);
    }

    public int k() {
        return this.l.intValue();
    }

    public void f(int i) {
        this.l = Integer.valueOf(i);
    }

    public int l() {
        return this.m.intValue();
    }

    public void g(int i) {
        this.m = Integer.valueOf(i);
    }

    public Date m() {
        return this.n;
    }

    public void a(Date date) {
        this.n = date;
    }

    public int n() {
        return this.o.intValue();
    }

    public void h(int i) {
        this.o = Integer.valueOf(i);
    }

    public String o() {
        return this.k;
    }

    public void c(String str) {
        this.k = str;
    }

    public double p() {
        return this.p.doubleValue();
    }

    public void a(double d) {
        this.p = Double.valueOf(d);
    }

    public double q() {
        return this.q.doubleValue();
    }

    public void b(double d) {
        this.q = Double.valueOf(d);
    }

    public double r() {
        return this.s.doubleValue();
    }

    public void c(double d) {
        this.s = Double.valueOf(d);
    }

    public double s() {
        return this.r.doubleValue();
    }

    public void d(double d) {
        this.r = Double.valueOf(d);
    }

    public String t() {
        return this.t;
    }

    public void d(String str) {
        this.t = str;
    }

    public String u() {
        return this.u;
    }

    public void e(String str) {
        this.u = str;
    }

    public int v() {
        return this.y.intValue();
    }

    public void i(int i) {
        this.y = Integer.valueOf(i);
    }

    public long w() {
        return this.z.longValue();
    }

    public void a(long j) {
        this.z = Long.valueOf(j);
    }

    public int x() {
        return this.A.intValue();
    }

    public void j(int i) {
        this.A = Integer.valueOf(i);
    }

    public int y() {
        return this.B.intValue();
    }

    public void k(int i) {
        this.B = Integer.valueOf(i);
    }

    public String z() {
        return "1.0";
    }

    public synchronized void f(String str) {
        Exception e;
        FileWriter fileWriter;
        Throwable th;
        Properties properties = new Properties();
        for (Field field : f.class.getFields()) {
            a aVar = (a) field.getAnnotation(a.class);
            if (aVar != null) {
                try {
                    String str2 = "";
                    Object obj = field.get(this);
                    Class type = field.getType();
                    e.c(false, K, "storing: " + type.getName());
                    if (type.equals(CameraType.class)) {
                        String valueOf;
                        CameraType cameraType = (CameraType) obj;
                        if (cameraType != null) {
                            valueOf = String.valueOf(cameraType.value());
                        } else {
                            valueOf = str2;
                        }
                        str2 = valueOf;
                    } else if (type.equals(ExposureMode.class)) {
                        ExposureMode exposureMode = (ExposureMode) obj;
                        if (exposureMode != null) {
                            str2 = String.valueOf(exposureMode.a());
                        }
                    } else if (type.equals(ProductType.class)) {
                        ProductType productType = (ProductType) obj;
                        if (productType != null) {
                            str2 = String.valueOf(productType.value());
                        }
                    } else if (type.equals(Date.class)) {
                        Date date = (Date) obj;
                        if (date != null) {
                            str2 = Q.format(date);
                        }
                    } else if (type.getName().equals("[Ljava.lang.Integer;")) {
                        Integer[] numArr = (Integer[]) obj;
                        if (numArr != null) {
                            r9 = new StringBuilder();
                            for (r2 = 0; r2 < numArr.length; r2++) {
                                if (r2 != 0) {
                                    r9.append(",");
                                }
                                r9.append(numArr[r2]);
                            }
                            str2 = r9.toString();
                        }
                    } else if (type.getName().equals("[Ljava.lang.Long;")) {
                        Long[] lArr = (Long[]) obj;
                        if (lArr != null) {
                            r9 = new StringBuilder();
                            for (r2 = 0; r2 < lArr.length; r2++) {
                                if (r2 != 0) {
                                    r9.append(",");
                                }
                                r9.append(lArr[r2]);
                            }
                            str2 = r9.toString();
                        }
                    } else if (type.equals(Vector.class)) {
                        Vector vector = (Vector) obj;
                        if (vector != null) {
                            r9 = new StringBuilder();
                            for (r2 = 0; r2 < vector.size(); r2++) {
                                if (r2 != 0) {
                                    r9.append(",");
                                }
                                r9.append(vector.get(r2).toString());
                            }
                            str2 = r9.toString();
                        }
                    } else {
                        str2 = obj == null ? O : String.valueOf(obj);
                    }
                    e.c(false, K, aVar.a() + "=" + str2);
                    properties.setProperty(aVar.a(), str2);
                } catch (Exception e2) {
                    e.a(K, e2);
                } catch (Exception e22) {
                    e.a(K, e22);
                }
            }
        }
        try {
            fileWriter = new FileWriter(str);
            try {
                properties.store(fileWriter, null);
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            } catch (IOException e4) {
                e22 = e4;
                try {
                    e.a(K, e22);
                    if (fileWriter != null) {
                        try {
                            fileWriter.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileWriter != null) {
                        try {
                            fileWriter.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e6) {
            e22 = e6;
            fileWriter = null;
            e.a(K, e22);
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (Throwable th3) {
            th = th3;
            fileWriter = null;
            if (fileWriter != null) {
                fileWriter.close();
            }
            throw th;
        }
    }

    public synchronized void g(String str) throws Exception {
        int i = 0;
        synchronized (this) {
            Properties properties = new Properties();
            Reader fileReader = new FileReader(str);
            properties.load(fileReader);
            fileReader.close();
            Vector vector = new Vector();
            for (Field field : f.class.getFields()) {
                a aVar = (a) field.getAnnotation(a.class);
                if (aVar != null) {
                    String trim = aVar.a().trim();
                    try {
                        String property = properties.getProperty(trim);
                        if (!trim.equals(d.e)) {
                            Class type = field.getType();
                            vector.add(type.toString());
                            if (property != null) {
                                String trim2 = property.trim();
                                if (!(trim2.equals("") || trim2.equals(O))) {
                                    try {
                                        if (type.equals(Integer.class)) {
                                            field.set(this, Integer.valueOf(Integer.parseInt(trim2)));
                                        } else if (type.equals(Long.class)) {
                                            field.set(this, Long.valueOf(Long.parseLong(trim2)));
                                        } else if (type.equals(Boolean.class)) {
                                            field.set(this, Boolean.valueOf(Boolean.parseBoolean(trim2)));
                                        } else if (type.equals(String.class)) {
                                            field.set(this, trim2);
                                        } else if (type.equals(Date.class)) {
                                            field.set(this, new Date(trim2));
                                        } else if (type.equals(Double.class)) {
                                            field.set(this, Double.valueOf(Double.parseDouble(trim2)));
                                        } else if (type.equals(CameraType.class)) {
                                            field.set(this, CameraType.find(Integer.parseInt(trim2)));
                                        } else if (type.equals(ExposureMode.class)) {
                                            field.set(this, ExposureMode.find(Integer.parseInt(trim2)));
                                        } else if (type.equals(ProductType.class)) {
                                            field.set(this, ProductType.find(Integer.parseInt(trim2)));
                                        } else if (type.getName().equals("[Ljava.lang.Integer;")) {
                                            r9 = trim2.split(",");
                                            r11 = new Integer[r9.length];
                                            for (r0 = 0; r0 < r11.length; r0++) {
                                                r11[r0] = Integer.valueOf(Integer.parseInt(r9[r0].trim()));
                                            }
                                            field.set(this, r11);
                                        } else if (type.getName().equals("[Ljava.lang.Long;")) {
                                            r9 = trim2.split(",");
                                            r11 = new Long[r9.length];
                                            for (r0 = 0; r0 < r11.length; r0++) {
                                                r11[r0] = Long.valueOf(Long.parseLong(r9[r0].trim()));
                                            }
                                            field.set(this, r11);
                                        } else if (!type.equals(Vector.class)) {
                                            e.b(K, "unsupported type when loading");
                                            throw new Exception("Unsupported type");
                                        } else if (((b) field.getAnnotation(b.class)).a().equals(Integer.class)) {
                                            Vector vector2 = new Vector();
                                            String[] split = trim2.split(",");
                                            for (String trim3 : split) {
                                                vector2.add(Integer.valueOf(Integer.parseInt(trim3.trim())));
                                            }
                                            field.set(this, vector2);
                                        } else {
                                            e.b(K, "unsupported type when loading");
                                            throw new Exception("Unsupported type");
                                        }
                                    } catch (Exception e) {
                                        e.a(K, e);
                                        e.d(K, field.toString());
                                        e.d(K, "key=" + trim + " ; value=" + trim2);
                                    }
                                }
                            } else {
                                continue;
                            }
                        } else if (property == null || !property.trim().equals("1.0")) {
                            throw new Exception("This method only support for loading Version 1.0");
                        }
                    } catch (Exception e2) {
                        e.a("loading key " + trim + " fails");
                    }
                }
            }
            Collections.sort(vector);
            while (i < vector.size()) {
                e.c(false, "xx", (String) vector.get(i));
                i++;
            }
        }
    }

    public int A() {
        return this.D.intValue();
    }

    public void l(int i) {
        this.D = Integer.valueOf(i);
    }

    public int B() {
        return this.C.intValue();
    }

    public void m(int i) {
        this.C = Integer.valueOf(i);
    }

    public int C() {
        return this.x.intValue();
    }

    public void n(int i) {
        this.x = Integer.valueOf(i);
    }

    public int D() {
        return this.E.intValue();
    }

    public void o(int i) {
        this.E = Integer.valueOf(i);
    }

    public Vector<Integer> E() {
        return this.F;
    }

    public void a(Vector<Integer> vector) {
        this.F = vector;
    }

    public void a(Integer num) {
        if (this.F == null) {
            this.F = new Vector();
        }
        this.F.add(num);
    }

    public Vector<Integer> F() {
        return this.G;
    }

    public void b(Vector<Integer> vector) {
        this.G = vector;
    }

    public void b(Integer num) {
        if (this.G == null) {
            this.G = new Vector();
        }
        this.G.add(num);
    }

    public dji.midware.media.j.a a(int i, int i2) {
        return new dji.midware.media.j.a(p(i), p(i2));
    }

    public Vector<dji.midware.media.j.a> b(int i, int i2) {
        Vector<dji.midware.media.j.a> vector = new Vector();
        if (this.F == null) {
            vector.add(new dji.midware.media.j.a(i, i2));
            return vector;
        }
        int i3;
        int intValue = ((Integer) this.F.get(0)).intValue();
        int i4 = 0;
        int i5 = 0;
        while (i >= intValue) {
            i4++;
            i5 = intValue;
            intValue = i4 < this.F.size() ? ((Integer) this.F.get(i4)).intValue() : Integer.MAX_VALUE;
        }
        if (i4 == 0) {
            i3 = 0;
        } else {
            i3 = ((Integer) this.G.get(i4 - 1)).intValue();
        }
        i3 += i - i5;
        while (intValue <= i2) {
            vector.add(new dji.midware.media.j.a(i3, (intValue - i) + i3));
            int intValue2 = ((Integer) this.G.get(i4)).intValue();
            i4++;
            if (i4 < this.F.size()) {
                i3 = ((Integer) this.F.get(i4)).intValue();
            } else {
                i3 = Integer.MAX_VALUE;
            }
            i = intValue;
            intValue = i3;
            i3 = intValue2;
        }
        vector.add(new dji.midware.media.j.a(i3, (i2 - i) + i3));
        return vector;
    }

    public int p(int i) {
        if (this.F == null) {
            return i;
        }
        int intValue = ((Integer) this.F.get(0)).intValue();
        int i2 = 0;
        int i3 = 0;
        while (i >= intValue) {
            i2 = i3 + 1;
            int intValue2 = i2 < this.F.size() ? ((Integer) this.F.get(i2)).intValue() : Integer.MAX_VALUE;
            i3 = i2;
            i2 = intValue;
            intValue = intValue2;
        }
        return (i3 == 0 ? 0 : ((Integer) this.G.get(i3 - 1)).intValue()) + (i - i2);
    }

    public String toString() {
        return this.u + " at folderID=" + this.B + " fileID=" + this.A + " uuid=" + this.z;
    }

    public Boolean G() {
        return this.H;
    }

    public void a(Boolean bool) {
        this.H = bool;
    }

    public String H() {
        return this.I;
    }

    public void h(String str) {
        this.I = str;
    }

    public Integer I() {
        return this.J;
    }

    public void c(Integer num) {
        this.J = num;
    }
}
