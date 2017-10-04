package dji.pilot2.mine.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.pilot.R;

public class MineContactButton extends RelativeLayout {
    private Context a;
    private ImageView b;
    private TextView c;

    public MineContactButton(Context context) {
        this(context, null);
    }

    public MineContactButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MineContactButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        inflate(getContext(), R.layout.v2_mine_contact_button, this);
        this.b = (ImageView) findViewById(R.id.csm);
        this.c = (TextView) findViewById(R.id.csn);
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
}
