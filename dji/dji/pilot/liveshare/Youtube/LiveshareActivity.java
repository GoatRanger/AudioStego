package dji.pilot.liveshare.Youtube;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import dji.pilot.R;
import dji.pilot.f.a.a;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJIBaseActivity;

public class LiveshareActivity extends DJIBaseActivity {
    private OnClickListener mWidgetClickListener = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        setContentView(R.layout.liveshare_mode_select);
        a.D = 1;
        initListeners();
        Button button = (Button) findViewById(R.id.azq);
        Button button2 = (Button) findViewById(R.id.azl);
        ImageView imageView = (ImageView) findViewById(R.id.azk);
        ((Button) findViewById(R.id.azn)).setOnClickListener(this.mWidgetClickListener);
        button.setOnClickListener(this.mWidgetClickListener);
        button2.setOnClickListener(this.mWidgetClickListener);
        imageView.setOnClickListener(this.mWidgetClickListener);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    private void initListeners() {
        this.mWidgetClickListener = new OnClickListener() {
            public void onClick(View view) {
                int id = view.getId();
                Intent intent;
                if (id == R.id.azn) {
                    e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_Button_BasicMode");
                    intent = new Intent();
                    intent.setClass(LiveshareActivity.this, BasicModeActivity.class);
                    LiveshareActivity.this.startActivity(intent);
                    LiveshareActivity.this.finish();
                } else if (id == R.id.azq) {
                    e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_Button_CustomMode");
                    intent = new Intent();
                    intent.setClass(LiveshareActivity.this, CustomModeActivity.class);
                    LiveshareActivity.this.startActivity(intent);
                    LiveshareActivity.this.finish();
                } else if (id == R.id.azl) {
                    e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_Button_Cancel");
                    LiveshareActivity.this.finish();
                } else if (id == R.id.azk) {
                    e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_Button_Help");
                    intent = new Intent();
                    intent.setClass(LiveshareActivity.this, SelectModeQuestionActivity.class);
                    LiveshareActivity.this.startActivity(intent);
                }
            }
        };
    }
}
