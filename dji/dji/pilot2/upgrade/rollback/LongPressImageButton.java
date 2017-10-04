package dji.pilot2.upgrade.rollback;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.publics.DJIUI.DJIImageButton;

public class LongPressImageButton extends DJIImageButton {
    private static final int f = 40;
    private int a;
    private int b;
    private boolean c;
    private boolean d;
    private Runnable e = new Runnable(this) {
        final /* synthetic */ LongPressImageButton a;

        {
            this.a = r1;
        }

        public void run() {
            if (DJIUpgradeP4Service.e()) {
                DJIRollBackActivity.a(this.a.getContext());
            }
            this.a.d = true;
            this.a.setPressed(false);
        }
    };

    public LongPressImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                this.a = x;
                this.b = y;
                this.c = false;
                this.d = false;
                postDelayed(this.e, 2000);
                Log.d("LongPressTextView", "MotionEvent.ACTION_DOWN");
                break;
            case 1:
                removeCallbacks(this.e);
                break;
            case 2:
                if (!this.c && (Math.abs(this.a - x) > 40 || Math.abs(this.b - y) > 40)) {
                    this.c = true;
                    removeCallbacks(this.e);
                    Log.d("LongPressTextView", "移动超过阈值，则表示移动了  ");
                    break;
                }
        }
        if (this.d) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
