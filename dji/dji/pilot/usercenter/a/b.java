package dji.pilot.usercenter.a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import dji.pilot.R;
import dji.publics.DJIUI.DJITextView;

public class b extends BaseAdapter {
    public static final int a = 0;
    public static final int b = 1;
    private static final int[] c = new int[]{R.string.share_mail, R.string.share_facebook, R.string.share_dropbox, R.string.share_tumblr, R.string.share_weibo, R.string.share_twitter, R.string.share_instagram, R.string.share_wechat};
    private static final int[] d = new int[]{R.drawable.share_mail, R.drawable.share_facebook, R.drawable.share_dropbox, R.drawable.share_tumblr, R.drawable.share_weibo, R.drawable.share_twitter, R.drawable.share_instagram, R.drawable.share_wechat};
    private static final int[] e = new int[]{R.string.share_youku, R.string.share_youtube};
    private static final int[] f = new int[]{R.drawable.share_youku, R.drawable.share_youtube};
    private final LayoutInflater g;
    private int[] h = null;
    private int[] i = null;

    private static final class a {
        public DJITextView a;

        private a() {
        }
    }

    public b(LayoutInflater layoutInflater) {
        this.g = layoutInflater;
    }

    public b a(int i) {
        if (i == 0) {
            this.h = c;
            this.i = d;
        } else if (1 == i) {
            this.h = e;
            this.i = f;
        }
        return this;
    }

    public int getCount() {
        return this.h.length;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) this.h[i];
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            a aVar2 = new a();
            view = this.g.inflate(R.layout.share_pw_item, null);
            aVar2.a = (DJITextView) view.findViewById(R.id.bza);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setCompoundDrawablesWithIntrinsicBounds(0, this.i[i], 0, 0);
        aVar.a.setText(this.h[i]);
        return view;
    }
}
