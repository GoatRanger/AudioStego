package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;
import java.util.ArrayList;

public class DataCameraDeleteFile extends n implements e {
    private static DataCameraDeleteFile instance = null;
    private ArrayList<Integer> indexs;
    private int num;

    public static synchronized DataCameraDeleteFile getInstance() {
        DataCameraDeleteFile dataCameraDeleteFile;
        synchronized (DataCameraDeleteFile.class) {
            if (instance == null) {
                instance = new DataCameraDeleteFile();
            }
            dataCameraDeleteFile = instance;
        }
        return dataCameraDeleteFile;
    }

    public DataCameraDeleteFile setNum(int i) {
        this.num = i;
        return this;
    }

    public DataCameraDeleteFile setIndexs(ArrayList<Integer> arrayList) {
        this.indexs = arrayList;
        return this;
    }

    public ArrayList<Integer> getFailNum() {
        int i = 0;
        int intValue = ((Integer) get(0, 1, Integer.class)).intValue();
        if (intValue <= 0) {
            return null;
        }
        ArrayList<Integer> arrayList = new ArrayList(intValue);
        while (i < intValue) {
            arrayList.add(get((i * 4) + 1, 4, Integer.class));
            i++;
        }
        return arrayList;
    }

    protected void doPack() {
        if (this.num > 0) {
            this._sendData = new byte[((this.num * 4) + 1)];
            this._sendData[0] = (byte) this.num;
            for (int i = 0; i < this.indexs.size(); i++) {
                System.arraycopy(c.a(((Integer) this.indexs.get(i)).intValue()), 0, this._sendData, (i * 4) + 1, 4);
            }
            return;
        }
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.num;
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.a.a();
        cVar.n = dji.midware.data.config.P3.d.a.r.a();
        cVar.v = 4000;
        start(cVar, dVar);
    }
}
