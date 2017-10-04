package dji.sdksharedlib.b;

import dji.common.handheld.DJIHandheldButtonStatus;
import dji.common.handheld.DJIHandheldPowerMode;
import dji.common.handheld.DJIHandheldTriggerStatus;
import dji.common.handheld.JoystickHorizontalDirection;
import dji.common.handheld.JoystickVerticalDirection;
import dji.sdksharedlib.b.b.a;
import dji.sdksharedlib.b.b.d;
import dji.sdksharedlib.b.b.f;
import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;
import dji.sdksharedlib.hardware.abstractions.f.c;

public class g extends d {
    public static final String a = "HandheldController";
    @d(a = String.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String b = "IsTriggerBeingPressed";
    @d(a = String.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String c = "SerialNumber";
    @a(a = {@d(a = DJIHandheldPowerMode.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC), @d(a = DJIHandheldPowerMode.class, c = 2, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.f.a.class, c.class})})
    public static final String d = "HandheldPowerMode";
    @f
    @d(a = DJIHandheldButtonStatus.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {c.class})
    public static final String e = "DJIHandheldButtonStatus";
    @f
    @d(a = DJIHandheldTriggerStatus.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {c.class})
    public static final String f = "DJIHandheldTriggerStatus";
    @f
    @d(a = JoystickVerticalDirection.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {c.class})
    public static final String g = "JoystickVerticalMovement";
    @f
    @d(a = JoystickHorizontalDirection.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {c.class})
    public static final String h = "joystickHorizontalMovement";
    @f
    @d(a = String.class, c = 3, d = DJISDKCacheUpdateType.DYNAMIC, e = {c.class})
    public static final String i = "HandheldName";
    public static final String j = "FakeAction";

    public g(String str) {
        super(str);
    }

    protected String a() {
        return "handHeld";
    }
}
