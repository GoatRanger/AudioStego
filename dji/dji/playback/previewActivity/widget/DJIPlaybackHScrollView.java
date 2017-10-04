package dji.playback.previewActivity.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import dji.gs.e.b;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.playback.previewActivity.DJIPlaybackPreviewActivity;

public class DJIPlaybackHScrollView extends HorizontalScrollView {
    private Context a;
    private int b;
    private int c;
    private LinearLayout d;
    private int e = 0;
    private boolean f = false;
    private a g;

    public interface a {
        void a(int i);

        void a(int i, int i2, int i3, int i4);

        void b(int i);

        void c(int i);
    }

    public DJIPlaybackHScrollView(Context context) {
        super(context);
        a(context);
    }

    public DJIPlaybackHScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public DJIPlaybackHScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    @TargetApi(21)
    public DJIPlaybackHScrollView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context);
    }

    private void a(Context context) {
        this.a = context;
        this.b = dji.publics.e.a.b(context, 40.0f);
        this.c = (int) ((((float) this.b) * 9.0f) / b.a);
        this.e = 0;
    }

    public void init(LinearLayout linearLayout, int i, String str, a aVar) {
        int i2 = 0;
        if (!this.f) {
            int i3;
            this.f = true;
            DJILogHelper.getInstance().LOGI("bob", "duration = " + i);
            if (i > 1000) {
                i3 = i / 1000;
            } else {
                i3 = 1;
            }
            float f = 1.0f;
            if (i3 > 30) {
                f = 1.0f + ((float) ((i3 - 30) / 30));
            }
            DJILogHelper.getInstance().LOGI("bob", "step = " + f + "  duration=" + i3);
            this.d = linearLayout;
            View view = new View(this.a);
            view.setBackgroundColor(getResources().getColor(R.color.a_));
            view.setLayoutParams(new LayoutParams(DJIPlaybackPreviewActivity.a / 2, this.b));
            this.d.addView(view);
            int i4 = 0;
            while (i4 < i3) {
                ImageView imageView = new ImageView(this.a);
                ViewGroup.LayoutParams layoutParams = new LayoutParams(this.c, this.b);
                this.e += this.c;
                imageView.setLayoutParams(layoutParams);
                imageView.setScaleType(ScaleType.CENTER_CROP);
                this.d.addView(imageView);
                dji.playback.entryActivity.b.getInstance().a(str, imageView, i4);
                i4 = (int) (((float) (i2 + 1)) * f);
                i2++;
            }
            View view2 = new View(this.a);
            view2.setBackgroundColor(getResources().getColor(R.color.a_));
            view2.setLayoutParams(new LayoutParams(DJIPlaybackPreviewActivity.a / 2, this.b));
            this.d.addView(view2);
            this.g = aVar;
        }
    }

    public int getTotalWidth() {
        return this.e;
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.g != null) {
            this.g.a(i, i2, i3, i4);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                DJILogHelper.getInstance().LOGI("bob", "playback HorizonalSegmentView ACTION_DOWN");
                if (this.g != null) {
                    this.g.c(getScrollX());
                    break;
                }
                break;
            case 1:
                DJILogHelper.getInstance().LOGI("bob", "playback HorizonalSegmentView ACTION_UP");
                if (this.g != null) {
                    this.g.b(getScrollX());
                    break;
                }
                break;
            case 2:
                if (this.g != null) {
                    this.g.a(getScrollX());
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }
}
