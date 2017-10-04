package dji.pilot2.academy;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;
import com.meetme.android.horizontallistview.HorizontalListView;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.usercenter.protocol.e.a;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.academy.model.AcademyProductTypeModel.ProductTypeStruct;
import dji.pilot2.academy.widget.d;
import dji.pilot2.academy.widget.g;
import dji.pilot2.academy.widget.h;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.afinal.a.b.c;

@Deprecated
public class DJIAcademyActivity extends DJIActivityNoFullScreen {
    public static final String a = "key_product_index";
    public static boolean b = false;
    private static final String c = "key_support_product";
    private static final int d = 1024;
    @c(a = 2131365709)
    private DJIRelativeLayout A;
    @c(a = 2131365708)
    private DJITextView B;
    private h C = null;
    private d D = null;
    private g E = null;
    private OnItemSelectedListener F = null;
    private OnClickListener G = null;
    private OnItemClickListener H = null;
    private OnItemClickListener I = null;
    private a J = null;
    private boolean K = false;
    private ProductType L = ProductType.None;
    @c(a = 2131366893)
    private Spinner t;
    @c(a = 2131365665)
    private DJIStateImageView u;
    @c(a = 2131365711)
    private HorizontalListView v;
    @c(a = 2131365712)
    private HorizontalListView w;
    @c(a = 2131365666)
    private DJITextView x;
    @c(a = 2131365667)
    private DJIStateTextView y;
    @c(a = 2131365710)
    private DJIStateTextView z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_academy);
        f();
        h();
        g();
        b();
        a();
    }

    private void a() {
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            LayoutParams layoutParams = this.A.getLayoutParams();
            layoutParams.width = getResources().getDimensionPixelSize(R.dimen.gi);
            this.A.setLayoutParams(layoutParams);
        }
    }

    private void b() {
        int i;
        int intExtra = getIntent().getIntExtra("key_product_index", 0);
        if (intExtra >= this.C.getCount()) {
            i = 0;
        } else {
            i = intExtra;
        }
        if (i == -1) {
            for (intExtra = 0; intExtra < dji.pilot2.academy.model.a.getInstance(this).a().size(); intExtra++) {
                if (i.getInstance().c().value() == ((ProductTypeStruct) dji.pilot2.academy.model.a.getInstance(this).a().get(intExtra)).value) {
                    this.t.setSelection(intExtra);
                    return;
                }
            }
        }
        this.t.setSelection(i);
    }

    private void f() {
        this.F = new 1(this);
        this.G = new 2(this);
        this.H = new 3(this);
        this.I = new 4(this);
        this.J = new 5(this);
    }

    private void g() {
        this.t.setAdapter(this.C);
        this.t.setOnItemSelectedListener(this.F);
        this.v.setAdapter(this.D);
        this.v.setOnItemClickListener(this.H);
        this.w.setAdapter(this.E);
        this.w.setOnItemClickListener(this.I);
        this.u.setOnClickListener(this.G);
        this.x.setOnClickListener(this.G);
        this.y.setOnClickListener(this.G);
        this.z.setOnClickListener(this.G);
    }

    private void h() {
        this.C = new h(this, dji.pilot2.academy.model.a.getInstance(this).a());
        this.D = new d(this);
        this.E = new g(this);
        this.D.a(this.J);
    }

    private void a(ProductType productType) {
        if (this.L != productType) {
            this.L = productType;
            dji.pilot.publics.objects.g.a(getApplicationContext(), c, productType.value());
            this.D.a(this.L);
            this.E.a(this.L);
            if (b.h(this.L)) {
                j();
            } else {
                i();
            }
        }
    }

    private void i() {
        this.A.show();
        this.B.show();
    }

    private void j() {
        this.A.go();
        this.B.go();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.D.b(this.J);
    }

    protected void onResume() {
        this.D.notifyDataSetChanged();
        dji.thirdparty.a.c.a().a((Object) this);
        super.onResume();
        if (ServiceManager.getInstance().isRemoteOK()) {
            onEventMainThread(o.ConnectOK);
        } else {
            onEventMainThread(o.ConnectLose);
        }
        k();
    }

    private void k() {
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
        bVar.setPositiveButton(17039379, new 6(this));
        bVar.show();
    }

    protected void onPause() {
        dji.thirdparty.a.c.a().d((Object) this);
        super.onPause();
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.ConnectLose) {
            this.K = false;
            this.z.setText(R.string.v2_smlt_academy_connect_tip);
            this.z.setBackgroundColor(getResources().getColor(R.color.an));
        } else if (oVar == o.ConnectOK && !i.getInstance().c().equals(ProductType.Longan)) {
            this.K = true;
            this.z.setText(R.string.v2_smlt_academy_enter_tip);
            this.z.setBackgroundColor(getResources().getColor(R.color.j5));
        }
    }
}
