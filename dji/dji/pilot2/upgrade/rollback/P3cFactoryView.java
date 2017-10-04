package dji.pilot2.upgrade.rollback;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.odnp.debug.DebugFile;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.publics.control.p3cupgrade.b.i;
import dji.pilot.publics.control.p3cupgrade.b.j;
import dji.pilot.publics.control.upgrade.DLPackageInfo;
import dji.pilot.publics.control.upgrade.e;
import dji.pilot.publics.control.upgrade.e.a;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import dji.pilot.upgrade.FirmwareVersion;
import dji.pilot.upgrade.b$b;
import dji.pilot.upgrade.c;
import dji.pilot.upgrade.c.b;
import dji.pilot.upgrade.d;
import dji.pilot2.upgrade.rollback.widget.DJIRBProgressBar;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class P3cFactoryView extends LinearLayout implements b {
    public static int a = 0;
    ArrayList<FirmwareVersion> b;
    private DJIUpgradePack c;
    private b$b d;
    private String e;
    private dji.pilot.publics.control.p3cupgrade.b f;
    private TextView g;
    private TextView h;
    private View i;
    private TextView j;
    private TextView k;
    private DJIRBProgressBar l;
    private TextView m;
    private TextView n;
    private TextView o;
    private CheckBox p;
    private Handler q;
    private int r;

    public P3cFactoryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (a == 0) {
            this.e = Environment.getExternalStorageDirectory().getAbsolutePath() + "/P3C_Upgrade.bin";
        } else if (a == 2) {
            this.e = Environment.getExternalStorageDirectory().getAbsolutePath() + "/P3XW_Upgrade.bin";
        } else {
            this.e = Environment.getExternalStorageDirectory().getAbsolutePath() + "/OSMO_Upgrade.bin";
        }
        a();
        this.q = new Handler();
    }

    private void a() {
        if (!isInEditMode()) {
            this.g = (TextView) findViewById(R.id.cwd);
            this.h = (TextView) findViewById(R.id.cwf);
            this.i = findViewById(R.id.cwe);
            this.j = (TextView) findViewById(R.id.cwh);
            this.k = (TextView) findViewById(R.id.cwi);
            this.l = (DJIRBProgressBar) findViewById(R.id.cwg);
            this.m = (TextView) findViewById(R.id.cwk);
            this.n = (TextView) findViewById(R.id.cwl);
            this.o = (TextView) findViewById(R.id.cwm);
            this.p = (CheckBox) findViewById(R.id.cwj);
            this.m.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ P3cFactoryView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.f != null) {
                        j h = this.a.f.h();
                        if (h == j.DOWNLOAD_PAUSE || h == j.START_WAIT_DOWNLOAD) {
                            this.a.f.c();
                        } else if (h == j.UPGRADE_PAUSE || h == j.START_WAIT_UPGRADE || h == j.WAITINGT_TO_UPGRADE) {
                            this.a.f.b(this.a.p.isChecked());
                            this.a.f.d();
                        }
                    }
                }
            });
        }
    }

    private void b() {
        File file = new File(this.e);
        if (file.exists()) {
            this.g.setVisibility(8);
            this.i.setVisibility(0);
            DLPackageInfo dLPackageInfo = new DLPackageInfo();
            if (a == 0) {
                dLPackageInfo.mProductId = ProductType.litchiC.value();
            } else if (a == 2) {
                dLPackageInfo.mProductId = ProductType.P34K.value();
            } else {
                dLPackageInfo.mProductId = ProductType.Longan.value();
            }
            dLPackageInfo.mAbsPath = file.getAbsolutePath();
            dLPackageInfo.mDLSize = file.length();
            dLPackageInfo.mPackageSize = file.length();
            dLPackageInfo.mDLStatus = 3;
            if (a == 0) {
                dLPackageInfo.mDLUrl = "http://fake.dji.com/P3C_Upgrade.bin";
            } else {
                dLPackageInfo.mDLUrl = "http://fake.dji.com/OSMO_Upgrade.bin";
            }
            dLPackageInfo.mVersion = "50.50.50";
            dLPackageInfo.mFileName = "P3C_Upgrade.bin";
            dji.pilot.publics.control.upgrade.b.getInstance().a(dLPackageInfo);
            DJIUpgradePack a = a(this.e, dLPackageInfo.mDLUrl);
            this.f = new dji.pilot.publics.control.p3cupgrade.b();
            if (a == 0) {
                this.f.a(a, ProductType.litchiC);
            } else if (a == 2) {
                dLPackageInfo.mFileName = "P3XW_Upgrade.bin";
                this.f.a(a, ProductType.P34K);
            } else {
                this.f.a(a, ProductType.Longan);
            }
            c();
            return;
        }
        this.g.setVisibility(0);
        if (a == 0) {
            this.g.setText("根目录找不到升级文件，请将升级文件命名为“P3C_Upgrade.bin”,放置在sd卡根目录下，再重启app尝试");
        } else if (a == 2) {
            this.g.setText("根目录找不到升级文件，请将升级文件命名为“P3XW_Upgrade.bin”,放置在sd卡根目录下，再重启app尝试");
        } else {
            this.g.setText("根目录找不到升级文件，请将升级文件命名为“OSMO_Upgrade.bin”,放置在sd卡根目录下，再重启app尝试");
        }
        this.i.setVisibility(8);
    }

    private void c() {
        c cVar;
        if (a == 2) {
            cVar = new c(new String[]{"0400", "1100", "1101", "0100", "0101", "0305", "0306", "0700", "0900", "1200", "1201", "1202", "1203", "1400", "2700", "1700", "1701"}, this);
        } else if (a == 0) {
            cVar = new c(new String[]{"0400", "1100", "1101", "0100", "0101", "0305", "0306", "0700", "0900", "1200", "1201", "1202", "1203", "1400", "2700"}, this);
        } else {
            cVar = new c(new String[]{"0700", "0800", "0100", "0101", "0400", "0900"}, this);
        }
    }

    public void onResultCallBack(boolean z, ArrayList<FirmwareVersion> arrayList) {
        this.b = arrayList;
        this.q.post(new Runnable(this) {
            final /* synthetic */ P3cFactoryView a;

            {
                this.a = r1;
            }

            public void run() {
                e.c a;
                if (P3cFactoryView.a == 0) {
                    a = e.a(this.a.e, ProductType.litchiC, false);
                } else if (P3cFactoryView.a == 2) {
                    a = e.a(this.a.e, ProductType.P34K, false);
                } else {
                    a = e.a(this.a.e, ProductType.Longan, false);
                }
                StringBuilder stringBuilder = new StringBuilder();
                Iterator it = this.a.b.iterator();
                while (it.hasNext()) {
                    FirmwareVersion firmwareVersion = (FirmwareVersion) it.next();
                    a a2 = a.a(firmwareVersion.firmware);
                    if (a2 == null) {
                        stringBuilder.append(firmwareVersion.firmware + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + firmwareVersion.versionStr + "====>包里没这固件");
                        stringBuilder.append("(无需更新)");
                    } else {
                        stringBuilder.append(firmwareVersion.firmware + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + firmwareVersion.versionStr + "====>" + a2.l);
                        if (firmwareVersion.version < a2.f) {
                            stringBuilder.append("(**需要更新**)");
                        } else {
                            stringBuilder.append("(无需更新)");
                        }
                    }
                    stringBuilder.append(DebugFile.EOL);
                }
                this.a.h.setText(stringBuilder.toString());
            }
        });
    }

    private DJIUpgradePack a(String str, String str2) {
        e.c a;
        DJIUpgradePack dJIUpgradePack;
        if (a == 2) {
            a = e.a(str, ProductType.P34K, false);
            dJIUpgradePack = new DJIUpgradePack();
            dJIUpgradePack.date = 19871126;
            dJIUpgradePack.version = "50.50.50";
            dJIUpgradePack.packurl = str2;
            dJIUpgradePack.m0400 = a.b("0400");
            dJIUpgradePack.m1100 = a.b("1100");
            dJIUpgradePack.m1101 = a.b("1101");
            dJIUpgradePack.m0100 = a.b("0100");
            dJIUpgradePack.m0101 = a.b("0101");
            dJIUpgradePack.m0305 = a.b("0305");
            dJIUpgradePack.m0306 = a.b("0306");
            dJIUpgradePack.m0700 = a.b("0700");
            dJIUpgradePack.m0900 = a.b("0900");
            dJIUpgradePack.m1200 = a.b("1200");
            dJIUpgradePack.m1201 = a.b("1201");
            dJIUpgradePack.m1202 = a.b("1202");
            dJIUpgradePack.m1203 = a.b("1203");
            dJIUpgradePack.m1400 = a.b("1400");
            dJIUpgradePack.m2700 = a.b("2700");
            dJIUpgradePack.m1700 = a.b("1700");
            dJIUpgradePack.m1701 = a.b("1701");
            return dJIUpgradePack;
        } else if (a == 0) {
            a = e.a(str, ProductType.litchiC, false);
            dJIUpgradePack = new DJIUpgradePack();
            dJIUpgradePack.date = 19871126;
            dJIUpgradePack.version = "50.50.50";
            dJIUpgradePack.packurl = str2;
            dJIUpgradePack.m0400 = a.b("0400");
            dJIUpgradePack.m1100 = a.b("1100");
            dJIUpgradePack.m1101 = a.b("1101");
            dJIUpgradePack.m0100 = a.b("0100");
            dJIUpgradePack.m0101 = a.b("0101");
            dJIUpgradePack.m0305 = a.b("0305");
            dJIUpgradePack.m0306 = a.b("0306");
            dJIUpgradePack.m0700 = a.b("0700");
            dJIUpgradePack.m0900 = a.b("0900");
            dJIUpgradePack.m1200 = a.b("1200");
            dJIUpgradePack.m1201 = a.b("1201");
            dJIUpgradePack.m1202 = a.b("1202");
            dJIUpgradePack.m1203 = a.b("1203");
            dJIUpgradePack.m1400 = a.b("1400");
            dJIUpgradePack.m2700 = a.b("2700");
            return dJIUpgradePack;
        } else {
            a = e.a(str, ProductType.Longan, false);
            dJIUpgradePack = new DJIUpgradePack();
            dJIUpgradePack.date = 19871126;
            dJIUpgradePack.version = "50.50.50";
            dJIUpgradePack.packurl = str2;
            dJIUpgradePack.m0700 = a.b("0700");
            dJIUpgradePack.m0800 = a.b("0800");
            dJIUpgradePack.m0100 = a.b("0100");
            dJIUpgradePack.m0101 = a.b("0101");
            dJIUpgradePack.m0400 = a.b("0400");
            dJIUpgradePack.m0900 = a.b("0900");
            return dJIUpgradePack;
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            dji.thirdparty.a.c.a().e(DJIRollBackActivity$a.SHOWING);
            this.g.setVisibility(0);
            this.g.setText("不在工程模式，别乱进这页面，赶紧退出");
            this.i.setVisibility(8);
        }
    }

    private void d() {
        d.a("DJIRollBackUpgrade3cLonganView updateValue");
        DJIUpgradePack g = a.getInstance().g();
        b$b b = a.getInstance().b();
        d.a("DJIRollBackUpgrade3cLonganView updateValue pack " + g);
        d.a("DJIRollBackUpgrade3cLonganView updateValue type " + b);
        if (g != null && b != null && (b == b$b.P3c || b == b$b.OSMO || b$b.P34k == b)) {
            d.a("DJIRollBackUpgrade3cLonganView updateValue 1");
            if (this.f != null) {
                this.f.a();
                this.f = null;
                d.a("DJIRollBackUpgrade3cLonganView updateValue 2");
            }
            this.c = g;
            this.d = b;
            this.f = new dji.pilot.publics.control.p3cupgrade.b();
            this.f.b(true);
            this.f.a(this.c, dji.pilot.upgrade.b.a(this.d));
            d.a("DJIRollBackUpgrade3cLonganView updateValue 3");
        } else if (this.f != null) {
            this.f.a();
            this.f = null;
            d.a("DJIRollBackUpgrade3cLonganView updateValue 4");
        }
        d.a("DJIRollBackUpgrade3cLonganView updateValue 5");
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!isInEditMode()) {
            dji.thirdparty.a.c.a().e(DJIRollBackActivity$a.NONE);
            dji.thirdparty.a.c.a().d(this);
            if (this.f != null) {
                this.f.a();
                this.f = null;
            }
        }
    }

    public void onEventMainThread(a.a aVar) {
        d();
    }

    public void onEventMainThread(dji.pilot.publics.control.p3cupgrade.b.a aVar) {
        this.l.setProgress(aVar.a);
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
        this.r++;
        int i = this.r % 4;
        if (i == 1) {
            str = str + ".  ";
        } else if (i == 2) {
            str = str + ".. ";
        } else if (i == 3) {
            str = str + "...";
        } else {
            str = str + "   ";
        }
        this.j.setText(getResources().getString(R.string.v2_upgrade_activity_downloading, new Object[]{str}));
    }

    public void onEventMainThread(i iVar) {
        CharSequence charSequence;
        this.l.setProgress(iVar.a);
        this.r++;
        String string = getResources().getString(R.string.v2_upgrade_activity_upgrading, new Object[]{iVar.a + "%"});
        int i = this.r % 4;
        if (i == 1) {
            charSequence = string + ".  ";
        } else if (i == 2) {
            charSequence = string + ".. ";
        } else if (i == 3) {
            charSequence = string + "...";
        } else {
            charSequence = string + "   ";
        }
        this.j.setText(charSequence);
    }

    public void onEventMainThread(dji.pilot.publics.control.p3cupgrade.b.c cVar) {
        a(cVar.a);
    }

    public void onEventMainThread(dji.pilot.publics.control.p3cupgrade.b.e eVar) {
        b(eVar.a);
    }

    public void onEventMainThread(dji.pilot.publics.control.p3cupgrade.b bVar) {
        if (this.f == bVar) {
            switch (bVar.h()) {
                case INITING:
                    return;
                case INIT_FAILS:
                    setFailsView(R.string.v2_upgrade_init_fails);
                    return;
                case NOT_NEED_UPGRADE:
                    setFailsView(R.string.v2_upgrade_not_need);
                    return;
                case START_WAIT_DOWNLOAD:
                case DOWNLOAD_PAUSE:
                    this.n.setVisibility(8);
                    this.o.setVisibility(8);
                    this.l.setVisibility(8);
                    this.j.setVisibility(8);
                    this.k.setVisibility(0);
                    this.m.setVisibility(0);
                    this.m.setText(R.string.v2_upgrade_activity_down_btn);
                    this.k.setText(R.string.rcupgrade_upgrade_tip);
                    return;
                case CHECKING_DOWNLOAD_NETWORK:
                case DOWNLOADING:
                    this.n.setVisibility(8);
                    this.o.setVisibility(8);
                    this.l.setVisibility(0);
                    this.j.setVisibility(0);
                    this.k.setVisibility(8);
                    this.m.setVisibility(8);
                    this.l.setProgress(this.f.f().a);
                    this.j.setText(getResources().getString(R.string.v2_upgrade_activity_downloading, new Object[]{this.f.f().b + "%"}));
                    return;
                case START_WAIT_UPGRADE:
                case WAITINGT_TO_UPGRADE:
                case UPGRADE_PAUSE:
                    this.n.setVisibility(8);
                    this.o.setVisibility(8);
                    this.l.setVisibility(8);
                    this.j.setVisibility(8);
                    this.k.setVisibility(0);
                    this.m.setVisibility(0);
                    this.m.setText(R.string.v2_upgrade_activity_upgrade_btn);
                    this.k.setText(R.string.rcupgrade_upgrade_tip);
                    return;
                case CHECKING_UPGRADE_BASE:
                case UPGRADING:
                    this.n.setVisibility(8);
                    this.o.setVisibility(8);
                    this.l.setVisibility(0);
                    this.j.setVisibility(0);
                    this.k.setVisibility(8);
                    this.m.setVisibility(8);
                    this.l.setProgress(this.f.g().a);
                    this.j.setText(getResources().getString(R.string.v2_upgrade_activity_upgrading, new Object[]{this.f.g().a + "%"}));
                    return;
                case UPGRADE_FAILS:
                    setFailsView(R.string.v2_upgrade_activity_finish_desc_fails);
                    return;
                case UPGRADE_SUCCESS:
                    e();
                    return;
                default:
                    return;
            }
        }
    }

    private void e() {
        this.n.setVisibility(8);
        this.o.setVisibility(0);
        this.l.setVisibility(8);
        this.j.setVisibility(8);
        this.k.setVisibility(8);
        this.m.setVisibility(8);
        this.o.setText(R.string.v2_upgrade_activity_finish);
    }

    private void setFailsView(int i) {
        this.n.setVisibility(0);
        this.o.setVisibility(8);
        this.l.setVisibility(8);
        this.j.setVisibility(8);
        this.k.setVisibility(8);
        this.m.setVisibility(8);
        ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
        this.n.setText(String.format(getResources().getString(i), new Object[]{c._name()}));
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
            final /* synthetic */ P3cFactoryView b;

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (!z) {
                }
            }
        });
        bVar.show();
    }
}
