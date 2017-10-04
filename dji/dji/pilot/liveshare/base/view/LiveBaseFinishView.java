package dji.pilot.liveshare.base.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import dji.pilot.R;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.setting.a.a;
import dji.thirdparty.a.c;

public abstract class LiveBaseFinishView extends DJIRelativeLayout implements OnClickListener {
    protected Activity mActivity;
    protected Button mFinishBtn;

    protected abstract void initTypeWidget();

    public LiveBaseFinishView(Activity activity) {
        this(activity, null);
        this.mActivity = activity;
    }

    public LiveBaseFinishView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        a.a((View) this, (int) R.layout.fpv_liveshare_base_finish);
        if (!isInEditMode()) {
            this.mFinishBtn = (Button) findViewById(R.id.a5e);
            this.mFinishBtn.setOnClickListener(this);
            initTypeWidget();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.a5e:
                c.a().e(new dji.pilot.f.a.a(16));
                return;
            default:
                return;
        }
    }
}
