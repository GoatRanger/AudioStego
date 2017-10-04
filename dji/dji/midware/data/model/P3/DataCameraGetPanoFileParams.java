package dji.midware.data.model.P3;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.logic.album.a.b.f;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataCameraGetPanoFileParams extends n implements e {
    private static DataCameraGetPanoFileParams instance = null;
    private int index;
    private int subIndex;

    public static synchronized DataCameraGetPanoFileParams getInstance() {
        DataCameraGetPanoFileParams dataCameraGetPanoFileParams;
        synchronized (DataCameraGetPanoFileParams.class) {
            if (instance == null) {
                instance = new DataCameraGetPanoFileParams();
            }
            dataCameraGetPanoFileParams = instance;
        }
        return dataCameraGetPanoFileParams;
    }

    public DataCameraGetPanoFileParams setIndex(int i) {
        this.index = i;
        return this;
    }

    public DataCameraGetPanoFileParams setSubindex(int i) {
        this.subIndex = i;
        return this;
    }

    public int getSubImageNum() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public f getFileType() {
        return f.find(((Integer) get(1, 1, Integer.class)).intValue());
    }

    public int getFileSize() {
        return ((Integer) get(2, 8, Integer.class)).intValue();
    }

    public long getOrgCreateTimestamp() {
        return (long) ((Integer) get(10, 4, Integer.class)).intValue();
    }

    public long getCreateTimestamp() {
        return parseTime((long) ((Integer) get(10, 4, Integer.class)).intValue()).getTime();
    }

    public Date getCreateDate() {
        return parseTime((long) ((Integer) get(10, 4, Integer.class)).intValue());
    }

    public Date parseTime(long j) {
        int i = (int) ((j >> 21) & 15);
        int i2 = (int) ((j >> 16) & 31);
        int i3 = (int) ((j >> 11) & 31);
        int i4 = (int) ((j >> 5) & 63);
        int i5 = (int) (j & 31);
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(((int) (1980 + (j >> 25))) + "-" + i + "-" + i2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i3 + ":" + i4 + ":" + i5);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    protected void doPack() {
        this._sendData = new byte[5];
        System.arraycopy(c.a(this.index), 0, this._sendData, 0, 4);
        this._sendData[4] = (byte) this.subIndex;
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bm.a();
        start(cVar, dVar);
    }
}
