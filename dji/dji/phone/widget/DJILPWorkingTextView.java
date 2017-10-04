package dji.phone.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class DJILPWorkingTextView extends TextView {
    private static final String b = "DJILPWorkingTextView";
    Runnable a = new Runnable(this) {
        final /* synthetic */ DJILPWorkingTextView a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.d = this.a.getText().toString();
            if (this.a.d.indexOf(".") == this.a.d.length() - 3) {
                this.a.setText(this.a.c);
            } else {
                this.a.append(".");
            }
        }
    };
    private String c;
    private String d;
    private Timer e;

    public DJILPWorkingTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
    }

    public void startWorking() {
        if (this.e == null) {
            this.c = getText().toString().replace(".", "");
            this.e = new Timer("working text timer");
            this.e.schedule(new TimerTask(this) {
                final /* synthetic */ DJILPWorkingTextView a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.removeCallbacks(this.a.a);
                    this.a.post(this.a.a);
                }
            }, 0, 300);
        }
    }

    public void stopWorking() {
        if (this.e != null) {
            this.e.cancel();
            this.e = null;
        }
    }
}
