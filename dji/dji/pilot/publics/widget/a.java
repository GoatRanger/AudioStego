package dji.pilot.publics.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import dji.pilot.R;
import dji.pilot.publics.objects.b;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;

public class a extends b implements OnClickListener {
    private DJITextView b;
    private DJIImageView c;
    private DJITextView d;
    private Button e;
    private Button f;
    private DialogInterface.OnClickListener g;
    private DialogInterface.OnClickListener h;

    public static a a(Context context, int i, int i2, DialogInterface.OnClickListener onClickListener, int i3, DialogInterface.OnClickListener onClickListener2) {
        return a(context, context.getString(i), context.getString(i2), onClickListener, context.getString(i3), onClickListener2);
    }

    private static a a(Context context, String str, String str2, DialogInterface.OnClickListener onClickListener, String str3, DialogInterface.OnClickListener onClickListener2) {
        return new a(context).a(str).c(str2).a(onClickListener).d(str3).b(onClickListener2);
    }

    public static a a(Context context, int i, int i2, int i3, DialogInterface.OnClickListener onClickListener, int i4, DialogInterface.OnClickListener onClickListener2) {
        return a(context, context.getString(i), context.getString(i2), context.getString(i3), onClickListener, context.getString(i4), onClickListener2);
    }

    public static a a(Context context, String str, String str2, String str3, DialogInterface.OnClickListener onClickListener, String str4, DialogInterface.OnClickListener onClickListener2) {
        return a(context, str, str3, onClickListener, str4, onClickListener2).b(str2);
    }

    public static a a(Context context, int i, int i2, int i3, DialogInterface.OnClickListener onClickListener) {
        return a(context, context.getString(i), context.getString(i2), context.getString(i3), onClickListener, null, null);
    }

    public static a a(Context context, String str, String str2, String str3, DialogInterface.OnClickListener onClickListener) {
        return a(context, str, str3, onClickListener, null, null).b(str2);
    }

    public a(Context context) {
        this(context, R.style.c6);
    }

    public a(Context context, int i) {
        super(context, i);
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        b();
    }

    private void b() {
        setContentView(R.layout.button_dlg_view);
        this.b = (DJITextView) findViewById(R.id.sj);
        this.c = (DJIImageView) findViewById(R.id.sl);
        this.d = (DJITextView) findViewById(R.id.if);
        this.e = (Button) findViewById(R.id.ih);
        this.f = (Button) findViewById(R.id.ii);
        this.f.setVisibility(8);
        this.c.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
    }

    protected void onCreate(Bundle bundle) {
        a(dji.pilot.fpv.model.b.a(this.a, R.dimen.lt), -2, 0, 17, false, false);
    }

    public void a(boolean z) {
        if (z) {
            this.c.hide();
        } else {
            this.c.go();
        }
    }

    public a a(int i) {
        return a(this.a.getString(i));
    }

    public a a(String str) {
        this.b.setText(str);
        return this;
    }

    public a b(String str) {
        this.d.show();
        this.d.setText(str);
        return this;
    }

    public a b(int i) {
        return c(this.a.getString(i));
    }

    public a c(String str) {
        this.e.setText(str);
        return this;
    }

    public a c(int i) {
        return d(getContext().getString(i));
    }

    public a d(String str) {
        if (str != null) {
            this.f.setVisibility(0);
            this.f.setText(str);
        } else {
            this.f.setVisibility(8);
        }
        return this;
    }

    public a a() {
        this.c.hide();
        return this;
    }

    public a a(DialogInterface.OnClickListener onClickListener) {
        if (onClickListener == null) {
            this.g = new DialogInterface.OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.dismiss();
                }
            };
        } else {
            this.g = onClickListener;
        }
        return this;
    }

    public a b(DialogInterface.OnClickListener onClickListener) {
        if (onClickListener == null) {
            this.h = new DialogInterface.OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.dismiss();
                }
            };
        } else {
            this.f.setVisibility(0);
            this.h = onClickListener;
        }
        return this;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (R.id.sl == id) {
            dismiss();
        } else if (R.id.ih == id) {
            if (this.g != null) {
                this.g.onClick(this, 0);
            }
        } else if (R.id.ii == id && this.h != null) {
            this.h.onClick(this, 1);
        }
    }
}
