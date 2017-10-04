package dji.common.error;

import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.midware.data.config.P3.a;

public class DJIError {
    public static final DJIError BATTERY_GET_SMART_BATTERY_INFO_FAILED = new DJIError("Get smart battery info failed");
    public static final DJIError COMMAND_NOT_SUPPORTED_BY_FIRMWARE = new DJIError("The command is not supported by the current firmware version.");
    public static final DJIError COMMON_DISCONNECTED = new DJIError("Disconnected");
    public static final DJIError COMMON_EXECUTIONFAILED = new DJIError("The execution could not be executed.");
    public static final DJIError COMMON_EXECUTION_FAILED = new DJIError("The execution could not be executed.");
    public static final DJIError COMMON_PARAM_ILLEGAL = new DJIError("Param Illegal");
    public static final DJIError COMMON_PARAM_INVALID = new DJIError("Param Invalid");
    public static final DJIError COMMON_SYSTEM_BUSY = new DJIError("The system is too busy to execute the action");
    public static final DJIError COMMON_TIMEOUT = new DJIError("Execution of this process has timed out");
    public static final DJIError COMMON_UNDEFINED = new DJIError("Undefined Error");
    public static final DJIError COMMON_UNKNOWN = new DJIError("Server error, please contact <dev@dji.com> for help.");
    public static final DJIError COMMON_UNSUPPORTED = new DJIError("Not supported");
    public static final DJIError FIRMWARE_CRC_WRONG = new DJIError("Firmware crc value invalid");
    public static final DJIError FIRMWARE_LENGTH_WRONG = new DJIError("Firmware length invalid");
    public static final DJIError FIRMWARE_MATCH_WRONG = new DJIError("Firmware match error ");
    public static final DJIError FIRMWARE_NON_SEQUENCE = new DJIError("Firmware sequence number not continuous");
    public static final DJIError FLASH_CLEAR_WRONG = new DJIError("Flash clear error ");
    public static final DJIError FLASH_FLUSHING = new DJIError("Firmware flushing");
    public static final DJIError FLASH_WRITE_WRONG = new DJIError("Flash write error ");
    public static final DJIError IMAGE_TRANSMITTER_INVALID_PARAMETER = new DJIError("The input parameter is out of bound or invalid.");
    public static final DJIError MEDIA_INVALID_REQUEST_TYPE = new DJIError("Media download result: media downloading request type is invalid");
    public static final DJIError MEDIA_NO_SUCH_FILE = new DJIError("Media download result: no such file");
    public static final DJIError MEDIA_REQUEST_CLIENT_ABORT = new DJIError("Media download result: the client aborts the downloading");
    public static final DJIError MEDIA_REQUEST_DISCONNECT = new DJIError("Media download result: the downloading link disconnects");
    public static final DJIError MEDIA_REQUEST_SERVER_ABORT = new DJIError("Media download result: the server aborts the downloading");
    public static final DJIError UNABLE_TO_GET_FIRMWARE_VERSION = new DJIError("Unable to get the firmware version. Note: The sdk will fetch the firmware version from the server so, please ensure you have Internet connectivity before you invoke getVersion().");
    public static final DJIError UNABLE_TO_GET_FLAGS = new DJIError("Unable to get the analytics flags from server. Please ensure you have Internet connectivity before you invoke this method.");
    public static final DJIError UNABLE_TO_GET_FLAG_BUT_RETRY = new DJIError("Unable to get the analytics flags from server, but retrying.  Please ensure you have Internet connectivity before invoking this method");
    public static final DJIError UPDATE_WRONG = new DJIError("Update error");
    protected String mDescription;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$dji$midware$data$config$P3$Ccode = new int[a.values().length];

        static {
            $SwitchMap$dji$logic$album$model$DJIAlbumPullErrorType = new int[DJIAlbumPullErrorType.values().length];
            try {
                $SwitchMap$dji$logic$album$model$DJIAlbumPullErrorType[DJIAlbumPullErrorType.ERROR_REQ.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$dji$logic$album$model$DJIAlbumPullErrorType[DJIAlbumPullErrorType.NO_SUCH_FILE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$dji$logic$album$model$DJIAlbumPullErrorType[DJIAlbumPullErrorType.DATA_NOMATCH.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$dji$logic$album$model$DJIAlbumPullErrorType[DJIAlbumPullErrorType.TIMEOUT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$dji$logic$album$model$DJIAlbumPullErrorType[DJIAlbumPullErrorType.CLIENT_ABORT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$dji$logic$album$model$DJIAlbumPullErrorType[DJIAlbumPullErrorType.SERVER_ABORT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$dji$logic$album$model$DJIAlbumPullErrorType[DJIAlbumPullErrorType.DISCONNECT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$dji$logic$album$model$DJIAlbumPullErrorType[DJIAlbumPullErrorType.UNKNOW.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.a.ordinal()] = 1;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.b.ordinal()] = 2;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.c.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.e.ordinal()] = 4;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.f.ordinal()] = 5;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.g.ordinal()] = 6;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.h.ordinal()] = 7;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.i.ordinal()] = 8;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.j.ordinal()] = 9;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.D.ordinal()] = 10;
            } catch (NoSuchFieldError e18) {
            }
        }
    }

    protected DJIError() {
    }

    private DJIError(String str) {
        this.mDescription = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public static DJIError getDJIError(a aVar) {
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
                return COMMON_UNDEFINED;
            default:
                return COMMON_UNKNOWN;
        }
    }

    public static DJIError getDjiError(DJIAlbumPullErrorType dJIAlbumPullErrorType) {
        switch (dJIAlbumPullErrorType) {
            case ERROR_REQ:
                return MEDIA_INVALID_REQUEST_TYPE;
            case NO_SUCH_FILE:
                return MEDIA_NO_SUCH_FILE;
            case DATA_NOMATCH:
                return COMMON_UNKNOWN;
            case TIMEOUT:
                return COMMON_TIMEOUT;
            case CLIENT_ABORT:
                return MEDIA_REQUEST_CLIENT_ABORT;
            case SERVER_ABORT:
                return MEDIA_REQUEST_SERVER_ABORT;
            case DISCONNECT:
                return MEDIA_REQUEST_DISCONNECT;
            case UNKNOW:
                return COMMON_UNKNOWN;
            default:
                return COMMON_UNKNOWN;
        }
    }

    public String toString() {
        if (this.mDescription != null) {
            return this.mDescription;
        }
        return super.toString();
    }
}
