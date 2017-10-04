package dji.log;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class LogView extends TextView {
    public LogView(Context context) {
        this(context, null);
    }

    public LogView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LogView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
