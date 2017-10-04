package dji.pilot.visual.radar;

import dji.gs.c.e;
import dji.midware.data.model.P3.DataEyeGetPushFrontAvoidance.SensorType;
import dji.pilot.R;
import dji.publics.d.c;

public interface b {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final long f = 2000;
    public static final int[] g = new int[]{R.string.vision_brake_disallow_front, R.string.vision_brake_disallow_back, R.string.vision_brake_disallow_left, R.string.vision_brake_disallow_right};
    public static final int h = 4;
    public static final int[] i = new int[]{300, 600, 1000, 1500, 2000};
    public static final int[] j = new int[]{200, 400, 600, e.g, 1000};
    public static final int[] k = new int[]{300, 600};
    public static final int[][] l = new int[][]{new int[]{R.drawable.radar_forward_1_5, R.drawable.radar_forward_1_4, R.drawable.radar_forward_1_3, R.drawable.radar_forward_1_2, R.drawable.radar_forward_1_1, R.drawable.radar_forward_1_0}, new int[]{R.drawable.radar_forward_2_5, R.drawable.radar_forward_2_4, R.drawable.radar_forward_2_3, R.drawable.radar_forward_2_2, R.drawable.radar_forward_2_1, R.drawable.radar_forward_2_0}, new int[]{R.drawable.radar_forward_3_5, R.drawable.radar_forward_3_4, R.drawable.radar_forward_3_3, R.drawable.radar_forward_3_2, R.drawable.radar_forward_3_1, R.drawable.radar_forward_3_0}, new int[]{R.drawable.radar_forward_4_5, R.drawable.radar_forward_4_4, R.drawable.radar_forward_4_3, R.drawable.radar_forward_4_2, R.drawable.radar_forward_4_1, R.drawable.radar_forward_4_0}};
    public static final int[][] m = new int[][]{new int[]{R.drawable.radar_backward_1_5, R.drawable.radar_backward_1_4, R.drawable.radar_backward_1_3, R.drawable.radar_backward_1_2, R.drawable.radar_backward_1_1, R.drawable.radar_backward_1_0}, new int[]{R.drawable.radar_backward_2_5, R.drawable.radar_backward_2_4, R.drawable.radar_backward_2_3, R.drawable.radar_backward_2_2, R.drawable.radar_backward_2_1, R.drawable.radar_backward_2_0}, new int[]{R.drawable.radar_backward_3_5, R.drawable.radar_backward_3_4, R.drawable.radar_backward_3_3, R.drawable.radar_backward_3_2, R.drawable.radar_backward_3_1, R.drawable.radar_backward_3_0}, new int[]{R.drawable.radar_backward_4_5, R.drawable.radar_backward_4_4, R.drawable.radar_backward_4_3, R.drawable.radar_backward_4_2, R.drawable.radar_backward_4_1, R.drawable.radar_backward_4_0}};
    public static final int[] n = new int[]{R.drawable.radar_backward_dangerous, R.drawable.radar_backward_warning, 0};
    public static final int[] o = new int[]{R.drawable.radar_left_dangerous, R.drawable.radar_left_warning, 0};
    public static final int[] p = new int[]{R.drawable.radar_right_dangerous, R.drawable.radar_right_warning, 0};

    public interface b extends c {
        void hideTypeView(int i, int i2);

        void safeToHideView(int i, boolean z, int i2);

        void showTypeInvalidView(int i, int i2, int i3);

        void showTypeNormalView(int i, int i2);

        void showWarningView(int i, int i2);

        void updateNormalDesc(int i, String str, int i2);

        void updateRadarImg(int i, int i2, int i3, int i4);
    }

    public interface a {
        int a(SensorType sensorType);

        void a();

        boolean a(int i);

        boolean a(boolean z, int i);

        SensorType b(int i);

        void b();
    }
}
