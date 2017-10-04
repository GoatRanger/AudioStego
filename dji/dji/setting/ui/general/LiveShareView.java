package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.setting.ui.widget.ItemViewGroup;

public class LiveShareView extends ItemViewGroup {
    public LiveShareView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            ProductType c = i.getInstance().c();
            if (c == ProductType.litchiC || c == ProductType.P34K || CameraType.DJICameraTypeGD600 == DataCameraGetPushStateInfo.getInstance().getCameraType()) {
                setVisibility(8);
            } else {
                setVisibility(0);
            }
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
