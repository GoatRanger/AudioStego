package dji.setting.ui.flyc.imu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.sdk.j.i;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.flyc.imu.b.c;
import java.util.Locale;

public class ImuCalView extends LinearLayout implements OnClickListener, dji.midware.data.params.P3.a, b, dji.setting.ui.flyc.imu.b.a, dji.setting.ui.flyc.imu.b.b {
    private static final String aa = ImuCalView.class.getSimpleName();
    private ImageView ab = null;
    private LinearLayout ac = null;
    private ImageView ad = null;
    private LinearLayout ae = null;
    private final LinearLayout[] af = new LinearLayout[3];
    private final TextView[] ag = new TextView[3];
    private TextView ah = null;
    private ProgressBar ai = null;
    private TextView aj = null;
    private TextView ak = null;
    private LinearLayout al = null;
    private ImageView am = null;
    private TextView an = null;
    private TextView ao = null;
    private final a ap = new a();
    private b aq = null;
    private int[] ar = I;

    private static final class a {
        byte a;
        byte b;
        byte c;
        byte d;
        int e;
        byte[] f;

        private a() {
            this.a = (byte) 0;
            this.b = (byte) 0;
            this.c = (byte) 0;
            this.d = (byte) 0;
            this.e = 0;
            this.f = new byte[3];
        }
    }

    public interface b {
        void a(int i);
    }

    public ImuCalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnImuCalListener(b bVar) {
        this.aq = bVar;
    }

    protected void onFinishInflate() {
        int i = 0;
        super.onFinishInflate();
        this.ab = (ImageView) findViewById(R.id.imu_cal_close_img);
        this.ac = (LinearLayout) findViewById(R.id.imu_cal_process_ly);
        this.ad = (ImageView) findViewById(R.id.imu_cal_left_content_img);
        this.ae = (LinearLayout) findViewById(R.id.imu_cal_left_botom_ly);
        this.ah = (TextView) findViewById(R.id.imu_cal_start_tv);
        int[] iArr = new int[]{R.id.imu_cal_right_desc_ly1, R.id.imu_cal_right_desc_ly2, R.id.imu_cal_right_desc_ly3};
        if (dji.pilot.publics.e.a.d(null)) {
            this.ar = J;
        }
        while (i < 3) {
            this.af[i] = (LinearLayout) findViewById(iArr[i]);
            this.ag[i] = (TextView) this.af[i].findViewById(R.id.setting_ui_imucal_desc_tv);
            if (i < this.ar.length) {
                this.ag[i].setText(this.ar[i]);
            }
            i++;
        }
        this.ai = (ProgressBar) findViewById(R.id.imu_cal_pgb);
        this.aj = (TextView) findViewById(R.id.imu_cal_pgb_tv);
        this.ak = (TextView) findViewById(R.id.imu_cal_page_tv);
        this.al = (LinearLayout) findViewById(R.id.imu_cal_status_ly);
        this.am = (ImageView) findViewById(R.id.imu_cal_status_img);
        this.an = (TextView) findViewById(R.id.imu_cal_status_tv);
        this.ao = (TextView) findViewById(R.id.imu_cal_status_opt_tv);
        this.ab.setOnClickListener(this);
        this.ah.setOnClickListener(this);
        this.ao.setOnClickListener(this);
    }

    private void a(final boolean z, final boolean z2) {
        new DataFlycGetParams().setInfos(E).start(new d(this) {
            final /* synthetic */ ImuCalView c;

            public void onSuccess(Object obj) {
                this.c.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.c.b(z, z2);
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (z) {
                    this.c.postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.c.a(z, z2);
                        }
                    }, 1000);
                }
            }
        });
    }

    private void b(boolean z, boolean z2) {
        byte byteValue = dji.midware.data.manager.P3.d.read(dji.midware.data.params.P3.a.q).value.byteValue();
        if (z2 && (dji.setting.ui.flyc.imu.b.a.eZ_ == byteValue || dji.setting.ui.flyc.imu.b.a.fa_ == byteValue || (byte) -1 == byteValue || (byte) -3 == byteValue)) {
            byteValue = (byte) 0;
        }
        boolean z3 = byteValue != this.ap.d;
        DJILogHelper.getInstance().LOGD(aa, "status-" + byteValue + i.b + this.ap.d, false, true);
        if (z3) {
            this.ap.d = byteValue;
            e();
        }
        int byteValue2;
        if ((byte) 1 == byteValue || (byte) 3 == byteValue) {
            byte byteValue3 = dji.midware.data.manager.P3.d.read(dji.midware.data.params.P3.a.n).value.byteValue();
            byte byteValue4 = dji.midware.data.manager.P3.d.read(dji.midware.data.params.P3.a.o).value.byteValue();
            byte byteValue5 = dji.midware.data.manager.P3.d.read(dji.midware.data.params.P3.a.p).value.byteValue();
            byteValue2 = dji.midware.data.manager.P3.d.read(dji.midware.data.params.P3.a.r).value.byteValue();
            if (byteValue2 < 0) {
                byteValue2 = 0;
            } else if (byteValue2 > 100) {
                byteValue2 = 100;
            }
            DJILogHelper.getInstance().LOGD(aa, "data-" + byteValue4 + i.b + byteValue3 + i.b + byteValue5 + i.b + byteValue2, false, true);
            if (!(byteValue5 == this.ap.c && byteValue4 == this.ap.b)) {
                this.ap.c = byteValue5;
                this.ap.b = byteValue4;
                f();
                g();
            }
            if (byteValue2 != this.ap.e) {
                this.ap.e = byteValue2;
                h();
            }
            if (byteValue3 != this.ap.a) {
                this.ap.a = byteValue3;
                g();
            }
            postDelayed(new Runnable(this) {
                final /* synthetic */ ImuCalView a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a(true, false);
                }
            }, 1000);
        } else if ((byte) -1 == byteValue || (byte) -3 == byteValue) {
            byte[] bArr = new byte[F.length];
            c[] cVarArr = new c[F.length];
            StringBuilder stringBuilder = new StringBuilder(128);
            stringBuilder.append(getContext().getString(R.string.setting_ui_imu_fail));
            for (byteValue2 = 0; byteValue2 < F.length; byteValue2++) {
                bArr[byteValue2] = dji.midware.data.manager.P3.d.read(F[byteValue2]).value.byteValue();
                cVarArr[byteValue2] = c.find(bArr[byteValue2]);
                if (!a(cVarArr[byteValue2])) {
                    stringBuilder.append("\n");
                    stringBuilder.append(getContext().getString(R.string.setting_ui_imu_fail_reason, new Object[]{Integer.valueOf(byteValue2 + 1), Byte.valueOf(bArr[byteValue2])}));
                }
            }
            this.an.setText(stringBuilder.toString());
        }
    }

    private boolean a(c cVar) {
        return c.Idle == cVar || c.Calibrating == cVar || c.MultiCaling == cVar || c.Success == cVar || c.MultiSuccess == cVar;
    }

    private void a() {
        this.ab.setVisibility(0);
        this.ah.setVisibility(0);
        this.ac.setVisibility(0);
        this.ae.setVisibility(4);
        this.ad.setImageResource(getReadyResId());
        a(this.ar);
        this.ai.setVisibility(8);
        this.aj.setVisibility(8);
        this.ak.setVisibility(4);
        this.al.setVisibility(8);
    }

    private void b() {
        this.ab.setVisibility(8);
        this.ah.setVisibility(8);
        this.ac.setVisibility(0);
        this.ae.setVisibility(0);
        this.ad.setImageResource(a(4));
        a(K);
        this.ai.setVisibility(0);
        this.ai.setProgress(0);
        this.aj.setVisibility(0);
        this.ak.setVisibility(0);
        this.al.setVisibility(8);
    }

    private void c() {
        this.ab.setVisibility(0);
        this.ac.setVisibility(8);
        this.al.setVisibility(0);
        this.an.setText(R.string.setting_ui_imu_success);
        this.am.setBackgroundResource(R.drawable.setting_ui_success);
        this.ao.setText(R.string.setting_ui_imu_back);
    }

    private void d() {
        this.ab.setVisibility(0);
        this.ac.setVisibility(8);
        this.al.setVisibility(0);
        this.an.setText(R.string.setting_ui_imu_fail);
        this.am.setBackgroundResource(R.drawable.setting_ui_fail);
        this.ao.setText(R.string.setting_ui_imu_retry);
    }

    private void e() {
        if (this.ap.d == (byte) 0) {
            a();
        } else if ((byte) 1 == this.ap.d || (byte) 3 == this.ap.d) {
            b();
        } else if (dji.setting.ui.flyc.imu.b.a.eZ_ == this.ap.d || dji.setting.ui.flyc.imu.b.a.fa_ == this.ap.d) {
            c();
        } else if ((byte) -1 == this.ap.d || (byte) -3 == this.ap.d) {
            d();
        }
    }

    private void a(int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            this.af[i].setVisibility(0);
            this.ag[i].setText(iArr[i]);
        }
        while (length < 3) {
            this.af[length].setVisibility(8);
            length++;
        }
    }

    private void f() {
        int a = a(this.ap.c);
        int a2 = a((byte) (this.ap.b & this.ap.c));
        if (a2 >= a) {
            a2 = a - 1;
        }
        int childCount = this.ae.getChildCount();
        if (childCount != a) {
            int i;
            if (childCount <= a) {
                int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.dp_9);
                int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.dp_5);
                i = childCount;
                while (true) {
                    childCount = i + 1;
                    if (i >= a) {
                        break;
                    }
                    LayoutParams layoutParams = new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize);
                    layoutParams.setMargins(dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2);
                    this.ae.addView(LayoutInflater.from(getContext()).inflate(R.layout.setting_circle_view, null), layoutParams);
                    i = childCount;
                }
            } else {
                i = childCount;
                while (true) {
                    childCount = i - 1;
                    if (i <= a) {
                        break;
                    }
                    this.ae.removeViewAt(childCount);
                    i = childCount;
                }
            }
        }
        DJILogHelper.getInstance().LOGD(aa, "pages[" + a2 + i.b + a + i.b + childCount, false, true);
        for (childCount = 0; childCount < a2; childCount++) {
            View childAt = this.ae.getChildAt(childCount);
            childAt.setHovered(false);
            childAt.setSelected(true);
        }
        for (childCount = a2 + 1; childCount < a; childCount++) {
            childAt = this.ae.getChildAt(childCount);
            childAt.setHovered(false);
            childAt.setSelected(false);
        }
        this.ae.getChildAt(a2).setHovered(true);
        this.ak.setText(String.format(Locale.US, "%d/%d", new Object[]{Integer.valueOf(a2 + 1), Integer.valueOf(a)}));
    }

    private void g() {
        byte b = (byte) 0;
        if (this.ap.b != (byte) 0) {
            b = (byte) ((this.ap.b ^ -1) & this.ap.c);
        }
        int a = a(b(b));
        if (a != 0) {
            this.ad.setImageResource(a);
        }
    }

    private void h() {
        this.ai.setProgress(this.ap.e);
    }

    private int getReadyResId() {
        ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
        if (dji.pilot.publics.e.a.c(c)) {
            return R.drawable.setting_ui_kumquat_imucali_ready;
        }
        if (c == ProductType.N3) {
            return R.drawable.setting_ui_imu_ready_n3;
        }
        if (c == ProductType.PM820) {
            return R.drawable.setting_ui_imu_ready_m600;
        }
        if (c == ProductType.PM820PRO) {
            return R.drawable.setting_ui_imu_ready_m600_pro;
        }
        return R.drawable.setting_ui_imu_ready;
    }

    private int a(int i) {
        int[] iArr;
        ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
        if (dji.pilot.publics.e.a.c(c)) {
            iArr = M;
        } else if (c == ProductType.N3) {
            iArr = N;
        } else if (c == ProductType.PM820) {
            iArr = O;
        } else if (c == ProductType.PM820PRO) {
            iArr = P;
        } else {
            iArr = L;
        }
        if (i < 0 || i >= iArr.length) {
            return 0;
        }
        return iArr[i];
    }

    private int a(byte b) {
        int i = 0;
        if (b != (byte) 0) {
            int i2 = 0;
            while (i2 < 6) {
                int i3 = i2 + 1;
                if (((1 << i2) & b) != 0) {
                    i++;
                    i2 = i3;
                } else {
                    i2 = i3;
                }
            }
        }
        return i;
    }

    private int b(byte b) {
        if (b != (byte) 0) {
            int i = 0;
            int[] iArr = W;
            while (i < 6) {
                int i2 = i + 1;
                i = iArr[i];
                if (((1 << i) & b) != 0) {
                    return i;
                }
                i = i2;
            }
        }
        return 4;
    }

    private void b(int i) {
        if (this.aq != null) {
            this.aq.a(i);
        }
    }

    private void i() {
        new DataFlycGetParams().setInfos(new String[]{dji.midware.data.params.P3.a.s}).start(null);
    }

    private void a(final View view) {
        if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
            Toast.makeText(getContext().getApplicationContext(), R.string.setting_ui_imu_tip, 0).show();
            return;
        }
        view.setEnabled(false);
        int i = 1 == dji.midware.data.manager.P3.d.read(dji.midware.data.params.P3.a.s).value.intValue() ? 1 : 3;
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a(a);
        dataFlycSetParams.a(new Integer[]{Integer.valueOf(i), Integer.valueOf(i), Integer.valueOf(i)});
        dataFlycSetParams.start(new d(this) {
            final /* synthetic */ ImuCalView b;

            public void onSuccess(Object obj) {
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        view.setEnabled(true);
                        this.a.b.a(true, false);
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        view.setEnabled(true);
                    }
                });
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (R.id.imu_cal_close_img == id) {
            b(0);
        } else if (R.id.imu_cal_status_opt_tv == id) {
            if ((byte) -1 == this.ap.d || (byte) -3 == this.ap.d) {
                a(this.ao);
            } else {
                b(0);
            }
        } else if (R.id.imu_cal_start_tv == id) {
            a(this.ah);
        }
    }

    public void onEventMainThread(o oVar) {
        if (o.a == oVar) {
            b(0);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!dji.thirdparty.a.c.a().c(this)) {
                dji.thirdparty.a.c.a().a(this);
            }
            i();
            a();
            a(false, true);
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
