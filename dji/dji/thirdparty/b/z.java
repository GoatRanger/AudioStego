package dji.thirdparty.b;

import java.io.IOException;

public enum z {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2");
    
    private final String e;

    private z(String str) {
        this.e = str;
    }

    public static z get(String str) throws IOException {
        if (str.equals(HTTP_1_0.e)) {
            return HTTP_1_0;
        }
        if (str.equals(HTTP_1_1.e)) {
            return HTTP_1_1;
        }
        if (str.equals(HTTP_2.e)) {
            return HTTP_2;
        }
        if (str.equals(SPDY_3.e)) {
            return SPDY_3;
        }
        throw new IOException("Unexpected protocol: " + str);
    }

    public String toString() {
        return this.e;
    }
}
