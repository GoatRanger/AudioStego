package dji.pilot2.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import dji.pilot.R;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.mine.b.e;

public class PrivacyActivity extends DJIActivityNoFullScreen {
    private Switch a;
    private Switch b;
    private Switch c;
    private Boolean d;
    private Boolean t;
    private Boolean u;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_privacy);
        this.a = (Switch) findViewById(R.id.cf4);
        this.b = (Switch) findViewById(R.id.cf5);
        this.c = (Switch) findViewById(R.id.cf6);
        this.a.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ PrivacyActivity a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.a.d = Boolean.valueOf(z);
                e.getInstance().a(Boolean.valueOf(z));
            }
        });
        this.b.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ PrivacyActivity a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.a.t = Boolean.valueOf(z);
                e.getInstance().c(Boolean.valueOf(z));
            }
        });
        this.c.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ PrivacyActivity a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.a.u = Boolean.valueOf(z);
                e.getInstance().b(Boolean.valueOf(z));
            }
        });
    }

    protected void onResume() {
        super.onResume();
        this.d = e.getInstance().e();
        this.t = e.getInstance().g();
        this.u = e.getInstance().f();
        this.a.setChecked(this.d.booleanValue());
        this.b.setChecked(this.t.booleanValue());
        this.c.setChecked(this.u.booleanValue());
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.cf3:
                finish();
                return;
            default:
                return;
        }
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }
}
