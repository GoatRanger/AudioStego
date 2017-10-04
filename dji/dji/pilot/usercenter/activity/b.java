package dji.pilot.usercenter.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.objects.c;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJITextView;
import dji.publics.widget.djiviewpager.DJIViewPager;
import java.util.ArrayList;
import java.util.List;

public class b extends c {
    private static final boolean a = false;
    private DJITextView b = null;
    private DJITextView c = null;
    private DJITextView d = null;
    private DJIViewPager e = null;
    private List<Bitmap> f = new ArrayList();
    private a g = null;
    private final b[] h = new b[4];
    private final int[] i = new int[]{R.drawable.v2_photo_editor_filter_photo_rise, R.drawable.v2_photo_editor_filter_photo_rise, R.drawable.v2_photo_editor_filter_photo_rise, R.drawable.v2_photo_editor_filter_photo_rise};

    private final class a extends PagerAdapter {
        final /* synthetic */ b a;

        private a(b bVar) {
            this.a = bVar;
        }

        public int getCount() {
            return this.a.f != null ? this.a.f.size() : 0;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            int length = i % this.a.h.length;
            if (viewGroup.getChildCount() == this.a.h.length) {
                viewGroup.removeView(this.a.h[length].a);
            }
            if (this.a.h[length].a.getParent() != null) {
                viewGroup.removeView(this.a.h[length].a);
            }
            this.a.h[length].b.setImageBitmap((Bitmap) this.a.f.get(i));
            viewGroup.addView(this.a.h[length].a);
            this.a.e.setObjectForPosition(this.a.h[length].a, i);
            return this.a.h[length].a;
        }
    }

    private static final class b {
        public View a;
        public DJIImageView b;

        private b() {
            this.a = null;
            this.b = null;
        }
    }

    public b(Context context) {
        super(context);
        b();
    }

    public void a(List<Bitmap> list) {
        this.f.clear();
        for (int i = 0; i < list.size(); i++) {
            Bitmap bitmap = (Bitmap) list.get(i);
            if (bitmap != null) {
                this.f.add(bitmap);
            }
        }
    }

    protected void onStart() {
        super.onStart();
        this.g.notifyDataSetChanged();
        this.e.setCurrentItem(0);
        this.d.setText(String.format("1/%d", new Object[]{Integer.valueOf(this.f.size())}));
    }

    protected void onStop() {
        c();
        super.onStop();
    }

    private void b() {
        setContentView(R.layout.photo_look_view);
        a(LayoutInflater.from(this.N));
        this.d = (DJITextView) findViewById(R.id.c0s);
        this.b = (DJITextView) findViewById(R.id.c0r);
        this.c = (DJITextView) findViewById(R.id.c0t);
        this.e = (DJIViewPager) findViewById(R.id.bg0);
        this.b.go();
        this.c.setText("");
        this.c.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.close_black_icon, 0);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.dismiss();
            }
        });
        this.d.setTextColor(-16777216);
        this.d.getPaint().setFakeBoldText(true);
        this.d.setText(R.string.flight_record_moment_desc);
        DJIViewPager dJIViewPager = this.e;
        PagerAdapter aVar = new a();
        this.g = aVar;
        dJIViewPager.setAdapter(aVar);
        this.e.setOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onPageSelected(int i) {
                this.a.d.setText(String.format("%d/%d", new Object[]{Integer.valueOf(i + 1), Integer.valueOf(this.a.f.size())}));
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    private void a(LayoutInflater layoutInflater) {
        for (int i = 0; i < this.h.length; i++) {
            b bVar = new b();
            View inflate = layoutInflater.inflate(R.layout.photo_look_item, null);
            bVar.a = inflate;
            bVar.b = (DJIImageView) inflate.findViewById(R.id.bfz);
            this.h[i] = bVar;
        }
    }

    private void c() {
        for (b bVar : this.h) {
            bVar.b.setImageBitmap(null);
        }
    }

    protected void onCreate(Bundle bundle) {
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            a((int) (((float) DJIBaseActivity.screenHeight) * 0.85f), (int) ((((((float) DJIBaseActivity.screenHeight) * 0.85f) * 9.0f) / dji.gs.e.b.a) + getContext().getResources().getDimension(R.dimen.g1)), 0, 17, true, true);
        } else {
            int i = (int) (((float) DJIBaseActivity.screenWidth) * 0.4f);
            a(i, (int) (((((float) i) * 9.0f) / dji.gs.e.b.a) + getContext().getResources().getDimension(R.dimen.g1)), 0, 17, true, true);
        }
        a(0.4f);
    }
}
