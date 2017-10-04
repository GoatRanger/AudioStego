package dji.pilot.fpv.camera.more.a;

import android.content.Context;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.data.model.P3.DataCameraSetPhotoMode;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.camera.more.a$a;
import dji.pilot.fpv.d.b;
import dji.pilot.publics.objects.g;
import dji.thirdparty.a.c;

public class a {
    private static final String a = "PhotoType";
    private static final String b = "PhotoTypeValue";
    private Context c = null;
    private CameraType d = CameraType.OTHER;
    private int e = -1;
    private TYPE f = TYPE.b;
    private int g = 0;

    public a(Context context) {
        this.c = context;
        k();
    }

    public int[] a() {
        ProductType c = i.getInstance().c();
        if (b.k(null)) {
            return new int[]{TYPE.b.a(), TYPE.e.a(), TYPE.g.a()};
        } else if (!b.a(c)) {
            ProductType c2 = i.getInstance().c();
            if (dji.pilot.publics.e.a.d(c2) || c2 == ProductType.A3 || c2 == ProductType.N3) {
                return new int[]{TYPE.b.a(), TYPE.e.a(), TYPE.f.a(), TYPE.g.a()};
            } else if (c == ProductType.KumquatX) {
                return new int[]{TYPE.b.a(), TYPE.c.a(), TYPE.e.a(), TYPE.f.a(), TYPE.g.a()};
            } else if (c == ProductType.Pomato || c == ProductType.KumquatS) {
                return new int[]{TYPE.b.a(), TYPE.e.a(), TYPE.f.a(), TYPE.g.a()};
            } else {
                return new int[]{TYPE.b.a(), TYPE.c.a(), TYPE.e.a(), TYPE.f.a(), TYPE.g.a()};
            }
        } else if (ProductType.OrangeCV600 == c) {
            return new int[]{TYPE.b.a(), TYPE.c.a(), TYPE.e.a(), TYPE.f.a(), TYPE.g.a()};
        } else if (b.c(this.d) || DataCameraGetPushStateInfo.getInstance().getVerstion() < 1) {
            return new int[]{TYPE.b.a(), TYPE.e.a(), TYPE.f.a(), TYPE.g.a()};
        } else {
            return new int[]{TYPE.b.a(), TYPE.c.a(), TYPE.e.a(), TYPE.f.a(), TYPE.g.a()};
        }
    }

    public int[] b() {
        ProductType c = i.getInstance().c();
        if (b.k(null)) {
            return new int[]{R.drawable.advanced_more_photomode_single, R.drawable.advanced_more_photomode_burst, R.drawable.advanced_more_photomode_timer};
        }
        if (!b.a(c)) {
            ProductType c2 = i.getInstance().c();
            if (dji.pilot.publics.e.a.d(c2) || c2 == ProductType.A3 || c2 == ProductType.N3) {
                return new int[]{R.drawable.advanced_more_photomode_single, R.drawable.advanced_more_photomode_burst, R.drawable.advanced_more_photomode_aeb, R.drawable.advanced_more_photomode_timer};
            }
            if (c == ProductType.KumquatX) {
                return new int[]{R.drawable.advanced_more_photomode_single, R.drawable.advanced_more_photomode_hdr, R.drawable.advanced_more_photomode_burst, R.drawable.advanced_more_photomode_aeb, R.drawable.advanced_more_photomode_timer};
            }
            if (c == ProductType.Pomato || c == ProductType.KumquatS) {
                return new int[]{R.drawable.advanced_more_photomode_single, R.drawable.advanced_more_photomode_burst, R.drawable.advanced_more_photomode_aeb, R.drawable.advanced_more_photomode_timer};
            }
            return new int[]{R.drawable.advanced_more_photomode_single, R.drawable.advanced_more_photomode_hdr, R.drawable.advanced_more_photomode_burst, R.drawable.advanced_more_photomode_aeb, R.drawable.advanced_more_photomode_timer};
        } else if (ProductType.OrangeCV600 == c) {
            return new int[]{R.drawable.advanced_more_photomode_single, R.drawable.advanced_more_photomode_hdr, R.drawable.advanced_more_photomode_burst, R.drawable.advanced_more_photomode_aeb, R.drawable.advanced_more_photomode_timer};
        } else {
            if (b.c(this.d) || DataCameraGetPushStateInfo.getInstance().getVerstion() < 1) {
                return new int[]{R.drawable.advanced_more_photomode_single, R.drawable.advanced_more_photomode_burst, R.drawable.advanced_more_photomode_aeb, R.drawable.advanced_more_photomode_timer};
            }
            return new int[]{R.drawable.advanced_more_photomode_single, R.drawable.advanced_more_photomode_hdr, R.drawable.advanced_more_photomode_burst, R.drawable.advanced_more_photomode_aeb, R.drawable.advanced_more_photomode_timer};
        }
    }

    public String[] c() {
        ProductType c = i.getInstance().c();
        if (b.k(null)) {
            return new String[]{this.c.getString(R.string.fpv_camera_photomode_single), this.c.getString(R.string.fpv_camera_photomode_burst), this.c.getString(R.string.fpv_camera_photomode_time)};
        } else if (!b.a(c)) {
            ProductType c2 = i.getInstance().c();
            if (dji.pilot.publics.e.a.d(c2) || c2 == ProductType.A3 || c2 == ProductType.N3) {
                return new String[]{this.c.getString(R.string.fpv_camera_photomode_single), this.c.getString(R.string.fpv_camera_photomode_burst), this.c.getString(R.string.fpv_camera_photomode_aeb), this.c.getString(R.string.fpv_camera_photomode_time)};
            } else if (c == ProductType.KumquatX) {
                return new String[]{this.c.getString(R.string.fpv_camera_photomode_single), this.c.getString(R.string.fpv_camera_photomode_hdr), this.c.getString(R.string.fpv_camera_photomode_burst), this.c.getString(R.string.fpv_camera_photomode_aeb), this.c.getString(R.string.fpv_camera_photomode_time)};
            } else if (c == ProductType.Pomato || c == ProductType.KumquatS) {
                return new String[]{this.c.getString(R.string.fpv_camera_photomode_single), this.c.getString(R.string.fpv_camera_photomode_burst), this.c.getString(R.string.fpv_camera_photomode_aeb), this.c.getString(R.string.fpv_camera_photomode_time)};
            } else {
                return new String[]{this.c.getString(R.string.fpv_camera_photomode_single), this.c.getString(R.string.fpv_camera_photomode_hdr), this.c.getString(R.string.fpv_camera_photomode_burst), this.c.getString(R.string.fpv_camera_photomode_aeb), this.c.getString(R.string.fpv_camera_photomode_time)};
            }
        } else if (ProductType.OrangeCV600 == c) {
            return new String[]{this.c.getString(R.string.fpv_camera_photomode_single), this.c.getString(R.string.fpv_camera_photomode_hdr), this.c.getString(R.string.fpv_camera_photomode_burst), this.c.getString(R.string.fpv_camera_photomode_aeb), this.c.getString(R.string.fpv_camera_photomode_time)};
        } else if (b.c(this.d) || DataCameraGetPushStateInfo.getInstance().getVerstion() < 1) {
            return new String[]{this.c.getString(R.string.fpv_camera_photomode_single), this.c.getString(R.string.fpv_camera_photomode_burst), this.c.getString(R.string.fpv_camera_photomode_aeb), this.c.getString(R.string.fpv_camera_photomode_time)};
        } else {
            return new String[]{this.c.getString(R.string.fpv_camera_photomode_single), this.c.getString(R.string.fpv_camera_photomode_hdr), this.c.getString(R.string.fpv_camera_photomode_burst), this.c.getString(R.string.fpv_camera_photomode_aeb), this.c.getString(R.string.fpv_camera_photomode_time)};
        }
    }

    public int a(int i, int i2) {
        if (TYPE.c.a() == i) {
            return R.drawable.advanced_more_photomode_hdr;
        }
        if (TYPE.e.a() == i) {
            if (i2 == 14) {
                return R.drawable.advanced_more_photomode_burst14;
            }
            if (i2 == 10) {
                return R.drawable.advanced_more_photomode_burst10;
            }
            if (i2 == 7) {
                return R.drawable.advanced_more_photomode_burst7;
            }
            if (i2 == 5) {
                return R.drawable.advanced_more_photomode_burst5;
            }
            return R.drawable.advanced_more_photomode_burst3;
        } else if (TYPE.f.a() == i) {
            if (i2 == 7) {
                return R.drawable.advanced_more_photomode_aeb7;
            }
            if (i2 == 5) {
                return R.drawable.advanced_more_photomode_aeb5;
            }
            return R.drawable.advanced_more_photomode_aeb3;
        } else if (TYPE.g.a() != i && TYPE.a.a() != i) {
            return R.drawable.advanced_more_photomode_single;
        } else {
            if (i2 == 60) {
                return R.drawable.advanced_more_photomode_timer60;
            }
            if (i2 == 30) {
                return R.drawable.advanced_more_photomode_timer30;
            }
            if (i2 == 20) {
                return R.drawable.advanced_more_photomode_timer20;
            }
            if (i2 == 15) {
                return R.drawable.advanced_more_photomode_timer15;
            }
            if (i2 == 10) {
                return R.drawable.advanced_more_photomode_timer10;
            }
            if (i2 == 7) {
                return R.drawable.advanced_more_photomode_timer7;
            }
            if (i2 == 3) {
                return R.drawable.advanced_more_photomode_timer3;
            }
            if (i2 == 2) {
                return R.drawable.advanced_more_photomode_timer2;
            }
            return R.drawable.advanced_more_photomode_timer5;
        }
    }

    public int[] a(int i) {
        CameraType b = i.getInstance().b();
        if (TYPE.e.a() == i) {
            if (b == CameraType.DJICameraTypeFC220S || b == CameraType.DJICameraTypeGD600) {
                return new int[]{3, 5};
            }
            if (CameraType.DJICameraTypeFC6310 == b) {
                return new int[]{3, 5, 7, 10, 14};
            }
            return new int[]{3, 5, 7};
        } else if (TYPE.f.a() == i) {
            if (b == CameraType.DJICameraTypeFC220S) {
                return new int[]{3};
            } else if (CameraType.DJICameraTypeFC6310 == b) {
                return new int[]{3, 5};
            } else {
                return new int[]{3, 5};
            }
        } else if (TYPE.g.a() != i && TYPE.a.a() != i) {
            return null;
        } else {
            if (b.c(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
                if (DataCameraGetPushShotParams.getInstance().isGetted() && DataCameraGetPushShotParams.getInstance().getImageFormat() == 1) {
                    if (b == CameraType.DJICameraTypeFC220S) {
                        return new int[]{5, 7, 10, 20, 30};
                    }
                    if (b == CameraType.DJICameraTypeFC220) {
                        return new int[]{2, 3, 5, 7, 10, 15, 20, 30, 60};
                    }
                    if (CameraType.DJICameraTypeFC6310 == b) {
                        return new int[]{2, 3, 5, 7, 10, 15, 30, 60};
                    }
                    if (CameraType.DJICameraTypeGD600 == b) {
                        return new int[]{2, 3, 5, 7, 10, 15, 20, 30};
                    }
                    return new int[]{5, 7, 10, 20, 30, 60};
                } else if (b == CameraType.DJICameraTypeFC220S) {
                    return new int[]{10, 20, 30};
                } else {
                    if (b == CameraType.DJICameraTypeFC220) {
                        return new int[]{10, 15, 20, 30, 60};
                    }
                    if (CameraType.DJICameraTypeFC6310 == b) {
                        return new int[]{5, 7, 10, 15, 30, 60};
                    }
                    if (CameraType.DJICameraTypeGD600 == b) {
                        return new int[]{2, 3, 5, 7, 10, 15, 20, 30};
                    }
                    return new int[]{10, 20, 30, 60};
                }
            } else if (DataCameraGetPushShotParams.getInstance().isGetted() && DataCameraGetPushShotParams.getInstance().getImageFormat() == 1) {
                return new int[]{5, 7, 10, 20, 30};
            } else {
                return new int[]{10, 20, 30};
            }
        }
    }

    public TYPE d() {
        return this.f;
    }

    private boolean j() {
        return b.j(this.d) || !(b.k(null) || this.d == CameraType.DJICameraTypeFC220S || this.d == CameraType.DJICameraTypeFC220 || this.d == CameraType.DJICameraTypeFC6310);
    }

    private void a(TYPE type, int i, int i2) {
        dji.pilot.fpv.camera.a.a.a(null, "setPhotoType-" + type + com.alipay.sdk.j.i.b + i);
        DataCameraSetPhotoMode.getInstance().a(type).a(i).c(255).b(0).d(i2).start(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("", "DataCameraSetPhotoMode success value=" + this.a.g, false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("", "DataCameraSetPhotoMode fail " + aVar, false, true);
            }
        });
    }

    public void e() {
        a(this.f, this.g, this.g);
    }

    public int f() {
        return this.g;
    }

    public int a(TYPE type, int i) {
        if (j()) {
            return g.b(this.c, type + b, i);
        }
        if (TYPE.f == type) {
            return DataCameraGetPushShotParams.getInstance().getAEBNumber();
        }
        if (TYPE.e == type) {
            return DataCameraGetPushShotParams.getInstance().getContinuous();
        }
        if (TYPE.g == type) {
            return DataCameraGetPushShotParams.getInstance().getTimeParamsPeriod();
        }
        return i;
    }

    public boolean b(TYPE type, int i) {
        if (j()) {
            a(type, i, false);
            return true;
        }
        a(type, i, i);
        return false;
    }

    private void a(TYPE type, int i, boolean z) {
        if (this.f != type || this.g != i) {
            this.f = type;
            this.g = i;
            dji.pilot.fpv.camera.a.a.a(null, "updatePhotoTypeInner-" + type + com.alipay.sdk.j.i.b + i + com.alipay.sdk.j.i.b + z);
            g.a(this.c, a, type.a());
            g.a(this.c, this.f + b, i);
            e();
            c.a().e(a$a.PHOTOTYPE_CHANGED);
            if (z) {
                c.a().e(a$a.PHOTOTYPE_CHANGED_AUTO);
            }
        }
    }

    public void a(CameraType cameraType) {
        if (this.d != cameraType) {
            this.d = cameraType;
            if (j()) {
                int[] g;
                k();
                if (b.j(cameraType)) {
                    g = g();
                } else {
                    g = a();
                }
                if (dji.pilot.fpv.camera.more.a.a(g, this.f.a(), -1) == -1) {
                    this.f = TYPE.b;
                    e();
                    c.a().e(a$a.PHOTOTYPE_CHANGED);
                    c.a().e(a$a.PHOTOTYPE_CHANGED_AUTO);
                } else if (TYPE.g == this.f && !b.j(cameraType)) {
                    g = a(this.f.a());
                    if (dji.pilot.fpv.camera.more.a.a(g, this.g, -1) == -1) {
                        a(this.f, g[0], true);
                    }
                }
            }
        }
    }

    public void a(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (this.d != CameraType.OTHER && !b.j(this.d)) {
            if (!j()) {
                TYPE photoType = dataCameraGetPushShotParams.getPhotoType();
                if (photoType == TYPE.a) {
                    photoType = TYPE.g;
                }
                int i = 0;
                if (photoType == TYPE.g) {
                    i = dataCameraGetPushShotParams.getTimeParamsPeriod();
                } else if (photoType == TYPE.f) {
                    i = dataCameraGetPushShotParams.getAEBNumber();
                } else if (photoType == TYPE.e) {
                    i = dataCameraGetPushShotParams.getContinuous();
                }
                a(photoType, i, true);
            }
            int imageFormat = dataCameraGetPushShotParams.getImageFormat();
            if (this.e != imageFormat) {
                this.e = imageFormat;
                if (TYPE.g == this.f) {
                    a(this.d);
                }
            }
        }
    }

    public void a(o oVar) {
        if (oVar != o.b && oVar == o.a) {
            this.e = -1;
            this.d = CameraType.OTHER;
        }
    }

    private void k() {
        this.f = TYPE.find(g.b(this.c, a, TYPE.b.a()));
        this.g = g.b(this.c, this.f + b, 0);
    }

    public int[] g() {
        return new int[]{TYPE.b.a(), TYPE.g.a()};
    }

    public int[] h() {
        return new int[]{R.drawable.advanced_more_photomode_single, R.drawable.advanced_more_photomode_timer};
    }

    public String[] i() {
        return new String[]{this.c.getString(R.string.fpv_camera_photomode_single), this.c.getString(R.string.fpv_camera_photomode_time)};
    }

    public int[] b(int i) {
        if (TYPE.g.a() == i) {
            return new int[]{1, 60};
        }
        return a(i);
    }
}
