package dji.pilot.liveshare.Youtube;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import dji.midware.natives.FPVController;
import dji.pilot.R;
import dji.pilot.fpv.d.c.g;
import dji.pilot.fpv.d.e;
import dji.pilot.liveshare.b;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.objects.DJINetWorkReceiver;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;

public class CustomModeActivity extends DJIBaseActivity implements g {
    public static final int CUSTOM_MODE = 1;
    private static final long DELAY_UPDATE_BITRATE = 2000;
    private static final int MSG_ID_STREAMINITERR = 12288;
    private static final int MSG_ID_UPDATE_BITRATE = 4096;
    private static final int MSG_ID_UPDATE_PROCIMG = 8192;
    private TextView audioBitrate;
    private boolean isRunning;
    private ImageView mLiveProcessImg = null;
    private b mLiveStreaming = null;
    private dji.pilot2.a mStopDlg = null;
    private a mUIHandler = null;
    private OnClickListener mWidgetClickListener = null;
    Button start;
    private String streamingName = null;
    private EditText streamingNameET;
    private String url = null;
    private EditText urlET;
    private TextView videoBitrate;
    private TextView videoFPS;

    private static final class a extends Handler {
        private final WeakReference<CustomModeActivity> mOutCls;

        private a(CustomModeActivity customModeActivity) {
            super(Looper.getMainLooper());
            this.mOutCls = new WeakReference(customModeActivity);
        }

        public void handleMessage(Message message) {
            CustomModeActivity customModeActivity = (CustomModeActivity) this.mOutCls.get();
            if (customModeActivity != null && !customModeActivity.isFinishing()) {
                switch (message.what) {
                    case 4096:
                        customModeActivity.updateBitrate();
                        sendEmptyMessageDelayed(4096, 2000);
                        return;
                    case 8192:
                        customModeActivity.mLiveProcessImg.setVisibility(4);
                        customModeActivity.start.setText(customModeActivity.getResources().getString(R.string.liveshare_youtube_live_customstart_stop_btn));
                        customModeActivity.start.setBackground(customModeActivity.getResources().getDrawable(R.drawable.live_red_btn_bg));
                        customModeActivity.start.setEnabled(true);
                        c.a().e(customModeActivity);
                        return;
                    case 12288:
                        customModeActivity.mLiveProcessImg.setVisibility(4);
                        customModeActivity.start.setEnabled(true);
                        Toast.makeText(customModeActivity.getApplicationContext(), R.string.liveshare_streaminiterror, 0).show();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        setContentView(R.layout.liveshare_custommode);
        this.mUIHandler = new a();
        Button button = (Button) findViewById(R.id.az0);
        this.start = (Button) findViewById(R.id.az7);
        Button button2 = (Button) findViewById(R.id.az2);
        ImageView imageView = (ImageView) findViewById(R.id.az1);
        this.urlET = (EditText) findViewById(R.id.az6);
        this.streamingNameET = (EditText) findViewById(R.id.az4);
        this.videoFPS = (TextView) findViewById(R.id.az_);
        this.videoBitrate = (TextView) findViewById(R.id.aza);
        this.audioBitrate = (TextView) findViewById(R.id.azb);
        initListeners();
        button.setOnClickListener(this.mWidgetClickListener);
        this.start.setOnClickListener(this.mWidgetClickListener);
        button2.setOnClickListener(this.mWidgetClickListener);
        imageView.setOnClickListener(this.mWidgetClickListener);
        this.mLiveStreaming = b.getInstance();
        this.streamingName = this.mLiveStreaming.getStreamName();
        this.url = this.mLiveStreaming.getPrimaryServerUrl();
        this.isRunning = this.mLiveStreaming.isRunning();
        this.mLiveProcessImg = (ImageView) findViewById(R.id.az8);
        this.mLiveProcessImg.setVisibility(4);
        if (this.url != null) {
            this.urlET.setText(this.url);
        }
        if (this.streamingName != null) {
            this.streamingNameET.setText(this.streamingName);
        }
        if (this.mLiveStreaming.isRunning()) {
            this.mUIHandler.sendEmptyMessageDelayed(4096, 2000);
            this.start.setText(getResources().getString(R.string.liveshare_youtube_live_customstart_stop_btn));
            this.start.setBackground(getResources().getDrawable(R.drawable.live_red_btn_bg));
        }
    }

    protected void onDestroy() {
        this.mUIHandler.removeMessages(4096);
        super.onDestroy();
    }

    private void initListeners() {
        this.mWidgetClickListener = new OnClickListener() {
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.az7) {
                    e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_CustomModeView_Button_Stop");
                    if (CustomModeActivity.this.mLiveStreaming.isRunning()) {
                        if (CustomModeActivity.this.mStopDlg == null) {
                            CustomModeActivity.this.mStopDlg = new dji.pilot2.a(CustomModeActivity.this);
                            CustomModeActivity.this.mStopDlg.b(CustomModeActivity.this.getString(R.string.liveshare_youtube_live_stream_stop_remind));
                            CustomModeActivity.this.mStopDlg.c(CustomModeActivity.this.getString(R.string.ve_cancel));
                            CustomModeActivity.this.mStopDlg.d(CustomModeActivity.this.getString(R.string.ok));
                            CustomModeActivity.this.mStopDlg.b();
                            CustomModeActivity.this.mStopDlg.a(new dji.pilot2.a.a() {
                                public void onRightBtnClick() {
                                    b.getInstance().stopStream();
                                    b.getInstance().setLaunch(false);
                                    b.getInstance().setTestTag(1);
                                    c.a().e(CustomModeActivity.this);
                                    CustomModeActivity.this.finish();
                                }

                                public void onLeftBtnClick() {
                                }
                            });
                            CustomModeActivity.this.mStopDlg.a(0.4f);
                        }
                        if (!CustomModeActivity.this.mStopDlg.isShowing()) {
                            CustomModeActivity.this.mStopDlg.show();
                            return;
                        }
                        return;
                    }
                    e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_CustomModeView_Button_Start");
                    if (CustomModeActivity.this.urlET.getText().toString().equals("") || CustomModeActivity.this.streamingNameET.getText().toString().equals("")) {
                        Toast.makeText(CustomModeActivity.this.getApplicationContext(), R.string.liveshare_basicmode_inputcheck, 0).show();
                        return;
                    }
                    e.c(g.J);
                    CustomModeActivity.this.url = CustomModeActivity.this.urlET.getText().toString();
                    CustomModeActivity.this.streamingName = CustomModeActivity.this.streamingNameET.getText().toString();
                    CustomModeActivity.this.mLiveStreaming.setStreamName(CustomModeActivity.this.streamingName);
                    CustomModeActivity.this.mLiveStreaming.setPrimaryServerUrl(CustomModeActivity.this.url);
                    if (DJINetWorkReceiver.a(CustomModeActivity.this)) {
                        if (!DJINetWorkReceiver.b(CustomModeActivity.this)) {
                            Toast.makeText(CustomModeActivity.this, R.string.liveshare_no_wifi, 0).show();
                        }
                        CustomModeActivity.this.mLiveProcessImg.setVisibility(0);
                        Drawable background = CustomModeActivity.this.mLiveProcessImg.getBackground();
                        if (background instanceof AnimationDrawable) {
                            ((AnimationDrawable) background).start();
                        }
                        CustomModeActivity.this.start.setEnabled(false);
                        CustomModeActivity.this.mLiveStreaming.setStreamMode(1);
                        new Thread(new Runnable() {
                            public void run() {
                                CustomModeActivity.this.mLiveStreaming.setLaunch(true);
                                int startStream = CustomModeActivity.this.mLiveStreaming.startStream();
                                if (startStream == 0) {
                                    CustomModeActivity.this.mUIHandler.sendEmptyMessage(8192);
                                    b.getInstance().setStreamBeginTime();
                                    b.getInstance().setTestTag(0);
                                    CustomModeActivity.this.mUIHandler.sendEmptyMessageDelayed(4096, 2000);
                                } else if (startStream == -2) {
                                    CustomModeActivity.this.mUIHandler.sendEmptyMessage(12288);
                                }
                            }
                        }).start();
                        return;
                    }
                    Toast.makeText(CustomModeActivity.this, R.string.liveshare_no_network, 0).show();
                } else if (id == R.id.az2) {
                    e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_CustomModeView_Button_Done");
                    b.getInstance().setTestTag(1);
                    c.a().e(CustomModeActivity.this);
                    CustomModeActivity.this.finish();
                } else if (id == R.id.az0) {
                    e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_CustomModeView_Button_Cancel");
                    if (CustomModeActivity.this.mLiveStreaming.isRunning()) {
                        b.getInstance().setTestTag(0);
                        c.a().e(CustomModeActivity.this);
                    }
                    r0 = new Intent();
                    r0.setClass(CustomModeActivity.this, LiveshareActivity.class);
                    CustomModeActivity.this.startActivity(r0);
                    CustomModeActivity.this.finish();
                } else if (id == R.id.az1) {
                    r0 = new Intent();
                    r0.setClass(CustomModeActivity.this, CustomModeQuestionActivity.class);
                    CustomModeActivity.this.startActivity(r0);
                }
            }
        };
    }

    private void updateBitrate() {
        short[] native_getStreamParams = FPVController.native_getStreamParams();
        Log.e("videoFPS", "" + native_getStreamParams[0]);
        Log.e("videoBitrate", "" + native_getStreamParams[1]);
        Log.e("audioBitrate", "" + native_getStreamParams[2]);
        this.videoFPS.setText("" + native_getStreamParams[0]);
        this.videoBitrate.setText("" + native_getStreamParams[1]);
        this.audioBitrate.setText("" + native_getStreamParams[2]);
    }
}
