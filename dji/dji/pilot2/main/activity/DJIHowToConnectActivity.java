package dji.pilot2.main.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot2.DJIFragmentActivityNoFullScreen;
import dji.pilot2.academy.model.a;
import dji.pilot2.academy.widget.h;
import dji.pilot2.utils.k;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;

public class DJIHowToConnectActivity extends DJIFragmentActivityNoFullScreen {
    public static final String a = "DJIHowToConnectActivity";
    public static final String r = "type_index";
    public static final String s = "title_text";
    private int t;
    private String u;
    private TextView v;
    private Spinner w;
    private h x = null;
    private DJIWebviewFragment y = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_how_to_connect);
        a();
        FragmentManager fragmentManager = getFragmentManager();
        Fragment findFragmentById = fragmentManager.findFragmentById(R.id.ck);
        if (findFragmentById == null) {
            findFragmentById = DJIWebviewFragment.c(k.a((Context) this, this.t));
            this.y = findFragmentById;
            fragmentManager.beginTransaction().add(R.id.ck, findFragmentById).commit();
        } else {
            this.y = (DJIWebviewFragment) findFragmentById;
        }
        f();
    }

    private void a() {
        Intent intent = getIntent();
        this.t = intent.getIntExtra(r, 0);
        this.u = intent.getStringExtra("title_text");
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    private void f() {
        this.v = (TextView) findViewById(R.id.d09);
        this.w = (Spinner) findViewById(R.id.d0_);
        this.v.setText(this.u);
        Spinner spinner = this.w;
        SpinnerAdapter hVar = new h(this, a.getInstance(this).a());
        this.x = hVar;
        spinner.setAdapter(hVar);
        this.x.a(ProductType.Grape2);
        this.x.a(ProductType.Olives);
        this.x.a(ProductType.OrangeCV600);
        this.x.a(ProductType.Pomato);
        this.x.a(ProductType.P34K);
        this.x.a(ProductType.A3);
        this.x.a(ProductType.N3);
        this.x.a(ProductType.LonganMobile);
        this.x.a(ProductType.KumquatS);
        this.w.setOnItemSelectedListener(new OnItemSelectedListener(this) {
            final /* synthetic */ DJIHowToConnectActivity a;

            {
                this.a = r1;
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                this.a.x.a(i);
                this.a.y.b(k.a(this.a, i));
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        if (this.t >= this.x.getCount()) {
            this.t = this.x.getCount() - 1;
        }
        this.w.setSelection(this.t);
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.d08:
                finish();
                return;
            default:
                return;
        }
    }
}
