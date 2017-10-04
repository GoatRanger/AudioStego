package dji.pilot.fpv.camera.more;

import android.content.Context;
import android.util.SparseArray;
import dji.midware.data.model.P3.DataCameraGetPushTauParam.LenFocusLength;
import dji.midware.data.model.P3.DataCameraGetPushTauParam.LenFps;
import dji.midware.data.model.P3.DataCameraGetPushTauParam.VideoResolution;
import dji.midware.usb.P3.DJIUsbAccessoryReceiver;
import dji.pilot.R;

public final class b {
    private static final String a = "Unknown";
    private static final String b = "Unknown";
    private static final SparseArray<SparseArray<String>> c = new SparseArray();
    private static final SparseArray<String> d = new SparseArray();
    private static final int e = 8;
    private static final int f = 7;
    private static final int g = 6;
    private static final int h = 5;
    private static final int i = 3;
    private static final int j = 2;
    private static final int k = 1;
    private static final int l = 0;
    private static final String[] m = new String[]{"640", "336"};
    private static final VideoResolution[] n = new VideoResolution[]{VideoResolution.VR_640, VideoResolution.VR_336};
    private static final String[] o = new String[]{"6.8mm", "7.5mm", "9mm", "13mm", "19mm"};
    private static final LenFocusLength[] p = new LenFocusLength[]{LenFocusLength.LFL_68, LenFocusLength.LFL_75, LenFocusLength.LFL_90, LenFocusLength.LFL_130, LenFocusLength.LFL_190};
    private static final String[] q = new String[]{"<9Hz", "30Hz"};
    private static final LenFps[] r = new LenFps[]{LenFps.FPS_LESS_9, LenFps.FPS_30};

    public static String a(int i) {
        if (d.size() == 0) {
            d.put(0, "Olympus");
            d.put(1, "SIGMA");
            d.put(2, "Panasonic");
            d.put(3, "Panasonic");
            d.put(5, "Tamron");
            d.put(6, "Kenko Tokina");
            d.put(7, "JK Imaging");
            d.put(8, DJIUsbAccessoryReceiver.c);
        }
        return (String) d.get(i, "Unknown");
    }

    public static String a(int i, int i2, int i3) {
        SparseArray sparseArray = (SparseArray) c.get(i2);
        if (sparseArray == null) {
            if (8 == i) {
                sparseArray = h();
            } else if (7 == i) {
                sparseArray = g();
            } else if (6 == i) {
                sparseArray = f();
            } else if (5 == i) {
                sparseArray = e();
            } else if (3 == i) {
                sparseArray = d();
            } else if (2 == i) {
                sparseArray = c();
            } else if (1 == i) {
                sparseArray = b();
            } else if (i == 0) {
                sparseArray = a();
            }
        }
        if (sparseArray != null) {
            return (String) sparseArray.get(a(i2, i3), "Unknown");
        }
        return "Unknown";
    }

    private static SparseArray<String> a() {
        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(a(4097, 0), "M.ZUIKO DIGITAL ED 14-42mm F3.5-5.6");
        sparseArray.put(a(4103, 0), "M.ZUIKO DIGITAL ED 12mm F2.0");
        sparseArray.put(a(4113, 0), "M.ZUIKO DIGITAL 45mm F1.8");
        sparseArray.put(a(4118, 0), "M.ZUIKO DIGITAL 17mm F1.8");
        sparseArray.put(a(4129, 0), "M.ZUIKO DIGITAL ED 14-42mm F3.5-5.6 EZ");
        sparseArray.put(a(4130, 0), "M.ZUIKO DIGITAL 25mm F1.8");
        sparseArray.put(a(4131, 0), "M.ZUIKO DIGITAL ED 7-14mm F2.8 PRO");
        sparseArray.put(a(4133, 0), "M.ZUIKO DIGITAL ED 8mm F1.8 Fisheye");
        c.put(0, sparseArray);
        return sparseArray;
    }

    private static SparseArray<String> b() {
        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(a(4099, 0), "SIGMA 30mm F2.8 DN");
        sparseArray.put(a(4100, 0), "SIGMA 19mm F2.8 DN");
        sparseArray.put(a(4101, 0), "SIGMA 60mm F2.8 DN");
        c.put(1, sparseArray);
        return sparseArray;
    }

    private static SparseArray<String> c() {
        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(a(4101, 4096), "LUMIX G 20mm F1.7");
        sparseArray.put(a(4101, 4352), "LUMIX G 20mm F1.7 II");
        sparseArray.put(a(4116, 4096), "LUMIX G VARIO PZ 14-42mm/F3.5-5.6");
        sparseArray.put(a(4131, 4096), "LUMIX G VARIO 35-100mm/F4.0-5.6");
        sparseArray.put(a(4132, 4096), "LUMIX G MACRO 30mm/F2.8");
        sparseArray.put(a(4133, 4096), "LUMIX G 42.5mm/F1.7");
        sparseArray.put(a(4134, 4096), "LUMIX G 25mm/F1.7");
        c.put(2, sparseArray);
        return sparseArray;
    }

    private static SparseArray<String> d() {
        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(a(2, 4096), "LEICA D SUMMILUX 25mm F1.4 ASPH");
        c.put(3, sparseArray);
        return sparseArray;
    }

    private static SparseArray<String> e() {
        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(a(4097, 1), "14-150mm F/3.5-5.8 Di IIII C 001");
        sparseArray.put(a(4098, 1), "14-150mm F/3.5-5.8 Di IIII C 001");
        c.put(5, sparseArray);
        return sparseArray;
    }

    private static SparseArray<String> f() {
        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(a(4097, 0), "Reflex 300mm F6.3 MF Macro");
        c.put(6, sparseArray);
        return sparseArray;
    }

    private static SparseArray<String> g() {
        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(a(4097, 4096), "PIXPRO SZ 12-45/F3.5-6.3 AF");
        sparseArray.put(a(4098, 4096), "PIXPRO SZ 42.5-160/F3.9-5.9 AF");
        c.put(7, sparseArray);
        return sparseArray;
    }

    private static SparseArray<String> h() {
        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(a(4097, 0), "DJI MFT 15mm F1.7 ASPH");
        c.put(8, sparseArray);
        return sparseArray;
    }

    private static int a(int i, int i2) {
        return (i << 16) & i2;
    }

    public static final String a(Context context, VideoResolution videoResolution, LenFocusLength lenFocusLength, LenFps lenFps, boolean z) {
        int length;
        int i;
        int i2 = 0;
        StringBuilder stringBuilder = new StringBuilder();
        if (VideoResolution.UNKNOWN != videoResolution) {
            length = n.length;
            i = 0;
            while (i < length) {
                if (n[i] == videoResolution) {
                    break;
                }
                i++;
            }
            i = -1;
            if (-1 != i) {
                stringBuilder.append(m[i]);
                i = -1;
            }
        } else {
            i = -1;
        }
        if (LenFocusLength.UNKNOWN != lenFocusLength) {
            int length2 = p.length;
            for (length = 0; length < length2; length++) {
                if (p[length] == lenFocusLength) {
                    i = length;
                    break;
                }
            }
            if (-1 != i) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(o[i]);
                i = -1;
            }
        }
        if (LenFps.UNKNOWN != lenFps) {
            length = r.length;
            while (i2 < length) {
                if (r[i2] == lenFps) {
                    i = i2;
                    break;
                }
                i2++;
            }
            if (-1 != i) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(q[i]);
            }
        }
        if (z) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(context.getString(R.string.tau_ffc_spot_thermometric));
        }
        return stringBuilder.toString();
    }
}
