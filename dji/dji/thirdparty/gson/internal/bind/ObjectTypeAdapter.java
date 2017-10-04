package dji.thirdparty.gson.internal.bind;

import dji.thirdparty.gson.Gson;
import dji.thirdparty.gson.TypeAdapter;
import dji.thirdparty.gson.TypeAdapterFactory;
import dji.thirdparty.gson.reflect.TypeToken;
import dji.thirdparty.gson.stream.JsonReader;
import dji.thirdparty.gson.stream.JsonToken;
import dji.thirdparty.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public final class ObjectTypeAdapter extends TypeAdapter<Object> {
    private static /* synthetic */ int[] $SWITCH_TABLE$dji$thirdparty$gson$stream$JsonToken;
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() == Object.class) {
                return new ObjectTypeAdapter(gson);
            }
            return null;
        }
    };
    private final Gson gson;

    static /* synthetic */ int[] $SWITCH_TABLE$dji$thirdparty$gson$stream$JsonToken() {
        int[] iArr = $SWITCH_TABLE$dji$thirdparty$gson$stream$JsonToken;
        if (iArr == null) {
            iArr = new int[JsonToken.values().length];
            try {
                iArr[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[JsonToken.BEGIN_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[JsonToken.BOOLEAN.ordinal()] = 8;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[JsonToken.END_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[JsonToken.END_DOCUMENT.ordinal()] = 10;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[JsonToken.END_OBJECT.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[JsonToken.NAME.ordinal()] = 5;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[JsonToken.NULL.ordinal()] = 9;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[JsonToken.NUMBER.ordinal()] = 7;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[JsonToken.STRING.ordinal()] = 6;
            } catch (NoSuchFieldError e10) {
            }
            $SWITCH_TABLE$dji$thirdparty$gson$stream$JsonToken = iArr;
        }
        return iArr;
    }

    private ObjectTypeAdapter(Gson gson) {
        this.gson = gson;
    }

    public Object read(JsonReader jsonReader) throws IOException {
        switch ($SWITCH_TABLE$dji$thirdparty$gson$stream$JsonToken()[jsonReader.peek().ordinal()]) {
            case 1:
                List arrayList = new ArrayList();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    arrayList.add(read(jsonReader));
                }
                jsonReader.endArray();
                return arrayList;
            case 3:
                Object linkedHashMap = new LinkedHashMap();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    linkedHashMap.put(jsonReader.nextName(), read(jsonReader));
                }
                jsonReader.endObject();
                return linkedHashMap;
            case 6:
                return jsonReader.nextString();
            case 7:
                return Double.valueOf(jsonReader.nextDouble());
            case 8:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case 9:
                jsonReader.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    public void write(JsonWriter jsonWriter, Object obj) throws IOException {
        if (obj == null) {
            jsonWriter.nullValue();
            return;
        }
        TypeAdapter adapter = this.gson.getAdapter(obj.getClass());
        if (adapter instanceof ObjectTypeAdapter) {
            jsonWriter.beginObject();
            jsonWriter.endObject();
            return;
        }
        adapter.write(jsonWriter, obj);
    }
}
