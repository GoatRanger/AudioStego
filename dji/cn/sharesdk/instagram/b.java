package cn.sharesdk.instagram;

import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.a.a;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.e;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import com.sina.weibo.sdk.constant.WBConstants;
import dji.pilot.usercenter.protocol.d;
import java.io.File;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class b extends cn.sharesdk.framework.b {
    private static b b;
    private String c;
    private String d;
    private String e;
    private String[] f = new String[]{"follower_list"};
    private String g;

    public static b a(Platform platform) {
        if (b == null) {
            b = new b(platform);
        }
        return b;
    }

    private b(Platform platform) {
        super(platform);
    }

    public void a(String str, String str2, String str3) {
        this.c = str;
        this.d = str2;
        this.e = str3;
    }

    public void a(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            this.f = strArr;
        }
    }

    public cn.sharesdk.framework.authorize.b getAuthorizeWebviewClient(e eVar) {
        return new a(eVar);
    }

    public String getAuthorizeUrl() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://instagram.com/oauth/authorize/?");
        stringBuilder.append("client_id=").append(this.c);
        stringBuilder.append("&redirect_uri=").append(getRedirectUri());
        stringBuilder.append("&response_type=code");
        if (this.f != null && this.f.length > 0) {
            stringBuilder.append("&scope=").append(TextUtils.join("+", this.f));
        }
        ShareSDK.logApiEvent("/oauth/authorize", c());
        return stringBuilder.toString();
    }

    public String getRedirectUri() {
        return this.e;
    }

    public void a(AuthorizeListener authorizeListener) {
        b(authorizeListener);
    }

    public String a(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("client_id", this.c));
        arrayList.add(new KVPair(WBConstants.AUTH_PARAMS_CLIENT_SECRET, this.d));
        arrayList.add(new KVPair(WBConstants.AUTH_PARAMS_GRANT_TYPE, "authorization_code"));
        arrayList.add(new KVPair("redirect_uri", this.e));
        arrayList.add(new KVPair("code", str));
        return a.a().b("https://api.instagram.com/oauth/access_token", arrayList, "/oauth/authorize", c());
    }

    public void b(String str) {
        this.g = str;
    }

    public HashMap<String, Object> c(String str) throws Throwable {
        String str2 = "https://api.instagram.com/v1/users/" + str + d.t;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.g));
        arrayList.add(new KVPair("sig", a("/users/" + str, arrayList, this.d)));
        String a = a.a().a(str2, arrayList, "/v1/users", c());
        if (a == null || a.length() <= 0) {
            throw new Throwable("response is empty");
        }
        HashMap<String, Object> fromJson = new Hashon().fromJson(a);
        if (fromJson == null || fromJson.size() <= 0) {
            throw new Throwable("response is empty");
        }
        HashMap hashMap = (HashMap) fromJson.get("meta");
        if (hashMap == null || hashMap.size() <= 0) {
            throw new Throwable(a);
        } else if (200 != ((Integer) hashMap.get("code")).intValue()) {
            throw new Throwable(a);
        } else {
            hashMap = (HashMap) fromJson.get("data");
            if (hashMap != null && hashMap.size() > 0) {
                return fromJson;
            }
            throw new Throwable(a);
        }
    }

    public boolean a() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setPackage("com.instagram.android");
        intent.setType("image/*");
        if (this.a.getContext().getPackageManager().resolveActivity(intent, 0) != null) {
            return true;
        }
        return false;
    }

    public void a(String str, String str2, String str3, PlatformActionListener platformActionListener) {
        if (!TextUtils.isEmpty(str)) {
            a(str3, str, platformActionListener);
        } else if (!TextUtils.isEmpty(str2)) {
            try {
                String downloadBitmap = BitmapHelper.downloadBitmap(this.a.getContext(), str2);
                if (!TextUtils.isEmpty(downloadBitmap) && new File(downloadBitmap).exists()) {
                    a(str3, downloadBitmap, platformActionListener);
                } else if (platformActionListener != null) {
                    platformActionListener.onError(this.a, 9, new Throwable("both imagePath and imageUrl are null"));
                }
            } catch (Throwable th) {
                cn.sharesdk.framework.utils.d.a().d(th);
                if (platformActionListener != null) {
                    platformActionListener.onError(this.a, 9, new Throwable("both imagePath and imageUrl are null"));
                }
            }
        } else if (platformActionListener != null) {
            platformActionListener.onError(this.a, 9, new Throwable("both imagePath and imageUrl are null"));
        }
    }

    public HashMap<String, Object> d(String str) throws Throwable {
        String str2 = "https://api.instagram.com/v1/users/" + str + "/follows";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.g));
        arrayList.add(new KVPair("sig", a("/users/" + str + "/follows", arrayList, this.d)));
        String a = a.a().a(str2, arrayList, "/v1/users/follows", c());
        if (a == null || a.length() <= 0) {
            throw new Throwable("response is empty");
        }
        HashMap<String, Object> fromJson = new Hashon().fromJson(a);
        if (fromJson == null || fromJson.size() <= 0) {
            throw new Throwable("response is empty");
        }
        HashMap hashMap = (HashMap) fromJson.get("meta");
        if (hashMap == null || hashMap.size() <= 0) {
            throw new Throwable(a);
        } else if (200 == ((Integer) hashMap.get("code")).intValue()) {
            return fromJson;
        } else {
            throw new Throwable(a);
        }
    }

    public HashMap<String, Object> e(String str) throws Throwable {
        String str2 = "https://api.instagram.com/v1/users/" + str + "/followed-by";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.g));
        arrayList.add(new KVPair("sig", a("/users/" + str + "/followed-by", arrayList, this.d)));
        String a = a.a().a(str2, arrayList, "/v1/users/followed-by", c());
        if (a == null || a.length() <= 0) {
            throw new Throwable("response is empty");
        }
        HashMap<String, Object> fromJson = new Hashon().fromJson(a);
        if (fromJson == null || fromJson.size() <= 0) {
            throw new Throwable("response is empty");
        }
        HashMap hashMap = (HashMap) fromJson.get("meta");
        if (hashMap == null || hashMap.size() <= 0) {
            throw new Throwable(a);
        } else if (200 == ((Integer) hashMap.get("code")).intValue()) {
            return fromJson;
        } else {
            throw new Throwable(a);
        }
    }

    private String a(String str, ArrayList<KVPair<String>> arrayList, String str2) {
        int i = 0;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        String[] strArr = new String[arrayList.size()];
        Iterator it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            KVPair kVPair = (KVPair) it.next();
            strArr[i2] = String.format("%s=%s", new Object[]{kVPair.name, kVPair.value});
            i2++;
        }
        Arrays.sort(strArr);
        int length = strArr.length;
        while (i < length) {
            str = str + "|" + strArr[i];
            i++;
        }
        try {
            return a(str.getBytes("utf-8"), str2.getBytes("utf-8"));
        } catch (Throwable e) {
            cn.sharesdk.framework.utils.d.a().d(e);
            return null;
        }
    }

    private String a(byte[] bArr, byte[] bArr2) {
        try {
            Key secretKeySpec = new SecretKeySpec(bArr2, "HmacSHA256");
            Mac instance = Mac.getInstance("HmacSHA256");
            instance.init(secretKeySpec);
            return a(instance.doFinal(bArr));
        } catch (Throwable e) {
            cn.sharesdk.framework.utils.d.a().d(e);
            return null;
        } catch (Throwable e2) {
            cn.sharesdk.framework.utils.d.a().d(e2);
            return null;
        }
    }

    public String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (bArr != null && i < bArr.length) {
            String toHexString = Integer.toHexString(bArr[i] & 255);
            if (toHexString.length() == 1) {
                stringBuilder.append('0');
            }
            stringBuilder.append(toHexString);
            i++;
        }
        return stringBuilder.toString().toLowerCase();
    }

    private void a(String str, String str2, PlatformActionListener platformActionListener) {
        if (a(this.a).a() && !TextUtils.isEmpty(str2)) {
            Intent intent = new Intent("android.intent.action.SEND");
            if (str2.endsWith("mp4") || str2.endsWith("mkv")) {
                intent.setType("video/*");
            } else {
                intent.setType("image/*");
            }
            intent.setPackage("com.instagram.android");
            if (!TextUtils.isEmpty(str)) {
                intent.putExtra("android.intent.extra.TEXT", str);
            }
            if (VERSION.SDK_INT >= 24) {
                intent.putExtra("android.intent.extra.STREAM", R.pathToContentUri(this.a.getContext(), str2));
            } else {
                intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str2)));
            }
            try {
                intent.addFlags(268435456);
                this.a.getContext().startActivity(intent);
                if (platformActionListener != null) {
                    platformActionListener.onComplete(this.a, 9, new HashMap());
                }
            } catch (Throwable th) {
                if (platformActionListener != null) {
                    platformActionListener.onError(this.a, 9, th);
                }
            }
        } else if (platformActionListener != null) {
            platformActionListener.onError(this.a, 9, new InstagramClientNotExistException());
        }
    }
}
