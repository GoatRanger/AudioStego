package dji.pilot2.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.usercenter.f.f;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.DJIActivity;
import java.util.ArrayList;

class j implements Runnable {
    public static int b = 4;
    ArrayList<Integer> a;
    private Context c;
    private int d = 0;
    private int e = 0;
    private RelativeLayout f;
    private String g;
    private ArrayList<Bitmap> h;

    j(int i, int i2, String str, ArrayList<Bitmap> arrayList) {
        this.d = i;
        this.e = i2;
        this.g = str;
        this.h = arrayList;
        DJILogHelper.getInstance().LOGD("bitmap", "MyThreadFFmpeg mTimeIndex = " + this.d + "mTotalTime" + this.e);
    }

    j(Context context, int i, String str, ArrayList<Integer> arrayList, RelativeLayout relativeLayout) {
        this.c = context;
        this.d = i;
        this.g = str;
        this.a = arrayList;
        this.f = relativeLayout;
        DJILogHelper.getInstance().LOGD("bitmap", "MyThreadFFmpeg mTimeIndex = " + this.d + "mTotalTime" + this.e);
    }

    public void run() {
        int i;
        OutOfMemoryError e;
        Exception e2;
        int i2;
        int i3 = 0;
        System.currentTimeMillis();
        int size = this.a.size();
        int i4 = 0;
        while (i3 < size) {
            int i5;
            try {
                Bitmap a = f.a(this.g, (((long) ((Integer) this.a.get(i3)).intValue()) * 1000) * 1000);
                if (a != null) {
                    final ImageView imageView = (ImageView) this.f.getChildAt(((q.f * i3) + this.d) + 1).findViewById(R.id.bl8);
                    final Bitmap a2 = p.a(a, d.k, 63);
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(this.c.getResources(), a);
                    ((DJIActivity) this.c).runOnUiThread(new Runnable(this) {
                        final /* synthetic */ j c;

                        public void run() {
                            if (imageView != null) {
                                imageView.setImageBitmap(a2);
                            }
                        }
                    });
                    i = i3;
                    i5 = i4;
                } else {
                    this.a.add(i3, Integer.valueOf(((Integer) this.a.remove(i3)).intValue() + 1));
                    i = i3 - 1;
                    i5 = i4 + 1;
                    try {
                        DJILogHelper.getInstance().LOGD(q.a, "thumb ffmpeg utils get null, errCount: " + i5);
                    } catch (OutOfMemoryError e3) {
                        e = e3;
                        e.printStackTrace();
                        DJILogHelper.getInstance().LOGD(q.a, "thumb child at: " + (((q.f * i) + this.d) + 1) + "thread index: " + this.d);
                        i3 = i + 1;
                        i4 = i5;
                    } catch (Exception e4) {
                        e2 = e4;
                        e2.printStackTrace();
                        DJILogHelper.getInstance().LOGD(q.a, "thumb child at: " + (((q.f * i) + this.d) + 1) + "thread index: " + this.d);
                        i3 = i + 1;
                        i4 = i5;
                    }
                }
            } catch (OutOfMemoryError e5) {
                i5 = i4;
                i2 = i3;
                e = e5;
                i = i2;
                e.printStackTrace();
                DJILogHelper.getInstance().LOGD(q.a, "thumb child at: " + (((q.f * i) + this.d) + 1) + "thread index: " + this.d);
                i3 = i + 1;
                i4 = i5;
            } catch (Exception e6) {
                i5 = i4;
                i2 = i3;
                e2 = e6;
                i = i2;
                e2.printStackTrace();
                DJILogHelper.getInstance().LOGD(q.a, "thumb child at: " + (((q.f * i) + this.d) + 1) + "thread index: " + this.d);
                i3 = i + 1;
                i4 = i5;
            }
            if (q.e || i5 > 3) {
                break;
            }
            DJILogHelper.getInstance().LOGD(q.a, "thumb child at: " + (((q.f * i) + this.d) + 1) + "thread index: " + this.d);
            i3 = i + 1;
            i4 = i5;
        }
        q.d.incrementAndGet();
        System.currentTimeMillis();
        DJILogHelper.getInstance().LOGD("bitmap", "MyThreadFFmpeg timeIndex = " + this.d);
    }
}
