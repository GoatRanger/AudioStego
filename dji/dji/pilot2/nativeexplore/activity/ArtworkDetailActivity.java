package dji.pilot2.nativeexplore.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.autonavi.amap.mapcore.MapCore;
import com.dji.frame.c.h;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.usercenter.b.f;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.nativeexplore.a.d;
import dji.pilot2.nativeexplore.model.DeleteArtworkEvent;
import dji.pilot2.nativeexplore.model.DeleteArtworkResultModel;
import dji.pilot2.nativeexplore.model.ExploreEvent;
import dji.pilot2.nativeexplore.model.ExploreItem;
import dji.pilot2.nativeexplore.model.ExploreItem.ExploreType;
import dji.pilot2.nativeexplore.model.ExploreListModel.ExploreItemModel;
import dji.pilot2.nativeexplore.model.FollowEvent;
import dji.pilot2.nativeexplore.model.FollowEvent.b;
import dji.pilot2.nativeexplore.model.LikeEvent;
import dji.pilot2.publics.b.a$i;
import dji.pilot2.share.b.a;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.io.File;

public class ArtworkDetailActivity extends DJIActivityNoFullScreen implements a$i {
    public static final String a = "id";
    public static final String b = "type";
    public static final String c = "duration";
    public static final String d = "explore_item_model_string";
    public static final String t = "explore_item_string";
    protected String A;
    protected int B;
    protected String C;
    protected String D;
    a E;
    DisplayImageOptions F = new Builder().showImageOnLoading(R.drawable.v2_avatar_default).cacheInMemory(true).cacheOnDisc(false).build();
    private boolean G;
    private View H;
    private ExploreItem I;
    private d J;
    private dji.pilot2.nativeexplore.c.a K;
    protected DJIStateImageView u;
    protected DJITextView v;
    protected View w;
    protected View x;
    protected dji.pilot2.nativeexplore.b.a y;
    protected String z;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_artworkdetail);
        c.a().a(this);
        this.G = false;
        Intent intent = getIntent();
        this.z = intent.getStringExtra("id");
        this.A = intent.getStringExtra("type");
        this.B = intent.getIntExtra("duration", 0);
        this.C = intent.getStringExtra(d);
        this.D = intent.getStringExtra(t);
        a();
        b();
    }

    protected void a() {
        this.u = (DJIStateImageView) findViewById(R.id.c6z);
        this.v = (DJITextView) findViewById(R.id.c70);
        this.w = findViewById(R.id.c74);
        this.w.setVisibility(4);
        this.x = findViewById(R.id.c72);
        this.H = findViewById(R.id.c71);
        this.E = new a(this);
    }

    protected void b() {
        if (this.C != null) {
            ExploreItemModel exploreItemModel = (ExploreItemModel) h.b(this.C, ExploreItemModel.class);
            if (exploreItemModel != null) {
                this.I = new ExploreItem(exploreItemModel);
            }
        }
        if (this.D != null) {
            this.I = (ExploreItem) h.b(this.D, ExploreItem.class);
        }
        if (this.I != null) {
            this.w.setVisibility(0);
            this.J = new d(this, this.F);
            this.J.a(this.w);
            this.w.setTag(this.J);
            this.J = (d) this.w.getTag();
            this.J.a(this.I);
            this.J.a();
            this.v.setText(this.I.userName);
            this.x.setVisibility(8);
            if (f.getInstance().c() && f.getInstance().i().equals(this.I.userId)) {
                this.H.setVisibility(0);
                return;
            }
            return;
        }
        this.y = new dji.pilot2.nativeexplore.b.a(this);
        this.y.a(new dji.pilot2.nativeexplore.b.a.a(this) {
            final /* synthetic */ ArtworkDetailActivity a;

            {
                this.a = r1;
            }

            public void a(ExploreItem exploreItem) {
                this.a.I = exploreItem;
                DJILogHelper.getInstance().LOGI("Lyric", "is followed: " + this.a.I.isFollowed);
                this.a.w.setVisibility(0);
                this.a.J = new d(this.a, this.a.F);
                this.a.J.a(this.a.w);
                this.a.w.setTag(this.a.J);
                this.a.J = (d) this.a.w.getTag();
                this.a.J.a(exploreItem);
                this.a.J.a();
                this.a.v.setText(exploreItem.userName);
                this.a.x.setVisibility(8);
                if (f.getInstance().c() && f.getInstance().i().equals(this.a.I.userId)) {
                    this.a.H.setVisibility(0);
                }
            }

            public void a() {
                this.a.finish();
            }
        });
        this.u.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ArtworkDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        if (this.z == null || !(this.A.equals("videos") || this.A.equals("photos"))) {
            finish();
        } else {
            this.y.a(this.z, this.A, this.B);
        }
    }

    public void onEventMainThread(FollowEvent followEvent) {
        FollowEvent.a aVar = followEvent.subject;
        FollowEvent.a aVar2 = followEvent.object;
        boolean z = followEvent.action == b.FOLLOW;
        if (this.I != null && aVar2.a.equals(this.I.userId)) {
            this.I.isFollowed = z;
            if (this.J != null) {
                this.J.a();
            }
        }
    }

    public void onEventMainThread(LikeEvent likeEvent) {
        String str = likeEvent.id;
        boolean z = likeEvent.action == LikeEvent.a.LIKE;
        if (this.I != null && this.I.id.equals(str) && this.I.isLiked != z) {
            this.I.isLiked = z;
            this.I.likeCount = likeEvent.likeCount;
            if (this.J != null) {
                this.J.a();
            }
        }
    }

    public void onEventMainThread(ExploreEvent exploreEvent) {
        if (exploreEvent == ExploreEvent.USER_LOGIN && this.I.userId.equals(f.getInstance().i())) {
            this.H.setVisibility(0);
        }
    }

    public void onEventMainThread(DeleteArtworkEvent deleteArtworkEvent) {
        if (this.I.id.equals(deleteArtworkEvent.id)) {
            finish();
        }
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.c6z:
                finish();
                return;
            case R.id.c71:
                this.K = new dji.pilot2.nativeexplore.c.a(this);
                this.K.a(getResources().getString(R.string.v2_artwork_delete_confirm));
                this.K.b(getResources().getString(R.string.v2_artwork_delete_cancel));
                this.K.a(new OnClickListener(this) {
                    final /* synthetic */ ArtworkDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        String str = "";
                        if (this.a.I != null) {
                            this.a.K.dismiss();
                            this.a.E.show();
                            if (this.a.I.type == ExploreType.photo) {
                                str = "https://www.skypixel.com/api/photos/" + this.a.I.id;
                            } else if (this.a.I.type == ExploreType.video) {
                                str = "https://www.skypixel.com/api/videos/" + this.a.I.id;
                            }
                            dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
                            bVar.a("token", f.getInstance().n());
                            com.dji.frame.c.c.b(this.a).d(str, bVar, new dji.thirdparty.afinal.f.a<String>(this) {
                                final /* synthetic */ AnonymousClass3 a;

                                {
                                    this.a = r1;
                                }

                                public void a(boolean z) {
                                }

                                public void a(long j, long j2) {
                                }

                                public void a(String str) {
                                    if (str != null) {
                                        DeleteArtworkResultModel deleteArtworkResultModel = (DeleteArtworkResultModel) h.b(str, DeleteArtworkResultModel.class);
                                        if (deleteArtworkResultModel == null || !(deleteArtworkResultModel.status == 0 || deleteArtworkResultModel.status == MapCore.MAPRENDER_CAN_STOP_AND_FULLSCREEN_RENDEROVER)) {
                                            this.a.a.E.hide();
                                            if (this.a.a.G) {
                                                new dji.pilot2.nativeexplore.c.d(this.a.a).show();
                                                return;
                                            }
                                            return;
                                        }
                                        c.a().e(new DeleteArtworkEvent(this.a.a.I.id));
                                        for (int i = 0; i < dji.pilot2.mine.b.c.getInstance().b(); i++) {
                                            dji.pilot2.mine.e.b b = dji.pilot2.mine.b.c.getInstance().b(i);
                                            if (this.a.a.I.id.equals(b.o())) {
                                                dji.pilot2.mine.b.c.getInstance().c(b);
                                                if (b.n() != null) {
                                                    new File(b.n()).delete();
                                                }
                                                if (b.e() != null) {
                                                    new File(b.e() + ".info").delete();
                                                    return;
                                                }
                                                return;
                                            }
                                        }
                                    }
                                }

                                public void a(Throwable th, int i, String str) {
                                    this.a.a.E.hide();
                                    if (this.a.a.G) {
                                        new dji.pilot2.nativeexplore.c.d(this.a.a).show();
                                    }
                                }
                            });
                        }
                    }
                });
                this.K.b(new OnClickListener(this) {
                    final /* synthetic */ ArtworkDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.K.dismiss();
                    }
                });
                this.K.show();
                return;
            default:
                return;
        }
    }

    protected void onResume() {
        super.onResume();
        this.G = true;
    }

    protected void onPause() {
        super.onPause();
        this.G = false;
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
        c.a().d(this);
    }
}
