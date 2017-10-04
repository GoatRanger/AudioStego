package dji.pilot.visual.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataEyeGetPushTrackStatus.TrackMode;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataRcSetCustomFuction.DJICustomType;
import dji.midware.data.model.P3.DataSingleVisualParam.TrackingMode;
import dji.pilot.R;
import dji.pilot.fpv.camera.focus.DJIFocusAreaView;
import dji.pilot.fpv.flightmode.c$b;
import dji.pilot.fpv.model.n$b;
import dji.pilot.publics.widget.e;
import dji.pilot.visual.a.c;
import dji.pilot.visual.a.c$a;
import dji.pilot.visual.a.d;
import dji.pilot.visual.a.g;
import dji.pilot.visual.a.g$d;
import dji.pilot.visual.a.g$e;
import dji.pilot.visual.a.g$f;
import dji.publics.DJIUI.DJIRelativeLayout;

public class VisualScreenTouchView extends DJIRelativeLayout implements g {
    protected static final String a = VisualScreenTouchView.class.getSimpleName();
    private static final int c = 4096;
    private dji.pilot.visual.stage.a A = null;
    private a b;
    private dji.pilot.visual.a.b d = null;
    private DJIRedGradientView e = null;
    private FingerFlyView f = null;
    private VisualTrackView g = null;
    private b h = b.NONE;
    private c i;
    private final Context j;
    private float k = 0.0f;
    private float s = 0.0f;
    private float t = 0.0f;
    private float u = 0.0f;
    private PointF v = null;
    private MotionEvent w;
    private boolean x = false;
    private Handler y = new Handler(Looper.getMainLooper(), new Callback(this) {
        final /* synthetic */ VisualScreenTouchView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 4096:
                    if (DJIFocusAreaView.canLongPressFocus(this.a.getContext())) {
                        this.a.x = true;
                        this.a.b.a(this.a.w);
                    } else {
                        this.a.d.a(this.a.k, this.a.s, this.a.i.c() ? 1 : 0);
                    }
                    this.a.f.a();
                    break;
            }
            return true;
        }
    });
    private e z = null;

    public interface a {
        void a(MotionEvent motionEvent);
    }

    public enum b {
        NONE,
        TRACK_EVENT,
        POINT_EVENT,
        BOTH
    }

    public void setOnLongPressListener(a aVar) {
        this.b = aVar;
    }

    public VisualScreenTouchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = context;
        if (!isInEditMode()) {
            this.i = c.getInstance();
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.d = new dji.pilot.visual.a.b(this, (Activity) this.j);
            this.e = (DJIRedGradientView) findViewById(R.id.da8);
            this.f = (FingerFlyView) findViewById(R.id.d98);
            this.g = (VisualTrackView) findViewById(R.id.d_z);
        }
    }

    private void a() {
        this.y.removeMessages(4096);
        this.d.b();
    }

    private boolean a(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float x;
        if (action == 0) {
            x = motionEvent.getX();
            this.k = x;
            this.t = x;
            x = motionEvent.getY();
            this.s = x;
            this.u = x;
            if (this.g.a(motionEvent)) {
                this.v = this.g.getTargetCenter();
                this.d.c();
                this.d.a(this.k, this.s, 2);
            } else if (DJIFocusAreaView.canLongPressFocus(getContext()) || !this.i.b().b()) {
                this.d.c();
                this.y.sendEmptyMessageDelayed(4096, (long) ViewConfiguration.getLongPressTimeout());
            }
        } else if (action == 1 || action == 3) {
            a();
        } else if (action == 2) {
            x = motionEvent.getX();
            float y = motionEvent.getY();
            if (this.d.g) {
                this.d.a(motionEvent);
                this.d.a(((float) this.i.f) * (x - this.k), ((float) this.i.g) * (y - this.s));
                if (this.d.a() && (Math.abs(x - this.t) >= 48.0f || Math.abs(y - this.u) >= 27.0f)) {
                    this.i.b().a((this.v.x + x) - this.k, (this.v.y + y) - this.s);
                    this.t = x;
                    this.u = y;
                }
            } else if (Math.abs(y - this.u) >= 48.0f || Math.abs(x - this.t) >= 48.0f) {
                a();
            }
        }
        return true;
    }

    void a(b bVar) {
        if (this.h != bVar) {
            if (!(bVar == b.NONE && bVar == b.BOTH)) {
                a();
            }
            this.h = bVar;
            this.f.a(bVar);
            this.g.a(bVar);
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.w = motionEvent;
        MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
        if (!this.i.l() || !this.i.h() || !ServiceManager.getInstance().isRemoteOK() || mode == MODE.PLAYBACK || mode == MODE.NEW_PLAYBACK) {
            return false;
        }
        if (this.d.g) {
            a(motionEvent);
        } else if (!this.x) {
            if (this.h == b.NONE || this.h == b.BOTH) {
                boolean a;
                if (this.i.f() == g$e.POINT_MODE) {
                    a = this.f.a(motionEvent);
                } else {
                    a = this.g.b(motionEvent);
                }
                if (!a) {
                    a(motionEvent);
                }
            } else if (this.h == b.POINT_EVENT) {
                this.f.a(motionEvent);
            } else if (this.h == b.TRACK_EVENT) {
                this.g.b(motionEvent);
            }
        }
        if (motionEvent.getAction() != 3 && motionEvent.getAction() != 1) {
            return true;
        }
        this.h = b.NONE;
        this.x = false;
        a();
        return true;
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (this.i.l()) {
            this.f.onEventMainThread(dataOsdGetPushCommon);
        }
    }

    public void onEventMainThread(DataOsdGetPushHome dataOsdGetPushHome) {
        if (this.i.l()) {
            this.f.onEventMainThread(dataOsdGetPushHome);
        }
    }

    public void onEventMainThread(g$f dji_pilot_visual_a_g_f) {
        a(dji_pilot_visual_a_g_f);
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (this.i.l()) {
            MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
            if (mode == MODE.PLAYBACK || mode == MODE.NEW_PLAYBACK || mode == MODE.DOWNLOAD) {
                go();
            } else {
                show();
            }
        }
    }

    public void onEventMainThread(g$e dji_pilot_visual_a_g_e) {
        if (this.i.l()) {
            this.f.a(dji_pilot_visual_a_g_e);
            this.g.a(dji_pilot_visual_a_g_e);
        }
    }

    public void onEventMainThread(dji.pilot.visual.a.f.a aVar) {
        if (this.i.l()) {
            this.g.onEventMainThread(aVar);
        }
    }

    public void onEventMainThread(d.c cVar) {
        if (this.i.l()) {
            this.f.onEventMainThread(cVar);
        }
    }

    public void onEventMainThread(c$a dji_pilot_visual_a_c_a) {
        if (this.i.l()) {
            this.f.onEventMainThread(dji_pilot_visual_a_c_a);
        }
    }

    public void onEventMainThread(g$d dji_pilot_visual_a_g_d) {
        if (this.i.l()) {
            if (dji_pilot_visual_a_g_d == g$d.MODE_SELECT_VIEW) {
                d();
            } else if (dji_pilot_visual_a_g_d == g$d.ENTER_VISUAL) {
                if (this.i.f() == g$e.POINT_MODE) {
                    d();
                } else if (this.i.f() == g$e.TRACK_MODE && dji.pilot.fpv.flightmode.c.getInstance().a() == c$b.TRACK && this.i.b().a(TrackingMode.a)) {
                    b();
                }
            } else if (dji_pilot_visual_a_g_d == g$d.HIDE_MODE_VIEW) {
                e();
            } else {
                this.f.a(dji_pilot_visual_a_g_d);
            }
            if (dji_pilot_visual_a_g_d == g$d.CTRLMODE_CHANGED && dji.pilot.fpv.flightmode.c.getInstance().a() == c$b.TRACK) {
                TrackMode c = this.i.b().c();
                if (TrackMode.LOST == c) {
                    e();
                } else if (TrackMode.CONFIRM == c && dji.pilot.visual.util.c.b()) {
                    d();
                }
            }
        }
    }

    public boolean isNonVisionDlg() {
        return this.A == null || !this.A.isShowing();
    }

    private void b() {
        if (this.z == null) {
            this.z = new e(getContext());
            this.z.a((int) R.string.visual_track_normal_tip_title).b(R.string.visual_track_normal_tip).c(R.drawable.visual_track_normal_icon).a(new OnClickListener(this) {
                final /* synthetic */ VisualScreenTouchView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.i.b().a(TrackingMode.a, !this.a.z.b());
                    this.a.c();
                }
            });
        }
        if (!this.z.isShowing()) {
            this.z.a(true);
            dji.thirdparty.a.c.a().e(n$b.HIDE_VIEWS);
            this.z.show();
        }
    }

    private void c() {
        if (this.z != null && this.z.isShowing()) {
            dji.thirdparty.a.c.a().e(n$b.SHOW_VIEWS);
            this.z.dismiss();
            this.z = null;
        }
    }

    private void d() {
        if (dji.pilot.visual.util.c.c()) {
            if (this.A == null) {
                this.A = new dji.pilot.visual.stage.a(getContext());
                this.A.setOnShowListener(new OnShowListener(this) {
                    final /* synthetic */ VisualScreenTouchView a;

                    {
                        this.a = r1;
                    }

                    public void onShow(DialogInterface dialogInterface) {
                        dji.thirdparty.a.c.a().e(DJICustomType.r);
                    }
                });
                this.A.setOnDismissListener(new OnDismissListener(this) {
                    final /* synthetic */ VisualScreenTouchView a;

                    {
                        this.a = r1;
                    }

                    public void onDismiss(DialogInterface dialogInterface) {
                        dji.thirdparty.a.c.a().e(DJICustomType.s);
                    }
                });
            }
            if (!this.A.isShowing()) {
                this.A.show();
            }
        }
    }

    private void e() {
        if (this.A != null && this.A.isShowing()) {
            this.A.dismiss();
        }
    }

    private void a(g$f dji_pilot_visual_a_g_f) {
        this.g.a(dji_pilot_visual_a_g_f);
        this.f.a(dji_pilot_visual_a_g_f);
        if (this.i.l()) {
            this.g.show();
            this.f.show();
            onEventMainThread(DataOsdGetPushCommon.getInstance());
            onEventMainThread(DataOsdGetPushHome.getInstance());
            return;
        }
        this.g.go();
        this.f.go();
        this.e.go();
        e();
        c();
    }

    protected void onAttachedToWindow() {
        if (!isInEditMode()) {
            if (!dji.thirdparty.a.c.a().c(this)) {
                dji.thirdparty.a.c.a().a(this);
            }
            this.i.j();
        }
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode()) {
            this.i.k();
            if (dji.thirdparty.a.c.a().c(this)) {
                dji.thirdparty.a.c.a().d(this);
            }
        }
        super.onDetachedFromWindow();
    }
}
