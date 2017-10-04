package dji.pilot2.upgrade.rollback;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.publics.control.rc.DJIDLPackageInfo;
import dji.pilot.publics.control.rc.b;
import dji.pilot.publics.control.rc.b$c;
import dji.pilot.publics.control.rc.b$d;
import dji.pilot.publics.control.rc.b$e;
import dji.pilot.publics.e.d;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import dji.pilot2.upgrade.rollback.widget.DJIRBProgressBar;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJIRollBackUpgrade3x3sView extends LinearLayout {
    private DJIUpgradePack a;
    private ProductType b;
    private DJIRBProgressBar c = null;
    private DJITextView d = null;
    private DJITextView e = null;
    private b f = null;
    private b$c g = null;
    private b$e h = null;
    private b$d i = null;

    public DJIRollBackUpgrade3x3sView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            a();
        }
    }

    private void a() {
        this.c = (DJIRBProgressBar) findViewById(R.id.cwg);
        this.d = (DJITextView) findViewById(R.id.cwi);
        this.e = (DJITextView) findViewById(R.id.cz2);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIRollBackUpgrade3x3sView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.f();
            }
        });
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b();
        if (!isInEditMode()) {
            a(this.f.i());
            c.a().a(this);
            r();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
        c.a().d(this);
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 0 && this.f != null) {
            a(this.f.i());
        }
    }

    private void b() {
        this.f = b.getInstance();
        this.g = new b$c(this) {
            final /* synthetic */ DJIRollBackUpgrade3x3sView a;

            {
                this.a = r1;
            }

            public void a(DJIDLPackageInfo dJIDLPackageInfo, long j, long j2) {
                this.a.p();
            }

            public void a(DJIDLPackageInfo dJIDLPackageInfo, int i) {
                this.a.m();
            }

            public void a(DJIDLPackageInfo dJIDLPackageInfo, boolean z) {
                this.a.o();
            }

            public void a(DJIDLPackageInfo dJIDLPackageInfo, int i, String str) {
                this.a.n();
            }
        };
        this.h = new b$e(this) {
            final /* synthetic */ DJIRollBackUpgrade3x3sView a;

            {
                this.a = r1;
            }

            public void a(b bVar, int i, int i2) {
                this.a.q();
            }

            public void a(b bVar, int i) {
                this.a.k();
            }

            public void b(b bVar, int i) {
                this.a.l();
            }

            public void a(b bVar, a aVar, int i, int i2) {
                this.a.j();
            }
        };
        this.i = new b$d(this) {
            final /* synthetic */ DJIRollBackUpgrade3x3sView a;

            {
                this.a = r1;
            }

            public void a(int i) {
                this.a.a(i);
            }

            public void a(int i, int i2) {
                this.a.a(i, i2);
            }
        };
        this.f.a(this.g);
        this.f.a(this.h);
        this.f.a(this.i);
    }

    private void c() {
        this.f.a(null);
        this.f.c();
        this.f.d();
        this.f.e();
    }

    public boolean canExit() {
        int i = this.f.i();
        return (b.j == i || 259 == i) ? false : true;
    }

    private void d() {
        if (!dji.pilot.fpv.d.b.c(getContext())) {
            dji.pilot.publics.widget.b.a(getContext(), (int) R.string.app_tip, (int) R.string.rcupgrade_nonet_tip, (int) R.string.app_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIRollBackUpgrade3x3sView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }, (int) R.string.rcupgrade_check_now, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIRollBackUpgrade3x3sView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    d.b(this.a.getContext(), true);
                }
            }).show();
        } else if (dji.pilot.publics.control.a.getInstance(getContext()).g()) {
            Toast.makeText(getContext(), R.string.rcupgrade_wait_version_dling, 0).show();
        } else {
            this.f.p();
        }
        c.a().e(DJIRollBackActivity$b.Change);
    }

    private void e() {
        if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
            Toast.makeText(getContext(), R.string.rcupgrade_motorup, 0).show();
        } else if (ServiceManager.getInstance().isConnected()) {
            this.f.s();
        } else {
            Toast.makeText(getContext(), R.string.fpv_tip_disconnect, 0).show();
        }
    }

    private void f() {
        int i = this.f.i();
        if (i == 258) {
            d();
        } else if (i == 259) {
            this.f.q();
            i();
        } else if (i == 260) {
            d();
        } else if (i == 261) {
            e();
        } else if (i == b.l) {
            e();
        } else if (i == b.k) {
            this.f.r();
            Toast.makeText(getContext(), R.string.rcupgrade_delpackage_tip, 0).show();
        }
    }

    private void a(int i) {
        if (258 == i) {
            i();
        } else if (259 == i) {
            o();
        } else if (260 == i) {
            n();
        } else if (261 == i) {
            m();
        } else if (b.j == i) {
            l();
        } else if (b.k == i) {
            k();
        } else if (b.l == i) {
            j();
        } else if (257 == i) {
            g();
        } else if (256 == i) {
            h();
        }
        c.a().e(DJIRollBackActivity$b.Change);
    }

    private void a(int i, int i2) {
        i();
        Toast.makeText(getContext(), getContext().getString(R.string.rcupgrade_package_error), 1).show();
    }

    private void g() {
        this.d.setText(R.string.rcupgrade_cant_upgrade);
        this.e.go();
    }

    private void h() {
        a(false);
        this.d.setText(R.string.rcupgrade_cant_upgrade);
        this.e.go();
    }

    private void i() {
        this.d.setText(R.string.rcupgrade_upgrade_tip);
        this.e.show();
        this.e.setText(R.string.rcupgrade_download_package);
    }

    private void j() {
        this.c.setProgress(0);
        this.d.setText(R.string.rcupgrade_upgrade_fail);
        this.e.show();
        this.e.setText(R.string.rcupgrade_upgrade_retry);
    }

    private void k() {
        this.c.setProgress(100);
        this.d.setText(R.string.rcupgrade_upgrade_success);
        this.e.show();
        this.e.setText(R.string.rcupgrade_delete_package);
    }

    private void l() {
        int i = 100;
        a(true);
        int o = (this.f.o() * 100) / 200;
        if (o <= 100) {
            i = o;
        }
        this.c.setProgress(i);
        this.d.setText(R.string.rcupgrade_upgrade_now);
        this.e.go();
    }

    private void m() {
        this.d.setText(R.string.rcupgrade_download_success);
        this.e.show();
        this.e.setText(R.string.rcupgrade_upgrade);
    }

    private void n() {
        this.d.setText(R.string.rcupgrade_download_package_fail);
        this.e.show();
        this.e.setText(R.string.rcupgrade_download_retry);
    }

    private void o() {
        a(true);
        DJIDLPackageInfo n = this.f.n();
        if (n != null) {
            int a = (this.f.a(n) * 100) / 200;
            this.c.show();
            this.c.setProgress(a);
            this.d.setText(getResources().getString(R.string.rcupgrade_download_percent, new Object[]{Integer.valueOf(a)}));
            this.e.show();
            this.e.setText(R.string.rcupgrade_download_cancel);
        }
    }

    private void p() {
        a(true);
        DJIDLPackageInfo n = this.f.n();
        if (n != null) {
            int a = (this.f.a(n) * 100) / 200;
            this.d.setText(getResources().getString(R.string.rcupgrade_download_percent, new Object[]{Integer.valueOf(a)}));
            this.c.show();
            this.c.setProgress(a);
        }
    }

    private void q() {
        a(true);
        int o = (this.f.o() * 100) / 200;
        if (o != this.c.getProgress()) {
            this.d.setText(getResources().getString(R.string.rcupgrade_upgrade_percent, new Object[]{Integer.valueOf(o)}));
            this.c.show();
            this.c.setProgress(o);
        }
    }

    private void a(boolean z) {
        if (z) {
            if (!this.c.isShown()) {
                this.c.show();
            }
        } else if (this.c.isShown()) {
            this.c.go();
        }
    }

    public void onEventMainThread(a.a aVar) {
        r();
        c.a().e(DJIRollBackActivity$b.Change);
    }

    private void r() {
        DJIUpgradePack g = a.getInstance().g();
        ProductType a = dji.pilot.upgrade.b.a(a.getInstance().b());
        if (g != null && a != null && b.a(a)) {
            this.a = g;
            this.b = a;
            this.f.a(this.a);
        }
    }
}
