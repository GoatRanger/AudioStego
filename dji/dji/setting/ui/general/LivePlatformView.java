package dji.setting.ui.general;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.apppublic.reflect.AppPublicReflect;
import dji.pilot.setting.ui.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.setting.a.a;
import dji.setting.ui.SettingUIRootView;
import dji.thirdparty.a.c;

public class LivePlatformView extends DJILinearLayout implements OnClickListener {
    private static final String d = "facebook";
    private static final String e = "youtube";
    private static final String f = "weibo";
    private static final String g = "custom";
    private DJIImageView a;
    private DJITextView b;
    private String c = d;
    private Context h;

    public LivePlatformView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.liveshare);
        this.c = obtainStyledAttributes.getString(R.styleable.liveshare_type);
        obtainStyledAttributes.recycle();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a.a((View) this, R.layout.setting_ui_group_general_liveshare_platform);
            this.a = (DJIImageView) findViewById(R.id.liveshare_platform_iv);
            this.b = (DJITextView) findViewById(R.id.liveshare_platform_tv);
            setPlatformType(this.c);
            setOnClickListener(this);
        }
    }

    private void setPlatformType(String str) {
        if (str.equals(d)) {
            this.a.setImageDrawable(getResources().getDrawable(R.drawable.live_logo_fb));
            this.b.setText(R.string.setting_ui_general_platform_facebook);
        } else if (str.equals("youtube")) {
            this.a.setImageDrawable(getResources().getDrawable(R.drawable.live_logo_youtube));
            this.b.setText(R.string.setting_ui_general_platform_youtube);
        } else if (str.equals(f)) {
            this.a.setImageDrawable(getResources().getDrawable(R.drawable.live_logo_wb));
            this.b.setText(R.string.setting_ui_general_platform_weibo);
        } else if (str.equals("custom")) {
            this.a.setImageResource(R.drawable.live_logo_custom);
            this.b.setText(R.string.setting_ui_general_platform_custom);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void onClick(View view) {
        if (this.c.equals(d)) {
            AppPublicReflect.enterLiveShare(this.h, 0);
        } else if (this.c.equals("youtube")) {
            AppPublicReflect.enterYoutubeLive(this.h);
        } else if (this.c.equals(f)) {
            AppPublicReflect.enterLiveShare(this.h, 2);
        } else if (this.c.equals("custom")) {
            AppPublicReflect.enterLiveShare(this.h, 3);
        }
        c.a().e(SettingUIRootView.a.CloseBtnClick);
    }
}
