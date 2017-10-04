package dji.pilot2.academy;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class DJIAcademyActivity$3 implements OnItemClickListener {
    final /* synthetic */ DJIAcademyActivity a;

    DJIAcademyActivity$3(DJIAcademyActivity dJIAcademyActivity) {
        this.a = dJIAcademyActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        DJIAcademyActivity.d(this.a).d(i);
    }
}
