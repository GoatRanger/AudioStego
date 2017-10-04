package dji.pilot.liveshare.Facebook.view;

import android.app.Activity;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.f;
import dji.pilot.R;
import dji.pilot.liveshare.Facebook.a.a;
import dji.pilot.liveshare.Facebook.a.b;
import dji.pilot.liveshare.base.view.LiveBaseLoginView;
import dji.thirdparty.a.c;

public class LiveFBLoginView extends LiveBaseLoginView implements b {
    public f callbackManager;
    private a mLoginManager;

    public LiveFBLoginView(Activity activity) {
        super(activity);
    }

    protected void initTypeWidget() {
        this.mPlatformLogoIv.setImageDrawable(getResources().getDrawable(R.drawable.live_facebook_login));
        this.mFBLoginBtn.setVisibility(0);
        this.mWBLoginBtn.setVisibility(8);
    }

    protected void initLogin() {
        this.mLoginManager = new a(this.mFBLoginBtn, this.mActivity);
        this.mLoginManager.init(this);
        this.callbackManager = f.a.a();
        this.mFBLoginBtn.registerCallback(this.callbackManager, this.mLoginManager.loginResultFacebookCallback);
        if (this.mLoginManager.checkLogin()) {
            onFetchInfoSuccess("cookie");
        }
    }

    protected void unInit() {
        this.mLoginManager.unInit();
    }

    protected void showLoadingLy() {
        this.mActivity.runOnUiThread(new Runnable() {
            public void run() {
                LiveFBLoginView.this.mLoadingLy.show();
            }
        });
    }

    protected void hideLoadingLy() {
        this.mActivity.runOnUiThread(new Runnable() {
            public void run() {
                LiveFBLoginView.this.mLoadingLy.go();
            }
        });
    }

    public void onLoginSuccess() {
        showLoadingLy();
    }

    public void onFetchInfoSuccess(String str) {
        hideLoadingLy();
        c.a().e(new dji.pilot.f.a.a(1));
    }

    public void onFetchInfoFail() {
        AccessToken.a(null);
        Profile.a(null);
    }

    public void onLoginError() {
    }
}
