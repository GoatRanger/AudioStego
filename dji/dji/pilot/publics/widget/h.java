package dji.pilot.publics.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import com.dji.frame.c.c;
import com.dji.frame.c.c.a;
import dji.pilot.R;
import dji.pilot.publics.objects.b;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class h extends b implements OnClickListener {
    private DJITextView b;
    private ProgressBar c;
    private DJIImageView d;
    private DJIImageView e;
    private DJILinearLayout f;
    private DialogInterface.OnClickListener g;
    private DialogInterface.OnClickListener h;
    private DJITextView i;
    private DJITextView j;
    private DJIImageView k;
    private DJILinearLayout l;
    private boolean m = true;

    public h(Context context) {
        super(context, R.style.c6);
        g();
    }

    public h a(DialogInterface.OnClickListener onClickListener) {
        this.g = onClickListener;
        return this;
    }

    public h b(DialogInterface.OnClickListener onClickListener) {
        this.h = onClickListener;
        return this;
    }

    public h a(int i) {
        this.i.setText(i);
        return this;
    }

    public h b(int i) {
        this.j.setText(i);
        return this;
    }

    public h a(boolean z) {
        this.j.setClickable(z);
        this.j.setEnabled(z);
        return this;
    }

    public boolean a() {
        return this.j.isClickable();
    }

    public h c(int i) {
        if (i == 0) {
            this.d.hide();
        } else {
            this.d.show();
            this.d.setBackgroundResource(i);
        }
        return this;
    }

    public boolean b() {
        return this.c.isShown();
    }

    public boolean f() {
        return this.f.isShown();
    }

    public h c(boolean z) {
        if (z) {
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
        return this;
    }

    public h d(boolean z) {
        if (z) {
            this.e.show();
            this.f.show();
        } else {
            this.e.go();
            this.f.go();
        }
        return this;
    }

    public h e(boolean z) {
        if (z) {
            this.e.show();
            this.f.show();
            this.k.go();
            this.j.go();
        } else {
            this.e.go();
            this.f.go();
        }
        return this;
    }

    public h f(boolean z) {
        if (z) {
            this.l.setBackgroundResource(R.drawable.stroke_dlg_trans_bg);
        }
        return this;
    }

    public h a(String str) {
        this.b.setText(str);
        return this;
    }

    public h d(int i) {
        this.b.setText(i);
        return this;
    }

    private void g() {
        setContentView(R.layout.icon_dlg_text_view);
        this.b = (DJITextView) findViewById(R.id.awu);
        this.d = (DJIImageView) findViewById(R.id.awt);
        this.c = (ProgressBar) findViewById(R.id.awx);
        this.e = (DJIImageView) findViewById(R.id.awy);
        this.f = (DJILinearLayout) findViewById(R.id.awz);
        this.i = (DJITextView) findViewById(R.id.ax0);
        this.j = (DJITextView) findViewById(R.id.ax2);
        this.k = (DJIImageView) findViewById(R.id.ax1);
        this.l = (DJILinearLayout) findViewById(R.id.ap3);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        c.a(getWindow());
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.thirdparty.a.c.a().e(a.a);
    }

    protected void onCreate(Bundle bundle) {
        a(dji.pilot.fpv.model.b.a(this.a, R.dimen.lt), -2, 0, 17, false, false);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (R.id.ax0 == id) {
            if (this.g != null) {
                this.g.onClick(this, 0);
            }
            if (this.m) {
                dismiss();
            }
        } else if (R.id.ax2 == id) {
            if (this.h != null) {
                this.h.onClick(this, 1);
            }
            if (this.m) {
                dismiss();
            }
        }
    }

    public void g(boolean z) {
        this.m = z;
    }
}
