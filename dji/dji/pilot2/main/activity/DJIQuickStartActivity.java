package dji.pilot2.main.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.main.activity.DJIHubActivity;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.g;
import dji.pilot2.DJIActivity;
import dji.pilot2.main.fragment.b.b;
import dji.publics.DJIUI.DJIRelativeLayout;
import java.util.ArrayList;

public class DJIQuickStartActivity extends DJIActivity implements OnClickListener, b {
    private Fragment A;
    private DJIRelativeLayout B;
    private View C;
    private View D;
    private View E;
    private View F;
    private View G;
    private View H;
    private View I;
    private View J;
    private LinearLayout K;
    private a L;
    private boolean M = true;
    private Button a;
    private Button b;
    private ViewPager c;
    private ArrayList<Fragment> d;
    private Fragment t;
    private Fragment u;
    private Fragment v;
    private Fragment w;
    private Fragment x;
    private Fragment y;
    private Fragment z;

    public class a extends FragmentPagerAdapter {
        final /* synthetic */ DJIQuickStartActivity a;

        public a(DJIQuickStartActivity dJIQuickStartActivity, FragmentManager fragmentManager) {
            this.a = dJIQuickStartActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return (Fragment) this.a.d.get(i);
        }

        public int getCount() {
            return this.a.d.size();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (dji.pilot.publics.e.a.c(null)) {
            e();
        }
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        setContentView(R.layout.active_view_quickstart);
        setRequestedOrientation(6);
        g.a(this, "is_FirstTime", true);
        a();
        b();
    }

    private void a() {
        this.t = new dji.pilot2.main.fragment.b(R.layout.fragment_quick_one);
        this.u = new dji.pilot2.main.fragment.b(R.layout.fragment_quick_two);
        this.v = new dji.pilot2.main.fragment.b(R.layout.fragment_quick_three);
        this.w = new dji.pilot2.main.fragment.b(R.layout.fragment_quick_four);
        this.x = new dji.pilot2.main.fragment.b(R.layout.fragment_quick_five);
        this.y = new dji.pilot2.main.fragment.b(R.layout.fragment_quick_six);
        this.z = new dji.pilot2.main.fragment.b(R.layout.fragment_quick_seven);
        this.A = new dji.pilot2.main.fragment.b(R.layout.fragment_quick_eight);
        this.d = new ArrayList();
        this.d.add(this.t);
        this.d.add(this.u);
        this.d.add(this.v);
        this.d.add(this.w);
        this.d.add(this.A);
    }

    private void b() {
        this.K = (LinearLayout) findViewById(R.id.fm);
        this.C = findViewById(R.id.fn);
        this.D = findViewById(R.id.fo);
        this.E = findViewById(R.id.fp);
        this.F = findViewById(R.id.fq);
        this.J = findViewById(R.id.fu);
        this.B = (DJIRelativeLayout) findViewById(R.id.fg);
        this.c = (ViewPager) findViewById(R.id.fl);
        this.L = new a(this, getFragmentManager());
        this.c.setAdapter(this.L);
        this.a = (Button) findViewById(R.id.fj);
        this.b = (Button) findViewById(R.id.fk);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ DJIQuickStartActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return !this.a.M;
            }
        });
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(17432576, 0);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fj:
                this.B.setVisibility(8);
                this.c.setVisibility(0);
                this.K.setVisibility(0);
                return;
            case R.id.fk:
                e();
                return;
            default:
                return;
        }
    }

    private void e() {
        finish();
        Intent intent = new Intent();
        intent.setClass(this, DJIHubActivity.class);
        intent.putExtra(dji.pilot.c.b.a, true);
        startActivity(intent);
    }

    private void b(int i) {
        DJILogHelper.getInstance().LOGI("bob", "currentPager = " + i);
        this.C.setBackgroundResource(R.drawable.v2_circle);
        this.D.setBackgroundResource(R.drawable.v2_circle);
        this.E.setBackgroundResource(R.drawable.v2_circle);
        this.F.setBackgroundResource(R.drawable.v2_circle);
        this.J.setBackgroundResource(R.drawable.v2_circle);
        if (i == 0) {
            this.C.setBackgroundResource(R.drawable.v2_circle_press);
        } else if (i == 1) {
            this.D.setBackgroundResource(R.drawable.v2_circle_press);
        } else if (i == 2) {
            this.E.setBackgroundResource(R.drawable.v2_circle_press);
        } else if (i == 3) {
            this.M = false;
            this.F.setBackgroundResource(R.drawable.v2_circle_press);
        } else if (i != 4 && i != 5 && i != 6) {
            if (i == 7) {
                this.J.setBackgroundResource(R.drawable.v2_circle_press);
                this.M = false;
                this.K.setVisibility(8);
            } else if (i == 101) {
                this.K.setVisibility(8);
            } else if (i == 102) {
                this.K.setVisibility(0);
            }
        }
    }

    public void a(final boolean z) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ DJIQuickStartActivity b;

            public void run() {
                this.b.M = z;
                this.b.c.setCurrentItem(this.b.c.getCurrentItem() + 1);
            }
        });
    }

    public void a(final int i) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ DJIQuickStartActivity b;

            public void run() {
                this.b.b(i);
            }
        });
    }
}
