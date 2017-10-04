package dji.pilot2.explore.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.usercenter.b.f;
import dji.pilot2.explore.activity.DJIExploreCommentActivity;
import dji.pilot2.explore.model.CommentListModel.Comment;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class a extends BaseAdapter {
    private static final String a = "SlideAdapterComment";
    private Context b;
    private List<Comment> c = new ArrayList();
    private ImageLoader d = null;
    private DisplayImageOptions e = null;
    private ImageLoadingListener f = null;
    private View g;
    private View h;
    private boolean i;
    private boolean j = false;

    public class a {
        public ImageView a;
        public TextView b;
        public TextView c;
        public TextView d;
        public ViewGroup e;
        public String f;
        public int g;
        final /* synthetic */ a h;

        a(a aVar, View view) {
            this.h = aVar;
            this.a = (ImageView) view.findViewById(R.id.ckw);
            this.b = (TextView) view.findViewById(R.id.ckx);
            this.c = (TextView) view.findViewById(R.id.cky);
            this.d = (TextView) view.findViewById(R.id.ckz);
            this.e = (ViewGroup) view.findViewById(R.id.cl0);
        }

        void a(Comment comment) {
            this.b.setText(comment.user);
            try {
                this.c.setText(dji.pilot2.explore.c.a.a(this.h.b, comment.created_at));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.d.setText(comment.content);
            if (f.getInstance().i().equals(comment.account_id)) {
                this.e.setVisibility(0);
            } else {
                this.e.setVisibility(8);
            }
            this.h.d.displayImage(comment.avatar, this.a, this.h.e, this.h.f);
        }
    }

    public a(Context context) {
        this.b = context;
        a();
        this.g = LayoutInflater.from(context).inflate(R.layout.v2_explore_refresh_footer, null);
        this.h = new View(context);
    }

    public void a(boolean z) {
        this.i = z;
    }

    public void b(boolean z) {
        this.j = z;
    }

    private void a() {
        this.d = ImageLoader.getInstance();
        this.e = new Builder().imageScaleType(ImageScaleType.EXACTLY).considerExifParams(true).bitmapConfig(Config.RGB_565).cacheInMemory(true).cacheOnDisc(false).build();
        this.f = new ImageLoadingListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onLoadingStarted(String str, View view) {
            }

            public void onLoadingFailed(String str, View view, FailReason failReason) {
                this.a.d.displayImage(str, (ImageView) view, this.a.e);
            }

            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            }

            public void onLoadingCancelled(String str, View view) {
            }
        };
    }

    public int getCount() {
        if (this.c != null) {
            return this.c.size() + 1;
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

    public synchronized void a(List<Comment> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Object obj;
                Comment comment = (Comment) list.get(i);
                for (int i2 = 0; i2 < this.c.size(); i2++) {
                    if (comment.id == ((Comment) this.c.get(i2)).id) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    if (this.j) {
                        this.j = false;
                        this.c.add(0, comment);
                    } else {
                        this.c.add(comment);
                    }
                }
            }
            notifyDataSetChanged();
        }
    }

    public synchronized void a(int i) {
        Object obj;
        for (int i2 = 0; i2 < this.c.size(); i2++) {
            if (i == ((Comment) this.c.get(i2)).id) {
                obj = 1;
                this.c.remove(i2);
                break;
            }
        }
        obj = null;
        if (obj != null) {
            notifyDataSetChanged();
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i != this.c.size()) {
            a aVar;
            DJILogHelper.getInstance().LOGD(a, "position=" + i + ",convertView=" + view);
            if (view == null || view.getTag() == null) {
                view = LayoutInflater.from(this.b).inflate(R.layout.v2_explore_comment_list_item, null);
                a aVar2 = new a(this, view);
                view.setTag(aVar2);
                aVar = aVar2;
            } else {
                aVar = (a) view.getTag();
            }
            aVar.a((Comment) this.c.get(i));
            aVar.e.setOnClickListener((DJIExploreCommentActivity) this.b);
            aVar.e.setTag(Integer.valueOf(i));
            return view;
        } else if (this.i || this.c.size() == 0) {
            return this.h;
        } else {
            return this.g;
        }
    }
}
