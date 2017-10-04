package dji.pilot2.academy;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class DJIAcademyActivity$4 implements OnItemClickListener {
    final /* synthetic */ DJIAcademyActivity a;

    DJIAcademyActivity$4(DJIAcademyActivity dJIAcademyActivity) {
        this.a = dJIAcademyActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        DJIAcademyActivity.e(this.a).d(i);
    }
}
