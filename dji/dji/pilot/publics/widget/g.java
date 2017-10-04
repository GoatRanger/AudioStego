package dji.pilot.publics.widget;

import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import com.dji.frame.c.c;
import com.dji.frame.c.c.a;
import dji.pilot.R;
import dji.pilot.publics.objects.b;
import dji.publics.DJIUI.DJITextView;

public class g extends b {
    private DJITextView b;
    private DJITextView c;
    private ProgressBar d;

    public g(Context context) {
        super(context, R.style.c6);
        a();
    }

    public g a(int i) {
        this.c.setText(i + "%");
        this.d.setProgress(i);
        return this;
    }

    public g a(String str) {
        this.b.setText(str);
        return this;
    }

    private void a() {
        setContentView(R.layout.icon_dlg_progress_view);
        this.b = (DJITextView) findViewById(R.id.awu);
        this.c = (DJITextView) findViewById(R.id.awv);
        this.d = (ProgressBar) findViewById(R.id.aww);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        c.a(getWindow());
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.thirdparty.a.c.a().e(a.a);
    }

    protected void onCreate(Bundle bundle) {
        a(dji.pilot.fpv.model.b.a(this.a, R.dimen.lt), -2, 0, 17, false, false);
    }
}
