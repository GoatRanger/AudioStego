package dji.thirdparty.gson.stream;

import com.alipay.sdk.j.i;
import com.google.android.gms.location.places.Place;
import dji.pilot.phonecamera.h;
import dji.pilot.usercenter.protocol.d;
import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JsonWriter implements Closeable {
    private static /* synthetic */ int[] $SWITCH_TABLE$dji$thirdparty$gson$stream$JsonScope;
    private String deferredName;
    private boolean htmlSafe;
    private String indent;
    private boolean lenient;
    private final Writer out;
    private String separator;
    private boolean serializeNulls;
    private final List<JsonScope> stack = new ArrayList();

    static /* synthetic */ int[] $SWITCH_TABLE$dji$thirdparty$gson$stream$JsonScope() {
        int[] iArr = $SWITCH_TABLE$dji$thirdparty$gson$stream$JsonScope;
        if (iArr == null) {
            iArr = new int[JsonScope.values().length];
            try {
                iArr[JsonScope.CLOSED.ordinal()] = 8;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[JsonScope.DANGLING_NAME.ordinal()] = 4;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[JsonScope.EMPTY_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[JsonScope.EMPTY_DOCUMENT.ordinal()] = 6;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[JsonScope.EMPTY_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[JsonScope.NONEMPTY_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[JsonScope.NONEMPTY_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[JsonScope.NONEMPTY_OBJECT.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            $SWITCH_TABLE$dji$thirdparty$gson$stream$JsonScope = iArr;
        }
        return iArr;
    }

    public JsonWriter(Writer writer) {
        this.stack.add(JsonScope.EMPTY_DOCUMENT);
        this.separator = ":";
        this.serializeNulls = true;
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.out = writer;
    }

    public final void setIndent(String str) {
        if (str.length() == 0) {
            this.indent = null;
            this.separator = ":";
            return;
        }
        this.indent = str;
        this.separator = ": ";
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    public boolean isLenient() {
        return this.lenient;
    }

    public final void setHtmlSafe(boolean z) {
        this.htmlSafe = z;
    }

    public final boolean isHtmlSafe() {
        return this.htmlSafe;
    }

    public final void setSerializeNulls(boolean z) {
        this.serializeNulls = z;
    }

    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    public JsonWriter beginArray() throws IOException {
        writeDeferredName();
        return open(JsonScope.EMPTY_ARRAY, d.G);
    }

    public JsonWriter endArray() throws IOException {
        return close(JsonScope.EMPTY_ARRAY, JsonScope.NONEMPTY_ARRAY, d.H);
    }

    public JsonWriter beginObject() throws IOException {
        writeDeferredName();
        return open(JsonScope.EMPTY_OBJECT, "{");
    }

    public JsonWriter endObject() throws IOException {
        return close(JsonScope.EMPTY_OBJECT, JsonScope.NONEMPTY_OBJECT, i.d);
    }

    private JsonWriter open(JsonScope jsonScope, String str) throws IOException {
        beforeValue(true);
        this.stack.add(jsonScope);
        this.out.write(str);
        return this;
    }

    private JsonWriter close(JsonScope jsonScope, JsonScope jsonScope2, String str) throws IOException {
        JsonScope peek = peek();
        if (peek != jsonScope2 && peek != jsonScope) {
            throw new IllegalStateException("Nesting problem: " + this.stack);
        } else if (this.deferredName != null) {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        } else {
            this.stack.remove(this.stack.size() - 1);
            if (peek == jsonScope2) {
                newline();
            }
            this.out.write(str);
            return this;
        }
    }

    private JsonScope peek() {
        return (JsonScope) this.stack.get(this.stack.size() - 1);
    }

    private void replaceTop(JsonScope jsonScope) {
        this.stack.set(this.stack.size() - 1, jsonScope);
    }

    public JsonWriter name(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.deferredName != null) {
            throw new IllegalStateException();
        } else {
            this.deferredName = str;
            return this;
        }
    }

    private void writeDeferredName() throws IOException {
        if (this.deferredName != null) {
            beforeName();
            string(this.deferredName);
            this.deferredName = null;
        }
    }

    public JsonWriter value(String str) throws IOException {
        if (str == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue(false);
        string(str);
        return this;
    }

    public JsonWriter nullValue() throws IOException {
        if (this.deferredName != null) {
            if (this.serializeNulls) {
                writeDeferredName();
            } else {
                this.deferredName = null;
                return this;
            }
        }
        beforeValue(false);
        this.out.write("null");
        return this;
    }

    public JsonWriter value(boolean z) throws IOException {
        writeDeferredName();
        beforeValue(false);
        this.out.write(z ? "true" : h.e);
        return this;
    }

    public JsonWriter value(double d) throws IOException {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
        }
        writeDeferredName();
        beforeValue(false);
        this.out.append(Double.toString(d));
        return this;
    }

    public JsonWriter value(long j) throws IOException {
        writeDeferredName();
        beforeValue(false);
        this.out.write(Long.toString(j));
        return this;
    }

    public JsonWriter value(Number number) throws IOException {
        if (number == null) {
            return nullValue();
        }
        writeDeferredName();
        CharSequence obj = number.toString();
        if (this.lenient || !(obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            beforeValue(false);
            this.out.append(obj);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public void close() throws IOException {
        this.out.close();
        if (peek() != JsonScope.NONEMPTY_DOCUMENT) {
            throw new IOException("Incomplete document");
        }
    }

    private void string(String str) throws IOException {
        this.out.write("\"");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '\b':
                    this.out.write("\\b");
                    break;
                case '\t':
                    this.out.write("\\t");
                    break;
                case '\n':
                    this.out.write("\\n");
                    break;
                case '\f':
                    this.out.write("\\f");
                    break;
                case '\r':
                    this.out.write("\\r");
                    break;
                case '\"':
                case Place.TYPE_TRAIN_STATION /*92*/:
                    this.out.write(92);
                    this.out.write(charAt);
                    break;
                case '&':
                case '\'':
                case '<':
                case '=':
                case '>':
                    if (!this.htmlSafe) {
                        this.out.write(charAt);
                        break;
                    }
                    this.out.write(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
                    break;
                case ' ':
                case ' ':
                    this.out.write(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
                    break;
                default:
                    if (charAt > '\u001f') {
                        this.out.write(charAt);
                        break;
                    }
                    this.out.write(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
                    break;
            }
        }
        this.out.write("\"");
    }

    private void newline() throws IOException {
        if (this.indent != null) {
            this.out.write("\n");
            for (int i = 1; i < this.stack.size(); i++) {
                this.out.write(this.indent);
            }
        }
    }

    private void beforeName() throws IOException {
        JsonScope peek = peek();
        if (peek == JsonScope.NONEMPTY_OBJECT) {
            this.out.write(44);
        } else if (peek != JsonScope.EMPTY_OBJECT) {
            throw new IllegalStateException("Nesting problem: " + this.stack);
        }
        newline();
        replaceTop(JsonScope.DANGLING_NAME);
    }

    private void beforeValue(boolean z) throws IOException {
        switch ($SWITCH_TABLE$dji$thirdparty$gson$stream$JsonScope()[peek().ordinal()]) {
            case 1:
                replaceTop(JsonScope.NONEMPTY_ARRAY);
                newline();
                return;
            case 2:
                this.out.append(',');
                newline();
                return;
            case 4:
                this.out.append(this.separator);
                replaceTop(JsonScope.NONEMPTY_OBJECT);
                return;
            case 6:
                if (this.lenient || z) {
                    replaceTop(JsonScope.NONEMPTY_DOCUMENT);
                    return;
                }
                throw new IllegalStateException("JSON must start with an array or an object.");
            case 7:
                throw new IllegalStateException("JSON must have only one top-level value.");
            default:
                throw new IllegalStateException("Nesting problem: " + this.stack);
        }
    }
}
