package dji.setting.ui.general;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewButtonBig;
import dji.setting.ui.widget.a;

public class ClearRouteView extends ItemViewButtonBig {
    public ClearRouteView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onClick(View view) {
        a.b(getContext(), R.string.setting_ui_general_clear_route_tip, new OnClickListener(this) {
            final /* synthetic */ ClearRouteView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                DJIGenSettingDataManager.getInstance().u();
            }
        });
    }
}
