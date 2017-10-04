package dji.setting.ui.hd.sdr;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.setting.ui.widget.ItemViewButtonBig;

public class SdrViewStatusBtn extends ItemViewButtonBig {
    public SdrViewStatusBtn(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onClick(View view) {
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0.0f, 0.0f, (float) getWidth(), 1.0f, eV_);
    }
}
