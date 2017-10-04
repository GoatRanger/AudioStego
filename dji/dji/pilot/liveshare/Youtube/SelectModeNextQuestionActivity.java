package dji.pilot.liveshare.Youtube;

import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.thirdparty.afinal.a.b.c;

public class SelectModeNextQuestionActivity extends DJIBaseActivity {
    private static int STEP_NUM = 4;
    @c(a = 2131362052)
    private Button mBackBtn;
    private OnClickListener mClickListener = null;
    @c(a = 2131362121)
    private Button mStartBtn;
    @c(a = 2131362122)
    private GridView mStepGridView;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        setContentView(R.layout.activity_select_mode_next_question);
        this.mStepGridView.setAdapter(new a(this));
        initListener();
        setListener();
    }

    private void initListener() {
        this.mClickListener = new 1(this);
    }

    private void setListener() {
        this.mBackBtn.setOnClickListener(this.mClickListener);
        this.mStartBtn.setOnClickListener(this.mClickListener);
    }
}
