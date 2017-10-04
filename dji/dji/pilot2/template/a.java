package dji.pilot2.template;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import dji.log.DJILogHelper;
import dji.pilot2.utils.n;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class a extends d {
    public String a;
    public List<Integer> b;
    public List<Integer> c;
    public List<Integer> d;
    public String e;
    public String f;
    public String g;
    public Bitmap h;
    public long i;
    public String j;
    public int k;
    public int l;
    private Bitmap m;
    private long n;
    private int o;
    private int p;
    private Boolean q;

    public a() {
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.o = 24;
        this.q = Boolean.valueOf(false);
        this.n = 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public a(android.content.Context r11, java.io.InputStream r12) throws java.io.IOException {
        /*
        r10 = this;
        r9 = 320; // 0x140 float:4.48E-43 double:1.58E-321;
        r4 = 24;
        r2 = 0;
        r1 = 0;
        r10.<init>();
        r0 = new java.util.ArrayList;
        r0.<init>();
        r10.b = r0;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r10.c = r0;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r10.d = r0;
        r10.o = r4;
        r0 = java.lang.Boolean.valueOf(r1);
        r10.q = r0;
        r10.i = r2;
        r10.n = r2;
        r0 = new java.io.InputStreamReader;
        r0.<init>(r12);
        r2 = new java.io.BufferedReader;
        r2.<init>(r0);
    L_0x0035:
        r0 = r2.readLine();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r0 == 0) goto L_0x023b;
    L_0x003b:
        r3 = "=";
        r0 = r0.split(r3);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3 = r0.length;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = 2;
        if (r3 != r4) goto L_0x0035;
    L_0x0045:
        r3 = 0;
        r4 = 0;
        r4 = r0[r4];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = r4.trim();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0[r3] = r4;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3 = 1;
        r4 = 1;
        r4 = r0[r4];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = r4.trim();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0[r3] = r4;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3 = 0;
        r3 = r0[r3];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = "templateName";
        r3 = r3.equals(r4);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r3 == 0) goto L_0x006f;
    L_0x0064:
        r3 = 1;
        r0 = r0[r3];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r10.a = r0;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        goto L_0x0035;
    L_0x006a:
        r0 = move-exception;
        r2.close();
        throw r0;
    L_0x006f:
        r3 = 0;
        r3 = r0[r3];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = "description";
        r3 = r3.equals(r4);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r3 == 0) goto L_0x0088;
    L_0x007a:
        r3 = 1;
        r0 = r0[r3];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r10.e = r0;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        goto L_0x0035;
    L_0x0080:
        r0 = move-exception;
        r0.printStackTrace();
        r2.close();
        throw r0;
    L_0x0088:
        r3 = 0;
        r3 = r0[r3];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = "previewFileName";
        r3 = r3.equals(r4);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r3 == 0) goto L_0x00b6;
    L_0x0093:
        r3 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3.<init>();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = dji.pilot2.utils.n.m(r11);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3 = r3.append(r4);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = 1;
        r0 = r0[r4];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = r3.append(r0);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = r0.toString();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r10.f = r0;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        goto L_0x0035;
    L_0x00ae:
        r0 = move-exception;
        r0.printStackTrace();
        r2.close();
        throw r0;
    L_0x00b6:
        r3 = 0;
        r3 = r0[r3];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = "previewMusicFileName";
        r3 = r3.equals(r4);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r3 == 0) goto L_0x00f4;
    L_0x00c1:
        r3 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3.<init>();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = dji.pilot2.utils.n.h(r11);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3 = r3.append(r4);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = 1;
        r0 = r0[r4];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = r3.append(r0);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = r0.toString();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r10.g = r0;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = r10.g;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r0 == 0) goto L_0x0035;
    L_0x00df:
        r0 = new java.io.File;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3 = r10.g;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0.<init>(r3);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = r0.exists();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r0 != 0) goto L_0x0035;
    L_0x00ec:
        r0 = new java.io.FileNotFoundException;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r1 = "MultiTemplate music file not found";
        r0.<init>(r1);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        throw r0;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
    L_0x00f4:
        r3 = "fps";
        r4 = 0;
        r4 = r0[r4];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3 = r3.equals(r4);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r3 == 0) goto L_0x0112;
    L_0x00ff:
        r3 = 1;
        r0 = r0[r3];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r10.o = r0;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = r10.o;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r0 != 0) goto L_0x0035;
    L_0x010c:
        r0 = 24;
        r10.o = r0;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        goto L_0x0035;
    L_0x0112:
        r3 = 0;
        r3 = r0[r3];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = "range";
        r3 = r3.equals(r4);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r3 == 0) goto L_0x0167;
    L_0x011d:
        r3 = 1;
        r0 = r0[r3];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3 = ";";
        r3 = r0.split(r3);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = r3.length;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r10.k = r0;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = r3.length;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = r1;
    L_0x012b:
        if (r0 >= r4) goto L_0x0035;
    L_0x012d:
        r5 = r3[r0];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r6 = "-";
        r5 = r5.split(r6);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r6 = 1;
        r6 = r5[r6];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r7 = r10.o;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r6 = r10.parseFrameToTime(r6, r7);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r7 = 0;
        r5 = r5[r7];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r7 = r10.o;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r5 = r10.parseFrameToTime(r5, r7);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r7 = r10.b;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r8 = java.lang.Integer.valueOf(r5);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r7.add(r8);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r7 = r10.c;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r8 = java.lang.Integer.valueOf(r6);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r7.add(r8);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r7 = r10.d;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r5 = r6 - r5;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r7.add(r5);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = r0 + 1;
        goto L_0x012b;
    L_0x0167:
        r3 = 0;
        r3 = r0[r3];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = "concatMusicFilePath";
        r3 = r3.equals(r4);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r3 == 0) goto L_0x018e;
    L_0x0172:
        r3 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3.<init>();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = dji.pilot2.utils.n.h(r11);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3 = r3.append(r4);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = 1;
        r0 = r0[r4];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = r3.append(r0);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = r0.toString();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r10.j = r0;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        goto L_0x0035;
    L_0x018e:
        r3 = 0;
        r3 = r0[r3];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = "thumbnailPath";
        r3 = r3.equals(r4);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r3 == 0) goto L_0x0206;
    L_0x0199:
        r3 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3.<init>();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = dji.pilot2.utils.n.n(r11);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3 = r3.append(r4);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = 1;
        r0 = r0[r4];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = r3.append(r0);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = r0.toString();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r0 == 0) goto L_0x01c6;
    L_0x01b3:
        r3 = new java.io.File;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3.<init>(r0);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3 = r3.exists();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r3 != 0) goto L_0x01c6;
    L_0x01be:
        r0 = new java.io.FileNotFoundException;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r1 = "MultiTemplate thumbnail file not found";
        r0.<init>(r1);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        throw r0;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
    L_0x01c6:
        r3 = android.graphics.Bitmap.Config.RGB_565;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = r10.getAdjustThumbnail(r0, r3);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r10.h = r0;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = r10.h;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r0 == 0) goto L_0x0035;
    L_0x01d2:
        r0 = r10.h;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r0 = r0.getWidth();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r0 <= r9) goto L_0x0035;
    L_0x01da:
        r0 = 320; // 0x140 float:4.48E-43 double:1.58E-321;
        r3 = r10.h;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3 = r3.getHeight();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3 = r3 * r9;
        r4 = r10.h;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = r4.getWidth();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3 = r3 / r4;
        r4 = r10.h;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r5 = 0;
        r0 = android.graphics.Bitmap.createScaledBitmap(r4, r0, r3, r5);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3 = r10.h;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r3 == 0) goto L_0x0202;
    L_0x01f5:
        r3 = r10.h;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3 = r3.isRecycled();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r3 != 0) goto L_0x0202;
    L_0x01fd:
        r3 = r10.h;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r3.recycle();	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
    L_0x0202:
        r10.h = r0;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        goto L_0x0035;
    L_0x0206:
        r3 = 0;
        r3 = r0[r3];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = "timeStamp";
        r3 = r3.equals(r4);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r3 == 0) goto L_0x0223;
    L_0x0211:
        r3 = 1;
        r0 = r0[r3];	 Catch:{ NumberFormatException -> 0x021c, FileNotFoundException -> 0x006a, IOException -> 0x0080 }
        r4 = java.lang.Long.parseLong(r0);	 Catch:{ NumberFormatException -> 0x021c, FileNotFoundException -> 0x006a, IOException -> 0x0080 }
        r10.i = r4;	 Catch:{ NumberFormatException -> 0x021c, FileNotFoundException -> 0x006a, IOException -> 0x0080 }
        goto L_0x0035;
    L_0x021c:
        r0 = move-exception;
        r4 = 0;
        r10.i = r4;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        goto L_0x0035;
    L_0x0223:
        r3 = 0;
        r3 = r0[r3];	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        r4 = "id";
        r3 = r3.equals(r4);	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
        if (r3 == 0) goto L_0x0035;
    L_0x022e:
        r3 = 1;
        r0 = r0[r3];	 Catch:{ NumberFormatException -> 0x0239, FileNotFoundException -> 0x006a, IOException -> 0x0080 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x0239, FileNotFoundException -> 0x006a, IOException -> 0x0080 }
        r10.p = r0;	 Catch:{ NumberFormatException -> 0x0239, FileNotFoundException -> 0x006a, IOException -> 0x0080 }
        goto L_0x0035;
    L_0x0239:
        r0 = move-exception;
        throw r0;	 Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0080, NumberFormatException -> 0x00ae }
    L_0x023b:
        r2.close();
        r10.a();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot2.template.a.<init>(android.content.Context, java.io.InputStream):void");
    }

    protected void a() {
        if (this.c.size() == 0) {
            this.l = 0;
        } else {
            this.l = ((Integer) this.c.get(this.c.size() - 1)).intValue();
        }
    }

    public int getDurationAtOrder(int i) {
        return ((Integer) this.c.get(i)).intValue() - ((Integer) this.b.get(i)).intValue();
    }

    public int size() {
        return this.k;
    }

    public List<Integer> getSegDurations() {
        return this.d;
    }

    public long getTotalDurations() {
        return (long) this.l;
    }

    public String getTemplateName() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public List<Integer> getStartTime() {
        return this.b;
    }

    public void a(List<Integer> list) {
        this.b = list;
    }

    public List<Integer> getEndTime() {
        return this.c;
    }

    public void b(List<Integer> list) {
        this.c = list;
    }

    public String getDescription() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public String getPreviewFileName() {
        return this.f;
    }

    public void c(String str) {
        this.f = str;
    }

    public String getPreviewMusicName() {
        return this.g;
    }

    public String getConcatMusicName() {
        return this.j;
    }

    public void d(String str) {
        this.g = str;
    }

    public Bitmap getThumbnailBitmap() {
        return this.h;
    }

    public void a(Bitmap bitmap) {
        this.h = bitmap;
    }

    public Bitmap b() {
        return this.m;
    }

    public void a(long j) {
        this.i = j;
    }

    public long c() {
        return this.i;
    }

    public int d() {
        return this.p;
    }

    public void b(long j) {
        this.n = j;
    }

    public long e() {
        return this.n;
    }

    public void a(int i) {
        this.p = i;
    }

    public Boolean a(String str, Context context) {
        String str2 = n.n(context) + str;
        if (str2 == null || new File(str2).exists()) {
            this.h = getAdjustThumbnail(str2, Config.RGB_565);
            if (this.h != null && this.h.getWidth() > 320) {
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(this.h, 320, (this.h.getHeight() * 320) / this.h.getWidth(), false);
                if (!(this.h == null || this.h.isRecycled())) {
                    this.h.recycle();
                }
                this.h = createScaledBitmap;
            }
            return Boolean.valueOf(true);
        }
        DJILogHelper.getInstance().LOGI("bob", "thumbnailPath is not exist");
        return Boolean.valueOf(false);
    }

    public Boolean b(Bitmap bitmap) {
        if (bitmap == null) {
            DJILogHelper.getInstance().LOGE("bob", "setThumbImage err image==null");
            return Boolean.valueOf(false);
        }
        if (!(this.h == null || this.h.isRecycled())) {
            this.h.recycle();
        }
        this.h = bitmap;
        if (this.h != null && this.h.getWidth() > 320) {
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(this.h, 320, (this.h.getHeight() * 320) / this.h.getWidth(), false);
            if (!(this.h == null || this.h.isRecycled())) {
                this.h.recycle();
            }
            this.h = createScaledBitmap;
        }
        return Boolean.valueOf(true);
    }

    public Boolean f() {
        return this.q;
    }

    public void a(Boolean bool) {
        this.q = bool;
    }
}
