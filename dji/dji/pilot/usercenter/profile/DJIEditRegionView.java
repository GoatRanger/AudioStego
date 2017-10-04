package dji.pilot.usercenter.profile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.j;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIEditRegionView extends DJILinearLayout implements a {
    private EditText a = null;
    private EditText b = null;
    private View c = null;
    private DJITextView d = null;

    public DJIEditRegionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
        j h = f.getInstance().h();
        h.w = this.a.getText().toString();
        h.v = this.b.getText().toString();
    }

    public void dispatchOnResume() {
        j h = f.getInstance().h();
        this.a.setText(h.w);
        this.b.setText(h.v);
        this.d.setText(h.R == null ? h.t : h.R.b);
    }

    public void dispatchOnPause() {
        j h = f.getInstance().h();
        h.w = this.a.getText().toString();
        h.v = this.b.getText().toString();
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.a = (EditText) findViewById(R.id.bj_);
            this.b = (EditText) findViewById(R.id.bja);
            this.c = findViewById(R.id.bjb);
            this.d = (DJITextView) findViewById(R.id.bjd);
            this.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DJIEditRegionView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    View view2 = (View) this.a.getParent();
                    if (view2 instanceof DJIStageView) {
                        ((DJIStageView) view2).createStageView(R.layout.profile_select_region_view, R.string.usercenter_my_info_select_region, true);
                    }
                }
            });
        }
    }

    public View getView() {
        return this;
    }
}
