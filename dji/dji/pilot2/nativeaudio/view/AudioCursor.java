package dji.pilot2.nativeaudio.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import dji.log.DJILogHelper;

public class AudioCursor extends RelativeLayout {
    private a a;
    private int b;

    public interface a {
        void a();

        void a(int i);

        void b();
    }

    public AudioCursor(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setListener(a aVar) {
        this.a = aVar;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.b = (int) motionEvent.getRawX();
                DJILogHelper.getInstance().LOGD("rxq", "cursor down oldx: " + this.b);
                this.a.b();
                break;
            case 1:
                this.a.a();
                break;
            case 2:
                int rawX = (int) motionEvent.getRawX();
                int i = rawX - this.b;
                DJILogHelper.getInstance().LOGD("rxq", "cursor move oldx: " + this.b + " newX: " + rawX + " step: " + i);
                this.a.a(i);
                this.b = rawX;
                break;
        }
        return true;
    }
}
