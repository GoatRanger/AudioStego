package dji.pilot.gallery.entryActivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.gallery.previewActivity.DJIGalleryPreviewActivity;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot2.media.activity.DJIPhotoEditorActivity;
import dji.pilot2.multimoment.activity.DJIMultiMomentEditActivity;
import dji.pilot2.multimoment.view.HorizonalSegmentView;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DJIGalleryFragment extends Fragment implements dji.pilot.gallery.entryActivity.a.b {
    private dji.pilot.gallery.entryActivity.a.a a;
    private ExpandableListView b;
    private f c;
    private a d;
    private b e;
    private DJIStateImageView f;
    private DJITextView g;
    private DJIStateTextView h;
    private DJIGalleryAlbumsGridView i;
    private View j;
    private View k;
    private View l;
    private View m;
    private RelativeLayout n;
    private DJIStateTextView o;
    private RelativeLayout p;
    private RelativeLayout q;

    public enum a {
        SelectOrPreview_SELECT,
        SelectOrPreview_PREVIEW
    }

    public enum b {
        SHOW_MODE_PIC,
        SHOW_MODE_VIDEO,
        SHOW_MODE_ALL,
        SHOW_MODE_ALBUM
    }

    public a b() {
        return this.d;
    }

    public b c() {
        return this.e;
    }

    private void d() {
        this.d = a.SelectOrPreview_PREVIEW;
        this.e = b.SHOW_MODE_ALL;
    }

    private void a(View view) {
        this.f = (DJIStateImageView) view.findViewById(R.id.ago);
        this.g = (DJITextView) view.findViewById(R.id.agp);
        this.h = (DJIStateTextView) view.findViewById(R.id.agq);
        if (this.d.equals(a.SelectOrPreview_SELECT)) {
            this.h.setText(R.string.v2_hg_cancel);
        } else {
            this.h.setText(R.string.v2_hg_select);
        }
        this.g.setText(R.string.v2_hg_title);
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIGalleryFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.d.equals(a.SelectOrPreview_SELECT)) {
                    this.a.h.setText(R.string.v2_hg_select);
                    this.a.g.setText(R.string.v2_hg_title);
                    this.a.f.setImageResource(R.drawable.v2_explore_arrow_left_back);
                    this.a.d = a.SelectOrPreview_PREVIEW;
                    this.a.c.b();
                    this.a.c.a();
                    this.a.i.refresh();
                    this.a.o.setVisibility(8);
                    return;
                }
                this.a.f.setImageResource(R.drawable.hg_delete);
                this.a.h.setText(R.string.v2_hg_cancel);
                this.a.g.setText(String.format(this.a.getResources().getString(R.string.v2_hg_selectnum), new Object[]{Integer.valueOf(this.a.a.a())}));
                this.a.d = a.SelectOrPreview_SELECT;
                this.a.c.a();
                this.a.i.refresh();
            }
        });
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIGalleryFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!this.a.d.equals(a.SelectOrPreview_SELECT)) {
                    this.a.getActivity().finish();
                } else if (this.a.a.a() > 0) {
                    c.a(this.a.getActivity(), this.a.a.a(), new dji.pilot.gallery.entryActivity.c.a(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                            this.a.a.a.d();
                            this.a.a.a.a(this.a.a.e);
                            this.a.a.g.setText(String.format(this.a.a.getResources().getString(R.string.v2_hg_selectnum), new Object[]{Integer.valueOf(this.a.a.a.a())}));
                        }

                        public void b() {
                            this.a.a.c.a();
                            this.a.a.i.refresh();
                        }
                    });
                } else {
                    Toast.makeText(this.a.getActivity(), R.string.v2_hg_delete, 0).show();
                }
            }
        });
    }

    private void b(View view) {
        this.n = (RelativeLayout) view.findViewById(R.id.agv);
        this.j = view.findViewById(R.id.ahg);
        this.l = view.findViewById(R.id.ahi);
        this.k = view.findViewById(R.id.ahk);
        this.m = view.findViewById(R.id.ahm);
        this.j.setSelected(true);
        this.j.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIGalleryFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.e != b.SHOW_MODE_ALL) {
                    this.a.j.setSelected(true);
                    this.a.l.setSelected(false);
                    this.a.k.setSelected(false);
                    this.a.m.setSelected(false);
                    this.a.e = b.SHOW_MODE_ALL;
                    this.a.a.a(b.SHOW_MODE_ALL);
                }
            }
        });
        this.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIGalleryFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.e != b.SHOW_MODE_VIDEO) {
                    this.a.l.setSelected(true);
                    this.a.j.setSelected(false);
                    this.a.k.setSelected(false);
                    this.a.m.setSelected(false);
                    this.a.e = b.SHOW_MODE_VIDEO;
                    this.a.a.a(b.SHOW_MODE_VIDEO);
                }
            }
        });
        this.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIGalleryFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.e != b.SHOW_MODE_PIC) {
                    this.a.l.setSelected(false);
                    this.a.j.setSelected(false);
                    this.a.k.setSelected(true);
                    this.a.m.setSelected(false);
                    this.a.e = b.SHOW_MODE_PIC;
                    this.a.a.a(b.SHOW_MODE_PIC);
                }
            }
        });
        this.m.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIGalleryFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.e != b.SHOW_MODE_ALBUM) {
                    this.a.m.setSelected(true);
                    this.a.l.setSelected(false);
                    this.a.j.setSelected(false);
                    this.a.k.setSelected(false);
                    this.a.e = b.SHOW_MODE_ALBUM;
                    this.a.a.a(b.SHOW_MODE_ALBUM);
                }
            }
        });
    }

    private void c(View view) {
        d();
        this.q = (RelativeLayout) view.findViewById(R.id.agw);
        this.i = (DJIGalleryAlbumsGridView) view.findViewById(R.id.agz);
        this.i.setChildItemClickListener(new dji.pilot.gallery.entryActivity.DJIGalleryAlbumsGridView.b(this) {
            final /* synthetic */ DJIGalleryFragment a;

            {
                this.a = r1;
            }

            public void a(List<g> list) {
                if (this.a.d.equals(a.SelectOrPreview_SELECT)) {
                    for (int i = 0; i < list.size(); i++) {
                        boolean z;
                        g gVar = (g) list.get(i);
                        if (((g) list.get(i)).k) {
                            z = false;
                        } else {
                            z = true;
                        }
                        gVar.k = z;
                    }
                    this.a.g.setText(String.format(this.a.getResources().getString(R.string.v2_hg_selectnum), new Object[]{Integer.valueOf(this.a.a.a())}));
                    if (this.a.a.b()) {
                        this.a.o.setVisibility(0);
                    } else {
                        this.a.o.setVisibility(8);
                    }
                    this.a.c.a();
                    this.a.i.refresh();
                    return;
                }
                Intent intent = new Intent(this.a.getActivity(), DJIGalleryAlbumContentActivity.class);
                intent.putExtra("intent_filename", d.d(((g) list.get(0)).c));
                this.a.startActivity(intent);
            }
        });
        this.c = e();
        this.b = (ExpandableListView) view.findViewById(R.id.agx);
        this.p = (RelativeLayout) view.findViewById(R.id.ah0);
        this.b.setDivider(null);
        this.b.setChildDivider(null);
        this.b.setGroupIndicator(null);
        this.b.setDividerHeight(0);
        this.b.setChildIndicator(null);
        this.b.setAdapter(this.c);
        this.b.setOnGroupClickListener(new OnGroupClickListener(this) {
            final /* synthetic */ DJIGalleryFragment a;

            {
                this.a = r1;
            }

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                return true;
            }
        });
        this.b.setOnChildClickListener(new OnChildClickListener(this) {
            final /* synthetic */ DJIGalleryFragment a;

            {
                this.a = r1;
            }

            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
                return true;
            }
        });
        for (int i = 0; i < this.c.getGroupCount(); i++) {
            this.b.expandGroup(i);
        }
        a(view);
        b(view);
        this.o = (DJIStateTextView) view.findViewById(R.id.agy);
        this.o.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIGalleryFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int i = 0;
                List h = this.a.a.h();
                if (h != null && h.size() != 0) {
                    if (((g) h.get(0)).h == dji.pilot.gallery.entryActivity.d.b.Type_IMG) {
                        Intent intent = new Intent();
                        intent.setClass(this.a.getActivity(), DJIPhotoEditorActivity.class);
                        intent.putExtra("key_filepath", ((g) h.get(0)).c);
                        this.a.startActivity(intent);
                        return;
                    }
                    List arrayList = new ArrayList();
                    List arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < h.size(); i2++) {
                        g gVar = (g) h.get(i2);
                        if (gVar.d >= HorizonalSegmentView.N) {
                            arrayList.add(gVar.c);
                            arrayList2.add(Integer.valueOf(gVar.d));
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
        View inflate = layoutInflater.inflate(R.layout.gallery_main_fragment1, viewGroup, false);
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
        this.a.a(getActivity().getApplicationContext());
    }

    public void onResume() {
        super.onResume();
        if (this.e == b.SHOW_MODE_ALBUM && this.i != null) {
            this.i.refresh();
            this.m.setSelected(true);
            this.l.setSelected(false);
            this.j.setSelected(false);
            this.k.setSelected(false);
            this.e = b.SHOW_MODE_ALBUM;
            this.a.a(b.SHOW_MODE_ALBUM);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
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

    public void a(dji.pilot.gallery.entryActivity.a.a aVar) {
        this.a = aVar;
    }

    private f e() {
        this.c = new f(getActivity(), this, new dji.pilot.gallery.entryActivity.f.a(this) {
            final /* synthetic */ DJIGalleryFragment a;

            {
                this.a = r1;
            }

            public void a(g gVar) {
                DJILogHelper.getInstance().LOGI("bob", "onChildItemClick " + this.a.d);
                if (this.a.d.equals(a.SelectOrPreview_SELECT)) {
                    gVar.k = !gVar.k;
                    DJILogHelper.getInstance().LOGI("bob", "onChildItemClick " + gVar.k);
                    this.a.g.setText(String.format(this.a.getResources().getString(R.string.v2_hg_selectnum), new Object[]{Integer.valueOf(this.a.a.a())}));
                    if (this.a.a.b()) {
                        this.a.o.setVisibility(0);
                    } else {
                        this.a.o.setVisibility(8);
                    }
                    this.a.c.a();
                    this.a.i.refresh();
                    return;
                }
                Intent intent = new Intent(this.a.getActivity(), DJIGalleryPreviewActivity.class);
                Bundle bundle = new Bundle();
                List g = this.a.a.g();
                ArrayList arrayList = new ArrayList();
                arrayList.add(g);
                bundle.putParcelableArrayList("data", arrayList);
                bundle.putInt("index", g.indexOf(gVar));
                intent.putExtras(bundle);
                this.a.startActivityForResult(intent, 11);
                DJILogHelper.getInstance().LOGI("bob", "onChildItemClick index=");
            }

            public void a() {
                DJILogHelper.getInstance().LOGI("bob", "onNotifyDataSetChanged ");
                for (int i = 0; i < this.a.c.getGroupCount(); i++) {
                    this.a.b.collapseGroup(i);
                    this.a.b.expandGroup(i);
                }
            }

            public void a(int i) {
                this.a.c.a(i);
                this.a.c.a();
                this.a.i.refresh();
                this.a.g.setText(String.format(this.a.getResources().getString(R.string.v2_hg_selectnum), new Object[]{Integer.valueOf(this.a.a.a())}));
            }

            public void b(int i) {
                this.a.c.b(i);
                this.a.c.a();
                this.a.i.refresh();
                this.a.g.setText(String.format(this.a.getResources().getString(R.string.v2_hg_selectnum), new Object[]{Integer.valueOf(this.a.a.a())}));
            }
        });
        return this.c;
    }

    public void a(TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>> treeMap) {
        e();
        if (a() != b.SHOW_MODE_ALBUM) {
            if (this.c != null) {
                this.b.setAdapter(this.c);
                this.c.a((TreeMap) treeMap);
                if (this.a.b()) {
                    this.o.setVisibility(0);
                } else {
                    this.o.setVisibility(8);
                }
            }
            this.b.setEmptyView(this.p);
            this.i.setVisibility(4);
            this.b.setVisibility(0);
        } else if (a() == b.SHOW_MODE_ALBUM) {
            this.b.setVisibility(4);
            this.p.setVisibility(4);
            this.i.setDate(treeMap);
            this.i.setVisibility(0);
        }
    }

    public b a() {
        return this.e;
    }

    public void a(boolean z) {
        if (z) {
            this.q.setVisibility(0);
            this.n.setEnabled(false);
            d(this.q);
            return;
        }
        e(this.q);
        this.q.setVisibility(8);
        this.n.setEnabled(true);
    }

    private void d(View view) {
        Drawable background = ((ImageView) view.findViewById(R.id.rt)).getBackground();
        if (background instanceof AnimationDrawable) {
            ((AnimationDrawable) background).start();
        }
    }

    private void e(View view) {
        Drawable background = ((ImageView) view.findViewById(R.id.rt)).getBackground();
        if (background instanceof AnimationDrawable) {
            ((AnimationDrawable) background).stop();
        }
    }

    public void b(boolean z) {
        if (z) {
            this.n.setVisibility(0);
        } else {
            this.n.setVisibility(8);
        }
    }

    public void a(String str) {
        this.g.setText(str);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 11 && i2 == 1) {
            Activity activity = getActivity();
            if (activity != null) {
                this.a.a(activity.getApplicationContext());
            }
        }
    }
}
