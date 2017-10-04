package com.nokia.maps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.mapping.Map.InfoBubbleAdapter;
import com.here.android.mpa.mapping.MapMarker;
import dji.gs.e.b;
import java.util.ArrayList;
import java.util.List;

class bd {
    private static bd a = null;
    private static Object j = new Object();
    private MapMarker b = null;
    private View c = null;
    private MapImpl d = null;
    private final String e;
    private final String f;
    private boolean g = false;
    private boolean h = false;
    private MapMarker i = null;

    private static class a extends ShapeDrawable {
        private final Paint a = new Paint(getPaint());
        private final Paint b;

        public a(RectShape rectShape) {
            super(rectShape);
            this.a.setColor(-1);
            this.b = new Paint(getPaint());
            this.b.setStyle(Style.STROKE);
            this.b.setStrokeWidth(1.0f);
            this.b.setColor(-16777216);
        }

        protected void onDraw(Shape shape, Canvas canvas, Paint paint) {
            shape.resize((float) canvas.getClipBounds().right, (float) canvas.getClipBounds().bottom);
            shape.draw(canvas, this.a);
            shape.draw(canvas, this.b);
        }
    }

    public static int a(Context context, MapImpl mapImpl, MapMarker mapMarker, GeoCoordinate geoCoordinate, String str, String str2) {
        int hashCode;
        synchronized (j) {
            d();
            a = new bd(context, mapImpl, mapMarker, geoCoordinate, str, str2);
            hashCode = a.b == null ? 0 : a.b.hashCode();
        }
        return hashCode;
    }

    public static void a() {
        synchronized (j) {
            if (a != null) {
                a.h();
                a = null;
            }
        }
    }

    static MapMarker b() {
        MapMarker mapMarker = null;
        synchronized (j) {
            if (a != null) {
                mapMarker = a.b;
            }
        }
        return mapMarker;
    }

    private static void d() {
        synchronized (j) {
            if (a != null) {
                a.e();
            }
        }
    }

    private void e() {
        synchronized (j) {
            if (this.i != null) {
                this.i.hideInfoBubble();
            } else {
                a();
            }
        }
    }

    static void c() {
        synchronized (j) {
            if (a != null) {
                a.f();
            }
        }
    }

    private void f() {
        if (this.i != null) {
            int intValue;
            int intValue2;
            MapMarker mapMarker = this.b;
            Image icon = mapMarker.getIcon();
            PointF anchorPoint = this.i.getAnchorPoint();
            if (anchorPoint == null) {
                intValue = Long.valueOf(mapMarker.getIcon().getWidth()).intValue() / 2;
                intValue2 = Long.valueOf(this.i.getIcon().getHeight()).intValue() / 2;
            } else {
                intValue = ((Long.valueOf(mapMarker.getIcon().getWidth()).intValue() / 2) + ((int) anchorPoint.x)) - (Long.valueOf(this.i.getIcon().getWidth()).intValue() / 2);
                intValue2 = (int) anchorPoint.y;
            }
            this.b.setAnchorPoint(new PointF((float) (intValue + 1), (float) (icon.getHeight() + ((long) intValue2))));
        }
    }

    static void a(GeoCoordinate geoCoordinate) {
        synchronized (j) {
            if (a != null) {
                a.b.setCoordinate(geoCoordinate);
            }
        }
    }

    private bd(Context context, MapImpl mapImpl, MapMarker mapMarker, GeoCoordinate geoCoordinate, String str, String str2) {
        this.e = str;
        this.f = str2;
        this.d = mapImpl;
        this.i = mapMarker;
        if (this.d == null) {
            return;
        }
        if (this.e != null || this.d.z() != null) {
            b(context, mapMarker);
            if (this.c != null) {
                b(geoCoordinate);
            }
        }
    }

    @SuppressLint({"NewApi"})
    private View a(Context context, MapMarker mapMarker) {
        InfoBubbleAdapter z = this.d.z();
        View infoBubble = z == null ? null : z.getInfoBubble(mapMarker);
        if (infoBubble == null) {
            View linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(new LayoutParams(-2, -2));
            linearLayout.setPadding(4, 4, 4, 4);
            if (VERSION.SDK_INT >= 16) {
                linearLayout.setBackground(g());
            } else {
                linearLayout.setBackgroundDrawable(g());
            }
            linearLayout.setOrientation(1);
            View infoBubbleContents = z == null ? null : z.getInfoBubbleContents(mapMarker);
            if (infoBubbleContents == null) {
                infoBubble = new TextView(context);
                infoBubble.setPadding(4, 4, 4, 4);
                infoBubble.setTextColor(-16777216);
                infoBubble.setTextSize(b.a);
                infoBubble.setTypeface(null, 1);
                infoBubble.setId(1);
                linearLayout.addView(infoBubble, new LayoutParams(-1, -2));
                if (!TextUtils.isEmpty(this.f)) {
                    infoBubble = new TextView(context);
                    infoBubble.setPadding(4, 4, 4, 4);
                    infoBubble.setTextColor(-16777216);
                    infoBubble.setTextSize(14.0f);
                    infoBubble.setId(2);
                    linearLayout.addView(infoBubble, new LayoutParams(-1, -2));
                }
            } else {
                this.h = true;
                ViewGroup viewGroup = (ViewGroup) infoBubbleContents.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(infoBubbleContents);
                }
                linearLayout.addView(infoBubbleContents);
            }
            infoBubble = new FrameLayout(context);
            infoBubble.addView(linearLayout, new LayoutParams(-1, -1));
            return infoBubble;
        }
        this.g = true;
        return infoBubble;
    }

    private ShapeDrawable g() {
        ShapeDrawable aVar = new a(new RoundRectShape(new float[]{6.0f, 6.0f, 6.0f, 6.0f, 6.0f, 6.0f, 6.0f, 6.0f}, null, null));
        aVar.setPadding(5, 5, 5, 5);
        return aVar;
    }

    private void b(Context context, MapMarker mapMarker) {
        if (this.c == null) {
            this.c = a(context, mapMarker);
            if (!(this.c == null || this.g || this.h || this.e == null)) {
                this.c.setVisibility(4);
                TextView textView = (TextView) this.c.findViewById(1);
                if (textView != null) {
                    textView.setText(this.e);
                }
                if (!TextUtils.isEmpty(this.f)) {
                    textView = (TextView) this.c.findViewById(2);
                    if (textView != null) {
                        textView.setText(this.f);
                        textView.setVisibility(0);
                    }
                }
            }
        }
        a(context);
        this.c.setVisibility(0);
    }

    private void a(Context context) {
        if (this.c != null) {
            int measuredWidth;
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
            int i = (context.getResources().getDisplayMetrics().widthPixels * 5) / 6;
            for (View view : a(this.c, TextView.class)) {
                ((TextView) view).setMaxWidth(i - 10);
            }
            this.c.measure(makeMeasureSpec, makeMeasureSpec);
            View view2 = this.c;
            if (this.c.getMeasuredWidth() < i) {
                measuredWidth = this.c.getMeasuredWidth();
            } else {
                measuredWidth = i;
            }
            view2.layout(0, 0, measuredWidth, this.c.getMeasuredHeight());
        }
    }

    private List<View> a(View view, Class<?> cls) {
        List<View> arrayList = new ArrayList();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            if (childCount > 0) {
                for (int i = 0; i < childCount; i++) {
                    arrayList.addAll(a(viewGroup.getChildAt(i), (Class) cls));
                }
            }
        }
        if (cls.isInstance(view)) {
            arrayList.add(view);
        }
        return arrayList;
    }

    private void b(GeoCoordinate geoCoordinate) {
        int height = this.c.getHeight();
        if (!this.g) {
            height += 20;
        }
        Bitmap createBitmap = Bitmap.createBitmap(this.c.getWidth(), height, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.translate((float) (-this.c.getScrollX()), (float) (-this.c.getScrollY()));
        this.c.draw(canvas);
        if (!this.g) {
            Path path = new Path();
            path.moveTo((((float) createBitmap.getWidth()) - 20.0f) / 2.0f, ((float) createBitmap.getHeight()) - 20.0f);
            path.lineTo(((float) createBitmap.getWidth()) / 2.0f, (float) createBitmap.getHeight());
            path.lineTo((((float) createBitmap.getWidth()) + 20.0f) / 2.0f, ((float) createBitmap.getHeight()) - 20.0f);
            Paint paint = new Paint();
            paint.setColor(-1);
            canvas.drawPath(path, paint);
            Paint paint2 = new Paint(paint);
            paint2.setStyle(Style.STROKE);
            paint2.setStrokeWidth(1.0f);
            paint2.setColor(-16777216);
            canvas.drawPath(path, paint2);
            path = new Path();
            path.moveTo((((float) createBitmap.getWidth()) - 20.0f) / 2.0f, (((float) createBitmap.getHeight()) - 20.0f) - 1.0f);
            path.lineTo((((float) createBitmap.getWidth()) + 20.0f) / 2.0f, (((float) createBitmap.getHeight()) - 20.0f) - 1.0f);
            paint = new Paint(paint2);
            paint.setColor(-1);
            canvas.drawPath(path, paint);
        }
        Image image = new Image();
        image.setBitmap(createBitmap);
        this.b = new MapMarker(geoCoordinate, image);
        if (this.i != null) {
            this.b.setTransparency(this.i.getTransparency());
            this.b.setDeclutteringEnabled(this.i.isDeclutteringEnabled());
        }
        f();
        this.b.setZIndex(Integer.MAX_VALUE);
        this.d.a(this.b);
    }

    private void h() {
        if (this.b != null) {
            if (this.d != null) {
                this.d.b(this.b);
            }
            this.b = null;
        }
        this.d = null;
    }
}
