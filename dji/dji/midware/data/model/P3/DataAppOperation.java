package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.util.c;

public class DataAppOperation extends n {
    public static final int a = 5;
    public int b;
    public APP_OPERATION_STATE c;

    public enum APP_OPERATION_STATE {
        NORMAL(1),
        SEND(2),
        ACK_SUCCESS(3),
        ACK_FAILED(4),
        OTHER(100);
        
        private int f;

        private APP_OPERATION_STATE(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }

        public boolean a(int i) {
            return this.f == i;
        }

        public static APP_OPERATION_STATE find(int i) {
            APP_OPERATION_STATE app_operation_state = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return app_operation_state;
        }
    }

    public DataAppOperation(boolean z) {
        super(z);
    }

    public void a(int i, APP_OPERATION_STATE app_operation_state) {
        this.b = i;
        this.c = app_operation_state;
    }

    public void a(FLYC_COMMAND flyc_command, APP_OPERATION_STATE app_operation_state) {
        this.b = flyc_command.value();
        this.c = app_operation_state;
    }

    public void a(int i, APP_OPERATION_STATE app_operation_state, boolean z) {
        switch (i) {
            case 0:
                this.b = APP_OPERATION_ID.c.a();
                break;
            case 1:
                this.b = APP_OPERATION_ID.e.a();
                break;
            case 2:
                this.b = APP_OPERATION_ID.d.a();
                break;
            case 3:
                this.b = APP_OPERATION_ID.f.a();
                break;
            default:
                this.b = APP_OPERATION_ID.e.a();
                break;
        }
        this.c = app_operation_state;
    }

    public byte[] getRecData() {
        Object obj = new byte[5];
        System.arraycopy(c.a(this.b), 0, obj, 0, 4);
        obj[4] = (byte) this.c.a();
        return obj;
    }

    public void setRecData(byte[] bArr) {
        this.b = c.b(bArr);
        this.c = APP_OPERATION_STATE.find(bArr[4]);
    }

    protected void doPack() {
    }
}
