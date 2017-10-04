package dji.pilot2.mine.c;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;

public class a extends dji.pilot2.publics.object.a implements OnClickListener {
    private View b;
    private View c;
    private View d;
    private View e;
    private View f;
    private OnClickListener g;
    private OnClickListener h;
    private OnClickListener i;
    private OnClickListener j;

    public a(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    public a(Context context, int i) {
        super(context, i);
    }

    public a(Context context) {
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_contact_dji_email_dialog_layout);
        a();
    }

    private void a() {
        this.b = findViewById(R.id.ci5);
        this.c = findViewById(R.id.ci3);
        this.d = findViewById(R.id.ci4);
        this.e = findViewById(R.id.ci2);
        this.f = findViewById(R.id.ci6);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.f.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == this.c) {
            if (this.g != null) {
                this.g.onClick(view);
            }
        } else if (view == this.d) {
            if (this.h != null) {
                this.h.onClick(view);
            }
        } else if (view == this.e) {
            if (this.i != null) {
                this.i.onClick(view);
            }
        } else if (view == this.b && this.j != null) {
            this.j.onClick(view);
        }
        dismiss();
    }

    public void a(OnClickListener onClickListener, OnClickListener onClickListener2, OnClickListener onClickListener3, OnClickListener onClickListener4) {
        this.g = onClickListener;
        this.h = onClickListener2;
        this.i = onClickListener3;
        this.j = onClickListener4;
    }
}
