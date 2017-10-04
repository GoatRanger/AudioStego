package com.nokia.maps;

import com.nokia.maps.annotation.Internal;
import java.io.Serializable;
import java.net.HttpCookie;

@Internal
public class ds implements Serializable {
    private transient HttpCookie a;

    public ds(HttpCookie httpCookie) {
        this.a = httpCookie;
    }

    public HttpCookie a() {
        return this.a;
    }
}
