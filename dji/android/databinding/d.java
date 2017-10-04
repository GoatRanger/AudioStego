package android.databinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface d {
    String a();

    String b();

    String c();

    int d();

    String e();

    String f();

    boolean g();

    boolean h() default false;

    boolean i() default false;
}
