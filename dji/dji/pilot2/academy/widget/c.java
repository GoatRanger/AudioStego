package dji.pilot2.academy.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot2.academy.model.FlighBookModel.FlightBookData;
import dji.pilot2.publics.b.a$i;
import java.util.ArrayList;
import java.util.List;

public class c extends BaseAdapter implements a$i {
    private Context a;
    private List<FlightBookData> b = new ArrayList();
    private boolean c;

    public static class a {
        Context a;
        public FlightBookData b;
        public TextView c;

        public a(Context context) {
            this.a = context;
        }

        public void a(FlightBookData flightBookData) {
            this.b = flightBookData;
        }

        public void a(View view) {
            this.c = (TextView) view.findViewById(R.id.cn4);
        }

        public void a() {
            this.c.setText(this.b.title);
        }
    }

    public c(Context context) {
        this.a = context;
    }

    public void a() {
        this.b.clear();
        this.c = false;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public int getCount() {
        if (this.b != null) {
            return this.b.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        if (this.b != null) {
            return this.b.get(i);
        }
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public synchronized void a(List<FlightBookData> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Object obj;
                FlightBookData flightBookData = (FlightBookData) list.get(i);
                for (int i2 = 0; i2 < this.b.size(); i2++) {
                    if (flightBookData.id == ((FlightBookData) this.b.get(i2)).id) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    this.b.add(flightBookData);
                }
            }
            notifyDataSetChanged();
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.a).inflate(R.layout.v2_flight_book_item, null);
            view.setTag(new a(this.a));
        }
        a aVar = (a) view.getTag();
        aVar.a(view);
        aVar.a((FlightBookData) this.b.get(i));
        aVar.a();
        return view;
    }
}
