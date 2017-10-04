package dji.pilot2.simulator;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;

public class c extends PagerAdapter {
    private static final int[] c = new int[]{R.string.simulator_tipstitle0, R.string.simulator_tipstitle1, R.string.simulator_tipstitle2, R.string.simulator_tipstitle3, R.string.simulator_tipstitle4, R.string.simulator_tipstitle6, R.string.simulator_tipstitle6};
    private static final int[] d = new int[]{R.string.simulator_tips0, R.string.simulator_tips1, R.string.simulator_tips2, R.string.simulator_tips3, R.string.simulator_tips4, R.string.simulator_tips6, R.string.simulator_tips6};
    private static final int[] e = new int[]{R.drawable.v2_smlt_tip0, R.drawable.v2_smlt_tip1, R.drawable.v2_smlt_tip2, R.drawable.v2_smlt_tip3, R.drawable.v2_smlt_tip4, R.drawable.v2_smlt_tip5, R.drawable.v2_smlt_phantom_rc_tip};
    private static final int[] f = new int[]{R.string.simulator_tipstitle0, R.string.simulator_tipstitle1, R.string.simulator_tipstitle2, R.string.simulator_tipstitle3, R.string.simulator_tipstitle4, R.string.simulator_tipstitle_p3c5, R.string.simulator_tipstitle_p3c6, R.string.simulator_tipstitle6};
    private static final int[] g = new int[]{R.string.simulator_tips0, R.string.simulator_tips1, R.string.simulator_tips2, R.string.simulator_tips3, R.string.simulator_tips4, R.string.simulator_tips_p3c5, R.string.simulator_tips_p3c6, R.string.simulator_tips6};
    private static final int[] h = new int[]{R.drawable.v2_smlt_tip0, R.drawable.v2_smlt_tip1, R.drawable.v2_smlt_tip2, R.drawable.v2_smlt_tip3, R.drawable.v2_smlt_tip4, R.drawable.v2_smlt_tip5_p3c, R.drawable.v2_smlt_tip6_p3c, R.drawable.v2_smlt_phantom_rc_tip};
    private static final int[] i = new int[]{R.string.simulator_tipstitle0, R.string.simulator_tipstitle1, R.string.simulator_tipstitle2, R.string.simulator_tipstitle3, R.string.simulator_tipstitle4, R.string.simulator_tipstitle5, R.string.simulator_tipstitle6, R.string.simulator_tipstitle6};
    private static final int[] j = new int[]{R.string.simulator_tips0, R.string.simulator_tips1, R.string.simulator_tips2, R.string.simulator_tips3, R.string.simulator_tips4, R.string.simulator_tips5, R.string.simulator_tips6, R.string.simulator_tips6};
    private static final int[] k = new int[]{R.drawable.v2_smlt_tip0, R.drawable.v2_smlt_tip1_in1, R.drawable.v2_smlt_tip2_in1, R.drawable.v2_smlt_tip3_in1, R.drawable.v2_smlt_tip4_in1, R.drawable.v2_smlt_tip5_in1, R.drawable.v2_smlt_tip6_in1, R.drawable.v2_smlt_in1_rc_tip};
    private ArrayList<View> a;
    private int b;
    private int[] l;
    private int[] m;
    private int[] n;
    private Context o;

    public c() {
        this.a = new ArrayList();
        this.b = 7;
        this.m = d;
        this.l = c;
        this.n = e;
    }

    public c(LayoutInflater layoutInflater, ProductType productType, Context context) {
        this();
        if (productType == ProductType.BigBanana || productType == ProductType.litchiC || productType == ProductType.Orange || productType == ProductType.OrangeRAW || productType == ProductType.OrangeCV600 || productType == ProductType.Olives) {
            this.b = 8;
        }
        if (productType == ProductType.litchiC) {
            this.m = g;
            this.l = f;
            this.n = h;
        } else if (productType == ProductType.BigBanana || productType == ProductType.Orange || productType == ProductType.OrangeRAW || productType == ProductType.Olives || productType == ProductType.OrangeCV600) {
            this.m = j;
            this.l = i;
            this.n = k;
        }
        for (int i = 0; i != this.b - 1; i++) {
            this.a.add(layoutInflater.inflate(R.layout.v2_viewpager_smlt_help_first, null));
        }
        this.a.add(layoutInflater.inflate(R.layout.v2_viewpager_smlt_help_last, null));
        this.o = context;
    }

    public int getCount() {
        return this.m.length;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = (View) this.a.get(i);
        if (i != getCount() - 1) {
            DJITextView dJITextView = (DJITextView) view.findViewById(R.id.d58);
            ImageView imageView = (ImageView) view.findViewById(R.id.d56);
            ((DJITextView) view.findViewById(R.id.d57)).setText("" + (i + 1) + ". " + this.o.getString(this.l[i]));
            dJITextView.setText(this.m[i]);
            imageView.setBackgroundResource(this.n[i]);
        }
        viewGroup.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) this.a.get(i));
    }
}
