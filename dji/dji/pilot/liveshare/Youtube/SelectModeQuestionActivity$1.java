package dji.pilot.liveshare.Youtube;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;

class SelectModeQuestionActivity$1 implements OnClickListener {
    final /* synthetic */ SelectModeQuestionActivity this$0;

    SelectModeQuestionActivity$1(SelectModeQuestionActivity selectModeQuestionActivity) {
        this.this$0 = selectModeQuestionActivity;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gb:
                this.this$0.finish();
                return;
            case R.id.i9:
                Intent intent = new Intent();
                intent.setClass(this.this$0, SelectModeNextQuestionActivity.class);
                this.this$0.startActivity(intent);
                return;
            default:
                return;
        }
    }
}
