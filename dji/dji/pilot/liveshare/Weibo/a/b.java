package dji.pilot.liveshare.Weibo.a;

import android.app.Activity;
import android.util.Log;
import com.facebook.internal.ab;
import com.google.api.client.http.HttpMethods;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import dji.pilot.f.a.a;
import dji.pilot.liveshare.Weibo.b.d;
import dji.thirdparty.a.c;
import org.json.JSONException;
import org.json.JSONObject;

public class b implements RequestListener {
    private static final String TAG = b.class.getName();
    private static b mInstance;
    private String access_Token;
    private String live_id;
    private Activity mActivity;
    private String mLiveHeight = "720";
    private dji.pilot.liveshare.b mLiveStreaming = dji.pilot.liveshare.b.getInstance();
    private String mLiveSummary = "";
    private String mLiveTitle = "微博直播";
    private String mLiveWidth = "1280";
    private int published = 0;
    private String room_id;
    private String rtmp_url;
    private AsyncWeiboRunner weiboRunner;

    public static b getInstance(Activity activity) {
        if (mInstance == null) {
            mInstance = new b(activity);
        }
        return mInstance;
    }

    private b(Activity activity) {
        this.mActivity = activity;
        init();
    }

    private void init() {
        this.weiboRunner = new AsyncWeiboRunner(this.mActivity);
    }

    public void createLiveUrl() {
        Log.d(TAG, "Create Live Url!!!");
        WeiboParameters weiboParameters = new WeiboParameters(d.APP_KEY);
        this.access_Token = dji.pilot.liveshare.Weibo.b.b.readAccessToken(this.mActivity).getToken();
        weiboParameters.put("access_token", this.access_Token);
        weiboParameters.put("title", this.mLiveTitle);
        weiboParameters.put("width", this.mLiveWidth);
        weiboParameters.put("height", this.mLiveHeight);
        if (!this.mLiveSummary.equals("")) {
            weiboParameters.put("summary", this.mLiveSummary);
        }
        weiboParameters.put("published", this.published);
        this.weiboRunner.requestAsync("https://api.weibo.com/2/proxy/live/create", weiboParameters, HttpMethods.POST, this);
        this.mLiveSummary = "";
        a.C = "";
    }

    public void updateStreamStatus() {
        a.a();
        WeiboParameters weiboParameters = new WeiboParameters(d.APP_KEY);
        this.access_Token = dji.pilot.liveshare.Weibo.b.b.readAccessToken(this.mActivity).getToken();
        weiboParameters.put("access_token", this.access_Token);
        weiboParameters.put("id", this.live_id);
        weiboParameters.put("stop", 1);
        this.weiboRunner.requestAsync("https://api.weibo.com/2/proxy/live/update", weiboParameters, HttpMethods.POST, new RequestListener() {
            public void onComplete(String str) {
                Log.d(b.TAG, "直播结束");
            }

            public void onWeiboException(WeiboException weiboException) {
            }
        });
    }

    public void onComplete(String str) {
        Log.d(TAG, str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.rtmp_url = jSONObject.getString("url");
            this.live_id = jSONObject.getString("id");
            this.room_id = jSONObject.getString("room_id");
            Log.d(TAG, "url: " + this.rtmp_url + " live_id: " + this.live_id);
            handleLiveThread();
            c.a().e(new a(5));
            new Thread(new Runnable() {
                public void run() {
                    WeiboParameters weiboParameters = new WeiboParameters(d.APP_KEY);
                    b.this.access_Token = dji.pilot.liveshare.Weibo.b.b.readAccessToken(b.this.mActivity).getToken();
                    weiboParameters.put("access_token", b.this.access_Token);
                    weiboParameters.put("room_id", b.this.room_id);
                    b.this.weiboRunner.requestAsync("https://api.weibo.com/2/liveim/message/pull.stream", weiboParameters, HttpMethods.GET, new RequestListener() {
                        public void onComplete(String str) {
                            Log.d(b.TAG, "onComplete: " + str);
                        }

                        public void onWeiboException(WeiboException weiboException) {
                        }
                    });
                }
            }).start();
        } catch (JSONException e) {
            e.printStackTrace();
            try {
                a aVar = new a(9);
                aVar.L = new JSONObject(str).getInt(ab.an);
                c.a().e(aVar);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void onWeiboException(WeiboException weiboException) {
        Log.d(TAG, weiboException.getMessage());
    }

    public void handleLiveThread() {
        new Thread(new Runnable() {
            public void run() {
                b.this.startStreaming();
                c.a().e(new a(6));
            }
        }).start();
        this.mLiveStreaming.setLaunch(true);
    }

    public void startStreaming() {
        this.mLiveStreaming.setPrimaryServerUrl(this.rtmp_url);
        this.mLiveStreaming.setUrl(this.rtmp_url);
        this.mLiveStreaming.setStreamMode(2);
        this.mLiveStreaming.setStreamBeginTime();
        this.mLiveStreaming.startStream();
    }

    public void setSummary(String str) {
        this.mLiveSummary = str;
    }

    public void setPublished(String str) {
        if (str.equals("public")) {
            this.published = 0;
        } else if (str.equals(a.B)) {
            this.published = 1;
        }
    }
}
