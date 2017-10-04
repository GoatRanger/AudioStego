package dji.pilot2.flymonitor.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.publics.objects.c;
import dji.pilot.publics.widget.DJIScrollTextView;
import dji.pilot2.mine.activity.WebActivity;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class a extends c implements OnClickListener {
    private View a;
    private View b;
    private DJIScrollTextView c;
    private String d;
    private Context e;

    public class a extends ClickableSpan {
        String a;
        String b;
        final /* synthetic */ a c;

        public a(a aVar, String str, String str2) {
            this.c = aVar;
            this.a = str;
            this.b = str2;
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setColor(this.c.e.getResources().getColor(R.color.aw));
            textPaint.setUnderlineText(false);
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.c.e, WebActivity.class);
            intent.putExtra(DJIWebviewFragment.o, this.b);
            this.c.e.startActivity(intent);
        }
    }

    public a(Context context, boolean z) {
        super(context, z);
        this.e = context;
    }

    public a(Context context, int i, boolean z) {
        super(context, i, z);
        this.e = context;
    }

    public a(Context context, int i) {
        super(context, i);
        this.e = context;
    }

    public a(Context context) {
        super(context);
        this.e = context;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_fly_limit_config_dialog);
        b();
    }

    private void b() {
        this.a = findViewById(R.id.sl);
        this.b = findViewById(R.id.cn5);
        this.c = (DJIScrollTextView) findViewById(R.id.cjk);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        c();
    }

    private void c() {
        Pattern compile = Pattern.compile("\\[.*?\\|.*?\\]");
        String[] split = compile.split(this.d, -1);
        Matcher matcher = compile.matcher(this.d);
        this.c.setText(split[0]);
        int i = 1;
        while (i < split.length && matcher.find()) {
            String group = matcher.group();
            String[] split2 = group.substring(1, group.length() - 1).split("\\|");
            CharSequence spannableString = new SpannableString(split2[0]);
            spannableString.setSpan(new a(this, split2[0], split2[1]), 0, split2[0].length(), 33);
            this.c.append(spannableString, 0, split2[0].length());
            int i2 = i + 1;
            this.c.append(split[i]);
            i = i2;
        }
        this.c.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void a(boolean z) {
        if (!z) {
            this.e.getSharedPreferences(dji.pilot2.flymonitor.service.a.a, 4).edit().putBoolean(dji.pilot2.flymonitor.service.a.b, z);
        }
    }

    public void show() {
        a(false);
        super.show();
    }

    public void a(String str) {
        this.d = str;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sl:
            case R.id.cn5:
                dismiss();
                return;
            default:
                return;
        }
    }
}
