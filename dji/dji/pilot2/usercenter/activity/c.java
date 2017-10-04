package dji.pilot2.usercenter.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import dji.pilot.R;
import dji.pilot.fpv.model.b;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.usercenter.b.d;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;

public class c extends dji.pilot.publics.objects.c implements OnClickListener {
    private DJIStateTextView a = null;
    private DJIStateTextView b = null;
    private ProgressBar c = null;
    private DJIStateImageView d = null;
    private d e = d.getInstance();

    public c(Context context) {
        super(context);
        setContentView(R.layout.v2_usercenter_flightrecord_sync_waiting_view);
        findViewById(R.id.d4x).setOnClickListener(this);
        this.a = (DJIStateTextView) findViewById(R.id.d4v);
        this.b = (DJIStateTextView) findViewById(R.id.d4u);
        this.c = (ProgressBar) findViewById(R.id.d4w);
        this.c.setMax(100);
        this.d = (DJIStateImageView) findViewById(R.id.d4t);
        String str = "";
        switch (this.e.c()) {
            case 1:
                str = getContext().getString(R.string.flight_record_withinmonth);
                break;
            case 2:
                str = getContext().getString(R.string.flight_record_withinhalfyear);
                break;
            case 3:
                str = getContext().getString(R.string.flight_record_withinall);
                break;
        }
        this.b.setText(String.format(getContext().getString(R.string.flight_record_sync_for_time), new Object[]{str}));
    }

    public void show() {
        super.show();
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.i);
        loadAnimation.setInterpolator(new LinearInterpolator());
        this.d.startAnimation(loadAnimation);
    }

    protected void onCreate(Bundle bundle) {
        int i;
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            i = -1;
        } else {
            i = b.a(this.N, R.dimen.gs);
        }
        a(i, -2, 0, 17, false, false);
        a(0.4f);
    }

    public void a(int i) {
        this.a.setText(String.format(getContext().getString(R.string.flight_record_sync_progress), new Object[]{Integer.valueOf(i)}));
        this.c.setProgress(i);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.d4x:
                this.e.s();
                dismiss();
                return;
            default:
                return;
        }
    }
}
