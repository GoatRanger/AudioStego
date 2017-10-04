package dji.pilot2.coupon;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c$c;
import dji.pilot.fpv.d.e;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.publics.b.a$e;
import dji.pilot2.utils.k;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.thirdparty.a.c;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Stack;

public class ExpandView extends FrameLayout implements OnClickListener {
    private Animation a;
    private Animation b;
    private boolean c;
    private dji.pilot2.share.mode.b[] d;
    private LinearLayout e;
    private a f;
    private b g;

    public static class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
    }

    public interface b {
        void a();
    }

    public ExpandView(Context context) {
        this(context, null);
    }

    public ExpandView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ExpandView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        if (isInEditMode()) {
            LayoutInflater.from(getContext()).inflate(R.layout.v2_giftshare_dialog, this, true);
        } else {
            setContentView();
        }
        this.a = AnimationUtils.loadAnimation(getContext(), R.anim.bx);
        this.a.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ ExpandView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.setVisibility(0);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.b = AnimationUtils.loadAnimation(getContext(), R.anim.bw);
        this.b.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ ExpandView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.setVisibility(8);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public void collapse() {
        if (this.c) {
            this.c = false;
            clearAnimation();
            startAnimation(this.b);
            if (this.g != null) {
                this.g.a();
            }
        }
    }

    public void expand() {
        if (!this.c) {
            this.c = true;
            clearAnimation();
            startAnimation(this.a);
        }
    }

    public boolean isExpand() {
        return this.c;
    }

    public void setContentView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.v2_giftshare_dialog, null);
        removeAllViews();
        addView(inflate);
        b();
    }

    private void b() {
        if (getContext().getResources().getConfiguration().locale.getCountry().equalsIgnoreCase("CN")) {
            this.d = dji.pilot2.share.mode.b.i;
        } else {
            this.d = dji.pilot2.share.mode.b.j;
        }
        c();
        findViewById(R.id.cou).setOnClickListener(this);
    }

    private void c() {
        int i;
        this.e = (LinearLayout) findViewById(R.id.cow);
        DJIDeviceType deviceType = DJIOriLayout.getDeviceType();
        if (deviceType == DJIDeviceType.Pad) {
            i = 4;
        } else {
            i = 3;
        }
        int i2 = 0;
        View view = null;
        while (i2 < this.d.length) {
            LayoutInflater from = LayoutInflater.from(getContext());
            if (i2 % i == 0) {
                view = new LinearLayout(getContext());
                view.setOrientation(0);
                view.setLayoutParams(new LayoutParams(-1, -2, 3.0f));
                view.setWeightSum((float) i);
                this.e.addView(view);
            }
            View view2 = view;
            View inflate = from.inflate(R.layout.v2_coupon_share, null);
            ((ImageView) inflate.findViewById(R.id.cjd)).setImageResource(this.d[i2].b);
            inflate.setTag(this.d[i2]);
            inflate.setOnClickListener(this);
            inflate.setLayoutParams(new LayoutParams(0, -2, 1.0f));
            if (deviceType == DJIDeviceType.Phone) {
                view2.addView(inflate);
            } else {
                view2.addView(inflate);
            }
            i2++;
            view = view2;
        }
    }

    public void setOnCollapseListener(b bVar) {
        this.g = bVar;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.cou) {
            collapse();
            return;
        }
        dji.pilot2.share.mode.b bVar = (dji.pilot2.share.mode.b) view.getTag();
        DJILogHelper.getInstance().LOGI("bob", "share info mShareInfo.mTitle=" + this.f.a + " mShareInfo.mShareDesc=" + this.f.b + " mShareInfo.mShareDetailUrl=" + this.f.d + " mShareInfo.mShareDetailUrl= " + this.f.d);
        if (bVar != null && (bVar instanceof dji.pilot2.share.mode.b)) {
            if (bVar.d == dji.pilot2.share.e.a.b.PLATFORM_TYPE_MAIL) {
                g();
            } else if (bVar.d == dji.pilot2.share.e.a.b.PLATFORM_TYPE_SMS) {
                String str = "";
                if (this.f != null) {
                    str = this.f.b + this.f.d;
                }
                Uri parse = Uri.parse("smsto:");
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.putExtra("sms_body", str);
                intent.setData(parse);
                if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                    getContext().startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "need download a sms client", 0).show();
                }
                e.b(c$c.l);
            } else if (bVar.d == dji.pilot2.share.e.a.b.PLATFORM_TYPE_FACKBOOK) {
                if (this.f != null) {
                    c.a().e(this.f);
                    DJILogHelper.getInstance().LOGI("bob", "mShareInfo");
                }
            } else if (this.f != null) {
                String e;
                if (bVar.d == dji.pilot2.share.e.a.b.PLATFORM_TYPE_TWITTER) {
                    e = e();
                } else {
                    e = d();
                }
                DJILogHelper.getInstance().LOGI("bob", "resultPath=" + e);
                if (bVar.d.equals(dji.pilot2.share.e.a.b.PLATFORM_TYPE_WECHAT_MOMENTS) || bVar.d.equals(dji.pilot2.share.e.a.b.PLATFORM_TYPE_WECHAT)) {
                    dji.pilot2.share.f.b.a(getContext(), this.f.a, this.f.b, e, this.f.d, bVar.d, dji.pilot2.share.e.a.a.CONTENT_LINK_ADDR, dji.pilot2.share.b.b.a.GIFT_SHARE);
                } else {
                    dji.pilot2.share.f.b.a(getContext(), this.f.a, this.f.b + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.f.d, e, this.f.d, bVar.d, dji.pilot2.share.e.a.a.CONTENT_LINK_ADDR, dji.pilot2.share.b.b.a.GIFT_SHARE);
                }
            }
        }
    }

    private String d() {
        String str = (dji.pilot2.b.a.a().getExternalCacheDir() + d.t) + "couponshareattachment.jpg";
        if (!new File(str).exists()) {
            OutputStream fileOutputStream;
            Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.v2_coupon_giftcard_share_attachment);
            try {
                fileOutputStream = new FileOutputStream(str);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                fileOutputStream = null;
                str = null;
            }
            if (fileOutputStream != null) {
                decodeResource.compress(CompressFormat.JPEG, 80, fileOutputStream);
            }
        }
        return str;
    }

    private String e() {
        String str = (dji.pilot2.b.a.a().getExternalCacheDir() + d.t) + "coupontwitterttachment.jpg";
        if (!new File(str).exists()) {
            OutputStream fileOutputStream;
            Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.v2_coupon_bg_header);
            try {
                fileOutputStream = new FileOutputStream(str);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                fileOutputStream = null;
                str = null;
            }
            if (fileOutputStream != null) {
                decodeResource.compress(CompressFormat.JPEG, 80, fileOutputStream);
            }
        }
        return str;
    }

    private void f() {
        if (this.f != null) {
            Intent intent = new Intent("android.intent.action.SENDTO");
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra("android.intent.extra.TEXT", this.f.b);
            intent.putExtra("android.intent.extra.SUBJECT", this.f.a);
            Intent.createChooser(intent, "Choose Email Client");
            if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                getContext().startActivity(intent);
            } else {
                Toast.makeText(getContext(), "need download a email client", 0).show();
            }
        }
    }

    public Intent createEmailOnlyChooserIntent(Intent intent, CharSequence charSequence) {
        Stack stack = new Stack();
        for (ResolveInfo resolveInfo : getContext().getPackageManager().queryIntentActivities(new Intent("android.intent.action.SENDTO", Uri.parse("mailto:")), 0)) {
            Intent intent2 = new Intent(intent);
            intent2.setPackage(resolveInfo.activityInfo.packageName);
            stack.add(intent2);
        }
        if (stack.isEmpty()) {
            return Intent.createChooser(intent, charSequence);
        }
        DJILogHelper.getInstance().LOGI("bob", "createEmailOnlyChooserIntent");
        Intent createChooser = Intent.createChooser((Intent) stack.remove(0), charSequence);
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) stack.toArray(new Parcelable[stack.size()]));
        return createChooser;
    }

    private void g() {
        if (this.f != null) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.TEXT", this.f.b + this.f.d);
            intent.putExtra("android.intent.extra.SUBJECT", this.f.a);
            intent.putExtra("android.intent.extra.EMAIL", "");
            if (this.f.c != null) {
                DJILogHelper.getInstance().LOGI("bob", "mShareInfo.mThumbnailPath = " + this.f.c);
                intent.putExtra("android.intent.extra.STREAM", Uri.parse(a$e.a + this.f.c));
            }
            intent.setType("*/*");
            if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                intent = createEmailOnlyChooserIntent(intent, "Choose Email Client");
                if (intent != null) {
                    getContext().startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "need download a email client", 0).show();
                }
            } else {
                Toast.makeText(getContext(), "need download a email client", 0).show();
            }
            e.b(c$c.k);
        }
    }

    public void genShareInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        if (this.f == null) {
            this.f = new a();
        }
        this.f.a = str5;
        this.f.b = str6;
        this.f.d = k.j(str3);
        this.f.c = str4;
        this.f.e = str7;
    }
}
