package com.here.android.mpa.mapping;

import android.graphics.PointF;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public class Map$PixelResult {
    private PointF a;
    private Error b;

    @Online
    public enum Error {
        NONE(0),
        NOT_IN_VIEW(1),
        OVERFLOW(2),
        UNKNOWN(3);
        
        int a;

        private Error(int i) {
            this.a = i;
        }

        static Error a(int i) {
            Error error = UNKNOWN;
            switch (i) {
                case 0:
                    return NONE;
                case 1:
                    return NOT_IN_VIEW;
                case 2:
                    return OVERFLOW;
                case 3:
                    return UNKNOWN;
                default:
                    return error;
            }
        }
    }

    @OnlineNative
    private Map$PixelResult(int i, float f, float f2) {
        this.a = new PointF(f, f2);
        this.b = Error.a(i);
    }

    public PointF getResult() {
        return this.a;
    }

    public Error getError() {
        return this.b;
    }
}
