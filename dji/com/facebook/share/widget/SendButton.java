package com.facebook.share.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.R;
import com.facebook.internal.a;
import com.facebook.internal.f.b;
import com.facebook.internal.j;
import com.facebook.share.c;
import com.facebook.share.model.ShareContent;

public final class SendButton extends ShareButtonBase {
    public SendButton(Context context) {
        super(context, null, 0, a.ao, a.aq);
    }

    public SendButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, a.ao, a.aq);
    }

    public SendButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, a.ao, a.aq);
    }

    protected int getDefaultStyleResource() {
        return R.style.com_facebook_button_send;
    }

    protected int getDefaultRequestCode() {
        return b.Message.a();
    }

    protected j<ShareContent, c.a> getDialog() {
        if (getFragment() != null) {
            return new e(getFragment(), getRequestCode());
        }
        if (getNativeFragment() != null) {
            return new e(getNativeFragment(), getRequestCode());
        }
        return new e(getActivity(), getRequestCode());
    }
}
