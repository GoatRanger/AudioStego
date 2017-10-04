package dji.pilot2.main.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import dji.pilot2.mine.activity.WebActivity;
import dji.pilot2.mine.activity.WebLanscapeActivity;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;

public class DJILegalAgreement$a extends ClickableSpan {
    String a;
    Context b;
    final /* synthetic */ DJILegalAgreement c;

    public DJILegalAgreement$a(DJILegalAgreement dJILegalAgreement, String str, Context context) {
        this.c = dJILegalAgreement;
        this.a = str;
        this.b = context;
    }

    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setColor(-16776961);
    }

    public void onClick(View view) {
        Intent intent;
        String language = this.c.getResources().getConfiguration().locale.getLanguage();
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            intent = new Intent(this.c, WebActivity.class);
        } else {
            intent = new Intent(this.c, WebLanscapeActivity.class);
        }
        if (language.equals("zh")) {
            intent.putExtra(DJIWebviewFragment.o, "http://djistatic.com/agreement/dji-go-pp-cn.html");
        } else {
            intent.putExtra(DJIWebviewFragment.o, "http://djistatic.com/agreement/dji-go-pp.html");
        }
        this.c.startActivity(intent);
    }
}
