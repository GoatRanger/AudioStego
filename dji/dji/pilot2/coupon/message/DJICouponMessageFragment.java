package dji.pilot2.coupon.message;

import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c$c;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot2.coupon.message.b.a;
import dji.pilot2.coupon.message.b.b;
import dji.pilot2.coupon.model.CouponGiftCardMsg.CardItem;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJITextView;
import java.util.List;

public class DJICouponMessageFragment extends Fragment implements OnClickListener, b {
    private a a;
    private View b;
    private DJIStateImageView c;
    private DJITextView d;
    private View e;
    private DJIStateTextView f;
    private TextView g;
    private RelativeLayout h;
    private ListView i;
    private a j;
    private RelativeLayout k;
    private DJITextView l;
    private DJIImageView m;

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            this.k.setBackgroundColor(getResources().getColor(R.color.om));
        }
        this.a.a(0);
    }

    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.b = layoutInflater.inflate(R.layout.v2_fragment_coupon_message, viewGroup, true);
        b();
        a();
        c();
        return this.b;
    }

    protected void a() {
        this.d.setText(R.string.v2_coupon_message);
        this.f.setText(R.string.v2_coupon_clearmsg);
    }

    protected void b() {
        this.c = (DJIStateImageView) this.b.findViewById(R.id.cib);
        this.d = (DJITextView) this.b.findViewById(R.id.cic);
        this.e = this.b.findViewById(R.id.cid);
        this.f = (DJIStateTextView) this.b.findViewById(R.id.cie);
        this.g = (TextView) this.b.findViewById(R.id.cif);
        this.h = (RelativeLayout) this.b.findViewById(R.id.cih);
        this.i = (ListView) this.b.findViewById(R.id.cnx);
        this.j = new a(getActivity());
        this.k = (RelativeLayout) this.b.findViewById(R.id.cny);
        this.l = (DJITextView) this.b.findViewById(R.id.cnz);
        this.m = (DJIImageView) this.b.findViewById(R.id.cii);
    }

    protected void c() {
        this.c.setOnClickListener(this);
        this.i.setDivider(null);
        this.i.setAdapter(this.j);
        this.f.setOnClickListener(this);
    }

    public void a(boolean z) {
        if (z) {
            this.h.setVisibility(0);
            ((AnimationDrawable) ((ImageView) this.h.findViewById(R.id.cii)).getDrawable()).start();
            return;
        }
        this.h.setVisibility(8);
        AnimationDrawable animationDrawable = (AnimationDrawable) ((ImageView) this.h.findViewById(R.id.cii)).getDrawable();
        if (animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
    }

    public void a(List<CardItem> list) {
        DJILogHelper.getInstance().LOGI("bob", "enter setListData");
        if (this.j != null) {
            this.j.a((List) list);
        }
    }

    public void b(boolean z) {
        if (z) {
            this.j.a();
        }
    }

    public void c(boolean z) {
        this.h.setVisibility(8);
        this.i.setEmptyView(this.l);
    }

    public void onClick(View view) {
        int id = view.getId();
        DJILogHelper.getInstance().LOGI("bob", "onClick id = " + id);
        switch (id) {
            case R.id.cib:
                getActivity().finish();
                return;
            case R.id.cie:
                if (this.a != null) {
                    this.a.a();
                }
                e.b(c$c.s);
                return;
            default:
                return;
        }
    }

    public void a(a aVar) {
        this.a = aVar;
    }
}
