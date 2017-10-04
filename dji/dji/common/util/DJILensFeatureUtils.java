package dji.common.util;

import android.util.SparseArray;
import dji.common.camera.DJICameraSettingsDef.CameraAperture;
import dji.gs.c.e;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.util.EnumMap;
import java.util.HashMap;

public class DJILensFeatureUtils {
    private static final String DEFAULT_MEMBERNAME = "Unknown";
    private static final String DEFAUL_PRODUCTNAME = "Unknown";
    private static final int MEMBERID_DJI = 8;
    private static final int MEMBERID_JKIMAGING = 7;
    private static final int MEMBERID_KENKO_TOKINA = 6;
    private static final int MEMBERID_OLYMPUS = 0;
    private static final int MEMBERID_PANASONIC2 = 2;
    private static final int MEMBERID_PANASONIC3 = 3;
    private static final int MEMBERID_SIGMA = 1;
    private static final int MEMBERID_TAMRON = 5;
    private static final SparseArray<SparseArray<String>> mCameraInfoSpArray = new SparseArray();

    private static int generateKey(int i, int i2) {
        return (i << 16) & i2;
    }

    static HashMap<Integer, CameraAperture> buildApertureMapRevert() {
        HashMap<Integer, CameraAperture> hashMap = new HashMap();
        hashMap.put(Integer.valueOf(1000), CameraAperture.F_10p0);
        hashMap.put(Integer.valueOf(1100), CameraAperture.F_11p0);
        hashMap.put(Integer.valueOf(1300), CameraAperture.F_13p0);
        hashMap.put(Integer.valueOf(1400), CameraAperture.F_14p0);
        hashMap.put(Integer.valueOf(1600), CameraAperture.F_16p0);
        hashMap.put(Integer.valueOf(1800), CameraAperture.F_18p0);
        hashMap.put(Integer.valueOf(170), CameraAperture.F_1p7);
        hashMap.put(Integer.valueOf(180), CameraAperture.F_1p8);
        hashMap.put(Integer.valueOf(2000), CameraAperture.F_20p0);
        hashMap.put(Integer.valueOf(2200), CameraAperture.F_22p0);
        hashMap.put(Integer.valueOf(200), CameraAperture.F_2p0);
        hashMap.put(Integer.valueOf(FTPCodes.SERVICE_READY_FOR_NEW_USER), CameraAperture.F_2p2);
        hashMap.put(Integer.valueOf(250), CameraAperture.F_2p5);
        hashMap.put(Integer.valueOf(280), CameraAperture.F_2p8);
        hashMap.put(Integer.valueOf(320), CameraAperture.F_3p2);
        hashMap.put(Integer.valueOf(FTPCodes.PENDING_FURTHER_INFORMATION), CameraAperture.F_3p5);
        hashMap.put(Integer.valueOf(400), CameraAperture.F_4p0);
        hashMap.put(Integer.valueOf(FTPCodes.FILE_ACTION_NOT_TAKEN), CameraAperture.F_4p5);
        hashMap.put(Integer.valueOf(500), CameraAperture.F_5p0);
        hashMap.put(Integer.valueOf(560), CameraAperture.F_5p6);
        hashMap.put(Integer.valueOf(630), CameraAperture.F_6p3);
        hashMap.put(Integer.valueOf(710), CameraAperture.F_7p1);
        hashMap.put(Integer.valueOf(e.g), CameraAperture.F_8p0);
        hashMap.put(Integer.valueOf(900), CameraAperture.F_9p0);
        return hashMap;
    }

    static EnumMap<CameraAperture, Short> buildApertureMap() {
        EnumMap<CameraAperture, Short> enumMap = new EnumMap(CameraAperture.class);
        enumMap.put(CameraAperture.F_10p0, Short.valueOf((short) 1000));
        enumMap.put(CameraAperture.F_11p0, Short.valueOf((short) 1100));
        enumMap.put(CameraAperture.F_13p0, Short.valueOf((short) 1300));
        enumMap.put(CameraAperture.F_14p0, Short.valueOf((short) 1400));
        enumMap.put(CameraAperture.F_16p0, Short.valueOf((short) 1600));
        enumMap.put(CameraAperture.F_18p0, Short.valueOf((short) 1800));
        enumMap.put(CameraAperture.F_1p7, Short.valueOf((short) 170));
        enumMap.put(CameraAperture.F_1p8, Short.valueOf((short) 180));
        enumMap.put(CameraAperture.F_20p0, Short.valueOf((short) 2000));
        enumMap.put(CameraAperture.F_22p0, Short.valueOf((short) 2200));
        enumMap.put(CameraAperture.F_2p0, Short.valueOf((short) 200));
        enumMap.put(CameraAperture.F_2p2, Short.valueOf((short) 220));
        enumMap.put(CameraAperture.F_2p5, Short.valueOf((short) 250));
        enumMap.put(CameraAperture.F_2p8, Short.valueOf((short) 280));
        enumMap.put(CameraAperture.F_3p2, Short.valueOf((short) 320));
        enumMap.put(CameraAperture.F_3p5, Short.valueOf((short) 350));
        enumMap.put(CameraAperture.F_4p0, Short.valueOf((short) 400));
        enumMap.put(CameraAperture.F_4p5, Short.valueOf((short) 450));
        enumMap.put(CameraAperture.F_5p0, Short.valueOf((short) 500));
        enumMap.put(CameraAperture.F_5p6, Short.valueOf((short) 560));
        enumMap.put(CameraAperture.F_6p3, Short.valueOf((short) 630));
        enumMap.put(CameraAperture.F_7p1, Short.valueOf((short) 710));
        enumMap.put(CameraAperture.F_8p0, Short.valueOf((short) 800));
        enumMap.put(CameraAperture.F_9p0, Short.valueOf((short) 900));
        return enumMap;
    }

    public static String getProductName(int i, int i2, int i3) {
        SparseArray sparseArray = (SparseArray) mCameraInfoSpArray.get(i2);
        if (sparseArray == null) {
            if (8 == i) {
                sparseArray = loadDJISp();
            } else if (7 == i) {
                sparseArray = loadJKImagingSp();
            } else if (6 == i) {
                sparseArray = loadKenkoTokinaSp();
            } else if (5 == i) {
                sparseArray = loadTamronSp();
            } else if (3 == i) {
                sparseArray = loadPanasonic3Sp();
            } else if (2 == i) {
                sparseArray = loadPanasonic2Sp();
            } else if (1 == i) {
                sparseArray = loadSigmaSp();
            } else if (i == 0) {
                sparseArray = loadOlympusSp();
            }
        }
        if (sparseArray != null) {
            return (String) sparseArray.get(generateKey(i2, i3), "Unknown");
        }
        return "Unknown";
    }

    public static SparseArray<String> loadOlympusSp() {
        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(generateKey(4097, 0), "M.ZUIKO DIGITAL ED 14-42mm F3.5-5.6");
        sparseArray.put(generateKey(4103, 0), "M.ZUIKO DIGITAL ED 12mm F2.0");
        sparseArray.put(generateKey(4113, 0), "M.ZUIKO DIGITAL 45mm F1.8");
        sparseArray.put(generateKey(4118, 0), "M.ZUIKO DIGITAL 17mm F1.8");
        sparseArray.put(generateKey(4129, 0), "M.ZUIKO DIGITAL ED 14-42mm F3.5-5.6 EZ");
        sparseArray.put(generateKey(4130, 0), "M.ZUIKO DIGITAL 25mm F1.8");
        sparseArray.put(generateKey(4131, 0), "M.ZUIKO DIGITAL ED 7-14mm F2.8 PRO");
        sparseArray.put(generateKey(4133, 0), "M.ZUIKO DIGITAL ED 8mm F1.8 Fisheye");
        mCameraInfoSpArray.put(0, sparseArray);
        return sparseArray;
    }

    public static SparseArray<String> loadSigmaSp() {
        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(generateKey(4099, 0), "SIGMA 30mm F2.8 DN");
        sparseArray.put(generateKey(4100, 0), "SIGMA 19mm F2.8 DN");
        sparseArray.put(generateKey(4101, 0), "SIGMA 60mm F2.8 DN");
        mCameraInfoSpArray.put(1, sparseArray);
        return sparseArray;
    }

    public static SparseArray<String> loadPanasonic2Sp() {
        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(generateKey(4101, 4096), "LUMIX G 20mm F1.7");
        sparseArray.put(generateKey(4101, 4352), "LUMIX G 20mm F1.7 II");
        sparseArray.put(generateKey(4116, 4096), "LUMIX G VARIO PZ 14-42mm/F3.5-5.6");
        sparseArray.put(generateKey(4131, 4096), "LUMIX G VARIO 35-100mm/F4.0-5.6");
        sparseArray.put(generateKey(4132, 4096), "LUMIX G MACRO 30mm/F2.8");
        sparseArray.put(generateKey(4133, 4096), "LUMIX G 42.5mm/F1.7");
        sparseArray.put(generateKey(4134, 4096), "LUMIX G 25mm/F1.7");
        mCameraInfoSpArray.put(2, sparseArray);
        return sparseArray;
    }

    public static SparseArray<String> loadPanasonic3Sp() {
        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(generateKey(2, 4096), "LEICA D SUMMILUX 25mm F1.4 ASPH");
        mCameraInfoSpArray.put(3, sparseArray);
        return sparseArray;
    }

    public static SparseArray<String> loadTamronSp() {
        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(generateKey(4097, 1), "14-150mm F/3.5-5.8 Di IIII C 001");
        sparseArray.put(generateKey(4098, 1), "14-150mm F/3.5-5.8 Di IIII C 001");
        mCameraInfoSpArray.put(5, sparseArray);
        return sparseArray;
    }

    public static SparseArray<String> loadKenkoTokinaSp() {
        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(generateKey(4097, 0), "Reflex 300mm F6.3 MF Macro");
        mCameraInfoSpArray.put(6, sparseArray);
        return sparseArray;
    }

    public static SparseArray<String> loadJKImagingSp() {
        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(generateKey(4097, 4096), "PIXPRO SZ 12-45/F3.5-6.3 AF");
        sparseArray.put(generateKey(4098, 4096), "PIXPRO SZ 42.5-160/F3.9-5.9 AF");
        mCameraInfoSpArray.put(7, sparseArray);
        return sparseArray;
    }

    public static SparseArray<String> loadDJISp() {
        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(generateKey(4097, 0), "DJI MFT 15mm F1.7 ASPH");
        mCameraInfoSpArray.put(8, sparseArray);
        return sparseArray;
    }
}
