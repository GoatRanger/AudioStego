package dji.pilot2.academy.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.publics.c.d;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.n;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.academy.a.e;
import dji.pilot2.academy.model.FlighBookModel.FlightBookData;
import dji.pilot2.academy.widget.c;
import dji.pilot2.nativeexplore.b.i;
import dji.pilot2.publics.b.a$j;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJITextView;
import java.util.HashMap;
import java.util.Map;

public class DJIFlightBookActivity extends DJIActivityNoFullScreen implements OnClickListener, a$j {
    public static String a = "name";
    public static String b = "type";
    private c A;
    private e B;
    private ProductType C = ProductType.None;
    private String D;
    private View E;
    private View F;
    private View G;
    private View H;
    private View I;
    private DJITextView w;
    private DJIStateImageView x;
    private OnClickListener y = null;
    private ListView z;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_flight_book_activity);
        this.C = ProductType.find(getIntent().getIntExtra("key_product_index", 0));
        this.D = d.getInstance().k(this.C);
        a();
        b();
        f();
        g();
        i();
        j();
        this.F.setVisibility(0);
    }

    protected void a() {
        this.w = (DJITextView) findViewById(R.id.c43);
        this.x = (DJIStateImageView) findViewById(R.id.c42);
        this.z = (ListView) findViewById(R.id.cn3);
        this.E = findViewById(R.id.c69);
        this.F = findViewById(R.id.c6b);
        this.F.setOnClickListener(this);
        this.G = findViewById(R.id.d5c);
        this.H = findViewById(R.id.d5a);
        this.H.setOnClickListener(this);
        this.I = findViewById(R.id.d5b);
        this.I.setOnClickListener(this);
    }

    protected void b() {
        this.y = new OnClickListener(this) {
            final /* synthetic */ DJIFlightBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.c42:
                        this.a.finish();
                        return;
                    case R.id.d5b:
                        this.a.h();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    protected void f() {
        this.A = new c(getApplicationContext());
    }

    protected void g() {
        CharSequence string = getIntent().getExtras().getString(a, "");
        this.z.setAdapter(this.A);
        this.z.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DJIFlightBookActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                FlightBookData flightBookData = (FlightBookData) this.a.A.getItem(i);
                Intent intent = new Intent(this.a, DJIAcademyWebViewActivity.class);
                intent.putExtra(DJIWebviewFragment.o, "http://djistatic.com/academy/faq/?id=" + String.valueOf(flightBookData.id) + a$j.p + this.a.k());
                this.a.startActivity(intent);
            }
        });
        this.w.setOnClickListener(this.y);
        this.w.setText(string);
        this.x.setOnClickListener(this.y);
    }

    private void i() {
        Intent intent = getIntent();
        String str = null;
        if (intent.getExtras().getInt(b) == 1) {
            str = "http://www.djiexplore.com/academy/faq/" + k() + "/1/" + this.D;
        } else if (intent.getExtras().getInt(b) == 2) {
            str = "http://www.djiexplore.com/academy/faq/" + k() + "/2/" + this.D;
        }
        String[] strArr = new String[]{str};
        Map hashMap = new HashMap();
        if (f.getInstance().c()) {
            hashMap.put("token", f.getInstance().n());
        } else {
            hashMap.put("token", "");
        }
        this.B = new e(this, strArr, hashMap, ParamKey.PAGE, n.am);
        this.B.a(new i(this) {
            final /* synthetic */ DJIFlightBookActivity a;

            {
                this.a = r1;
            }

            public void a(int i, boolean z, boolean z2) {
                this.a.F.setVisibility(8);
                this.a.G.setVisibility(8);
                this.a.H.setVisibility(8);
                this.a.A.a(z2);
                this.a.A.a(this.a.B.a);
                if (this.a.A.getCount() == 0) {
                    this.a.E.setVisibility(0);
                } else {
                    this.a.E.setVisibility(4);
                }
            }

            public void a(int i) {
                this.a.F.setVisibility(8);
                this.a.G.setVisibility(8);
                this.a.E.setVisibility(4);
                this.a.H.setVisibility(0);
            }
        });
    }

    protected void h() {
    }

    private void j() {
        this.B.c();
    }

    private String k() {
        String language = getResources().getConfiguration().locale.getLanguage();
        if (language.endsWith("zh")) {
            return "cn";
        }
        return language;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.d5b:
                j();
                this.G.setVisibility(0);
                return;
            default:
                return;
        }
    }
}
