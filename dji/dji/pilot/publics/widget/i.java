package dji.pilot.publics.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import dji.pilot.R;
import dji.pilot.fpv.model.b;
import dji.pilot.publics.objects.c;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class i extends c implements OnClickListener {
    private DJITextView a = null;
    private DJIImageView b = null;
    private DJILinearLayout c = null;
    private DJITextView d = null;
    private ProgressBar e = null;
    private DJILinearLayout f = null;
    private Button g = null;
    private Button h = null;
    private DialogInterface.OnClickListener i = null;
    private DialogInterface.OnClickListener j = null;
    private DJITextView k;

    public static i a(Context context, int i, String str, int i2) {
        return new i(context).a(i).b(str).d(i2);
    }

    public i(Context context) {
        super(context);
        b();
    }

    public i a(int i) {
        this.a.setText(i);
        return this;
    }

    public i a(String str) {
        this.a.setText(str);
        return this;
    }

    public i b(int i) {
        this.c.setVisibility(i);
        return this;
    }

    public i c(int i) {
        this.d.setText(i);
        return this;
    }

    public i b(String str) {
        this.d.setText(str);
        return this;
    }

    public i c(String str) {
        this.k.setText(str);
        return this;
    }

    public i d(int i) {
        if (this.e.getProgress() != i) {
            this.e.setProgress(i);
        }
        return this;
    }

    public i e(int i) {
        this.f.setVisibility(i);
        return this;
    }

    public i f(int i) {
        return d(this.N.getString(i));
    }

    public i d(String str) {
        this.g.setVisibility(0);
        this.g.setText(str);
        return this;
    }

    public i a(DialogInterface.OnClickListener onClickListener) {
        this.i = onClickListener;
        return this;
    }

    public i g(int i) {
        return e(this.N.getString(i));
    }

    public i e(String str) {
        this.h.setText(str);
        return this;
    }

    public i b(DialogInterface.OnClickListener onClickListener) {
        this.j = onClickListener;
        return this;
    }

    private void b() {
        setContentView(R.layout.numberpgb_dlg_view);
        this.a = (DJITextView) findViewById(R.id.sj);
        this.b = (DJIImageView) findViewById(R.id.sl);
        this.c = (DJILinearLayout) findViewById(R.id.bfs);
        this.d = (DJITextView) findViewById(R.id.bft);
        this.k = (DJITextView) findViewById(R.id.bfv);
        this.e = (ProgressBar) findViewById(R.id.bfu);
        this.f = (DJILinearLayout) findViewById(R.id.bfw);
        this.g = (Button) findViewById(R.id.bfx);
        this.h = (Button) findViewById(R.id.bfy);
        this.b.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
    }

    protected void onCreate(Bundle bundle) {
        a(b.a(this.N, R.dimen.ls), -2, 0, 17, false, false);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.sl) {
            dismiss();
        } else if (R.id.bfx == id) {
            if (this.i != null) {
                this.i.onClick(this, 0);
            } else {
                dismiss();
            }
        } else if (R.id.bfy != id) {
        } else {
            if (this.j != null) {
                this.j.onClick(this, 1);
            } else {
                dismiss();
            }
        }
    }
}
