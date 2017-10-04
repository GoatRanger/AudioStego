package dji.pilot2.usercenter.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import dji.pilot.R;
import dji.pilot.publics.objects.c;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJISwitch;
import dji.pilot.usercenter.b.d;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJITextView;

public class b extends c {
    private DJITextView a = null;
    private DJIStateImageView b = null;
    private DJIStateImageView c = null;
    private DJITextView d = null;
    private int e = 0;
    private DJISwitch f = null;
    private d g = d.getInstance();
    private OnClickListener h = new OnClickListener(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.d42:
                    this.a.dismiss();
                    return;
                case R.id.d44:
                    this.a.e = this.a.e - 1;
                    this.a.c.setEnabled(true);
                    if (this.a.e == 0) {
                        this.a.b.setEnabled(false);
                    }
                    this.a.a(this.a.e);
                    return;
                case R.id.d46:
                    this.a.e = this.a.e + 1;
                    this.a.b.setEnabled(true);
                    if (this.a.e == 2) {
                        this.a.c.setEnabled(false);
                    }
                    this.a.a(this.a.e);
                    return;
                case R.id.d47:
                    this.a.dismiss();
                    new a(this.a.N).show();
                    return;
                default:
                    return;
            }
        }
    };

    public b(Context context) {
        super(context);
        setContentView(R.layout.v2_usercenter_flightrecord_setting_view);
        findViewById(R.id.d42).setOnClickListener(this.h);
        this.a = (DJITextView) findViewById(R.id.d47);
        this.a.setOnClickListener(this.h);
        this.d = (DJITextView) findViewById(R.id.d45);
        this.b = (DJIStateImageView) findViewById(R.id.d44);
        this.b.setOnClickListener(this.h);
        this.c = (DJIStateImageView) findViewById(R.id.d46);
        this.c.setOnClickListener(this.h);
        this.f = (DJISwitch) findViewById(R.id.d43);
        this.f.setChecked(this.g.d());
        this.f.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.a.g.a(z);
            }
        });
        switch (this.g.c()) {
            case 1:
                this.b.setEnabled(false);
                this.c.setEnabled(true);
                this.d.setText(R.string.flight_record_withinmonth);
                this.e = 0;
                return;
            case 2:
                this.b.setEnabled(true);
                this.c.setEnabled(true);
                this.d.setText(R.string.flight_record_withinhalfyear);
                this.e = 1;
                return;
            case 3:
                this.b.setEnabled(true);
                this.c.setEnabled(false);
                this.d.setText(R.string.flight_record_withinall);
                this.e = 2;
                return;
            default:
                return;
        }
    }

    protected void onCreate(Bundle bundle) {
        int i;
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            i = -1;
        } else {
            i = dji.pilot.fpv.model.b.a(this.N, R.dimen.gs);
        }
        a(i, -2, 0, 17, false, false);
        a(0.4f);
    }

    private void a(int i) {
        if (i == 0) {
            this.d.setText(R.string.flight_record_withinmonth);
            this.g.a(1);
        } else if (i == 1) {
            this.d.setText(R.string.flight_record_withinhalfyear);
            this.g.a(2);
        } else if (i == 2) {
            this.d.setText(R.string.flight_record_withinall);
            this.g.a(3);
        }
    }
}
