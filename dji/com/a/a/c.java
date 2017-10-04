package com.a.a;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class c {
    static final c a = new c();

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[JsonToken.values().length];

        static {
            try {
                a[JsonToken.BEGIN_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[JsonToken.BEGIN_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[JsonToken.BOOLEAN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[JsonToken.NUMBER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[JsonToken.STRING.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    private c() {
    }

    Map<String, Object> a(String str) throws IOException {
        return a(new StringReader(str));
    }

    Map<String, Object> a(Reader reader) throws IOException {
        try {
            Map<String, Object> a = a(new JsonReader(reader));
            return a;
        } finally {
            reader.close();
        }
    }

    String a(Map<?, ?> map) throws IOException {
        Writer stringWriter = new StringWriter();
        a((Map) map, stringWriter);
        return stringWriter.toString();
    }

    void a(Map<?, ?> map, Writer writer) throws IOException {
        if (map == null) {
            throw new IllegalArgumentException("map == null");
        }
        JsonWriter jsonWriter = new JsonWriter(writer);
        try {
            a((Map) map, jsonWriter);
        } finally {
            jsonWriter.close();
        }
    }

    private Map<String, Object> a(JsonReader jsonReader) throws IOException {
        Map<String, Object> linkedHashMap = new LinkedHashMap();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            linkedHashMap.put(jsonReader.nextName(), c(jsonReader));
        }
        jsonReader.endObject();
        return linkedHashMap;
    }

    private List<Object> b(JsonReader jsonReader) throws IOException {
        List<Object> arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(c(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    private Object c(JsonReader jsonReader) throws IOException {
        JsonToken peek = jsonReader.peek();
        switch (AnonymousClass1.a[peek.ordinal()]) {
            case 1:
                return a(jsonReader);
            case 2:
                return b(jsonReader);
            case 3:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case 4:
                jsonReader.nextNull();
                return null;
            case 5:
                return Double.valueOf(jsonReader.nextDouble());
            case 6:
                return jsonReader.nextString();
            default:
                throw new IllegalStateException("Invalid token " + peek);
        }
    }

    private void a(Map<?, ?> map, JsonWriter jsonWriter) throws IOException {
        jsonWriter.beginObject();
        for (Entry entry : map.entrySet()) {
            jsonWriter.name(String.valueOf(entry.getKey()));
            a(entry.getValue(), jsonWriter);
        }
        jsonWriter.endObject();
    }

    private void a(List<?> list, JsonWriter jsonWriter) throws IOException {
        jsonWriter.beginArray();
        for (Object a : list) {
            a(a, jsonWriter);
        }
        jsonWriter.endArray();
    }

    private void a(Object obj, JsonWriter jsonWriter) throws IOException {
        if (obj == null) {
            jsonWriter.nullValue();
        } else if (obj instanceof Number) {
            jsonWriter.value((Number) obj);
        } else if (obj instanceof Boolean) {
            jsonWriter.value(((Boolean) obj).booleanValue());
        } else if (obj instanceof List) {
            a((List) obj, jsonWriter);
        } else if (obj instanceof Map) {
            a((Map) obj, jsonWriter);
        } else {
            jsonWriter.value(String.valueOf(obj));
        }
    }
}
