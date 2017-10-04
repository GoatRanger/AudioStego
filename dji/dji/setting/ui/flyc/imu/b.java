package dji.setting.ui.flyc.imu;

import dji.pilot.setting.ui.R;

public interface b extends dji.midware.data.params.P3.a {
    public static final String[] E = new String[]{dji.midware.data.params.P3.a.n, dji.midware.data.params.P3.a.o, dji.midware.data.params.P3.a.p, dji.midware.data.params.P3.a.q, dji.midware.data.params.P3.a.r, dji.midware.data.params.P3.a.w, dji.midware.data.params.P3.a.x, dji.midware.data.params.P3.a.y};
    public static final String[] F = new String[]{dji.midware.data.params.P3.a.w, dji.midware.data.params.P3.a.x, dji.midware.data.params.P3.a.y};
    public static final int G = 6;
    public static final int H = 3;
    public static final int[] I = new int[]{R.string.setting_ui_imu_prepare_desc1, R.string.setting_ui_imu_prepare_desc2, R.string.setting_ui_imu_prepare_desc3};
    public static final int[] J = new int[]{R.string.setting_ui_imu_prepare_desc2, R.string.setting_ui_imu_prepare_desc3};
    public static final int[] K = new int[]{R.string.setting_ui_imu_process_desc1, R.string.setting_ui_imu_process_desc2};
    public static final int[] L = new int[]{R.drawable.setting_ui_imu_front, R.drawable.setting_ui_imu_behind, R.drawable.setting_ui_imu_right, R.drawable.setting_ui_imu_left, R.drawable.setting_ui_imu_top, R.drawable.setting_ui_imu_under};
    public static final int[] M = new int[]{R.drawable.setting_ui_kumquat_imucali_front, R.drawable.setting_ui_kumquat_imucali_front, R.drawable.setting_ui_kumquat_imucali_right, R.drawable.setting_ui_kumquat_imucali_left, R.drawable.setting_ui_kumquat_imucali_top, R.drawable.setting_ui_kumquat_imucali_underside};
    public static final int[] N = new int[]{R.drawable.setting_ui_imu_front_n3, R.drawable.setting_ui_imu_behind_n3, R.drawable.setting_ui_imu_right_n3, R.drawable.setting_ui_imu_left_n3, R.drawable.setting_ui_imu_top_n3, R.drawable.setting_ui_imu_under_n3};
    public static final int[] O = new int[]{R.drawable.setting_ui_imu_front_m600, R.drawable.setting_ui_imu_below_m600, R.drawable.setting_ui_imu_right_m600, R.drawable.setting_ui_imu_left_m600, R.drawable.setting_ui_imu_top_m600, R.drawable.setting_ui_imu_top_m600};
    public static final int[] P = new int[]{R.drawable.setting_ui_imu_front_m600_pro, R.drawable.setting_ui_imu_below_m600_pro, R.drawable.setting_ui_imu_right_m600_pro, R.drawable.setting_ui_imu_left_m600_pro, R.drawable.setting_ui_imu_top_m600_pro, R.drawable.setting_ui_imu_top_m600_pro};
    public static final int Q = 0;
    public static final int R = 1;
    public static final int S = 2;
    public static final int T = 3;
    public static final int U = 4;
    public static final int V = 5;
    public static final int[] W = new int[]{4, 2, 3, 0, 1, 5};
    public static final String[] a = new String[]{dji.midware.data.params.P3.a.k, dji.midware.data.params.P3.a.l, dji.midware.data.params.P3.a.m};

    public interface a {
        public static final byte eW_ = (byte) 0;
        public static final byte eX_ = (byte) 1;
        public static final byte eY_ = (byte) 3;
        public static final byte eZ_ = (byte) 80;
        public static final byte fa_ = (byte) 81;
        public static final byte fb_ = (byte) -1;
        public static final byte fc_ = (byte) -3;
    }

    public interface b {
        public static final int fd_ = 1;
        public static final int fe_ = 3;
    }

    public enum c {
        Idle(0),
        Calibrating(1),
        MultiCaling(3),
        Success(80),
        MultiSuccess(81),
        OutOfTemperature(-11),
        OverTemperature(-12),
        SingleSideNotReady(-13),
        MultiSideNotReady(-14),
        AccelerometerFault(-20),
        GyroscopeFault(-21),
        AccelerometerScaleFault(-22),
        AccelerometerBiasFault(-23),
        SampledSideTypeFault(-24),
        FitDiffTooLarge(-25),
        GyroscopeBiasFault(-26),
        SampleTimeOut(-27),
        FlashError1(-40, -30),
        FlashError2(-60),
        FlashError3(-70),
        AutoIdentifyDirectionError1(-50),
        AutoIdentifyDirectionError2(-71),
        AutoIdentifyDirectionError3(-72),
        MotorUp(-51),
        InSimulationMode(-52),
        OTHER(100);
        
        private final int A;
        private final int B;

        private c(int i) {
            this.A = i;
            this.B = i;
        }

        private c(int i, int i2) {
            this.A = i;
            this.B = i2;
        }

        public boolean a(int i) {
            return this.A <= i && i <= this.B;
        }

        public static c find(int i) {
            c cVar = Idle;
            for (c cVar2 : values()) {
                if (cVar2.a(i)) {
                    return cVar2;
                }
            }
            return cVar;
        }
    }

    public interface d {
        public static final int a = 0;
        public static final int b = 1;
        public static final int c = 2;
    }
}
