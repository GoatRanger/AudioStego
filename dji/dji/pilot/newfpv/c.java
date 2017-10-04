package dji.pilot.newfpv;

import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataRcSetCustomFuction.DJICustomType;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.newfpv.f.d;
import dji.pilot.newfpv.view.b.a;

public class c extends b {

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[DJICustomType.values().length];

        static {
            try {
                a[DJICustomType.r.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[DJICustomType.s.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public void onEventMainThread(d dVar) {
        if (dVar == d.SHOW_ALL_VIEWS) {
            a(new a[]{a.ViewId_SimAtti, a.ViewId_WholeAtti, a.ViewId_GroupAttiView, a.ViewId_CameraControl}, new boolean[]{true, true, true, true});
        } else if (dVar == d.HIDE_ALL_VIEWS) {
            a(new a[]{a.ViewId_SimAtti, a.ViewId_WholeAtti, a.ViewId_WifiFlightMode, a.ViewId_GroupAttiView, a.ViewId_CameraControl, a.ViewId_CameraMenu}, new boolean[]{false, false, false, false, false, false});
        } else if (dVar == d.CAMERA_SETTING_SHOW) {
        } else {
            if (dVar == d.CAMERA_SETTING_HIDE) {
                a(new a[]{a.ViewId_SimAtti, a.ViewId_WholeAtti}, new boolean[]{true, true});
            } else if (dVar == d.LEFT_JOYSTICK_SHOW) {
                a(new a[]{a.ViewId_WifiFlightMode, a.ViewId_CameraMenu}, new boolean[]{false, false});
            } else if (dVar == d.LEFT_JOYSTICK_HIDE) {
            } else {
                if (dVar == d.RIGHT_JOYSTICK_SHOW) {
                    a(new a[]{a.ViewId_WifiFlightMode, a.ViewId_CameraMenu}, new boolean[]{false, false});
                } else if (dVar == d.RIGHT_JOYSTICK_HIDE) {
                } else {
                    if (dVar == d.TOUCH_BLANK_PLACE) {
                        a(new a[]{a.ViewId_WifiFlightMode, a.ViewId_CameraMenu}, new boolean[]{false, false});
                    } else if (d.CAMERACTRL_MENU == dVar) {
                        if (dji.pilot2.simulator.d.h()) {
                            b bVar = new b();
                            bVar.a = DJIErrorPopView.d.b;
                            bVar.b = R.string.v2_smlt_not_support_camera_setting;
                            dji.thirdparty.a.c.a().e(bVar);
                            return;
                        }
                        this.a.t();
                    } else if (d.CAMERACTRL_ENTER_PB == dVar) {
                        if (dji.pilot.fpv.control.b.p) {
                            dji.pilot.fpv.control.b.p = false;
                            this.a.r();
                            return;
                        }
                        DataCameraSetMode.getInstance().a(dji.pilot.c.d.a).start(null);
                        DJILogHelper.getInstance().LOGD("playback", "onGoPlayBackMode tochangemode " + dji.pilot.c.d.a, false, true);
                    } else if (d.CAMERACTRL_EXIT_PB == dVar) {
                        this.a.s();
                    }
                }
            }
        }
    }

    public void onEventMainThread(dji.pilot.newfpv.f.c cVar) {
        String a;
        if (dji.pilot.newfpv.f.c.SHOW == cVar) {
            this.a.c(true);
            a = dji.pilot.fpv.model.b.a();
            if (!"large".equals(a) && !"xlarge".equals(a)) {
                a(new a[]{a.ViewId_TopBar}, new boolean[]{false});
            }
        } else if (dji.pilot.newfpv.f.c.HIDE == cVar) {
            this.a.c(false);
            a = dji.pilot.fpv.model.b.a();
            if (!"large".equals(a) && !"xlarge".equals(a)) {
                a(new a[]{a.ViewId_TopBar}, new boolean[]{true});
            }
        }
    }

    public void onEventBackgroundThread(DJICustomType dJICustomType) {
        switch (AnonymousClass1.a[dJICustomType.ordinal()]) {
            case 1:
                dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
                if (instance == null || !instance.q()) {
                    a(new a[]{a.ViewId_SimAtti, a.ViewId_WholeAtti, a.ViewId_GroupAttiView, a.ViewId_CameraControl}, new boolean[]{true, true, true, false});
                    return;
                }
                a(new a[]{a.ViewId_SimAtti, a.ViewId_WholeAtti, a.ViewId_GroupAttiView, a.ViewId_CameraControl, a.ViewId_CameraMenu}, new boolean[]{false, false, false, false, false, false});
                return;
            case 2:
                a(new a[]{a.ViewId_SimAtti, a.ViewId_WholeAtti, a.ViewId_GroupAttiView, a.ViewId_CameraControl}, new boolean[]{true, true, true, true});
                return;
            default:
                return;
        }
    }
}
