package dji.playback.entryActivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot2.media.activity.DJIPhotoEditorActivity;
import dji.pilot2.multimoment.activity.DJIMultiMomentEditActivity;
import dji.pilot2.multimoment.view.HorizonalSegmentView;
import dji.playback.previewActivity.DJIPlaybackPreviewActivity;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DJIPlaybackFragment extends Fragment implements dji.playback.entryActivity.a.b {
    public long a = 0;
    private dji.playback.entryActivity.a.a b;
    private ExpandableListView c;
    private g d;
    private a e;
    private b f;
    private DJIStateImageView g;
    private DJITextView h;
    private DJIStateTextView i;
    private View j;
    private View k;
    private View l;
    private View m;
    private ProgressBar n;
    private boolean o = false;
    private int p = 0;
    private DJIStateTextView q;
    private RelativeLayout r;
    private Animation s;
    private Animation t;
    private boolean u = false;
    private boolean v = false;

    public enum a {
        SelectOrPreview_SELECT,
        SelectOrPreview_PREVIEW
    }

    public enum b {
        SHOW_MODE_PIC,
        SHOW_MODE_VIDEO,
        SHOW_MODE_ALL
    }

    public a d() {
        return this.e;
    }

    public b e() {
        return this.f;
    }

    private void f() {
        this.e = a.SelectOrPreview_PREVIEW;
        this.f = b.SHOW_MODE_ALL;
    }

    private void a(View view) {
        this.g = (DJIStateImageView) view.findViewById(R.id.ago);
        this.h = (DJITextView) view.findViewById(R.id.agp);
        this.i = (DJIStateTextView) view.findViewById(R.id.agq);
        if (this.e.equals(a.SelectOrPreview_SELECT)) {
            this.i.setText(R.string.v2_hg_cancel);
        } else {
            this.i.setText(R.string.v2_hg_select);
        }
        this.h.setText(R.string.v2_hg_title);
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIPlaybackFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.e.equals(a.SelectOrPreview_SELECT)) {
                    this.a.i.setText(R.string.v2_hg_select);
                    this.a.h.setText(R.string.v2_hg_title);
                    this.a.g.setImageResource(R.drawable.v2_mine_back_pic);
                    this.a.e = a.SelectOrPreview_PREVIEW;
                    this.a.d.c();
                    this.a.d.b();
                    this.a.q.setVisibility(8);
                    return;
                }
                this.a.g.setImageResource(R.drawable.hg_delete);
                this.a.i.setText(R.string.v2_hg_cancel);
                this.a.h.setText(String.format(this.a.getResources().getString(R.string.v2_hg_selectnum), new Object[]{Integer.valueOf(this.a.b.a())}));
                this.a.e = a.SelectOrPreview_SELECT;
                this.a.d.b();
            }
        });
        this.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIPlaybackFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!this.a.e.equals(a.SelectOrPreview_SELECT)) {
                    this.a.getActivity().finish();
                } else if (this.a.b.a() > 0) {
                    c.a(this.a.getActivity(), this.a.b.a(), new dji.playback.entryActivity.c.a(this) {
                        final /* synthetic */ AnonymousClass5 a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                            DJILogHelper.getInstance().LOGI("bob", "onOkClicked");
                            this.a.a.b.d();
                            this.a.a.b.a(this.a.a.f);
                            this.a.a.h.setText(String.format(this.a.a.getResources().getString(R.string.v2_hg_selectnum), new Object[]{Integer.valueOf(this.a.a.b.a())}));
                        }

                        public void b() {
                            DJILogHelper.getInstance().LOGI("bob", "OnCancelClicked");
                            this.a.a.d.b();
                        }

                        public void c() {
                            this.a.a.h();
                        }
                    });
                } else {
                    Toast.makeText(this.a.getActivity(), R.string.v2_hg_delete, 0).show();
                }
            }
        });
    }

    private void b(View view) {
        this.n = (ProgressBar) view.findViewById(R.id.cxv);
        this.t = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -1.0f);
        this.t.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ DJIPlaybackFragment a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
                this.a.u = true;
                this.a.v = false;
            }

            public void onAnimationEnd(Animation animation) {
                this.a.m.setVisibility(8);
            }

            public void onAnimationRepeat(Animation animation) {
                this.a.u = false;
            }
        });
        this.t.setDuration(100);
        this.s = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, -1.0f, 1, 0.0f);
        this.s.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ DJIPlaybackFragment a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
                this.a.v = true;
                this.a.u = false;
            }

            public void onAnimationEnd(Animation animation) {
                this.a.m.setVisibility(0);
            }

            public void onAnimationRepeat(Animation animation) {
                this.a.v = false;
            }
        });
        this.s.setDuration(200);
        this.m = view.findViewById(R.id.cxs);
        this.j = view.findViewById(R.id.ahg);
        this.l = view.findViewById(R.id.ahi);
        this.k = view.findViewById(R.id.ahk);
        this.j.setSelected(true);
        this.j.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIPlaybackFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.f != b.SHOW_MODE_ALL) {
                    this.a.j.setSelected(true);
                    this.a.l.setSelected(false);
                    this.a.k.setSelected(false);
                    this.a.f = b.SHOW_MODE_ALL;
                    this.a.b.a(b.SHOW_MODE_ALL);
                }
            }
        });
        this.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIPlaybackFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.f != b.SHOW_MODE_VIDEO) {
                    this.a.l.setSelected(true);
                    this.a.j.setSelected(false);
                    this.a.k.setSelected(false);
                    this.a.f = b.SHOW_MODE_VIDEO;
                    this.a.b.a(b.SHOW_MODE_VIDEO);
                }
            }
        });
        this.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIPlaybackFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.f != b.SHOW_MODE_PIC) {
                    this.a.l.setSelected(false);
                    this.a.j.setSelected(false);
                    this.a.k.setSelected(true);
                    this.a.f = b.SHOW_MODE_PIC;
                    this.a.b.a(b.SHOW_MODE_PIC);
                }
            }
        });
    }

    private void c(View view) {
        f();
        this.d = g();
        this.c = (ExpandableListView) view.findViewById(R.id.cxu);
        this.r = (RelativeLayout) view.findViewById(R.id.ah0);
        this.c.setDivider(null);
        this.c.setChildDivider(null);
        this.c.setGroupIndicator(null);
        this.c.setDividerHeight(0);
        this.c.setChildIndicator(null);
        this.c.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ DJIPlaybackFragment a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == 1 || i == 2) {
                    this.a.o = true;
                } else {
                    this.a.o = false;
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (this.a.o) {
                    if (i > this.a.p) {
                        if (!(this.a.u || this.a.m == null)) {
                            this.a.m.startAnimation(this.a.t);
                        }
                    } else if (!(i >= this.a.p || this.a.v || this.a.m == null)) {
                        this.a.m.startAnimation(this.a.s);
                    }
                    this.a.p = i;
                }
            }
        });
        this.c.setAdapter(this.d);
        this.c.setOnGroupClickListener(new OnGroupClickListener(this) {
            final /* synthetic */ DJIPlaybackFragment a;

            {
                this.a = r1;
            }

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                return true;
            }
        });
        this.c.setOnChildClickListener(new OnChildClickListener(this) {
            final /* synthetic */ DJIPlaybackFragment a;

            {
                this.a = r1;
            }

            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
                return true;
            }
        });
        for (int i = 0; i < this.d.getGroupCount(); i++) {
            this.c.expandGroup(i);
        }
        a(view);
        b(view);
        this.q = (DJIStateTextView) view.findViewById(R.id.agy);
        this.q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIPlaybackFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int i = 0;
                List h = this.a.b.h();
                if (h != null && h.size() != 0) {
                    if (((h) h.get(0)).h == dji.playback.entryActivity.d.b.Type_IMG) {
                        Intent intent = new Intent();
                        intent.setClass(this.a.getActivity(), DJIPhotoEditorActivity.class);
                        intent.putExtra("key_filepath", ((h) h.get(0)).c);
                        this.a.startActivity(intent);
                        return;
                    }
                    List arrayList = new ArrayList();
                    List arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < h.size(); i2++) {
                        h hVar = (h) h.get(i2);
                        if (hVar.d >= HorizonalSegmentView.N) {
                            arrayList.add(hVar.c);
                            arrayList2.add(Integer.valueOf(hVar.d));
                        }
                    }
                    String[] strArr = new String[arrayList.size()];
                    int[] iArr = new int[arrayList.size()];
                    while (i < arrayList.size()) {
                        strArr[i] = (String) arrayList.get(i);
                        iArr[i] = ((Integer) arrayList2.get(i)).intValue();
                        i++;
                    }
                    Intent intent2 = new Intent(this.a.getActivity(), DJIMultiMomentEditActivity.class);
                    intent2.putExtra(DJIMultiMomentEditActivity.M, strArr);
                    intent2.putExtra("duration", iArr);
                    this.a.startActivity(intent2);
                }
            }
        });
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.v2_playbackmain_fragment1, viewGroup, false);
        c(inflate);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void onStart() {
        super.onStart();
        this.b.a(getActivity().getApplicationContext());
    }

    public void onResume() {
        super.onResume();
        h();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.d.b();
    }

    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void a(dji.playback.entryActivity.a.a aVar) {
        this.b = aVar;
    }

    private g g() {
        if (this.d != null) {
            this.d.a();
            this.d = null;
        }
        Activity activity = getActivity();
        if (activity != null) {
            this.d = new g(activity.getApplicationContext(), this, new dji.playback.entryActivity.g.a(this) {
                final /* synthetic */ DJIPlaybackFragment a;

                {
                    this.a = r1;
                }

                public void a(h hVar) {
                    DJILogHelper.getInstance().LOGI("bob", "onChildItemClick " + this.a.e);
                    if (this.a.e.equals(a.SelectOrPreview_SELECT)) {
                        hVar.k = !hVar.k;
                        DJILogHelper.getInstance().LOGI("bob", "onChildItemClick " + hVar.k);
                        this.a.h.setText(String.format(this.a.getResources().getString(R.string.v2_hg_selectnum), new Object[]{Integer.valueOf(this.a.b.a())}));
                        if (this.a.b.b()) {
                            this.a.q.setVisibility(0);
                        } else {
                            this.a.q.setVisibility(8);
                        }
                        this.a.d.b();
                        return;
                    }
                    Intent intent = new Intent(this.a.getActivity(), DJIPlaybackPreviewActivity.class);
                    Bundle bundle = new Bundle();
                    List g = this.a.b.g();
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(g);
                    bundle.putParcelableArrayList("data", arrayList);
                    bundle.putInt("index", g.indexOf(hVar));
                    intent.putExtras(bundle);
                    this.a.startActivityForResult(intent, 11);
                    DJILogHelper.getInstance().LOGI("bob", "onChildItemClick index=");
                }

                public void a() {
                    DJILogHelper.getInstance().LOGI("bob", "onNotifyDataSetChanged ");
                    if (this.a.d != null) {
                        for (int i = 0; i < this.a.d.getGroupCount(); i++) {
                            if (this.a.c != null) {
                                this.a.c.collapseGroup(i);
                                this.a.c.expandGroup(i);
                            }
                        }
                    }
                }

                public void a(int i) {
                    this.a.d.a(i);
                    this.a.d.b();
                    this.a.h.setText(String.format(this.a.getResources().getString(R.string.v2_hg_selectnum), new Object[]{Integer.valueOf(this.a.b.a())}));
                }

                public void b(int i) {
                    this.a.d.b(i);
                    this.a.d.b();
                    this.a.h.setText(String.format(this.a.getResources().getString(R.string.v2_hg_selectnum), new Object[]{Integer.valueOf(this.a.b.a())}));
                }
            });
        }
        return this.d;
    }

    public void a(TreeMap<dji.playback.entryActivity.d.a, List<h>> treeMap) {
        g();
        if (this.d != null) {
            this.c.setAdapter(this.d);
            this.d.a((TreeMap) treeMap);
            if (this.b.b()) {
                this.q.setVisibility(0);
            } else {
                this.q.setVisibility(8);
            }
        }
        this.c.setEmptyView(this.r);
    }

    public b a() {
        return this.f;
    }

    public void b() {
        this.n.setVisibility(0);
        this.j.setEnabled(false);
        this.k.setEnabled(false);
        this.l.setEnabled(false);
        this.i.setEnabled(false);
        this.a = System.currentTimeMillis();
    }

    public void c() {
        this.n.setVisibility(4);
        this.j.setEnabled(true);
        this.k.setEnabled(true);
        this.l.setEnabled(true);
        this.i.setEnabled(true);
        DJILogHelper.getInstance().LOGI("bob", "loading time =" + (System.currentTimeMillis() - this.a));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 11 && i2 == 1) {
            Activity activity = getActivity();
            if (activity != null) {
                this.b.a(activity.getApplicationContext());
            }
        }
    }

    private void h() {
        int i;
        if (VERSION.SDK_INT >= 19) {
            i = 5894;
        } else {
            i = 1798;
        }
        Activity activity = getActivity();
        if (activity != null) {
            activity.getWindow().getDecorView().setSystemUiVisibility(i);
        }
    }
}
