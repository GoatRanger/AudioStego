package dji.thirdparty.gson.internal.bind;

import dji.thirdparty.gson.Gson;
import dji.thirdparty.gson.JsonSyntaxException;
import dji.thirdparty.gson.TypeAdapter;
import dji.thirdparty.gson.TypeAdapterFactory;
import dji.thirdparty.gson.reflect.TypeToken;
import dji.thirdparty.gson.stream.JsonReader;
import dji.thirdparty.gson.stream.JsonToken;
import dji.thirdparty.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class TimeTypeAdapter extends TypeAdapter<Time> {
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return typeToken.getRawType() == Time.class ? new TimeTypeAdapter() : null;
        }
    };
    private final DateFormat format = new SimpleDateFormat("hh:mm:ss a");

    public synchronized Time read(JsonReader jsonReader) throws IOException {
        Time time;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            time = null;
        } else {
            try {
                time = new Time(this.format.parse(jsonReader.nextString()).getTime());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }
        return time;
    }

    public synchronized void write(JsonWriter jsonWriter, Time time) throws IOException {
        jsonWriter.value(time == null ? null : this.format.format(time));
    }
}
