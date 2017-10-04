package dji.pilot2.nativeexplore.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.dji.frame.c.h;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.usercenter.b.f;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.mine.activity.ProfileTestActivity;
import dji.pilot2.nativeexplore.c.d;
import dji.pilot2.nativeexplore.model.ExploreLikeListModel.LikeListAccountModel;
import dji.pilot2.nativeexplore.model.FollowEvent;
import dji.pilot2.nativeexplore.model.FollowListModel.AccountModel;
import dji.pilot2.nativeexplore.model.FollowResultModel;
import dji.pilot2.publics.b.a$e;
import dji.pilot2.publics.b.a$i;
import dji.pilot2.utils.c;
import java.util.ArrayList;
import java.util.List;

public class b extends BaseAdapter implements a$i {
    private Context a;
    private List<AccountModel> b = new ArrayList();
    private View c;
    private View d;
    private boolean t;
    private DisplayImageOptions u = new Builder().showImageOnLoading(R.drawable.v2_avatar_default).cacheInMemory(true).cacheOnDisc(false).build();

    public static class a implements OnClickListener {
        Context a;
        DisplayImageOptions b;
        public AccountModel c;
        public ImageView d;
        public TextView e;
        public TextView f;
        public ImageView g;
        public TextView h;

        public a(Context context, DisplayImageOptions displayImageOptions) {
            this.a = context;
            this.b = displayImageOptions;
        }

        public void a(AccountModel accountModel) {
            this.c = accountModel;
        }

        public void a(View view) {
            this.d = (ImageView) view.findViewById(R.id.cle);
            this.e = (TextView) view.findViewById(R.id.clf);
            this.f = (TextView) view.findViewById(R.id.cli);
            this.h = (TextView) view.findViewById(R.id.clg);
            this.g = (ImageView) view.findViewById(R.id.clh);
        }

        public void a() {
            this.d.setImageResource(R.drawable.v2_avatar_default);
            ImageLoader.getInstance().displayImage(this.c.avatar, new ImageViewAware(this.d, false), this.b);
            this.e.setText(this.c.name);
            this.f.setText(c.a(this.c.country, this.a));
            int a = c.a(this.a, this.c.country);
            if (a == 0) {
                this.g.setVisibility(8);
            } else {
                this.g.setVisibility(0);
                this.g.setImageResource(a);
            }
            if (this.c.id.equals(f.getInstance().i())) {
                this.h.setVisibility(4);
            } else {
                this.h.setVisibility(0);
            }
            if (this.c.is_follow) {
                this.h.setText(R.string.v2_explore_followed);
                this.h.setSelected(true);
                this.h.setVisibility(4);
            } else if (f.getInstance().c() && f.getInstance().i() != null && f.getInstance().i().equals(this.c.id)) {
                this.h.setVisibility(4);
            } else {
                this.h.setText(R.string.v2_explore_follow);
                this.h.setSelected(false);
                this.h.setVisibility(0);
            }
            this.d.setOnClickListener(this);
            this.e.setOnClickListener(this);
            this.h.setOnClickListener(this);
        }

        private void b() {
            Intent intent = new Intent(this.a, DJIAccountSignActivity.class);
            intent.putExtra(DJIAccountSignActivity.a, 1005);
            ((Activity) this.a).startActivity(intent);
        }

        private void c() {
            new d(this.a).show();
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.cle:
                case R.id.clf:
                    Intent intent = new Intent(this.a, ProfileTestActivity.class);
                    intent.putExtra("user_id", this.c.id);
                    intent.putExtra("user_name", this.c.name);
                    intent.putExtra(ProfileTestActivity.I, this.c.avatar);
                    intent.putExtra("country", this.c.country);
                    this.a.startActivity(intent);
                    return;
                case R.id.clg:
                    if (f.getInstance().c()) {
                        this.h.setClickable(false);
                        String str;
                        dji.thirdparty.afinal.f.b bVar;
                        if (this.c.is_follow) {
                            str = "https://www.skypixel.com/api/users/" + this.c.id + a$i.Y;
                            bVar = new dji.thirdparty.afinal.f.b();
                            bVar.a("token", f.getInstance().n());
                            DJILogHelper.getInstance().LOGI("Lyric", "url: " + str);
                            com.dji.frame.c.c.b(this.a).c(str, bVar, new dji.pilot2.nativeexplore.a.d.a(this, this.c) {
                                final /* synthetic */ a a;

                                public void a(String str, Object obj) {
                                    if (obj instanceof LikeListAccountModel) {
                                        AccountModel accountModel = (AccountModel) obj;
                                        FollowResultModel followResultModel = (FollowResultModel) h.b(str, FollowResultModel.class);
                                        if (followResultModel != null && followResultModel.status == 0) {
                                            this.a.c.is_follow = false;
                                            if (accountModel == this.a.c) {
                                                this.a.h.setSelected(false);
                                            }
                                            dji.thirdparty.a.c.a().e(new FollowEvent(dji.pilot2.nativeexplore.model.FollowEvent.b.UNFOLLOW, new dji.pilot2.nativeexplore.model.FollowEvent.a(f.getInstance().i(), f.getInstance().m(), f.getInstance().e(), f.getInstance().h().u), new dji.pilot2.nativeexplore.model.FollowEvent.a(accountModel.id, accountModel.name, accountModel.avatar, accountModel.country)));
                                        }
                                    }
                                    this.a.h.setOnClickListener(this.a);
                                }

                                public void a(Throwable th, int i, String str) {
                                    this.a.h.setOnClickListener(this.a);
                                    this.a.c();
                                }
                            });
                            return;
                        }
                        str = "https://www.skypixel.com/api/users/" + this.c.id + a$i.S;
                        bVar = new dji.thirdparty.afinal.f.b();
                        bVar.a("token", f.getInstance().n());
                        DJILogHelper.getInstance().LOGI("Lyric", "url: " + str);
                        com.dji.frame.c.c.b(this.a).c(str, bVar, new dji.pilot2.nativeexplore.a.d.a(this, this.c) {
                            final /* synthetic */ a a;

                            public void a(String str, Object obj) {
                                if (obj instanceof LikeListAccountModel) {
                                    AccountModel accountModel = (AccountModel) obj;
                                    FollowResultModel followResultModel = (FollowResultModel) h.b(str, FollowResultModel.class);
                                    if (followResultModel != null && followResultModel.status == 0) {
                                        this.a.c.is_follow = true;
                                        if (accountModel == this.a.c) {
                                            this.a.h.setSelected(true);
                                        }
                                        dji.thirdparty.a.c.a().e(new FollowEvent(dji.pilot2.nativeexplore.model.FollowEvent.b.FOLLOW, new dji.pilot2.nativeexplore.model.FollowEvent.a(f.getInstance().i(), f.getInstance().m(), a$e.a + f.getInstance().e(), f.getInstance().h().u), new dji.pilot2.nativeexplore.model.FollowEvent.a(accountModel.id, accountModel.name, accountModel.avatar, accountModel.country)));
                                    }
                                }
                                this.a.h.setVisibility(4);
                                this.a.h.setOnClickListener(this.a);
                            }

                            public void a(Throwable th, int i, String str) {
                                this.a.h.setOnClickListener(this.a);
                                this.a.c();
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

    public b(Context context) {
        this.a = context;
        this.c = LayoutInflater.from(context).inflate(R.layout.v2_explore_refresh_footer, null);
        this.d = new View(context);
    }

    public void a() {
        this.b.clear();
        this.t = false;
    }

    public void a(boolean z) {
        this.t = z;
    }

    public int getCount() {
        if (this.b != null) {
            return this.b.size() + 1;
        }
        return 0;
    }

    public Object getItem(int i) {
        if (this.b != null) {
            return this.b.get(i);
        }
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public boolean isEnabled(int i) {
        return false;
    }

    public synchronized void a(List<AccountModel> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Object obj;
                AccountModel accountModel = (AccountModel) list.get(i);
                int i2 = 0;
                while (i2 < this.b.size()) {
                    if (accountModel.id != null && this.b.get(i2) != null && accountModel.id.equals(((AccountModel) this.b.get(i2)).id)) {
                        obj = 1;
                        break;
                    }
                    i2++;
                }
                obj = null;
                if (obj == null) {
                    this.b.add(accountModel);
                }
            }
            notifyDataSetChanged();
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i != this.b.size()) {
            if (view == null || view.getTag() == null) {
                view = LayoutInflater.from(this.a).inflate(R.layout.v2_explore_like_list_item, null);
                view.setTag(new a(this.a, this.u));
            }
            a aVar = (a) view.getTag();
            aVar.a(view);
            aVar.a((AccountModel) this.b.get(i));
            aVar.a();
            return view;
        } else if (this.t || this.b.size() == 0) {
            return this.d;
        } else {
            return this.c;
        }
    }
}
