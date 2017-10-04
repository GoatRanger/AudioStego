package dji.pilot.liveshare.Youtube;

import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.thirdparty.afinal.a.b.c;

public class SelectModeQuestionActivity extends DJIBaseActivity {
    @c(a = 2131362052)
    private Button mBackBtn;
    private OnClickListener mClickListener = null;
    @c(a = 2131362123)
    private Button mNextBtn;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        setContentView(R.layout.activity_select_mode_question);
        initListener();
        setListener();
    }

    private void initListener() {
        this.mClickListener = new 1(this);
    }

    private void setListener() {
        this.mBackBtn.setOnClickListener(this.mClickListener);
        this.mNextBtn.setOnClickListener(this.mClickListener);
    }
}
