package com.e;

import android.text.TextUtils;
import com.autonavi.aps.amapapi.model.AmapLoc;
import dji.common.flightcontroller.DJIFlightControllerDataType;

public class au {
    private static au a = null;
    private AmapLoc b = null;
    private long c = 0;
    private long d = 0;
    private boolean e = true;

    private au() {
    }

    public static synchronized au a() {
        au auVar;
        synchronized (au.class) {
            if (a == null) {
                a = new au();
            }
            auVar = a;
        }
        return auVar;
    }

    private AmapLoc c(AmapLoc amapLoc) {
        if (br.a(amapLoc) && this.e && bo.p() && br.a() - amapLoc.getTime() <= bo.q() && (amapLoc.getLocationType() == 5 || amapLoc.getLocationType() == 6)) {
            amapLoc.setLocationType(2);
        }
        return amapLoc;
    }

    public synchronized AmapLoc a(AmapLoc amapLoc) {
        if (!br.a(this.b) || !br.a(amapLoc)) {
            this.c = br.b();
            this.b = amapLoc;
            amapLoc = this.b;
        } else if (amapLoc.getTime() != this.b.getTime() || amapLoc.getAccuracy() >= 300.0f) {
            if (amapLoc.getProvider().equals("gps")) {
                this.c = br.b();
                this.b = amapLoc;
                amapLoc = this.b;
            } else if (amapLoc.getCoord() != this.b.getCoord()) {
                this.c = br.b();
                this.b = amapLoc;
                amapLoc = this.b;
            } else if (amapLoc.getPoiid().equals(this.b.getPoiid()) || TextUtils.isEmpty(amapLoc.getPoiid())) {
                float a = br.a(amapLoc, this.b);
                float accuracy = this.b.getAccuracy();
                float accuracy2 = amapLoc.getAccuracy();
                float f = accuracy2 - accuracy;
                long b = br.b();
                long j = b - this.c;
                if ((accuracy < 101.0f && accuracy2 > 299.0f) || (accuracy > 299.0f && accuracy2 > 299.0f)) {
                    if (this.d == 0) {
                        this.d = b;
                    } else if (b - this.d > 30000) {
                        this.c = b;
                        this.b = amapLoc;
                        this.d = 0;
                        amapLoc = this.b;
                    }
                    this.b = c(this.b);
                    amapLoc = this.b;
                } else if (accuracy2 >= DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity || accuracy <= 299.0f) {
                    if (accuracy2 <= 299.0f) {
                        this.d = 0;
                    }
                    if (a >= 10.0f || ((double) a) <= 0.1d) {
                        if (f < 300.0f) {
                            this.c = br.b();
                            this.b = amapLoc;
                            amapLoc = this.b;
                        } else if (j >= 30000) {
                            this.c = br.b();
                            this.b = amapLoc;
                            amapLoc = this.b;
                        } else {
                            this.b = c(this.b);
                            amapLoc = this.b;
                        }
                    } else if (f >= -300.0f) {
                        this.b = c(this.b);
                        amapLoc = this.b;
                    } else if (accuracy / accuracy2 >= 2.0f) {
                        this.c = b;
                        this.b = amapLoc;
                        amapLoc = this.b;
                    } else {
                        this.b = c(this.b);
                        amapLoc = this.b;
                    }
                } else {
                    this.c = b;
                    this.b = amapLoc;
                    this.d = 0;
                    amapLoc = this.b;
                }
            } else {
                this.c = br.b();
                this.b = amapLoc;
                amapLoc = this.b;
            }
        }
        return amapLoc;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public AmapLoc b(AmapLoc amapLoc) {
        return amapLoc;
    }

    public synchronized void b() {
        this.b = null;
        this.c = 0;
        this.d = 0;
    }
}
