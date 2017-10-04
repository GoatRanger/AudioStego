package dji.pilot.fpv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.fpv.view.DJIStageView.b;

public class DJIGenFirstStageView extends FrameLayout implements a {
    private b a;

    public DJIGenFirstStageView(Context context) {
        super(context);
    }

    public DJIGenFirstStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJIGenFirstStageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setLifeListener(b bVar) {
        this.a = bVar;
    }

    public void dispatchOnStart() {
        if (this.a != null) {
            this.a.f();
        }
    }

    public void dispatchOnStop() {
        if (this.a != null) {
            this.a.g();
        }
    }

    public void dispatchOnResume() {
        if (this.a != null) {
            this.a.d();
        }
    }

    public void dispatchOnPause() {
        if (this.a != null) {
            this.a.e();
        }
    }

    public View getView() {
        return this;
    }
}
