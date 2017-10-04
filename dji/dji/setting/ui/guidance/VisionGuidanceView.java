package dji.setting.ui.guidance;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.e.d;
import dji.pilot.fpv.control.o;
import dji.pilot.fpv.model.n$b;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.thirdparty.a.c;

public class VisionGuidanceView extends LinearLayout implements OnClickListener, OnCheckedChangeListener {
    private Switch a = null;
    private LinearLayout b = null;
    private Switch c = null;
    private LinearLayout d = null;
    private Switch e = null;
    private TextView f = null;

    public VisionGuidanceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_guidance_setting);
        if (!isInEditMode()) {
            this.a = (Switch) findViewById(R.id.setting_ui_item_guidance_switch);
            this.b = (LinearLayout) findViewById(R.id.setting_ui_item_ass_ly);
            this.c = (Switch) findViewById(R.id.setting_ui_item_ass_switch);
            this.d = (LinearLayout) findViewById(R.id.setting_ui_item_tip_ly);
            this.e = (Switch) findViewById(R.id.setting_ui_item_tip_switch);
            this.f = (TextView) findViewById(R.id.setting_ui_item_use_tv);
            this.a.setOnCheckedChangeListener(this);
            this.c.setOnCheckedChangeListener(this);
            this.e.setOnCheckedChangeListener(this);
            this.f.setOnClickListener(this);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
        int i;
        DataFlycSetParams dataFlycSetParams;
        if (compoundButton == this.a) {
            if (o.a() != z) {
                i = z ? 1 : 0;
                dataFlycSetParams = new DataFlycSetParams();
                if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 10) {
                    dataFlycSetParams.a(new String[]{"g_config.mvo_cfg.mvo_func_en_0", "g_config.avoid_obstacle_limit_cfg.avoid_obstacle_enable_0"});
                    dataFlycSetParams.a(new Integer[]{Integer.valueOf(i), Integer.valueOf(i)});
                } else if (z) {
                    dataFlycSetParams.a(new String[]{"g_config.mvo_cfg.mvo_func_en_0", "g_config.avoid_obstacle_limit_cfg.avoid_obstacle_enable_0", "g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0"});
                    dataFlycSetParams.a(new Integer[]{Integer.valueOf(i), Integer.valueOf(i), Integer.valueOf(i)});
                } else {
                    dataFlycSetParams.a(new String[]{"g_config.mvo_cfg.mvo_func_en_0", "g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0"});
                    dataFlycSetParams.a(new Integer[]{Integer.valueOf(i), Integer.valueOf(i)});
                }
                this.a.setEnabled(false);
                dataFlycSetParams.start(new d(this) {
                    final /* synthetic */ VisionGuidanceView b;

                    public void onSuccess(Object obj) {
                        this.b.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.b.a.setEnabled(true);
                                o.a(z);
                                o.b(z);
                                o.a(this.a.b.getContext(), true);
                                this.a.b.a(false);
                            }
                        });
                        DJILogHelper.getInstance().LOGD("", "==== Guidance success", false, true);
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.b.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.b.a.setEnabled(true);
                                this.a.b.a.setChecked(o.a());
                            }
                        });
                    }
                });
            }
        } else if (compoundButton == this.c) {
            if (o.b() != z) {
                i = z ? 1 : 0;
                dataFlycSetParams = new DataFlycSetParams();
                if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 10) {
                    dataFlycSetParams.a(new String[]{"g_config.avoid_obstacle_limit_cfg.avoid_obstacle_enable_0"});
                    dataFlycSetParams.a(new Integer[]{Integer.valueOf(i)});
                } else if (z) {
                    dataFlycSetParams.a(new String[]{"g_config.avoid_obstacle_limit_cfg.avoid_obstacle_enable_0", "g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0"});
                    dataFlycSetParams.a(new Integer[]{Integer.valueOf(i), Integer.valueOf(i)});
                } else {
                    dataFlycSetParams.a(new String[]{"g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0"});
                    dataFlycSetParams.a(new Integer[]{Integer.valueOf(i)});
                }
                this.c.setEnabled(false);
                dataFlycSetParams.start(new d(this) {
                    final /* synthetic */ VisionGuidanceView b;

                    public void onSuccess(Object obj) {
                        this.b.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.b.c.setEnabled(true);
                                o.b(z);
                            }
                        });
                        DJILogHelper.getInstance().LOGD("", "==== Ass success", false, true);
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.b.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.b.c.setEnabled(true);
                                this.a.b.c.setChecked(o.b());
                            }
                        });
                    }
                });
            }
        } else if (compoundButton == this.e) {
            o.a(getContext(), z);
        }
    }

    public void onClick(View view) {
        if (R.id.setting_ui_item_use_tv == view.getId()) {
            c.a().e(n$b.SHOW_GUIDANCE_USER);
        }
    }

    private void a(boolean z) {
        if (o.a()) {
            this.b.setVisibility(0);
            getAssData();
            this.d.setVisibility(0);
            this.e.setChecked(o.c());
            return;
        }
        this.b.setVisibility(8);
        this.d.setVisibility(8);
    }

    private void b() {
        new DataFlycGetParams().setInfos(new String[]{"g_config.mvo_cfg.mvo_func_en_0"}).start(new d(this) {
            final /* synthetic */ VisionGuidanceView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        boolean z = dji.midware.data.manager.P3.d.read("g_config.mvo_cfg.mvo_func_en_0").value.intValue() != 0;
                        o.a(z);
                        this.a.a.a.setChecked(z);
                        this.a.a.a(true);
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private void getAssData() {
        final String str = DataOsdGetPushCommon.getInstance().getFlycVersion() >= 10 ? "g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0" : "g_config.avoid_obstacle_limit_cfg.avoid_obstacle_enable_0";
        new DataFlycGetParams().setInfos(new String[]{str}).start(new d(this) {
            final /* synthetic */ VisionGuidanceView b;

            public void onSuccess(Object obj) {
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        o.b(dji.midware.data.manager.P3.d.read(str).value.intValue() != 0);
                        this.a.b.c.setChecked(o.b());
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private void c() {
        this.a.setChecked(o.a());
        this.c.setChecked(o.b());
        this.e.setChecked(o.c());
    }

    public void onEventMainThread(dji.midware.data.manager.P3.o oVar) {
        if (oVar == dji.midware.data.manager.P3.o.b) {
            onEventMainThread(i.getInstance().c());
        }
    }

    public void onEventMainThread(ProductType productType) {
        if (dji.pilot.publics.e.a.r(productType)) {
            setVisibility(0);
            b();
            return;
        }
        setVisibility(8);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            c();
            onEventMainThread(i.getInstance().c());
        }
    }

    protected void onDetachedFromWindow() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
