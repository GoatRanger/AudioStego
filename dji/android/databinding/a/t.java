package android.databinding.a;

import android.content.Context;
import android.databinding.w;
import android.databinding.w.a;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

class t<T> extends BaseAdapter {
    private List<T> a;
    private a b;
    private final Context c;
    private final int d;
    private final int e;
    private final int f;
    private final LayoutInflater g;

    public t(Context context, List<T> list, int i, int i2, int i3) {
        LayoutInflater layoutInflater;
        this.c = context;
        this.e = i;
        this.d = i2;
        this.f = i3;
        if (i == 0) {
            layoutInflater = null;
        } else {
            layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        }
        this.g = layoutInflater;
        a(list);
    }

    public void a(List<T> list) {
        if (this.a != list) {
            if (this.a instanceof w) {
                ((w) this.a).b(this.b);
            }
            this.a = list;
            if (this.a instanceof w) {
                if (this.b == null) {
                    this.b = new a(this) {
                        final /* synthetic */ t a;

                        {
                            this.a = r1;
                        }

                        public void a(w wVar) {
                            this.a.notifyDataSetChanged();
                        }

                        public void a(w wVar, int i, int i2) {
                            this.a.notifyDataSetChanged();
                        }

                        public void b(w wVar, int i, int i2) {
                            this.a.notifyDataSetChanged();
                        }

                        public void a(w wVar, int i, int i2, int i3) {
                            this.a.notifyDataSetChanged();
                        }

                        public void c(w wVar, int i, int i2) {
                            this.a.notifyDataSetChanged();
                        }
                    };
                }
                ((w) this.a).a(this.b);
            }
            notifyDataSetChanged();
        }
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return a(this.e, i, view, viewGroup);
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return a(this.d, i, view, viewGroup);
    }

    public View a(int i, int i2, View view, ViewGroup viewGroup) {
        View view2;
        CharSequence charSequence;
        if (view == null) {
            if (i == 0) {
                view = new TextView(this.c);
            } else {
                view = this.g.inflate(i, viewGroup, false);
            }
        }
        if (this.f == 0) {
            view2 = view;
        } else {
            view2 = view.findViewById(this.f);
        }
        TextView textView = (TextView) view2;
        Object obj = this.a.get(i2);
        if (obj instanceof CharSequence) {
            charSequence = (CharSequence) obj;
        } else {
            charSequence = String.valueOf(obj);
        }
        textView.setText(charSequence);
        return view;
    }
}
