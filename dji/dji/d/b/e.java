package dji.d.b;

import dji.d.b.d.a;
import dji.sdksharedlib.b.d;
import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;

public class e extends d {
    public static final String a = "FakeSubComponent";
    @dji.sdksharedlib.b.b.d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String b = "FakeSubValue";
    @dji.sdksharedlib.b.b.d(b = {Integer.class, a.class, Boolean.class}, c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String c = "FakeAction";
    @dji.sdksharedlib.b.b.d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String d = "FakeActionNoParam";
    @dji.sdksharedlib.b.b.a(a = {@dji.sdksharedlib.b.b.d(c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {d.class}), @dji.sdksharedlib.b.b.d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {c.class})})
    public static final String e = "FakePush";

    public e(String str) {
        super(str);
    }
}
