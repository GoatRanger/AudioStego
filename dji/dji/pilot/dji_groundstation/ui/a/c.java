package dji.pilot.dji_groundstation.ui.a;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.dji.frame.c.c.a;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.controller.d;
import dji.pilot.publics.objects.g;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class c extends e implements OnClickListener {
    private static final String a = "show_terrain_tracking_info";
    private DJITextView g = null;
    private DJITextView h;
    private DJIImageView i;
    private DJIImageView j;
    private DJILinearLayout k = null;
    private DJITextView l;
    private DJITextView m;
    private DJIImageView n = null;
    private DialogInterface.OnClickListener o;
    private DialogInterface.OnClickListener p;
    private boolean q = true;
    private boolean r = false;
    private boolean s = false;

    public c(Context context, boolean z) {
        super(context);
        this.s = z;
        g();
    }

    public void a(boolean z) {
        this.q = z;
    }

    public c a(int i) {
        this.i.setImageResource(i);
        this.i.show();
        return this;
    }

    public c a(String str) {
        this.g.setText(str);
        return this;
    }

    public c b(String str) {
        this.h.show();
        this.h.setText(str);
        return this;
    }

    public c a() {
        this.h.go();
        return this;
    }

    public c b() {
        this.k.go();
        this.l.go();
        this.m.go();
        this.n.go();
        return this;
    }

    public c b(int i) {
        return c(this.b.getString(i));
    }

    public c c(String str) {
        this.k.show();
        this.n.go();
        this.j.show();
        this.l.setVisibility(0);
        this.l.setText(str);
        return this;
    }

    public c c(int i) {
        return d(getContext().getString(i));
    }

    private c d(String str) {
        this.k.show();
        this.n.show();
        this.j.show();
        this.m.setVisibility(0);
        this.m.setText(str);
        return this;
    }

    public c a(DialogInterface.OnClickListener onClickListener) {
        this.o = onClickListener;
        return this;
    }

    public c b(DialogInterface.OnClickListener onClickListener) {
        this.p = onClickListener;
        return this;
    }

    private void g() {
        setContentView(R.layout.terrain_tracking_info_dlg_view_new);
        this.g = (DJITextView) findViewById(R.id.img_dlg_title);
        this.i = (DJIImageView) findViewById(R.id.img_dlg_icon);
        this.j = (DJIImageView) findViewById(R.id.img_dlg_content_divider_img);
        this.h = (DJITextView) findViewById(R.id.img_dlg_content_tv);
        this.k = (DJILinearLayout) findViewById(R.id.img_dlg_btn_ly);
        this.l = (DJITextView) findViewById(R.id.img_dlg_left_btn);
        this.m = (DJITextView) findViewById(R.id.img_dlg_right_btn);
        this.n = (DJIImageView) findViewById(R.id.img_dlg_btn_divider_img);
        this.k.go();
        this.n.go();
        this.i.go();
        this.j.go();
        a(R.drawable.gs_terrain_tracking_help_image);
        a(getContext().getResources().getString(R.string.gsnew_gs_terrain_tracking_help_title));
        b(getContext().getResources().getString(R.string.gsnew_gs_terrain_tracking_help_desc));
        c(R.string.gsnew_gs_terrain_tracking_help_i_know);
        b(R.string.gsnew_gs_terrain_tracking_help_dont_show_again);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        com.dji.frame.c.c.a(getWindow());
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.thirdparty.a.c.a().e(a.a);
    }

    protected void onCreate(Bundle bundle) {
        a(-1, -1, 0, 17, false, false);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (R.id.dlg_titlebar_close_img == id) {
            dismiss();
        } else if (R.id.img_dlg_left_btn == id) {
            if (this.o != null) {
                this.o.onClick(this, 0);
            }
            g.a(getContext(), a, false);
            dismiss();
        } else if (R.id.img_dlg_right_btn == id) {
            if (this.p != null) {
                this.p.onClick(this, 1);
            }
            if (this.s) {
                d.getInstance().b(dji.pilot.dji_groundstation.a.d.c.t);
            }
            dismiss();
        }
    }

    public void c() {
        this.r = g.b(this.b, a, false);
        if (!this.r) {
            show();
        } else if (this.s) {
            d.getInstance().b(dji.pilot.dji_groundstation.a.d.c.t);
        }
    }
}
