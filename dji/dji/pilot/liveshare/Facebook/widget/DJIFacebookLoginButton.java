package dji.pilot.liveshare.Facebook.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.facebook.login.widget.LoginButton;
import dji.pilot.R;
import dji.pilot.fpv.d.b;

public class DJIFacebookLoginButton extends LoginButton {
    public DJIFacebookLoginButton(Context context) {
        super(context);
    }

    public DJIFacebookLoginButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJIFacebookLoginButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    protected void setInternalOnClickListener(final OnClickListener onClickListener) {
        super.setInternalOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (b.c(DJIFacebookLoginButton.this.getContext())) {
                    onClickListener.onClick(view);
                } else {
                    Toast.makeText(DJIFacebookLoginButton.this.getContext(), R.string.liveshare_no_network, 0).show();
                }
            }
        });
    }
}
