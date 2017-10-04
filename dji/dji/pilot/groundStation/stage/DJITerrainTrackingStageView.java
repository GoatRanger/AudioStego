package dji.pilot.groundStation.stage;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.model.P3.DataFlycStartNoeMission;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.d.c$i;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.groundStation.b.f;
import dji.publics.DJIUI.DJIRelativeLayout;

public class DJITerrainTrackingStageView extends DJIRelativeLayout implements c$i, a {
    private View n = null;
    private final Handler o = new Handler();
    private OnClickListener p = new OnClickListener(this) {
        final /* synthetic */ DJITerrainTrackingStageView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ang:
                    ((DJIStageView) this.a.getParent()).destoryStageView(false);
                    dji.pilot.groundStation.a.a.getInstance(null).a(this.a);
                    return;
                case R.id.anh:
                    dji.pilot.groundStation.a.a.getInstance(null).a();
                    dji.pilot.groundStation.a.a.getInstance(null).k().hide();
                    f fVar = new f(this.a.getContext());
                    fVar.a((int) R.drawable.gs_terrain_tracking_help_image);
                    fVar.a(this.a.getResources().getString(R.string.gs_terrain_tracking_help_title));
                    fVar.b(this.a.getResources().getString(R.string.gs_terrain_tracking_help_desc));
                    fVar.b();
                    fVar.c((int) R.string.gs_terrain_tracking_help_i_know);
                    fVar.b(new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            this.a.a.o.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    dji.pilot.groundStation.a.a.getInstance(null).k().show();
                                }
                            });
                        }
                    });
                    fVar.show();
                    return;
                case R.id.ani:
                    final dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
                    this.a.n.setEnabled(false);
                    instance.b(new d(this) {
                        final /* synthetic */ AnonymousClass2 b;

                        public void onSuccess(Object obj) {
                            this.b.a.o.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass2 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    e.c(c$i.f);
                                    instance.a(dji.pilot.groundStation.a.a.d.TERRAIN_TRACKING);
                                    ((DJIStageView) this.a.b.a.getParent()).createStageView(R.layout.gs_terrain_tracking_status_view, 32, true);
                                }
                            });
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.a();
                            if (DataFlycStartNoeMission.getInstance().a() == 247) {
                                instance.a((int) R.string.gs_terrain_lock_send_command_fail, (int) R.string.gs_terrain_tracking_start_error_no_relative_height, true);
                            }
                            if (DataFlycStartNoeMission.getInstance().a() == 248) {
                                instance.a((int) R.string.gs_terrain_lock_send_command_fail, (int) R.string.gs_terrain_tracking_start_error_no_horizontal_velocity, true);
                            }
                        }
                    });
                    return;
                default:
                    return;
            }
        }
    };

    public DJITerrainTrackingStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
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

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            findViewById(R.id.ang).setOnClickListener(this.p);
            findViewById(R.id.anh).setOnClickListener(this.p);
            this.n = findViewById(R.id.ani);
            this.n.setOnClickListener(this.p);
        }
    }

    private void a() {
        this.o.post(new Runnable(this) {
            final /* synthetic */ DJITerrainTrackingStageView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.n.setEnabled(true);
            }
        });
    }
}
