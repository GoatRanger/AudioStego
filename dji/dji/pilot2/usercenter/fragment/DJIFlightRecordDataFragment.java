package dji.pilot2.usercenter.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.model.DJIGeocoderResult;
import dji.pilot.fpv.model.DJIGeocoderResult.FirstLevel;
import dji.pilot.fpv.model.DJIGeocoderResult.SecondLevel;
import dji.pilot.fpv.model.f;
import dji.pilot.fpv.model.i;
import dji.pilot.fpv.model.k;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJISwipeListView;
import dji.pilot.usercenter.b.d;
import dji.pilot.usercenter.widget.DJIRoundImageView;
import dji.pilot2.DJIFragmentActivityNoFullScreen;
import dji.pilot2.publics.b.a$j;
import dji.pilot2.usercenter.activity.DJIFlightRecordActivity;
import dji.pilot2.usercenter.activity.DJIFlightRecordPlayerActivity;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJITextView;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class DJIFlightRecordDataFragment extends Fragment {
    private DJISwipeListView a = null;
    private DJILinearLayout b = null;
    private DJIStateImageView c = null;
    private int d = 0;
    private DJITextView e = null;
    private DJITextView f = null;
    private DJITextView g = null;
    private DJITextView h = null;
    private DJITextView i = null;
    private ListView j = null;
    private DJIStateImageView k = null;
    private boolean l = false;
    private DJIStateImageView m = null;
    private DecimalFormat n = new DecimalFormat("###,###,###,###");
    private int o = 0;
    private int p = 0;
    private final int[] q = new int[]{0, 0, 0};
    private dji.pilot.usercenter.activity.b r = null;
    private f s = null;
    private View t = null;
    private boolean u = false;
    private OnClickListener v = new OnClickListener(this) {
        final /* synthetic */ DJIFlightRecordDataFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            boolean z = false;
            boolean z2 = true;
            Object tag;
            switch (view.getId()) {
                case R.id.d1x:
                    ((DJIFlightRecordActivity) this.a.getActivity()).i();
                    return;
                case R.id.d1y:
                    DJIFlightRecordDataFragment dJIFlightRecordDataFragment = this.a;
                    if (this.a.j.getVisibility() == 0) {
                        z2 = false;
                    }
                    dJIFlightRecordDataFragment.a(z2);
                    return;
                case R.id.d20:
                    if (!((DJIFlightRecordActivity) this.a.getActivity()).g()) {
                        ((DJIFlightRecordActivity) this.a.getActivity()).e();
                        return;
                    }
                    return;
                case R.id.d23:
                    this.a.a(this.a.e, true, false);
                    return;
                case R.id.d24:
                    DJIStateImageView h = this.a.c;
                    if (!this.a.c.isSelected()) {
                        z = true;
                    }
                    h.setSelected(z);
                    this.a.a(this.a.t != null ? this.a.t : this.a.e, true, true);
                    return;
                case R.id.d26:
                    this.a.a(this.a.f, true, false);
                    return;
                case R.id.d28:
                    this.a.a(this.a.g, true, false);
                    return;
                case R.id.d2_:
                    this.a.a(this.a.h, true, false);
                    return;
                case R.id.d2v:
                    tag = view.getTag();
                    if (tag instanceof String) {
                        try {
                            this.a.c(Integer.parseInt((String) tag));
                            return;
                        } catch (Exception e) {
                            return;
                        }
                    }
                    return;
                case R.id.d2z:
                    tag = view.getTag();
                    if (tag instanceof String) {
                        try {
                            this.a.b(Integer.parseInt((String) tag));
                            return;
                        } catch (Exception e2) {
                            return;
                        }
                    }
                    return;
                case R.id.d30:
                    tag = view.getTag();
                    if (tag instanceof String) {
                        try {
                            this.a.a(Integer.parseInt((String) tag));
                            return;
                        } catch (Exception e3) {
                            return;
                        }
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private b w = null;
    private a x = null;

    private final class a extends BaseAdapter {
        final /* synthetic */ DJIFlightRecordDataFragment a;
        private final LayoutInflater b;

        public a(DJIFlightRecordDataFragment dJIFlightRecordDataFragment, Context context) {
            this.a = dJIFlightRecordDataFragment;
            this.b = LayoutInflater.from(context);
        }

        public int getCount() {
            return ((DJIFlightRecordActivity) this.a.getActivity()).a().size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.b.inflate(R.layout.v2_usercenter_flightrecord_drone_type_list_item, null);
            }
            DJITextView dJITextView = (DJITextView) view.findViewById(R.id.d31);
            if (i == 0) {
                dJITextView.setText(this.a.getActivity().getString(R.string.flight_record_fragment_data_title_all));
            } else {
                dJITextView.setText(((dji.pilot.usercenter.b.d.a) ((DJIFlightRecordActivity) this.a.getActivity()).a().get(i)).a.mAircraftName);
            }
            if (i == this.a.d) {
                dJITextView.setTextColor(this.a.getActivity().getResources().getColor(R.color.at));
            } else {
                dJITextView.setTextColor(this.a.getActivity().getResources().getColor(R.color.om));
            }
            return view;
        }
    }

    private final class b extends BaseAdapter {
        private static final String e = "%1$02d/%2$02d/%3$2d";
        private static final String f = "%1$02d:%2$02d:%3$02d";
        final /* synthetic */ DJIFlightRecordDataFragment a;
        private final LayoutInflater b;
        private final GregorianCalendar c = new GregorianCalendar();
        private final GregorianCalendar d = new GregorianCalendar();

        public b(DJIFlightRecordDataFragment dJIFlightRecordDataFragment, Context context) {
            this.a = dJIFlightRecordDataFragment;
            this.b = LayoutInflater.from(context);
        }

        public int getCount() {
            List a = ((DJIFlightRecordActivity) this.a.getActivity()).a();
            if (this.a.d >= a.size()) {
                return 0;
            }
            return ((dji.pilot.usercenter.b.d.a) a.get(this.a.d)).b.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public boolean isEnabled(int i) {
            return !this.a.a.isRightShow();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            c cVar;
            if (view == null) {
                c cVar2 = new c();
                view = this.b.inflate(R.layout.v2_usercenter_flightrecord_data_item, null);
                cVar2.a = (DJIImageView) view.findViewById(R.id.d2k);
                cVar2.b = (DJIImageView) view.findViewById(R.id.d2l);
                cVar2.c = (DJITextView) view.findViewById(R.id.d2m);
                cVar2.d = (DJITextView) view.findViewById(R.id.d2n);
                cVar2.e = (DJITextView) view.findViewById(R.id.d2o);
                cVar2.f = (DJITextView) view.findViewById(R.id.d2p);
                cVar2.g = (DJITextView) view.findViewById(R.id.d2q);
                cVar2.h = (DJITextView) view.findViewById(R.id.d2s);
                cVar2.i = (DJITextView) view.findViewById(R.id.d2u);
                cVar2.j = view.findViewById(R.id.d2v);
                cVar2.k = (DJIRoundImageView) view.findViewById(R.id.d2w);
                cVar2.l = (DJIRoundImageView) view.findViewById(R.id.d2x);
                cVar2.m = (DJIRoundImageView) view.findViewById(R.id.d2y);
                cVar2.p = (DJIImageView) view.findViewById(R.id.c2r);
                int dimension = (int) this.a.getActivity().getResources().getDimension(R.dimen.ga);
                cVar2.k.setRound(dimension);
                cVar2.l.setRound(dimension);
                cVar2.m.setRound(dimension);
                cVar2.n = (DJIImageView) view.findViewById(R.id.d2z);
                cVar2.o = (DJIImageView) view.findViewById(R.id.d30);
                dji.pilot.publics.e.f.a(view.findViewById(R.id.d2r), this.a.o);
                dji.pilot.publics.e.f.a(view.findViewById(R.id.d2t), this.a.p);
                dji.pilot.publics.e.f.a(cVar2.k, this.a.q[0]);
                dji.pilot.publics.e.f.a(cVar2.l, this.a.q[1]);
                dji.pilot.publics.e.f.a(cVar2.m, this.a.q[2]);
                view.setTag(cVar2);
                cVar = cVar2;
            } else {
                cVar = (c) view.getTag();
            }
            if (i % 2 == 1) {
                view.setBackgroundResource(R.drawable.flight_record_data_item_bg1);
            } else {
                view.setBackgroundResource(R.drawable.flight_record_data_item_bg2);
            }
            cVar.n.setOnClickListener(this.a.v);
            cVar.o.setOnClickListener(this.a.v);
            cVar.j.setOnClickListener(this.a.v);
            cVar.n.setTag(String.valueOf(i));
            cVar.o.setTag(String.valueOf(i));
            cVar.j.setTag(String.valueOf(i));
            f fVar = (f) ((dji.pilot.usercenter.b.d.a) ((DJIFlightRecordActivity) this.a.getActivity()).a().get(this.a.d)).b.get(i);
            if (fVar != null) {
                cVar.c.setText(a(fVar.C));
                CharSequence charSequence = fVar.v;
                if (!dji.pilot.fpv.d.b.a(fVar.E) || !dji.pilot.fpv.d.b.b(fVar.D)) {
                    charSequence = this.a.getString(R.string.flight_record_nogps);
                } else if (k.b.equals(fVar.v)) {
                    charSequence = this.a.getString(R.string.flight_record_maploading);
                    a(fVar);
                }
                cVar.d.setText(charSequence);
                int[] e = dji.pilot.fpv.d.b.e(fVar.G / 1000);
                if (e[0] > 0) {
                    e[1] = e[1] + 1;
                }
                cVar.f.setText(this.a.getString(R.string.flight_record_minute_format, new Object[]{Integer.valueOf(e[1])}));
                float f = fVar.H;
                if (f - ((float) ((int) f)) > 0.0f) {
                    f = (float) (((int) f) + 1);
                }
                float f2 = fVar.F;
                if (f2 - ((float) ((int) f2)) > 0.0f) {
                    f2 = (float) (((int) f2) + 1);
                }
                if (DJIGenSettingDataManager.getInstance().v() == 1 || DJIGenSettingDataManager.getInstance().v() == 2) {
                    cVar.g.setText(this.a.getString(R.string.flight_record_distance_m_format, new Object[]{this.a.n.format((double) f)}));
                    cVar.e.setText(this.a.getString(R.string.flight_record_distance_m_format, new Object[]{this.a.n.format((double) f2)}));
                } else {
                    cVar.g.setText(this.a.getString(R.string.flight_record_distance_ft_format, new Object[]{this.a.n.format((double) DJIGenSettingDataManager.getInstance().b(f))}));
                    cVar.e.setText(this.a.getString(R.string.flight_record_distance_ft_format, new Object[]{this.a.n.format((double) DJIGenSettingDataManager.getInstance().b(f2))}));
                }
                cVar.h.setText(String.valueOf(fVar.K));
                e = dji.pilot.fpv.d.b.e((int) fVar.L);
                cVar.i.setText(String.format(Locale.US, "%1$02d:%2$02d", new Object[]{Integer.valueOf(e[1]), Integer.valueOf(e[0])}));
                List e2 = fVar.e();
                int size = e2 != null ? e2.size() : 0;
                int i2 = 0;
                while (i2 < size && i2 < 3) {
                    a(i2, cVar).setImageBitmap((Bitmap) e2.get(i2));
                    i2++;
                }
                for (int i3 = i2; i3 < 3; i3++) {
                    a(i3, cVar).setImageResource(R.drawable.my_flight_nullmoment);
                }
                if (fVar.x == (byte) 1) {
                    cVar.b.show();
                    cVar.a.go();
                } else if (fVar.A == (byte) 1) {
                    cVar.a.show();
                    cVar.b.go();
                } else {
                    cVar.a.go();
                    cVar.b.go();
                }
                cVar.n.setSelected(fVar.x == (byte) 1);
                if (fVar.a() == 1) {
                    cVar.p.go();
                } else {
                    cVar.p.show();
                }
            }
            return view;
        }

        private DJIImageView a(int i, c cVar) {
            if (i == 0) {
                return cVar.k;
            }
            if (i == 1) {
                return cVar.l;
            }
            if (i == 2) {
                return cVar.m;
            }
            return null;
        }

        private String a(long j) {
            this.d.setTimeInMillis(j);
            int i = this.d.get(1);
            int i2 = this.d.get(2) + 1;
            int i3 = this.d.get(5);
            int i4 = this.d.get(11);
            int i5 = this.d.get(12);
            int i6 = this.d.get(13);
            int i7 = this.c.get(1);
            int i8 = this.c.get(2) + 1;
            int i9 = this.c.get(5);
            if (i == i7 && i2 == i8 && i3 == i9) {
                return String.format(Locale.US, f, new Object[]{Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6)});
            } else if (i == i7 && i2 == i8 && i3 == i9 - 1) {
                return this.a.getString(R.string.flight_record_yesterday);
            } else {
                return String.format(Locale.US, e, new Object[]{Integer.valueOf(i3), Integer.valueOf(i2), Integer.valueOf(i)});
            }
        }

        private void a(final f fVar) {
            final Context applicationContext = this.a.getActivity().getApplicationContext();
            if (f.a(fVar.v)) {
                DJIGeocoderResult.get(applicationContext, fVar.E, fVar.D, new com.dji.frame.b.c(this) {
                    final /* synthetic */ b c;

                    public void a(Object obj) {
                        if (obj != null) {
                            DJIGeocoderResult dJIGeocoderResult = (DJIGeocoderResult) obj;
                            if (dJIGeocoderResult.status.equals("OK")) {
                                FirstLevel streetAdress = DJIGeocoderResult.getStreetAdress(dJIGeocoderResult);
                                if (streetAdress != null) {
                                    Iterator it = streetAdress.address_components.iterator();
                                    while (it.hasNext()) {
                                        SecondLevel secondLevel = (SecondLevel) it.next();
                                        if (secondLevel.types.contains("administrative_area_level_1")) {
                                            fVar.w = secondLevel.long_name;
                                        } else if (secondLevel.types.contains("administrative_area_level_2") || secondLevel.types.contains(dji.pilot.usercenter.protocol.c.W)) {
                                            fVar.v = secondLevel.long_name;
                                        } else if (secondLevel.types.contains("sublocality")) {
                                            fVar.u = secondLevel.long_name;
                                        } else if (secondLevel.types.contains("route")) {
                                            fVar.t = secondLevel.long_name;
                                        }
                                    }
                                    fVar.a(fVar.a() == 1 ? 1 : 2);
                                    i.b(applicationContext, fVar);
                                    if (this.c.a.w != null) {
                                        this.c.a.w.notifyDataSetChanged();
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    private static final class c {
        public DJIImageView a;
        public DJIImageView b;
        public DJITextView c;
        public DJITextView d;
        public DJITextView e;
        public DJITextView f;
        public DJITextView g;
        public DJITextView h;
        public DJITextView i;
        public View j;
        public DJIRoundImageView k;
        public DJIRoundImageView l;
        public DJIRoundImageView m;
        public DJIImageView n;
        public DJIImageView o;
        public DJIImageView p;

        private c() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
            this.i = null;
            this.j = null;
            this.k = null;
            this.l = null;
            this.m = null;
            this.n = null;
            this.o = null;
            this.p = null;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.v2_usercenter_flightrecord_data_fragment, null);
        this.a = (DJISwipeListView) inflate.findViewById(R.id.d2e);
        this.b = (DJILinearLayout) inflate.findViewById(R.id.d2f);
        this.c = (DJIStateImageView) inflate.findViewById(R.id.d24);
        this.c.setOnClickListener(this.v);
        this.m = (DJIStateImageView) inflate.findViewById(R.id.d21);
        inflate.findViewById(R.id.d23).setOnClickListener(this.v);
        inflate.findViewById(R.id.d26).setOnClickListener(this.v);
        inflate.findViewById(R.id.d28).setOnClickListener(this.v);
        inflate.findViewById(R.id.d2_).setOnClickListener(this.v);
        inflate.findViewById(R.id.d20).setOnClickListener(this.v);
        this.k = (DJIStateImageView) inflate.findViewById(R.id.d1x);
        int i = DJIFragmentActivityNoFullScreen.ev_;
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            this.k.setOnClickListener(this.v);
            this.k.show();
        } else {
            this.k.go();
            i = (int) ((((float) DJIFragmentActivityNoFullScreen.cK_) * 7.0f) / 11.0f);
        }
        a(i, inflate);
        this.i = (DJITextView) inflate.findViewById(R.id.d1y);
        this.i.setOnClickListener(this.v);
        this.j = (ListView) inflate.findViewById(R.id.d2i);
        this.x = new a(this, getActivity());
        this.j.setAdapter(this.x);
        this.j.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DJIFlightRecordDataFragment a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    this.a.i.setText(String.format(this.a.getActivity().getString(R.string.flight_record_fragment_data_title), new Object[]{this.a.getActivity().getString(R.string.flight_record_fragment_data_title_all)}));
                } else {
                    this.a.i.setText(String.format(this.a.getActivity().getString(R.string.flight_record_fragment_data_title), new Object[]{((dji.pilot.usercenter.b.d.a) ((DJIFlightRecordActivity) this.a.getActivity()).a().get(i)).a.mAircraftName}));
                }
                this.a.d = i;
                this.a.i.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.my_flight_downitemable, 0);
                this.a.j.setVisibility(8);
                ((DJIFlightRecordActivity) this.a.getActivity()).a(i);
                dji.thirdparty.a.c.a().e(dji.pilot2.usercenter.activity.DJIFlightRecordActivity.a.DataItemChanged);
                this.a.a();
            }
        });
        this.i.setText(String.format(getActivity().getString(R.string.flight_record_fragment_data_title), new Object[]{getActivity().getString(R.string.flight_record_fragment_data_title_all)}));
        this.e = (DJITextView) inflate.findViewById(R.id.d25);
        this.f = (DJITextView) inflate.findViewById(R.id.d27);
        this.g = (DJITextView) inflate.findViewById(R.id.d29);
        this.h = (DJITextView) inflate.findViewById(R.id.d2a);
        this.e.setCompoundDrawablesWithIntrinsicBounds(0, 0, this.u ? R.drawable.flight_record_sort_max : R.drawable.flight_record_sort_min, 0);
        this.w = new b(this, getActivity());
        this.a.setRightViewWidth((int) (getResources().getDimension(R.dimen.hf) * 2.0f));
        this.a.setAdapter(this.w);
        this.a.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DJIFlightRecordDataFragment a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (!this.a.l) {
                    this.a.l = true;
                    e.a("UserCenter_FlightRecord_TopBarView_Button_ItemSelected");
                    List a = ((DJIFlightRecordActivity) this.a.getActivity()).a();
                    f fVar = (f) ((dji.pilot.usercenter.b.d.a) a.get(this.a.d)).b.get(i);
                    if (fVar != null) {
                        d.getInstance().a(a, this.a.d);
                        if (fVar.A != (byte) 2) {
                            fVar.A = (byte) 2;
                            d.getInstance().b(fVar);
                            this.a.w.notifyDataSetChanged();
                            ((DJIFlightRecordActivity) this.a.getActivity()).h();
                        }
                        Intent intent = new Intent(this.a.getActivity(), DJIFlightRecordPlayerActivity.class);
                        intent.putExtra("POSITION", i);
                        this.a.startActivityForResult(intent, 100);
                    }
                }
            }
        });
        inflate.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ DJIFlightRecordDataFragment a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        return inflate;
    }

    private void a(int i, View view) {
        DJITextView dJITextView = (DJITextView) view.findViewById(R.id.d2b);
        DJITextView dJITextView2 = (DJITextView) view.findViewById(R.id.d2c);
        DJITextView dJITextView3 = (DJITextView) view.findViewById(R.id.d2d);
        Resources resources = getActivity().getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.hh);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.hc);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.hi);
        int dimensionPixelSize4 = resources.getDimensionPixelSize(R.dimen.hg);
        int dimensionPixelSize5 = resources.getDimensionPixelSize(R.dimen.hb);
        int dimensionPixelSize6 = resources.getDimensionPixelSize(R.dimen.hk);
        int dimensionPixelSize7 = resources.getDimensionPixelSize(R.dimen.yb) + resources.getDimensionPixelSize(R.dimen.hr);
        dimensionPixelSize = (((i - dimensionPixelSize) - dimensionPixelSize2) - dimensionPixelSize3) - dimensionPixelSize4;
        if (((dimensionPixelSize - dimensionPixelSize5) - dimensionPixelSize6) - (dimensionPixelSize7 * 3) >= 0) {
            this.o = 0;
            this.p = 0;
            this.q[1] = 0;
            this.q[2] = 0;
        } else if (((dimensionPixelSize - dimensionPixelSize5) - dimensionPixelSize6) - (dimensionPixelSize7 * 2) >= 0) {
            this.o = 0;
            this.p = 0;
            this.q[1] = 0;
            this.q[2] = 8;
        } else if (((dimensionPixelSize - dimensionPixelSize5) - dimensionPixelSize6) - dimensionPixelSize7 > 0) {
            this.o = 0;
            this.p = 0;
            this.q[0] = 0;
            this.q[1] = 8;
            this.q[2] = 8;
            dJITextView3.go();
        } else if ((dimensionPixelSize - dimensionPixelSize5) - dimensionPixelSize7 > 0) {
            this.o = 0;
            this.p = 8;
            this.q[0] = 0;
            this.q[1] = 8;
            this.q[2] = 8;
            dJITextView2.go();
            dJITextView3.go();
        } else if (dimensionPixelSize - dimensionPixelSize7 > 0) {
            this.o = 8;
            this.p = 8;
            this.q[0] = 0;
            this.q[1] = 8;
            this.q[2] = 8;
            dJITextView.go();
            dJITextView3.go();
            dJITextView2.go();
        } else {
            this.o = 8;
            this.p = 8;
            this.q[0] = 8;
            this.q[1] = 8;
            this.q[2] = 8;
            dJITextView3.go();
            dJITextView2.go();
            dJITextView.go();
        }
    }

    public void onStart() {
        super.onStart();
        int j = ((DJIFlightRecordActivity) getActivity()).j();
        if (this.d != j) {
            this.d = j;
            if (j == 0) {
                this.i.setText(String.format(getActivity().getString(R.string.flight_record_fragment_data_title), new Object[]{getActivity().getString(R.string.flight_record_fragment_data_title_all)}));
            } else {
                this.i.setText(String.format(getActivity().getString(R.string.flight_record_fragment_data_title), new Object[]{((dji.pilot.usercenter.b.d.a) ((DJIFlightRecordActivity) getActivity()).a().get(j)).a.mAircraftName}));
            }
        }
        a();
        onEventMainThread(d.getInstance().h() ? d.i.Start : d.i.Stop);
        dji.thirdparty.a.c.a().a(this);
    }

    private void a() {
        this.w.notifyDataSetChanged();
        this.x.notifyDataSetChanged();
        if (this.w.getCount() > 0) {
            this.a.show();
            this.b.go();
            return;
        }
        this.a.go();
        this.b.show();
    }

    public void onResume() {
        super.onResume();
        this.l = false;
    }

    public void onStop() {
        dji.thirdparty.a.c.a().d(this);
        super.onStop();
    }

    public void onEventMainThread(dji.pilot2.usercenter.activity.DJIFlightRecordActivity.a aVar) {
        if (aVar == dji.pilot2.usercenter.activity.DJIFlightRecordActivity.a.Changed) {
            a();
        } else if (aVar == dji.pilot2.usercenter.activity.DJIFlightRecordActivity.a.OverviewChanged) {
            int j = ((DJIFlightRecordActivity) getActivity()).j();
            if (this.d != j) {
                this.d = j;
                if (j == 0) {
                    this.i.setText(String.format(getActivity().getString(R.string.flight_record_fragment_data_title), new Object[]{getActivity().getString(R.string.flight_record_fragment_data_title_all)}));
                } else {
                    this.i.setText(String.format(getActivity().getString(R.string.flight_record_fragment_data_title), new Object[]{((dji.pilot.usercenter.b.d.a) ((DJIFlightRecordActivity) getActivity()).a().get(j)).a.mAircraftName}));
                }
                a();
            }
        }
    }

    public void onEventMainThread(d.i iVar) {
        if (iVar == d.i.Start) {
            this.m.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.i));
        } else {
            this.m.setAnimation(null);
        }
    }

    private void a(int i) {
        List a = ((DJIFlightRecordActivity) getActivity()).a();
        dji.pilot.usercenter.b.d.a aVar = (dji.pilot.usercenter.b.d.a) a.get(this.d);
        f fVar = (f) aVar.b.get(i);
        if (fVar != null) {
            String str = d.a(fVar.P, true) ? dji.pilot.usercenter.protocol.c.T : fVar.P;
            int size = a.size();
            for (int i2 = 0; i2 < size; i2++) {
                dji.pilot.usercenter.b.d.a aVar2 = (dji.pilot.usercenter.b.d.a) a.get(i2);
                if (str.equals(aVar2.a.mBoardNum) || dji.pilot.usercenter.protocol.c.T.equals(aVar2.a.mBoardNum)) {
                    d.getInstance().a(aVar, fVar, false);
                    if (i2 != 0) {
                        break;
                    }
                }
            }
            d.getInstance().a(aVar, fVar, true);
            d.getInstance().a(fVar);
            this.a.hiddenRight();
            a();
        }
    }

    private void b(int i) {
        byte b = (byte) 1;
        f fVar = (f) ((dji.pilot.usercenter.b.d.a) ((DJIFlightRecordActivity) getActivity()).a().get(this.d)).b.get(i);
        if (fVar != null) {
            if (fVar.x == (byte) 1) {
                b = (byte) 2;
            }
            fVar.x = b;
            d.getInstance().b(fVar);
            this.a.hiddenRight();
            a();
        }
    }

    private List<Bitmap> a(f fVar) {
        List<Bitmap> list = null;
        try {
            list = fVar.a(getActivity());
        } catch (Exception e) {
        } catch (OutOfMemoryError e2) {
        }
        return list;
    }

    private void b() {
        if (this.r == null) {
            this.r = new dji.pilot.usercenter.activity.b(getActivity());
            this.r.setOnDismissListener(new OnDismissListener(this) {
                final /* synthetic */ DJIFlightRecordDataFragment a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                    if (this.a.s != null) {
                        this.a.s.f();
                        this.a.s = null;
                    }
                    this.a.r = null;
                }
            });
        }
        if (this.r != null && !this.r.isShowing()) {
            this.r.a(a(this.s));
            this.r.show();
            Log.d(a$j.f, "mPhotoLookDlg=");
        }
    }

    private void c(int i) {
        f fVar = (f) ((dji.pilot.usercenter.b.d.a) ((DJIFlightRecordActivity) getActivity()).a().get(this.d)).b.get(i);
        Log.d(a$j.f, "pos=" + i);
        if (fVar != null) {
            List a = a(fVar);
            if (a != null && !a.isEmpty()) {
                this.s = fVar;
                Log.d(a$j.f, "showdialog=" + i);
                b();
            }
        }
    }

    private void a(View view, boolean z, boolean z2) {
        dji.pilot.fpv.model.f.a aVar;
        int i = R.drawable.flight_record_sort_max;
        boolean z3 = true;
        dji.pilot.fpv.model.f.a aVar2 = dji.pilot.fpv.model.f.a.c;
        this.e.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.flight_record_unsort, 0);
        this.f.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.flight_record_unsort, 0);
        this.g.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.flight_record_unsort, 0);
        this.h.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.flight_record_unsort, 0);
        if (view == this.e) {
            aVar2 = dji.pilot.fpv.model.f.a.c;
            if (this.t != this.e) {
                this.u = false;
            } else if (!z2) {
                if (this.u) {
                    z3 = false;
                }
                this.u = z3;
            }
            this.t = this.e;
            this.e.setCompoundDrawablesWithIntrinsicBounds(0, 0, this.u ? R.drawable.flight_record_sort_max : R.drawable.flight_record_sort_min, 0);
            aVar = aVar2;
        } else if (view == this.f) {
            aVar2 = dji.pilot.fpv.model.f.a.d;
            if (this.t != this.f) {
                this.u = false;
            } else if (!z2) {
                if (this.u) {
                    z3 = false;
                }
                this.u = z3;
            }
            this.t = this.f;
            if (!this.u) {
                i = R.drawable.flight_record_sort_min;
            }
            this.f.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
            aVar = aVar2;
        } else if (view == this.g) {
            aVar2 = dji.pilot.fpv.model.f.a.e;
            if (this.t != this.g) {
                this.u = false;
            } else if (!z2) {
                if (this.u) {
                    z3 = false;
                }
                this.u = z3;
            }
            this.t = this.g;
            if (!this.u) {
                i = R.drawable.flight_record_sort_min;
            }
            this.g.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
            aVar = aVar2;
        } else {
            if (view == this.h) {
                aVar2 = dji.pilot.fpv.model.f.a.f;
                if (this.t != this.h) {
                    this.u = false;
                } else if (!z2) {
                    if (this.u) {
                        z3 = false;
                    }
                    this.u = z3;
                }
                this.t = this.h;
                if (!this.u) {
                    i = R.drawable.flight_record_sort_min;
                }
                this.h.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
            }
            aVar = aVar2;
        }
        for (dji.pilot.usercenter.b.d.a aVar3 : ((DJIFlightRecordActivity) getActivity()).a()) {
            i.a(aVar3.b, aVar, this.u, this.c.isSelected());
        }
        if (z) {
            a();
        }
        ((DJIFlightRecordActivity) getActivity()).a(this.d);
    }

    public void a(boolean z) {
        int i = 0;
        if (this.j.getVisibility() != (z ? 0 : 8)) {
            this.i.setCompoundDrawablesWithIntrinsicBounds(0, 0, z ? R.drawable.my_flight_upitemable : R.drawable.my_flight_downitemable, 0);
            ListView listView = this.j;
            if (!z) {
                i = 8;
            }
            listView.setVisibility(i);
        }
    }
}
