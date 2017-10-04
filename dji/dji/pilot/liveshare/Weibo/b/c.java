package dji.pilot.liveshare.Weibo.b;

import android.content.Context;
import android.util.SparseArray;
import com.google.api.client.http.HttpMethods;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;

public class c extends a {
    private static final String API_BASE_URL = "https://api.weibo.com/2/users";
    private static final int READ_USER = 0;
    private static final int READ_USER_BY_DOMAIN = 1;
    private static final int READ_USER_COUNT = 2;
    private static final SparseArray<String> sAPIList = new SparseArray();

    static {
        sAPIList.put(0, "https://api.weibo.com/2/users/show.json");
        sAPIList.put(1, "https://api.weibo.com/2/users/domain_show.json");
        sAPIList.put(2, "https://api.weibo.com/2/users/counts.json");
    }

    public c(Context context, String str, Oauth2AccessToken oauth2AccessToken) {
        super(context, str, oauth2AccessToken);
    }

    public void show(long j, RequestListener requestListener) {
        WeiboParameters weiboParameters = new WeiboParameters(this.mAppKey);
        weiboParameters.put("uid", j);
        requestAsync((String) sAPIList.get(0), weiboParameters, HttpMethods.GET, requestListener);
    }

    public void show(String str, RequestListener requestListener) {
        WeiboParameters weiboParameters = new WeiboParameters(this.mAppKey);
        weiboParameters.put("screen_name", str);
        requestAsync((String) sAPIList.get(0), weiboParameters, HttpMethods.GET, requestListener);
    }

    public void domainShow(String str, RequestListener requestListener) {
        WeiboParameters weiboParameters = new WeiboParameters(this.mAppKey);
        weiboParameters.put("domain", str);
        requestAsync((String) sAPIList.get(1), weiboParameters, HttpMethods.GET, requestListener);
    }

    public void counts(long[] jArr, RequestListener requestListener) {
        requestAsync((String) sAPIList.get(2), buildCountsParams(jArr), HttpMethods.GET, requestListener);
    }

    public String showSync(long j) {
        WeiboParameters weiboParameters = new WeiboParameters(this.mAppKey);
        weiboParameters.put("uid", j);
        return requestSync((String) sAPIList.get(0), weiboParameters, HttpMethods.GET);
    }

    public String showSync(String str) {
        WeiboParameters weiboParameters = new WeiboParameters(this.mAppKey);
        weiboParameters.put("screen_name", str);
        return requestSync((String) sAPIList.get(0), weiboParameters, HttpMethods.GET);
    }

    public String domainShowSync(String str) {
        WeiboParameters weiboParameters = new WeiboParameters(this.mAppKey);
        weiboParameters.put("domain", str);
        return requestSync((String) sAPIList.get(1), weiboParameters, HttpMethods.GET);
    }

    public String countsSync(long[] jArr) {
        return requestSync((String) sAPIList.get(2), buildCountsParams(jArr), HttpMethods.GET);
    }

    private WeiboParameters buildCountsParams(long[] jArr) {
        WeiboParameters weiboParameters = new WeiboParameters(this.mAppKey);
        StringBuilder stringBuilder = new StringBuilder();
        for (long append : jArr) {
            stringBuilder.append(append).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        weiboParameters.put("uids", stringBuilder.toString());
        return weiboParameters;
    }
}
