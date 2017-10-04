package dji.pilot.set.view.base;

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

public class TextListView extends ListView implements OnItemClickListener {
    private int a;
    private String[] b;
    private int c;

    private class a extends BaseAdapter {
        final /* synthetic */ TextListView a;

        private a(TextListView textListView) {
            this.a = textListView;
        }

        public int getCount() {
            return this.a.b.length;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = ((LayoutInflater) this.a.getContext().getSystemService("layout_inflater")).inflate(R.layout.set_item_choice_text, null, false);
            }
            TextView textView = (TextView) view.findViewById(R.id.set_item_title);
            textView.setText(this.a.b[i]);
            View findViewById = view.findViewById(R.id.set_item_selected_icon);
            if (i == this.a.a) {
                findViewById.setVisibility(0);
                textView.setSelected(true);
            } else {
                findViewById.setVisibility(8);
                textView.setSelected(false);
            }
            return view;
        }
    }

    public TextListView(int i, String[] strArr, int i2, Context context) {
        super(context);
        this.a = i;
        this.b = strArr;
        this.c = i2;
        setAdapter(new a());
        setOnItemClickListener(this);
        setDivider(null);
    }

    public static View showSelectView(int i, String[] strArr, int i2, Context context) {
        c.a().e(new b(new TextListView(i, strArr, i2, context), i2));
        return null;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (!dji.pilot.set.c.a() && i != this.a) {
            c.a().e(new dji.pilot.set.longan.b.a(this));
            c.a().e(new dji.pilot.set.view.base.SetTextView.a(this.c, i));
        }
    }
}
