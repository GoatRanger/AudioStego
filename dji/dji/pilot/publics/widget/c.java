package dji.pilot.publics.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.model.b;
import dji.pilot.publics.e.f;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class c extends dji.pilot.publics.objects.c implements OnClickListener {
    private DJIImageView a = null;
    private DJITextView b = null;
    private DJITextView c = null;
    private DJIImageView d = null;
    private DJIImageView e = null;
    private DJILinearLayout f = null;
    private DJITextView g;
    private DJITextView h;
    private DJIImageView i = null;
    private DialogInterface.OnClickListener j;
    private DialogInterface.OnClickListener k;

    public c(Context context) {
        super(context);
        d();
    }

    public c a(int i) {
        this.a.setBackgroundResource(i);
        this.a.show();
        return this;
    }

    public c a(String str) {
        this.b.setText(str);
        return this;
    }

    public c b(String str) {
        this.c.setText(str);
        return this;
    }

    public c b(int i) {
        f.a(this.c, i);
        return this;
    }

    public c c(int i) {
        this.d.setImageResource(i);
        this.d.show();
        return this;
    }

    public c b() {
        this.d.go();
        return this;
    }

    public c c() {
        this.f.go();
        this.g.go();
        this.h.go();
        this.i.go();
        return this;
    }

    public c d(int i) {
        return c(this.N.getString(i));
    }

    public c c(String str) {
        this.f.show();
        this.i.go();
        this.e.show();
        this.g.setVisibility(0);
        this.g.setText(str);
        return this;
    }

    public c e(int i) {
        return d(getContext().getString(i));
    }

    public c d(String str) {
        this.f.show();
        this.i.show();
        this.e.show();
        this.h.setVisibility(0);
        this.h.setText(str);
        return this;
    }

    public c a(DialogInterface.OnClickListener onClickListener) {
        this.j = onClickListener;
        return this;
    }

    public c b(DialogInterface.OnClickListener onClickListener) {
        this.k = onClickListener;
        return this;
    }

    private void d() {
        setContentView(R.layout.double_img_dlg_view);
        this.a = (DJIImageView) findViewById(R.id.sm);
        this.b = (DJITextView) findViewById(R.id.sn);
        this.c = (DJITextView) findViewById(R.id.so);
        this.d = (DJIImageView) findViewById(R.id.sp);
        this.e = (DJIImageView) findViewById(R.id.sq);
        this.f = (DJILinearLayout) findViewById(R.id.sr);
        this.g = (DJITextView) findViewById(R.id.st);
        this.h = (DJITextView) findViewById(R.id.sw);
        this.i = (DJIImageView) findViewById(R.id.su);
        this.f.go();
        this.i.go();
        this.e.go();
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
    }

    protected void onCreate(Bundle bundle) {
        a(b.a(this.N, R.dimen.lt), -2, 0, 17, false, false);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (R.id.st == id) {
            if (this.j != null) {
                this.j.onClick(this, 0);
            } else {
                dismiss();
            }
        } else if (R.id.sw != id) {
        } else {
            if (this.k != null) {
                this.k.onClick(this, 1);
            } else {
                dismiss();
            }
        }
    }
}
