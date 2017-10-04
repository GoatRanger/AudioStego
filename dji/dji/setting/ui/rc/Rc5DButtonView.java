package dji.setting.ui.rc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.b.i;
import dji.setting.ui.widget.DJISpinnerButton;
import dji.thirdparty.a.c;

public class Rc5DButtonView extends LinearLayout {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final String[] f = new String[]{i.aj, i.ak, i.am, i.an, i.al};
    private static final int[] g = new int[]{a.GIMBAL_FORWARDS_DOWN.ordinal(), a.PORTRAIT.ordinal(), a.ZOOM_IN.ordinal(), a.ZOOM_OUT.ordinal(), a.NAVIGATION.ordinal()};
    private DJISpinnerButton h;
    private DJISpinnerButton i;
    private DJISpinnerButton j;
    private DJISpinnerButton k;

    public enum a {
        GIMBAL_FORWARDS_DOWN(R.string.setting_ui_rc_custom_2_litchi),
        ZOOM_IN(R.string.setting_ui_rc_5d_button_left_button_desc),
        ZOOM_OUT(R.string.setting_ui_rc_5d_button_right_button_desc),
        PORTRAIT(R.string.setting_ui_rc_5d_button_portrait),
        POINT_FOCUS(R.string.setting_ui_rc_5d_button_point_focus),
        AE_AE_LOCK(R.string.setting_ui_rc_5d_button_ae_switch),
        NAVIGATION(R.string.setting_ui_rc_5d_button_press_button_desc);
        
        private int h;

        private a(int i) {
            this.h = i;
        }

        public int a() {
            return this.h;
        }
    }

    public class b implements dji.setting.ui.widget.DJISpinnerButton.b {
        final /* synthetic */ Rc5DButtonView a;
        private int b;

        public b(Rc5DButtonView rc5DButtonView, int i) {
            this.a = rc5DButtonView;
            this.b = i;
        }

        public void onItemClick(int i) {
            dji.midware.util.i.a(this.a.getContext(), Rc5DButtonView.f[this.b], i);
        }
    }

    public Rc5DButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a();
        }
    }

    private void a() {
        if (dji.pilot.publics.e.a.c(dji.midware.data.manager.P3.i.getInstance().c())) {
            dji.setting.a.a.a((View) this, R.layout.setting_ui_rc_5d_button);
            if (!isInEditMode()) {
                b();
            }
        }
    }

    private void b() {
        this.h = (DJISpinnerButton) findViewById(R.id.setting_ui_rc_five_dimens_up_button_spinner);
        this.i = (DJISpinnerButton) findViewById(R.id.setting_ui_rc_five_dimens_down_button_spinner);
        this.j = (DJISpinnerButton) findViewById(R.id.setting_ui_rc_five_dimens_left_button_spinner);
        this.k = (DJISpinnerButton) findViewById(R.id.setting_ui_rc_five_dimens_right_button_spinner);
        a[] values = a.values();
        int[] iArr = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            iArr[i] = values[i].a();
        }
        a(iArr, 0, this.h);
        a(iArr, 1, this.i);
        a(iArr, 2, this.j);
        a(iArr, 3, this.k);
    }

    private void a(int[] iArr, int i, DJISpinnerButton dJISpinnerButton) {
        dJISpinnerButton.setData(iArr, dji.midware.util.i.b(getContext(), f[i], getDefaultSelectIndex(i)), new b(this, i));
    }

    public static int getDefaultSelectIndex(int i) {
        if (i < 0 || i >= g.length) {
            return g[0];
        }
        return g[i];
    }

    public void onEventMainThread(ProductType productType) {
        a(productType);
    }

    private void a(ProductType productType) {
        if (productType != null) {
            if (dji.pilot.publics.e.a.c(productType)) {
                setVisibility(0);
            } else {
                setVisibility(8);
            }
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!c.a().c(this)) {
            c.a().a(this);
        }
        a(dji.midware.data.manager.P3.i.getInstance().c());
    }

    protected void onDetachedFromWindow() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
