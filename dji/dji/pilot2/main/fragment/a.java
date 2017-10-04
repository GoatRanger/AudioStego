package dji.pilot2.main.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.sina.weibo.sdk.component.ShareRequestParam;
import dji.pilot.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.util.List;
import java.util.Map;

public class a extends BaseAdapter {
    private Context a;
    private LayoutInflater b = null;
    private List<Map<String, Object>> c;

    static class a {
        public DJIImageView a;
        public DJITextView b;

        a() {
        }
    }

    public a(Context context, List<Map<String, Object>> list) {
        this.a = context;
        this.b = LayoutInflater.from(context);
        this.c = list;
    }

    public int getCount() {
        if (this.c != null) {
            return this.c.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a();
            view = this.b.inflate(R.layout.mainframe_topbar_listview_item, null);
            aVar.a = (DJIImageView) view.findViewById(R.id.be0);
            aVar.b = (DJITextView) view.findViewById(R.id.aej);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setImageResource(((Integer) ((Map) this.c.get(i)).get(ShareRequestParam.REQ_UPLOAD_PIC_PARAM_IMG)).intValue());
        aVar.b.setText(((Integer) ((Map) this.c.get(i)).get("title")).intValue());
        return view;
    }
}
