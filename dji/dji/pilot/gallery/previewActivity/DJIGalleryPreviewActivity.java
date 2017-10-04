package dji.pilot.gallery.previewActivity;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.FrameLayout;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.thirdparty.afinal.FinalActivity;
import java.util.ArrayList;
import java.util.List;

public class DJIGalleryPreviewActivity extends FinalActivity {
    public static int a;
    public static int b;
    private c c;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        DJILogHelper.getInstance().LOGI("bob", "DJIGalleryPreviewActivity onCreate");
        Bundle extras = getIntent().getExtras();
        super.onCreate(bundle);
        setContentView(R.layout.gallery_preview_activity);
        a();
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.ah4);
        FragmentManager fragmentManager = getFragmentManager();
        this.c = new c();
        ArrayList parcelableArrayList = extras.getParcelableArrayList("data");
        if (parcelableArrayList != null) {
            DJILogHelper.getInstance().LOGI("bob", "DJIGalleryPreviewActivity size = " + ((List) parcelableArrayList.get(0)).size());
        }
        this.c.setArguments(extras);
        fragmentManager.beginTransaction().replace(R.id.ah4, this.c).commit();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (getResources().getConfiguration().orientation == 2) {
            DJILogHelper.getInstance().LOGI("bob", "onConfigurationChanged land");
        } else if (getResources().getConfiguration().orientation == 1) {
            DJILogHelper.getInstance().LOGI("bob", "onConfigurationChanged port");
        }
    }

    private void a() {
        if (VERSION.SDK_INT < 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            if (getResources().getConfiguration().orientation == 2) {
                int i;
                a = displayMetrics.widthPixels > displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels;
                if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
                    i = displayMetrics.widthPixels;
                } else {
                    i = displayMetrics.heightPixels;
                }
                b = i;
                return;
            }
            a = displayMetrics.widthPixels;
            b = displayMetrics.widthPixels;
            return;
        }
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        if (getResources().getConfiguration().orientation == 2) {
            a = point.x > point.y ? point.x : point.y;
            b = point.y > point.x ? point.x : point.y;
            return;
        }
        a = point.x;
        b = point.y;
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }
}
