package it.sauronsoftware.ftp4j;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.StringTokenizer;

class NVTASCIIWriter extends Writer {
    private static final String LINE_SEPARATOR = "\r\n";
    private OutputStream stream;
    private Writer writer;

    public NVTASCIIWriter(OutputStream outputStream, String str) throws IOException {
        this.stream = outputStream;
        this.writer = new OutputStreamWriter(outputStream, str);
    }

    public void close() throws IOException {
        synchronized (this) {
            this.writer.close();
        }
    }

    public void flush() throws IOException {
        synchronized (this) {
            this.writer.flush();
        }
    }

    public void write(char[] cArr, int i, int i2) throws IOException {
        synchronized (this) {
            this.writer.write(cArr, i, i2);
        }
    }

    public void changeCharset(String str) throws IOException {
        synchronized (this) {
            this.writer = new OutputStreamWriter(this.stream, str);
        }
    }

    public void writeLine(String str) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        StringTokenizer stringTokenizer = new StringTokenizer(str, "\r\n");
        int countTokens = stringTokenizer.countTokens();
        char c = '\u0000';
        for (int i = 0; i < countTokens; i++) {
            String nextToken = stringTokenizer.nextToken();
            if (nextToken.length() > 0) {
                if (c != '\u0000') {
                    stringBuffer.append('\r');
                    stringBuffer.append('\u0000');
                }
                stringBuffer.append(nextToken);
                c = '\u0001';
            }
        }
        if (stringBuffer.length() > 0) {
            this.writer.write(stringBuffer.toString());
            this.writer.write("\r\n");
            this.writer.flush();
        }
    }
}
