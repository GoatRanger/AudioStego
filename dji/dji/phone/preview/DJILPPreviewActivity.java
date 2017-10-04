package dji.phone.preview;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import dji.apppublic.reflect.AppPublicReflect;
import dji.device.common.view.DJIGimbalRollFineTuneViewCompat;
import dji.device.common.view.DJIGridLineCompat;
import dji.device.widget.popview.DJIErrorPopViewCompat;
import dji.log.DJILogHelper;
import dji.phone.a.e;
import dji.phone.a.h;
import dji.phone.bluetooth.DJILPBleStatusView;
import dji.phone.controview.DJILPCameraControView;
import dji.phone.controview.DJILPCameraModuleSwitcher;
import dji.phone.controview.a;
import dji.phone.f.b;
import dji.phone.live.DJILPLivePresenter;
import dji.phone.live.DJILPLiveShareFpvView;
import dji.phone.menu.DJILPCameraLevel1MenuView;
import dji.phone.menu.DJILPCameraLevel2MenuView;
import dji.phone.pano.DJILPPanoDisplayer;
import dji.phone.rightbar.DJILPCameraQuickSettingView;
import dji.phone.set.gimbalplan.DJILPGimbalRotationPlanView;
import dji.phone.set.main.DJILPCameraSetView;
import dji.phone.set.main.DJILPGimbalSetView;
import dji.phone.timelapse.DJILPTimelapseSetView;
import dji.phone.tutorial.d;
import dji.phone.widget.DJILPMeterView;
import dji.phone.widget.DJILPUISwitcher;
import dji.pilot.fpv.R;
import dji.pilot.set.g;
import dji.publics.DJIObject.DJIBaseActivityForVirtualKey;
import dji.thirdparty.a.c;

public class DJILPPreviewActivity extends DJIBaseActivityForVirtualKey {
    public static final int REQUEST_ENABLE_BT = 1021;
    private TextView mAEAFLockTv;
    private DJILPBleStatusView mBLEView;
    private DJILPCameraControView mCameraControlView;
    public a mCameraPresenter;
    private b mDJILPGimbleInfoHandleAndDispatch;
    private DJIErrorPopViewCompat mErrorPopView;
    private dji.phone.g.a mEventHandler;
    private DJIGimbalRollFineTuneViewCompat mGimbalRollTuneView;
    public dji.phone.set.gimbalplan.b mGimbalRotationPlanPresenter;
    private DJIGridLineCompat mGridLine;
    private DJILPCameraLevel1MenuView mLevel1Menu;
    private DJILPCameraLevel2MenuView mLevel2Menu;
    private DJILPLivePresenter mLivePresenter;
    private DJILPLiveShareFpvView mLiveshareBar;
    public dji.phone.longexposure.b mLongExposurePresenter;
    private View mMenuBg;
    private DJILPMeterView mMeterLy;
    private DJILPCameraModuleSwitcher mModuleSwitchView;
    private DJILPPanoDisplayer mPanoLy;
    private dji.phone.pano.a mPanoPresenter;
    private a mPresenter;
    private FrameLayout mPreviewLy;
    private View mPreviewSwitcherBg;
    private ImageView mPreviewSwitcherIv;
    private DJILPPreviewTexture mPreviewTextureView;
    private DJILPCameraSetView mRightCamera;
    private DJILPGimbalSetView mRightGimbal;
    private DJILPCameraQuickSettingView mRightQuickSettingsView;
    private e mSwitchCameraAnim;
    private DJILPTimelapseSetView mTimelapseSetView;
    private dji.phone.tracking.a.a mTkPresenter;
    public d mTutoialPresenter;
    private DJILPUISwitcher mUISwitcherView;
    public View rootLayout;
    private e rotateAnimation;

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[dji.pilot.phonecamera.a.a.values().length];

        static {
            try {
                a[dji.pilot.phonecamera.a.a.h.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[dji.pilot.phonecamera.a.a.i.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        dji.phone.e.a.getInstance().a();
        this.mPresenter = a.getInstance();
        this.mPresenter.a(this);
        this.mEventHandler = new dji.phone.g.a(this);
        DJILogHelper.getInstance().LOGD("longanp view", "onCreate", false, true);
        initCamera();
        setContentView(R.layout.fpv_longan_phone);
        findView();
        initCameraView();
        initPresenter();
        c.a().a(this);
        initAnimation();
        initOthers();
    }

    private void initAnimation() {
        float f = getResources().getDisplayMetrics().density;
        a.getInstance();
        int i = a.e / 2;
        a.getInstance();
        int i2 = a.f / 2;
        this.mSwitchCameraAnim = new e(0.0f, 90.0f, (float) i, (float) i2, 400.0f, f, true);
        this.mSwitchCameraAnim.setDuration(700);
        this.mSwitchCameraAnim.setFillAfter(true);
        this.mSwitchCameraAnim.setInterpolator(new DecelerateInterpolator());
        this.mSwitchCameraAnim.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ DJILPPreviewActivity a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (this.a.mPreviewTextureView != null) {
                    Bitmap bitmap = this.a.mPreviewTextureView.getBitmap(160, 90);
                    if (bitmap != null) {
                        this.a.mPreviewSwitcherIv.setImageBitmap(bitmap);
                        this.a.mPreviewSwitcherIv.startAnimation(this.a.rotateAnimation);
                    }
                }
            }
        });
        this.rotateAnimation = new e(270.0f, 360.0f, (float) i, (float) i2, 400.0f, f, false);
        this.rotateAnimation.setDuration(200);
        this.rotateAnimation.setFillAfter(true);
        this.rotateAnimation.setInterpolator(new AccelerateInterpolator());
        this.rotateAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ DJILPPreviewActivity a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.hideCameraSwitcher();
                this.a.mCameraControlView.setSwitchLensBtnEnable(true);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void initOthers() {
        dji.phone.f.a.getInstance().a((Context) this);
        dji.phone.k.b.builder.a(getApplicationContext());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mGimbalRotationPlanPresenter != null) {
            this.mGimbalRotationPlanPresenter.a(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    private void findView() {
        this.mMeterLy = (DJILPMeterView) findViewById(R.id.lp_meter_ly);
        this.mMenuBg = findViewById(R.id.lp_menu_bg_mask_iv);
        this.mPreviewLy = (FrameLayout) findViewById(R.id.lp_preview_ly);
        this.mPreviewSwitcherIv = (ImageView) findViewById(R.id.video_previewer_iv);
        this.mPreviewSwitcherBg = findViewById(R.id.video_previewer_bg);
        this.mAEAFLockTv = (TextView) findViewById(R.id.lp_aeaf_lock_tv);
        this.mPanoLy = (DJILPPanoDisplayer) findViewById(R.id.lp_pano_displayer);
        this.mLevel1Menu = (DJILPCameraLevel1MenuView) findViewById(R.id.lp_level1_menu_layout);
        this.mLevel2Menu = (DJILPCameraLevel2MenuView) findViewById(R.id.lp_level2_menu_layout);
        this.mTimelapseSetView = (DJILPTimelapseSetView) findViewById(R.id.longan_timelapse2_ly);
        this.mRightCamera = (DJILPCameraSetView) findViewById(R.id.lp_camera_shotcuts_view);
        this.mRightGimbal = (DJILPGimbalSetView) findViewById(R.id.lp_gimbal_shortcut_view);
        this.mCameraControlView = (DJILPCameraControView) findViewById(R.id.lp_preview_cameracontrol);
        this.mRightQuickSettingsView = (DJILPCameraQuickSettingView) findViewById(R.id.lp_quickset_bar);
        this.mBLEView = (DJILPBleStatusView) findViewById(R.id.lp_ble_status_view);
        this.mModuleSwitchView = (DJILPCameraModuleSwitcher) findViewById(R.id.longan_camera_switch);
        this.mUISwitcherView = (DJILPUISwitcher) findViewById(R.id.lp_ui_switch_ly);
        this.mLiveshareBar = (DJILPLiveShareFpvView) findViewById(R.id.liveshare_bar);
        this.mErrorPopView = (DJIErrorPopViewCompat) findViewById(R.id.lp_error_popview);
    }

    private void initCamera() {
        dji.phone.c.a.a((Context) this);
        dji.phone.controview.b.getInstance().a();
    }

    private void initPresenter() {
        this.mCameraPresenter = new a(this.rootLayout, this);
        this.mModuleSwitchView.setSwitchCallback(this.mCameraPresenter);
        this.mGimbalRotationPlanPresenter = new dji.phone.set.gimbalplan.b(this, (DJILPGimbalRotationPlanView) findViewById(R.id.lp_gimbal_rotation_plan));
        this.mLongExposurePresenter = new dji.phone.longexposure.b(this, this.rootLayout);
        dji.phone.d.d.a((Context) this);
        if (!dji.pilot.set.a.e(this)) {
            this.mTutoialPresenter = new d(this);
            this.mTutoialPresenter.a();
        }
        this.mPanoPresenter = new dji.phone.pano.a(this, this.mPanoLy);
        this.mPanoLy.setLintener(this.mPanoPresenter);
        this.mPanoPresenter.a(this.mCameraPresenter);
        this.mLivePresenter = new DJILPLivePresenter(this, this.rootLayout);
        this.mLivePresenter.init();
        this.mLivePresenter.setLiveShareFpvView(this.mLiveshareBar);
    }

    public void showGimbalRollTune() {
        if (!dji.phone.bluetooth.c.getInstance().b()) {
            dji.phone.k.b.showLong(R.string.longan_error_notconnected);
        } else if (this.mGimbalRollTuneView == null) {
            ViewStub viewStub = (ViewStub) findViewById(R.id.longan_gimbal_roll_adjust_vs);
            if (viewStub != null) {
                this.mGimbalRollTuneView = (DJIGimbalRollFineTuneViewCompat) viewStub.inflate();
            }
        } else {
            this.mGimbalRollTuneView.show();
        }
    }

    public void onEventMainThread(dji.pilot.phonecamera.a.a aVar) {
        switch (AnonymousClass3.a[aVar.ordinal()]) {
            case 1:
                this.mAEAFLockTv.setVisibility(0);
                return;
            case 2:
                this.mAEAFLockTv.setVisibility(8);
                return;
            default:
                return;
        }
    }

    private void initCameraView() {
        this.rootLayout = getWindow().getDecorView().getRootView();
        this.mGridLine = (DJIGridLineCompat) findViewById(R.id.fpv_grid_line);
        this.mErrorPopView.dispatchOnCreate();
    }

    protected void onResume() {
        super.onResume();
        this.mDJILPGimbleInfoHandleAndDispatch = new b(this);
        refreshTrackingConfig();
        DJILogHelper.getInstance().LOGD("longanp view", "onResume", false, true);
        initCamera();
        dji.phone.c.a.c().a(dji.phone.j.d.getInstance().a());
        dji.phone.c.a.c().k();
        handleShowGrid(Integer.valueOf(dji.pilot.phonecamera.a.c.a().k()));
        this.mLongExposurePresenter.a((FrameLayout) this.rootLayout.findViewById(R.id.lp_preview_ly));
        this.mCameraPresenter.k();
        this.mLivePresenter.onResume();
        this.mPresenter.a();
    }

    protected void onPause() {
        super.onPause();
        this.mPresenter.b();
        if (!(AppPublicReflect.isRunning() || AppPublicReflect.isLaunch())) {
            releaseCamera();
        }
        this.mLivePresenter.onPause();
        this.mUISwitcherView.switchMode(dji.phone.g.b.AUTO);
        if (this.mDJILPGimbleInfoHandleAndDispatch != null) {
            this.mDJILPGimbleInfoHandleAndDispatch.a();
        }
    }

    public void releaseCamera() {
        this.mCameraPresenter.h();
        dji.phone.c.a.a();
    }

    public void resumeCamera() {
        initCamera();
        dji.phone.c.a.c().a(dji.phone.j.d.getInstance().a());
        dji.phone.c.a.c().k();
        this.mCameraPresenter.k();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mLivePresenter.dispose();
        dji.phone.e.a.getInstance().b();
        dji.phone.f.a.getInstance().a();
        dji.phone.c.a.a();
        dji.phone.j.d.getInstance().e();
        this.mPresenter.c();
        this.mCameraPresenter.i();
        this.mGimbalRotationPlanPresenter.a();
        if (this.mDJILPGimbleInfoHandleAndDispatch != null) {
            this.mDJILPGimbleInfoHandleAndDispatch.a();
            this.mDJILPGimbleInfoHandleAndDispatch = null;
        }
        dji.pilot.phonecamera.a.c.a().y();
        if (this.mTutoialPresenter != null) {
            this.mTutoialPresenter.d();
        }
        if (this.mTkPresenter != null) {
            this.mTkPresenter.a();
        }
        this.mErrorPopView.dispatchOnDestroy();
        this.mEventHandler.a();
        c.a().d(this);
        dji.phone.d.d.getInstance().a();
        this.mPanoPresenter.b();
    }

    private void refreshTrackingConfig() {
        switch (dji.pilot.set.a.b(this, g.n, 2)) {
            case 1:
                dji.phone.tracking.b.d = dji.phone.tracking.b.b / 2;
                dji.phone.tracking.b.c = dji.phone.tracking.b.a / 2;
                dji.phone.tracking.b.e = true;
                return;
            case 2:
                dji.phone.tracking.b.d = dji.phone.tracking.b.b;
                dji.phone.tracking.b.c = dji.phone.tracking.b.a;
                dji.phone.tracking.b.e = true;
                return;
            case 3:
                dji.phone.tracking.b.d = dji.phone.tracking.b.b;
                dji.phone.tracking.b.c = dji.phone.tracking.b.a;
                dji.phone.tracking.b.e = false;
                return;
            default:
                return;
        }
    }

    public FragmentManager getFragmentManager() {
        return super.getFragmentManager();
    }

    public void handleShowGrid(Integer num) {
        if (num.intValue() == 0) {
            this.mGridLine.go();
        } else if (dji.pilot.phonecamera.a.c.a().k() == 0) {
            this.mGridLine.go();
        } else {
            if (dji.pilot.phonecamera.a.c.a().k() == 1) {
                this.mGridLine.setType(1);
            } else if (dji.pilot.phonecamera.a.c.a().k() == 2) {
                this.mGridLine.setType(2);
            } else if (dji.pilot.phonecamera.a.c.a().k() == 3) {
                this.mGridLine.setType(4);
            }
            this.mGridLine.show();
        }
    }

    public void switchUIModeImg(dji.phone.g.b bVar) {
        if (bVar == dji.phone.g.b.TRACKING) {
            if (this.mTkPresenter == null) {
                this.mTkPresenter = new dji.phone.tracking.a.a(this);
                this.mTkPresenter.b();
            }
        } else if (this.mTkPresenter != null) {
            this.mTkPresenter.c();
            this.mTkPresenter.a();
            this.mTkPresenter = null;
        }
    }

    public void switchCameraID() {
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.mPreviewTextureView = new DJILPPreviewTexture(this);
        this.mPreviewTextureView.setId(R.id.video_previewer_surface);
        this.mPreviewLy.addView(this.mPreviewTextureView, layoutParams);
        this.mTkPresenter.c();
        this.mTkPresenter.a();
        this.mTkPresenter = null;
        this.mTkPresenter = new dji.phone.tracking.a.a(this);
        this.mTkPresenter.b();
    }

    public void handleActionSwitchCamera() {
        this.mPreviewTextureView = (DJILPPreviewTexture) this.mPreviewLy.findViewById(R.id.video_previewer_surface);
        startSwitchCameraAnim();
        if (this.mPreviewTextureView != null) {
            this.mPreviewTextureView.setVisibility(4);
            this.mPreviewLy.removeView(this.mPreviewTextureView);
        }
    }

    public void changeMeterPos(int i, int i2) {
        this.mMeterLy.setLimit(findViewById(R.id.lp_preview_cameracontrol).getRight(), findViewById(R.id.lp_quickset_bar).getLeft());
        this.mMeterLy.changeMeterPos(i, i2);
    }

    public void changeMenuBgVisible(int i) {
        this.mMenuBg.setVisibility(i);
    }

    public boolean isPopViewShown() {
        return this.mLevel1Menu.isShown() || this.mLevel2Menu.isShown() || this.mTimelapseSetView.isShown() || this.mRightCamera.isShown() || this.mRightGimbal.isShown();
    }

    public void hidePopView() {
        hideViews(this.mLevel1Menu, this.mLevel2Menu, this.mTimelapseSetView, this.mRightCamera, this.mRightGimbal);
    }

    private void hideViews(View... viewArr) {
        for (View view : viewArr) {
            if (view.isShown()) {
                view.setVisibility(4);
            }
        }
    }

    private void showViews(View... viewArr) {
        for (View view : viewArr) {
            if (!view.isShown()) {
                view.setVisibility(0);
            }
        }
    }

    public static void reflectPostEvent(String str) {
        if (!TextUtils.isEmpty(str) && "VIEW_GIMBAL_ROLL_TUNE".equals(str)) {
            c.a().e(new dji.phone.e.b((dji.phone.e.a.e) Enum.valueOf(dji.phone.e.a.e.class, str), dji.phone.e.a.c.f));
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        Log.d(this.TAG, "onSaveInstanceState: ");
        super.onSaveInstanceState(bundle);
        this.mDJILPGimbleInfoHandleAndDispatch.a();
    }

    public void startSwitchCameraAnim() {
        if (this.mPreviewTextureView != null) {
            this.mCameraControlView.setSwitchLensBtnEnable(false);
            Bitmap bitmap = this.mPreviewTextureView.getBitmap(320, 180);
            if (bitmap != null) {
                this.mPreviewSwitcherIv.setImageBitmap(bitmap);
                this.mPreviewSwitcherBg.setVisibility(0);
                this.mPreviewSwitcherIv.setVisibility(0);
                this.mPreviewSwitcherIv.startAnimation(this.mSwitchCameraAnim);
            }
        }
    }

    public void hideCameraSwitcher() {
        this.mPreviewSwitcherIv.clearAnimation();
        this.mPreviewSwitcherBg.setVisibility(4);
        this.mPreviewSwitcherIv.setVisibility(4);
    }

    public Bitmap peekPreview(int i, int i2) {
        DJILPPreviewTexture dJILPPreviewTexture = (DJILPPreviewTexture) this.mPreviewLy.findViewById(R.id.video_previewer_surface);
        if (dJILPPreviewTexture != null) {
            return dJILPPreviewTexture.getBitmap(i, i2);
        }
        return Bitmap.createBitmap(i, i2, Config.ARGB_8888);
    }

    public void setBrightness(float f) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.screenBrightness = f;
        getWindow().setAttributes(attributes);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 1021) {
            return;
        }
        if (i2 == -1) {
            if (DJILPBleStatusView.c != dji.phone.bluetooth.b.NOTCONNECTED) {
                return;
            }
            if (h.getInstance().isVisible()) {
                h.getInstance().dismiss();
            } else {
                h.getInstance().show(getFragmentManager(), null);
            }
        } else if (i2 != 0) {
        }
    }

    public void startPano(dji.phone.pano.d dVar, boolean z) {
        this.mPanoPresenter.a(dVar, z);
    }

    public void onBackPressed() {
        Log.d(this.TAG, "isLaunch = " + AppPublicReflect.isLaunch() + " isRunning = " + AppPublicReflect.isRunning());
        if (AppPublicReflect.isLaunch() || AppPublicReflect.isRunning()) {
            AppPublicReflect.handleLiveShare(this);
        } else if (!this.mPanoPresenter.h()) {
            finish();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 25 && i != 24 && i != 27) {
            return super.onKeyDown(i, keyEvent);
        }
        dji.phone.d.c.a c = dji.phone.d.d.getInstance().c();
        if (c == dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE_PREVIEW) {
            c.a().e(dji.phone.b.a.CMD_TAKEPICTURE);
        } else if (c == dji.phone.d.c.a.CAMERASTATE_RECORD_PREVIEW) {
            c.a().e(dji.phone.b.a.CMD_START_RECORD);
        } else if (c == dji.phone.d.c.a.CAMERASTATE_RECORDING) {
            c.a().e(dji.phone.b.a.CMD_STOP_RECORD);
        }
        return true;
    }
}
