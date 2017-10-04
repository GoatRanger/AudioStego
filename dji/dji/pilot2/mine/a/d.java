package dji.pilot2.mine.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.dji.frame.c.h;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c$m;
import dji.pilot.fpv.d.e;
import dji.pilot.usercenter.b.f;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.mine.activity.ProfileTestActivity;
import dji.pilot2.nativeexplore.model.FollowEvent;
import dji.pilot2.nativeexplore.model.FollowListModel.AccountModel;
import dji.pilot2.nativeexplore.model.FollowResultModel;
import dji.pilot2.publics.b.a$e;
import dji.pilot2.publics.b.a$i;
import dji.pilot2.utils.c;
import java.util.ArrayList;
import java.util.List;

public class d extends BaseExpandableListAdapter implements c$m, a$i {
    private static a K;
    private Context G;
    private DisplayImageOptions H;
    private List<AccountModel> I = new ArrayList();
    private dji.pilot2.mine.a.b.b J;
    private dji.pilot2.mine.activity.ProfileTestActivity.a L;

    public enum a {
        FOLLOWER,
        FOLLOWING
    }

    public static class b implements OnClickListener {
        Context a;
        DisplayImageOptions b;
        public AccountModel c;
        public ImageView d;
        public TextView e;
        public TextView f;
        public TextView g;
        public ImageView h;
        public View i;

        public b(Context context, DisplayImageOptions displayImageOptions) {
            this.a = context;
            this.b = displayImageOptions;
        }

        public void a(AccountModel accountModel) {
            this.c = accountModel;
        }

        public void a(View view) {
            this.d = (ImageView) view.findViewById(R.id.cle);
            this.i = view.findViewById(R.id.c74);
            this.e = (TextView) view.findViewById(R.id.clf);
            this.f = (TextView) view.findViewById(R.id.cli);
            this.g = (TextView) view.findViewById(R.id.clg);
            this.h = (ImageView) view.findViewById(R.id.clh);
        }

        public void a() {
            this.d.setImageResource(R.drawable.v2_avatar_default);
            ImageLoader.getInstance().displayImage(this.c.avatar, new ImageViewAware(this.d, false), this.b);
            this.e.setText(this.c.name);
            this.f.setText(c.a(this.c.country, this.a));
            int a = c.a(this.a, this.c.country);
            if (a == 0) {
                this.h.setVisibility(8);
            } else {
                this.h.setVisibility(0);
                this.h.setImageResource(a);
            }
            if (this.c.id.equals(f.getInstance().i())) {
                this.g.setVisibility(4);
            } else {
                this.g.setVisibility(0);
            }
            if (this.c.is_follow) {
                this.g.setText(R.string.v2_explore_followed);
                this.g.setSelected(true);
            } else {
                this.g.setText(R.string.v2_explore_follow);
                this.g.setSelected(false);
            }
            this.g.setOnClickListener(this);
            this.i.setOnClickListener(this);
        }

        private void b() {
            Intent intent = new Intent(this.a, DJIAccountSignActivity.class);
            intent.putExtra(DJIAccountSignActivity.a, 1005);
            ((Activity) this.a).startActivity(intent);
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.c74:
                case R.id.clf:
                    if (d.K == a.FOLLOWER) {
                        e.b(c$m.eC_);
                    } else {
                        e.b(c$m.dp_);
                    }
                    Intent intent = new Intent(this.a, ProfileTestActivity.class);
                    intent.putExtra("user_id", this.c.id);
                    intent.putExtra("user_name", this.c.name);
                    intent.putExtra(ProfileTestActivity.I, this.c.avatar);
                    intent.putExtra("country", this.c.country);
                    this.a.startActivity(intent);
                    return;
                case R.id.clg:
                    if (f.getInstance().c()) {
                        this.g.setClickable(false);
                        String str;
                        dji.thirdparty.afinal.f.b bVar;
                        if (this.c.is_follow) {
                            if (d.K == a.FOLLOWING) {
                                e.b(c$m.dq_);
                            }
                            str = "https://www.skypixel.com/api/users/" + this.c.id + a$i.Y;
                            bVar = new dji.thirdparty.afinal.f.b();
                            bVar.a("token", f.getInstance().n());
                            DJILogHelper.getInstance().LOGI("Lyric", "url: " + str);
                            com.dji.frame.c.c.b(this.a).c(str, bVar, new dji.pilot2.nativeexplore.a.d.a(this, this.c) {
                                final /* synthetic */ b a;

                                public void a(String str, Object obj) {
                                    DJILogHelper.getInstance().LOGI("Lyric", "result: " + str);
                                    if (obj instanceof AccountModel) {
                                        AccountModel accountModel = (AccountModel) obj;
                                        FollowResultModel followResultModel = (FollowResultModel) h.b(str, FollowResultModel.class);
                                        if (followResultModel != null && followResultModel.status == 0) {
                                            this.a.c.is_follow = false;
                                            if (accountModel == this.a.c) {
                                                this.a.g.setSelected(false);
                                            }
                                            dji.thirdparty.a.c.a().e(new FollowEvent(dji.pilot2.nativeexplore.model.FollowEvent.b.UNFOLLOW, new dji.pilot2.nativeexplore.model.FollowEvent.a(f.getInstance().i(), f.getInstance().m(), f.getInstance().e(), f.getInstance().h().u), new dji.pilot2.nativeexplore.model.FollowEvent.a(accountModel.id, accountModel.name, accountModel.avatar, accountModel.country)));
                                        }
                                    }
                                    this.a.g.setOnClickListener(this.a);
                                }

                                public void a(Throwable th, int i, String str) {
                                    this.a.g.setOnClickListener(this.a);
                                }
                            });
                            return;
                        }
                        if (d.K == a.FOLLOWER) {
                            e.b(c$m.ds_);
                        }
                        str = "https://www.skypixel.com/api/users/" + this.c.id + a$i.S;
                        bVar = new dji.thirdparty.afinal.f.b();
                        bVar.a("token", f.getInstance().n());
                        DJILogHelper.getInstance().LOGI("Lyric", "url: " + str);
                        com.dji.frame.c.c.b(this.a).c(str, bVar, new dji.pilot2.nativeexplore.a.d.a(this, this.c) {
                            final /* synthetic */ b a;

                            public void a(String str, Object obj) {
                                DJILogHelper.getInstance().LOGI("Lyric", "result: " + str);
                                if (obj instanceof AccountModel) {
                                    AccountModel accountModel = (AccountModel) obj;
                                    FollowResultModel followResultModel = (FollowResultModel) h.b(str, FollowResultModel.class);
                                    if (followResultModel != null && followResultModel.status == 0) {
                                        this.a.c.is_follow = true;
                                        if (accountModel == this.a.c) {
                                            this.a.g.setSelected(true);
                                        }
                                        dji.thirdparty.a.c.a().e(new FollowEvent(dji.pilot2.nativeexplore.model.FollowEvent.b.FOLLOW, new dji.pilot2.nativeexplore.model.FollowEvent.a(f.getInstance().i(), f.getInstance().m(), a$e.a + f.getInstance().e(), f.getInstance().h().u), new dji.pilot2.nativeexplore.model.FollowEvent.a(accountModel.id, accountModel.name, accountModel.avatar, accountModel.country)));
                                    }
                                }
                                this.a.g.setOnClickListener(this.a);
                            }

                            public void a(Throwable th, int i, String str) {
                                this.a.g.setOnClickListener(this.a);
                            }
                        });
                        return;
                    }
                    b();
                    return;
                default:
                    return;
            }
        }
    }

    public void a(dji.pilot2.mine.activity.ProfileTestActivity.a aVar) {
        this.L = aVar;
    }

    public d(Context context, a aVar) {
        this.G = context;
        K = aVar;
        this.H = new Builder().showImageOnLoading(R.drawable.v2_avatar_default).cacheInMemory(true).cacheOnDisc(false).build();
    }

    public void a(dji.pilot2.mine.a.b.b bVar) {
        this.J = bVar;
    }

    public synchronized void a(AccountModel accountModel) {
        this.I.remove(accountModel);
    }

    public int getGroupCount() {
        return 1;
    }

    public int getChildrenCount(int i) {
        if (this.I != null) {
            return this.I.size();
        }
        return 0;
    }

    public Object getGroup(int i) {
        return null;
    }

    public Object getChild(int i, int i2) {
        if (this.I != null) {
            return this.I.get(i2);
        }
        return null;
    }

    public long getGroupId(int i) {
        return 0;
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public boolean hasStableIds() {
        return false;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if ((this.I == null || this.I.isEmpty()) && this.L != null) {
            return this.L.a();
        }
        View view2 = new View(this.G);
        view2.setVisibility(8);
        return view2;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.G).inflate(R.layout.v2_explore_like_list_item, null);
            view.setTag(new b(this.G, this.H));
        }
        b bVar = (b) view.getTag();
        bVar.a(view);
        bVar.a((AccountModel) this.I.get(i2));
        bVar.a();
        return view;
    }

    public boolean isChildSelectable(int i, int i2) {
        return false;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (this.J != null) {
            this.J.a();
        }
    }

    public synchronized void a(List<AccountModel> list) {
        for (int i = 0; i < list.size(); i++) {
            Object obj;
            AccountModel accountModel = (AccountModel) list.get(i);
            int i2 = 0;
            while (i2 < this.I.size()) {
                if (accountModel.id != null && this.I.get(i2) != null && accountModel.id.equals(((AccountModel) this.I.get(i2)).id)) {
                    obj = 1;
                    break;
                }
                i2++;
            }
            obj = null;
            if (obj == null) {
                this.I.add(accountModel);
            }
        }
    }
}
