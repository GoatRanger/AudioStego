package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataRTKPushStatus extends n {
    private static DataRTKPushStatus a = null;

    public static DataRTKPushStatus getInstance() {
        if (a == null) {
            a = new DataRTKPushStatus();
            a.isNeedPushLosed = true;
        }
        return a;
    }

    public int a() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int b() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int c() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public int d() {
        return ((Integer) get(3, 1, Integer.class)).intValue();
    }

    public int e() {
        return ((Integer) get(4, 1, Integer.class)).intValue();
    }

    public int f() {
        return ((Integer) get(5, 1, Integer.class)).intValue();
    }

    public int g() {
        return ((Integer) get(6, 1, Integer.class)).intValue();
    }

    public int h() {
        return ((Integer) get(7, 1, Integer.class)).intValue();
    }

    public int i() {
        return ((Integer) get(8, 1, Integer.class)).intValue();
    }

    public int j() {
        return ((Integer) get(9, 1, Integer.class)).intValue();
    }

    public int k() {
        return ((Integer) get(10, 1, Integer.class)).intValue();
    }

    public double l() {
        return ((Double) get(11, 8, Double.class)).doubleValue();
    }

    public double m() {
        return ((Double) get(19, 8, Double.class)).doubleValue();
    }

    public float n() {
        return ((Float) get(27, 4, Float.class)).floatValue();
    }

    public double o() {
        return ((Double) get(31, 8, Double.class)).doubleValue();
    }

    public double p() {
        return ((Double) get(39, 8, Double.class)).doubleValue();
    }

    public float q() {
        return ((Float) get(47, 4, Float.class)).floatValue();
    }

    public float r() {
        return ((Float) get(51, 4, Float.class)).floatValue();
    }

    public int s() {
        return ((Integer) get(55, 1, Integer.class)).intValue();
    }

    public boolean t() {
        return ((Integer) get(56, 1, Integer.class)).intValue() == 1;
    }

    public boolean u() {
        return ((Integer) get(55, 1, Integer.class)).intValue() == 50;
    }

    public boolean v() {
        return ((Integer) get(56, 1, Integer.class)).intValue() == 1;
    }

    protected void doPack() {
    }
}
