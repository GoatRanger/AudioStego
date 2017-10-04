package dji.midware.data.model.P3;

import dji.pilot.publics.control.rc.b;

public enum DataAppOperation$APP_OPERATION_ID {
    SPECIAL_CMD_GOHOME(256),
    SPECIAL_CMD_STOPHOME(257),
    SET_HOMEPOINT_TYPE_AIRCRAFT(258),
    SET_HOMEPOINT_TYPE_MOBILE(259),
    SET_HOMEPOINT_TYPE_RC(260),
    SET_HOMEPOINT_TYPE_TRACKING(261),
    SET_HOMEPOINT_TYPE_STOPTRACKING(b.j),
    OTHER(b.k);
    
    private int i;

    private DataAppOperation$APP_OPERATION_ID(int i) {
        this.i = i;
    }

    public int a() {
        return this.i;
    }

    public boolean a(int i) {
        return this.i == i;
    }

    public static DataAppOperation$APP_OPERATION_ID find(int i) {
        DataAppOperation$APP_OPERATION_ID dataAppOperation$APP_OPERATION_ID = OTHER;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2].a(i)) {
                return values()[i2];
            }
        }
        return dataAppOperation$APP_OPERATION_ID;
    }
}
