package dji.pilot2.nativeexplore.explorepost;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot2.library.b.a.b;
import dji.pilot2.library.model.DJIScanerMediaManager.a;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;

public class d extends Fragment implements a {
    private DJIStateTextView a;
    private TextView b;
    private TextView c;
    private DJITextView d;
    private ExpandableListView e;
    private a f;
    private View g;
    private b h;
    private boolean i;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.v2_explorepostselect_fragment, viewGroup, false);
        a(inflate);
        this.i = getArguments().getBoolean(ExplorePostSelectActivity.a, false);
        return inflate;
    }

    private void a(View view) {
        this.a = (DJIStateTextView) view.findViewById(R.id.cmv);
        this.b = (TextView) view.findViewById(R.id.cmw);
        this.c = (TextView) view.findViewById(R.id.cmx);
        this.d = (DJITextView) view.findViewById(R.id.cmz);
        this.e = (ExpandableListView) view.findViewById(R.id.cmy);
        this.g = view.findViewById(R.id.cn0);
        this.f = new a(getActivity());
        this.e.setGroupIndicator(null);
        this.e.setOnGroupClickListener(new OnGroupClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                return true;
            }
        });
        this.e.setAdapter(this.f);
        if (this.f.getGroupCount() > 0) {
            this.f.notifyDataSetChanged();
            for (int i = 0; i < this.f.getGroupCount(); i++) {
                if (this.e != null) {
                    this.e.expandGroup(i);
                }
            }
        }
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.getActivity().finish();
            }
        });
        this.f.a(new b(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(ArrayList<Integer> arrayList, int i, boolean z) {
            }
        });
    }

    public void onResume() {
        super.onResume();
        if (this.h != null) {
            this.h.a(this.i);
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onStop() {
        super.onStop();
    }

    public void onStart() {
        super.onStart();
    }

    public void a(b bVar) {
        this.h = bVar;
    }

    public void a(ArrayList<a> arrayList) {
        if (arrayList != null) {
            DJILogHelper.getInstance().LOGI("bob", "setData mAdapter = " + this.f + " datas.size = " + arrayList.size());
            if (this.f != null) {
                this.f.a((ArrayList) arrayList);
            }
        }
        if (this.f.getGroupCount() > 0) {
            this.f.notifyDataSetChanged();
            for (int i = 0; i < this.f.getGroupCount(); i++) {
                if (this.e != null) {
                    this.e.expandGroup(i);
                }
            }
        }
        this.d.setVisibility(8);
        this.e.setEmptyView(this.g);
    }
}
