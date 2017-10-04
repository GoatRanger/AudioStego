package dji.pilot2.upgrade.rollback;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.pilot.R;
import dji.pilot.publics.control.rc.b;
import dji.pilot.publics.widget.CustomerSpinner;
import dji.pilot.publics.widget.k;
import dji.pilot.upgrade.d;
import dji.pilot2.DJIActivityFullScreen;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.afinal.a.b.c;
import java.util.ArrayList;

public class DJIRollBackActivity extends DJIActivityFullScreen implements OnClickListener {
    public static final String a = "key_rollback_type";
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    private static final String t = "DJIRollBackActivity";
    private static final boolean u = true;
    private static final boolean w = true;
    @c(a = 2131366856)
    private DJITextView A = null;
    @c(a = 2131366859)
    private DJITextView B = null;
    @c(a = 2131366857)
    private DJITextView C = null;
    @c(a = 2131366858)
    private CustomerSpinner D = null;
    @c(a = 2131366847)
    private DJIRollBackUpgrade3x3sView E;
    @c(a = 2131366748)
    private DJIRollBackUpgrade3cLonganView F;
    @c(a = 2131366849)
    private DJIRollBackUpgradeP4View G;
    private int H = 1;
    private k I = null;
    private b J;
    private a v = null;
    @c(a = 2131366851)
    private DJIImageView x = null;
    @c(a = 2131366853)
    private DJITextView y = null;
    @c(a = 2131366854)
    private DJITextView z = null;

    public static void a(Context context, int i) {
        Intent intent = new Intent(context, DJIRollBackActivity.class);
        intent.putExtra(a, i);
        context.startActivity(intent);
    }

    public static void a(Context context) {
        context.startActivity(new Intent(context, DJIRollBackActivity.class));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_rollback_view);
        a();
        this.v = a.getInstance();
        this.v.a(this);
        dji.thirdparty.a.c.a().a((Object) this);
        Intent intent = getIntent();
        if (intent != null) {
            this.H = intent.getIntExtra(a, this.H);
        }
        dji.thirdparty.a.c.a().e(a.b);
        this.J = new b(this);
        b();
    }

    private void a() {
        this.x.setOnClickListener(this);
        this.D.setShowType(2);
        this.D.setOnItemClickListener(new 1(this));
    }

    protected void onDestroy() {
        this.J.a();
        super.onDestroy();
        dji.thirdparty.a.c.a().d((Object) this);
        this.v.a();
        this.v = null;
    }

    public void onBackPressed() {
        e();
    }

    public void onEventMainThread(a aVar) {
        d.a(t, "DJIRollBackActivity DJIRollBackController ", true);
        if (this.v == aVar) {
            b();
        }
    }

    private void b() {
        if (!DJIUpgradeP4Service.g()) {
            switch (3.a[this.v.f().ordinal()]) {
                case 1:
                    d.a(t, "DJIRollBackActivity NONE", true);
                    this.y.setText(getResources().getString(R.string.v2_rollback_product, new Object[]{"N/A"}));
                    this.z.setText(getResources().getString(R.string.v2_rollback_cur_version, new Object[]{"N/A"}));
                    this.D.setEnabled(false);
                    this.D.setData(null);
                    this.D.setText("");
                    this.B.show();
                    this.E.setVisibility(8);
                    this.F.setVisibility(8);
                    this.G.setVisibility(8);
                    return;
                case 2:
                    d.a(t, "DJIRollBREADYackActivity READY", true);
                    this.y.setText(getResources().getString(R.string.v2_rollback_product, new Object[]{this.v.c()}));
                    this.z.setText(getResources().getString(R.string.v2_rollback_cur_version, new Object[]{this.v.d()}));
                    ArrayList e = this.v.e();
                    if (e == null || e.size() == 0) {
                        d.a(t, "DJIRollBackActivity READY 1", true);
                        this.D.setEnabled(false);
                        this.D.setData(null);
                        this.D.setText("");
                        this.B.setVisibility(0);
                        this.E.setVisibility(8);
                        this.F.setVisibility(8);
                        return;
                    }
                    d.a(t, "DJIRollBackActivity READY 2 isUpgrading=" + b.getInstance().j(), true);
                    if (b.getInstance().j()) {
                        d.a(t, "DJIRollBackActivity READY 3", true);
                        return;
                    }
                    String[] strArr = new String[e.size()];
                    for (int i = 0; i < e.size(); i++) {
                        strArr[i] = (String) e.get(i);
                    }
                    this.D.setEnabled(true);
                    this.D.setData(strArr);
                    this.B.setVisibility(8);
                    dji.pilot.upgrade.b.b b = this.v.b();
                    if (b == dji.pilot.upgrade.b.b.d || b == dji.pilot.upgrade.b.b.j || b == dji.pilot.upgrade.b.b.e) {
                        this.E.setVisibility(8);
                        this.F.setVisibility(0);
                        return;
                    }
                    this.E.setVisibility(0);
                    this.F.setVisibility(8);
                    return;
                default:
                    return;
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.cz5) {
            e();
        } else if (id != R.id.czb) {
        }
    }

    private void e() {
        if ((!this.F.isShown() || this.F.canExit()) && ((!this.E.isShown() || this.E.canExit()) && (!this.G.isShown() || this.J.b()))) {
            b.getInstance().b();
            dji.thirdparty.a.c.a().e(a.a);
            finish();
            return;
        }
        Builder bVar = new dji.pilot2.publics.object.b(this, R.style.hu);
        if (this.G.isShown()) {
            bVar.setMessage(R.string.v2_rollback_forbiden_quit_p4);
        } else {
            bVar.setMessage(R.string.v2_rollback_forbiden_quit);
        }
        bVar.setPositiveButton(R.string.app_ok, new 2(this));
        bVar.show();
    }

    public void onEventMainThread(b bVar) {
        d.a(t, "DJIRollBackActivity event=" + bVar + " isUpgrading=" + b.getInstance().j(), true);
        if ((this.F.getVisibility() == 0 && !this.F.canExit()) || (this.E.getVisibility() == 0 && !this.E.canExit())) {
            this.D.setEnabled(false);
        } else if (b.getInstance().j()) {
            this.D.setEnabled(false);
        } else {
            this.D.setEnabled(true);
        }
    }
}
