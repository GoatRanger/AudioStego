package dji.pilot.publics.objects;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class d extends Fragment {
    protected DJIFragmentActivity j = null;
    protected View k = null;

    protected abstract View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    protected abstract void l();

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.j = (DJIFragmentActivity) activity;
        } catch (ClassCastException e) {
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.k == null) {
            this.k = a(layoutInflater, viewGroup, bundle);
        }
        l();
        return this.k;
    }

    public void onDestroyView() {
        p();
        super.onDestroyView();
    }

    protected void p() {
    }

    protected View b(int i) {
        return this.k.findViewById(i);
    }

    public boolean a(KeyEvent keyEvent) {
        return false;
    }

    public boolean m() {
        return false;
    }

    public void q() {
    }
}
