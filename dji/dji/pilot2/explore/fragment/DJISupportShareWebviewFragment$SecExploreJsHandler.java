package dji.pilot2.explore.fragment;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import dji.pilot.fpv.d.c$f;
import dji.pilot.fpv.d.c$m;
import dji.pilot.fpv.d.c.o;
import dji.pilot.fpv.d.e;
import dji.pilot2.mine.e.a.a;
import dji.pilot2.nativeexplore.model.ExploreEvent;
import dji.thirdparty.a.c;

protected class DJISupportShareWebviewFragment$SecExploreJsHandler extends WebBaseJsHandler {
    private static final String GET_DDS_SHARE_LINKS_JAVA_FUNC = "addDDSShareLinks";
    private static final String GET_DDS_SHARE_LINKS_LENGTH_JAVA_FUNC = "getDDSShareLinksLength";
    private static final String GET_DESC_JAVA_FUNC = "getDesc";
    public static final String GET_DESC_JS_FUNCTION_NAME = "javascript:window.ibg_js_manager.getDesc(document.getElementsByName('description')[0].getAttribute('content'));";
    public static final String GET_FIRST_IMGSRC_JS_FUNCTION_NAME = "javascript:window.ibg_js_manager.getFirstImgSrc(document.getElementsByTagName('img')[0].getAttribute('src'));";
    private static final String GET_FIRST_IMG_SRC_JAVA_FUNC = "getFirstImgSrc";
    public static final String GET_SHARE_CMD_JS_FUNCTION_NAME = "getShareCmdFromApp";
    private static final String JS_FUNC_PREFIX = "javascript:window.ibg_js_manager.";
    public static final String V19_GET_DESC_JS_FUNCTION_NAME = "(function(){ return document.getElementsByName('description')[0].getAttribute('content');})();";
    public static final String V19_GET_FIRST_IMGSRC_JS_FUNCTION_NAME = "(function(){ return document.getElementsByTagName('img')[0].getAttribute('src');})();";
    final /* synthetic */ DJISupportShareWebviewFragment this$0;

    public DJISupportShareWebviewFragment$SecExploreJsHandler(DJISupportShareWebviewFragment dJISupportShareWebviewFragment, Activity activity, WebView webView) {
        this.this$0 = dJISupportShareWebviewFragment;
        super(activity, webView);
    }

    public void sendShareCmdToJs() {
        String str = "javascript:getShareCmdFromApp()";
        this.mActivity.runOnUiThread(new 1(this));
    }

    @JavascriptInterface
    public void show_share_dialog(String str, String str2, String str3, String str4, String str5) {
        a aVar = new a();
        aVar.d = str2;
        aVar.c = str;
        aVar.b = str3;
        aVar.a = str4;
        aVar.e = str5;
        if (!DJISupportShareWebviewFragment.m(this.this$0)) {
            if ("".equals(str5)) {
                if (DJISupportShareWebviewFragment.n(this.this$0)) {
                    e.b(c$f.dQ_);
                } else {
                    e.b(c$m.dx_);
                }
            } else if (DJISupportShareWebviewFragment.o(this.this$0)) {
                e.b(c$f.dR_);
            } else {
                e.b(c$m.dy_);
            }
        }
        if (DJISupportShareWebviewFragment.p(this.this$0)) {
            e.b(o.dS_);
        }
        this.mActivity.runOnUiThread(new 2(this, aVar));
    }

    @JavascriptInterface
    public void getFirstImgSrc(String str) {
        if (str != null) {
            DJISupportShareWebviewFragment.g(this.this$0).a = str;
        }
    }

    @JavascriptInterface
    public void getDesc(String str) {
        if (str != null) {
            DJISupportShareWebviewFragment.g(this.this$0).d = DJISupportShareWebviewFragment.a(this.this$0, str);
        }
    }

    @JavascriptInterface
    public void special_share(boolean z) {
        DJISupportShareWebviewFragment.b(this.this$0, z);
    }

    @JavascriptInterface
    public void JSFlurry(String str) {
        e.d(str);
    }

    @JavascriptInterface
    public void paticipateActivity(int i) {
        c.a().e(ExploreEvent.GOTO_LIBRARY);
        this.this$0.getActivity().finish();
    }

    @JavascriptInterface
    public void open_app_library() {
        c.a().e(ExploreEvent.GOTO_LIBRARY);
        this.this$0.getActivity().finish();
    }

    @JavascriptInterface
    public void open_app_explore() {
        c.a().e(ExploreEvent.GOTO_EXPLORE);
        this.this$0.getActivity().finish();
    }

    @JavascriptInterface
    public void open_app_equipment() {
        c.a().e(ExploreEvent.GOTO_DEVICE);
        this.this$0.getActivity().finish();
    }
}
