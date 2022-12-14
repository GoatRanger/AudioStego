package dji.thirdparty.gson.internal;

import dji.thirdparty.gson.JsonElement;
import dji.thirdparty.gson.JsonIOException;
import dji.thirdparty.gson.JsonNull;
import dji.thirdparty.gson.JsonParseException;
import dji.thirdparty.gson.JsonSyntaxException;
import dji.thirdparty.gson.internal.bind.TypeAdapters;
import dji.thirdparty.gson.stream.JsonReader;
import dji.thirdparty.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Writer;

public final class Streams {

    private static class AppendableWriter extends Writer {
        private final Appendable appendable;
        private final CurrentWrite currentWrite;

        static class CurrentWrite implements CharSequence {
            char[] chars;

            CurrentWrite() {
            }

            public int length() {
                return this.chars.length;
            }

            public char charAt(int i) {
                return this.chars[i];
            }

            public CharSequence subSequence(int i, int i2) {
                return new String(this.chars, i, i2 - i);
            }
        }

        private AppendableWriter(Appendable appendable) {
            this.currentWrite = new CurrentWrite();
            this.appendable = appendable;
        }

        public void write(char[] cArr, int i, int i2) throws IOException {
            this.currentWrite.chars = cArr;
            this.appendable.append(this.currentWrite, i, i + i2);
        }

        public void write(int i) throws IOException {
            this.appendable.append((char) i);
        }

        public void flush() {
        }

        public void close() {
        }
    }

    public static JsonElement parse(JsonReader jsonReader) throws JsonParseException {
        Object obj = 1;
        try {
            jsonReader.peek();
            obj = null;
            return (JsonElement) TypeAdapters.JSON_ELEMENT.read(jsonReader);
        } catch (Throwable e) {
            if (obj != null) {
                return JsonNull.INSTANCE;
            }
            throw new JsonIOException(e);
        } catch (Throwable e2) {
            throw new JsonSyntaxException(e2);
        } catch (Throwable e22) {
            throw new JsonIOException(e22);
        } catch (Throwable e222) {
            throw new JsonSyntaxException(e222);
        }
    }

    public static void write(JsonElement jsonElement, JsonWriter jsonWriter) throws IOException {
        TypeAdapters.JSON_ELEMENT.write(jsonWriter, jsonElement);
    }

    public static Writer writerForAppendable(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new AppendableWriter(appendable);
    }
}
