package dji.pilot2.whatsnew.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c$w;
import dji.pilot.fpv.d.c.h;
import dji.pilot.fpv.d.e;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.protocol.b;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot2.main.activity.DJILegalAgreement;
import dji.pilot2.main.activity.DJIMainFragmentActivity;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;

public class a extends PagerAdapter implements h, c$w {
    public static final int n = 1;
    private static final int[] p = new int[0];
    private static final int[] q = new int[]{R.string.v2_whatsnew_1_1, R.string.v2_whatsnew_2_1, R.string.v2_whatsnew_3_1, R.string.v2_whatsnew_4_1, R.string.v2_whatsnew_5_1};
    private static final int[] r = new int[]{R.string.v2_whatsnew_1_2, R.string.v2_whatsnew_2_2, R.string.v2_whatsnew_3_2, R.string.v2_whatsnew_4_2, R.string.v2_whatsnew_5_2};
    protected dji.pilot2.whatsnew.b.a o;
    private Context s;
    private View[] t;
    private DJIImageView u;
    private e$a v;
    private final f w = f.getInstance();

    public a(Context context) {
        this.s = context;
        this.t = new View[]{null, null, null, null, null};
    }

    public void a(e$a dji_pilot_usercenter_protocol_e_a) {
        this.v = dji_pilot_usercenter_protocol_e_a;
    }

    public void a() {
        this.u.startAnimation(AnimationUtils.loadAnimation(this.s, R.anim.c3));
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view;
        if (this.t[i] == null) {
            View inflate = LayoutInflater.from(this.s).inflate(R.layout.v2_whatsnew_page, viewGroup, false);
            this.t[i] = inflate;
            view = inflate;
        } else {
            view = this.t[i];
        }
        DJIRelativeLayout dJIRelativeLayout = (DJIRelativeLayout) view.findViewById(R.id.d5g);
        ImageView imageView = (ImageView) view.findViewById(R.id.d5h);
        TextView textView = (TextView) view.findViewById(R.id.d5i);
        TextView textView2 = (TextView) view.findViewById(R.id.d5j);
        View findViewById = view.findViewById(R.id.d5k);
        TextView textView3 = (TextView) view.findViewById(R.id.d5l);
        TextView textView4 = (TextView) view.findViewById(R.id.d5m);
        DJIImageView dJIImageView = (DJIImageView) view.findViewById(R.id.d5n);
        dJIRelativeLayout.setBackgroundColor(this.s.getResources().getColor(R.color.ol));
        imageView.setImageResource(p[i]);
        textView.setText(q[i]);
        textView2.setText(r[i]);
        if (textView.getText().equals("")) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
        if (textView2.getText().equals("")) {
            textView2.setVisibility(8);
        } else {
            textView2.setVisibility(0);
        }
        if (i == 0) {
            this.u = dJIImageView;
            findViewById.setVisibility(0);
            dJIRelativeLayout.setBackgroundDrawable(this.s.getResources().getDrawable(R.drawable.v2_grad_bg));
            textView3.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(this.a.s, DJIMainFragmentActivity.class);
                    this.a.s.startActivity(intent);
                    DJILogHelper.getInstance().LOGI("bob", "onClick go");
                    ((Activity) this.a.s).finish();
                }
            });
            textView4.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.s.startActivity(new Intent(this.a.s, DJILegalAgreement.class));
                    e.b(c$w.b);
                    if (this.a.s instanceof Activity) {
                        ((Activity) this.a.s).finish();
                    }
                }
            });
        }
        viewGroup.addView(this.t[i]);
        return view;
    }

    private void a(int i) {
        b.a(this.s, this.w.n(), i, 48, (Object) "whatsnew", this.v);
    }

    public int getCount() {
        return 1;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public int getItemPosition(Object obj) {
        return super.getItemPosition(obj);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (this.t[i] != null) {
            viewGroup.removeView(this.t[i]);
        }
    }
}
