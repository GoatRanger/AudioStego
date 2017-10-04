package dji.pilot2.nativeexplore.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot2.mine.activity.ProfileTestActivity;
import dji.pilot2.nativeexplore.model.BottomCommentModel;
import dji.pilot2.nativeexplore.model.BottomCommentModel.CommentItemModel;
import dji.pilot2.usercenter.widget.DJIProfileRoundImageView;

public class b extends a<BottomCommentModel> {
    private GLExploreList e;
    private a f;

    class a extends BaseAdapter {
        final /* synthetic */ b a;

        a(b bVar) {
            this.a = bVar;
        }

        public int getCount() {
            if (this.a.a == null || ((BottomCommentModel) this.a.a).items == null || ((BottomCommentModel) this.a.a).items.size() <= 0) {
                return 0;
            }
            return ((BottomCommentModel) this.a.a).items.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public boolean isEnabled(int i) {
            return false;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            if (view == null) {
                view = LayoutInflater.from(this.a.b).inflate(R.layout.gl_commentitem_layout, null);
                bVar = new b(this.a);
            } else {
                bVar = (b) view.getTag();
            }
            bVar.a = (DJIProfileRoundImageView) view.findViewById(R.id.ahq);
            bVar.b = (TextView) view.findViewById(R.id.ahr);
            bVar.c = (TextView) view.findViewById(R.id.ahs);
            bVar.d = (TextView) view.findViewById(R.id.aht);
            final CommentItemModel commentItemModel = (CommentItemModel) ((BottomCommentModel) this.a.a).items.get(i);
            bVar.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    Intent intent = new Intent(this.b.a.b, ProfileTestActivity.class);
                    intent.putExtra("user_id", commentItemModel.account_id);
                    intent.putExtra("user_name", commentItemModel.user);
                    intent.putExtra(ProfileTestActivity.I, commentItemModel.avatar);
                    this.b.a.b.startActivity(intent);
                }
            });
            if (commentItemModel != null) {
                this.a.d.displayImage(commentItemModel.avatar, bVar.a);
                bVar.b.setText(commentItemModel.user);
                bVar.c.setText(commentItemModel.created_at);
                bVar.d.setText(commentItemModel.content);
            }
            view.setTag(bVar);
            return view;
        }
    }

    class b {
        public DJIProfileRoundImageView a;
        public TextView b;
        public TextView c;
        public TextView d;
        final /* synthetic */ b e;

        b(b bVar) {
            this.e = bVar;
        }
    }

    public b(BottomCommentModel bottomCommentModel, Context context) {
        super(bottomCommentModel, context);
    }

    public void a(BottomCommentModel bottomCommentModel) {
        this.c = LayoutInflater.from(this.b).inflate(R.layout.gl_comment_layout, null);
        this.e = (GLExploreList) this.c.findViewById(R.id.ahp);
        this.f = new a(this);
        this.e.setAdapter(this.f);
        this.f.notifyDataSetChanged();
    }

    public View a() {
        return this.c;
    }

    public void b(BottomCommentModel bottomCommentModel) {
        this.a = bottomCommentModel;
    }

    public void b() {
        this.f.notifyDataSetChanged();
    }
}
