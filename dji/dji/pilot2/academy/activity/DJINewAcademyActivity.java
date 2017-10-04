package dji.pilot2.academy.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.d.c$b;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.e.a;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.g;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.academy.model.AcademyProductTypeModel.ProductTypeStruct;
import dji.pilot2.academy.widget.h;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.simulator.DJISimulatorActivity;
import dji.pilot2.simulator.d;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJINewAcademyActivity extends DJIActivityNoFullScreen {
    public static final String a = "key_product_index";
    public static boolean b = false;
    private static final String c = "key_support_product";
    private static final int d = 1024;
    private ImageView A;
    private TextView B;
    private ProductType C = ProductType.None;
    private h D = null;
    private OnItemSelectedListener E = null;
    private OnClickListener F = null;
    private TextView G;
    private View H;
    private boolean I = false;
    private Spinner t;
    private RelativeLayout u;
    private RelativeLayout v;
    private RelativeLayout w;
    private RelativeLayout x;
    private DJITextView y;
    private DJIStateImageView z;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_academy_new);
        a();
        b();
        f();
        g();
        h();
    }

    protected void a() {
        this.t = (Spinner) findViewById(R.id.d0_);
        this.u = (RelativeLayout) findViewById(R.id.c61);
        this.v = (RelativeLayout) findViewById(R.id.c64);
        this.w = (RelativeLayout) findViewById(R.id.c65);
        this.x = (RelativeLayout) findViewById(R.id.c67);
        this.y = (DJITextView) findViewById(R.id.c43);
        this.z = (DJIStateImageView) findViewById(R.id.c42);
        this.B = (TextView) findViewById(R.id.c66);
        this.G = (TextView) findViewById(R.id.c62);
        this.A = (ImageView) findViewById(R.id.c5z);
        this.H = findViewById(R.id.c63);
        ProductType c = i.getInstance().c();
        if (a.d(c) || c == ProductType.A2 || c == ProductType.A3 || c == ProductType.Grape2 || b.h(c) || c == ProductType.N3) {
            this.u.setVisibility(8);
            this.H.setVisibility(8);
        }
    }

    private void g() {
        this.t.setOnItemSelectedListener(this.E);
        this.t.setAdapter(this.D);
        this.u.setOnClickListener(this.F);
        this.v.setOnClickListener(this.F);
        this.w.setOnClickListener(this.F);
        this.x.setOnClickListener(this.F);
        this.z.setOnClickListener(this.F);
        this.y.setOnClickListener(this.F);
        this.y.setText(R.string.college_title);
    }

    protected void b() {
        this.D = new h(this, dji.pilot2.academy.model.a.getInstance(this).a());
        this.D.a(ProductType.Pomato);
        this.D.a(ProductType.KumquatS);
    }

    protected void f() {
        this.E = new OnItemSelectedListener(this) {
            final /* synthetic */ DJINewAcademyActivity a;

            {
                this.a = r1;
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                this.a.D.a(i);
                this.a.a(this.a.D.b(i));
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        this.F = new OnClickListener(this) {
            final /* synthetic */ DJINewAcademyActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Intent intent;
                switch (view.getId()) {
                    case R.id.c42:
                        this.a.finish();
                        return;
                    case R.id.c43:
                        this.a.startActivity(new Intent(this.a, DJIAccountSignActivity.class));
                        return;
                    case R.id.c61:
                        if (!this.a.I || b.h(this.a.C)) {
                            this.a.a((int) R.string.v2_smlt_academy_cannot_enter_title, (int) R.string.v2_smlt_academy_cannot_enter_content);
                            return;
                        } else if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
                            this.a.a(-1, (int) R.string.v2_smlt_not_enter_motorup_tip);
                            return;
                        } else if (dji.logic.c.b.getInstance().a(null)) {
                            this.a.a((int) R.string.v2_smlt_academy_cannot_enter_title, (int) R.string.smlt_academy_cannot_enter_wifi_control);
                            return;
                        } else {
                            ServiceManager.getInstance().pauseService(true);
                            d.b(true);
                            this.a.u.setEnabled(false);
                            this.a.startActivity(new Intent(this.a, DJISimulatorActivity.class));
                            this.a.u.setEnabled(true);
                            return;
                        }
                    case R.id.c64:
                        e.b(c$b.a);
                        intent = new Intent(this.a, DJINewAcademyVideoActivity.class);
                        intent.putExtra("key_product_index", this.a.C.value());
                        this.a.startActivity(intent);
                        return;
                    case R.id.c65:
                        e.b(c$b.b);
                        intent = new Intent(this.a, DJINewAcademyFlightActivity.class);
                        intent.putExtra("key_product_index", this.a.C.value());
                        this.a.startActivity(intent);
                        return;
                    case R.id.c67:
                        e.b(c$b.c);
                        intent = new Intent(this.a, DJINewAcademyInstructionActivity.class);
                        intent.putExtra("key_product_index", this.a.C.value());
                        this.a.startActivity(intent);
                        return;
                    default:
                        return;
                }
            }
        };
    }

    private void h() {
        int i;
        int intExtra = getIntent().getIntExtra("key_product_index", -1);
        if (intExtra >= this.D.getCount()) {
            i = 0;
        } else {
            i = intExtra;
        }
        if (i == -1 || ServiceManager.getInstance().isRemoteOK()) {
            int count = this.D.getCount();
            for (intExtra = 0; intExtra != count; intExtra++) {
                if (((ProductTypeStruct) this.D.getItem(intExtra)).value == i.getInstance().c().value()) {
                    this.t.setSelection(intExtra);
                    return;
                }
            }
        }
        if (i != -1) {
            this.t.setSelection(i);
        }
    }

    private void a(ProductType productType) {
        if (this.C != productType) {
            this.C = productType;
            g.a(getApplicationContext(), c, productType.value());
            if (this.C.equals(ProductType.A2) || this.C.equals(ProductType.Grape2)) {
                this.A.setImageDrawable(dji.pilot.publics.c.d.getInstance().b(this.C.value()));
            } else {
                this.A.setImageDrawable(dji.pilot.publics.c.d.getInstance().g(this.C));
            }
            Drawable drawable;
            if (b.h(this.C) || this.C.equals(ProductType.A2) || this.C.equals(ProductType.Grape2)) {
                this.B.setText(R.string.v2_academy_flight_osmo_text);
                drawable = getResources().getDrawable(R.drawable.newacademy_simulator_disable);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                    this.G.setCompoundDrawables(drawable, null, null, null);
                } else {
                    this.G.setCompoundDrawables(null, drawable, null, null);
                }
                this.u.setVisibility(8);
                this.H.setVisibility(8);
                return;
            }
            drawable = getResources().getDrawable(R.drawable.newacademy_simulator);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                this.G.setCompoundDrawables(drawable, null, null, null);
            } else {
                this.G.setCompoundDrawables(null, drawable, null, null);
            }
            this.B.setText(R.string.v2_academy_flight_text);
            if (a.d(productType) || productType == ProductType.A2 || productType == ProductType.Grape2 || b.h(productType)) {
                this.u.setVisibility(8);
                this.H.setVisibility(8);
                return;
            }
            this.u.setVisibility(0);
            this.H.setVisibility(0);
        }
    }

    public void onEventMainThread(ProductType productType) {
        if (a.d(productType) || productType == ProductType.A2 || productType == ProductType.A3 || productType == ProductType.Grape2 || b.h(productType) || productType == ProductType.N3) {
            this.u.setVisibility(8);
            this.H.setVisibility(8);
            return;
        }
        this.u.setVisibility(0);
        this.H.setVisibility(0);
    }

    private void i() {
        if (b) {
            b = false;
            a((int) R.string.v2_smlt_academy_exit_tip, (int) R.string.v2_smlt_academy_reboot_tip);
        }
    }

    private void a(int i, int i2) {
        Builder bVar = new dji.pilot2.publics.object.b(this);
        if (i != -1) {
            View textView = new TextView(this);
            textView.setText(i);
            textView.setGravity(17);
            textView.setTextColor(getResources().getColor(R.color.a_));
            textView.setPadding(0, 15, 0, 15);
            textView.setTextSize(20.0f);
            bVar.setCustomTitle(textView);
        }
        bVar.setMessage(i2);
        bVar.setPositiveButton(17039379, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ DJINewAcademyActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        bVar.show();
    }

    protected void onResume() {
        c.a().a(this);
        super.onResume();
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        if (ServiceManager.getInstance().isRemoteOK()) {
            onEventMainThread(o.b);
        } else {
            onEventMainThread(o.a);
        }
        i();
    }

    protected void onPause() {
        c.a().d(this);
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.a) {
            this.I = false;
        } else if (oVar == o.b && !b.h(null)) {
            this.I = true;
        }
    }
}
