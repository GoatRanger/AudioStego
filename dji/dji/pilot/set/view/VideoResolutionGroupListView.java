package dji.pilot.set.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import dji.pilot.set.R;
import dji.pilot.set.longan.b;
import dji.thirdparty.a.c;

public class VideoResolutionGroupListView extends ListView implements OnItemClickListener {
    private int a;
    private int[] b;
    private String[] c;
    private int d;

    private class a extends BaseAdapter {
        final /* synthetic */ VideoResolutionGroupListView a;

        private a(VideoResolutionGroupListView videoResolutionGroupListView) {
            this.a = videoResolutionGroupListView;
        }

        public int getCount() {
            return this.a.c.length;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public int getItemViewType(int i) {
            return this.a.b[i];
        }

        public int getViewTypeCount() {
            return 2;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            int itemViewType = getItemViewType(i);
            if (view == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.a.getContext().getSystemService("layout_inflater");
                if (itemViewType == 0) {
                    view = layoutInflater.inflate(R.layout.set_item_choice_text, null, false);
                } else {
                    view = layoutInflater.inflate(R.layout.set_item_group_text, null, false);
                }
            }
            TextView textView = (TextView) view.findViewById(R.id.set_item_title);
            textView.setText(this.a.c[i]);
            if (itemViewType == 0) {
                View findViewById = view.findViewById(R.id.set_item_selected_icon);
                if (i == this.a.a) {
                    findViewById.setVisibility(0);
                    textView.setSelected(true);
                } else {
                    findViewById.setVisibility(8);
                    textView.setSelected(false);
                }
            } else {
                textView.setSelected(false);
            }
            return view;
        }
    }

    public VideoResolutionGroupListView(int i, String[] strArr, int[] iArr, int i2, Context context) {
        super(context);
        this.a = i;
        this.c = strArr;
        this.b = iArr;
        this.d = i2;
        setAdapter(new a());
        setOnItemClickListener(this);
        setDivider(null);
    }

    public static View showSelectView(int i, String[] strArr, int[] iArr, int i2, Context context) {
        c.a().e(new b(new VideoResolutionGroupListView(i, strArr, iArr, i2, context), i2));
        return null;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (!dji.pilot.set.c.a() && i != this.a && getAdapter().getItemViewType(i) != 1) {
            c.a().e(new dji.pilot.set.longan.b.a(this));
            c.a().e(new dji.pilot.set.view.VideoResolutionSetterView.a(this.d, i));
        }
    }
}
