package dji.pilot.liveshare.Youtube;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import dji.pilot.R;

class SelectModeNextQuestionActivity$a extends BaseAdapter {
    final /* synthetic */ SelectModeNextQuestionActivity this$0;

    SelectModeNextQuestionActivity$a(SelectModeNextQuestionActivity selectModeNextQuestionActivity) {
        this.this$0 = selectModeNextQuestionActivity;
    }

    public int getCount() {
        return SelectModeNextQuestionActivity.access$000();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        SelectModeNextQuestionActivity$b selectModeNextQuestionActivity$b;
        if (view == null) {
            view = LayoutInflater.from(this.this$0).inflate(R.layout.liveshare_question_gridview_item, null);
            SelectModeNextQuestionActivity$b selectModeNextQuestionActivity$b2 = new SelectModeNextQuestionActivity$b(this.this$0);
            selectModeNextQuestionActivity$b2.mStepImg = (ImageView) view.findViewById(R.id.azs);
            selectModeNextQuestionActivity$b2.mStepTxt = (TextView) view.findViewById(R.id.azt);
            view.setTag(selectModeNextQuestionActivity$b2);
            selectModeNextQuestionActivity$b = selectModeNextQuestionActivity$b2;
        } else {
            selectModeNextQuestionActivity$b = (SelectModeNextQuestionActivity$b) view.getTag();
        }
        selectModeNextQuestionActivity$b.configure(i);
        return view;
    }
}
