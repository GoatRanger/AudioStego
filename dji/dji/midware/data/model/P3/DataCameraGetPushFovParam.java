package dji.midware.data.model.P3;

import com.alipay.sdk.j.i;
import dji.log.DJILogHelper;
import dji.midware.data.model.a.a;
import dji.pilot.visual.a.d;
import dji.sdksharedlib.b.b;
import java.util.Arrays;

public class DataCameraGetPushFovParam extends a {
    private static DataCameraGetPushFovParam instance = null;
    private float mFovH = 0.0f;
    private float mFovV = 0.0f;

    public static synchronized DataCameraGetPushFovParam getInstance() {
        DataCameraGetPushFovParam dataCameraGetPushFovParam;
        synchronized (DataCameraGetPushFovParam.class) {
            if (instance == null) {
                instance = new DataCameraGetPushFovParam();
            }
            dataCameraGetPushFovParam = instance;
        }
        return dataCameraGetPushFovParam;
    }

    public void setRecData(byte[] bArr) {
        if (!Arrays.equals(this._recData, bArr)) {
            super.setRecData(bArr);
            if (hasFovData()) {
                this.mFovH = (float) Math.toDegrees(Math.atan2((double) (((float) getImageWidth()) * d.c), (double) getLensFocalLength()) * 2.0d);
                this.mFovV = (float) Math.toDegrees(Math.atan2((double) (((float) getImageHeight()) * d.c), (double) getLensFocalLength()) * 2.0d);
            }
            DJILogHelper.getInstance().LOGD(b.a, "Fovh-" + this.mFovH + i.b + this.mFovV, false, true);
        }
    }

    public boolean hasFovData() {
        return this._recData != null && this._recData.length > 0;
    }

    public float getFovH() {
        if (isGetted()) {
            return this.mFovH;
        }
        return 0.0f;
    }

    public float getFovV() {
        if (isGetted()) {
            return this.mFovV;
        }
        return 0.0f;
    }

    public int getImageWidth() {
        return ((Integer) get(0, 4, Integer.class)).intValue();
    }

    public int getImageHeight() {
        return ((Integer) get(4, 4, Integer.class)).intValue();
    }

    public int getImageRatio() {
        return ((Integer) get(8, 4, Integer.class)).intValue();
    }

    public int getLensFocalLength() {
        return ((Integer) get(12, 4, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
