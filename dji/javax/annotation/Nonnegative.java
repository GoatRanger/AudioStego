package javax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifier;
import javax.annotation.meta.TypeQualifierValidator;
import javax.annotation.meta.When;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@TypeQualifier(applicableTo = Number.class)
public @interface Nonnegative {

    public static class Checker implements TypeQualifierValidator<Nonnegative> {
        public When forConstantValue(Nonnegative nonnegative, Object obj) {
            Object obj2 = 1;
            if (!(obj instanceof Number)) {
                return When.NEVER;
            }
            Number number = (Number) obj;
            if (number instanceof Long) {
                if (number.longValue() >= 0) {
                    obj2 = null;
                }
            } else if (number instanceof Double) {
                if (number.doubleValue() >= 0.0d) {
                    obj2 = null;
                }
            } else if (number instanceof Float) {
                if (number.floatValue() >= 0.0f) {
                    obj2 = null;
                }
            } else if (number.intValue() >= 0) {
                obj2 = null;
            }
            if (obj2 != null) {
                return When.NEVER;
            }
            return When.ALWAYS;
        }
    }

    When when() default When.ALWAYS;
}
