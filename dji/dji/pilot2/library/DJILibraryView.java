package dji.pilot2.library;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.pilot.R;
import dji.pilot.fpv.d.c$l;
import dji.pilot.fpv.d.e;
import dji.pilot.playback.litchi.DJIPlayBackActivity;
import dji.pilot.playback.litchi.h;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.support.DJINonViewPager;
import dji.pilot2.library.a.d;
import dji.pilot2.main.fragment.DJILibraryFragment;
import dji.pilot2.publics.object.b;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.io.FileNotFoundException;

public class DJILibraryView extends FrameLayout implements c$l {
    private static final int u = -1;
    private static final int v = 0;
    private static final int w = 1;
    private static final int x = 1;
    private static final int y = 2;
    private static final int z = 4;
    private Context A;
    private View B;
    private int C;
    private DJILinearLayout D;
    private DJINonViewPager E;
    private OnPageChangeListener F;
    private OnClickListener G;
    private a H;
    private int I;
    private DJIStateTextView J;
    private DJIStateTextView K;
    private DJITextView L;
    private DJITextView M;
    private DJITextView N;
    private DJIRelativeLayout O;
    private DJILinearLayout P;
    private DJIImageView Q;
    private DJIRelativeLayout R;
    private ListView S;
    private dji.pilot2.library.b.a T;
    private FrameLayout U;
    private FrameLayout V;
    private b W;
    private AlertDialog aa;
    private DJILibraryView ab;
    public DJILibraryFragment p;
    public DJILibraryPhotoView q;
    public DJILibraryVideoView r;
    MODE s;
    int t;

    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] a = new int[o.values().length];

        static {
            try {
                a[o.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    private final class a extends PagerAdapter {
        final /* synthetic */ DJILibraryView a;

        private a(DJILibraryView dJILibraryView) {
            this.a = dJILibraryView;
        }

        public int getCount() {
            return 2;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView(this.a.a(i));
        }

        public int getItemPosition(Object obj) {
            return super.getItemPosition(obj);
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View d = this.a.a(i);
            viewGroup.addView(d);
            this.a.E.setObjectForPosition(d, i);
            return d;
        }
    }

    public DJILibraryView(Context context) {
        this(context, null);
    }

    public DJILibraryView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJILibraryView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.A = null;
        this.p = null;
        this.C = 0;
        this.D = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.q = null;
        this.r = null;
        this.I = -1;
        this.s = null;
        this.t = 0;
        if (!isInEditMode()) {
            this.A = context;
            c.a().a(this);
        }
    }

    public void attachFragment(DJILibraryFragment dJILibraryFragment) {
        this.p = dJILibraryFragment;
        this.r.setSuperFragment(this.p);
    }

    public void detachFragment() {
        c.a().d(this);
        this.p = null;
        this.q.unregisterEventBus();
        this.r.unregisterEventBus();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            initMember();
            initWidget();
        }
    }

    public void initWidget() {
        c();
        this.ab = this;
        this.W = new b(this.A);
        this.E = (DJINonViewPager) findViewById(R.id.cra);
        this.E.setPagingEnabled(true);
        this.E.setOnPageChangeListener(this.F);
        LayoutInflater from = LayoutInflater.from(this.A);
        this.r = (DJILibraryVideoView) from.inflate(R.layout.v2_library_video_view, null);
        this.q = (DJILibraryPhotoView) from.inflate(R.layout.v2_library_photo_view, null);
        this.r.setParentView(this);
        this.q.setParentView(this);
        this.q.registerEventBus();
        this.r.registerEventBus();
        this.L = (DJITextView) findViewById(R.id.cr4);
        this.M = (DJITextView) findViewById(R.id.cr5);
        this.N = (DJITextView) findViewById(R.id.cr6);
        this.O = (DJIRelativeLayout) findViewById(R.id.er);
        this.P = (DJILinearLayout) findViewById(R.id.es);
        this.Q = (DJIImageView) findViewById(R.id.cr_);
        this.U = (FrameLayout) findViewById(R.id.crb);
        this.V = (FrameLayout) findViewById(R.id.cr1);
        this.H = new a();
        this.E.setAdapter(this.H);
        this.J = (DJIStateTextView) findViewById(R.id.cr7);
        this.K = (DJIStateTextView) findViewById(R.id.cr8);
        this.J.setOnClickListener(this.G);
        this.K.setOnClickListener(this.G);
        this.J.setSelected(true);
        this.K.setSelected(false);
        this.M.setOnClickListener(this.G);
        this.N.setOnClickListener(this.G);
        this.Q.setOnClickListener(this.G);
        View findViewById;
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            this.R = (DJIRelativeLayout) findViewById(R.id.aya);
            findViewById = findViewById(R.id.cq8);
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            this.S = (ListView) findViewById(R.id.ayb);
        } else {
            this.R = (DJIRelativeLayout) findViewById(R.id.cq8);
            findViewById = findViewById(R.id.aya);
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            this.S = (ListView) findViewById(R.id.cq_);
        }
        this.R.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ DJILibraryView a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0 || this.a.R.getVisibility() != 0) {
                    return false;
                }
                this.a.R.go();
                return true;
            }
        });
        this.R.go();
        Object dVar = new d(this.A);
        this.S.setAdapter(dVar);
        dVar.notifyDataSetChanged();
        this.S.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DJILibraryView a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    e.b(c$l.es_);
                    if (d.getInstance().c()) {
                        this.a.a();
                    } else {
                        this.a.b();
                    }
                    this.a.R.go();
                } else if (i == 1) {
                    e.b(c$l.g);
                    this.a.T = new dji.pilot2.library.b.a(false, this.a.A);
                    this.a.T.a(this.a.p);
                    this.a.T.a(this.a.ab);
                    this.a.U.setVisibility(0);
                    r0 = ((Activity) this.a.A).getFragmentManager().beginTransaction();
                    r0.replace(R.id.crb, this.a.T);
                    r0.commit();
                    this.a.R.go();
                } else if (i == 2) {
                    e.b(c$l.c);
                    this.a.T = new dji.pilot2.library.b.a(true, this.a.A);
                    this.a.T.a(this.a.p);
                    this.a.T.a(this.a.ab);
                    this.a.U.setVisibility(0);
                    r0 = ((Activity) this.a.A).getFragmentManager().beginTransaction();
                    r0.replace(R.id.crb, this.a.T);
                    r0.commit();
                    this.a.R.go();
                }
            }
        });
        b(0);
    }

    public void updateMedias(boolean z) {
        if (z) {
            this.r.invalidViews();
            this.E.setCurrentItem(0);
        } else {
            this.q.invalidViews();
            this.E.setCurrentItem(1);
        }
        this.R.go();
        this.U.setVisibility(8);
    }

    public void deleteVideoView() {
        try {
            this.r.deleteVideoView();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initMember() {
        this.F = new OnPageChangeListener(this) {
            final /* synthetic */ DJILibraryView a;

            {
                this.a = r1;
            }

            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        if (this.a.C == 1) {
                            this.a.J.setSelected(true);
                            this.a.K.setSelected(false);
                            this.a.K.setTextColor(this.a.getResources().getColor(R.color.e7));
                            this.a.J.setTextColor(this.a.getResources().getColor(R.color.a_));
                        }
                        this.a.I = 0;
                        break;
                    case 1:
                        if (this.a.C == 0) {
                            this.a.J.setSelected(false);
                            this.a.K.setSelected(true);
                            this.a.J.setTextColor(this.a.getResources().getColor(R.color.e7));
                            this.a.K.setTextColor(this.a.getResources().getColor(R.color.a_));
                        }
                        this.a.I = 1;
                        break;
                }
                this.a.C = i;
            }

            public void onPageScrolled(int i, float f, int i2) {
                this.a.B.setTranslationX(((float) this.a.B.getWidth()) * (((float) i) + f));
            }

            public void onPageScrollStateChanged(int i) {
            }
        };
        this.G = new OnClickListener(this) {
            final /* synthetic */ DJILibraryView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.cr5:
                        this.a.exitSelectMode();
                        FragmentTransaction beginTransaction = ((Activity) this.a.A).getFragmentManager().beginTransaction();
                        Fragment findFragmentById = ((Activity) this.a.A).getFragmentManager().findFragmentById(R.id.cr1);
                        if (findFragmentById != null) {
                            beginTransaction.hide(findFragmentById).commit();
                            return;
                        }
                        return;
                    case R.id.cr6:
                        if (this.a.aa == null) {
                            this.a.aa = this.a.W.setPositiveButton(this.a.A.getResources().getString(R.string.v2_lib_sure), new DialogInterface.OnClickListener(this) {
                                final /* synthetic */ AnonymousClass4 a;

                                {
                                    this.a = r1;
                                }

                                public void onClick(DialogInterface dialogInterface, int i) {
                                    this.a.a.deleteVideoView();
                                    this.a.a.exitSelectMode();
                                    FragmentTransaction beginTransaction = ((Activity) this.a.a.A).getFragmentManager().beginTransaction();
                                    Fragment findFragmentById = ((Activity) this.a.a.A).getFragmentManager().findFragmentById(R.id.cr1);
                                    if (findFragmentById != null) {
                                        beginTransaction.hide(findFragmentById).commit();
                                    }
                                    e.b(c$l.n);
                                }
                            }).setNegativeButton(this.a.A.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener(this) {
                                final /* synthetic */ AnonymousClass4 a;

                                {
                                    this.a = r1;
                                }

                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            }).setMessage(this.a.A.getResources().getString(R.string.v2_lib_suredelete)).show();
                            return;
                        } else if (!this.a.aa.isShowing()) {
                            this.a.aa.show();
                            return;
                        } else {
                            return;
                        }
                    case R.id.cr7:
                        this.a.b(0);
                        return;
                    case R.id.cr8:
                        this.a.b(1);
                        return;
                    case R.id.cr_:
                        e.b(c$l.er_);
                        this.a.R.show();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    private void b() {
        Builder bVar = new b(this.A);
        updateSynState(bVar);
        bVar.setNeutralButton(R.string.v2_library_02, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ DJILibraryView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        bVar.show();
    }

    protected void a() {
        final MODE b = dji.pilot.publics.e.c.b();
        this.s = DataCameraGetPushStateInfo.getInstance().getMode();
        DJILogHelper.getInstance().LOGD("library", "get cameraMode " + this.s);
        d.getInstance().c(true);
        h.getInstance().c();
        if (this.s == b) {
            Intent intent = new Intent(getContext(), DJIPlayBackActivity.class);
            intent.setFlags(131072);
            intent.putExtra("isphotoview", true);
            getContext().startActivity(intent);
            return;
        }
        DataCameraSetMode.getInstance().a(b).start(new dji.midware.e.d(this) {
            final /* synthetic */ DJILibraryView b;

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("library", "switch cameraMode success " + b);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("library", "switch cameraMode failure " + aVar + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + b);
            }
        });
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        intent = new Intent(getContext(), DJIPlayBackActivity.class);
        intent.setFlags(131072);
        intent.putExtra("isphotoview", true);
        getContext().startActivity(intent);
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        MODE mode = dataCameraGetPushStateInfo.getMode();
        if (mode != this.s) {
            this.s = mode;
        }
    }

    public void onEventBackgroundThread(o oVar) {
        switch (AnonymousClass7.a[oVar.ordinal()]) {
            case 1:
                this.s = null;
                return;
            default:
                return;
        }
    }

    private void c() {
        this.B = findViewById(R.id.cr9);
        this.D = (DJILinearLayout) findViewById(R.id.ev);
    }

    private View a(int i) {
        if (i == 0) {
            return this.r;
        }
        if (i == 1) {
            return this.q;
        }
        return null;
    }

    private void b(int i) {
        if (this.I != i) {
            this.I = i;
            if (i == 0) {
                this.r.setParentViewPager(this.E);
            } else if (i == 1) {
            }
            this.E.setCurrentItem(i, true);
        }
    }

    private void d() {
        this.r.dispatchOnStart();
    }

    private void e() {
        this.r.dispatchOnStop();
    }

    public void enterSelectMode(int i, int i2) {
        this.L.show();
        this.M.show();
        this.N.show();
        if (i2 == 1 || i2 == 2) {
            this.N.go();
        } else {
            this.N.show();
        }
        if (i2 == 1 || i2 == 4) {
            this.L.setText(getResources().getString(R.string.v2_library_title_num, new Object[]{i + ""}));
        } else {
            this.L.setText(getResources().getString(R.string.v2_library_title_num_photo, new Object[]{i + ""}));
        }
        this.Q.go();
        if (i == 0) {
            this.N.setClickable(false);
            this.O.setBackgroundColor(getResources().getColor(R.color.om));
            this.L.setTextColor(getResources().getColor(R.color.na));
            this.M.setTextColor(getResources().getColor(R.color.na));
        } else {
            this.N.setClickable(true);
            this.O.setBackgroundColor(getResources().getColor(R.color.na));
            this.L.setTextColor(getResources().getColor(R.color.om));
            this.M.setTextColor(getResources().getColor(R.color.om));
        }
        this.P.go();
        this.D.hide();
        this.E.setPagingEnabled(false);
        this.p.a(i2, i);
    }

    public void exitSelectMode() {
        this.r.dismissManager();
        this.U.setVisibility(8);
        this.r.clearSelect();
        this.q.clearSelect();
        this.L.go();
        this.M.go();
        this.N.go();
        this.Q.show();
        this.O.setBackgroundColor(getResources().getColor(R.color.om));
        this.L.setTextColor(getResources().getColor(R.color.na));
        this.M.setTextColor(getResources().getColor(R.color.na));
        this.P.show();
        this.D.show();
        this.E.setPagingEnabled(true);
        this.p.b();
    }

    public void showTitleBar() {
        this.L.go();
        this.M.go();
        this.Q.show();
        this.O.setBackgroundColor(getResources().getColor(R.color.om));
        this.P.show();
        this.D.show();
    }

    public void updateSynState(Builder builder) {
        switch (d.getInstance().g()) {
            case 0:
                a(0, builder);
                return;
            case 1:
                a((int) R.string.v2_library_syn_pic_error1, builder);
                return;
            case 2:
                a((int) R.string.v2_library_syn_pic_error2, builder);
                return;
            case 3:
                a((int) R.string.v2_library_syn_pic_error3, builder);
                return;
            case 4:
                a((int) R.string.v2_library_syn_pic_error4, builder);
                return;
            case 5:
                a((int) R.string.v2_library_syn_pic_error5, builder);
                return;
            case 6:
                a((int) R.string.v2_library_syn_pic_error6, builder);
                return;
            case 7:
                a((int) R.string.v2_library_syn_pic_error7, builder);
                return;
            case 8:
                a((int) R.string.v2_library_syn_pic_error8, builder);
                return;
            default:
                return;
        }
    }

    private void a(int i, Builder builder) {
        if (i == 0) {
            builder.setMessage(R.string.v2_library_09);
        } else {
            builder.setMessage(i);
        }
    }

    public void onDestory() {
        e();
    }

    public void goToVideoTab() {
        b(0);
    }
}
