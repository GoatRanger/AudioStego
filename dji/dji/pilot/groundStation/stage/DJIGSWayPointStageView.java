package dji.pilot.groundStation.stage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.dji.frame.c.l;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.fpv.view.DJIErrorPopView.c;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.fpv.view.DJIErrorPopView.f;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.groundStation.db.DJIWPCollectionItem;
import dji.pilot.groundStation.db.DJIWPCollectionItem$WayPoint;
import dji.publics.DJIUI.DJIRelativeLayout;

public class DJIGSWayPointStageView extends DJIRelativeLayout implements a {
    private DJIStageView a = null;
    private OnClickListener b = new OnClickListener(this) {
        final /* synthetic */ DJIGSWayPointStageView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.aos:
                    ((DJIStageView) this.a.getParent()).destoryStageView(false);
                    dji.pilot.groundStation.a.a.getInstance(null).a(this.a);
                    return;
                case R.id.aot:
                    if (dji.pilot.groundStation.a.a.getInstance(this.a.getContext()).h().size() > 0) {
                        if (this.a.a == null) {
                            this.a.a = (DJIStageView) this.a.getParent();
                        }
                        this.a.a.createStageView(R.layout.gs_way_point_collection_view, 7, true);
                        return;
                    }
                    b bVar = new b();
                    bVar.a = d.a;
                    bVar.f = c.a;
                    bVar.g = f.a;
                    bVar.b = R.string.gs_way_point_collection_is_empty;
                    dji.thirdparty.a.c.a().e(bVar);
                    return;
                case R.id.aou:
                    dji.pilot.groundStation.a.a.getInstance(null).i().l().g();
                    dji.pilot.groundStation.a.a.getInstance(null).M();
                    if (this.a.a == null) {
                        this.a.a = (DJIStageView) this.a.getParent();
                    }
                    this.a.a.createStageView(R.layout.gs_way_point_add_point_small_view, 23, true);
                    return;
                default:
                    return;
            }
        }
    };

    public DJIGSWayPointStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).invalidate();
        }
        if (dji.pilot.groundStation.b.a(getContext())) {
            for (final DJIWPCollectionItem dJIWPCollectionItem : dji.pilot.groundStation.a.a.getInstance(getContext()).h()) {
                if (l.a(dJIWPCollectionItem.getLocation()) && dJIWPCollectionItem.getPoints().size() > 0) {
                    DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint = (DJIWPCollectionItem$WayPoint) dJIWPCollectionItem.getPoints().get(0);
                    dji.pilot.groundStation.b.a(getContext(), dJIWPCollectionItem$WayPoint.getLat(), dJIWPCollectionItem$WayPoint.getLng(), new dji.pilot.groundStation.c(this) {
                        final /* synthetic */ DJIGSWayPointStageView b;

                        public void a(String str) {
                            dJIWPCollectionItem.setLocation(str);
                            dji.pilot.groundStation.a.a.getInstance(this.b.getContext()).b(dJIWPCollectionItem);
                        }
                    });
                }
            }
        }
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            findViewById(R.id.aos).setOnClickListener(this.b);
            findViewById(R.id.aou).setOnClickListener(this.b);
            findViewById(R.id.aot).setOnClickListener(this.b);
        }
    }
}
