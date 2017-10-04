package dji.pilot2.upgrade;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.pilot.R;
import dji.pilot.publics.control.p3cupgrade.b;
import dji.pilot.publics.control.p3cupgrade.b.j;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import dji.pilot.upgrade.e;
import dji.pilot2.upgrade.b.d;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.b.b.a;
import dji.thirdparty.a.c;

public class UpgradeTipBannerView extends DJILinearLayout {
    public static final String a = "UpgradeTipBannerView";
    private View b;
    private View c;
    private TextView d;
    private TextView e;
    private b f;
    private b g;

    public UpgradeTipBannerView(Context context) {
        super(context);
    }

    public UpgradeTipBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public UpgradeTipBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
        if (!isInEditMode()) {
            this.f = b.getInstance();
            onEventMainThread(this.f.c());
            c.a().a(this);
            if (b.a) {
                setVisibility(0);
            }
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f = null;
        c.a().d(this);
    }

    private void a() {
        this.b = findViewById(R.id.cpc);
        this.c = findViewById(R.id.d1f);
        this.d = (TextView) findViewById(R.id.d1e);
        this.e = (TextView) findViewById(R.id.d1g);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UpgradeTipBannerView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (b.a) {
                    this.a.b.setVisibility(8);
                    this.a.c.setVisibility(0);
                    this.a.getContext().startActivity(new Intent(this.a.getContext(), P3cUpgradeActivity.class));
                    this.a.b();
                    this.a.getHandler().postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.g.d();
                        }
                    }, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
                    return;
                }
                if (this.a.f.c() == d.NeedUpgrade) {
                    this.a.f.b();
                }
                if (this.a.f.c() == d.NeedUpgrade || this.a.f.c() == d.Upgrading || this.a.f.c() == d.UpgradeFinish) {
                    this.a.getContext().startActivity(new Intent(this.a.getContext(), P3cUpgradeActivity.class));
                }
                a.getInstance().f("createtime", System.currentTimeMillis() + "", false).f(dji.publics.b.a.b.x, "2", false).f("device_ver", e.getInstance().b(), false).f("device_type", i.getInstance().c()._name(), true);
            }
        });
    }

    private void b() {
        this.g = new b();
        DJIUpgradePack dJIUpgradePack = new DJIUpgradePack();
        dJIUpgradePack.m0400 = "01.30.01.18&0";
        dJIUpgradePack.m0401 = "01.30.01.18&0";
        dJIUpgradePack.m0402 = "01.30.01.18&0";
        dJIUpgradePack.m0403 = "01.30.01.18&0";
        dJIUpgradePack.m0700 = "01.03.01.02&0";
        dJIUpgradePack.m0900 = "00.00.00.89&0";
        dJIUpgradePack.m0901 = "00.00.00.08&0";
        b.b = true;
        this.g.b(true);
        this.g.a(dJIUpgradePack, ProductType.LonganMobile);
    }

    private void c() {
        d c = this.f.c();
        ProductType g = this.f.g();
        if (c != d.NeedUpgrade) {
            return;
        }
        if (!g.isFromWifi() && g != ProductType.LonganMobile) {
            return;
        }
        if (ServiceManager.getInstance().isConnected() && ServiceManager.getInstance().isRemoteOK()) {
            setVisibility(0);
            return;
        }
        String lastProductSn = getLastProductSn();
        if (System.currentTimeMillis() - b(lastProductSn) > 604800000) {
            setVisibility(0);
            a(lastProductSn);
        } else if (!b.a) {
            setVisibility(8);
        }
    }

    private void setNoShowTime(String str) {
    }

    private void a(String str) {
    }

    private long b(String str) {
        return 0;
    }

    private String getLastProductSn() {
        return null;
    }

    public void onEventMainThread(b.a aVar) {
        this.e.setText(getResources().getString(R.string.v2_upgrade_tip_downloading, new Object[]{aVar.b + "%"}));
    }

    public void onEventMainThread(b.i iVar) {
        this.e.setText(getResources().getString(R.string.v2_upgrade_tip_upgrading, new Object[]{iVar.a + "%"}));
    }

    public void onEventMainThread(d dVar) {
        DJILogHelper.getInstance().LOGD(a, "machine status = " + dVar);
        switch (dVar) {
            case None:
            case Initing:
            case NotNeedUpgrade:
                if (!b.a) {
                    setVisibility(8);
                    return;
                }
                return;
            case NeedUpgrade:
                c();
                i();
                return;
            case Upgrading:
            case UpgradeFinish:
                setVisibility(0);
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(j jVar) {
        String str = "6";
        if (this.f.c() == d.Upgrading) {
            switch (jVar) {
                case INIT_FAILS:
                case NOT_NEED_UPGRADE:
                case UPGRADE_FAILS:
                    setUpgradeFailsMode(this.f.f());
                    str = "7";
                    break;
                case UPGRADE_SUCCESS:
                    j();
                    str = "6";
                    break;
                case INITING:
                case START_WAIT_DOWNLOAD:
                case START_WAIT_UPGRADE:
                case CHECKING_DOWNLOAD_NETWORK:
                    i();
                    break;
                case DOWNLOAD_PAUSE:
                    d();
                    break;
                case DOWNLOADING:
                    h();
                    str = "3";
                    break;
                case WAITINGT_TO_UPGRADE:
                case CHECKING_UPGRADE_BASE:
                    e();
                    break;
                case UPGRADE_PAUSE:
                    f();
                    break;
                case UPGRADING:
                    g();
                    str = "5";
                    break;
            }
            a.getInstance().f("createtime", System.currentTimeMillis() + "", false).f(dji.publics.b.a.b.x, str, false).f("device_ver", e.getInstance().b(), false).f("device_type", i.getInstance().c()._name(), true);
        }
    }

    private void d() {
        this.b.setVisibility(8);
        this.c.setVisibility(0);
        this.c.setBackgroundResource(R.color.nc);
        this.e.setText(R.string.v2_upgrade_tip_down_pause);
    }

    private void e() {
        this.b.setVisibility(8);
        this.c.setVisibility(0);
        this.c.setBackgroundResource(R.color.nf);
        this.e.setText(R.string.v2_upgrade_tip_upgrade_wait);
    }

    private void f() {
        this.b.setVisibility(8);
        this.c.setVisibility(0);
        this.c.setBackgroundResource(R.color.nf);
        this.e.setText(R.string.v2_upgrade_tip_upgrade_pause);
    }

    private void g() {
        this.b.setVisibility(8);
        this.c.setVisibility(0);
        this.c.setBackgroundResource(R.color.nf);
        this.e.setText(getResources().getString(R.string.v2_upgrade_tip_upgrading, new Object[]{"0%"}));
    }

    private void h() {
        this.b.setVisibility(8);
        this.c.setVisibility(0);
        this.c.setBackgroundResource(R.color.nc);
        this.e.setText(getResources().getString(R.string.v2_upgrade_tip_downloading, new Object[]{"0%"}));
    }

    private void i() {
        this.b.setVisibility(0);
        this.c.setVisibility(8);
        this.d.setText(getResources().getString(R.string.v2_upgrade_tip_new_upgrade_desc, new Object[]{this.f.e(), this.f.d()}));
    }

    private void j() {
        this.b.setVisibility(8);
        this.c.setVisibility(0);
        this.c.setBackgroundResource(R.color.ne);
        this.e.setText(R.string.v2_upgrade_tip_upgrade_finish);
    }

    private void setUpgradeFailsMode(b.c cVar) {
        this.b.setVisibility(8);
        this.c.setVisibility(0);
        this.c.setBackgroundResource(R.color.nd);
        this.e.setText(R.string.v2_upgrade_tip_upgrade_fails);
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 0) {
            a.getInstance().f("createtime", System.currentTimeMillis() + "", false).f(dji.publics.b.a.b.x, "1", false).f("device_ver", e.getInstance().b(), false).f("device_type", i.getInstance().c()._name(), true);
        }
    }
}
