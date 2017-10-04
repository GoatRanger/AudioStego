package dji.pilot.fpv.flightmode;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.RecordType;
import dji.midware.data.model.P3.DataCameraGetStateInfo.PhotoState;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.f;
import dji.pilot.publics.objects.g;
import dji.pilot.visual.util.a;
import dji.pilot.visual.util.c;
import dji.pilot2.simulator.d;

public class b {
    private static a a = null;
    private static final String b = "show_selfie_mode_info";

    public static boolean a(Context context) {
        boolean b = b(context);
        if (b) {
            return c.a(context, null);
        }
        return b;
    }

    public static boolean b(Context context) {
        if (d.h()) {
            dji.pilot.fpv.view.DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.fpv_flight_mode_failed_by_simulator, 0, DJIErrorPopView.c.a, f.a);
            return false;
        }
        DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
        if (instance.getVoltageWarning() == 2) {
            dji.pilot.fpv.view.DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.battery_low_warning, 0, DJIErrorPopView.c.a, f.a);
            return false;
        } else if (instance.getFlycState() == FLYC_STATE.GoHome || instance.getFlycState() == FLYC_STATE.AutoLanding) {
            dji.pilot.fpv.view.DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.gs_function_not_available, 0, DJIErrorPopView.c.a, f.a);
            return false;
        } else if (instance.getFlycState() != FLYC_STATE.Atti) {
            return a(context, true);
        } else {
            dji.pilot.fpv.view.DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.fpv_flight_mode_atti_tip, 0, DJIErrorPopView.c.a, f.a);
            return false;
        }
    }

    public static boolean a(Context context, boolean z) {
        int i;
        DataCameraGetPushStateInfo instance = DataCameraGetPushStateInfo.getInstance();
        if (dji.pilot.publics.e.a.c(i.getInstance().c())) {
            if (instance.getFirmUpgradeState()) {
                i = R.string.fpv_flight_mode_tracking_camera_upgrade;
            } else if (instance.getSensorState()) {
                i = R.string.fpv_flight_mode_tracking_sensor_error;
            } else if (instance.getMode() == MODE.TRANSCODE) {
                i = R.string.fpv_flight_mode_tracking_transcode;
            } else if (instance.getRecordState() == RecordType.START || instance.getRecordState() == RecordType.STARTING) {
                i = R.string.fpv_flight_mode_tracking_recording;
            } else if (instance.getIsTimePhotoing() || instance.getPhotoState() != PhotoState.NO) {
                i = R.string.fpv_flight_mode_tracking_takephoto;
            } else if (instance.getIsStoring()) {
                i = R.string.fpv_flight_mode_tracking_storing;
            }
            if (i != 0) {
                return true;
            }
            String string = context.getString(z ? R.string.fpv_flight_mode_enter : R.string.fpv_flight_mode_exit);
            dji.pilot.fpv.view.DJIErrorPopView.b.a(DJIErrorPopView.d.b, context.getString(i, new Object[]{string}), null, DJIErrorPopView.c.a, f.a);
            return false;
        }
        i = 0;
        if (i != 0) {
            return true;
        }
        if (z) {
        }
        String string2 = context.getString(z ? R.string.fpv_flight_mode_enter : R.string.fpv_flight_mode_exit);
        dji.pilot.fpv.view.DJIErrorPopView.b.a(DJIErrorPopView.d.b, context.getString(i, new Object[]{string2}), null, DJIErrorPopView.c.a, f.a);
        return false;
    }

    public static boolean a() {
        if (d.h()) {
            dji.pilot.fpv.view.DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.fpv_flight_mode_failed_by_simulator, 0, DJIErrorPopView.c.a, f.a);
            return false;
        }
        DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
        if (instance.getVoltageWarning() == 2) {
            dji.pilot.fpv.view.DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.battery_low_warning, 0, DJIErrorPopView.c.a, f.a);
            return false;
        } else if (instance.getFlycState() == FLYC_STATE.GoHome || instance.getFlycState() == FLYC_STATE.AutoLanding) {
            dji.pilot.fpv.view.DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.gs_function_not_available, 0, DJIErrorPopView.c.a, f.a);
            return false;
        } else if (instance.getFlycState() != FLYC_STATE.Atti) {
            return true;
        } else {
            dji.pilot.fpv.view.DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.fpv_flight_mode_atti_tip, 0, DJIErrorPopView.c.a, f.a);
            return false;
        }
    }

    public static boolean b() {
        DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
        if (instance.getVoltageWarning() == 2) {
            dji.pilot.fpv.view.DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.battery_low_warning, 0, DJIErrorPopView.c.a, f.a);
            return false;
        } else if (instance.getFlycState() == FLYC_STATE.NOVICE) {
            dji.pilot.fpv.view.DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.fpv_flight_mode_novice_tip, 0, DJIErrorPopView.c.a, f.a);
            return false;
        } else if (instance.getFlycState() == FLYC_STATE.GoHome || instance.getFlycState() == FLYC_STATE.AutoLanding) {
            dji.pilot.fpv.view.DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.gs_function_not_available, 0, DJIErrorPopView.c.a, f.a);
            return false;
        } else if (instance.getFlycState() == FLYC_STATE.Atti) {
            dji.pilot.fpv.view.DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.fpv_flight_mode_atti_tip, 0, DJIErrorPopView.c.a, f.a);
            return false;
        } else if (instance.isMotorUp() && instance.groundOrSky() == 2) {
            return true;
        } else {
            dji.pilot.fpv.view.DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.gs_not_take_off_warning_title, 0, DJIErrorPopView.c.a, f.a);
            return false;
        }
    }

    public static void a(final Context context, final Runnable runnable) {
        if (g.b(context, b, false)) {
            runnable.run();
            return;
        }
        a = new a(context);
        a.a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dji.midware.util.i.a(context, b.b, b.a.b());
                runnable.run();
            }
        }).show();
    }

    private b() {
    }
}
