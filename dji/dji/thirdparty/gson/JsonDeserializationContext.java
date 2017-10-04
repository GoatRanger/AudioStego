package dji.thirdparty.gson;

import java.lang.reflect.Type;

public interface JsonDeserializationContext {
    <T> T deserialize(JsonElement jsonElement, Type type) throws JsonParseException;
}
