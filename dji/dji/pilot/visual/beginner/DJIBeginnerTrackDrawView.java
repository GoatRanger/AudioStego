package dji.pilot.visual.beginner;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.R;
import dji.pilot.publics.objects.k;
import dji.pilot.publics.objects.k.a;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;

public class DJIBeginnerTrackDrawView extends DJIRelativeLayout implements a {
    private static final int a = 4096;
    private static final long b = 70;
    private static final long c = 1000;
    private static final int d = 30;
    private static final int e = 100;
    private static final int f = 5;
    private DJIImageView g = null;
    private DJIImageView h = null;
    private int i = 100;
    private int j = 0;
    private int k = 0;
    private k l = null;
    private Callback m = null;

    public DJIBeginnerTrackDrawView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void show() {
        if (getVisibility() != 0) {
            setVisibility(0);
            this.i = 100;
            a();
            this.l.sendMessageDelayed(this.l.obtainMessage(4096, 1, 0), b);
        }
    }

    public void go() {
        if (getVisibility() != 8) {
            this.l.removeMessages(4096);
            setVisibility(8);
        }
    }

    private void a() {
        int i = this.i + 5;
        if (i > 100 || i < 30) {
            i = 30;
        }
        this.i = i;
        if (this.j == 0) {
            Drawable background = this.g.getBackground();
            this.j = background.getIntrinsicWidth();
            this.k = background.getIntrinsicHeight();
        }
        float f = (((float) i) * 1.0f) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity;
        int i2 = (int) (((float) this.j) * f);
        i = (int) (f * ((float) this.k));
        LayoutParams layoutParams = this.h.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i;
        this.h.setLayoutParams(layoutParams);
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.g = (DJIImageView) findViewById(R.id.d8o);
            this.h = (DJIImageView) findViewById(R.id.d8p);
            this.m = new Callback(this) {
                final /* synthetic */ DJIBeginnerTrackDrawView a;

                {
                    this.a = r1;
                }

                public boolean handleMessage(Message message) {
                    switch (message.what) {
                        case 4096:
                            this.a.a();
                            if (message.arg1 == 1) {
                                this.a.l.sendMessageDelayed(this.a.l.obtainMessage(4096, 1, 0), this.a.i >= 100 ? 1000 : DJIBeginnerTrackDrawView.b);
                                break;
                            }
                            break;
                    }
                    return true;
                }
            };
            this.l = new k(this, this.m);
        }
    }

    public boolean isFinished() {
        return getVisibility() != 0;
    }
}
