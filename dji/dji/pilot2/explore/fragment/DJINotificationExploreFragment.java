package dji.pilot2.explore.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import dji.pilot.R;
import dji.pilot.fpv.d.c.o;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.n;
import dji.pilot.visual.a.d;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.explore.activity.DJIExploreCommentActivity;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.explore.model.DJIMsgBean;
import dji.pilot2.explore.model.DJINoticeBean;
import dji.pilot2.mine.activity.ProfileTestActivity;
import dji.pilot2.nativeexplore.activity.ArtworkDetailActivity;
import dji.pilot2.nativeexplore.activity.GLExporeActivity;
import dji.pilot2.utils.k;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJITextView;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DJINotificationExploreFragment extends DJIActivityNoFullScreen implements OnClickListener, o {
    public static final String T = "key_force_landscap";
    public boolean U = false;
    private DJIStateImageView V;
    private DJIDragListView W;
    private DJIDragListView X;
    private c Y;
    private c Z;
    private DisplayImageOptions aA = new Builder().showImageOnLoading(null).cacheInMemory(true).cacheOnDisc(false).build();
    private Handler aB = new Handler(this) {
        final /* synthetic */ DJINotificationExploreFragment a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == 101) {
                this.a.aa.setSelected(false);
                this.a.ax.setVisibility(8);
                this.a.ab.setSelected(true);
                this.a.ay.setVisibility(0);
                if (this.a.ag.size() == 0) {
                    this.a.ap.show();
                } else {
                    this.a.ap.go();
                }
                this.a.aq.go();
            }
            if (message.what == 102) {
                this.a.aa.setSelected(true);
                this.a.ab.setSelected(false);
                this.a.ax.setVisibility(0);
                this.a.ay.setVisibility(8);
                if (this.a.ah.size() == 0) {
                    this.a.aq.show();
                } else {
                    this.a.aq.go();
                }
                this.a.ap.go();
            }
        }
    };
    private DJITextView aa;
    private DJITextView ab;
    private DJITextView ac;
    private LinearLayout ad;
    private LinearLayout ae;
    private ProgressBar af;
    private List<DJINoticeBean> ag;
    private List<DJIMsgBean> ah;
    private List<DJIMsgBean> ai;
    private ImageLoader aj;
    private final int ak = 101;
    private final int al = 102;
    private b am = new b(this);
    private Context an;
    private final String ao = "DJINotificationExploreFragment";
    private DJITextView ap;
    private DJITextView aq;
    private int ar = 0;
    private int as = 1;
    private boolean at = false;
    private boolean au = false;
    private int av = 1;
    private int aw;
    private View ax;
    private View ay;
    private DisplayImageOptions az = new Builder().showImageOnLoading(R.drawable.v2_avatar_default).cacheInMemory(true).cacheOnDisc(false).build();

    private enum a {
        EXPLORE,
        NOTICE
    }

    public class b extends Thread {
        dji.thirdparty.afinal.c a;
        final /* synthetic */ DJINotificationExploreFragment b;

        public b(DJINotificationExploreFragment dJINotificationExploreFragment) {
            this.b = dJINotificationExploreFragment;
            this.a = com.dji.frame.c.c.b(dJINotificationExploreFragment.an);
        }

        public void a(int i) {
            Log.i("zzhang", "fetchMsg");
            this.a.a(k.a(f.getInstance().n(), i), new dji.thirdparty.afinal.f.a<String>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                    this.a.b.ap.go();
                }

                public void a(long j, long j2) {
                }

                public void a(String str) {
                    Log.i("DJINotificationExploreFragment", "msg onsuccess");
                    this.a.b.av = this.a.b.av + 1;
                    this.a.b.ai.clear();
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject != null) {
                        this.a.b.ar = jSONObject.getInt("total");
                        jSONObject.getInt(n.am);
                        JSONArray jSONArray = jSONObject.getJSONArray("messagelist");
                        if (this.a.b.ar > 0 && jSONArray != null && jSONArray.length() > 0) {
                            for (int i = 0; i < jSONArray.length(); i++) {
                                jSONObject = (JSONObject) jSONArray.get(i);
                                if (jSONObject != null) {
                                    DJIMsgBean dJIMsgBean = new DJIMsgBean();
                                    dJIMsgBean.mUserId = jSONObject.getString("commenter_id");
                                    dJIMsgBean.mUserName = jSONObject.getString("commenter_name");
                                    dJIMsgBean.mTime = jSONObject.getString(n.ax);
                                    try {
                                        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(this.a.b.a(dJIMsgBean.mTime).getTimeInMillis()));
                                        Log.i("zhang", "cur:" + format);
                                        dJIMsgBean.mTime = format;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        dJIMsgBean.mType = jSONObject.getString("feed_type");
                                        dJIMsgBean.mUserHeadPic = jSONObject.getString("avatar");
                                        if (!(dJIMsgBean.mType == null || dJIMsgBean.mType.equals("follow"))) {
                                            dJIMsgBean.mMediaType = jSONObject.getString("type");
                                            try {
                                                if ("story".equals(dJIMsgBean.mMediaType)) {
                                                    dJIMsgBean.mWorkUrl = jSONObject.getString("cover_image");
                                                } else {
                                                    dJIMsgBean.mWorkUrl = jSONObject.getString("thumb_s_url");
                                                }
                                            } catch (Exception e2) {
                                                e2.printStackTrace();
                                            }
                                            dJIMsgBean.mMsg = jSONObject.getString("title");
                                            if (dJIMsgBean.mMediaType != null && dJIMsgBean.mMediaType.equals("videos")) {
                                                dJIMsgBean.mDuration = jSONObject.getInt("duration");
                                            }
                                            dJIMsgBean.mWorkId = jSONObject.getString("id");
                                        }
                                        this.a.b.ai.add(dJIMsgBean);
                                    } catch (Exception e3) {
                                        Log.i("DJINotificationExploreFragment", "json has error");
                                        e3.printStackTrace();
                                        this.a.b.W.onRefreshComplete();
                                        this.a.b.au = false;
                                        this.a.b.af.setVisibility(8);
                                        return;
                                    }
                                }
                            }
                        }
                        if (this.a.b.ai == null || this.a.b.ai.size() <= 0) {
                            this.a.b.ap.show();
                        } else {
                            this.a.b.aw = this.a.b.aw + this.a.b.ai.size();
                            this.a.b.ah.addAll(this.a.b.ai);
                            this.a.b.ap.go();
                            Log.i("zzhang", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>line<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                            Log.i("zzhang", "total size:" + this.a.b.ar);
                            Log.i("zzhang", "mCurrentPage:" + this.a.b.av);
                            Log.i("zzhang", "mTempList add size:" + this.a.b.ai.size());
                            Log.i("zzhang", "mMsgCount:" + this.a.b.aw);
                            Log.i("zzhang", "list size:" + this.a.b.ah.size());
                        }
                        this.a.b.Y.notifyDataSetChanged();
                        this.a.b.W.onRefreshComplete();
                        this.a.b.W.onLoadMoreComplete(true);
                        this.a.b.au = false;
                        this.a.b.af.setVisibility(8);
                    }
                }

                public void a(Throwable th, int i, String str) {
                    Log.i("DJINotificationExploreFragment", "msg failed");
                    this.a.b.W.onRefreshComplete();
                    this.a.b.au = false;
                    this.a.b.af.setVisibility(8);
                }
            });
        }

        public void b(int i) {
            this.a.a(k.b(), new dji.thirdparty.afinal.f.a<String>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void a(long j, long j2) {
                }

                public void a(String str) {
                    Log.i("DJINotificationExploreFragment", "data onsuccess");
                    this.a.b.ag.clear();
                    try {
                        JSONArray jSONArray = (JSONArray) new JSONObject(str).get("notices");
                        if (jSONArray != null && jSONArray.length() > 0) {
                            for (int i = 0; i < jSONArray.length(); i++) {
                                DJINoticeBean dJINoticeBean = new DJINoticeBean();
                                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                                dJINoticeBean.mTitle = jSONObject.getString("title");
                                Log.i("zc", "title :" + dJINoticeBean.mTitle);
                                dJINoticeBean.mSubTitle = jSONObject.getString("subtitle");
                                dJINoticeBean.mTargetUrl = jSONObject.getString(dji.pilot2.main.a.a.j);
                                dJINoticeBean.mHeadImgLink = jSONObject.getString("avatar_img");
                                dJINoticeBean.mThumbnailImgLink = jSONObject.getString("thumbnail_img");
                                dJINoticeBean.mHeadMd5 = jSONObject.getString("avatar_md5");
                                dJINoticeBean.mThumbnailMd5 = jSONObject.getString("thumbnail_md5");
                                dJINoticeBean.mCreatTime = jSONObject.getInt(n.L);
                                dJINoticeBean.mUpdatetime = jSONObject.getInt(n.M);
                                this.a.b.ag.add(dJINoticeBean);
                            }
                            this.a.b.Z.notifyDataSetChanged();
                            this.a.b.X.onRefreshComplete();
                            this.a.b.at = false;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        this.a.b.X.onRefreshComplete();
                        this.a.b.at = false;
                    }
                }

                public void a(Throwable th, int i, String str) {
                    Log.i("DJINotificationExploreFragment", "onFailure");
                    this.a.b.X.onRefreshComplete();
                    this.a.b.at = false;
                }

                public void a(boolean z) {
                }
            });
        }

        public void run() {
            this.b.ah.clear();
            this.b.ag.clear();
            if (!f.getInstance().c() || this.b.au) {
                this.b.ap.show();
                this.b.af.setVisibility(8);
                this.b.au = false;
            } else {
                a(this.b.av);
                this.b.au = true;
            }
            if (!this.b.at) {
                b(this.b.as);
            }
        }
    }

    public class c extends BaseAdapter {
        final /* synthetic */ DJINotificationExploreFragment a;
        private a b;

        public class a {
            public ImageView a;
            public ImageView b;
            public DJITextView c;
            public DJITextView d;
            public DJITextView e;
            final /* synthetic */ c f;

            public a(c cVar) {
                this.f = cVar;
            }
        }

        public c(DJINotificationExploreFragment dJINotificationExploreFragment, a aVar) {
            this.a = dJINotificationExploreFragment;
            this.b = aVar;
        }

        public int getCount() {
            if (this.b == a.EXPLORE) {
                return this.a.ah.size();
            }
            if (this.b == a.NOTICE) {
                return this.a.ag.size();
            }
            return 0;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = LayoutInflater.from(this.a.an).inflate(R.layout.v2_explore_draglist_item, null);
                aVar = new a(this);
                aVar.a = (ImageView) view.findViewById(R.id.cl2);
                aVar.b = (ImageView) view.findViewById(R.id.cl6);
                aVar.c = (DJITextView) view.findViewById(R.id.cl3);
                aVar.d = (DJITextView) view.findViewById(R.id.cl5);
                aVar.e = (DJITextView) view.findViewById(R.id.cl4);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            if (this.b == a.EXPLORE) {
                if (this.a.ah.size() > 0) {
                    final DJIMsgBean dJIMsgBean = (DJIMsgBean) this.a.ah.get(i);
                    aVar.e.setVisibility(0);
                    aVar.e.setText(dJIMsgBean.mMsg);
                    aVar.d.setText(dJIMsgBean.mTime.substring(0, dJIMsgBean.mTime.length() - 3));
                    if (dJIMsgBean.mType != null) {
                        if (dJIMsgBean.mType.equals("follow")) {
                            aVar.c.setText(dJIMsgBean.mUserName + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.a.an.getResources().getString(R.string.v2_notification_follow));
                            aVar.b.setVisibility(8);
                            aVar.e.show();
                        } else if (dJIMsgBean.mType.equals("like")) {
                            aVar.c.setText(dJIMsgBean.mUserName + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.a.an.getResources().getString(R.string.v2_notification_like));
                            aVar.e.show();
                        } else {
                            aVar.c.setText(dJIMsgBean.mUserName + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.a.an.getResources().getString(R.string.v2_notification_comment, new Object[]{dJIMsgBean.mMsg}));
                            aVar.e.go();
                            aVar.b.setVisibility(0);
                            aVar.e.go();
                        }
                    }
                    if (!(dJIMsgBean.mWorkUrl == null || dJIMsgBean.mWorkUrl.contains("http"))) {
                        dJIMsgBean.mWorkUrl = "http:" + dJIMsgBean.mWorkUrl;
                    }
                    aVar.a.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ c b;

                        public void onClick(View view) {
                            e.b(o.J);
                            Log.i("DJINotificationExploreFragment", "user id :" + f.getInstance().i());
                            Log.i("DJINotificationExploreFragment", "cur user id :" + dJIMsgBean.mUserId);
                            Intent intent = new Intent(this.b.a.an, ProfileTestActivity.class);
                            intent.putExtra("user_id", dJIMsgBean.mUserId);
                            intent.putExtra("user_name", dJIMsgBean.mUserName);
                            intent.putExtra(ProfileTestActivity.I, dJIMsgBean.mUserHeadPic);
                            this.b.a.startActivity(intent);
                        }
                    });
                    aVar.e.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ c b;

                        public void onClick(View view) {
                            e.b(o.J);
                            Log.i("DJINotificationExploreFragment", "work id:" + dJIMsgBean.mWorkId);
                            Log.i("DJINotificationExploreFragment", "work type:" + dJIMsgBean.mMediaType);
                            if (dJIMsgBean.mType.equals("follow")) {
                                Log.i("DJINotificationExploreFragment", "follow");
                            } else if (dJIMsgBean.mType.equals("like")) {
                                Log.i("DJINotificationExploreFragment", "like");
                                r0 = new Intent(this.b.a.an, ArtworkDetailActivity.class);
                                r0.putExtra("id", dJIMsgBean.mWorkId);
                                r0.putExtra("type", dJIMsgBean.mMediaType);
                                r0.putExtra("duration", dJIMsgBean.mDuration);
                                this.b.a.startActivity(r0);
                            } else {
                                Log.i("DJINotificationExploreFragment", "comment");
                                r0 = new Intent(this.b.a.an, DJIExploreCommentActivity.class);
                                r0.putExtra(DJIExploreCommentActivity.V, dJIMsgBean.mWorkId);
                                r0.putExtra(DJIExploreCommentActivity.U, dJIMsgBean.mMediaType);
                                this.b.a.startActivity(r0);
                            }
                        }
                    });
                    aVar.b.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ c b;

                        public void onClick(View view) {
                            e.b(o.J);
                            Intent intent = new Intent(this.b.a.an, ArtworkDetailActivity.class);
                            intent.putExtra("id", dJIMsgBean.mWorkId);
                            intent.putExtra("type", dJIMsgBean.mMediaType);
                            intent.putExtra("duration", dJIMsgBean.mDuration);
                            this.b.a.startActivity(intent);
                        }
                    });
                    aVar.a.setImageResource(R.drawable.v2_avatar_default);
                    ImageLoader.getInstance().displayImage(dJIMsgBean.mUserHeadPic, new ImageViewAware(aVar.a, false), this.a.az);
                    Log.i("DJINotificationExploreFragment", "work url:" + dJIMsgBean.mUserHeadPic);
                    this.a.aj.displayImage(dJIMsgBean.mWorkUrl, aVar.b, this.a.aA);
                }
            } else if (this.a.ag.size() > 0) {
                DJINoticeBean dJINoticeBean = (DJINoticeBean) this.a.ag.get(i);
                aVar.c.setText(dJINoticeBean.mTitle);
                if (dJINoticeBean.mSubTitle == null || dJINoticeBean.mSubTitle.isEmpty()) {
                    aVar.d.go();
                    LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams.addRule(16);
                    aVar.c.setLayoutParams(layoutParams);
                } else {
                    aVar.d.show();
                    aVar.d.setText(dJINoticeBean.mSubTitle);
                }
                this.a.aj.displayImage(dJINoticeBean.mHeadImgLink, aVar.a, this.a.az);
                if (dJINoticeBean.mThumbnailImgLink == null) {
                    aVar.b.setVisibility(8);
                } else {
                    aVar.b.setVisibility(0);
                    this.a.aj.displayImage(dJINoticeBean.mThumbnailImgLink, aVar.b, this.a.aA);
                }
            }
            return view;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.U = getIntent().getBooleanExtra("key_force_landscap", false);
        setContentView(R.layout.v2_explore_notification);
        a();
    }

    private void a() {
        this.V = (DJIStateImageView) findViewById(R.id.cm_);
        this.W = (DJIDragListView) findViewById(R.id.cmf);
        this.X = (DJIDragListView) findViewById(R.id.cmd);
        this.aa = (DJITextView) findViewById(R.id.cm6);
        this.ab = (DJITextView) findViewById(R.id.cm8);
        this.aa.setOnClickListener(this);
        this.aa.setSelected(true);
        this.ab.setOnClickListener(this);
        this.ad = (LinearLayout) findViewById(R.id.cmc);
        this.ae = (LinearLayout) findViewById(R.id.cme);
        this.ag = new ArrayList();
        this.ah = new ArrayList();
        this.ai = new ArrayList();
        this.ax = findViewById(R.id.cm7);
        this.ay = findViewById(R.id.cm9);
        this.aj = ImageLoader.getInstance();
        this.ap = (DJITextView) findViewById(R.id.cmg);
        this.aq = (DJITextView) findViewById(R.id.cmh);
        this.af = (ProgressBar) findViewById(R.id.cmi);
        this.V.setOnClickListener(this);
        this.an = this;
        this.W.setOnRefreshListener(new dji.pilot2.explore.fragment.DJIDragListView.c(this) {
            final /* synthetic */ DJINotificationExploreFragment a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.av = 1;
                this.a.ar = 0;
                this.a.aw = 0;
                this.a.ah.clear();
                this.a.ai.clear();
                this.a.am.a(this.a.av);
                this.a.au = true;
            }

            public void b() {
                if (this.a.aw < this.a.ar) {
                    this.a.am.a(this.a.av);
                }
            }

            public void a(boolean z) {
                this.a.aB.sendEmptyMessageDelayed(102, 500);
            }
        });
        this.Y = new c(this, a.EXPLORE);
        this.W.setAdapter(this.Y);
        this.W.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DJINotificationExploreFragment a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                DJIMsgBean dJIMsgBean = (DJIMsgBean) this.a.ah.get(i - 1);
                if (!(dJIMsgBean.mType == null || !dJIMsgBean.mType.equals("follow") || dJIMsgBean.mMediaType == null || dJIMsgBean.mMediaType.equals("story"))) {
                    Log.i("DJINotificationExploreFragment", "user id :" + f.getInstance().i());
                    Log.i("DJINotificationExploreFragment", "cur user id :" + dJIMsgBean.mUserId);
                    Intent intent = new Intent(this.a.an, ProfileTestActivity.class);
                    intent.putExtra("user_id", dJIMsgBean.mUserId);
                    intent.putExtra("user_name", dJIMsgBean.mUserName);
                    intent.putExtra(ProfileTestActivity.I, dJIMsgBean.mUserHeadPic);
                    this.a.startActivity(intent);
                }
                if (!(!dJIMsgBean.mType.equals("like") || dJIMsgBean.mMediaType == null || dJIMsgBean.mMediaType.equals("story"))) {
                    intent = new Intent(this.a.an, ArtworkDetailActivity.class);
                    intent.putExtra("id", dJIMsgBean.mWorkId);
                    intent.putExtra("type", dJIMsgBean.mMediaType);
                    intent.putExtra("duration", dJIMsgBean.mDuration);
                    this.a.startActivity(intent);
                }
                if (!(!dJIMsgBean.mType.equals("comment") || dJIMsgBean.mMediaType == null || dJIMsgBean.mMediaType.equals("story"))) {
                    Log.i("DJINotificationExploreFragment", "comment");
                    intent = new Intent(this.a.an, DJIExploreCommentActivity.class);
                    intent.putExtra(DJIExploreCommentActivity.V, dJIMsgBean.mWorkId);
                    intent.putExtra(DJIExploreCommentActivity.U, dJIMsgBean.mMediaType);
                    this.a.startActivity(intent);
                }
                if (dJIMsgBean.mMediaType != null && dJIMsgBean.mMediaType.equals("story")) {
                    Log.i("DJINotificationExploreFragment", "story :" + dJIMsgBean.mWorkId);
                    intent = new Intent(this.a.an, GLExporeActivity.class);
                    intent.putExtra("id", dJIMsgBean.mWorkId);
                    this.a.startActivity(intent);
                }
            }
        });
        this.Y.notifyDataSetChanged();
        this.X.setOnRefreshListener(new dji.pilot2.explore.fragment.DJIDragListView.c(this) {
            final /* synthetic */ DJINotificationExploreFragment a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.as = 1;
                this.a.am.b(this.a.as);
                this.a.at = true;
            }

            public void b() {
            }

            public void a(boolean z) {
                this.a.aB.sendEmptyMessageDelayed(101, 500);
            }
        });
        this.X.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DJINotificationExploreFragment a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                DJINoticeBean dJINoticeBean = (DJINoticeBean) this.a.ag.get(i - 1);
                String str = dJINoticeBean.mTargetUrl;
                if (dJINoticeBean != null && !dJINoticeBean.equals("")) {
                    Intent intent = new Intent(this.a, DJISupportShareWebviewActivity.class);
                    intent.putExtra(DJIWebviewFragment.o, str);
                    this.a.startActivity(intent);
                    e.b(o.L);
                }
            }
        });
        this.Z = new c(this, a.NOTICE);
        this.X.setAdapter(this.Z);
        this.Z.notifyDataSetChanged();
        this.am.start();
    }

    private Calendar a(String str) {
        String substring = str.substring(0, 4);
        String substring2 = str.substring(5, 7);
        String substring3 = str.substring(8, 10);
        String substring4 = str.substring(11, 13);
        String substring5 = str.substring(14, 16);
        String substring6 = str.substring(17, 19);
        int intValue = Integer.valueOf(str.substring(20, 23)).intValue();
        Calendar gregorianCalendar = new GregorianCalendar(Integer.valueOf(substring).intValue(), Integer.valueOf(substring2).intValue() - 1, Integer.valueOf(substring3).intValue(), Integer.valueOf(substring4).intValue(), Integer.valueOf(substring5).intValue(), Integer.valueOf(substring6).intValue());
        gregorianCalendar.set(14, intValue);
        gregorianCalendar.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        return gregorianCalendar;
    }

    public void onResume() {
        super.onResume();
        if (this.U && getRequestedOrientation() != 0) {
            setRequestedOrientation(0);
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cm6:
                e.b(o.I);
                this.ad.setVisibility(8);
                this.ae.setVisibility(0);
                this.aa.setSelected(true);
                this.ab.setSelected(false);
                this.ax.setVisibility(0);
                this.ay.setVisibility(8);
                if (this.ah.size() != 0 || this.af.getVisibility() == 0) {
                    this.ap.go();
                } else {
                    this.ap.show();
                }
                Log.i("zyt", "isMsgRefreshing:" + this.au);
                if (this.au) {
                    this.af.setVisibility(0);
                    this.ap.go();
                }
                this.aq.go();
                return;
            case R.id.cm8:
                e.b(o.K);
                this.af.setVisibility(8);
                this.ae.setVisibility(8);
                this.ad.setVisibility(0);
                this.aa.setSelected(false);
                this.ab.setSelected(true);
                this.ax.setVisibility(8);
                this.ay.setVisibility(0);
                if (this.ag.size() == 0) {
                    this.aq.show();
                } else {
                    this.aq.go();
                }
                this.ap.go();
                return;
            case R.id.cm_:
                finish();
                return;
            default:
                return;
        }
    }

    private Bitmap a(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int i;
        int dimension = (int) ((this.an.getResources().getDisplayMetrics().density * this.an.getResources().getDimension(R.dimen.gq)) + d.c);
        if (dimension > dimension) {
            i = dimension;
        } else {
            i = dimension;
        }
        Bitmap createBitmap = Bitmap.createBitmap(dimension, dimension, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0.0f, 0.0f, (float) i, (float) i);
        canvas.drawRoundRect(rectF, (float) (i / 2), (float) (i / 2), paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, null, rectF, paint);
        return createBitmap;
    }
}
