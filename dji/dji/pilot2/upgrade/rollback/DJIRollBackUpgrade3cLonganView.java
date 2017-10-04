package dji.pilot2.upgrade.rollback;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.publics.control.p3cupgrade.b;
import dji.pilot.publics.control.p3cupgrade.b.e;
import dji.pilot.publics.control.p3cupgrade.b.i;
import dji.pilot.publics.control.p3cupgrade.b.j;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import dji.pilot.upgrade.b$b;
import dji.pilot.upgrade.d;
import dji.pilot2.upgrade.rollback.a.a;
import dji.pilot2.upgrade.rollback.widget.DJIRBProgressBar;
import dji.thirdparty.a.c;

public class DJIRollBackUpgrade3cLonganView extends LinearLayout {
    private DJIUpgradePack a;
    private b$b b;
    private b c;
    private TextView d;
    private TextView e;
    private DJIRBProgressBar f;
    private TextView g;
    private TextView h;
    private TextView i;
    private int j;

    public DJIRollBackUpgrade3cLonganView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            a();
        }
    }

    private void a() {
        this.d = (TextView) findViewById(R.id.cwh);
        this.e = (TextView) findViewById(R.id.cwi);
        this.f = (DJIRBProgressBar) findViewById(R.id.cwg);
        this.g = (TextView) findViewById(R.id.cwk);
        this.h = (TextView) findViewById(R.id.cwl);
        this.i = (TextView) findViewById(R.id.cwm);
        this.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIRollBackUpgrade3cLonganView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.c != null) {
                    j h = this.a.c.h();
                    if (h == j.DOWNLOAD_PAUSE || h == j.START_WAIT_DOWNLOAD) {
                        this.a.c.c();
                    } else if (h == j.UPGRADE_PAUSE || h == j.START_WAIT_UPGRADE || h == j.WAITINGT_TO_UPGRADE) {
                        this.a.c.d();
                    }
                }
            }
        });
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        b();
    }

    private void b() {
        d.a("DJIRollBackUpgrade3cLonganView updateValue");
        DJIUpgradePack g = a.getInstance().g();
        b$b b = a.getInstance().b();
        d.a("DJIRollBackUpgrade3cLonganView updateValue pack " + g);
        d.a("DJIRollBackUpgrade3cLonganView updateValue type " + b);
        if (g != null && b != null && (b == b$b.P3c || b == b$b.OSMO || b == b$b.P34k)) {
            d.a("DJIRollBackUpgrade3cLonganView updateValue 1");
            if (this.c != null) {
                this.c.a();
                this.c = null;
                d.a("DJIRollBackUpgrade3cLonganView updateValue 2");
            }
            this.a = g;
            this.b = b;
            this.c = new b();
            this.c.b(true);
            this.c.a(this.a, dji.pilot.upgrade.b.a(this.b));
            d.a("DJIRollBackUpgrade3cLonganView updateValue 3");
        } else if (this.c != null) {
            this.c.a();
            this.c = null;
            d.a("DJIRollBackUpgrade3cLonganView updateValue 4");
        }
        d.a("DJIRollBackUpgrade3cLonganView updateValue 5");
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void onEventMainThread(a aVar) {
        b();
    }

    public void onEventMainThread(b.a aVar) {
        this.f.setProgress(aVar.a);
        String str = aVar.b + "%";
        if (aVar.c > 0 && aVar.c > aVar.d) {
            StringBuilder stringBuilder = new StringBuilder(str);
            stringBuilder.append("(");
            stringBuilder.append((aVar.d / 1024) / 1024);
            stringBuilder.append("MB/");
            stringBuilder.append((aVar.c / 1024) / 1024);
            stringBuilder.append("MB)");
            str = stringBuilder.toString();
        }
        this.j++;
        int i = this.j % 4;
        if (i == 1) {
            str = str + ".  ";
        } else if (i == 2) {
            str = str + ".. ";
        } else if (i == 3) {
            str = str + "...";
        } else {
            str = str + "   ";
        }
        this.d.setText(getResources().getString(R.string.v2_upgrade_activity_downloading, new Object[]{str}));
    }

    public void onEventMainThread(i iVar) {
        CharSequence charSequence;
        this.f.setProgress(iVar.a);
        this.j++;
        String string = getResources().getString(R.string.v2_upgrade_activity_upgrading, new Object[]{iVar.a + "%"});
        int i = this.j % 4;
        if (i == 1) {
            charSequence = string + ".  ";
        } else if (i == 2) {
            charSequence = string + ".. ";
        } else if (i == 3) {
            charSequence = string + "...";
        } else {
            charSequence = string + "   ";
        }
        this.d.setText(charSequence);
    }

    public void onEventMainThread(b.c cVar) {
        a(cVar.a);
    }

    public void onEventMainThread(e eVar) {
        b(eVar.a);
    }

    public void onEventMainThread(b bVar) {
        if (this.c == bVar) {
            switch (bVar.h()) {
                case INIT_FAILS:
                    setFailsView(R.string.v2_upgrade_init_fails);
                    break;
                case NOT_NEED_UPGRADE:
                    setFailsView(R.string.v2_upgrade_not_need);
                    break;
                case START_WAIT_DOWNLOAD:
                case DOWNLOAD_PAUSE:
                    this.h.setVisibility(8);
                    this.i.setVisibility(8);
                    this.f.setVisibility(8);
                    this.d.setVisibility(8);
                    this.e.setVisibility(0);
                    this.g.setVisibility(0);
                    this.g.setText(R.string.v2_upgrade_activity_down_btn);
                    this.e.setText(R.string.rcupgrade_upgrade_tip);
                    break;
                case CHECKING_DOWNLOAD_NETWORK:
                case DOWNLOADING:
                    this.h.setVisibility(8);
                    this.i.setVisibility(8);
                    this.f.setVisibility(0);
                    this.d.setVisibility(0);
                    this.e.setVisibility(8);
                    this.g.setVisibility(8);
                    this.f.setProgress(this.c.f().a);
                    this.d.setText(getResources().getString(R.string.v2_upgrade_activity_downloading, new Object[]{this.c.f().b + "%"}));
                    break;
                case START_WAIT_UPGRADE:
                case WAITINGT_TO_UPGRADE:
                case UPGRADE_PAUSE:
                    this.h.setVisibility(8);
                    this.i.setVisibility(8);
                    this.f.setVisibility(8);
                    this.d.setVisibility(8);
                    this.e.setVisibility(0);
                    this.g.setVisibility(0);
                    this.g.setText(R.string.v2_upgrade_activity_upgrade_btn);
                    this.e.setText(R.string.rcupgrade_upgrade_tip);
                    break;
                case CHECKING_UPGRADE_BASE:
                case UPGRADING:
                    this.h.setVisibility(8);
                    this.i.setVisibility(8);
                    this.f.setVisibility(0);
                    this.d.setVisibility(0);
                    this.e.setVisibility(8);
                    this.g.setVisibility(8);
                    this.f.setProgress(this.c.g().a);
                    this.d.setText(getResources().getString(R.string.v2_upgrade_activity_upgrading, new Object[]{this.c.g().a + "%"}));
                    break;
                case UPGRADE_FAILS:
                    setFailsView(R.string.v2_upgrade_activity_finish_desc_fails);
                    break;
                case UPGRADE_SUCCESS:
                    c();
                    break;
            }
            c.a().e(DJIRollBackActivity$b.Change);
        }
    }

    private void c() {
        this.h.setVisibility(8);
        this.i.setVisibility(0);
        this.f.setVisibility(8);
        this.d.setVisibility(8);
        this.e.setVisibility(8);
        this.g.setVisibility(8);
        this.i.setText(R.string.v2_upgrade_activity_finish);
    }

    private void setFailsView(int i) {
        this.h.setVisibility(0);
        this.i.setVisibility(8);
        this.f.setVisibility(8);
        this.d.setVisibility(8);
        this.e.setVisibility(8);
        this.g.setVisibility(8);
        ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
        this.h.setText(String.format(getResources().getString(i), new Object[]{c._name()}));
    }

    private void a(int i) {
        a(R.string.v2_upgrade_dialog_error_title, i, false);
    }

    private void b(int i) {
        a(R.string.v2_upgrade_dialog_title, i, false);
    }

    private void a(int i, int i2, final boolean z) {
        Builder bVar = new dji.pilot2.publics.object.b(getContext(), R.style.hu);
        bVar.setTitle(i);
        bVar.setMessage(i2);
        bVar.setPositiveButton(R.string.app_ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ DJIRollBackUpgrade3cLonganView b;

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (!z) {
                }
            }
        });
        bVar.show();
    }

    public boolean canExit() {
        if (this.c == null) {
            return true;
        }
        j h = this.c.h();
        if (j.DOWNLOADING == h || j.UPGRADING == h) {
            return false;
        }
        return true;
    }
}
