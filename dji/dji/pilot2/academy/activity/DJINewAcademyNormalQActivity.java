package dji.pilot2.academy.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.publics.c.d;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.n;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.academy.model.FrequentlyQuestionModel.Questions;
import dji.pilot2.academy.widget.e;
import dji.pilot2.academy.widget.e.b;
import dji.pilot2.nativeexplore.b.i;
import dji.pilot2.publics.b.a$j;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DJINewAcademyNormalQActivity extends DJIActivityNoFullScreen implements OnClickListener, a$j {
    public static String a = "name";
    private View A;
    private View B;
    private View C;
    private ExpandableListView D;
    private e E;
    private dji.pilot2.academy.a.a F;
    private ProductType G = ProductType.None;
    private String H;
    private SearchView I;
    private ListView J;
    private a K;
    private DJITextView b;
    private DJIStateImageView w;
    private OnClickListener x = null;
    private View y;
    private View z;

    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] a = new int[ProductType.values().length];

        static {
            try {
                a[ProductType.Pomato.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ProductType.Tomato.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ProductType.P34K.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ProductType.litchiX.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[ProductType.litchiS.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[ProductType.litchiC.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[ProductType.Orange.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[ProductType.BigBanana.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[ProductType.OrangeRAW.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[ProductType.OrangeCV600.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[ProductType.N1.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[ProductType.Longan.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[ProductType.LonganPro.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[ProductType.LonganRaw.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[ProductType.LonganZoom.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[ProductType.Grape2.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[ProductType.Olives.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                a[ProductType.KumquatS.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                a[ProductType.KumquatX.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
        }
    }

    public class a extends BaseAdapter {
        final /* synthetic */ DJINewAcademyNormalQActivity a;
        private Context b;
        private List<Questions> c;

        public a(DJINewAcademyNormalQActivity dJINewAcademyNormalQActivity, Context context) {
            this.a = dJINewAcademyNormalQActivity;
            this.b = context;
        }

        public synchronized void a(List<Questions> list) {
            this.c = list;
            notifyDataSetChanged();
        }

        public int getCount() {
            if (this.c != null) {
                return this.c.size();
            }
            return 0;
        }

        public Object getItem(int i) {
            if (this.c != null) {
                return this.c.get(i);
            }
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.b).inflate(R.layout.v2_faq_search_suggestion, null);
            }
            ((TextView) view.findViewById(R.id.cn2)).setText(((Questions) this.c.get(i)).question);
            return view;
        }
    }

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_academy_questions);
        this.G = ProductType.find(getIntent().getIntExtra("key_product_index", 0));
        this.H = d.getInstance().k(this.G);
        a();
        b();
        f();
        g();
        i();
        l();
        this.z.setVisibility(0);
    }

    protected void a() {
        this.b = (DJITextView) findViewById(R.id.c43);
        this.w = (DJIStateImageView) findViewById(R.id.c42);
        this.D = (ExpandableListView) findViewById(R.id.c6_);
        this.y = findViewById(R.id.c69);
        this.z = findViewById(R.id.c6b);
        this.z.setOnClickListener(this);
        this.A = findViewById(R.id.d5c);
        this.B = findViewById(R.id.d5a);
        this.B.setOnClickListener(this);
        this.C = findViewById(R.id.d5b);
        this.C.setOnClickListener(this);
    }

    protected void b() {
        this.x = new OnClickListener(this) {
            final /* synthetic */ DJINewAcademyNormalQActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.c42:
                        this.a.finish();
                        return;
                    case R.id.d5b:
                        this.a.h();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    protected void f() {
        this.E = new e(getApplicationContext());
    }

    protected void g() {
        CharSequence string = getIntent().getExtras().getString(a, "");
        DJILogHelper.getInstance().LOGI("bob", "DJINewAcademyNormalQActivity nameString= " + string);
        this.b.setOnClickListener(this.x);
        this.b.setText(string);
        this.w.setOnClickListener(this.x);
        this.D.setAdapter(this.E);
        this.D.setOnChildClickListener(new OnChildClickListener(this) {
            final /* synthetic */ DJINewAcademyNormalQActivity a;

            {
                this.a = r1;
            }

            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
                Questions questions = (Questions) this.a.E.getChild(i, i2);
                Intent intent = new Intent(this.a, DJIFaqAnswerActivity.class);
                intent.putExtra(DJIFaqAnswerActivity.a, questions.question);
                intent.putExtra(DJIFaqAnswerActivity.b, questions.answer);
                this.a.startActivity(intent);
                return true;
            }
        });
        this.D.setOnGroupClickListener(new OnGroupClickListener(this) {
            final /* synthetic */ DJINewAcademyNormalQActivity a;

            {
                this.a = r1;
            }

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                return false;
            }
        });
        this.E.a(new b(this) {
            final /* synthetic */ DJINewAcademyNormalQActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                DJILogHelper.getInstance().LOGI("bob", "onGroupExpanded  groupPosition " + i);
            }

            public void b(int i) {
                DJILogHelper.getInstance().LOGI("bob", "onGroupCollapsed groupPosition " + i);
            }
        });
        this.D.setGroupIndicator(null);
        this.D.setDivider(null);
        this.D.setChildDivider(null);
        this.D.setDividerHeight(0);
        if (this.I != null) {
            this.I.setIconifiedByDefault(false);
            this.I.setSubmitButtonEnabled(false);
            this.I.setOnQueryTextListener(new OnQueryTextListener(this) {
                final /* synthetic */ DJINewAcademyNormalQActivity a;

                {
                    this.a = r1;
                }

                public boolean onQueryTextChange(String str) {
                    this.a.K.a(this.a.a(str));
                    return false;
                }

                public boolean onQueryTextSubmit(String str) {
                    return false;
                }
            });
            string = new SpannableString(getString(R.string.v2_academy_flight_search_hint));
            string.setSpan(new AbsoluteSizeSpan(14, true), 0, string.length(), 33);
            this.I.setQueryHint(new SpannedString(string));
        }
        this.J = (ListView) findViewById(R.id.c6a);
        this.K = new a(this, this);
        this.J.setAdapter(this.K);
        this.J.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DJINewAcademyNormalQActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Questions questions = (Questions) this.a.K.getItem(i);
                Intent intent = new Intent(this.a, DJIFaqAnswerActivity.class);
                intent.putExtra(DJIFaqAnswerActivity.a, questions.question);
                intent.putExtra(DJIFaqAnswerActivity.b, questions.answer);
                this.a.startActivity(intent);
            }
        });
    }

    private void i() {
        String[] strArr = new String[]{j()};
        Map hashMap = new HashMap();
        if (f.getInstance().c()) {
            hashMap.put("token", f.getInstance().n());
        } else {
            hashMap.put("token", "");
        }
        this.F = new dji.pilot2.academy.a.a(this, strArr, hashMap, ParamKey.PAGE, n.am);
        this.F.a(new i(this) {
            final /* synthetic */ DJINewAcademyNormalQActivity a;

            {
                this.a = r1;
            }

            public void a(int i, boolean z, boolean z2) {
                this.a.z.setVisibility(8);
                this.a.A.setVisibility(8);
                this.a.B.setVisibility(8);
                this.a.E.a(this.a.F.a);
                if (this.a.E.getGroupCount() == 0) {
                    this.a.y.setVisibility(0);
                } else {
                    this.a.y.setVisibility(4);
                }
            }

            public void a(int i) {
                this.a.z.setVisibility(8);
                this.a.A.setVisibility(8);
                this.a.y.setVisibility(4);
                this.a.B.setVisibility(0);
            }
        });
    }

    protected void h() {
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(motionEvent);
    }

    private String j() {
        String str;
        if (k()) {
            str = "cn";
        } else {
            str = "en";
        }
        str = a$j.t + str + dji.pilot.usercenter.protocol.d.t + m();
        Log.i("rxq", "frequently asked question url: " + str);
        return str;
    }

    private boolean k() {
        if (getResources().getConfiguration().locale.getLanguage().endsWith("zh")) {
            return true;
        }
        return false;
    }

    private void l() {
        this.F.c();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.d5b:
                l();
                this.A.setVisibility(0);
                return;
            default:
                return;
        }
    }

    private String m() {
        switch (AnonymousClass8.a[this.G.ordinal()]) {
            case 1:
            case 2:
                return "phantom-4";
            case 3:
            case 4:
                return "phantom-3-pro";
            case 5:
                return "phantom-3-adv";
            case 6:
                return "phantom-3-standard";
            case 7:
            case 8:
            case 9:
            case 10:
                return "inspire-1";
            case 11:
                return "matrice-100";
            case 12:
            case 13:
            case 14:
            case 15:
                return "osmo";
            case 16:
                return "lightbridge-2";
            case 18:
            case 19:
                return "mavic";
            default:
                return null;
        }
    }

    public List<Questions> a(String str) {
        ArrayList arrayList = new ArrayList();
        if (str.equals("")) {
            return arrayList;
        }
        int groupCount = this.E.getGroupCount();
        for (int i = 0; i < groupCount; i++) {
            int childrenCount = this.E.getChildrenCount(i);
            for (int i2 = 0; i2 < childrenCount; i2++) {
                Questions questions = (Questions) this.E.getChild(i, i2);
                String str2 = questions.question;
                int indexOf = str2.indexOf(str);
                Log.i("rxq", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + indexOf);
                if (indexOf != -1) {
                    arrayList.add(questions);
                }
            }
        }
        return arrayList;
    }
}
