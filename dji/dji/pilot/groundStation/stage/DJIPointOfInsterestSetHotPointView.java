package dji.pilot.groundStation.stage;

import android.content.Context;
import android.os.Handler;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.c;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.groundStation.a.b;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIPointOfInsterestSetHotPointView extends DJIRelativeLayout implements a {
    private DJIStageView a = null;
    private DJITextView b = null;
    private OnClickListener c = new OnClickListener(this) {
        final /* synthetic */ DJIPointOfInsterestSetHotPointView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.am5:
                    this.a.e = true;
                    ((DJIStageView) this.a.getParent()).destoryStageView(false);
                    dji.pilot.groundStation.a.a.getInstance(null).a(this.a);
                    return;
                case R.id.am6:
                    b instance = b.getInstance();
                    if (((double) instance.c()) > 5.0d) {
                        double b = instance.b();
                        double a = instance.a();
                        dji.pilot.groundStation.a.a.getInstance(null).i().l().b(dji.gs.utils.a.a(new dji.gs.e.b(b, a)), 0.0d);
                        dji.pilot.groundStation.a.a.getInstance(null).a(b, a);
                        dji.pilot.groundStation.a.a.getInstance(null).b(0.0d);
                        if (this.a.a == null) {
                            this.a.a = (DJIStageView) this.a.getParent();
                        }
                        this.a.a.createStageView(R.layout.gs_point_of_insterest_start_view, 17, true);
                        return;
                    }
                    DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                    bVar.a = d.b;
                    bVar.f = c.a;
                    if (DJIGenSettingDataManager.getInstance().v() == 0) {
                        bVar.c = String.format(this.a.getContext().getString(R.string.gs_point_of_insterest_height_limits), new Object[]{Float.valueOf(dji.pilot.groundStation.b.a(5.0f)), "FT"});
                    } else {
                        bVar.c = String.format(this.a.getContext().getString(R.string.gs_point_of_insterest_height_limits), new Object[]{Double.valueOf(5.0d), "M"});
                    }
                    dji.thirdparty.a.c.a().e(bVar);
                    return;
                default:
                    return;
            }
        }
    };
    private final Handler d = new Handler();
    private boolean e = false;
    private final Runnable f = new Runnable(this) {
        final /* synthetic */ DJIPointOfInsterestSetHotPointView a;

        {
            this.a = r1;
        }

        public void run() {
            if (!this.a.e) {
                if (((double) DataOsdGetPushCommon.getInstance().getHeight()) * 0.1d > 5.0d) {
                    this.a.b.setTextColor(this.a.getContext().getResources().getColor(R.color.er));
                } else {
                    this.a.b.setTextColor(SupportMenu.CATEGORY_MASK);
                }
                if (DJIGenSettingDataManager.getInstance().v() == 0) {
                    this.a.b.setText(String.format("%.1fFT", new Object[]{Float.valueOf(dji.pilot.groundStation.b.a((float) r0))}));
                } else {
                    this.a.b.setText(String.format("%.1fM", new Object[]{Double.valueOf(r0)}));
                }
                this.a.d.postDelayed(this.a.f, 500);
            }
        }
    };

    public DJIPointOfInsterestSetHotPointView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
        this.e = true;
    }

    public void dispatchOnResume() {
        this.e = false;
        this.d.post(this.f);
    }

    public void dispatchOnPause() {
        this.e = true;
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            findViewById(R.id.am5).setOnClickListener(this.c);
            findViewById(R.id.am6).setOnClickListener(this.c);
            this.b = (DJITextView) findViewById(R.id.am9);
            DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
            if (DJIGenSettingDataManager.getInstance().v() == 0) {
                this.b.setText(String.format("%.1fFT", new Object[]{Float.valueOf(dji.pilot.groundStation.b.a((float) (((double) instance.getHeight()) * 0.1d)))}));
                ((DJITextView) findViewById(R.id.am_)).setText(String.format(getContext().getString(R.string.gs_point_of_insterest_height_limits), new Object[]{Float.valueOf(dji.pilot.groundStation.b.a(5.0f)), "FT"}));
                return;
            }
            this.b.setText(String.format("%.1fM", new Object[]{Double.valueOf(((double) instance.getHeight()) * 0.1d)}));
            ((DJITextView) findViewById(R.id.am_)).setText(String.format(getContext().getString(R.string.gs_point_of_insterest_height_limits), new Object[]{Double.valueOf(5.0d), "M"}));
        }
    }
}
