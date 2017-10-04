package dji.pilot.usercenter.profile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.j;
import dji.publics.DJIUI.DJILinearLayout;

public class DJIEditUserNameView extends DJILinearLayout implements a {
    private EditText a = null;
    private EditText b = null;

    public DJIEditUserNameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJIEditUserNameView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.a = (EditText) findViewById(R.id.bj8);
            this.b = (EditText) findViewById(R.id.bj9);
        }
    }

    public void dispatchOnStart() {
        j h = f.getInstance().h();
        this.a.setText(h.o);
        this.b.setText(h.p);
    }

    public void dispatchOnStop() {
        j h = f.getInstance().h();
        h.o = this.a.getText().toString();
        h.p = this.b.getText().toString();
        h.j = h.o + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + h.p;
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }
}
