package dji.pilot.set.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import dji.midware.media.j.g;
import dji.pilot.set.R;
import dji.pilot.set.e;
import dji.pilot.set.view.base.SetButtonView;
import dji.pilot2.media.activity.DJIPhotoPreveiwActivity;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FirmwareUpgradeSetterView extends SetButtonView {
    public FirmwareUpgradeSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onClick(View view) {
        e.a(getContext(), R.string.firmware_upgrade_notification, new OnClickListener(this) {
            final /* synthetic */ FirmwareUpgradeSetterView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                g.e();
                this.a.d();
                dialogInterface.dismiss();
                e.c(this.a.getContext(), R.string.firmware_upgrade_success_notification);
            }
        });
    }

    protected int getTitleId() {
        return R.string.firmware_upgrade;
    }

    protected int getButtonStringId() {
        return R.string.firmware_upgrade_start;
    }

    private void d() {
        try {
            try {
                Method method = Class.forName("dji.pilot.support.longan.DJISupportLongan").getMethod("cleanCache", new Class[0]);
                Log.d(DJIPhotoPreveiwActivity.N, "find class");
                try {
                    method.invoke(null, new Object[0]);
                    Log.d(DJIPhotoPreveiwActivity.N, "invoked refresh");
                } catch (IllegalAccessException e) {
                    Log.d(DJIPhotoPreveiwActivity.N, "IllegalAccessException");
                    e.printStackTrace();
                } catch (IllegalArgumentException e2) {
                    Log.d(DJIPhotoPreveiwActivity.N, "IllegalArgumentException");
                    e2.printStackTrace();
                } catch (InvocationTargetException e3) {
                    Log.d(DJIPhotoPreveiwActivity.N, "InvocationTargetException");
                    e3.printStackTrace();
                }
            } catch (NoSuchMethodException e4) {
                Log.d(DJIPhotoPreveiwActivity.N, "NoSuchMethodException");
                e4.printStackTrace();
            }
        } catch (ClassNotFoundException e5) {
            Log.d(DJIPhotoPreveiwActivity.N, "ClassNotFoundException");
            e5.printStackTrace();
        }
    }
}
