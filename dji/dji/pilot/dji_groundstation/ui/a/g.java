package dji.pilot.dji_groundstation.ui.a;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.f;
import dji.publics.DJIUI.DJITextView;

public class g extends e implements OnClickListener {
    private static final String a = "GSDisclaimer";
    private static final String g = "show_disclaimer";
    private DJITextView h = null;
    private DJITextView i = null;
    private DJITextView j = null;
    private DJITextView k = null;
    private DialogInterface.OnClickListener l = null;
    private DialogInterface.OnClickListener m = null;

    public g(Context context) {
        super(context);
        setContentView(R.layout.fm_introduce_view);
        this.h = (DJITextView) findViewById(R.id.fm_dlg_title_tv);
        this.i = (DJITextView) findViewById(R.id.fm_dlg_content_tv);
        this.j = (DJITextView) findViewById(R.id.fm_dlg_disagree);
        this.k = (DJITextView) findViewById(R.id.fm_dlg_agree);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
    }

    public g a(String str) {
        this.h.setText(str);
        return this;
    }

    public g b(String str) {
        this.i.setText(str);
        return this;
    }

    public g a(DialogInterface.OnClickListener onClickListener) {
        this.l = onClickListener;
        return this;
    }

    public g b(DialogInterface.OnClickListener onClickListener) {
        this.m = onClickListener;
        return this;
    }

    public g c(String str) {
        this.k.setText(str);
        return this;
    }

    public g d(String str) {
        this.j.setText(str);
        return this;
    }

    public g a() {
        this.j.go();
        return this;
    }

    protected void onCreate(Bundle bundle) {
        int a = (int) f.a(33.0d, getContext());
        int a2 = ((((int) f.a(80.0d, getContext())) - a) / 2) + a;
        a((int) (((float) f.b(getContext())) * 0.66f), f.c(getContext()) - ((int) f.a(80.0d, getContext())), a2, 49, false, false);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fm_dlg_disagree) {
            if (this.l != null) {
                this.l.onClick(this, 0);
            }
            dismiss();
        } else if (id == R.id.fm_dlg_agree) {
            if (this.m != null) {
                this.m.onClick(this, 1);
            } else {
                dismiss();
            }
            dji.pilot.publics.objects.g.a(this.b, g, true);
            dji.pilot.dji_groundstation.controller.f.getInstance(this.b).a(this.b);
        }
    }

    public void show() {
        a(new Runnable(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void run() {
                if (dji.pilot.publics.objects.g.b(this.a.b, g.g, false)) {
                    dji.pilot.dji_groundstation.controller.f.getInstance(this.a.b).a(this.a.b);
                } else if (!this.a.isShowing()) {
                    super.show();
                }
            }
        });
    }
}
