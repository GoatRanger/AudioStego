package dji.pilot2.videolib;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import dji.log.DJILogHelper;
import dji.pilot2.bigfilm.RenderDatas;
import dji.pilot2.template.DealedFilterConf;
import java.util.HashMap;

public class VideoLibWrapper {
    protected static final boolean DEBUG = false;
    public static String TAG = "VideoLibWrapper";
    static String[] ffmpeglibStrings = new String[]{"videoLib"};
    static String[] ffmpeglibStrings1 = new String[]{"videoLib"};
    private int mNativeContext;

    private static native byte[] _getFileFrameAtTime(String str, long j, int i);

    public static native long getVideoDuration(String str);

    public static native HashMap<String, String> nativeGetMetadata(String str);

    public static native void nativeSetSingleTemplateConf(int i, DealedFilterConf dealedFilterConf);

    public native void _nativeInit();

    public native void _nativeUnInit();

    public native int nativeFilterApply(int[] iArr, int[] iArr2, long j, long j2, boolean z, RenderDatas renderDatas);

    public native int nativeFilterDestroy();

    public native int nativeFilterSetOutputSize(int i, int i2);

    public native void nativeSetBright(double[] dArr);

    public native void nativeSetContrast(double[] dArr);

    public native void nativeSetFilters(int[] iArr, double[] dArr);

    public native void nativeSetSaturation(double[] dArr);

    public native void nativeSetSelectSingleTemplateID(int i);

    public native void nativeSetWaterFresh(int i);

    public native void setMultiVideoFilterNum(int i);

    static {
        int i = 0;
        while (i < ffmpeglibStrings.length) {
            System.loadLibrary(ffmpeglibStrings[i]);
            i++;
        }
    }

    public static Bitmap getFrameAtTime(String str, long j, int i) {
        Bitmap bitmap = null;
        Options options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        options.inSampleSize = 4;
        options.inDither = false;
        byte[] _getFileFrameAtTime = _getFileFrameAtTime(str, j, i);
        if (_getFileFrameAtTime != null) {
            bitmap = BitmapFactory.decodeByteArray(_getFileFrameAtTime, 0, _getFileFrameAtTime.length, options);
        }
        DJILogHelper.getInstance().LOGI("VVV", "bitmapOptionsCache.outWidth = " + options.outWidth + "bitmapOptionsCache.outHeight = " + options.outHeight);
        return bitmap;
    }
}
