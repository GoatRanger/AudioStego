package com.nokia.maps;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.here.android.mpa.common.CopyrightLogoPosition;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.here.android.mpa.common.ViewRect;
import com.here.android.mpa.streetlevel.StreetLevel;
import com.here.android.mpa.streetlevel.StreetLevelGesture;
import com.here.android.mpa.streetlevel.StreetLevelModel;
import com.nokia.maps.annotation.Internal;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

@Internal
public class el extends ViewGroup {
    AtomicBoolean a = new AtomicBoolean(false);
    private View b;
    private db c;
    private AttributeSet d = null;
    private boolean e = false;
    private Context f;
    private boolean g = false;
    private BitmapDrawable h = null;
    private ImageView i;
    private Rect j = null;
    private CopyrightLogoPosition k = CopyrightLogoPosition.BOTTOM_CENTER;
    private int l = -1;
    private int m = -1;
    private int n = 0;
    private int o = 0;
    private int p = 0;
    private boolean q = false;
    private RelativeLayout r = null;
    private TextView s = null;
    private boolean t = false;
    private TextView u = null;
    private TextView v = null;

    class a implements Runnable {
        final /* synthetic */ el a;

        a(el elVar) {
            this.a = elVar;
        }

        public void run() {
            this.a.q = true;
            this.a.requestLayout();
        }
    }

    public el(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = context.getApplicationContext();
        this.d = attributeSet;
    }

    private void d() {
        StreetLevelModel streetLevelModel = getStreetLevelModel();
        if (streetLevelModel != null) {
            StreetLevel streetLevel = streetLevelModel.getStreetLevel();
            if (streetLevel != null) {
                GeoCoordinate position = streetLevel.getPosition();
                if (position != null) {
                    long id = PanoramaImpl.a(streetLevel).getId();
                    if (id > 0) {
                        StringBuilder stringBuilder;
                        ApplicationContext b = ApplicationContext.b();
                        String valueOf = String.valueOf(id);
                        double heading = (double) (((90.0f - streetLevelModel.getHeading()) + 360.0f) % 360.0f);
                        double pitch = (double) (90.0f - streetLevelModel.getPitch());
                        PointF pointF = new PointF(((float) streetLevelModel.getWidth()) / 2.0f, 0.0f);
                        PointF pointF2 = new PointF(((float) streetLevelModel.getWidth()) / 2.0f, (float) streetLevelModel.getHeight());
                        PointF pointF3 = new PointF(0.0f, ((float) streetLevelModel.getHeight()) / 2.0f);
                        PointF pointF4 = new PointF((float) streetLevelModel.getWidth(), ((float) streetLevelModel.getHeight()) / 2.0f);
                        ArrayList arrayList = new ArrayList();
                        double abs = (double) Math.abs(((Float) streetLevelModel.toCameraOrientation(pointF).get(1)).floatValue() - ((Float) streetLevelModel.toCameraOrientation(pointF2).get(1)).floatValue());
                        double abs2 = (double) Math.abs(((Float) streetLevelModel.toCameraOrientation(pointF4).get(0)).floatValue() - ((Float) streetLevelModel.toCameraOrientation(pointF3).get(0)).floatValue());
                        File file = new File(MapSettings.g());
                        Object obj = null;
                        if (file.exists()) {
                            stringBuilder = new StringBuilder(MapsEngine.p());
                            obj = 1;
                        } else {
                            stringBuilder = new StringBuilder(MapsEngine.o());
                        }
                        stringBuilder.append("panoramaid").append("=").append(valueOf).append(com.alipay.sdk.h.a.b);
                        stringBuilder.append("latitude").append("=").append(String.valueOf(position.getLatitude())).append(com.alipay.sdk.h.a.b);
                        stringBuilder.append("longitude").append("=").append(String.valueOf(position.getLongitude())).append(com.alipay.sdk.h.a.b);
                        stringBuilder.append("azimuth").append("=").append(String.valueOf(heading)).append(com.alipay.sdk.h.a.b);
                        stringBuilder.append("polar").append("=").append(String.valueOf(pitch)).append(com.alipay.sdk.h.a.b);
                        stringBuilder.append("width").append("=").append(String.valueOf(abs2)).append(com.alipay.sdk.h.a.b);
                        stringBuilder.append("height").append("=").append(String.valueOf(abs)).append(com.alipay.sdk.h.a.b);
                        stringBuilder.append("app_id").append("=").append(b.getAppId()).append(com.alipay.sdk.h.a.b);
                        stringBuilder.append("app_code").append("=").append(b.getAppToken()).append(com.alipay.sdk.h.a.b);
                        stringBuilder.append("lang").append("=").append(Locale.getDefault().getLanguage().toUpperCase());
                        if (obj != null) {
                            a(file, stringBuilder.toString());
                        }
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
                        intent.addFlags(268435456);
                        getContext().startActivity(intent);
                    }
                }
            }
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            if (this.i != null) {
                this.i.setVisibility(4);
            }
            if (!this.a.get()) {
                this.j = null;
            }
        }
        if (this.b != null) {
            this.b.layout(0, 0, i3 - i, i4 - i2);
            a(z, i, i2, i3, i4);
            b(z, i, i2, i3, i4);
        }
        if (this.v != null) {
            this.v.layout(0, 0, i3 - i, i4 - i2);
        }
        if (z && this.i != null && this.i.getVisibility() != 0) {
            this.i.setVisibility(0);
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        if (this.i != null) {
            bundle.putInt("CopyrightLogoPosition", this.k.ordinal());
        }
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            if (this.i != null) {
                this.k = CopyrightLogoPosition.values()[bundle.getInt("CopyrightLogoPosition")];
                return;
            }
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected void onMeasure(int i, int i2) {
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            getChildAt(i3).measure(i, i2);
        }
        super.onMeasure(i, i2);
    }

    public void setStreetLevelModel(StreetLevelModel streetLevelModel) {
        try {
            if (this.b == null && streetLevelModel != null) {
                a(this.f, this.d);
            }
            bj.e(getClass().getName(), "StreetLevelView::setPanorama attempt", new Object[0]);
            this.c.setPanorama(streetLevelModel);
            this.e = true;
            if (!this.g) {
                b();
                this.g = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.e = false;
        }
    }

    public StreetLevelModel getStreetLevelModel() {
        return this.c.getPanorama();
    }

    private void a(Context context, AttributeSet attributeSet) {
        String str = (String) getTag();
        if (str == null) {
            str = "";
        }
        if (this.r == null) {
            this.r = new RelativeLayout(context);
        }
        if (this.b == null) {
            Object obj;
            if (VERSION.SDK_INT < 14) {
                obj = null;
            } else if (str.compareTo("SurfaceView") == 0) {
                obj = null;
            } else {
                int i = 1;
            }
            View czVar;
            if (obj == 1) {
                czVar = new cz(context, attributeSet);
                this.c = czVar.getProxy();
                this.b = czVar;
            } else {
                czVar = new ct(context, attributeSet);
                this.c = czVar.getProxy();
                this.b = czVar;
            }
            this.b.setId(ce.a());
            addView(this.b, -1);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(12);
            layoutParams.addRule(11);
            this.s = a(context);
            this.r.addView(this.s, layoutParams);
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(12);
            layoutParams.addRule(9);
            this.u = b(context);
            this.r.addView(this.u, layoutParams);
            addView(this.r, -1);
            c(context);
            e();
        }
    }

    private void e() {
        this.v = new fb(this.f).a();
        if (this.v != null) {
            addView(this.v);
        }
    }

    private TextView a(Context context) {
        TextView textView = new TextView(context);
        textView.setText(getPrivacyText());
        textView.setTextColor(-1);
        textView.setTextSize(10.0f);
        textView.setPaintFlags(8);
        textView.setPadding(0, 0, 25, 25);
        textView.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        this.o = textView.getMeasuredHeight();
        this.n = textView.getMeasuredWidth();
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ el a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.d();
            }
        });
        return textView;
    }

    private TextView b(Context context) {
        TextView textView = new TextView(context);
        textView.setText(" © 2015 HERE ");
        textView.setTextColor(-1);
        textView.setTextSize(10.0f);
        textView.setPadding(0, 0, 25, 25);
        textView.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        this.p = textView.getMeasuredHeight();
        return textView;
    }

    private String getPrivacyText() {
        String language = Locale.getDefault().getLanguage();
        String str = "";
        if (language.compareToIgnoreCase(Locale.FRENCH.getLanguage()) == 0) {
            return " Signaler une image ";
        }
        if (language.compareToIgnoreCase(Locale.GERMAN.getLanguage()) == 0) {
            return " Bild melden ";
        }
        return " Report Image ";
    }

    private Boolean f() {
        Boolean valueOf = Boolean.valueOf(false);
        if (this.b == null || !this.e) {
            return valueOf;
        }
        return Boolean.valueOf(true);
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        if (f().booleanValue()) {
            this.b.setOnTouchListener(onTouchListener);
        }
    }

    public void a() {
        if (f().booleanValue()) {
            this.c.onPause();
        }
    }

    public void b() {
        if (f().booleanValue()) {
            this.c.onResume();
            this.g = true;
        }
    }

    public void c() {
        if (this.c != null) {
            this.c.setPanorama(null);
        }
        setOnTouchListener(null);
        this.b = null;
        this.r = null;
        this.c = null;
    }

    public StreetLevelGesture getStreetLevelGesture() {
        if (f().booleanValue()) {
            return this.c.getStreetLevelGesture();
        }
        return null;
    }

    public void setBlankStreetLevelImageVisible(boolean z) {
        if (f().booleanValue()) {
            this.c.setBlankStreetLevelImageVisible(z);
        }
    }

    public void a(OnScreenCaptureListener onScreenCaptureListener) {
        if (this.c != null) {
            this.c.a(onScreenCaptureListener);
            return;
        }
        throw new RuntimeException("View is not initialized");
    }

    public void setCopyrightMargin(int i) {
        if (this.m != i) {
            int i2 = this.m;
            if (i >= this.l || this.l <= 0) {
                this.m = i;
            } else {
                this.m = this.l;
            }
            if (this.m != i2) {
                i();
            }
        }
    }

    public int getCopyrightMargin() {
        return this.m;
    }

    public Rect getCopyrightBoundaryRect() {
        return this.j;
    }

    public void setCopyrightBoundaryRect(Rect rect) {
        boolean z = false;
        if (rect != null) {
            if (!(this.j != null && rect.left == this.j.left && rect.right == this.j.right && rect.top == this.j.top && rect.bottom == this.j.bottom)) {
                if (rect.isEmpty() || rect.right <= 0 || rect.bottom <= 0 || !new ViewRect(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top).isValid()) {
                    throw new IllegalArgumentException("Input parameter rect is invalid.");
                }
                this.j = new Rect(rect);
                z = true;
            }
        } else if (this.j != null) {
            this.j = null;
            z = true;
        }
        if (z) {
            this.a.set(true);
            i();
        }
    }

    public CopyrightLogoPosition getCopyrightLogoPosition() {
        return this.k;
    }

    public int getCopyrightLogoWidth() {
        return this.i == null ? -1 : this.i.getWidth();
    }

    public int getCopyrightLogoHeight() {
        return this.i == null ? -1 : this.i.getHeight();
    }

    private void c(Context context) {
        this.i = new ImageView(context);
        this.i.setVisibility(0);
        this.i.setScaleType(ScaleType.FIT_XY);
        g();
        h();
        addView(this.i, -2);
        bringChildToFront(this.i);
    }

    private void g() {
        if (this.h == null) {
            byte[] a = ResourceManager.a(this.f, bl.a("satellite", this.f.getResources().getDisplayMetrics().densityDpi));
            if (a != null && a.length > 0) {
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(a, 0, a.length);
                if (decodeByteArray != null) {
                    this.h = new BitmapDrawable(this.f.getResources(), decodeByteArray);
                }
            }
        }
    }

    private void h() {
        Object obj = Looper.myLooper() == Looper.getMainLooper() ? 1 : null;
        if (this.h != null) {
            this.l = this.h.getIntrinsicWidth() / 3;
            if (obj != null) {
                this.i.setImageDrawable(this.h);
            } else {
                post(new Runnable(this) {
                    final /* synthetic */ el a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.i.setImageDrawable(this.a.h);
                    }
                });
            }
        } else if (obj != null) {
            this.i.setImageDrawable(null);
        } else {
            post(new Runnable(this) {
                final /* synthetic */ el a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.i.setImageDrawable(null);
                }
            });
        }
    }

    private void i() {
        if (this.b != null && this.h != null && this.i != null) {
            post(new a(this));
        }
    }

    private void a(boolean z, int i, int i2, int i3, int i4) {
        if (this.r != null) {
            double d = ((double) (this.n + this.o)) / ((double) (i3 - i));
            if (((double) Math.max(this.o, this.o)) / ((double) (i4 - i2)) > 0.4d || d > 2.0d * 0.4d) {
                this.r.layout(-1, -1, -1, -1);
                this.t = false;
                return;
            }
            this.r.layout(0, 0, i3 - i, i4 - i2);
            this.t = true;
        }
    }

    private void b(boolean z, int i, int i2, int i3, int i4) {
        if (this.b != null && this.i != null && this.i.getDrawable() != null) {
            ViewRect viewRect;
            int i5;
            if (getCopyrightMargin() < 0) {
                setCopyrightMargin(this.l);
            }
            Object obj = null;
            if (z) {
                viewRect = new ViewRect(i, i2, i3 - i, i4 - i2);
                this.q = false;
            } else if (this.q) {
                this.q = false;
                viewRect = a(i, i2, i3, i4);
                if (viewRect != null) {
                    obj = 1;
                } else {
                    viewRect = new ViewRect(i, i2, i3 - i, i4 - i2);
                    if (!viewRect.isValid()) {
                        return;
                    }
                }
            } else {
                return;
            }
            int width = viewRect.getWidth();
            int height = viewRect.getHeight();
            int intrinsicHeight = this.i.getDrawable().getIntrinsicHeight();
            int intrinsicWidth = this.i.getDrawable().getIntrinsicWidth();
            if (obj == null) {
                double d = ((double) intrinsicWidth) / ((double) width);
                if (((double) intrinsicHeight) / ((double) height) > 0.4d || d > 0.4d) {
                    this.i.layout(-1, -1, -1, -1);
                    return;
                }
            }
            int b = b(width, height, intrinsicWidth, intrinsicHeight);
            int y = (viewRect.getY() + b) - i2;
            int x = viewRect.getX() + b;
            int y2 = ((viewRect.getY() + height) - b) - i2;
            int x2 = (viewRect.getX() + width) - b;
            switch (this.k) {
                case TOP_LEFT:
                    i5 = x + intrinsicWidth;
                    y2 = y + intrinsicHeight;
                    x2 = x;
                    x = y;
                    break;
                case TOP_RIGHT:
                    x = y;
                    i5 = x2;
                    x2 -= intrinsicWidth;
                    y2 = y + intrinsicHeight;
                    break;
                case TOP_CENTER:
                    y2 = y + intrinsicHeight;
                    x2 = viewRect.getX() + ((width - intrinsicWidth) / 2);
                    i5 = x2 + intrinsicWidth;
                    x = y;
                    break;
                default:
                    x2 = x + intrinsicWidth;
                    if (obj == null && this.t) {
                        i5 = y2 - this.p;
                    } else {
                        i5 = y2;
                    }
                    int i6 = x2;
                    x2 = x;
                    x = i5 - intrinsicHeight;
                    y2 = i5;
                    i5 = i6;
                    break;
            }
            if (x < viewRect.getY() + b) {
                x = viewRect.getY() + b;
                y2 = x + intrinsicHeight;
            }
            if (y2 > (viewRect.getY() + height) - b) {
                y2 = (viewRect.getY() + height) - b;
                x = y2 - intrinsicHeight;
            }
            if (x2 < viewRect.getX() + b) {
                x2 = viewRect.getX() + b;
                i5 = x2 + intrinsicWidth;
            }
            if (i5 > (viewRect.getX() + width) - b) {
                i5 = (viewRect.getX() + width) - b;
                x2 = i5 - intrinsicWidth;
            }
            this.i.layout(x2, x, i5, y2);
            bringChildToFront(this.i);
        }
    }

    private ViewRect a(int i, int i2, int i3, int i4) {
        if (this.j == null || this.i == null || this.i.getDrawable() == null || this.i.getDrawable().getIntrinsicHeight() <= 0 || this.i.getDrawable().getIntrinsicWidth() <= 0) {
            return null;
        }
        Rect rect = new Rect(i, i2, i3, i4);
        int intrinsicWidth = this.i.getDrawable().getIntrinsicWidth();
        int intrinsicHeight = this.i.getDrawable().getIntrinsicHeight();
        int b;
        if (rect.contains(this.j)) {
            b = b(this.j.width(), this.j.height(), intrinsicWidth, intrinsicHeight);
            if (this.j.width() < intrinsicWidth + (b * 2) || this.j.height() < (b * 2) + intrinsicHeight) {
                return null;
            }
            return new ViewRect(this.j.left, this.j.top, this.j.right - this.j.left, this.j.bottom - this.j.top);
        } else if (!rect.intersect(this.j)) {
            return null;
        } else {
            Rect rect2 = new Rect(Math.max(rect.left, this.j.left), Math.max(rect.top, this.j.top), Math.min(rect.right, this.j.right), Math.min(rect.bottom, this.j.bottom));
            b = b(rect2.width(), rect2.height(), intrinsicWidth, intrinsicHeight);
            if (rect2.width() < intrinsicWidth + (b * 2) || rect2.height() < (b * 2) + intrinsicHeight) {
                return null;
            }
            return new ViewRect(rect2.left, rect2.top, rect2.right - rect2.left, rect2.bottom - rect2.top);
        }
    }

    private int b(int i, int i2, int i3, int i4) {
        int i5 = (i - i3 > i2 - i4 ? i2 - i4 : i - i3) >> 1;
        int i6 = this.m;
        if (i5 <= 0) {
            return i6;
        }
        if (this.m > i5) {
            return i5;
        }
        return this.m;
    }

    private void a(File file, String str) {
        BufferedWriter bufferedWriter;
        Throwable th;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                IOException e2;
                e2.printStackTrace();
            }
        }
        BufferedWriter bufferedWriter2 = null;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), Charset.forName("UTF-8")));
            try {
                bufferedWriter.append(str);
                bufferedWriter.newLine();
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e22 = e3;
                try {
                    e22.printStackTrace();
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedWriter2 = bufferedWriter;
                    if (bufferedWriter2 != null) {
                        try {
                            bufferedWriter2.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e222 = e5;
            bufferedWriter = null;
            e222.printStackTrace();
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (Throwable th3) {
            th = th3;
            if (bufferedWriter2 != null) {
                bufferedWriter2.close();
            }
            throw th;
        }
    }
}
