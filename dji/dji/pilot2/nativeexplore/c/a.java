package dji.pilot2.nativeexplore.c;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.TextView;
import dji.pilot.R;

public class a extends dji.pilot2.publics.object.a {
    private TextView b;
    private TextView c;
    private String d;
    private String e;
    private OnClickListener f;
    private OnClickListener g;

    public a(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        a(context);
    }

    public a(Context context, int i) {
        super(context, i);
        a(context);
    }

    public a(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.d = context.getResources().getString(17039379);
        this.e = context.getResources().getString(17039369);
        this.f = null;
        this.g = null;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_bottom_confirm_dialog);
        a();
    }

    private void a() {
        this.b = (TextView) findViewById(R.id.chr);
        this.c = (TextView) findViewById(R.id.re);
        this.b.setText(this.d);
        this.c.setText(this.e);
        this.b.setOnClickListener(this.f);
        this.c.setOnClickListener(this.g);
    }

    public void a(String str) {
        this.d = str;
        if (this.b != null) {
            this.b.setText(this.d);
        }
    }

    public void b(String str) {
        this.e = str;
        if (this.c != null) {
            this.c.setText(this.e);
        }
    }

    public void a(OnClickListener onClickListener) {
        this.f = onClickListener;
        if (this.b != null) {
            this.b.setOnClickListener(this.f);
        }
    }

    public void b(OnClickListener onClickListener) {
        this.g = onClickListener;
        if (this.c != null) {
            this.c.setOnClickListener(this.g);
        }
    }
}
