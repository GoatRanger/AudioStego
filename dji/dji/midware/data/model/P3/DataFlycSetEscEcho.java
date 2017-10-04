package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycSetEscEcho extends n implements e {
    private SetEchoCmd a = SetEchoCmd.OPEN_ALL;
    private int b = 0;

    public enum SetEchoCmd {
        CLOSE_ALL(1),
        OPEN_ALL(2),
        CLOSE_ONE(3),
        OPEN_ONE(4),
        REQUEST_SOME(5),
        OTHER(100);
        
        private final int g;

        private SetEchoCmd(int i) {
            this.g = i;
        }

        public int a() {
            return this.g;
        }

        public boolean a(int i) {
            return this.g == i;
        }

        public static SetEchoCmd find(int i) {
            SetEchoCmd setEchoCmd = CLOSE_ALL;
            for (SetEchoCmd setEchoCmd2 : values()) {
                if (setEchoCmd2.a(i)) {
                    return setEchoCmd2;
                }
            }
            return setEchoCmd;
        }
    }

    public enum SetResult {
        SUCCESS(0),
        FAIL_MOTORUP(1),
        FAIL_NONSMART(2),
        FAIL_ILLEGALCMD(3),
        FAIL_ERRORID(4),
        OTHER(100);
        
        private final int g;

        private SetResult(int i) {
            this.g = i;
        }

        public int a() {
            return this.g;
        }

        public boolean a(int i) {
            return this.g == i;
        }

        public static SetResult find(int i) {
            SetResult setResult = SUCCESS;
            for (SetResult setResult2 : values()) {
                if (setResult2.a(i)) {
                    return setResult2;
                }
            }
            return setResult;
        }
    }

    public DataFlycSetEscEcho a(SetEchoCmd setEchoCmd) {
        this.a = setEchoCmd;
        return this;
    }

    public DataFlycSetEscEcho a(int i) {
        this.b = i;
        return this;
    }

    public SetResult a() {
        return SetResult.find(((Integer) get(0, 1, Integer.class)).intValue());
    }

    public boolean b() {
        return (((Integer) get(1, 1, Integer.class)).intValue() & 1) != 0;
    }

    protected void doPack() {
        this._sendData = new byte[2];
        this._sendData[0] = (byte) this.a.a();
        this._sendData[1] = (byte) this.b;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.aL.a();
        start(cVar, dVar);
    }
}
