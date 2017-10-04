package dji.midware.data.model.P3;

import android.support.v4.media.TransportMediator;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetStateInfo.FirmErrorType;
import dji.midware.data.model.P3.DataCameraGetStateInfo.PhotoState;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataCameraSetFileIndexMode.FileIndexMode;
import dji.midware.data.model.a.a;

public class DataCameraGetPushStateInfo extends a {
    private static DataCameraGetPushStateInfo instance = null;

    public enum CameraType {
        DJICameraTypeFC350(0),
        DJICameraTypeFC550(1),
        DJICameraTypeFC260(2),
        DJICameraTypeFC300S(3),
        DJICameraTypeFC300X(4),
        DJICameraTypeFC550Raw(5),
        DJICameraTypeFC330X(6),
        DJICameraTypeTau640(7),
        DJICameraTypeTau336(8),
        DJICameraTypeFC220(9),
        DJICameraTypeFC300XW(10),
        DJICameraTypeCV600(11),
        DJICameraTypeFC6310(13),
        DJICameraTypeFC6510(14),
        DJICameraTypeFC6520(15),
        DJICameraTypeFC220S(18),
        DJICameraTypeGD600(20),
        OTHER(255);
        
        private int data;

        private CameraType(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static CameraType find(int i) {
            CameraType cameraType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraType;
        }
    }

    public static synchronized DataCameraGetPushStateInfo getInstance() {
        DataCameraGetPushStateInfo dataCameraGetPushStateInfo;
        synchronized (DataCameraGetPushStateInfo.class) {
            if (instance == null) {
                instance = new DataCameraGetPushStateInfo();
                instance.isNeedPushLosed = true;
                instance.isRemoteModel = true;
            }
            dataCameraGetPushStateInfo = instance;
        }
        return dataCameraGetPushStateInfo;
    }

    public boolean isOK() {
        return getFirmUpgradeErrorState() != FirmErrorType.NO || getSensorState() || getHotState();
    }

    public boolean getConnectState() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 1) == 1;
    }

    public boolean getUsbState() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 1) & 1) == 1;
    }

    public boolean getTimeSyncState() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 2) & 1) == 1;
    }

    public PhotoState getPhotoState() {
        return PhotoState.find((((Integer) get(0, 4, Integer.class)).intValue() >> 3) & 7);
    }

    public RecordType getRecordState() {
        return RecordType.find((((Integer) get(0, 4, Integer.class)).intValue() >> 6) & 3);
    }

    public boolean getSensorState() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 8) & 1) == 1;
    }

    public boolean getSDCardInsertState() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 9) & 1) == 1;
    }

    public SDCardState getSDCardState() {
        return SDCardState.find((((Integer) get(0, 4, Integer.class)).intValue() >> 10) & 15);
    }

    public SDCardState getSDCardState(boolean z) {
        if (getUsbState()) {
            return SDCardState.USBConnected;
        }
        return SDCardState.find((((Integer) get(0, 4, Integer.class)).intValue() >> 10) & 15);
    }

    public boolean getFirmUpgradeState() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 14) & 1) == 1;
    }

    public FirmErrorType getFirmUpgradeErrorState() {
        return FirmErrorType.find((((Integer) get(0, 4, Integer.class)).intValue() >> 15) & 3);
    }

    public boolean getHotState() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 17) & 1) == 1;
    }

    public boolean getEnabledPhoto() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 18) & 1) == 0;
    }

    public boolean getIsStoring() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 19) & 1) == 1;
    }

    public boolean getIsTimePhotoing() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 20) & 1) == 1;
    }

    public EncryptStatus getEncryptStatus() {
        return EncryptStatus.find((((Integer) get(0, 4, Integer.class)).intValue() >> 22) & 3);
    }

    public boolean getIsGimbalBusy() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 27) & 1) == 1;
    }

    public boolean beInTrackingMode() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 268435456) != 0;
    }

    public MODE getMode() {
        return MODE.find(((Integer) get(4, 1, Integer.class)).intValue());
    }

    public int getSDCardTotalSize() {
        return ((Integer) get(5, 4, Integer.class)).intValue();
    }

    public int getSDCardFreeSize() {
        return ((Integer) get(9, 4, Integer.class)).intValue();
    }

    public long getRemainedShots() {
        return ((Long) get(13, 4, Long.class)).longValue();
    }

    public int getRemainedTime() {
        return ((Integer) get(17, 4, Integer.class)).intValue();
    }

    public FileIndexMode getFileIndexMode() {
        if (this._recData == null) {
            return FileIndexMode.a;
        }
        return FileIndexMode.find(((Integer) get(21, 1, Integer.class)).intValue());
    }

    public boolean getFastPlayBackEnabled() {
        return (((Integer) get(22, 1, Integer.class)).intValue() >> 7) == 1;
    }

    public int getFastPlayBackTime() {
        return ((Integer) get(22, 1, Integer.class)).intValue() & TransportMediator.KEYCODE_MEDIA_PAUSE;
    }

    public boolean getPhotoOsdTimeIsShow() {
        return (((Integer) get(23, 2, Integer.class)).intValue() & 1) == 1;
    }

    public boolean getPhotoOsdApertureIsShow() {
        return ((((Integer) get(23, 2, Integer.class)).intValue() >> 1) & 1) == 1;
    }

    public boolean getPhotoOsdShutterIsShow() {
        return ((((Integer) get(23, 2, Integer.class)).intValue() >> 2) & 1) == 1;
    }

    public boolean getPhotoOsdIsoIsShow() {
        return ((((Integer) get(23, 2, Integer.class)).intValue() >> 3) & 1) == 1;
    }

    public boolean getPhotoOsdExposureIsShow() {
        return ((((Integer) get(23, 2, Integer.class)).intValue() >> 4) & 1) == 1;
    }

    public boolean getPhotoOsdSharpeIsShow() {
        return ((((Integer) get(23, 2, Integer.class)).intValue() >> 5) & 1) == 1;
    }

    public boolean getPhotoOsdContrastIsShow() {
        return ((((Integer) get(23, 2, Integer.class)).intValue() << 6) & 1) == 1;
    }

    public boolean getPhotoOsdSaturationIsShow() {
        return ((((Integer) get(23, 2, Integer.class)).intValue() << 7) & 1) == 1;
    }

    public boolean beInDebugMode() {
        return ((Integer) get(27, 1, Integer.class)).intValue() != 0;
    }

    public int getVideoRecordTime() {
        return ((Integer) get(29, 2, Integer.class)).intValue();
    }

    public int getMaxPhotoNum() {
        return ((Integer) get(31, 1, Integer.class)).intValue();
    }

    public boolean isHistogramEnable() {
        return (((Integer) get(32, 1, Integer.class)).intValue() & 1) == 1;
    }

    public CameraType getCameraType() {
        return CameraType.find(((Integer) get(33, 1, Integer.class)).intValue());
    }

    public int getVerstion() {
        return ((Integer) get(36, 1, Integer.class)).intValue();
    }

    protected void setPushRecData(byte[] bArr) {
        super.setPushRecData(bArr);
    }

    public void clear() {
        super.clear();
    }

    protected void doPack() {
    }
}
