package dji.pilot.publics.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import dji.publics.DJIUI.DJITextView;

public class DJIRepeatButton extends DJITextView {
    public static final String a = "DJIRepeatButton";
    private long b = 200;
    private int c = 0;
    private long d = 0;
    private boolean e = false;
    private a f = null;
    private Handler g = new Handler(this) {
        final /* synthetic */ DJIRepeatButton a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == 4132) {
                if (this.a.f != null) {
                    this.a.f.a(this.a, System.currentTimeMillis() - this.a.d);
                }
                if (this.a.e) {
                    this.a.g.sendEmptyMessageDelayed(4132, this.a.b);
                }
            }
        }
    };
    private final int h = 4132;

    public interface a {
        void a(View view, long j);
    }

    public DJIRepeatButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setFocusable(true);
        setClickable(true);
    }

    public void setRepeatListener(a aVar, int i) {
        this.f = aVar;
        this.b = (long) i;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        Log.d(a, "action " + action);
        if (action == 0) {
            this.e = true;
            this.c = 0;
            this.d = System.currentTimeMillis();
            Message obtainMessage = this.g.obtainMessage(4132);
            obtainMessage.arg1 = (int) System.currentTimeMillis();
            if (this.g != null) {
                this.g.sendMessageDelayed(obtainMessage, this.b);
            }
        } else if (1 == action) {
            this.e = false;
            if (this.g != null) {
                this.g.removeMessages(4132);
            }
        }
        return super.onTouchEvent(motionEvent);
    }
}
