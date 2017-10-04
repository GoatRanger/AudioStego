package dji.setting.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import dji.pilot.setting.ui.R;

public class c extends Dialog {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;

    public c(Context context) {
        super(context, R.style.setting_log_dialog);
        a();
    }

    public c(Context context, int i, int i2, int i3, String str, OnClickListener onClickListener, OnClickListener onClickListener2) {
        super(context, R.style.setting_log_dialog);
        a();
        this.b.setText(i);
        this.a.setText(i2);
        this.c.getPaint().setFlags(8);
        this.c.setText(Html.fromHtml("<a href=\"" + str + "\">" + getContext().getString(i3) + "</a>"));
        this.c.setMovementMethod(LinkMovementMethod.getInstance());
        this.d.setOnClickListener(onClickListener);
        this.e.setOnClickListener(onClickListener2);
    }

    private void a() {
        setContentView(R.layout.setting_geo_switch_tip_dialog);
        this.b = (TextView) findViewById(R.id.geo_tip_title);
        this.a = (TextView) findViewById(R.id.geo_tip_content);
        this.c = (TextView) findViewById(R.id.geo_tip_link);
        this.d = (TextView) findViewById(R.id.geo_tip_confirm);
        this.e = (TextView) findViewById(R.id.geo_tip_cancel);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        com.dji.frame.c.c.a(getWindow());
    }

    public void show() {
        super.show();
        getWindow().clearFlags(8);
    }

    protected void onCreate(Bundle bundle) {
        getWindow().setFlags(8, 8);
        a(getContext().getResources().getDimensionPixelSize(R.dimen.dp_410), getContext().getResources().getDimensionPixelSize(R.dimen.dp_239), 0, 17, false, false);
    }

    public void a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = i;
        attributes.height = i2;
        attributes.y = i3;
        attributes.flags &= -3;
        attributes.gravity = i4;
        getWindow().setAttributes(attributes);
        setCancelable(z);
        setCanceledOnTouchOutside(z2);
    }
}
