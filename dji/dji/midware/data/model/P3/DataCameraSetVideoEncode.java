package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraSetVideoEncode extends n implements e {
    private VideoEncodeType a = VideoEncodeType.H264;
    private VideoEncodeType b = VideoEncodeType.H264;

    public enum VideoEncodeType {
        H264(0),
        H265(1),
        OTHER(100);
        
        private final int d;

        private VideoEncodeType(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i) {
            return this.d == i;
        }

        public static VideoEncodeType find(int i) {
            VideoEncodeType videoEncodeType = H264;
            for (VideoEncodeType videoEncodeType2 : values()) {
                if (videoEncodeType2.a(i)) {
                    return videoEncodeType2;
                }
            }
            return videoEncodeType;
        }
    }

    public DataCameraSetVideoEncode a(VideoEncodeType videoEncodeType) {
        this.a = videoEncodeType;
        return this;
    }

    public DataCameraSetVideoEncode b(VideoEncodeType videoEncodeType) {
        this.b = videoEncodeType;
        return this;
    }

    public DataCameraSetVideoEncode a() {
        this.a = DataCameraGetPushShotParams.getInstance().getPrimaryVideoEncodeType();
        this.b = DataCameraGetPushShotParams.getInstance().getSecondaryVideoEncodeType();
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        byte[] bArr = this._sendData;
        bArr[0] = (byte) (bArr[0] | this.a.a());
        bArr = this._sendData;
        bArr[0] = (byte) (bArr[0] | (this.b.a() << 4));
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bn.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
