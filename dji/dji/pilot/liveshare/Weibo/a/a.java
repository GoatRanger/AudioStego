package dji.pilot.liveshare.Weibo.a;

import android.os.Bundle;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;

public class a implements WeiboAuthListener {
    private Oauth2AccessToken mAccessToken;

    public void onComplete(Bundle bundle) {
        this.mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
    }

    public void onWeiboException(WeiboException weiboException) {
    }

    public void onCancel() {
    }
}
