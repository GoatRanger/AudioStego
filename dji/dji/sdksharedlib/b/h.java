package dji.sdksharedlib.b;

import dji.common.product.Model;
import dji.sdksharedlib.b.b.c;
import dji.sdksharedlib.b.b.d;
import dji.sdksharedlib.b.b.f;
import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;

public class h extends d {
    public static final String a = "Product";
    @d(a = String.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String b = "FirmwarePackageVersion";
    @d(a = Model.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String c = "ModelName";
    @d(a = Boolean.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String d = "IsOSMO";
    @f
    @d(a = Boolean.class, c = 4)
    @c
    public static final String e = "HasRemoteController";
    @f
    @d(a = Boolean.class, c = 4)
    @c
    public static final String f = "HasFlightControllerBeenActivated";
    @f
    @d(a = Boolean.class, c = 4)
    @c
    public static final String g = "HasCameraBeenActivated";
    @f
    @d(a = Boolean.class, c = 4)
    @c
    public static final String h = "HasOFDMModuleBeenActivated";

    public h(String str) {
        super(str);
    }
}
