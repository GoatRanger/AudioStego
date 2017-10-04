package dji.sdksharedlib.e.a.a;

import android.support.v4.media.TransportMediator;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.e.a.b;
import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;
import java.util.HashMap;

public class a implements dji.midware.data.params.P3.a {
    public static HashMap<String, b> a() {
        dji.sdksharedlib.e.a.b.a aVar = new dji.sdksharedlib.e.a.b.a();
        HashMap<String, b> hashMap = new HashMap();
        hashMap.put(e.cc, aVar.c(2).a(Integer.valueOf(10)).b(Integer.valueOf(60)).c(Integer.valueOf(35)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cc).c("g_config.control.atti_range_0").a(15).b(-1).a(null).a(Short.class).a());
        hashMap.put(e.cd, aVar.c(2).a(Integer.valueOf(60)).b(Integer.valueOf(300)).c(Integer.valueOf(150)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cd).c("g_config.control.atti_torsion_w_rate_0").a(15).b(-1).a(e.ch).a(Short.class).a());
        hashMap.put(e.ce, aVar.c(2).a(Integer.valueOf(20)).b(Integer.valueOf(100)).c(Integer.valueOf(50)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.ce).c("g_config.control.rc_tilt_sensitivity_0").a(-1).b(0).a(e.cf).a(Short.class).a());
        hashMap.put(e.cg, aVar.c(2).a(Integer.valueOf(70)).b(Integer.valueOf(TransportMediator.KEYCODE_MEDIA_RECORD)).c(Integer.valueOf(-1)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cg).c("g_config.control.brake_sensitivity_0").a(-1).b(0).a(null).a(Short.class).a());
        hashMap.put(e.ch, aVar.c(4).a(Integer.valueOf(10)).b(Integer.valueOf(1000)).c(Integer.valueOf(150)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.ch).c("g_config.mode_normal_cfg.tors_gyro_range_0").a(-1).b(16).a(null).a(Float.class).a());
        hashMap.put(e.cn, aVar.c(2).a(Integer.valueOf(20)).b(Integer.valueOf(80)).c(Integer.valueOf(40)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cn).c("g_config.control.thr_exp_mid_point_0").a(15).b(-1).a(e.cz).a(Short.class).a());
        hashMap.put(e.co, aVar.c(2).a(Integer.valueOf(20)).b(Integer.valueOf(80)).c(Integer.valueOf(40)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.co).c("g_config.control.tilt_exp_mid_point_0").a(15).b(-1).a(e.cx).a(Short.class).a());
        hashMap.put(e.cp, aVar.c(2).a(Integer.valueOf(20)).b(Integer.valueOf(80)).c(Integer.valueOf(40)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cp).c("g_config.control.yaw_exp_mid_point_0").a(15).b(-1).a(e.cy).a(Short.class).a());
        hashMap.put(e.cq, aVar.c(4).a(Integer.valueOf(20)).b(Integer.valueOf(80)).c(Integer.valueOf(40)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cq).c("g_config.sport_cfg.thr_exp_mid_point_0").a(15).b(11).a(e.cC).a(Float.class).a());
        hashMap.put(e.cr, aVar.c(4).a(Integer.valueOf(20)).b(Integer.valueOf(80)).c(Integer.valueOf(40)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cr).c("g_config.sport_cfg.tilt_exp_mid_point_0").a(15).b(11).a(e.cA).a(Float.class).a());
        hashMap.put(e.cs, aVar.c(4).a(Integer.valueOf(20)).b(Integer.valueOf(80)).c(Integer.valueOf(40)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cs).c("g_config.sport_cfg.yaw_exp_mid_point_0").a(15).b(11).a(e.cB).a(Float.class).a());
        hashMap.put(e.ct, aVar.c(1).a(Integer.valueOf(0)).b(Integer.valueOf(255)).c(Integer.valueOf(0)).d(1).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.ct).c("imu_temp.real_ctl_out_per_0").a(15).b(-1).a(null).a(Short.class).a());
        hashMap.put(e.cu, aVar.c(4).a(Double.valueOf(0.1d)).b(Double.valueOf(0.9d)).c(Double.valueOf(0.5d)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cu).c("g_config.mode_gentle_cfg.tilt_exp_mid_point_0").a(-1).b(16).a(null).a(Float.class).a());
        hashMap.put(e.cv, aVar.c(4).a(Double.valueOf(0.1d)).b(Double.valueOf(0.9d)).c(Double.valueOf(0.5d)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cv).c("g_config.mode_gentle_cfg.tors_exp_mid_point_0").a(-1).b(16).a(null).a(Float.class).a());
        hashMap.put(e.cw, aVar.c(4).a(Double.valueOf(0.1d)).b(Double.valueOf(0.9d)).c(Double.valueOf(0.5d)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cw).c("g_config.mode_gentle_cfg.lift_exp_mid_point_0").a(-1).b(16).a(null).a(Float.class).a());
        hashMap.put(e.cx, aVar.c(4).a(Double.valueOf(0.1d)).b(Double.valueOf(0.9d)).c(Double.valueOf(0.5d)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cu).c("g_config.mode_normal_cfg.tilt_exp_mid_point_0").a(-1).b(16).a(null).a(Float.class).a());
        hashMap.put(e.cy, aVar.c(4).a(Double.valueOf(0.1d)).b(Double.valueOf(0.9d)).c(Double.valueOf(0.5d)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cy).c("g_config.mode_normal_cfg.tors_exp_mid_point_0").a(-1).b(16).a(null).a(Float.class).a());
        hashMap.put(e.cz, aVar.c(4).a(Double.valueOf(0.1d)).b(Double.valueOf(0.9d)).c(Double.valueOf(0.5d)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cz).c("g_config.mode_normal_cfg.lift_exp_mid_point_0").a(-1).b(16).a(null).a(Float.class).a());
        hashMap.put(e.cA, aVar.c(4).a(Double.valueOf(0.1d)).b(Double.valueOf(0.9d)).c(Double.valueOf(0.5d)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cA).c("g_config.mode_sport_cfg.tilt_exp_mid_point_0").a(-1).b(16).a(null).a(Float.class).a());
        hashMap.put(e.cB, aVar.c(4).a(Double.valueOf(0.1d)).b(Double.valueOf(0.9d)).c(Double.valueOf(0.5d)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cB).c("g_config.mode_sport_cfg.tors_exp_mid_point_0").a(-1).b(16).a(null).a(Float.class).a());
        hashMap.put(e.cC, aVar.c(4).a(Double.valueOf(0.1d)).b(Double.valueOf(0.9d)).c(Double.valueOf(0.5d)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cC).c("g_config.mode_sport_cfg.lift_exp_mid_point_0").a(-1).b(16).a(null).a(Float.class).a());
        hashMap.put(e.cD, aVar.c(2).a(Integer.valueOf(70)).b(Integer.valueOf(TransportMediator.KEYCODE_MEDIA_RECORD)).c(Integer.valueOf(75)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cD).c("g_config.control.atti_vertical_0").a(-1).b(0).a(null).a(Short.class).a());
        hashMap.put(e.cE, aVar.c(2).a(Integer.valueOf(70)).b(Integer.valueOf(TransportMediator.KEYCODE_MEDIA_RECORD)).c(Integer.valueOf(100)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cE).c("g_config.control.basic_pitch_0").a(-1).b(0).a(null).a(Short.class).a());
        hashMap.put(e.cG, aVar.c(2).a(Integer.valueOf(70)).b(Integer.valueOf(TransportMediator.KEYCODE_MEDIA_RECORD)).c(Integer.valueOf(100)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cG).c("g_config.control.basic_roll_0").a(-1).b(0).a(null).a(Short.class).a());
        hashMap.put(e.cF, aVar.c(2).a(Integer.valueOf(70)).b(Integer.valueOf(TransportMediator.KEYCODE_MEDIA_RECORD)).c(Integer.valueOf(100)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cF).c("g_config.control.basic_yaw_0").a(-1).b(0).a(null).a(Short.class).a());
        hashMap.put(e.cH, aVar.c(2).a(Integer.valueOf(70)).b(Integer.valueOf(TransportMediator.KEYCODE_MEDIA_RECORD)).c(Integer.valueOf(75)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cH).c("g_config.control.basic_tail_0").a(-1).b(0).a(null).a(Short.class).a());
        hashMap.put(e.cI, aVar.c(1).a(Integer.valueOf(0)).b(Integer.valueOf(1)).c(Integer.valueOf(0)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cI).c("g_config.mode_tripod_en_cfg.tripod_func_enabled_0").a(-1).b(16).a(null).a(Short.class).a());
        hashMap.put(e.cK, aVar.c(1).a(Integer.valueOf(0)).b(Integer.valueOf(1)).c(Integer.valueOf(0)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cK).c("g_config.landing.adv_landing_enable_0").a(-1).b(16).a(null).a(Short.class).a());
        hashMap.put(e.cL, aVar.c(1).a(Integer.valueOf(0)).b(Integer.valueOf(1)).c(Integer.valueOf(0)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cL).c("g_config.go_home.adv_gohome_enable_0").a(-1).b(16).a(null).a(Short.class).a());
        hashMap.put(e.cP, aVar.c(1).a(Integer.valueOf(0)).b(Integer.valueOf(255)).c(Integer.valueOf(0)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cP).c(dji.midware.data.params.P3.a.e).a(15).b(0).a(e.cQ).a(Integer.class).a());
        hashMap.put(e.cQ, aVar.c(1).a(Integer.valueOf(0)).b(Integer.valueOf(255)).c(Integer.valueOf(0)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cQ).c(dji.midware.data.params.P3.a.f).a(-1).b(16).a(null).a(Integer.class).a());
        hashMap.put(e.cR, aVar.c(1).a(Integer.valueOf(0)).b(Integer.valueOf(1)).c(Integer.valueOf(0)).d(3).a(DJISDKCacheUpdateType.USER_DRIVEN).b(e.cR).c(dji.midware.data.params.P3.a.C).a(-1).b(16).a(null).a(Short.class).a());
        return hashMap;
    }
}
