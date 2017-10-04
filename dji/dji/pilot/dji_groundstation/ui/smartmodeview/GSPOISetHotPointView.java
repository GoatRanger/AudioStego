package dji.pilot.dji_groundstation.ui.smartmodeview;

import android.content.Context;
import android.view.LayoutInflater;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.ui.views.CurHeightView;
import dji.publics.DJIUI.DJILinearLayout;

public class GSPOISetHotPointView extends DJILinearLayout {
    private static final String a = "GSPOISetHotPointView";
    private CurHeightView b = null;

    public GSPOISetHotPointView(Context context) {
        super(context);
        LayoutInflater.from(getContext()).inflate(R.layout.view_poi_sethotpoint, this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.b = (CurHeightView) findViewById(R.id.gs_point_of_insterest_cur_height);
    }
}
