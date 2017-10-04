package dji.pilot.groundStation.stage;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.d.c$i;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.groundStation.b.f;
import dji.publics.DJIUI.DJIRelativeLayout;

public class DJITerrainTrackingStatusStageView extends DJIRelativeLayout implements c$i, a {
    private OnClickListener n = new OnClickListener(this) {
        final /* synthetic */ DJITerrainTrackingStatusStageView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.anb:
                    dji.pilot.groundStation.a.a.getInstance(null).a();
                    dji.pilot.groundStation.a.a.getInstance(null).k().hide();
                    f fVar = new f(this.a.getContext());
                    fVar.a((int) R.drawable.gs_terrain_tracking_help_image);
                    fVar.a(this.a.getResources().getString(R.string.gs_terrain_tracking_help_title));
                    fVar.b(this.a.getResources().getString(R.string.gs_terrain_tracking_help_desc));
                    fVar.b();
                    fVar.c((int) R.string.gs_terrain_tracking_help_i_know);
                    fVar.b(new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            dji.pilot.groundStation.a.a.getInstance(null).k().show();
                        }
                    });
                    fVar.show();
                    return;
                case R.id.anc:
                    ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_exit_current_mession_view, 26, false);
                    return;
                case R.id.and:
                    dji.pilot.groundStation.a.a.getInstance(null).a();
                    ((DJIStageView) this.a.getParent()).stop();
                    return;
                default:
                    return;
            }
        }
    };

    public DJITerrainTrackingStatusStageView(Context context, AttributeSet attributeSet) {
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
            findViewById(R.id.and).setOnClickListener(this.n);
            findViewById(R.id.anc).setOnClickListener(this.n);
            findViewById(R.id.anb).setOnClickListener(this.n);
        }
    }
}
