package dji.pilot.visual.stage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.model.P3.DataSingleSetPointPos.TapMode;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.visual.a.c;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIVisualPointModeView extends DJILinearLayout implements OnClickListener, a {
    private DJITextView a = null;
    private DJIImageView b = null;
    private DJITextView c = null;
    private DJITextView d = null;

    public DJIVisualPointModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.a = (DJITextView) findViewById(R.id.d9p);
            this.b = (DJIImageView) findViewById(R.id.d9q);
            this.c = (DJITextView) findViewById(R.id.d9r);
            this.d = (DJITextView) findViewById(R.id.d9s);
            this.d.setOnClickListener(this);
        }
    }

    public void onClick(View view) {
        if (R.id.d9s == view.getId()) {
            ((DJIStageView) getParent()).destoryStageView(false);
        }
    }

    private void a() {
        TapMode b = c.getInstance().a().b();
        if (b == TapMode.b) {
            this.a.setText(R.string.visual_point_normal);
            this.b.setImageResource(R.drawable.visual_point_success);
            this.c.setText(R.string.visual_point_normal_desc);
            this.d = (DJITextView) findViewById(R.id.d9s);
        } else if (b != TapMode.d) {
        }
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        a();
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }
}
