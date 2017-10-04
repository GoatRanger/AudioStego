package dji.pilot.publics.objects;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraControlUpgrade;
import dji.midware.data.model.P3.DataCameraControlUpgrade.ControlCmd;
import dji.midware.data.model.P3.DataCameraGetPushUpgradeStatus;
import dji.midware.data.model.P3.DataCameraGetPushUpgradeStatus.UpgradeEndCause;
import dji.midware.data.model.P3.DataCameraGetPushUpgradeStatus.UpgradeStep;
import dji.midware.data.model.P3.DataFlycGetPushRTKLocationData;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataRcAckGimbalCtrPermission;
import dji.pilot.R;
import dji.pilot.dji_groundstation.controller.d;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.publics.control.a.c;
import dji.pilot.publics.control.a.e;
import dji.pilot.publics.widget.a;
import dji.pilot.publics.widget.b;
import dji.pilot.publics.widget.g;
import dji.pilot.publics.widget.h;
import dji.publics.DJIObject.DJIBaseActivityForVirtualKey;

public class DJIBaseActivity extends DJIBaseActivityForVirtualKey {
    private static final int INTERVAL_LOG = 300;
    private static long mLastTime = 0;
    public static int screenHeight;
    public static float screenRatio;
    public static int screenWidth;
    private b appStatusDialog;
    private b appStatusDialogForce;
    private h dialogUpgrade;
    protected Handler handler = new Handler(new 10(this));
    private boolean isMotorUp = false;
    private boolean isUseRTKFlagInited = false;
    private b lockDialog;
    private boolean mCurUseRTKFlag = false;
    protected boolean mGuideShowing = false;
    private dji.pilot.fpv.leftmenu.b mNoticeDialog;
    private UpgradeStep mStep;
    private g mUpgradeDlg;
    private a rcGimbaldialog;
    protected b unlockDialog;
    private int upgradeRestartTime = 5;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 19) {
            getWindow().addFlags(67108864);
            getWindow().addFlags(134217728);
        }
        getWindow().addFlags(128);
    }

    @SuppressLint({"NewApi"})
    public void setContentView(int i) {
        super.setContentView(i);
        dji.pilot.fpv.model.b.a(getBaseContext());
        if (screenWidth == 0) {
            if (VERSION.SDK_INT < 17) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                screenWidth = displayMetrics.widthPixels;
                screenHeight = displayMetrics.heightPixels;
                if (screenWidth < screenHeight) {
                    int i2 = screenWidth;
                    screenWidth = screenHeight;
                    screenHeight = i2;
                }
            } else {
                Display defaultDisplay = getWindowManager().getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getRealSize(point);
                screenWidth = point.x > point.y ? point.x : point.y;
                screenHeight = point.x > point.y ? point.y : point.x;
            }
            screenRatio = (((float) screenWidth) * 1.0f) / ((float) screenHeight);
        }
        this.handler.sendEmptyMessageDelayed(100, 1000);
    }

    protected void onResume() {
        super.onResume();
        dji.pilot2.publics.a.b.getInstance().c(this);
        if (dji.logic.c.b.getInstance().a()) {
            dji.logic.c.b.getInstance().a(this, getString(R.string.wm220_switch_to_rc_title), getString(R.string.wm220_switch_to_rc_tip), getString(R.string.wm220_flying_not_switch_to_rc));
        }
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
        DataCameraGetPushUpgradeStatus.getInstance().clear();
    }

    public void finishThis() {
    }

    public void finish() {
        super.finish();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - mLastTime < 300) {
                DJILogHelper.getInstance().autoHandle();
                Log.d("", "click double");
                mLastTime = 0;
            } else {
                mLastTime = currentTimeMillis;
                Log.d("", "click single");
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onEventMainThread(dji.pilot.publics.control.a.b bVar) {
        if (this.isVisible) {
            switch (3.a[bVar.ordinal()]) {
                case 1:
                    return;
                case 2:
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis >= g.b(getBaseContext(), dji.pilot.publics.control.a.d, currentTimeMillis)) {
                        if (this.appStatusDialog == null) {
                            this.appStatusDialog = new b(this, false);
                            this.appStatusDialog.f();
                            this.appStatusDialog.a(R.string.app_tip);
                            this.appStatusDialog.b(R.string.check_app_upgrade_tip);
                            this.appStatusDialog.d(R.string.check_app_upgrade_not_show);
                            this.appStatusDialog.a(new 1(this, currentTimeMillis));
                            this.appStatusDialog.e(R.string.show_netupgrade_dialog_right);
                            this.appStatusDialog.b(new 4(this));
                        }
                        this.appStatusDialog.show();
                        return;
                    }
                    return;
                case 3:
                    if (this.appStatusDialogForce == null) {
                        this.appStatusDialogForce = new b(this, false);
                        this.appStatusDialogForce.f();
                        this.appStatusDialogForce.a(R.string.app_tip);
                        this.appStatusDialogForce.b(R.string.check_app_must_upgrade);
                        this.appStatusDialogForce.d(R.string.check_app_must_upgrade_now);
                        this.appStatusDialogForce.a(new 5(this));
                        this.appStatusDialogForce.e(R.string.check_app_must_upgrade_now_web);
                        this.appStatusDialogForce.b(new 6(this));
                    }
                    this.appStatusDialogForce.show();
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventMainThread(e eVar) {
        if (this.isVisible) {
            switch (3.b[eVar.ordinal()]) {
                case 1:
                    if (this.lockDialog == null) {
                        this.lockDialog = new b(this, false);
                        this.lockDialog.f();
                        this.lockDialog.a(R.string.app_tip);
                        this.lockDialog.b(getString(R.string.check_upgrade_remain_time_tips, new Object[]{Integer.valueOf(dji.pilot.publics.control.a.getInstance().k())}));
                        this.lockDialog.d(R.string.app_enter);
                        this.lockDialog.a(new 7(this));
                    }
                    this.lockDialog.show();
                    return;
                case 2:
                case 3:
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventMainThread(c cVar) {
        if (!this.isVisible) {
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (this.isMotorUp != dataOsdGetPushCommon.isMotorUp()) {
            this.isMotorUp = dataOsdGetPushCommon.isMotorUp();
            this.handler.sendEmptyMessage(300);
        }
        onBackgroundThreadOver(dataOsdGetPushCommon);
    }

    protected void onBackgroundThreadOver(DataOsdGetPushCommon dataOsdGetPushCommon) {
    }

    public void onEventMainThread(o oVar) {
        if (this.isVisible) {
            switch (3.c[oVar.ordinal()]) {
                case 1:
                    return;
                case 2:
                    this.mStep = null;
                    if (this.dialogUpgrade != null) {
                        this.dialogUpgrade.dismiss();
                    }
                    if (this.mUpgradeDlg == null) {
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventMainThread(p pVar) {
        if (this.isVisible) {
            switch (3.d[pVar.ordinal()]) {
                case 1:
                    return;
                case 2:
                    if (this.mUpgradeDlg != null) {
                        this.mUpgradeDlg.dismiss();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventBackgroundThread(DataRcAckGimbalCtrPermission dataRcAckGimbalCtrPermission) {
        if (this.isVisible) {
            Log.d("", "DataRcAckGimbalCtrPermission");
            this.handler.post(new 8(this, dataRcAckGimbalCtrPermission));
        }
    }

    private void showUpgradeCheck() {
        if (this.dialogUpgrade == null) {
            this.dialogUpgrade = new h(this);
        }
        this.dialogUpgrade.d(false);
        this.dialogUpgrade.c(true);
        this.dialogUpgrade.c(0);
        this.dialogUpgrade.d(R.string.app_upgrade_check);
        this.dialogUpgrade.show();
        if (this.mUpgradeDlg != null) {
            this.mUpgradeDlg.dismiss();
        }
    }

    private void showUserConfirm() {
        DataCameraControlUpgrade.getInstance().setControlCmd(ControlCmd.Start).start(new 9(this));
    }

    public void onEventMainThread(DataCameraGetPushUpgradeStatus dataCameraGetPushUpgradeStatus) {
        if (this.isVisible) {
            UpgradeStep step = dataCameraGetPushUpgradeStatus.getStep();
            if (step != this.mStep) {
                this.mStep = step;
                DJILogHelper.getInstance().LOGD(this.TAG, "upgrade step " + this.mStep, false, true);
                switch (3.e[step.ordinal()]) {
                    case 1:
                    case 3:
                        return;
                    case 2:
                        showUserConfirm();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public void onEventBackgroundThread(DataFlycGetPushRTKLocationData dataFlycGetPushRTKLocationData) {
        if (d.getInstance().a().a() == dji.pilot.dji_groundstation.a.d.a.f.a()) {
            dji.pilot.dji_groundstation.a.d.c b = d.getInstance().b();
            if (b.a(dji.pilot.dji_groundstation.a.d.c.PointOfInterest) || b.a(dji.pilot.dji_groundstation.a.d.c.WayPoint)) {
                boolean isRTKCanbeUsed = dataFlycGetPushRTKLocationData.isRTKCanbeUsed();
                if (!this.isUseRTKFlagInited) {
                    this.isUseRTKFlagInited = true;
                    this.mCurUseRTKFlag = isRTKCanbeUsed;
                }
                if (this.mCurUseRTKFlag != isRTKCanbeUsed) {
                    this.mCurUseRTKFlag = isRTKCanbeUsed;
                    DJIErrorPopView.b bVar;
                    if (this.mCurUseRTKFlag) {
                        bVar = new DJIErrorPopView.b();
                        bVar.b = R.string.rtk_gps_switch_to_rtk_tips;
                        dji.thirdparty.a.c.a().e(bVar);
                        return;
                    }
                    bVar = new DJIErrorPopView.b();
                    bVar.b = R.string.rtk_gps_switch_to_gps_tips;
                    dji.thirdparty.a.c.a().e(bVar);
                }
            }
        }
    }

    private void showUpgradeResult(UpgradeEndCause upgradeEndCause) {
        String string;
        switch (3.f[upgradeEndCause.ordinal()]) {
            case 1:
                string = getString(R.string.app_upgrade_success, new Object[]{Integer.valueOf(this.upgradeRestartTime)});
                break;
            case 2:
                string = getString(R.string.app_upgrade_firmwareerror);
                break;
            case 3:
                string = getString(R.string.app_upgrade_usercancel);
                break;
            case 4:
                string = getString(R.string.app_upgrade_versionsame);
                break;
            case 5:
                string = getString(R.string.app_upgrade_timeout);
                break;
            case 6:
                string = getString(R.string.app_upgrade_motorup);
                break;
            default:
                string = getString(R.string.app_upgrade_failed);
                break;
        }
        if (this.mUpgradeDlg != null) {
            this.mUpgradeDlg.dismiss();
        }
        if (this.dialogUpgrade == null) {
            this.dialogUpgrade = new h(this);
        }
        this.dialogUpgrade.d(false);
        this.dialogUpgrade.c(false);
        this.dialogUpgrade.a(string);
        if (upgradeEndCause == UpgradeEndCause.Success) {
            this.dialogUpgrade.c(R.drawable.upgrade_finished);
            this.handler.sendEmptyMessageDelayed(200, 1000);
        } else {
            this.dialogUpgrade.c(R.drawable.upgrade_error);
            this.handler.sendEmptyMessageDelayed(210, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
        }
        this.dialogUpgrade.show();
    }

    private void showDownloadPgbDialog(int i, int i2, int i3) {
        if (this.mUpgradeDlg == null) {
            this.mUpgradeDlg = new g(this);
            this.mUpgradeDlg.a(getString(R.string.app_upgrade_progress));
            this.mUpgradeDlg.a(i3);
        } else {
            this.mUpgradeDlg.a(i3);
        }
        if (this.mUpgradeDlg != null && !this.mUpgradeDlg.isShowing()) {
            this.mUpgradeDlg.show();
        }
    }

    private void updateDialogConfirm() {
        if (this.dialogUpgrade == null || !this.dialogUpgrade.isShowing() || !this.dialogUpgrade.f() || this.dialogUpgrade.b()) {
            return;
        }
        if (this.isMotorUp) {
            this.dialogUpgrade.a(false);
            this.dialogUpgrade.d(R.string.upgrade_prohibited_message);
            return;
        }
        this.dialogUpgrade.a(true);
        this.dialogUpgrade.d(R.string.app_upgrade_start_title);
    }

    public void onEventMainThread(dji.logic.c.b.a aVar) {
        if (aVar == dji.logic.c.b.a.SHOW_SWITCH_DLG) {
            DJILogHelper.getInstance().LOGD("wm220", "***in SwitchUIAction.SHOW_SWITCH_DLG", false, true);
            dji.logic.c.b.getInstance().a(true);
        } else if (aVar == dji.logic.c.b.a.SHOW_NOT_CONNECT_DLG) {
            dji.logic.c.b.getInstance().a((Context) this, getString(R.string.wm220_not_connect_rc));
        }
    }

    public void onEventMainThread(DJIGlobalService.b bVar) {
    }

    protected void showG04RecommendDialog() {
        if (dji.logic.f.d.k(null) && !g.b((Context) this, "fpv_go4_recommended", false)) {
            dji.pilot.fpv.leftmenu.b bVar = new dji.pilot.fpv.leftmenu.b(this);
            bVar.a(getString(R.string.fpv_recommend_go4_title)).b(getString(R.string.fpv_recommend_go4_content)).a(6).c(getString(R.string.app_never_tip)).e(8).f(8).d(getString(R.string.app_ikonw)).a(new 11(this));
            this.handler.postDelayed(new 2(this, bVar), 4000);
        }
    }
}
