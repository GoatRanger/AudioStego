package android.databinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE})
public @interface o {
    Class a();

    String b();

    String c() default "";

    String d() default "";
}
