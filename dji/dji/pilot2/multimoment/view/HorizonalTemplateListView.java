package dji.pilot2.multimoment.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import com.meetme.android.horizontallistview.HorizontalListView;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.visual.a.d;
import dji.pilot2.utils.p;

public class HorizonalTemplateListView extends HorizontalListView {
    private Rect e = new Rect();
    private a f;
    private boolean g = false;
    private Point h = new Point();
    private ScaleAnimation i = new ScaleAnimation(1.0f, 0.95f, 1.0f, 0.95f, 1, d.c, 1, d.c);
    private ScaleAnimation j = new ScaleAnimation(0.95f, 1.0f, 0.95f, 1.0f, 1, d.c, 1, d.c);

    public interface a {
        void a(View view);
    }

    private static class b implements AnimationListener {
        private b() {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public HorizonalTemplateListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    @SuppressLint({"WrongCall"})
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    private int a(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        switch (mode) {
            case Integer.MIN_VALUE:
            case 1073741824:
                return size;
            case 0:
                DJILogHelper.getInstance().LOGI("wbwb", "UNSPECIFIED=");
                break;
        }
        return 0;
    }

    private int a(int i, int i2) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            getChildAt(i3).getHitRect(this.e);
            if (this.e.contains(i, i2)) {
                return i3;
            }
        }
        return -1;
    }

    public void setListener(a aVar) {
        this.f = aVar;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        DJILogHelper.getInstance().LOGI("WB", "wb onTouchEvent", false, true);
        if (motionEvent.getAction() == 1) {
            DJILogHelper.getInstance().LOGI("WB", "wb ACTION_UP", false, true);
            int a = a((int) motionEvent.getX(), (int) motionEvent.getY());
            if (this.g) {
                DJILogHelper.getInstance().LOGI("WB", "wb ACTION_UPx= " + motionEvent.getX() + "y= " + motionEvent.getY(), false, true);
                DJILogHelper.getInstance().LOGI("WB", "wb mIsMoved" + Math.abs(((float) this.h.x) - motionEvent.getX()) + "  " + Math.abs(((float) this.h.y) - motionEvent.getY()), false, true);
                if (Math.abs(((float) this.h.x) - motionEvent.getX()) <= 30.0f && Math.abs(((float) this.h.y) - motionEvent.getY()) <= 30.0f) {
                    this.g = false;
                }
            }
            if (!(a < 0 || this.g || this.f == null)) {
                this.f.a(getChildAt(a));
                View childAt = getChildAt(a);
                if (childAt != null) {
                    this.i.setDuration(30);
                    childAt.startAnimation(this.i);
                }
            }
            this.g = false;
        } else if (motionEvent.getAction() == 2) {
            if (!this.g) {
                this.h.x = (int) motionEvent.getX();
                this.h.y = (int) motionEvent.getY();
            }
            DJILogHelper.getInstance().LOGI("WB", "wb ACTION_MOVEx= " + this.h.x + "y= " + this.h.y, false, true);
            this.g = true;
        } else if (motionEvent.getAction() == 0) {
            this.h.x = (int) motionEvent.getX();
            this.h.y = (int) motionEvent.getY();
            DJILogHelper.getInstance().LOGI("WB", "wb ACTION_DOWNx= " + this.h.x + "y= " + this.h.y, false, true);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void scrollToIndex(int i) {
        int childCount = getChildCount();
        int lastVisiblePosition = getLastVisiblePosition();
        int firstVisiblePosition = getFirstVisiblePosition();
        DJILogHelper.getInstance().LOGI("bob", "scrollToIndex index=" + i + " nCnt=" + childCount + "begin =" + firstVisiblePosition + " end=" + lastVisiblePosition);
        childCount = dji.pilot.fpv.model.b.a(getContext(), R.dimen.bc) + (dji.pilot.fpv.model.b.a(getContext(), R.dimen.bh) * 2);
        int i2 = i * childCount;
        childCount = (childCount * (i + 1)) - p.b(getContext());
        if (childCount < 0) {
            childCount = 0;
        }
        DJILogHelper.getInstance().LOGI("bob", "scrollToIndex scroll=" + i2 + " scroolMin=" + childCount + " scroolx= " + getScrollX());
        if (i < firstVisiblePosition || i > lastVisiblePosition) {
            DJILogHelper.getInstance().LOGI("bob", "scrollToIndex scroll=" + i2);
            scrollTo(i2);
        }
    }

    public void clickPosition(final int i) {
        DJILogHelper.getInstance().LOGI("bob", "nCnt=" + getChildCount() + "getFirstVisiblePosition() = " + getFirstVisiblePosition());
        int lastVisiblePosition = getLastVisiblePosition();
        int firstVisiblePosition = getFirstVisiblePosition();
        if (lastVisiblePosition <= i || i <= firstVisiblePosition) {
            lastVisiblePosition = (dji.pilot.fpv.model.b.a(getContext(), R.dimen.bc) + (dji.pilot.fpv.model.b.a(getContext(), R.dimen.bh) * 2)) * i;
            DJILogHelper.getInstance().LOGI("bob", "scroll=" + lastVisiblePosition);
            scrollTo(lastVisiblePosition);
            postDelayed(new Runnable(this) {
                final /* synthetic */ HorizonalTemplateListView b;

                public void run() {
                    if (this.b.a.isFinished()) {
                        int firstVisiblePosition = i - this.b.getFirstVisiblePosition();
                        DJILogHelper.getInstance().LOGI("bob", "getFirstVisiblePosition=" + this.b.getFirstVisiblePosition() + " index=" + i + " n=" + firstVisiblePosition);
                        View childAt = this.b.getChildAt(firstVisiblePosition);
                        if (this.b.f == null || childAt == null) {
                            DJILogHelper.getInstance().LOGI("bob", new StringBuilder().append("view=").append(childAt).toString() == null ? "null" : "not null");
                            return;
                        } else {
                            this.b.f.a(childAt);
                            return;
                        }
                    }
                    DJILogHelper.getInstance().LOGI("bob", "getFirstVisiblePosition=" + this.b.getFirstVisiblePosition() + " index=" + i);
                    this.b.postDelayed(this, 20);
                }
            }, 100);
            return;
        }
        View childAt = getChildAt(i);
        if (this.f != null && childAt != null) {
            this.f.a(childAt);
        }
    }

    protected boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return super.a(motionEvent, motionEvent2, f, f2);
    }
}
