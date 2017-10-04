package dji.phone.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import dji.phone.customui.DJILPRotationRelativeLayout;
import dji.phone.d.a.c;
import dji.phone.e.a.e;
import dji.phone.f.d;
import dji.phone.g.b;
import dji.pilot.fpv.R;
import dji.pilot.phonecamera.a.a;

public class DJILPUISwitcher extends DJILPRotationRelativeLayout implements OnClickListener {
    public static b a = b.AUTO;
    private static final String c = "DJILPUISwitcher";
    LayoutParams b;
    private b d = b.AUTO;
    private ImageView e;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[a.values().length];

        static {
            b = new int[b.values().length];
            try {
                b[b.AUTO.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[b.METERING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[b.TRACKING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[b.DARKENED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[a.e.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public DJILPUISwitcher(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnClickListener(this);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.e = (ImageView) findViewById(R.id.lp_mode_switch_btn);
        this.e.setOnClickListener(this);
        dji.f.a.a(this);
        this.b = (LayoutParams) getLayoutParams();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.f.a.b(this);
        a = b.AUTO;
        this.d = b.AUTO;
    }

    public void onEventMainThread(a aVar) {
        switch (AnonymousClass1.a[aVar.ordinal()]) {
            case 1:
                if (!isShown()) {
                    setVisibility(0);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.lp_mode_switch_btn) {
            switchMode(this.d == b.TRACKING ? b.AUTO : b.TRACKING);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void switchMode(dji.phone.g.b r3) {
        /*
        r2 = this;
        r0 = a;
        if (r0 == r3) goto L_0x0017;
    L_0x0004:
        r0 = dji.phone.g.b.TRACKING;
        if (r3 != r0) goto L_0x001e;
    L_0x0008:
        r0 = dji.midware.data.manager.P3.ServiceManager.getInstance();
        r0 = r0.isConnected();
        if (r0 != 0) goto L_0x0018;
    L_0x0012:
        r0 = dji.pilot.fpv.R.string.longan_error_notconnected;
        dji.phone.k.b.showLong(r0);
    L_0x0017:
        return;
    L_0x0018:
        r0 = dji.phone.a.d.a();
        if (r0 != 0) goto L_0x001e;
    L_0x001e:
        a = r3;
        r2.d = r3;
        r0 = dji.thirdparty.a.c.a();
        r1 = a;
        r0.e(r1);
        r2.switchUIModeImg();
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.phone.widget.DJILPUISwitcher.switchMode(dji.phone.g.b):void");
    }

    public void onEventMainThread(c cVar) {
        if (cVar == c.TIMELAPSE_MOTION || cVar == c.TIMELAPSE_STATIONARY) {
            switchMode(b.AUTO);
        }
    }

    public void onEventMainThread(dji.phone.d.a.b bVar) {
        if (bVar == dji.phone.d.a.b.LONGEXPOSURE || bVar == dji.phone.d.a.b.PANO) {
            switchMode(b.AUTO);
        }
    }

    public void onEventMainThread(d dVar) {
        if (dVar == d.SLEEP) {
            switchMode(b.AUTO);
        }
    }

    public void onEventMainThread(dji.phone.e.b bVar) {
        if (bVar.a == e.VIEW_UI_SWITCHER) {
            dji.phone.k.a.a(this, bVar);
        } else if (bVar.a == e.VIEW_PREVIEW && bVar.c == dji.phone.e.a.c.b) {
            if (dji.phone.preview.a.getInstance().e()) {
                dji.thirdparty.a.c.a().e(new dji.phone.e.b(dji.phone.e.a.a.h, dji.phone.e.a.c.c));
                return;
            }
            dji.phone.e.b bVar2 = new dji.phone.e.b(dji.phone.e.a.a.f, dji.phone.e.a.c.i);
            switch (a) {
                case AUTO:
                    bVar2.b = dji.phone.e.a.a.f;
                    switchMode(b.METERING);
                    break;
                case METERING:
                    bVar2.b = dji.phone.e.a.a.f;
                    break;
                case TRACKING:
                    bVar2.b = dji.phone.e.a.a.g;
                    break;
                case DARKENED:
                    bVar2.b = dji.phone.e.a.a.j;
                    break;
            }
            dji.thirdparty.a.c.a().e(bVar2);
        } else if (bVar.b == dji.phone.e.a.a.i) {
            a = b.DARKENED;
        } else if (bVar.b == dji.phone.e.a.a.j) {
            a = this.d;
        }
    }

    public void switchUIModeImg() {
        this.e.setImageResource(this.d == b.TRACKING ? R.drawable.lp_ic_track : R.drawable.lp_ic_normal);
        if (this.d != b.TRACKING) {
            dji.thirdparty.a.c.a().e(new dji.phone.e.b(e.BTN_TK_QUIT, dji.phone.e.a.c.c));
        }
    }
}
