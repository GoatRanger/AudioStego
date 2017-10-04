package cn.sharesdk.tencent.qq;

import android.content.Intent;
import android.os.Bundle;
import cn.sharesdk.framework.authorize.d;
import org.json.JSONObject;

public class c extends d {
    private String d;
    private String e;

    public c(cn.sharesdk.framework.authorize.c cVar) {
        super(cVar);
    }

    public void a(String str, String str2) {
        this.d = str;
        this.e = str2;
    }

    public void a() {
        String str = "com.tencent.mobileqq";
        try {
            if (this.a.getContext().getPackageManager().getPackageInfo(str, 0) == null) {
                this.a.finish();
                if (this.c != null) {
                    this.c.onFailed(new TencentSSOClientNotInstalledException());
                    return;
                }
                return;
            }
            Intent intent = new Intent();
            intent.setClassName(str, "com.tencent.open.agent.AgentActivity");
            if (this.a.getContext().getPackageManager().resolveActivity(intent, 0) == null) {
                this.a.finish();
                if (this.c != null) {
                    this.c.onFailed(new TencentSSOClientNotInstalledException());
                    return;
                }
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("scope", this.e);
            bundle.putString("client_id", this.d);
            bundle.putString("pf", "openmobile_android");
            bundle.putString("need_pay", "1");
            intent.putExtra("key_params", bundle);
            intent.putExtra("key_request_code", this.b);
            intent.putExtra("key_action", "action_login");
            try {
                this.a.startActivityForResult(intent, this.b);
            } catch (Throwable th) {
                this.a.finish();
                if (this.c != null) {
                    this.c.onFailed(th);
                }
            }
        } catch (Throwable th2) {
            this.a.finish();
            if (this.c != null) {
                this.c.onFailed(new TencentSSOClientNotInstalledException());
            }
        }
    }

    public void a(int i, int i2, Intent intent) {
        this.a.finish();
        if (i2 == 0) {
            if (this.c != null) {
                this.c.onCancel();
            }
        } else if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras == null) {
                if (this.c != null) {
                    this.c.onFailed(new Throwable("response is empty"));
                }
            } else if (extras.containsKey("key_response")) {
                String string = extras.getString("key_response");
                if (string != null && string.length() > 0) {
                    try {
                        JSONObject jSONObject = new JSONObject(string);
                        extras = new Bundle();
                        extras.putInt("ret", jSONObject.optInt("ret"));
                        extras.putString("pay_token", jSONObject.optString("pay_token"));
                        extras.putString("pf", jSONObject.optString("pf"));
                        extras.putString("open_id", jSONObject.optString("openid"));
                        extras.putString("expires_in", jSONObject.optString("expires_in"));
                        extras.putString("pfkey", jSONObject.optString("pfkey"));
                        extras.putString("msg", jSONObject.optString("msg"));
                        extras.putString("access_token", jSONObject.optString("access_token"));
                        if (this.c != null) {
                            this.c.onComplete(extras);
                            this.c = null;
                        }
                    } catch (Throwable th) {
                        if (this.c != null) {
                            this.c.onFailed(th);
                        }
                    }
                } else if (this.c != null) {
                    this.c.onFailed(new Throwable("response is empty"));
                }
            } else if (this.c != null) {
                this.c.onFailed(new Throwable("response is empty"));
            }
        } else if (this.c != null) {
            this.c.onFailed(new Throwable("response is empty"));
        }
    }
}
