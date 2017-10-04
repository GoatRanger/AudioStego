package dji.pilot.playback.litchi;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataDm368GetPushStatus;
import dji.midware.data.model.P3.DataDm368SetParams;
import dji.midware.data.model.P3.DataDm368SetParams.DM368CmdId;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.midware.media.DJIVideoDecoder;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.d.e;
import dji.pilot.playback.litchi.DJIHeadTabView.a;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.d;
import dji.pilot.usercenter.d.c;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIPlayBackActivity extends DJIAlbumFragmentActivity implements c {
    @dji.thirdparty.afinal.a.b.c(a = 2131364879)
    public DJIHeadTabView a;
    @dji.thirdparty.afinal.a.b.c(a = 2131364855)
    public DJITextView b;
    @dji.thirdparty.afinal.a.b.c(a = 2131364849)
    public DJILinearLayout c;
    @dji.thirdparty.afinal.a.b.c(a = 2131364851)
    public DJILinearLayout d;
    @dji.thirdparty.afinal.a.b.c(a = 2131364856)
    public DJITextView e;
    @dji.thirdparty.afinal.a.b.c(a = 2131364854)
    public DJITextView f;
    private a h = null;
    private OnClickListener i = null;
    private FragmentManager j = null;
    private dji.pilot.usercenter.c.a k = null;
    private int l = 1;
    private boolean m = false;
    private DJIVideoDecoder n;
    private DataDm368SetParams o = new DataDm368SetParams();
    private MODE p = null;

    protected /* synthetic */ d a() {
        return b();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        Intent intent = getIntent();
        this.m = intent.getBooleanExtra("isphotoview", false);
        DJILogHelper.getInstance().LOGD("", this.m + "isPhotoView", false, true);
        DJIVideoDecoder e = ServiceManager.getInstance().e();
        if (e != null) {
            e.pauseStatusCheck();
        }
        if (intent.getBooleanExtra("isSensor", false)) {
            c();
        } else {
            c();
        }
    }

    private void c() {
        g();
        e();
        f();
        h.getInstance();
        this.a.setCurrentTab(0);
        if (b.q() && !DataDm368GetPushStatus.getInstance().isDisableLiveview()) {
            this.o.a(DM368CmdId.a, 1).start(new 1(this));
        }
    }

    private void d() {
        this.h = new 2(this);
        this.i = new 3(this);
    }

    public void onBackPressed() {
        dji.pilot2.library.d.getInstance().c(false);
        dji.thirdparty.a.c.a().e(dji.pilot2.library.a.n);
        super.onBackPressed();
    }

    private void e() {
        d();
        dji.pilot.usercenter.b.c.getInstance().a(getApplicationContext());
        this.j = getFragmentManager();
        this.k = new dji.pilot.usercenter.c.a(this, this.j, R.id.biu);
        this.k.a(DJIHeadTabView.d, g.class, new Bundle());
        this.k.a(DJIHeadTabView.c, f.class, new Bundle());
    }

    private void f() {
        setContentView(R.layout.playback_view);
        this.a.setOnTabSelectedListener(this.h);
        this.e.setOnClickListener(this.i);
        this.c.setOnClickListener(this.i);
    }

    private void g() {
        dji.thirdparty.a.c.a().a((Object) this);
    }

    private void h() {
        dji.thirdparty.a.c.a().d((Object) this);
    }

    public void onEventBackgroundThread(p pVar) {
        switch (8.a[pVar.ordinal()]) {
            case 2:
                Log.d(this.TAG, "connect lost");
                finishThis();
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        MODE mode = dataCameraGetPushStateInfo.getMode();
        if (this.p != mode) {
            this.p = mode;
            if (this.p != dji.pilot.publics.e.c.b()) {
                finishThis();
            }
        }
    }

    public void a(int i, int i2) {
        this.a.showTabNew(i, i2);
    }

    protected void onStart() {
        super.onStart();
        e.b((Context) this);
    }

    protected void onStop() {
        super.onStop();
        e.c((Context) this);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        new Handler(Looper.getMainLooper()).postDelayed(new 4(this), 500);
        if (this.n != null) {
            this.n.resumeStatusCheck();
        }
        h.getInstance().c();
        h();
    }

    public void finishThis() {
        dji.pilot2.library.d.getInstance().c(false);
        dji.thirdparty.a.c.a().e(dji.pilot2.library.a.n);
        if (!this.m) {
            ProductType c = i.getInstance().c();
            if (DataCameraGetPushStateInfo.getInstance().getMode() == MODE.PLAYBACK && (c == ProductType.litchiS || c == ProductType.litchiC || dji.pilot.publics.e.c.a(DataCameraGetPushStateInfo.getInstance().getCameraType()) || c == ProductType.P34K || dji.pilot.publics.e.a.c(c))) {
                DJILogHelper.getInstance().LOGD("test playback", "out playback mode=" + dji.pilot.c.d.a, false, false);
                DataCameraSetMode.getInstance().a(dji.pilot.c.d.a).start(new 5(this));
            }
            if (b.h(c)) {
                DJILogHelper.getInstance().LOGD("test playback", "out playback mode=" + dji.pilot.c.d.a, false, false);
                DataCameraSetMode.getInstance().a(dji.pilot.c.d.a).start(new 6(this));
            }
            DataSpecialControl.getInstance().setPlayBackType(false).start(20);
        }
        finish();
    }

    public void finish() {
        super.finish();
    }

    protected e b() {
        try {
            return (e) this.j.findFragmentById(R.id.biu);
        } catch (Exception e) {
            return null;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            new Handler().postDelayed(new 7(this, intent), 500);
        } else if (i2 == this.l) {
            DJILogHelper.getInstance().LOGD("", "handleDownload", false, true);
            b().i();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || g.h != 1) {
            return super.onKeyDown(i, keyEvent);
        }
        b().g();
        return false;
    }
}
