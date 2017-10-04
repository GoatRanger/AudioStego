package dji.pilot2.explore.fragment;

import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.explore.fragment.DJISupportShareWebviewFragment.SecExploreJsHandler;
import dji.pilot2.mine.e.a.a;

class DJISupportShareWebviewFragment$SecExploreJsHandler$2 implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ SecExploreJsHandler b;

    DJISupportShareWebviewFragment$SecExploreJsHandler$2(SecExploreJsHandler secExploreJsHandler, a aVar) {
        this.b = secExploreJsHandler;
        this.a = aVar;
    }

    public void run() {
        ((DJISupportShareWebviewActivity) this.b.this$0.getActivity()).a(this.a);
    }
}
