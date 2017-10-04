package dji.pilot2.upgrade;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import dji.dbox.upgrade.p4.b.b;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.dbox.upgrade.p4.statemachine.f;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCommonGetPushUpgradeStatus.DJIUpgradeCompleteReason;
import dji.pilot.R;
import dji.pilot2.upgrade.rollback.DJIRollBackActivity$a;
import dji.publics.DJIUI.DJILinearLayout;
import dji.thirdparty.a.c;

public class DJIPADUpgradeTipBannerView extends DJILinearLayout {
    public static final String a = "DJIPADUpgradeTipBannerView";
    public static boolean b = false;
    private static int h = 0;
    private static int i = 1;
    private String c = "Pomato";
    private String d = "1.0.0.1";
    private TextView e;
    private f f;
    private b g = new b(this) {
        final /* synthetic */ DJIPADUpgradeTipBannerView a;

        {
            this.a = r1;
        }

        public void e() {
        }

        public void g() {
            this.a.n.sendEmptyMessage(7);
        }

        public void f() {
            this.a.n.sendMessage(this.a.n.obtainMessage(2, DJIPADUpgradeTipBannerView.i, 5));
        }

        public void h() {
            this.a.n.sendEmptyMessage(5);
        }

        public void b(int i) {
            this.a.n.sendMessage(this.a.n.obtainMessage(2, DJIPADUpgradeTipBannerView.i, ((int) Math.round(((double) i) * 0.25d)) + 5));
        }

        public void a(dji.dbox.upgrade.p4.b.b.a aVar, dji.midware.data.config.P3.a aVar2) {
            this.a.n.sendMessage(this.a.n.obtainMessage(7, aVar2));
        }

        public void i() {
        }

        public void a(String str, int i) {
            this.a.n.sendMessage(this.a.n.obtainMessage(2, DJIPADUpgradeTipBannerView.i, ((int) Math.round(((double) i) * 0.7d)) + 30));
        }

        public void a(DJIUpgradeCompleteReason dJIUpgradeCompleteReason) {
            this.a.n.sendMessage(this.a.n.obtainMessage(7, dJIUpgradeCompleteReason));
        }

        public void j() {
            this.a.n.sendMessage(this.a.n.obtainMessage(2, DJIPADUpgradeTipBannerView.i, 100));
            this.a.n.sendEmptyMessageDelayed(6, 300);
        }

        public void c() {
            this.a.n.sendEmptyMessage(1);
        }

        public void a(int i) {
            this.a.n.sendMessage(this.a.n.obtainMessage(2, DJIPADUpgradeTipBannerView.h, i));
        }

        public void b(String str) {
            this.a.n.sendMessage(this.a.n.obtainMessage(7, str));
        }

        public void d() {
            this.a.n.sendMessage(this.a.n.obtainMessage(2, DJIPADUpgradeTipBannerView.i, 0));
        }

        public void a() {
            DJILogHelper.getInstance().LOGE(DJIPADUpgradeTipBannerView.a, "onCollectStart");
        }

        public void a(String str) {
            DJILogHelper.getInstance().LOGE(DJIPADUpgradeTipBannerView.a, "onCollectFail " + str);
        }

        public void b() {
            DJILogHelper.getInstance().LOGE(DJIPADUpgradeTipBannerView.a, "onCollectComplete");
            this.a.n.sendEmptyMessage(0);
        }

        public void k() {
        }

        public void l() {
        }

        public void m() {
            this.a.n.sendEmptyMessage(9);
        }
    };
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private String m = null;
    private Handler n = new Handler(new Callback(this) {
        final /* synthetic */ DJIPADUpgradeTipBannerView a;
        private Handler b = null;

        {
            this.a = r2;
        }

        public boolean handleMessage(Message message) {
            if (P4UpgradeActivity.b != null) {
                this.b = P4UpgradeActivity.b.v;
            }
            this.a.j = message.what;
            this.a.k = message.arg1;
            this.a.l = message.arg2;
            this.a.m = message.obj == null ? null : message.obj.toString();
            switch (message.what) {
                case 0:
                    this.a.c = DJIUpgradeP4Service.b();
                    if (dji.dbox.upgrade.p4.a.a.s && dji.dbox.upgrade.p4.a.a.v != null) {
                        this.a.setVisibility(0);
                        this.a.d = dji.dbox.upgrade.p4.a.a.v.product_version;
                        this.a.h();
                    } else if (!this.a.i()) {
                        this.a.setVisibility(8);
                    }
                    if (!(this.a.i() || this.b == null)) {
                        this.b.sendEmptyMessage(message.what);
                        break;
                    }
                    break;
                case 1:
                    this.a.g();
                    if (this.b != null) {
                        this.b.sendEmptyMessage(message.what);
                        break;
                    }
                    break;
                case 2:
                    this.a.show();
                    this.a.updateUpgradeProgress(message.arg1, message.arg2);
                    if (this.b != null) {
                        this.b.sendMessage(this.b.obtainMessage(message.what, message.arg1, message.arg2));
                        break;
                    }
                    break;
                case 4:
                    this.a.d();
                    break;
                case 5:
                    this.a.f();
                    break;
                case 6:
                    this.a.show();
                    this.a.j();
                    if (this.b != null) {
                        this.b.sendEmptyMessage(message.what);
                        break;
                    }
                    break;
                case 7:
                    this.a.show();
                    this.a.k();
                    if (this.b != null) {
                        this.b.sendMessage(this.b.obtainMessage(message.what, this.a.m));
                        break;
                    }
                    break;
                case 9:
                    if (this.b != null) {
                        this.b.sendMessage(this.b.obtainMessage(message.what, this.a.m));
                        break;
                    }
                    break;
            }
            return false;
        }
    });

    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] b = new int[dji.pilot.publics.objects.DJINetWorkReceiver.a.values().length];

        static {
            c = new int[a.values().length];
            try {
                c[a.Show.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                c[a.Go.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[dji.pilot.publics.objects.DJINetWorkReceiver.a.a.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[dji.pilot.publics.objects.DJINetWorkReceiver.a.c.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            a = new int[DJIRollBackActivity$a.values().length];
            try {
                a[DJIRollBackActivity$a.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public enum a {
        Show,
        Go
    }

    public DJIPADUpgradeTipBannerView(Context context) {
        super(context);
    }

    public DJIPADUpgradeTipBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJIPADUpgradeTipBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.f = DJIUpgradeP4Service.i();
            this.f.a(this.g);
            c();
            c.a().a(this);
        }
    }

    public void onEventMainThread(DJIRollBackActivity$a dJIRollBackActivity$a) {
        switch (dJIRollBackActivity$a) {
            case NONE:
                if (i()) {
                    go();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(dji.pilot.publics.objects.DJINetWorkReceiver.a aVar) {
        switch (AnonymousClass4.b[aVar.ordinal()]) {
            case 1:
                this.f.g();
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(a aVar) {
        switch (aVar) {
            case Show:
                setVisibility(0);
                return;
            case Go:
                if (!dji.dbox.upgrade.p4.a.a.s || dji.dbox.upgrade.p4.a.a.v == null) {
                    setVisibility(8);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void show() {
        if ((dji.dbox.upgrade.p4.a.a.s || dji.dbox.upgrade.p4.a.a.u == null) && DJIUpgradeP4Service.c()) {
            super.show();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public static void enterP4UpgradeActivity(Context context) {
        Intent intent = new Intent(context, P4UpgradeActivity.class);
        intent.putExtra(P4UpgradeActivity.c, 0);
        intent.putExtra(P4UpgradeActivity.d, 0);
        intent.putExtra(P4UpgradeActivity.t, 0);
        context.startActivity(intent);
    }

    private void c() {
        this.e = (TextView) findViewById(R.id.sd);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIPADUpgradeTipBannerView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.i()) {
                    this.a.j = 6;
                    this.a.k = 0;
                    this.a.l = 0;
                    this.a.m = null;
                }
                Intent intent = new Intent(this.a.getContext(), P4UpgradeActivity.class);
                intent.putExtra(P4UpgradeActivity.c, this.a.j);
                intent.putExtra(P4UpgradeActivity.d, this.a.k);
                intent.putExtra(P4UpgradeActivity.t, this.a.l);
                intent.putExtra(P4UpgradeActivity.u, this.a.m);
                this.a.getContext().startActivity(intent);
            }
        });
    }

    public void updateUpgradeProgress(int i, int i2) {
        int i3;
        if (i == h) {
            i3 = R.string.v2_upgrade_tip_downloading;
        } else {
            i3 = R.string.v2_upgrade_tip_upgrading;
        }
        this.e.setText(getResources().getString(i3, new Object[]{i2 + "%"}));
    }

    private void d() {
        this.e.setText(R.string.v2_upgrade_tip_down_pause);
    }

    private void e() {
        this.e.setText(R.string.v2_upgrade_tip_upgrade_wait);
    }

    private void f() {
        updateUpgradeProgress(i, 0);
    }

    private void g() {
        updateUpgradeProgress(h, 0);
    }

    private void h() {
        this.e.setText(getResources().getString(R.string.v2_upgrade_tip_new_upgrade_desc, new Object[]{this.c, this.d}));
    }

    private boolean i() {
        if (this.e.getText().equals(getResources().getString(R.string.v2_upgrade_tip_upgrade_finish)) || this.e.getText().equals(getResources().getString(R.string.v2_upgrade_tip_upgrade_fails))) {
            return true;
        }
        return false;
    }

    private void j() {
        this.e.setText(R.string.v2_upgrade_tip_upgrade_finish);
    }

    private void k() {
        this.e.setText(R.string.v2_upgrade_tip_upgrade_fails);
    }
}
