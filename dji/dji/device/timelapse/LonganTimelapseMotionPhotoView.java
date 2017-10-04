package dji.device.timelapse;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import dji.device.common.DJIUIEventManagerLongan.k;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class LonganTimelapseMotionPhotoView extends RelativeLayout {
    LonganTimelapseImageView a;
    DJIStateImageViewCompat b;

    public enum a {
        FIRST,
        MIDDLE,
        LAST
    }

    public LonganTimelapseMotionPhotoView(Context context, a aVar, int i) {
        super(context);
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.longan_timelapse_photo_view, this, true);
        this.a = new LonganTimelapseImageView(context, aVar, i);
        addView(this.a);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(12);
        layoutParams.addRule(11);
        layoutParams.setMargins(10, 10, 10, 10);
        this.b = new DJIStateImageViewCompat(getContext());
        this.b.setImageResource(R.drawable.longan_timelapse_cancle);
        this.b.setLayoutParams(layoutParams);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LonganTimelapseMotionPhotoView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                c.a().e(k.DELETE_ONE_POINT);
            }
        });
        addView(this.b);
    }

    private LonganTimelapseMotionPhotoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void hideCancleBtn() {
        this.b.hide();
    }

    public void showCancleBtn() {
        this.b.show();
    }

    public LonganTimelapseImageView getImageView() {
        return this.a;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void setPhoto(Drawable drawable) {
        this.a.setBackground(drawable);
    }
}
