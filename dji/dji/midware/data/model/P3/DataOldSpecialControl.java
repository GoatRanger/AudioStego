package dji.midware.data.model.P3;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.f.a.a.g;
import dji.log.DJILogHelper;
import dji.logic.c.b;
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
import dji.midware.e.c;
import dji.midware.e.d;
import java.util.Arrays;

public class DataOldSpecialControl extends DataSpecialControl implements c {
    private static final int j = 0;
    private static final int k = 1;
    private static final int l = 2;
    private static final long m = 100;
    private static DataOldSpecialControl n = null;
    protected boolean a = false;
    protected boolean b = false;
    protected byte c = (byte) 0;
    protected byte d = (byte) 0;
    protected DataSpecialControl$PlayCtrType e = DataSpecialControl$PlayCtrType.OTHER;
    protected DataSpecialControl$PlayBrowseType f = DataSpecialControl$PlayBrowseType.OTHER;
    protected DataSpecialControl$FlyGoHomeStaus g = DataSpecialControl$FlyGoHomeStaus.INIT;
    protected FlycMode h = FlycMode.OTHER;
    protected final byte[] i = new byte[2];
    private Handler o = new Handler(Looper.getMainLooper(), new Callback(this) {
        final /* synthetic */ DataOldSpecialControl a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.e();
                    if (message.arg1 != 0) {
                        this.a.o.sendEmptyMessageDelayed(0, (long) message.arg1);
                        break;
                    }
                    break;
                case 1:
                    this.a.o.removeMessages(0);
                    break;
                case 2:
                    this.a.a().start(20);
                    break;
            }
            return true;
        }
    });

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

    public static synchronized DataOldSpecialControl getInstance() {
        DataOldSpecialControl dataOldSpecialControl;
        synchronized (DataOldSpecialControl.class) {
            if (n == null) {
                n = new DataOldSpecialControl();
            }
            dataOldSpecialControl = n;
        }
        return dataOldSpecialControl;
    }

    protected void _reset() {
        this.a = false;
        this.c = (byte) 0;
        this.d = (byte) 0;
        this.e = DataSpecialControl$PlayCtrType.OTHER;
        this.f = DataSpecialControl$PlayBrowseType.OTHER;
        this.g = DataSpecialControl$FlyGoHomeStaus.INIT;
        Arrays.fill(this.i, (byte) 0);
    }

    protected DataOldSpecialControl a() {
        _reset();
        this.a = true;
        return this;
    }

    public DataOldSpecialControl b() {
        _reset();
        this.b = true;
        return this;
    }

    public DataOldSpecialControl a(TYPE type) {
        _reset();
        this.c = (byte) ((type.a() << 5) | 16);
        return this;
    }

    public DataOldSpecialControl a(TYPE type, int i, int i2) {
        _reset();
        this.c = (byte) ((type.a() << 5) | 16);
        if (type == TYPE.TIME) {
            this.i[0] = (byte) i2;
            this.i[1] = (byte) i;
        } else {
            this.i[0] = (byte) i;
        }
        return this;
    }

    public DataOldSpecialControl a(boolean z) {
        _reset();
        if (z) {
            this.c = g.ZERO_TAG;
        } else {
            this.c = (byte) 4;
        }
        return this;
    }

    public DataOldSpecialControl a(boolean z, int i, int i2) {
        _reset();
        if (z) {
            this.c = g.ZERO_TAG;
        } else {
            this.c = (byte) 4;
        }
        this.i[0] = (byte) (i2 & 255);
        this.i[1] = (byte) (i2 >> 8);
        byte[] bArr = this.i;
        bArr[1] = (byte) (bArr[1] | ((byte) (i << 5)));
        return this;
    }

    public DataOldSpecialControl b(boolean z) {
        _reset();
        if (z) {
            this.c = (byte) 3;
        } else {
            this.c = (byte) 1;
        }
        return this;
    }

    public DataOldSpecialControl a(DataSpecialControl$PlayCtrType dataSpecialControl$PlayCtrType, byte b) {
        _reset();
        this.e = dataSpecialControl$PlayCtrType;
        this.i[0] = b;
        return this;
    }

    public DataOldSpecialControl a(DataSpecialControl$PlayBrowseType dataSpecialControl$PlayBrowseType, byte b, byte b2) {
        _reset();
        this.f = dataSpecialControl$PlayBrowseType;
        this.i[0] = b;
        this.i[1] = b2;
        return this;
    }

    public DataOldSpecialControl a(short s) {
        _reset();
        this.f = DataSpecialControl$PlayBrowseType.SCALE;
        System.arraycopy(dji.midware.util.c.b(s), 0, this.i, 0, 2);
        return this;
    }

    public DataOldSpecialControl a(MODE mode) {
        _reset();
        this.d = (byte) (mode.value() << 2);
        this.d = (byte) (this.d | 2);
        return this;
    }

    public DataOldSpecialControl a(MODE mode, boolean z) {
        _reset();
        this.d = (byte) (mode.value() << 2);
        this.d = (byte) (this.d | 2);
        this.d = (byte) ((z ? 1 : 0) | this.d);
        return this;
    }

    public DataOldSpecialControl c() {
        _reset();
        this.d = (byte) 1;
        return this;
    }

    public DataOldSpecialControl d() {
        _reset();
        this.d = (byte) 16;
        return this;
    }

    public DataOldSpecialControl a(DataSpecialControl$FlyGoHomeStaus dataSpecialControl$FlyGoHomeStaus) {
        _reset();
        this.g = dataSpecialControl$FlyGoHomeStaus;
        return this;
    }

    public DataOldSpecialControl a(FlycMode flycMode) {
        _reset();
        this.h = flycMode;
        return this;
    }

    protected void doPack() {
        int i = 1;
        this._sendData = new byte[10];
        this._sendData[0] = this.c;
        if (this.e != DataSpecialControl$PlayCtrType.OTHER) {
            this._sendData[1] = (byte) (1 << this.e.value());
        }
        if (this.f != DataSpecialControl$PlayBrowseType.OTHER) {
            System.arraycopy(dji.midware.util.c.b((short) (1 << this.f.value())), 0, this._sendData, 2, 2);
        }
        this._sendData[4] = this.d;
        this._sendData[5] = (byte) (this.g.value() | (this.h.a() << 2));
        System.arraycopy(this.i, 0, this._sendData, 7, 2);
        byte b = this._sendData[0];
        while (i < 9) {
            b = (byte) (b ^ this._sendData[i]);
            i++;
        }
        this._sendData[9] = b;
    }

    public void start(long j) {
        if (j == 0) {
            e();
            return;
        }
        this.o.removeMessages(1);
        this.o.sendEmptyMessageDelayed(1, m);
        this.o.removeMessages(0);
        this.o.obtainMessage(0, (int) j, 0).sendToTarget();
    }

    public void start(d dVar) {
        start(20);
    }

    private void e() {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
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
        cVar.n = n.a.Control.a();
        start(cVar);
    }

    public void stop() {
        this.o.removeMessages(0);
        this.o.removeMessages(1);
        if (!this.a) {
            this.o.removeMessages(2);
            if (!this.b) {
                this.o.sendEmptyMessageDelayed(2, m);
            }
        }
        if (this.b) {
            this.b = false;
            DJILogHelper.getInstance().LOGD("", "special mInit[" + this.b + dji.pilot.usercenter.protocol.d.H, false, false);
        }
    }
}
