package dji.pilot.fpv.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.dji.frame.c.l;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataSmartBatteryGetPushReArrangement;
import dji.midware.data.model.P3.DataSmartBatteryGetPushReArrangement.ReArrangement;
import dji.midware.data.model.P3.DataSmartBatteryGetPushReArrangement.ReArrangementOption;
import dji.pilot.R;
import dji.pilot.fpv.model.b;
import dji.pilot.publics.objects.c;
import dji.publics.DJIUI.DJITextView;

public class j extends c implements OnClickListener {
    private DJITextView a = null;
    private OnClickListener b = null;

    public enum a {
        open
    }

    public j(Context context) {
        super(context);
        setContentView(R.layout.fpv_switch_battery_pos_dialog);
        findViewById(R.id.abl).setOnClickListener(this);
        this.a = (DJITextView) findViewById(R.id.abk);
    }

    protected void onCreate(Bundle bundle) {
        a(b.a(this.N, R.dimen.ge), b.a(this.N, R.dimen.g9), 0, 17, true, true);
    }

    protected void onStart() {
        super.onStart();
        onEventMainThread(DataSmartBatteryGetPushReArrangement.getInstance());
        dji.thirdparty.a.c.a().a(this);
    }

    protected void onStop() {
        dji.thirdparty.a.c.a().d(this);
        super.onStop();
    }

    public void a(OnClickListener onClickListener) {
        this.b = onClickListener;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.abl && this.b != null) {
            this.b.onClick(view);
        }
    }

    public void onEventMainThread(DataSmartBatteryGetPushReArrangement dataSmartBatteryGetPushReArrangement) {
        if (dataSmartBatteryGetPushReArrangement.isGetted()) {
            String string = getContext().getString(R.string.fpv_switch_battery_pos_switch);
            String str = "";
            for (ReArrangement reArrangement : dataSmartBatteryGetPushReArrangement.getReArrangement()) {
                DJILogHelper.getInstance().LOGD("", "rearra.option=" + reArrangement.option + " si=" + reArrangement.srcIndex + " di=" + reArrangement.dstIndex, false, true);
                if (reArrangement.option == ReArrangementOption.Switch) {
                    str = str + String.format(string, new Object[]{Integer.valueOf(reArrangement.srcIndex), Integer.valueOf(reArrangement.dstIndex)});
                }
            }
            if (!l.a(str)) {
                this.a.setText(String.format(getContext().getString(R.string.fpv_switch_battery_pos_desc), new Object[]{str}));
                return;
            }
            return;
        }
        DJILogHelper.getInstance().LOGD("", "DataSmartBatteryGetPushReArrangement.isGetted()=false", false, true);
    }
}
