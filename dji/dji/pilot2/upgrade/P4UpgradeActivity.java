package dji.pilot2.upgrade;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.dbox.upgrade.p4.statemachine.f;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIScrollTextView;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.publics.object.b;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.afinal.f.a;
import dji.thirdparty.g.b.b.a.c;
import java.text.DecimalFormat;

public class P4UpgradeActivity extends DJIActivityNoFullScreen {
    public static MoreReleaseNoteModel a = null;
    protected static P4UpgradeActivity b;
    protected static String c = "msg_what";
    protected static String d = "msg_a1";
    protected static String t = "msg_a2";
    protected static String u = "msg_obj";
    private DJITextView A;
    private View B;
    private View C;
    private ProgressBar D;
    private DJITextView E;
    private DJITextView F;
    private DJITextView G;
    private ImageView H;
    private View I;
    private boolean J = true;
    private f K;
    private a L;
    private String M = "";
    private a<String> N = new 6(this);
    protected Handler v = new Handler(new 12(this));
    private View w;
    private ImageView x;
    private String y = "1.0.0.1";
    private DJITextView z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_upgrade_p4_activity);
        a();
        b();
        this.L = new a(this.x);
        a((int) R.string.v2_upgrade_activity_start_wait, a.a);
        b = this;
        this.K = DJIUpgradeP4Service.i();
        Intent intent = getIntent();
        this.v.sendMessage(this.v.obtainMessage(intent.getIntExtra(c, 0), intent.getIntExtra(d, 0), intent.getIntExtra(t, 0), intent.getStringExtra(u)));
    }

    private void a() {
        this.w = findViewById(R.id.d11);
        this.x = (ImageView) findViewById(R.id.d16);
        this.A = (DJITextView) findViewById(R.id.d13);
        this.z = (DJITextView) findViewById(R.id.d14);
        this.G = (DJITextView) findViewById(R.id.d15);
        this.H = (ImageView) findViewById(R.id.d12);
        this.I = findViewById(R.id.d19);
        this.C = findViewById(R.id.d1c);
        this.D = (ProgressBar) findViewById(R.id.d0n);
        this.E = (DJITextView) findViewById(R.id.d0o);
        this.F = (DJITextView) findViewById(R.id.d1b);
        this.F.setOnClickListener(new 1(this));
        if (!this.J) {
            findViewById(R.id.d10).setVisibility(4);
        }
        findViewById(R.id.d10).setOnClickListener(new 8(this));
        this.B = findViewById(R.id.d17);
        findViewById(R.id.d17).setOnClickListener(new 9(this));
        a(null);
    }

    private void b() {
        if (dji.dbox.upgrade.p4.a.a.v != null) {
            if (dji.dbox.upgrade.p4.a.a.v.cfgModel != null) {
                this.z.setText(new StringBuilder(new DecimalFormat("##0.0").format((double) ((float) (dji.dbox.upgrade.p4.a.a.v.cfgModel.g / 1048576)))).append("MB"));
            }
            if (dji.dbox.upgrade.p4.a.a.v.release_note != null) {
                this.y = dji.dbox.upgrade.p4.a.a.v.product_version;
                this.A.setText(new StringBuilder(c.il_).append(this.y).append(getResources().getString(R.string.fpv_about_version)).toString());
                CharSequence charSequence = dji.dbox.upgrade.p4.a.a.v.release_note.get();
                if (!TextUtils.isEmpty(charSequence)) {
                    ((DJIScrollTextView) findViewById(R.id.d18)).setText(charSequence);
                }
            }
            l();
        }
    }

    private void f() {
        Builder bVar = new b(this, R.style.hu);
        bVar.setMessage(R.string.v2_upgrade_activity_dlg_interupt_title1);
        bVar.setNegativeButton(R.string.btn_dlg_no, new 10(this));
        bVar.setPositiveButton(R.string.btn_dlg_yes, new 11(this));
        bVar.show();
    }

    public void onBackPressed() {
        g();
    }

    private void g() {
        if (!this.J) {
            return;
        }
        if (dji.dbox.upgrade.p4.a.a.n) {
            f();
        } else {
            finish();
        }
    }

    protected void onDestroy() {
        dji.thirdparty.a.c.a().d((Object) this);
        b = null;
        this.v = null;
        this.L.c();
        super.onDestroy();
    }

    private void a(int i, a aVar) {
        this.F.setText(i);
        this.F.setTag(aVar);
        a(this.F);
    }

    private void a(int i) {
        this.E.setText(i);
        a(this.C);
    }

    private void a(int i, int i2) {
        this.D.setProgress(i2);
        this.E.setText(getResources().getString(i, new Object[]{i2 + "%"}));
        a(this.C);
    }

    private void a(dji.midware.data.config.P3.a aVar) {
        String str = "";
        switch (7.b[aVar.ordinal()]) {
            case 1:
                str = getResources().getString(R.string.v2_upgrade_p4_fail_motorworking);
                break;
            case 2:
                str = getResources().getString(R.string.v2_upgrade_p4_fail_rcnotconnect);
                break;
        }
        b(str);
    }

    private void b(String str) {
        a(this.I);
        this.L.b();
        CharSequence string = getResources().getString(R.string.v2_upgrade_activity_finish_desc_fails, new Object[]{getResources().getString(R.string.v2_tabhost_equipment)});
        if (str != null) {
            string = string + getResources().getString(R.string.v2_upgrade_p4_fail_reason) + str;
        }
        Builder builder = new Builder(this, R.style.hu);
        builder.setTitle(R.string.v2_upgrade_activity_finish_fails);
        builder.setMessage(string);
        builder.setPositiveButton(R.string.btn_dlg_yes, new 13(this));
        builder.show();
    }

    private void h() {
        CharSequence string = getResources().getString(R.string.v2_upgrade_activity_upgrade_fails_dlg_collect_msg);
        Builder bVar = new b(this, R.style.hu);
        bVar.setTitle(R.string.v2_upgrade_activity_finish_fails);
        bVar.setMessage(string);
        bVar.setNegativeButton(R.string.btn_dlg_no, new 14(this));
        bVar.setPositiveButton(R.string.btn_dlg_yes, new 15(this));
        bVar.show();
    }

    private void i() {
        dji.pilot2.upgrade.a.a.getInstance().a(null);
    }

    @Deprecated
    private void j() {
        CharSequence string = getResources().getString(R.string.v2_upgrade_activity_upgrade_fails_dlg_upload_msg);
        Builder bVar = new b(this, R.style.hu);
        bVar.setTitle(R.string.v2_upgrade_activity_finish_fails);
        bVar.setMessage(string);
        bVar.setNegativeButton(R.string.btn_dlg_no, new 2(this));
        bVar.setPositiveButton(R.string.btn_dlg_yes, new 3(this));
        bVar.show();
    }

    private final void a(View view) {
        if (view == null) {
            this.I.setVisibility(4);
            this.F.setVisibility(4);
            this.C.setVisibility(4);
            return;
        }
        int id = view.getId();
        if (id == this.I.getId()) {
            if (this.I.getVisibility() != 0) {
                this.I.setVisibility(0);
                this.F.setVisibility(4);
                this.C.setVisibility(4);
            }
        } else if (id == this.F.getId()) {
            if (this.F.getVisibility() != 0) {
                this.I.setVisibility(4);
                this.F.setVisibility(0);
                this.C.setVisibility(4);
            }
        } else if (id == this.C.getId() && this.C.getVisibility() != 0) {
            this.I.setVisibility(4);
            this.F.setVisibility(4);
            this.C.setVisibility(0);
        }
    }

    private void b(int i) {
        Builder bVar = new b(this, R.style.hu);
        bVar.setTitle(R.string.v2_upgrade_dialog_title);
        bVar.setMessage(i);
        bVar.setPositiveButton(R.string.app_ok, new 4(this));
        bVar.show();
    }

    private void k() {
        Builder bVar = new b(this, R.style.hu);
        bVar.setTitle(R.string.v2_upgrade_dialog_title);
        bVar.setMessage(R.string.v2_upgrade_complete_tip_osmo);
        bVar.setPositiveButton(R.string.app_ok, new 5(this));
        bVar.show();
    }

    private void l() {
        this.B.setVisibility(4);
        dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
        bVar.a("product", "p4");
        bVar.a("version", dji.dbox.upgrade.p4.a.a.v.product_version);
        com.dji.frame.c.c.b(this).a("Token", dji.pilot.server.a.a());
        com.dji.frame.c.c.b(this).a(dji.dbox.upgrade.p4.a.a.g, bVar, this.N);
    }

    public static void a(String str) {
        DJILogHelper.getInstance().LOGE("P4UpgradeActivity", str, false, false);
    }
}
