package dji.setting.ui.gimbal;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.e;
import dji.midware.data.model.P3.DataGimbalSetUserParams;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.ItemViewSpinner;
import dji.thirdparty.a.c;

public class AdvConfigView extends ItemViewSpinner {
    private final String a = "table_choice";
    private final int[] b = new int[]{R.string.setting_ui_gimbal_config1, R.string.setting_ui_gimbal_config2, R.string.setting_ui_gimbal_config3};

    public AdvConfigView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            a();
            a.a();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(a aVar) {
        a();
    }

    private void a() {
        this.f.setData(this.b, e.read("table_choice").value.intValue(), (b) this);
    }

    public void onItemClick(int i) {
        if (i != e.read("table_choice").value.intValue()) {
            DataGimbalSetUserParams.getInstance().a("table_choice", Integer.valueOf(i)).a(250).start(new d(this) {
                final /* synthetic */ AdvConfigView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    a.c();
                    a.a();
                }

                public void onFailure(a aVar) {
                    a.c();
                    a.a();
                }
            });
        }
    }
}
