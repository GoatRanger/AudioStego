package dji.pilot2.usercenter.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.dji.frame.c.h;
import com.dji.frame.c.l;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.usercenter.b.d.i;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.f.e;
import dji.pilot.usercenter.mode.FlightOverviewInfo;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.mine.jsonbean.UserLevelJsonBean;
import dji.pilot2.mine.jsonbean.UserLevelJsonBean.LevelInfo;
import dji.pilot2.usercenter.activity.DJIFlightRecordActivity;
import dji.pilot2.usercenter.widget.DJIProfileRoundImageView;
import dji.pilot2.utils.c;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.publics.widget.djiviewpager.DJIViewPager;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DJIFlightRecordOverviewFragment extends Fragment implements OnClickListener {
    private DJIViewPager a = null;
    private DJILinearLayout b = null;
    private DJITextView c = null;
    private DisplayImageOptions d;
    private DJITextView e = null;
    private DJITextView f = null;
    private DJITextView g = null;
    private ProgressBar h = null;
    private DJITextView i = null;
    private DJIProfileRoundImageView j = null;
    private DJIStateImageView k = null;
    private DJIStateImageView l = null;
    private DecimalFormat m = new DecimalFormat("###,###,###,###");
    private a n = null;

    private class a extends PagerAdapter {
        private static final int b = 5;
        final /* synthetic */ DJIFlightRecordOverviewFragment a;
        private LayoutInflater c = null;
        private List<DJIImageView> d = new ArrayList();
        private b[] e = new b[3];

        public a(DJIFlightRecordOverviewFragment dJIFlightRecordOverviewFragment, Context context) {
            this.a = dJIFlightRecordOverviewFragment;
            this.c = LayoutInflater.from(context);
        }

        public int getItemPosition(Object obj) {
            return -2;
        }

        public void a(int i) {
            List a = ((DJIFlightRecordActivity) this.a.getActivity()).a();
            if (a == null || a.size() <= i) {
                this.a.c.go();
                this.d.clear();
                this.a.b.removeAllViews();
                return;
            }
            if (a.size() > 5) {
                this.a.c.setText(String.valueOf(i + 1) + d.t + String.valueOf(a.size()));
            } else {
                for (int size = this.d.size() - 1; size >= 0; size--) {
                    if (size == i) {
                        ((DJIImageView) this.d.get(size)).setAlpha(1.0f);
                    } else {
                        ((DJIImageView) this.d.get(size)).setAlpha(0.2f);
                    }
                }
            }
            if (i == 0) {
                this.a.f.go();
                this.a.e.setText(f.getInstance().m());
                this.a.b();
                return;
            }
            int i2;
            dji.pilot.usercenter.b.d.a aVar = (dji.pilot.usercenter.b.d.a) a.get(i);
            this.a.e.setText(aVar.a.mAircraftName);
            if (dji.pilot.usercenter.b.d.a(aVar.a.mBoardNum, false)) {
                i2 = R.drawable.my_flight_repaired;
            } else {
                i2 = dji.pilot.publics.c.d.getInstance().i(ProductType.find(aVar.a.mDroneType));
            }
            this.a.j.setImageResource(i2);
        }

        @SuppressLint({"DefaultLocale"})
        public void b(int i) {
            b bVar = this.e[i % this.e.length];
            if (bVar != null) {
                dji.pilot.usercenter.b.d.a aVar;
                List a = ((DJIFlightRecordActivity) this.a.getActivity()).a();
                if (a == null || a.size() <= i) {
                    aVar = null;
                } else {
                    aVar = (dji.pilot.usercenter.b.d.a) a.get(i);
                }
                if (aVar != null) {
                    int[] d = dji.pilot.fpv.d.b.d((int) (aVar.a.mTotalTime / 1000));
                    bVar.b.setText(this.a.getString(R.string.flight_record_total_time_format, new Object[]{Integer.valueOf(d[1]), Integer.valueOf(d[0])}));
                    float f = (float) aVar.a.mTotalDistance;
                    if (f - ((float) ((int) f)) > 0.0f) {
                        f = (float) (((int) f) + 1);
                    }
                    float f2 = aVar.a.mTopDistance;
                    if (f2 - ((float) ((int) f2)) > 0.0f) {
                        f2 = (float) (((int) f2) + 1);
                    }
                    float f3 = aVar.a.mTopAttitude;
                    DJITextView dJITextView;
                    DJIFlightRecordOverviewFragment dJIFlightRecordOverviewFragment;
                    Object[] objArr;
                    if (DJIGenSettingDataManager.getInstance().v() == 1 || DJIGenSettingDataManager.getInstance().v() == 2) {
                        bVar.c.setText(this.a.getString(R.string.flight_record_distance_m_format, new Object[]{this.a.m.format((double) f)}));
                        bVar.j.setText(this.a.getString(R.string.flight_record_distance_m_format, new Object[]{this.a.m.format((double) f2)}));
                        if (DJIGenSettingDataManager.getInstance().v() == 1) {
                            bVar.l.setText(this.a.getString(R.string.flight_record_vspeed_metric, new Object[]{Float.valueOf(aVar.a.mTopVSpeed)}));
                            bVar.n.setText(this.a.getString(R.string.flight_record_hspeed_metric, new Object[]{Float.valueOf(aVar.a.mTopHSpeed)}));
                        } else {
                            bVar.l.setText(this.a.getString(R.string.flight_record_vspeed_kilometric, new Object[]{Float.valueOf(DJIGenSettingDataManager.getInstance().c(aVar.a.mTopVSpeed))}));
                            bVar.n.setText(this.a.getString(R.string.flight_record_hspeed_kilometric, new Object[]{Float.valueOf(DJIGenSettingDataManager.getInstance().c(aVar.a.mTopHSpeed))}));
                        }
                        if (-2.14748365E9f == f3) {
                            bVar.p.setText(this.a.getString(R.string.flight_record_distance_m_format, new Object[]{FlightOverviewInfo.INVALID_ALTITUDE}));
                        } else {
                            dJITextView = bVar.p;
                            dJIFlightRecordOverviewFragment = this.a;
                            objArr = new Object[1];
                            objArr[0] = String.format("%.1f", new Object[]{Float.valueOf(f3)});
                            dJITextView.setText(dJIFlightRecordOverviewFragment.getString(R.string.flight_record_distance_m_format, objArr));
                        }
                    } else {
                        bVar.c.setText(this.a.getString(R.string.flight_record_distance_ft_format, new Object[]{this.a.m.format((double) DJIGenSettingDataManager.getInstance().b(f))}));
                        bVar.j.setText(this.a.getString(R.string.flight_record_distance_ft_format, new Object[]{this.a.m.format((double) DJIGenSettingDataManager.getInstance().b(f2))}));
                        bVar.l.setText(this.a.getString(R.string.flight_record_vspeed_imperial, new Object[]{Float.valueOf(DJIGenSettingDataManager.getInstance().c(aVar.a.mTopVSpeed))}));
                        bVar.n.setText(this.a.getString(R.string.flight_record_hspeed_imperial, new Object[]{Float.valueOf(DJIGenSettingDataManager.getInstance().c(aVar.a.mTopHSpeed))}));
                        if (-2.14748365E9f == f3) {
                            bVar.p.setText(this.a.getString(R.string.flight_record_distance_ft_format, new Object[]{FlightOverviewInfo.INVALID_ALTITUDE}));
                        } else {
                            dJITextView = bVar.p;
                            dJIFlightRecordOverviewFragment = this.a;
                            objArr = new Object[1];
                            objArr[0] = String.format("%.1f", new Object[]{Float.valueOf(DJIGenSettingDataManager.getInstance().b(f3))});
                            dJITextView.setText(dJIFlightRecordOverviewFragment.getString(R.string.flight_record_distance_ft_format, objArr));
                        }
                    }
                    bVar.i.setText(l.a(new Date(aVar.a.mTopDistanceDate), e.a));
                    bVar.k.setText(l.a(new Date(aVar.a.mTopVSpeedDate), e.a));
                    bVar.m.setText(l.a(new Date(aVar.a.mTopHSpeedDate), e.a));
                    bVar.o.setText(l.a(new Date(aVar.a.mTopAttitudeDate), e.a));
                    if (aVar.a.footprints == null || aVar.a.footprints.isEmpty()) {
                        bVar.e.setText(this.a.getString(R.string.flight_record_visit_area, new Object[]{Integer.valueOf(0)}));
                        bVar.f.removeAllViews();
                        bVar.g.go();
                    } else {
                        List<String> list = aVar.a.footprints;
                        int size = list.size();
                        bVar.e.setText(this.a.getString(R.string.flight_record_visit_area, new Object[]{Integer.valueOf(size)}));
                        bVar.f.removeAllViews();
                        int dimension = (int) this.a.getActivity().getResources().getDimension(R.dimen.nc);
                        int dimension2 = (int) this.a.getActivity().getResources().getDimension(R.dimen.nd);
                        int dimension3 = (int) this.a.getActivity().getResources().getDimension(R.dimen.ne);
                        for (String str : list) {
                            View imageView = new ImageView(this.a.getActivity());
                            LayoutParams layoutParams = new LinearLayout.LayoutParams(dimension3, dimension2);
                            layoutParams.leftMargin = dimension;
                            imageView.setLayoutParams(layoutParams);
                            int a2 = c.a(this.a.getActivity(), str.toLowerCase());
                            if (a2 != 0) {
                                imageView.setImageResource(a2);
                                bVar.f.addView(imageView);
                            }
                        }
                        if (((float) ((dimension3 + dimension) * size)) > this.a.getActivity().getResources().getDimension(R.dimen.nf)) {
                            bVar.g.show();
                        } else {
                            bVar.g.go();
                        }
                    }
                    bVar.d.setText(String.valueOf(aVar.a.mTotalFlightTime));
                    return;
                }
                bVar.c.setText(R.string.flight_record_na);
                bVar.b.setText(R.string.flight_record_na);
                bVar.d.setText(R.string.flight_record_na);
                bVar.j.setText(R.string.flight_record_na);
                bVar.l.setText(R.string.flight_record_na);
                bVar.n.setText(R.string.flight_record_na);
                bVar.p.setText(R.string.flight_record_na);
                bVar.i.setText(R.string.flight_record_na);
                bVar.k.setText(R.string.flight_record_na);
                bVar.m.setText(R.string.flight_record_na);
                bVar.o.setText(R.string.flight_record_na);
                bVar.e.setText(this.a.getString(R.string.flight_record_visit_area, new Object[]{Integer.valueOf(0)}));
                bVar.f.removeAllViews();
                bVar.g.go();
            }
        }

        public int getCount() {
            List a = ((DJIFlightRecordActivity) this.a.getActivity()).a();
            if (a == null || a.size() == 0) {
                this.a.c.go();
                this.d.clear();
                this.a.b.removeAllViews();
                return 1;
            }
            int size = a.size();
            if (size <= 5) {
                this.a.c.go();
                this.a.b.show();
                if (a.size() != this.d.size()) {
                    this.d.clear();
                    this.a.b.removeAllViews();
                    for (size = 0; size < a.size(); size++) {
                        View dJIImageView = new DJIImageView(this.a.b.getContext());
                        dJIImageView.setImageResource(R.drawable.gs_remote_control_circle);
                        dJIImageView.setScaleType(ScaleType.CENTER_CROP);
                        dJIImageView.setScaleX(0.8f);
                        dJIImageView.setScaleY(0.8f);
                        this.d.add(dJIImageView);
                        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                        layoutParams.leftMargin = 6;
                        layoutParams.rightMargin = 6;
                        this.a.b.addView(dJIImageView, layoutParams);
                    }
                    a(0);
                }
            } else if (this.a.c.getVisibility() != 0) {
                this.a.c.show();
                this.a.b.go();
                int currentItem = this.a.a.getCurrentItem();
                this.a.c.setText(String.valueOf(currentItem + 1) + d.t + String.valueOf(size));
                a(currentItem);
            }
            return a.size();
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        }

        @SuppressLint({"InflateParams"})
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            int length = i % this.e.length;
            if (this.e[length] == null || this.e[length].a == null) {
                b bVar = new b();
                View inflate = this.c.inflate(R.layout.v2_usercenter_flightrecord_page_view, null);
                bVar.a = inflate;
                bVar.b = (DJITextView) inflate.findViewById(R.id.d3b);
                bVar.c = (DJITextView) inflate.findViewById(R.id.d3c);
                bVar.d = (DJITextView) inflate.findViewById(R.id.d3d);
                bVar.e = (DJITextView) inflate.findViewById(R.id.d3e);
                bVar.f = (DJILinearLayout) inflate.findViewById(R.id.d3g);
                bVar.g = (DJILinearLayout) inflate.findViewById(R.id.d3h);
                bVar.h = (HorizontalScrollView) inflate.findViewById(R.id.d3f);
                bVar.i = (DJITextView) inflate.findViewById(R.id.d3i);
                bVar.j = (DJITextView) inflate.findViewById(R.id.d3j);
                bVar.k = (DJITextView) inflate.findViewById(R.id.d3m);
                bVar.l = (DJITextView) inflate.findViewById(R.id.d3n);
                bVar.m = (DJITextView) inflate.findViewById(R.id.d3o);
                bVar.n = (DJITextView) inflate.findViewById(R.id.d3p);
                bVar.o = (DJITextView) inflate.findViewById(R.id.d3k);
                bVar.p = (DJITextView) inflate.findViewById(R.id.d3l);
                this.e[length] = bVar;
            }
            if (this.e[length].a.getParent() != null) {
                viewGroup.removeView(this.e[length].a);
            }
            b(i);
            viewGroup.addView(this.e[length].a);
            this.a.a.setObjectForPosition(this.e[length].a, i);
            return this.e[length].a;
        }
    }

    private class b {
        public View a;
        public DJITextView b;
        public DJITextView c;
        public DJITextView d;
        public DJITextView e;
        public DJILinearLayout f;
        public DJILinearLayout g;
        public HorizontalScrollView h;
        public DJITextView i;
        public DJITextView j;
        public DJITextView k;
        public DJITextView l;
        public DJITextView m;
        public DJITextView n;
        public DJITextView o;
        public DJITextView p;
        final /* synthetic */ DJIFlightRecordOverviewFragment q;

        private b(DJIFlightRecordOverviewFragment dJIFlightRecordOverviewFragment) {
            this.q = dJIFlightRecordOverviewFragment;
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
        View inflate = layoutInflater.inflate(R.layout.v2_usercenter_flightrecord_overview_fragment, null);
        this.a = (DJIViewPager) inflate.findViewById(R.id.d39);
        this.b = (DJILinearLayout) inflate.findViewById(R.id.d3_);
        this.c = (DJITextView) inflate.findViewById(R.id.d3a);
        inflate.findViewById(R.id.d32).setOnClickListener(this);
        inflate.findViewById(R.id.d20).setOnClickListener(this);
        inflate.findViewById(R.id.d35).setOnClickListener(this);
        this.f = (DJITextView) inflate.findViewById(R.id.d34);
        this.e = (DJITextView) inflate.findViewById(R.id.d33);
        this.g = (DJITextView) inflate.findViewById(R.id.d36);
        this.h = (ProgressBar) inflate.findViewById(R.id.d37);
        this.i = (DJITextView) inflate.findViewById(R.id.d38);
        this.j = (DJIProfileRoundImageView) inflate.findViewById(R.id.c9r);
        this.k = (DJIStateImageView) inflate.findViewById(R.id.d20);
        this.l = (DJIStateImageView) inflate.findViewById(R.id.d21);
        DJIRelativeLayout dJIRelativeLayout = (DJIRelativeLayout) inflate.findViewById(R.id.d1z);
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            this.k.setOnClickListener(this);
            dJIRelativeLayout.show();
        } else {
            dJIRelativeLayout.go();
        }
        this.d = new Builder().showImageOnLoading(R.drawable.v2_avatar_default).cacheInMemory(true).cacheOnDisc(false).build();
        this.n = new a(this, getActivity());
        this.a.setAdapter(this.n);
        this.a.addOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ DJIFlightRecordOverviewFragment a;

            {
                this.a = r1;
            }

            public void onPageSelected(int i) {
                ((DJIFlightRecordActivity) this.a.getActivity()).a(i);
                dji.thirdparty.a.c.a().e(dji.pilot2.usercenter.activity.DJIFlightRecordActivity.a.OverviewChanged);
                this.a.n.a(i);
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        return inflate;
    }

    public void onEventMainThread(dji.pilot2.usercenter.activity.DJIFlightRecordActivity.a aVar) {
        if (aVar == dji.pilot2.usercenter.activity.DJIFlightRecordActivity.a.Changed) {
            this.n.notifyDataSetChanged();
        } else if (aVar == dji.pilot2.usercenter.activity.DJIFlightRecordActivity.a.DataItemChanged) {
            if (this.a.getCurrentItem() != ((DJIFlightRecordActivity) getActivity()).j()) {
                this.a.setCurrentItem(((DJIFlightRecordActivity) getActivity()).j());
                this.n.notifyDataSetChanged();
            }
        } else if (dji.pilot2.usercenter.activity.DJIFlightRecordActivity.a.UserLevelChanged == aVar) {
            a();
        }
    }

    public void onEventMainThread(i iVar) {
        if (iVar == i.Start) {
            this.l.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.i));
        } else {
            this.l.clearAnimation();
        }
    }

    public void onStart() {
        super.onStart();
        if (this.a.getCurrentItem() != ((DJIFlightRecordActivity) getActivity()).j()) {
            this.a.setCurrentItem(((DJIFlightRecordActivity) getActivity()).j());
        }
        this.n.notifyDataSetChanged();
        onEventMainThread(dji.pilot.usercenter.b.d.getInstance().h() ? i.Start : i.Stop);
        dji.thirdparty.a.c.a().a(this);
        a();
    }

    public void onStop() {
        dji.thirdparty.a.c.a().d(this);
        super.onStop();
    }

    private void a() {
        this.g.setText("0");
        this.i.setText("LV0");
        String c = dji.pilot2.mine.b.e.getInstance().c(f.getInstance().j());
        if (!(c == null || c.equals(""))) {
            UserLevelJsonBean userLevelJsonBean = (UserLevelJsonBean) h.b(c, UserLevelJsonBean.class);
            if (!(userLevelJsonBean == null || userLevelJsonBean.level == null)) {
                LevelInfo levelInfo = userLevelJsonBean.level;
                if (levelInfo != null) {
                    this.i.setText("LV" + levelInfo.level);
                    this.h.setMax(levelInfo.nextexp - levelInfo.lastexp);
                    this.h.setProgress(levelInfo.exp - levelInfo.lastexp);
                    this.g.setText(this.m.format((long) levelInfo.exp));
                }
            }
        }
        if (this.a.getCurrentItem() == 0) {
            b();
        }
    }

    private void b() {
        String e = f.getInstance().e();
        String str = f.getInstance().h().l;
        if (!dji.pilot.publics.e.d.a(e)) {
            File file = new File(e);
            if (file.exists() && file.isFile()) {
                this.j.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
            } else if (str == null || str.equals("")) {
                this.j.setImageDrawable(getResources().getDrawable(R.drawable.v2_avatar_default));
            } else {
                ImageLoader.getInstance().displayImage(str, this.j, this.d);
            }
        } else if (str == null || str.equals("")) {
            this.j.setImageDrawable(getResources().getDrawable(R.drawable.v2_avatar_default));
        } else {
            ImageLoader.getInstance().displayImage(str, this.j, this.d);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.d20:
                if (!((DJIFlightRecordActivity) getActivity()).g()) {
                    ((DJIFlightRecordActivity) getActivity()).e();
                    return;
                }
                return;
            case R.id.d32:
                getActivity().finish();
                return;
            case R.id.d35:
                ((DJIFlightRecordActivity) getActivity()).f();
                return;
            default:
                return;
        }
    }
}
