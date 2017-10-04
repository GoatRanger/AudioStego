package com.facebook.share.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.R;
import com.facebook.internal.a;
import com.facebook.internal.f.b;
import com.facebook.internal.j;
import com.facebook.share.c;
import com.facebook.share.model.ShareContent;

public final class ShareButton extends ShareButtonBase {
    public ShareButton(Context context) {
        super(context, null, 0, a.an, a.ap);
    }

    public ShareButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, a.an, a.ap);
    }

    public ShareButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, a.an, a.ap);
    }

    protected int getDefaultStyleResource() {
        return R.style.com_facebook_button_share;
    }

    protected int getDefaultRequestCode() {
        return b.Share.a();
    }

    protected j<ShareContent, c.a> getDialog() {
        if (getFragment() != null) {
            return new f(getFragment(), getRequestCode());
        }
        if (getNativeFragment() != null) {
            return new f(getNativeFragment(), getRequestCode());
        }
        return new f(getActivity(), getRequestCode());
    }
}
