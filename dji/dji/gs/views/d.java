package dji.gs.views;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import dji.gs.R;
import dji.gs.c.e;
import dji.gs.utils.c;

public class d extends PopupWindow {
    private LayoutParams a;
    private TextView b;
    private TextView c;
    private e d;
    private int e;
    private dji.gs.e.e f;
    private TextView g;
    private Context h;
    private View i;

    public d(Context context, e eVar, View view) {
        this.h = context;
        this.i = view;
        View inflate = LayoutInflater.from(context).inflate(R.layout.gs_marker_show, null, false);
        setContentView(inflate);
        setWindowLayoutMode(-2, -2);
        setAnimationStyle(R.style.dialogWindowAnim);
        setBackgroundDrawable(new BitmapDrawable(context.getResources()));
        setFocusable(true);
        setOutsideTouchable(true);
        this.b = (TextView) inflate.findViewById(R.id.gs_marker_show_direction);
        this.c = (TextView) inflate.findViewById(R.id.gs_marker_show_height);
        this.g = (TextView) inflate.findViewById(R.id.gs_marker_info_title);
        this.d = eVar;
    }

    public void a(int i, int i2) {
        a();
        showAtLocation(this.i, 21, 0, 0);
    }

    public void dismiss() {
        this.d.u();
        super.dismiss();
    }

    private void a() {
        this.e = this.d.e();
        this.f = this.d.b(this.e).getInfo();
        this.g.setText(c.a(this.h, R.string.gs_manager_point_info) + this.e);
        this.b.setText(this.f.d().toString());
    }
}
