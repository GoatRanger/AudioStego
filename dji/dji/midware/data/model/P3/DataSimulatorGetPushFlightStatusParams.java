package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataSimulatorGetPushFlightStatusParams extends n {
    private static DataSimulatorGetPushFlightStatusParams instance = null;

    public static synchronized DataSimulatorGetPushFlightStatusParams getInstance() {
        DataSimulatorGetPushFlightStatusParams dataSimulatorGetPushFlightStatusParams;
        synchronized (DataSimulatorGetPushFlightStatusParams.class) {
            if (instance == null) {
                instance = new DataSimulatorGetPushFlightStatusParams();
            }
            dataSimulatorGetPushFlightStatusParams = instance;
        }
        return dataSimulatorGetPushFlightStatusParams;
    }

    public byte[] getResult() {
        return this._recData;
    }

    public int getLength() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public boolean hasMotorTurnedOn() {
        return (((Integer) get(1, 1, Integer.class)).intValue() & 1) == 1;
    }

    public boolean isInTheAir() {
        return (((Integer) get(1, 1, Integer.class)).intValue() & 2) == 2;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    protected void doPack() {
    }
}
