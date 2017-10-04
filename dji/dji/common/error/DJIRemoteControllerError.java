package dji.common.error;

import dji.midware.data.config.P3.a;

public class DJIRemoteControllerError extends DJIError {
    public static final DJIRemoteControllerError FIRMWARE_CRC_WRONG = new DJIRemoteControllerError("Firmware CRC value invalid");
    public static final DJIRemoteControllerError FIRMWARE_LENGTH_WRONG = new DJIRemoteControllerError("Firmware length invalid");
    public static final DJIRemoteControllerError FIRMWARE_MATCH_ERROR = new DJIRemoteControllerError("Firmware match error");
    public static final DJIRemoteControllerError FIRMWARE_NON_SEQUENCE = new DJIRemoteControllerError("Firmware not sequence");
    public static final DJIRemoteControllerError FLASH_CLEAR_WRONG = new DJIRemoteControllerError("Flash clear error");
    public static final DJIRemoteControllerError FLASH_FLUSHING = new DJIRemoteControllerError("Firmware flushing");
    public static final DJIRemoteControllerError FLASH_WRITE_WRONG = new DJIRemoteControllerError("Flash write error");
    public static final DJIRemoteControllerError UPDATE_WRONG = new DJIRemoteControllerError("Update error");
    private String mDescription;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$dji$midware$data$config$P3$Ccode = new int[a.values().length];

        static {
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.b.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.c.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.e.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.f.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.g.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.h.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.i.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.j.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.s.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.t.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.u.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.v.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.w.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.x.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.C.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.D.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    private DJIRemoteControllerError() {
    }

    private DJIRemoteControllerError(String str) {
        this.mDescription = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public static DJIError getDJIError(a aVar) {
        if (COMMON_UNKNOWN != DJIError.getDJIError(aVar)) {
            return DJIError.getDJIError(aVar);
        }
        switch (AnonymousClass1.$SwitchMap$dji$midware$data$config$P3$Ccode[aVar.ordinal()]) {
            case 1:
                return COMMON_TIMEOUT;
            case 2:
                return COMMON_UNKNOWN;
            case 3:
                return COMMON_UNKNOWN;
            case 4:
                return COMMON_UNKNOWN;
            case 5:
                return COMMON_TIMEOUT;
            case 6:
                return COMMON_UNKNOWN;
            case 7:
                return COMMON_PARAM_ILLEGAL;
            case 8:
                return COMMON_UNKNOWN;
            case 9:
                return COMMON_UNKNOWN;
            case 10:
                return FIRMWARE_NON_SEQUENCE;
            case 11:
                return FIRMWARE_LENGTH_WRONG;
            case 12:
                return FIRMWARE_CRC_WRONG;
            case 13:
                return FLASH_CLEAR_WRONG;
            case 14:
                return FLASH_WRITE_WRONG;
            case 15:
                return UPDATE_WRONG;
            case 16:
                return FLASH_FLUSHING;
            case 17:
                return COMMON_UNDEFINED;
            default:
                return COMMON_UNKNOWN;
        }
    }
}
