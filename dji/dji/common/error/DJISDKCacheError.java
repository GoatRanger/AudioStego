package dji.common.error;

public class DJISDKCacheError extends DJIError {
    public static final DJISDKCacheError DISCONNECTED = new DJISDKCacheError("The abstraction is no longer connected to real hardware.");
    public static final DJISDKCacheError INVALID_KEY_FORMAT = new DJISDKCacheError("The key does not match theformat: component/index/key with index being a number or *.");
    public static final DJISDKCacheError INVALID_KEY_FOR_COMPONENT = new DJISDKCacheError("The key does not exist in component abstraction.");
    public static final DJISDKCacheError INVALID_VALUE = new DJISDKCacheError("Received invalid value");
    public static final DJISDKCacheError INVALID_VALUE_TYPE = new DJISDKCacheError("The value type is not correct.");
    public static final DJISDKCacheError KEY_UNSUPPORTED = new DJISDKCacheError("The key is unsupported.");
    public static final DJISDKCacheError NO_ACTION_FOR_KEY = new DJISDKCacheError("The key is not actionable.");
    public static final DJISDKCacheError NO_GET_FOR_KEY = new DJISDKCacheError("The key is not gettable.");
    public static final DJISDKCacheError NO_SET_FOR_KEY = new DJISDKCacheError("The key is not settable.");
    public static final DJISDKCacheError NO_STORAGE_ACCESS = new DJISDKCacheError("Can not access to component storage.");
    public static final DJISDKCacheError UNKNOWN_ACCESS_TYPE = new DJISDKCacheError("The access type requested for thekey unknown. This is an implementation error in the cache as only valid types should be exposedto the public interfaces.");

    private DJISDKCacheError() {
    }

    private DJISDKCacheError(String str) {
        this.mDescription = str;
    }
}
