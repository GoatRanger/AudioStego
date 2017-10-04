package dji.pilot2.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import com.dji.frame.c.c;
import com.dji.frame.c.h;
import com.tencent.android.tpush.common.MessageKey;
import dji.log.DJILogHelper;
import dji.midware.util.i;
import dji.pilot.R;
import dji.pilot.usercenter.b.d;
import dji.pilot.usercenter.b.f;
import dji.pilot2.main.fragment.DJIMineFragment;
import dji.pilot2.mine.b.e;
import dji.pilot2.mine.jsonbean.UserLevelJsonBean;
import dji.pilot2.mine.jsonbean.UserLevelJsonBean.LevelInfo;
import dji.pilot2.mine.view.MineButton;
import dji.thirdparty.afinal.f.a;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class l {
    public static String[] a = new String[]{"Device", "Explore", "Mine"};
    public static boolean b = false;
    public static boolean c = false;
    private static l d;
    private HashMap<String, Boolean> e;
    private a f;
    private boolean g = false;
    private ArrayList<String> h;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private boolean l = false;
    private Handler m;
    private int n = 0;

    public static synchronized l getInstance() {
        l lVar;
        synchronized (l.class) {
            if (d == null) {
                d = new l();
            }
            lVar = d;
        }
        return lVar;
    }

    public int a() {
        return this.n;
    }

    public void b() {
        for (String b : a) {
            b(b, false);
        }
    }

    public void a(String str) {
        a(str, true);
        this.h.add(str);
        this.g = true;
    }

    public boolean a(String str, boolean z) {
        if (this.h == null || this.h.size() <= 0) {
            return false;
        }
        for (int i = 0; i < this.h.size(); i++) {
            if (((String) this.h.get(i)).equalsIgnoreCase(str)) {
                if (z) {
                    this.h.remove(i);
                }
                return true;
            }
        }
        return false;
    }

    public void a(Context context) {
        d instance = d.getInstance();
        instance.a(context);
        if (instance.j()) {
            b(a[0], true);
        } else {
            b(a[0], false);
        }
    }

    public void a(Context context, final View view) {
        if (f.getInstance().c() && view != null) {
            f.getInstance().n();
            f.getInstance().i();
            String o = f.getInstance().o();
            final String j = f.getInstance().j();
            c.b(context).a(k.t(o), new a<String>(this) {
                final /* synthetic */ l c;

                public void a(boolean z) {
                }

                public void a(long j, long j2) {
                }

                public void a(String str) {
                    UserLevelJsonBean userLevelJsonBean;
                    if (str == null || str.equals("")) {
                        userLevelJsonBean = null;
                    } else {
                        userLevelJsonBean = (UserLevelJsonBean) h.b(str, UserLevelJsonBean.class);
                    }
                    if (userLevelJsonBean != null && userLevelJsonBean.level != null && view != null) {
                        LevelInfo levelInfo;
                        LevelInfo levelInfo2 = userLevelJsonBean.level;
                        String c = e.getInstance().c(j);
                        if (c == null || c.equals("")) {
                            userLevelJsonBean = null;
                        } else {
                            userLevelJsonBean = (UserLevelJsonBean) h.b(c, UserLevelJsonBean.class);
                        }
                        if (userLevelJsonBean == null || userLevelJsonBean.level == null) {
                            levelInfo = null;
                        } else {
                            levelInfo = userLevelJsonBean.level;
                        }
                        if (levelInfo2 != null && levelInfo != null) {
                            Message message = new Message();
                            Bundle bundle = new Bundle();
                            message.what = 3;
                            if (levelInfo2.level != levelInfo.level) {
                                bundle.putBoolean("Exp", true);
                                message.setData(bundle);
                                view.setVisibility(0);
                            } else {
                                bundle.putBoolean("Exp", false);
                                message.setData(bundle);
                                view.setVisibility(8);
                            }
                            this.c.m.sendMessage(message);
                        }
                    }
                }

                public void a(Throwable th, int i, String str) {
                }
            });
        }
    }

    public boolean c() {
        if (this.h.size() == 0) {
            this.g = false;
        }
        return this.g;
    }

    public boolean d() {
        return false;
    }

    public void b(final Context context, final View view) {
        c.b(context).a(k.h(f.getInstance().n()), new a<String>(this) {
            final /* synthetic */ l c;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    this.c.k = jSONObject.getBoolean("isupdate");
                    int i = jSONObject.getInt("uptime");
                    if (i > i.b(context, "store_version", 0)) {
                        this.c.b(l.a[1], true);
                        i.a(context, "store_version", i);
                    }
                    ((MineButton) view).setRedPointVisiblity(this.c.k);
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("BBS", this.c.k);
                    message.what = 3;
                    message.setData(bundle);
                    this.c.m.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void a(Throwable th, int i, String str) {
            }
        });
    }

    public void c(final Context context, final View view) {
        c.b(context).a(k.i(f.getInstance().n()), new a<String>(this) {
            final /* synthetic */ l c;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    DJILogHelper.getInstance().LOGI("bob", "isCoupOnHasUpdate " + str);
                    if (!jSONObject.isNull("status") && jSONObject.getInt("status") == 0 && !jSONObject.isNull("data")) {
                        jSONObject = jSONObject.getJSONObject("data");
                        if (jSONObject != null && !jSONObject.isNull("has_gift_cards")) {
                            this.c.l = jSONObject.getBoolean("has_gift_cards");
                            if (i.b(context, DJIMineFragment.I, 0) == 0) {
                                DJILogHelper.getInstance().LOGI("bob", "isCoupOnHasUpdate COUPONNEW is 0");
                                this.c.l = true;
                            }
                            ((MineButton) view).setCoupOnRedPoint(this.c.l);
                            Message message = new Message();
                            Bundle bundle = new Bundle();
                            bundle.putBoolean("COUPON", this.c.l);
                            message.what = 3;
                            message.setData(bundle);
                            this.c.m.sendMessage(message);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void a(Throwable th, int i, String str) {
                if (i.b(context, DJIMineFragment.I, 0) == 0) {
                    DJILogHelper.getInstance().LOGI("bob", "isCoupOnHasUpdate COUPONNEW is 0");
                    this.c.l = true;
                    ((MineButton) view).setCoupOnRedPoint(this.c.l);
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("COUPON", this.c.l);
                    message.what = 3;
                    message.setData(bundle);
                    this.c.m.sendMessage(message);
                }
            }
        });
    }

    public void d(Context context, final View view) {
        dji.thirdparty.afinal.c b = c.b(context);
        Log.i("red", "isNativeExploreHasUpdate");
        if (!b && !c) {
            b.a(k.a(f.getInstance().n(), 2), new a<String>(this) {
                final /* synthetic */ l b;

                public void a(boolean z) {
                    l.b = true;
                }

                public void a(long j, long j2) {
                }

                public void a(String str) {
                    int i = 0;
                    try {
                        l.b = false;
                        JSONObject jSONObject = new JSONObject(str);
                        if (!jSONObject.isNull("allunread")) {
                            i = jSONObject.getInt("allunread");
                        }
                        Log.i("red", "msg:" + i);
                        this.b.n = i;
                        if (i != 0) {
                            ((MineButton) view).setNotificationText("" + this.b.n);
                            Message message = new Message();
                            Bundle bundle = new Bundle();
                            bundle.putBoolean("MESSAGE", true);
                            message.what = 3;
                            message.setData(bundle);
                            this.b.m.sendMessage(message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void a(Throwable th, int i, String str) {
                    l.b = false;
                }
            });
        }
    }

    public void e(final Context context, View view) {
        dji.thirdparty.afinal.c b = c.b(context);
        b.a("Accept", "application/vnd.dji-v3");
        b.a(k.d(), new a<String>(this) {
            final /* synthetic */ l b;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                String str2 = null;
                try {
                    String string;
                    JSONObject jSONObject = new JSONObject(str).getJSONObject(MessageKey.MSG_CREATE_TIMESTAMPS);
                    if (jSONObject != null) {
                        string = jSONObject.getString("banners");
                        str2 = jSONObject.getString("products");
                    } else {
                        string = null;
                    }
                    String b = i.b(context, "explore_banner", "");
                    String b2 = i.b(context, "explore_product", "");
                    if (!(b.equals(string) && b2.equals(str2))) {
                        this.b.i = true;
                    }
                    i.a(context, "explore_banner", string);
                    i.a(context, "explore_product", str2);
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("Store", this.b.i);
                    message.what = 3;
                    message.setData(bundle);
                    this.b.m.sendMessage(message);
                } catch (Exception e) {
                    this.b.i = false;
                }
            }

            public void a(Throwable th, int i, String str) {
            }
        });
    }

    public void f(final Context context, final View view) {
        dji.thirdparty.afinal.c b = c.b(context);
        b.a("Accept", "application/vnd.dji-v3");
        if (f.getInstance().f() != null) {
            b.a(k.g(f.getInstance().j()), new a<String>(this) {
                final /* synthetic */ l c;

                public void a(boolean z) {
                }

                public void a(long j, long j2) {
                }

                public void a(String str) {
                    try {
                        String string;
                        String str2 = "";
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject != null) {
                            string = jSONObject.getString("last_update");
                        } else {
                            string = str2;
                        }
                        if (!string.equals(i.b(context, "last_update", ""))) {
                            this.c.j = true;
                        }
                        ((MineButton) view).setRedPointVisiblity(this.c.i);
                        i.a(context, "last_update", string);
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("DDS", this.c.j);
                        message.what = 3;
                        message.setData(bundle);
                        this.c.m.sendMessage(message);
                    } catch (Exception e) {
                        ((MineButton) view).setRedPointVisiblity(false);
                        this.c.j = false;
                    }
                }

                public void a(Throwable th, int i, String str) {
                }
            });
        }
    }

    public void b(Context context) {
        a[0] = context.getString(R.string.v2_tabhost_equipment);
        a[1] = context.getString(R.string.v2_tabhost_discovery);
        a[2] = context.getString(R.string.v2_tabhost_me);
        if (this.e == null) {
            this.e = new HashMap();
        }
        for (int i = 0; i < 3; i++) {
            this.e.put(a[i], Boolean.valueOf(false));
        }
        this.h = new ArrayList();
        this.m = DJIMineFragment.P;
    }

    public void b(String str, boolean z) {
        if (this.e.get(str) == null || ((Boolean) this.e.get(str)).booleanValue() != z) {
            this.e.put(str, Boolean.valueOf(z));
        }
        this.f.a(str, z);
    }

    public boolean b(String str) {
        return ((Boolean) this.e.get(str)).booleanValue();
    }

    public void a(a aVar) {
        this.f = aVar;
    }
}
