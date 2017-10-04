package dji.pilot.publics.control.p3cupgrade;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetDeviceStatus;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.m;
import dji.pilot.R;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;

public class UpgradeTestActivity extends Activity {
    public final String a = "UpgradeTestActivity";
    private Button b;
    private Button c;
    private b d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.upgrade_test_activity);
        this.b = (Button) findViewById(R.id.c1m);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UpgradeTestActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.c();
            }
        });
        this.c = (Button) findViewById(R.id.c1n);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UpgradeTestActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b();
            }
        });
        this.c = (Button) findViewById(R.id.c1o);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UpgradeTestActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a();
            }
        });
    }

    private void a() {
        e dataCommonGetDeviceStatus = new DataCommonGetDeviceStatus();
        dataCommonGetDeviceStatus.setReceiveType(DeviceType.find(9)).setReceiveId(1).setVersioin(0, 0);
        new m(dataCommonGetDeviceStatus, 10, new d(this) {
            final /* synthetic */ UpgradeTestActivity a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("DataCommonGetDeviceStatus", "success");
            }

            public void onFailure(a aVar) {
                DJILogHelper.getInstance().LOGD("DataCommonGetDeviceStatus", "onFailure");
            }
        }).a();
    }

    private void b() {
        this.d.d();
    }

    private void c() {
        this.d = new b();
        DJIUpgradePack dJIUpgradePack = new DJIUpgradePack();
        dJIUpgradePack.m0400 = "01.30.01.18&0";
        dJIUpgradePack.m0401 = "01.30.01.18&0";
        dJIUpgradePack.m0402 = "01.30.01.18&0";
        dJIUpgradePack.m0403 = "01.30.01.18&0";
        dJIUpgradePack.m0700 = "01.03.01.02&0";
        dJIUpgradePack.m0900 = "00.00.00.89&0";
        dJIUpgradePack.m0901 = "00.00.00.08&0";
        this.d.b(true);
        b bVar = this.d;
        b.b = true;
        this.d.a(dJIUpgradePack, ProductType.LonganMobile);
    }
}
