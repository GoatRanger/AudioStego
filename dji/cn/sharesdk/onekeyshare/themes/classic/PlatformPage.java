package cn.sharesdk.onekeyshare.themes.classic;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.sharesdk.framework.CustomPlatform;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.CustomerLogo;
import cn.sharesdk.onekeyshare.OnekeySharePage;
import cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import com.mob.tools.gui.MobViewPager;
import com.mob.tools.gui.ViewPagerAdapter;
import com.mob.tools.utils.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public abstract class PlatformPage extends OnekeySharePage {
    private Animation animHide;
    private Animation animShow;
    private Runnable beforeFinish;
    private boolean finished;
    private ClassicTheme impl;
    private LinearLayout llPanel;

    protected abstract PlatformPageAdapter newAdapter(ArrayList<Object> arrayList);

    public PlatformPage(OnekeyShareThemeImpl onekeyShareThemeImpl) {
        super(onekeyShareThemeImpl);
        this.impl = (ClassicTheme) R.forceCast(onekeyShareThemeImpl);
    }

    public void onCreate() {
        this.activity.getWindow().setBackgroundDrawable(new ColorDrawable(1275068416));
        initAnims();
        View linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(1);
        this.activity.setContentView(linearLayout);
        View textView = new TextView(this.activity);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.weight = 1.0f;
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PlatformPage.this.finish();
            }
        });
        linearLayout.addView(textView, layoutParams);
        this.llPanel = new LinearLayout(this.activity);
        this.llPanel.setOrientation(1);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        this.llPanel.setAnimation(this.animShow);
        linearLayout.addView(this.llPanel, layoutParams2);
        linearLayout = new MobViewPager(this.activity);
        ViewPagerAdapter newAdapter = newAdapter(collectCells());
        this.llPanel.addView(linearLayout, new LinearLayout.LayoutParams(-1, newAdapter.getPanelHeight()));
        View indicatorView = new IndicatorView(this.activity);
        this.llPanel.addView(indicatorView, new LinearLayout.LayoutParams(-1, newAdapter.getBottomHeight()));
        indicatorView.setScreenCount(newAdapter.getCount());
        indicatorView.onScreenChange(0, 0);
        newAdapter.setIndicator(indicatorView);
        linearLayout.setAdapter(newAdapter);
    }

    protected ArrayList<Object> collectCells() {
        int i = 0;
        ArrayList<Object> arrayList = new ArrayList();
        Platform[] platformList = ShareSDK.getPlatformList();
        if (platformList == null) {
            platformList = new Platform[0];
        }
        HashMap hiddenPlatforms = getHiddenPlatforms();
        if (hiddenPlatforms == null) {
            hiddenPlatforms = new HashMap();
        }
        int length = platformList.length;
        while (i < length) {
            Platform platform = platformList[i];
            if (!hiddenPlatforms.containsKey(platform.getName())) {
                arrayList.add(platform);
            }
            i++;
        }
        Collection customerLogos = getCustomerLogos();
        if (customerLogos != null && customerLogos.size() > 0) {
            arrayList.addAll(customerLogos);
        }
        return arrayList;
    }

    public final void showEditPage(final Platform platform) {
        this.beforeFinish = new Runnable() {
            public void run() {
                boolean access$000 = PlatformPage.this.isSilent();
                boolean z = platform instanceof CustomPlatform;
                boolean access$100 = PlatformPage.this.isUseClientToShare(platform);
                if (access$000 || z || access$100) {
                    PlatformPage.this.shareSilently(platform);
                    return;
                }
                ShareParams access$300 = PlatformPage.this.formateShareData(platform);
                if (access$300 != null) {
                    ShareSDK.logDemoEvent(3, null);
                    if (PlatformPage.this.getCustomizeCallback() != null) {
                        PlatformPage.this.getCustomizeCallback().onShare(platform, access$300);
                    }
                    PlatformPage.this.impl.showEditPage(PlatformPage.this.activity, platform, access$300);
                }
            }
        };
        finish();
    }

    public final void performCustomLogoClick(final View view, final CustomerLogo customerLogo) {
        this.beforeFinish = new Runnable() {
            public void run() {
                customerLogo.listener.onClick(view);
            }
        };
        finish();
    }

    private void initAnims() {
        this.animShow = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        this.animShow.setDuration(300);
        this.animHide = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        this.animHide.setDuration(300);
    }

    public boolean onFinish() {
        if (this.finished) {
            this.finished = false;
            return false;
        }
        this.animHide.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (PlatformPage.this.beforeFinish == null) {
                    ShareSDK.logDemoEvent(2, null);
                } else {
                    PlatformPage.this.beforeFinish.run();
                    PlatformPage.this.beforeFinish = null;
                }
                PlatformPage.this.finished = true;
                PlatformPage.this.finish();
            }
        });
        this.llPanel.clearAnimation();
        this.llPanel.setAnimation(this.animHide);
        this.llPanel.setVisibility(8);
        return true;
    }
}
