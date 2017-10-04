package dji.pilot.publics.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.dji.frame.c.c;
import com.dji.frame.c.c.a;
import dji.pilot.R;
import dji.pilot.publics.objects.b;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class f extends b implements OnClickListener {
    private DJITextView b = null;
    private DJITextView c;
    private DJIImageView d;
    private DJIImageView e;
    private DJILinearLayout f = null;
    private DJITextView g;
    private DJITextView h;
    private DJIImageView i = null;
    private DJILinearLayout j = null;
    private DialogInterface.OnClickListener k;
    private DialogInterface.OnClickListener l;
    private boolean m = false;

    public f(Context context) {
        super(context, R.style.c6);
        f();
    }

    public f(Context context, int i) {
        super(context, i);
        f();
    }

    public void a(boolean z) {
        this.m = z;
    }

    public f a(int i) {
        this.d.setImageResource(i);
        this.d.show();
        return this;
    }

    public f a(String str) {
        this.b.setText(str);
        return this;
    }

    public f b(String str) {
        this.c.show();
        this.c.setText(str);
        return this;
    }

    public f a() {
        this.c.go();
        return this;
    }

    public f b() {
        this.f.go();
        this.g.go();
        this.h.go();
        this.i.go();
        return this;
    }

    public f b(int i) {
        return c(this.a.getString(i));
    }

    public f c(String str) {
        this.f.show();
        this.i.go();
        this.e.show();
        this.g.setVisibility(0);
        this.g.setText(str);
        return this;
    }

    public f c(int i) {
        return d(getContext().getString(i));
    }

    private f d(String str) {
        this.f.show();
        this.i.show();
        this.e.show();
        this.h.setVisibility(0);
        this.h.setText(str);
        return this;
    }

    public f a(DialogInterface.OnClickListener onClickListener) {
        this.k = onClickListener;
        return this;
    }

    public f b(DialogInterface.OnClickListener onClickListener) {
        this.l = onClickListener;
        return this;
    }

    private void f() {
        setContentView(R.layout.icon_dlg_view);
        this.j = (DJILinearLayout) findViewById(R.id.ax3);
        this.b = (DJITextView) findViewById(R.id.ax4);
        this.d = (DJIImageView) findViewById(R.id.awt);
        this.e = (DJIImageView) findViewById(R.id.awy);
        this.c = (DJITextView) findViewById(R.id.awu);
        this.f = (DJILinearLayout) findViewById(R.id.awz);
        this.g = (DJITextView) findViewById(R.id.ax0);
        this.h = (DJITextView) findViewById(R.id.ax2);
        this.i = (DJIImageView) findViewById(R.id.ax1);
        this.f.go();
        this.i.go();
        this.d.go();
        this.e.go();
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
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
        int a;
        if (this.m) {
            a = dji.pilot.fpv.model.b.a(this.a, R.dimen.lt);
        } else {
            a = dji.pilot.fpv.model.b.a(this.a, R.dimen.lw);
        }
        a(a, -2, 0, 17, false, false);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (R.id.sl == id) {
            dismiss();
        } else if (R.id.ax0 == id) {
            if (this.k != null) {
                this.k.onClick(this, 0);
            }
            dismiss();
        } else if (R.id.ax2 == id) {
            if (this.l != null) {
                this.l.onClick(this, 1);
            }
            dismiss();
        }
    }

    public f d(int i) {
        this.j.setBackgroundColor(i);
        return this;
    }

    public f e(int i) {
        this.b.setTextColor(i);
        return this;
    }

    public f f(int i) {
        this.c.setTextColor(i);
        return this;
    }
}
