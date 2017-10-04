package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.dji.frame.c.d;
import dji.log.ErrorPopLogHelper;
import dji.log.ErrorPopLogHelper.PopLogDetailItem;
import dji.pilot.setting.ui.R;
import dji.thirdparty.a.c;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PopLogDetailView extends LinearLayout {
    private ListView a;
    private a b;
    private List<PopLogDetailItem> c = new ArrayList();

    public class a extends BaseAdapter {
        final /* synthetic */ PopLogDetailView a;
        private LayoutInflater b;

        public class a {
            public TextView a;
            public TextView b;
            final /* synthetic */ a c;

            public a(a aVar) {
                this.c = aVar;
            }
        }

        public a(PopLogDetailView popLogDetailView, Context context) {
            this.a = popLogDetailView;
            this.b = LayoutInflater.from(context);
        }

        public int getCount() {
            return this.a.c.size();
        }

        public Object getItem(int i) {
            return this.a.c.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = this.b.inflate(R.layout.pop_log_details_item, viewGroup, false);
                aVar = new a(this);
                aVar.a = (TextView) view.findViewById(R.id.pop_logs_detail_item_title);
                aVar.b = (TextView) view.findViewById(R.id.pop_logs_detail_item_content);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            int size = (this.a.c.size() - i) - 1;
            aVar.a.setText(((PopLogDetailItem) this.a.c.get(size)).mTitle);
            aVar.b.setText(((PopLogDetailItem) this.a.c.get(size)).mContent);
            return view;
        }
    }

    public PopLogDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (ListView) findViewById(R.id.pop_log_details_lv);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        a();
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    private void a() {
        if (getTag() instanceof String) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(d.a(getContext(), ErrorPopLogHelper.POP_LOGS_RELATIVE_PATH + ((String) getTag())))));
                PopLogDetailItem popLogDetailItem = null;
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (readLine.contains(ErrorPopLogHelper.TAG_TITLE)) {
                        if (popLogDetailItem != null) {
                            this.c.add(popLogDetailItem);
                        }
                        popLogDetailItem = new PopLogDetailItem();
                        popLogDetailItem.mTitle = readLine.replace(ErrorPopLogHelper.TAG_TITLE, "");
                    } else if (popLogDetailItem != null) {
                        popLogDetailItem.mContent += readLine;
                    } else {
                        continue;
                    }
                }
                if (popLogDetailItem != null) {
                    this.c.add(popLogDetailItem);
                }
                bufferedReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            ListView listView = this.a;
            ListAdapter aVar = new a(this, getContext());
            this.b = aVar;
            listView.setAdapter(aVar);
        }
    }

    public void onEventMainThread(PopLogDetailItem popLogDetailItem) {
        this.c.add(popLogDetailItem);
        if (this.b != null) {
            this.b.notifyDataSetChanged();
        }
    }
}
