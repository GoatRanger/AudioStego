package dji.pilot.fpv.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.Toast;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.publics.control.rc.DJIDLPackageInfo;
import dji.pilot.publics.control.rc.b;
import dji.pilot.publics.control.rc.b$c;
import dji.pilot.publics.control.rc.b$d;
import dji.pilot.publics.control.rc.b$e;
import dji.pilot.publics.e.d;
import dji.pilot.publics.widget.DJIStateButton;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.Locale;

public class DJIRcUpgradeView extends DJILinearLayout {
    private DJILinearLayout a = null;
    private DJITextView b = null;
    private DJIStateButton c = null;
    private DJIImageView d = null;
    private DJITextView e = null;
    private DJILinearLayout f = null;
    private DJIRelativeLayout g = null;
    private DJITextView h = null;
    private DJILinearLayout i = null;
    private DJITextView j = null;
    private DJITextView k = null;
    private DJILinearLayout l = null;
    private DJITextView m = null;
    private DJILinearLayout n = null;
    private ProgressBar o = null;
    private DJIStateImageView p = null;
    private DJILinearLayout q = null;
    private DJIImageView r = null;
    private DJIStateTextView s = null;
    private Context t = null;
    private OnClickListener u = null;
    private b v = null;
    private b$c w = null;
    private b$e x = null;
    private b$d y = null;

    public DJIRcUpgradeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private String a(int i, Object... objArr) {
        return this.t.getString(i, objArr);
    }

    private void a() {
        this.a = (DJILinearLayout) findViewById(R.id.a_7);
        this.b = (DJITextView) findViewById(R.id.a_9);
        this.c = (DJIStateButton) findViewById(R.id.a_8);
        this.d = (DJIImageView) findViewById(R.id.a__);
        this.e = (DJITextView) findViewById(R.id.a_a);
        this.f = (DJILinearLayout) findViewById(R.id.a_b);
        this.g = (DJIRelativeLayout) findViewById(R.id.a_c);
        this.h = (DJITextView) findViewById(R.id.a_d);
        this.i = (DJILinearLayout) findViewById(R.id.a_e);
        this.j = (DJITextView) findViewById(R.id.a_f);
        this.k = (DJITextView) findViewById(R.id.a_g);
        this.l = (DJILinearLayout) findViewById(R.id.a_i);
        this.m = (DJITextView) findViewById(R.id.a_j);
        this.n = (DJILinearLayout) findViewById(R.id.a_k);
        this.o = (ProgressBar) findViewById(R.id.a_l);
        this.p = (DJIStateImageView) findViewById(R.id.a_m);
        this.q = (DJILinearLayout) findViewById(R.id.a_n);
        this.r = (DJIImageView) findViewById(R.id.a_o);
        this.s = (DJIStateTextView) findViewById(R.id.a_p);
        this.e.setOnClickListener(this.u);
        this.p.setOnClickListener(this.u);
        this.s.setOnClickListener(this.u);
        this.c.setOnClickListener(this.u);
        this.o.setMax(200);
    }

    private void b() {
        this.w = new b$c(this) {
            final /* synthetic */ DJIRcUpgradeView a;

            {
                this.a = r1;
            }

            public void a(DJIDLPackageInfo dJIDLPackageInfo, long j, long j2) {
                this.a.l();
            }

            public void a(DJIDLPackageInfo dJIDLPackageInfo, int i) {
                this.a.t();
            }

            public void a(DJIDLPackageInfo dJIDLPackageInfo, boolean z) {
                this.a.v();
            }

            public void a(DJIDLPackageInfo dJIDLPackageInfo, int i, String str) {
                this.a.u();
            }
        };
    }

    private void c() {
        this.x = new b$e(this) {
            final /* synthetic */ DJIRcUpgradeView a;

            {
                this.a = r1;
            }

            public void a(b bVar, int i, int i2) {
                this.a.m();
            }

            public void a(b bVar, int i) {
                this.a.r();
            }

            public void b(b bVar, int i) {
                this.a.s();
            }

            public void a(b bVar, a aVar, int i, int i2) {
                this.a.q();
            }
        };
    }

    private void d() {
        this.y = new b$d(this) {
            final /* synthetic */ DJIRcUpgradeView a;

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
        this.t = getContext();
        b();
        c();
        d();
        this.v = b.getInstance();
        this.u = new OnClickListener(this) {
            final /* synthetic */ DJIRcUpgradeView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.a_m) {
                    this.a.h();
                } else if (id == R.id.a_p) {
                    this.a.i();
                } else if (id == R.id.a_a) {
                    this.a.g();
                } else if (id == R.id.a_8) {
                    this.a.f();
                }
            }
        };
    }

    private void f() {
        String str = "https://www.skypixel.com/videos/3-en-mp4";
        ProductType a = i.getInstance().a();
        if (a == ProductType.litchiS || a == ProductType.litchiX) {
            if (Locale.CHINA.getLanguage().equals(Locale.getDefault().getLanguage())) {
                str = "https://www.skypixel.com/videos/3-cn-mp4";
            }
            getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }
    }

    private void g() {
        j();
    }

    private void h() {
        DJIDLPackageInfo n = this.v.n();
        if (n == null) {
            return;
        }
        if (n.mDLStatus == 2) {
            this.v.b(n);
            v();
            return;
        }
        j();
    }

    private void i() {
        int i = this.v.i();
        if (i == 259) {
            this.v.q();
            p();
        } else if (i == 260) {
            j();
        } else if (i == 261) {
            k();
        } else if (i == b.l) {
            k();
        } else if (i == b.k) {
            this.v.r();
            Toast.makeText(this.t.getApplicationContext(), R.string.rcupgrade_delpackage_tip, 0).show();
        }
    }

    private void j() {
        if (!dji.pilot.fpv.d.b.c(this.t.getApplicationContext())) {
            dji.pilot.publics.widget.b.a(this.t, (int) R.string.app_tip, (int) R.string.rcupgrade_nonet_tip, (int) R.string.app_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIRcUpgradeView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }, (int) R.string.rcupgrade_check_now, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIRcUpgradeView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    d.b(this.a.t, true);
                }
            }).show();
        } else if (dji.pilot.publics.control.a.getInstance(this.t).g()) {
            Toast.makeText(this.t.getApplicationContext(), R.string.rcupgrade_wait_version_dling, 0).show();
        } else {
            this.v.p();
        }
    }

    private void k() {
        if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
            Toast.makeText(getContext().getApplicationContext(), R.string.rcupgrade_motorup, 0).show();
        } else if (ServiceManager.getInstance().isConnected()) {
            this.v.s();
        } else {
            Toast.makeText(getContext().getApplicationContext(), R.string.fpv_tip_disconnect, 0).show();
        }
    }

    private void l() {
        DJIDLPackageInfo n = this.v.n();
        if (n != null) {
            int a = this.v.a(n);
            this.m.setText(a((int) R.string.rcupgrade_download_percent, Integer.valueOf((a * 100) / 200)));
            this.n.show();
            this.o.setProgress(a);
        }
    }

    private void m() {
        int o = this.v.o();
        if (o != this.o.getProgress()) {
            this.m.setText(a((int) R.string.rcupgrade_upgrade_percent, Integer.valueOf((o * 100) / 200)));
            this.n.show();
            this.o.setProgress(o);
            this.p.go();
        }
    }

    private void a(int i) {
        if (258 == i) {
            p();
        } else if (259 == i) {
            v();
        } else if (260 == i) {
            u();
        } else if (261 == i) {
            t();
        } else if (b.j == i) {
            s();
        } else if (b.k == i) {
            r();
        } else if (b.l == i) {
            q();
        } else if (257 == i) {
            n();
        } else if (256 == i) {
            o();
        }
    }

    private void a(int i, int i2) {
        p();
        Toast.makeText(this.t, this.t.getString(R.string.rcupgrade_package_error), 1).show();
    }

    public void handleRcStatus() {
        int i = this.v.i();
        if (258 == i) {
            p();
        } else if (259 == i) {
            v();
        } else if (260 == i) {
            u();
        } else if (261 == i) {
            t();
        } else if (b.j == i) {
            s();
        } else if (b.k == i) {
            r();
        } else if (b.l == i) {
            q();
        } else if (257 == i) {
            n();
        } else if (256 == i) {
            o();
        }
    }

    private void n() {
        this.a.show();
        this.d.show();
        this.e.show();
        this.e.setText(R.string.rcupgrade_cant_upgrade);
        this.e.setEnabled(false);
        this.f.go();
    }

    private void o() {
        this.a.go();
        this.d.go();
        this.e.go();
        this.f.go();
    }

    private void p() {
        this.a.show();
        if (this.v.g()) {
            this.d.show();
            this.e.show();
            this.e.setText(R.string.rcupgrade_download_package);
            this.e.setEnabled(true);
            this.f.go();
            ProductType a = i.getInstance().a();
            if (a == ProductType.litchiS || a == ProductType.litchiX) {
                this.c.setVisibility(0);
                return;
            } else {
                this.c.setVisibility(8);
                return;
            }
        }
        this.d.go();
        this.e.go();
        this.c.setVisibility(8);
        this.f.go();
    }

    private void q() {
        this.a.show();
        this.d.go();
        this.e.go();
        this.f.show();
        this.h.setText(R.string.rcupgrade_upgrade_fail);
        this.h.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rc_upgrade_fail, 0, 0, 0);
        this.j.show();
        this.j.setText(a((int) R.string.rcupgrade_current_version, this.v.k()));
        this.k.show();
        this.k.setText(a((int) R.string.rcupgrade_download_version, this.v.l()));
        this.m.setText(R.string.rcupgrade_upgrade_tip);
        this.n.hide();
        this.q.show();
        this.s.setText(R.string.rcupgrade_upgrade_retry);
    }

    private void r() {
        this.a.go();
        this.d.go();
        this.e.go();
        this.f.show();
        this.h.setText(R.string.rcupgrade_upgrade_success);
        this.h.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rc_upgrade_success, 0, 0, 0);
        this.j.go();
        this.k.go();
        this.m.setText(a((int) R.string.rcupgrade_upgrade_success_tip, this.v.l(), Integer.valueOf(5)));
        this.n.hide();
        this.q.show();
        this.s.setText(R.string.rcupgrade_delete_package);
    }

    private void s() {
        this.a.show();
        this.d.go();
        this.e.go();
        this.f.show();
        this.h.setText(R.string.rcupgrade_upgrade_now);
        this.h.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rc_upgrade_upgrade, 0, 0, 0);
        this.j.show();
        this.j.setText(a((int) R.string.rcupgrade_current_version, this.v.k()));
        this.k.show();
        this.k.setText(a((int) R.string.rcupgrade_download_version, this.v.l()));
        int o = this.v.o();
        this.m.setText(a((int) R.string.rcupgrade_upgrade_percent, Integer.valueOf((o * 100) / 200)));
        this.n.show();
        this.o.setProgress(o);
        this.p.go();
        this.q.go();
    }

    private void t() {
        this.a.show();
        this.d.go();
        this.e.go();
        this.f.show();
        this.h.setText(R.string.rcupgrade_download_success);
        this.h.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rc_upgrade_success, 0, 0, 0);
        this.j.go();
        this.k.show();
        if (this.v.n() != null) {
            this.k.setText(a((int) R.string.rcupgrade_download_version, r0.mVersion));
        } else {
            this.k.setText(a((int) R.string.rcupgrade_download_version, this.v.l()));
        }
        this.m.setText(R.string.rcupgrade_upgrade_tip);
        this.n.hide();
        this.q.show();
        this.s.setText(R.string.rcupgrade_upgrade);
    }

    private void u() {
        this.a.show();
        this.d.go();
        this.e.go();
        this.f.show();
        if (this.v.n() != null) {
            this.h.setText(R.string.rcupgrade_download_package_fail);
            this.h.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rc_upgrade_fail, 0, 0, 0);
            this.j.go();
            this.k.show();
            this.k.setText(a((int) R.string.rcupgrade_download_version, r0.mVersion));
            this.m.setText(R.string.rcupgrade_download_nettip);
            this.n.hide();
            this.q.show();
            this.s.setText(R.string.rcupgrade_download_retry);
        }
    }

    private void v() {
        this.a.show();
        this.d.go();
        this.e.go();
        this.f.show();
        DJIDLPackageInfo n = this.v.n();
        if (n != null) {
            this.j.go();
            this.k.show();
            this.k.setText(a((int) R.string.rcupgrade_download_version, n.mVersion));
            int a = this.v.a(n);
            this.m.setText(a((int) R.string.rcupgrade_download_percent, Integer.valueOf((a * 100) / 200)));
            this.n.show();
            this.o.setProgress(a);
            if (n.mDLStatus == 2 || n.mDLStatus == 0) {
                this.h.setText(R.string.rcupgrade_download_now);
                this.h.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rc_upgrade_downloading, 0, 0, 0);
                this.p.setImageResource(R.drawable.rc_upgrade_pause);
            } else {
                this.h.setText(R.string.rcupgrade_download_pause);
                this.h.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rc_upgrade_download_pause, 0, 0, 0);
                this.p.setImageResource(R.drawable.rc_upgrade_download);
            }
            this.q.show();
            this.s.setText(R.string.rcupgrade_download_cancel);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        e();
        a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        handleRcStatus();
        this.v.a(this.w);
        this.v.a(this.x);
        this.v.a(this.y);
    }

    protected void onDetachedFromWindow() {
        this.v.c();
        this.v.d();
        this.v.e();
        super.onDetachedFromWindow();
    }
}
