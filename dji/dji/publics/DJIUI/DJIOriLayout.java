package dji.publics.DJIUI;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import dji.frame.widget.R;

public class DJIOriLayout extends DJIRelativeLayout {
    private static boolean isAllowSetTypeByLayout = true;
    private static DJIDeviceType mdeviceType = DJIDeviceType.Phone;

    public enum DJIDeviceType {
        Phone,
        Pad,
        DJI5_5
    }

    public static void setDeviceType(DJIDeviceType dJIDeviceType) {
        mdeviceType = dJIDeviceType;
        isAllowSetTypeByLayout = false;
    }

    public static DJIDeviceType getDeviceType() {
        return mdeviceType;
    }

    public static void setOrientation(Activity activity, int i) {
        if (activity.getRequestedOrientation() != i) {
            activity.setRequestedOrientation(i);
        }
    }

    public static void setOrientationByDevice(Activity activity) {
        int i;
        if (mdeviceType == DJIDeviceType.Phone) {
            i = 7;
        } else {
            i = 6;
        }
        if (activity.getRequestedOrientation() != i) {
            activity.setRequestedOrientation(i);
        }
    }

    public DJIOriLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DJIDeviceType);
            int i = obtainStyledAttributes.getInt(R.styleable.DJIDeviceType_djiDeviceType, 0);
            obtainStyledAttributes.recycle();
            if (!isAllowSetTypeByLayout) {
                return;
            }
            if (i == 0) {
                mdeviceType = DJIDeviceType.Phone;
            } else {
                mdeviceType = DJIDeviceType.Pad;
            }
        }
    }
}
