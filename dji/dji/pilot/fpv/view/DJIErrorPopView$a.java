package dji.pilot.fpv.view;

import android.content.Context;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.fpv.view.DJIErrorPopView.c;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

final class DJIErrorPopView$a {
    public DJILinearLayout a;
    public DJIImageView b;
    public DJITextView c;
    public DJITextView d;
    public DJIImageView e;
    public int f;
    public final b g;

    private DJIErrorPopView$a() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = 0;
        this.g = new b();
    }

    public final boolean a() {
        return this.g.f == c.c;
    }

    public void b() {
        Context context = this.a.getContext();
        if (d.c == this.g.a) {
            this.a.setBackgroundColor(context.getResources().getColor(R.color.cz));
            this.c.setTextColor(context.getResources().getColor(R.color.om));
            this.b.setImageResource(R.drawable.osd_error_tips_dangerous_icon);
            this.b.show();
        } else if (d.b == this.g.a) {
            this.a.setBackgroundColor(context.getResources().getColor(R.color.d_));
            this.c.setTextColor(context.getResources().getColor(R.color.d1));
            this.b.setImageResource(R.drawable.errpop_warning_icon);
            this.b.show();
        } else if (d.a == this.g.a) {
            this.a.setBackgroundColor(context.getResources().getColor(R.color.d_));
            this.c.setTextColor(context.getResources().getColor(R.color.d0));
            this.b.setImageResource(R.drawable.osd_error_tips_notify_icon);
            this.b.show();
        } else {
            this.a.setBackgroundColor(context.getResources().getColor(R.color.d_));
            this.b.go();
        }
        if (this.g.f == c.c) {
            this.e.show();
            this.e.setTag(String.valueOf(this.f));
        } else {
            this.e.go();
            this.e.setTag(null);
        }
        if (this.g.b != 0) {
            this.c.setText(this.g.b);
            this.c.show();
        } else if (dji.pilot.publics.e.d.a(this.g.c)) {
            this.c.go();
        } else {
            this.c.setText(this.g.c);
            this.c.show();
        }
        if (this.g.d != 0) {
            this.d.setText(this.g.d);
            this.d.show();
        } else if (dji.pilot.publics.e.d.a(this.g.e)) {
            this.d.go();
        } else {
            this.d.setText(this.g.e);
            this.d.show();
        }
    }
}
