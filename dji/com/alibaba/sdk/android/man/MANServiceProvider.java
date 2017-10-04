package com.alibaba.sdk.android.man;

public class MANServiceProvider implements MANService {

    private static class Singleton {
        static MANService instance = new MANServiceProvider();

        private Singleton() {
        }
    }

    private MANServiceProvider() {
    }

    public static MANService getService() {
        return Singleton.instance;
    }

    public MANAnalytics getMANAnalytics() {
        return MANAnalytics.getInstance();
    }

    public MANPageHitHelper getMANPageHitHelper() {
        return MANPageHitHelper.getInstance();
    }
}
