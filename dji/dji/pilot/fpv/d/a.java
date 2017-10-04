package dji.pilot.fpv.d;

import android.app.Activity;
import android.content.Context;
import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.man.MANHitBuilders.MANCustomHitBuilder;
import com.alibaba.sdk.android.man.MANServiceProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class a {
    private static final a a = new a();
    private static boolean b = false;
    private Map<String, Queue<Long>> c = new HashMap();

    public static void a(boolean z) {
        b = z;
    }

    public static a getInstance() {
        return a;
    }

    public void a(Context context) {
        AlibabaSDK.asyncInit(context.getApplicationContext(), new 1(this));
    }

    public void a(String str) {
        Queue queue = (Queue) this.c.get(str);
        if (queue == null) {
            queue = new LinkedBlockingQueue();
            this.c.put(str, queue);
        }
        queue.add(Long.valueOf(System.currentTimeMillis()));
    }

    public void b(String str) {
        Queue queue = (Queue) this.c.get(str);
        if (queue != null && !queue.isEmpty()) {
            Long l = (Long) queue.poll();
            if (l != null) {
                a(str, Long.valueOf(System.currentTimeMillis() - l.longValue()));
            }
        }
    }

    public void c(String str) {
        a(str, new HashMap(0), null);
    }

    public void a(String str, Map<String, String> map) {
        a(str, map, null);
    }

    public void a(String str, Long l) {
        a(str, new HashMap(0), l);
    }

    public void a(String str, Map<String, String> map, Long l) {
        if (b) {
            MANCustomHitBuilder mANCustomHitBuilder = new MANCustomHitBuilder(str);
            mANCustomHitBuilder.setProperties(map);
            if (l != null) {
                mANCustomHitBuilder.setDurationOnEvent(l.longValue());
            }
            MANServiceProvider.getService().getMANAnalytics().getDefaultTracker().send(mANCustomHitBuilder.build());
        }
    }

    public void a(Activity activity) {
        if (b) {
            MANServiceProvider.getService().getMANPageHitHelper().pageAppear(activity);
        }
    }

    public void b(Activity activity) {
        if (b) {
            MANServiceProvider.getService().getMANPageHitHelper().pageDisAppear(activity);
        }
    }

    public void d(String str) {
        MANServiceProvider.getService().getMANAnalytics().userRegister(str);
    }

    public void a(String str, String str2) {
        if (b) {
            MANServiceProvider.getService().getMANAnalytics().updateUserAccount(str, str2);
        }
    }

    public void a() {
        MANServiceProvider.getService().getMANAnalytics().updateUserAccount("", "");
    }
}
