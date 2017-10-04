package dji.pilot2.upgrade.rollback;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.dbox.upgrade.p4.a.a;
import dji.dbox.upgrade.p4.model.DJIUpListElement;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.dbox.upgrade.p4.statemachine.f;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCommonGetPushUpgradeStatus;
import dji.midware.data.model.P3.DataCommonGetPushUpgradeStatus.DJIUpgradeCompleteReason;
import dji.midware.data.model.P3.DataCommonGetPushUpgradeStatus.DataCommonGetPushUpgradeStatusDescInfo;
import dji.pilot.R;
import dji.pilot.publics.widget.CustomerSpinner;
import dji.pilot2.upgrade.rollback.widget.DJIRBProgressBar;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.util.ArrayList;

public class b implements OnClickListener {
    private static int t = 0;
    private static int u = 1;
    private final DJITextView a;
    private String b = getClass().getSimpleName();
    private DJITextView c = null;
    private DJITextView d = null;
    private DJITextView e = null;
    private DJITextView f = null;
    private CustomerSpinner g = null;
    private ProgressBar h = null;
    private DJITextView i = null;
    private DJIRollBackUpgradeP4View j = null;
    private DJIRBProgressBar k = null;
    private DJITextView l = null;
    private DJITextView m = null;
    private Context n;
    private f o;
    private String p;
    private String q;
    private String r;
    private String s;
    private Handler v = new Handler(new Callback(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (!DJIUpgradeP4Service.g()) {
                        this.a.j.go();
                        break;
                    }
                    this.a.g.setEnabled(true);
                    this.a.q = a.q;
                    this.a.r = a.p;
                    ProductType c = i.getInstance().c();
                    if (c == ProductType.KumquatX || c == ProductType.KumquatS) {
                        this.a.d.setText(this.a.n.getResources().getString(R.string.v2_rollback_cur_version, new Object[]{this.a.q}) + "(RC)");
                    } else {
                        this.a.d.setText(this.a.n.getResources().getString(R.string.v2_rollback_cur_version, new Object[]{this.a.q}));
                    }
                    this.a.g();
                    this.a.v.sendEmptyMessage(3);
                    break;
                    break;
                case 1:
                    this.a.h();
                    break;
                case 2:
                    this.a.a(message.arg1, message.arg2);
                    break;
                case 3:
                    if (DJIUpgradeP4Service.g()) {
                        this.a.i();
                        break;
                    }
                    break;
                case 6:
                    this.a.j();
                    break;
                case 7:
                    if (!(message.obj instanceof dji.midware.data.config.P3.a)) {
                        if (!(message.obj instanceof DJIUpgradeCompleteReason)) {
                            this.a.a(message.obj == null ? null : message.obj.toString());
                            break;
                        }
                        this.a.a(b.a((DJIUpgradeCompleteReason) message.obj));
                        break;
                    }
                    this.a.a((dji.midware.data.config.P3.a) message.obj);
                    break;
                case 8:
                case 9:
                    this.a.l.setText(R.string.rcupgrade_upgradep4_timeout);
                    break;
                case 100:
                    this.a.f.setText(this.a.n.getString(R.string.v2_rollback_select_version) + " (Loading)");
                    break;
                case 101:
                    this.a.f.setText(this.a.n.getString(R.string.v2_rollback_select_version));
                    break;
                case 102:
                    this.a.f.setText(this.a.n.getString(R.string.v2_rollback_select_version) + " (Failed to load)");
                    break;
            }
            return false;
        }
    });
    private final int w = 100;
    private final int x = 101;
    private final int y = 102;
    private dji.dbox.upgrade.p4.b.b z = new dji.dbox.upgrade.p4.b.b(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void e() {
        }

        public void g() {
            this.a.v.sendEmptyMessage(7);
        }

        public void f() {
            this.a.v.sendMessage(this.a.v.obtainMessage(2, b.u, 5));
        }

        public void h() {
            this.a.v.sendEmptyMessage(5);
        }

        public void b(int i) {
            this.a.v.sendMessage(this.a.v.obtainMessage(2, b.u, ((int) Math.round(((double) i) * 0.25d)) + 5));
        }

        public void a(dji.dbox.upgrade.p4.b.b.a aVar, dji.midware.data.config.P3.a aVar2) {
            this.a.v.sendMessage(this.a.v.obtainMessage(7, aVar2));
        }

        public void i() {
        }

        public void a(String str, int i) {
            this.a.v.sendMessage(this.a.v.obtainMessage(2, b.u, ((int) Math.round(((double) i) * 0.7d)) + 30));
        }

        public void a(DJIUpgradeCompleteReason dJIUpgradeCompleteReason) {
            this.a.v.sendMessage(this.a.v.obtainMessage(7, dJIUpgradeCompleteReason));
        }

        public void j() {
            this.a.v.sendMessage(this.a.v.obtainMessage(2, b.u, 100));
            this.a.v.sendEmptyMessageDelayed(6, 300);
        }

        public void c() {
            this.a.v.sendEmptyMessage(1);
        }

        public void a(int i) {
            this.a.v.sendMessage(this.a.v.obtainMessage(2, b.t, i));
        }

        public void b(String str) {
            this.a.v.sendMessage(this.a.v.obtainMessage(7, str));
        }

        public void d() {
            this.a.v.sendMessage(this.a.v.obtainMessage(2, b.u, 0));
        }

        public void a() {
            DJILogHelper.getInstance().LOGE(this.a.b, "onCollectStart");
            this.a.v.sendEmptyMessage(100);
        }

        public void a(String str) {
            DJILogHelper.getInstance().LOGE(this.a.b, "onCollectFail " + str);
            this.a.v.sendEmptyMessage(102);
        }

        public void l() {
            DJILogHelper.getInstance().LOGE(this.a.b, "onCollectProductTypeComplete");
            this.a.v.sendEmptyMessage(0);
        }

        public void k() {
            DJILogHelper.getInstance().LOGE(this.a.b, "onCollectDeviceComplete");
            this.a.v.sendEmptyMessage(0);
        }

        public void b() {
            DJILogHelper.getInstance().LOGE(this.a.b, "onCollectComplete");
            this.a.v.sendEmptyMessage(101);
            this.a.v.sendEmptyMessage(0);
        }

        public void m() {
            this.a.v.sendEmptyMessage(9);
        }
    };

    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] a = new int[o.values().length];
        static final /* synthetic */ int[] b = new int[dji.midware.data.config.P3.a.values().length];

        static {
            try {
                b[dji.midware.data.config.P3.a.A.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[dji.midware.data.config.P3.a.B.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[o.b.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[o.a.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public b(Activity activity) {
        this.n = activity;
        this.c = (DJITextView) activity.findViewById(R.id.cz7);
        this.d = (DJITextView) activity.findViewById(R.id.cz8);
        this.a = (DJITextView) activity.findViewById(R.id.cz9);
        this.e = (DJITextView) activity.findViewById(R.id.czc);
        this.f = (DJITextView) activity.findViewById(R.id.cza);
        this.g = (CustomerSpinner) activity.findViewById(R.id.czb);
        this.h = (ProgressBar) activity.findViewById(R.id.czd);
        this.i = (DJITextView) activity.findViewById(R.id.cz4);
        this.j = (DJIRollBackUpgradeP4View) activity.findViewById(R.id.cz3);
        this.k = (DJIRBProgressBar) this.j.findViewById(R.id.cwg);
        this.l = (DJITextView) this.j.findViewById(R.id.cwi);
        this.m = (DJITextView) this.j.findViewById(R.id.cz2);
        this.c.setText(this.n.getResources().getString(R.string.v2_rollback_product, new Object[]{"Loading"}));
        this.d.setText(this.n.getResources().getString(R.string.v2_rollback_cur_version, new Object[]{"Loading"}));
        this.o = DJIUpgradeP4Service.i();
        this.o.a(this.z);
        if (!(!DJIUpgradeP4Service.g() || DJIUpgradeP4Service.a == DJIUpgradeP4Service.a.d || a.o.equals(""))) {
            this.v.sendEmptyMessage(0);
        }
        this.m.setOnClickListener(this);
        c.a().a(this);
    }

    private void e() {
        final ArrayList arrayList = new ArrayList();
        String[] strArr = new String[a.t.size()];
        for (int i = 0; i < a.t.size(); i++) {
            DJIUpListElement dJIUpListElement = (DJIUpListElement) a.t.get(i);
            if (dJIUpListElement.isAllow) {
                arrayList.add(Integer.valueOf(a.t.indexOf(dJIUpListElement)));
                strArr[i] = dJIUpListElement.product_version;
            }
        }
        this.g.setData(strArr);
        if (a.t != null && a.t.size() > 0 && arrayList.size() > 0) {
            a.u = (DJIUpListElement) a.t.get(((Integer) arrayList.get(0)).intValue());
            f();
            this.g.setOnItemClickListener(new OnItemClickListener(this) {
                final /* synthetic */ b b;

                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    if (a.t != null && arrayList != null) {
                        a.u = (DJIUpListElement) a.t.get(((Integer) arrayList.get(i)).intValue());
                        this.b.f();
                    }
                }
            });
        }
    }

    private void f() {
        this.i.setText("ReleaseNote : " + a.u.release_note.get());
    }

    private static void a(Context context, String str) {
        Builder bVar = new dji.pilot2.publics.object.b(context, R.style.hu);
        bVar.setTitle(R.string.v2_upgrade_dialog_title);
        bVar.setMessage(str);
        bVar.setPositiveButton(R.string.app_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        bVar.show();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r7) {
        /*
        r6 = 2131301604; // 0x7f0914e4 float:1.822127E38 double:1.0530029035E-314;
        r1 = 1;
        r0 = 0;
        r5 = 50;
        r2 = dji.midware.data.model.P3.DataFlycGetPushSmartBattery.getInstance();
        r2 = r2.getBattery();
        r3 = dji.midware.data.manager.P3.i.getInstance();
        r3 = r3.c();
        r4 = dji.midware.data.config.P3.ProductType.KumquatS;
        if (r3 == r4) goto L_0x0027;
    L_0x001b:
        r3 = dji.midware.data.manager.P3.i.getInstance();
        r3 = r3.c();
        r4 = dji.midware.data.config.P3.ProductType.KumquatX;
        if (r3 != r4) goto L_0x0067;
    L_0x0027:
        r3 = dji.midware.data.model.P3.DataRcGetPushBatteryInfo.getInstance();
        r3 = r3.getBattery();
        r4 = dji.midware.data.manager.P3.ServiceManager.getInstance();
        r4 = r4.isRemoteOK();
        if (r4 == 0) goto L_0x0051;
    L_0x0039:
        if (r3 < r5) goto L_0x003d;
    L_0x003b:
        if (r2 >= r5) goto L_0x0069;
    L_0x003d:
        r2 = r7.getResources();
        r1 = new java.lang.Object[r1];
        r3 = java.lang.Integer.valueOf(r5);
        r1[r0] = r3;
        r1 = r2.getString(r6, r1);
        a(r7, r1);
    L_0x0050:
        return r0;
    L_0x0051:
        if (r3 >= r5) goto L_0x0069;
    L_0x0053:
        r2 = r7.getResources();
        r1 = new java.lang.Object[r1];
        r3 = java.lang.Integer.valueOf(r5);
        r1[r0] = r3;
        r1 = r2.getString(r6, r1);
        a(r7, r1);
        goto L_0x0050;
    L_0x0067:
        if (r2 >= r5) goto L_0x0069;
    L_0x0069:
        r0 = r1;
        goto L_0x0050;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot2.upgrade.rollback.b.a(android.content.Context):boolean");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cz2:
                if (a(this.n)) {
                    this.h.setVisibility(0);
                    this.h.setIndeterminate(true);
                    this.m.go();
                    this.o.a(a.u);
                    this.l.setText(R.string.rcupgrade_upgradep4_now);
                    this.k.setProgress(0);
                    this.k.show();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void a() {
        this.o.b(this.z);
        c.a().d(this);
    }

    private void g() {
        String str = "";
        if (DJIUpgradeP4Service.e()) {
            this.l.setText(R.string.rcupgrade_upgradep4_tip);
        } else if (!a.n && (i.getInstance().c() == ProductType.KumquatS || i.getInstance().c() == ProductType.KumquatX)) {
            if (ServiceManager.getInstance().isRemoteOK()) {
                this.l.setText(R.string.rcupgrade_upgrade220ac_tip);
            } else {
                this.l.setText(R.string.rcupgrade_upgrade220rc_tip);
            }
        }
        this.p = DJIUpgradeP4Service.b() + str;
        this.c.setText(this.n.getResources().getString(R.string.v2_rollback_product, new Object[]{this.p}));
        if (this.r.equals("") || !ServiceManager.getInstance().isRemoteOK()) {
            this.a.go();
            return;
        }
        this.a.show();
        this.a.setText(this.n.getResources().getString(R.string.v2_rollback_cur_version, new Object[]{this.r}) + "(AC)");
    }

    public void onEventMainThread(o oVar) {
        switch (AnonymousClass5.a[oVar.ordinal()]) {
            case 1:
                g();
                return;
            case 2:
                g();
                return;
            default:
                return;
        }
    }

    private void h() {
        this.g.setEnabled(false);
    }

    private void i() {
        if (a.t != null && a.t.size() > 0) {
            this.j.show();
            if (this.o.c()) {
                this.k.show();
                this.m.go();
                this.m.setText(R.string.rcupgrade_upgrade);
                h();
            } else {
                this.k.go();
                this.m.show();
                this.m.setText(R.string.rcupgrade_upgrade);
            }
            this.e.go();
            e();
        }
    }

    private void a(dji.midware.data.config.P3.a aVar) {
        String str = "";
        switch (AnonymousClass5.b[aVar.ordinal()]) {
            case 1:
                str = this.n.getResources().getString(R.string.v2_upgrade_p4_fail_motorworking);
                break;
            case 2:
                str = this.n.getResources().getString(R.string.v2_upgrade_p4_fail_rcnotconnect);
                break;
        }
        a(str);
    }

    public static String a(DJIUpgradeCompleteReason dJIUpgradeCompleteReason) {
        String str = "" + "0x" + dji.midware.util.c.d(dJIUpgradeCompleteReason.value());
        DataCommonGetPushUpgradeStatus instance = DataCommonGetPushUpgradeStatus.getInstance();
        if (instance.getDescList().mUpgradeDescList.size() <= 0) {
            return str;
        }
        DataCommonGetPushUpgradeStatusDescInfo dataCommonGetPushUpgradeStatusDescInfo = (DataCommonGetPushUpgradeStatusDescInfo) instance.getDescList().mUpgradeDescList.get(0);
        return (str + "-" + dji.midware.util.c.d(dataCommonGetPushUpgradeStatusDescInfo.mDeviceId)) + "-" + dji.midware.util.c.d(dataCommonGetPushUpgradeStatusDescInfo.mUpgradeStatus);
    }

    private void a(String str) {
        this.g.setEnabled(true);
        if (str == null) {
            this.l.setText(R.string.rcupgrade_upgrade_fail);
        } else {
            this.l.setText(this.n.getResources().getString(R.string.rcupgrade_upgrade_fail) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str);
        }
        this.h.setVisibility(4);
        this.m.show();
        this.m.setText(R.string.rcupgrade_upgrade_retry);
    }

    private void j() {
        this.g.setEnabled(true);
        this.l.setText(R.string.rcupgrade_upgradep4_success);
    }

    private void a(int i, int i2) {
        this.k.setProgress(i2);
        if (i == t) {
            this.l.setText(this.n.getResources().getString(R.string.rcupgrade_download_percent, new Object[]{Integer.valueOf(i2)}));
            return;
        }
        this.l.setText(this.n.getResources().getString(R.string.rcupgrade_upgradep4_percent, new Object[]{Integer.valueOf(i2)}));
    }

    public boolean b() {
        return !this.o.c();
    }
}
