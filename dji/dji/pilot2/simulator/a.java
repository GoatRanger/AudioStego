package dji.pilot2.simulator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.publics.objects.c;
import dji.pilot.visual.a.d;
import dji.publics.DJIUI.DJIImageView;
import java.util.ArrayList;

public class a extends c {
    DJIImageView a;
    private ArrayList<View> b = new ArrayList();
    private ViewPager c;
    private c d;
    private LinearLayout e;

    public a(Context context, DJIImageView dJIImageView, ProductType productType) {
        super(context);
        setContentView(R.layout.v2_dialog_smlt_help);
        a(productType);
        this.a = dJIImageView;
    }

    private void a(ProductType productType) {
        LayoutInflater layoutInflater = getLayoutInflater();
        this.c = (ViewPager) findViewById(R.id.cjr);
        this.d = new c(layoutInflater, productType, this.N);
        this.c.setAdapter(this.d);
        this.e = (LinearLayout) findViewById(R.id.cjq);
        for (int i = 0; i != this.d.getCount(); i++) {
            View imageView = new ImageView(this.N);
            imageView.setLayoutParams(new LayoutParams(-2, -2));
            imageView.setPadding(10, 5, 10, 5);
            imageView.setImageDrawable(this.N.getResources().getDrawable(R.drawable.help_tip_index_circle));
            this.e.addView(imageView);
        }
        b();
        this.e.getChildAt(0).setAlpha(1.0f);
        this.c.addOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onPageSelected(int i) {
                this.a.b();
                this.a.e.getChildAt(i).setAlpha(1.0f);
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    private void b() {
        for (int i = 0; i != this.d.getCount(); i++) {
            this.e.getChildAt(i).setAlpha(d.c);
        }
    }

    protected void onCreate(Bundle bundle) {
        a((int) this.N.getResources().getDimension(R.dimen.go), (int) this.N.getResources().getDimension(R.dimen.fp), 0, 51, true, true);
        int[] iArr = new int[2];
        this.a.getLocationOnScreen(iArr);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.x = iArr[0] - this.N.getResources().getDimensionPixelSize(R.dimen.go);
        attributes.y = ((iArr[1] + (this.a.getHeight() / 2)) - this.N.getResources().getDimensionPixelSize(R.dimen.gq)) - this.N.getResources().getDimensionPixelSize(R.dimen.gj);
        getWindow().setAttributes(attributes);
    }

    protected boolean a() {
        return false;
    }
}
