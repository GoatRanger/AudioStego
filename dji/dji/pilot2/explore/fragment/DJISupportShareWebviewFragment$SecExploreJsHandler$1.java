package dji.pilot2.explore.fragment;

import dji.pilot2.explore.fragment.DJISupportShareWebviewFragment.SecExploreJsHandler;

class DJISupportShareWebviewFragment$SecExploreJsHandler$1 implements Runnable {
    final /* synthetic */ SecExploreJsHandler a;

    DJISupportShareWebviewFragment$SecExploreJsHandler$1(SecExploreJsHandler secExploreJsHandler) {
        this.a = secExploreJsHandler;
    }

    public void run() {
        SecExploreJsHandler.access$1700(this.a).loadUrl("javascript:getShareCmdFromApp()");
    }
}
