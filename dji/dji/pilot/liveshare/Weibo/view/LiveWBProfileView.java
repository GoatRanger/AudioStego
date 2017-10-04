package dji.pilot.liveshare.Weibo.view;

import android.app.Activity;
import android.util.Log;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import dji.pilot.R;
import dji.pilot.f.a.a;
import dji.pilot.liveshare.Weibo.b.b;
import dji.pilot.liveshare.Weibo.b.c;
import dji.pilot.liveshare.Weibo.b.d;
import dji.pilot.liveshare.base.view.LiveBaseProfileView;
import org.json.JSONException;
import org.json.JSONObject;

public class LiveWBProfileView extends LiveBaseProfileView implements RequestListener {
    private String mAvatarUrl;
    private String mProfileName;
    private c mUsersAPI;

    public LiveWBProfileView(Activity activity) {
        super(activity);
    }

    protected void initTypeWidget() {
        this.mProfileLogoIv.setImageResource(R.drawable.live_logo_weibo_small);
        this.mStartBtn.setBackground(getResources().getDrawable(R.drawable.liveshare_weibo_start_btn));
        this.mProfileName = b.readUserName(this.mActivity);
        this.mAvatarUrl = b.readUserAvatar(this.mActivity);
        this.mNameTv.setText(this.mProfileName);
        ImageLoader.getInstance().displayImage(this.mAvatarUrl, this.mAvatarIv, this.options);
        fetchUserInfo();
    }

    protected void logout() {
        b.clear(this.mActivity);
        dji.thirdparty.a.c.a().e(new a(3));
    }

    protected void start() {
        dji.thirdparty.a.c.a().e(new a(4));
    }

    protected void fetchUserInfo() {
        Oauth2AccessToken readAccessToken = b.readAccessToken(this.mActivity);
        this.mUsersAPI = new c(this.mActivity, d.APP_KEY, readAccessToken);
        this.mUsersAPI.show(Long.parseLong(readAccessToken.getUid()), (RequestListener) this);
    }

    public void onComplete(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.mProfileName = jSONObject.getString("screen_name");
            this.mAvatarUrl = jSONObject.getString("profile_image_url");
            this.mNameTv.setText(this.mProfileName);
            ImageLoader.getInstance().displayImage(this.mAvatarUrl, this.mAvatarIv, this.options);
            b.writeInfo(this.mActivity, this.mProfileName, this.mAvatarUrl);
            Log.d("LiveBaseProfileView", "weibo -- " + this.mProfileName + this.mAvatarUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onWeiboException(WeiboException weiboException) {
        Log.d("LiveBaseProfileView", weiboException.getMessage());
    }
}
