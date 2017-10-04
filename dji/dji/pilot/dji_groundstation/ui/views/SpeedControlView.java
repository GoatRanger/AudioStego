package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.model.P3.DataFlycGetPushWayPointMissionInfo;
import dji.midware.data.model.P3.DataFlycHotPointMissionSwitch;
import dji.midware.data.model.P3.DataFlycHotPointMissionSwitch.HOTPOINTMISSIONSWITCH;
import dji.midware.data.model.P3.DataFlycHotPointResetParams;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo.ROTATION_DIR;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.c;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.dji_groundstation.controller.DataMgr.d;
import dji.pilot.dji_groundstation.ui.views.GSSpeedBar.a;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class SpeedControlView extends DJILinearLayout {
    private static final String a = "SpeedControlView";
    private DJITextView b = null;
    private GSSpeedBar c = null;
    private DJITextView d = null;
    private DJITextView e = null;
    private boolean f = true;
    private Handler g = new Handler(Looper.getMainLooper());

    public SpeedControlView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
        LayoutInflater.from(context).inflate(R.layout.view_speed_control, this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        a();
    }

    private void a(double d) {
        if (DJIGenSettingDataManager.getInstance().v() == 0) {
            this.b.setText(String.format("%.1fMPH", new Object[]{Float.valueOf(Math.abs(f.c((float) d)))}));
        } else if (DJIGenSettingDataManager.getInstance().v() == 2) {
            this.b.setText(String.format("%.1fKM/H", new Object[]{Float.valueOf(Math.abs(f.d((float) d)))}));
        } else {
            this.b.setText(String.format("%.1fM/S", new Object[]{Double.valueOf(Math.abs(d))}));
        }
    }

    private void b(double d) {
        if (d > 0.0d) {
            int i = (int) (360.0d / d);
            if (i < 3600) {
                int i2 = i / 60;
                int i3 = i % 60;
                CharSequence charSequence = "";
                if (i2 > 0) {
                    charSequence = String.format("%d'", new Object[]{Integer.valueOf(i2)});
                }
                if (i3 > 0) {
                    if (charSequence.isEmpty()) {
                        charSequence = String.format("%d''", new Object[]{Integer.valueOf(i3)});
                    } else {
                        charSequence = charSequence + String.format("%d''", new Object[]{Integer.valueOf(i3)});
                    }
                }
                this.d.setText(charSequence);
                return;
            }
            this.d.setText(">1h");
            return;
        }
        this.d.setText(">1h");
    }

    private void a() {
        this.b = (DJITextView) findViewById(R.id.gs_point_of_insterest_status_speed_label);
        this.c = (GSSpeedBar) findViewById(R.id.gs_point_of_insterest_status_speed_seekbar);
        this.d = (DJITextView) findViewById(R.id.gs_point_of_insterest_status_fly_time);
        this.e = (DJITextView) findViewById(R.id.gs_point_of_insterest_status_pause);
        this.c.setRange(-100, 100, false);
        this.c.setOnValueChanged(new a(this) {
            final /* synthetic */ SpeedControlView a;

            {
                this.a = r1;
            }

            public void a(View view, int i, boolean z) {
                if (z) {
                    d.getInstance().m();
                    d.getInstance().b((float) ((((double) i) * d.getInstance().q()) / 100.0d));
                    double k = (double) d.getInstance().k();
                    this.a.a(Math.abs((double) d.getInstance().l()));
                    this.a.b(Math.abs(k));
                    ROTATION_DIR rotation_dir = ROTATION_DIR.Clockwise;
                    if (k > 0.0d) {
                        rotation_dir = ROTATION_DIR.Anti_Clockwise;
                    }
                    DataFlycHotPointResetParams instance = DataFlycHotPointResetParams.getInstance();
                    instance.setRotationDir(rotation_dir);
                    instance.setVelocity((float) Math.abs(k));
                    instance.start(new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            int result = DataFlycHotPointResetParams.getInstance().getResult();
                            if (result != 0) {
                                dji.pilot.dji_groundstation.controller.d.getInstance().a(8, Integer.valueOf(c.a(result)));
                            }
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                        }
                    });
                }
            }
        });
        this.c.setValue((int) (((double) (d.getInstance().l() * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity)) / d.getInstance().q()));
        a((double) Math.abs(d.getInstance().l()));
        b((double) Math.abs(d.getInstance().k()));
        a(new Runnable(this) {
            final /* synthetic */ SpeedControlView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.b((double) Math.abs(d.getInstance().k()));
                this.a.b();
                this.a.g.postDelayed(this, 2000);
            }
        }, 2000);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SpeedControlView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                boolean o = d.getInstance().o();
                DataFlycHotPointMissionSwitch instance = DataFlycHotPointMissionSwitch.getInstance();
                if (o) {
                    instance.setSwitch(HOTPOINTMISSIONSWITCH.PAUSE);
                } else {
                    instance.setSwitch(HOTPOINTMISSIONSWITCH.RESUME);
                }
                instance.start(new dji.midware.e.d(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        d.getInstance().a(!d.getInstance().o());
                        this.a.a.a(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.a.e.setText(d.getInstance().o() ? R.string.gsnew_gs_pause : R.string.gsnew_gs_resume);
                            }
                        });
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                    }
                });
            }
        });
        b();
    }

    private void b() {
        if (DataFlycGetPushWayPointMissionInfo.getInstance().getHotPointMissionStatus() == 2) {
            d.getInstance().a(false);
        } else {
            d.getInstance().a(true);
        }
        this.e.setText(d.getInstance().o() ? R.string.gsnew_gs_pause : R.string.gsnew_gs_resume);
    }

    private void a(Runnable runnable) {
        if (this.g != null) {
            this.g.post(runnable);
        }
    }

    private void a(Runnable runnable, int i) {
        if (this.g != null) {
            this.g.postDelayed(runnable, (long) i);
        }
    }
}
