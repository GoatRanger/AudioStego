package dji.pilot.publics.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import dji.pilot.R;
import dji.pilot.publics.objects.c;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;

public class e extends c implements OnClickListener {
    private DJITextView a = null;
    private DJITextView b = null;
    private DJIImageView c = null;
    private CheckBox d = null;
    private DJITextView e = null;
    private DialogInterface.OnClickListener f = null;

    public e(Context context) {
        super(context);
        c();
    }

    public e a(int i) {
        this.a.setText(i);
        return this;
    }

    public e b(int i) {
        this.b.setText(i);
        return this;
    }

    public e c(int i) {
        this.c.setImageResource(i);
        return this;
    }

    public e a(DialogInterface.OnClickListener onClickListener) {
        this.f = onClickListener;
        return this;
    }

    public e a(boolean z) {
        this.d.setVisibility(z ? 0 : 4);
        return this;
    }

    public boolean b() {
        return this.d.isChecked();
    }

    private void c() {
        setContentView(R.layout.fullscreen_img_view);
        this.a = (DJITextView) findViewById(R.id.afd);
        this.b = (DJITextView) findViewById(R.id.afe);
        this.c = (DJIImageView) findViewById(R.id.aff);
        this.d = (CheckBox) findViewById(R.id.afg);
        this.e = (DJITextView) findViewById(R.id.afh);
        this.e.setOnClickListener(this);
    }

    protected void onCreate(Bundle bundle) {
        a(-1, -1, 0, 17, false, false);
    }

    public void onClick(View view) {
        if (R.id.afh != view.getId()) {
            return;
        }
        if (this.f != null) {
            this.f.onClick(this, 0);
        } else {
            dismiss();
        }
    }
}
