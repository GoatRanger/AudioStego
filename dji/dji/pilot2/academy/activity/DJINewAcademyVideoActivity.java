package dji.pilot2.academy.activity;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.publics.c.d;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.academy.model.AcademyVideoInfo;
import dji.pilot2.academy.widget.b;
import dji.pilot2.mine.view.RefreshableView;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJITextView;

public class DJINewAcademyVideoActivity extends DJIActivityNoFullScreen {
    private b A;
    private DJITextView a;
    private DJIStateImageView b;
    private OnClickListener c = null;
    private RefreshableView d;
    private ListView t;
    private View u;
    private View v;
    private View w;
    private View x;
    private ProductType y = ProductType.None;
    private String z;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.y = ProductType.find(getIntent().getIntExtra("key_product_index", 0));
        this.z = d.getInstance().k(this.y);
        setContentView(R.layout.v2_activity_academy_video);
        a();
        b();
        f();
        g();
    }

    protected void a() {
        this.a = (DJITextView) findViewById(R.id.c43);
        this.b = (DJIStateImageView) findViewById(R.id.c42);
        this.d = (RefreshableView) findViewById(R.id.c6c);
        this.t = (ListView) findViewById(R.id.c6d);
        this.v = findViewById(R.id.d5a);
        this.u = findViewById(R.id.cvf);
        this.w = findViewById(R.id.d5b);
        this.x = findViewById(R.id.d5c);
    }

    protected void b() {
        this.c = new OnClickListener(this) {
            final /* synthetic */ DJINewAcademyVideoActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.c42:
                        this.a.finish();
                        return;
                    case R.id.d5b:
                        Toast.makeText(this.a.getApplicationContext(), "reload button", 0).show();
                        this.a.h();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    protected void f() {
        this.A = new b(this);
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            this.A.a(2);
        } else {
            this.A.a(1);
        }
    }

    protected void g() {
        this.a.setOnClickListener(this.c);
        this.a.setText(R.string.v2_academy_video_text);
        this.b.setOnClickListener(this.c);
        this.w.setOnClickListener(this.c);
        this.d.setOnRefreshListener(new RefreshableView.b(this) {
            final /* synthetic */ DJINewAcademyVideoActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.h();
            }
        }, 1781);
        this.t.setAdapter(this.A);
        dji.pilot2.academy.a.d.getInstance().a(new e$a(this) {
            final /* synthetic */ DJINewAcademyVideoActivity a;

            {
                this.a = r1;
            }

            public void a(int i, long j, long j2, int i2, Object obj) {
            }

            public void a(int i, int i2, int i3, Object obj, Object obj2) {
                this.a.d.finishRefreshing();
                if (obj2 instanceof AcademyVideoInfo) {
                    DJILogHelper.getInstance().LOGI("bob", "DJIVideoDataController onSuccess");
                    this.a.A.a(((AcademyVideoInfo) obj2).mVideoInfos);
                }
                this.a.t.setEmptyView(this.a.u);
                this.a.v.setVisibility(8);
            }

            public void a(int i, boolean z, int i2, Object obj) {
                DJILogHelper.getInstance().LOGI("bob", "DJIVideoDataController onStart");
            }

            public void a(int i, int i2, int i3, Object obj) {
                this.a.d.finishRefreshing();
                DJILogHelper.getInstance().LOGI("bob", "DJIVideoDataController onFail");
                this.a.v.setVisibility(0);
            }
        });
        dji.pilot2.academy.a.d.getInstance().a(getApplicationContext(), this.z);
    }

    protected void h() {
        if (dji.pilot2.academy.a.d.getInstance().b()) {
            this.d.finishRefreshing();
        } else {
            dji.pilot2.academy.a.d.getInstance().a(getApplicationContext(), this.z);
        }
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
    }

    protected void onDestroy() {
        dji.pilot2.academy.a.d.getInstance().a();
        super.onDestroy();
    }
}
