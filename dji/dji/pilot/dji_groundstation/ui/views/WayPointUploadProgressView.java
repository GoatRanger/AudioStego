package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.controller.DataMgr.e;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class WayPointUploadProgressView extends DJILinearLayout {
    private static final String a = "WayPointUploadProgressView";
    private ProgressBar b = null;
    private DJITextView c = null;
    private Handler d = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ WayPointUploadProgressView a;

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 257:
                case 258:
                    this.a.a(message.arg1);
                    return;
                default:
                    return;
            }
        }
    };

    public WayPointUploadProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
        LayoutInflater.from(getContext()).inflate(R.layout.view_waypoint_upload_progress_bar, this);
        e.getInstance().a(this.d);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.b = (ProgressBar) findViewById(R.id.gs_wait_dialog_progress);
        this.c = (DJITextView) findViewById(R.id.gs_wait_dialog_desc);
        a(0);
        this.b.setMax(100);
    }

    private void a(int i) {
        int i2 = 100;
        if (i <= 100) {
            i2 = i;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        this.b.setProgress(i2);
        CharSequence spannableString = new SpannableString(Integer.toString(i2));
        spannableString.setSpan(new AbsoluteSizeSpan(14, true), 0, spannableString.length(), 17);
        CharSequence spannableString2 = new SpannableString("%");
        spannableString2.setSpan(new AbsoluteSizeSpan(12, true), 0, spannableString2.length(), 17);
        this.c.setText("");
        this.c.append(spannableString);
        this.c.append(spannableString2);
    }

    public Handler getUpdateHandler() {
        return this.d;
    }
}
