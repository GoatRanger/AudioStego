package dji.phone.live;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import dji.apppublic.reflect.AppPublicReflect;
import dji.device.widget.popview.DJIErrorPopViewCompat;
import dji.device.widget.popview.DJIErrorPopViewCompat.f;
import dji.phone.controview.DJILPCameraControView;
import dji.phone.controview.DJILPCameraModuleSwitcher;
import dji.phone.d.a.b;
import dji.phone.d.d;
import dji.phone.e.a.e;
import dji.phone.preview.DJILPPreviewActivity;
import dji.phone.rightbar.DJILPCameraQuickSettingView;
import dji.pilot.f.a.a;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class DJILPLivePresenter {
    private static boolean liveButtonPressed = false;
    public static DJILPPreviewActivity mActivity;
    private final int MSG_LIVE_STOP = 1;
    private String TAG = "DJILPLivePresenter";
    private boolean isOtherLiving = false;
    private boolean isPopNotifyInfo = false;
    private boolean isYoutubeLiving = false;
    private Handler mHandler = new Handler(this) {
        final /* synthetic */ DJILPLivePresenter a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    Log.d(this.a.TAG, "handleMessage: MSG_LIVE_STOP");
                    AppPublicReflect.stopLiveShare(DJILPLivePresenter.mActivity);
                    DJILPLivePresenter.mActivity.releaseCamera();
                    return;
                default:
                    return;
            }
        }
    };
    private DJILPLiveShareFpvView mLiveshareBar;
    private View rootLayout;

    public DJILPLivePresenter(DJILPPreviewActivity dJILPPreviewActivity, View view) {
        mActivity = dJILPPreviewActivity;
        this.rootLayout = view;
    }

    public void init() {
        c.a().a(this);
    }

    public void onEventBackgroundThread(a aVar) {
        if (aVar.I == 8 || aVar.I == 16 || aVar.I == 5 || aVar.I == 6) {
            Log.d(this.TAG, "onEventBackgroundThread: LiveEvent = " + aVar.I);
            if (aVar.I == 8 || aVar.I == 16) {
                this.isOtherLiving = false;
            }
            if (aVar.I == 5 || aVar.I == 6) {
                this.isOtherLiving = true;
            }
            mActivity.runOnUiThread(new Runnable(this) {
                final /* synthetic */ DJILPLivePresenter a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.handleLivestreamEvent();
                }
            });
        }
        if (aVar.I == a.x || aVar.I == a.y) {
            Log.d(this.TAG, "onEventBackgroundThread: LiveEvent = " + aVar.I);
            if (aVar.I == a.x) {
                this.isYoutubeLiving = true;
            }
            if (aVar.I == a.y) {
                this.isYoutubeLiving = false;
                setliveButtonState(false);
            }
            mActivity.runOnUiThread(new Runnable(this) {
                final /* synthetic */ DJILPLivePresenter a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.handleLivestreamEvent();
                }
            });
        }
    }

    public static void setliveButtonState(boolean z) {
        liveButtonPressed = z;
    }

    public void onResume() {
        Log.d(this.TAG, "onResume: ");
        this.mHandler.postDelayed(new Runnable(this) {
            final /* synthetic */ DJILPLivePresenter a;

            {
                this.a = r1;
            }

            public void run() {
                if (AppPublicReflect.isRunning() && !this.a.isYoutubeLiving && !this.a.isOtherLiving) {
                    AppPublicReflect.stopLiveShare(DJILPLivePresenter.mActivity);
                }
            }
        }, 1000);
        if (liveButtonPressed) {
            setliveButtonState(false);
        }
        if (this.mHandler.hasMessages(1)) {
            this.mHandler.removeMessages(1);
        }
        this.mHandler.postDelayed(new Runnable(this) {
            final /* synthetic */ DJILPLivePresenter a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.resetPopNotifyInfoFlag();
            }
        }, 1000);
    }

    private void resetPopNotifyInfoFlag() {
        if (!AppPublicReflect.isLaunch() && !AppPublicReflect.isRunning()) {
            this.isPopNotifyInfo = false;
        }
    }

    public void onPause() {
        Log.d(this.TAG, "onPause: liveButtonPressed = " + liveButtonPressed);
        if (!liveButtonPressed && AppPublicReflect.isLaunch() && AppPublicReflect.isRunning()) {
            this.mHandler.sendEmptyMessageDelayed(1, 30000);
        }
    }

    public static void enterLiveShare(Integer num) {
        Log.d("DJILPLivePresenter", "enterLiveShare: ");
        if (mActivity == null) {
            Log.d("DJILPLivePresenter", "enterLiveShare: Activity is null");
            return;
        }
        setliveButtonState(false);
        mActivity.resumeCamera();
        AppPublicReflect.enterLiveShare(mActivity, num.intValue());
    }

    public static void enterYoutubeLive() {
        Log.d("DJILPLivePresenter", "enterYoutubeLive: ");
        if (mActivity == null) {
            Log.d("DJILPLivePresenter", "enterYoutubeLive: Activity is null");
            return;
        }
        setliveButtonState(false);
        mActivity.resumeCamera();
        AppPublicReflect.enterYoutubeLive(mActivity);
    }

    private void handleLivestreamEvent() {
        Log.d(this.TAG, "handleLivestreamEvent: isLaunch = " + AppPublicReflect.isLaunch());
        if (AppPublicReflect.isLaunch()) {
            if (dji.phone.c.a.c().a() == 1) {
                this.mHandler.postDelayed(new Runnable(this) {
                    final /* synthetic */ DJILPLivePresenter a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        DJILPLivePresenter.mActivity.mCameraPresenter.a(0);
                        this.a.enableOrDisableCameraMode(false);
                    }
                }, 1000);
            }
            this.mLiveshareBar.setVisibility(0);
            hideOrshowSomeUI(false);
            enableOrDisableCameraMode(false);
            resetPhotoOrRecordingSettings();
            popNotifyInfo();
        } else {
            this.mLiveshareBar.setVisibility(8);
            hideOrshowSomeUI(true);
            enableOrDisableCameraMode(true);
        }
        this.mLiveshareBar.handleEvent();
    }

    private void resetPhotoOrRecordingSettings() {
        dji.phone.d.c.a c = d.getInstance().c();
        if (c == dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE_PREVIEW || c == dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE_PANO_PREVIEW || c == dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE) {
            d.getInstance().a(b.SINGLE, true);
            d.getInstance().a(dji.phone.d.a.a.SINGLE_0s, true);
            c.a().e(new dji.phone.e.b(e.BTN_CAMERA_MODE, dji.phone.e.a.c.e));
            return;
        }
        dji.pilot.phonecamera.a.c.a().a(0.0f);
        dji.pilot.phonecamera.a.c.a().n(0);
        d.getInstance().a(dji.phone.d.a.c.AUTO, true);
        c.a().e(new dji.phone.e.b(e.BTN_CAMERA_MODE, dji.phone.e.a.c.e));
    }

    private void popNotifyInfo() {
        Log.d(this.TAG, "popNotifyInfo: isLaunch = " + AppPublicReflect.isLaunch() + " isRunning = " + AppPublicReflect.isRunning());
        DJIErrorPopViewCompat.b bVar;
        if (AppPublicReflect.isLaunch() && !AppPublicReflect.isRunning()) {
            bVar = new DJIErrorPopViewCompat.b();
            bVar.a = DJIErrorPopViewCompat.d.NOTIFY;
            bVar.b = R.string.lp_live_pop_text_connecting;
            c.a().e(bVar);
        } else if (AppPublicReflect.isRunning()) {
            bVar = new DJIErrorPopViewCompat.b();
            bVar.a = DJIErrorPopViewCompat.d.NOTIFY;
            bVar.b = R.string.lp_live_pop_text_connecting;
            bVar.g = f.REMOVE;
            c.a().e(bVar);
            Log.d(this.TAG, "popNotifyInfo: isPopNotifyInfo = " + this.isPopNotifyInfo);
            if (!this.isPopNotifyInfo) {
                bVar = new DJIErrorPopViewCompat.b();
                bVar.a = DJIErrorPopViewCompat.d.NOTIFY;
                bVar.b = R.string.lp_live_pop_text_connected;
                c.a().e(bVar);
                this.isPopNotifyInfo = true;
            }
        }
    }

    private void hideOrshowSomeUI(boolean z) {
        DJILPCameraQuickSettingView dJILPCameraQuickSettingView = (DJILPCameraQuickSettingView) this.rootLayout.findViewById(R.id.lp_quickset_bar);
        if (z) {
            dJILPCameraQuickSettingView.setVisibility(0);
        } else {
            dJILPCameraQuickSettingView.setVisibility(8);
        }
    }

    private void enableOrDisableCameraMode(boolean z) {
        DJILPCameraControView dJILPCameraControView = (DJILPCameraControView) this.rootLayout.findViewById(R.id.lp_preview_cameracontrol);
        View findViewById = dJILPCameraControView.findViewById(R.id.longan_camera_mode_iv);
        ((DJILPCameraModuleSwitcher) dJILPCameraControView.findViewById(R.id.longan_camera_switch)).setEnabled(z);
        setEnabledView(findViewById, z);
    }

    private void setEnabledView(View view, boolean z) {
        if (view != null) {
            view.setEnabled(z);
        }
    }

    public void setLiveShareFpvView(DJILPLiveShareFpvView dJILPLiveShareFpvView) {
        this.mLiveshareBar = dJILPLiveShareFpvView;
        this.mLiveshareBar.setPresenter(this);
    }

    public void dispose() {
        c.a().d(this);
        mActivity = null;
    }
}
