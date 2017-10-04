package dji.pilot.usercenter.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.f.c;
import dji.pilot.usercenter.mode.j;
import dji.pilot.usercenter.widget.DJICircleProgressBar;
import dji.pilot.usercenter.widget.DJIRoundImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIUserInfoView extends DJIRelativeLayout {
    private DJIRoundImageView a = null;
    private DJITextView b = null;
    private DJITextView c = null;
    private DJICircleProgressBar d = null;
    private DJITextView e = null;
    private DJITextView f = null;
    private DJITextView g = null;
    private DJITextView h = null;
    private OnClickListener i = null;
    private Bitmap j = null;

    public DJIUserInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getAvatarWidth() {
        return this.a.getWidth();
    }

    public View getAvatarView() {
        return this.a;
    }

    public void setOuterOnClickListener(OnClickListener onClickListener) {
        this.i = onClickListener;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.a = (DJIRoundImageView) findViewById(R.id.c33);
            this.c = (DJITextView) findViewById(R.id.c35);
            this.b = (DJITextView) findViewById(R.id.c34);
            this.d = (DJICircleProgressBar) findViewById(R.id.c37);
            this.e = (DJITextView) findViewById(R.id.c39);
            this.f = (DJITextView) findViewById(R.id.c38);
            this.g = (DJITextView) findViewById(R.id.c3a);
            this.h = (DJITextView) findViewById(R.id.c3_);
            this.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DJIUserInfoView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.i.onClick(view);
                }
            });
        }
    }

    public void updateUserInfo(j jVar) {
        String e = f.getInstance().e();
        if (this.j == null && c.a(e)) {
            this.j = BitmapFactory.decodeFile(e);
        }
        if (this.j != null) {
            this.a.setImageBitmap(this.j);
        }
        this.c.setText(jVar.j);
        this.b.setText(R.string.account_info_junior_pilots);
        this.d.setProgress(10);
        this.e.setText(String.valueOf(jVar.K));
        this.f.setText(String.valueOf(jVar.L));
        this.g.setText(String.valueOf(jVar.I));
        this.h.setText(String.valueOf(jVar.J));
    }

    public void updateUserAvatar(j jVar) {
        if (!(this.j == null || this.j.isRecycled())) {
            this.j.recycle();
        }
        this.j = BitmapFactory.decodeFile(f.getInstance().e());
        this.a.setImageBitmap(this.j);
    }
}
