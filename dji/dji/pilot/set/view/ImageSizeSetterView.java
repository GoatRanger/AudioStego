package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.model.P3.DataCameraGetImageSize.RatioType;
import dji.midware.data.model.P3.DataCameraGetImageSize.SizeType;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraSetImageSize;
import dji.pilot.set.R;
import dji.pilot.set.view.base.SetGroupButtonView;

public class ImageSizeSetterView extends SetGroupButtonView {
    public ImageSizeSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected int getTitleId() {
        return R.string.set_photo_ratio;
    }

    protected void a() {
        this.b = DataCameraGetPushShotParams.getInstance().getImageRatio().value();
        setSelect(this.b);
    }

    public void setValue(int i) {
        DataCameraSetImageSize.getInstance().a().a(SizeType.DEFAULT).a(RatioType.find(i)).start(null);
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        a();
    }

    protected int getLeftBtnStrId() {
        return R.string.set_imagine_ratio_4x3;
    }

    protected int getRightBtnStrId() {
        return R.string.set_imagine_ratio_16x9;
    }
}
