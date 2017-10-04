package dji.publics.DJIObject;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.Window;
import com.dji.frame.c.c;
import com.dji.frame.c.c.a;
import dji.thirdparty.afinal.FinalActivity;

public class DJIBaseActivityForVirtualKey extends FinalActivity {
    private Handler handler = new Handler(new 1(this));
    protected boolean isVisible;
    protected Window window;

    protected void enter(Window window) {
        c.a(window);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.window = getWindow();
    }

    protected void onResume() {
        super.onResume();
        enter(this.window);
        this.isVisible = true;
    }

    protected void onPause() {
        super.onPause();
        this.isVisible = false;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            enter(this.window);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onEventMainThread(a aVar) {
        if (this.isVisible) {
            switch (2.a[aVar.ordinal()]) {
                case 1:
                    enter(this.window);
                    return;
                case 2:
                    this.handler.sendEmptyMessageDelayed(3, 2000);
                    return;
                default:
                    return;
            }
        }
    }
}
