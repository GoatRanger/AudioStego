package dji.thirdparty.gson.internal.bind;

import dji.thirdparty.gson.JsonSyntaxException;
import dji.thirdparty.gson.TypeAdapter;
import dji.thirdparty.gson.stream.JsonReader;
import dji.thirdparty.gson.stream.JsonToken;
import dji.thirdparty.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigDecimal;

public final class BigDecimalTypeAdapter extends TypeAdapter<BigDecimal> {
    public BigDecimal read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        try {
            return new BigDecimal(jsonReader.nextString());
        } catch (Throwable e) {
            throw new JsonSyntaxException(e);
        }
    }

    public void write(JsonWriter jsonWriter, BigDecimal bigDecimal) throws IOException {
        jsonWriter.value((Number) bigDecimal);
    }
}
