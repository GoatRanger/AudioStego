package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycWayPointSetIdleSpeed;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.d.c;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.dji_groundstation.controller.DataMgr.e;
import dji.pilot.dji_groundstation.controller.d;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class WayPointSpeedControlView extends DJILinearLayout {
    private static final String a = "WayPointSpeedControlView";
    private SeekBar b = null;
    private DJITextView c = null;

    public WayPointSpeedControlView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(0);
        LayoutInflater.from(context).inflate(R.layout.view_waypoint_speedcontrol, this);
    }

    protected void onFinishInflate() {
        this.b = (SeekBar) findViewById(R.id.gs_way_point_auto_fly_setting_speed_seekbar);
        this.c = (DJITextView) findViewById(R.id.gs_way_point_auto_fly_setting_speed_value);
        a();
    }

    private void a() {
        if (this.b != null) {
            this.b.setThumbOffset(0);
            this.b.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
                final /* synthetic */ WayPointSpeedControlView a;

                {
                    this.a = r1;
                }

                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    final float f = ((float) i) / 10.0f;
                    if (DJIGenSettingDataManager.getInstance().v() == 0) {
                        this.a.c.setText(String.format("%.1fMPH", new Object[]{Float.valueOf(f.c(f))}));
                    } else if (DJIGenSettingDataManager.getInstance().v() == 2) {
                        this.a.c.setText(String.format("%.1fKM/H", new Object[]{Float.valueOf(f.d(f))}));
                    } else {
                        this.a.c.setText(String.format("%.1fM/S", new Object[]{Float.valueOf(f)}));
                    }
                    e.getInstance().a(f);
                    if (d.getInstance().b().a() == c.g.a()) {
                        DataFlycWayPointSetIdleSpeed instance = DataFlycWayPointSetIdleSpeed.getInstance();
                        instance.a(f);
                        instance.start(new dji.midware.e.d(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void onSuccess(Object obj) {
                                e.getInstance().a(f);
                            }

                            public void onFailure(a aVar) {
                            }
                        });
                    }
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
            this.b.setProgress((int) ((e.getInstance().o() / 10.0f) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity));
        }
    }
}
