package dji.pilot.publics.widget;

import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import dji.pilot.R;
import dji.pilot.publics.objects.c;
import dji.publics.DJIUI.DJITextView;

public class k extends c {
    private ProgressBar a;
    private DJITextView b;

    public static k a(Context context, int i) {
        return new k(context).a(i);
    }

    public static k a(Context context, String str) {
        return new k(context).a(str);
    }

    public k(Context context) {
        this(context, R.style.c6);
    }

    public k(Context context, int i) {
        super(context, i);
        this.a = null;
        this.b = null;
        b();
    }

    private void b() {
        setContentView(R.layout.progress_dlg_view);
        this.a = (ProgressBar) findViewById(R.id.bji);
        this.b = (DJITextView) findViewById(R.id.bjj);
    }

    public k a(int i) {
        this.b.setText(i);
        return this;
    }

    public k a(String str) {
        this.b.setText(str);
        return this;
    }

    protected void onCreate(Bundle bundle) {
        a(-2, -2, 0, 17, false, false);
    }
}
