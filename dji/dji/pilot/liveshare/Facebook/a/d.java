package dji.pilot.liveshare.Facebook.a;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.login.f;
import com.facebook.share.internal.n;
import com.facebook.v;
import com.facebook.w;
import dji.pilot.f.a.a;
import dji.pilot.liveshare.b;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    private static final String PRIVACY_ALL_FRIENDS = "{\"value\":\"ALL_FRIENDS\"}";
    private static final String PRIVACY_EVERYONE = "{\"value\":\"EVERYONE\"}";
    private static final String PRIVACY_SELF = "{\"value\":\"SELF\"}";
    private String liveUrl;
    private String liveUrl_secure;
    private Activity mActivity;
    private String mDescription = "";
    private b mLiveStreaming = b.getInstance();
    private List<String> publishPermission = new ArrayList();
    private int published = 0;
    private String video_id;

    public d(Activity activity) {
        this.mActivity = activity;
    }

    private void requestPublishPermission(Activity activity) {
        f.getInstance().b(activity, this.publishPermission);
    }

    public void checkPermission() {
        this.publishPermission.clear();
        if (!AccessToken.a().e().contains("publish_actions")) {
            this.publishPermission.add("publish_actions");
        }
        if (!AccessToken.a().e().contains("publish_pages")) {
            this.publishPermission.add("publish_pages");
        }
        if (!AccessToken.a().e().contains("manage_pages")) {
            this.publishPermission.add("manage_pages");
        }
        if (this.publishPermission.size() != 0) {
            Log.d("FBLive", "request publish permission");
            requestPublishPermission(this.mActivity);
            return;
        }
        Log.d("FBLive", "all publish permission ready");
        c.a().e(new a(4));
    }

    public void createLiveVideoUrl(final c cVar) {
        Log.d("FB", "createLiveVideoUrl");
        Log.d("FB", "Permission: " + AccessToken.a().e().toString());
        Log.d("FB", AccessToken.a().j());
        Bundle bundle = new Bundle();
        bundle.putString("description", this.mDescription);
        bundle.putString("published", "true");
        String str = PRIVACY_SELF;
        if (a.E.equals("public")) {
            str = PRIVACY_EVERYONE;
        } else if (a.E.equals(a.A)) {
            str = PRIVACY_ALL_FRIENDS;
        } else if (a.E.equals(a.B)) {
            str = PRIVACY_SELF;
        }
        Log.d("FBLive", "privacy: " + str);
        bundle.putString(n.p, str);
        new GraphRequest(AccessToken.a(), dji.pilot.usercenter.protocol.d.t + AccessToken.a().j() + "/live_videos", bundle, w.b, new GraphRequest.b() {
            public void onCompleted(v vVar) {
                Log.d("FBLive", vVar.toString());
                try {
                    JSONObject b = vVar.b();
                    if (b == null) {
                        cVar.onFailure(vVar.toString());
                        return;
                    }
                    d.this.video_id = b.getString("id");
                    d.this.liveUrl_secure = b.getString("secure_stream_url");
                    d.this.liveUrl = b.getString("stream_url");
                    List arrayList = new ArrayList();
                    arrayList.add(d.this.liveUrl_secure);
                    arrayList.add(d.this.liveUrl);
                    Log.d("FBLive", "rtmpUrl1 " + d.this.liveUrl_secure);
                    Log.d("FBLive", "rtmpUrl2 " + d.this.liveUrl);
                    cVar.onCreateLiveVideoUrl(d.this.video_id, arrayList);
                    if (a.G != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("place", a.G);
                        new GraphRequest(AccessToken.a(), dji.pilot.usercenter.protocol.d.t + d.this.video_id, bundle, w.b, new GraphRequest.b() {
                            public void onCompleted(v vVar) {
                            }
                        }).n();
                    }
                } catch (Exception e) {
                    Log.d("FB", e.getMessage());
                    e.printStackTrace();
                    d.this.requestPublishPermission(d.this.mActivity);
                }
            }
        }).n();
    }

    public void startStreaming() {
        this.mLiveStreaming.setPrimaryServerUrl(this.liveUrl);
        this.mLiveStreaming.setUrl(this.liveUrl);
        this.mLiveStreaming.setStreamMode(2);
        this.mLiveStreaming.startStream();
        this.mLiveStreaming.setStreamBeginTime();
    }

    public void previewStream() {
        Log.d("FBLive", "Token:" + AccessToken.a().toString());
        Bundle bundle = new Bundle();
        bundle.putString(GraphRequest.c, "preview_url");
        new GraphRequest(AccessToken.a(), dji.pilot.usercenter.protocol.d.t + this.video_id, bundle, w.a, new GraphRequest.b() {
            public void onCompleted(v vVar) {
                try {
                    Log.e("FBLive", "URL: " + vVar.b().getString("preview_url"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).n();
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public void setPublished(boolean z) {
        if (z) {
            this.published = 0;
        } else {
            this.published = 1;
        }
    }
}
