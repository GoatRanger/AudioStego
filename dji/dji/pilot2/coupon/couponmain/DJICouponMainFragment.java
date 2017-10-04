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
import android.widget.Toast;
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
import dji.pilot2.coupon.model.CouponGiftCards.GiftItem;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.utils.k;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJITextView;

public class DJICouponMainFragment extends Fragment implements OnClickListener, dji.pilot2.coupon.couponmain.b.a {
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
    private CouponRefreshableView k;
    private DJITextView l;
    private DJITextView m;

    public class a extends ClickableSpan {
        final /* synthetic */ DJICouponMainFragment a;

        public a(DJICouponMainFragment dJICouponMainFragment) {
            this.a = dJICouponMainFragment;
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
        this.b = layoutInflater.inflate(R.layout.v2_fragment_coupon_main, viewGroup, true);
        e();
        g();
        if (this.k != null) {
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ DJICouponMainFragment a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.k.setStartRefreshing();
                }
            }, 300);
        }
        return this.b;
    }

    protected void d() {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.v2_coupon_main_header, null);
        this.l = (DJITextView) inflate.findViewById(R.id.cj_);
        this.l.setText(String.format(getResources().getString(R.string.v2_coupon_headertext1), new Object[]{"0"}));
        this.i.addHeaderView(inflate);
    }

    protected void e() {
        this.c = (DJIStateImageView) this.b.findViewById(R.id.cib);
        this.d = (DJITextView) this.b.findViewById(R.id.cic);
        this.e = this.b.findViewById(R.id.cid);
        this.f = (DJIStateTextView) this.b.findViewById(R.id.cie);
        this.g = (TextView) this.b.findViewById(R.id.cif);
        this.h = (RelativeLayout) this.b.findViewById(R.id.cih);
        this.i = (ListView) this.b.findViewById(R.id.cnx);
        this.k = (CouponRefreshableView) this.b.findViewById(R.id.cnw);
        this.j = new a(getActivity());
        this.m = (DJITextView) this.b.findViewById(R.id.cia);
        h();
        f();
        d();
    }

    protected void f() {
        this.d.setText(R.string.v2_mine_coupon);
        this.f.setText(R.string.v2_coupon_message);
        this.i.setDivider(null);
        this.i.setVerticalScrollBarEnabled(false);
    }

    protected void g() {
        this.c.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.i.setAdapter(this.j);
        this.i.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DJICouponMainFragment a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                String str;
                DJILogHelper.getInstance().LOGI("bob", " onItemClick id=" + j);
                Intent intent = new Intent(this.a.getActivity(), DJICouponDetailActivity.class);
                Object item = adapterView.getAdapter().getItem(i);
                String str2 = "";
                if (item instanceof dji.pilot2.coupon.couponmain.a.a) {
                    dji.pilot2.coupon.couponmain.a.a aVar = (dji.pilot2.coupon.couponmain.a.a) item;
                    GiftItem giftItem = aVar.a;
                    str = aVar.f;
                } else {
                    str = str2;
                }
                if (!str.equals("")) {
                    DJILogHelper.getInstance().LOGI("bob", "code = " + str + " position=" + i + " id =" + j);
                    intent.putExtra(DJICouponDetailActivity.a, str);
                    this.a.startActivity(intent);
                }
            }
        });
        this.k.setOnRefreshListener(new CouponRefreshableView.b(this) {
            final /* synthetic */ DJICouponMainFragment a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a.a(1);
                e.b(c$c.r);
            }
        }, 201605);
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
        if (this.k != null) {
            this.k.finishRefreshing();
        }
    }

    public void a(CouponGiftCards couponGiftCards) {
        DJILogHelper.getInstance().LOGI("bob", "DJICouponMainFragment setmDatas ");
        if (this.j != null) {
            this.j.a(couponGiftCards);
        }
    }

    public void a(String str) {
        if (isAdded() && this.l != null) {
            this.l.setText(String.format(getResources().getString(R.string.v2_coupon_headertext1), new Object[]{str}));
        }
    }

    public void b() {
        if (this.g != null) {
            this.g.setVisibility(0);
        }
    }

    public void c() {
    }

    public void b(boolean z) {
        if (this.j != null) {
            this.j.a(c.ELoaded);
        }
        if (!z) {
            Toast.makeText(getActivity(), R.string.v2_explore_network_error, 1).show();
        }
    }

    private void h() {
        TextView textView = (TextView) this.b.findViewById(R.id.cj7);
        CharSequence string = getActivity().getResources().getString(R.string.v2_coupon_text1);
        Object string2 = getActivity().getResources().getString(R.string.v2_coupon_text2);
        textView.setText(string);
        string = new SpannableString(string2);
        string.setSpan(new a(this), 0, string2.length(), 33);
        textView.append(string);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
