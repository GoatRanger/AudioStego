package dji.pilot.liveshare.Facebook.a;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.h;
import com.facebook.internal.ah;
import com.facebook.k;
import com.facebook.login.g;
import com.facebook.login.widget.LoginButton;
import dji.pilot.usercenter.mode.n;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class a {
    private AccessToken currentAccessToken = null;
    private Profile currentProfile = null;
    private boolean isFetch = false;
    public h<g> loginResultFacebookCallback = null;
    private Activity mActivity;
    private LoginButton mLoginBtn = null;
    private b mLoginStateListener = null;
    private List<String> readPermission = new ArrayList();

    public a(LoginButton loginButton, Activity activity) {
        this.mLoginBtn = loginButton;
        this.mActivity = activity;
    }

    public void init(b bVar) {
        this.currentAccessToken = AccessToken.a();
        this.currentProfile = Profile.a();
        this.mLoginStateListener = bVar;
        requestReadPermission();
        initCallback();
    }

    public void unInit() {
        this.mLoginBtn = null;
        this.mActivity = null;
        this.mLoginStateListener = null;
    }

    public boolean checkLogin() {
        return (this.currentProfile == null || this.currentAccessToken == null) ? false : true;
    }

    private void requestReadPermission() {
        this.readPermission.add("public_profile");
        this.readPermission.add("user_posts");
        this.mLoginBtn.setReadPermissions(this.readPermission);
    }

    private void initCallback() {
        Log.d("FBLive", "init callback");
        this.loginResultFacebookCallback = new h<g>() {
            public void onSuccess(g gVar) {
                if (Profile.a() == null && !a.this.isFetch) {
                    Log.d("FBLive", "profile null");
                    a.this.fetchProfile();
                    a.this.mLoginStateListener.onLoginSuccess();
                } else if (AccessToken.a().e().contains("publish_actions") && AccessToken.a().e().contains("publish_pages") && AccessToken.a().e().contains("manage_pages")) {
                    Log.d("FBLive", "all publish permission ready");
                    c.a().e(new dji.pilot.f.a.a(4));
                }
            }

            public void onCancel() {
                Log.d("FBLive", "login Cancel");
                a.this.mLoginStateListener.onLoginError();
            }

            public void onError(k kVar) {
                Log.d("FBLive", "login error: " + kVar.toString());
                a.this.mLoginStateListener.onLoginError();
            }
        };
    }

    public void fetchProfile() {
        AccessToken a = AccessToken.a();
        if (a == null) {
            Profile.a(null);
        } else {
            ah.a(a.c(), new ah.c() {
                public void onSuccess(JSONObject jSONObject) {
                    String optString = jSONObject.optString("id");
                    if (optString != null) {
                        String optString2 = jSONObject.optString("link");
                        Profile.a(new Profile(optString, jSONObject.optString(n.aa), jSONObject.optString("middle_name"), jSONObject.optString(n.ab), jSONObject.optString("name"), optString2 != null ? Uri.parse(optString2) : null));
                        a.this.isFetch = true;
                        a.this.mLoginStateListener.onFetchInfoSuccess("facebook");
                    }
                }

                public void onFailure(k kVar) {
                    if (a.this.mLoginStateListener != null) {
                        a.this.mLoginStateListener.onFetchInfoFail();
                    }
                }
            });
        }
    }

    public void logout() {
        AccessToken.a(null);
        Profile.a(null);
    }
}
