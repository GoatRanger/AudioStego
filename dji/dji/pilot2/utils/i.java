package dji.pilot2.utils;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.pilot.usercenter.protocol.d;
import java.util.ArrayList;

class i implements Runnable {
    public static int a = 4;
    private int b = 0;
    private int c = 0;
    private String d;
    private ArrayList<Bitmap> e;

    i(int i, int i2, String str, ArrayList<Bitmap> arrayList) {
        this.b = i;
        this.c = i2;
        this.d = str;
        this.e = arrayList;
        DJILogHelper.getInstance().LOGD("bitmap", "MyThread mTimeIndex = " + this.b + "    mTotalTime" + this.c);
    }

    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(this.d);
        int i = (int) (((float) this.c) * (((float) this.b) / ((float) a)));
        if (i % 10 != 0) {
            i++;
        }
        int i2 = (int) (((float) this.c) * ((((float) this.b) + 1.0f) / ((float) a)));
        for (int i3 = i; i3 < i2; i3 += 10) {
            try {
                DJILogHelper.getInstance().LOGD("bitmap", "MyThread timeIndex = " + this.b + " start = " + i3 + " end = " + i2);
                Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime((((long) i3) * 1000) * 1000, 2);
                if (frameAtTime != null) {
                    this.e.add(p.a(frameAtTime, d.k, 63));
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        q.d.incrementAndGet();
        mediaMetadataRetriever.release();
        DJILogHelper.getInstance().LOGD("bitmap", "MyThread timeIndex = " + this.b + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + (((float) (System.currentTimeMillis() - currentTimeMillis)) / 1000.0f));
    }
}
