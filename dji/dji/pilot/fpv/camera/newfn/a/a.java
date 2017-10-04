package dji.pilot.fpv.camera.newfn.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.BaseExpandableListAdapter;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import dji.pilot.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.List;

public class a extends BaseExpandableListAdapter {
    private static final int[] a = new int[]{R.id.kc, R.id.kd, R.id.ke};
    private static final int b = a.length;
    private final LayoutInflater c;
    private List<dji.pilot.fpv.camera.newfn.b.b> d = null;
    private OnClickListener e = null;
    private c f = null;
    private OnSeekBarChangeListener g = null;

    public interface c {
        void a(dji.pilot.fpv.camera.newfn.b.b bVar, dji.pilot.fpv.camera.newfn.b.a aVar);

        void a(dji.pilot.fpv.camera.newfn.b.b bVar, dji.pilot.fpv.camera.newfn.b.a aVar, boolean z);

        void b(dji.pilot.fpv.camera.newfn.b.b bVar, dji.pilot.fpv.camera.newfn.b.a aVar);
    }

    private static final class a {
        public View a;
        public final DJITextView[] b;

        private a() {
            this.a = null;
            this.b = new DJITextView[a.b];
        }

        public void a(dji.pilot.fpv.camera.newfn.b.b bVar, int i) {
            int i2 = 0;
            int a = i * a.b;
            List list = bVar.k;
            for (int i3 = 0; i3 < a.b; i3++) {
                this.b[i3].hide();
            }
            while (a + i2 < list.size() && i2 < a.b) {
                dji.pilot.fpv.camera.newfn.b.a aVar = (dji.pilot.fpv.camera.newfn.b.a) list.get(a + i2);
                this.b[i2].setTag(aVar);
                this.b[i2].show();
                this.b[i2].setSelected(aVar.d);
                this.b[i2].setText(aVar.a);
                i2++;
            }
        }
    }

    private static final class b {
        public View a;
        public DJIImageView b;
        public DJITextView c;
        public DJITextView d;

        private b() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
        }

        public void a(dji.pilot.fpv.camera.newfn.b.b bVar) {
            if (bVar.d.contains("@T")) {
                this.c.setText(bVar.d.replace("@T", ""));
                return;
            }
            this.a.setSelected(bVar.h);
            if (bVar.h && !bVar.k.isEmpty() && bVar.j == 1) {
                this.d.show();
            } else {
                this.d.go();
            }
            if (bVar.c == 0) {
                this.b.go();
            } else {
                this.b.show();
                this.b.setImageResource(bVar.c);
            }
            this.c.setText(bVar.d);
            this.d.setText(bVar.e);
        }
    }

    private final class d {
        final /* synthetic */ a a;
        private SeekBar b;
        private DJITextView c;
        private long d;

        private d(a aVar) {
            this.a = aVar;
            this.b = null;
            this.c = null;
            this.d = 0;
        }

        public void a(dji.pilot.fpv.camera.newfn.b.b bVar, int i, int i2) {
            this.d = this.a.getChildId(i, i2);
            dji.pilot.fpv.camera.newfn.b.a aVar = (dji.pilot.fpv.camera.newfn.b.a) bVar.k.get(i2);
            this.b.setTag(this);
            this.b.setMax(aVar.g - aVar.f);
            this.b.setProgress(aVar.b - aVar.f);
            if (aVar.e instanceof String) {
                this.c.setText(String.valueOf(aVar.b) + ((String) aVar.e));
            } else {
                this.c.setText(String.valueOf(aVar.b));
            }
        }
    }

    public a(Context context) {
        this.c = LayoutInflater.from(context);
        this.g = new OnSeekBarChangeListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Object tag = seekBar.getTag();
                if (tag instanceof d) {
                    d dVar = (d) tag;
                    int a = (int) (dVar.d % 1000);
                    Object group = this.a.getGroup((int) (dVar.d / 1000));
                    if (group instanceof dji.pilot.fpv.camera.newfn.b.b) {
                        dji.pilot.fpv.camera.newfn.b.b bVar = (dji.pilot.fpv.camera.newfn.b.b) group;
                        dji.pilot.fpv.camera.newfn.b.a aVar = (dji.pilot.fpv.camera.newfn.b.a) bVar.k.get(a);
                        aVar.b = seekBar.getProgress() + aVar.f;
                        if (aVar.e instanceof String) {
                            dVar.c.setText(String.valueOf(aVar.b) + ((String) aVar.e));
                        } else {
                            dVar.c.setText(String.valueOf(aVar.b));
                        }
                        this.a.f.a(bVar, aVar);
                    }
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                Object tag = seekBar.getTag();
                if (tag instanceof d) {
                    d dVar = (d) tag;
                    int a = (int) (dVar.d % 1000);
                    tag = this.a.getGroup((int) (dVar.d / 1000));
                    if (tag instanceof dji.pilot.fpv.camera.newfn.b.b) {
                        dji.pilot.fpv.camera.newfn.b.b bVar = (dji.pilot.fpv.camera.newfn.b.b) tag;
                        this.a.f.b(bVar, (dji.pilot.fpv.camera.newfn.b.a) bVar.k.get(a));
                    }
                }
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                Object tag = seekBar.getTag();
                if (tag instanceof d) {
                    d dVar = (d) tag;
                    int a = (int) (dVar.d % 1000);
                    Object group = this.a.getGroup((int) (dVar.d / 1000));
                    if (group instanceof dji.pilot.fpv.camera.newfn.b.b) {
                        dji.pilot.fpv.camera.newfn.b.b bVar = (dji.pilot.fpv.camera.newfn.b.b) group;
                        dji.pilot.fpv.camera.newfn.b.a aVar = (dji.pilot.fpv.camera.newfn.b.a) bVar.k.get(a);
                        if (z) {
                            if (aVar.e instanceof String) {
                                dVar.c.setText(String.valueOf(aVar.f + i) + ((String) aVar.e));
                            } else {
                                dVar.c.setText(String.valueOf(aVar.f + i));
                            }
                        }
                        this.a.f.a(bVar, aVar, z);
                    }
                }
            }
        };
    }

    public void a(List<dji.pilot.fpv.camera.newfn.b.b> list) {
        this.d = list;
        notifyDataSetChanged();
    }

    public void a(OnClickListener onClickListener) {
        this.e = onClickListener;
    }

    public void a(c cVar) {
        this.f = cVar;
    }

    public int getGroupCount() {
        return this.d != null ? this.d.size() : 0;
    }

    public int getChildrenCount(int i) {
        if (this.d == null || this.d.isEmpty() || i < 0 || i >= this.d.size()) {
            return 0;
        }
        dji.pilot.fpv.camera.newfn.b.b bVar = (dji.pilot.fpv.camera.newfn.b.b) this.d.get(i);
        int size = bVar.k.size();
        if (size == 0 || bVar.j != 1) {
            return size;
        }
        return ((size - 1) / b) + 1;
    }

    public Object getGroup(int i) {
        if (this.d == null || this.d.isEmpty() || i < 0 || i >= this.d.size()) {
            return null;
        }
        return (dji.pilot.fpv.camera.newfn.b.b) this.d.get(i);
    }

    public Object getChild(int i, int i2) {
        return null;
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public long getChildId(int i, int i2) {
        return (long) ((i * 1000) + i2);
    }

    public boolean hasStableIds() {
        return false;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        Object group = getGroup(i);
        if (group instanceof dji.pilot.fpv.camera.newfn.b.b) {
            b bVar;
            b bVar2;
            if (((dji.pilot.fpv.camera.newfn.b.b) group).d.contains("@T")) {
                bVar2 = new b();
                view = this.c.inflate(R.layout.camera_newfn_base_expandview_title, null);
                view.setEnabled(false);
                bVar2.a = (DJILinearLayout) view.findViewById(R.id.kh);
                bVar2.c = (DJITextView) view.findViewById(R.id.kl);
                bVar = bVar2;
            } else if (view == null || view.getTag() == null) {
                bVar2 = new b();
                view = this.c.inflate(R.layout.camera_newfn_base_expandview_group, null);
                bVar2.a = (DJILinearLayout) view.findViewById(R.id.kh);
                bVar2.b = (DJIImageView) view.findViewById(R.id.ki);
                bVar2.c = (DJITextView) view.findViewById(R.id.kj);
                bVar2.d = (DJITextView) view.findViewById(R.id.kk);
                view.setTag(bVar2);
                bVar = bVar2;
            } else {
                bVar = (b) view.getTag();
            }
            bVar.a((dji.pilot.fpv.camera.newfn.b.b) group);
        }
        return view;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        Object group = getGroup(i);
        if (group instanceof dji.pilot.fpv.camera.newfn.b.b) {
            dji.pilot.fpv.camera.newfn.b.b bVar = (dji.pilot.fpv.camera.newfn.b.b) group;
            if (1 == bVar.j) {
                a aVar;
                if (view == null) {
                    a aVar2 = new a();
                    view = this.c.inflate(R.layout.camera_newfn_base_expandview_child, null);
                    aVar2.a = (DJILinearLayout) view.findViewById(R.id.kb);
                    for (int i3 = 0; i3 < b; i3++) {
                        aVar2.b[i3] = (DJITextView) view.findViewById(a[i3]);
                        aVar2.b[i3].setOnClickListener(this.e);
                    }
                    view.setTag(aVar2);
                    aVar = aVar2;
                } else {
                    aVar = (a) view.getTag();
                }
                aVar.a(bVar, i2);
            } else if (2 == bVar.j) {
                d dVar;
                if (view == null) {
                    d dVar2 = new d();
                    view = this.c.inflate(R.layout.camera_newfn_base_expandview_child_sb, null);
                    dVar2.b = (SeekBar) view.findViewById(R.id.kg);
                    dVar2.c = (DJITextView) view.findViewById(R.id.kf);
                    view.setTag(dVar2);
                    dVar2.b.setOnSeekBarChangeListener(this.g);
                    dVar = dVar2;
                } else {
                    dVar = (d) view.getTag();
                }
                dVar.a(bVar, i, i2);
            }
        }
        return view;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    protected void a(DJITextView dJITextView, SeekBar seekBar, int i, int i2) {
        int i3;
        int measureText = (int) dJITextView.getPaint().measureText(dJITextView.getText().toString());
        int i4 = seekBar.getThumb().getBounds().left;
        if (i4 <= 0) {
            i4 = seekBar.getWidth();
            while (i4 <= 0) {
                i4 = seekBar.getContext().getResources().getDimensionPixelSize(R.dimen.i9);
            }
            i3 = (i4 * i) / i2;
        } else {
            i3 = i4;
        }
        i3 += seekBar.getThumbOffset();
        i3 = dji.pilot.fpv.model.b.a(seekBar.getContext(), R.dimen.rp) + ((((MarginLayoutParams) seekBar.getLayoutParams()).leftMargin + i3) - (measureText / 2));
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) dJITextView.getLayoutParams();
        if (i3 != marginLayoutParams.leftMargin) {
            marginLayoutParams.leftMargin = i3;
            dJITextView.setLayoutParams(marginLayoutParams);
        }
    }
}
