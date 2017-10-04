package dji.setting.ui.flyc.imu;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager.LayoutParams;
import com.dji.frame.c.c;
import dji.pilot.setting.ui.R;
import dji.setting.ui.flyc.imu.ImuCalView.b;

public class a extends Dialog implements b {
    private ImuCalView a = null;

    public a(Context context) {
        super(context, R.style.setting_ui_dialog);
        a();
    }

    private void a() {
        setContentView(R.layout.setting_ui_imu_cal_view);
        this.a = (ImuCalView) findViewById(R.id.imu_cal_root_view);
        this.a.setOnImuCalListener(this);
    }

    public void a(int i) {
        dismiss();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        getWindow().setAttributes(attributes);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    public void show() {
        getWindow().setFlags(8, 8);
        c.a(getWindow());
        dji.setting.ui.widget.a.a = getWindow().getDecorView().getSystemUiVisibility();
        getWindow().setSoftInputMode(2);
        super.show();
        getWindow().clearFlags(8);
    }
}
