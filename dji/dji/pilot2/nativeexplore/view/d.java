package dji.pilot2.nativeexplore.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot.usercenter.b.f;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.mine.activity.ProfileTestActivity;
import dji.pilot2.nativeexplore.b.b;
import dji.pilot2.nativeexplore.model.ExploreItem;
import dji.pilot2.nativeexplore.model.FollowEvent;
import dji.pilot2.nativeexplore.model.TopTileModel;
import dji.thirdparty.a.c;

public class d extends a<TopTileModel> {
    private ImageView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private View k;
    private ImageView l;
    private GLExploreList m;
    private boolean n = false;
    private View o;
    private OnItemClickListener p;

    class a extends BaseAdapter {
        final /* synthetic */ d a;

        a(d dVar) {
            this.a = dVar;
        }

        public int getCount() {
            if (this.a.a == null || ((TopTileModel) this.a.a).positionList == null) {
                return 0;
            }
            return ((TopTileModel) this.a.a).positionList.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View textView;
            if (view == null) {
                textView = new TextView(this.a.b);
            } else {
                textView = view;
            }
            textView.setLayoutParams(new LayoutParams(-1, 150));
            ((TextView) textView).setGravity(17);
            ((TextView) textView).setText((CharSequence) ((TopTileModel) this.a.a).positionList.get(i));
            return textView;
        }
    }

    public d(TopTileModel topTileModel, Context context) {
        super(topTileModel, context);
        c.a().a(this);
    }

    public void a(TopTileModel topTileModel) {
        this.c = LayoutInflater.from(this.b).inflate(R.layout.gl_titleview_layout, null);
        this.e = (ImageView) this.c.findViewById(R.id.aig);
        this.f = (TextView) this.c.findViewById(R.id.aej);
        this.g = (TextView) this.c.findViewById(R.id.aii);
        this.h = (TextView) this.c.findViewById(R.id.bv);
        this.i = (TextView) this.c.findViewById(R.id.aik);
        this.l = (ImageView) this.c.findViewById(R.id.aij);
        this.l.setImageDrawable(this.b.getResources().getDrawable(R.drawable.v2_photo_defalut));
        this.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Intent intent = new Intent(this.a.b, ProfileTestActivity.class);
                intent.putExtra("user_id", ((TopTileModel) this.a.a).id);
                intent.putExtra("user_name", ((TopTileModel) this.a.a).account_name);
                intent.putExtra(ProfileTestActivity.I, ((TopTileModel) this.a.a).avatarUrl);
                this.a.b.startActivity(intent);
            }
        });
        this.m = (GLExploreList) this.c.findViewById(R.id.ain);
        this.m.setVisibility(8);
        this.o = this.c.findViewById(R.id.aih);
        this.o.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (f.getInstance().c()) {
                    this.a.o.setVisibility(8);
                    ExploreItem exploreItem = new ExploreItem();
                    exploreItem.userId = ((TopTileModel) this.a.a).id;
                    exploreItem.userAvatarUrl = ((TopTileModel) this.a.a).avatarUrl;
                    exploreItem.userCountry = ((TopTileModel) this.a.a).country;
                    exploreItem.userName = ((TopTileModel) this.a.a).account_name;
                    b.getInstance(this.a.b).a(((TopTileModel) this.a.a).id, exploreItem);
                    return;
                }
                this.a.b.startActivity(new Intent(this.a.b, DJIAccountSignActivity.class));
            }
        });
        this.j = (TextView) this.c.findViewById(R.id.aim);
        this.k = this.c.findViewById(R.id.ail);
        this.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.n) {
                    this.a.m.setVisibility(8);
                    this.a.n = false;
                    return;
                }
                this.a.m.setVisibility(0);
                this.a.n = true;
            }
        });
    }

    public void a(OnItemClickListener onItemClickListener) {
        this.p = onItemClickListener;
        this.m.setOnItemClickListener(this.p);
    }

    public void b(TopTileModel topTileModel) {
        this.a = topTileModel;
    }

    public void b() {
        if (this.a != null) {
            this.d.displayImage(((TopTileModel) this.a).avatarUrl, this.l);
            this.d.displayImage(((TopTileModel) this.a).PicUrl, this.e);
            this.f.setText(((TopTileModel) this.a).account_name);
            this.g.setText(((TopTileModel) this.a).position);
            this.h.setText(((TopTileModel) this.a).Content);
            this.i.setText(((TopTileModel) this.a).ContentTitle);
            if (((TopTileModel) this.a).isFollowed) {
                this.o.setVisibility(8);
            } else {
                this.o.setVisibility(0);
            }
            Object aVar = new a(this);
            this.m.setAdapter(aVar);
            aVar.notifyDataSetChanged();
            this.j.setText(this.b.getString(R.string.v2_nativeexplore_gl_position, new Object[]{Integer.valueOf(((TopTileModel) this.a).positionList.size())}));
        }
    }

    public int c() {
        return this.c.getHeight();
    }

    public void onEventMainThread(FollowEvent followEvent) {
        if (followEvent.action == FollowEvent.b.FOLLOW) {
            this.o.setVisibility(8);
        } else {
            this.o.setVisibility(0);
        }
    }

    public View a() {
        return this.c;
    }
}
