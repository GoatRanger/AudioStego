package dji.pilot.liveshare.Weibo.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

public class b {
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_AVATAR_URL = "avatar_url";
    private static final String KEY_EXPIRES_IN = "expires_in";
    private static final String KEY_UID = "uid";
    private static final String KEY_USERNAME = "name";
    private static final String PREFERENCES_NAME = "com_weibo_sdk_android";

    public static void writeAccessToken(Context context, Oauth2AccessToken oauth2AccessToken) {
        if (context != null && oauth2AccessToken != null) {
            Editor edit = context.getSharedPreferences(PREFERENCES_NAME, 32768).edit();
            edit.putString("uid", oauth2AccessToken.getUid());
            edit.putString("access_token", oauth2AccessToken.getToken());
            edit.putLong("expires_in", oauth2AccessToken.getExpiresTime());
            edit.commit();
        }
    }

    public static void writeInfo(Context context, String str, String str2) {
        if (context != null) {
            Editor edit = context.getSharedPreferences(PREFERENCES_NAME, 32768).edit();
            edit.putString("name", str);
            edit.putString("avatar_url", str2);
            edit.commit();
        }
    }

    public static Oauth2AccessToken readAccessToken(Context context) {
        if (context == null) {
            return null;
        }
        Oauth2AccessToken oauth2AccessToken = new Oauth2AccessToken();
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, 32768);
        oauth2AccessToken.setUid(sharedPreferences.getString("uid", ""));
        oauth2AccessToken.setToken(sharedPreferences.getString("access_token", ""));
        oauth2AccessToken.setExpiresTime(sharedPreferences.getLong("expires_in", 0));
        return oauth2AccessToken;
    }

    public static String readUserName(Context context) {
        if (context == null) {
            return "";
        }
        return context.getSharedPreferences(PREFERENCES_NAME, 32768).getString("name", "");
    }

    public static String readUserAvatar(Context context) {
        if (context == null) {
            return "";
        }
        return context.getSharedPreferences(PREFERENCES_NAME, 32768).getString("avatar_url", "");
    }

    public static void clear(Context context) {
        if (context != null) {
            Editor edit = context.getSharedPreferences(PREFERENCES_NAME, 32768).edit();
            edit.clear();
            edit.commit();
        }
    }
}
