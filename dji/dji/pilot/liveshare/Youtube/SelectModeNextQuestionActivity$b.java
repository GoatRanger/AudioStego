package dji.pilot.liveshare.Youtube;

import android.widget.ImageView;
import android.widget.TextView;
import dji.pilot.R;

class SelectModeNextQuestionActivity$b {
    ImageView mStepImg;
    TextView mStepTxt;
    final /* synthetic */ SelectModeNextQuestionActivity this$0;

    SelectModeNextQuestionActivity$b(SelectModeNextQuestionActivity selectModeNextQuestionActivity) {
        this.this$0 = selectModeNextQuestionActivity;
    }

    public void configure(int i) {
        switch (i) {
            case 0:
                this.mStepImg.setImageDrawable(this.this$0.getResources().getDrawable(R.drawable.livestream_tutorial_001));
                this.mStepTxt.setText(this.this$0.getResources().getString(R.string.liveshare_select_mode_ques_step_01));
                return;
            case 1:
                this.mStepImg.setImageDrawable(this.this$0.getResources().getDrawable(R.drawable.livestream_tutorial_002));
                this.mStepTxt.setText(this.this$0.getResources().getString(R.string.liveshare_select_mode_ques_step_02));
                return;
            case 2:
                this.mStepImg.setImageDrawable(this.this$0.getResources().getDrawable(R.drawable.livestream_tutorial_003));
                this.mStepTxt.setText(this.this$0.getResources().getString(R.string.liveshare_select_mode_ques_step_03));
                return;
            case 3:
                this.mStepImg.setImageDrawable(this.this$0.getResources().getDrawable(R.drawable.livestream_tutorial_004));
                this.mStepTxt.setText(this.this$0.getResources().getString(R.string.liveshare_select_mode_ques_step_04));
                return;
            default:
                return;
        }
    }
}
