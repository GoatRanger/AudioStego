package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import dji.pilot.setting.ui.R;

public class LivePlatformSelectView extends ScrollView {
    private LivePlatformView a;
    private LivePlatformView b;
    private LivePlatformView c;
    private LivePlatformView d;

    public LivePlatformSelectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.a = (LivePlatformView) findViewById(R.id.liveshare_platform_facebook);
            this.b = (LivePlatformView) findViewById(R.id.liveshare_platform_youtube);
            this.c = (LivePlatformView) findViewById(R.id.liveshare_platform_weibo);
            this.d = (LivePlatformView) findViewById(R.id.liveshare_platform_custom);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
