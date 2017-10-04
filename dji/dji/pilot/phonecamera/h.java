package dji.pilot.phonecamera;

import android.app.Activity;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.location.Location;
import android.media.MediaRecorder;
import android.util.Log;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class h {
    public static final String a = "continuous-picture";
    public static final String b = "recording-hint";
    public static final String c = "hdr";
    public static final String d = "true";
    public static final String e = "false";
    public static final int f = 5;
    private static final String g = "DJILPCameraUtil";
    private static final boolean h = false;
    private static final int i = 400000;
    private static final int j = 30000;
    private static final String k = "auto-exposure-lock-supported";
    private static final String l = "auto-whitebalance-lock-supported";
    private static final String m = "video-snapshot-supported";

    public static class a implements Comparator<Size> {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((Size) obj, (Size) obj2);
        }

        public int a(Size size, Size size2) {
            if (size.width == size2.width) {
                return 0;
            }
            if (size.width < size2.width) {
                return 1;
            }
            return -1;
        }
    }

    public static boolean a(String str, List<String> list) {
        return list != null && list.indexOf(str) >= 0;
    }

    public static boolean a(Parameters parameters) {
        return "true".equals(parameters.get(k));
    }

    public static boolean b(Parameters parameters) {
        return "true".equals(parameters.get(l));
    }

    public static boolean c(Parameters parameters) {
        return "true".equals(parameters.get(m));
    }

    public static boolean d(Parameters parameters) {
        List supportedSceneModes = parameters.getSupportedSceneModes();
        return supportedSceneModes != null && supportedSceneModes.contains(c);
    }

    public static boolean e(Parameters parameters) {
        return parameters.getMaxNumMeteringAreas() > 0;
    }

    public static boolean f(Parameters parameters) {
        return parameters.getMaxNumFocusAreas() > 0 && a("auto", parameters.getSupportedFocusModes());
    }

    public static int[] g(Parameters parameters) {
        return a(parameters.getSupportedPreviewFpsRange());
    }

    public static int[] a(List<int[]> list) {
        if (list.size() == 0) {
            Log.e(g, "No suppoted frame rates returned!");
            return null;
        }
        int[] iArr;
        int i;
        int i2 = i;
        for (int[] iArr2 : list) {
            int i3 = iArr2[0];
            if (iArr2[1] < 30000 || i3 > 30000 || i3 >= i2) {
                i = i2;
            } else {
                i = i3;
            }
            i2 = i;
        }
        int i4 = 0;
        int i5 = -1;
        for (i3 = 0; i3 < list.size(); i3++) {
            iArr2 = (int[]) list.get(i3);
            int i6 = iArr2[0];
            i = iArr2[1];
            if (i6 == i2 && r4 < i) {
                i4 = i;
                i5 = i3;
            }
        }
        if (i5 >= 0) {
            return (int[]) list.get(i5);
        }
        Log.e(g, "Can't find an appropiate frame rate range!");
        return null;
    }

    public static Size a(Activity activity, List<Size> list) {
        Point a = a(activity, new Point());
        Log.d(g, "getOptimalPreviewSize: DisplaySize " + a.x + "x" + a.y);
        Collections.sort(list, new a());
        double d = ((double) a.x) / ((double) a.y);
        if (d < 1.0d) {
            d = 1.0d / d;
        }
        Log.d(g, "getOptimalPreviewSize: targetRatio = " + d);
        return a(activity, (List) list, d);
    }

    public static Size a(Activity activity, List<Size> list, double d) {
        Point[] pointArr = new Point[list.size()];
        int i = 0;
        for (Size size : list) {
            int i2 = i + 1;
            pointArr[i] = new Point(size.width, size.height);
            i = i2;
        }
        int a = a(activity, pointArr, d);
        return a == -1 ? null : (Size) list.get(a);
    }

    public static int a(Activity activity, Point[] pointArr, double d) {
        if (pointArr == null) {
            return -1;
        }
        int i;
        int i2 = -1;
        double d2 = Double.MAX_VALUE;
        Point a = a(activity, new Point());
        Log.d(g, "getOptimalPreviewSize: DisplaySize " + a.x + "x" + a.y);
        int min = Math.min(a.x, a.y);
        Log.d(g, "getOptimalPreviewSize: targetHeight " + min);
        for (i = 0; i < pointArr.length; i++) {
            Point point = pointArr[i];
            if (Math.abs((((double) point.x) / ((double) point.y)) - d) <= 0.01d && ((double) Math.abs(point.y - min)) < r2) {
                d2 = (double) Math.abs(point.y - min);
                i2 = i;
            }
        }
        if (i2 != -1) {
            return i2;
        }
        Log.w(g, "No preview size match the aspect ratio");
        d2 = Double.MAX_VALUE;
        for (i = 0; i < pointArr.length; i++) {
            point = pointArr[i];
            if (((double) Math.abs(point.y - min)) < d2) {
                d2 = (double) Math.abs(point.y - min);
                i2 = i;
            }
        }
        return i2;
    }

    public static int a(int i, int i2) {
        if (i2 == -1) {
            return 0;
        }
        CameraInfo cameraInfo = d.a().e()[i];
        if (cameraInfo.facing == 1) {
            return ((cameraInfo.orientation - i2) + 360) % 360;
        }
        return (cameraInfo.orientation + i2) % 360;
    }

    public static int b(int i, int i2) {
        Object obj = 1;
        if (i2 != -1) {
            int abs = Math.abs(i - i2);
            if (Math.min(abs, 360 - abs) < 50) {
                obj = null;
            }
        }
        if (obj != null) {
            return (((i + 45) / 90) * 90) % 360;
        }
        return i2;
    }

    public static void a(Activity activity, List<Size> list, Parameters parameters) {
        Collections.sort(list, new a());
        Point a = a(activity, new Point());
        Double valueOf = Double.valueOf(((double) a.x) / ((double) a.y));
        if (valueOf.doubleValue() < 1.0d) {
            valueOf = Double.valueOf(1.0d / valueOf.doubleValue());
        }
        Size a2 = a((List) list, valueOf.doubleValue());
        parameters.setPictureSize(a2.width, a2.height);
    }

    private static Point a(Activity activity, Point point) {
        activity.getWindowManager().getDefaultDisplay().getRealSize(point);
        return point;
    }

    public static int a(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        Log.d(g, "getDisplayRotation: rotation = " + rotation);
        switch (rotation) {
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            default:
                return 0;
        }
    }

    public static int[] h(Parameters parameters) {
        List supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        if (supportedPreviewFpsRange == null || supportedPreviewFpsRange.size() <= 0) {
            return new int[0];
        }
        return (int[]) supportedPreviewFpsRange.get(supportedPreviewFpsRange.size() - 1);
    }

    public static Size a(List<Size> list, double d) {
        if (list == null) {
            return null;
        }
        for (Size size : list) {
            if (Math.abs((((double) size.width) / ((double) size.height)) - d) <= 0.001d) {
                break;
            }
        }
        Size size2 = null;
        if (size2 != null) {
            return size2;
        }
        Log.w(g, "No picture size match the aspect ratio");
        Size size3 = size2;
        for (Size size4 : list) {
            if (size3 != null && size4.width <= size3.width) {
                size2 = size3;
            }
            size3 = size2;
        }
        return size3;
    }

    public static Size b(List<Size> list, double d) {
        Size size = null;
        if (list != null) {
            Size size2;
            for (Size size22 : list) {
                if (Math.abs((((double) size22.width) / ((double) size22.height)) - d) <= 0.001d) {
                    if (size != null && size22.width <= size.width) {
                        size22 = size;
                    }
                    size = size22;
                }
            }
            if (size == null) {
                Log.w(g, "No picture size match the aspect ratio");
                for (Size size222 : list) {
                    if (size == null || size222.width > size.width) {
                        size = size222;
                    }
                }
            }
        }
        return size;
    }

    public static int c(int i, int i2) {
        int i3;
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i2, cameraInfo);
        if (cameraInfo.facing == 1) {
            Log.d(g, "getDisplayOrientation front-facing: info.orientation = " + cameraInfo.orientation + " degrees = " + i);
            i3 = (360 - ((cameraInfo.orientation + i) % 360)) % 360;
        } else {
            Log.d(g, "getDisplayOrientation back-facing : info.orientation = " + cameraInfo.orientation + " degrees = " + i);
            i3 = ((cameraInfo.orientation - i) + 360) % 360;
        }
        Log.d(g, "getDisplayOrientation: result = " + i3);
        return i3;
    }

    public static String a(long j) {
        return new SimpleDateFormat("'DJI'_yyyyMMdd_HHmmss").format(new Date(j));
    }

    public static void a(MediaRecorder mediaRecorder, Location location) {
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Object obj = (latitude == 0.0d && longitude == 0.0d) ? null : 1;
            if (obj != null) {
                Log.d(g, "Set gps location");
                mediaRecorder.setLocation((float) latitude, (float) longitude);
            }
        }
    }

    public static void a(Parameters parameters, Location location) {
        parameters.removeGpsData();
        parameters.setGpsTimestamp(System.currentTimeMillis() / 1000);
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Object obj = (latitude == 0.0d && longitude == 0.0d) ? null : 1;
            if (obj != null) {
                Log.d(g, "Set gps location");
                parameters.setGpsLatitude(latitude);
                parameters.setGpsLongitude(longitude);
                parameters.setGpsProcessingMethod(location.getProvider().toUpperCase());
                if (location.hasAltitude()) {
                    parameters.setGpsAltitude(location.getAltitude());
                } else {
                    parameters.setGpsAltitude(0.0d);
                }
                if (location.getTime() != 0) {
                    parameters.setGpsTimestamp(location.getTime() / 1000);
                }
            }
        }
    }

    public static String a(int i) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(a(stackTrace, i2)).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
        return stringBuffer.toString();
    }

    private static String a(StackTraceElement[] stackTraceElementArr, int i) {
        if (i + 4 >= stackTraceElementArr.length) {
            return "<bottom of call stack>";
        }
        StackTraceElement stackTraceElement = stackTraceElementArr[i + 4];
        return stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ":" + stackTraceElement.getLineNumber();
    }
}
