package dji.thirdparty.e.a.a;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dji.thirdparty.b.ac;
import dji.thirdparty.b.ae;
import dji.thirdparty.e.e;
import dji.thirdparty.e.m;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public final class a extends dji.thirdparty.e.e.a {
    private final Gson a;

    public static a a() {
        return a(new Gson());
    }

    public static a a(Gson gson) {
        return new a(gson);
    }

    private a(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }
        this.a = gson;
    }

    public e<ae, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        return new c(this.a, this.a.getAdapter(TypeToken.get(type)));
    }

    public e<?, ac> a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, m mVar) {
        return new b(this.a, this.a.getAdapter(TypeToken.get(type)));
    }
}
