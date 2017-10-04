package dji.pilot2.mine.e;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.text.format.DateFormat;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.mine.b.c;
import dji.pilot2.mine.db.DraftBean;
import dji.pilot2.utils.g;
import dji.pilot2.utils.m;
import java.io.File;

public class b {
    public static final String a = "video";
    public static final String b = "photo";
    private String c;
    private String d;
    private long e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private int m;
    private int n;

    public static class a {
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 4;
        public static final int d = 8;
        public static final int e = 16;
        public static final int f = 32;
    }

    public b(String str, String str2, String str3, String str4, String str5, String str6) {
        this.i = str;
        this.c = str2;
        this.f = str3;
        this.g = str4;
        this.h = str5;
        this.m = 1;
        this.n = 0;
        this.j = str6;
        this.k = "";
        this.l = "";
    }

    public b(String str, String str2, String str3, String str4, String str5) {
        this.i = str;
        this.c = str2;
        this.f = str3;
        this.g = str4;
        this.h = str5;
        this.m = 1;
        this.n = 0;
        this.k = "";
        this.l = "";
    }

    public b(DraftBean draftBean) {
        this.i = draftBean.getUserEmail();
        this.c = draftBean.getFilePath();
        this.f = draftBean.getTitle();
        this.g = draftBean.getDescription();
        this.m = draftBean.getStatus();
        this.h = draftBean.getType();
        this.n = 0;
        this.j = draftBean.getThumbnailPath();
        this.k = draftBean.getShareUrl();
        this.d = draftBean.getDuration();
        this.l = draftBean.getTag();
        try {
            this.e = Long.valueOf(draftBean.getCreateTime()).longValue();
        } catch (NumberFormatException e) {
            this.e = 0;
        }
    }

    public String a() {
        return this.i;
    }

    public void a(String str) {
        this.i = str;
    }

    public String b() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public String c() {
        return this.g;
    }

    public void c(String str) {
        this.g = str;
    }

    public String d() {
        return this.h;
    }

    public void d(String str) {
        this.h = str;
    }

    public String e() {
        return this.c;
    }

    public void e(String str) {
        this.c = str;
    }

    public String f() {
        return this.k;
    }

    public void f(String str) {
        this.k = str;
        DraftBean c = c.getInstance().c(e());
        if (c != null) {
            c.setShareUrl(str);
            com.dji.frame.c.c.c(dji.pilot2.b.a.a()).e(c);
        }
    }

    public int g() {
        return this.m;
    }

    public void a(int i) {
        this.m = i;
        DraftBean c = c.getInstance().c(e());
        if (c != null && i != 2 && i != 32) {
            c.setStatus(i);
            com.dji.frame.c.c.c(dji.pilot2.b.a.a()).e(c);
        }
    }

    public int h() {
        return this.n;
    }

    public void b(int i) {
        this.n = i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap i() {
        /*
        r4 = this;
        r0 = 0;
        r1 = r4.d();
        r2 = "photo";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x001c;
    L_0x000d:
        r0 = new android.graphics.BitmapFactory$Options;
        r0.<init>();
        r1 = 4;
        r0.inSampleSize = r1;
        r1 = r4.c;
        r0 = android.graphics.BitmapFactory.decodeFile(r1, r0);
    L_0x001b:
        return r0;
    L_0x001c:
        r1 = r4.d();
        r2 = "video";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x001b;
    L_0x0028:
        r2 = new android.media.MediaMetadataRetriever;
        r2.<init>();
        r1 = r4.c;	 Catch:{ IllegalArgumentException -> 0x0052, Exception -> 0x005c }
        r2.setDataSource(r1);	 Catch:{ IllegalArgumentException -> 0x0052, Exception -> 0x005c }
        r1 = r2.getFrameAtTime();	 Catch:{ IllegalArgumentException -> 0x0052, Exception -> 0x005c }
        r2.release();	 Catch:{ RuntimeException -> 0x006d }
    L_0x0039:
        if (r1 == 0) goto L_0x001b;
    L_0x003b:
        r0 = r1.getWidth();
        r2 = r1.getHeight();
        r3 = 320; // 0x140 float:4.48E-43 double:1.58E-321;
        r2 = r2 * 320;
        r0 = r2 / r0;
        r2 = 0;
        r0 = android.graphics.Bitmap.createScaledBitmap(r1, r3, r0, r2);
        r1.recycle();
        goto L_0x001b;
    L_0x0052:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x0068 }
        r2.release();	 Catch:{ RuntimeException -> 0x005a }
        goto L_0x001b;
    L_0x005a:
        r1 = move-exception;
        goto L_0x001b;
    L_0x005c:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x0068 }
        r2.release();	 Catch:{ RuntimeException -> 0x0065 }
        r1 = r0;
        goto L_0x0039;
    L_0x0065:
        r1 = move-exception;
        r1 = r0;
        goto L_0x0039;
    L_0x0068:
        r0 = move-exception;
        r2.release();	 Catch:{ RuntimeException -> 0x006f }
    L_0x006c:
        throw r0;
    L_0x006d:
        r2 = move-exception;
        goto L_0x0039;
    L_0x006f:
        r1 = move-exception;
        goto L_0x006c;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot2.mine.e.b.i():android.graphics.Bitmap");
    }

    public String j() {
        return DateFormat.format("MM/dd/yy HH:mm", this.e).toString();
    }

    public long k() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }

    public void g(String str) {
        this.d = str;
    }

    public String l() {
        String extractMetadata;
        long j;
        if (!d().equals("video")) {
            return "";
        }
        if (this.d != null) {
            return this.d;
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(this.c);
            extractMetadata = mediaMetadataRetriever.extractMetadata(9);
        } catch (IllegalArgumentException e) {
            extractMetadata = e;
            extractMetadata.printStackTrace();
            extractMetadata = null;
            if (extractMetadata != null) {
                j = 0;
            } else {
                try {
                    j = Long.parseLong(extractMetadata) + 500;
                } catch (NumberFormatException e2) {
                    this.d = m.a(0);
                }
            }
            this.d = m.a(j);
            return this.d;
        } catch (RuntimeException e3) {
            extractMetadata = e3;
            extractMetadata.printStackTrace();
            extractMetadata = null;
            if (extractMetadata != null) {
                j = Long.parseLong(extractMetadata) + 500;
            } else {
                j = 0;
            }
            this.d = m.a(j);
            return this.d;
        } finally {
            mediaMetadataRetriever.release();
        }
        if (extractMetadata != null) {
            j = Long.parseLong(extractMetadata) + 500;
        } else {
            j = 0;
        }
        this.d = m.a(j);
        return this.d;
    }

    public boolean m() {
        return this.d == null;
    }

    public String n() {
        return this.j;
    }

    public void h(String str) {
        this.j = str;
    }

    public String o() {
        if (this.k == null) {
            return "";
        }
        return this.k.substring(this.k.lastIndexOf(d.t) + 1);
    }

    public String p() {
        return this.l;
    }

    public void i(String str) {
        this.l = str;
    }

    public void q() {
        g.f(this.c);
    }

    public void r() {
        g.g(this.j);
    }

    public void s() {
        if (this.c != null) {
            g.f(this.c + ".info");
        }
    }

    public void a(Context context) {
        if (this.c != null) {
            File file = new File(context.getFilesDir(), new File(this.c).getName().split("\\.")[0]);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
