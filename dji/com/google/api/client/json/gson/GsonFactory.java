package com.google.api.client.json.gson;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Charsets;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.Charset;

public class GsonFactory extends JsonFactory {

    @Beta
    static class InstanceHolder {
        static final GsonFactory INSTANCE = new GsonFactory();

        InstanceHolder() {
        }
    }

    @Beta
    public static GsonFactory getDefaultInstance() {
        return InstanceHolder.INSTANCE;
    }

    public JsonParser createJsonParser(InputStream inputStream) {
        return createJsonParser(new InputStreamReader(inputStream, Charsets.UTF_8));
    }

    public JsonParser createJsonParser(InputStream inputStream, Charset charset) {
        if (charset == null) {
            return createJsonParser(inputStream);
        }
        return createJsonParser(new InputStreamReader(inputStream, charset));
    }

    public JsonParser createJsonParser(String str) {
        return createJsonParser(new StringReader(str));
    }

    public JsonParser createJsonParser(Reader reader) {
        return new GsonParser(this, new JsonReader(reader));
    }

    public JsonGenerator createJsonGenerator(OutputStream outputStream, Charset charset) {
        return createJsonGenerator(new OutputStreamWriter(outputStream, charset));
    }

    public JsonGenerator createJsonGenerator(Writer writer) {
        return new GsonGenerator(this, new JsonWriter(writer));
    }
}
