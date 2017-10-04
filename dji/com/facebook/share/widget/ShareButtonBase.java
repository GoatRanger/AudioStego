package com.facebook.share.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.FacebookButtonBase;
import com.facebook.f;
import com.facebook.h;
import com.facebook.internal.j;
import com.facebook.o;
import com.facebook.share.c.a;
import com.facebook.share.internal.q;
import com.facebook.share.model.ShareContent;

public abstract class ShareButtonBase extends FacebookButtonBase {
    private ShareContent a;
    private int b = 0;
    private boolean c = false;

    protected abstract j<ShareContent, a> getDialog();

    protected ShareButtonBase(Context context, AttributeSet attributeSet, int i, String str, String str2) {
        super(context, attributeSet, i, 0, str, str2);
        this.b = isInEditMode() ? 0 : getDefaultRequestCode();
        a(false);
    }

    public ShareContent getShareContent() {
        return this.a;
    }

    public void setShareContent(ShareContent shareContent) {
        this.a = shareContent;
        if (!this.c) {
            a(a());
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.c = true;
    }

    public int getRequestCode() {
        return this.b;
    }

    protected void setRequestCode(int i) {
        if (o.b(i)) {
            throw new IllegalArgumentException("Request code " + i + " cannot be within the range reserved by the Facebook SDK.");
        }
        this.b = i;
    }

    public void registerCallback(f fVar, h<a> hVar) {
        q.a(getRequestCode(), fVar, (h) hVar);
    }

    public void registerCallback(f fVar, h<a> hVar, int i) {
        setRequestCode(i);
        registerCallback(fVar, hVar);
    }

    protected void configureButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super.configureButton(context, attributeSet, i, i2);
        setInternalOnClickListener(getShareOnClickListener());
    }

    protected boolean a() {
        return getDialog().a(getShareContent());
    }

    protected OnClickListener getShareOnClickListener() {
        return new OnClickListener(this) {
            final /* synthetic */ ShareButtonBase a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.callExternalOnClickListener(view);
                this.a.getDialog().b(this.a.getShareContent());
            }
        };
    }

    private void a(boolean z) {
        setEnabled(z);
        this.c = false;
    }
}
