package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import dji.apppublic.reflect.AppPublicReflect;
import dji.dbox.upgrade.p4.a.b;
import dji.midware.data.manager.P3.i;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewText;
import dji.thirdparty.a.c;

public class VersionPackageAircraftView extends ItemViewText {

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[b.values().length];

        static {
            try {
                a[b.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    public VersionPackageAircraftView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    private void a() {
        i.getInstance().c();
        CharSequence aircraftVersion = AppPublicReflect.getAircraftVersion();
        if (aircraftVersion == null) {
            this.d.setText(R.string.setting_ui_general_default_str);
        } else {
            this.d.setText(aircraftVersion);
        }
    }

    public void onEventBackgroundThread(b bVar) {
        switch (AnonymousClass1.a[bVar.ordinal()]) {
            case 1:
                a();
                return;
            default:
                return;
        }
    }
}
