package dji.pilot2.academy;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class DJIAcademyActivity$1 implements OnItemSelectedListener {
    final /* synthetic */ DJIAcademyActivity a;

    DJIAcademyActivity$1(DJIAcademyActivity dJIAcademyActivity) {
        this.a = dJIAcademyActivity;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        DJIAcademyActivity.a(this.a).a(i);
        DJIAcademyActivity.a(this.a, DJIAcademyActivity.a(this.a).b(i));
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
