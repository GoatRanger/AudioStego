package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.apppublic.reflect.AppPublicReflect;
import dji.pilot.setting.ui.R;
import dji.setting.ui.reflect.SettingUIReflect;
import dji.setting.ui.tab.TabInnerToolsViewer.a;
import dji.setting.ui.widget.ItemViewText;
import dji.thirdparty.a.c;

public class AppVersionView extends ItemViewText implements OnClickListener {
    private int a = 0;
    private long b = 0;

    public AppVersionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (AppPublicReflect.isFactoryMode().booleanValue()) {
            setOnClickListener(this);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private void a() {
        CharSequence appVersion = SettingUIReflect.getAppVersion();
        if (appVersion == null) {
            this.d.setText(R.string.setting_ui_general_default_str);
        } else {
            this.d.setText(appVersion);
        }
    }

    public void onClick(View view) {
        if (System.currentTimeMillis() - this.b > 1500) {
            this.a = 0;
        } else {
            this.a++;
        }
        this.b = System.currentTimeMillis();
        if (this.a > 8) {
            c.a().e(a.Open);
        }
    }
}
