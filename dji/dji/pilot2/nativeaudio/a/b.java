package dji.pilot2.nativeaudio.a;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot.usercenter.protocol.d;
import java.util.ArrayList;

public class b extends BaseAdapter {
    private a a;
    private ArrayList<dji.pilot2.nativeaudio.model.c> b = new ArrayList();
    private ArrayList<dji.pilot2.nativeaudio.model.b> c = new ArrayList();
    private Context d;
    private boolean e = true;
    private boolean f = false;
    private int g = 0;
    private ArrayList<b> h = new ArrayList();

    public enum a {
        AdapterHasTime,
        AdapterNormal
    }

    public class b {
        public TextView a;
        public TextView b;
        public TextView c;
        public ImageView d;
        final /* synthetic */ b e;

        public b(b bVar) {
            this.e = bVar;
        }
    }

    public class c {
        TextView a;
        TextView b;
        ImageView c;
        final /* synthetic */ b d;

        public c(b bVar) {
            this.d = bVar;
        }
    }

    public b(Context context) {
        this.d = context;
    }

    public void a(int i) {
        this.g = i;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void b(boolean z) {
        this.f = z;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void a(dji.pilot2.nativeaudio.model.c cVar) {
        this.b.add(cVar);
    }

    public void a(ArrayList<dji.pilot2.nativeaudio.model.b> arrayList) {
        this.c = arrayList;
    }

    public void b(ArrayList<dji.pilot2.nativeaudio.model.c> arrayList) {
        this.b = arrayList;
    }

    public int getCount() {
        if (this.a == a.AdapterHasTime) {
            return this.c.size();
        }
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.h.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (this.a == a.AdapterHasTime) {
            b bVar;
            if (view == null) {
                view = LayoutInflater.from(this.d).inflate(R.layout.v2_layout_hastime_item, null);
                bVar = new b(this);
                bVar.d = (ImageView) view.findViewById(R.id.cpl);
                bVar.a = (TextView) view.findViewById(R.id.cpm);
                bVar.b = (TextView) view.findViewById(R.id.cpn);
                bVar.c = (TextView) view.findViewById(R.id.cpo);
                view.setTag(bVar);
            } else {
                bVar = (b) view.getTag();
            }
            if (i == this.g) {
                bVar.d.setVisibility(0);
                bVar.a.setTextColor(this.d.getResources().getColor(R.color.jp));
                bVar.b.setTextColor(this.d.getResources().getColor(R.color.jp));
                bVar.c.setTextColor(this.d.getResources().getColor(R.color.jp));
            } else {
                bVar.d.setVisibility(4);
                bVar.a.setTextColor(this.d.getResources().getColor(R.color.js));
                bVar.b.setTextColor(this.d.getResources().getColor(R.color.js));
                bVar.c.setTextColor(this.d.getResources().getColor(R.color.js));
            }
            dji.pilot2.nativeaudio.model.b bVar2 = (dji.pilot2.nativeaudio.model.b) this.c.get(i);
            bVar.a.setText(bVar2.c);
            bVar.b.setText(bVar2.h);
            bVar.c.setText((String) DateFormat.format("mm:ss", bVar2.e));
            this.h.add(bVar);
            return view;
        }
        c cVar;
        if (view == null) {
            view = LayoutInflater.from(this.d).inflate(R.layout.v2_audio_nativeitem, null);
            cVar = new c(this);
            cVar.c = (ImageView) view.findViewById(R.id.cha);
            cVar.a = (TextView) view.findViewById(R.id.chc);
            cVar.b = (TextView) view.findViewById(R.id.chd);
        } else {
            cVar = (c) view.getTag();
        }
        if (cVar == null) {
            return null;
        }
        if (!this.e) {
            cVar.c.setVisibility(8);
        } else if (cVar.c != null) {
            cVar.c.setVisibility(0);
            if (((dji.pilot2.nativeaudio.model.c) this.b.get(i)).a != 0) {
                cVar.c.setImageDrawable(this.d.getResources().getDrawable(((dji.pilot2.nativeaudio.model.c) this.b.get(i)).a));
            } else if (((dji.pilot2.nativeaudio.model.c) this.b.get(i)).c != null) {
                cVar.c.setImageBitmap(BitmapFactory.decodeFile(((dji.pilot2.nativeaudio.model.c) this.b.get(i)).c));
            } else {
                cVar.c.setVisibility(4);
            }
        }
        if (!this.f) {
            cVar.a.setText(((dji.pilot2.nativeaudio.model.c) this.b.get(i)).b);
            return view;
        } else if (((dji.pilot2.nativeaudio.model.c) this.b.get(i)).b == null || ((dji.pilot2.nativeaudio.model.c) this.b.get(i)).b.isEmpty()) {
            return view;
        } else {
            cVar.a.setText(((dji.pilot2.nativeaudio.model.c) this.b.get(i)).b.substring(((dji.pilot2.nativeaudio.model.c) this.b.get(i)).b.lastIndexOf(d.t) + 1));
            cVar.b.setVisibility(0);
            cVar.b.setText(((dji.pilot2.nativeaudio.model.c) this.b.get(i)).b);
            return view;
        }
    }
}
