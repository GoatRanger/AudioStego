package dji.pilot.liveshare.Youtube;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;

class SelectModeNextQuestionActivity$1 implements OnClickListener {
    final /* synthetic */ SelectModeNextQuestionActivity this$0;

    SelectModeNextQuestionActivity$1(SelectModeNextQuestionActivity selectModeNextQuestionActivity) {
        this.this$0 = selectModeNextQuestionActivity;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gb:
                this.this$0.finish();
                return;
            case R.id.i7:
                Intent intent = new Intent();
                intent.setClass(this.this$0, LiveshareActivity.class);
                this.this$0.startActivity(intent);
                this.this$0.finish();
                return;
            default:
                return;
        }
    }
}
