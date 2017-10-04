package dji.pilot2.bigfilm;

import android.graphics.Bitmap;

public class RenderBasicData {
    public Bitmap bitmap1;
    public Bitmap bitmap2;
    public Bitmap bitmap3;
    public String filterName;
    public int filterNum;
    public float param1;
    public float param10;
    public float param2;
    public float param3;
    public float param4;
    public float param5;
    public float param6;
    public float param7;
    public float param8;
    public float param9;

    public RenderBasicData() {
        this.filterName = null;
        this.bitmap1 = null;
    }

    public RenderBasicData(String str, int i, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3) {
        this.filterName = str;
        this.filterNum = i;
        this.param1 = f;
        this.param2 = f2;
        this.param3 = f3;
        this.param4 = f4;
        this.param5 = f5;
        this.param6 = f6;
        this.param7 = f7;
        this.param8 = f8;
        this.param9 = f9;
        this.param10 = f10;
        this.bitmap1 = bitmap;
        this.bitmap2 = bitmap2;
        this.bitmap3 = bitmap3;
    }

    public RenderBasicData(String str, int i, float f, float f2, float f3) {
        this.filterName = str;
        this.filterNum = i;
        this.param1 = f;
        this.param2 = f2;
        this.param3 = f3;
        this.param4 = 0.0f;
        this.param5 = 0.0f;
        this.param6 = 0.0f;
        this.param7 = 0.0f;
        this.param8 = 0.0f;
        this.param9 = 0.0f;
        this.param10 = 0.0f;
        this.bitmap1 = null;
        this.bitmap2 = null;
        this.bitmap3 = null;
    }

    public RenderBasicData(String str, int i, float f, Bitmap bitmap) {
        this.filterName = str;
        this.filterNum = i;
        this.param1 = f;
        this.param2 = 0.0f;
        this.param3 = 0.0f;
        this.param4 = 0.0f;
        this.param5 = 0.0f;
        this.param6 = 0.0f;
        this.param7 = 0.0f;
        this.param8 = 0.0f;
        this.param9 = 0.0f;
        this.param10 = 0.0f;
        this.bitmap1 = bitmap;
        this.bitmap2 = null;
        this.bitmap3 = null;
    }

    public RenderBasicData(String str, int i, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        this.filterName = str;
        this.filterNum = i;
        this.param1 = f;
        this.param2 = f2;
        this.param3 = f3;
        this.param4 = f4;
        this.param5 = f5;
        this.param6 = f6;
        this.param7 = f7;
        this.param8 = f8;
        this.param9 = f9;
        this.param10 = f10;
        this.bitmap1 = null;
        this.bitmap2 = null;
        this.bitmap3 = null;
    }
}
