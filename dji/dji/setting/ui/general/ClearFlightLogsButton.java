package dji.setting.ui.general;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.dji.frame.c.d;
import dji.log.ErrorPopLogHelper;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewButtonBig;
import dji.setting.ui.widget.a;
import java.io.File;

public class ClearFlightLogsButton extends ItemViewButtonBig {
    public ClearFlightLogsButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onClick(View view) {
        a.a(getContext(), R.string.setting_ui_general_clear_flight_logs_tip, new OnClickListener(this) {
            final /* synthetic */ ClearFlightLogsButton a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.a();
            }
        }, new OnClickListener(this) {
            final /* synthetic */ ClearFlightLogsButton a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
    }

    private void a() {
        File file = new File(d.a(getContext(), ErrorPopLogHelper.POP_LOGS_RELATIVE_PATH));
        if (file.exists() && file.isDirectory()) {
            for (File delete : file.listFiles()) {
                delete.delete();
            }
        }
        Log.i(getClass().getSimpleName(), "delete success: " + file.delete());
    }
}
