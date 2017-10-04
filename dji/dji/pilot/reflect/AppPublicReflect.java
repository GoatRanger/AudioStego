package dji.pilot.reflect;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import dji.gs.b;
import dji.gs.c.e;
import dji.midware.data.config.P3.DeviceType;
import dji.pilot.R;
import dji.pilot.fpv.activity.DJIPreviewActivity;
import dji.pilot.fpv.control.DJIRedundancySysController;
import dji.pilot.fpv.control.k;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.groundStation.db.DJIWPCollectionItem;
import dji.pilot.groundStation.db.DJIWPCollectionItem$WayPoint;
import dji.pilot.liveshare.DJILiveShareActivity;
import dji.pilot.liveshare.Youtube.CustomModeActivity;
import dji.pilot.liveshare.Youtube.LiveshareActivity;
import dji.pilot.liveshare.Youtube.youtubeLiveActivity;
import dji.pilot.liveshare.b.a;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.usercenter.b.f;
import dji.thirdparty.a.c;

public class AppPublicReflect {
    private static Button mFBLiveButton;
    private static a mLiveStatusListener;
    private static Switch mYoutubeSwitch;

    public static class YoutubeChangeEvent {
    }

    public static void notifyConfigChange(String str) {
        SetReflect.notifyConfigChange(str);
    }

    public static String getDJIMemberManager_getUID() {
        return f.getInstance().o();
    }

    public static String getDJIMemberManager_getEmail() {
        return f.getInstance().j();
    }

    public static boolean getBuildConfig_DEBUG() {
        return false;
    }

    public static void dji_gs_Config_setUnitFT(boolean z) {
        b.a(z);
    }

    public static void dji_gs_utils_GpsUtils_OPEN(boolean z) {
        dji.gs.utils.a.a = z;
    }

    public static void handleParamUnitChanged(int i) {
        dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
        if (instance != null && instance.c()) {
            k i2 = instance.i();
            if (i2 != null) {
                e l = i2.l();
                if (l != null && l.m() != null && l.m().size() > 0) {
                    l.g();
                    DJIWPCollectionItem L = instance.L();
                    for (int i3 = 0; i3 < L.getPoints().size(); i3++) {
                        DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint = (DJIWPCollectionItem$WayPoint) L.getPoints().get(i3);
                        instance.i().l().g(dji.gs.utils.a.a(new dji.gs.e.b(dJIWPCollectionItem$WayPoint.getLat(), dJIWPCollectionItem$WayPoint.getLng())));
                    }
                }
            }
        }
    }

    public static void postVideoAllDeleteEvent() {
        c.a().e(dji.pilot2.library.a.VideoAllDelete);
    }

    public static double[] getLocation() {
        dji.gs.e.b k = k.k();
        double[] dArr = new double[3];
        if (k != null) {
            dArr[0] = k.b;
            dArr[1] = k.c;
            dArr[2] = (double) k.e;
        }
        return dArr;
    }

    public static String getAircraftVersion() {
        return dji.pilot.upgrade.e.getInstance().b();
    }

    public static String getAircraftLB2Version() {
        return dji.pilot.upgrade.e.getInstance().c();
    }

    public static String getCameraVersion() {
        return dji.pilot.upgrade.a.getInstance().b();
    }

    public static String getRcVersion() {
        return dji.pilot.upgrade.f.getInstance().b();
    }

    public static void GS_HOME_CIRCLE_EVENT_UPDATE() {
        c.a().e(k.a.UPDATE);
    }

    public static void showConnectWarning() {
        DJIPreviewActivity.e();
    }

    public static void DismissConnectWaning() {
        DJIPreviewActivity.f();
    }

    public static void postErrorModel(int i, int i2, int i3) {
        DJIErrorPopView.b bVar = new DJIErrorPopView.b();
        if (i3 == 0) {
            bVar.a = d.a;
        } else if (i3 == 1) {
            bVar.a = d.b;
        } else {
            bVar.a = d.a;
        }
        bVar.b = i;
        bVar.d = i2;
        c.a().e(bVar);
    }

    public static boolean isOpenAllChanel() {
        return dji.pilot.publics.c.a.a;
    }

    public static String getDM368Version() {
        return dji.pilot.publics.control.a.getInstance().e((DeviceType.DM368_G.value() * 100) + "");
    }

    public static void enterLiveActivity(final Context context, final Integer num) {
        Log.d("", "enterLiveActivity");
        Intent intent;
        if (!dji.pilot.liveshare.b.getInstance().isRunning() && !dji.pilot.liveshare.b.getInstance().isLaunch()) {
            intent = new Intent();
            intent.putExtra("type", num);
            intent.setClass(context, DJILiveShareActivity.class);
            context.startActivity(intent);
        } else if (num.equals(Integer.valueOf(dji.pilot.f.a.a.D))) {
            intent = new Intent();
            intent.putExtra("type", num);
            intent.setClass(context, DJILiveShareActivity.class);
            context.startActivity(intent);
        } else {
            new dji.pilot.publics.widget.b(context).a((int) R.string.app_warning).b((int) R.string.liveshare_base_exit).d((int) R.string.app_enter).e(R.string.app_cancel).a(new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    dji.pilot.liveshare.b.getInstance().stopStream();
                    c.a().e(new dji.pilot.f.a.a(16));
                    Intent intent = new Intent();
                    intent.putExtra("type", num);
                    intent.setClass(context, DJILiveShareActivity.class);
                    context.startActivity(intent);
                }
            }).b(new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
    }

    public static void stopLiveShare(Activity activity) {
        Log.d("", "forceStopShare: ");
        if (dji.pilot.liveshare.b.getInstance().getStreamMode() == 0) {
            dji.pilot.liveshare.b.getInstance().stopStream();
            dji.pilot.liveshare.b.getInstance().setLaunch(false);
            dji.pilot.liveshare.Youtube.a.getInstance().finishYTBroadcast();
            c.a().e(new dji.pilot.f.a.a(dji.pilot.f.a.a.y));
            dji.pilot.liveshare.b.getInstance().stopStream();
        } else if (dji.pilot.liveshare.b.getInstance().getStreamMode() == 2) {
            dji.pilot.liveshare.Weibo.a.b.getInstance(activity).updateStreamStatus();
            dji.pilot.liveshare.b.getInstance().stopStream();
            c.a().e(new dji.pilot.f.a.a(8));
        }
    }

    public static void handleLiveShare(Context context) {
        Log.d("", "handleLiveShare: streamMode = " + dji.pilot.liveshare.b.getInstance().getStreamMode());
        Intent intent;
        if (dji.pilot.liveshare.b.getInstance().getStreamMode() == 0) {
            intent = new Intent();
            intent.setClass(context, youtubeLiveActivity.class);
            context.startActivity(intent);
        } else if (dji.pilot.liveshare.b.getInstance().getStreamMode() == 1) {
            intent = new Intent();
            intent.setClass(context, CustomModeActivity.class);
            context.startActivity(intent);
        } else if (dji.pilot.liveshare.b.getInstance().getStreamMode() == 2) {
            intent = new Intent();
            intent.setClass(context, DJILiveShareActivity.class);
            intent.putExtra("type", dji.pilot.f.a.a.D);
            context.startActivity(intent);
        }
    }

    public static void enterYoutubeLive(final Context context) {
        Intent intent;
        if (!dji.pilot.liveshare.b.getInstance().isLaunch() || !dji.pilot.liveshare.b.getInstance().isRunning()) {
            intent = new Intent();
            intent.setClass(context, LiveshareActivity.class);
            context.startActivity(intent);
        } else if (dji.pilot.f.a.a.D != 1) {
            new dji.pilot.publics.widget.b(context).a((int) R.string.app_warning).b((int) R.string.liveshare_base_exit).d((int) R.string.app_enter).e(R.string.app_cancel).a(new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    dji.pilot.liveshare.b.getInstance().stopStream();
                    c.a().e(new dji.pilot.f.a.a(16));
                    Intent intent = new Intent();
                    intent.setClass(context, LiveshareActivity.class);
                    context.startActivity(intent);
                }
            }).b(new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        } else if (dji.pilot.liveshare.b.getInstance().getStreamMode() == 0) {
            intent = new Intent();
            intent.setClass(context, youtubeLiveActivity.class);
            context.startActivity(intent);
        } else if (dji.pilot.liveshare.b.getInstance().getStreamMode() == 1) {
            intent = new Intent();
            intent.setClass(context, CustomModeActivity.class);
            context.startActivity(intent);
        }
    }

    public static void youtubeInit(Switch switchR) {
        mYoutubeSwitch = switchR;
        mLiveStatusListener = new a() {
            public void onStatusChanged(int i) {
                if (i == 0) {
                    AppPublicReflect.mYoutubeSwitch.setChecked(dji.pilot.liveshare.b.getInstance().isRunning());
                }
            }
        };
        dji.pilot.liveshare.b.getInstance().registerListener(mLiveStatusListener);
        mYoutubeSwitch.setChecked(dji.pilot.liveshare.b.getInstance().isRunning());
        mYoutubeSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_Switcher_YouTubeLiveStreaming_ON");
                    if (!dji.pilot.liveshare.b.getInstance().isRunning()) {
                        Intent intent = new Intent();
                        intent.setClass(AppPublicReflect.mYoutubeSwitch.getContext(), LiveshareActivity.class);
                        AppPublicReflect.mYoutubeSwitch.getContext().startActivity(intent);
                        AppPublicReflect.mYoutubeSwitch.setChecked(false);
                        return;
                    }
                    return;
                }
                dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_Switcher_YouTubeLiveStreaming_OFF");
                dji.pilot.liveshare.b.getInstance().stopStream();
                if (dji.pilot.liveshare.b.getInstance().getStreamMode() == 0) {
                    dji.pilot.liveshare.Youtube.a.getInstance().finishYTBroadcast();
                }
                c.a().e(new YoutubeChangeEvent());
            }
        });
    }

    public static void youtubeUnInit() {
        dji.pilot.liveshare.b.getInstance().registerListener(mLiveStatusListener);
        mLiveStatusListener = null;
        mYoutubeSwitch = null;
    }

    public static void facebookInit(Button button) {
        mFBLiveButton = button;
        mLiveStatusListener = new a() {
            public void onStatusChanged(int i) {
                if (i != 0) {
                    return;
                }
                if (dji.pilot.liveshare.b.getInstance().isRunning()) {
                    AppPublicReflect.mFBLiveButton.setText(AppPublicReflect.mFBLiveButton.getContext().getString(R.string.setting_ui_general_facebook_stop));
                } else {
                    AppPublicReflect.mFBLiveButton.setText(AppPublicReflect.mFBLiveButton.getContext().getString(R.string.setting_ui_general_platform_facebook));
                }
            }
        };
        if (dji.pilot.liveshare.b.getInstance().isRunning()) {
            mFBLiveButton.setText(mFBLiveButton.getContext().getString(R.string.setting_ui_general_facebook_stop));
        } else {
            mFBLiveButton.setText(mFBLiveButton.getContext().getString(R.string.setting_ui_general_platform_facebook));
        }
        dji.pilot.liveshare.b.getInstance().registerListener(mLiveStatusListener);
    }

    public static void facebookUnInit() {
        dji.pilot.liveshare.b.getInstance().unregisterListener(mLiveStatusListener);
        mLiveStatusListener = null;
        mFBLiveButton = null;
    }

    public static void enterFacebookLive() {
        if (dji.pilot.liveshare.b.getInstance().isRunning()) {
            dji.pilot.liveshare.b.getInstance().stopStream();
            return;
        }
        Intent intent = new Intent();
        intent.setClass(mFBLiveButton.getContext(), DJILiveShareActivity.class);
        mFBLiveButton.getContext().startActivity(intent);
    }

    public static void sensorPopWindow(DJIRedundancySysController.c cVar) {
        DJIErrorPopView.b bVar = new DJIErrorPopView.b();
        bVar.a = d.b;
        bVar.f = DJIErrorPopView.c.a;
        bVar.c = cVar.d.in_air_used_action;
        bVar.e = String.format("[%s]%s", new Object[]{cVar.b, cVar.d.usr_err_tips});
        c.a().e(bVar);
    }

    public static void sensorPopWindow(Integer num) {
        DJIErrorPopView.b bVar = new DJIErrorPopView.b();
        bVar.a = d.b;
        bVar.f = DJIErrorPopView.c.a;
        bVar.b = num.intValue();
        c.a().e(bVar);
    }

    public static String getAppVersion() {
        return DJIApplication.a().getResources().getString(R.string.versionname);
    }

    public static Boolean isFactoryMode() {
        return Boolean.valueOf(false);
    }

    public static long getStreamTime() {
        return dji.pilot.liveshare.b.getInstance().getStreamTime();
    }

    public static boolean isRunning() {
        return dji.pilot.liveshare.b.getInstance().isRunning();
    }

    public static boolean isLaunch() {
        return dji.pilot.liveshare.b.getInstance().isLaunch();
    }
}
