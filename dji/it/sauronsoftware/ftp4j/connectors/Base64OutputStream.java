package it.sauronsoftware.ftp4j.connectors;

import com.here.odnp.debug.DebugFile;
import java.io.IOException;
import java.io.OutputStream;

class Base64OutputStream extends OutputStream {
    private int buffer;
    private int bytecounter;
    private int linecounter;
    private int linelength;
    private OutputStream outputStream;

    public Base64OutputStream(OutputStream outputStream) {
        this(outputStream, 76);
    }

    public Base64OutputStream(OutputStream outputStream, int i) {
        this.outputStream = null;
        this.buffer = 0;
        this.bytecounter = 0;
        this.linecounter = 0;
        this.linelength = 0;
        this.outputStream = outputStream;
        this.linelength = i;
    }

    public void write(int i) throws IOException {
        this.buffer = ((i & 255) << (16 - (this.bytecounter * 8))) | this.buffer;
        this.bytecounter++;
        if (this.bytecounter == 3) {
            commit();
        }
    }

    public void close() throws IOException {
        commit();
        this.outputStream.close();
    }

    protected void commit() throws IOException {
        if (this.bytecounter > 0) {
            if (this.linelength > 0 && this.linecounter == this.linelength) {
                this.outputStream.write(DebugFile.EOL.getBytes());
                this.linecounter = 0;
            }
            char charAt = Base64.chars.charAt((this.buffer << 8) >>> 26);
            char charAt2 = Base64.chars.charAt((this.buffer << 14) >>> 26);
            int charAt3 = this.bytecounter < 2 ? Base64.pad : Base64.chars.charAt((this.buffer << 20) >>> 26);
            int charAt4 = this.bytecounter < 3 ? Base64.pad : Base64.chars.charAt((this.buffer << 26) >>> 26);
            this.outputStream.write(charAt);
            this.outputStream.write(charAt2);
            this.outputStream.write(charAt3);
            this.outputStream.write(charAt4);
            this.linecounter += 4;
            this.bytecounter = 0;
            this.buffer = 0;
        }
    }
}
