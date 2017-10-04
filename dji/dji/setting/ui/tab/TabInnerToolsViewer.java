package dji.setting.ui.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import dji.apppublic.reflect.AppPublicReflect;
import dji.setting.ui.reflect.SettingUIReflect;
import dji.thirdparty.a.c;

public class TabInnerToolsViewer extends ImageView {

    public enum a {
        Open
    }

    public TabInnerToolsViewer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (AppPublicReflect.isInnerToolsApk().booleanValue() && SettingUIReflect.isSDKToolsExist()) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    private void a() {
    }

    public void onEventMainThread(a aVar) {
        if (aVar == a.Open && AppPublicReflect.isFactoryMode().booleanValue()) {
            setVisibility(0);
        }
    }
}
