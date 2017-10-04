package dji.setting.ui.general;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import dji.apppublic.reflect.AppPublicReflect;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewButtonBig;

public class FBLiveView extends ItemViewButtonBig {
    public FBLiveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            ProductType c = i.getInstance().c();
            if (c == ProductType.litchiC || c == ProductType.P34K) {
                setVisibility(8);
            } else {
                setVisibility(0);
            }
            a();
            AppPublicReflect.facebookInit(this.c);
        }
    }

    private void a() {
        Drawable drawable = getResources().getDrawable(R.drawable.setting_ui_general_live_facebook);
        drawable.setBounds(0, 0, (int) (((double) drawable.getIntrinsicWidth()) * 0.8d), (int) (((double) drawable.getIntrinsicHeight()) * 0.8d));
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.c.setLayoutParams(layoutParams);
        this.c.setCompoundDrawablePadding(8);
        this.c.setCompoundDrawables(drawable, null, null, null);
    }

    protected void onDetachedFromWindow() {
        AppPublicReflect.facebookUnInit();
        super.onDetachedFromWindow();
    }

    public void onClick(View view) {
        AppPublicReflect.enterFacebookLive();
    }
}
