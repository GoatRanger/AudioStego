package com.alibaba.sdk.android.man;

import android.app.Activity;
import com.ut.mini.UTPageHitHelper;
import java.util.Map;

public class MANPageHitHelper {

    private static class Singleton {
        static MANPageHitHelper instance = new MANPageHitHelper();

        private Singleton() {
        }
    }

    private MANPageHitHelper() {
    }

    protected static MANPageHitHelper getInstance() {
        return Singleton.instance;
    }

    public void pageAppear(Activity activity) {
        UTPageHitHelper.getInstance().pageAppear(activity);
    }

    public void pageDisAppear(Activity activity) {
        UTPageHitHelper.getInstance().pageDisAppear(activity);
    }

    public void updatePageProperties(Map<String, String> map) {
        UTPageHitHelper.getInstance().updatePageProperties(map);
    }
}
