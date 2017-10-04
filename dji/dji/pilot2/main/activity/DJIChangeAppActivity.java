package dji.pilot2.main.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJIChangeAppActivity extends DJIBaseActivity implements OnClickListener {
    private static final String c = "https://m.dji.com/djigo4";
    protected DJIRelativeLayout a;
    protected DJITextView b;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ProductType.values().length];

        static {
            try {
                a[ProductType.Orange2.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ProductType.Pomato.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main_layout_change_app);
        if (!c.a().c(this)) {
            c.a().a(this);
        }
        this.b = (DJITextView) findViewById(R.id.bdu);
        this.a = (DJIRelativeLayout) findViewById(R.id.bdv);
        this.a.setOnClickListener(this);
        onEventMainThread(i.getInstance().c());
    }

    public void onDestroy() {
        super.onDestroy();
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    public void onBackPressed() {
    }

    public void onEventMainThread(ProductType productType) {
        switch (AnonymousClass1.a[productType.ordinal()]) {
            case 1:
                this.b.setText("Inspire 2");
                return;
            case 2:
                this.b.setText("Phantom 4 Pro");
                return;
            default:
                finish();
                return;
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.a) {
            finish();
        }
    }

    public void onEventBackgroundThread(p pVar) {
        if (pVar == p.a) {
            finish();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bdv:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(c)));
                return;
            default:
                return;
        }
    }
}
