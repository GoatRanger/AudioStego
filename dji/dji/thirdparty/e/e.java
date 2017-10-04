package dji.thirdparty.e;

import dji.thirdparty.b.ac;
import dji.thirdparty.b.ae;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public interface e<F, T> {

    public static abstract class a {
        public e<ae, ?> a(Type type, Annotation[] annotationArr, m mVar) {
            return null;
        }

        public e<?, ac> a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, m mVar) {
            return null;
        }

        public e<?, String> b(Type type, Annotation[] annotationArr, m mVar) {
            return null;
        }
    }

    T a(F f) throws IOException;
}
