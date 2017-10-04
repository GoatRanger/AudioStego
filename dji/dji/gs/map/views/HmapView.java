package dji.gs.map.views;

import android.content.Context;
import android.os.Bundle;
import com.here.android.mpa.mapping.MapView;
import dji.gs.c.b;

public class HmapView extends MapView implements b {
    public HmapView(Context context) {
        super(context);
    }

    public void onCreate(Bundle bundle) {
        onRestoreInstanceState(bundle);
    }

    public void onDestroy() {
    }

    public void onLowMemory() {
    }

    public void onSaveInstanceState(Bundle bundle) {
        onSaveInstanceState();
    }
}
