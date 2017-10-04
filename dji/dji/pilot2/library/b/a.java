package dji.pilot2.library.b;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c$l;
import dji.pilot.fpv.d.e;
import dji.pilot2.library.DJILibraryView;
import dji.pilot2.library.a.c;
import dji.pilot2.library.model.DJIScanerMediaManager;
import dji.pilot2.main.fragment.DJILibraryFragment;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;
import java.util.Iterator;

public class a extends Fragment implements OnClickListener, c$l {
    private TextView A;
    private TextView B;
    private boolean C = false;
    private TextView D;
    private DJITextView E;
    private dji.pilot2.publics.object.b F;
    private AlertDialog G;
    private Fragment H;
    private final String I = "DJILibraryInputMediaFragment";
    b p = new b(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(ArrayList<Integer> arrayList, int i, boolean z) {
            if (arrayList == null || i != -1) {
                if (z) {
                    this.a.v.remove(Integer.valueOf(i));
                } else {
                    this.a.v.add(Integer.valueOf(i));
                }
            } else if (z) {
                for (r1 = 0; r1 < arrayList.size(); r1++) {
                    this.a.v.remove(Integer.valueOf(((Integer) arrayList.get(r1)).intValue()));
                }
            } else {
                for (r1 = 0; r1 < arrayList.size(); r1++) {
                    int i2;
                    Iterator it = this.a.v.iterator();
                    while (it.hasNext()) {
                        if (((Integer) it.next()).intValue() == ((Integer) arrayList.get(r1)).intValue()) {
                            i2 = 0;
                            break;
                        }
                    }
                    i2 = 1;
                    if (i2 != 0) {
                        this.a.v.add(arrayList.get(r1));
                    }
                }
            }
            this.a.t.updateThumbnailList(this.a.v);
            if (this.a.v.size() == 0) {
                if (this.a.q) {
                    this.a.D.setText(this.a.r.getResources().getString(R.string.v2_lib_inputvideo));
                } else {
                    this.a.D.setText(this.a.r.getResources().getString(R.string.v2_lib_inputpic));
                }
                this.a.A.setTextColor(this.a.r.getResources().getColor(R.color.na));
                this.a.D.setTextColor(this.a.r.getResources().getColor(R.color.na));
                if (this.a.C) {
                    this.a.z.enterSelectMode(0, 4);
                    this.a.y.setVisibility(8);
                    return;
                }
                this.a.w.a(3, 0);
                this.a.y.setBackgroundColor(this.a.r.getResources().getColor(R.color.om));
                return;
            }
            this.a.D.setText(this.a.getResources().getString(R.string.v2_library_title_num, new Object[]{Integer.valueOf(this.a.v.size())}));
            if (this.a.C) {
                this.a.z.enterSelectMode(this.a.v.size(), 4);
                this.a.y.setVisibility(8);
            } else {
                this.a.w.a(3, this.a.v.size());
                this.a.y.setBackgroundColor(this.a.r.getResources().getColor(R.color.na));
            }
            this.a.A.setTextColor(this.a.r.getResources().getColor(R.color.om));
            this.a.D.setTextColor(this.a.r.getResources().getColor(R.color.om));
        }
    };
    private boolean q = false;
    private Context r;
    private ExpandableListView s;
    private DJIScanerMediaManager t;
    private c u;
    private ArrayList<Integer> v;
    private DJILibraryFragment w;
    private RelativeLayout x;
    private RelativeLayout y;
    private DJILibraryView z;

    public interface b {
        void a(ArrayList<Integer> arrayList, int i, boolean z);
    }

    class a extends AsyncTask<Void, ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a>, ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a>> {
        boolean a;
        final /* synthetic */ a b;

        a(a aVar) {
            this.b = aVar;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((ArrayList) obj);
        }

        protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
            a((ArrayList[]) objArr);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.b.s.setAdapter(this.b.u);
            this.a = true;
        }

        protected ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a> a(Void... voidArr) {
            if (this.b.t == null) {
                return null;
            }
            this.b.t.setOnMediaDataGetListener(new dji.pilot2.library.model.DJIScanerMediaManager.b(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.publishProgress(new ArrayList[]{this.a.b.t.getMediaTimeStamps()});
                }
            });
            this.b.t.scaneAllMedias(this.b.q);
            return this.b.t.getMediaTimeStamps();
        }

        protected void a(ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a> arrayList) {
            if (arrayList != null && this.b.u != null) {
                this.b.u.a((ArrayList) arrayList);
                this.b.u.notifyDataSetChanged();
                for (int i = 0; i < this.b.u.getGroupCount(); i++) {
                    if (this.b.s != null) {
                        this.b.s.expandGroup(i);
                    }
                }
            }
        }

        protected void a(ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a>... arrayListArr) {
            int i = 0;
            super.onProgressUpdate(arrayListArr);
            if (this.a) {
                this.b.A.setEnabled(true);
                this.b.A.setClickable(true);
                this.b.E.go();
                this.b.t.setOnMediaDataGetListener(null);
                this.a = false;
            }
            if (arrayListArr[0] != null && this.b.u != null) {
                this.b.u.a(arrayListArr[0]);
                this.b.u.notifyDataSetChanged();
                while (i < this.b.u.getGroupCount()) {
                    if (this.b.s != null) {
                        this.b.s.expandGroup(i);
                    }
                    i++;
                }
            }
        }
    }

    public a(Context context, boolean z, ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a> arrayList) {
        this.r = context;
        this.C = z;
        this.t = DJIScanerMediaManager.getInstance(this.r);
        this.t.setFlagVideo(true);
        this.u = new c(this.r);
        this.v = new ArrayList();
        this.u.a(this.p);
        this.u.a((ArrayList) arrayList);
        this.u.a(true);
    }

    public a(boolean z, Context context) {
        DJILogHelper.getInstance().LOGD("DJILibraryInputMediaFragment", "DJILibraryInputMediaFragment");
        this.q = z;
        this.r = context;
        this.t = DJIScanerMediaManager.getInstance(this.r);
        this.t.setFlagVideo(this.q);
        this.u = new c(this.r);
        this.v = new ArrayList();
        this.u.a(this.p);
        this.u.a(false);
    }

    public void a(DJILibraryFragment dJILibraryFragment) {
        this.w = dJILibraryFragment;
    }

    public void a(DJILibraryView dJILibraryView) {
        this.z = dJILibraryView;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.v2_library_inputmedia_fragment, viewGroup, false);
        if (this.r == null) {
            return null;
        }
        this.s = (ExpandableListView) inflate.findViewById(R.id.cmy);
        this.D = (TextView) inflate.findViewById(R.id.cmw);
        if (this.q) {
            this.D.setText(this.r.getResources().getString(R.string.v2_lib_inputvideo));
        } else {
            this.D.setText(this.r.getResources().getString(R.string.v2_lib_inputpic));
        }
        this.H = this;
        this.A = (TextView) inflate.findViewById(R.id.cmv);
        this.A.setOnClickListener(this);
        this.A.setClickable(true);
        this.A.setEnabled(true);
        this.y = (RelativeLayout) inflate.findViewById(R.id.cmu);
        this.E = (DJITextView) inflate.findViewById(R.id.cmz);
        this.B = (TextView) inflate.findViewById(R.id.cmx);
        this.B.setOnClickListener(this);
        this.F = new dji.pilot2.publics.object.b(this.r);
        if (this.C) {
            this.E.go();
        }
        this.s.setGroupIndicator(null);
        this.s.setOnGroupClickListener(new OnGroupClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                return true;
            }
        });
        this.u.a(this.w);
        if (this.C) {
            this.y.setVisibility(8);
            this.B.setVisibility(0);
            if (this.u.getGroupCount() > 0) {
                this.s.setAdapter(this.u);
                this.u.notifyDataSetChanged();
                for (int i = 0; i < this.u.getGroupCount(); i++) {
                    if (this.s != null) {
                        this.s.expandGroup(i);
                    }
                }
            }
        } else {
            new a(this).execute(new Void[0]);
            this.w.a(3, 0);
            this.E.show();
            this.t.resetLists();
            this.A.setClickable(false);
            this.A.setEnabled(false);
        }
        return inflate;
    }

    public void a(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this.r, R.anim.bh));
    }

    public void b(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this.r, R.anim.bo));
    }

    public void onDestroy() {
        if (this.v != null) {
            this.v.clear();
        }
        if (this.t != null) {
            this.t.resetLists();
        }
        this.C = false;
        super.onDestroy();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cmv:
                onDestroy();
                getActivity().getFragmentManager().beginTransaction().hide(this).commit();
                this.w.b();
                this.z.exitSelectMode();
                if (this.q) {
                    e.b(c$l.e);
                    return;
                } else {
                    e.b(c$l.i);
                    return;
                }
            case R.id.cmx:
                if (this.G == null) {
                    this.G = this.F.setMessage(this.r.getResources().getString(R.string.v2_lib_suredelete)).setPositiveButton(this.r.getResources().getString(R.string.delete), new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.z.deleteVideoView();
                            this.a.onDestroy();
                            this.a.getActivity().getFragmentManager().beginTransaction().hide(this.a.H).commit();
                            this.a.w.b();
                        }
                    }).setNegativeButton(this.r.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).show();
                    return;
                } else if (!this.G.isShowing()) {
                    this.G.show();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }
}
