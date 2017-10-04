package com.alibaba.sdk.android.c;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.alibaba.sdk.android.util.ResourceUtils;

public final class a extends ProgressDialog {
    private ProgressBar a;
    private TextView b;
    private CharSequence c;
    private boolean d;
    private boolean e;

    public a(Context context) {
        super(context);
    }

    protected final void onCreate(Bundle bundle) {
        int i;
        super.onCreate(bundle);
        setContentView(ResourceUtils.getIdentifier("layout", "com_taobao_tae_sdk_progress_dialog"));
        getWindow().setBackgroundDrawableResource(17170445);
        this.a = (ProgressBar) findViewById(16908301);
        this.b = (TextView) findViewById(ResourceUtils.getIdentifier("id", "com_taobao_tae_sdk_progress_dialog_message"));
        this.b.setText(this.c);
        if (this.c == null || "".equals(this.c)) {
            this.b.setVisibility(8);
        }
        ProgressBar progressBar = this.a;
        if (this.e) {
            i = 0;
        } else {
            i = 8;
        }
        progressBar.setVisibility(i);
        setIndeterminate(this.d);
    }

    public final void setMessage(CharSequence charSequence) {
        this.c = charSequence;
    }

    public final void a(boolean z) {
        this.e = z;
    }

    public final void setIndeterminate(boolean z) {
        if (this.a != null) {
            this.a.setIndeterminate(z);
        } else {
            this.d = z;
        }
    }
}
