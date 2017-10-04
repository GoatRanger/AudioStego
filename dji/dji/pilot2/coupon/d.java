package dji.pilot2.coupon;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot2.share.mode.b;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import java.util.Stack;

public class d extends dji.pilot2.publics.object.a implements OnClickListener {
    private b[] b;
    private LinearLayout c;
    private a d;

    public static class a {
        public String a;
        public String b;
        public String c;
    }

    public d(Context context) {
        super(context);
    }

    public d(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    public d(Context context, int i) {
        super(context, i);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_giftshare_dialog);
        a();
    }

    private void a() {
        if (this.a.getResources().getConfiguration().locale.getCountry().equals("CN")) {
            this.b = b.i;
        } else {
            this.b = b.j;
        }
        b();
    }

    private void b() {
        this.c = (LinearLayout) findViewById(R.id.cow);
        DJIDeviceType deviceType = DJIOriLayout.getDeviceType();
        if (deviceType == DJIDeviceType.Pad) {
            this.c.setOrientation(0);
        }
        int i = 0;
        View view = null;
        while (i < this.b.length) {
            LayoutInflater from = LayoutInflater.from(this.a);
            if (i % 3 == 0 && deviceType == DJIDeviceType.Phone) {
                view = new LinearLayout(this.a);
                view.setOrientation(0);
                view.setLayoutParams(new LayoutParams(-1, -2, 3.0f));
                this.c.addView(view);
            }
            View view2 = view;
            View inflate = from.inflate(R.layout.v2_share_dialog_item, null);
            TextView textView = (TextView) inflate.findViewById(R.id.czj);
            ((ImageView) inflate.findViewById(R.id.cjd)).setImageResource(this.b[i].c);
            textView.setText(this.b[i].a);
            inflate.setTag(this.b[i]);
            inflate.setOnClickListener(this);
            inflate.setLayoutParams(new LayoutParams(-2, -2, 1.0f));
            if (deviceType == DJIDeviceType.Phone) {
                view2.addView(inflate);
            } else {
                this.c.addView(inflate);
            }
            i++;
            view = view2;
        }
    }

    public void onClick(View view) {
        view.getId();
        b bVar = (b) view.getTag();
        if (bVar != null && (bVar instanceof b)) {
            if (bVar.d == dji.pilot2.share.e.a.b.PLATFORM_TYPE_MAIL) {
                d();
            } else if (bVar.d == dji.pilot2.share.e.a.b.PLATFORM_TYPE_SMS) {
                Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:"));
                intent.putExtra("sms_body", "ssss");
                if (intent.resolveActivity(this.a.getPackageManager()) != null) {
                    this.a.startActivity(intent);
                } else {
                    Toast.makeText(this.a, "need download a sms client", 0).show();
                }
            }
        }
    }

    private void c() {
        if (this.d != null) {
            Intent intent = new Intent("android.intent.action.SENDTO");
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra("android.intent.extra.TEXT", this.d.b);
            intent.putExtra("android.intent.extra.SUBJECT", this.d.a);
            Intent.createChooser(intent, "Choose Email Client");
            if (intent.resolveActivity(this.a.getPackageManager()) != null) {
                this.a.startActivity(intent);
            } else {
                Toast.makeText(this.a, "need download a email client", 0).show();
            }
        }
    }

    public Intent a(Intent intent, CharSequence charSequence) {
        Stack stack = new Stack();
        for (ResolveInfo resolveInfo : this.a.getPackageManager().queryIntentActivities(new Intent("android.intent.action.SENDTO", Uri.parse("mailto:")), 0)) {
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

    private void d() {
        this.d = new a();
        this.d.b = "ddd";
        this.d.a = "title";
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", this.d.b);
        intent.putExtra("android.intent.extra.SUBJECT", this.d.a);
        intent.putExtra("android.intent.extra.EMAIL", "wzyzb@qq.com");
        intent.putExtra("android.intent.extra.STREAM", Uri.parse("file:///mnt/sdcard/DJI/dji.pilot/PhotoEditor/1445327472541.jpg"));
        intent.setType("*/*");
        if (intent.resolveActivity(this.a.getPackageManager()) != null) {
            intent = a(intent, (CharSequence) "Choose Email Client");
            if (intent != null) {
                this.a.startActivity(intent);
                return;
            } else {
                Toast.makeText(this.a, "need download a email client", 0).show();
                return;
            }
        }
        Toast.makeText(this.a, "need download a email client", 0).show();
    }

    public void a(String str, String str2) {
        if (this.d == null) {
            this.d = new a();
        }
        this.d.b = str;
        this.d.c = str2;
    }
}
