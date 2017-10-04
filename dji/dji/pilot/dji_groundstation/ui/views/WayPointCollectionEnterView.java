package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import dji.pilot.dji_groundstation.controller.e;

public class WayPointCollectionEnterView extends TextView {
    private static final String a = "WayPointCollectionEnterView";

    public WayPointCollectionEnterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getPaint().setFlags(8);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WayPointCollectionEnterView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                e.a("gs://smartmode/waypoint/collection", this.a.getContext());
            }
        });
    }
}
