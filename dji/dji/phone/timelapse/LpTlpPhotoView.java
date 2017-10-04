package dji.phone.timelapse;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.Toast;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.pilot.fpv.R;

public class LpTlpPhotoView extends RelativeLayout implements OnClickListener {
    LpTimelapseImageView a;
    DJIStateImageViewCompat b;
    b c;
    int d;
    float e;

    public interface b {
        void onDeleted(View view, int i);
    }

    public enum a {
        FIRST,
        MIDDLE,
        LAST
    }

    public LpTlpPhotoView(Context context, a aVar, int i) {
        super(context);
        this.d = i;
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.longan_timelapse_photo_view, this, true);
        this.a = new LpTimelapseImageView(context, aVar, i);
        this.a.setId(R.id.timelapse_position_iv);
        this.a.setScaleType(ScaleType.CENTER);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        addView(this.a, layoutParams);
    }

    private void a() {
        View imageView = new ImageView(getContext());
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_timelapse_cancel, null));
        imageView.setScaleType(ScaleType.CENTER);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(7, this.a.getId());
        layoutParams.addRule(6, this.a.getId());
        layoutParams.rightMargin = getResources().getDimensionPixelOffset(R.dimen.dp_2_in_sw320dp);
        layoutParams.topMargin = getResources().getDimensionPixelOffset(R.dimen.dp_5_in_sw320dp);
        addView(imageView, layoutParams);
        imageView.setOnClickListener(this);
    }

    public void onClick(View view) {
        this.c.onDeleted(this, this.d);
    }

    public void setDeleteListener(b bVar) {
        this.c = bVar;
    }

    private LpTlpPhotoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void hideCancleBtn() {
        this.b.hide();
    }

    public void showCancleBtn() {
        this.b.show();
    }

    public LpTimelapseImageView getImageView() {
        return this.a;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void setPhoto(Drawable drawable) {
        this.a.setImageDrawable(drawable);
        a();
    }

    public void setPosition(a aVar) {
        this.a.setLayoutPosition(aVar);
    }

    public void setIndex(int i) {
        this.d = i;
        this.a.setIndex(i);
    }

    public a getPosition() {
        return this.a.getLayoutPosition();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.e = motionEvent.getX();
        } else if (motionEvent.getAction() == 2 && motionEvent.getX() - this.e < DJIFlightControllerDataType.DJIVirtualStickYawControlMinAngularVelocity) {
            Toast.makeText(getContext(), "Heihei" + this.d, 0).show();
        }
        return true;
    }
}
