package dji.midware.media.i;

import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;

public class e {

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[CameraType.values().length];
        static final /* synthetic */ int[] b = new int[ProductType.values().length];

        static {
            try {
                b[ProductType.Orange.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[ProductType.litchiX.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[ProductType.Longan.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[ProductType.N1.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                b[ProductType.BigBanana.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                b[ProductType.OrangeRAW.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                b[ProductType.OrangeCV600.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                b[ProductType.litchiC.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                b[ProductType.litchiS.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                b[ProductType.P34K.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                b[ProductType.Tomato.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                b[ProductType.Pomato.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[CameraType.DJICameraTypeTau336.ordinal()] = 1;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[CameraType.DJICameraTypeTau640.ordinal()] = 2;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[CameraType.DJICameraTypeGD600.ordinal()] = 3;
            } catch (NoSuchFieldError e15) {
            }
        }
    }

    public static d a(ProductType productType) {
        d dVar = null;
        switch (AnonymousClass1.a[DataCameraGetPushStateInfo.getInstance().getCameraType().ordinal()]) {
            case 1:
            case 2:
            case 3:
                dVar = new i();
                break;
        }
        if (dVar != null) {
            return dVar;
        }
        switch (AnonymousClass1.b[productType.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return new j();
            case 8:
            case 9:
            case 10:
                return new i();
            case 11:
                return new j();
            case 12:
                return new f();
            default:
                return new j();
        }
    }
}
