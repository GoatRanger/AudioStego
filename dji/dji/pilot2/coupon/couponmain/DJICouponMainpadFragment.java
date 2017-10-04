package dji.pilot2.coupon.couponmain;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c$c;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot2.coupon.CouponRefreshableView;
import dji.pilot2.coupon.coupondetail.DJICouponDetailActivity;
import dji.pilot2.coupon.couponmain.a.c;
import dji.pilot2.coupon.couponmain.b.b;
import dji.pilot2.coupon.message.DJICouponMessageActivity;
import dji.pilot2.coupon.model.CouponGiftCards;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.utils.k;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJITextView;

public class DJICouponMainpadFragment extends Fragment implements OnClickListener, dji.pilot2.coupon.couponmain.b.a {
    private b a;
    private View b;
    private DJIStateImageView c;
    private DJITextView d;
    private View e;
    private DJIStateTextView f;
    private TextView g;
    private RelativeLayout h;
    private ListView i;
    private a j;
    private DJIStateTextView k;
    private DJITextView l;
    private DJITextView m;
    private CouponRefreshableView n;

    public class a extends ClickableSpan {
        final /* synthetic */ DJICouponMainpadFragment a;

        public a(DJICouponMainpadFragment dJICouponMainpadFragment) {
            this.a = dJICouponMainpadFragment;
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setColor(Color.parseColor("#1088F2"));
        }

        public void onClick(View view) {
            String str = "";
            Intent intent = new Intent(this.a.getActivity(), DJISupportShareWebviewActivity.class);
            intent.putExtra(DJIWebviewFragment.o, k.f());
            if (intent != null) {
                this.a.getActivity().startActivity(intent);
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.b = layoutInflater.inflate(R.layout.v2_mainpad_fragment, viewGroup, true);
        d();
        f();
        if (this.n != null) {
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ DJICouponMainpadFragment a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.n.setStartRefreshing();
                }
            }, 200);
        }
        return this.b;
    }

    protected void d() {
        this.c = (DJIStateImageView) this.b.findViewById(R.id.cib);
        this.d = (DJITextView) this.b.findViewById(R.id.cic);
        this.e = this.b.findViewById(R.id.cid);
        this.f = (DJIStateTextView) this.b.findViewById(R.id.cie);
        this.g = (TextView) this.b.findViewById(R.id.cif);
        this.h = (RelativeLayout) this.b.findViewById(R.id.cih);
        this.i = (ListView) this.b.findViewById(R.id.cnx);
        this.n = (CouponRefreshableView) this.b.findViewById(R.id.cnw);
        this.l = (DJITextView) this.b.findViewById(R.id.cj_);
        this.l.setText(String.format(getResources().getString(R.string.v2_coupon_headertext1), new Object[]{"0"}));
        this.j = new a(getActivity());
        this.j.a(true);
        this.m = (DJITextView) this.b.findViewById(R.id.cia);
        this.k = (DJIStateTextView) this.b.findViewById(R.id.cj7);
        CharSequence string = getActivity().getResources().getString(R.string.v2_coupon_text1);
        Object string2 = getActivity().getResources().getString(R.string.v2_coupon_text2);
        this.k.setText(string);
        string = new SpannableString(string2);
        string.setSpan(new a(this), 0, string2.length(), 33);
        this.k.append(string);
        this.k.setMovementMethod(LinkMovementMethod.getInstance());
        e();
    }

    protected void e() {
        this.d.setText(R.string.v2_mine_coupon);
        this.f.setText(R.string.v2_coupon_message);
        this.i.setDivider(null);
        this.i.setVerticalScrollBarEnabled(false);
    }

    protected void f() {
        this.c.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.i.setAdapter(this.j);
        this.i.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DJICouponMainpadFragment a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                DJILogHelper.getInstance().LOGI("bob", " onItemClick id=" + j);
                Intent intent = new Intent(this.a.getActivity(), DJICouponDetailActivity.class);
                String a = this.a.j.a(i);
                DJILogHelper.getInstance().LOGI("bob", "code = " + a + " position=" + i + " id =" + j);
                intent.putExtra(DJICouponDetailActivity.a, a);
                this.a.startActivity(intent);
            }
        });
        this.n.setOnRefreshListener(new CouponRefreshableView.b(this) {
            final /* synthetic */ DJICouponMainpadFragment a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a.a(1);
                e.b(c$c.r);
            }
        }, 201604);
    }

    public void a(b bVar) {
        this.a = bVar;
    }

    public void a(boolean z) {
        if (z) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(8);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        DJILogHelper.getInstance().LOGI("bob", "onClick id = " + id);
        switch (id) {
            case R.id.cib:
                getActivity().finish();
                return;
            case R.id.cie:
                startActivity(new Intent(getActivity(), DJICouponMessageActivity.class));
                e.b(c$c.b);
                this.g.setVisibility(8);
                return;
            default:
                return;
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void a() {
        if (this.n != null) {
            this.n.finishRefreshing();
        }
    }

    public void a(CouponGiftCards couponGiftCards) {
        if (this.j != null) {
            this.j.a(couponGiftCards);
            this.i.setEmptyView(this.m);
        }
    }

    public void a(String str) {
        if (isAdded()) {
            this.l.setText(String.format(getResources().getString(R.string.v2_coupon_headertext1), new Object[]{str}));
        }
    }

    public void b() {
        this.g.setVisibility(0);
    }

    public void c() {
        this.i.setEmptyView(null);
    }

    public void b(boolean z) {
        if (this.j != null) {
            this.j.a(c.ELoaded);
        }
    }
}
