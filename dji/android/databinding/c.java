package android.databinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
public @interface c {
    String[] a();

    boolean b() default true;
}
