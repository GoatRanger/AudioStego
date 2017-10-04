package dji.pilot2.coupon.couponmain;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.usercenter.b.f;
import dji.pilot2.coupon.model.CouponGiftCards;
import dji.pilot2.coupon.model.CouponGiftCards.GiftItem;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class a extends BaseAdapter {
    private List<a> a = new ArrayList();
    private Context b;
    private LayoutInflater c;
    private c d;
    private boolean e = false;
    private ImageView f;

    public static class a {
        public GiftItem a;
        public String b = "fssf";
        public String c = "XXXX";
        public String d = "bob";
        public String e = "$ 500";
        public String f = "";
        public String g;
    }

    final class b {
        DJITextView a;
        DJITextView b;
        DJITextView c;
        DJITextView d;
        DJIImageView e;
        final /* synthetic */ a f;

        b(a aVar) {
            this.f = aVar;
        }

        public void a(int i) {
            Object item = this.f.getItem(i);
            if (item instanceof a) {
                a aVar = (a) item;
                this.a.setText(aVar.b);
                this.b.setText(aVar.c);
                this.c.setText(aVar.d);
                this.d.setText(aVar.e);
                if (aVar.g != null && aVar.g.equals("plane")) {
                    this.e.setImageResource(R.drawable.v2_coupon_aircraftpic);
                }
            }
        }
    }

    public enum c {
        ELoading,
        ELoaded
    }

    public a(Context context) {
        this.b = context;
        this.c = LayoutInflater.from(context);
        this.d = c.ELoading;
    }

    public int getCount() {
        if (this.e) {
            if (this.d == c.ELoaded && this.a.size() == 0) {
                return 1;
            }
            return this.a.size();
        } else if (this.d == c.ELoaded && this.a.size() == 0) {
            return 1;
        } else {
            return this.a.size();
        }
    }

    public Object getItem(int i) {
        if (this.e) {
            if (i == this.a.size()) {
                return new Object();
            }
            return this.a.get(i);
        } else if (this.a.size() == 0 && i == 0) {
            return new Object();
        } else {
            if (i >= this.a.size()) {
                return null;
            }
            return this.a.get(i);
        }
    }

    public String a(int i) {
        Object item = getItem(i);
        if (item instanceof a) {
            return ((a) item).f;
        }
        return "";
    }

    public long getItemId(int i) {
        return (long) i;
    }

    private View a(int i, View view, ViewGroup viewGroup) {
        DJILogHelper.getInstance().LOGI("bob", "doInModeLoading mDatas.size()=" + this.a.size());
        return view;
    }

    private View b() {
        View inflate = this.c.inflate(R.layout.v2_coupon_main_childitemlast1, null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.cj8);
        ((AnimationDrawable) imageView.getBackground()).start();
        this.f = imageView;
        return inflate;
    }

    public void a(c cVar) {
        if (cVar != this.d) {
            if (this.d == c.ELoading && this.f != null) {
                ((AnimationDrawable) this.f.getBackground()).stop();
            }
            this.d = cVar;
            if (this.d == c.ELoading) {
                this.a.clear();
            }
            notifyDataSetChanged();
        }
    }

    private View b(int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view != null && view.getTag() != null && (view.getTag() instanceof b)) {
            bVar = (b) view.getTag();
            if (i == this.a.size() && i == 0) {
                view = a();
                bVar = null;
            }
        } else if (i == this.a.size() && this.a.size() == 0) {
            view = a();
            bVar = null;
        } else {
            view = this.c.inflate(R.layout.v2_coupon_main_childitem, null);
            b bVar2 = new b(this);
            bVar2.a = (DJITextView) view.findViewById(R.id.cj1);
            bVar2.b = (DJITextView) view.findViewById(R.id.cj2);
            bVar2.c = (DJITextView) view.findViewById(R.id.cj5);
            bVar2.e = (DJIImageView) view.findViewById(R.id.cj0);
            bVar2.d = (DJITextView) view.findViewById(R.id.cj3);
            view.setTag(bVar2);
            bVar = bVar2;
        }
        if (bVar != null) {
            bVar.a(i);
        }
        return view;
    }

    public View a() {
        return this.c.inflate(R.layout.v2_coupon_empty, null);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        DJILogHelper.getInstance().LOGI("bob", "getView positon=" + i);
        if (this.d == c.ELoaded) {
            return b(i, view, viewGroup);
        }
        return a(i, view, viewGroup);
    }

    public void a(boolean z) {
        this.e = z;
    }

    public boolean isEnabled(int i) {
        if (i == this.a.size() && i == 0) {
            return false;
        }
        return super.isEnabled(i);
    }

    public void a(CouponGiftCards couponGiftCards) {
        DJILogHelper.getInstance().LOGI("bob", "enter setmDatas");
        List arrayList = new ArrayList();
        String m = f.getInstance().m();
        for (int i = 0; i < couponGiftCards.data.giftcards.size(); i++) {
            GiftItem giftItem = (GiftItem) couponGiftCards.data.giftcards.get(i);
            a aVar = new a();
            DJILogHelper.getInstance().LOGI("bob", "setmDatas mcardid=" + giftItem.code);
            aVar.f = giftItem.code;
            aVar.d = m;
            a(giftItem.discount_params.currency, this.b);
            if (giftItem.discount_params != null) {
                aVar.e = giftItem.discount_params.reduce_cost_format;
            } else {
                aVar.e = "";
            }
            aVar.b = giftItem.title;
            aVar.c = giftItem.subtitle;
            aVar.g = giftItem.type;
            aVar.a = giftItem;
            arrayList.add(aVar);
        }
        this.a = arrayList;
        this.d = c.ELoaded;
        notifyDataSetChanged();
    }

    public static String a(String str, Context context) {
        try {
            return Currency.getInstance(str.toUpperCase()).getSymbol(context.getResources().getConfiguration().locale);
        } catch (IllegalArgumentException e) {
            String str2 = "";
            DJILogHelper.getInstance().LOGI("bob", "illegal currency code !");
            return str2;
        }
    }
}
