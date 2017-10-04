package dji.pilot2.academy.widget;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.visual.a.d;
import dji.pilot2.academy.model.AcademyProductTypeModel.ProductTypeStruct;
import dji.pilot2.main.fragment.DJIPhantomFragment;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;
import java.util.Iterator;

public class h extends BaseAdapter {
    private static int d = 0;
    private final LayoutInflater a;
    private Context b = null;
    private ArrayList<ProductTypeStruct> c;

    private static final class a {
        public DJIRelativeLayout a;
        public DJITextView b;
        public DJITextView c;
        public DJITextView d;
        public DJIImageView e;

        private a() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
        }

        public void a(int i) {
            b(i);
        }

        private void b(int i) {
            if (h.d == i) {
                this.a.setAlpha(1.0f);
            } else {
                this.a.setAlpha(d.c);
            }
        }
    }

    public h(Context context, ArrayList<ProductTypeStruct> arrayList) {
        this.b = context;
        this.a = LayoutInflater.from(context);
        this.c = new ArrayList(arrayList);
    }

    public final void a(int i) {
        d = i;
        notifyDataSetChanged();
    }

    public final ProductType b(int i) {
        return ((ProductTypeStruct) this.c.get(i)).mProductCode;
    }

    public void a(ProductType productType) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ProductTypeStruct productTypeStruct = (ProductTypeStruct) it.next();
            if (productTypeStruct.mProductCode.equals(productType)) {
                this.c.remove(productTypeStruct);
                return;
            }
        }
    }

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return a(R.layout.v2_academy_typestab_item, i, view);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return a(R.layout.v2_academy_typestab_actionbar_item, i, view);
    }

    private View a(int i, int i2, View view) {
        a aVar;
        if (view == null) {
            a aVar2 = new a();
            view = this.a.inflate(i, null);
            aVar2.a = (DJIRelativeLayout) view.findViewById(R.id.c45);
            aVar2.b = (DJITextView) view.findViewById(R.id.c46);
            aVar2.c = (DJITextView) view.findViewById(R.id.c47);
            aVar2.d = (DJITextView) view.findViewById(R.id.c48);
            aVar2.e = (DJIImageView) view.findViewById(R.id.c49);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        if (aVar.e != null) {
            if (this.c.size() == 1) {
                aVar.e.go();
            } else {
                aVar.e.show();
            }
        }
        ProductTypeStruct productTypeStruct = (ProductTypeStruct) this.c.get(i2);
        aVar.b.setText(productTypeStruct.mSeries);
        if (productTypeStruct.mVersion == 0) {
            aVar.c.setText("");
        } else {
            aVar.c.setText("" + ((ProductTypeStruct) this.c.get(i2)).mVersion);
        }
        if (!DJIPhantomFragment.m.equals(productTypeStruct.mSeries) || dji.pilot.publics.e.d.a(productTypeStruct.mSubVersion)) {
            aVar.c.setTextColor(this.b.getResources().getColor(R.color.gj));
        } else {
            aVar.c.setTextColor(this.b.getResources().getColor(R.color.a_));
        }
        String str = ((ProductTypeStruct) this.c.get(i2)).mSubVersion;
        if (str.equals("PROFESSIONAL")) {
            aVar.d.setText(Html.fromHtml("<font color=#C7A753>PROFESSIONAL</font>"));
        } else if (str.equals("ADVANCED")) {
            aVar.d.setText(Html.fromHtml("<font color=#8C8C8C>ADVANCED</font>"));
        } else if (str.equals("STANDARD")) {
            aVar.d.setText(Html.fromHtml("<font color=#CA0000>STANDARD</font>"));
        } else if (str.equals("XT")) {
            aVar.d.setText(Html.fromHtml("<font color=#CA0000>XT</font>"));
        } else if (str.equals("+")) {
            aVar.c.setText(Html.fromHtml("<sup><small>+</small></sup>"));
            aVar.c.setTextColor(this.b.getResources().getColor(R.color.a_));
            aVar.d.setText("");
        } else {
            aVar.d.setText(Html.fromHtml(String.format("<font color=#FF0000>%s</font>", new Object[]{str})));
        }
        if (dji.pilot.publics.e.d.a(str)) {
            aVar.d.go();
        } else {
            aVar.d.show();
        }
        aVar.a(i2);
        return view;
    }
}
