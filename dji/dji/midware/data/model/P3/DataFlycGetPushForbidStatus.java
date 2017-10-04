package dji.midware.data.model.P3;

import dji.midware.data.forbid.DJIFlightLimitAreaModel;
import dji.midware.data.manager.P3.n;
import java.util.ArrayList;

public class DataFlycGetPushForbidStatus extends n {
    private static DataFlycGetPushForbidStatus instance = null;
    private ArrayList<DJIFlightLimitAreaModel> limitAreaModels = new ArrayList();

    public enum DJIFlightLimitActionEvent {
        None(0),
        ExitLanding(1),
        Collision(2),
        StartLanding(3),
        StopMotor(4),
        OTHER(100);
        
        private int data;

        private DJIFlightLimitActionEvent(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static DJIFlightLimitActionEvent find(int i) {
            DJIFlightLimitActionEvent dJIFlightLimitActionEvent = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return dJIFlightLimitActionEvent;
        }
    }

    public enum DJIFlightLimitAreaState {
        None(0),
        NearLimit(1),
        InHalfLimit(2),
        InSlowDownArea(3),
        InnerLimit(4),
        InnerUnLimit(5),
        OTHER(100);
        
        private int data;

        private DJIFlightLimitAreaState(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static DJIFlightLimitAreaState find(int i) {
            DJIFlightLimitAreaState dJIFlightLimitAreaState = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return dJIFlightLimitAreaState;
        }
    }

    public static synchronized DataFlycGetPushForbidStatus getInstance() {
        DataFlycGetPushForbidStatus dataFlycGetPushForbidStatus;
        synchronized (DataFlycGetPushForbidStatus.class) {
            if (instance == null) {
                instance = new DataFlycGetPushForbidStatus();
            }
            dataFlycGetPushForbidStatus = instance;
        }
        return dataFlycGetPushForbidStatus;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    public void clear() {
        this.limitAreaModels.clear();
        super.clear();
    }

    public DJIFlightLimitAreaState getFlightLimitAreaState() {
        return DJIFlightLimitAreaState.find(((Integer) get(0, 1, Integer.class)).intValue());
    }

    public DJIFlightLimitActionEvent getDJIFlightLimitActionEvent() {
        return DJIFlightLimitActionEvent.find(((Integer) get(1, 1, Integer.class)).intValue());
    }

    public int getlimitSpaceNum() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public ArrayList<DJIFlightLimitAreaModel> getFlightLimitAreaModels() {
        this.limitAreaModels.clear();
        int i = getlimitSpaceNum();
        for (int i2 = 0; i2 < i; i2++) {
            DJIFlightLimitAreaModel dJIFlightLimitAreaModel = new DJIFlightLimitAreaModel();
            dJIFlightLimitAreaModel.latitude = ((Integer) get((i2 * 17) + 7, 4, Integer.class)).intValue();
            dJIFlightLimitAreaModel.longitude = ((Integer) get((i2 * 17) + 11, 4, Integer.class)).intValue();
            dJIFlightLimitAreaModel.innerRadius = ((Integer) get((i2 * 17) + 15, 2, Integer.class)).intValue();
            dJIFlightLimitAreaModel.outerRadius = ((Integer) get((i2 * 17) + 17, 2, Integer.class)).intValue();
            dJIFlightLimitAreaModel.type = ((Integer) get((i2 * 17) + 19, 1, Integer.class)).intValue();
            this.limitAreaModels.add(dJIFlightLimitAreaModel);
        }
        return this.limitAreaModels;
    }

    protected void doPack() {
    }
}
