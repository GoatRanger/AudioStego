package dji.pilot.home.rc.view;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.pilot.R;
import dji.pilot.fpv.model.f;
import dji.pilot.main.activity.DJIHubActivity;
import dji.pilot.playback.litchi.h;
import dji.pilot.publics.c.d;
import dji.pilot2.publics.object.b;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;

public class MainConnectionStateView extends RelativeLayout implements OnClickListener, OnTouchListener {
    private static final String a = MainConnectionStateView.class.getName();
    private static final int q = 1;
    private static final int r = 2;
    private static final int s = 3;
    private static final int t = 4;
    private Context b;
    private AlphaAnimation c;
    private AlphaAnimation d;
    private TranslateAnimation e;
    private TranslateAnimation f;
    private ScaleAnimation g;
    private ScaleAnimation h;
    private AnimationSet i;
    private AnimationSet j;
    private DJILinearLayout k;
    private DJILinearLayout l;
    private DJITextView m;
    private Button n;
    private Button o;
    private a p;
    private CameraType u;

    private static class a extends Handler {
        private WeakReference<MainConnectionStateView> a;

        a(MainConnectionStateView mainConnectionStateView) {
            this.a = new WeakReference(mainConnectionStateView);
        }

        public void handleMessage(Message message) {
            final MainConnectionStateView mainConnectionStateView = (MainConnectionStateView) this.a.get();
            switch (message.what) {
                case 1:
                    mainConnectionStateView.e();
                    return;
                case 2:
                    mainConnectionStateView.f();
                    return;
                case 3:
                    mainConnectionStateView.m.setText(Html.fromHtml(mainConnectionStateView.b.getString(d.getInstance().c(i.getInstance().c().value()))));
                    return;
                case 4:
                    MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
                    DJILogHelper.getInstance().LOGD(MainConnectionStateView.a, "***************首页 mode=" + mode + "********************");
                    if (mode == MODE.PLAYBACK || mode == MODE.NEW_PLAYBACK) {
                        DataCameraSetMode.getInstance().a(dji.pilot.c.d.a).start(new dji.midware.e.d(this) {
                            final /* synthetic */ a b;

                            public void onSuccess(Object obj) {
                                mainConnectionStateView.g();
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                Log.e(MainConnectionStateView.a, "切换相机模式失败：" + aVar.name());
                                mainConnectionStateView.p.sendEmptyMessageDelayed(4, 300);
                            }
                        });
                        return;
                    } else {
                        mainConnectionStateView.g();
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public MainConnectionStateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        b();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    private void b() {
        dji.setting.a.a.a((View) this, (int) R.layout.rc_main_connect_state_view);
        if (!isInEditMode()) {
            c();
            d();
            this.p = new a(this);
            d.a(this.b);
            if (ServiceManager.getInstance().isRemoteOK()) {
                onEventMainThread(o.b);
            }
        }
    }

    private void c() {
        this.c = new AlphaAnimation(0.0f, 1.0f);
        this.c.setDuration(500);
        this.c.setStartOffset(500);
        this.d = new AlphaAnimation(1.0f, 0.0f);
        this.d.setDuration(500);
        this.e = new TranslateAnimation(0.0f, f.a, 0.0f, 0.0f);
        this.e.setInterpolator(new OvershootInterpolator());
        this.e.setDuration(500);
        this.e.setStartOffset(100);
        this.f = new TranslateAnimation(-50.0f, 0.0f, 0.0f, 0.0f);
        this.f.setInterpolator(new OvershootInterpolator());
        this.f.setDuration(500);
        this.f.setStartOffset(100);
        this.i = new AnimationSet(true);
        this.i.addAnimation(this.d);
        this.i.addAnimation(this.e);
        this.j = new AnimationSet(true);
        this.j.addAnimation(this.c);
        this.j.addAnimation(this.f);
        this.g = new ScaleAnimation(1.0f, 1.09f, 1.0f, 1.08f, 1, dji.pilot.visual.a.d.c, 1, dji.pilot.visual.a.d.c);
        this.g.setDuration(150);
        this.g.setFillAfter(true);
        this.h = new ScaleAnimation(1.09f, 1.0f, 1.08f, 1.0f, 1, dji.pilot.visual.a.d.c, 1, dji.pilot.visual.a.d.c);
        this.h.setDuration(150);
        this.h.setFillAfter(true);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            this.o.startAnimation(this.h);
        }
        if (motionEvent.getAction() == 0) {
            this.o.startAnimation(this.g);
        }
        return false;
    }

    private void d() {
        this.k = (DJILinearLayout) findViewById(R.id.bjp);
        this.l = (DJILinearLayout) findViewById(R.id.bjs);
        this.n = (Button) findViewById(R.id.bju);
        this.n.setOnClickListener(this);
        this.o = (Button) findViewById(R.id.bjr);
        this.o.setOnClickListener(this);
        this.o.setOnTouchListener(this);
        this.m = (DJITextView) findViewById(R.id.bjt);
    }

    private void e() {
        this.k.setAnimation(this.i);
        this.l.setAnimation(this.j);
        this.k.go();
        this.i.start();
        this.j.start();
        this.l.show();
    }

    private void f() {
        this.l.setAnimation(this.i);
        this.k.setAnimation(this.j);
        this.l.go();
        this.i.start();
        this.j.start();
        this.k.show();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bjr:
                g();
                return;
            case R.id.bju:
                h();
                return;
            default:
                return;
        }
    }

    private void g() {
        if (DJIUpgradeP4Service.f()) {
            Builder bVar = new b(getContext());
            bVar.setMessage(R.string.connectmc_notallowfpv_2);
            bVar.setPositiveButton(17039379, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ MainConnectionStateView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            bVar.show();
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this.b, DJIHubActivity.class);
        intent.putExtra(dji.pilot.c.b.a, true);
        this.b.startActivity(intent);
    }

    private void h() {
        dji.pilot2.library.d.getInstance().b(true);
        if (dji.pilot.publics.e.c.a() || dji.pilot.publics.e.c.c()) {
            DJILogHelper.getInstance().LOGD("", "*********首页 进入FPV    MSG_SETMODE**********", true, true);
            this.p.sendEmptyMessage(4);
        }
        h.getInstance().g().c();
        c.a().e(dji.pilot2.library.a.PhotoDIsConnect);
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.b) {
            if (this.p.hasMessages(2)) {
                this.p.removeMessages(2);
            } else if (!this.p.hasMessages(1)) {
                this.p.sendEmptyMessageDelayed(1, 300);
            }
        } else if (oVar != o.a) {
        } else {
            if (this.p.hasMessages(1)) {
                this.p.removeMessages(1);
            } else if (!this.p.hasMessages(2)) {
                this.p.sendEmptyMessageDelayed(2, 300);
            }
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        boolean j = dji.pilot.fpv.d.b.j(cameraType);
        if (this.u != cameraType || j) {
            Log.d(a, "相机类型种类收到");
            this.u = cameraType;
            this.p.sendEmptyMessage(3);
        }
    }
}
