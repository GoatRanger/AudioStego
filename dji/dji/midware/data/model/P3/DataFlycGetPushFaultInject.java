package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataFlycGetPushFaultInject extends n {
    private static DataFlycGetPushFaultInject instance = null;

    public enum INJECT_STATUS {
        FIT_VERSION_UNMATCH(1),
        FIT_OPEN_FAILED(2),
        FIT_OPEN_SUCCESS(3),
        FIT_CLOSE_SUCCESS(4),
        FIT_INJECT_SUCCESS(5),
        FIT_INJECT_FAILED(6),
        FIT_FDI_DETECT_SUCCESS(7),
        FIT_FDI_DETECT_FAILED(8),
        FIT_AUTO_STOP_FOR_SAFE(9),
        FIT_TIME_PARA_INVALID(10),
        FIT_DENY_FOR_UNSAFE(11),
        FIT_DENY_FOR_FAULT(12),
        FIT_DENY_FOR_DISCONNECT(13),
        FIT_UNKNOWN_FAULT_TYPE(14),
        FIT_INVALID_SYSTEM_ID(15),
        FIT_UNKNOWN_MODULE_TYPE(16),
        FIT_MODULE_CANNOT_FOUND(17),
        FIT_UNKNOWN_CMD_ID(18),
        FIT_UNSUPPORT_NOW(19),
        FIT_DENY_FOR_UNOPEN(20),
        FIT_DENY_FOR_FUNC_CLOSED(21),
        FIT_MSG_LEN_ERR(22),
        FIT_ROUTE_FAILED(23);
        
        private int data;

        private INJECT_STATUS(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static INJECT_STATUS find(int i) {
            INJECT_STATUS inject_status = FIT_VERSION_UNMATCH;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return inject_status;
        }
    }

    public static synchronized DataFlycGetPushFaultInject getInstance() {
        DataFlycGetPushFaultInject dataFlycGetPushFaultInject;
        synchronized (DataFlycGetPushFaultInject.class) {
            if (instance == null) {
                instance = new DataFlycGetPushFaultInject();
            }
            dataFlycGetPushFaultInject = instance;
        }
        return dataFlycGetPushFaultInject;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    protected void doPack() {
    }

    public INJECT_STATUS getStatus() {
        return INJECT_STATUS.find(((Integer) get(0, 1, Integer.class)).intValue());
    }
}
