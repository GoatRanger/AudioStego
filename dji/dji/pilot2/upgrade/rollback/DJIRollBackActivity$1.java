package dji.pilot2.upgrade.rollback;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class DJIRollBackActivity$1 implements OnItemClickListener {
    final /* synthetic */ DJIRollBackActivity a;

    DJIRollBackActivity$1(DJIRollBackActivity dJIRollBackActivity) {
        this.a = dJIRollBackActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        DJIRollBackActivity.b(this.a).a(DJIRollBackActivity.a(this.a).getSelectedItemPosition());
    }
}
