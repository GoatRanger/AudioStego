package com.facebook.login.widget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.facebook.R;
import java.lang.ref.WeakReference;

public class ToolTipPopup {
    public static final long a = 6000;
    private final String b;
    private final WeakReference<View> c;
    private final Context d;
    private PopupContentView e;
    private PopupWindow f;
    private a g = a.BLUE;
    private long h = a;
    private final OnScrollChangedListener i = new OnScrollChangedListener(this) {
        final /* synthetic */ ToolTipPopup a;

        {
            this.a = r1;
        }

        public void onScrollChanged() {
            if (this.a.c.get() != null && this.a.f != null && this.a.f.isShowing()) {
                if (this.a.f.isAboveAnchor()) {
                    this.a.e.showBottomArrow();
                } else {
                    this.a.e.showTopArrow();
                }
            }
        }
    };

    private class PopupContentView extends FrameLayout {
        final /* synthetic */ ToolTipPopup a;
        private ImageView b;
        private ImageView c;
        private View d;
        private ImageView e;

        public PopupContentView(ToolTipPopup toolTipPopup, Context context) {
            this.a = toolTipPopup;
            super(context);
            a();
        }

        private void a() {
            LayoutInflater.from(getContext()).inflate(R.layout.com_facebook_tooltip_bubble, this);
            this.b = (ImageView) findViewById(R.id.com_facebook_tooltip_bubble_view_top_pointer);
            this.c = (ImageView) findViewById(R.id.com_facebook_tooltip_bubble_view_bottom_pointer);
            this.d = findViewById(R.id.com_facebook_body_frame);
            this.e = (ImageView) findViewById(R.id.com_facebook_button_xout);
        }

        public void showTopArrow() {
            this.b.setVisibility(0);
            this.c.setVisibility(4);
        }

        public void showBottomArrow() {
            this.b.setVisibility(4);
            this.c.setVisibility(0);
        }
    }

    public enum a {
        BLUE,
        BLACK
    }

    public ToolTipPopup(String str, View view) {
        this.b = str;
        this.c = new WeakReference(view);
        this.d = view.getContext();
    }

    public void a(a aVar) {
        this.g = aVar;
    }

    public void a() {
        if (this.c.get() != null) {
            this.e = new PopupContentView(this, this.d);
            ((TextView) this.e.findViewById(R.id.com_facebook_tooltip_bubble_view_text_body)).setText(this.b);
            if (this.g == a.BLUE) {
                this.e.d.setBackgroundResource(R.drawable.com_facebook_tooltip_blue_background);
                this.e.c.setImageResource(R.drawable.com_facebook_tooltip_blue_bottomnub);
                this.e.b.setImageResource(R.drawable.com_facebook_tooltip_blue_topnub);
                this.e.e.setImageResource(R.drawable.com_facebook_tooltip_blue_xout);
            } else {
                this.e.d.setBackgroundResource(R.drawable.com_facebook_tooltip_black_background);
                this.e.c.setImageResource(R.drawable.com_facebook_tooltip_black_bottomnub);
                this.e.b.setImageResource(R.drawable.com_facebook_tooltip_black_topnub);
                this.e.e.setImageResource(R.drawable.com_facebook_tooltip_black_xout);
            }
            View decorView = ((Activity) this.d).getWindow().getDecorView();
            int width = decorView.getWidth();
            int height = decorView.getHeight();
            d();
            this.e.measure(MeasureSpec.makeMeasureSpec(width, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(height, Integer.MIN_VALUE));
            this.f = new PopupWindow(this.e, this.e.getMeasuredWidth(), this.e.getMeasuredHeight());
            this.f.showAsDropDown((View) this.c.get());
            c();
            if (this.h > 0) {
                this.e.postDelayed(new Runnable(this) {
                    final /* synthetic */ ToolTipPopup a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b();
                    }
                }, this.h);
            }
            this.f.setTouchable(true);
            this.e.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ToolTipPopup a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.b();
                }
            });
        }
    }

    public void a(long j) {
        this.h = j;
    }

    private void c() {
        if (this.f != null && this.f.isShowing()) {
            if (this.f.isAboveAnchor()) {
                this.e.showBottomArrow();
            } else {
                this.e.showTopArrow();
            }
        }
    }

    public void b() {
        e();
        if (this.f != null) {
            this.f.dismiss();
        }
    }

    private void d() {
        e();
        if (this.c.get() != null) {
            ((View) this.c.get()).getViewTreeObserver().addOnScrollChangedListener(this.i);
        }
    }

    private void e() {
        if (this.c.get() != null) {
            ((View) this.c.get()).getViewTreeObserver().removeOnScrollChangedListener(this.i);
        }
    }
}
