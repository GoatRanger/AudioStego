package dji.pilot2.whatsnew.acitivty;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import dji.pilot.R;

class WhatsNewActivity$2 implements OnPageChangeListener {
    final /* synthetic */ WhatsNewActivity a;

    WhatsNewActivity$2(WhatsNewActivity whatsNewActivity) {
        this.a = whatsNewActivity;
    }

    public void onPageSelected(int i) {
        for (int i2 = 0; i2 < WhatsNewActivity.a(this.a).getCount(); i2++) {
            if (i == i2) {
                WhatsNewActivity.b(this.a).getChildAt(i2).setBackgroundResource(R.drawable.v2_whatsnew_indicator_active);
            } else {
                WhatsNewActivity.b(this.a).getChildAt(i2).setBackgroundResource(R.drawable.v2_whatsnew_indicator_inactive);
            }
            if (i == WhatsNewActivity.a(this.a).getCount() - 1) {
                WhatsNewActivity.b(this.a).setBackground(this.a.getResources().getDrawable(R.drawable.v2_grad_bg));
                WhatsNewActivity.a(this.a).a();
            } else {
                WhatsNewActivity.b(this.a).setVisibility(0);
                WhatsNewActivity.b(this.a).setBackgroundColor(this.a.getResources().getColor(R.color.nq));
            }
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageScrollStateChanged(int i) {
    }
}
