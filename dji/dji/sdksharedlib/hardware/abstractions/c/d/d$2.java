package dji.sdksharedlib.hardware.abstractions.c.d;

import dji.common.camera.CameraVideoResolutionAndFrameRate;
import dji.common.error.DJIError;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$2 implements e {
    final /* synthetic */ CameraVideoResolutionAndFrameRate a;
    final /* synthetic */ e b;
    final /* synthetic */ d c;

    d$2(d dVar, CameraVideoResolutionAndFrameRate cameraVideoResolutionAndFrameRate, e eVar) {
        this.c = dVar;
        this.a = cameraVideoResolutionAndFrameRate;
        this.b = eVar;
    }

    public void a(Object obj) {
        this.c.e(false, new e(this) {
            final /* synthetic */ d$2 a;

            {
                this.a = r1;
            }

            public void a(Object obj) {
                d.a(this.a.c, this.a.a, new e(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void a(Object obj) {
                        if (this.a.a.b != null) {
                            this.a.a.b.a(null);
                        }
                    }

                    public void a(DJIError dJIError) {
                        if (this.a.a.b != null) {
                            this.a.a.b.a(dJIError);
                        }
                    }
                });
            }

            public void a(DJIError dJIError) {
                if (this.a.b != null) {
                    this.a.b.a(dJIError);
                }
            }
        });
    }

    public void a(DJIError dJIError) {
        this.b.a(dJIError);
    }
}
