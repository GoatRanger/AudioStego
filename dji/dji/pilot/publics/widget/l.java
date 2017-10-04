package dji.pilot.publics.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import dji.pilot.R;
import dji.pilot.fpv.model.b;
import dji.pilot.publics.objects.c;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class l extends c implements OnClickListener {
    private DJILinearLayout a = null;
    private Button b = null;
    private Button c = null;
    private DialogInterface.OnClickListener d = null;
    private DialogInterface.OnClickListener e = null;
    private DJITextView f;
    private DJILinearLayout g;

    public l(Context context) {
        super(context);
        b();
    }

    public l a(int i) {
        this.a.setVisibility(i);
        return this;
    }

    public l b(int i) {
        this.f.setText(i);
        return this;
    }

    public l c(int i) {
        return a(this.N.getString(i));
    }

    public l a(String str) {
        this.a.show();
        this.b.setVisibility(0);
        this.b.setText(str);
        return this;
    }

    public l a(DialogInterface.OnClickListener onClickListener) {
        this.d = onClickListener;
        return this;
    }

    public l d(int i) {
        return b(this.N.getString(i));
    }

    public l b(String str) {
        this.a.show();
        this.c.setVisibility(0);
        this.c.setText(str);
        return this;
    }

    public l b(DialogInterface.OnClickListener onClickListener) {
        this.e = onClickListener;
        return this;
    }

    public l a(boolean z) {
        if (z) {
            this.g.setBackgroundResource(R.drawable.top_radius_corner_bg);
        }
        return this;
    }

    private void b() {
        setContentView(R.layout.text_dlg_view);
        this.f = (DJITextView) findViewById(R.id.c0a);
        this.a = (DJILinearLayout) findViewById(R.id.c0b);
        this.b = (Button) findViewById(R.id.c0c);
        this.c = (Button) findViewById(R.id.c0d);
        this.g = (DJILinearLayout) findViewById(R.id.ap3);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
    }

    protected void onCreate(Bundle bundle) {
        a(b.a(this.N, R.dimen.ls), -2, 0, 17, false, false);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (R.id.c0c == id) {
            if (this.d != null) {
                this.d.onClick(this, 0);
            } else {
                dismiss();
            }
        } else if (R.id.c0d != id) {
        } else {
            if (this.e != null) {
                this.e.onClick(this, 1);
            } else {
                dismiss();
            }
        }
    }
}
