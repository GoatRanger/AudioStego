package dji.pilot2.academy.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot2.academy.a.c;
import dji.pilot2.academy.model.AcademyHotFaqInfo.a;
import dji.pilot2.utils.k;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class f extends BaseAdapter {
    private Context a;
    private List<a> b = new ArrayList();

    public f(Context context) {
        this.a = context;
    }

    public boolean a(List<a> list) {
        if (list != null && list.size() > 0) {
            this.b = list;
            notifyDataSetChanged();
        }
        return true;
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        if (i < 0 || i >= this.b.size()) {
            return null;
        }
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.a).inflate(R.layout.v2_new_academy_novicecheat_childview, null);
        }
        TextView textView = (TextView) view.findViewById(R.id.cvz);
        a aVar = (a) this.b.get(i);
        if (aVar != null) {
            textView.setText(aVar.c);
        }
        return view;
    }

    public void a(int i, String str) {
        DJILogHelper.getInstance().LOGI("bob", "DJINoviceCheatsAdapter handleDataSuccess cmdId = " + i);
        if (i == 4) {
            a(str);
        }
    }

    public void a(int i) {
        DJILogHelper.getInstance().LOGI("bob", "DJINoviceCheatsAdapter handleDataStart cmdId = " + i);
    }

    public void a(int i, int i2, Object obj) {
        DJILogHelper.getInstance().LOGI("bob", "DJINoviceCheatsAdapter handleDataFail cmdId = " + i);
        if (i != 4) {
        }
    }

    protected void a(String str) {
        Collection a = c.getInstance().a(this.a, str);
        this.b.clear();
        if (!(a == null || a.isEmpty())) {
            this.b.addAll(a);
        }
        notifyDataSetChanged();
    }

    public String b(int i) {
        Object item = getItem(i);
        if (item == null) {
            return null;
        }
        return k.a(((a) item).a, this.a);
    }

    public boolean b(String str) {
        if (c.getInstance().b()) {
            return false;
        }
        c.getInstance().b(this.a, str);
        return true;
    }
}
