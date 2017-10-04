package dji.pilot.fpv.inner;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.Toast;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.publics.control.rc.DJIDLPackageInfo;
import dji.pilot.publics.control.rc.b;
import dji.pilot.publics.control.rc.b$c;
import dji.pilot.publics.control.rc.b$d;
import dji.pilot.publics.control.rc.b$e;
import dji.pilot.publics.e.d;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIInnerRcUpgradeView extends DJILinearLayout implements a {
    private DJILinearLayout a = null;
    private DJITextView b = null;
    private DJIImageView c = null;
    private DJITextView d = null;
    private DJILinearLayout e = null;
    private DJIRelativeLayout f = null;
    private DJITextView g = null;
    private DJILinearLayout h = null;
    private DJITextView i = null;
    private DJITextView j = null;
    private DJILinearLayout k = null;
    private DJITextView l = null;
    private DJILinearLayout m = null;
    private ProgressBar n = null;
    private DJIStateImageView o = null;
    private DJILinearLayout p = null;
    private DJIImageView q = null;
    private DJIStateTextView r = null;
    private Context s = null;
    private OnClickListener t = null;
    private b u = null;
    private b$c v = null;
    private b$e w = null;
    private b$d x = null;

    public DJIInnerRcUpgradeView(Context context) {
        super(context);
    }

    public DJIInnerRcUpgradeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJIInnerRcUpgradeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private String a(int i, Object... objArr) {
        return this.s.getString(i, objArr);
    }

    private void a() {
        this.a = (DJILinearLayout) findViewById(R.id.a_7);
        this.b = (DJITextView) findViewById(R.id.a_9);
        this.c = (DJIImageView) findViewById(R.id.a__);
        this.d = (DJITextView) findViewById(R.id.a_a);
        this.e = (DJILinearLayout) findViewById(R.id.a_b);
        this.f = (DJIRelativeLayout) findViewById(R.id.a_c);
        this.g = (DJITextView) findViewById(R.id.a_d);
        this.h = (DJILinearLayout) findViewById(R.id.a_e);
        this.i = (DJITextView) findViewById(R.id.a_f);
        this.j = (DJITextView) findViewById(R.id.a_g);
        this.k = (DJILinearLayout) findViewById(R.id.a_i);
        this.l = (DJITextView) findViewById(R.id.a_j);
        this.m = (DJILinearLayout) findViewById(R.id.a_k);
        this.n = (ProgressBar) findViewById(R.id.a_l);
        this.o = (DJIStateImageView) findViewById(R.id.a_m);
        this.p = (DJILinearLayout) findViewById(R.id.a_n);
        this.q = (DJIImageView) findViewById(R.id.a_o);
        this.r = (DJIStateTextView) findViewById(R.id.a_p);
        this.d.setOnClickListener(this.t);
        this.o.setOnClickListener(this.t);
        this.r.setOnClickListener(this.t);
        this.n.setMax(200);
    }

    private void b() {
        this.v = new b$c(this) {
            final /* synthetic */ DJIInnerRcUpgradeView a;

            {
                this.a = r1;
            }

            public void a(DJIDLPackageInfo dJIDLPackageInfo, long j, long j2) {
                this.a.k();
            }

            public void a(DJIDLPackageInfo dJIDLPackageInfo, int i) {
                this.a.s();
            }

            public void a(DJIDLPackageInfo dJIDLPackageInfo, boolean z) {
                this.a.u();
            }

            public void a(DJIDLPackageInfo dJIDLPackageInfo, int i, String str) {
                this.a.t();
            }
        };
    }

    private void c() {
        this.w = new b$e(this) {
            final /* synthetic */ DJIInnerRcUpgradeView a;

            {
                this.a = r1;
            }

            public void a(b bVar, int i, int i2) {
                this.a.l();
            }

            public void a(b bVar, int i) {
                this.a.q();
            }

            public void b(b bVar, int i) {
                this.a.r();
            }

            public void a(b bVar, dji.midware.data.config.P3.a aVar, int i, int i2) {
                this.a.p();
            }
        };
    }

    private void d() {
        this.x = new b$d(this) {
            final /* synthetic */ DJIInnerRcUpgradeView a;

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
    }

    private void e() {
        this.s = getContext();
        b();
        c();
        d();
        this.u = b.getInstance();
        this.t = new OnClickListener(this) {
            final /* synthetic */ DJIInnerRcUpgradeView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.a_m) {
                    this.a.g();
                } else if (id == R.id.a_p) {
                    this.a.h();
                } else if (id == R.id.a_a) {
                    this.a.f();
                }
            }
        };
    }

    private void f() {
        i();
    }

    private void g() {
        DJIDLPackageInfo n = this.u.n();
        if (n == null) {
            return;
        }
        if (n.mDLStatus == 2) {
            this.u.b(n);
            u();
            return;
        }
        i();
    }

    private void h() {
        int i = this.u.i();
        if (i == 259) {
            this.u.q();
            o();
        } else if (i == 260) {
            i();
        } else if (i == 261) {
            j();
        } else if (i == b.l) {
            j();
        } else if (i == b.k) {
            this.u.r();
            Toast.makeText(this.s.getApplicationContext(), R.string.rcupgrade_delpackage_tip, 0).show();
        }
    }

    private void i() {
        if (!dji.pilot.fpv.d.b.c(this.s.getApplicationContext())) {
            dji.pilot.publics.widget.b.a(this.s, (int) R.string.app_tip, (int) R.string.rcupgrade_nonet_tip, (int) R.string.app_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIInnerRcUpgradeView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }, (int) R.string.rcupgrade_check_now, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIInnerRcUpgradeView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    d.b(this.a.s, true);
                }
            }).show();
        } else if (dji.pilot.publics.control.a.getInstance(this.s).g()) {
            Toast.makeText(this.s.getApplicationContext(), R.string.rcupgrade_wait_version_dling, 0).show();
        } else {
            this.u.p();
        }
    }

    private void j() {
        if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
            Toast.makeText(getContext().getApplicationContext(), R.string.rcupgrade_motorup, 0).show();
        } else if (ServiceManager.getInstance().isConnected()) {
            this.u.s();
        } else {
            Toast.makeText(getContext().getApplicationContext(), R.string.fpv_tip_disconnect, 0).show();
        }
    }

    private void k() {
        DJIDLPackageInfo n = this.u.n();
        if (n != null) {
            int a = this.u.a(n);
            this.l.setText(a((int) R.string.rcupgrade_download_percent, Integer.valueOf((a * 100) / 200)));
            this.m.show();
            this.n.setProgress(a);
        }
    }

    private void l() {
        int o = this.u.o();
        if (o != this.n.getProgress()) {
            this.l.setText(a((int) R.string.rcupgrade_upgrade_percent, Integer.valueOf((o * 100) / 200)));
            this.m.show();
            this.n.setProgress(o);
            this.o.go();
        }
    }

    private void a(int i) {
        if (258 == i) {
            o();
        } else if (259 == i) {
            u();
        } else if (260 == i) {
            t();
        } else if (261 == i) {
            s();
        } else if (b.j == i) {
            r();
        } else if (b.k == i) {
            q();
        } else if (b.l == i) {
            p();
        } else if (257 == i) {
            m();
        } else if (256 == i) {
            n();
        }
    }

    private void a(int i, int i2) {
        o();
        Toast.makeText(this.s, this.s.getString(R.string.rcupgrade_package_error), 1).show();
    }

    public void handleRcStatus() {
        int i = this.u.i();
        if (258 == i) {
            o();
        } else if (259 == i) {
            u();
        } else if (260 == i) {
            t();
        } else if (261 == i) {
            s();
        } else if (b.j == i) {
            r();
        } else if (b.k == i) {
            q();
        } else if (b.l == i) {
            p();
        } else if (257 == i) {
            m();
        } else if (256 == i) {
            n();
        }
    }

    private void m() {
        this.a.show();
        this.c.show();
        this.d.show();
        this.d.setText(R.string.rcupgrade_cant_upgrade);
        this.d.setEnabled(false);
        this.e.go();
    }

    private void n() {
        this.a.go();
        this.c.go();
        this.d.go();
        this.e.go();
    }

    private void o() {
        this.a.show();
        if (this.u.g()) {
            this.c.show();
            this.d.show();
            this.d.setText(R.string.rcupgrade_download_package);
            this.d.setEnabled(true);
            this.e.go();
            return;
        }
        this.c.go();
        this.d.go();
        this.e.go();
    }

    private void p() {
        this.a.show();
        this.c.go();
        this.d.go();
        this.e.show();
        this.g.setText(R.string.rcupgrade_upgrade_fail);
        this.g.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rc_upgrade_fail, 0, 0, 0);
        this.i.show();
        this.i.setText(a((int) R.string.rcupgrade_current_version, this.u.k()));
        this.j.show();
        this.j.setText(a((int) R.string.rcupgrade_download_version, this.u.l()));
        this.l.setText(R.string.rcupgrade_upgrade_tip);
        this.m.hide();
        this.p.show();
        this.r.setText(R.string.rcupgrade_upgrade_retry);
    }

    private void q() {
        this.a.go();
        this.c.go();
        this.d.go();
        this.e.show();
        this.g.setText(R.string.rcupgrade_upgrade_success);
        this.g.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rc_upgrade_success, 0, 0, 0);
        this.i.go();
        this.j.go();
        this.l.setText(a((int) R.string.rcupgrade_upgrade_success_tip, this.u.l(), Integer.valueOf(5)));
        this.m.hide();
        this.p.show();
        this.r.setText(R.string.rcupgrade_delete_package);
    }

    private void r() {
        this.a.show();
        this.c.go();
        this.d.go();
        this.e.show();
        this.g.setText(R.string.rcupgrade_upgrade_now);
        this.g.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rc_upgrade_upgrade, 0, 0, 0);
        this.i.show();
        this.i.setText(a((int) R.string.rcupgrade_current_version, this.u.k()));
        this.j.show();
        this.j.setText(a((int) R.string.rcupgrade_download_version, this.u.l()));
        int o = this.u.o();
        this.l.setText(a((int) R.string.rcupgrade_upgrade_percent, Integer.valueOf((o * 100) / 200)));
        this.m.show();
        this.n.setProgress(o);
        this.o.go();
        this.p.go();
    }

    private void s() {
        this.a.show();
        this.c.go();
        this.d.go();
        this.e.show();
        this.g.setText(R.string.rcupgrade_download_success);
        this.g.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rc_upgrade_success, 0, 0, 0);
        this.i.go();
        this.j.show();
        if (this.u.n() != null) {
            this.j.setText(a((int) R.string.rcupgrade_download_version, r0.mVersion));
        } else {
            this.j.setText(a((int) R.string.rcupgrade_download_version, this.u.l()));
        }
        this.l.setText(R.string.rcupgrade_upgrade_tip);
        this.m.hide();
        this.p.show();
        this.r.setText(R.string.rcupgrade_upgrade);
    }

    private void t() {
        this.a.show();
        this.c.go();
        this.d.go();
        this.e.show();
        if (this.u.n() != null) {
            this.g.setText(R.string.rcupgrade_download_package_fail);
            this.g.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rc_upgrade_fail, 0, 0, 0);
            this.i.go();
            this.j.show();
            this.j.setText(a((int) R.string.rcupgrade_download_version, r0.mVersion));
            this.l.setText(R.string.rcupgrade_download_nettip);
            this.m.hide();
            this.p.show();
            this.r.setText(R.string.rcupgrade_download_retry);
        }
    }

    private void u() {
        this.a.show();
        this.c.go();
        this.d.go();
        this.e.show();
        DJIDLPackageInfo n = this.u.n();
        if (n != null) {
            this.i.go();
            this.j.show();
            this.j.setText(a((int) R.string.rcupgrade_download_version, n.mVersion));
            int a = this.u.a(n);
            this.l.setText(a((int) R.string.rcupgrade_download_percent, Integer.valueOf((a * 100) / 200)));
            this.m.show();
            this.n.setProgress(a);
            if (n.mDLStatus == 2 || n.mDLStatus == 0) {
                this.g.setText(R.string.rcupgrade_download_now);
                this.g.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rc_upgrade_downloading, 0, 0, 0);
                this.o.setImageResource(R.drawable.rc_upgrade_pause);
            } else {
                this.g.setText(R.string.rcupgrade_download_pause);
                this.g.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rc_upgrade_download_pause, 0, 0, 0);
                this.o.setImageResource(R.drawable.rc_upgrade_download);
            }
            this.p.show();
            this.r.setText(R.string.rcupgrade_download_cancel);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        e();
        a();
    }

    public void dispatchOnStart() {
        this.u.a(this.v);
        this.u.a(this.w);
        this.u.a(this.x);
        handleRcStatus();
    }

    public void dispatchOnStop() {
        this.u.c();
        this.u.d();
        this.u.e();
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }
}
