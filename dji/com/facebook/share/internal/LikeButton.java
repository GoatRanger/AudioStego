package com.facebook.share.internal;

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.FacebookButtonBase;
import com.facebook.R;
import com.facebook.internal.a;

public class LikeButton extends FacebookButtonBase {
    public LikeButton(Context context, boolean z) {
        super(context, null, 0, 0, a.al, a.ar);
        setSelected(z);
    }

    public void setSelected(boolean z) {
        super.setSelected(z);
        a();
    }

    protected void configureButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super.configureButton(context, attributeSet, i, i2);
        a();
    }

    protected int getDefaultRequestCode() {
        return 0;
    }

    protected int getDefaultStyleResource() {
        return R.style.com_facebook_button_like;
    }

    private void a() {
        if (isSelected()) {
            setCompoundDrawablesWithIntrinsicBounds(R.drawable.com_facebook_button_like_icon_selected, 0, 0, 0);
            setText(getResources().getString(R.string.com_facebook_like_button_liked));
            return;
        }
        setCompoundDrawablesWithIntrinsicBounds(R.drawable.com_facebook_button_icon, 0, 0, 0);
        setText(getResources().getString(R.string.com_facebook_like_button_not_liked));
    }
}
