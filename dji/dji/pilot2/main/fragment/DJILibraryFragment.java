package dji.pilot2.main.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dji.pilot.R;
import dji.pilot.home.rc.activity.DJIRcLibraryActivity;
import dji.pilot2.library.DJILibraryView;
import dji.pilot2.main.activity.DJIMainFragmentActivity;

public class DJILibraryFragment extends Fragment {
    public DJILibraryView a = null;
    public Activity b = null;
    private Context c = null;
    private View d = null;
    private final String e = "DJILibraryFragment";

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.d = layoutInflater.inflate(R.layout.v2_library_view, viewGroup, false);
        this.c = layoutInflater.getContext();
        c();
        d();
        return this.d;
    }

    private void c() {
        this.b = getActivity();
    }

    private void d() {
        this.a = (DJILibraryView) this.d.findViewById(R.id.cr3);
        this.a.attachFragment(this);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        this.a.onDestory();
        super.onDestroy();
    }

    public void a(boolean z) {
        this.a.updateMedias(z);
    }

    public void a() {
        this.a.deleteVideoView();
    }

    public void a(int i, int i2) {
        if (this.b instanceof DJIRcLibraryActivity) {
            ((DJIRcLibraryActivity) this.b).a(i, i2);
        } else if (this.b instanceof DJIMainFragmentActivity) {
            ((DJIMainFragmentActivity) this.b).a(i, i2);
        }
    }

    public void b() {
        if (this.b instanceof DJIRcLibraryActivity) {
            ((DJIRcLibraryActivity) this.b).a();
        } else if (this.b instanceof DJIMainFragmentActivity) {
            ((DJIMainFragmentActivity) this.b).f();
        }
    }
}
