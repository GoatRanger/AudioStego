package dji.pilot2.nativeexplore.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dji.frame.c.c;
import com.dji.frame.c.h;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.gs.e.b;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c.o;
import dji.pilot.fpv.d.e;
import dji.pilot.usercenter.b.f;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.explore.activity.DJIExploreCommentActivity;
import dji.pilot2.explore.activity.DJIExploreDetailActivity;
import dji.pilot2.explore.activity.DJIMediaDisplayActivity;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.explore.fragment.DJISupportShareWebviewFragment;
import dji.pilot2.mine.activity.ProfileTestActivity;
import dji.pilot2.mine.view.FixRatioImageView;
import dji.pilot2.nativeexplore.activity.DJI360WebViewActivity;
import dji.pilot2.nativeexplore.activity.ExploreLikesActivity;
import dji.pilot2.nativeexplore.activity.FullScreenLandscapeWebActivity;
import dji.pilot2.nativeexplore.activity.SearchTagActivity;
import dji.pilot2.nativeexplore.model.ClosedAdsModel;
import dji.pilot2.nativeexplore.model.ExploreItem;
import dji.pilot2.nativeexplore.model.ExploreItem.ExploreType;
import dji.pilot2.nativeexplore.model.ExploreLikeResultModel;
import dji.pilot2.nativeexplore.model.FollowEvent;
import dji.pilot2.nativeexplore.model.FollowResultModel;
import dji.pilot2.nativeexplore.model.LikeEvent;
import dji.pilot2.nativeexplore.model.TagModel;
import dji.pilot2.publics.b.a$e;
import dji.pilot2.publics.b.a$i;
import dji.pilot2.utils.k;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import java.util.HashMap;

public class d implements OnClickListener, o, a$i {
    public static boolean T = true;
    public ExploreItem U;
    private Context V;
    private DisplayImageOptions W;
    private View X;
    private TextView aA;
    private FixRatioImageView aB;
    private FixRatioImageView aC;
    private View aD;
    private TextView aE;
    private TextView aF;
    private TextView aG;
    private View aH;
    private View aI;
    private View aJ;
    private ImageView aK;
    private ImageView aL;
    private TextView aM;
    private LinearLayout aN;
    private View aO;
    private FixRatioImageView aP;
    private View aQ;
    private TextView aR;
    private boolean aS;
    private View at;
    private View au;
    private ImageView av;
    private TextView aw;
    private TextView ax;
    private ImageView ay;
    private TextView az;

    public static abstract class a extends dji.thirdparty.afinal.f.a<String> {
        private Object a;

        public abstract void a(String str, Object obj);

        public abstract void a(Throwable th, int i, String str);

        public a(Object obj) {
            this.a = obj;
        }

        public void a(boolean z) {
        }

        public void a(long j, long j2) {
        }

        public void a(String str) {
            a(str, this.a);
        }
    }

    public void a(boolean z) {
        this.aS = z;
    }

    public d(Context context) {
        this.W = null;
        this.V = context;
    }

    public d(Context context, DisplayImageOptions displayImageOptions) {
        this(context);
        this.W = displayImageOptions;
    }

    public void a(ExploreItem exploreItem) {
        this.U = exploreItem;
    }

    public void a(View view) {
        this.X = view;
        this.at = view.findViewById(R.id.cuo);
        this.au = view.findViewById(R.id.cv4);
        this.av = (ImageView) view.findViewById(R.id.cle);
        this.aw = (TextView) view.findViewById(R.id.clf);
        this.ax = (TextView) view.findViewById(R.id.cli);
        this.ay = (ImageView) view.findViewById(R.id.clh);
        this.az = (TextView) view.findViewById(R.id.clg);
        this.aA = (TextView) view.findViewById(R.id.cup);
        this.aB = (FixRatioImageView) view.findViewById(R.id.cuq);
        this.aC = (FixRatioImageView) view.findViewById(R.id.cur);
        this.aD = view.findViewById(R.id.cus);
        this.aE = (TextView) view.findViewById(R.id.cut);
        this.aF = (TextView) view.findViewById(R.id.cuu);
        this.aG = (TextView) view.findViewById(R.id.cuw);
        this.aH = view.findViewById(R.id.cv1);
        this.aI = view.findViewById(R.id.cuy);
        this.aJ = view.findViewById(R.id.cv0);
        this.aL = (ImageView) view.findViewById(R.id.cuv);
        this.aN = (LinearLayout) view.findViewById(R.id.cv3);
        this.aP = (FixRatioImageView) view.findViewById(R.id.cv5);
        this.aQ = view.findViewById(R.id.cv6);
        this.aR = (TextView) view.findViewById(R.id.cv7);
        this.aK = (ImageView) view.findViewById(R.id.cuz);
        this.aO = view.findViewById(R.id.cv2);
        this.aM = (TextView) view.findViewById(R.id.cux);
    }

    public void a() {
        if (this.U.type == ExploreType.photo || this.U.type == ExploreType.video) {
            this.aF.setVisibility(4);
            this.at.setVisibility(0);
            this.au.setVisibility(8);
            if (this.U.type == ExploreType.photo) {
                b();
            } else {
                c();
            }
        } else if (this.U.type == ExploreType.ads) {
            this.at.setVisibility(8);
            this.au.setVisibility(0);
            h();
        } else {
            this.at.setVisibility(8);
            this.au.setVisibility(8);
        }
    }

    private void b() {
        d();
        this.aB.setWeight(DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity, 3.0f);
        this.aC.setVisibility(4);
        c.a(this.V).a(this.aB, this.U.displayImageUrl);
        this.aE.setVisibility(4);
        this.aD.setVisibility(4);
    }

    private void c() {
        d();
        this.aB.setWeight(b.a, 9.0f);
        this.aC.setWeight(b.a, 9.0f);
        if (this.U.videoStatus == null || !this.U.videoStatus.equals("compressing")) {
            c.a(this.V).a(this.aB, this.U.displayImageUrl);
            this.aE.setVisibility(0);
            this.aE.setText(a(this.U.duration));
            this.aD.setVisibility(0);
            return;
        }
        for (int i = 0; i < dji.pilot2.mine.b.c.getInstance().b(); i++) {
            dji.pilot2.mine.e.b b = dji.pilot2.mine.b.c.getInstance().b(i);
            if (this.U.id.equals(b.o())) {
                String n = b.n();
                if (n != null) {
                    DJILogHelper.getInstance().LOGI("Lyric", "thumbnail path: " + n);
                    c.a(this.V).a(this.aB, n);
                    break;
                }
            }
        }
        this.aF.setVisibility(0);
        this.aE.setVisibility(4);
        this.aD.setVisibility(4);
    }

    private void d() {
        if (this.W == null) {
            this.av.setImageBitmap(null);
            c.a(this.V).a(this.av, this.U.userAvatarUrl);
        } else {
            this.av.setImageResource(R.drawable.v2_avatar_default);
            ImageLoader.getInstance().displayImage(this.U.userAvatarUrl, new ImageViewAware(this.av, false), this.W);
        }
        this.aw.setText(this.U.userName);
        this.ax.setText(dji.pilot2.utils.c.a(this.U.userCountry, this.V));
        this.ay.setVisibility(0);
        this.ay.setImageResource(R.drawable.v2_explore_location_img);
        this.aB.setImageBitmap(null);
        a(this.U.isLiked, this.U.likeCount);
        e();
        g();
        f();
        this.av.setOnClickListener(this);
        this.aw.setOnClickListener(this);
        this.aB.setOnClickListener(this);
        this.aH.setOnClickListener(this);
        this.aI.setOnClickListener(this);
        this.aJ.setOnClickListener(this);
    }

    private void e() {
        if (this.U.isFavorite) {
            this.aK.setImageResource(R.drawable.v2_explore_favorited_icon);
        } else {
            this.aK.setImageResource(R.drawable.v2_explore_favorite_icon);
        }
        this.aK.setOnClickListener(this);
    }

    private void a(boolean z, int i) {
        this.aG.setText(String.valueOf(i));
        if (i <= 0) {
            this.aG.setClickable(false);
        } else {
            this.aG.setClickable(true);
        }
        if (z) {
            this.aL.setImageResource(R.drawable.v2_explore_liked_icon);
        } else {
            this.aL.setImageResource(R.drawable.v2_explore_like_icon);
        }
        this.aG.setOnClickListener(this);
        this.aL.setOnClickListener(this);
    }

    private void f() {
        this.aN.removeAllViews();
        if (this.U.tags == null || this.U.tags.size() == 0) {
            this.aN.setVisibility(8);
            this.aO.setVisibility(4);
            this.aM.setVisibility(4);
            this.aM.setText(this.V.getString(R.string.v2_nativeexplore_tags_count, new Object[]{Integer.valueOf(0)}));
        } else {
            this.aN.setVisibility(0);
            this.aO.setVisibility(0);
            this.aM.setVisibility(0);
            this.aM.setText(this.V.getString(R.string.v2_nativeexplore_tags_count, new Object[]{Integer.valueOf(this.U.tags.size())}));
        }
        int dimensionPixelSize = this.V.getResources().getDimensionPixelSize(R.dimen.gw);
        if (this.U.tags != null) {
            for (int i = 0; i < this.U.tags.size(); i++) {
                if (this.U.tags.get(i) != null) {
                    TextView textView = (TextView) LayoutInflater.from(this.V).inflate(R.layout.v2_explore_tag_layout, null);
                    textView.setOnClickListener(this);
                    textView.setText(((TagModel) this.U.tags.get(i)).name);
                    LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.setMargins(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
                    this.aN.addView(textView, layoutParams);
                }
            }
        }
    }

    private void g() {
        if (this.aS) {
            this.aA.setVisibility(0);
            this.az.setVisibility(4);
            this.aA.setText(dji.pilot2.nativeexplore.d.a.a(this.V, this.U.created_at));
            return;
        }
        this.aA.setVisibility(4);
        if (!T) {
            return;
        }
        if (f.getInstance().c() && this.U.userId.equals(f.getInstance().i())) {
            this.az.setVisibility(4);
        } else if (this.U.isFollowed) {
            this.az.setVisibility(0);
            this.az.setOnClickListener(this);
            this.az.setText(R.string.v2_explore_followed);
            this.az.setEnabled(false);
            this.az.setSelected(true);
        } else {
            this.az.setVisibility(0);
            this.az.setOnClickListener(this);
            this.az.setSelected(false);
            this.az.setText(R.string.v2_explore_follow);
            this.az.setEnabled(true);
        }
    }

    private void h() {
        if (this.U.isAdsVisible) {
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
                this.aP.setWeight(5.0f, 1.0f);
            }
            this.au.setVisibility(0);
            this.aP.setImageBitmap(null);
            c.a(this.V).a(this.aP, this.U.adsImageUrl);
            this.aP.setOnClickListener(this);
            this.aQ.setOnClickListener(this);
            return;
        }
        this.au.setVisibility(8);
    }

    private String a(int i) {
        int i2 = i % 60;
        return String.format("%d:%02d", new Object[]{Integer.valueOf(i / 60), Integer.valueOf(i2)});
    }

    private void i() {
        String str;
        boolean z;
        e.b(o.g);
        if (this.U.isFollowed) {
            str = "https://www.skypixel.com/api/users/" + this.U.userId + a$i.Y;
        } else {
            str = "https://www.skypixel.com/api/users/" + this.U.userId + a$i.S;
        }
        dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
        bVar.a("token", f.getInstance().n());
        DJILogHelper.getInstance().LOGI("Lyric", "url: " + str);
        ExploreItem exploreItem = this.U;
        if (this.U.isFollowed) {
            z = false;
        } else {
            z = true;
        }
        exploreItem.isFollowed = z;
        a();
        this.az.setEnabled(false);
        c.b(this.V).c(str, bVar, new a(this, this.U) {
            final /* synthetic */ d a;

            public void a(String str, Object obj) {
                if (obj instanceof ExploreItem) {
                    ExploreItem exploreItem = (ExploreItem) obj;
                    FollowResultModel followResultModel = (FollowResultModel) h.b(str, FollowResultModel.class);
                    if (followResultModel == null || followResultModel.status != 0) {
                        this.a.k();
                        this.a.az.setEnabled(true);
                        return;
                    }
                    FollowEvent.b bVar;
                    dji.pilot2.nativeexplore.model.FollowEvent.a aVar = new dji.pilot2.nativeexplore.model.FollowEvent.a(f.getInstance().i(), f.getInstance().m(), a$e.a + f.getInstance().e(), f.getInstance().h().u);
                    dji.pilot2.nativeexplore.model.FollowEvent.a aVar2 = new dji.pilot2.nativeexplore.model.FollowEvent.a(exploreItem.userId, exploreItem.userName, exploreItem.userAvatarUrl, exploreItem.userCountry);
                    if (this.a.U.isFollowed) {
                        bVar = FollowEvent.b.FOLLOW;
                    } else {
                        bVar = FollowEvent.b.UNFOLLOW;
                    }
                    dji.thirdparty.a.c.a().e(new FollowEvent(bVar, aVar, aVar2));
                    this.a.az.setEnabled(true);
                }
            }

            public void a(Throwable th, int i, String str) {
                DJILogHelper.getInstance().LOGI("Lyric", "follow failed: " + str);
                this.a.U.isFollowed = !this.a.U.isFollowed;
                this.a.a();
                this.a.k();
                this.a.az.setEnabled(true);
            }
        });
    }

    public void onClick(View view) {
        Intent intent;
        String str;
        Intent intent2;
        switch (view.getId()) {
            case R.id.cle:
            case R.id.clf:
                e.b(o.f);
                intent = new Intent(this.V, ProfileTestActivity.class);
                intent.putExtra("user_id", this.U.userId);
                intent.putExtra("user_name", this.U.userName);
                intent.putExtra(ProfileTestActivity.I, this.U.userAvatarUrl);
                intent.putExtra("country", this.U.userCountry);
                this.V.startActivity(intent);
                return;
            case R.id.clg:
                if (f.getInstance().c()) {
                    i();
                    return;
                } else {
                    j();
                    return;
                }
            case R.id.cuq:
                if (this.U.type == ExploreType.photo) {
                    if (!this.U.is_360 || !this.U.image_status.equals("available")) {
                        intent = new Intent(this.V, DJIMediaDisplayActivity.class);
                        intent.putExtra("preview_photo", "photo");
                        intent.putExtra("preview_thumburl", this.U.displayImageUrl);
                        intent.putExtra("preview_originurl", this.U.orignalImageUrl);
                        intent.putExtra("preview_title", this.U.title);
                        intent.putExtra(ExploreLikesActivity.a, this.U.id);
                        this.V.startActivity(intent);
                        ((Activity) this.V).overridePendingTransition(17432576, 17432577);
                        e.b(o.i);
                        return;
                    } else if (this.U.page_link != null && !this.U.page_link.equals("")) {
                        str = this.U.page_link;
                        intent2 = new Intent(this.V, DJI360WebViewActivity.class);
                        intent2.putExtra(DJIWebviewFragment.r, true);
                        intent2.putExtra(DJIWebviewFragment.o, str);
                        this.V.startActivity(intent2);
                        return;
                    } else {
                        return;
                    }
                } else if (this.U.type == ExploreType.video) {
                    intent = new Intent(this.V, FullScreenLandscapeWebActivity.class);
                    intent.putExtra(DJIWebviewFragment.o, this.U.embedUrl);
                    DJILogHelper.getInstance().LOGD("Lyric", "embedUrl: " + this.U.embedUrl);
                    this.V.startActivity(intent);
                    e.b(o.j);
                    return;
                } else {
                    return;
                }
            case R.id.cuv:
                if (f.getInstance().c()) {
                    this.aL.setEnabled(false);
                    dji.thirdparty.afinal.f.b bVar;
                    if (this.U.isLiked) {
                        e.b(o.ei_);
                        a(false, this.U.likeCount - 1);
                        str = "";
                        if (this.U.type == ExploreType.photo) {
                            str = "photos";
                        } else if (this.U.type == ExploreType.video) {
                            str = "videos";
                        }
                        str = "https://www.skypixel.com/api/" + str + dji.pilot.usercenter.protocol.d.t + this.U.id + a$i.co_ + a$i.cq_;
                        bVar = new dji.thirdparty.afinal.f.b();
                        bVar.a("token", f.getInstance().n());
                        c.b(this.V).d(str, bVar, new a(this, this.U) {
                            final /* synthetic */ d a;

                            public void a(String str, Object obj) {
                                if (obj instanceof ExploreItem) {
                                    ExploreLikeResultModel exploreLikeResultModel = (ExploreLikeResultModel) h.b(str, ExploreLikeResultModel.class);
                                    if (exploreLikeResultModel != null && exploreLikeResultModel.status == 0) {
                                        ((ExploreItem) obj).isLiked = exploreLikeResultModel.you_like_now;
                                        ((ExploreItem) obj).likeCount--;
                                        if (((ExploreItem) obj) == this.a.U) {
                                            this.a.aG.setText("" + ((ExploreItem) obj).likeCount);
                                            this.a.aL.setImageResource(R.drawable.v2_explore_like_icon);
                                            if (this.a.U.likeCount <= 0) {
                                                this.a.aG.setClickable(false);
                                            } else {
                                                this.a.aG.setClickable(true);
                                            }
                                        }
                                        dji.thirdparty.a.c.a().e(new LikeEvent(exploreLikeResultModel.you_like_now ? dji.pilot2.nativeexplore.model.LikeEvent.a.LIKE : dji.pilot2.nativeexplore.model.LikeEvent.a.DISLIKE, ((ExploreItem) obj).id, ((ExploreItem) obj).likeCount));
                                    }
                                }
                                this.a.aL.setEnabled(true);
                            }

                            public void a(Throwable th, int i, String str) {
                                DJILogHelper.getInstance().LOGI("Lyric", "disfavourite failed: " + str);
                                this.a.k();
                                this.a.aL.setEnabled(true);
                            }
                        });
                        return;
                    }
                    e.b(o.A);
                    a(true, this.U.likeCount + 1);
                    str = "";
                    if (this.U.type == ExploreType.photo) {
                        str = "photos";
                    } else if (this.U.type == ExploreType.video) {
                        str = "videos";
                    }
                    str = "https://www.skypixel.com/api/" + str + dji.pilot.usercenter.protocol.d.t + this.U.id + a$i.co_ + a$i.cp_;
                    bVar = new dji.thirdparty.afinal.f.b();
                    bVar.a("token", f.getInstance().n());
                    DJILogHelper.getInstance().LOGI("Lyric", "url: " + str);
                    c.b(this.V).b(str, bVar, new a(this, this.U) {
                        final /* synthetic */ d a;

                        public void a(String str, Object obj) {
                            if (obj instanceof ExploreItem) {
                                ExploreLikeResultModel exploreLikeResultModel = (ExploreLikeResultModel) h.b(str, ExploreLikeResultModel.class);
                                if (exploreLikeResultModel != null && exploreLikeResultModel.status == 0) {
                                    ((ExploreItem) obj).isLiked = exploreLikeResultModel.you_like_now;
                                    ((ExploreItem) obj).likeCount++;
                                    if (((ExploreItem) obj) == this.a.U) {
                                        this.a.aG.setText("" + this.a.U.likeCount);
                                        this.a.aL.setImageResource(R.drawable.v2_explore_liked_icon);
                                        if (this.a.U.likeCount <= 0) {
                                            this.a.aG.setClickable(false);
                                        } else {
                                            this.a.aG.setClickable(true);
                                        }
                                    }
                                    dji.thirdparty.a.c.a().e(new LikeEvent(exploreLikeResultModel.you_like_now ? dji.pilot2.nativeexplore.model.LikeEvent.a.LIKE : dji.pilot2.nativeexplore.model.LikeEvent.a.DISLIKE, ((ExploreItem) obj).id, ((ExploreItem) obj).likeCount));
                                }
                            }
                            this.a.aL.setEnabled(true);
                        }

                        public void a(Throwable th, int i, String str) {
                            DJILogHelper.getInstance().LOGI("Lyric", "favourite failed: " + str);
                            this.a.k();
                            this.a.aL.setEnabled(true);
                        }
                    });
                    return;
                }
                j();
                return;
            case R.id.cuw:
                if (this.U.likeCount > 0) {
                    intent = new Intent(this.V, ExploreLikesActivity.class);
                    intent.putExtra(ExploreLikesActivity.a, this.U.id);
                    if (this.U.type == ExploreType.video) {
                        intent.putExtra(ExploreLikesActivity.b, 1);
                    } else if (this.U.type == ExploreType.photo) {
                        intent.putExtra(ExploreLikesActivity.b, 2);
                    }
                    intent.putExtra("title", this.V.getString(R.string.v2_explore_likes_count, new Object[]{Integer.valueOf(this.U.likeCount)}));
                    this.V.startActivity(intent);
                    return;
                }
                return;
            case R.id.cuy:
                e.b(o.dX_);
                intent = new Intent(this.V, DJIExploreDetailActivity.class);
                if (this.U.type == ExploreType.video) {
                    intent.putExtra("detail_video", true);
                } else {
                    intent.putExtra("detail_video", false);
                }
                intent.putExtra("country", this.U.userCountry);
                intent.putExtra(ExploreLikesActivity.a, this.U.id);
                this.V.startActivity(intent);
                ((Activity) this.V).overridePendingTransition(17432576, 17432577);
                return;
            case R.id.cuz:
                if (!f.getInstance().c()) {
                    this.V.startActivity(new Intent(this.V, DJIAccountSignActivity.class));
                    return;
                } else if (this.U.type == ExploreType.video) {
                    if (this.U.isFavorite) {
                        dji.pilot2.nativeexplore.b.b.getInstance(this.V).c("videos", this.U.id);
                        this.U.isFavorite = false;
                        this.aK.setImageResource(R.drawable.v2_explore_favorite_icon);
                        return;
                    }
                    dji.pilot2.nativeexplore.b.b.getInstance(this.V).b("videos", this.U.id);
                    this.U.isFavorite = true;
                    this.aK.setImageResource(R.drawable.v2_explore_favorited_icon);
                    return;
                } else if (this.U.type == ExploreType.photo) {
                    Log.i(DJISupportShareWebviewFragment.T, "photo:" + k.a("photos", this.U.id));
                    if (this.U.isFavorite) {
                        dji.pilot2.nativeexplore.b.b.getInstance(this.V).c("photos", this.U.id);
                        this.U.isFavorite = false;
                        this.aK.setImageResource(R.drawable.v2_explore_favorite_icon);
                        return;
                    }
                    dji.pilot2.nativeexplore.b.b.getInstance(this.V).b("photos", this.U.id);
                    this.U.isFavorite = true;
                    this.aK.setImageResource(R.drawable.v2_explore_favorited_icon);
                    return;
                } else {
                    return;
                }
            case R.id.cv0:
                e.b(o.y);
                intent = new Intent();
                if (this.U.type == ExploreType.photo) {
                    intent.putExtra(DJIExploreCommentActivity.U, "photos");
                } else if (this.U.type == ExploreType.video) {
                    intent.putExtra(DJIExploreCommentActivity.U, "videos");
                }
                intent.putExtra(DJIExploreCommentActivity.V, this.U.id);
                intent.setClass(this.V, DJIExploreCommentActivity.class);
                this.V.startActivity(intent);
                return;
            case R.id.cv1:
                e.b(o.dY_);
                dji.pilot2.share.b.b bVar2 = new dji.pilot2.share.b.b(this.V);
                dji.pilot2.mine.e.a.a aVar = new dji.pilot2.mine.e.a.a();
                aVar.d = this.U.description;
                aVar.c = this.U.title;
                if (f.getInstance().c()) {
                    aVar.b = this.U.shareUrl + "?account_id=" + f.getInstance().i();
                } else {
                    aVar.b = this.U.shareUrl;
                }
                aVar.a = this.U.displayImageUrl;
                dji.pilot2.share.e.a.a aVar2 = dji.pilot2.share.e.a.a.CONTENT_LINK_ADDR;
                if (this.U.type == ExploreType.photo) {
                    aVar2 = dji.pilot2.share.e.a.a.CONTENT_IMG;
                    e.b(o.dZ_);
                } else if (this.U.type == ExploreType.video) {
                    aVar2 = dji.pilot2.share.e.a.a.CONTENT_VIDEO;
                    e.b(o.cE_);
                }
                if (this.U.userId.equals(f.getInstance().i())) {
                    bVar2.a(dji.pilot2.share.b.b.a.EDIT_UPLOAD);
                } else {
                    bVar2.a(dji.pilot2.share.b.b.a.EXPLORE_MINE);
                }
                bVar2.a(aVar2);
                bVar2.a(aVar);
                bVar2.a(this.U.shareUrl);
                bVar2.show();
                return;
            case R.id.cv5:
                HashMap hashMap = new HashMap();
                hashMap.put(dji.pilot.fpv.d.d.dH, this.U.adsName);
                e.a(o.C, hashMap);
                str = this.U.adsRedirectUrl;
                if (str != null && !str.equals("")) {
                    intent2 = new Intent(this.V, DJISupportShareWebviewActivity.class);
                    intent2.putExtra(DJIWebviewFragment.o, str);
                    this.V.startActivity(intent2);
                    return;
                }
                return;
            case R.id.cv6:
                this.U.isAdsVisible = false;
                this.au.setVisibility(8);
                c.c(this.V).a(new ClosedAdsModel(this.U.adsName, this.U.adsRedirectUrl));
                return;
            default:
                if (view instanceof TextView) {
                    e.b(o.dW_);
                    str = ((TextView) view).getText().toString();
                    if (str != null) {
                        intent2 = new Intent(this.V, SearchTagActivity.class);
                        intent2.putExtra(SearchTagActivity.a, str);
                        this.V.startActivity(intent2);
                        return;
                    }
                    return;
                }
                return;
        }
    }

    private void j() {
        Intent intent = new Intent(this.V, DJIAccountSignActivity.class);
        intent.putExtra(DJIAccountSignActivity.a, 1005);
        ((Activity) this.V).startActivity(intent);
    }

    private void k() {
        new dji.pilot2.nativeexplore.c.d(this.V).show();
    }
}
