package dji.pilot.playback.litchi;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class DJIPlayBackVideoPreviewActivity$8 implements OnItemClickListener {
    final /* synthetic */ DJIPlayBackVideoPreviewActivity a;

    DJIPlayBackVideoPreviewActivity$8(DJIPlayBackVideoPreviewActivity dJIPlayBackVideoPreviewActivity) {
        this.a = dJIPlayBackVideoPreviewActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        DJIPlayBackVideoPreviewActivity.a(this.a, i, j);
    }
}
