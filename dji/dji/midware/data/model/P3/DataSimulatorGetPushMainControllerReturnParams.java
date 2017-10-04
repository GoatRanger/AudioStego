package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataSimulatorGetPushMainControllerReturnParams extends n {
    private static DataSimulatorGetPushMainControllerReturnParams instance;

    public static synchronized DataSimulatorGetPushMainControllerReturnParams getInstance() {
        DataSimulatorGetPushMainControllerReturnParams dataSimulatorGetPushMainControllerReturnParams;
        synchronized (DataSimulatorGetPushMainControllerReturnParams.class) {
            if (instance == null) {
                instance = new DataSimulatorGetPushMainControllerReturnParams();
            }
            dataSimulatorGetPushMainControllerReturnParams = instance;
        }
        return dataSimulatorGetPushMainControllerReturnParams;
    }

    public int getDroneType() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
