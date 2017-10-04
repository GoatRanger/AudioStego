package dji.pilot2.academy.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot2.academy.model.FrequentlyQuestionModel.Faq;
import dji.pilot2.academy.model.FrequentlyQuestionModel.Questions;
import java.util.ArrayList;
import java.util.List;

public class e extends BaseExpandableListAdapter {
    private Context a;
    private List<a> b = new ArrayList();
    private b c;

    public interface b {
        void a(int i);

        void b(int i);
    }

    private static class a {
        public String a;
        public List<Questions> b;

        public a(String str, List<Questions> list) {
            this.a = str;
            this.b = list;
        }
    }

    public e(Context context) {
        this.a = context;
    }

    public synchronized void a(List<Faq> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Object obj;
                Faq faq = (Faq) list.get(i);
                for (int i2 = 0; i2 < this.b.size(); i2++) {
                    if (((a) this.b.get(i2)).a.equals(faq.title)) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    this.b.add(new a(faq.title, faq.questions));
                }
            }
            notifyDataSetChanged();
        }
    }

    public int getGroupCount() {
        if (this.b == null) {
            return 0;
        }
        return this.b.size();
    }

    public int getChildrenCount(int i) {
        if (this.b == null || i < 0) {
            return 0;
        }
        if (i >= this.b.size()) {
            return 0;
        }
        a aVar = (a) this.b.get(i);
        if (aVar == null || aVar.b == null) {
            return 0;
        }
        return aVar.b.size();
    }

    public Object getGroup(int i) {
        if (this.b == null || i < 0 || i >= this.b.size()) {
            return null;
        }
        return this.b.get(i);
    }

    public Object getChild(int i, int i2) {
        a aVar = (a) getGroup(i);
        if (aVar == null) {
            return null;
        }
        if (aVar.b == null || aVar.b.size() <= i2) {
            return null;
        }
        return aVar.b.get(i2);
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public boolean hasStableIds() {
        return false;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.a).inflate(R.layout.v2_new_academy_normalq_group_layout, null);
        }
        DJIStateTextView dJIStateTextView = (DJIStateTextView) view.findViewById(R.id.cvv);
        DJIStateImageView dJIStateImageView = (DJIStateImageView) view.findViewById(R.id.cvx);
        a aVar = (a) getGroup(i);
        if (!(dJIStateTextView == null || aVar == null)) {
            dJIStateTextView.setText(aVar.a);
        }
        if (z) {
            dJIStateImageView.setImageResource(R.drawable.newacademy_normalq_group_expand);
        } else {
            dJIStateImageView.setImageResource(R.drawable.newacademy_normalq_group);
        }
        return view;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.a).inflate(R.layout.v2_new_academy_normalq_group_child_layout, null);
        }
        DJIStateTextView dJIStateTextView = (DJIStateTextView) view.findViewById(R.id.cvv);
        CharSequence charSequence = ((Questions) getChild(i, i2)).question;
        if (dJIStateTextView != null) {
            dJIStateTextView.setText(charSequence);
        }
        return view;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public void onGroupCollapsed(int i) {
        super.onGroupCollapsed(i);
        if (this.c != null) {
            this.c.b(i);
        }
    }

    public void onGroupExpanded(int i) {
        super.onGroupExpanded(i);
        if (this.c != null) {
            this.c.a(i);
        }
    }

    public void a(b bVar) {
        this.c = bVar;
    }
}
