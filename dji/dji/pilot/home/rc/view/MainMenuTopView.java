package dji.pilot.home.rc.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import dji.midware.data.manager.P3.ServiceManager;
import dji.pilot.R;
import dji.pilot.home.rc.activity.DJIRcSettingActivity;
import dji.pilot.usercenter.b.f;
import dji.pilot2.academy.activity.DJINewAcademyActivity;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.usercenter.activity.DJIFlightRecordActivity;
import dji.publics.DJIUI.DJIImageButton;
import dji.setting.a.a;

public class MainMenuTopView extends LinearLayout implements OnClickListener {
    private Context a;
    private DJIImageButton b;
    private DJIImageButton c;
    private DJIImageButton d;

    public MainMenuTopView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        a();
    }

    private void a() {
        a.a((View) this, (int) R.layout.rc_main_top_menu_view);
        if (!isInEditMode()) {
            this.b = (DJIImageButton) findViewById(R.id.bkb);
            this.c = (DJIImageButton) findViewById(R.id.bkc);
            this.d = (DJIImageButton) findViewById(R.id.bkd);
            this.b.setOnClickListener(this);
            this.c.setOnClickListener(this);
            this.d.setOnClickListener(this);
        }
    }

    private void b() {
        if (f.getInstance().c()) {
            this.a.startActivity(new Intent(this.a, DJIFlightRecordActivity.class));
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this.a, DJIAccountSignActivity.class);
        intent.putExtra(DJIAccountSignActivity.a, 1006);
        this.a.startActivity(intent);
    }

    private void c() {
        Intent intent = new Intent(this.a, DJINewAcademyActivity.class);
        if (ServiceManager.getInstance().isRemoteOK()) {
            intent.putExtra("key_product_index", -1);
        } else {
            intent.putExtra("key_product_index", 0);
        }
        this.a.startActivity(intent);
    }

    private void d() {
        this.a.startActivity(new Intent(this.a, DJIRcSettingActivity.class));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bkb:
                c();
                return;
            case R.id.bkc:
                b();
                return;
            case R.id.bkd:
                d();
                return;
            default:
                return;
        }
    }
}
