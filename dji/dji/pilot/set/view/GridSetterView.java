package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import dji.pilot.set.R;
import dji.pilot.set.g;
import dji.pilot.set.view.base.SetLocalTextView;

public class GridSetterView extends SetLocalTextView {
    public GridSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected int getTitleId() {
        return R.string.set_grid;
    }

    protected int getValuesId() {
        return R.array.camera_grid_value_array;
    }

    protected int getStringArrayId() {
        return R.array.camera_grid_str_array;
    }

    protected String getKey() {
        return g.a;
    }
}
