package dji.common.error;

import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.midware.data.config.P3.a;

public class DJICameraError extends DJIError {
    public static final DJICameraError CAMERASDCARDFULL = new DJICameraError("The Camera's SD Card is full");
    public static final DJICameraError CAMERA_CANNOT_SET_PARAMETERS_IN_THIS_STATE = new DJICameraError("Cannot set the parameters in this state");
    public static final DJICameraError CAMERA_CONNECTION_NOT_OK = new DJICameraError("Connection is not ok");
    public static final DJICameraError CAMERA_COULD_NOT_DELETE_ALL_FILES = new DJICameraError("");
    public static final DJICameraError CAMERA_EXEC_TIMEOUT = new DJICameraError("Camera's execution of this action has timed out");
    public static final DJICameraError CAMERA_GET_REMOTE_MEDIA_FAILED = new DJICameraError("Get remote media failed");
    public static final DJICameraError CAMERA_GET_THUMBNAIL_FAILED = new DJICameraError("Failed to get the thumbnail");
    public static final DJICameraError CAMERA_INVALID_PARAM = new DJICameraError("Camera received invalid parameters");
    public static final DJICameraError CAMERA_NO_SDCARD = new DJICameraError("Camera has no SD Card");
    public static final DJICameraError CAMERA_PARAMETERS_GET_FAILED = new DJICameraError("Camera param get failed");
    public static final DJICameraError CAMERA_PARAMETERS_NOT_AVAILABLE = new DJICameraError("Camera received parameters of invalid length");
    public static final DJICameraError CAMERA_PARAMETERS_SET_FAILED = new DJICameraError("Camera failed to set the parameters it received");
    public static final DJICameraError CAMERA_SDCARD_ERROR = new DJICameraError("Error accessing the SD Card");
    public static final DJICameraError CAMERA_SENSOR_ERROR = new DJICameraError("Camera sensor error");
    public static final DJICameraError CAMERA_SYSTEM_ERROR = new DJICameraError("Camera system error ");
    public static final DJICameraError CAMERA_UNSUPPORTED_CMD_STATE = new DJICameraError("Camera is busy or the command is not supported in the Camera's current state");
    public static final DJICameraError CHECK_PERMISSION_LEVEL1_IS_INVALID = new DJICameraError("Level 1 API permission is invalid");
    public static final DJICameraError COMMON_CAMERA_UNKNOWN = new DJICameraError("Server error, please contact <dev@dji.com> for help.");
    public static final DJICameraError MEDIA_INVALID_REQUEST_TYPE = new DJICameraError("Media download result: media downloading request type is invalid");
    public static final DJICameraError MEDIA_NO_SUBMEDIA_FILES = new DJICameraError("Sub media fetching result: No sub media files");
    public static final DJICameraError MEDIA_NO_SUCH_FILE = new DJICameraError("Media download result: no such file");
    public static final DJICameraError MEDIA_REQUEST_CLIENT_ABORT = new DJICameraError("Media download result: the client aborted the download");
    public static final DJICameraError MEDIA_REQUEST_DISCONNECT = new DJICameraError("Media download result: the download link disconnected");
    public static final DJICameraError MEDIA_REQUEST_SERVER_ABORT = new DJICameraError("Media download result: the server aborted the download");
    private String mDescription;

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
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.k.ordinal()] = 1;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.l.ordinal()] = 2;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.m.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.n.ordinal()] = 4;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.o.ordinal()] = 5;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.q.ordinal()] = 6;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.r.ordinal()] = 7;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.h.ordinal()] = 8;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.i.ordinal()] = 9;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    private DJICameraError() {
    }

    private DJICameraError(String str) {
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
                return CAMERA_PARAMETERS_SET_FAILED;
            case 2:
                return CAMERA_PARAMETERS_GET_FAILED;
            case 3:
                return CAMERA_SDCARD_ERROR;
            case 4:
                return CAMERASDCARDFULL;
            case 5:
                return CAMERA_SDCARD_ERROR;
            case 6:
                return COMMON_CAMERA_UNKNOWN;
            case 7:
                return CAMERA_PARAMETERS_NOT_AVAILABLE;
            case 8:
                return CAMERA_INVALID_PARAM;
            case 9:
                return CAMERA_UNSUPPORTED_CMD_STATE;
            default:
                return COMMON_CAMERA_UNKNOWN;
        }
    }

    public static DJIError getDJIError(DJIAlbumPullErrorType dJIAlbumPullErrorType) {
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
}
