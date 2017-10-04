package dji.pilot.fpv.flightmode;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import dji.pilot.R;
import dji.pilot.fpv.model.b;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.objects.c;
import dji.publics.DJIUI.DJITextView;

public class a extends c implements OnClickListener, OnCheckedChangeListener {
    private DJITextView a = null;
    private DJITextView b = null;
    private CheckBox c = null;
    private DJITextView d = null;
    private DJITextView e = null;
    private DialogInterface.OnClickListener f = null;
    private DialogInterface.OnClickListener g = null;

    public a(Context context) {
        super(context);
        setContentView(R.layout.fm_introduce_view);
        this.a = (DJITextView) findViewById(R.id.tm);
        this.b = (DJITextView) findViewById(R.id.tn);
        this.c = (CheckBox) findViewById(R.id.to);
        this.d = (DJITextView) findViewById(R.id.tp);
        this.e = (DJITextView) findViewById(R.id.tq);
        this.c.setOnCheckedChangeListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    public a a(String str) {
        this.a.setText(str);
        return this;
    }

    public a b(String str) {
        this.b.setText(str);
        return this;
    }

    public a a(DialogInterface.OnClickListener onClickListener) {
        this.f = onClickListener;
        return this;
    }

    public a b(DialogInterface.OnClickListener onClickListener) {
        this.g = onClickListener;
        return this;
    }

    public a c(String str) {
        this.e.setText(str);
        return this;
    }

    public a d(String str) {
        this.d.setText(str);
        return this;
    }

    public a b() {
        this.d.go();
        return this;
    }

    protected void onCreate(Bundle bundle) {
        int a = b.a(this.N, R.dimen.h4);
        int a2 = b.a(this.N, R.dimen.n5);
        int i = ((a - a2) / 2) + a2;
        a((int) (((float) DJIBaseActivity.screenWidth) * 0.66f), DJIBaseActivity.screenHeight - b.a(this.N, R.dimen.h4), i, 49, false, false);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tp) {
            if (this.f != null) {
                this.f.onClick(this, 0);
            } else {
                dismiss();
            }
        } else if (id != R.id.tq) {
        } else {
            if (this.g != null) {
                this.g.onClick(this, 1);
            } else {
                dismiss();
            }
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton != this.c) {
        }
    }
}
