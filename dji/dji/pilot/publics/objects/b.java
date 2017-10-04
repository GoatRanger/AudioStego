package dji.pilot.publics.objects;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager.LayoutParams;
import com.dji.frame.c.c.a;
import dji.pilot.R;
import dji.thirdparty.a.c;

public class b extends AlertDialog {
    protected Context a;

    public b(Context context) {
        this(context, R.style.c6);
    }

    public b(Context context, int i) {
        super(context, i);
        this.a = null;
        this.a = context;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().e(a.a);
    }

    public void setContentView(int i) {
        super.setContentView(i);
    }

    protected void c() {
        a(dji.pilot.fpv.model.b.a(this.a, R.dimen.l0), DJIBaseActivity.screenHeight - dji.pilot.fpv.model.b.a(this.a, R.dimen.kz), 0, 17, true, true);
    }

    public void d() {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.flags |= 32;
        getWindow().setAttributes(attributes);
    }

    public void b(boolean z) {
        setCancelable(z);
        setCanceledOnTouchOutside(z);
    }

    public void a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = i;
        attributes.height = i2;
        attributes.y = i3;
        attributes.dimAmount = 0.0f;
        attributes.gravity = i4;
        getWindow().setAttributes(attributes);
        setCancelable(z);
        setCanceledOnTouchOutside(z2);
    }

    protected boolean e() {
        return false;
    }
}
