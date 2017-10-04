package it.sauronsoftware.ftp4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

class NVTASCIIReader extends Reader {
    private static final String SYSTEM_LINE_SEPARATOR = System.getProperty("line.separator");
    private Reader reader;
    private InputStream stream;

    public NVTASCIIReader(InputStream inputStream, String str) throws IOException {
        this.stream = inputStream;
        this.reader = new InputStreamReader(inputStream, str);
    }

    public void close() throws IOException {
        synchronized (this) {
            this.reader.close();
        }
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        int read;
        synchronized (this) {
            read = this.reader.read(cArr, i, i2);
        }
        return read;
    }

    public void changeCharset(String str) throws IOException {
        synchronized (this) {
            this.reader = new InputStreamReader(this.stream, str);
        }
    }

    public String readLine() throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        int i = -1;
        while (true) {
            int read = this.reader.read();
            if (read == -1) {
                break;
            } else if (read == 10) {
                return stringBuffer.toString();
            } else {
                if (i == 13 && read == 0) {
                    stringBuffer.append(SYSTEM_LINE_SEPARATOR);
                } else if (!(read == 0 || read == 13)) {
                    stringBuffer.append((char) read);
                }
                i = read;
            }
        }
        if (stringBuffer.length() == 0) {
            return null;
        }
        return stringBuffer.toString();
    }
}
