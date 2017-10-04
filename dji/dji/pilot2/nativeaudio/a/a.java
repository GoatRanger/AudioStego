package dji.pilot2.nativeaudio.a;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import dji.pilot2.nativeaudio.b;

public class a extends FragmentPagerAdapter {
    private int a = 2;
    private Fragment b = new dji.pilot2.nativeaudio.a();
    private Fragment c = new b();

    public a(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public Fragment getItem(int i) {
        if (i % 2 == 0) {
            return this.c;
        }
        return this.b;
    }

    public int getCount() {
        return this.a;
    }
}
