package dji.midware.natives;

import android.util.Log;

public class SDKRelativeJNI {
    public static native String native_getBatteryBanSnListUrl();

    public static native String native_getBatteryValidatingSPKey();

    public static native String native_getGeoAESKeys();

    public static native String native_getGeoAirmapApiKey();

    public static native String native_getGeoAirmapDataUrl();

    public static native String native_getGeoAirmapFileName();

    public static native String native_getGeoAirmapUuid();

    public static native String native_getGeoAndroidKey();

    public static native String native_getGeoDjiFileName();

    public static native String native_getGeoDjiFileUuid();

    public static native String native_getGeoFlyForbidUrl();

    public static native String native_getGeoMobileUnlockAreasUrl();

    public static native String native_getGeoNoFlyZonesUrl();

    public static native String native_getGeoSignatureKey();

    public static native String native_getLicenseUnlockList();

    public static native String native_getRemoteServerDevUrl();

    public static native String native_getRemoteServerDevUserName();

    public static native String native_getRemoteServerProdUrl();

    public static native String native_getRemoteServerProdUserName();

    public static native String native_getRemoteServerStageUrl();

    public static native String native_getRemoteServerStageUserName();

    public static native String native_getRequestKey();

    public static native String native_getSDKConfigFileName();

    public static native String native_getServerUrl();

    public static native String native_getStatTestUrl();

    public static native String native_getSyncFileFromServerApi();

    public static native String native_getUnlimitListUrl();

    public static native String native_getUpgradeUrls0();

    public static native String native_getUpgradeUrls1();

    public static native String native_getUpgradeUrls2();

    public static native String native_getUrlForBr();

    public static native String native_getUrlForBrTest();

    public static native String native_getUrlForDate();

    public static native String native_getUsbAccessoryAttachedString();

    static {
        try {
            System.loadLibrary("SDKRelativeJNI");
            Log.d("SDKRelativeJNI", "load lib suc");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            Log.d("SDKRelativeJNI", "Couldn't load lib");
        }
    }
}
