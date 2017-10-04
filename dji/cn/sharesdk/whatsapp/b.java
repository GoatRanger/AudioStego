package cn.sharesdk.whatsapp;

import android.content.Intent;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.e;
import cn.sharesdk.framework.utils.d;

public class b extends cn.sharesdk.framework.b {
    public b(Platform platform) {
        super(platform);
    }

    public cn.sharesdk.framework.authorize.b getAuthorizeWebviewClient(e eVar) {
        return null;
    }

    public String getAuthorizeUrl() {
        return null;
    }

    public String getRedirectUri() {
        return null;
    }

    public boolean a() {
        Boolean valueOf;
        Boolean.valueOf(false);
        try {
            valueOf = Boolean.valueOf(this.a.getContext().getPackageManager().getPackageInfo("com.whatsapp", 0) != null);
        } catch (Exception e) {
            Exception exception = e;
            valueOf = Boolean.valueOf(false);
            d.a().d("Exception", new Object[]{exception.toString()});
        }
        return valueOf.booleanValue();
    }

    public void a(int i, String str, PlatformActionListener platformActionListener) {
        Intent intent = new Intent();
        intent.putExtra("type", i);
        intent.putExtra("path", str);
        a aVar = new a();
        aVar.a(this.a, platformActionListener);
        aVar.show(this.a.getContext(), intent);
    }

    public void a(String str, String str2, PlatformActionListener platformActionListener) {
        Intent intent = new Intent();
        intent.putExtra("type", 1);
        intent.putExtra("text", str);
        intent.putExtra("title", str2);
        a aVar = new a();
        aVar.a(this.a, platformActionListener);
        aVar.show(this.a.getContext(), intent);
    }

    public void a(String str, PlatformActionListener platformActionListener) {
        Intent intent = new Intent();
        intent.putExtra("type", 100);
        intent.putExtra("phone", str);
        a aVar = new a();
        aVar.a(this.a, platformActionListener);
        aVar.show(this.a.getContext(), intent);
    }
}
