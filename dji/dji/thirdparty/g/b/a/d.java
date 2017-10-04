package dji.thirdparty.g.b.a;

import dji.thirdparty.g.a.c;
import dji.thirdparty.g.e;
import dji.thirdparty.g.f;
import java.io.IOException;
import java.io.InputStream;

public class d extends c implements a {

    public interface a {
        void a(int i, byte[] bArr, byte[] bArr2);

        boolean a();

        boolean a(int i, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) throws e, IOException;

        boolean a(int i, byte[] bArr, InputStream inputStream);
    }

    public d() {
        a(77);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(dji.thirdparty.g.a.a.a r12, dji.thirdparty.g.b.a.d.a r13) throws dji.thirdparty.g.e, java.io.IOException {
        /*
        r11 = this;
        r6 = 0;
        r1 = 0;
        r7 = r12.a();	 Catch:{ all -> 0x0081 }
        r0 = ft_;	 Catch:{ all -> 0x008d }
        r1 = "Not a Valid JPEG File: doesn't begin with 0xffd8";
        r11.a(r7, r0, r1);	 Catch:{ all -> 0x008d }
        r9 = r11.g();	 Catch:{ all -> 0x008d }
        r8 = r6;
    L_0x0012:
        r0 = "markerBytes";
        r1 = 2;
        r2 = "markerBytes";
        r2 = r11.a(r0, r1, r7, r2);	 Catch:{ all -> 0x008d }
        r0 = "marker";
        r1 = r11.c(r0, r2, r9);	 Catch:{ all -> 0x008d }
        r0 = 65497; // 0xffd9 float:9.1781E-41 double:3.236E-319;
        if (r1 == r0) goto L_0x002b;
    L_0x0026:
        r0 = 65498; // 0xffda float:9.1782E-41 double:3.23603E-319;
        if (r1 != r0) goto L_0x0052;
    L_0x002b:
        r0 = r13.a();	 Catch:{ all -> 0x008d }
        if (r0 != 0) goto L_0x003c;
    L_0x0031:
        if (r7 == 0) goto L_0x0036;
    L_0x0033:
        r7.close();	 Catch:{ Exception -> 0x0037 }
    L_0x0036:
        return;
    L_0x0037:
        r0 = move-exception;
        dji.thirdparty.g.c.c.a(r0);
        goto L_0x0036;
    L_0x003c:
        r0 = r13.a(r1, r2, r7);	 Catch:{ all -> 0x008d }
        if (r0 != 0) goto L_0x0050;
    L_0x0042:
        r0 = 1;
    L_0x0043:
        if (r7 == 0) goto L_0x0036;
    L_0x0045:
        if (r0 == 0) goto L_0x0036;
    L_0x0047:
        r7.close();	 Catch:{ Exception -> 0x004b }
        goto L_0x0036;
    L_0x004b:
        r0 = move-exception;
        dji.thirdparty.g.c.c.a(r0);
        goto L_0x0036;
    L_0x0050:
        r0 = r6;
        goto L_0x0043;
    L_0x0052:
        r0 = "segmentLengthBytes";
        r3 = 2;
        r4 = "segmentLengthBytes";
        r4 = r11.a(r0, r3, r7, r4);	 Catch:{ all -> 0x008d }
        r0 = "segmentLength";
        r3 = r11.c(r0, r4, r9);	 Catch:{ all -> 0x008d }
        r0 = "Segment Data";
        r5 = r3 + -2;
        r10 = "Invalid Segment: insufficient data";
        r5 = r11.a(r0, r5, r7, r10);	 Catch:{ all -> 0x008d }
        r0 = r13;
        r0 = r0.a(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x008d }
        if (r0 != 0) goto L_0x007d;
    L_0x0072:
        if (r7 == 0) goto L_0x0036;
    L_0x0074:
        r7.close();	 Catch:{ Exception -> 0x0078 }
        goto L_0x0036;
    L_0x0078:
        r0 = move-exception;
        dji.thirdparty.g.c.c.a(r0);
        goto L_0x0036;
    L_0x007d:
        r0 = r8 + 1;
        r8 = r0;
        goto L_0x0012;
    L_0x0081:
        r0 = move-exception;
    L_0x0082:
        if (r1 == 0) goto L_0x0087;
    L_0x0084:
        r1.close();	 Catch:{ Exception -> 0x0088 }
    L_0x0087:
        throw r0;
    L_0x0088:
        r1 = move-exception;
        dji.thirdparty.g.c.c.a(r1);
        goto L_0x0087;
    L_0x008d:
        r0 = move-exception;
        r1 = r7;
        goto L_0x0082;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.g.b.a.d.a(dji.thirdparty.g.a.a.a, dji.thirdparty.g.b.a.d$a):void");
    }

    public static String c(int i) {
        switch (i) {
            case a.q /*65472*/:
                return "SOF0Marker";
            case a.r /*65473*/:
                return "SOF1Marker";
            case a.s /*65474*/:
                return "SOF2Marker";
            case a.t /*65475*/:
                return "SOF3Marker";
            case a.u /*65476*/:
                return "SOF4Marker";
            case a.v /*65477*/:
                return "SOF5Marker";
            case a.w /*65478*/:
                return "SOF6Marker";
            case a.x /*65479*/:
                return "SOF7Marker";
            case a.y /*65480*/:
                return "SOF8Marker";
            case a.z /*65481*/:
                return "SOF9Marker";
            case a.A /*65482*/:
                return "SOF10Marker";
            case a.B /*65483*/:
                return "SOF11Marker";
            case a.C /*65484*/:
                return "SOF12Marker";
            case a.D /*65485*/:
                return "SOF13Marker";
            case a.E /*65486*/:
                return "SOF14Marker";
            case a.F /*65487*/:
                return "SOF15Marker";
            case a.fv_ /*65498*/:
                return "SOS_Marker";
            case 65504:
                return "JFIFMarker";
            case a.k /*65505*/:
                return "JPEG_APP1_Marker";
            case a.l /*65506*/:
                return "JPEG_APP2_Marker";
            case a.m /*65517*/:
                return "JPEG_APP13_Marker";
            case a.n /*65518*/:
                return "JPEG_APP14_Marker";
            case a.o /*65519*/:
                return "JPEG_APP15_Marker";
            default:
                return "Unknown";
        }
    }

    public void a(dji.thirdparty.g.a.a.a aVar) throws e, IOException, f {
        a(aVar, new a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public boolean a() {
                return true;
            }

            public void a(int i, byte[] bArr, byte[] bArr2) {
                dji.thirdparty.g.c.c.a("SOS marker.  " + bArr2.length + " bytes of image data.");
                dji.thirdparty.g.c.c.a("");
            }

            public boolean a(int i, byte[] bArr, InputStream inputStream) {
                return false;
            }

            public boolean a(int i, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) {
                dji.thirdparty.g.c.c.a("Segment marker: " + Integer.toHexString(i) + " (" + d.c(i) + "), " + bArr3.length + " bytes of segment data.");
                return true;
            }
        });
    }
}
