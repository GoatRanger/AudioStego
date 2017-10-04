package dji.pilot.liveshare.base.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import dji.pilot.R;
import dji.pilot.liveshare.base.widget.LiveSelectRadioButton;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.setting.a.a;

public abstract class LiveBasePublicView extends DJILinearLayout implements OnClickListener {
    protected static final String TAG = LiveBasePublicView.class.getName();
    protected String lastShareType;
    protected Activity mActivity;
    protected ImageButton mBackBtn;
    protected DJITextView mDoneBtn;
    protected LiveSelectRadioButton mFriendBtn;
    protected LiveSelectRadioButton mPrivateBtn;
    protected LiveSelectRadioButton mPublicBtn;
    protected String shareType;

    public LiveBasePublicView(Activity activity) {
        this(activity, null);
        this.mActivity = activity;
    }

    public LiveBasePublicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.shareType = "public";
        init();
    }

    private void init() {
        a.a((View) this, (int) R.layout.fpv_liveshare_base_public);
        if (!isInEditMode()) {
            this.mBackBtn = (ImageButton) findViewById(R.id.a5v);
            this.mDoneBtn = (DJITextView) findViewById(R.id.a5w);
            this.mPublicBtn = (LiveSelectRadioButton) findViewById(R.id.a5x);
            this.mFriendBtn = (LiveSelectRadioButton) findViewById(R.id.a5y);
            this.mPrivateBtn = (LiveSelectRadioButton) findViewById(R.id.a5z);
            this.mBackBtn.setOnClickListener(this);
            this.mDoneBtn.setOnClickListener(this);
            this.mPublicBtn.setOnClickListener(this);
            this.mFriendBtn.setOnClickListener(this);
            this.mPrivateBtn.setOnClickListener(this);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.lastShareType = dji.pilot.f.a.a.E;
        Log.d(TAG, "onAttachedToWindow");
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClick(android.view.View r3) {
        /*
        r2 = this;
        r0 = r3.getId();
        switch(r0) {
            case 2131362995: goto L_0x001e;
            case 2131362996: goto L_0x0022;
            case 2131362997: goto L_0x0030;
            case 2131362998: goto L_0x0035;
            case 2131362999: goto L_0x003a;
            default: goto L_0x0007;
        };
    L_0x0007:
        r0 = r2.shareType;
        dji.pilot.f.a.a.E = r0;
        r0 = new dji.pilot.f.a.a;
        r1 = 257; // 0x101 float:3.6E-43 double:1.27E-321;
        r0.<init>(r1);
        r1 = r2.shareType;
        r0.J = r1;
        r1 = dji.thirdparty.a.c.a();
        r1.e(r0);
    L_0x001d:
        return;
    L_0x001e:
        r0 = r2.lastShareType;
        dji.pilot.f.a.a.E = r0;
    L_0x0022:
        r0 = new dji.pilot.f.a.a;
        r1 = 4;
        r0.<init>(r1);
        r1 = dji.thirdparty.a.c.a();
        r1.e(r0);
        goto L_0x001d;
    L_0x0030:
        r0 = "public";
        r2.shareType = r0;
        goto L_0x0007;
    L_0x0035:
        r0 = "friend";
        r2.shareType = r0;
        goto L_0x0007;
    L_0x003a:
        r0 = "private";
        r2.shareType = r0;
        goto L_0x0007;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.liveshare.base.view.LiveBasePublicView.onClick(android.view.View):void");
    }
}
