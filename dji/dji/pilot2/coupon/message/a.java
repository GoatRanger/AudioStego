package dji.pilot2.coupon.message;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot2.coupon.model.CouponGiftCardMsg.CardItem;
import dji.publics.DJIUI.DJIImageView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class a extends BaseAdapter {
    private List<a> a = new ArrayList();
    private Context b;
    private LayoutInflater c;

    public static class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;

        public static String a(String str, Context context) {
            String format = String.format(context.getResources().getString(R.string.v2_coupon_msg_txt1), new Object[]{str});
            return "" + format + context.getResources().getString(R.string.v2_coupon_msg_txt_expired);
        }
    }

    final class b {
        public DJIImageView a;
        public DJIStateTextView b;
        public DJIStateTextView c;
        final /* synthetic */ a d;

        b(a aVar) {
            this.d = aVar;
        }

        public void a(int i) {
            a aVar = (a) this.d.getItem(i);
            String format;
            if (aVar.d.equals("expired")) {
                format = String.format(this.d.b.getResources().getString(R.string.v2_coupon_msg_txt1), new Object[]{aVar.a});
                this.b.setText("" + format + this.d.b.getResources().getString(R.string.v2_coupon_msg_txt_expired));
            } else if (aVar.d.equals("canceled")) {
                String.format(this.d.b.getResources().getString(R.string.v2_coupon_msg_txt1), new Object[]{aVar.a});
                format = String.format(this.d.b.getResources().getString(R.string.v2_coupon_msg_txt_canceld), new Object[]{aVar.b});
                format.lastIndexOf(aVar.b);
                CharSequence substring = format.substring(aVar.b.length(), format.length());
                r2 = new SpannableString(aVar.b);
                r2.setSpan(new ForegroundColorSpan(Color.parseColor("#9B9B9B")), 0, r2.length(), 33);
                this.b.setText(r2);
                this.b.append(substring);
            } else if (aVar.d.equals("paid")) {
                format = String.format(this.d.b.getResources().getString(R.string.v2_coupon_msg_txt1), new Object[]{aVar.a});
                if (aVar.b == null || aVar.b.equals("")) {
                    this.b.setText("" + format + this.d.b.getResources().getString(R.string.v2_coupon_msg_txt_paid));
                    if (aVar.c != null && Integer.valueOf(aVar.c).intValue() > 0) {
                        r2 = new SpannableString(String.format(this.d.b.getResources().getString(R.string.v2_coupon_msg_txt_feedback), new Object[]{aVar.c}));
                        r2.setSpan(new ForegroundColorSpan(Color.parseColor("#9B9B9B")), 0, r2.length(), 33);
                        this.b.append(r2);
                    }
                } else {
                    r2 = aVar.b;
                    r3 = String.format(this.d.b.getResources().getString(R.string.v2_coupon_msg_txt_paid_by), new Object[]{r2});
                    r4 = r3.lastIndexOf(r2);
                    r5 = r3.substring(0, r4);
                    r3 = r3.substring(r4 + r2.length(), r3.length());
                    r4 = new SpannableString(r2);
                    r4.setSpan(new ForegroundColorSpan(Color.parseColor("#9B9B9B")), 0, r4.length(), 33);
                    this.b.setText("" + format + r5);
                    this.b.append(r4);
                    this.b.append(r3);
                    if (aVar.c != null) {
                        r2 = new SpannableString(String.format(this.d.b.getResources().getString(R.string.v2_coupon_msg_txt_feedback), new Object[]{aVar.c}));
                        r2.setSpan(new ForegroundColorSpan(Color.parseColor("#9B9B9B")), 0, r2.length(), 33);
                        this.b.append(r2);
                    }
                }
            } else if (aVar.d.equals("used")) {
                format = String.format(this.d.b.getResources().getString(R.string.v2_coupon_msg_txt1), new Object[]{aVar.a});
                this.d.b.getResources().getString(R.string.v2_coupon_msg_txt_expired);
                r2 = aVar.b;
                r3 = String.format(this.d.b.getResources().getString(R.string.v2_coupon_msg_txt_usedby), new Object[]{r2});
                r4 = r3.lastIndexOf(r2);
                r5 = r3.substring(0, r4);
                r3 = r3.substring(r4 + r2.length(), r3.length());
                r4 = new SpannableString(r2);
                r4.setSpan(new ForegroundColorSpan(Color.parseColor("#9B9B9B")), 0, r4.length(), 33);
                this.b.setText("" + format + r5);
                this.b.append(r4);
                this.b.append(r3);
            } else {
                this.b.setText("" + String.format(this.d.b.getResources().getString(R.string.v2_coupon_msg_txt1), new Object[]{aVar.a}));
            }
            this.c.setText(aVar.e);
        }
    }

    public a(Context context) {
        this.b = context;
        this.c = LayoutInflater.from(context);
    }

    public void a() {
        this.a.clear();
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        if (i >= this.a.size()) {
            return null;
        }
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null || view.getTag() == null || !(view.getTag() instanceof b)) {
            view = this.c.inflate(R.layout.v2_coupon_msg_childitem, null);
            b bVar2 = new b(this);
            bVar2.a = (DJIImageView) view.findViewById(R.id.cj0);
            bVar2.b = (DJIStateTextView) view.findViewById(R.id.cjb);
            bVar2.c = (DJIStateTextView) view.findViewById(R.id.cjc);
            view.setTag(bVar2);
            bVar = bVar2;
        } else {
            bVar = (b) view.getTag();
        }
        if (bVar != null) {
            bVar.a(i);
        }
        return view;
    }

    public void a(List<CardItem> list) {
        List arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Date parse;
            a aVar = new a();
            CardItem cardItem = (CardItem) list.get(i);
            aVar.d = cardItem.msg_type;
            aVar.a = cardItem.coupon.discount_params.reduce_cost_format;
            if (cardItem.rebate != null) {
                aVar.c = cardItem.rebate.format;
            }
            aVar.b = cardItem.used_by;
            try {
                parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cardItem.created_at);
            } catch (ParseException e) {
                e.printStackTrace();
                parse = null;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (parse != null) {
                aVar.e = simpleDateFormat.format(parse);
            }
            arrayList.add(aVar);
        }
        this.a = arrayList;
        notifyDataSetChanged();
    }

    public boolean isEnabled(int i) {
        return super.isEnabled(i);
    }
}
