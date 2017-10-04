package dji.setting.ui.vision;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataFlycGetPushAvoidParam;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.params.P3.ParamInfo;
import dji.pilot.setting.ui.R;
import dji.pilot.visual.util.c;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;

public class VisionFrontAssView extends DividerLinearLayout implements OnCheckedChangeListener, a {
    private TextView f = null;
    private ImageView g = null;
    private TextView h = null;
    private ImageView i = null;
    private TextView l = null;
    private ImageView m = null;
    private TextView n = null;
    private ImageView o = null;
    private TextView p = null;
    private Switch q = null;
    private ParamInfo r = null;

    public VisionFrontAssView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_vision_forward_ass);
        if (!isInEditMode()) {
            this.r = d.read("g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0");
            this.f = (TextView) findViewById(R.id.setting_ui_item_title);
            this.g = (ImageView) findViewById(R.id.setting_ui_vision_ass_desc1_img);
            this.h = (TextView) findViewById(R.id.setting_ui_vision_ass_desc1_tv);
            this.i = (ImageView) findViewById(R.id.setting_ui_vision_ass_desc2_img);
            this.l = (TextView) findViewById(R.id.setting_ui_vision_ass_desc2_tv);
            this.m = (ImageView) findViewById(R.id.setting_ui_vision_ass_desc3_img);
            this.n = (TextView) findViewById(R.id.setting_ui_vision_ass_desc3_tv);
            this.o = (ImageView) findViewById(R.id.setting_ui_vision_ass_desc4_img);
            this.p = (TextView) findViewById(R.id.setting_ui_vision_ass_desc4_tv);
            this.q = (Switch) findViewById(R.id.setting_ui_item_switch);
            this.q.setOnCheckedChangeListener(this);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton == this.q) {
            if ((this.r.value.intValue() != 0) != z) {
                if (z) {
                    this.q.setEnabled(false);
                    DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{"g_config.avoid_obstacle_limit_cfg.avoid_obstacle_enable_0", "g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0", a.e});
                    dataFlycSetParams.a(new Integer[]{Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1)});
                    dataFlycSetParams.start(new dji.midware.e.d(this) {
                        final /* synthetic */ VisionFrontAssView a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            this.a.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.q.setEnabled(true);
                                }
                            });
                            DJILogHelper.getInstance().LOGD(c.a, "==== Brake On success", false, true);
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.a.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.a(false);
                                    this.a.a.q.setEnabled(true);
                                }
                            });
                        }
                    });
                    return;
                }
                dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_vision_ass_tip_content, new OnClickListener(this) {
                    final /* synthetic */ VisionFrontAssView a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.q.setEnabled(false);
                        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
                        dataFlycSetParams.a(new String[]{"g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0", a.e});
                        dataFlycSetParams.a(new Number[]{Integer.valueOf(0), Integer.valueOf(0)});
                        dataFlycSetParams.start(new dji.midware.e.d(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                                this.a.a.post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        this.a.a.a.q.setEnabled(true);
                                    }
                                });
                                DJILogHelper.getInstance().LOGD(c.a, "==== Brake Off Success", false, true);
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                this.a.a.post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        this.a.a.a.a(false);
                                        this.a.a.a.q.setEnabled(true);
                                    }
                                });
                            }
                        });
                    }
                }, new OnClickListener(this) {
                    final /* synthetic */ VisionFrontAssView a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.a(false);
                    }
                }).setCancelable(false);
            }
        }
    }

    private void a(boolean z) {
        boolean isUserAvoidEnable = z ? DataFlycGetPushAvoidParam.getInstance().isUserAvoidEnable() : this.r.value.intValue() != 0;
        this.q.setChecked(isUserAvoidEnable);
    }

    public void onEventMainThread(a.a aVar) {
        if ("g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0".equals(aVar.a)) {
            a(false);
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.b) {
            onEventMainThread(i.getInstance().c());
        }
    }

    public void onEventMainThread(ProductType productType) {
        if (dji.pilot.publics.e.a.k(productType)) {
            setVisibility(0);
            a(productType);
            a(true);
            b.getInstance().a("g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0");
            return;
        }
        setVisibility(8);
    }

    private void a(ProductType productType) {
        if (dji.pilot.fpv.model.o.a.RADAR == dji.pilot.publics.e.a.m(productType)) {
            this.f.setText(R.string.setting_ui_vision_frontback_ass);
            this.h.setText(R.string.setting_ui_vision_frontback_ass_desc1);
            this.l.setText(R.string.setting_ui_vision_frontback_ghass_desc);
            this.n.setText(R.string.setting_ui_vision_frontback_ass_desc2);
            this.p.setText(R.string.setting_ui_vision_frontback_ass_desc3);
            this.g.setBackgroundResource(R.drawable.setting_ui_vision_forback_ass_icon1);
            return;
        }
        this.f.setText(R.string.setting_ui_vision_ass);
        this.h.setText(R.string.setting_ui_vision_ass_desc1);
        this.l.setText(R.string.setting_ui_vision_ghass_desc);
        this.n.setText(R.string.setting_ui_vision_ass_desc2);
        this.p.setText(R.string.setting_ui_vision_ass_desc3);
        this.g.setBackgroundResource(R.drawable.setting_ui_vision_ass_icon1);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!dji.thirdparty.a.c.a().c(this)) {
                dji.thirdparty.a.c.a().a(this);
            }
            onEventMainThread(i.getInstance().c());
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
