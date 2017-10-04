package dji.sdksharedlib.b.b;

import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface d {
    Class a() default Object.class;

    Class[] b() default {};

    int c();

    DJISDKCacheUpdateType d() default DJISDKCacheUpdateType.DYNAMIC;

    Class[] e() default {};

    Class[] f() default {};
}
