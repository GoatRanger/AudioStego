package dji.midware.data.model.P3;

import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.data.model.P3.DataFlycSetJoyStickParams.FlycMode;
import dji.midware.data.model.P3.DataGimbalControl.MODE;
import dji.midware.e.d;

@Deprecated
public abstract class DataSpecialControl extends n {
    protected abstract void _reset();

    protected abstract void doPack();

    public abstract DataSpecialControl init();

    protected abstract DataSpecialControl reset();

    public abstract DataSpecialControl resetGimbal();

    public abstract DataSpecialControl selfieGimbal();

    public abstract DataSpecialControl setFlyGoHomeStatus(FlyGoHomeStaus flyGoHomeStaus);

    public abstract DataSpecialControl setFlycMode(FlycMode flycMode);

    public abstract DataSpecialControl setGimbalMode(MODE mode);

    public abstract DataSpecialControl setGimbalMode(MODE mode, boolean z);

    public abstract DataSpecialControl setPhotoType(TYPE type);

    public abstract DataSpecialControl setPhotoType(TYPE type, int i, int i2);

    public abstract DataSpecialControl setPlayBackBrowserScaleType(short s);

    public abstract DataSpecialControl setPlayBackBrowserType(PlayBrowseType playBrowseType, byte b, byte b2);

    public abstract DataSpecialControl setPlayBackPlayCtr(PlayCtrType playCtrType, byte b);

    public abstract DataSpecialControl setPlayBackType(boolean z);

    public abstract DataSpecialControl setRecordType(boolean z);

    public abstract DataSpecialControl setRecordType(boolean z, int i, int i2);

    public abstract void start(long j);

    public abstract void start(d dVar);

    public abstract void stop();

    private static final boolean useNewControl() {
        ProductType c = i.getInstance().c();
        return ProductType.Pomato == c || ProductType.Orange2 == c;
    }

    @Deprecated
    public static synchronized DataSpecialControl getInstance() {
        DataSpecialControl instance;
        synchronized (DataSpecialControl.class) {
            if (useNewControl()) {
                instance = DataNewSpecialControl.getInstance();
            } else {
                instance = DataOldSpecialControl.getInstance();
            }
        }
        return instance;
    }
}
