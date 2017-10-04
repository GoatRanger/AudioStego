package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycWayPointMissionPauseOrResume;
import dji.midware.data.model.P3.DataFlycWayPointMissionPauseOrResume.CMD;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.controller.DataMgr.e;

public class WayPointPauseView extends TextView {
    private static final String a = "WayPointPauseView";
    private Handler b = new Handler(Looper.getMainLooper());

    public WayPointPauseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WayPointPauseView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (e.getInstance().i()) {
                    DataFlycWayPointMissionPauseOrResume.getInstance().setCMD(CMD.RESUME);
                } else {
                    DataFlycWayPointMissionPauseOrResume.getInstance().setCMD(CMD.PAUSE);
                }
                DataFlycWayPointMissionPauseOrResume.getInstance().start(new d(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.a.b();
                    }

                    public void onFailure(a aVar) {
                        this.a.a.b();
                    }
                });
            }
        });
    }

    private void b() {
        a(new Runnable(this) {
            final /* synthetic */ WayPointPauseView a;

            {
                this.a = r1;
            }

            public void run() {
                if (e.getInstance().i()) {
                    e.getInstance().a(false);
                    this.a.setText(R.string.gsnew_gs_pause);
                    return;
                }
                e.getInstance().a(true);
                this.a.setText(R.string.gsnew_gs_resume);
            }
        });
    }

    private void a(Runnable runnable) {
        if (this.b != null) {
            this.b.post(runnable);
        }
    }
}
