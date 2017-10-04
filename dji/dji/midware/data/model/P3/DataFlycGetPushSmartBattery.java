package dji.midware.data.model.P3;

import android.support.v4.media.TransportMediator;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.n;

public class DataFlycGetPushSmartBattery extends n {
    public static final int MaskBatteryCellError = 64;
    public static final int MaskBatteryCommunicateError = 128;
    public static final int MaskBatteryDangerous = 8192;
    public static final int MaskBatteryDangerousWarning = 16384;
    public static final int MaskBatteryFirstChargeNotFull = 2048;
    public static final int MaskBatteryLimitOutputMax = 4096;
    public static final int MaskBatteryNotReady = 1024;
    public static final int MaskBatteryTempVoltageLow = 512;
    public static final int MaskMainVoltageLowGoHOme = 16;
    public static final int MaskMainVoltageLowLand = 32;
    public static final int MaskSmartBatteryReqGoHome = 4;
    public static final int MaskSmartBatteryReqLand = 8;
    public static final int MaskUserBatteryReqGoHome = 1;
    public static final int MaskUserBatteryReqLand = 2;
    public static final int MaskVoltageLowNeedLand = 256;
    private static DataFlycGetPushSmartBattery instance = null;

    public enum SmartGoHomeStatus {
        NON_GOHOME((byte) 0),
        GOHOME((byte) 1),
        GOHOME_ALREADY((byte) 2);
        
        private byte _value;

        private SmartGoHomeStatus(byte b) {
            this._value = (byte) 0;
            this._value = b;
        }

        public final byte value() {
            return this._value;
        }

        public boolean _equals(byte b) {
            return this._value == b;
        }

        public static SmartGoHomeStatus find(byte b) {
            for (SmartGoHomeStatus smartGoHomeStatus : values()) {
                if (smartGoHomeStatus._equals(b)) {
                    return smartGoHomeStatus;
                }
            }
            return NON_GOHOME;
        }
    }

    public static synchronized DataFlycGetPushSmartBattery getInstance() {
        DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery;
        synchronized (DataFlycGetPushSmartBattery.class) {
            if (instance == null) {
                instance = new DataFlycGetPushSmartBattery();
            }
            dataFlycGetPushSmartBattery = instance;
        }
        return dataFlycGetPushSmartBattery;
    }

    public DataFlycGetPushSmartBattery(boolean z) {
        super(z);
    }

    public int getUsefulTime() {
        return ((Integer) get(0, 2, Integer.class)).intValue();
    }

    public int getGoHomeTime() {
        return ((Integer) get(2, 2, Integer.class)).intValue();
    }

    public int getLandTime() {
        return ((Integer) get(4, 2, Integer.class)).intValue();
    }

    public int getGoHomeBattery() {
        return ((Integer) get(6, 2, Integer.class)).intValue();
    }

    public int getLandBattery() {
        return ((Integer) get(8, 2, Integer.class)).intValue();
    }

    public float getSafeFlyRadius() {
        return ((Float) get(10, 4, Float.class)).floatValue();
    }

    public float getVolumeComsume() {
        return ((Float) get(14, 4, Float.class)).floatValue();
    }

    public int getStatus() {
        return ((Integer) get(18, 4, Integer.class)).intValue();
    }

    public SmartGoHomeStatus getGoHomeStatus() {
        return SmartGoHomeStatus.find((byte) ((Integer) get(22, 1, Integer.class)).intValue());
    }

    public int getGoHomeCountDown() {
        return ((Integer) get(23, 1, Integer.class)).intValue();
    }

    public int getVoltage() {
        return ((Integer) get(24, 2, Integer.class)).intValue();
    }

    public int getBattery() {
        if (d.isNewForOsd()) {
            return ((Integer) get(26, 1, Integer.class)).intValue();
        }
        return DataOsdGetPushCommon.getInstance().getBattery();
    }

    public int getBatteryPercent() {
        return ((Integer) get(26, 1, Integer.class)).intValue();
    }

    public int getLowWarning() {
        return ((Integer) get(27, 1, Integer.class)).intValue() & TransportMediator.KEYCODE_MEDIA_PAUSE;
    }

    public boolean getLowWarningGoHome() {
        return (((Integer) get(27, 1, Integer.class)).intValue() & 128) != 0;
    }

    public int getSeriousLowWarning() {
        return ((Integer) get(28, 1, Integer.class)).intValue() & TransportMediator.KEYCODE_MEDIA_PAUSE;
    }

    public boolean getSeriousLowWarningLanding() {
        return (((Integer) get(28, 1, Integer.class)).intValue() & 128) != 0;
    }

    public int getVoltagePercent() {
        return ((Integer) get(29, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
