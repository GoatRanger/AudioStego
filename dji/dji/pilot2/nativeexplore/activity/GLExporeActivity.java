package dji.pilot2.nativeexplore.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import com.dji.frame.c.c;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.n;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.nativeexplore.b.b;
import dji.pilot2.nativeexplore.b.b.a;
import dji.pilot2.nativeexplore.model.BottomCommentModel;
import dji.pilot2.nativeexplore.model.MiddleListModel;
import dji.pilot2.nativeexplore.model.MiddleListModel.MiddleItemModel;
import dji.pilot2.nativeexplore.view.ExploreGLView;
import dji.pilot2.utils.k;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GLExporeActivity extends DJIActivityNoFullScreen implements OnClickListener {
    public static final String a = "id";
    private String A;
    private DJIImageView B;
    private DJIRelativeLayout C;
    private View D;
    private ExploreGLView b;
    private ScrollView c;
    private ImageView d;
    private ImageView t;
    private ImageView u;
    private ImageView v;
    private int w;
    private b x;
    private boolean y = false;
    private boolean z = false;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_glexplore_layout);
        this.b = (ExploreGLView) findViewById(R.id.gw);
        this.c = (ScrollView) findViewById(R.id.gv);
        this.C = (DJIRelativeLayout) findViewById(R.id.d5a);
        this.d = (ImageView) findViewById(R.id.gr);
        this.t = (ImageView) findViewById(R.id.gt);
        this.u = (ImageView) findViewById(R.id.gs);
        this.v = (ImageView) findViewById(R.id.gu);
        this.B = (DJIImageView) findViewById(R.id.d5c);
        this.D = findViewById(R.id.d5b);
        this.d.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.B.setOnClickListener(this);
        this.D.setOnClickListener(this);
        this.A = getIntent().getStringExtra("id");
        this.B.show();
        this.b.setTopListListener(new OnItemClickListener(this) {
            final /* synthetic */ GLExporeActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                this.a.c.smoothScrollTo(0, this.a.b.getListPositionById(i) + this.a.b.getTopPosition());
            }
        });
        this.x = b.getInstance(this);
        this.x.a(new a(this) {
            final /* synthetic */ GLExporeActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                Log.i("zhangchen", "post:" + z);
            }

            public void a(boolean z, BottomCommentModel bottomCommentModel) {
                Log.i("zhangchen", "");
            }

            public void b(boolean z) {
                Log.i("zhangchen", "delete:" + z);
            }
        });
        a();
        c.b(this).a(k.a(this.A), new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ GLExporeActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                if (str != null) {
                    BottomCommentModel bottomCommentModel = (BottomCommentModel) h.b(str, BottomCommentModel.class);
                    if (bottomCommentModel != null) {
                        this.a.b.addCommentData(bottomCommentModel);
                    }
                }
            }

            public void a(Throwable th, int i, String str) {
            }
        });
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStop() {
        super.onStop();
    }

    private void a() {
        Log.i("zhangchen", "url:" + k.c(this.A, f.getInstance().n()));
        c.b(this).a(k.c(this.A, f.getInstance().n()), new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ GLExporeActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                this.a.B.show();
                this.a.C.go();
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                Log.i("zhang", "t:" + str);
                dji.pilot2.nativeexplore.model.b bVar = new dji.pilot2.nativeexplore.model.b();
                JSONObject jSONObject = new JSONObject(str);
                bVar.b = jSONObject.getString("id");
                JSONObject jSONObject2 = jSONObject.getJSONObject("account");
                if (jSONObject2 != null) {
                    bVar.c = new dji.pilot2.nativeexplore.model.b.a();
                    bVar.c.a = jSONObject2.getString("id");
                    bVar.c.b = jSONObject2.getString("name");
                    bVar.c.c = jSONObject2.getString("avatar");
                    bVar.c.e = jSONObject2.getString(n.aS);
                }
                bVar.d = jSONObject.getString("title");
                bVar.g = jSONObject.getString("bg_image");
                bVar.f = jSONObject.getString("cover_image");
                bVar.e = jSONObject.getString("description");
                bVar.h = jSONObject.getString("country");
                bVar.i = jSONObject.getString(n.B);
                bVar.l = jSONObject.getInt("comments_count");
                bVar.j = jSONObject.getInt("nodes_count");
                bVar.o = jSONObject.getBoolean("is_follow");
                this.a.z = jSONObject.getBoolean("is_favorite");
                if (this.a.z && f.getInstance().c()) {
                    this.a.t.setImageDrawable(this.a.getResources().getDrawable(R.drawable.ic_topbar_mark_pre));
                } else {
                    this.a.t.setImageDrawable(this.a.getResources().getDrawable(R.drawable.ic_topbar_mark_nor));
                }
                this.a.y = jSONObject.getBoolean("is_like");
                if (this.a.y && f.getInstance().c()) {
                    this.a.v.setImageDrawable(this.a.getResources().getDrawable(R.drawable.ic_topbar_like_pre));
                } else {
                    try {
                        this.a.v.setImageDrawable(this.a.getResources().getDrawable(R.drawable.ic_topbar_like_nor));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        this.a.c.setVisibility(8);
                        this.a.C.show();
                        this.a.B.go();
                        return;
                    }
                }
                JSONArray jSONArray = jSONObject.getJSONArray("nodes");
                MiddleListModel middleListModel = new MiddleListModel();
                middleListModel.items = new ArrayList();
                if (jSONArray != null && jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                        MiddleItemModel middleItemModel = new MiddleItemModel();
                        middleItemModel.title = jSONObject3.getString("title");
                        middleItemModel.description = jSONObject3.getString("description");
                        middleItemModel.iso = jSONObject3.getInt("iso");
                        middleItemModel.shutter = jSONObject3.getString(dji.publics.b.a.b.l);
                        middleItemModel.aperture = jSONObject3.getDouble("aperture");
                        middleItemModel.image_large = jSONObject3.getString("large_image");
                        middleItemModel.image = jSONObject3.getString("image");
                        middleItemModel.shooting_latitude = jSONObject3.getDouble("shooting_latitude");
                        middleItemModel.shooting_longitude = jSONObject3.getDouble("shooting_longitude");
                        try {
                            middleItemModel.take_off_latitude = jSONObject3.getDouble("take_off_latitude");
                            middleItemModel.take_off_longitude = jSONObject3.getDouble("take_off_longitude");
                        } catch (Exception e2) {
                            DJILogHelper.getInstance().LOGD("zhang", "take off location error!");
                            middleItemModel.take_off_latitude = 0.0d;
                            middleItemModel.take_off_longitude = 0.0d;
                        }
                        middleListModel.items.add(middleItemModel);
                    }
                }
                bVar.q = middleListModel;
                Log.i("zhang", "id:" + bVar.b);
                Log.i("zhang", "size:" + middleListModel.items.size());
                this.a.c.setVisibility(0);
                this.a.b.addSrcData(bVar);
                this.a.b.updateAllViews();
                this.a.C.go();
                this.a.B.go();
            }

            public void a(Throwable th, int i, String str) {
                th.printStackTrace();
                this.a.c.setVisibility(8);
                this.a.C.show();
                this.a.B.go();
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gr:
                finish();
                return;
            case R.id.gs:
                dji.pilot2.mine.e.a.a aVar = new dji.pilot2.mine.e.a.a();
                dji.pilot2.nativeexplore.model.b srcData = this.b.getSrcData();
                if (srcData != null) {
                    aVar.d = srcData.e;
                    aVar.e = srcData.g;
                    aVar.b = k.d(srcData.b);
                    aVar.a = srcData.g;
                    aVar.c = srcData.d;
                    dji.pilot2.share.b.b bVar = new dji.pilot2.share.b.b(this);
                    bVar.a(dji.pilot2.share.e.a.a.CONTENT_IMG);
                    bVar.a(aVar);
                    bVar.a(aVar.b());
                    bVar.show();
                    return;
                }
                return;
            case R.id.gt:
                if (!f.getInstance().c()) {
                    startActivity(new Intent(this, DJIAccountSignActivity.class));
                    return;
                } else if (this.z) {
                    this.x.c("stories", this.A);
                    this.t.setImageDrawable(getResources().getDrawable(R.drawable.ic_topbar_mark_nor));
                    this.z = false;
                    return;
                } else {
                    this.x.b("stories", this.A);
                    this.t.setImageDrawable(getResources().getDrawable(R.drawable.ic_topbar_mark_pre));
                    this.z = true;
                    return;
                }
            case R.id.gu:
                if (!f.getInstance().c()) {
                    startActivity(new Intent(this, DJIAccountSignActivity.class));
                    return;
                } else if (this.y) {
                    this.v.setImageDrawable(getResources().getDrawable(R.drawable.ic_topbar_like_nor));
                    this.y = false;
                    this.x.d(k.c(this.A));
                    return;
                } else {
                    this.v.setImageDrawable(getResources().getDrawable(R.drawable.ic_topbar_like_pre));
                    this.y = true;
                    this.x.c(k.b(this.A));
                    return;
                }
            case R.id.d5b:
                a();
                return;
            default:
                return;
        }
    }
}
