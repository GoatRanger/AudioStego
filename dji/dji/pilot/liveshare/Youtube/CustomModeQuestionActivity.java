package dji.pilot.liveshare.Youtube;

import android.os.Bundle;
import android.widget.Button;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.thirdparty.afinal.a.b.c;

public class CustomModeQuestionActivity extends DJIBaseActivity {
    @c(a = 2131362052)
    private Button mBackBtn;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        setContentView(R.layout.activity_custom_mode_question);
        this.mBackBtn.setOnClickListener(new 1(this));
    }
}
