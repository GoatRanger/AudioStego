package dji.sdksharedlib.hardware.abstractions.e;

import android.util.Log;
import com.google.android.gms.common.api.CommonStatusCodes;
import dji.common.error.DJIGimbalError;
import dji.common.gimbal.DJIGimbalAxis;
import dji.common.gimbal.DJIGimbalBalanceTestResult;
import dji.common.gimbal.DJIGimbalCapabilityKey;
import dji.common.gimbal.DJIGimbalEndpointDirection;
import dji.common.gimbal.DJIGimbalMotorControlPreset;
import dji.common.util.LatchHelper;
import dji.midware.data.model.P3.DataGimbalRoninGetUserParams;
import dji.midware.data.model.P3.DataGimbalRoninSetUserParams;
import dji.midware.data.model.P3.DataGimbalSetUserParams;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.a;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.b.f;

public class h extends d {
    private static final String[] m = new String[]{"ronin_sensibility_yaw", "ronin_sensibility_pitch", "ronin_sensibility_roll", "ronin_strength_yaw", "ronin_strength_pitch", "ronin_strength_roll", "ronin_filter_yaw", "ronin_filter_pitch", "ronin_filter_roll", "ronin_feedback_yaw", "ronin_feedback_pitch", "ronin_feedback_roll", "ronin_pitch_up", "ronin_pitch_down", "ronin_yaw_left", "ronin_yaw_right", "pitch_dead_zone", "yaw_dead_zone", "pitch_expo", "yaw_expo", "pitch_time_expo", "yaw_time_expo", "system_calc", "balance_test"};
    boolean a = false;
    private LatchHelper l = LatchHelper.getInstance();
    private final int[][] n = new int[][]{new int[]{63, 75, 70, 40, 40, 25, 100, 100, 100, 20, 20, 60}, new int[]{55, 45, 45, 40, 40, 40, 100, 100, 100, 20, 60, 60}};

    public void a(String str, int i, c cVar, f fVar) {
        super.a(str, i, cVar, fVar);
        b();
    }

    protected void a() {
        super.a();
    }

    public void b() {
        super.b();
        a(DJIGimbalCapabilityKey.AdjustPitch, Integer.valueOf(-135), Integer.valueOf(45));
        a(DJIGimbalCapabilityKey.AdjustYaw, Integer.valueOf(-179), Integer.valueOf(179));
        a(DJIGimbalCapabilityKey.ControllerSpeedPitch, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.ControllerSpeedYaw, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.ControllerSmoothingPitch, Integer.valueOf(0), Integer.valueOf(30));
        a(DJIGimbalCapabilityKey.ControllerSmoothingYaw, Integer.valueOf(0), Integer.valueOf(30));
        a(DJIGimbalCapabilityKey.ControllerDeadbandPitch, Integer.valueOf(0), Integer.valueOf(90));
        a(DJIGimbalCapabilityKey.ControllerDeadbandYaw, Integer.valueOf(0), Integer.valueOf(90));
        a(DJIGimbalCapabilityKey.SmoothTrackSpeedPitch, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.SmoothTrackSpeedYaw, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.SmoothTrackDeadbandPitch, Integer.valueOf(0), Integer.valueOf(90));
        a(DJIGimbalCapabilityKey.SmoothTrackDeadbandYaw, Integer.valueOf(0), Integer.valueOf(90));
        a(DJIGimbalCapabilityKey.EndpointPitchUp, Integer.valueOf(0), Integer.valueOf(45));
        a(DJIGimbalCapabilityKey.EndpointPitchDown, Integer.valueOf(0), Integer.valueOf(135));
        a(DJIGimbalCapabilityKey.EndpointYawLeft, Integer.valueOf(0), Integer.valueOf(179));
        a(DJIGimbalCapabilityKey.EndpointYawRight, Integer.valueOf(0), Integer.valueOf(179));
        a(DJIGimbalCapabilityKey.MotorControlStiffnessPitch, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.MotorControlStiffnessRoll, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.MotorControlStiffnessYaw, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.MotorControlStrengthPitch, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.MotorControlStrengthRoll, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.MotorControlStrengthYaw, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.MotorControlGyroFilteringPitch, Integer.valueOf(0), Integer.valueOf(99));
        a(DJIGimbalCapabilityKey.MotorControlGyroFilteringRoll, Integer.valueOf(0), Integer.valueOf(99));
        a(DJIGimbalCapabilityKey.MotorControlGyroFilteringYaw, Integer.valueOf(0), Integer.valueOf(99));
        a(DJIGimbalCapabilityKey.MotorControlPrecontrolPitch, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.MotorControlPrecontrolRoll, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.MotorControlPrecontrolYaw, Integer.valueOf(0), Integer.valueOf(100));
    }

    protected void a(int i, int i2, e eVar) {
        if (i >= 0 && i < m.length) {
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                    if (!a(Integer.valueOf(i2), Integer.valueOf(1), Integer.valueOf(100), eVar)) {
                        return;
                    }
                    break;
                case 12:
                    if (!a(Integer.valueOf(i2), Integer.valueOf(0), Integer.valueOf(45), eVar)) {
                        return;
                    }
                    break;
                case 13:
                    if (!a(Integer.valueOf(i2), Integer.valueOf(0), Integer.valueOf(135), eVar)) {
                        return;
                    }
                    break;
                case 14:
                    if (!a(Integer.valueOf(i2), Integer.valueOf(0), Integer.valueOf(179), eVar)) {
                        return;
                    }
                    break;
                case 15:
                    if (!a(Integer.valueOf(i2), Integer.valueOf(0), Integer.valueOf(179), eVar)) {
                        return;
                    }
                    break;
                case 16:
                    if (!a(Integer.valueOf(i2), Integer.valueOf(0), Integer.valueOf(90), eVar)) {
                        return;
                    }
                    break;
                case 17:
                    if (!a(Integer.valueOf(i2), Integer.valueOf(0), Integer.valueOf(90), eVar)) {
                        return;
                    }
                    break;
                case 18:
                    if (!a(Integer.valueOf(i2), Integer.valueOf(0), Integer.valueOf(100), eVar)) {
                        return;
                    }
                    break;
                case 19:
                    if (!a(Integer.valueOf(i2), Integer.valueOf(0), Integer.valueOf(100), eVar)) {
                        return;
                    }
                    break;
                case 20:
                    if (!a(Integer.valueOf(i2), Integer.valueOf(0), Integer.valueOf(30), eVar)) {
                        return;
                    }
                    break;
                case 21:
                    if (!a(Integer.valueOf(i2), Integer.valueOf(0), Integer.valueOf(30), eVar)) {
                        return;
                    }
                    break;
            }
            DataGimbalSetUserParams.getInstance().a(CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS);
            DataGimbalRoninSetUserParams.getInstance().a(m[i], Integer.valueOf(i2)).start(new 1(this, eVar));
        }
    }

    @a(a = "MotorControl")
    public void a(e eVar, DJIGimbalMotorControlPreset dJIGimbalMotorControlPreset) {
        this.l.setUpLatch(12);
        this.a = true;
        for (int i = 0; i < 12; i++) {
            DataGimbalRoninSetUserParams.getInstance().a(m[i], Integer.valueOf(this.n[dJIGimbalMotorControlPreset.ordinal()][i])).start(new 3(this));
        }
        this.l.waitForLatch(60);
        if (this.a) {
            eVar.a((Object) dJIGimbalMotorControlPreset);
        } else {
            eVar.a(DJIGimbalError.GIMBAL_RESULT_FAILED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MotorControlStiffnessPitch")
    public void k(int i, e eVar) {
        a(i, DJIGimbalAxis.Pitch.ordinal(), eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MotorControlStiffnessRoll")
    public void l(int i, e eVar) {
        a(i, DJIGimbalAxis.Roll.ordinal(), eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MotorControlStiffnessYaw")
    public void m(int i, e eVar) {
        a(i, DJIGimbalAxis.Yaw.ordinal(), eVar);
    }

    private void C(int i, e eVar) {
        DataGimbalRoninGetUserParams.getInstance().setInfos(new String[]{m[i]}).start(new 4(this, i, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MotorControlStiffnessPitch")
    public void B(e eVar) {
        C(DJIGimbalAxis.Pitch.ordinal(), eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MotorControlStiffnessRoll")
    public void C(e eVar) {
        C(DJIGimbalAxis.Roll.ordinal(), eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MotorControlStiffnessYaw")
    public void D(e eVar) {
        C(DJIGimbalAxis.Yaw.ordinal(), eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MotorControlStrengthPitch")
    public void n(int i, e eVar) {
        a(i, DJIGimbalAxis.Pitch.ordinal() + 3, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MotorControlStrengthRoll")
    public void o(int i, e eVar) {
        a(i, DJIGimbalAxis.Roll.ordinal() + 3, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MotorControlStrengthYaw")
    public void p(int i, e eVar) {
        a(i, DJIGimbalAxis.Yaw.ordinal() + 3, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MotorControlStrengthPitch")
    public void E(e eVar) {
        C(DJIGimbalAxis.Pitch.ordinal() + 3, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MotorControlStrengthRoll")
    public void F(e eVar) {
        C(DJIGimbalAxis.Roll.ordinal() + 3, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MotorControlStrengthYaw")
    public void G(e eVar) {
        C(DJIGimbalAxis.Yaw.ordinal() + 3, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MotorControlGyroFilteringPitch")
    public void q(int i, e eVar) {
        a(i, DJIGimbalAxis.Pitch.ordinal() + 6, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MotorControlGyroFilteringRoll")
    public void r(int i, e eVar) {
        a(i, DJIGimbalAxis.Roll.ordinal() + 6, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MotorControlGyroFilteringYaw")
    public void s(int i, e eVar) {
        a(i, DJIGimbalAxis.Yaw.ordinal() + 6, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MotorControlGyroFilteringPitch")
    public void H(e eVar) {
        C(DJIGimbalAxis.Pitch.ordinal() + 6, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MotorControlGyroFilteringRoll")
    public void I(e eVar) {
        C(DJIGimbalAxis.Roll.ordinal() + 6, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MotorControlGyroFilteringYaw")
    public void J(e eVar) {
        C(DJIGimbalAxis.Yaw.ordinal() + 6, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MotorControlPrecontrolPitch")
    public void t(int i, e eVar) {
        a(DJIGimbalAxis.Pitch.ordinal() + 9, i, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MotorControlPrecontrolRoll")
    public void u(int i, e eVar) {
        a(DJIGimbalAxis.Roll.ordinal() + 9, i, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MotorControlPrecontrolYaw")
    public void v(int i, e eVar) {
        a(DJIGimbalAxis.Yaw.ordinal() + 9, i, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MotorControlPrecontrolPitch")
    public void K(e eVar) {
        C(DJIGimbalAxis.Pitch.ordinal() + 9, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MotorControlPrecontrolRoll")
    public void L(e eVar) {
        C(DJIGimbalAxis.Roll.ordinal() + 9, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MotorControlPrecontrolYaw")
    public void M(e eVar) {
        C(DJIGimbalAxis.Yaw.ordinal() + 9, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "EndpointPitchUp")
    public void w(int i, e eVar) {
        a(DJIGimbalEndpointDirection.PitchUp.ordinal() + 12, i, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "EndpointPitchDown")
    public void x(int i, e eVar) {
        a(DJIGimbalEndpointDirection.PitchDown.ordinal() + 12, i, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "EndpointYawLeft")
    public void y(int i, e eVar) {
        a(DJIGimbalEndpointDirection.YawLeft.ordinal() + 12, i, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "EndpointYawRight")
    public void z(int i, e eVar) {
        a(DJIGimbalEndpointDirection.YawRight.ordinal() + 12, i, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "EndpointPitchUp")
    public void N(e eVar) {
        C(DJIGimbalEndpointDirection.PitchUp.ordinal() + 12, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "EndpointPitchDown")
    public void O(e eVar) {
        C(DJIGimbalEndpointDirection.PitchDown.ordinal() + 12, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "EndpointYawLeft")
    public void P(e eVar) {
        C(DJIGimbalEndpointDirection.YawLeft.ordinal() + 12, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "EndpointYawRight")
    public void Q(e eVar) {
        C(DJIGimbalEndpointDirection.YawRight.ordinal() + 12, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ControllerDeadbandPitch")
    public void A(int i, e eVar) {
        a(DJIGimbalAxis.Pitch.ordinal() + 16, i, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ControllerDeadbandYaw")
    public void B(int i, e eVar) {
        a(DJIGimbalAxis.Yaw.ordinal() + 16, i, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ControllerDeadbandPitch")
    public void R(e eVar) {
        C(DJIGimbalAxis.Pitch.ordinal() + 16, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ControllerDeadbandYaw")
    public void S(e eVar) {
        C(DJIGimbalAxis.Yaw.ordinal() + 16, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ControllerSpeedPitch")
    public void i(int i, e eVar) {
        a(DJIGimbalAxis.Pitch.ordinal() + 18, i, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ControllerSpeedYaw")
    public void j(int i, e eVar) {
        a(DJIGimbalAxis.Yaw.ordinal() + 18, i, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ControllerSpeedPitch")
    public void s(e eVar) {
        C(DJIGimbalAxis.Pitch.ordinal() + 18, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ControllerSpeedYaw")
    public void t(e eVar) {
        C(DJIGimbalAxis.Yaw.ordinal() + 18, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ControllerSmoothingPitch")
    public void g(int i, e eVar) {
        a(DJIGimbalAxis.Pitch.ordinal() + 20, i, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ControllerSmoothingYaw")
    public void h(int i, e eVar) {
        a(DJIGimbalAxis.Yaw.ordinal() + 20, i, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ControllerSmoothingPitch")
    public void q(e eVar) {
        C(DJIGimbalAxis.Pitch.ordinal() + 20, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ControllerSmoothingYaw")
    public void r(e eVar) {
        C(DJIGimbalAxis.Yaw.ordinal() + 20, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "CameraUprightEnabled")
    public void d(boolean z, e eVar) {
        DataGimbalSetUserParams.getInstance().a("enable_camera_up_side_down", Integer.valueOf(z ? 1 : 0)).start(new 5(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "CameraUprightEnabled")
    public void u(e eVar) {
        boolean z = true;
        if (dji.midware.data.manager.P3.e.read("enable_camera_up_side_down").value.intValue() != 1) {
            z = false;
        }
        eVar.a(Boolean.valueOf(z));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MotorEnabled")
    public void e(boolean z, e eVar) {
        int i;
        DataGimbalSetUserParams instance = DataGimbalSetUserParams.getInstance();
        String str = "shut_down_motor";
        if (z) {
            i = 0;
        } else {
            i = 1;
        }
        instance.a(str, Integer.valueOf(i)).start(new 6(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MotorEnabled")
    public void v(e eVar) {
        eVar.a(Boolean.valueOf(dji.midware.data.manager.P3.e.read("shut_down_motor").value.intValue() == 0));
    }

    private void s() {
        Log.e("CalSystem", "update calibration: " + this.d);
        this.c.setCalibrating(this.d);
        this.c.setIsCalibrationSuccess(this.e);
    }

    private void t() {
        this.a = false;
        this.l.setUpLatch(1);
        long currentTimeMillis = System.currentTimeMillis();
        Runnable 7 = new 7(this);
        while (System.currentTimeMillis() - currentTimeMillis < 30000) {
            try {
                s();
                Thread.sleep(2000);
                new Thread(7).start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.l.waitForLatch(31);
        if (!this.a) {
            this.d = false;
            this.e = false;
        }
        s();
    }

    @a(a = "StartGimbalAutoCalibration")
    public void e(e eVar) {
        DataGimbalSetUserParams.getInstance().a(m[22], Integer.valueOf(1)).start(new 8(this, eVar));
    }

    private void u() {
        this.a = false;
        this.l.setUpLatch(1);
        long currentTimeMillis = System.currentTimeMillis();
        Runnable 9 = new 9(this);
        while (System.currentTimeMillis() - currentTimeMillis < 60000) {
            try {
                Thread.sleep(2000);
                v();
                new Thread(9).start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.l.waitForLatch(61);
        if (!this.a) {
            c(false);
            a(DJIGimbalBalanceTestResult.Unknown);
            b(DJIGimbalBalanceTestResult.Unknown);
        }
        v();
    }

    private void v() {
        Log.e("BalanceTest", "update balance test: " + this.f);
        this.c.setIsTestingBalance(this.f);
        this.c.setPitchTestResult(this.g);
        this.c.setRollTestResult(this.h);
    }

    @a(a = "StartGimbalBalanceTest")
    public void w(e eVar) {
        DataGimbalSetUserParams.getInstance().a(m[23], Integer.valueOf(1)).start(new 10(this, eVar));
    }
}
