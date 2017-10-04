package dji.pilot.liveshare.Youtube;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.login.widget.ToolTipPopup;
import com.google.android.gms.common.Scopes;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.services.youtube.YouTube.LiveBroadcasts;
import com.google.api.services.youtube.YouTube.LiveStreams.List;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.LiveBroadcast;
import com.google.api.services.youtube.model.LiveBroadcastListResponse;
import com.google.api.services.youtube.model.LiveStream;
import com.google.api.services.youtube.model.LiveStreamListResponse;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.liveshare.b;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.mine.b.e;
import dji.thirdparty.a.c;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class youtubeLiveActivity extends Activity {
    public static final int BASIC_MODE = 0;
    private static final String[] SCOPES = new String[]{Scopes.PROFILE, YouTubeScopes.YOUTUBE};
    public static final int SHOWTOAST = 7;
    public static final int STREAM_CREATE = 1;
    public static final int STREAM_DISABLE = 6;
    public static final int VIDEO_ACTIVE = 2;
    public static final int VIDEO_LIVE = 5;
    public static final int VIDEO_TEST = 4;
    public static final int VIDEO_TESTING = 3;
    String broadcastDesc;
    String broadcastTitle;
    GoogleAccountCredential credential;
    Button done;
    Button encoding;
    private Handler handler;
    private String ingestionAddress;
    private boolean isFinishSelf = true;
    private TextView linkshare;
    a mBroadcast = null;
    private a mBroadcastManager = null;
    String mEmail;
    private ImageView mLiveProcessImg = null;
    private ImageView mLiveProcessImg2 = null;
    private b mLiveStreaming = null;
    private LinearLayout mLiveStreamingTagLy;
    private TextView mStatuText;
    private dji.pilot2.a mStopDlg = null;
    private OnClickListener mWidgetClickListener = null;
    boolean paused;
    String privacyStatus;
    LiveBroadcast returnedBroadcast = null;
    LiveStream returnedStream = null;
    Button share;
    int stage = -1;
    Button start;
    Button stop;
    private Timer timer = null;
    private String urlSource = null;
    private String watchUrl = null;

    class a extends TimerTask {
        a() {
        }

        public void run() {
            try {
                String streamStatus;
                Message message;
                Bundle bundle;
                a.getInstance();
                List list = a.getYt().liveStreams().list("id,snippet,status");
                list.setMine(Boolean.valueOf(true));
                java.util.List items = ((LiveStreamListResponse) list.execute()).getItems();
                Log.e(e.p, "stream status " + ((LiveStream) items.get(0)).getStatus().getStreamStatus());
                a.getInstance();
                LiveBroadcasts.List list2 = a.getYt().liveBroadcasts().list("id,snippet, status");
                list2.setBroadcastStatus("all");
                java.util.List items2 = ((LiveBroadcastListResponse) list2.execute()).getItems();
                Log.e(e.p, "LifeCycleStatus " + ((LiveBroadcast) items2.get(0)).getStatus().getLifeCycleStatus());
                if (((LiveStream) items.get(0)).getStatus().getStreamStatus().equals("active") && ((LiveBroadcast) items2.get(0)).getStatus().getLifeCycleStatus().equals("ready")) {
                    streamStatus = ((LiveStream) items.get(0)).getStatus().getStreamStatus();
                    message = new Message();
                    message.what = 2;
                    a.getInstance().setStage(2);
                    bundle = new Bundle();
                    bundle.putString("MessageContent", streamStatus);
                    message.setData(bundle);
                    youtubeLiveActivity.this.handler.sendMessage(message);
                }
                if (((LiveBroadcast) items2.get(0)).getStatus().getLifeCycleStatus().equals("testStarting")) {
                    streamStatus = ((LiveBroadcast) items2.get(0)).getStatus().getLifeCycleStatus();
                    message = new Message();
                    message.what = 3;
                    bundle = new Bundle();
                    bundle.putString("MessageContent", streamStatus);
                    message.setData(bundle);
                    youtubeLiveActivity.this.handler.sendMessage(message);
                }
                if (((LiveBroadcast) items2.get(0)).getStatus().getLifeCycleStatus().equals("testing")) {
                    streamStatus = ((LiveBroadcast) items2.get(0)).getStatus().getLifeCycleStatus();
                    message = new Message();
                    message.what = 4;
                    a.getInstance().setStage(4);
                    bundle = new Bundle();
                    bundle.putString("MessageContent", streamStatus);
                    message.setData(bundle);
                    youtubeLiveActivity.this.handler.sendMessage(message);
                }
                if (((LiveBroadcast) items2.get(0)).getStatus().getLifeCycleStatus().equals("live")) {
                    c.a().e(new dji.pilot.f.a.a(dji.pilot.f.a.a.x));
                    c.a().e(new dji.pilot.f.a.a(5));
                    streamStatus = ((LiveBroadcast) items2.get(0)).getStatus().getLifeCycleStatus();
                    message = new Message();
                    message.what = 5;
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("MessageContent", streamStatus);
                    message.setData(bundle2);
                    youtubeLiveActivity.this.handler.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        setContentView(R.layout.liveshare_youtube_live);
        this.credential = GoogleAccountCredential.usingOAuth2(this, dji.thirdparty.afinal.c.c.a(SCOPES));
        Intent intent = getIntent();
        if (intent.getStringExtra("title") != null) {
            this.broadcastTitle = intent.getStringExtra("title");
            this.broadcastDesc = intent.getStringExtra("description");
            this.privacyStatus = intent.getStringExtra("privacyStatus");
            this.mEmail = intent.getStringExtra("mEmail");
            b.getInstance().setBroadcastTitle(this.broadcastTitle);
            b.getInstance().setBroadcastDesc(this.broadcastDesc);
        }
        this.broadcastTitle = b.getInstance().getBroadcastTitle();
        this.broadcastDesc = b.getInstance().getBroadcastDesc();
        ((TextView) findViewById(R.id.azx)).setText(this.broadcastTitle);
        ((TextView) findViewById(R.id.azy)).setText(this.broadcastDesc);
        this.linkshare = (TextView) findViewById(R.id.b09);
        this.start = (Button) findViewById(R.id.b04);
        this.encoding = (Button) findViewById(R.id.b01);
        this.done = (Button) findViewById(R.id.azv);
        this.mStatuText = (TextView) findViewById(R.id.b08);
        this.share = (Button) findViewById(R.id.b0_);
        this.stop = (Button) findViewById(R.id.b0a);
        this.mLiveStreamingTagLy = (LinearLayout) findViewById(R.id.b06);
        this.mLiveStreamingTagLy.setVisibility(4);
        initListeners();
        this.stop.setOnClickListener(this.mWidgetClickListener);
        this.encoding.setOnClickListener(this.mWidgetClickListener);
        this.encoding.setEnabled(false);
        this.start.setOnClickListener(this.mWidgetClickListener);
        this.start.setEnabled(false);
        this.done.setOnClickListener(this.mWidgetClickListener);
        this.mLiveProcessImg = (ImageView) findViewById(R.id.b02);
        this.mLiveProcessImg.setVisibility(4);
        this.mLiveProcessImg2 = (ImageView) findViewById(R.id.b05);
        this.mLiveProcessImg2.setVisibility(4);
        this.share.setOnClickListener(this.mWidgetClickListener);
        Drawable background;
        if (a.getInstance().getStage() == -1) {
            this.mLiveProcessImg.setVisibility(0);
            background = this.mLiveProcessImg.getBackground();
            if (background instanceof AnimationDrawable) {
                ((AnimationDrawable) background).start();
            }
        } else if (a.getInstance().getStage() == 1) {
            this.mLiveProcessImg.setVisibility(0);
            background = this.mLiveProcessImg.getBackground();
            if (background instanceof AnimationDrawable) {
                ((AnimationDrawable) background).start();
            }
            c.a().e(new dji.pilot.f.a.a(512));
        } else if (a.getInstance().getStage() == 2) {
            this.encoding.setEnabled(true);
            this.start.setEnabled(false);
            this.mStatuText.setText(getResources().getString(R.string.liveshare_youtube_live_stream_status_label_02));
            c.a().e(new dji.pilot.f.a.a(514));
        } else if (a.getInstance().getStage() == 3) {
            this.encoding.setEnabled(false);
            this.start.setEnabled(false);
            this.mLiveProcessImg2.setVisibility(0);
            background = this.mLiveProcessImg2.getBackground();
            if (background instanceof AnimationDrawable) {
                ((AnimationDrawable) background).start();
            }
            this.mStatuText.setText(getResources().getString(R.string.liveshare_youtube_live_stream_status_label_03));
            c.a().e(new dji.pilot.f.a.a(dji.pilot.f.a.a.v));
        } else if (a.getInstance().getStage() == 4) {
            this.encoding.setEnabled(false);
            this.start.setEnabled(true);
            this.mStatuText.setText(getResources().getString(R.string.liveshare_youtube_live_stream_status_label_04));
            c.a().e(new dji.pilot.f.a.a(dji.pilot.f.a.a.w));
        } else if (a.getInstance().getStage() == 5) {
            this.encoding.setEnabled(false);
            this.start.setEnabled(true);
            if (b.getInstance().getStreamBeginTime() != -1) {
                this.mStatuText.setText(getResources().getString(R.string.liveshare_youtube_live_stream_status_label_05));
                this.start.setText(getResources().getString(R.string.liveshare_youtube_live_step2_btn_stop_title));
                this.start.setBackground(getResources().getDrawable(R.drawable.live_red_btn_bg));
            }
            c.a().e(new dji.pilot.f.a.a(dji.pilot.f.a.a.x));
            this.isFinishSelf = false;
        }
        if (a.getInstance().getWatchUrl() != null) {
            this.linkshare.setText(a.getInstance().getWatchUrl());
            this.watchUrl = a.getInstance().getWatchUrl();
        } else {
            this.watchUrl = null;
        }
        this.handler = new Handler() {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1:
                        youtubeLiveActivity.this.urlSource = a.getInstance().getReturnedBroadcast().getContentDetails().getMonitorStream().getEmbedHtml();
                        youtubeLiveActivity dji_pilot_liveshare_Youtube_youtubeLiveActivity = youtubeLiveActivity.this;
                        a.getInstance();
                        dji_pilot_liveshare_Youtube_youtubeLiveActivity.watchUrl = a.getMatcher("www\\.youtube\\.com/embed/.{11}", youtubeLiveActivity.this.urlSource);
                        youtubeLiveActivity.this.linkshare.setText(youtubeLiveActivity.this.watchUrl);
                        a.getInstance().setWatchUrl(youtubeLiveActivity.this.watchUrl);
                        youtubeLiveActivity.this.timer = new Timer();
                        youtubeLiveActivity.this.timer.schedule(new a(), 1, ToolTipPopup.a);
                        c.a().e(new dji.pilot.f.a.a(512));
                        return;
                    case 2:
                        message.getData().get("MessageContent").toString();
                        youtubeLiveActivity.this.encoding.setEnabled(true);
                        youtubeLiveActivity.this.start.setEnabled(false);
                        youtubeLiveActivity.this.mStatuText.setText(youtubeLiveActivity.this.getResources().getString(R.string.liveshare_youtube_live_stream_status_label_02));
                        youtubeLiveActivity.this.mLiveProcessImg.setVisibility(4);
                        c.a().e(new dji.pilot.f.a.a(514));
                        return;
                    case 3:
                        youtubeLiveActivity.this.encoding.setEnabled(false);
                        youtubeLiveActivity.this.start.setEnabled(false);
                        youtubeLiveActivity.this.mLiveProcessImg2.setVisibility(0);
                        Drawable background = youtubeLiveActivity.this.mLiveProcessImg2.getBackground();
                        if (background instanceof AnimationDrawable) {
                            ((AnimationDrawable) background).start();
                        }
                        c.a().e(new dji.pilot.f.a.a(dji.pilot.f.a.a.v));
                        return;
                    case 4:
                        youtubeLiveActivity.this.mLiveProcessImg2.setVisibility(4);
                        youtubeLiveActivity.this.encoding.setEnabled(false);
                        youtubeLiveActivity.this.start.setEnabled(true);
                        youtubeLiveActivity.this.mStatuText.setText(youtubeLiveActivity.this.getResources().getString(R.string.liveshare_youtube_live_stream_status_label_04));
                        c.a().e(new dji.pilot.f.a.a(dji.pilot.f.a.a.w));
                        return;
                    case 5:
                        a.getInstance().setStage(5);
                        youtubeLiveActivity.this.mLiveProcessImg.setVisibility(4);
                        youtubeLiveActivity.this.mLiveProcessImg2.setVisibility(4);
                        youtubeLiveActivity.this.encoding.setEnabled(false);
                        youtubeLiveActivity.this.start.setEnabled(true);
                        if (b.getInstance().getStreamBeginTime() == -1) {
                            b.getInstance().setStreamBeginTime();
                        }
                        if (b.getInstance().getStreamBeginTime() != -1) {
                            youtubeLiveActivity.this.start.setEnabled(true);
                            youtubeLiveActivity.this.start.setBackground(youtubeLiveActivity.this.getResources().getDrawable(R.drawable.live_red_btn_bg));
                            youtubeLiveActivity.this.start.setText(youtubeLiveActivity.this.getResources().getString(R.string.liveshare_youtube_live_step2_btn_stop_title));
                            youtubeLiveActivity.this.mStatuText.setText(youtubeLiveActivity.this.getResources().getString(R.string.liveshare_youtube_live_stream_status_label_05));
                        }
                        youtubeLiveActivity.this.timer.cancel();
                        if (youtubeLiveActivity.this.isFinishSelf) {
                            youtubeLiveActivity.this.finish();
                            return;
                        }
                        return;
                    case 6:
                        youtubeLiveActivity.this.checkDialog();
                        c.a().e(new dji.pilot.f.a.a(513));
                        return;
                    case 7:
                        youtubeLiveActivity.this.showMessage(null, (String) message.obj);
                        return;
                    default:
                        return;
                }
            }
        };
        if (b.getInstance().isRunning()) {
            this.timer = new Timer();
            this.timer.schedule(new a(), 1, ToolTipPopup.a);
            return;
        }
        new Thread(new Runnable() {
            public void run() {
                int createYTBroadcast;
                youtubeLiveActivity.this.mBroadcastManager = a.getInstance();
                youtubeLiveActivity.this.mBroadcastManager.setTitle(youtubeLiveActivity.this.broadcastTitle);
                youtubeLiveActivity.this.mBroadcastManager.setDescription(youtubeLiveActivity.this.broadcastDesc);
                youtubeLiveActivity.this.mBroadcastManager.setPrivacy(youtubeLiveActivity.this.privacyStatus);
                b.getInstance().setLaunch(true);
                if (youtubeLiveActivity.this.mBroadcastManager.getReturnedBroadcast() == null || youtubeLiveActivity.this.mBroadcastManager.getReturnedStream() == null) {
                    createYTBroadcast = youtubeLiveActivity.this.mBroadcastManager.createYTBroadcast();
                } else {
                    createYTBroadcast = 0;
                }
                Message message;
                if (createYTBroadcast == 0) {
                    youtubeLiveActivity.this.returnedStream = youtubeLiveActivity.this.mBroadcastManager.getReturnedStream();
                    youtubeLiveActivity.this.returnedBroadcast = youtubeLiveActivity.this.mBroadcastManager.getReturnedBroadcast();
                    youtubeLiveActivity.this.mLiveStreaming = b.getInstance();
                    DJILogHelper.getInstance().LOGD("", "basicmode stream" + youtubeLiveActivity.this.mBroadcastManager.getReturnedStream(), false, true);
                    DJILogHelper.getInstance().LOGD("", "basicmode broadcast" + youtubeLiveActivity.this.mBroadcastManager.getReturnedBroadcast(), false, true);
                    youtubeLiveActivity.this.mLiveStreaming.setUrl(youtubeLiveActivity.this.returnedStream.getCdn().getIngestionInfo().getIngestionAddress() + d.t + youtubeLiveActivity.this.returnedStream.getCdn().getIngestionInfo().getStreamName());
                    youtubeLiveActivity.this.mLiveStreaming.setStreamMode(0);
                    createYTBroadcast = youtubeLiveActivity.this.mLiveStreaming.startStream();
                    if (createYTBroadcast == 0) {
                        message = new Message();
                        message.what = 1;
                        a.getInstance().setStage(1);
                        youtubeLiveActivity.this.handler.sendMessage(message);
                    } else if (createYTBroadcast == -2) {
                        youtubeLiveActivity.this.handler.sendMessage(youtubeLiveActivity.this.handler.obtainMessage(7, youtubeLiveActivity.this.getString(R.string.liveshare_streaminiterror)));
                        b.getInstance().setLaunch(false);
                        c.a().e(new dji.pilot.f.a.a(16));
                    }
                } else if (HttpStatusCodes.STATUS_CODE_FORBIDDEN == createYTBroadcast) {
                    message = new Message();
                    message.what = 6;
                    youtubeLiveActivity.this.handler.sendMessage(message);
                    b.getInstance().setLaunch(false);
                    c.a().e(new dji.pilot.f.a.a(16));
                }
            }
        }).start();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    private void initListeners() {
        this.mWidgetClickListener = new OnClickListener() {
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.b01) {
                    dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_BasicModeView_ShareView_Button_StartEncoder");
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                a.getInstance();
                                a.getYt().liveBroadcasts().transition("testing", a.getInstance().getReturnedBroadcast().getId(), "id,snippet,status").execute();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    youtubeLiveActivity.this.mStatuText.setText(youtubeLiveActivity.this.getResources().getString(R.string.liveshare_youtube_live_stream_status_label_03));
                    youtubeLiveActivity.this.encoding.setEnabled(false);
                    a.getInstance().setStage(3);
                } else if (id == R.id.b04) {
                    if (a.getInstance().getStage() == 5) {
                        dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_BasicModeView_ShareView_Button_StopStreaming");
                        if (youtubeLiveActivity.this.mStopDlg == null) {
                            youtubeLiveActivity.this.mStopDlg = new dji.pilot2.a(youtubeLiveActivity.this);
                            youtubeLiveActivity.this.mStopDlg.b(youtubeLiveActivity.this.getString(R.string.liveshare_youtube_live_stream_stop_remind));
                            youtubeLiveActivity.this.mStopDlg.c(youtubeLiveActivity.this.getString(R.string.ve_cancel));
                            youtubeLiveActivity.this.mStopDlg.d(youtubeLiveActivity.this.getString(R.string.ok));
                            youtubeLiveActivity.this.mStopDlg.b();
                            youtubeLiveActivity.this.mStopDlg.a(new dji.pilot2.a.a() {
                                public void onRightBtnClick() {
                                    b.getInstance().stopStream();
                                    b.getInstance().setLaunch(false);
                                    a.getInstance().finishYTBroadcast();
                                    c.a().e(youtubeLiveActivity.this);
                                    c.a().e(new dji.pilot.f.a.a(dji.pilot.f.a.a.y));
                                    youtubeLiveActivity.this.finish();
                                }

                                public void onLeftBtnClick() {
                                }
                            });
                            youtubeLiveActivity.this.mStopDlg.a(0.4f);
                        }
                        if (!youtubeLiveActivity.this.mStopDlg.isShowing()) {
                            youtubeLiveActivity.this.mStopDlg.show();
                            return;
                        }
                        return;
                    }
                    dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_BasicModeView_ShareView_Button_StartStreaming");
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                a.getInstance();
                                a.getYt().liveBroadcasts().transition("live", a.getInstance().getReturnedBroadcast().getId(), "id,snippet,status").execute();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    youtubeLiveActivity.this.mLiveProcessImg2.setVisibility(0);
                    Drawable background = youtubeLiveActivity.this.mLiveProcessImg2.getBackground();
                    if (background instanceof AnimationDrawable) {
                        ((AnimationDrawable) background).start();
                    }
                    youtubeLiveActivity.this.start.setEnabled(false);
                } else if (id == R.id.azv) {
                    dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_BasicModeView_ShareView_Button_Done");
                    c.a().e(youtubeLiveActivity.this);
                    if (youtubeLiveActivity.this.timer != null) {
                        youtubeLiveActivity.this.timer.cancel();
                    }
                    youtubeLiveActivity.this.finish();
                } else if (id == R.id.b0_) {
                    dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_BasicModeView_ShareView_Button_ShareLink");
                    if (youtubeLiveActivity.this.watchUrl != null) {
                        youtubeLiveActivity.this.shareLink(youtubeLiveActivity.this.getString(R.string.youtube_share), youtubeLiveActivity.this.watchUrl);
                    }
                } else if (id == R.id.b0a) {
                    if (youtubeLiveActivity.this.mStopDlg == null) {
                        youtubeLiveActivity.this.mStopDlg = new dji.pilot2.a(youtubeLiveActivity.this);
                        youtubeLiveActivity.this.mStopDlg.b(youtubeLiveActivity.this.getString(R.string.liveshare_youtube_live_stream_stop_remind));
                        youtubeLiveActivity.this.mStopDlg.c(youtubeLiveActivity.this.getString(R.string.ve_cancel));
                        youtubeLiveActivity.this.mStopDlg.d(youtubeLiveActivity.this.getString(R.string.ok));
                        youtubeLiveActivity.this.mStopDlg.b();
                        youtubeLiveActivity.this.mStopDlg.a(new dji.pilot2.a.a() {
                            public void onRightBtnClick() {
                                b.getInstance().stopStream();
                                b.getInstance().setLaunch(false);
                                a.getInstance().finishYTBroadcast();
                                c.a().e(youtubeLiveActivity.this);
                                c.a().e(new dji.pilot.f.a.a(dji.pilot.f.a.a.y));
                                youtubeLiveActivity.this.finish();
                            }

                            public void onLeftBtnClick() {
                            }
                        });
                        youtubeLiveActivity.this.mStopDlg.a(0.4f);
                    }
                    if (!youtubeLiveActivity.this.mStopDlg.isShowing()) {
                        youtubeLiveActivity.this.mStopDlg.show();
                    }
                }
            }
        };
    }

    protected void checkDialog() {
        Builder builder = new Builder(this);
        builder.setTitle(R.string.liveshare_basicmode_accountcheck);
        builder.setNegativeButton(R.string.liveshare_basicmode_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                youtubeLiveActivity.this.finish();
            }
        });
        builder.create().show();
    }

    private void shareLink(String str, String str2) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", str);
        intent.putExtra("android.intent.extra.TEXT", str2);
        intent.setFlags(268435456);
        startActivity(Intent.createChooser(intent, getString(R.string.app_share)));
    }

    protected void onResume() {
        super.onResume();
        this.paused = false;
    }

    protected void onPause() {
        super.onPause();
        this.paused = true;
    }

    public void showMessage(final String str, final String str2) {
        this.handler.postDelayed(new Runnable() {
            public void run() {
                if (!youtubeLiveActivity.this.paused) {
                    Builder builder = new Builder(youtubeLiveActivity.this);
                    builder.setTitle(str).setMessage(str2).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.create().show();
                }
            }
        }, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
    }
}
