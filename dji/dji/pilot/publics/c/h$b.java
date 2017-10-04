package dji.pilot.publics.c;

import dji.pilot.R;

public enum h$b {
    CompassError(R.raw.tips_voice_compass_error),
    b(R.raw.tips_voice_takeoff),
    Landing(R.raw.tips_voice_landing),
    d(R.raw.tips_voice_gohome),
    GoHomePointRecorded(R.raw.tips_voice_home_point_is_update),
    GoHomePointUpdated(R.raw.tips_voice_home_point_is_update),
    LowBattery(R.raw.tips_voice_low_power),
    SeriousLowBattery(R.raw.tips_voice_seriou_low_power),
    SdcardFull(R.raw.tips_voice_sdcard_full),
    SdcardError(R.raw.tips_voice_sdcard_error),
    P_GPS(R.raw.tips_voice_pgps_mode),
    ATTI(R.raw.tips_voice_atti_mode),
    LandingGearRising(R.raw.tips_voice_tripod_unfold),
    LandingGearDroping(R.raw.tips_voice_tripod_fold),
    GimbalReachMax(R.raw.tips_voice_gimbal_limit),
    VisualPositionStart(R.raw.tips_voice_vision_work),
    BatteryOverheat(R.raw.tips_voice_battery_overheating),
    BatteryError(R.raw.tips_voice_battery_error),
    Welcome(R.raw.tips_voice_welcome_to_use_inspire),
    AutoGoHomeByPower(R.raw.tips_voice_smart_low_battery),
    AutoLandBySmart(R.raw.tips_voice_smart_seriou_low_battery),
    AutoGoHomeByLoseVideo(R.raw.tips_voice_no_signal),
    NoRcSignal(R.raw.tips_voice_falisafe),
    ReachMaxFlyDistance(R.raw.tips_voice_distance_limit),
    ReachMaxFlyHeight(R.raw.tips_voice_height_limit),
    InLimitFlyArea(R.raw.tips_voice_in_limit_area),
    WillReachLimitFlyArea(R.raw.tips_voice_near_limit_area),
    NoVideoAutoGohome(R.raw.tips_voice_disconnect_gohome),
    DropGoHome(R.raw.tips_voice_stop_gohome),
    DropLand(R.raw.tips_voice_stop_landing),
    AutoGoHomeByPowerFiveS(R.raw.tips_voice_smart_low_battery);
    
    private int F;
    private int G;

    private h$b(int i) {
        this.F = i;
    }

    public int a() {
        return this.F;
    }

    public void a(int i) {
        this.G = i;
    }

    public int b() {
        return this.G;
    }
}
