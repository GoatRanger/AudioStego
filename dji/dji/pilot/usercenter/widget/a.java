package dji.pilot.usercenter.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow;
import dji.pilot.R;
import dji.pilot.usercenter.a.b;
import dji.publics.DJIUI.DJIGridView;

public class a extends PopupWindow {
    private DJIGridView a = null;
    private b b = null;
    private int c = 0;

    public a(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater from = LayoutInflater.from(context);
        View inflate = from.inflate(R.layout.share_pw_view, null);
        this.a = (DJIGridView) inflate.findViewById(R.id.bzb);
        this.b = new b(from);
        setContentView(inflate);
        setWidth(-2);
        setHeight(-2);
        setFocusable(true);
        setOutsideTouchable(true);
        setTouchable(true);
        setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(17170445)));
    }

    public a a(int i) {
        this.a.setAdapter(this.b.a(i));
        if (i == 1) {
            this.a.setNumColumns(2);
        }
        return this;
    }

    public a a(OnItemClickListener onItemClickListener) {
        this.a.setOnItemClickListener(onItemClickListener);
        return this;
    }

    public void a(View view, int i, int i2) {
        if (!isShowing()) {
            showAsDropDown(view, i, i2);
        }
    }

    public void a() {
        if (isShowing()) {
            dismiss();
        }
    }
}
