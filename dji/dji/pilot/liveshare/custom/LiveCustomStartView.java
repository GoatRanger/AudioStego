package dji.pilot.liveshare.custom;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import dji.pilot.R;
import dji.pilot.liveshare.b;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.setting.a.a;
import dji.thirdparty.a.c;

public class LiveCustomStartView extends DJIRelativeLayout implements OnClickListener {
    protected ImageButton mBackBtn;
    protected Button mStartBtn;

    public LiveCustomStartView(Activity activity) {
        this(activity, null);
    }

    public LiveCustomStartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    protected void init() {
        a.a((View) this, (int) R.layout.fpv_liveshare_custom_start);
        if (!isInEditMode()) {
            this.mBackBtn = (ImageButton) findViewById(R.id.a6y);
            this.mBackBtn.setOnClickListener(this);
            this.mStartBtn = (Button) findViewById(R.id.a70);
            this.mStartBtn.setOnClickListener(this);
        }
    }

    protected void handleLiveThread() {
        new Thread(new Runnable() {
            public void run() {
                LiveCustomStartView.this.startCustomStream();
                c.a().e(new dji.pilot.f.a.a(6));
            }
        }).start();
        b.getInstance().setLaunch(true);
    }

    protected void startCustomStream() {
        b.getInstance().setUrl(dji.pilot.f.a.a.F);
        b.getInstance().setPrimaryServerUrl(dji.pilot.f.a.a.F);
        b.getInstance().setStreamMode(2);
        b.getInstance().startStream();
        b.getInstance().setStreamBeginTime();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.a6y:
                c.a().e(new dji.pilot.f.a.a(18));
                return;
            case R.id.a70:
                handleLiveThread();
                c.a().e(new dji.pilot.f.a.a(5));
                return;
            default:
                return;
        }
    }
}
