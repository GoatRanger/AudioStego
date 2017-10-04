package dji.pilot2.usercenter.activate;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public abstract class ActivateBaseView extends LinearLayout implements b {
    public g a = null;
    public Handler b;

    public abstract void setData();

    public ActivateBaseView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ActivateBaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActivateBaseView(Context context) {
        super(context);
    }

    public boolean handleGoNext() {
        setData();
        return true;
    }

    public boolean handleGoPre() {
        return false;
    }

    public boolean onResume() {
        return false;
    }

    public boolean onShow() {
        return true;
    }

    public boolean canShowTopView() {
        return true;
    }

    public void setMainTopViewControl(g gVar) {
        this.a = gVar;
    }

    public boolean canGoPre() {
        return true;
    }

    public boolean canGoNext() {
        return true;
    }
}
