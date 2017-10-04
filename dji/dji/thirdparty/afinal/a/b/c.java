package dji.thirdparty.afinal.a.b;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface c {
    int a() default 0;

    String b() default "";

    String c() default "";

    String d() default "";

    String e() default "";

    String f() default "";

    b g() default @b(a = "");
}
