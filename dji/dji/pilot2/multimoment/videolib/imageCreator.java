package dji.pilot2.multimoment.videolib;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.util.a;
import dji.pilot.R;
import dji.pilot.publics.c.d;
import dji.pilot2.mine.b.e;
import dji.pilot2.template.SingleTemplateJson.TextInfo;
import dji.pilot2.template.c;
import dji.pilot2.utils.n;
import dji.pilot2.utils.o;
import dji.pilot2.utils.p;
import dji.publics.b.a.b;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class imageCreator {
    static Context mContext = null;
    static String mDate;
    static String[] mDatesString;
    static String mDirector;
    static String mLocation;
    static String[] mLocationsString;
    public static Bitmap mLogoBitmap = null;
    static View mLogoView = null;
    public static Bitmap mWaterBitmap = null;
    static int mi = 0;

    public static void setLogoInfo(Context context, String[] strArr, String str, String str2, String str3, boolean z) {
        ProductType productType;
        ProductType productType2;
        Object obj;
        mContext = context;
        DJILogHelper.getInstance().LOGI("bob", "setLogoInfo enter");
        mLogoView = LayoutInflater.from(mContext).inflate(R.layout.v2_logo_image, null);
        ProductType productType3 = ProductType.OTHER;
        for (int i = 0; i < strArr.length; i++) {
            productType3 = p.e(strArr[i]);
            DJILogHelper.getInstance().LOGI("bob", "setLogoInfo " + strArr[i]);
            if (productType3 != null) {
                productType = productType3;
                break;
            }
        }
        productType = productType3;
        if (productType == null) {
            productType2 = ProductType.OTHER;
        } else {
            productType2 = productType;
        }
        ImageView imageView = (ImageView) mLogoView.findViewById(R.id.b0k);
        TextView textView = (TextView) mLogoView.findViewById(R.id.cre);
        TextView textView2 = (TextView) mLogoView.findViewById(R.id.crf);
        TextView textView3 = (TextView) mLogoView.findViewById(R.id.crg);
        TextView textView4 = (TextView) mLogoView.findViewById(R.id.cri);
        TextView textView5 = (TextView) mLogoView.findViewById(R.id.crj);
        TextView textView6 = (TextView) mLogoView.findViewById(R.id.crk);
        String str4 = "";
        String str5 = "";
        if (z) {
            imageView.setImageResource(R.drawable.eventlogo);
            obj = str5;
            Object obj2 = str4;
        } else {
            imageView.setImageDrawable(d.getInstance().g(productType2));
            str5 = d.getInstance().p(productType2);
            obj = d.getInstance().q(productType2);
            String str6 = str5;
        }
        if (obj2.equals("")) {
            textView2.setVisibility(8);
        } else {
            textView2.setVisibility(0);
            textView2.setText(obj2);
        }
        if (obj.equals("")) {
            textView3.setVisibility(8);
        } else {
            textView3.setVisibility(0);
            textView3.setText(obj);
        }
        if (obj2.equals("") && obj.equals("")) {
            textView.setVisibility(8);
        }
        if (str == null || str.equals("")) {
            textView6.setVisibility(8);
        } else if (e.getInstance().g().booleanValue()) {
            textView6.setVisibility(0);
            textView6.setText(str);
        } else {
            textView6.setVisibility(8);
        }
        if (str2 == null || str2.equals("")) {
            textView4.setVisibility(8);
        } else if (e.getInstance().f().booleanValue()) {
            textView4.setVisibility(0);
            textView4.setText(str2);
        } else {
            textView4.setVisibility(8);
        }
        if (str3 == null || str3.equals("")) {
            textView5.setVisibility(8);
        } else if (e.getInstance().e().booleanValue()) {
            textView5.setVisibility(0);
            textView5.setText(str3);
        } else {
            textView5.setVisibility(8);
        }
        mLogoBitmap = null;
        DJILogHelper.getInstance().LOGI("bob", "setLogoInfo leave");
    }

    public int[] getLogoBitmap(int i, int i2) {
        if (mContext == null || mLogoView == null) {
            DJILogHelper.getInstance().LOGE("bob", "getLogoBitmap mContext==null || mView ==null || mLogoBitmap == null");
            return null;
        }
        int i3 = i / 1;
        int i4 = i2 / 1;
        int height;
        int width;
        if (mLogoBitmap != null) {
            height = mLogoBitmap.getHeight();
            width = mLogoBitmap.getWidth();
        } else {
            height = 0;
            width = 0;
        }
        if (!(mLogoBitmap != null && i == r1 && i2 == r0)) {
            DJILogHelper.getInstance().LOGI("bob", "getLogoBitmap mLogoBitmap==null");
            if (!(mLogoBitmap == null || mLogoBitmap.isRecycled())) {
                mLogoBitmap.recycle();
                mLogoBitmap = null;
            }
            mLogoView.setDrawingCacheEnabled(false);
            mLogoView.measure(MeasureSpec.makeMeasureSpec(i3, 1073741824), MeasureSpec.makeMeasureSpec(i4, 1073741824));
            mLogoView.layout(0, 0, mLogoView.getMeasuredWidth(), mLogoView.getMeasuredHeight());
            mLogoView.buildDrawingCache();
            mLogoBitmap = mLogoView.getDrawingCache();
        }
        DJILogHelper.getInstance().LOGE("bob", "getLogoBitmap width =" + i + "height = " + i2 + "mLogoBitmap width = " + mLogoBitmap.getWidth() + "height = " + mLogoBitmap.getHeight());
        int[] iArr = new int[(i3 * i4)];
        mLogoBitmap.getPixels(iArr, 0, i3, 0, 0, i3, i4);
        return iArr;
    }

    public static void setWaterInfo(Context context, String str, String str2, String str3) {
        mContext = context;
        mLocation = str;
        mDate = str2;
        mDirector = str3;
    }

    public static void setWaterInfo(Context context, String[] strArr, String[] strArr2, String str) {
        mContext = context;
        if (strArr != null) {
            mLocationsString = new String[strArr.length];
            mLocationsString = (String[]) strArr.clone();
        } else {
            mLocationsString = null;
        }
        if (strArr2 != null) {
            mDatesString = new String[strArr2.length];
            mDatesString = (String[]) strArr2.clone();
        } else {
            mDatesString = null;
        }
        mDirector = str;
    }

    public int[] getWaterBitmap(int i, int i2, int i3) {
        DJILogHelper.getInstance().LOGI(b.m, "enter getWaterBitmap segIndex " + i3);
        if (mContext == null) {
            o.a("getWaterBitmap mContext==null || mView ==null || mLogoBitmap == null");
            return null;
        }
        float f;
        int i4 = i / 1;
        int i5 = i2 / 1;
        int i6 = i4 / 30;
        if (i6 > 20) {
            i6 = 20;
        }
        DJILogHelper.getInstance().LOGI(b.m, "width =" + i + "height = " + i2);
        int i7 = i6 * 2;
        Bitmap createBitmap = Bitmap.createBitmap(i4, i5, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.rgb(255, 255, 255));
        paint.setStyle(Style.FILL);
        paint.setTextSize((float) i6);
        float f2 = (float) (i5 - i6);
        o.a(" getWaterBitmap 1");
        if (!mDirector.equals("")) {
            canvas.drawText(mDirector, (float) ((i4 - i7) - ((int) paint.measureText(mDirector))), f2, paint);
        }
        o.a(" getWaterBitmap 2");
        if (mDatesString == null || mDatesString.length <= i3 || mDatesString[i3] == null || mDatesString[i3].equals("")) {
            f = f2;
        } else {
            canvas.drawText(mDatesString[i3], (float) i7, f2, paint);
            f = f2 - (((float) i6) * a.b.c);
        }
        o.a(" getWaterBitmap 3");
        if (!(mLocationsString == null || mLocationsString.length <= i3 || mLocationsString[i3] == null || mLocationsString[i3].equals(""))) {
            canvas.drawText(mLocationsString[i3], (float) i7, f, paint);
        }
        int[] iArr = new int[(i4 * i5)];
        createBitmap.getPixels(iArr, 0, i4, 0, 0, i4, i5);
        o.a("leave getWaterBitmap");
        return iArr;
    }

    public int[] getWaterBitmap(int i, int i2) {
        o.a("enter getWaterBitmap");
        if (mContext == null) {
            o.a("getWaterBitmap mContext==null || mView ==null || mLogoBitmap == null");
            return null;
        }
        int i3 = i / 1;
        int i4 = i2 / 1;
        int i5 = i3 / 20;
        int i6 = i5 * 2;
        Bitmap createBitmap = Bitmap.createBitmap(i3, i4, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.rgb(255, 255, 255));
        paint.setStyle(Style.FILL);
        paint.setTextSize((float) i5);
        float f = (float) (i4 - i5);
        if (!mDirector.equals("")) {
            canvas.drawText(mDirector, (float) ((i3 - i6) - ((int) paint.measureText(mDirector))), f, paint);
        }
        if (!mDate.equals("")) {
            canvas.drawText(mDate, (float) i6, f, paint);
            f -= ((float) i5) * a.b.c;
        }
        paint.setTextSize(50.0f);
        if (!mLocation.equals("")) {
            canvas.drawText(mLocation, (float) i6, f, paint);
        }
        o.a("width =" + i + "height = " + i2);
        int[] iArr = new int[(i3 * i4)];
        createBitmap.getPixels(iArr, 0, i3, 0, 0, i3, i4);
        o.a("leave getWaterBitmap");
        return iArr;
    }

    public int[] getTextBitmap(int i, int i2, String str, int i3) {
        DJILogHelper.getInstance().LOGI("bob", "enter getTextBitmap");
        if (str.equals("")) {
            o.a("leave getTextBitmap return null 1");
            return null;
        }
        int i4 = i / 1;
        int i5 = i2 / 1;
        c a = dji.pilot2.multimoment.template.c.getInstance().a(i3, mContext);
        if (a == null) {
            DJILogHelper.getInstance().LOGI("bob", "getTextBitmap getmSingleTemplateBeanById err templateID = " + i3);
            return null;
        }
        TextInfo a2 = a.a(str);
        int i6 = ((int) a2.size) * 2;
        String[] split = a2.color.split(",");
        int parseInt = Integer.parseInt(split[0]);
        int parseInt2 = Integer.parseInt(split[1]);
        int parseInt3 = Integer.parseInt(split[2]);
        o.a(" getTextBitmap  r =" + parseInt + " g =" + parseInt2 + ", b=" + parseInt3);
        Bitmap createBitmap = Bitmap.createBitmap(i4, i5, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.rgb(parseInt3, parseInt2, parseInt));
        paint.setStyle(Style.FILL);
        paint.setTextSize((float) i6);
        paint.setAntiAlias(true);
        float f = (float) (a2.centerX * ((double) i4));
        float f2 = (float) (a2.centerY * ((double) i5));
        o.a(" getTextBitmap  " + f + "  " + f2 + "fontSize = " + i6 + "textStr = ");
        canvas.drawText(str, f - ((float) (((int) paint.measureText(str)) / 2)), f2, paint);
        int[] iArr = new int[(i4 * i5)];
        createBitmap.getPixels(iArr, 0, i4, 0, 0, i4, i5);
        return iArr;
    }

    private Bitmap decodeResource(Resources resources, int i) {
        TypedValue typedValue = new TypedValue();
        resources.openRawResource(i, typedValue);
        Options options = new Options();
        options.inTargetDensity = typedValue.density;
        options.inPreferredConfig = Config.ARGB_8888;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    public int[] getFilterData(String str, int i) {
        DJILogHelper.getInstance().LOGI("videofilter", "enter getFilterData inputNum =" + i);
        if (mContext == null) {
            DJILogHelper.getInstance().LOGI("wbwb", "mContext = null");
            return null;
        }
        int i2;
        if (str.equalsIgnoreCase("AESTHETICISM")) {
            i2 = i == 3 ? R.drawable.v2_photo_editor_filter_overlay_map : R.drawable.v2_photo_editor_filter_blackboard1024;
            if (i == 4) {
                i2 = R.drawable.v2_photo_editor_filter_amaro_map;
            }
        } else {
            i2 = R.drawable.v2_photo_editor_filter_blackboard1024;
        }
        if (str.equalsIgnoreCase("GORGEOUS")) {
            i2 = R.drawable.v2_photo_editor_filter_hudson_background;
            if (i == 3) {
                i2 = R.drawable.v2_photo_editor_filter_overlay_map;
            }
            if (i == 4) {
                i2 = R.drawable.v2_photo_editor_filter_hudson_map;
            }
        }
        if (str.equalsIgnoreCase("SOFT")) {
            i2 = i == 3 ? R.drawable.v2_photo_editor_filter_overlay_map : R.drawable.v2_photo_editor_filter_blackboard1024;
            if (i == 4) {
                i2 = R.drawable.v2_photo_editor_filter_rise_map;
            }
        }
        if (str.equalsIgnoreCase("FIERCE")) {
            i2 = R.drawable.v2_photo_editor_filter_sierra_vignette;
            if (i == 3) {
                i2 = R.drawable.v2_photo_editor_filter_overlay_map;
            }
            if (i == 4) {
                i2 = R.drawable.v2_photo_editor_filter_sierra_map;
            }
        }
        if (str.equalsIgnoreCase("FRESH")) {
            i2 = R.drawable.v2_photo_editor_filter_valencia_map;
            if (i == 3) {
                i2 = R.drawable.v2_photo_editor_filter_valencia_gradient_map;
            }
        }
        if (str.equalsIgnoreCase("COOL")) {
            i2 = R.drawable.v2_photo_editor_filter_walden_map;
            if (i == 3) {
                i2 = R.drawable.v2_photo_editor_filter_vignette_map;
            }
        }
        if (str.equalsIgnoreCase("REMINISCENT")) {
            i2 = R.drawable.v2_photo_editor_filter_earlybird_curves;
            if (i == 3) {
                i2 = R.drawable.v2_photo_editor_filter_earlybird_overlay_map;
            }
            if (i == 4) {
                i2 = R.drawable.v2_photo_editor_filter_vignette_map;
            }
            if (i == 5) {
                i2 = R.drawable.v2_photo_editor_filter_earlybird_blowout;
            }
            if (i == 6) {
                i2 = R.drawable.v2_photo_editor_filter_earlybird_map;
            }
        }
        if (str.equalsIgnoreCase("DARKSIDE")) {
            i2 = R.drawable.v2_photo_editor_filter_edge_burn;
            if (i == 3) {
                i2 = R.drawable.v2_photo_editor_filter_hefe_map;
            }
            if (i == 4) {
                i2 = R.drawable.v2_photo_editor_filter_hefe_gradient_map;
            }
            if (i == 5) {
                i2 = R.drawable.v2_photo_editor_filter_hefe_soft_light;
            }
            if (i == 6) {
                i2 = R.drawable.v2_photo_editor_filter_hefe_metal;
            }
        }
        if (str.equalsIgnoreCase("BLACKWHITE")) {
            i2 = R.drawable.v2_photo_editor_filter_inkwell_map;
        }
        if (str.equalsIgnoreCase("RETRO")) {
            i2 = R.drawable.v2_photo_editor_filter_nashville_map;
        }
        Bitmap decodeResource = BitmapFactory.decodeResource(mContext.getResources(), i2);
        Object obj = new int[(decodeResource.getWidth() * decodeResource.getHeight())];
        decodeResource.getPixels(obj, 0, decodeResource.getWidth(), 0, 0, decodeResource.getWidth(), decodeResource.getHeight());
        Object obj2 = new int[((decodeResource.getWidth() * decodeResource.getHeight()) + 2)];
        obj2[0] = decodeResource.getWidth();
        obj2[1] = decodeResource.getHeight();
        System.arraycopy(obj, 0, obj2, 2, obj.length);
        DJILogHelper.getInstance().LOGI("videofilter", "leave getFilterData" + decodeResource.getWidth() + "," + decodeResource.getHeight());
        return obj2;
    }

    protected static void saveBitmapTofile(Bitmap bitmap) throws FileNotFoundException {
        mi++;
        String str = "";
        try {
            OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File("" + n.a(mContext).getPath() + dji.pilot.usercenter.protocol.d.t + mi + "wbbb.bmp")));
            bitmap.compress(CompressFormat.JPEG, 80, bufferedOutputStream);
            try {
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (i == width && i2 == height) {
            return bitmap;
        }
        float f;
        float f2;
        float f3;
        if ((((float) height) * 1.0f) / ((float) width) > (((float) i2) * 1.0f) / ((float) i)) {
            f = (((float) i) * 1.0f) / ((float) width);
            f2 = (((float) height) - (((((float) i2) * 1.0f) / ((float) i)) * ((float) width))) / 2.0f;
            f3 = 0.0f;
        } else {
            f = (((float) i2) * 1.0f) / ((float) height);
            f2 = 0.0f;
            f3 = (((float) width) - (((((float) i) * 1.0f) / ((float) i2)) * ((float) height))) / 2.0f;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, Math.round(f3), Math.round(f2), Math.round(((float) width) - (f3 * 2.0f)), Math.round(((float) height) - (f2 * 2.0f)), matrix, true);
            if (!(bitmap == null || bitmap.isRecycled())) {
                bitmap.recycle();
                System.gc();
            }
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
