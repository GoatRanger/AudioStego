package dji.gs.c;

import android.os.Bundle;

public interface b {
    void onCreate(Bundle bundle);

    void onDestroy();

    void onLowMemory();

    void onPause();

    void onResume();

    void onSaveInstanceState(Bundle bundle);
}
