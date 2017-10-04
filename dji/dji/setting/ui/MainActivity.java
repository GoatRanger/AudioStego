package dji.setting.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.pilot.setting.ui.R;
import dji.thirdparty.a.c;

public class MainActivity extends Activity implements OnClickListener {
    private b a;
    private TextView b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.setting_activity);
        findViewById(R.id.btn_1).setOnClickListener(this);
        this.b = (TextView) findViewById(R.id.status);
        c.a().a(this);
        if (ServiceManager.getInstance().isRemoteOK()) {
            this.b.setText("Aircraft Connected");
        } else {
            this.b.setText("Aircraft Disconnected");
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        c.a().d(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_1) {
            if (this.a == null) {
                this.a = new b(this);
                this.a.d = false;
            }
            this.a.show();
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.b) {
            this.b.setText("飞行器已连接");
        } else if (oVar == o.a) {
            this.b.setText("飞行器未连接");
        }
    }
}
