package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import dji.pilot.set.R;
import dji.pilot.set.f;
import dji.pilot.set.longan.SetActivity.a;
import dji.thirdparty.a.c;

public class LiveSelectGroupView extends ScrollView implements OnClickListener {
    private ImageView a;
    private ImageView b;
    private ImageView c;
    private ImageView d;
    private Context e;

    public LiveSelectGroupView(Context context) {
        super(context);
        this.e = context;
    }

    public LiveSelectGroupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = context;
    }

    public LiveSelectGroupView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = context;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.a = (ImageView) findViewById(R.id.live_platform_facebook_iv);
            this.a.setOnClickListener(this);
            this.b = (ImageView) findViewById(R.id.live_platform_youtube_iv);
            this.b.setOnClickListener(this);
            this.c = (ImageView) findViewById(R.id.live_platform_weibo_iv);
            this.c.setOnClickListener(this);
            this.d = (ImageView) findViewById(R.id.live_platform_custom_iv);
            this.d.setOnClickListener(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.live_platform_facebook_iv) {
            c.a().e(a.CLOSE);
            f.a(0);
        }
        if (id == R.id.live_platform_youtube_iv) {
            c.a().e(a.CLOSE);
            f.a();
        }
        if (id == R.id.live_platform_weibo_iv) {
            c.a().e(a.CLOSE);
            f.a(2);
        }
        if (id == R.id.live_platform_custom_iv) {
            c.a().e(a.CLOSE);
            f.a(3);
        }
    }
}
