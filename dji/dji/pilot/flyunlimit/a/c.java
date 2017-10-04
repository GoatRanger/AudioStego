package dji.pilot.flyunlimit.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.d.c$p;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.nativeexplore.model.ExploreEvent;
import dji.pilot2.usercenter.a.a;
import dji.pilot2.usercenter.a.a.b;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;

public class c extends dji.pilot.publics.objects.c {
    private a a;
    private DJIRelativeLayout b;
    private OnClickListener c = new OnClickListener(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view.getId() == R.id.ie) {
                this.a.dismiss();
            } else if (view.getId() == R.id.bf7) {
                Intent intent = new Intent(this.a.getContext(), DJIAccountSignActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean(DJIAccountSignActivity.c, true);
                intent.putExtras(bundle);
                this.a.getContext().startActivity(intent);
            }
        }
    };
    private a.a d = new a.a(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void a() {
            dji.thirdparty.a.c.a().e(ExploreEvent.USER_LOGIN);
            this.a.dismiss();
        }

        public void b() {
        }

        public void c() {
            e.a("UserCenter_TopBarView_Button_BackHome");
        }
    };
    private dji.pilot2.usercenter.a.a.c e = new dji.pilot2.usercenter.a.a.c(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void a() {
        }
    };

    public c(Context context) {
        super(context);
        setContentView(R.layout.nfz_login_dialog);
        this.b = (DJIRelativeLayout) findViewById(R.id.bf0);
        this.a = new a(getContext(), this.b, b.NORMAL);
        this.a.a(true);
        this.a.a(this.d);
        this.a.a(this.e);
        findViewById(R.id.ie).setOnClickListener(this.c);
        findViewById(R.id.bf7).setOnClickListener(this.c);
        e.c(c$p.d);
    }

    protected void onCreate(Bundle bundle) {
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            a((DJIBaseActivity.screenWidth * 3) / 5, (DJIBaseActivity.screenHeight * 9) / 10, 0, 17, true, false);
            return;
        }
        a(DJIBaseActivity.screenWidth / 2, (DJIBaseActivity.screenHeight * 8) / 10, 0, 17, true, false);
    }

    protected void onStart() {
        super.onStart();
        this.a.a();
        e.b(getContext());
        dji.thirdparty.a.c.a().a(this);
    }

    protected void onStop() {
        this.a.b();
        dji.thirdparty.a.c.a().d(this);
        super.onStop();
        e.c(getContext());
    }
}
