package dji.midware.natives;

import android.util.Log;

public class FlyForbid {

    public static class FlyForbidParam {
        public double[] ForbidCountry;
        public double[] ForbidLat;
        public double[] ForbidLon;
        public double[] ForbidRadius;
        public double[] ForbidType;
        public int count;

        public void SetForbidPoint(double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4, double[] dArr5, int i) {
            if (i > 0) {
                this.ForbidLon = new double[i];
                this.ForbidLat = new double[i];
                this.ForbidRadius = new double[i];
                this.ForbidCountry = new double[i];
                this.ForbidType = new double[i];
                for (int i2 = 0; i2 < i; i2++) {
                    this.ForbidLon[i2] = dArr[i2];
                    this.ForbidLat[i2] = dArr2[i2];
                    this.ForbidRadius[i2] = dArr3[i2];
                    this.ForbidCountry[i2] = dArr4[i2];
                    this.ForbidType[i2] = dArr5[i2];
                }
            }
        }
    }

    public static native FlyForbidParam native_CheckNearForbidPoints(double d, double d2, Object obj);

    public static native boolean native_intersectSegCircle(double d, double d2, double d3, int i);

    static {
        try {
            System.loadLibrary("FlyForbid");
            Log.d("FlyForbid", "load lib success");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            Log.d("FlyForbid", "Couldn't load lib");
        }
    }
}
