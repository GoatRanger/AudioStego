package dji.pilot.liveshare.Weibo.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import dji.pilot.R;
import dji.pilot.f.a.a;
import dji.pilot.liveshare.Weibo.b.b;
import dji.pilot.liveshare.Weibo.b.d;
import dji.pilot.liveshare.base.view.LiveBaseLoginView;
import dji.thirdparty.a.c;

public class LiveWBLoginView extends LiveBaseLoginView implements WeiboAuthListener {
    private AuthInfo authInfo;
    public SsoHandler mSsoHandler;

    public LiveWBLoginView(Activity activity) {
        super(activity);
    }

    protected void initTypeWidget() {
        this.mPlatformLogoIv.setImageDrawable(getResources().getDrawable(R.drawable.live_logo_weibo));
        this.mFBLoginBtn.setVisibility(8);
        this.mWBLoginBtn.setVisibility(0);
    }

    protected void initLogin() {
        this.authInfo = new AuthInfo(this.mActivity, d.APP_KEY, d.REDIRECT_URL, d.SCOPE);
        this.mSsoHandler = new SsoHandler(this.mActivity, this.authInfo);
        this.mWBLoginBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                LiveWBLoginView.this.mSsoHandler.authorizeWeb(LiveWBLoginView.this);
            }
        });
        Oauth2AccessToken readAccessToken = b.readAccessToken(this.mActivity);
        if (readAccessToken != null && System.currentTimeMillis() < readAccessToken.getExpiresTime() && readAccessToken.isSessionValid()) {
            c.a().e(new a(1));
        }
    }

    protected void unInit() {
    }

    public void onComplete(Bundle bundle) {
        Oauth2AccessToken parseAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
        if (parseAccessToken == null || !parseAccessToken.isSessionValid()) {
            Log.d("LiveBaseLoginView", "login error code:" + bundle.getString("code", ""));
            return;
        }
        Log.d("LiveBaseLoginView", "Sina Token: " + parseAccessToken.getToken());
        b.writeAccessToken(this.mActivity.getApplicationContext(), parseAccessToken);
        c.a().e(new a(1));
    }

    public void onWeiboException(WeiboException weiboException) {
        Log.e("LiveBaseLoginView", weiboException.getMessage());
    }

    public void onCancel() {
    }
}
