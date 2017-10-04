package dji.pilot.flyunlimit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.midware.data.forbid.FlyForbidProtocol.LevelType;
import dji.midware.data.model.P3.DataFlycGetPushForbidStatus.DJIFlightLimitAreaState;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.pilot.R;
import dji.pilot.c.d;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;

public class NfzRedAlertView extends RelativeLayout implements a {
    private OnClickListener a;
    private TextView b;
    private TextView c;
    private View d;
    private TextView e;
    private TextView f;
    private int g = 0;

    public NfzRedAlertView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.b = (TextView) findViewById(R.id.bf8);
        this.c = (TextView) findViewById(R.id.bf9);
        this.d = findViewById(R.id.bf_);
        this.e = (TextView) findViewById(R.id.bfa);
        this.f = (TextView) findViewById(R.id.bfb);
        this.e.getPaint().setFlags(8);
        this.f.getPaint().setFlags(8);
        this.a = new OnClickListener(this) {
            final /* synthetic */ NfzRedAlertView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                View view2 = (View) this.a.getParent();
                switch (view.getId()) {
                    case R.id.bf_:
                        if (view2 instanceof DJIStageView) {
                            ((DJIStageView) view2).stop();
                            return;
                        }
                        return;
                    case R.id.bfa:
                        if (!(view2 instanceof DJIStageView)) {
                            return;
                        }
                        if (this.a.g == 0 || this.a.g == 9) {
                            ((DJIStageView) view2).createStageView(R.layout.nfz_red_zone_detail_view, R.string.nfz_all_title, true);
                            return;
                        }
                        a createStageView = ((DJIStageView) view2).createStageView(R.layout.flight_forbid_detail_view, R.string.nfz_all_title, true);
                        if (createStageView instanceof NfzZoneDescView) {
                            ((NfzZoneDescView) createStageView).setType(LevelType.CAN_NOT_UNLIMIT);
                            return;
                        }
                        return;
                    case R.id.bfb:
                        if (view2 instanceof DJIStageView) {
                            ((DJIStageView) view2).createStageView(R.layout.nfz_report_error_view, 2, true);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        this.d.setOnClickListener(this.a);
        this.e.setOnClickListener(this.a);
        this.f.setOnClickListener(this.a);
    }

    public void setContent(int i, int i2, int i3, String str) {
        if (d.b.equals(MODE.a) || i2 != LevelType.CAN_UNLIMIT.value()) {
            if (i2 == LevelType.CAN_NOT_UNLIMIT.value()) {
                if (i3 == DJIFlightLimitAreaState.NearLimit.value() || i3 == DJIFlightLimitAreaState.InHalfLimit.value() || i3 == DJIFlightLimitAreaState.InSlowDownArea.value()) {
                    this.c.setText(String.format(getContext().getResources().getString(R.string.flight_forbid_not_unlock_edge_alert_txt), new Object[]{dji.pilot.flyunlimit.c.a.a(getContext(), i)}));
                } else {
                    this.c.setText(String.format(getContext().getResources().getString(R.string.flight_forbid_not_unlock_alert_txt), new Object[]{dji.pilot.flyunlimit.c.a.a(getContext(), i)}));
                }
            } else if (i2 == LevelType.CAN_UNLIMIT.value()) {
                if (i3 == DJIFlightLimitAreaState.NearLimit.value() || i3 == DJIFlightLimitAreaState.InHalfLimit.value() || i3 == DJIFlightLimitAreaState.InSlowDownArea.value()) {
                    this.c.setText(String.format(getContext().getResources().getString(R.string.flight_forbid_edge_alert_p3c_txt), new Object[]{dji.pilot.flyunlimit.c.a.a(getContext(), i)}));
                } else {
                    this.c.setText(String.format(getContext().getResources().getString(R.string.flight_forbid_alert_p3c_txt), new Object[]{dji.pilot.flyunlimit.c.a.a(getContext(), i)}));
                }
            }
        } else if (i3 == DJIFlightLimitAreaState.NearLimit.value() || i3 == DJIFlightLimitAreaState.InHalfLimit.value() || i3 == DJIFlightLimitAreaState.InSlowDownArea.value()) {
            this.c.setText(String.format(getContext().getResources().getString(R.string.flight_forbid_edge_alert_slave_txt), new Object[]{dji.pilot.flyunlimit.c.a.a(getContext(), i)}));
        } else {
            this.c.setText(String.format(getContext().getResources().getString(R.string.flight_forbid_alert_slave_txt), new Object[]{dji.pilot.flyunlimit.c.a.a(getContext(), i)}));
        }
        this.c.setText(this.c.getText() + getContext().getString(R.string.nfz_detected_zone_title) + str);
        if (i == 0 || i == 9) {
            this.b.setText(R.string.flight_forbid_red_zone_title);
            this.e.setText(R.string.flight_forbid_red_zone_entrance);
        } else {
            this.b.setText(R.string.geo_red_zone_title);
            this.e.setText(R.string.flight_forbid_yellow_zone_entrance);
        }
        this.g = i;
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }
}
