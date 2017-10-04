package dji.pilot2.mine.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.pilot.R;

public class MineButton extends RelativeLayout {
    private Context a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private View e;
    private TextView f;

    public MineButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public MineButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public MineButton(Context context) {
        super(context);
        a();
    }

    private void a() {
        View inflate = inflate(getContext(), R.layout.v2_mine_button, null);
        removeAllViews();
        addView(inflate);
        this.b = (ImageView) findViewById(R.id.csg);
        this.c = (TextView) findViewById(R.id.csh);
        this.d = (TextView) findViewById(R.id.csl);
        this.e = findViewById(R.id.csj);
        this.f = (TextView) findViewById(R.id.csk);
    }

    public void setButtonIcon(Bitmap bitmap) {
        this.b.setImageBitmap(bitmap);
    }

    public void setButtonIcon(int i) {
        this.b.setImageResource(i);
    }

    public void setButtonIcon(Drawable drawable) {
        this.b.setImageDrawable(drawable);
    }

    public void setButtonText(String str) {
        this.c.setText(str);
    }

    public void setRedPointVisiblity(boolean z) {
        if (z) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
    }

    public void setCoupOnRedPoint(boolean z) {
        if (z) {
            this.f.setVisibility(0);
            this.f.setGravity(17);
            return;
        }
        this.f.setVisibility(8);
    }

    public void setNotificationText(String str) {
        if (str == null || str.equals("")) {
            this.d.setVisibility(4);
            return;
        }
        this.d.setText(str);
        this.d.setVisibility(0);
    }
}
