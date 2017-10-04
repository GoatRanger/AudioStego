package dji.thirdparty.e.a.a;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import dji.thirdparty.b.ac;
import dji.thirdparty.b.w;
import dji.thirdparty.c.c;
import dji.thirdparty.e.e;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

final class b<T> implements e<T, ac> {
    private static final w a = w.a("application/json; charset=UTF-8");
    private static final Charset b = Charset.forName("UTF-8");
    private final Gson c;
    private final TypeAdapter<T> d;

    public /* synthetic */ Object a(Object obj) throws IOException {
        return b(obj);
    }

    b(Gson gson, TypeAdapter<T> typeAdapter) {
        this.c = gson;
        this.d = typeAdapter;
    }

    public ac b(T t) throws IOException {
        c cVar = new c();
        JsonWriter newJsonWriter = this.c.newJsonWriter(new OutputStreamWriter(cVar.d(), b));
        this.d.write(newJsonWriter, t);
        newJsonWriter.close();
        return ac.a(a, cVar.s());
    }
}
