package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import dji.pilot.dji_groundstation.controller.DataMgr.e;

public class WayPointDeleteView extends TextView {
    private static final String a = "WayPointDeleteView";

    public WayPointDeleteView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WayPointDeleteView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                e.getInstance().s();
            }
        });
        getPaint().setFlags(8);
    }
}
