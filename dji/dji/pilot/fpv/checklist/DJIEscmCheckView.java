package dji.pilot.fpv.checklist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataOsdGetPushHome.MotorEscmState;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.publics.DJIUI.DJILinearLayout;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DJIEscmCheckView extends DJILinearLayout implements a {
    private ListView a = null;
    private a b = null;
    private final MotorEscmState[] c = new MotorEscmState[8];
    private List<a.a> d = new ArrayList();

    public DJIEscmCheckView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.b = new a(getContext());
            this.a = (ListView) findViewById(R.id.vz);
            this.a.setAdapter(this.b);
        }
    }

    public void onEventMainThread(DataOsdGetPushHome dataOsdGetPushHome) {
        MotorEscmState[] motorEscmState = dataOsdGetPushHome.getMotorEscmState();
        if (!Arrays.equals(motorEscmState, this.c)) {
            this.d.clear();
            for (int i = 0; i < 8; i++) {
                this.c[i] = motorEscmState[i];
                if (!b.a(motorEscmState[i])) {
                    this.d.add(new a.a(motorEscmState[i], i));
                }
            }
            this.b.a(this.d).notifyDataSetChanged();
        }
    }

    public void dispatchOnStart() {
        if (!c.a().c(this)) {
            c.a().a(this);
        }
        onEventMainThread(DataOsdGetPushHome.getInstance());
    }

    public void dispatchOnStop() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }
}
