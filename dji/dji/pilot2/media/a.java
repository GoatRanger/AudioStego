package dji.pilot2.media;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import dji.midware.media.e;

public class a implements g {
    private static final String a = "AndroidNativeMediaRetriever";
    private MediaMetadataRetriever b = new MediaMetadataRetriever();

    public void a(String str) {
        this.b.setDataSource(str);
    }

    public Bitmap a(long j) {
        return this.b.getFrameAtTime(j);
    }

    public Bitmap a(long j, int i) {
        return this.b.getFrameAtTime(j, i);
    }

    public Bitmap[] a(long[] jArr, int i) {
        Bitmap[] bitmapArr = new Bitmap[jArr.length];
        for (int i2 = 0; i2 < bitmapArr.length; i2++) {
            bitmapArr[i2] = a(jArr[i2], i);
        }
        return bitmapArr;
    }

    public int a() {
        int i = 0;
        try {
            i = Integer.parseInt(this.b.extractMetadata(9));
        } catch (Exception e) {
            e.b(a, "can't recoginize Duration");
        }
        return i;
    }

    public int b() {
        int i = 0;
        try {
            i = Integer.parseInt(this.b.extractMetadata(18));
        } catch (Exception e) {
            e.b(a, "can't recoginize VideoWidth");
        }
        return i;
    }

    public int c() {
        int i = 0;
        try {
            i = Integer.parseInt(this.b.extractMetadata(19));
        } catch (Exception e) {
            e.b(a, "can't recoginize VideoHeight");
        }
        return i;
    }

    public boolean d() {
        if (this.b.extractMetadata(17) != null) {
            return true;
        }
        return false;
    }
}
