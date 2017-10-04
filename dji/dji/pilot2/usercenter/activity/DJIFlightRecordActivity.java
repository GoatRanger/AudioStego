package dji.pilot2.usercenter.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.SlidingDrawer.OnDrawerScrollListener;
import android.widget.Toast;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.c.e;
import dji.pilot.fpv.d.c.h;
import dji.pilot.fpv.model.i;
import dji.pilot.fpv.model.k;
import dji.pilot.publics.e.f;
import dji.pilot.publics.objects.DJINetWorkReceiver;
import dji.pilot.publics.widget.DJISwipeListView;
import dji.pilot.usercenter.b.d;
import dji.pilot.usercenter.b.f$b;
import dji.pilot.usercenter.mode.FlightOverviewInfo;
import dji.pilot.usercenter.protocol.FlightRecordUserInfo;
import dji.pilot.usercenter.protocol.FlightRecordUserInfo.DroneList;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot.usercenter.widget.DJIRoundImageView;
import dji.pilot2.DJIActivityFullScreen;
import dji.pilot2.DJIFragmentActivityNoFullScreen;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.usercenter.fragment.DJIFlightRecordDataFragment;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJITextView;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import org.apache.http.util.EncodingUtils;

public class DJIFlightRecordActivity extends DJIActivityFullScreen implements e, h {
    private DJITextView A = null;
    private DJISwipeListView B = null;
    private DecimalFormat C = new DecimalFormat("###,###,###,###");
    private int D = 8;
    private int E = 8;
    private final int[] F = new int[]{0, 8, 8};
    private f$b G = new f$b(this) {
        final /* synthetic */ DJIFlightRecordActivity a;

        {
            this.a = r1;
        }

        public void a(String str, Object obj) {
            Toast.makeText(this.a, R.string.v2_invalidate_token, 1).show();
            this.a.startActivityForResult(new Intent(this.a, DJIAccountSignActivity.class), 1007);
        }
    };
    private int H = 0;
    private OnClickListener I = new OnClickListener(this) {
        final /* synthetic */ DJIFlightRecordActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            view.getId();
        }
    };
    private b J = null;
    private int K = 0;
    private d t = d.getInstance();
    private e$a u = null;
    private List<dji.pilot.usercenter.b.d.a> v = new ArrayList();
    private final dji.pilot.usercenter.b.d.b w = new dji.pilot.usercenter.b.d.b();
    private c x = null;
    private b y;
    private SlidingDrawer z = null;

    public enum a {
        Changed,
        OverviewChanged,
        DataItemChanged,
        UserLevelChanged
    }

    private final class b extends BaseAdapter {
        private static final String e = "%1$02d/%2$02d/%3$2d";
        private static final String f = "%1$02d:%2$02d:%3$02d";
        final /* synthetic */ DJIFlightRecordActivity a;
        private final LayoutInflater b;
        private final GregorianCalendar c = new GregorianCalendar();
        private final GregorianCalendar d = new GregorianCalendar();

        public b(DJIFlightRecordActivity dJIFlightRecordActivity, Context context) {
            this.a = dJIFlightRecordActivity;
            this.b = LayoutInflater.from(context);
        }

        public int getCount() {
            return 1;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        @SuppressLint({"InflateParams"})
        public View getView(int i, View view, ViewGroup viewGroup) {
            c cVar;
            List a = this.a.a();
            if (view == null || a.size() == 0) {
                c cVar2 = new c();
                view = this.b.inflate(R.layout.v2_usercenter_flightrecord_data_item, null);
                cVar2.a = (DJIImageView) view.findViewById(R.id.d2k);
                cVar2.b = (DJIImageView) view.findViewById(R.id.d2l);
                cVar2.c = (DJITextView) view.findViewById(R.id.d2m);
                cVar2.d = (DJITextView) view.findViewById(R.id.d2n);
                cVar2.e = (DJITextView) view.findViewById(R.id.d2o);
                cVar2.f = (DJITextView) view.findViewById(R.id.d2p);
                cVar2.g = (DJITextView) view.findViewById(R.id.d2q);
                cVar2.h = (DJIRoundImageView) view.findViewById(R.id.d2w);
                cVar2.i = (DJIRoundImageView) view.findViewById(R.id.d2x);
                cVar2.j = (DJIRoundImageView) view.findViewById(R.id.d2y);
                cVar2.m = (DJIImageView) view.findViewById(R.id.c2r);
                cVar2.h.setRound((int) this.a.getResources().getDimension(R.dimen.ga));
                cVar2.k = (DJIImageView) view.findViewById(R.id.d2z);
                cVar2.l = (DJIImageView) view.findViewById(R.id.d30);
                f.a(view.findViewById(R.id.d2r), this.a.D);
                f.a(view.findViewById(R.id.d2t), this.a.E);
                f.a(cVar2.h, this.a.F[0]);
                f.a(cVar2.i, this.a.F[1]);
                f.a(cVar2.j, this.a.F[2]);
                view.setTag(cVar2);
                cVar = cVar2;
            } else {
                cVar = (c) view.getTag();
            }
            view.setBackgroundColor(Color.rgb(47, 54, 71));
            cVar.k.setOnClickListener(this.a.I);
            cVar.l.setOnClickListener(this.a.I);
            cVar.k.setTag(String.valueOf(i));
            cVar.l.setTag(String.valueOf(i));
            if (a.size() > this.a.K && ((dji.pilot.usercenter.b.d.a) a.get(this.a.K)).b.size() > i) {
                dji.pilot.fpv.model.f fVar = (dji.pilot.fpv.model.f) ((dji.pilot.usercenter.b.d.a) a.get(this.a.K)).b.get(i);
                if (fVar != null) {
                    cVar.c.setText(a(fVar.C));
                    CharSequence charSequence = fVar.v;
                    if (!dji.pilot.fpv.d.b.a(fVar.E) || !dji.pilot.fpv.d.b.b(fVar.D)) {
                        charSequence = this.a.getString(R.string.flight_record_nogps);
                    } else if (k.b.equals(fVar.v)) {
                        charSequence = this.a.getString(R.string.flight_record_maploading);
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
                        cVar.g.setText(this.a.getString(R.string.flight_record_distance_m_format, new Object[]{this.a.C.format((double) f)}));
                        cVar.e.setText(this.a.getString(R.string.flight_record_distance_m_format, new Object[]{this.a.C.format((double) f2)}));
                    } else {
                        cVar.g.setText(this.a.getString(R.string.flight_record_distance_ft_format, new Object[]{this.a.C.format((double) DJIGenSettingDataManager.getInstance().b(f))}));
                        cVar.e.setText(this.a.getString(R.string.flight_record_distance_ft_format, new Object[]{this.a.C.format((double) DJIGenSettingDataManager.getInstance().b(f2))}));
                    }
                    List e2 = fVar.e();
                    if (e2 == null || e2.size() <= 0) {
                        cVar.h.setImageResource(R.drawable.my_flight_nullmoment);
                    } else {
                        cVar.h.setImageBitmap((Bitmap) e2.get(0));
                    }
                    if (fVar.x == (byte) 1) {
                        cVar.a.go();
                        cVar.b.show();
                    } else if (fVar.A == (byte) 1) {
                        cVar.a.show();
                        cVar.b.go();
                    } else {
                        cVar.a.go();
                        cVar.b.go();
                    }
                    cVar.k.setSelected(fVar.x == (byte) 1);
                    if (fVar.a() == 1) {
                        cVar.m.go();
                    } else {
                        cVar.m.show();
                    }
                }
            }
            return view;
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
    }

    private static final class c {
        public DJIImageView a;
        public DJIImageView b;
        public DJITextView c;
        public DJITextView d;
        public DJITextView e;
        public DJITextView f;
        public DJITextView g;
        public DJIRoundImageView h;
        public DJIRoundImageView i;
        public DJIRoundImageView j;
        public DJIImageView k;
        public DJIImageView l;
        public DJIImageView m;

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
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_usercenter_flightrecord_view);
        this.t.a(getApplicationContext());
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            this.z = (SlidingDrawer) findViewById(R.id.d4z);
            this.A = (DJITextView) findViewById(R.id.d53);
            this.B = (DJISwipeListView) findViewById(R.id.d54);
            this.J = new b(this, this);
            this.B.setAdapter(this.J);
            this.B.setOnItemClickListener(new OnItemClickListener(this) {
                final /* synthetic */ DJIFlightRecordActivity a;

                {
                    this.a = r1;
                }

                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    if (this.a.a().size() > 0) {
                        Intent intent = new Intent(this.a, DJIFlightRecordPlayerActivity.class);
                        intent.putExtra("POSITION", i);
                        this.a.startActivityForResult(intent, 100);
                    }
                }
            });
            this.z.setOnDrawerOpenListener(new OnDrawerOpenListener(this) {
                final /* synthetic */ DJIFlightRecordActivity a;

                {
                    this.a = r1;
                }

                public void onDrawerOpened() {
                }
            });
            this.z.setOnDrawerScrollListener(new OnDrawerScrollListener(this) {
                final /* synthetic */ DJIFlightRecordActivity a;

                {
                    this.a = r1;
                }

                public void onScrollStarted() {
                }

                public void onScrollEnded() {
                }
            });
            this.z.setOnDrawerCloseListener(new OnDrawerCloseListener(this) {
                final /* synthetic */ DJIFlightRecordActivity a;

                {
                    this.a = r1;
                }

                public void onDrawerClosed() {
                }
            });
            b(DJIFragmentActivityNoFullScreen.ev_);
        }
        this.u = new e$a(this) {
            final /* synthetic */ DJIFlightRecordActivity a;

            {
                this.a = r1;
            }

            public void a(int i, long j, long j2, int i2, Object obj) {
                this.a.a(i, j, j2, obj);
            }

            public void a(int i, int i2, int i3, Object obj, Object obj2) {
                this.a.a(i, i2, obj2);
            }

            public void a(int i, boolean z, int i2, Object obj) {
            }

            public void a(int i, int i2, int i3, Object obj) {
                this.a.a(i, i2, i3, obj);
            }
        };
        this.t.a(this.u);
        this.t.p();
        if (DJINetWorkReceiver.b(this)) {
            this.t.o();
        }
        this.y = new b(this);
        this.y.setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ DJIFlightRecordActivity a;

            {
                this.a = r1;
            }

            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
        this.x = new c(this);
        if (!this.t.h()) {
            if (this.w.a) {
                this.x.show();
                this.x.a(this.t.i());
            }
            this.w.a = false;
            a(true);
        }
    }

    private void b(int i) {
        Resources resources = getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.hh);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.hc);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.hi);
        int dimensionPixelSize4 = resources.getDimensionPixelSize(R.dimen.hg);
        int dimensionPixelSize5 = resources.getDimensionPixelSize(R.dimen.hb);
        int dimensionPixelSize6 = resources.getDimensionPixelSize(R.dimen.hk);
        int dimensionPixelSize7 = resources.getDimensionPixelSize(R.dimen.yb) + resources.getDimensionPixelSize(R.dimen.hr);
        dimensionPixelSize = (((i - dimensionPixelSize) - dimensionPixelSize2) - dimensionPixelSize3) - dimensionPixelSize4;
        if (((dimensionPixelSize - dimensionPixelSize5) - dimensionPixelSize6) - (dimensionPixelSize7 * 3) >= 0) {
            this.D = 0;
            this.E = 0;
            this.F[1] = 0;
            this.F[2] = 0;
        } else if (((dimensionPixelSize - dimensionPixelSize5) - dimensionPixelSize6) - (dimensionPixelSize7 * 2) >= 0) {
            this.D = 0;
            this.E = 0;
            this.F[1] = 0;
            this.F[2] = 8;
        } else if (((dimensionPixelSize - dimensionPixelSize5) - dimensionPixelSize6) - dimensionPixelSize7 > 0) {
            this.D = 0;
            this.E = 0;
            this.F[0] = 0;
            this.F[1] = 8;
            this.F[2] = 8;
        } else if ((dimensionPixelSize - dimensionPixelSize5) - dimensionPixelSize7 > 0) {
            this.D = 0;
            this.E = 8;
            this.F[0] = 0;
            this.F[1] = 8;
            this.F[2] = 8;
        } else if (dimensionPixelSize - dimensionPixelSize7 > 0) {
            this.D = 8;
            this.E = 8;
            this.F[0] = 0;
            this.F[1] = 8;
            this.F[2] = 8;
        } else {
            this.D = 8;
            this.E = 8;
            this.F[0] = 8;
            this.F[1] = 8;
            this.F[2] = 8;
        }
    }

    public void onStart() {
        super.onStart();
        dji.pilot.usercenter.b.f.getInstance().a(this.G);
        dji.pilot.usercenter.b.f.getInstance().b(this);
        if (!this.t.h()) {
            a(false);
        }
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            k();
        }
        dji.pilot.fpv.d.e.b(this);
    }

    public void onStop() {
        dji.pilot.usercenter.b.f.getInstance().b();
        dji.pilot.fpv.d.e.c(this);
        super.onStop();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        this.t.b(this.u);
        super.onDestroy();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == 0 && i == 1007) {
            finish();
        }
    }

    private void a(int i, long j, long j2, Object obj) {
        int i2 = 0;
        if (i == dji.pilot.usercenter.protocol.c.i) {
            if (this.x != null && this.x.isShowing()) {
                this.x.a((int) j);
            }
        } else if (i == dji.pilot.usercenter.protocol.c.l) {
            if (obj instanceof dji.pilot.fpv.model.f) {
                dji.pilot.fpv.model.f fVar = (dji.pilot.fpv.model.f) obj;
                r1 = d.a(fVar.P, true) ? dji.pilot.usercenter.protocol.c.T : fVar.P;
                while (i2 < this.v.size()) {
                    r0 = (dji.pilot.usercenter.b.d.a) this.v.get(i2);
                    if ((r1.equals(r0.a.mBoardNum) || dji.pilot.usercenter.protocol.c.T.equals(r0.a.mBoardNum)) && r0.a.mTimeStamp == fVar.C) {
                        r0.a.mArea = fVar.w;
                        r0.a.mCity = fVar.v;
                        r0.a.mStreet = fVar.u;
                        r0.a.mSubStreet = fVar.t;
                        r0.a.mLongitude = fVar.D;
                        r0.a.mLatitude = fVar.E;
                    }
                    i2++;
                }
                if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                    this.J.notifyDataSetChanged();
                }
                dji.thirdparty.a.c.a().e(a.Changed);
            }
        } else if (i == dji.pilot.usercenter.protocol.c.m && (obj instanceof d.e)) {
            d.e eVar = (d.e) obj;
            dji.pilot.fpv.model.f fVar2 = eVar.a;
            r1 = dji.pilot.publics.e.d.a(fVar2.P) ? dji.pilot.usercenter.protocol.c.T : fVar2.P;
            while (i2 < this.v.size()) {
                r0 = (dji.pilot.usercenter.b.d.a) this.v.get(i2);
                if (r1.equals(r0.a.mBoardNum) || dji.pilot.usercenter.protocol.c.T.equals(r0.a.mBoardNum)) {
                    FlightOverviewInfo flightOverviewInfo = r0.a;
                    flightOverviewInfo.mTotalDistance += (double) (fVar2.F - eVar.b);
                }
                i2++;
            }
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                this.J.notifyDataSetChanged();
            }
            dji.thirdparty.a.c.a().e(a.Changed);
        }
    }

    private void a(int i, int i2, Object obj) {
        if (i == dji.pilot.usercenter.protocol.c.x) {
            a(false);
        } else if (i == dji.pilot.usercenter.protocol.c.h) {
            if (this.x != null && this.x.isShowing()) {
                this.x.dismiss();
            }
            if (!this.t.r()) {
                if (this.t.m()) {
                    Toast.makeText(this, R.string.flight_record_sync_fail, 1).show();
                } else {
                    Toast.makeText(this, R.string.flight_record_sync_success, 1).show();
                }
                a(false);
            }
        } else if (i == 1048584) {
            a(true);
        } else if (i == dji.pilot.usercenter.protocol.c.w) {
            dji.thirdparty.a.c.a().e(a.UserLevelChanged);
        }
    }

    private void a(int i, int i2, int i3, Object obj) {
        if (i == dji.pilot.usercenter.protocol.c.g) {
            if (this.x != null && this.x.isShowing()) {
                this.x.dismiss();
            }
            Toast.makeText(this, R.string.flight_record_sync_fail, 1).show();
        }
    }

    public String a(String str) {
        String str2 = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            str2 = EncodingUtils.getString(bArr, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    private void a(boolean z) {
        List list;
        int size;
        DroneList droneList;
        int size2;
        FlightRecordUserInfo flightRecordUserInfo = null;
        int size3 = this.v.size();
        for (int i = 0; i < size3; i++) {
            ((dji.pilot.usercenter.b.d.a) this.v.get(i)).b.clear();
        }
        this.v.clear();
        FlightRecordUserInfo flightRecordUserInfo2 = (FlightRecordUserInfo) com.dji.frame.c.h.b(a(com.dji.frame.c.d.a(this, d.p) + d.t), FlightRecordUserInfo.class);
        if (!(flightRecordUserInfo2 == null || flightRecordUserInfo2.Email == null || flightRecordUserInfo2.Email.compareTo(dji.pilot.usercenter.b.f.getInstance().j()) != 0)) {
            list = flightRecordUserInfo2.DroneList;
            if (!(list == null || list.isEmpty())) {
                DroneList droneList2;
                size = list.size();
                for (size3 = 0; size3 < size; size3++) {
                    droneList = (DroneList) list.get(size3);
                    if (dji.pilot.usercenter.protocol.c.T.compareToIgnoreCase(droneList.Boardnum) == 0) {
                        break;
                    }
                }
                droneList = null;
                if (droneList == null) {
                    droneList = new DroneList();
                    droneList.Boardnum = dji.pilot.usercenter.protocol.c.T;
                    flightRecordUserInfo2.DroneList.add(droneList);
                    droneList2 = droneList;
                } else {
                    droneList2 = droneList;
                }
                size2 = list.size();
                for (size = 0; size < size2; size++) {
                    droneList = (DroneList) list.get(size);
                    if (dji.pilot.usercenter.protocol.c.T.compareToIgnoreCase(droneList.Boardnum) != 0) {
                        if (!(droneList.CountryList == null || droneList.CountryList.isEmpty())) {
                            if (droneList2.CountryList == null) {
                                droneList2.CountryList = new ArrayList();
                            }
                            for (String str : droneList.CountryList) {
                                if (!droneList2.CountryList.contains(str)) {
                                    droneList2.CountryList.add(str);
                                }
                            }
                        }
                        if (droneList2.FlyHeight < droneList.FlyHeight) {
                            droneList2.FlyHeight = droneList.FlyHeight;
                            droneList2.FlyHeightDate = droneList.FlyHeightDate;
                        }
                        if (droneList2.MaxFlyDistance < droneList.MaxFlyDistance) {
                            droneList2.MaxFlyDistance = droneList.MaxFlyDistance;
                            droneList2.MaxFlyDistanceDate = droneList.MaxFlyDistanceDate;
                        }
                        if (droneList2.MaxFlyTime < droneList.MaxFlyTime) {
                            droneList2.MaxFlyTime = droneList.MaxFlyTime;
                            droneList2.MaxFlyTimeDate = droneList.MaxFlyTimeDate;
                        }
                        if (droneList2.MaxHeight < droneList.MaxHeight) {
                            droneList2.MaxHeight = droneList.MaxHeight;
                            droneList2.MaxHeightDate = droneList.MaxHeightDate;
                        }
                        if (droneList2.MaxHorizontalSpeed < droneList.MaxHorizontalSpeed) {
                            droneList2.MaxHorizontalSpeed = droneList.MaxHorizontalSpeed;
                            droneList2.MaxHorizontalSpeedDate = droneList.MaxHorizontalSpeedDate;
                        }
                        if (droneList2.MaxVirticalSpeed < droneList.MaxVirticalSpeed) {
                            droneList2.MaxVirticalSpeed = droneList.MaxVirticalSpeed;
                            droneList2.MaxVirticalSpeedDate = droneList.MaxVirticalSpeedDate;
                        }
                        if (droneList2.TakeOffAltitude < droneList.TakeOffAltitude) {
                            droneList2.TakeOffAltitude = droneList.TakeOffAltitude;
                            droneList2.TakeOffAltitudeDate = droneList.TakeOffAltitudeDate;
                        }
                        droneList2.TotalFlyCount += droneList.TotalFlyCount;
                        droneList2.TotalFlyDistance += droneList.TotalFlyDistance;
                        droneList2.TotalFlyTime = droneList.TotalFlyTime + droneList2.TotalFlyTime;
                    }
                }
            }
            flightRecordUserInfo = flightRecordUserInfo2;
        }
        this.H = 0;
        list = this.t.a(z, this.w);
        if (!(list == null || list.isEmpty())) {
            size2 = list.size();
            size = 0;
            while (size < size2) {
                dji.pilot.usercenter.b.d.a aVar = (dji.pilot.usercenter.b.d.a) list.get(size);
                dji.pilot.usercenter.b.d.a aVar2 = new dji.pilot.usercenter.b.d.a(false);
                aVar2.a = aVar.a;
                aVar2.b.addAll(aVar.b);
                for (size3 = 0; size3 < aVar.b.size(); size3++) {
                    dji.pilot.fpv.model.f fVar = (dji.pilot.fpv.model.f) aVar.b.get(size3);
                    if (fVar.A == (byte) 1 && size == 0) {
                        this.H++;
                    }
                    if (fVar.F > aVar2.a.mTopDistance) {
                        aVar2.a.mTopDistance = fVar.F;
                        aVar2.a.mTopDistanceDate = fVar.C;
                    }
                    if (fVar.J > aVar2.a.mTopVSpeed && dji.pilot.fpv.model.f.b(fVar.J)) {
                        aVar2.a.mTopVSpeed = fVar.J;
                        aVar2.a.mTopVSpeedDate = fVar.C;
                    }
                    if (fVar.I > aVar2.a.mTopHSpeed && dji.pilot.fpv.model.f.a(fVar.I)) {
                        aVar2.a.mTopHSpeed = fVar.I;
                        aVar2.a.mTopHSpeedDate = fVar.C;
                    }
                    float f = fVar.Y * 0.1f;
                    if (f > aVar2.a.mTopAttitude && dji.pilot.fpv.model.f.c(f)) {
                        aVar2.a.mTopAttitude = f;
                        aVar2.a.mTopAttitudeDate = fVar.C;
                    }
                }
                if (!(flightRecordUserInfo == null || flightRecordUserInfo.DroneList == null)) {
                    for (DroneList droneList3 : flightRecordUserInfo.DroneList) {
                        if (droneList3.Boardnum.compareToIgnoreCase(aVar.a.mBoardNum) == 0) {
                            if (droneList3.TotalFlyDistance > aVar2.a.mTopDistance) {
                                aVar2.a.mTopDistance = droneList3.MaxFlyDistance;
                                aVar2.a.mTopDistanceDate = droneList3.MaxFlyDistanceDate;
                            }
                            if (droneList3.MaxVirticalSpeed > aVar2.a.mTopVSpeed && dji.pilot.fpv.model.f.b(droneList3.MaxVirticalSpeed)) {
                                aVar2.a.mTopVSpeed = droneList3.MaxVirticalSpeed;
                                aVar2.a.mTopVSpeedDate = droneList3.MaxVirticalSpeedDate;
                            }
                            if (droneList3.MaxHorizontalSpeed > aVar2.a.mTopHSpeed && dji.pilot.fpv.model.f.a(droneList3.MaxHorizontalSpeed)) {
                                aVar2.a.mTopHSpeed = droneList3.MaxHorizontalSpeed;
                                aVar2.a.mTopHSpeedDate = droneList3.MaxHorizontalSpeedDate;
                            }
                            if (droneList3.TakeOffAltitude > aVar2.a.mTopAttitude && dji.pilot.fpv.model.f.c(droneList3.TakeOffAltitude)) {
                                aVar2.a.mTopAttitude = droneList3.TakeOffAltitude;
                                aVar2.a.mTopAttitudeDate = droneList3.TakeOffAltitudeDate;
                            }
                            if (droneList3.CountryList != null) {
                                aVar2.a.footprints.clear();
                                for (String str2 : droneList3.CountryList) {
                                    if (!dji.pilot.publics.e.d.a(str2)) {
                                        aVar2.a.footprints.add(str2);
                                    }
                                }
                            }
                            flightRecordUserInfo.DroneList.remove(droneList3);
                        }
                    }
                }
                if (-2.14748365E9f == aVar2.a.mTopAttitude) {
                    if (aVar2.b.isEmpty()) {
                        aVar2.a.mTopAttitudeDate = 0;
                    } else {
                        aVar2.a.mTopAttitudeDate = ((dji.pilot.fpv.model.f) aVar2.b.get(0)).C;
                    }
                }
                i.a(aVar2.b, dji.pilot.fpv.model.f.a.c);
                i.a(aVar2.b);
                this.v.add(aVar2);
                size++;
            }
            if (!this.v.isEmpty()) {
                Collections.sort(this.v);
                Collections.reverse(this.v);
            }
        }
        if (!z) {
            dji.thirdparty.a.c.a().e(a.Changed);
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                a(0);
                k();
            }
        }
    }

    public List<dji.pilot.usercenter.b.d.a> a() {
        return this.v;
    }

    public int b() {
        return this.H;
    }

    public boolean e() {
        dji.pilot.fpv.d.e.a("UserCenter_FlightRecord_TopBarView_Button_Upload/SyncRecord");
        if (this.t.h()) {
            if (this.x == null || !this.x.isShowing()) {
                this.x = new c(this);
            }
            this.x.a(this.t.i());
            this.x.show();
            return true;
        } else if (dji.pilot.fpv.d.b.c(this) && dji.pilot.publics.control.a.b()) {
            if (this.x == null || !this.x.isShowing()) {
                this.x = new c(this);
            }
            this.x.a(0);
            this.x.show();
            this.t.l();
            return true;
        } else {
            Toast.makeText(this, R.string.home_account_nonet, 0).show();
            return false;
        }
    }

    public void f() {
        this.y.show();
    }

    public void a(e$a dji_pilot_usercenter_protocol_e_a) {
        this.t.a(dji_pilot_usercenter_protocol_e_a);
    }

    public void b(e$a dji_pilot_usercenter_protocol_e_a) {
        this.t.b(dji_pilot_usercenter_protocol_e_a);
    }

    public boolean g() {
        return this.x != null && this.x.isShowing();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || DJIOriLayout.getDeviceType() != DJIDeviceType.Phone) {
            return super.onKeyDown(i, keyEvent);
        }
        if (!this.z.isOpened()) {
            return super.onKeyDown(i, keyEvent);
        }
        i();
        return false;
    }

    public void h() {
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            this.H--;
            k();
        }
    }

    public void i() {
        ((DJIFlightRecordDataFragment) getFragmentManager().findFragmentById(R.id.d55)).a(false);
        this.z.animateClose();
    }

    private void k() {
        if (this.H > 0) {
            this.A.setText("" + this.H);
            this.A.go();
            return;
        }
        this.A.go();
    }

    public void a(int i) {
        this.K = i;
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone && this.J != null) {
            this.J.notifyDataSetChanged();
        }
    }

    public int j() {
        return this.K;
    }
}
