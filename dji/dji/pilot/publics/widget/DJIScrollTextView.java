package dji.pilot.publics.widget;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import dji.publics.DJIUI.DJITextView;

public class DJIScrollTextView extends DJITextView {
    public DJIScrollTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
