package dji.pilot.fpv.control;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.alipay.sdk.j.i;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataRcGetPushFollowFocus;
import dji.midware.data.model.P3.DataRcGetPushFollowFocus.CtrlDirection;
import dji.midware.data.model.P3.DataRcGetPushFollowFocus.CtrlType;
import dji.pilot.R;
import dji.pilot.publics.widget.f;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;

public class j {
    public static boolean a = false;
    private static final int b = 1000;
    private static final long c = 3000;
    private static final int d = 2000;
    private static final long e = 4000;
    private final Context f;
    private f g = null;
    private b h = null;
    private int i = Integer.MIN_VALUE;
    private CtrlType j = CtrlType.OTHER;
    private CtrlDirection k = CtrlDirection.OTHER;

    public enum a {
        a,
        DISCONNECT
    }

    private static final class b extends Handler {
        private final WeakReference<j> a;

        public b(j jVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(jVar);
        }

        public void handleMessage(Message message) {
            j jVar = (j) this.a.get();
            if (jVar == null) {
                return;
            }
            if (1000 == message.what) {
                jVar.f();
            } else if (2000 == message.what) {
                jVar.e();
            }
        }
    }

    public static boolean a() {
        return a;
    }

    public j(Context context) {
        this.f = context;
        this.h = new b(this);
    }

    public void a(DataRcGetPushFollowFocus dataRcGetPushFollowFocus) {
        boolean z;
        boolean z2;
        dji.pilot.fpv.camera.a.a.a(null, "Receiver focus follow packet");
        int curCtrlStatus = dataRcGetPushFollowFocus.getCurCtrlStatus();
        if (this.i != curCtrlStatus) {
            this.i = curCtrlStatus;
            z = true;
        } else {
            z = false;
        }
        CtrlType ctrlType = dataRcGetPushFollowFocus.getCtrlType();
        if (this.j != ctrlType) {
            this.j = ctrlType;
            z2 = true;
        } else {
            z2 = false;
        }
        CtrlDirection ctrlDirection = dataRcGetPushFollowFocus.getCtrlDirection();
        if (ctrlDirection != this.k) {
            this.k = ctrlDirection;
            z2 = true;
        }
        if (z) {
            if (curCtrlStatus == 0) {
                this.h.removeMessages(2000);
                c();
            } else if (curCtrlStatus == 1) {
                e();
            }
        } else if (curCtrlStatus == 0 && r3) {
            d();
        }
        if (curCtrlStatus == 1 && !a) {
            c();
            this.h.removeMessages(2000);
            this.h.sendEmptyMessageDelayed(2000, e);
        }
        if (!a) {
            a = true;
            c.a().e(a.a);
        }
        if (z || r3) {
            DJILogHelper.getInstance().LOGD("", "== Follow Focus[" + curCtrlStatus + i.b + ctrlType + i.b + ctrlDirection + i.b, false, true);
        }
        this.h.removeMessages(1000);
        this.h.sendEmptyMessageDelayed(1000, 3000);
    }

    public void b() {
        f();
    }

    private void c() {
        if (this.g == null) {
            this.g = new f(this.f);
            this.g.b().a().d();
        }
        if (this.g != null && !this.g.isShowing()) {
            this.g.show();
            d();
        }
    }

    private void d() {
        if (this.g != null && this.g.isShowing()) {
            if (this.i == 0) {
                if (this.k == CtrlDirection.CCW) {
                    this.g.a((int) R.drawable.follow_focus_tip_ccw);
                    if (this.j == CtrlType.APERTURE) {
                        this.g.a(this.f.getString(R.string.follow_focus_ccw_enable_iris));
                        return;
                    } else if (this.j == CtrlType.FOCAL_LENGTH) {
                        this.g.a(this.f.getString(R.string.follow_focus_ccw_enable_focus));
                        return;
                    } else {
                        return;
                    }
                }
                this.g.a((int) R.drawable.follow_focus_tip_cw);
                if (this.j == CtrlType.APERTURE) {
                    this.g.a(this.f.getString(R.string.follow_focus_cw_enable_iris));
                } else if (this.j == CtrlType.FOCAL_LENGTH) {
                    this.g.a(this.f.getString(R.string.follow_focus_cw_enable_focus));
                }
            } else if (!a) {
                this.g.a((int) R.drawable.follow_focus_tip_connect);
                this.g.a(this.f.getString(R.string.follow_focus_connected_tip));
            }
        }
    }

    private void e() {
        if (this.g != null && this.g.isShowing()) {
            this.g.dismiss();
            this.h.removeMessages(2000);
        }
    }

    private void f() {
        boolean z = a;
        a = false;
        this.i = Integer.MIN_VALUE;
        this.j = CtrlType.OTHER;
        this.k = CtrlDirection.OTHER;
        e();
        this.h.removeMessages(1000);
        if (z) {
            c.a().e(a.DISCONNECT);
        }
    }
}
