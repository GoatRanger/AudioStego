package com.nostra13.universalimageloader.core.download;

import com.alipay.sdk.b.b;
import dji.pilot.usercenter.protocol.d;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public interface ImageDownloader {

    public enum Scheme {
        HTTP("http"),
        HTTPS(b.a),
        FILE(d.A),
        CONTENT("content"),
        ASSETS("assets"),
        DRAWABLE("drawable"),
        UNKNOWN("");
        
        private String scheme;
        private String uriPrefix;

        private Scheme(String str) {
            this.scheme = str;
            this.uriPrefix = str + "://";
        }

        public static Scheme ofUri(String str) {
            if (str != null) {
                for (Scheme scheme : values()) {
                    if (scheme.belongsTo(str)) {
                        return scheme;
                    }
                }
            }
            return UNKNOWN;
        }

        private boolean belongsTo(String str) {
            return str.toLowerCase(Locale.US).startsWith(this.uriPrefix);
        }

        public String wrap(String str) {
            return this.uriPrefix + str;
        }

        public String crop(String str) {
            if (belongsTo(str)) {
                return str.substring(this.uriPrefix.length());
            }
            throw new IllegalArgumentException(String.format("URI [%1$s] doesn't have expected scheme [%2$s]", new Object[]{str, this.scheme}));
        }
    }

    InputStream getStream(String str, Object obj) throws IOException;
}
