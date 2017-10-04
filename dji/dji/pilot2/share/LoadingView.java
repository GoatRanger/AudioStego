package dji.pilot2.share;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import dji.thirdparty.afinal.f.c;
import java.io.File;

public class LoadingView extends FrameLayout {
    c<File> a = null;

    public LoadingView(Context context) {
        super(context);
        a();
    }

    public LoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public LoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        View progressBar = new ProgressBar(getContext());
        progressBar.setLayoutParams(new LayoutParams(-2, -2, 17));
        addView(progressBar);
        setBackgroundColor(Color.argb(100, 100, 100, 100));
    }

    public void setDownloadHandel(c<File> cVar) {
        this.a = cVar;
    }

    protected void onVisibilityChanged(View view, int i) {
        if (i != 0 && this.a != null) {
            this.a.a(true);
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }
}
