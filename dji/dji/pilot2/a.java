package dji.pilot2;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.model.b;
import dji.pilot.publics.objects.c;
import dji.pilot.publics.widget.DJIScrollTextView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;

public class a extends c implements OnClickListener {
    private DJITextView a = null;
    private DJIScrollTextView b;
    private DJIStateTextView c;
    private DJIStateTextView d;
    private DJIImageView e = null;
    private a f = null;

    public interface a {
        void onLeftBtnClick();

        void onRightBtnClick();
    }

    public a(Context context) {
        super(context);
        setContentView(R.layout.ve_myworks_del_dlg);
        this.a = (DJITextView) findViewById(R.id.d5q);
        this.b = (DJIScrollTextView) findViewById(R.id.d5r);
        this.c = (DJIStateTextView) findViewById(R.id.d5s);
        this.d = (DJIStateTextView) findViewById(R.id.d5u);
        this.e = (DJIImageView) findViewById(R.id.d5t);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    public void a(String str) {
        this.a.setText(str);
    }

    public void b() {
        this.a.go();
    }

    public void b(String str) {
        this.b.setText(str);
    }

    public void c(String str) {
        this.c.setText(str);
    }

    public void d(String str) {
        this.d.setText(str);
    }

    public void c() {
        this.d.go();
        this.e.go();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.d5s:
                if (this.f != null) {
                    this.f.onLeftBtnClick();
                }
                dismiss();
                return;
            case R.id.d5u:
                if (this.f != null) {
                    this.f.onRightBtnClick();
                }
                dismiss();
                return;
            default:
                return;
        }
    }

    protected void m() {
        super.m();
        a(b.a(this.N, R.dimen.g4), -2, 0, 17, true, true);
        setCanceledOnTouchOutside(false);
    }
}
