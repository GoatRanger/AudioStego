package dji.pilot2.coupon.coupondetail;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dji.frame.c.d;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.f;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c$c;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.usercenter.widget.DJICircleImageView;
import dji.pilot2.academy.activity.DJIAcademyWebViewActivity;
import dji.pilot2.coupon.ExpandView;
import dji.pilot2.coupon.coupondetail.a.a;
import dji.pilot2.coupon.coupondetail.a.b;
import dji.pilot2.coupon.model.CouponGiftCardDetail;
import dji.pilot2.coupon.model.CouponGiftCardDetail.Data;
import dji.pilot2.utils.k;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DJICouponDetailFragment extends Fragment implements OnClickListener, b {
    private a a;
    private View b;
    private DJIStateImageView c;
    private DJITextView d;
    private View e;
    private DJIStateTextView f;
    private TextView g;
    private View h;
    private View i;
    private RelativeLayout j;
    private DJIStateTextView k;
    private DJIStateTextView l;
    private DJITextView m;
    private DJICircleImageView n;
    private DJITextView o;
    private DJITextView p;
    private ExpandView q;
    private DJITextView r;
    private DJITextView s;
    private DJITextView t;
    private DJITextView u;
    private DJITextView v;
    private String w;

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
        this.w = ((DJICouponDetailActivity) getActivity()).a();
        DJILogHelper.getInstance().LOGI("bob", "onResume mCardCode = " + this.w);
        this.a.a(this.w);
        c.a().a(this);
    }

    public void onPause() {
        super.onPause();
        c.a().d(this);
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onEventMainThread(ExpandView.a aVar) {
        if (f.a(ShareLinkContent.class)) {
            String str = Environment.getExternalStorageDirectory() + "/DJI/";
            DJILogHelper.getInstance().LOGI("bob", "shareFacebook coupon card appDir=" + str);
            String str2 = str + "couponshareattachment.jpg";
            if (!new File(str2).exists()) {
                OutputStream fileOutputStream;
                Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.v2_coupon_giftcard_share_attachment);
                try {
                    fileOutputStream = new FileOutputStream(str2);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    fileOutputStream = null;
                }
                if (fileOutputStream != null) {
                    decodeResource.compress(CompressFormat.JPEG, 80, fileOutputStream);
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            new f(this).b(((ShareLinkContent.a) new ShareLinkContent.a().b(aVar.a).a(aVar.b + aVar.d).a(Uri.parse(aVar.d))).b());
            e.b(c$c.f);
            DJILogHelper.getInstance().LOGI("bob", "shareFacebook coupon card");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.w = ((DJICouponDetailActivity) getActivity()).a();
        DJILogHelper.getInstance().LOGI("bob", "onCreateView mCardCode = " + this.w);
        this.b = layoutInflater.inflate(R.layout.v2_fragment_coupon_detail, viewGroup, true);
        b();
        c();
        a();
        return this.b;
    }

    protected void a() {
        this.d.setVisibility(4);
        this.f.setVisibility(4);
        this.h.setBackgroundResource(R.color.j5);
        this.i.setVisibility(4);
        this.c.setImageResource(R.drawable.v2_mine_back_pic);
    }

    protected void b() {
        this.c = (DJIStateImageView) this.b.findViewById(R.id.cib);
        this.d = (DJITextView) this.b.findViewById(R.id.cic);
        this.e = this.b.findViewById(R.id.cid);
        this.f = (DJIStateTextView) this.b.findViewById(R.id.cie);
        this.g = (TextView) this.b.findViewById(R.id.cif);
        this.h = this.b.findViewById(R.id.cnt);
        this.i = this.b.findViewById(R.id.cig);
        this.j = (RelativeLayout) this.b.findViewById(R.id.cih);
        this.k = (DJIStateTextView) this.b.findViewById(R.id.ciu);
        this.l = (DJIStateTextView) this.b.findViewById(R.id.cix);
        this.m = (DJITextView) this.b.findViewById(R.id.cip);
        this.m.setTextIsSelectable(true);
        this.n = (DJICircleImageView) this.b.findViewById(R.id.ciq);
        this.o = (DJITextView) this.b.findViewById(R.id.cir);
        this.p = (DJITextView) this.b.findViewById(R.id.cim);
        this.q = (ExpandView) this.b.findViewById(R.id.ciy);
        this.t = (DJITextView) this.b.findViewById(R.id.cik);
        this.u = (DJITextView) this.b.findViewById(R.id.cil);
        this.r = (DJITextView) this.b.findViewById(R.id.cit);
        this.s = (DJITextView) this.b.findViewById(R.id.cin);
        this.v = (DJITextView) this.b.findViewById(R.id.cnv);
        this.e.setVisibility(8);
    }

    protected void c() {
        this.c.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void a(boolean z) {
        if (z) {
            this.j.setVisibility(0);
            ((AnimationDrawable) ((ImageView) this.j.findViewById(R.id.cii)).getDrawable()).start();
            return;
        }
        this.j.setVisibility(8);
        AnimationDrawable animationDrawable = (AnimationDrawable) ((ImageView) this.j.findViewById(R.id.cii)).getDrawable();
        if (animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
    }

    protected String a(String str) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        if (date != null) {
            return simpleDateFormat.format(date);
        }
        return "";
    }

    public void a(CouponGiftCardDetail couponGiftCardDetail) {
        if (isAdded()) {
            Data data = couponGiftCardDetail.data;
            this.r.setText(a(data.start_at) + "-" + a(data.end_at));
            this.t.setText(data.title);
            this.u.setText(data.subtitle);
            this.s.setText(data.discount_params.desc);
            dji.pilot2.coupon.couponmain.a.a(data.discount_params.currency, getActivity());
            Object obj = couponGiftCardDetail.data.discount_params.reduce_cost_format;
            this.p.setText(obj);
            this.o.setText(dji.pilot.usercenter.b.f.getInstance().m());
            String str = dji.pilot.usercenter.b.f.getInstance().f().l;
            DJILogHelper.getInstance().LOGI("bob", "avatar url = " + str);
            ImageLoader.getInstance().displayImage(str, this.n, new Builder().showImageOnLoading(R.drawable.v2_avatar_default).cacheInMemory(true).cacheOnDisc(false).build());
            if (data.code != null) {
                this.m.setText(data.code);
            }
            this.w = data.code;
            str = dji.pilot.usercenter.b.f.getInstance().e();
            dji.pilot.usercenter.b.f.getInstance().m();
            String str2 = dji.pilot.usercenter.b.f.getInstance().h().l;
            DisplayImageOptions build = new Builder().showImageOnLoading(R.drawable.v2_avatar_default).cacheInMemory(true).cacheOnDisc(false).build();
            if (str != null && !str.equals("")) {
                File file = new File(str);
                if (file.exists() && file.isFile()) {
                    this.n.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                } else if (str2 == null || str2.equals("")) {
                    this.n.setImageDrawable(getResources().getDrawable(R.drawable.v2_avatar_default));
                } else {
                    ImageLoader.getInstance().displayImage(str2, this.n, build);
                }
            } else if (str2 == null || str2.equals("")) {
                this.n.setImageDrawable(getResources().getDrawable(R.drawable.v2_avatar_default));
            } else {
                ImageLoader.getInstance().displayImage(str2, this.n, build);
            }
            this.q.genShareInfo(dji.pilot.usercenter.b.f.getInstance().m(), obj, this.w, b(couponGiftCardDetail), data.share_title, data.share_desc, data.share_sms);
            this.q.setOnCollapseListener(new ExpandView.b(this) {
                final /* synthetic */ DJICouponDetailFragment a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.l.setVisibility(0);
                }
            });
        }
    }

    public void b(boolean z) {
        if (z) {
            this.v.setVisibility(0);
        } else {
            this.v.setVisibility(8);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        DJILogHelper.getInstance().LOGI("bob", "onClick id = " + id);
        switch (id) {
            case R.id.cib:
                getActivity().finish();
                return;
            case R.id.ciu:
                String a = k.a(this.w, DJIOriLayout.getDeviceType());
                Intent intent = new Intent(getActivity(), DJIAcademyWebViewActivity.class);
                intent.putExtra(DJIWebviewFragment.o, a);
                startActivity(intent);
                e.b(c$c.c);
                return;
            case R.id.cix:
                e.b(c$c.d);
                if (this.q.getVisibility() == 8 || this.q.getVisibility() == 4) {
                    this.q.setVisibility(0);
                    this.q.expand();
                    return;
                }
                this.q.collapse();
                return;
            default:
                return;
        }
    }

    public String a(String str, String str2, String str3, String str4) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.v2_coupon_main_childitem, null);
        DJITextView dJITextView = (DJITextView) inflate.findViewById(R.id.cj2);
        DJITextView dJITextView2 = (DJITextView) inflate.findViewById(R.id.cj5);
        DJITextView dJITextView3 = (DJITextView) inflate.findViewById(R.id.cj3);
        ((DJITextView) inflate.findViewById(R.id.cj1)).setText(str);
        dJITextView.setText(str2);
        dJITextView2.setText(str3);
        dJITextView3.setText(str4);
        inflate.setDrawingCacheEnabled(false);
        inflate.measure(MeasureSpec.makeMeasureSpec(960, 1073741824), MeasureSpec.makeMeasureSpec(400, 1073741824));
        inflate.layout(0, 0, inflate.getMeasuredWidth(), inflate.getMeasuredHeight());
        inflate.buildDrawingCache();
        return a(inflate.getDrawingCache());
    }

    public String b(CouponGiftCardDetail couponGiftCardDetail) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.v2_coupon_mailpic, null);
        DJIStateTextView dJIStateTextView = (DJIStateTextView) inflate.findViewById(R.id.ciu);
        dJIStateTextView = (DJIStateTextView) inflate.findViewById(R.id.cix);
        inflate.findViewById(R.id.ciu).setVisibility(8);
        DJITextView dJITextView = (DJITextView) inflate.findViewById(R.id.cip);
        dJITextView.setTextIsSelectable(true);
        DJICircleImageView dJICircleImageView = (DJICircleImageView) inflate.findViewById(R.id.ciq);
        DJITextView dJITextView2 = (DJITextView) inflate.findViewById(R.id.cir);
        DJITextView dJITextView3 = (DJITextView) inflate.findViewById(R.id.cim);
        inflate.findViewById(R.id.civ).setVisibility(8);
        DJITextView dJITextView4 = (DJITextView) inflate.findViewById(R.id.cik);
        DJITextView dJITextView5 = (DJITextView) inflate.findViewById(R.id.cil);
        DJITextView dJITextView6 = (DJITextView) inflate.findViewById(R.id.cit);
        DJITextView dJITextView7 = (DJITextView) inflate.findViewById(R.id.cin);
        Data data = couponGiftCardDetail.data;
        dJITextView6.setText(a(data.start_at) + "-" + a(data.end_at));
        dJITextView4.setText(data.title);
        dJITextView5.setText(data.subtitle);
        if (data.discount_params != null) {
            dJITextView7.setText(data.discount_params.desc);
        }
        dJITextView3.setText(couponGiftCardDetail.data.discount_params.reduce_cost_format);
        dJITextView2.setText(dji.pilot.usercenter.b.f.getInstance().m());
        if (data.code != null) {
            dJITextView.setText(data.code);
        }
        String e = dji.pilot.usercenter.b.f.getInstance().e();
        dji.pilot.usercenter.b.f.getInstance().m();
        String str = dji.pilot.usercenter.b.f.getInstance().h().l;
        DisplayImageOptions build = new Builder().showImageOnLoading(R.drawable.v2_avatar_default).cacheInMemory(true).cacheOnDisc(false).build();
        if (e != null && !e.equals("")) {
            File file = new File(e);
            if (file.exists() && file.isFile()) {
                dJICircleImageView.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
            } else if (str == null || str.equals("")) {
                dJICircleImageView.setImageDrawable(getResources().getDrawable(R.drawable.v2_avatar_default));
            } else {
                ImageLoader.getInstance().displayImage(str, this.n, build);
            }
        } else if (str == null || str.equals("")) {
            dJICircleImageView.setImageDrawable(getResources().getDrawable(R.drawable.v2_avatar_default));
        } else {
            ImageLoader.getInstance().displayImage(str, dJICircleImageView, build);
        }
        inflate.setDrawingCacheEnabled(true);
        inflate.measure(MeasureSpec.makeMeasureSpec(900, 1073741824), MeasureSpec.makeMeasureSpec(1260, 1073741824));
        inflate.layout(0, 0, inflate.getMeasuredWidth(), inflate.getMeasuredHeight());
        inflate.buildDrawingCache();
        Bitmap drawingCache = inflate.getDrawingCache();
        if (drawingCache == null) {
            return null;
        }
        return a(drawingCache);
    }

    public String a(Bitmap bitmap) {
        OutputStream fileOutputStream;
        String a = d.a(getActivity(), "/CACHE_IMAGE/");
        File file = new File(a);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(a + "card.png");
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileOutputStream = new FileOutputStream(file2);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            fileOutputStream = null;
        }
        bitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
        try {
            fileOutputStream.flush();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        try {
            fileOutputStream.close();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return file2.getAbsolutePath();
    }
}
