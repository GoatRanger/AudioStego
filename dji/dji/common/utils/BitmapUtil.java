package dji.common.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import dji.log.DJILogHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class BitmapUtil {
    private static final String TAG = "bitmapUtil";

    public static Bitmap getLoacalBitmap(String str) {
        try {
            return BitmapFactory.decodeStream(new FileInputStream(str));
        } catch (FileNotFoundException e) {
            DJILogHelper.getInstance().LOGE(TAG, "DJIMethod : getLoacalBitmap (26)FileNotFoundException", true, false);
            return null;
        }
    }

    public static void scaleSrc(String str, float f, String str2) {
        Options options = new Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        if (decodeFile != null) {
            int width = decodeFile.getWidth();
            decodeFile = Bitmap.createScaledBitmap(decodeFile, (int) (((float) width) / f), (int) (((float) decodeFile.getHeight()) / f), true);
            DJILogHelper.getInstance().LOGD(TAG, "DJIMethod : scaleSrc (43)factor:" + f + "width:" + width, false, true);
            try {
                OutputStream fileOutputStream = new FileOutputStream(new File(str2));
                if (decodeFile.compress(CompressFormat.JPEG, 100, fileOutputStream)) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            } catch (Exception e) {
            }
        }
    }
}
