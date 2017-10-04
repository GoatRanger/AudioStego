package dji.logic.f;

import dji.logic.a.a;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import java.util.HashMap;

public class d {
    public static boolean a() {
        ProductType c = i.getInstance().c();
        if ((c == ProductType.Tomato || c == ProductType.litchiS || c == ProductType.litchiX || c == ProductType.Orange || c == ProductType.BigBanana || c == ProductType.Olives || c == ProductType.OrangeRAW || c == ProductType.OrangeCV600 || c == ProductType.KumquatX || c == ProductType.KumquatS || c == ProductType.Pomato) && DataOsdGetPushCommon.getInstance().getFlycVersion() >= 9) {
            return true;
        }
        return false;
    }

    public static boolean a(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        if (productType == ProductType.Longan || productType == ProductType.LonganPro || productType == ProductType.LonganRaw || productType == ProductType.LonganZoom || productType == ProductType.LonganMobile) {
            return true;
        }
        return false;
    }

    public static boolean b(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        if (a.getInstance().b && !a.getInstance().a && r1 == ProductType.LonganMobile) {
            return true;
        }
        return false;
    }

    public static boolean c(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        if (productType == ProductType.LonganMobile) {
            return true;
        }
        return false;
    }

    public static boolean a(CameraType cameraType) {
        if (cameraType == null) {
            cameraType = i.getInstance().b();
        }
        return cameraType == CameraType.DJICameraTypeFC350;
    }

    public static boolean d(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return a(productType);
    }

    public static boolean e(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return (productType == ProductType.Longan && DataCameraGetPushStateInfo.getInstance().getVerstion() >= 4) || productType == ProductType.LonganZoom;
    }

    public static ProductType a(HashMap<String, String> hashMap) {
        if (hashMap == null) {
            return ProductType.OTHER;
        }
        CameraType cameraType;
        ProductType productType = ProductType.None;
        String str = (String) hashMap.get("mdl");
        if ("FC300S".equals(str)) {
            cameraType = CameraType.DJICameraTypeFC300S;
        } else if ("FC300X".equals(str)) {
            cameraType = CameraType.DJICameraTypeFC300X;
        } else if ("FC260".equals(str)) {
            cameraType = CameraType.DJICameraTypeFC260;
        } else if ("FC350".equals(str)) {
            cameraType = CameraType.DJICameraTypeFC350;
        } else if ("HG310".equals(str)) {
            return ProductType.Longan;
        } else {
            if ("OSMO RAW".equals(str)) {
                return ProductType.LonganRaw;
            }
            if ("OSMO PRO".equals(str)) {
                return ProductType.LonganPro;
            }
            if ("HF310Z".equalsIgnoreCase(str)) {
                return ProductType.LonganZoom;
            }
            if ("FC300XW".equalsIgnoreCase(str)) {
                cameraType = CameraType.DJICameraTypeFC300XW;
            } else if ("FC550RAW".equalsIgnoreCase(str)) {
                cameraType = CameraType.DJICameraTypeFC550Raw;
            } else if ("FC550".equalsIgnoreCase(str)) {
                cameraType = CameraType.DJICameraTypeFC550;
            } else if ("FC330".equalsIgnoreCase(str)) {
                cameraType = CameraType.DJICameraTypeFC330X;
            } else {
                cameraType = str == null ? null : null;
            }
        }
        if (productType != ProductType.None) {
            return productType;
        }
        if (cameraType == CameraType.DJICameraTypeFC300S) {
            return ProductType.litchiS;
        }
        if (cameraType == CameraType.DJICameraTypeFC300X) {
            return ProductType.litchiX;
        }
        if (cameraType == CameraType.DJICameraTypeFC260) {
            return ProductType.litchiC;
        }
        if (cameraType == CameraType.DJICameraTypeFC350) {
            return ProductType.Orange;
        }
        if (cameraType == CameraType.DJICameraTypeFC300XW) {
            return ProductType.P34K;
        }
        if (cameraType == CameraType.DJICameraTypeFC550) {
            return ProductType.Orange;
        }
        if (cameraType == CameraType.DJICameraTypeFC550Raw) {
            return ProductType.OrangeRAW;
        }
        if (cameraType == CameraType.DJICameraTypeFC330X) {
            return ProductType.Tomato;
        }
        return ProductType.OTHER;
    }

    public static String f(ProductType productType) {
        if (ProductType.Orange == productType || ProductType.BigBanana == productType || ProductType.OrangeRAW == productType || ProductType.Olives == productType) {
            return "WM610";
        }
        if (ProductType.litchiS == productType) {
            return "P3XS";
        }
        if (ProductType.litchiX == productType) {
            return "P3XS";
        }
        if (ProductType.litchiC == productType) {
            return "P3C";
        }
        if (ProductType.Longan == productType) {
            return "OSMO";
        }
        if (ProductType.LonganPro == productType) {
            return "OSMO_X5";
        }
        if (ProductType.LonganRaw == productType) {
            return "OSMO_X5R";
        }
        if (ProductType.LonganMobile == productType) {
            return "HG300";
        }
        if (ProductType.P34K == productType) {
            return "P3XW";
        }
        if (ProductType.LonganZoom == productType) {
            return "OSMO_FC350Z";
        }
        return null;
    }

    public static boolean g(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.LonganZoom;
    }

    public static boolean h(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.LonganMobile;
    }

    public static int i(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        if (productType == ProductType.LonganMobile) {
            return 30;
        }
        return 50;
    }

    public static boolean j(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.LonganZoom;
    }

    public static boolean k(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.Pomato || productType == ProductType.Tomato || productType == ProductType.KumquatS || productType == ProductType.KumquatX || productType == ProductType.Orange2;
    }
}
