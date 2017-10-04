package dji.common.error;

public class DJISDKError extends DJIError {
    public static final DJISDKError APPLICATION_NOT_ACTIVATED = new DJISDKError("Application is not registered");
    public static final DJISDKError COMMAND_EXECUTION_ERROR = new DJISDKError("There was an error executing the command");
    public static final DJISDKError CONNECTION_TO_SDK_ERROR = new DJISDKError("There was an error connecting to the SDK");
    public static final DJISDKError FEATURE_NOT_SUPPORTED = new DJISDKError("This feature is not supported in the SDK");
    public static final DJISDKError GET_PARAMETERS_ERROR = new DJISDKError("Getting parameters operation failed");
    public static final DJISDKError INVALID_PARAMETERS = new DJISDKError("The parameters are invalid. Please review and submit the request");
    private static final DJISDKError LEVEL_NOT_PERMITTED = new DJISDKError("This interface needs a higher level app key, please apply from DJI's developer portal");
    public static final DJISDKError NOT_DEFINED = new DJISDKError("Not defined error.");
    public static final DJISDKError REGISTRATION_AESENCRYPT_ERROR = new DJISDKError("Server error, please contact <dev@dji.com> for help.");
    public static final DJISDKError REGISTRATION_AESENCRYPT_FAILED = new DJISDKError("Server error, please contact <dev@dji.com> for help");
    public static final DJISDKError REGISTRATION_APPKEY_INVALID_PLATFORM = new DJISDKError("The app key is not valid for this platform.");
    public static final DJISDKError REGISTRATION_APPKEY_LEVEL_NOT_PERMITTED = new DJISDKError("The app key does not have the required permission.");
    public static final DJISDKError REGISTRATION_APPKEY_NOT_EXIST = new DJISDKError("The app key does not exist. Please check the app key you provided.");
    public static final DJISDKError REGISTRATION_APPKEY_PROHIBITED = new DJISDKError("The app key is prohibited, please contact <dev@dji.com> for help.");
    public static final DJISDKError REGISTRATION_BUNDLE_NOT_MATCH = new DJISDKError("The bundle identifier of your app should be identical to the one you registered on the website.");
    public static final DJISDKError REGISTRATION_COULD_NOT_CONNECT_TO_INTERNET = new DJISDKError("For first time registration, app should be connected to Internet.");
    public static final DJISDKError REGISTRATION_DEVICE_NOT_MATCH = new DJISDKError("Attempt to copy metadata from another registered device is not permitted.");
    public static final DJISDKError REGISTRATION_EMPTY_APPKEY = new DJISDKError("The app key was not provided.");
    public static final DJISDKError REGISTRATION_HTTP_TIMEOUT = new DJISDKError("The server may be busy or is not reachable.");
    public static final DJISDKError REGISTRATION_INVALID_APPKEY = new DJISDKError("The app key submitted is invalid. Please check the app key you provided.");
    public static final DJISDKError REGISTRATION_INVALID_METADATA = new DJISDKError("The metadata received from server is invalid, please reconnect to the server and try.");
    public static final DJISDKError REGISTRATION_INVALID_UUID = new DJISDKError("Server error, please contact <dev@dji.com> for help.");
    public static final DJISDKError REGISTRATION_MAX_ACTIVATION_COUNT_REACHED = new DJISDKError("The app key reached maximum number of activations, please contact <dev@dji.com> for help.");
    public static final DJISDKError REGISTRATION_SERVER_DATA_ABNORMAL = new DJISDKError("Server error, please contact <dev@dji.com> for help.");
    public static final DJISDKError REGISTRATION_SERVER_PARSE_FAILSE = new DJISDKError("Server error, please contact <dev@dji.com> for help.");
    public static final DJISDKError REGISTRATION_SERVER_WRITE_FAILS = new DJISDKError("Server error, please contact <dev@dji.com> for help.");
    public static final DJISDKError REGISTRATION_SUCCESS = new DJISDKError("API Key successfully registered");
    public static final DJISDKError REGISTRATION_UNKNOWN_ERROR = new DJISDKError("Unknown error occured during registration");
    public static final DJISDKError SEND_DATA_ERROR = new DJISDKError("There was an error sending the data");
    public static final DJISDKError SET_PARAMETER_ERROR = new DJISDKError("Setting parameters operation failed");
    public static final DJISDKError SYSTEM_BUSY = new DJISDKError("System is busy, please retry later");
    private String mDescription;

    private DJISDKError() {
    }

    private DJISDKError(String str) {
        this.mDescription = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }
}
