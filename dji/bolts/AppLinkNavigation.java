package bolts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import bolts.AppLink.Target;
import com.alipay.sdk.j.f;
import com.facebook.internal.a;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppLinkNavigation {
    private static final String KEY_NAME_REFERER_APP_LINK = "referer_app_link";
    private static final String KEY_NAME_REFERER_APP_LINK_APP_NAME = "app_name";
    private static final String KEY_NAME_REFERER_APP_LINK_PACKAGE = "package";
    private static final String KEY_NAME_USER_AGENT = "user_agent";
    private static final String KEY_NAME_VERSION = "version";
    private static final String VERSION = "1.0";
    private static AppLinkResolver defaultResolver;
    private final AppLink appLink;
    private final Bundle appLinkData;
    private final Bundle extras;

    public enum NavigationResult {
        FAILED(f.b, false),
        WEB(a.Z, true),
        APP("app", true);
        
        private String code;
        private boolean succeeded;

        public String getCode() {
            return this.code;
        }

        public boolean isSucceeded() {
            return this.succeeded;
        }

        private NavigationResult(String str, boolean z) {
            this.code = str;
            this.succeeded = z;
        }
    }

    public AppLinkNavigation(AppLink appLink, Bundle bundle, Bundle bundle2) {
        if (appLink == null) {
            throw new IllegalArgumentException("appLink must not be null.");
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        this.appLink = appLink;
        this.extras = bundle;
        this.appLinkData = bundle2;
    }

    public AppLink getAppLink() {
        return this.appLink;
    }

    public Bundle getAppLinkData() {
        return this.appLinkData;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    private Bundle buildAppLinkDataForNavigation(Context context) {
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        if (context != null) {
            String packageName = context.getPackageName();
            if (packageName != null) {
                bundle2.putString(KEY_NAME_REFERER_APP_LINK_PACKAGE, packageName);
            }
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo != null) {
                packageName = context.getString(applicationInfo.labelRes);
                if (packageName != null) {
                    bundle2.putString("app_name", packageName);
                }
            }
        }
        bundle.putAll(getAppLinkData());
        bundle.putString(dji.pilot2.main.a.a.j, getAppLink().getSourceUrl().toString());
        bundle.putString("version", "1.0");
        bundle.putString("user_agent", "Bolts Android 1.4.0");
        bundle.putBundle(KEY_NAME_REFERER_APP_LINK, bundle2);
        bundle.putBundle("extras", getExtras());
        return bundle;
    }

    private Object getJSONValue(Object obj) throws JSONException {
        int i = 0;
        if (obj instanceof Bundle) {
            return getJSONForBundle((Bundle) obj);
        }
        if (obj instanceof CharSequence) {
            return obj.toString();
        }
        if (obj instanceof List) {
            JSONArray jSONArray = new JSONArray();
            for (Object jSONValue : (List) obj) {
                jSONArray.put(getJSONValue(jSONValue));
            }
            return jSONArray;
        } else if (obj instanceof SparseArray) {
            r1 = new JSONArray();
            SparseArray sparseArray = (SparseArray) obj;
            while (i < sparseArray.size()) {
                r1.put(sparseArray.keyAt(i), getJSONValue(sparseArray.valueAt(i)));
                i++;
            }
            return r1;
        } else if (obj instanceof Character) {
            return obj.toString();
        } else {
            if (obj instanceof Boolean) {
                return obj;
            }
            if (obj instanceof Number) {
                if ((obj instanceof Double) || (obj instanceof Float)) {
                    return Double.valueOf(((Number) obj).doubleValue());
                }
                return Long.valueOf(((Number) obj).longValue());
            } else if (obj instanceof boolean[]) {
                r1 = new JSONArray();
                boolean[] zArr = (boolean[]) obj;
                r2 = zArr.length;
                while (i < r2) {
                    r1.put(getJSONValue(Boolean.valueOf(zArr[i])));
                    i++;
                }
                return r1;
            } else if (obj instanceof char[]) {
                r1 = new JSONArray();
                char[] cArr = (char[]) obj;
                r2 = cArr.length;
                while (i < r2) {
                    r1.put(getJSONValue(Character.valueOf(cArr[i])));
                    i++;
                }
                return r1;
            } else if (obj instanceof CharSequence[]) {
                r1 = new JSONArray();
                CharSequence[] charSequenceArr = (CharSequence[]) obj;
                r2 = charSequenceArr.length;
                while (i < r2) {
                    r1.put(getJSONValue(charSequenceArr[i]));
                    i++;
                }
                return r1;
            } else if (obj instanceof double[]) {
                r1 = new JSONArray();
                double[] dArr = (double[]) obj;
                r2 = dArr.length;
                while (i < r2) {
                    r1.put(getJSONValue(Double.valueOf(dArr[i])));
                    i++;
                }
                return r1;
            } else if (obj instanceof float[]) {
                r1 = new JSONArray();
                float[] fArr = (float[]) obj;
                r2 = fArr.length;
                while (i < r2) {
                    r1.put(getJSONValue(Float.valueOf(fArr[i])));
                    i++;
                }
                return r1;
            } else if (obj instanceof int[]) {
                r1 = new JSONArray();
                int[] iArr = (int[]) obj;
                r2 = iArr.length;
                while (i < r2) {
                    r1.put(getJSONValue(Integer.valueOf(iArr[i])));
                    i++;
                }
                return r1;
            } else if (obj instanceof long[]) {
                r1 = new JSONArray();
                long[] jArr = (long[]) obj;
                r2 = jArr.length;
                while (i < r2) {
                    r1.put(getJSONValue(Long.valueOf(jArr[i])));
                    i++;
                }
                return r1;
            } else if (obj instanceof short[]) {
                r1 = new JSONArray();
                short[] sArr = (short[]) obj;
                r2 = sArr.length;
                while (i < r2) {
                    r1.put(getJSONValue(Short.valueOf(sArr[i])));
                    i++;
                }
                return r1;
            } else if (!(obj instanceof String[])) {
                return null;
            } else {
                r1 = new JSONArray();
                String[] strArr = (String[]) obj;
                r2 = strArr.length;
                while (i < r2) {
                    r1.put(getJSONValue(strArr[i]));
                    i++;
                }
                return r1;
            }
        }
    }

    private JSONObject getJSONForBundle(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            jSONObject.put(str, getJSONValue(bundle.get(str)));
        }
        return jSONObject;
    }

    public NavigationResult navigate(Context context) {
        Intent intent;
        Intent intent2;
        NavigationResult navigationResult;
        PackageManager packageManager = context.getPackageManager();
        Bundle buildAppLinkDataForNavigation = buildAppLinkDataForNavigation(context);
        for (Target target : getAppLink().getTargets()) {
            intent = new Intent("android.intent.action.VIEW");
            if (target.getUrl() != null) {
                intent.setData(target.getUrl());
            } else {
                intent.setData(this.appLink.getSourceUrl());
            }
            intent.setPackage(target.getPackageName());
            if (target.getClassName() != null) {
                intent.setClassName(target.getPackageName(), target.getClassName());
            }
            intent.putExtra("al_applink_data", buildAppLinkDataForNavigation);
            if (packageManager.resolveActivity(intent, 65536) != null) {
                intent2 = intent;
                break;
            }
        }
        intent2 = null;
        NavigationResult navigationResult2 = NavigationResult.FAILED;
        if (intent2 != null) {
            intent = intent2;
            navigationResult = NavigationResult.APP;
        } else {
            Uri webUrl = getAppLink().getWebUrl();
            if (webUrl != null) {
                try {
                    intent = new Intent("android.intent.action.VIEW", webUrl.buildUpon().appendQueryParameter("al_applink_data", getJSONForBundle(buildAppLinkDataForNavigation).toString()).build());
                    navigationResult = NavigationResult.WEB;
                } catch (Throwable e) {
                    sendAppLinkNavigateEventBroadcast(context, intent2, NavigationResult.FAILED, e);
                    throw new RuntimeException(e);
                }
            }
            navigationResult = navigationResult2;
            intent = null;
        }
        sendAppLinkNavigateEventBroadcast(context, intent, navigationResult, null);
        if (intent != null) {
            context.startActivity(intent);
        }
        return navigationResult;
    }

    private void sendAppLinkNavigateEventBroadcast(Context context, Intent intent, NavigationResult navigationResult, JSONException jSONException) {
        Map hashMap = new HashMap();
        if (jSONException != null) {
            hashMap.put("error", jSONException.getLocalizedMessage());
        }
        hashMap.put("success", navigationResult.isSucceeded() ? "1" : "0");
        hashMap.put("type", navigationResult.getCode());
        MeasurementEvent.sendBroadcastEvent(context, MeasurementEvent.APP_LINK_NAVIGATE_OUT_EVENT_NAME, intent, hashMap);
    }

    public static void setDefaultResolver(AppLinkResolver appLinkResolver) {
        defaultResolver = appLinkResolver;
    }

    public static AppLinkResolver getDefaultResolver() {
        return defaultResolver;
    }

    private static AppLinkResolver getResolver(Context context) {
        if (getDefaultResolver() != null) {
            return getDefaultResolver();
        }
        return new WebViewAppLinkResolver(context);
    }

    public static NavigationResult navigate(Context context, AppLink appLink) {
        return new AppLinkNavigation(appLink, null, null).navigate(context);
    }

    public static Task<NavigationResult> navigateInBackground(final Context context, Uri uri, AppLinkResolver appLinkResolver) {
        return appLinkResolver.getAppLinkFromUrlInBackground(uri).onSuccess(new Continuation<AppLink, NavigationResult>() {
            public NavigationResult then(Task<AppLink> task) throws Exception {
                return AppLinkNavigation.navigate(context, (AppLink) task.getResult());
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    public static Task<NavigationResult> navigateInBackground(Context context, URL url, AppLinkResolver appLinkResolver) {
        return navigateInBackground(context, Uri.parse(url.toString()), appLinkResolver);
    }

    public static Task<NavigationResult> navigateInBackground(Context context, String str, AppLinkResolver appLinkResolver) {
        return navigateInBackground(context, Uri.parse(str), appLinkResolver);
    }

    public static Task<NavigationResult> navigateInBackground(Context context, Uri uri) {
        return navigateInBackground(context, uri, getResolver(context));
    }

    public static Task<NavigationResult> navigateInBackground(Context context, URL url) {
        return navigateInBackground(context, url, getResolver(context));
    }

    public static Task<NavigationResult> navigateInBackground(Context context, String str) {
        return navigateInBackground(context, str, getResolver(context));
    }
}
