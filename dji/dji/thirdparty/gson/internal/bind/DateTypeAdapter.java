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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class DateTypeAdapter extends TypeAdapter<Date> {
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return typeToken.getRawType() == Date.class ? new DateTypeAdapter() : null;
        }
    };
    private final DateFormat enUsFormat = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat iso8601Format = buildIso8601Format();
    private final DateFormat localFormat = DateFormat.getDateTimeInstance(2, 2);

    private static DateFormat buildIso8601Format() {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    public Date read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() != JsonToken.NULL) {
            return deserializeToDate(jsonReader.nextString());
        }
        jsonReader.nextNull();
        return null;
    }

    private synchronized Date deserializeToDate(String str) {
        Date parse;
        try {
            parse = this.localFormat.parse(str);
        } catch (ParseException e) {
            try {
                parse = this.enUsFormat.parse(str);
            } catch (ParseException e2) {
                try {
                    parse = this.iso8601Format.parse(str);
                } catch (Throwable e3) {
                    throw new JsonSyntaxException(str, e3);
                }
            }
        }
        return parse;
    }

    public synchronized void write(JsonWriter jsonWriter, Date date) throws IOException {
        if (date == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(this.enUsFormat.format(date));
        }
    }
}
