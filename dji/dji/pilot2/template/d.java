package dji.pilot2.template;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import java.util.List;

public abstract class d {
    public long getTotalDurations() {
        return 0;
    }

    public String getTemplateName() {
        return "";
    }

    public int getDurationAtOrder(int i) {
        return 0;
    }

    public int size() {
        return 0;
    }

    public List<Integer> getSegDurations() {
        return null;
    }

    public List<Integer> getStartTime() {
        return null;
    }

    public List<Integer> getEndTime() {
        return null;
    }

    public String getDescription() {
        return "";
    }

    public String getPreviewFileName() {
        return null;
    }

    public String getPreviewMusicName() {
        return null;
    }

    public Bitmap getThumbnailBitmap() {
        return null;
    }

    public String getConcatMusicName() {
        return null;
    }

    protected int parseFrameToTime(String str, int i) {
        String[] split = str.split(":");
        return (Integer.parseInt(split[1]) * 10) + (Integer.parseInt(split[0]) * 1000);
    }

    protected Bitmap getAdjustThumbnail(String str, Config config) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = config;
        options.inSampleSize = 1;
        return BitmapFactory.decodeFile(str, options);
    }

    public String getTag() {
        return null;
    }
}
