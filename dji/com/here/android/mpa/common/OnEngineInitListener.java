package com.here.android.mpa.common;

import com.nokia.maps.annotation.Online;
import com.nokia.maps.aq;
import com.nokia.maps.aq.a;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

@Online
public interface OnEngineInitListener {

    @Online
    public enum Error {
        NONE,
        USAGE_EXPIRED,
        MODEL_NOT_SUPPORTED,
        DEVICE_NOT_SUPPORTED,
        UNKNOWN,
        MISSING_APP_CREDENTIAL,
        BUSY,
        FILE_RW_ERROR,
        MISSING_PERMISSION,
        MISSING_SERVICE,
        MISSING_LIBRARIES,
        OPERATION_NOT_ALLOWED;
        
        private String a;
        private String b;
        private Throwable c;

        static {
            aq.a(new a() {
                public Error a(Error error, String str, Throwable th) {
                    return error.a(str, th);
                }
            });
        }

        private Error a(String str, Throwable th) {
            this.a = str;
            this.c = th;
            Writer stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            this.b = stringWriter.toString();
            return this;
        }

        public String getDetails() {
            return this.a;
        }

        public String getStackTrace() {
            return this.b;
        }

        public Throwable getThrowable() {
            return this.c;
        }
    }

    void onEngineInitializationCompleted(Error error);
}
