package cn.sharesdk.sina.weibo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.d;
import com.mob.tools.utils.Data;
import com.sina.weibo.sdk.constant.WBConstants;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.json.JSONObject;

public class e {
    private static final Uri a = Uri.parse("content://com.sina.weibo.sdkProvider/query/package");
    private static e b;
    private static a c = null;
    private Context d;

    public static class a {
        private String a;
        private int b;

        private void a(String str) {
            this.a = str;
        }

        public String a() {
            return this.a;
        }

        private void a(int i) {
            this.b = i;
        }

        public int b() {
            return this.b;
        }

        public String toString() {
            return "WeiboInfo: PackageName = " + this.a + ", supportApi = " + this.b;
        }
    }

    private e(Context context) {
        this.d = context.getApplicationContext();
    }

    public static synchronized e a(Context context) {
        e eVar;
        synchronized (e.class) {
            if (b == null) {
                b = new e(context);
            }
            eVar = b;
        }
        return eVar;
    }

    public synchronized String a() {
        if (c == null) {
            c = b(this.d);
        }
        return c.a();
    }

    private a b(Context context) {
        Object obj = 1;
        a c = c(context);
        a d = d(context);
        Object obj2 = c != null ? 1 : null;
        if (d == null) {
            obj = null;
        }
        if (obj2 == null || obj == null) {
            if (obj2 != null) {
                return c;
            }
            if (obj != null) {
                return d;
            }
            return null;
        } else if (c.b() >= d.b()) {
            return c;
        } else {
            return d;
        }
    }

    private a c(Context context) {
        Exception e;
        Throwable th;
        Cursor query;
        try {
            query = context.getContentResolver().query(a, null, null, null, null);
            if (query == null) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            try {
                int columnIndex = query.getColumnIndex("support_api");
                int columnIndex2 = query.getColumnIndex("package");
                if (query.moveToFirst()) {
                    int i = -1;
                    try {
                        columnIndex = Integer.parseInt(query.getString(columnIndex));
                    } catch (Throwable e2) {
                        d.a().d(e2);
                        columnIndex = i;
                    }
                    String string = query.getString(columnIndex2);
                    if (!TextUtils.isEmpty(string) && a(context, string)) {
                        a aVar = new a();
                        aVar.a(string);
                        aVar.a(columnIndex);
                        if (query == null) {
                            return aVar;
                        }
                        query.close();
                        return aVar;
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    d.a().e(e.getMessage(), new Object[0]);
                    if (query != null) {
                        query.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
            return null;
        } catch (Exception e4) {
            e = e4;
            query = null;
            d.a().e(e.getMessage(), new Object[0]);
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private a d(Context context) {
        a aVar = null;
        Intent intent = new Intent("com.sina.weibo.action.sdkidentity");
        intent.addCategory("android.intent.category.DEFAULT");
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (!(queryIntentServices == null || queryIntentServices.isEmpty())) {
            for (ResolveInfo resolveInfo : queryIntentServices) {
                a a;
                if (!(resolveInfo.serviceInfo == null || resolveInfo.serviceInfo.applicationInfo == null || TextUtils.isEmpty(resolveInfo.serviceInfo.applicationInfo.packageName))) {
                    a = a(resolveInfo.serviceInfo.applicationInfo.packageName);
                    if (a != null) {
                        if (aVar != null) {
                            if (aVar.b() < a.b()) {
                            }
                        }
                        aVar = a;
                    }
                }
                a = aVar;
                aVar = a;
            }
        }
        return aVar;
    }

    public a a(String str) {
        NameNotFoundException e;
        Throwable th;
        Exception e2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        InputStream open;
        try {
            byte[] bArr = new byte[4096];
            open = this.d.createPackageContext(str, 2).getAssets().open("weibo_for_sdk.json");
            try {
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    int read = open.read(bArr, 0, 4096);
                    if (read == -1) {
                        break;
                    }
                    stringBuilder.append(new String(bArr, 0, read));
                }
                if (!TextUtils.isEmpty(stringBuilder.toString()) && a(this.d, str)) {
                    int optInt = new JSONObject(stringBuilder.toString()).optInt("support_api", -1);
                    a aVar = new a();
                    aVar.a(str);
                    aVar.a(optInt);
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e3) {
                            d.a().e(e3.getMessage(), new Object[0]);
                        }
                    }
                    return aVar;
                } else if (open == null) {
                    return null;
                } else {
                    try {
                        open.close();
                        return null;
                    } catch (IOException e4) {
                        d.a().e(e4.getMessage(), new Object[0]);
                        return null;
                    }
                }
            } catch (NameNotFoundException e5) {
                e = e5;
                try {
                    d.a().e(e.getMessage(), new Object[0]);
                    if (open != null) {
                        return null;
                    }
                    try {
                        open.close();
                        return null;
                    } catch (IOException e42) {
                        d.a().e(e42.getMessage(), new Object[0]);
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e422) {
                            d.a().e(e422.getMessage(), new Object[0]);
                        }
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e2 = e6;
                d.a().e(e2.getMessage(), new Object[0]);
                if (open != null) {
                    return null;
                }
                try {
                    open.close();
                    return null;
                } catch (IOException e4222) {
                    d.a().e(e4222.getMessage(), new Object[0]);
                    return null;
                }
            }
        } catch (NameNotFoundException e7) {
            e = e7;
            open = null;
            d.a().e(e.getMessage(), new Object[0]);
            if (open != null) {
                return null;
            }
            open.close();
            return null;
        } catch (Exception e8) {
            e2 = e8;
            open = null;
            d.a().e(e2.getMessage(), new Object[0]);
            if (open != null) {
                return null;
            }
            open.close();
            return null;
        } catch (Throwable th3) {
            open = null;
            th = th3;
            if (open != null) {
                open.close();
            }
            throw th;
        }
    }

    public static boolean a(Context context, String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return z;
        }
        try {
            return a(context.getPackageManager().getPackageInfo(str, 64).signatures, WBConstants.WEIBO_SIGN);
        } catch (NameNotFoundException e) {
            return z;
        }
    }

    private static boolean a(Signature[] signatureArr, String str) {
        if (signatureArr == null || str == null) {
            return false;
        }
        for (Signature toByteArray : signatureArr) {
            if (str.equals(Data.MD5(toByteArray.toByteArray()))) {
                d.a().d("check pass", new Object[0]);
                return true;
            }
        }
        return false;
    }
}
