package dji.pilot2.academy.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.pilot.publics.c.d;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.academy.a.c;
import dji.pilot2.academy.widget.f;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJITextView;

public class DJINewAcademyFlightActivity extends DJIActivityNoFullScreen {
    private RelativeLayout A;
    private ProductType B = ProductType.None;
    private String C;
    private View D;
    int a;
    private RelativeLayout b;
    private RelativeLayout c;
    private RelativeLayout d;
    private EditText t;
    private DJITextView u;
    private DJIStateImageView v;
    private ProgressBar w;
    private ListView x;
    private f y;
    private OnClickListener z = null;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_academy_flight);
        this.B = ProductType.find(getIntent().getIntExtra("key_product_index", 0));
        this.C = d.getInstance().k(this.B);
        DJILogHelper.getInstance().LOGI("bob", "appid =" + this.C);
        this.a = getIntent().getIntExtra("key_product_index", 0);
        a();
        b();
        f();
        g();
    }

    protected void a() {
        this.u = (DJITextView) findViewById(R.id.c43);
        this.v = (DJIStateImageView) findViewById(R.id.c42);
        this.b = (RelativeLayout) findViewById(R.id.c5r);
        this.c = (RelativeLayout) findViewById(R.id.c5s);
        this.d = (RelativeLayout) findViewById(R.id.c5t);
        this.t = (EditText) findViewById(R.id.c5v);
        this.x = (ListView) findViewById(R.id.c5x);
        this.w = (ProgressBar) findViewById(R.id.c5w);
        this.A = (RelativeLayout) findViewById(R.id.c5u);
        this.D = findViewById(R.id.cvf);
    }

    protected void b() {
        this.z = new OnClickListener(this) {
            final /* synthetic */ DJINewAcademyFlightActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Intent intent;
                switch (view.getId()) {
                    case R.id.c42:
                        this.a.finish();
                        return;
                    case R.id.c5r:
                        intent = new Intent(this.a, DJIFlightBookActivity.class);
                        intent.putExtra(DJIFlightBookActivity.a, this.a.getResources().getString(R.string.v2_academy_flight_novice_cheats));
                        intent.putExtra(DJIFlightBookActivity.b, 1);
                        intent.putExtra("key_product_index", this.a.a);
                        this.a.startActivity(intent);
                        return;
                    case R.id.c5s:
                        intent = new Intent(this.a, DJIFlightBookActivity.class);
                        intent.putExtra(DJIFlightBookActivity.a, this.a.getResources().getString(R.string.v2_academy_hot_tips));
                        intent.putExtra(DJIFlightBookActivity.b, 2);
                        intent.putExtra("key_product_index", this.a.a);
                        this.a.startActivity(intent);
                        return;
                    case R.id.c5t:
                        intent = new Intent(this.a, DJINewAcademyNormalQActivity.class);
                        intent.putExtra(DJINewAcademyNormalQActivity.a, this.a.getResources().getString(R.string.v2_academy_faq));
                        intent.putExtra("key_product_index", this.a.a);
                        this.a.startActivity(intent);
                        return;
                    default:
                        return;
                }
            }
        };
    }

    protected void f() {
        this.y = new f(getApplicationContext());
    }

    protected void g() {
        this.u.setOnClickListener(this.z);
        if (b.h(this.B)) {
            this.u.setText(R.string.v2_academy_flight_osmo_text);
        } else {
            this.u.setText(R.string.v2_academy_flight_text);
        }
        this.v.setOnClickListener(this.z);
        this.b.setOnClickListener(this.z);
        this.c.setOnClickListener(this.z);
        this.d.setOnClickListener(this.z);
        this.x.setAdapter(this.y);
        this.x.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DJINewAcademyFlightActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                DJILogHelper.getInstance().LOGI("bob", "点击了 " + i);
                String b = this.a.y.b(i);
                Intent intent = new Intent(this.a, DJIAcademyWebViewActivity.class);
                intent.putExtra(DJIWebviewFragment.o, b);
                this.a.startActivity(intent);
            }
        });
        this.A.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ DJINewAcademyFlightActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                this.a.A.setFocusable(true);
                this.a.A.setFocusableInTouchMode(true);
                this.a.A.requestFocus();
                ((InputMethodManager) this.a.getSystemService("input_method")).hideSoftInputFromWindow(this.a.t.getWindowToken(), 0);
                return false;
            }
        });
        c.getInstance().a(new e$a(this) {
            final /* synthetic */ DJINewAcademyFlightActivity a;

            {
                this.a = r1;
            }

            public void a(int i, long j, long j2, int i2, Object obj) {
            }

            public void a(int i, int i2, int i3, Object obj, Object obj2) {
                this.a.w.setVisibility(8);
                this.a.y.a(i, this.a.C);
                this.a.x.setEmptyView(this.a.D);
            }

            public void a(int i, boolean z, int i2, Object obj) {
                this.a.y.a(i);
            }

            public void a(int i, int i2, int i3, Object obj) {
                this.a.w.setVisibility(8);
                this.a.y.a(i, i2, obj);
                this.a.x.setEmptyView(this.a.D);
            }
        });
        this.w.setVisibility(0);
        this.y.b(this.C);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
        c.getInstance().a();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(motionEvent);
    }
}
