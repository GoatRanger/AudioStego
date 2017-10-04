package dji.pilot.publics.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import dji.pilot.R;
import dji.pilot.publics.objects.c;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;

public class b extends c implements OnClickListener {
    private DJITextView a;
    private DJIImageView b;
    private DJITextView c;
    private Button d;
    private Button e;
    private DialogInterface.OnClickListener f;
    private DialogInterface.OnClickListener g;
    private boolean h;
    private boolean i;

    public static b a(Context context, int i, int i2, DialogInterface.OnClickListener onClickListener, int i3, DialogInterface.OnClickListener onClickListener2) {
        return a(context, context.getString(i), context.getString(i2), onClickListener, context.getString(i3), onClickListener2);
    }

    public static b a(Context context, String str, String str2, DialogInterface.OnClickListener onClickListener, String str3, DialogInterface.OnClickListener onClickListener2) {
        return new b(context).a(str).c(str2).a(onClickListener).d(str3).b(onClickListener2);
    }

    public static b a(Context context, int i, int i2, int i3, DialogInterface.OnClickListener onClickListener, int i4, DialogInterface.OnClickListener onClickListener2) {
        return a(context, context.getString(i), context.getString(i2), context.getString(i3), onClickListener, context.getString(i4), onClickListener2);
    }

    public static b a(Context context, String str, String str2, String str3, DialogInterface.OnClickListener onClickListener, String str4, DialogInterface.OnClickListener onClickListener2) {
        return a(context, str, str3, onClickListener, str4, onClickListener2).b(str2);
    }

    public static b a(Context context, int i, int i2, int i3, DialogInterface.OnClickListener onClickListener) {
        return a(context, context.getString(i), context.getString(i2), context.getString(i3), onClickListener, null, null);
    }

    public static b a(Context context, String str, String str2, String str3, DialogInterface.OnClickListener onClickListener) {
        return a(context, str, str3, onClickListener, null, null).b(str2);
    }

    public b(Context context) {
        this(context, (int) R.style.c6);
    }

    public b(Context context, boolean z) {
        this(context, (int) R.style.c6);
        this.h = z;
    }

    public b a(boolean z) {
        this.h = z;
        return this;
    }

    public void b(boolean z) {
        this.i = z;
    }

    public b(Context context, int i) {
        super(context, i);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = true;
        h();
    }

    private void h() {
        setContentView(R.layout.button_dlg_view);
        this.a = (DJITextView) findViewById(R.id.sj);
        this.b = (DJIImageView) findViewById(R.id.sl);
        this.c = (DJITextView) findViewById(R.id.if);
        this.d = (Button) findViewById(R.id.ih);
        this.e = (Button) findViewById(R.id.ii);
        this.e.setVisibility(8);
        this.b.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    protected void onCreate(Bundle bundle) {
        a(dji.pilot.fpv.model.b.a(this.N, this.i ? R.dimen.ls : R.dimen.lv), -2, 0, 17, this.h, this.h);
    }

    public b a(int i) {
        return a(this.N.getString(i));
    }

    public b a(String str) {
        this.a.setText(str);
        return this;
    }

    public b b(String str) {
        this.c.show();
        this.c.setText(str);
        return this;
    }

    public b b(int i) {
        this.c.show();
        this.c.setText(i);
        return this;
    }

    public b c(int i) {
        this.c.setGravity(i);
        return this;
    }

    public b d(int i) {
        return c(this.N.getString(i));
    }

    public b c(String str) {
        this.d.setText(str);
        return this;
    }

    public b e(int i) {
        return d(getContext().getString(i));
    }

    public b d(String str) {
        if (str != null) {
            this.e.setVisibility(0);
            this.e.setText(str);
        }
        return this;
    }

    public b b() {
        if (this.e.getVisibility() != 8) {
            this.e.setVisibility(8);
        }
        return this;
    }

    public b c() {
        this.e.setVisibility(0);
        return this;
    }

    public b d() {
        if (this.d.getVisibility() != 8) {
            this.d.setVisibility(8);
        }
        return this;
    }

    public b e() {
        this.d.setVisibility(0);
        return this;
    }

    public b f() {
        this.b.hide();
        return this;
    }

    public b g() {
        this.b.show();
        return this;
    }

    public b a(DialogInterface.OnClickListener onClickListener) {
        this.f = onClickListener;
        return this;
    }

    public b b(DialogInterface.OnClickListener onClickListener) {
        this.g = onClickListener;
        return this;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (R.id.sl == id) {
            dismiss();
        } else if (R.id.ih == id) {
            if (this.f != null) {
                this.f.onClick(this, 0);
            } else {
                dismiss();
            }
        } else if (R.id.ii != id) {
        } else {
            if (this.g != null) {
                this.g.onClick(this, 1);
            } else {
                dismiss();
            }
        }
    }
}
