package dji.pilot.fpv.leftmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import dji.pilot.R;
import dji.pilot.fpv.model.n.a;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJILeftOptView extends DJIRelativeLayout implements OnClickListener {
    private DJITextView a = null;
    private Animation b = null;
    private Animation c = null;

    public DJILeftOptView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void hideMenu(boolean z) {
        if (getVisibility() != 8) {
            setVisibility(8);
            startAnimation(this.c);
        }
    }

    public void showMenu() {
        if (getVisibility() != 0) {
            setVisibility(0);
            startAnimation(this.b);
        }
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.b = AnimationUtils.loadAnimation(getContext(), R.anim.bi);
            this.c = AnimationUtils.loadAnimation(getContext(), R.anim.bk);
            this.a = (DJITextView) findViewById(R.id.a51);
            this.a.setOnClickListener(this);
        }
    }

    public void onClick(View view) {
        if (R.id.a51 == view.getId()) {
            c.a().e(a.a);
            dji.pilot.visual.beginner.a.getInstance().d();
        }
    }
}
