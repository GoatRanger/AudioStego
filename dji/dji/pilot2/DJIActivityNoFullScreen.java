package dji.pilot2;

import android.view.Window;

public class DJIActivityNoFullScreen extends DJIActivity {
    public void setContentView(int i) {
        super.setContentView(i);
    }

    protected void a(Window window) {
    }

    protected void onResume() {
        super.onResume();
    }

    public int e() {
        int i = 0;
        try {
            Class cls = Class.forName("com.android.internal.R$dimen");
            i = getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
}
