package dji.pilot2.upgrade;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.dji.frame.c.f;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.stat.StatService;
import dji.pilot.R;
import dji.pilot.publics.c.d;
import dji.pilot.publics.control.p3cupgrade.b.e;
import dji.pilot.publics.control.p3cupgrade.b.i;
import dji.pilot.publics.control.p3cupgrade.b.j;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.thirdparty.a.c;
import java.io.File;

public class P3cUpgradeActivity extends DJIActivityNoFullScreen implements OnClickListener {
    private ProgressBar a;
    private TextView b;
    private ProgressBar c;
    private TextView d;
    private dji.pilot.publics.control.p3cupgrade.b t;
    private TextView u;
    private TextView v;
    private TextView w;
    private TextView x;
    private String y;
    private int z;

    public static class a {
        int a;
    }

    public enum b {
        SHOWING,
        HIDDEN
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_upgrade_activity);
        a();
        this.t = b.getInstance().h();
        if (!dji.pilot.publics.control.p3cupgrade.b.a) {
            if (this.t == null || this.t.h() == null) {
                Toast.makeText(this, R.string.v2_upgrade_info_error, 0).show();
            } else {
                onEventMainThread(this.t.h());
                this.y = d.getInstance().a(this.t.k()).collegeName;
                f();
            }
        }
        c.a().a(this);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        c.a().d(this);
        this.t = null;
    }

    private void a() {
        findViewById(R.id.d0q).setOnClickListener(this);
        findViewById(R.id.d0t).setOnClickListener(this);
        findViewById(R.id.d0u).setOnClickListener(this);
        findViewById(R.id.d0y).setOnClickListener(this);
        findViewById(R.id.cpe).setOnClickListener(this);
        this.a = (ProgressBar) findViewById(R.id.d0n);
        this.c = (ProgressBar) findViewById(R.id.d0w);
        this.b = (TextView) findViewById(R.id.d0o);
        this.d = (TextView) findViewById(R.id.d0x);
        this.u = (TextView) findViewById(R.id.d0e);
        this.v = (TextView) findViewById(R.id.d0g);
        this.w = (TextView) findViewById(R.id.d0k);
        this.x = (TextView) findViewById(R.id.d0h);
        this.a.setProgress(10);
        this.c.setProgress(20);
    }

    public void onEventMainThread(dji.pilot.publics.control.p3cupgrade.b.a aVar) {
        this.a.setProgress(aVar.a);
        String str = aVar.b + "%";
        StringBuilder stringBuilder;
        if (aVar.c >= StatService.BYTES_IN_MEGA) {
            if (aVar.c > 0 && aVar.c > aVar.d) {
                stringBuilder = new StringBuilder(str);
                stringBuilder.append(" (");
                stringBuilder.append((aVar.d / 1024) / 1024);
                stringBuilder.append("MB/");
                stringBuilder.append((aVar.c / 1024) / 1024);
                stringBuilder.append("MB)");
                str = stringBuilder.toString();
            }
        } else if (aVar.c > 0 && aVar.c > aVar.d) {
            stringBuilder = new StringBuilder(str);
            stringBuilder.append(" (");
            stringBuilder.append(aVar.d / 1024);
            stringBuilder.append("KB/");
            stringBuilder.append(aVar.c / 1024);
            stringBuilder.append("KB)");
            str = stringBuilder.toString();
        }
        this.z++;
        int i = this.z % 4;
        if (i == 1) {
            str = str + ".  ";
        } else if (i == 2) {
            str = str + ".. ";
        } else if (i == 3) {
            str = str + "...";
        } else {
            str = str + "   ";
        }
        this.b.setText(getResources().getString(R.string.v2_upgrade_activity_downloading, new Object[]{str}));
    }

    public void onEventMainThread(i iVar) {
        CharSequence charSequence;
        this.c.setProgress(iVar.a);
        this.z++;
        String string = getResources().getString(R.string.v2_upgrade_activity_upgrading, new Object[]{iVar.a + "%"});
        int i = this.z % 4;
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

    public void onEventMainThread(dji.pilot.publics.control.p3cupgrade.b.c cVar) {
        Log.d(this.TAG, "有错误发生咯~~");
        b(cVar.a);
    }

    public void onEventMainThread(e eVar) {
        c(eVar.a);
    }

    public void onEventMainThread(j jVar) {
        switch (jVar) {
            case INIT_FAILS:
                a(R.string.v2_upgrade_init_fails);
                return;
            case NOT_NEED_UPGRADE:
                a(R.string.v2_upgrade_not_need);
                return;
            case START_WAIT_DOWNLOAD:
                findViewById(R.id.d0c).setVisibility(8);
                findViewById(R.id.d0y).setVisibility(8);
                findViewById(R.id.d0f).setVisibility(0);
                findViewById(R.id.d0p).setVisibility(0);
                findViewById(R.id.d0s).setVisibility(8);
                findViewById(R.id.d0m).setVisibility(8);
                findViewById(R.id.d0v).setVisibility(8);
                this.x.setVisibility(0);
                this.x.setText(this.t.p());
                findViewById(R.id.d0i).setVisibility(8);
                findViewById(R.id.d0j).setVisibility(8);
                findViewById(R.id.d0u).setVisibility(8);
                if (this.t.b()) {
                    findViewById(R.id.d0r).setVisibility(8);
                    return;
                } else {
                    findViewById(R.id.d0r).setVisibility(8);
                    return;
                }
            case DOWNLOAD_PAUSE:
                findViewById(R.id.d0c).setVisibility(8);
                findViewById(R.id.d0y).setVisibility(8);
                findViewById(R.id.d0f).setVisibility(0);
                findViewById(R.id.d0p).setVisibility(8);
                findViewById(R.id.d0s).setVisibility(0);
                findViewById(R.id.d0m).setVisibility(8);
                findViewById(R.id.d0v).setVisibility(8);
                this.x.setVisibility(0);
                this.x.setText(this.t.p());
                findViewById(R.id.d0i).setVisibility(8);
                findViewById(R.id.d0j).setVisibility(8);
                findViewById(R.id.d0u).setVisibility(8);
                findViewById(R.id.d0r).setVisibility(8);
                return;
            case CHECKING_DOWNLOAD_NETWORK:
            case DOWNLOADING:
                findViewById(R.id.d0c).setVisibility(8);
                findViewById(R.id.d0y).setVisibility(8);
                findViewById(R.id.d0f).setVisibility(0);
                findViewById(R.id.d0p).setVisibility(8);
                findViewById(R.id.d0s).setVisibility(8);
                findViewById(R.id.d0m).setVisibility(0);
                findViewById(R.id.d0v).setVisibility(8);
                this.x.setVisibility(0);
                this.x.setText(this.t.p());
                findViewById(R.id.d0i).setVisibility(8);
                findViewById(R.id.d0j).setVisibility(8);
                findViewById(R.id.d0u).setVisibility(8);
                findViewById(R.id.d0r).setVisibility(8);
                this.a.setProgress(this.t.f().a);
                this.b.setText(getResources().getString(R.string.v2_upgrade_activity_downloading, new Object[]{this.t.f().b + "%"}));
                return;
            case START_WAIT_UPGRADE:
            case WAITINGT_TO_UPGRADE:
            case UPGRADE_PAUSE:
                findViewById(R.id.d0c).setVisibility(8);
                findViewById(R.id.d0y).setVisibility(8);
                findViewById(R.id.d0f).setVisibility(0);
                findViewById(R.id.d0p).setVisibility(8);
                findViewById(R.id.d0s).setVisibility(8);
                findViewById(R.id.d0m).setVisibility(8);
                findViewById(R.id.d0v).setVisibility(8);
                this.x.setVisibility(8);
                findViewById(R.id.d0v).setVisibility(8);
                findViewById(R.id.d0i).setVisibility(8);
                findViewById(R.id.d0u).setVisibility(0);
                findViewById(R.id.d0j).setVisibility(0);
                findViewById(R.id.d0r).setVisibility(8);
                TextView textView = (TextView) findViewById(R.id.d0l);
                File externalCacheDir = getExternalCacheDir();
                if (externalCacheDir == null) {
                    externalCacheDir = getCacheDir();
                }
                String a = f.a(new File(externalCacheDir.getAbsolutePath() + "/wifi_ssid.json"));
                if (dji.pilot.publics.e.d.a(a)) {
                    DJILogHelper.getInstance().LOGD("", "MMMMMMMgetLastType=" + dji.midware.data.manager.P3.i.getInstance().d());
                    if (dji.pilot.fpv.d.b.h(dji.midware.data.manager.P3.i.getInstance().d()) || dji.pilot.fpv.d.b.h(dji.midware.data.manager.P3.i.getInstance().c())) {
                        textView.setText(getResources().getString(R.string.v2_upgrade_activity_upgrade_unknow_ssid_longan));
                    } else if (dji.midware.data.manager.P3.i.getInstance().d() == ProductType.P34K) {
                        textView.setText(getResources().getString(R.string.v2_upgrade_activity_upgrade_unknow_ssid_p34k));
                    } else {
                        textView.setText(getResources().getString(R.string.v2_upgrade_activity_upgrade_unknow_ssid_p3c));
                    }
                } else {
                    DJILogHelper.getInstance().LOGD("", "MMMMMMMS=" + a);
                    textView.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + a);
                }
                if (dji.midware.data.manager.P3.i.getInstance().c() == ProductType.LonganMobile) {
                    textView.setVisibility(8);
                    return;
                } else {
                    textView.setVisibility(0);
                    return;
                }
            case CHECKING_UPGRADE_BASE:
                findViewById(R.id.d0c).setVisibility(8);
                findViewById(R.id.d0y).setVisibility(8);
                findViewById(R.id.d0f).setVisibility(0);
                findViewById(R.id.d0p).setVisibility(8);
                findViewById(R.id.d0s).setVisibility(8);
                findViewById(R.id.d0m).setVisibility(8);
                findViewById(R.id.d0v).setVisibility(8);
                this.x.setVisibility(8);
                findViewById(R.id.d0j).setVisibility(8);
                findViewById(R.id.d0v).setVisibility(0);
                findViewById(R.id.d0i).setVisibility(0);
                findViewById(R.id.d0u).setVisibility(8);
                findViewById(R.id.d0r).setVisibility(8);
                this.c.setProgress(this.t.g().a);
                this.d.setText(getResources().getString(R.string.v2_upgrade_activity_upgrading, new Object[]{this.t.g().a + "%"}));
                return;
            case AUTO_UPGRADE:
                dji.pilot2.utils.e eVar = new dji.pilot2.utils.e(this);
                f.a(new File(getExternalCacheDir().getAbsolutePath() + "/wifi_ssid.json"));
                f.a(new File(getExternalCacheDir().getAbsolutePath() + "/wifi_password.json"));
                eVar.b(new dji.pilot2.utils.e.a(this) {
                    final /* synthetic */ P3cUpgradeActivity a;

                    {
                        this.a = r1;
                    }

                    public void c() {
                    }

                    public void a() {
                    }

                    public void b() {
                    }
                });
                return;
            case UPGRADING:
                findViewById(R.id.d0c).setVisibility(8);
                findViewById(R.id.d0y).setVisibility(8);
                findViewById(R.id.d0f).setVisibility(0);
                findViewById(R.id.d0p).setVisibility(8);
                findViewById(R.id.d0s).setVisibility(8);
                findViewById(R.id.d0m).setVisibility(8);
                findViewById(R.id.d0v).setVisibility(8);
                this.x.setVisibility(8);
                findViewById(R.id.d0j).setVisibility(8);
                findViewById(R.id.d0v).setVisibility(0);
                findViewById(R.id.d0i).setVisibility(0);
                findViewById(R.id.d0u).setVisibility(8);
                findViewById(R.id.d0r).setVisibility(8);
                this.c.setProgress(this.t.g().a);
                this.d.setText(getResources().getString(R.string.v2_upgrade_activity_upgrading, new Object[]{this.t.g().a + "%"}));
                return;
            case UPGRADE_FAILS:
                a(R.string.v2_upgrade_activity_finish_desc_fails);
                return;
            case UPGRADE_SUCCESS:
                b();
                return;
            case STOP_NO_SHOW:
                finish();
                return;
            default:
                return;
        }
    }

    private void b() {
        findViewById(R.id.d0c).setVisibility(0);
        findViewById(R.id.d0y).setVisibility(0);
        findViewById(R.id.d0f).setVisibility(8);
        findViewById(R.id.d0p).setVisibility(8);
        findViewById(R.id.d0s).setVisibility(8);
        findViewById(R.id.d0m).setVisibility(8);
        findViewById(R.id.d0v).setVisibility(8);
        findViewById(R.id.d0r).setVisibility(8);
        TextView textView = (TextView) findViewById(R.id.d0e);
        ((TextView) findViewById(R.id.d0d)).setText(R.string.v2_upgrade_activity_finish);
        textView.setText(getResources().getString(R.string.v2_upgrade_activity_finish_desc, new Object[]{this.y}));
    }

    private void a(int i) {
        findViewById(R.id.d0c).setVisibility(0);
        findViewById(R.id.d0y).setVisibility(0);
        findViewById(R.id.d0f).setVisibility(8);
        findViewById(R.id.d0p).setVisibility(8);
        findViewById(R.id.d0s).setVisibility(8);
        findViewById(R.id.d0m).setVisibility(8);
        findViewById(R.id.d0v).setVisibility(8);
        findViewById(R.id.d0r).setVisibility(8);
        TextView textView = (TextView) findViewById(R.id.d0e);
        ((TextView) findViewById(R.id.d0d)).setText(R.string.v2_upgrade_activity_finish_fails);
        ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
        textView.setText(String.format(getResources().getString(i), new Object[]{c._name()}));
    }

    private void f() {
        this.u.setText(getResources().getString(R.string.v2_upgrade_activity_finish_desc, new Object[]{this.y}));
        this.v.setText(getResources().getString(R.string.v2_upgrade_activity_desc, new Object[]{this.y}));
        if (dji.midware.data.manager.P3.i.getInstance().c() == ProductType.LonganMobile) {
            this.w.setText(getResources().getString(R.string.v2_upgrade_activity_down_success_longan_mobiile, new Object[]{this.y, this.t.l()}));
            return;
        }
        this.w.setText(getResources().getString(R.string.v2_upgrade_activity_down_success, new Object[]{this.y, this.t.l(), this.y}));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cpe:
                finish();
                return;
            case R.id.d0q:
                this.t.c();
                return;
            case R.id.d0r:
                this.t.e();
                return;
            case R.id.d0t:
                this.t.c();
                return;
            case R.id.d0u:
                this.t.d();
                return;
            case R.id.d0y:
                g();
                return;
            default:
                return;
        }
    }

    private void b(int i) {
        a(R.string.v2_upgrade_dialog_error_title, i, false);
    }

    private void c(int i) {
        a(R.string.v2_upgrade_dialog_title, i, false);
    }

    private void g() {
        Builder bVar = new dji.pilot2.publics.object.b(this, R.style.hu);
        bVar.setTitle(R.string.v2_upgrade_dialog_title);
        if (dji.logic.f.d.a(dji.midware.data.manager.P3.i.getInstance().c())) {
            bVar.setMessage(R.string.v2_upgrade_complete_tip_osmo);
        } else {
            bVar.setMessage(R.string.v2_upgrade_complete_tip);
        }
        bVar.setPositiveButton(R.string.app_ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ P3cUpgradeActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                this.a.finish();
                c.a().e(new a());
            }
        });
        bVar.show();
        dji.publics.b.b.a.getInstance().f("createtime", System.currentTimeMillis() + "", false).f(dji.publics.b.a.b.x, "13", false).f("device_ver", dji.pilot.upgrade.e.getInstance().b(), false).f("device_type", dji.midware.data.manager.P3.i.getInstance().c()._name(), true);
    }

    private void a(int i, int i2, final boolean z) {
        Builder bVar = new dji.pilot2.publics.object.b(this, R.style.hu);
        bVar.setTitle(i);
        bVar.setMessage(i2);
        bVar.setPositiveButton(R.string.app_ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ P3cUpgradeActivity b;

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (z) {
                    this.b.finish();
                }
            }
        });
        bVar.show();
    }
}
