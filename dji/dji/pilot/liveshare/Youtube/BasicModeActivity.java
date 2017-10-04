package dji.pilot.liveshare.Youtube;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.dji.frame.c.l;
import com.sina.weibo.sdk.constant.WBConstants;
import dji.pilot.R;
import dji.pilot.fpv.d.c.g;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot2.share.c.c;
import dji.pilot2.share.c.d;
import dji.pilot2.share.d.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint({"SetJavaScriptEnabled"})
public class BasicModeActivity extends DJIBaseActivity implements g {
    private static final int DIALOG_ACCOUNTS = 0;
    private static int MSG_HIDE_WEBVIEW = 0;
    private static int MSG_LOGIN_RESULT = 1;
    static final int REQUEST_CODE_PICK_ACCOUNT = 1000;
    static final int REQUEST_CODE_RECOVER_FROM_AUTH_ERROR = 1001;
    static final int REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR = 1002;
    private static final String SCOPE = "oauth2:https://www.googleapis.com/auth/youtube https://www.googleapis.com/auth/userinfo.profile";
    private static final String SCOPE_PROFILE = "https://www.googleapis.com/auth/userinfo.profile";
    private static final String SCOPE_YOUTUBE = "https://www.googleapis.com/auth/youtube";
    private static int SHOWLOGINDIALOG = 2;
    private static e youtubeManager;
    a adapter;
    boolean checkGoogleAccount = false;
    private EditText descET;
    private Button mAccount;
    private String mEmail = null;
    final Handler mHandleForUpdateYoutubeState = new Handler() {
        public void handleMessage(Message message) {
            BasicModeActivity.this.webView.setVisibility(8);
            BasicModeActivity.this.clearWebView();
            if (message.what == BasicModeActivity.MSG_LOGIN_RESULT) {
                if (message.getData().getBoolean("result")) {
                    BasicModeActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            BasicModeActivity.this.showUserInfo();
                        }
                    });
                } else if (message.what == BasicModeActivity.SHOWLOGINDIALOG) {
                    BasicModeActivity.this.loginDialog();
                } else {
                    BasicModeActivity.this.showMessage(BasicModeActivity.this.getString(R.string.share_msg_login_failed_title), BasicModeActivity.this.getString(R.string.share_msg_login_failed_desc));
                }
            }
            super.handleMessage(message);
        }
    };
    private OnClickListener mWidgetClickListener = null;
    private EditText titleET;
    private WebView webView;

    class JavaScriptInterfaceLow {
        JavaScriptInterfaceLow() {
        }

        public void processHTML(String str) {
            Log.d(BasicModeActivity.this.TAG, "processHTML: ");
            if (str == null || !str.startsWith("<head><head><title>Success code=")) {
                Log.d(BasicModeActivity.this.TAG, "processHTML: accessToken false");
                Log.i("BasicMode", "accessToken false");
                Bundle bundle = new Bundle();
                bundle.putBoolean("result", false);
                Message message = new Message();
                message.what = BasicModeActivity.MSG_LOGIN_RESULT;
                message.setData(bundle);
                BasicModeActivity.this.mHandleForUpdateYoutubeState.sendMessage(message);
                return;
            }
            Log.d(BasicModeActivity.this.TAG, "processHTML:Success code= ");
            int indexOf = str.indexOf("</title>");
            if (indexOf > 32) {
                BasicModeActivity.youtubeManager.a(str.substring(32, indexOf), new d() {
                    public void onTokenGet(String str) {
                        Log.i("BasicMode", "accessToken 2 " + str);
                        if (!str.isEmpty()) {
                            BasicModeActivity.this.getUserInfo();
                        }
                    }
                });
            }
        }
    }

    private class a extends BaseAdapter {
        private b[] holders;
        private List<Map<String, Object>> mData;
        private LayoutInflater mInflater;
        private int selectedIndex = 0;

        public a(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        public void setData(List<Map<String, Object>> list) {
            this.mData = list;
            this.holders = new b[this.mData.size()];
        }

        public int getCount() {
            return this.mData.size();
        }

        public Object getItem(int i) {
            return this.mData.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public String getSelectString() {
            return this.holders[this.selectedIndex].title.getText().toString();
        }

        public void setSelectedIndex(int i) {
            if (i >= 0 && i < this.mData.size() && i != this.selectedIndex) {
                this.holders[this.selectedIndex].imgView.setVisibility(4);
                this.selectedIndex = i;
                this.holders[this.selectedIndex].imgView.setVisibility(0);
            }
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view != null) {
                return view;
            }
            if (this.holders[i] != null && this.holders[i].convertView != null) {
                return this.holders[i].convertView;
            }
            b bVar = new b();
            view = this.mInflater.inflate(R.layout.liveshare_basicmode_list_item, null);
            bVar.title = (TextView) view.findViewById(R.id.ayx);
            bVar.info = (TextView) view.findViewById(R.id.ayy);
            bVar.imgView = (ImageView) view.findViewById(R.id.ayz);
            bVar.convertView = view;
            bVar.title.setText(((Map) this.mData.get(i)).get("title").toString());
            bVar.info.setText(((Map) this.mData.get(i)).get("info").toString());
            if (this.selectedIndex == i) {
                bVar.imgView.setVisibility(0);
            } else {
                bVar.imgView.setVisibility(4);
            }
            view.setTag(bVar);
            this.holders[i] = bVar;
            return view;
        }
    }

    private final class b {
        public View convertView;
        public ImageView imgView;
        public TextView info;
        public TextView title;

        private b() {
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        setContentView(R.layout.liveshare_basicmode);
        this.mAccount = (Button) findViewById(R.id.ayv);
        Button button = (Button) findViewById(R.id.aym);
        Button button2 = (Button) findViewById(R.id.ayl);
        initListView();
        this.webView = (WebView) findViewById(R.id.ayw);
        this.webView.getSettings().setJavaScriptEnabled(true);
        youtubeManager = dji.pilot2.share.d.d.getInstance().a();
        if (l.a(youtubeManager.e())) {
            getUserInfo();
        } else {
            this.mEmail = youtubeManager.e();
        }
        if (!youtubeManager.b()) {
            loginDialog();
        }
        initListeners();
        this.titleET = (EditText) findViewById(R.id.ayq);
        this.descET = (EditText) findViewById(R.id.ayr);
        button.setOnClickListener(this.mWidgetClickListener);
        button2.setOnClickListener(this.mWidgetClickListener);
        this.mAccount.setOnClickListener(this.mWidgetClickListener);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void loginDialog() {
        Builder builder = new Builder(this);
        builder.setMessage(getString(R.string.liveshare_basicmode_please_login));
        builder.setTitle("");
        builder.setPositiveButton(getString(R.string.home_account_signin), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                BasicModeActivity.this.youTubeLogin();
                dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_BasicModeView_Button_Account_Login");
            }
        });
        builder.setNegativeButton(getString(R.string.app_cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                BasicModeActivity.this.finish();
            }
        });
        builder.create().show();
    }

    private void initListView() {
        ListView listView = (ListView) findViewById(R.id.ayt);
        this.adapter = new a(getApplicationContext());
        this.adapter.setData(updateData());
        listView.setAdapter(this.adapter);
        int i = 0;
        for (int i2 = 0; i2 != this.adapter.getCount(); i2++) {
            View view = this.adapter.getView(i2, null, listView);
            if (view != null) {
                view.measure(0, 0);
                i += view.getMeasuredHeight();
            }
        }
        if (i != 0) {
            LayoutParams layoutParams = listView.getLayoutParams();
            layoutParams.height = i;
            listView.setLayoutParams(layoutParams);
        }
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_BasicModeView_Button_Public");
                } else if (i == 1) {
                    dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_BasicModeView_Button_Unlisted");
                } else if (i == 2) {
                    dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_BasicModeView_Button_Private");
                }
                BasicModeActivity.this.adapter.setSelectedIndex(i);
            }
        });
    }

    private List<Map<String, Object>> updateData() {
        List<Map<String, Object>> arrayList = new ArrayList();
        Map hashMap = new HashMap();
        hashMap.put("title", "public");
        hashMap.put("info", getString(R.string.liveshare_basicmode_privacypublic));
        arrayList.add(hashMap);
        hashMap = new HashMap();
        hashMap.put("title", "unlisted");
        hashMap.put("info", getString(R.string.liveshare_basicmode_privacyunlisted));
        arrayList.add(hashMap);
        hashMap = new HashMap();
        hashMap.put("title", dji.pilot.f.a.a.B);
        hashMap.put("info", getString(R.string.liveshare_basicmode_privacyprivate));
        arrayList.add(hashMap);
        return arrayList;
    }

    private void initListeners() {
        this.mWidgetClickListener = new OnClickListener() {
            @SuppressLint({"DefaultLocale"})
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.aym) {
                    dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_BasicModeView_Button_Start");
                    if (BasicModeActivity.this.checkGoogleAccount) {
                        dji.pilot.fpv.d.e.c(g.J);
                        String obj = BasicModeActivity.this.titleET.getText().toString();
                        String obj2 = BasicModeActivity.this.descET.getText().toString();
                        String selectString = BasicModeActivity.this.adapter.getSelectString();
                        Intent intent = new Intent();
                        intent.putExtra("title", obj);
                        intent.putExtra("description", obj2);
                        intent.putExtra("privacyStatus", selectString);
                        intent.putExtra("mEmail", BasicModeActivity.this.mEmail);
                        intent.setClass(BasicModeActivity.this, youtubeLiveActivity.class);
                        BasicModeActivity.this.startActivity(intent);
                        BasicModeActivity.this.finish();
                        return;
                    }
                    Toast.makeText(BasicModeActivity.this.getApplicationContext(), R.string.liveshare_basicmode_googleaccountcheck, 0).show();
                } else if (id == R.id.ayl) {
                    dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_YouTubeLiveStreaming_BasicModeView_Button_Cancel");
                    new Intent().setClass(BasicModeActivity.this, LiveshareActivity.class);
                    BasicModeActivity.this.finish();
                } else if (id == R.id.ayv) {
                    BasicModeActivity.youtubeManager.a();
                    BasicModeActivity.this.loginDialog();
                }
            }
        };
    }

    @SuppressLint({"JavascriptInterface"})
    private void youTubeLogin() {
        clearWebView();
        this.webView.setVisibility(0);
        this.webView.getSettings().setJavaScriptEnabled(true);
        if (VERSION.SDK_INT > 16) {
            this.webView.addJavascriptInterface(new JavaScriptInterfaceHigh(this), "HTML_PARSER");
        } else {
            this.webView.addJavascriptInterface(new JavaScriptInterfaceLow(), "HTML_PARSER");
        }
        this.webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                Log.d(BasicModeActivity.this.TAG, "shouldOverrideUrlLoading: ");
                return false;
            }

            @SuppressLint({"NewApi"})
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                Log.d(BasicModeActivity.this.TAG, "onPageFinished: ");
                if (str.startsWith("https://accounts.google.com/o/oauth2/approval")) {
                    BasicModeActivity.this.mHandleForUpdateYoutubeState.sendEmptyMessage(BasicModeActivity.MSG_HIDE_WEBVIEW);
                    Log.d(BasicModeActivity.this.TAG, "onPageFinished: view.evaluateJavascript");
                    webView.evaluateJavascript("(function(){return '<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>';})();", new ValueCallback<String>() {
                        public void onReceiveValue(String str) {
                            Log.d(BasicModeActivity.this.TAG, "onReceiveValue: ");
                            String replaceAll = str.replaceAll("<", "<").replaceAll(">", ">");
                            Log.v(WBConstants.ACTION_LOG_TYPE_SHARE, replaceAll);
                            BasicModeActivity.this.getYouTubeToken(replaceAll);
                        }
                    });
                }
            }
        });
        youtubeManager.a(new dji.pilot2.share.c.a() {
            public void onOauthUriGet(String str) {
                if (!TextUtils.isEmpty(str)) {
                    Log.d(BasicModeActivity.this.TAG, "onOauthUriGet: ");
                    BasicModeActivity.this.webView.loadUrl(str);
                }
            }

            public void onAccessTokenGet(String str) {
                if (TextUtils.isEmpty(str)) {
                    BasicModeActivity.this.showMessage(BasicModeActivity.this.getString(R.string.share_msg_login_failed_title), BasicModeActivity.this.getString(R.string.share_msg_login_failed_desc));
                    return;
                }
                BasicModeActivity.this.webView.setVisibility(8);
                Log.d(BasicModeActivity.this.TAG, "onAccessTokenGet: ");
            }
        });
    }

    private void clearWebView() {
        this.webView.clearView();
        this.webView.loadDataWithBaseURL(null, getString(R.string.share_msg_vibview_loading), "text/html", "utf-8", null);
    }

    public void showMessage(String str, String str2) {
        Builder builder = new Builder(this);
        builder.setTitle(str).setMessage(str2).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    public void getUserInfo() {
        youtubeManager.a(new c() {
            public void onGetUserInfo(String str, String str2) {
                BasicModeActivity.this.mEmail = str2;
                Bundle bundle = new Bundle();
                bundle.putBoolean("result", true);
                Message message = new Message();
                message.setData(bundle);
                message.what = BasicModeActivity.MSG_LOGIN_RESULT;
                BasicModeActivity.this.mHandleForUpdateYoutubeState.sendMessage(message);
            }
        });
    }

    public void showUserInfo() {
        if (this.mAccount != null) {
            Log.i(this.TAG, "process 1 " + this.mEmail);
            this.mAccount.setText(this.mEmail);
            this.checkGoogleAccount = true;
            return;
        }
        Log.i(this.TAG, "process 2 " + this.mEmail);
        this.mAccount = (Button) findViewById(R.id.ayv);
        this.mAccount.setText(this.mEmail);
        this.checkGoogleAccount = true;
    }

    public void onResume() {
        super.onResume();
        resumeWebView();
        resumeWebTimers();
    }

    public void onPause() {
        pauseWebTimers();
        pauseWebView();
        super.onPause();
    }

    private void resumeWebTimers() {
        try {
            this.webView.resumeTimers();
        } catch (Exception e) {
        }
    }

    private void pauseWebTimers() {
        try {
            this.webView.pauseTimers();
        } catch (Exception e) {
        }
    }

    private void resumeWebView() {
        try {
            this.webView.onResume();
        } catch (Exception e) {
        }
    }

    private void pauseWebView() {
        try {
            this.webView.onPause();
        } catch (Exception e) {
        }
    }

    private void getYouTubeToken(String str) {
        if (str != null) {
            int indexOf = str.indexOf("Success code=") + 13;
            int indexOf2 = str.indexOf("</title>");
            if (indexOf2 < indexOf) {
                indexOf2 = str.indexOf("\\u003C/title");
            }
            if (indexOf2 > indexOf) {
                String substring = str.substring(indexOf, indexOf2);
                Log.v(WBConstants.ACTION_LOG_TYPE_SHARE, substring);
                youtubeManager.a(substring, new d() {
                    public void onTokenGet(String str) {
                        Log.d(BasicModeActivity.this.TAG, "onTokenGet: accessToken = " + str);
                        if (!str.isEmpty()) {
                            BasicModeActivity.this.getUserInfo();
                        }
                    }
                });
                return;
            }
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("result", false);
        Message message = new Message();
        message.what = MSG_LOGIN_RESULT;
        message.setData(bundle);
        this.mHandleForUpdateYoutubeState.sendMessage(message);
    }
}
