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
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;

public class d extends c implements OnClickListener {
    private DJITextView a = null;
    private DJIImageView b = null;
    private DJIEditText c = null;
    private Button d = null;
    private Button e = null;
    private DialogInterface.OnClickListener f = null;
    private DialogInterface.OnClickListener g = null;

    public static d a(Context context, int i, int i2, DialogInterface.OnClickListener onClickListener, int i3, DialogInterface.OnClickListener onClickListener2) {
        return a(context, context.getString(i), context.getString(i2), onClickListener, context.getString(i3), onClickListener2);
    }

    public static d a(Context context, String str, String str2, DialogInterface.OnClickListener onClickListener, String str3, DialogInterface.OnClickListener onClickListener2) {
        return new d(context).a(str).c(str2).a(onClickListener).d(str3).b(onClickListener2);
    }

    public d(Context context) {
        super(context);
        d();
    }

    private void d() {
        setContentView(R.layout.edit_dlg_view);
        this.a = (DJITextView) findViewById(R.id.sj);
        this.b = (DJIImageView) findViewById(R.id.sl);
        this.c = (DJIEditText) findViewById(R.id.sx);
        this.d = (Button) findViewById(R.id.sy);
        this.e = (Button) findViewById(R.id.sz);
        this.b.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    public d b() {
        this.b.go();
        return this;
    }

    public String c() {
        return this.c.getText().toString();
    }

    public d a(int i) {
        return a(this.N.getString(i));
    }

    public d a(String str) {
        this.a.setText(str);
        return this;
    }

    public d b(String str) {
        this.c.setText(str);
        return this;
    }

    public d b(int i) {
        this.c.setText(i);
        return this;
    }

    public d c(int i) {
        this.c.setHint(i);
        return this;
    }

    public d d(int i) {
        return c(this.N.getString(i));
    }

    public d c(String str) {
        this.d.setText(str);
        return this;
    }

    public d e(int i) {
        return d(getContext().getString(i));
    }

    public d d(String str) {
        this.e.setText(str);
        return this;
    }

    public d a(DialogInterface.OnClickListener onClickListener) {
        this.f = onClickListener;
        return this;
    }

    public d b(DialogInterface.OnClickListener onClickListener) {
        this.g = onClickListener;
        return this;
    }

    protected void onCreate(Bundle bundle) {
        a(b.a(this.N, R.dimen.lv), -2, 0, 17, true, true);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (R.id.sl == id) {
            dismiss();
        } else if (R.id.sy == id) {
            if (this.f != null) {
                this.f.onClick(this, 0);
            } else {
                dismiss();
            }
        } else if (R.id.sz != id) {
        } else {
            if (this.g != null) {
                this.g.onClick(this, 1);
            } else {
                dismiss();
            }
        }
    }
}
