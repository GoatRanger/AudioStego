package dji.common.camera;

import android.util.Log;
import dji.common.camera.DJICameraSettingsDef.CameraAperture;
import dji.common.camera.DJICameraSettingsDef.CameraExposureCompensation;
import dji.common.camera.DJICameraSettingsDef.CameraISO;
import dji.common.camera.DJICameraSettingsDef.CameraShutterSpeed;
import dji.gs.c.e;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CameraUtils {

    public enum RecordVideoTimeoutLock {
        TIMEOUT_LOCK;
        
        private CountDownLatch mCountDownLatch;
        private boolean result;
        private boolean threadInitiatedState;

        public static RecordVideoTimeoutLock getInstance() {
            return TIMEOUT_LOCK;
        }

        public void waitResult() {
            try {
                this.mCountDownLatch.await(2000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void reset() {
            this.mCountDownLatch = new CountDownLatch(1);
            this.result = false;
            this.threadInitiatedState = true;
        }

        public void setResult(boolean z) {
            this.mCountDownLatch.countDown();
            this.result = z;
            this.threadInitiatedState = false;
        }

        public boolean getResult() {
            return this.result;
        }

        public boolean getThreadInitiatedState() {
            return this.threadInitiatedState;
        }
    }

    public enum ShootPhotoTimeoutLock {
        TIMEOUT_LOCK;
        
        private CountDownLatch mCountDownLatch;
        private boolean result;
        private boolean threadInitiatedState;

        public static ShootPhotoTimeoutLock getInstance() {
            return TIMEOUT_LOCK;
        }

        public void waitResult() {
            Log.e("WaitResult", "1");
            try {
                this.mCountDownLatch.await(2000, TimeUnit.MILLISECONDS);
                Log.e("WaitResult", "2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void reset() {
            this.mCountDownLatch = new CountDownLatch(1);
            Log.e("WaitResult", "3");
            this.result = false;
            this.threadInitiatedState = true;
        }

        public void setResult(boolean z) {
            this.mCountDownLatch.countDown();
            this.result = z;
            this.threadInitiatedState = false;
        }

        public boolean getResult() {
            return this.result;
        }

        public boolean getThreadInitiatedState() {
            return this.threadInitiatedState;
        }
    }

    public static HashMap<Integer, CameraAperture> buildApertureMapRevert() {
        HashMap<Integer, CameraAperture> hashMap = new HashMap();
        hashMap.put(Integer.valueOf(1000), CameraAperture.F_10p0);
        hashMap.put(Integer.valueOf(1100), CameraAperture.F_11p0);
        hashMap.put(Integer.valueOf(1300), CameraAperture.F_13p0);
        hashMap.put(Integer.valueOf(1400), CameraAperture.F_14p0);
        hashMap.put(Integer.valueOf(1600), CameraAperture.F_16p0);
        hashMap.put(Integer.valueOf(1800), CameraAperture.F_18p0);
        hashMap.put(Integer.valueOf(160), CameraAperture.F_1p6);
        hashMap.put(Integer.valueOf(170), CameraAperture.F_1p7);
        hashMap.put(Integer.valueOf(180), CameraAperture.F_1p8);
        hashMap.put(Integer.valueOf(2000), CameraAperture.F_20p0);
        hashMap.put(Integer.valueOf(2200), CameraAperture.F_22p0);
        hashMap.put(Integer.valueOf(200), CameraAperture.F_2p0);
        hashMap.put(Integer.valueOf(FTPCodes.SERVICE_READY_FOR_NEW_USER), CameraAperture.F_2p2);
        hashMap.put(Integer.valueOf(240), CameraAperture.F_2p4);
        hashMap.put(Integer.valueOf(250), CameraAperture.F_2p5);
        hashMap.put(Integer.valueOf(280), CameraAperture.F_2p8);
        hashMap.put(Integer.valueOf(320), CameraAperture.F_3p2);
        hashMap.put(Integer.valueOf(340), CameraAperture.F_3p4);
        hashMap.put(Integer.valueOf(FTPCodes.PENDING_FURTHER_INFORMATION), CameraAperture.F_3p5);
        hashMap.put(Integer.valueOf(400), CameraAperture.F_4p0);
        hashMap.put(Integer.valueOf(FTPCodes.FILE_ACTION_NOT_TAKEN), CameraAperture.F_4p5);
        hashMap.put(Integer.valueOf(480), CameraAperture.F_4p8);
        hashMap.put(Integer.valueOf(500), CameraAperture.F_5p0);
        hashMap.put(Integer.valueOf(560), CameraAperture.F_5p6);
        hashMap.put(Integer.valueOf(630), CameraAperture.F_6p3);
        hashMap.put(Integer.valueOf(680), CameraAperture.F_6p8);
        hashMap.put(Integer.valueOf(710), CameraAperture.F_7p1);
        hashMap.put(Integer.valueOf(e.g), CameraAperture.F_8p0);
        hashMap.put(Integer.valueOf(900), CameraAperture.F_9p0);
        hashMap.put(Integer.valueOf(960), CameraAperture.F_9p6);
        return hashMap;
    }

    public static EnumMap<CameraAperture, Short> buildApertureMap() {
        EnumMap<CameraAperture, Short> enumMap = new EnumMap(CameraAperture.class);
        enumMap.put(CameraAperture.F_10p0, Short.valueOf((short) 1000));
        enumMap.put(CameraAperture.F_11p0, Short.valueOf((short) 1100));
        enumMap.put(CameraAperture.F_13p0, Short.valueOf((short) 1300));
        enumMap.put(CameraAperture.F_14p0, Short.valueOf((short) 1400));
        enumMap.put(CameraAperture.F_16p0, Short.valueOf((short) 1600));
        enumMap.put(CameraAperture.F_18p0, Short.valueOf((short) 1800));
        enumMap.put(CameraAperture.F_1p6, Short.valueOf((short) 160));
        enumMap.put(CameraAperture.F_1p7, Short.valueOf((short) 170));
        enumMap.put(CameraAperture.F_1p8, Short.valueOf((short) 180));
        enumMap.put(CameraAperture.F_20p0, Short.valueOf((short) 2000));
        enumMap.put(CameraAperture.F_22p0, Short.valueOf((short) 2200));
        enumMap.put(CameraAperture.F_2p0, Short.valueOf((short) 200));
        enumMap.put(CameraAperture.F_2p2, Short.valueOf((short) 220));
        enumMap.put(CameraAperture.F_2p4, Short.valueOf((short) 240));
        enumMap.put(CameraAperture.F_2p5, Short.valueOf((short) 250));
        enumMap.put(CameraAperture.F_2p8, Short.valueOf((short) 280));
        enumMap.put(CameraAperture.F_3p2, Short.valueOf((short) 320));
        enumMap.put(CameraAperture.F_3p4, Short.valueOf((short) 340));
        enumMap.put(CameraAperture.F_3p5, Short.valueOf((short) 350));
        enumMap.put(CameraAperture.F_4p0, Short.valueOf((short) 400));
        enumMap.put(CameraAperture.F_4p5, Short.valueOf((short) 450));
        enumMap.put(CameraAperture.F_4p8, Short.valueOf((short) 480));
        enumMap.put(CameraAperture.F_5p0, Short.valueOf((short) 500));
        enumMap.put(CameraAperture.F_5p6, Short.valueOf((short) 560));
        enumMap.put(CameraAperture.F_6p3, Short.valueOf((short) 630));
        enumMap.put(CameraAperture.F_6p8, Short.valueOf((short) 680));
        enumMap.put(CameraAperture.F_7p1, Short.valueOf((short) 710));
        enumMap.put(CameraAperture.F_8p0, Short.valueOf((short) 800));
        enumMap.put(CameraAperture.F_9p0, Short.valueOf((short) 900));
        enumMap.put(CameraAperture.F_9p6, Short.valueOf((short) 960));
        return enumMap;
    }

    public static CameraAperture getRealCameraAperture(int i) {
        int realApertureSize = DataCameraGetPushShotParams.getInstance().getRealApertureSize();
        if (buildApertureMapRevert().containsKey(Integer.valueOf(realApertureSize))) {
            return (CameraAperture) buildApertureMapRevert().get(Integer.valueOf(realApertureSize));
        }
        return CameraAperture.Unknown;
    }

    public static boolean isSupportSSD(CameraType cameraType) {
        if (cameraType == null) {
            cameraType = i.getInstance().b();
        }
        return cameraType == CameraType.DJICameraTypeFC550Raw;
    }

    public static CameraExposureCompensation getRealCameraExposureCompensation(int i) {
        if (DataCameraGetPushShotParams.getInstance().getExposureMode().a() == 1) {
            return CameraExposureCompensation.find(DataCameraGetPushShotParams.getInstance().getExposureCompensation());
        }
        return CameraExposureCompensation.find(i);
    }

    public static CameraShutterSpeed getRealShutterSpeed(boolean z, int i, int i2) {
        float floatValue = Float.valueOf(i + "." + i2).floatValue();
        if (z) {
            return CameraShutterSpeed.find(1.0f / floatValue);
        }
        return CameraShutterSpeed.find(floatValue);
    }

    public static CameraISO getRealCameraISO(int i) {
        switch (i) {
            case 100:
                return CameraISO.ISO_100;
            case 200:
                return CameraISO.ISO_200;
            case 400:
                return CameraISO.ISO_400;
            case e.g /*800*/:
                return CameraISO.ISO_800;
            case 1600:
                return CameraISO.ISO_1600;
            case 3200:
                return CameraISO.ISO_3200;
            case 6400:
                return CameraISO.ISO_6400;
            case 12800:
                return CameraISO.ISO_12800;
            case 25600:
                return CameraISO.ISO_25600;
            default:
                return CameraISO.Unknown;
        }
    }
}
