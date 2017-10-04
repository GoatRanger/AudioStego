package dji.thirdparty.e.a.a;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import dji.thirdparty.b.ae;
import dji.thirdparty.e.e;
import java.io.IOException;

final class c<T> implements e<ae, T> {
    private final Gson a;
    private final TypeAdapter<T> b;

    c(Gson gson, TypeAdapter<T> typeAdapter) {
        this.a = gson;
        this.b = typeAdapter;
    }

    public T a(ae aeVar) throws IOException {
        try {
            T read = this.b.read(this.a.newJsonReader(aeVar.f()));
            return read;
        } finally {
            aeVar.close();
        }
    }
}
