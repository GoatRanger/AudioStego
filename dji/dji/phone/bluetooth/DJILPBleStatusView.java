package dji.phone.bluetooth;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.p;
import dji.phone.e.a.c;
import dji.phone.e.a.e;
import dji.phone.e.b;
import dji.pilot.fpv.R;
import dji.pilot.phonecamera.a.a;
import java.util.Timer;
import java.util.TimerTask;

public class DJILPBleStatusView extends ImageView implements OnClickListener {
    public static b c = b.NOTCONNECTED;
    private static final String f = "DJILPBleStatusView";
    private static final int g = R.drawable.lp_ble_link_fail;
    private static final int h = R.drawable.lp_ble_normal_img;
    int a;
    Timer b;
    TimerTask d;
    int e = R.drawable.lp_ble_link_fail;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b = new int[p.values().length];
        static final /* synthetic */ int[] c = new int[a.values().length];

        static {
            try {
                c[a.e.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[p.b.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[p.a.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            a = new int[b.values().length];
            try {
                a[b.NOTCONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[b.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[b.CONNECTING.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public DJILPBleStatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnClickListener(this);
        if (c.getInstance().b()) {
            c = b.CONNECTED;
            a(c, b.NOTCONNECTED);
        }
    }

    private void a(b bVar, b bVar2) {
        DJILogHelper.getInstance().LOGD(f, "handleStatusChanged: old:" + bVar2 + "new:" + bVar, false, true);
        if (bVar2 == b.CONNECTING) {
            a();
        }
        switch (bVar) {
            case NOTCONNECTED:
                setImageResource(g);
                break;
            case CONNECTED:
                setImageResource(h);
                break;
        }
        c = bVar;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        dji.f.a.a(this);
        if (!dji.pilot.set.a.e(getContext())) {
            bringToFront();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (c == b.CONNECTING) {
            a();
        }
        dji.f.a.b(this);
        c = b.NOTCONNECTED;
    }

    public void setImageResource(int i) {
        this.e = i;
        super.setImageResource(i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            super.setImageResource(R.drawable.lp_ble_link_fail_hover);
        } else if (motionEvent.getAction() == 1) {
            super.setImageResource(this.e);
        }
        return super.onTouchEvent(motionEvent);
    }

    private void a() {
        DJILogHelper.getInstance().LOGD(f, "stopConnectingTimer: ", false, true);
        if (this.b != null) {
            this.b.cancel();
            this.b = null;
        }
        if (this.d != null) {
            this.d.cancel();
            this.d = null;
        }
    }

    public void onEventMainThread(p pVar) {
        switch (AnonymousClass1.b[pVar.ordinal()]) {
            case 1:
                a(b.CONNECTED, c);
                return;
            case 2:
                a(b.NOTCONNECTED, c);
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(b bVar) {
        if (bVar.a == e.BTN_BLE_STATUS) {
            dji.phone.k.a.a(this, bVar);
            if (bVar.c == c.i) {
                bringToFront();
            }
        }
    }

    public void onEventMainThread(a aVar) {
        switch (AnonymousClass1.c[aVar.ordinal()]) {
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
        if (dji.midware.b.c.getInstance().c()) {
            dji.thirdparty.a.c.a().e(new b(e.BTN_BLE_STATUS, c.c));
        } else {
            dji.phone.preview.a.getInstance().h();
        }
    }

    public void onEventMainThread(dji.phone.h.b bVar) {
        dji.phone.h.a.a(this, getRotation(), dji.phone.k.c.a(bVar.b()));
    }
}
