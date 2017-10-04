package dji.pilot2.academy.activity;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.publics.c.d;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.academy.a.b;
import dji.pilot2.academy.model.AcademyDocInfo;
import dji.pilot2.academy.widget.a;
import dji.pilot2.mine.view.RefreshableView;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJITextView;

public class DJINewAcademyInstructionActivity extends DJIActivityNoFullScreen {
    private a A;
    private DJITextView a;
    private DJIStateImageView b;
    private OnClickListener c = null;
    private RefreshableView d;
    private ListView t;
    private View u;
    private View v;
    private View w;
    private View x;
    private ProductType y;
    private String z;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_academy_instruction);
        this.y = ProductType.find(getIntent().getIntExtra("key_product_index", 0));
        this.z = d.getInstance().k(this.y);
        a();
        b();
        f();
        g();
    }

    protected void a() {
        this.a = (DJITextView) findViewById(R.id.c43);
        this.b = (DJIStateImageView) findViewById(R.id.c42);
        this.t = (ListView) findViewById(R.id.c5y);
        this.v = findViewById(R.id.d5a);
        this.u = findViewById(R.id.cvf);
        this.w = findViewById(R.id.d5b);
        this.x = findViewById(R.id.d5c);
    }

    protected void b() {
        this.c = new OnClickListener(this) {
            final /* synthetic */ DJINewAcademyInstructionActivity a;

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
        this.A = new a(this, this.z);
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            this.A.a(5);
        } else {
            this.A.a(2);
        }
    }

    protected void g() {
        this.a.setOnClickListener(this.c);
        this.a.setText(R.string.v2_academy_introduction_text);
        this.b.setOnClickListener(this.c);
        this.w.setOnClickListener(this.c);
        this.t.setAdapter(this.A);
        b.getInstance().a(new e$a(this) {
            final /* synthetic */ DJINewAcademyInstructionActivity a;

            {
                this.a = r1;
            }

            public void a(int i, long j, long j2, int i2, Object obj) {
                DJILogHelper.getInstance().LOGI("bob", "DJIDocDataController onUpdate");
                this.a.A.b(i);
            }

            public void a(int i, int i2, int i3, Object obj, Object obj2) {
                if (obj2 instanceof AcademyDocInfo) {
                    DJILogHelper.getInstance().LOGI("bob", "DJIDocDataController onSuccess");
                    AcademyDocInfo academyDocInfo = (AcademyDocInfo) obj2;
                }
                this.a.A.c(i);
                this.a.t.setEmptyView(this.a.u);
                this.a.v.setVisibility(8);
            }

            public void a(int i, boolean z, int i2, Object obj) {
                DJILogHelper.getInstance().LOGI("bob", "DJIDocDataController onStart");
                this.a.A.d(i);
            }

            public void a(int i, int i2, int i3, Object obj) {
                DJILogHelper.getInstance().LOGI("bob", "DJIDocDataController onFail");
                this.a.A.a(i, i2, obj);
                if (i == 2) {
                    this.a.v.setVisibility(0);
                }
            }
        });
        this.A.a(this.z);
    }

    protected void h() {
        this.A.a(this.z);
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
        b.getInstance().a();
        super.onDestroy();
    }
}
