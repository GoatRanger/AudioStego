package dji.pilot2.main.fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.d;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataRcGetControlMode;
import dji.midware.data.model.P3.DataRcSetControlMode.ControlMode;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.util.i;
import dji.pilot.R;
import dji.pilot.main.activity.DJIHubActivity;
import dji.pilot2.main.activity.DJIQuickStartActivity;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIListView;
import dji.publics.DJIUI.DJIRelativeLayout;

public class b extends Fragment implements OnItemClickListener {
    private boolean A = false;
    private Handler B = new Handler(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == this.a.y) {
                dji.pilot2.publics.object.b bVar = new dji.pilot2.publics.object.b(this.a.getActivity());
                bVar.setMessage(R.string.quick_setfail_toast);
                bVar.setPositiveButton(R.string.quick_skip_button, new OnClickListener(this) {
                    final /* synthetic */ AnonymousClass7 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        this.a.a.A = true;
                        ((DJIQuickStartActivity) this.a.a.getActivity()).a(false);
                    }
                });
                bVar.setNegativeButton(17039369, new OnClickListener(this) {
                    final /* synthetic */ AnonymousClass7 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (this.a.a.b == R.layout.fragment_quick_four && this.a.a.getUserVisibleHint()) {
                            DJILogHelper.getInstance().LOGI("bob", "onClick cancel xxx");
                            this.a.a.c();
                        }
                    }
                });
                bVar.show();
            }
            if (message.what != this.a.z) {
            }
        }
    };
    private ExpandableListView a = null;
    private int b = -1;
    private a c;
    private Button d;
    private Button e;
    private RelativeLayout f;
    private RelativeLayout g;
    private View h;
    private View i;
    private View j;
    private View k;
    private View l;
    private View m;
    private View n;
    private View o;
    private DJIListView p;
    private View q;
    private ParamInfo r = null;
    private int s = 30;
    private Animation t;
    private Animation u;
    private Animation v;
    private Animation w;
    private DJIRelativeLayout x;
    private int y = 101;
    private int z = 102;

    public interface b {
        void a(int i);
    }

    public class a extends BaseExpandableListAdapter {
        public int[] a = new int[]{30, 50, 120};
        final /* synthetic */ b b;

        public a(b bVar) {
            this.b = bVar;
        }

        public int getGroupCount() {
            return 1;
        }

        public int getChildrenCount(int i) {
            return 0;
        }

        public Object getGroup(int i) {
            return null;
        }

        public Object getChild(int i, int i2) {
            return null;
        }

        public long getGroupId(int i) {
            return (long) i;
        }

        public long getChildId(int i, int i2) {
            return (long) i2;
        }

        public boolean hasStableIds() {
            return false;
        }

        public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
            if (view == null) {
                return LayoutInflater.from(this.b.getActivity()).inflate(R.layout.v2_quickstart_setup_item, null);
            }
            return view;
        }

        public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.b.getActivity()).inflate(R.layout.v2_quickstart_setup_item, null);
            }
            TextView textView = (TextView) view.findViewById(R.id.cz0);
            ((TextView) view.findViewById(R.id.cyy)).setTextColor(-5723474);
            ((TextView) view.findViewById(R.id.cz0)).setTextColor(-5723474);
            view.findViewById(R.id.cyz).setVisibility(4);
            textView.setText("" + this.a[i2] + "M");
            view.setTag(Integer.valueOf(this.a[i2]));
            return view;
        }

        public boolean isChildSelectable(int i, int i2) {
            return true;
        }
    }

    public b(int i) {
        this.b = i;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        View view = null;
        if (this.b != -1) {
            view = layoutInflater.inflate(this.b, viewGroup, false);
        }
        this.r = d.read("g_config.go_home.fixed_go_home_altitude_0");
        b(view);
        return view;
    }

    private void b(View view) {
        if (this.b != -1 && view != null) {
            TextView textView;
            switch (this.b) {
                case R.layout.fragment_quick_eight:
                    this.o = view.findViewById(R.id.adu);
                    ((AnimationDrawable) this.o.getBackground()).start();
                    this.g = (RelativeLayout) view.findViewById(R.id.ads);
                    this.e = (Button) view.findViewById(R.id.ady);
                    this.e.setOnClickListener(new View.OnClickListener(this) {
                        final /* synthetic */ b a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            i.a(this.a.getActivity(), "is_FirstTime", true);
                            this.a.getActivity().finish();
                            this.a.b();
                        }
                    });
                    return;
                case R.layout.fragment_quick_four:
                    this.p = (DJIListView) view.findViewById(R.id.aec);
                    this.q = view.findViewById(R.id.aea);
                    this.t = AnimationUtils.loadAnimation(getActivity(), R.anim.be);
                    this.u = AnimationUtils.loadAnimation(getActivity(), R.anim.bf);
                    this.v = AnimationUtils.loadAnimation(getActivity(), R.anim.bt);
                    this.w = AnimationUtils.loadAnimation(getActivity(), R.anim.bu);
                    this.q.setAnimation(this.v);
                    this.q.setVisibility(8);
                    this.x = (DJIRelativeLayout) view.findViewById(R.id.aeb);
                    this.x.setAnimation(this.t);
                    this.x.go();
                    ListAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.quick_item_bottom);
                    arrayAdapter.add("30M");
                    arrayAdapter.add("50M");
                    arrayAdapter.add("120M");
                    this.p.setAdapter(arrayAdapter);
                    this.p.setOnItemClickListener(this);
                    this.f = (RelativeLayout) view.findViewById(R.id.ae3);
                    this.d = (Button) view.findViewById(R.id.ae9);
                    this.d.setClickable(false);
                    this.a = (ExpandableListView) view.findViewById(R.id.ae_);
                    this.a.setGroupIndicator(null);
                    this.c = new a(this);
                    this.a.setOnGroupClickListener(new OnGroupClickListener(this) {
                        final /* synthetic */ b a;

                        {
                            this.a = r1;
                        }

                        public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                            this.a.c();
                            return true;
                        }
                    });
                    this.a.setOnChildClickListener(new OnChildClickListener(this) {
                        final /* synthetic */ b a;

                        {
                            this.a = r1;
                        }

                        public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
                            this.a.s = ((Integer) view.getTag()).intValue();
                            expandableListView.collapseGroup(i);
                            this.a.d.setBackground(this.a.getResources().getDrawable(R.drawable.v2_main_quickstart_button_selector));
                            this.a.d.setTextColor(this.a.getResources().getColor(R.color.mn));
                            this.a.d.setClickable(true);
                            this.a.A = false;
                            return true;
                        }
                    });
                    this.a.setOnGroupCollapseListener(new OnGroupCollapseListener(this) {
                        final /* synthetic */ b a;

                        {
                            this.a = r1;
                        }

                        public void onGroupCollapse(int i) {
                        }
                    });
                    this.d.setOnClickListener(new View.OnClickListener(this) {
                        final /* synthetic */ b a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            if (this.a.s != 0) {
                                new DataFlycSetParams().a(this.a.r.name, Integer.valueOf(this.a.s)).start(new dji.midware.e.d(this) {
                                    final /* synthetic */ AnonymousClass4 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void onSuccess(Object obj) {
                                        this.a.a.B.sendEmptyMessage(this.a.a.z);
                                        this.a.a.A = true;
                                        ((DJIQuickStartActivity) this.a.a.getActivity()).a(false);
                                    }

                                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                                        this.a.a.B.sendEmptyMessage(this.a.a.y);
                                    }
                                });
                            }
                        }
                    });
                    this.a.setAdapter(this.c);
                    return;
                case R.layout.fragment_quick_one:
                    this.h = view.findViewById(R.id.aed);
                    this.i = view.findViewById(R.id.aei);
                    return;
                case R.layout.fragment_quick_seven:
                    if (DataRcGetControlMode.getInstance().getControlType() == ControlMode.a) {
                        textView = (TextView) view.findViewById(R.id.aeq);
                        ((DJIImageView) view.findViewById(R.id.aek)).setImageResource(R.drawable.pic_quikstart_six_pic1);
                        textView.setText(R.string.v2_quickstart_six_4);
                        ((DJIImageView) view.findViewById(R.id.aen)).setImageResource(R.drawable.pic_quikstart_six_pic3);
                        ((TextView) view.findViewById(R.id.aep)).setText(R.string.v2_quickstart_six_2);
                        ((TextView) view.findViewById(R.id.aer)).setText(R.string.v2_quickstart_seven_6);
                        return;
                    }
                    return;
                case R.layout.fragment_quick_six:
                    if (DataRcGetControlMode.getInstance().getControlType() == ControlMode.a) {
                        ((DJIImageView) view.findViewById(R.id.aes)).setImageResource(R.drawable.pic_quikstart_seven_pic1);
                        ((TextView) view.findViewById(R.id.aew)).setText(R.string.v2_quickstart_seven_4);
                        ((DJIImageView) view.findViewById(R.id.aet)).setImageResource(R.drawable.pic_quikstart_seven_pic3);
                        ((TextView) view.findViewById(R.id.aev)).setText(R.string.v2_quickstart_seven_2);
                        ((TextView) view.findViewById(R.id.aex)).setText(R.string.v2_quickstart_six_6);
                        return;
                    }
                    return;
                case R.layout.fragment_quick_three:
                    this.l = view.findViewById(R.id.aez);
                    this.m = view.findViewById(R.id.af0);
                    this.n = view.findViewById(R.id.af3);
                    return;
                case R.layout.fragment_quick_two:
                    this.j = view.findViewById(R.id.af5);
                    TextView textView2 = (TextView) view.findViewById(R.id.af6);
                    textView = (TextView) view.findViewById(R.id.af8);
                    TextView textView3 = (TextView) view.findViewById(R.id.af_);
                    TextView textView4 = (TextView) view.findViewById(R.id.afb);
                    if (dji.midware.data.manager.P3.i.getInstance().c().isFromWifi()) {
                        this.j.setBackground(getResources().getDrawable(R.drawable.v2_quick_fragmenttwo_radio_p3c));
                        textView2.setText(getResources().getString(R.string.quick_fragment_two_content_p3c));
                        textView.setText("0-5");
                        textView3.setText("5-6");
                        textView4.setText("6-9");
                    }
                    this.k = view.findViewById(R.id.af7);
                    return;
                default:
                    return;
            }
        }
    }

    public void a() {
        if (this.s != 0 && !this.A && this.b == R.layout.fragment_quick_four) {
            new DataFlycSetParams().a(this.r.name, Integer.valueOf(this.s)).start(new dji.midware.e.d(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.B.sendEmptyMessage(this.a.z);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.B.sendEmptyMessage(this.a.y);
                }
            });
        }
    }

    public void a(int i) {
        if (getActivity() instanceof b) {
            ((b) getActivity()).a(i);
        }
    }

    public void a(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.a);
        if (view != null) {
            loadAnimation.setDuration(1000);
            view.setAnimation(loadAnimation);
            view.startAnimation(loadAnimation);
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            switch (this.b) {
                case R.layout.fragment_quick_eight:
                    a(7);
                    return;
                case R.layout.fragment_quick_five:
                    a(4);
                    return;
                case R.layout.fragment_quick_four:
                    a(3);
                    return;
                case R.layout.fragment_quick_one:
                    a(0);
                    return;
                case R.layout.fragment_quick_seven:
                    a(6);
                    return;
                case R.layout.fragment_quick_six:
                    a(5);
                    return;
                case R.layout.fragment_quick_three:
                    a(2);
                    this.l.setVisibility(0);
                    this.m.setVisibility(0);
                    this.n.setVisibility(0);
                    a(this.l);
                    a(this.m);
                    a(this.n);
                    return;
                case R.layout.fragment_quick_two:
                    a(1);
                    this.j.setVisibility(0);
                    this.k.setVisibility(0);
                    a(this.j);
                    a(this.k);
                    return;
                default:
                    return;
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onResume() {
        if (this.b == R.layout.fragment_quick_one) {
            this.h.setVisibility(0);
            this.i.setVisibility(0);
            a(this.h);
            a(this.i);
        }
        super.onResume();
    }

    private void b() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), DJIHubActivity.class);
        intent.putExtra(dji.pilot.c.b.a, true);
        startActivity(intent);
    }

    private void c() {
        a(101);
        this.x.animShow();
        this.q.setVisibility(0);
        this.x.startAnimation(this.t);
        this.q.startAnimation(this.v);
    }

    private void d() {
        this.x.go();
        this.q.setVisibility(8);
        this.x.startAnimation(this.u);
        a(102);
        TextView textView = (TextView) this.a.getChildAt(0).findViewById(R.id.cz0);
        textView.setVisibility(0);
        if (this.s != 0) {
            textView.setText("" + this.s + "M");
        }
        this.d.setClickable(true);
        a(3);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        switch (i) {
            case 0:
                this.s = 30;
                d();
                return;
            case 1:
                this.s = 50;
                d();
                return;
            case 2:
                this.s = 120;
                d();
                return;
            default:
                return;
        }
    }
}
