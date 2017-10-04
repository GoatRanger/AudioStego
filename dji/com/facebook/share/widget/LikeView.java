package com.facebook.share.widget;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.R;
import com.facebook.internal.ab;
import com.facebook.internal.ah;
import com.facebook.internal.o;
import com.facebook.k;
import com.facebook.share.internal.LikeBoxCountView;
import com.facebook.share.internal.LikeButton;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;

public class LikeView extends FrameLayout {
    private static final int a = -1;
    private String b;
    private e c;
    private LinearLayout d;
    private LikeButton e;
    private LikeBoxCountView f;
    private TextView g;
    private com.facebook.share.internal.d h;
    private f i;
    private BroadcastReceiver j;
    private c k;
    private g l = g.d;
    private b m = b.d;
    private a n = a.d;
    private int o = -1;
    private int p;
    private int q;
    private o r;
    private boolean s;

    public enum a {
        BOTTOM("bottom", 0),
        INLINE("inline", 1),
        TOP("top", 2);
        
        static a d;
        private String e;
        private int f;

        static {
            d = BOTTOM;
        }

        static a a(int i) {
            for (a aVar : values()) {
                if (aVar.a() == i) {
                    return aVar;
                }
            }
            return null;
        }

        private a(String str, int i) {
            this.e = str;
            this.f = i;
        }

        public String toString() {
            return this.e;
        }

        private int a() {
            return this.f;
        }
    }

    public enum b {
        CENTER("center", 0),
        LEFT("left", 1),
        RIGHT("right", 2);
        
        static b d;
        private String e;
        private int f;

        static {
            d = CENTER;
        }

        static b a(int i) {
            for (b bVar : values()) {
                if (bVar.a() == i) {
                    return bVar;
                }
            }
            return null;
        }

        private b(String str, int i) {
            this.e = str;
            this.f = i;
        }

        public String toString() {
            return this.e;
        }

        private int a() {
            return this.f;
        }
    }

    private class c implements com.facebook.share.internal.d.c {
        final /* synthetic */ LikeView a;
        private boolean b;

        private c(LikeView likeView) {
            this.a = likeView;
        }

        public void a() {
            this.b = true;
        }

        public void a(com.facebook.share.internal.d dVar, k kVar) {
            if (!this.b) {
                if (dVar != null) {
                    if (!dVar.e()) {
                        kVar = new k("Cannot use LikeView. The device may not be supported.");
                    }
                    this.a.a(dVar);
                    this.a.c();
                }
                if (!(kVar == null || this.a.i == null)) {
                    this.a.i.a(kVar);
                }
                this.a.k = null;
            }
        }
    }

    private class d extends BroadcastReceiver {
        final /* synthetic */ LikeView a;

        private d(LikeView likeView) {
            this.a = likeView;
        }

        public void onReceive(Context context, Intent intent) {
            Object obj = 1;
            String action = intent.getAction();
            Bundle extras = intent.getExtras();
            if (extras != null) {
                Object string = extras.getString(com.facebook.share.internal.d.d);
                if (!(ah.a((String) string) || ah.a(this.a.b, string))) {
                    obj = null;
                }
            }
            if (obj != null) {
                if (com.facebook.share.internal.d.a.equals(action)) {
                    this.a.c();
                } else if (com.facebook.share.internal.d.b.equals(action)) {
                    if (this.a.i != null) {
                        this.a.i.a(ab.a(extras));
                    }
                } else if (com.facebook.share.internal.d.c.equals(action)) {
                    this.a.a(this.a.b, this.a.c);
                    this.a.c();
                }
            }
        }
    }

    public enum e {
        UNKNOWN("unknown", 0),
        OPEN_GRAPH(com.facebook.internal.a.ah, 1),
        PAGE(ParamKey.PAGE, 2);
        
        public static e d;
        private String e;
        private int f;

        static {
            d = UNKNOWN;
        }

        public static e fromInt(int i) {
            for (e eVar : values()) {
                if (eVar.a() == i) {
                    return eVar;
                }
            }
            return null;
        }

        private e(String str, int i) {
            this.e = str;
            this.f = i;
        }

        public String toString() {
            return this.e;
        }

        public int a() {
            return this.f;
        }
    }

    public interface f {
        void a(k kVar);
    }

    public enum g {
        STANDARD("standard", 0),
        BUTTON("button", 1),
        BOX_COUNT("box_count", 2);
        
        static g d;
        private String e;
        private int f;

        static {
            d = STANDARD;
        }

        static g a(int i) {
            for (g gVar : values()) {
                if (gVar.a() == i) {
                    return gVar;
                }
            }
            return null;
        }

        private g(String str, int i) {
            this.e = str;
            this.f = i;
        }

        public String toString() {
            return this.e;
        }

        private int a() {
            return this.f;
        }
    }

    public LikeView(Context context) {
        super(context);
        a(context);
    }

    public LikeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
        a(context);
    }

    public void setObjectIdAndType(String str, e eVar) {
        String a = ah.a(str, null);
        if (eVar == null) {
            eVar = e.d;
        }
        if (!ah.a((Object) a, this.b) || eVar != this.c) {
            a(a, eVar);
            c();
        }
    }

    public void setLikeViewStyle(g gVar) {
        if (gVar == null) {
            gVar = g.d;
        }
        if (this.l != gVar) {
            this.l = gVar;
            d();
        }
    }

    public void setAuxiliaryViewPosition(a aVar) {
        if (aVar == null) {
            aVar = a.d;
        }
        if (this.n != aVar) {
            this.n = aVar;
            d();
        }
    }

    public void setHorizontalAlignment(b bVar) {
        if (bVar == null) {
            bVar = b.d;
        }
        if (this.m != bVar) {
            this.m = bVar;
            d();
        }
    }

    public void setForegroundColor(int i) {
        if (this.o != i) {
            this.g.setTextColor(i);
        }
    }

    public void setOnErrorListener(f fVar) {
        this.i = fVar;
    }

    public f getOnErrorListener() {
        return this.i;
    }

    public void setFragment(Fragment fragment) {
        this.r = new o(fragment);
    }

    public void setEnabled(boolean z) {
        this.s = !z;
        c();
    }

    protected void onDetachedFromWindow() {
        setObjectIdAndType(null, e.UNKNOWN);
        super.onDetachedFromWindow();
    }

    private void a(AttributeSet attributeSet) {
        if (attributeSet != null && getContext() != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.com_facebook_like_view);
            if (obtainStyledAttributes != null) {
                this.b = ah.a(obtainStyledAttributes.getString(R.styleable.com_facebook_like_view_com_facebook_object_id), null);
                this.c = e.fromInt(obtainStyledAttributes.getInt(R.styleable.com_facebook_like_view_com_facebook_object_type, e.d.a()));
                this.l = g.a(obtainStyledAttributes.getInt(R.styleable.com_facebook_like_view_com_facebook_style, g.d.a()));
                if (this.l == null) {
                    throw new IllegalArgumentException("Unsupported value for LikeView 'style'");
                }
                this.n = a.a(obtainStyledAttributes.getInt(R.styleable.com_facebook_like_view_com_facebook_auxiliary_view_position, a.d.a()));
                if (this.n == null) {
                    throw new IllegalArgumentException("Unsupported value for LikeView 'auxiliary_view_position'");
                }
                this.m = b.a(obtainStyledAttributes.getInt(R.styleable.com_facebook_like_view_com_facebook_horizontal_alignment, b.d.a()));
                if (this.m == null) {
                    throw new IllegalArgumentException("Unsupported value for LikeView 'horizontal_alignment'");
                }
                this.o = obtainStyledAttributes.getColor(R.styleable.com_facebook_like_view_com_facebook_foreground_color, -1);
                obtainStyledAttributes.recycle();
            }
        }
    }

    private void a(Context context) {
        this.p = getResources().getDimensionPixelSize(R.dimen.com_facebook_likeview_edge_padding);
        this.q = getResources().getDimensionPixelSize(R.dimen.com_facebook_likeview_internal_padding);
        if (this.o == -1) {
            this.o = getResources().getColor(R.color.com_facebook_likeview_text_color);
        }
        setBackgroundColor(0);
        this.d = new LinearLayout(context);
        this.d.setLayoutParams(new LayoutParams(-2, -2));
        b(context);
        c(context);
        d(context);
        this.d.addView(this.e);
        this.d.addView(this.g);
        this.d.addView(this.f);
        addView(this.d);
        a(this.b, this.c);
        c();
    }

    private void b(Context context) {
        boolean z = this.h != null && this.h.d();
        this.e = new LikeButton(context, z);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LikeView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a();
            }
        });
        this.e.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
    }

    private void c(Context context) {
        this.g = new TextView(context);
        this.g.setTextSize(0, getResources().getDimension(R.dimen.com_facebook_likeview_text_size));
        this.g.setMaxLines(2);
        this.g.setTextColor(this.o);
        this.g.setGravity(17);
        this.g.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
    }

    private void d(Context context) {
        this.f = new LikeBoxCountView(context);
        this.f.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    }

    private void a() {
        if (this.h != null) {
            Activity activity = null;
            if (this.r == null) {
                activity = getActivity();
            }
            this.h.a(activity, this.r, getAnalyticsParameters());
        }
    }

    private Activity getActivity() {
        Context context = getContext();
        while (!(context instanceof Activity) && (context instanceof ContextWrapper)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        throw new k("Unable to get Activity.");
    }

    private Bundle getAnalyticsParameters() {
        Bundle bundle = new Bundle();
        bundle.putString(com.facebook.internal.a.L, this.l.toString());
        bundle.putString(com.facebook.internal.a.M, this.n.toString());
        bundle.putString(com.facebook.internal.a.N, this.m.toString());
        bundle.putString("object_id", ah.a(this.b, ""));
        bundle.putString("object_type", this.c.toString());
        return bundle;
    }

    private void a(String str, e eVar) {
        b();
        this.b = str;
        this.c = eVar;
        if (!ah.a(str)) {
            this.k = new c();
            if (!isInEditMode()) {
                com.facebook.share.internal.d.a(str, eVar, this.k);
            }
        }
    }

    private void a(com.facebook.share.internal.d dVar) {
        this.h = dVar;
        this.j = new d();
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(getContext());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(com.facebook.share.internal.d.a);
        intentFilter.addAction(com.facebook.share.internal.d.b);
        intentFilter.addAction(com.facebook.share.internal.d.c);
        instance.registerReceiver(this.j, intentFilter);
    }

    private void b() {
        if (this.j != null) {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.j);
            this.j = null;
        }
        if (this.k != null) {
            this.k.a();
            this.k = null;
        }
        this.h = null;
    }

    private void c() {
        boolean z = !this.s;
        if (this.h == null) {
            this.e.setSelected(false);
            this.g.setText(null);
            this.f.setText(null);
        } else {
            this.e.setSelected(this.h.d());
            this.g.setText(this.h.c());
            this.f.setText(this.h.b());
            z &= this.h.e();
        }
        super.setEnabled(z);
        this.e.setEnabled(z);
        d();
    }

    private void d() {
        View view;
        int i = 1;
        LayoutParams layoutParams = (LayoutParams) this.d.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.e.getLayoutParams();
        int i2 = this.m == b.LEFT ? 3 : this.m == b.CENTER ? 1 : 5;
        layoutParams.gravity = i2 | 48;
        layoutParams2.gravity = i2;
        this.g.setVisibility(8);
        this.f.setVisibility(8);
        if (this.l == g.STANDARD && this.h != null && !ah.a(this.h.c())) {
            view = this.g;
        } else if (this.l == g.BOX_COUNT && this.h != null && !ah.a(this.h.b())) {
            e();
            view = this.f;
        } else {
            return;
        }
        view.setVisibility(0);
        ((LinearLayout.LayoutParams) view.getLayoutParams()).gravity = i2;
        LinearLayout linearLayout = this.d;
        if (this.n == a.INLINE) {
            i = 0;
        }
        linearLayout.setOrientation(i);
        if (this.n == a.TOP || (this.n == a.INLINE && this.m == b.RIGHT)) {
            this.d.removeView(this.e);
            this.d.addView(this.e);
        } else {
            this.d.removeView(view);
            this.d.addView(view);
        }
        switch (this.n) {
            case TOP:
                view.setPadding(this.p, this.p, this.p, this.q);
                return;
            case BOTTOM:
                view.setPadding(this.p, this.q, this.p, this.p);
                return;
            case INLINE:
                if (this.m == b.RIGHT) {
                    view.setPadding(this.p, this.p, this.q, this.p);
                    return;
                } else {
                    view.setPadding(this.q, this.p, this.p, this.p);
                    return;
                }
            default:
                return;
        }
    }

    private void e() {
        switch (this.n) {
            case TOP:
                this.f.setCaretPosition(com.facebook.share.internal.LikeBoxCountView.a.BOTTOM);
                return;
            case BOTTOM:
                this.f.setCaretPosition(com.facebook.share.internal.LikeBoxCountView.a.TOP);
                return;
            case INLINE:
                this.f.setCaretPosition(this.m == b.RIGHT ? com.facebook.share.internal.LikeBoxCountView.a.RIGHT : com.facebook.share.internal.LikeBoxCountView.a.LEFT);
                return;
            default:
                return;
        }
    }
}
