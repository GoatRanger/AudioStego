package dji.thirdparty.afinal.f;

import com.here.odnp.debug.DebugFile;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

class d implements HttpEntity {
    private static final char[] d = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    ByteArrayOutputStream a = new ByteArrayOutputStream();
    boolean b = false;
    boolean c = false;
    private String e = null;

    public d() {
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        while (i < 30) {
            stringBuffer.append(d[random.nextInt(d.length)]);
            i++;
        }
        this.e = stringBuffer.toString();
    }

    public void a() {
        if (!this.c) {
            try {
                this.a.write(("--" + this.e + DebugFile.EOL).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.c = true;
    }

    public void b() {
        if (!this.b) {
            try {
                this.a.write(("\r\n--" + this.e + "--\r\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.b = true;
        }
    }

    public void a(String str, String str2) {
        a();
        try {
            this.a.write(("Content-Disposition: form-data; name=\"" + str + "\"\r\n\r\n").getBytes());
            this.a.write(str2.getBytes());
            this.a.write(("\r\n--" + this.e + DebugFile.EOL).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a(String str, String str2, InputStream inputStream, boolean z) {
        a(str, str2, inputStream, RequestParams.APPLICATION_OCTET_STREAM, z);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r5, java.lang.String r6, java.io.InputStream r7, java.lang.String r8, boolean r9) {
        /*
        r4 = this;
        r4.a();
        r0 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x006e }
        r0.<init>();	 Catch:{ IOException -> 0x006e }
        r1 = "Content-Type: ";
        r0 = r0.append(r1);	 Catch:{ IOException -> 0x006e }
        r0 = r0.append(r8);	 Catch:{ IOException -> 0x006e }
        r1 = "\r\n";
        r0 = r0.append(r1);	 Catch:{ IOException -> 0x006e }
        r0 = r0.toString();	 Catch:{ IOException -> 0x006e }
        r1 = r4.a;	 Catch:{ IOException -> 0x006e }
        r2 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x006e }
        r2.<init>();	 Catch:{ IOException -> 0x006e }
        r3 = "Content-Disposition: form-data; name=\"";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x006e }
        r2 = r2.append(r5);	 Catch:{ IOException -> 0x006e }
        r3 = "\"; filename=\"";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x006e }
        r2 = r2.append(r6);	 Catch:{ IOException -> 0x006e }
        r3 = "\"\r\n";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x006e }
        r2 = r2.toString();	 Catch:{ IOException -> 0x006e }
        r2 = r2.getBytes();	 Catch:{ IOException -> 0x006e }
        r1.write(r2);	 Catch:{ IOException -> 0x006e }
        r1 = r4.a;	 Catch:{ IOException -> 0x006e }
        r0 = r0.getBytes();	 Catch:{ IOException -> 0x006e }
        r1.write(r0);	 Catch:{ IOException -> 0x006e }
        r0 = r4.a;	 Catch:{ IOException -> 0x006e }
        r1 = "Content-Transfer-Encoding: binary\r\n\r\n";
        r1 = r1.getBytes();	 Catch:{ IOException -> 0x006e }
        r0.write(r1);	 Catch:{ IOException -> 0x006e }
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r0 = new byte[r0];	 Catch:{ IOException -> 0x006e }
    L_0x0060:
        r1 = r7.read(r0);	 Catch:{ IOException -> 0x006e }
        r2 = -1;
        if (r1 == r2) goto L_0x0076;
    L_0x0067:
        r2 = r4.a;	 Catch:{ IOException -> 0x006e }
        r3 = 0;
        r2.write(r0, r3, r1);	 Catch:{ IOException -> 0x006e }
        goto L_0x0060;
    L_0x006e:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00af }
        r7.close();	 Catch:{ IOException -> 0x00aa }
    L_0x0075:
        return;
    L_0x0076:
        if (r9 != 0) goto L_0x009c;
    L_0x0078:
        r0 = r4.a;	 Catch:{ IOException -> 0x006e }
        r1 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x006e }
        r1.<init>();	 Catch:{ IOException -> 0x006e }
        r2 = "\r\n--";
        r1 = r1.append(r2);	 Catch:{ IOException -> 0x006e }
        r2 = r4.e;	 Catch:{ IOException -> 0x006e }
        r1 = r1.append(r2);	 Catch:{ IOException -> 0x006e }
        r2 = "\r\n";
        r1 = r1.append(r2);	 Catch:{ IOException -> 0x006e }
        r1 = r1.toString();	 Catch:{ IOException -> 0x006e }
        r1 = r1.getBytes();	 Catch:{ IOException -> 0x006e }
        r0.write(r1);	 Catch:{ IOException -> 0x006e }
    L_0x009c:
        r0 = r4.a;	 Catch:{ IOException -> 0x006e }
        r0.flush();	 Catch:{ IOException -> 0x006e }
        r7.close();	 Catch:{ IOException -> 0x00a5 }
        goto L_0x0075;
    L_0x00a5:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0075;
    L_0x00aa:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0075;
    L_0x00af:
        r0 = move-exception;
        r7.close();	 Catch:{ IOException -> 0x00b4 }
    L_0x00b3:
        throw r0;
    L_0x00b4:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00b3;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.afinal.f.d.a(java.lang.String, java.lang.String, java.io.InputStream, java.lang.String, boolean):void");
    }

    public void a(String str, File file, boolean z) {
        try {
            a(str, file.getName(), new FileInputStream(file), z);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public long getContentLength() {
        b();
        return (long) this.a.toByteArray().length;
    }

    public Header getContentType() {
        return new BasicHeader(AsyncHttpClient.HEADER_CONTENT_TYPE, "multipart/form-data; boundary=" + this.e);
    }

    public boolean isChunked() {
        return false;
    }

    public boolean isRepeatable() {
        return false;
    }

    public boolean isStreaming() {
        return false;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.a.toByteArray());
    }

    public Header getContentEncoding() {
        return null;
    }

    public void consumeContent() throws IOException, UnsupportedOperationException {
        if (isStreaming()) {
            throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
        }
    }

    public InputStream getContent() throws IOException, UnsupportedOperationException {
        return new ByteArrayInputStream(this.a.toByteArray());
    }
}
