package dji.midware.data.model.P3;

import android.support.v4.view.MotionEventCompat;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataCameraSetVideoRecordMode extends n implements e {
    int a = 0;
    int b = 0;
    int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;

    public DataCameraSetVideoRecordMode a(int i) {
        this.e = i;
        return this;
    }

    public DataCameraSetVideoRecordMode b(int i) {
        this.f = i;
        return this;
    }

    public DataCameraSetVideoRecordMode c(int i) {
        this.g = i;
        return this;
    }

    public DataCameraSetVideoRecordMode d(int i) {
        this.a = i;
        return this;
    }

    public DataCameraSetVideoRecordMode e(int i) {
        this.b = i;
        return this;
    }

    public DataCameraSetVideoRecordMode f(int i) {
        this.c = i;
        return this;
    }

    public DataCameraSetVideoRecordMode g(int i) {
        this.h = i;
        return this;
    }

    public DataCameraSetVideoRecordMode h(int i) {
        this.i = i;
        return this;
    }

    public DataCameraSetVideoRecordMode a(int i, int i2, int i3) {
        this.d = i;
        this.h = i2;
        this.i = i3;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[15];
        this._sendData[0] = (byte) this.d;
        this._sendData[1] = (byte) (this.e | (this.g << 2));
        this._sendData[2] = (byte) this.f;
        this._sendData[3] = (byte) (this.h & 255);
        this._sendData[4] = (byte) ((this.h & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
        Object a = c.a(this.i);
        System.arraycopy(a, 0, this._sendData, 5, 2);
        System.arraycopy(a, 2, this._sendData, 7, 2);
        this._sendData[9] = (byte) (this.a & 255);
        this._sendData[10] = (byte) ((this.a & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
        this._sendData[11] = (byte) (this.b & 255);
        this._sendData[12] = (byte) ((this.b & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
        this._sendData[13] = (byte) (this.c & 255);
        this._sendData[14] = (byte) ((this.c & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
    }

    public void start(d dVar) {
        a(dVar, 200, 3);
    }

    public void a(d dVar, int i, int i2) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.aw.a();
        cVar.p = getSendData();
        cVar.v = i;
        cVar.w = i2;
        start(cVar, dVar);
        DJILogHelper.getInstance().LOGD("", "DJIMethod : start (215)" + this.g, false, true);
    }
}
