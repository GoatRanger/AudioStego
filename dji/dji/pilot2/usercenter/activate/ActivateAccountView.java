package dji.pilot2.usercenter.activate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.login.widget.ToolTipPopup;
import dji.log.DJILogHelper;
import dji.logic.f.d;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.pilot.R;
import dji.pilot.active.DJIActiveController;
import dji.pilot.active.DJIActiveController$a;
import dji.pilot.active.DJIActiveController$b;
import dji.pilot.publics.objects.DJINetWorkReceiver;
import dji.pilot.usercenter.b.f;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.utils.e;

public class ActivateAccountView extends RelativeLayout implements OnClickListener, b, f {
    final int a = 2;
    final int b = 3;
    final int c = 6;
    final int d = 8;
    final int e = 9;
    e f;
    private g g = null;
    private Handler h = new c();
    private TextView i;
    private TextView j;
    private View k;
    private ImageView l;
    private TextView m;
    private ImageView n;
    private e o;
    private a p = a.SHOW_WIFI_LIST;
    private DJIActiveController q;
    private boolean r = false;
    private boolean s = true;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;
    private boolean w = false;
    private boolean x = false;
    private dji.pilot2.publics.object.b y;

    public enum a {
        SHOW_WIFI_LIST,
        GO_TO_ACTIVATE
    }

    private class b implements DJIActiveController$b {
        final /* synthetic */ ActivateAccountView a;

        private b(ActivateAccountView activateAccountView) {
            this.a = activateAccountView;
        }

        public void a() {
        }

        public void b() {
            this.a.h.sendEmptyMessage(2);
            c.a("Activate Success!");
            a.getInstance().b(false);
        }

        public void c() {
            this.a.r = false;
            this.a.x = false;
            this.a.h.sendEmptyMessage(3);
            c.a("Activate Fail! fail type" + this.a.q.c());
        }

        public void d() {
            if (!ServiceManager.getInstance().isRemoteOK()) {
                this.a.r = true;
                this.a.x = true;
                d.getInstance().a(a.getInstance().a());
                this.a.h.sendEmptyMessageDelayed(9, ToolTipPopup.a);
                a.getInstance().b(true);
                c.a("Activate onNext!");
            }
        }
    }

    private class c extends Handler {
        final /* synthetic */ ActivateAccountView a;

        private c(ActivateAccountView activateAccountView) {
            this.a = activateAccountView;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 2:
                    this.a.t = false;
                    this.a.h.removeMessages(8);
                    com.dji.a.a.b(true);
                    if (!this.a.w) {
                        this.a.w = true;
                        this.a.g.a();
                    }
                    if (dji.thirdparty.a.c.a().c(this.a)) {
                        dji.thirdparty.a.c.a().d(this.a);
                        return;
                    }
                    return;
                case 3:
                    try {
                        String string;
                        this.a.m.setText(R.string.v2_active_79);
                        this.a.k.setEnabled(true);
                        if (this.a.q.c() == DJIActiveController$a.ErrorSN) {
                            string = this.a.getContext().getString(R.string.v2_active_errorsn_fail);
                        } else if (this.a.q.c() == DJIActiveController$a.FirmwareNotMatch) {
                            string = this.a.getContext().getString(R.string.v2_active_firmware_notmatch_fail);
                        } else {
                            int i;
                            if (dji.pilot.fpv.d.b.h(i.getInstance().c())) {
                                i = R.string.v2_active_791_osmo;
                            } else {
                                i = R.string.v2_active_791;
                            }
                            string = String.format(this.a.getContext().getString(i), new Object[]{this.a.q.c().toString()});
                        }
                        this.a.a((int) R.string.v2_active_792, string);
                        return;
                    } catch (Exception e) {
                        DJILogHelper.getInstance().LOGD("activate", "zHang", true, true);
                        return;
                    }
                case 6:
                    this.a.m.setText(R.string.v2_activate_activate);
                    this.a.k.setEnabled(true);
                    if (ServiceManager.getInstance().isConnected()) {
                        this.a.q.e();
                        return;
                    } else if (a.getInstance().f() != dji.pilot2.usercenter.activate.a.b.WIFI) {
                        this.a.n();
                        return;
                    } else if (this.a.q.b()) {
                        this.a.f();
                        return;
                    } else {
                        return;
                    }
                case 8:
                    if (this.a.v) {
                        this.a.p = a.GO_TO_ACTIVATE;
                        this.a.m.setText(R.string.v2_activate_activate);
                        this.a.b((int) R.string.v2_activate_activate, (int) R.string.activate_auto_conn_wifi_fail);
                        return;
                    }
                    return;
                case 9:
                    if (this.a.x) {
                        this.a.h.sendEmptyMessage(6);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public ActivateAccountView(Context context) {
        super(context);
    }

    public ActivateAccountView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActivateAccountView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean handleGoNext() {
        return false;
    }

    public boolean handleGoPre() {
        this.t = false;
        return false;
    }

    public boolean onResume() {
        updateView();
        this.t = true;
        return true;
    }

    public boolean onShow() {
        this.t = true;
        if (dji.logic.c.b.getInstance().b() && this.s) {
            k();
            this.v = true;
        }
        this.s = false;
        g();
        a();
        updateView();
        return true;
    }

    public boolean canShowTopView() {
        return true;
    }

    public void setMainTopViewControl(g gVar) {
        this.g = gVar;
    }

    public boolean canGoPre() {
        return true;
    }

    public boolean canGoNext() {
        return false;
    }

    public void onWifiSelected(WifiConfiguration wifiConfiguration) {
        d.getInstance().a(wifiConfiguration);
        this.o.a();
        this.p = a.GO_TO_ACTIVATE;
        this.v = true;
        this.h.sendEmptyMessageDelayed(8, ToolTipPopup.a);
        this.m.setText(R.string.activate_wifi_connecting);
    }

    public void onPopShow() {
        a(true);
    }

    public void onPopDismiss() {
        a(false);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c();
            b();
        }
    }

    private void a() {
        this.o = new e(getContext(), this.k, this);
        if (dji.logic.c.b.getInstance().b() && !dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
    }

    private void b() {
        View findViewById = findViewById(R.id.cs);
        this.i = (TextView) findViewById(R.id.cw);
        this.j = (TextView) findViewById(R.id.cv);
        this.k = findViewById(R.id.cx);
        this.l = (ImageView) findViewById(R.id.cy);
        this.m = (TextView) findViewById(R.id.cz);
        this.n = (ImageView) findViewById(R.id.d0);
        findViewById.setOnClickListener(this);
        this.k.setOnClickListener(this);
    }

    private void c() {
        if (!isInEditMode()) {
            this.q = new DJIActiveController(getContext(), new b());
            this.h = new c();
            this.f = new e(getContext());
        }
    }

    public void updateView() {
        if (f.getInstance().c()) {
            this.i.setVisibility(0);
            this.i.setText(f.getInstance().j());
            this.j.setText(R.string.v2_activate_change);
            return;
        }
        this.i.setVisibility(4);
        this.j.setText(R.string.v2_activate_sign_in_register);
    }

    public void onEventMainThread(dji.pilot.publics.objects.DJINetWorkReceiver.a aVar) {
        if (aVar == dji.pilot.publics.objects.DJINetWorkReceiver.a.a && !ServiceManager.getInstance().isRemoteOK() && this.v) {
            this.v = false;
            this.p = a.GO_TO_ACTIVATE;
            this.o.a();
            this.l.setVisibility(8);
            this.m.setText(R.string.v2_activate_activate);
            this.k.setEnabled(true);
        } else if ((aVar == dji.pilot.publics.objects.DJINetWorkReceiver.a.a || aVar == dji.pilot.publics.objects.DJINetWorkReceiver.a.b) && ServiceManager.getInstance().isRemoteOK() && this.x) {
            this.h.sendEmptyMessage(6);
            this.x = false;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cs:
                i();
                return;
            case R.id.cx:
            case R.id.cy:
            case R.id.cz:
                if (this.p == a.SHOW_WIFI_LIST && i.getInstance().c().isFromWifi()) {
                    l();
                    return;
                } else {
                    d();
                    return;
                }
            default:
                return;
        }
    }

    private boolean d() {
        if (f.getInstance().c()) {
            if (this.q.f()) {
                if (!i.getInstance().c().isFromWifi()) {
                    h();
                } else if (this.q.b() && !ServiceManager.getInstance().isConnected()) {
                    f();
                } else if (this.r) {
                    this.h.sendEmptyMessage(6);
                } else {
                    h();
                }
            }
            return true;
        }
        this.g.a(getContext().getString(R.string.v2_activate_sign_in_register));
        return false;
    }

    private void e() {
    }

    private void f() {
        if (this.r) {
            int i;
            this.m.setText(R.string.v2_activate_activate);
            this.k.setEnabled(true);
            if (dji.pilot.fpv.d.b.h(null)) {
                i = R.string.v2_active_remote_success_device_osmo;
            } else {
                i = R.string.v2_active_remote_success_device;
            }
            b((int) R.string.v2_activate_activate, i);
        }
    }

    private void g() {
        if (a.getInstance().e() && !d.a(null)) {
            try {
                this.q.a(a.getInstance().c(), a.getInstance().d());
            } catch (Exception e) {
                c.a("DJIMethod : onClick (345)postActiveTermsRecord Exception");
                e.printStackTrace();
            }
        }
    }

    private void h() {
        if (!isInEditMode()) {
            try {
                ProductType c = i.getInstance().c();
                if (c.isFromWifi()) {
                    if (DJINetWorkReceiver.a(getContext()) && !ServiceManager.getInstance().isRemoteOK()) {
                        if (dji.pilot.fpv.d.b.h(null)) {
                            this.m.setText(R.string.v2_active_78_osmo);
                        } else {
                            this.m.setText(R.string.v2_active_78);
                        }
                        this.q.a();
                    } else if (this.r && ServiceManager.getInstance().isRemoteOK()) {
                        this.h.sendEmptyMessage(6);
                    } else {
                        int i;
                        if (dji.pilot.fpv.d.b.h(c)) {
                            i = R.string.v2_active_dialog_34_osmo;
                        } else if (c == ProductType.P34K) {
                            i = R.string.v2_active_dialog_34_p34k;
                        } else {
                            i = R.string.v2_active_dialog_34;
                        }
                        a((int) R.string.v2_active_dialog_21, i);
                    }
                } else if (DJINetWorkReceiver.a(getContext())) {
                    this.m.setText(R.string.v2_activate_activating);
                    this.q.a();
                } else {
                    a((int) R.string.v2_active_dialog_21, (int) R.string.v2_active_dialog_22);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void a(int i, int i2) {
        dji.pilot2.publics.object.b bVar = new dji.pilot2.publics.object.b(getContext());
        bVar.setTitle(i);
        bVar.setMessage(i2);
        bVar.setNeutralButton(R.string.v2_active_dialog_23, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ ActivateAccountView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.l();
            }
        });
        bVar.setCancelable(false);
        bVar.show();
    }

    private synchronized void a(int i, String str) {
        if (this.t && !this.u) {
            this.u = true;
            this.y = new dji.pilot2.publics.object.b(getContext());
            this.y.setTitle(i);
            this.y.setMessage(str);
            this.y.setNeutralButton(R.string.v2_active_dialog_23, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ ActivateAccountView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.u = false;
                }
            });
            this.y.setCancelable(false);
            this.y.show();
        }
    }

    private void b(int i, int i2) {
        if (this.t) {
            a(i, getContext().getString(i2));
        }
    }

    private void i() {
        if (f.getInstance().c()) {
            m();
            return;
        }
        j();
        this.t = false;
    }

    private void j() {
        Intent intent = new Intent();
        intent.setClass(getContext(), DJIAccountSignActivity.class);
        getContext().startActivity(intent);
    }

    private void k() {
        if (this.t) {
            dji.pilot2.publics.object.b bVar = new dji.pilot2.publics.object.b(getContext());
            bVar.setTitle(R.string.v2_activate_activate);
            bVar.setMessage(R.string.v2_active_dialog_22);
            bVar.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ ActivateAccountView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    this.a.l();
                }
            });
            bVar.setCancelable(false);
            bVar.show();
        }
    }

    private void l() {
        if (this.t) {
            if (this.o == null) {
                a();
            }
            this.o.c();
            this.l.setVisibility(0);
            if (this.o.b() == 0) {
                this.m.setText(R.string.activate_no_wifi);
            } else {
                this.m.setText(R.string.activate_wifi_connect);
            }
        }
    }

    private void a(boolean z) {
        if (z) {
            this.n.setVisibility(0);
        } else {
            this.n.setVisibility(8);
        }
    }

    private void m() {
        dji.pilot2.publics.object.b bVar = new dji.pilot2.publics.object.b(getContext());
        bVar.setTitle(R.string.v2_active_dialog_41);
        bVar.setMessage(R.string.v2_active_dialog_42);
        bVar.setPositiveButton(R.string.v2_active_dialog_43, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ ActivateAccountView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        bVar.setNegativeButton(R.string.v2_active_dialog_44, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ ActivateAccountView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                f.getInstance().p();
                this.a.j();
            }
        });
        bVar.setCancelable(false);
        bVar.show();
    }

    private void n() {
        dji.pilot2.publics.object.b bVar = new dji.pilot2.publics.object.b(getContext());
        if (dji.pilot.fpv.d.b.h(i.getInstance().c()) || dji.pilot.fpv.d.b.h(i.getInstance().d())) {
            bVar.setTitle(R.string.v2_active_dialog_31_osmo);
            bVar.setMessage(R.string.v2_active_dialog_32_osmo);
        } else {
            bVar.setTitle(R.string.v2_active_dialog_31);
            bVar.setMessage(R.string.v2_active_dialog_32);
        }
        bVar.setNeutralButton(R.string.v2_active_dialog_33, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ ActivateAccountView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        bVar.setCancelable(false);
        bVar.show();
    }
}
