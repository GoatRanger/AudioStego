package dji.thirdparty.b.a.b;

import java.io.IOException;

public final class l extends Exception {
    public /* synthetic */ Throwable getCause() {
        return a();
    }

    public l(IOException iOException) {
        super(iOException);
    }

    public IOException a() {
        return (IOException) super.getCause();
    }
}
