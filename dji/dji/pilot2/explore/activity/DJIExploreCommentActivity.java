package dji.pilot2.explore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.dji.frame.c.c;
import com.dji.frame.c.l;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c.o;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.model.b;
import dji.pilot.publics.widget.DJISwipeListView;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.explore.b.a;
import dji.pilot2.explore.model.CommentListModel.Comment;
import dji.pilot2.mine.activity.ProfileTestActivity;
import dji.pilot2.nativeexplore.b.i;
import dji.pilot2.publics.b.a$i;
import dji.publics.DJIUI.DJIEditText;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DJIExploreCommentActivity extends DJIActivityNoFullScreen implements OnClickListener, o, a$i {
    protected static final String T = "DJIExploreCommentActivity";
    public static final String U = "comment_type";
    public static final String V = "comment_workid";
    OnScrollListener W = new OnScrollListener(this) {
        final /* synthetic */ DJIExploreCommentActivity a;
        private boolean b = false;

        {
            this.a = r2;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (this.b && i == 0) {
                this.a.at.d();
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (i + i2 != i3 || i3 <= 0) {
                this.b = false;
            } else {
                this.b = true;
            }
        }
    };
    OnItemClickListener X = new OnItemClickListener(this) {
        final /* synthetic */ DJIExploreCommentActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Comment comment = (Comment) this.a.av.getItem(i);
            Intent intent = new Intent(this.a, ProfileTestActivity.class);
            intent.putExtra("user_id", comment.account_id);
            intent.putExtra("user_name", comment.user);
            intent.putExtra(ProfileTestActivity.I, comment.avatar);
            this.a.startActivity(intent);
        }
    };
    private List<String> aA;
    private DJIEditText aB;
    private boolean aC = false;
    private Button aD;
    private DJITextView aE;
    private View aF;
    private View aG;
    private View aH;
    private View aI;
    private int aJ;
    private String aK;
    private boolean aL = false;
    private TextWatcher aM = new TextWatcher(this) {
        final /* synthetic */ DJIExploreCommentActivity a;

        {
            this.a = r1;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (this.a.aL) {
                this.a.aL = false;
            } else if (i3 >= 2) {
                DJILogHelper.getInstance().LOGD("rxq..", "onTextChanged resetText false, cursorPos: " + this.a.aJ + ", count:" + i3 + "str:" + charSequence.toString());
                if (DJIExploreCommentActivity.a(charSequence.subSequence(i, i + i3).toString())) {
                    this.a.aL = true;
                    this.a.aB.setText(this.a.aK);
                    this.a.aB.invalidate();
                    this.a.aB.setSelection(this.a.aJ);
                    Toast.makeText(this.a, this.a.getString(R.string.v2_explore_comment_not_emotion_icon), 0).show();
                }
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (this.a.aL) {
                Log.i("rxq..", "beforeTextChanged resetText true, cursorPos: " + this.a.aJ + "tmpstr: " + this.a.aK);
                return;
            }
            this.a.aJ = this.a.aB.getSelectionEnd();
            this.a.aK = charSequence.toString();
            Log.i("rxq..", "beforeTextChanged resetText false, cursorPos: " + this.a.aJ + "tmpstr: " + this.a.aK);
        }

        public void afterTextChanged(Editable editable) {
            if (l.a(editable.toString().trim())) {
                this.a.a(false);
            } else {
                this.a.a(true);
            }
        }
    };
    private a at;
    private DJISwipeListView au;
    private dji.pilot2.explore.a.a av;
    private String aw = null;
    private String ax = null;
    private String ay = null;
    private List<Comment> az;

    public static boolean a(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!a(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(char c) {
        return c == '\u0000' || c == '\t' || c == '\n' || c == '\r' || ((c >= ' ' && c <= '퟿') || ((c >= '' && c <= '�') || (c >= '\u0000' && c <= '￿')));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_explore_comment_activity);
        Intent intent = getIntent();
        this.ax = intent.getStringExtra(V);
        this.ay = intent.getStringExtra(U);
        String[] strArr = new String[]{b()};
        Map hashMap = new HashMap();
        if (f.getInstance().c()) {
            hashMap.put("token", f.getInstance().n());
        } else {
            hashMap.put("token", "");
        }
        this.at = new a(this, strArr, hashMap, ParamKey.PAGE, n.am);
        this.at.a(new i(this) {
            final /* synthetic */ DJIExploreCommentActivity a;

            {
                this.a = r1;
            }

            public void a(int i, boolean z, boolean z2) {
                this.a.aF.setVisibility(8);
                this.a.aG.setVisibility(8);
                this.a.aH.setVisibility(8);
                dji.pilot2.explore.c.a.a();
                this.a.av.a(z2);
                this.a.av.a(this.a.at.a);
                this.a.au.setHandleAllEvent(false);
                if (this.a.av.getCount() == 1) {
                    this.a.aE.setVisibility(0);
                } else {
                    this.a.aE.setVisibility(4);
                }
            }

            public void a(int i) {
                this.a.aF.setVisibility(8);
                this.a.aG.setVisibility(8);
                this.a.aH.setVisibility(0);
                this.a.av.b(false);
                this.a.au.setHandleAllEvent(false);
                if (this.a.av.getCount() == 1) {
                    this.a.aE.setVisibility(0);
                } else {
                    this.a.aE.setVisibility(4);
                }
            }
        });
        this.av = new dji.pilot2.explore.a.a(this);
        this.aB = (DJIEditText) findViewById(R.id.cku);
        this.aB.addTextChangedListener(this.aM);
        this.au = (DJISwipeListView) findViewById(R.id.ckt);
        this.au.setRightViewWidth(b.a(this, R.dimen.fm));
        this.au.setAdapter(this.av);
        this.au.setOnScrollListener(this.W);
        this.au.setOnItemClickListener(this.X);
        this.aF = findViewById(R.id.c6b);
        this.aF.setOnClickListener(this);
        this.aG = findViewById(R.id.d5c);
        this.aH = findViewById(R.id.d5a);
        this.aH.setOnClickListener(this);
        this.aI = findViewById(R.id.d5b);
        this.aI.setOnClickListener(this);
        this.aD = (Button) findViewById(R.id.ckv);
        this.aE = (DJITextView) findViewById(R.id.cks);
        a(false);
        a();
    }

    protected void onResume() {
        super.onResume();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (!inputMethodManager.isActive() && this.aB.getText().toString().trim().length() != 0) {
            inputMethodManager.showSoftInput(this.aB, 2);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        dji.pilot2.explore.c.a.b();
    }

    private void a() {
        this.au.setHandleAllEvent(true);
        this.at.c();
        this.aF.setVisibility(0);
    }

    private String b() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.ay.equals("photos")) {
            stringBuilder.append("https://www.skypixel.com/api/photos/").append(this.ax).append(a$i.ah);
        } else {
            stringBuilder.append("https://www.skypixel.com/api/videos/").append(this.ax).append(a$i.ah);
        }
        DJILogHelper.getInstance().LOGD(T, "get url: " + stringBuilder.toString());
        return stringBuilder.toString();
    }

    private String f() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.ay.equals("photos")) {
            stringBuilder.append("https://www.skypixel.com/api/photos/").append(this.ax).append(a$i.ah);
        } else {
            stringBuilder.append("https://www.skypixel.com/api/videos/").append(this.ax).append(a$i.ah);
        }
        DJILogHelper.getInstance().LOGD(T, "get url: " + stringBuilder.toString());
        return stringBuilder.toString();
    }

    private String b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.ay.equals("photos")) {
            stringBuilder.append("https://www.skypixel.com/api/photos/").append(this.ax).append(a$i.ah).append(d.t).append(str);
        } else {
            stringBuilder.append("https://www.skypixel.com/api/videos/").append(this.ax).append(a$i.ah).append(d.t).append(str);
        }
        DJILogHelper.getInstance().LOGD(T, "delete url: " + stringBuilder.toString());
        return stringBuilder.toString();
    }

    private void a(String str, String str2) {
        this.aF.setVisibility(0);
        this.av.b(true);
        dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
        bVar.a("comment[content]", str2);
        bVar.a("token", f.getInstance().n());
        c.b(this).b(str, bVar, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ DJIExploreCommentActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                this.a.au.setHandleAllEvent(true);
                this.a.aC = true;
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                this.a.aF.setVisibility(4);
                DJILogHelper.getInstance().LOGD(DJIExploreCommentActivity.T, "post comment succ..");
                this.a.au.setHandleAllEvent(false);
                this.a.aB.setText("");
                this.a.aB.clearFocus();
                this.a.aC = false;
                this.a.a();
            }

            public void a(Throwable th, int i, String str) {
                this.a.aF.setVisibility(4);
                this.a.aC = false;
                this.a.au.setHandleAllEvent(false);
                this.a.av.b(false);
                this.a.aH.setVisibility(0);
                Toast.makeText(this.a, this.a.getString(R.string.v2_explore_comment_post_fail), 0).show();
            }
        });
    }

    private void a(final int i) {
        this.aF.setVisibility(0);
        String b = b(String.valueOf(i));
        dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
        bVar.a("token", f.getInstance().n());
        c.b(this).d(b, bVar, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ DJIExploreCommentActivity b;

            public void a(boolean z) {
                this.b.au.setHandleAllEvent(true);
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                this.b.aF.setVisibility(4);
                DJILogHelper.getInstance().LOGD(DJIExploreCommentActivity.T, "del comment succ..");
                this.b.au.setHandleAllEvent(false);
                Toast.makeText(this.b, this.b.getString(R.string.v2_explore_comment_delete_succ), 0).show();
                this.b.at.c(i);
                this.b.av.a(i);
                if (this.b.av.getCount() == 1) {
                    this.b.aE.setVisibility(0);
                } else {
                    this.b.aE.setVisibility(4);
                }
            }

            public void a(Throwable th, int i, String str) {
                this.b.aF.setVisibility(4);
                this.b.au.setHandleAllEvent(false);
                this.b.aH.setVisibility(0);
                DJILogHelper.getInstance().LOGD(DJIExploreCommentActivity.T, "del comment faile..");
            }
        });
    }

    private List<String> a(List<Comment> list) {
        List<String> arrayList = new ArrayList();
        for (Comment comment : list) {
            arrayList.add(comment.avatar);
        }
        return arrayList;
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.ckr:
                finish();
                return;
            case R.id.ckv:
                this.au.hiddenRight();
                g();
                return;
            default:
                return;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cl0:
                this.au.hiddenRight();
                a(((Comment) this.av.getItem(((Integer) view.getTag()).intValue())).id);
                return;
            case R.id.d5b:
                a();
                this.aG.setVisibility(0);
                return;
            default:
                return;
        }
    }

    private void a(boolean z) {
        if (z) {
            if (this.aD != null) {
                this.aD.setSelected(true);
                this.aD.setClickable(true);
            }
        } else if (this.aD != null) {
            this.aD.setSelected(false);
            this.aD.setClickable(false);
        }
    }

    private void g() {
        e.b(o.z);
        String trim = this.aB.getText().toString().trim();
        if (trim.length() == 0) {
            DJILogHelper.getInstance().LOGD(T, "no content to post");
            return;
        }
        String f = f();
        if (!this.aC) {
            if (f.getInstance().c()) {
                this.aC = true;
                a(f, trim);
                ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.aB.getWindowToken(), 0);
                return;
            }
            Intent intent = new Intent(this, DJIAccountSignActivity.class);
            intent.putExtra(DJIAccountSignActivity.a, 1005);
            startActivityForResult(intent, 2);
        }
    }
}
