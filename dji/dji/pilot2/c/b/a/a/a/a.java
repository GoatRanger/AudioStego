package dji.pilot2.c.b.a.a.a;

import android.media.ExifInterface;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.n;
import dji.pilot2.c.b.b;
import dji.pilot2.utils.k;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends b {
    private static final String d = "SkypixelPhotoUploadTask";
    public int a = 0;
    long b = 0;
    long c = 0;
    private String e;
    private boolean f;

    class AnonymousClass2 implements com.dji.videouploadsdk.a.c.a {
        final /* synthetic */ long a;
        final /* synthetic */ a b;

        AnonymousClass2(a aVar, long j) {
            this.b = aVar;
            this.a = j;
        }

        public void a(int i) {
            this.b.a((int) (99.0d * ((((double) this.b.c) + ((((double) i) * 0.01d) * ((double) this.a))) / ((double) this.b.b))));
        }

        public boolean a() {
            return this.b.o();
        }
    }

    private java.lang.String a(java.lang.String r16, java.util.Map<java.lang.String, java.lang.String> r17, java.lang.String r18) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:115:?
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.modifyBlocksTree(BlockProcessor.java:248)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.rerun(BlockProcessor.java:44)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:57)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r15 = this;
        r5 = new java.io.File;
        r0 = r18;
        r5.<init>(r0);
        r2 = r5.exists();
        if (r2 != 0) goto L_0x000f;
    L_0x000d:
        r2 = 0;
    L_0x000e:
        return r2;
    L_0x000f:
        r2 = java.util.UUID.randomUUID();
        r6 = r2.toString();
        r7 = "--";
        r8 = "\r\n";
        r3 = "multipart/form-data";
        r9 = "UTF-8";
        r2 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x00fa }
        r0 = r16;	 Catch:{ MalformedURLException -> 0x00fa }
        r2.<init>(r0);	 Catch:{ MalformedURLException -> 0x00fa }
        r2 = r2.openConnection();	 Catch:{ IOException -> 0x0101 }
        r2 = (javax.net.ssl.HttpsURLConnection) r2;	 Catch:{ IOException -> 0x0101 }
        if (r2 == 0) goto L_0x0118;
    L_0x002e:
        r4 = 3600000; // 0x36ee80 float:5.044674E-39 double:1.7786363E-317;
        r2.setReadTimeout(r4);
        r4 = 1;
        r2.setDoInput(r4);
        r4 = 1;
        r2.setDoOutput(r4);
        r4 = 0;
        r2.setUseCaches(r4);
        r4 = "POST";	 Catch:{ ProtocolException -> 0x0108, all -> 0x0113 }
        r2.setRequestMethod(r4);	 Catch:{ ProtocolException -> 0x0108, all -> 0x0113 }
        r2.disconnect();
        r4 = "connection";
        r10 = "keepxml-alive";
        r2.setRequestProperty(r4, r10);
        r4 = "Charsert";
        r10 = "UTF-8";
        r2.setRequestProperty(r4, r10);
        r4 = "Content-Type";
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r3 = r10.append(r3);
        r10 = ";boundary=";
        r3 = r3.append(r10);
        r3 = r3.append(r6);
        r3 = r3.toString();
        r2.setRequestProperty(r4, r3);
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r3 = r17.entrySet();
        r11 = r3.iterator();
    L_0x007f:
        r3 = r11.hasNext();
        if (r3 == 0) goto L_0x011b;
    L_0x0085:
        r3 = r11.next();
        r3 = (java.util.Map.Entry) r3;
        r10.append(r7);
        r10.append(r6);
        r10.append(r8);
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r12 = "Content-Disposition: form-data; name=\"";
        r12 = r4.append(r12);
        r4 = r3.getKey();
        r4 = (java.lang.String) r4;
        r4 = r12.append(r4);
        r12 = "\"";
        r4 = r4.append(r12);
        r4 = r4.append(r8);
        r4 = r4.toString();
        r10.append(r4);
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r12 = "Content-Type: text/plain; charset=";
        r4 = r4.append(r12);
        r4 = r4.append(r9);
        r4 = r4.append(r8);
        r4 = r4.toString();
        r10.append(r4);
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r12 = "Content-Transfer-Encoding: 8bit";
        r4 = r4.append(r12);
        r4 = r4.append(r8);
        r4 = r4.toString();
        r10.append(r4);
        r10.append(r8);
        r3 = r3.getValue();
        r3 = (java.lang.String) r3;
        r10.append(r3);
        r10.append(r8);
        goto L_0x007f;
    L_0x00fa:
        r2 = move-exception;
        r2.printStackTrace();
        r2 = 0;
        goto L_0x000e;
    L_0x0101:
        r2 = move-exception;
        r2.printStackTrace();
        r2 = 0;
        goto L_0x000e;
    L_0x0108:
        r3 = move-exception;
        r3.printStackTrace();	 Catch:{ ProtocolException -> 0x0108, all -> 0x0113 }
        r3 = 0;
        r2.disconnect();
        r2 = r3;
        goto L_0x000e;
    L_0x0113:
        r3 = move-exception;
        r2.disconnect();
        throw r3;
    L_0x0118:
        r2 = 0;
        goto L_0x000e;
    L_0x011b:
        r4 = 0;
        r11 = new java.io.DataOutputStream;	 Catch:{ IOException -> 0x0284, all -> 0x0281 }
        r3 = r2.getOutputStream();	 Catch:{ IOException -> 0x0284, all -> 0x0281 }
        r11.<init>(r3);	 Catch:{ IOException -> 0x0284, all -> 0x0281 }
        r3 = new com.dji.videouploadsdk.a.c;	 Catch:{ IOException -> 0x0284, all -> 0x0281 }
        r3.<init>(r11);	 Catch:{ IOException -> 0x0284, all -> 0x0281 }
        r4 = r10.toString();	 Catch:{ IOException -> 0x01bf }
        r4 = r4.getBytes();	 Catch:{ IOException -> 0x01bf }
        r3.write(r4);	 Catch:{ IOException -> 0x01bf }
        r3.flush();	 Catch:{ IOException -> 0x01bf }
        r4 = r5.exists();	 Catch:{ IOException -> 0x01bf }
        if (r4 == 0) goto L_0x018d;	 Catch:{ IOException -> 0x01bf }
    L_0x013e:
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x01bf }
        r4.<init>();	 Catch:{ IOException -> 0x01bf }
        r4.append(r7);	 Catch:{ IOException -> 0x01bf }
        r4.append(r6);	 Catch:{ IOException -> 0x01bf }
        r4.append(r8);	 Catch:{ IOException -> 0x01bf }
        r10 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x01bf }
        r10.<init>();	 Catch:{ IOException -> 0x01bf }
        r11 = "Content-Disposition: form-data; name=\"picture\";filename=\"photo.jpg\"";	 Catch:{ IOException -> 0x01bf }
        r10 = r10.append(r11);	 Catch:{ IOException -> 0x01bf }
        r10 = r10.append(r8);	 Catch:{ IOException -> 0x01bf }
        r10 = r10.toString();	 Catch:{ IOException -> 0x01bf }
        r4.append(r10);	 Catch:{ IOException -> 0x01bf }
        r10 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x01bf }
        r10.<init>();	 Catch:{ IOException -> 0x01bf }
        r11 = "Content-Type: image/jpeg; charset=";	 Catch:{ IOException -> 0x01bf }
        r10 = r10.append(r11);	 Catch:{ IOException -> 0x01bf }
        r9 = r10.append(r9);	 Catch:{ IOException -> 0x01bf }
        r9 = r9.append(r8);	 Catch:{ IOException -> 0x01bf }
        r9 = r9.toString();	 Catch:{ IOException -> 0x01bf }
        r4.append(r9);	 Catch:{ IOException -> 0x01bf }
        r4.append(r8);	 Catch:{ IOException -> 0x01bf }
        r4 = r4.toString();	 Catch:{ IOException -> 0x01bf }
        r4 = r4.getBytes();	 Catch:{ IOException -> 0x01bf }
        r3.write(r4);	 Catch:{ IOException -> 0x01bf }
        r3.flush();	 Catch:{ IOException -> 0x01bf }
    L_0x018d:
        r4 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x01bf }
        r4.<init>(r5);	 Catch:{ IOException -> 0x01bf }
        r10 = r5.length();	 Catch:{ IOException -> 0x01bf }
        r15.b = r10;	 Catch:{ IOException -> 0x01bf }
        r5 = 102400; // 0x19000 float:1.43493E-40 double:5.05923E-319;	 Catch:{ IOException -> 0x01bf }
        r5 = new byte[r5];	 Catch:{ IOException -> 0x01bf }
    L_0x019d:
        r9 = 0;	 Catch:{ IOException -> 0x01bf }
        r10 = 102400; // 0x19000 float:1.43493E-40 double:5.05923E-319;	 Catch:{ IOException -> 0x01bf }
        r9 = r4.read(r5, r9, r10);	 Catch:{ IOException -> 0x01bf }
        r10 = -1;	 Catch:{ IOException -> 0x01bf }
        if (r9 == r10) goto L_0x0206;	 Catch:{ IOException -> 0x01bf }
    L_0x01a8:
        r10 = (long) r9;	 Catch:{ IOException -> 0x01bf }
        r12 = new dji.pilot2.c.b.a.a.a.a$2;	 Catch:{ IOException -> 0x01bf }
        r12.<init>(r15, r10);	 Catch:{ IOException -> 0x01bf }
        r3.a(r12);	 Catch:{ IOException -> 0x01bf }
        r10 = 0;	 Catch:{ IOException -> 0x01bf }
        r3.write(r5, r10, r9);	 Catch:{ IOException -> 0x01bf }
        r3.flush();	 Catch:{ IOException -> 0x01bf }
        r10 = r15.c;	 Catch:{ IOException -> 0x01bf }
        r12 = (long) r9;	 Catch:{ IOException -> 0x01bf }
        r10 = r10 + r12;	 Catch:{ IOException -> 0x01bf }
        r15.c = r10;	 Catch:{ IOException -> 0x01bf }
        goto L_0x019d;
    L_0x01bf:
        r4 = move-exception;
    L_0x01c0:
        r4.printStackTrace();	 Catch:{ IOException -> 0x0248, all -> 0x024e }
        r2.disconnect();	 Catch:{ IOException -> 0x0248, all -> 0x024e }
        if (r3 == 0) goto L_0x01cb;
    L_0x01c8:
        r3.close();	 Catch:{ IOException -> 0x0248, all -> 0x024e }
    L_0x01cb:
        if (r3 == 0) goto L_0x01d1;
    L_0x01cd:
        r4 = 0;
        r3.a(r4);
    L_0x01d1:
        r6 = 0;
        r4 = r2.getResponseCode();	 Catch:{ IOException -> 0x027d }
        r7 = r2.getInputStream();	 Catch:{ IOException -> 0x027d }
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x027d }
        r5.<init>();	 Catch:{ IOException -> 0x027d }
        r6 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r4 != r6) goto L_0x0256;
    L_0x01e3:
        r4 = r7.read();	 Catch:{ IOException -> 0x01ef }
        r6 = -1;	 Catch:{ IOException -> 0x01ef }
        if (r4 == r6) goto L_0x0258;	 Catch:{ IOException -> 0x01ef }
    L_0x01ea:
        r4 = (char) r4;	 Catch:{ IOException -> 0x01ef }
        r5.append(r4);	 Catch:{ IOException -> 0x01ef }
        goto L_0x01e3;
    L_0x01ef:
        r4 = move-exception;
    L_0x01f0:
        r4.printStackTrace();	 Catch:{ all -> 0x026e }
        r4 = -2;	 Catch:{ all -> 0x026e }
        r15.a = r4;	 Catch:{ all -> 0x026e }
        r2.disconnect();	 Catch:{ all -> 0x026e }
        if (r3 == 0) goto L_0x01fe;
    L_0x01fb:
        r3.close();	 Catch:{ IOException -> 0x0269 }
    L_0x01fe:
        if (r5 == 0) goto L_0x027a;
    L_0x0200:
        r2 = r5.toString();
        goto L_0x000e;
    L_0x0206:
        r4.close();	 Catch:{ IOException -> 0x01bf }
        if (r3 == 0) goto L_0x023c;	 Catch:{ IOException -> 0x01bf }
    L_0x020b:
        r4 = 0;	 Catch:{ IOException -> 0x01bf }
        r3.a(r4);	 Catch:{ IOException -> 0x01bf }
        r4 = r8.getBytes();	 Catch:{ IOException -> 0x01bf }
        r3.write(r4);	 Catch:{ IOException -> 0x01bf }
        r3.flush();	 Catch:{ IOException -> 0x01bf }
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x01bf }
        r4.<init>();	 Catch:{ IOException -> 0x01bf }
        r4 = r4.append(r7);	 Catch:{ IOException -> 0x01bf }
        r4 = r4.append(r6);	 Catch:{ IOException -> 0x01bf }
        r4 = r4.append(r7);	 Catch:{ IOException -> 0x01bf }
        r4 = r4.append(r8);	 Catch:{ IOException -> 0x01bf }
        r4 = r4.toString();	 Catch:{ IOException -> 0x01bf }
        r4 = r4.getBytes();	 Catch:{ IOException -> 0x01bf }
        r3.write(r4);	 Catch:{ IOException -> 0x01bf }
        r3.flush();	 Catch:{ IOException -> 0x01bf }
    L_0x023c:
        r4 = 100;	 Catch:{ IOException -> 0x01bf }
        r15.a(r4);	 Catch:{ IOException -> 0x01bf }
        if (r3 == 0) goto L_0x01d1;
    L_0x0243:
        r4 = 0;
        r3.a(r4);
        goto L_0x01d1;
    L_0x0248:
        r4 = move-exception;
        r4.printStackTrace();	 Catch:{ IOException -> 0x0248, all -> 0x024e }
        goto L_0x01cb;
    L_0x024e:
        r2 = move-exception;
    L_0x024f:
        if (r3 == 0) goto L_0x0255;
    L_0x0251:
        r4 = 0;
        r3.a(r4);
    L_0x0255:
        throw r2;
    L_0x0256:
        r15.a = r4;	 Catch:{ IOException -> 0x01ef }
    L_0x0258:
        r3.close();	 Catch:{ IOException -> 0x01ef }
        r2.disconnect();	 Catch:{ IOException -> 0x01ef }
        if (r3 == 0) goto L_0x01fe;
    L_0x0260:
        r3.close();	 Catch:{ IOException -> 0x0264 }
        goto L_0x01fe;
    L_0x0264:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x01fe;
    L_0x0269:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x01fe;
    L_0x026e:
        r2 = move-exception;
        if (r3 == 0) goto L_0x0274;
    L_0x0271:
        r3.close();	 Catch:{ IOException -> 0x0275 }
    L_0x0274:
        throw r2;
    L_0x0275:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0274;
    L_0x027a:
        r2 = 0;
        goto L_0x000e;
    L_0x027d:
        r4 = move-exception;
        r5 = r6;
        goto L_0x01f0;
    L_0x0281:
        r2 = move-exception;
        r3 = r4;
        goto L_0x024f;
    L_0x0284:
        r3 = move-exception;
        r14 = r3;
        r3 = r4;
        r4 = r14;
        goto L_0x01c0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot2.c.b.a.a.a.a.a(java.lang.String, java.util.Map, java.lang.String):java.lang.String");
    }

    public a(String str, String str2, String str3, String str4) {
        super(str, str2, str3);
        this.e = str4;
        if (this.e == null) {
            this.e = "";
        }
        this.f = false;
    }

    protected void b() {
        d();
        new Thread(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                String a = this.a.a(this.a.i(), this.a.j(), this.a.k());
                if (a == null || a.equals("")) {
                    this.a.e();
                    return;
                }
                Map hashMap = new HashMap();
                hashMap.put("flag", "dji_pilot");
                hashMap.put("type", "photo");
                hashMap.put("picture.name", "123.jpg");
                hashMap.put("info", a);
                a = this.a.a(k.u(), hashMap, this.a.i());
                if (!this.a.o()) {
                    if (a == null || a.equals("")) {
                        this.a.e();
                        return;
                    }
                    try {
                        JSONObject jSONObject = new JSONObject(a);
                        if (jSONObject.getInt("retcode") == 0 && jSONObject.getString("message").equalsIgnoreCase("success")) {
                            this.a.a(jSONObject.getString(dji.pilot2.share.mode.a.q));
                            return;
                        }
                        this.a.e();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        this.a.e();
                    }
                }
            }
        }).start();
    }

    protected void c() {
        this.f = true;
    }

    private String a(String str, String str2, String str3) {
        ExifInterface exifInterface;
        try {
            exifInterface = new ExifInterface(str);
        } catch (IOException e) {
            e.printStackTrace();
            exifInterface = null;
        }
        if (exifInterface == null) {
            return null;
        }
        String attribute = exifInterface.getAttribute(dji.pilot2.utils.a.a.m);
        Object attribute2 = exifInterface.getAttribute("FNumber");
        Object attribute3 = exifInterface.getAttribute("WhiteBalance");
        Object attribute4 = exifInterface.getAttribute("ISOSpeedRatings");
        if (attribute == null) {
            attribute = "unknow";
        }
        if (attribute4 == null) {
            attribute4 = "0";
        }
        if (attribute2 == null) {
            attribute2 = "0";
        }
        if (attribute3 == null) {
            attribute3 = "0";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(n.y, exifInterface.getAttributeDouble("GPSLongitude", 0.0d));
            jSONObject2.put(n.x, exifInterface.getAttributeDouble("GPSAltitude", 0.0d));
            jSONObject.put("access_token", f.getInstance().n());
            jSONObject.put("photo_title", str2);
            jSONObject.put("photo_desc", str3);
            jSONObject.put("drones", dji.pilot2.share.f.b.a(dji.pilot2.b.a.a(), attribute));
            jSONObject.put("positions", jSONObject2);
            jSONObject.put("iso", attribute4);
            jSONObject.put("aperture", attribute2);
            jSONObject.put(dji.publics.b.a.b.l, attribute3);
            jSONObject.put(dji.publics.b.a.b.m, "0");
            if (this.e != null) {
                jSONObject.put("photo_tag_list", this.e);
            }
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private boolean o() {
        return this.f;
    }

    public int a() {
        return this.a;
    }
}
