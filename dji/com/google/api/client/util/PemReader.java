package com.google.api.client.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Beta
public final class PemReader {
    private static final Pattern BEGIN_PATTERN = Pattern.compile("-----BEGIN ([A-Z ]+)-----");
    private static final Pattern END_PATTERN = Pattern.compile("-----END ([A-Z ]+)-----");
    private BufferedReader reader;

    public static final class Section {
        private final byte[] base64decodedBytes;
        private final String title;

        Section(String str, byte[] bArr) {
            this.title = (String) Preconditions.checkNotNull(str);
            this.base64decodedBytes = (byte[]) Preconditions.checkNotNull(bArr);
        }

        public String getTitle() {
            return this.title;
        }

        public byte[] getBase64DecodedBytes() {
            return this.base64decodedBytes;
        }
    }

    public PemReader(Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    public Section readNextSection() throws IOException {
        return readNextSection(null);
    }

    public Section readNextSection(String str) throws IOException {
        boolean z;
        StringBuilder stringBuilder = null;
        String str2 = null;
        while (true) {
            Object readLine = this.reader.readLine();
            if (readLine == null) {
                break;
            } else if (stringBuilder == null) {
                Matcher matcher = BEGIN_PATTERN.matcher(readLine);
                if (matcher.matches()) {
                    String group = matcher.group(1);
                    if (str == null || group.equals(str)) {
                        stringBuilder = new StringBuilder();
                        str2 = group;
                    }
                }
            } else {
                Matcher matcher2 = END_PATTERN.matcher(readLine);
                if (matcher2.matches()) {
                    Object[] objArr = new Object[]{matcher2.group(1), str2};
                    Preconditions.checkArgument(matcher2.group(1).equals(str2), "end tag (%s) doesn't match begin tag (%s)", objArr);
                    return new Section(str2, Base64.decodeBase64(stringBuilder.toString()));
                }
                stringBuilder.append(readLine);
            }
        }
        if (str2 == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "missing end tag (%s)", str2);
        return null;
    }

    public static Section readFirstSectionAndClose(Reader reader) throws IOException {
        return readFirstSectionAndClose(reader, null);
    }

    public static Section readFirstSectionAndClose(Reader reader, String str) throws IOException {
        PemReader pemReader = new PemReader(reader);
        try {
            Section readNextSection = pemReader.readNextSection(str);
            return readNextSection;
        } finally {
            pemReader.close();
        }
    }

    public void close() throws IOException {
        this.reader.close();
    }
}
