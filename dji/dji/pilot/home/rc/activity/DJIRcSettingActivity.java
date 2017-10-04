package dji.pilot.home.rc.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import dji.pilot.R;
import dji.pilot.home.rc.view.MineApplicationsGridView;
import dji.pilot.home.rc.view.MineProfileDetailView;
import dji.pilot.home.rc.view.MineSettingsView;
import dji.pilot.publics.e.d;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.support.DJINonViewPager;
import dji.pilot.usercenter.b.f;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.usercenter.widget.DJIProfileRoundImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DJIRcSettingActivity extends DJIActivityNoFullScreen implements OnClickListener {
    private static final int a = 0;
    private DJIStateTextView A;
    private DJIStateTextView B;
    private MineProfileDetailView C;
    private DJIProfileRoundImageView D;
    private MineSettingsView b;
    private View c;
    private List<View> d;
    private PagerAdapter t;
    private DJINonViewPager u;
    private DJIStateImageView v;
    private DJIStateTextView w;
    private DJIStateTextView x;
    private DisplayImageOptions y;
    private DJIStateTextView z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mine_setting);
        f();
        g();
        h();
    }

    private void f() {
        this.C = (MineProfileDetailView) findViewById(R.id.i1);
        this.w = (DJIStateTextView) findViewById(R.id.hw);
        this.x = (DJIStateTextView) findViewById(R.id.hx);
        this.v = (DJIStateImageView) findViewById(R.id.hu);
        this.D = (DJIProfileRoundImageView) findViewById(R.id.hz);
        this.A = (DJIStateTextView) findViewById(R.id.i0);
        this.z = (DJIStateTextView) findViewById(R.id.i2);
        this.B = (DJIStateTextView) findViewById(R.id.i3);
        this.w.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.z.setOnClickListener(this);
        this.B.setOnClickListener(this);
    }

    private void g() {
        if (f.getInstance().c()) {
            a(true);
        } else {
            a(false);
        }
    }

    private void h() {
        this.u = (DJINonViewPager) findViewById(R.id.hv);
        this.d = new ArrayList();
        this.b = new MineSettingsView(this);
        this.c = new MineApplicationsGridView(this);
        this.d.add(this.b);
        this.d.add(this.c);
        this.t = new PagerAdapter(this) {
            final /* synthetic */ DJIRcSettingActivity a;

            {
                this.a = r1;
            }

            public int getCount() {
                return this.a.d.size();
            }

            public boolean isViewFromObject(View view, Object obj) {
                return view == obj;
            }

            public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
                super.destroyItem(viewGroup, i, obj);
                viewGroup.removeView((View) this.a.d.get(i));
            }

            public int getItemPosition(Object obj) {
                return super.getItemPosition(obj);
            }

            public Object instantiateItem(ViewGroup viewGroup, int i) {
                viewGroup.addView((View) this.a.d.get(i));
                return this.a.d.get(i);
            }
        };
        this.u.setAdapter(this.t);
        this.u.setCurrentItem(0);
        this.u.setPagingEnabled(false);
    }

    public void a(boolean z) {
        if (z) {
            this.z.setVisibility(4);
            this.B.setText(R.string.mine_log_out);
            this.C.setVisibility(0);
            a();
            return;
        }
        this.z.setVisibility(0);
        this.C.setVisibility(8);
        this.A.setText(getResources().getString(R.string.mine_setting_account_unlogin));
        this.B.setText(R.string.home_account_signin);
        this.D.setImageDrawable(getResources().getDrawable(R.drawable.v2_avatar_default));
    }

    public void a() {
        this.y = new Builder().showImageOnLoading(R.drawable.v2_avatar_default).cacheInMemory(true).cacheOnDisc(false).build();
        String e = f.getInstance().e();
        String str = f.getInstance().h().l;
        if (!d.a(e)) {
            File file = new File(e);
            if (file.exists() && file.isFile()) {
                this.D.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
            } else if (str == null || str.equals("")) {
                this.D.setImageDrawable(getResources().getDrawable(R.drawable.v2_avatar_default));
            } else {
                ImageLoader.getInstance().displayImage(str, this.D, this.y);
            }
        } else if (str == null || str.equals("")) {
            this.D.setImageDrawable(getResources().getDrawable(R.drawable.v2_avatar_default));
        } else {
            ImageLoader.getInstance().displayImage(str, this.D, this.y);
        }
        b();
    }

    public void b() {
        CharSequence m = f.getInstance().m();
        if (this.A == null) {
            this.A.setText("");
        } else if (this.A.length() > 15) {
            this.A.setText(m.substring(0, 15) + "...");
        } else {
            this.A.setText(m);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hu:
                k();
                return;
            case R.id.hw:
                this.w.setBackground(getResources().getDrawable(R.drawable.system_top_bar_button_blue_bg));
                this.x.setBackground(null);
                this.u.setCurrentItem(0);
                return;
            case R.id.hx:
                this.w.setBackground(null);
                this.x.setBackground(getResources().getDrawable(R.drawable.system_top_bar_button_blue_bg));
                this.u.setCurrentItem(1);
                return;
            case R.id.i2:
                j();
                return;
            case R.id.i3:
                i();
                return;
            default:
                return;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            f.getInstance().q();
            g();
        } else if (i != 0) {
        }
    }

    private void i() {
        if (f.getInstance().c()) {
            f.getInstance().p();
            a(false);
            return;
        }
        Intent intent = new Intent(this, DJIAccountSignActivity.class);
        intent.putExtra(DJIAccountSignActivity.c, false);
        intent.putExtra(DJIAccountSignActivity.b, true);
        intent.putExtra(DJIAccountSignActivity.a, 1008);
        startActivityForResult(intent, 0);
    }

    private void j() {
        Intent intent = new Intent(this, DJIAccountSignActivity.class);
        intent.putExtra(DJIAccountSignActivity.c, false);
        intent.putExtra(DJIAccountSignActivity.b, false);
        intent.putExtra(DJIAccountSignActivity.a, 1008);
        startActivityForResult(intent, 0);
    }

    public void onBackPressed() {
        k();
    }

    private void k() {
        finish();
    }
}
