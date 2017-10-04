package dji.midware.media.e;

import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.util.c;
import dji.pilot2.media.activity.DJIPhotoPreveiwActivity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;

public class b {
    private static final int b = 1836019574;
    private static final int c = 1969517665;
    private static final int d = -1451722335;
    private static final int e = -1452448660;
    RandomAccessFile a;
    private File f;
    private HashMap<String, String> g = new HashMap();
    private String h = "";
    private int i = 0;
    private int j = 0;
    private long k = 0;
    private byte[] l = new byte[4];
    private byte[] m = new byte[1000];
    private int n = 0;

    public ProductType a() {
        CameraType cameraType;
        ProductType productType = ProductType.None;
        String str = (String) this.g.get("mdl");
        if ("FC300S".equals(str)) {
            cameraType = CameraType.DJICameraTypeFC300S;
        } else if ("FC300X".equals(str)) {
            cameraType = CameraType.DJICameraTypeFC300X;
        } else if ("FC260".equals(str)) {
            cameraType = CameraType.DJICameraTypeFC260;
        } else if ("FC350".equals(str)) {
            cameraType = CameraType.DJICameraTypeFC350;
        } else if ("HG310".equals(str)) {
            return ProductType.Longan;
        } else {
            if ("OSMO RAW".equals(str)) {
                return ProductType.LonganRaw;
            }
            if ("OSMO PRO".equals(str)) {
                return ProductType.LonganPro;
            }
            if ("FC300XW".equalsIgnoreCase(str)) {
                cameraType = CameraType.DJICameraTypeFC300XW;
            } else if ("FC550RAW".equalsIgnoreCase(str)) {
                cameraType = CameraType.DJICameraTypeFC550Raw;
            } else if ("FC550".equalsIgnoreCase(str)) {
                cameraType = CameraType.DJICameraTypeFC550;
            } else if ("FC330".equalsIgnoreCase(str)) {
                cameraType = CameraType.DJICameraTypeFC330X;
            } else if ("FC6310".equals(str)) {
                cameraType = CameraType.DJICameraTypeFC6310;
            } else {
                cameraType = str == null ? null : null;
            }
        }
        if (productType != ProductType.None) {
            return productType;
        }
        if (cameraType == CameraType.DJICameraTypeFC300S) {
            return ProductType.litchiS;
        }
        if (cameraType == CameraType.DJICameraTypeFC300X) {
            return ProductType.litchiX;
        }
        if (cameraType == CameraType.DJICameraTypeFC260) {
            return ProductType.litchiC;
        }
        if (cameraType == CameraType.DJICameraTypeFC350) {
            return ProductType.Orange;
        }
        if (cameraType == CameraType.DJICameraTypeFC300XW) {
            return ProductType.P34K;
        }
        if (cameraType == CameraType.DJICameraTypeFC550) {
            return ProductType.BigBanana;
        }
        if (cameraType == CameraType.DJICameraTypeFC550Raw) {
            return ProductType.OrangeRAW;
        }
        if (cameraType == CameraType.DJICameraTypeFC330X) {
            return ProductType.Tomato;
        }
        if (cameraType == CameraType.DJICameraTypeFC6310) {
            return ProductType.Pomato;
        }
        return ProductType.OTHER;
    }

    public double[] b() {
        double[] dArr = new double[3];
        String str = (String) this.g.get("xyz");
        if (!(str == null || str.isEmpty())) {
            String[] split = str.split("\\+");
            if (split.length >= 4) {
                dArr[0] = Double.parseDouble(split[1]);
                dArr[1] = Double.parseDouble(split[2]);
                dArr[2] = Double.parseDouble(split[3]);
            } else {
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
                dArr[2] = 0.0d;
            }
        }
        return dArr;
    }

    public HashMap<String, String> c() {
        return this.g;
    }

    public void a(String str) throws FileNotFoundException {
        this.f = new File(str);
        this.a = new RandomAccessFile(this.f, "rw");
    }

    public void d() throws IOException {
        long length = this.a.length();
        this.k = 0;
        this.i = 0;
        this.j = 0;
        f();
        while (length > this.k) {
            switch (this.j) {
                case b /*1836019574*/:
                    this.n = this.i - 8;
                    a(false);
                    this.n -= 8;
                    while (this.n > 0) {
                        switch (this.j) {
                            case c /*1969517665*/:
                                int i = this.i - 8;
                                while (i > 0) {
                                    a(false);
                                    int i2 = this.i - 8;
                                    i = (i - 8) - i2;
                                    this.a.read(this.m, 0, i2);
                                    this.g.put(this.h, a(ByteBuffer.wrap(this.m, 0, i2).order(ByteOrder.BIG_ENDIAN)).trim());
                                }
                                this.n = 0;
                                break;
                            default:
                                f();
                                this.n -= this.i + 8;
                                break;
                        }
                    }
                    this.k = length;
                    break;
                default:
                    f();
                    break;
            }
        }
    }

    private void f() throws IOException {
        a(true);
    }

    private void a(boolean z) throws IOException {
        if (this.i > 0 && z) {
            this.a.skipBytes(this.i - 8);
            this.k += (long) (this.i - 8);
        }
        this.a.read(this.l);
        this.k += 4;
        this.i = b(this.l);
        this.a.read(this.l);
        this.k += 4;
        this.j = b(this.l);
        this.h = c(this.l);
    }

    private int b(byte[] bArr) {
        return ByteBuffer.wrap(bArr, 0, 4).order(ByteOrder.BIG_ENDIAN).getInt();
    }

    private String c(byte[] bArr) {
        bArr[0] = (byte) 0;
        return new String(ByteBuffer.wrap(bArr, 0, 4).order(ByteOrder.BIG_ENDIAN).array(), Charset.forName("UTF-8")).trim();
    }

    private String a(ByteBuffer byteBuffer) {
        return a(byteBuffer.array());
    }

    public static String a(byte[] bArr) {
        for (int i = 1; i < bArr.length; i++) {
            if (bArr[i] == (byte) 0) {
                bArr = c.e(bArr, 0, i);
                break;
            }
        }
        return new String(bArr, Charset.forName("GBK"));
    }

    public Date e() {
        String[] split = this.f.getName().split("_");
        if (!split[0].equals(DJIPhotoPreveiwActivity.E) || split.length != 3 || split[2].length() < 4) {
            return new Date();
        }
        try {
            return new Date(Long.parseLong(split[2].substring(0, split[2].length() - 4)));
        } catch (Exception e) {
            return new Date();
        }
    }
}
