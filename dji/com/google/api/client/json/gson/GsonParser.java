package com.google.api.client.json.gson;

import com.alipay.sdk.j.i;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonToken;
import com.google.api.client.util.Preconditions;
import com.google.gson.stream.JsonReader;
import dji.pilot.phonecamera.h;
import dji.pilot.usercenter.protocol.d;
import java.io.EOFException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class GsonParser extends JsonParser {
    private List<String> currentNameStack = new ArrayList();
    private String currentText;
    private JsonToken currentToken;
    private final GsonFactory factory;
    private final JsonReader reader;

    GsonParser(GsonFactory gsonFactory, JsonReader jsonReader) {
        this.factory = gsonFactory;
        this.reader = jsonReader;
        jsonReader.setLenient(true);
    }

    public void close() throws IOException {
        this.reader.close();
    }

    public String getCurrentName() {
        return this.currentNameStack.isEmpty() ? null : (String) this.currentNameStack.get(this.currentNameStack.size() - 1);
    }

    public JsonToken getCurrentToken() {
        return this.currentToken;
    }

    public JsonFactory getFactory() {
        return this.factory;
    }

    public byte getByteValue() {
        checkNumber();
        return Byte.valueOf(this.currentText).byteValue();
    }

    public short getShortValue() {
        checkNumber();
        return Short.valueOf(this.currentText).shortValue();
    }

    public int getIntValue() {
        checkNumber();
        return Integer.valueOf(this.currentText).intValue();
    }

    public float getFloatValue() {
        checkNumber();
        return Float.valueOf(this.currentText).floatValue();
    }

    public BigInteger getBigIntegerValue() {
        checkNumber();
        return new BigInteger(this.currentText);
    }

    public BigDecimal getDecimalValue() {
        checkNumber();
        return new BigDecimal(this.currentText);
    }

    public double getDoubleValue() {
        checkNumber();
        return Double.valueOf(this.currentText).doubleValue();
    }

    public long getLongValue() {
        checkNumber();
        return Long.valueOf(this.currentText).longValue();
    }

    private void checkNumber() {
        boolean z = this.currentToken == JsonToken.VALUE_NUMBER_INT || this.currentToken == JsonToken.VALUE_NUMBER_FLOAT;
        Preconditions.checkArgument(z);
    }

    public String getText() {
        return this.currentText;
    }

    public JsonToken nextToken() throws IOException {
        com.google.gson.stream.JsonToken peek;
        if (this.currentToken != null) {
            switch (this.currentToken) {
                case START_ARRAY:
                    this.reader.beginArray();
                    this.currentNameStack.add(null);
                    break;
                case START_OBJECT:
                    this.reader.beginObject();
                    this.currentNameStack.add(null);
                    break;
            }
        }
        try {
            peek = this.reader.peek();
        } catch (EOFException e) {
            peek = com.google.gson.stream.JsonToken.END_DOCUMENT;
        }
        switch (peek) {
            case BEGIN_ARRAY:
                this.currentText = d.G;
                this.currentToken = JsonToken.START_ARRAY;
                break;
            case END_ARRAY:
                this.currentText = d.H;
                this.currentToken = JsonToken.END_ARRAY;
                this.currentNameStack.remove(this.currentNameStack.size() - 1);
                this.reader.endArray();
                break;
            case BEGIN_OBJECT:
                this.currentText = "{";
                this.currentToken = JsonToken.START_OBJECT;
                break;
            case END_OBJECT:
                this.currentText = i.d;
                this.currentToken = JsonToken.END_OBJECT;
                this.currentNameStack.remove(this.currentNameStack.size() - 1);
                this.reader.endObject();
                break;
            case BOOLEAN:
                if (!this.reader.nextBoolean()) {
                    this.currentText = h.e;
                    this.currentToken = JsonToken.VALUE_FALSE;
                    break;
                }
                this.currentText = "true";
                this.currentToken = JsonToken.VALUE_TRUE;
                break;
            case NULL:
                this.currentText = "null";
                this.currentToken = JsonToken.VALUE_NULL;
                this.reader.nextNull();
                break;
            case STRING:
                this.currentText = this.reader.nextString();
                this.currentToken = JsonToken.VALUE_STRING;
                break;
            case NUMBER:
                this.currentText = this.reader.nextString();
                this.currentToken = this.currentText.indexOf(46) == -1 ? JsonToken.VALUE_NUMBER_INT : JsonToken.VALUE_NUMBER_FLOAT;
                break;
            case NAME:
                this.currentText = this.reader.nextName();
                this.currentToken = JsonToken.FIELD_NAME;
                this.currentNameStack.set(this.currentNameStack.size() - 1, this.currentText);
                break;
            default:
                this.currentText = null;
                this.currentToken = null;
                break;
        }
        return this.currentToken;
    }

    public JsonParser skipChildren() throws IOException {
        if (this.currentToken != null) {
            switch (this.currentToken) {
                case START_ARRAY:
                    this.reader.skipValue();
                    this.currentText = d.H;
                    this.currentToken = JsonToken.END_ARRAY;
                    break;
                case START_OBJECT:
                    this.reader.skipValue();
                    this.currentText = i.d;
                    this.currentToken = JsonToken.END_OBJECT;
                    break;
            }
        }
        return this;
    }
}
