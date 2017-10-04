package dji.midware.data.model.P3;

import dji.logic.c.b;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.n;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.data.model.P3.DataFlycSetJoyStickParams.FlycMode;
import dji.midware.data.model.P3.DataGimbalControl.MODE;
import dji.midware.e.d;
import dji.midware.e.e;
import java.util.Arrays;

public class DataNewSpecialControl extends DataSpecialControl implements e {
    protected static volatile int e = 0;
    private static DataNewSpecialControl n = null;
    protected SubCmd a = SubCmd.COMMON;
    protected int b = 0;
    protected boolean c = false;
    protected int d = 12;
    protected DataSpecialControl$FlyGoHomeStaus f = DataSpecialControl$FlyGoHomeStaus.INIT;
    protected DataSpecialControl$FlyCtrlCmd g = DataSpecialControl$FlyCtrlCmd.INIT;
    protected FlycMode h = FlycMode.OTHER;
    protected byte i = (byte) 0;
    protected byte j = (byte) 0;
    protected final byte[] k = new byte[]{(byte) 0, (byte) 0};
    protected final byte[] l = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
    protected WorkView m = WorkView.LiveView;

    public enum SubCmd {
        COMMON(0),
        FLYC(1),
        GIMBAL(2),
        CAMERA(3),
        OTHER(100);
        
        private final int f;

        private SubCmd(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }

        public boolean a(int i) {
            return this.f == i;
        }

        public static SubCmd find(int i) {
            SubCmd subCmd = COMMON;
            for (SubCmd subCmd2 : values()) {
                if (subCmd2.a(i)) {
                    return subCmd2;
                }
            }
            return subCmd;
        }
    }

    public enum WorkView {
        LiveView(0),
        PlayBack(1),
        Library(2),
        Rc(3),
        OTHER(100);
        
        private final int f;

        private WorkView(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }

        public boolean a(int i) {
            return this.f == i;
        }

        public static WorkView find(int i) {
            WorkView workView = LiveView;
            for (WorkView workView2 : values()) {
                if (workView2.a(i)) {
                    return workView2;
                }
            }
            return workView;
        }
    }

    public /* synthetic */ DataSpecialControl init() {
        return b();
    }

    protected /* synthetic */ DataSpecialControl reset() {
        return a();
    }

    public /* synthetic */ DataSpecialControl resetGimbal() {
        return c();
    }

    public /* synthetic */ DataSpecialControl selfieGimbal() {
        return d();
    }

    public /* synthetic */ DataSpecialControl setFlyGoHomeStatus(DataSpecialControl$FlyGoHomeStaus dataSpecialControl$FlyGoHomeStaus) {
        return a(dataSpecialControl$FlyGoHomeStaus);
    }

    public /* synthetic */ DataSpecialControl setFlycMode(FlycMode flycMode) {
        return a(flycMode);
    }

    public /* synthetic */ DataSpecialControl setGimbalMode(MODE mode) {
        return a(mode);
    }

    public /* synthetic */ DataSpecialControl setGimbalMode(MODE mode, boolean z) {
        return a(mode, z);
    }

    public /* synthetic */ DataSpecialControl setPhotoType(TYPE type) {
        return a(type);
    }

    public /* synthetic */ DataSpecialControl setPhotoType(TYPE type, int i, int i2) {
        return a(type, i, i2);
    }

    public /* synthetic */ DataSpecialControl setPlayBackBrowserScaleType(short s) {
        return a(s);
    }

    public /* synthetic */ DataSpecialControl setPlayBackBrowserType(DataSpecialControl$PlayBrowseType dataSpecialControl$PlayBrowseType, byte b, byte b2) {
        return a(dataSpecialControl$PlayBrowseType, b, b2);
    }

    public /* synthetic */ DataSpecialControl setPlayBackPlayCtr(DataSpecialControl$PlayCtrType dataSpecialControl$PlayCtrType, byte b) {
        return a(dataSpecialControl$PlayCtrType, b);
    }

    public /* synthetic */ DataSpecialControl setPlayBackType(boolean z) {
        return b(z);
    }

    public /* synthetic */ DataSpecialControl setRecordType(boolean z) {
        return a(z);
    }

    public /* synthetic */ DataSpecialControl setRecordType(boolean z, int i, int i2) {
        return a(z, i, i2);
    }

    public static synchronized DataNewSpecialControl getInstance() {
        DataNewSpecialControl dataNewSpecialControl;
        synchronized (DataNewSpecialControl.class) {
            if (n == null) {
                n = new DataNewSpecialControl();
            }
            dataNewSpecialControl = n;
        }
        return dataNewSpecialControl;
    }

    protected void _reset() {
        this.a = SubCmd.COMMON;
        this.b = 0;
        this.c = false;
        this.d = 12;
        this.f = DataSpecialControl$FlyGoHomeStaus.INIT;
        this.g = DataSpecialControl$FlyCtrlCmd.INIT;
        this.i = (byte) 0;
        this.j = (byte) 0;
        Arrays.fill(this.k, (byte) 0);
        Arrays.fill(this.l, (byte) 0);
    }

    protected DataNewSpecialControl a() {
        _reset();
        return this;
    }

    public DataNewSpecialControl b() {
        _reset();
        return this;
    }

    public DataNewSpecialControl a(WorkView workView) {
        this.m = workView;
        byte[] bArr = this.k;
        bArr[1] = (byte) (bArr[1] | (workView.a() << 6));
        return this;
    }

    public DataNewSpecialControl a(DataCameraGetMode.MODE mode) {
        _reset();
        a(WorkView.LiveView);
        byte[] bArr = this.k;
        bArr[1] = (byte) (bArr[1] | 32);
        bArr = this.l;
        bArr[1] = (byte) (bArr[1] | (mode.value() << 4));
        return this;
    }

    public DataNewSpecialControl a(TYPE type) {
        _reset();
        a(WorkView.LiveView);
        byte[] bArr = this.k;
        bArr[1] = (byte) (bArr[1] | 16);
        bArr = this.l;
        bArr[1] = (byte) (bArr[1] | type.a());
        return this;
    }

    public DataNewSpecialControl a(TYPE type, int i, int i2) {
        _reset();
        a(WorkView.LiveView);
        byte[] bArr = this.k;
        bArr[1] = (byte) (bArr[1] | 16);
        bArr = this.l;
        bArr[1] = (byte) (bArr[1] | type.a());
        if (type == TYPE.TIME) {
            this.l[2] = (byte) i2;
            this.l[3] = (byte) i;
        } else {
            this.l[2] = (byte) i;
        }
        return this;
    }

    public DataNewSpecialControl a(boolean z) {
        int i;
        _reset();
        a(WorkView.LiveView);
        byte[] bArr = this.k;
        bArr[1] = (byte) (bArr[1] | 8);
        byte[] bArr2 = this.l;
        byte b = bArr2[0];
        if (z) {
            i = 128;
        } else {
            i = 0;
        }
        bArr2[0] = (byte) (i | b);
        return this;
    }

    public DataNewSpecialControl a(boolean z, int i, int i2) {
        int i3;
        _reset();
        a(WorkView.LiveView);
        byte[] bArr = this.k;
        bArr[1] = (byte) (bArr[1] | 8);
        byte[] bArr2 = this.l;
        byte b = bArr2[0];
        if (z) {
            i3 = 128;
        } else {
            i3 = 0;
        }
        bArr2[0] = (byte) (i3 | b);
        bArr = this.l;
        bArr[2] = (byte) (bArr[2] | (i2 & 255));
        bArr = this.l;
        bArr[3] = (byte) (bArr[3] | ((i << 5) | (i2 >> 8)));
        return this;
    }

    public DataNewSpecialControl b(boolean z) {
        int i;
        _reset();
        a(WorkView.LiveView);
        byte[] bArr = this.k;
        bArr[1] = (byte) (bArr[1] | 4);
        byte[] bArr2 = this.l;
        byte b = bArr2[0];
        if (z) {
            i = 64;
        } else {
            i = 0;
        }
        bArr2[0] = (byte) (i | b);
        return this;
    }

    public DataNewSpecialControl a(DataSpecialControl$PlayCtrType dataSpecialControl$PlayCtrType, byte b) {
        _reset();
        a(WorkView.PlayBack);
        byte[] bArr = this.k;
        bArr[1] = (byte) (bArr[1] | 16);
        bArr = this.l;
        bArr[1] = (byte) (bArr[1] | (dataSpecialControl$PlayCtrType.value() << 5));
        bArr = this.l;
        bArr[2] = (byte) (bArr[2] | b);
        return this;
    }

    public DataNewSpecialControl a(DataSpecialControl$PlayBrowseType dataSpecialControl$PlayBrowseType, byte b, byte b2) {
        _reset();
        a(WorkView.PlayBack);
        byte[] bArr = this.k;
        bArr[1] = (byte) (bArr[1] | 8);
        bArr = this.l;
        bArr[1] = (byte) (bArr[1] | (dataSpecialControl$PlayBrowseType.value() << 1));
        bArr = this.l;
        bArr[2] = (byte) (bArr[2] | b);
        bArr = this.l;
        bArr[3] = (byte) (bArr[3] | b2);
        return this;
    }

    public DataNewSpecialControl a(short s) {
        _reset();
        a(WorkView.PlayBack);
        byte[] bArr = this.k;
        bArr[1] = (byte) (bArr[1] | 8);
        bArr = this.l;
        bArr[1] = (byte) (bArr[1] | (DataSpecialControl$PlayBrowseType.SCALE.value() << 1));
        bArr = this.l;
        bArr[2] = (byte) (bArr[2] | (s & 255));
        bArr = this.l;
        bArr[3] = (byte) (bArr[3] | (s >> 8));
        return this;
    }

    public DataNewSpecialControl a(MODE mode) {
        _reset();
        a(WorkView.LiveView);
        this.i = (byte) (mode.value() << 2);
        this.i = (byte) (this.i | 2);
        return this;
    }

    public DataNewSpecialControl a(MODE mode, boolean z) {
        _reset();
        a(WorkView.LiveView);
        this.i = (byte) (mode.value() << 2);
        this.i = (byte) (this.i | 2);
        this.i = (byte) ((z ? 1 : 0) | this.i);
        return this;
    }

    public DataNewSpecialControl c() {
        _reset();
        a(WorkView.LiveView);
        this.i = (byte) 1;
        return this;
    }

    public DataNewSpecialControl d() {
        _reset();
        a(WorkView.LiveView);
        this.i = (byte) 16;
        return this;
    }

    public DataNewSpecialControl a(DataSpecialControl$FlyGoHomeStaus dataSpecialControl$FlyGoHomeStaus) {
        _reset();
        a(WorkView.LiveView);
        this.f = dataSpecialControl$FlyGoHomeStaus;
        return this;
    }

    public DataNewSpecialControl a(FlycMode flycMode) {
        _reset();
        a(WorkView.LiveView);
        this.h = flycMode;
        return this;
    }

    public void start(long j) {
        start((d) null);
    }

    public void stop() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        ProductType c = i.getInstance().c();
        if (dji.logic.f.d.a(null) || b.getInstance().a(c)) {
            cVar.h = DeviceType.OFDM.value();
        } else {
            cVar.h = DeviceType.OSD.value();
        }
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = q.b.a.a();
        cVar.m = p.b.a();
        cVar.n = n.a.NewControl.a();
        cVar.w = 1;
        cVar.v = 2000;
        start(cVar, dVar);
    }

    private int e() {
        int i = e + 1;
        if (i > 255) {
            i = 0;
        }
        e = i;
        return i;
    }

    private void a(byte[] bArr, int i, byte b) {
        if (bArr != null && bArr.length != 0 && i < bArr.length) {
            int length = bArr.length;
            while (i < length) {
                bArr[i] = b;
                i++;
            }
        }
    }

    private void a(byte[] bArr, int i, boolean z) {
        bArr[i + 0] = (byte) this.a.a();
        int i2 = i + 1;
        bArr[i2] = (byte) (bArr[i2] | this.d);
        int i3 = i + 1;
        bArr[i3] = (byte) ((this.c ? 32 : 0) | bArr[i3]);
        i2 = i + 1;
        bArr[i2] = (byte) (bArr[i2] | (this.b << 6));
        bArr[i + 2] = (byte) e();
        if (z) {
            bArr[i + 3] = (byte) (DataSpecialControl$FlyGoHomeStaus.INIT.value() | (DataSpecialControl$FlyCtrlCmd.INIT.value() << 2));
            a(bArr, i + 4, (byte) 0);
            return;
        }
        bArr[i + 3] = (byte) (this.f.value() | (this.g.value() << 2));
        bArr[i + 4] = this.i;
        bArr[i + 5] = this.j;
        System.arraycopy(this.k, 0, bArr, i + 6, 2);
        System.arraycopy(this.l, 0, bArr, i + 8, 4);
    }

    protected void doPack() {
        boolean z = true;
        if (SubCmd.COMMON == this.a) {
            this.d = 12;
            this._sendData = new byte[(this.d * 2)];
            boolean z2 = (DataSpecialControl$FlyGoHomeStaus.INIT == this.f && DataSpecialControl$FlyCtrlCmd.INIT == this.g) ? false : true;
            a(this._sendData, 0, z2);
            byte[] bArr = this._sendData;
            int i = this.d;
            if (z2) {
                z = false;
            }
            a(bArr, i, z);
        }
    }
}
