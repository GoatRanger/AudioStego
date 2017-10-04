package dji.pilot.dji_groundstation.ui.a;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import dji.pilot.dji_groundstation.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;

public class h extends e implements OnClickListener {
    private DJITextView a = null;
    private DJITextView g = null;
    private DJIImageView h = null;
    private CheckBox i = null;
    private DJITextView j = null;
    private DialogInterface.OnClickListener k = null;

    public h(Context context) {
        super(context);
        b();
    }

    public h a(int i) {
        this.a.setText(i);
        return this;
    }

    public h b(int i) {
        this.g.setText(i);
        return this;
    }

    public h c(int i) {
        this.h.setImageResource(i);
        return this;
    }

    public h a(DialogInterface.OnClickListener onClickListener) {
        this.k = onClickListener;
        return this;
    }

    public h a(boolean z) {
        this.i.setVisibility(z ? 0 : 4);
        return this;
    }

    public boolean a() {
        return this.i.isChecked();
    }

    private void b() {
        setContentView(R.layout.fullscreen_img_view);
        this.a = (DJITextView) findViewById(R.id.fs_img_title_tv);
        this.g = (DJITextView) findViewById(R.id.fs_img_content_tv);
        this.h = (DJIImageView) findViewById(R.id.fs_img_tip_img);
        this.i = (CheckBox) findViewById(R.id.fs_img_tip_cb);
        this.j = (DJITextView) findViewById(R.id.fs_img_opt_tv);
        this.j.setOnClickListener(this);
    }

    protected void onCreate(Bundle bundle) {
        a(-1, -1, 0, 17, false, false);
    }

    public void onClick(View view) {
        if (R.id.fs_img_opt_tv != view.getId()) {
            return;
        }
        if (this.k != null) {
            this.k.onClick(this, 0);
        } else {
            dismiss();
        }
    }
}
