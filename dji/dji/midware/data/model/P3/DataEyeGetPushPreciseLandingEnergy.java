package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataEyeGetPushPreciseLandingEnergy extends n {
    private static DataEyeGetPushPreciseLandingEnergy instance = null;

    public static synchronized DataEyeGetPushPreciseLandingEnergy getInstance() {
        DataEyeGetPushPreciseLandingEnergy dataEyeGetPushPreciseLandingEnergy;
        synchronized (DataEyeGetPushPreciseLandingEnergy.class) {
            if (instance == null) {
                instance = new DataEyeGetPushPreciseLandingEnergy();
            }
            dataEyeGetPushPreciseLandingEnergy = instance;
        }
        return dataEyeGetPushPreciseLandingEnergy;
    }

    public int getEnery() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
