package dji.pilot2.usercenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.SeekBar;
import android.widget.Toast;
import com.dji.frame.c.l;
import com.here.odnp.posclient.pos.IPositioningSession;
import dji.gs.views.EventView;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.c.h;
import dji.pilot.fpv.model.b;
import dji.pilot.fpv.model.f;
import dji.pilot.fpv.model.k;
import dji.pilot.publics.c.d;
import dji.pilot.publics.control.a;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.usercenter.b.e;
import dji.pilot.usercenter.widget.DJIRoundImageView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.afinal.a.b.c;
import java.text.DecimalFormat;
import java.util.Date;

public class DJIFlightRecordPlayerActivity extends DJIBaseActivity implements OnClickListener, h {
    private DJILinearLayout A;
    private DJIImageView B;
    private DJIImageView C;
    private DJITextView D;
    private DJITextView E;
    private DJITextView F;
    private DJITextView G;
    private DJITextView H = null;
    private DJIRoundImageView I = null;
    private DJIGenSettingDataManager J = DJIGenSettingDataManager.getInstance();
    private boolean K;
    private DJILinearLayout L;
    private DJILinearLayout M;
    private DJIRelativeLayout N;
    private boolean O = false;
    private int P;
    private DJIRelativeLayout Q;
    private DJIRelativeLayout R;
    private boolean S = false;
    private DJILinearLayout T = null;
    private DJILinearLayout U = null;
    private DJIStateImageView V = null;
    private DecimalFormat W = new DecimalFormat("###,###,###,###");
    @c(a = 2131362846)
    private DJIRelativeLayout a;
    @c(a = 2131362855)
    private DJIRelativeLayout n;
    @c(a = 2131365566)
    private DJIImageView o;
    @c(a = 2131365567)
    private DJIImageView p;
    @c(a = 2131365568)
    private DJITextView q;
    @c(a = 2131362772)
    private SeekBar r;
    @c(a = 2131365570)
    private DJIImageView s;
    @c(a = 2131365569)
    private DJIImageView t;
    @c(a = 2131365572)
    private DJIRelativeLayout u;
    @c(a = 2131365552)
    private DJIRelativeLayout v;
    @c(a = 2131365561)
    private DJIRelativeLayout w;
    private e x = new e(this);
    private boolean y = false;
    private int z = 1;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_usercenter_flightrecord_player);
        this.P = getIntent().getIntExtra("POSITION", 0);
        this.T = (DJILinearLayout) findViewById(R.id.d3q);
        this.T.setOnTouchListener(new 1(this));
        this.L = (DJILinearLayout) findViewById(R.id.c0z);
        this.M = (DJILinearLayout) findViewById(R.id.c1b);
        this.N = (DJIRelativeLayout) findViewById(R.id.akg);
        this.Q = (DJIRelativeLayout) findViewById(R.id.c1i);
        this.R = (DJIRelativeLayout) findViewById(R.id.c0y);
        this.A = (DJILinearLayout) findViewById(R.id.d3r);
        this.B = (DJIImageView) findViewById(R.id.c0w);
        this.C = (DJIImageView) findViewById(R.id.c0x);
        this.D = (DJITextView) findViewById(R.id.d3y);
        this.E = (DJITextView) findViewById(R.id.c2y);
        this.F = (DJITextView) findViewById(R.id.c31);
        this.G = (DJITextView) findViewById(R.id.c32);
        this.H = (DJITextView) findViewById(R.id.c2v);
        this.I = (DJIRoundImageView) findViewById(R.id.c2u);
        this.B.setOnClickListener(this);
        this.C.setOnClickListener(this);
        this.A.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.t.setOnClickListener(this);
        boolean z = (this.J.v() == 1 || this.J.v() == 2) ? false : true;
        this.K = z;
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            this.U = (DJILinearLayout) findViewById(R.id.c12);
            this.V = (DJIStateImageView) findViewById(R.id.d40);
            this.V.setOnClickListener(this);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.V.getLayoutParams();
            marginLayoutParams.topMargin = com.dji.frame.c.e.b(this, 10.0f) + b.a((Context) this, (int) R.dimen.yt);
            this.V.setLayoutParams(marginLayoutParams);
        }
        a();
        dji.thirdparty.a.c.a().a((Object) this);
    }

    private void a() {
        this.o.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.x.a(new 2(this));
        this.r.setOnSeekBarChangeListener(new 3(this));
    }

    private int a(ProductType productType) {
        return d.getInstance().i(productType);
    }

    private void a(f fVar, long j, float f, boolean z) {
        if (IPositioningSession.NotSet != j) {
            int[] e = dji.pilot.fpv.d.b.e((int) (j / 1000));
            this.D.setText(getString(R.string.flight_record_time_format, new Object[]{Integer.valueOf(e[1]), Integer.valueOf(e[0])}));
        }
        if (this.K) {
            this.E.setText(getString(R.string.flight_record_distance_ft_format, new Object[]{this.W.format((double) this.J.b(f))}));
        } else {
            this.E.setText(getString(R.string.flight_record_distance_m_format, new Object[]{this.W.format((double) f)}));
        }
        if (z) {
            this.r.setProgress(0);
            this.F.setText(l.a(new Date(fVar.C), dji.pilot.usercenter.f.e.a));
            CharSequence charSequence = fVar.v;
            if (!dji.pilot.fpv.d.b.a(fVar.E) || !dji.pilot.fpv.d.b.b(fVar.D)) {
                charSequence = getString(R.string.flight_record_nogps);
            } else if (k.b.equals(fVar.v)) {
                charSequence = getString(R.string.flight_record_maploading);
            }
            this.G.setText(charSequence);
            ProductType find = ProductType.find(fVar.Q);
            if (dji.pilot.publics.e.d.a(fVar.R)) {
                this.H.setText(d.getInstance().j(find));
            } else {
                this.H.setText(fVar.R);
            }
            this.I.setImageResource(a(find));
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ak7:
                c();
                return;
            case R.id.c0w:
                this.x.m();
                return;
            case R.id.c0x:
                this.x.n();
                return;
            case R.id.c1d:
                dji.pilot.fpv.d.e.a("UserCenter_FlightRecord_FlightRecordDetailView_Button_SelectPlayerPlay");
                a(false);
                return;
            case R.id.c1e:
                dji.pilot.fpv.d.e.a("UserCenter_FlightRecord_FlightRecordDetailView_Button_Share");
                dji.pilot.fpv.d.e.c(h.ba_);
                if (dji.pilot.fpv.d.b.c((Context) this) && a.b()) {
                    Intent intent = new Intent(this, DJIFlightRecordShareActivity.class);
                    intent.putExtra("POSITION", this.P);
                    startActivity(intent);
                    return;
                }
                Toast.makeText(this, R.string.home_account_nonet, 0).show();
                return;
            case R.id.c1f:
                dji.pilot.fpv.d.e.a("UserCenter_FlightRecord_FlightRecordDetailView_Button_ChangePlaySpeed");
                d();
                return;
            case R.id.c1g:
                dji.pilot.fpv.d.e.a("GroundStation_RightControlView_Button_ShowMapLocation_Aircraft");
                dji.thirdparty.a.c.a().e(dji.pilot.fpv.control.k.b.a);
                return;
            case R.id.c1h:
                if (this.x.f == null) {
                    return;
                }
                if (this.S) {
                    this.S = false;
                    this.u.animGo();
                    return;
                }
                this.S = true;
                this.u.animShow();
                return;
            case R.id.d3r:
                if (this.x != null) {
                    this.x.h();
                }
                finish();
                return;
            case R.id.d40:
                if (this.U.isShown()) {
                    this.V.setImageResource(R.drawable.arrow_down);
                    this.U.go();
                    return;
                }
                this.V.setImageResource(R.drawable.arrow_up);
                this.U.show();
                return;
            default:
                return;
        }
    }

    private void b() {
        this.x.a(new 4(this));
    }

    private void c() {
        if (this.T.isShown()) {
            this.T.animGo();
            this.L.animGo();
            this.M.animGo();
            this.N.animGo();
            this.v.animGo();
            this.w.animGo();
            if (this.S && this.u.isShown()) {
                this.u.animGo();
            }
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                this.V.go();
                return;
            }
            return;
        }
        this.T.animShow();
        this.L.animShow();
        this.M.animShow();
        this.N.animShow();
        this.v.animShow();
        this.w.animShow();
        if (this.S) {
            this.u.animShow();
        }
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            this.V.show();
        }
    }

    private void a(boolean z) {
        boolean z2 = false;
        this.o.setEnabled(false);
        if (this.y) {
            this.o.setImageResource(R.drawable.my_flight_playerplay);
            if (!z) {
                this.x.k();
            }
        } else {
            this.o.setImageResource(R.drawable.my_flight_playerpause);
            if (!z) {
                this.x.l();
            }
        }
        if (!this.y) {
            z2 = true;
        }
        this.y = z2;
        this.o.setEnabled(true);
    }

    private void d() {
        switch (this.z) {
            case 1:
                this.z = 2;
                break;
            case 2:
                this.z = 4;
                break;
            case 4:
                this.z = 8;
                break;
            case 8:
                this.z = 1;
                break;
        }
        this.x.b(this.z);
        this.q.setText(String.format("X %d", new Object[]{Integer.valueOf(this.z)}));
    }

    public void finishThis() {
        finish();
        overridePendingTransition(0, 0);
    }

    protected void onDestroy() {
        this.x.e();
        dji.thirdparty.a.c.a().d((Object) this);
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
        if (!this.O) {
            this.O = true;
            this.x.a(null, this.a);
            this.x.a(this.P);
            b();
        }
        this.x.b();
    }

    protected void onPause() {
        this.x.c();
        super.onPause();
    }

    protected void onStart() {
        super.onStart();
        dji.pilot.fpv.d.e.b((Context) this);
    }

    protected void onStop() {
        super.onStop();
        dji.pilot.fpv.d.e.c((Context) this);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.x.a(bundle);
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.x.d();
    }

    public void onBackPressed() {
        finishThis();
    }

    public void onEventMainThread(EventView.a aVar) {
        switch (5.a[aVar.ordinal()]) {
            case 1:
                c();
                return;
            default:
                return;
        }
    }
}
