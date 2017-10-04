package dji.pilot2.upgrade.ble;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.midware.b.a.e;
import dji.midware.b.b;
import dji.midware.b.c;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.p;
import dji.pilot.R;
import dji.pilot2.upgrade.b.d;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.b.b.a;
import java.util.ArrayList;
import java.util.List;

public class BleBannerView extends DJIRelativeLayout implements e {
    public static List<b> a = null;
    private static final String b = "BleBannerView";
    private static final String f = "SHOW_BLE_BINNER";
    private TextView c;
    private boolean d = true;
    private c e;
    private Runnable g = new Runnable(this) {
        final /* synthetic */ BleBannerView a;

        {
            this.a = r1;
        }

        public void run() {
            if (BleBannerView.a.size() == 1) {
                this.a.c.setVisibility(0);
                this.a.c.setText(" (" + c.getInstance().f().a(((b) BleBannerView.a.get(0)).a) + ")");
            } else if (BleBannerView.a.size() > 1) {
                this.a.c.setVisibility(8);
            }
            this.a.show();
        }
    };

    public BleBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a = new ArrayList();
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ BleBannerView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!c.getInstance().c()) {
                    c.getInstance().b();
                }
                this.a.getContext().startActivity(new Intent(this.a.getContext(), BleDevicesActivity.class));
                a.getInstance().a("createtime", System.currentTimeMillis() + "", false).a("action", "2", false).a("pageid", "1", false).a("device_ver", "", false).a("device_type", i.getInstance().c()._name(), true);
            }
        });
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (VERSION.SDK_INT >= 19) {
            this.c = (TextView) findViewById(R.id.cpd);
            this.e = c.getInstance();
            this.e.f().a(this);
            dji.thirdparty.a.c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.thirdparty.a.c.a().d(this);
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.b) {
            hide();
            this.d = false;
        } else if (pVar == p.a) {
            this.d = true;
        }
    }

    public void onScanResultUpdate(ArrayList<b> arrayList) {
        if (this.d && !this.e.isConnected() && !arrayList.isEmpty()) {
            b.getInstance().a(arrayList);
            a.clear();
            a.addAll(arrayList);
            post(this.g);
        }
    }

    public void onEventMainThread(d dVar) {
        DJILogHelper.getInstance().LOGD(b, "machine status = " + dVar);
        switch (dVar) {
            case UpgradeFinish:
            case Upgrading:
            case NeedUpgrade:
                hide();
                this.d = false;
                return;
            default:
                return;
        }
    }

    public void show() {
        if (VERSION.SDK_INT >= 21 && dji.midware.util.i.b(getContext(), f, true)) {
            super.show();
            a.getInstance().a("createtime", System.currentTimeMillis() + "", false).a("action", "1", false).a("pageid", "1", false).a("device_ver", "", false).a("device_type", i.getInstance().c()._name(), true);
        }
    }

    public void onEventBackgroundThread(p pVar) {
        if (dji.midware.util.i.b(getContext(), f, true) && pVar == p.b && dji.midware.f.a.getInstance().d() == dji.midware.f.b.g) {
            dji.midware.util.i.a(getContext(), f, false);
        }
    }
}
