package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.dji.frame.c.d;
import dji.log.ErrorPopLogHelper;
import dji.pilot.setting.ui.R;
import dji.thirdparty.a.c;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PopLogsDirsView extends LinearLayout {
    private ListView a;
    private List<String> b = new ArrayList();

    private static class a implements Comparator<File> {
        private a() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((File) obj, (File) obj2);
        }

        public int a(File file, File file2) {
            long lastModified = file.lastModified() - file2.lastModified();
            if (lastModified > 0) {
                return -1;
            }
            if (lastModified == 0) {
                return 0;
            }
            return 1;
        }

        public boolean equals(Object obj) {
            return true;
        }
    }

    public class b extends BaseAdapter {
        final /* synthetic */ PopLogsDirsView a;
        private LayoutInflater b;

        public class a {
            public TextView a;
            final /* synthetic */ b b;

            public a(b bVar) {
                this.b = bVar;
            }
        }

        public b(PopLogsDirsView popLogsDirsView, Context context) {
            this.a = popLogsDirsView;
            this.b = LayoutInflater.from(context);
        }

        public int getCount() {
            return this.a.b.size();
        }

        public Object getItem(int i) {
            return this.a.b.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = this.b.inflate(R.layout.pop_logs_dirs_item, null);
                a aVar2 = new a(this);
                aVar2.a = (TextView) view.findViewById(R.id.pop_logs_dirs_item_name);
                view.setTag(aVar2);
                aVar = aVar2;
            } else {
                aVar = (a) view.getTag();
            }
            aVar.a.setText((CharSequence) this.a.b.get(i));
            return view;
        }
    }

    public PopLogsDirsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (ListView) findViewById(R.id.pop_logs_lv);
        File file = new File(d.a(getContext(), ErrorPopLogHelper.POP_LOGS_RELATIVE_PATH));
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            Arrays.sort(listFiles, new a());
            for (File name : listFiles) {
                this.b.add(name.getName());
            }
            this.a.setAdapter(new b(this, getContext()));
            this.a.setOnItemClickListener(new OnItemClickListener(this) {
                final /* synthetic */ PopLogsDirsView a;

                {
                    this.a = r1;
                }

                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    c.a().e(new dji.setting.ui.c(R.layout.setting_ui_group_general_pop_log_detail, R.string.setting_ui_general_flight_logs, this.a, this.a.b.get(i)));
                }
            });
        }
    }
}
