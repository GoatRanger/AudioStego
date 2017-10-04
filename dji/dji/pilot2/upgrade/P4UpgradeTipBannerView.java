package dji.pilot2.upgrade;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.tencent.android.tpush.common.Constants;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.dbox.upgrade.p4.statemachine.f;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCommonGetPushUpgradeStatus.DJIUpgradeCompleteReason;
import dji.pilot.R;
import dji.pilot2.upgrade.rollback.DJIRollBackActivity$a;
import dji.publics.DJIUI.DJILinearLayout;
import dji.thirdparty.a.c;
import java.util.List;

public class P4UpgradeTipBannerView extends DJILinearLayout {
    public static final String a = "P4UpgradeTipBannerView";
    public static dji.pilot.publics.objects.DJINetWorkReceiver.a b = dji.pilot.publics.objects.DJINetWorkReceiver.a.c;
    public static dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service.a c = dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service.a.d;
    public static boolean d = false;
    private static int p = 0;
    private static int q = 1;
    private String e = "P4";
    private String f = "1.0.0.1";
    private View g;
    private View h;
    private TextView i;
    private TextView j;
    private f k;
    private final int l = 0;
    private final int m = 5;
    private final int n = 30;
    private dji.dbox.upgrade.p4.b.b o = new dji.dbox.upgrade.p4.b.b(this) {
        final /* synthetic */ P4UpgradeTipBannerView a;

        {
            this.a = r1;
        }

        public void a() {
            DJILogHelper.getInstance().LOGE(P4UpgradeTipBannerView.a, "onCollectStart");
            this.a.v.sendMessage(this.a.v.obtainMessage(a.CollectStart.ordinal()));
        }

        public void a(String str) {
            DJILogHelper.getInstance().LOGE(P4UpgradeTipBannerView.a, "onCollectFail " + str);
            this.a.v.sendMessage(this.a.v.obtainMessage(a.CollectFail.ordinal(), str));
        }

        public void b() {
            DJILogHelper.getInstance().LOGE(P4UpgradeTipBannerView.a, "onCollectComplete");
            this.a.v.sendMessage(this.a.v.obtainMessage(a.CollectComplete.ordinal()));
        }

        public void k() {
            this.a.v.sendMessage(this.a.v.obtainMessage(a.CollectDeviceComplete.ordinal()));
        }

        public void c() {
            this.a.v.sendMessage(this.a.v.obtainMessage(a.DownloadStart.ordinal()));
        }

        public void a(int i) {
            this.a.v.sendMessage(this.a.v.obtainMessage(a.DownloadProgress.ordinal(), i, a.UnuseMsgArg2.ordinal()));
        }

        public void b(String str) {
            this.a.v.sendMessage(this.a.v.obtainMessage(a.DownloadFail.ordinal(), str));
        }

        public void d() {
            this.a.v.sendMessage(this.a.v.obtainMessage(a.DownloadComplete.ordinal()));
        }

        public void e() {
            this.a.v.sendMessage(this.a.v.obtainMessage(a.UpgradeStart.ordinal()));
            this.a.v.sendMessage(this.a.v.obtainMessage(a.UpgradeProgress.ordinal(), 0, a.UnuseMsgArg2.ordinal()));
        }

        public void g() {
            this.a.v.sendMessage(this.a.v.obtainMessage(a.UpgradeFail.ordinal()));
        }

        public void f() {
        }

        public void h() {
            this.a.v.sendMessage(this.a.v.obtainMessage(a.UpgradeProgress.ordinal(), 5, a.UnuseMsgArg2.ordinal()));
        }

        public void b(int i) {
            this.a.v.sendMessage(this.a.v.obtainMessage(a.UpgradeProgress.ordinal(), ((i * 25) / 100) + 5, a.UnuseMsgArg2.ordinal()));
        }

        public void a(dji.dbox.upgrade.p4.b.b.a aVar, dji.midware.data.config.P3.a aVar2) {
            this.a.v.sendMessage(this.a.v.obtainMessage(a.UpgradeFail.ordinal(), aVar2));
        }

        public void i() {
        }

        public void a(String str, int i) {
            DJILogHelper.getInstance().LOGE(P4UpgradeTipBannerView.a, "detail=" + str, true, true);
            this.a.v.sendMessage(this.a.v.obtainMessage(a.UpgradeProgress.ordinal(), ((int) Math.round((((double) i) * 0.01d) * 70.0d)) + 30, a.UnuseMsgArg2.ordinal()));
        }

        public void a(DJIUpgradeCompleteReason dJIUpgradeCompleteReason) {
            this.a.v.sendMessage(this.a.v.obtainMessage(a.UpgradeFail.ordinal(), dji.pilot2.upgrade.rollback.b.a(dJIUpgradeCompleteReason)));
        }

        public void j() {
            this.a.v.sendMessageDelayed(this.a.v.obtainMessage(a.UpgradeComplete.ordinal()), 300);
        }

        public void l() {
        }

        public void m() {
            this.a.v.sendMessage(this.a.v.obtainMessage(a.WaitTimeout.ordinal()));
        }
    };
    private int r = 0;
    private int s = 0;
    private int t = 0;
    private String u = null;
    private Handler v = new Handler(new Callback(this) {
        final /* synthetic */ P4UpgradeTipBannerView a;
        private Handler b = null;

        {
            this.a = r2;
        }

        public boolean handleMessage(Message message) {
            if (P4UpgradeActivity.b != null) {
                this.b = P4UpgradeActivity.b.v;
                if (this.b != null) {
                    this.b.sendMessage(this.b.obtainMessage(message.what, message.arg1, message.arg2, message.obj));
                }
            }
            this.a.r = message.what;
            this.a.s = message.arg1;
            this.a.t = message.arg2;
            this.a.u = message.obj == null ? null : message.obj.toString();
            if (a.CollectComplete.ordinal() == this.a.r) {
                this.a.e = DJIUpgradeP4Service.b();
                if (dji.dbox.upgrade.p4.a.a.s && dji.dbox.upgrade.p4.a.a.v != null) {
                    this.a.setVisibility(0);
                    this.a.f = dji.dbox.upgrade.p4.a.a.v.product_version;
                    this.a.h();
                } else if (!this.a.i()) {
                    this.a.setVisibility(8);
                }
            } else if (a.DownloadStart.ordinal() == this.a.r) {
                this.a.g();
            } else if (a.DownloadProgress.ordinal() == this.a.r) {
                this.a.show();
                this.a.updateUpgradeProgress(P4UpgradeTipBannerView.p, message.arg1);
            } else if (a.UpgradeProgress.ordinal() == this.a.r) {
                this.a.show();
                this.a.updateUpgradeProgress(P4UpgradeTipBannerView.q, message.arg1);
            } else if (a.UpgradeStart.ordinal() == this.a.r) {
                this.a.f();
            } else if (a.UpgradeFail.ordinal() == this.a.r) {
                this.a.show();
                this.a.l();
            } else if (a.UpgradeComplete.ordinal() == this.a.r) {
                this.a.show();
                this.a.k();
            }
            return false;
        }
    });

    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] b = new int[dji.pilot.publics.objects.DJINetWorkReceiver.a.values().length];

        static {
            c = new int[b.values().length];
            try {
                c[b.Show.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                c[b.Go.ordinal()] = 2;
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
        None,
        UnuseMsgArg2,
        ConnectOK,
        ConnectClose,
        CollectStart,
        CollectProgress,
        CollectFail,
        CollectComplete,
        CollectDeviceComplete,
        DownloadStart,
        DownloadProgress,
        DownloadFail,
        DownloadComplete,
        ZipStart,
        ZipFail,
        ZipComplete,
        UploadStart,
        UploadProgress,
        UploadFail,
        UploadComplete,
        UpgradeStart,
        UpgradeProgress,
        UpgradeFail,
        UpgradeComplete,
        LogCollectStart,
        LogCollectComplete,
        LogUploadStart,
        LogUploadComplete,
        WaitTimeout
    }

    public enum b {
        Show,
        Go
    }

    public P4UpgradeTipBannerView(Context context) {
        super(context);
    }

    public P4UpgradeTipBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public P4UpgradeTipBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.k = DJIUpgradeP4Service.i();
            this.k.a(this.o);
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

    public void onEventBackgroundThread(dji.pilot.publics.objects.DJINetWorkReceiver.a aVar) {
        switch (AnonymousClass4.b[aVar.ordinal()]) {
            case 1:
                this.k.g();
                break;
        }
        b = aVar;
    }

    public void onEventMainThread(b bVar) {
        switch (bVar) {
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

    public void onEventMainThread(dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service.a aVar) {
        c = aVar;
        if (aVar == dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service.a.d && !dji.dbox.upgrade.p4.a.a.n) {
            setVisibility(4);
            if (P4UpgradeActivity.b != null) {
                P4UpgradeActivity.b.finish();
            }
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
        intent.putExtra(P4UpgradeActivity.c, a.CollectComplete.ordinal());
        intent.putExtra(P4UpgradeActivity.d, 0);
        intent.putExtra(P4UpgradeActivity.t, 0);
        context.startActivity(intent);
    }

    private void c() {
        this.g = findViewById(R.id.cpc);
        this.h = findViewById(R.id.d1f);
        this.i = (TextView) findViewById(R.id.d1e);
        this.j = (TextView) findViewById(R.id.d1g);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ P4UpgradeTipBannerView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.i()) {
                    this.a.r = a.UpgradeComplete.ordinal();
                    this.a.s = 0;
                    this.a.t = 0;
                    this.a.u = null;
                }
                if (this.a.j()) {
                    this.a.r = a.UpgradeFail.ordinal();
                    this.a.s = 0;
                    this.a.t = 0;
                }
                Intent intent = new Intent(this.a.getContext(), P4UpgradeActivity.class);
                intent.putExtra(P4UpgradeActivity.c, this.a.r);
                intent.putExtra(P4UpgradeActivity.d, this.a.s);
                intent.putExtra(P4UpgradeActivity.t, this.a.t);
                intent.putExtra(P4UpgradeActivity.u, this.a.u);
                this.a.getContext().startActivity(intent);
            }
        });
    }

    public void updateUpgradeProgress(int i, int i2) {
        int i3;
        if (!this.h.isShown()) {
            this.g.setVisibility(8);
            this.h.setVisibility(0);
            this.h.setBackgroundResource(R.color.nf);
        }
        if (i == p) {
            i3 = R.string.v2_upgrade_tip_downloading;
        } else {
            i3 = R.string.v2_upgrade_tip_upgrading;
        }
        this.j.setText(getResources().getString(i3, new Object[]{i2 + "%"}));
    }

    private void d() {
        this.g.setVisibility(8);
        this.h.setVisibility(0);
        this.h.setBackgroundResource(R.color.nc);
        this.j.setText(R.string.v2_upgrade_tip_down_pause);
    }

    private void e() {
        this.g.setVisibility(8);
        this.h.setVisibility(0);
        this.h.setBackgroundResource(R.color.nf);
        this.j.setText(R.string.v2_upgrade_tip_upgrade_wait);
    }

    private void f() {
        this.g.setVisibility(8);
        this.h.setVisibility(0);
        this.h.setBackgroundResource(R.color.nf);
        updateUpgradeProgress(q, 0);
    }

    private void g() {
        this.g.setVisibility(8);
        this.h.setVisibility(0);
        this.h.setBackgroundResource(R.color.nc);
        updateUpgradeProgress(p, 0);
    }

    private void h() {
        this.g.setVisibility(0);
        this.h.setVisibility(8);
        this.j.setText(getResources().getString(R.string.v2_upgrade_tip_downloading, new Object[]{"0%"}));
        this.i.setText(getResources().getString(R.string.v2_upgrade_tip_new_upgrade_desc, new Object[]{this.e, this.f}));
    }

    private boolean i() {
        if (this.j.getText().equals(getResources().getString(R.string.v2_upgrade_tip_upgrade_finish))) {
            return true;
        }
        return false;
    }

    private boolean j() {
        if (this.j.getText().equals(getResources().getString(R.string.v2_upgrade_tip_upgrade_fails))) {
            return true;
        }
        return false;
    }

    private void k() {
        this.g.setVisibility(8);
        this.h.setVisibility(0);
        this.h.setBackgroundResource(R.color.ne);
        this.j.setText(R.string.v2_upgrade_tip_upgrade_finish);
    }

    private void l() {
        this.g.setVisibility(8);
        this.h.setVisibility(0);
        this.h.setBackgroundResource(R.color.nd);
        this.j.setText(R.string.v2_upgrade_tip_upgrade_fails);
    }

    public int isForeground(String str) {
        Context context = getContext();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME);
        String packageName = context.getPackageName();
        List runningTasks = activityManager.getRunningTasks(1);
        if (runningTasks.size() > 0) {
            ComponentName componentName = ((RunningTaskInfo) runningTasks.get(0)).topActivity;
            if (packageName.equals(componentName.getPackageName())) {
                if (componentName.getClassName().equals(str)) {
                    DJILogHelper.getInstance().LOGD(a, str + "在运行", true, true);
                    return 2;
                }
                DJILogHelper.getInstance().LOGD(a, str + "不在前台运行,而是" + componentName.getClassName() + "在前台运行", true, true);
                return 1;
            }
        }
        DJILogHelper.getInstance().LOGD(a, packageName + "后台运行", true, true);
        return 0;
    }
}
