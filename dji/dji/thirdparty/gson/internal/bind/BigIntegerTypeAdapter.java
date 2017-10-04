package dji.thirdparty.gson.internal.bind;

import dji.thirdparty.gson.JsonSyntaxException;
import dji.thirdparty.gson.TypeAdapter;
import dji.thirdparty.gson.stream.JsonReader;
import dji.thirdparty.gson.stream.JsonToken;
import dji.thirdparty.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigInteger;

public final class BigIntegerTypeAdapter extends TypeAdapter<BigInteger> {
    public BigInteger read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        try {
            return new BigInteger(jsonReader.nextString());
        } catch (Throwable e) {
            throw new JsonSyntaxException(e);
        }
    }

    public void write(JsonWriter jsonWriter, BigInteger bigInteger) throws IOException {
        jsonWriter.value((Number) bigInteger);
    }
}
