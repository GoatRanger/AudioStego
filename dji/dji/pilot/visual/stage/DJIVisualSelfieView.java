package dji.pilot.visual.stage;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataSingleVisualParam;
import dji.midware.data.model.P3.DataSingleVisualParam.ParamCmdId;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.publics.widget.DJISwitch;
import dji.pilot.usercenter.mode.n;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.Iterator;

public class DJIVisualSelfieView extends DJIRelativeLayout implements Listener {
    private DJISwitch a = null;
    private DJIImageView b = null;
    private DJITextView c = null;
    private DJIImageView d = null;
    private Handler e = new Handler(Looper.getMainLooper());
    private int f = 0;
    private LocationManager g = null;
    private volatile boolean h = false;

    public DJIVisualSelfieView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = (LocationManager) context.getSystemService(n.C);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        getContext();
        this.a = (DJISwitch) findViewById(R.id.d_l);
        this.b = (DJIImageView) findViewById(R.id.d_m);
        this.c = (DJITextView) findViewById(R.id.d_n);
        this.d = (DJIImageView) findViewById(R.id.d_j);
        this.a.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ DJIVisualSelfieView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                boolean z2 = false;
                if (this.a.h != z) {
                    DataSingleVisualParam a = new DataSingleVisualParam().a(false).a(ParamCmdId.i);
                    if (!this.a.h) {
                        z2 = true;
                    }
                    a.d(z2).start(new d(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            this.a.a.h = !this.a.a.h;
                            this.a.a.a(this.a.a.h);
                        }

                        public void onFailure(a aVar) {
                            this.a.a.a(this.a.a.h);
                        }
                    });
                }
            }
        });
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIVisualSelfieView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                new dji.pilot.visual.util.a(this.a.getContext()).show();
            }
        });
    }

    public void setHideClickListener(OnClickListener onClickListener) {
        ((DJITextView) findViewById(R.id.d_i)).setOnClickListener(onClickListener);
    }

    public void onDetachedFromWindow() {
        if (this.g != null) {
            this.g.removeGpsStatusListener(this);
            this.g = null;
        }
        super.onDetachedFromWindow();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (this.g != null) {
                this.g.addGpsStatusListener(this);
            }
            final DataSingleVisualParam a = new DataSingleVisualParam().a(true).a(ParamCmdId.i);
            a.start(new d(this) {
                final /* synthetic */ DJIVisualSelfieView b;

                public void onSuccess(Object obj) {
                    this.b.h = a.h();
                    this.b.a(true);
                }

                public void onFailure(a aVar) {
                }
            });
        }
    }

    private void a(final boolean z) {
        this.e.post(new Runnable(this) {
            final /* synthetic */ DJIVisualSelfieView b;

            public void run() {
                this.b.a.setChecked(z);
            }
        });
    }

    public void onGpsStatusChanged(int i) {
        if (this.g != null && i == 4) {
            GpsStatus gpsStatus = this.g.getGpsStatus(null);
            int maxSatellites = gpsStatus.getMaxSatellites();
            Iterator it = gpsStatus.getSatellites().iterator();
            while (it.hasNext() && this.f <= maxSatellites) {
                if (((GpsSatellite) it.next()).usedInFix()) {
                    this.f++;
                }
            }
            if (this.b != null) {
                this.e.post(new Runnable(this) {
                    final /* synthetic */ DJIVisualSelfieView a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        int i;
                        int i2;
                        if (this.a.f < 1) {
                            i = R.drawable.visual_gps_signal_invalid;
                            i2 = R.string.visual_track_gps_signal_invalid;
                        } else if (this.a.f <= 5 && this.a.f > 1) {
                            i = R.drawable.visual_gps_signal_low;
                            i2 = R.string.visual_track_gps_signal_low;
                        } else if (this.a.f > 10 || this.a.f <= 5) {
                            i = R.drawable.visual_gps_signal_good;
                            i2 = R.string.visual_track_gps_signal_good;
                        } else {
                            i = R.drawable.visual_gps_signal_medium;
                            i2 = R.string.visual_track_gps_signal_medium;
                        }
                        this.a.b.setImageResource(i);
                        this.a.c.setText(i2);
                    }
                });
            }
        }
    }
}
