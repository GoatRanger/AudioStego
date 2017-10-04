package com.here.android.mpa.common;

import android.app.Activity;
import android.os.Bundle;
import com.here.android.mpa.common.OnEngineInitListener.Error;
import com.nokia.maps.annotation.Online;

@Online
public class MapActivity extends Activity {
    private int a = 0;
    private boolean b = false;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MapEngine.getInstance().init(this, new OnEngineInitListener(this) {
            final /* synthetic */ MapActivity a;

            {
                this.a = r1;
            }

            public void onEngineInitializationCompleted(Error error) {
                if (error == Error.NONE) {
                    this.a.b = true;
                    for (int i = 0; i < this.a.a; i++) {
                        MapEngine.getInstance().onResume();
                    }
                    this.a.a = 0;
                }
                this.a.onInitialized(error);
            }
        });
    }

    protected void onResume() {
        super.onResume();
        if (this.b) {
            MapEngine.getInstance().onResume();
        } else {
            this.a++;
        }
    }

    protected void onPause() {
        if (this.b) {
            MapEngine.getInstance().onPause();
        } else {
            this.a--;
        }
        super.onPause();
    }

    protected void onInitialized(Error error) {
    }
}
