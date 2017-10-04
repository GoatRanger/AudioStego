package com.nokia.maps;

import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class fb {
    private Context a;
    private TextView b = null;

    public fb(Context context) {
        this.a = context;
        b();
    }

    public TextView a() {
        return this.b;
    }

    private void b() {
        if (MapsEngine.isEval()) {
            long permissionStringTimeExpiry = MapsEngine.getPermissionStringTimeExpiry();
            if (permissionStringTimeExpiry <= 0) {
                this.b = null;
                return;
            }
            long time = new Date().getTime();
            permissionStringTimeExpiry = TimeUnit.SECONDS.toMillis(permissionStringTimeExpiry);
            if (time > permissionStringTimeExpiry) {
                this.b = null;
                return;
            }
            Date date = new Date(permissionStringTimeExpiry);
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            instance.add(6, -30);
            this.b = new TextView(this.a);
            this.b.setTextColor(SupportMenu.CATEGORY_MASK);
            Calendar.getInstance().setTime(date);
            this.b.append(String.format("HERE SDK for Android Evaluation Package expires on %04d/%02d/%02d", new Object[]{Integer.valueOf(r1.get(1)), Integer.valueOf(r1.get(2) + 1), Integer.valueOf(r1.get(5))}));
            this.b.setGravity(49);
            if (instance.getTimeInMillis() <= time) {
                return;
            }
            if (MapsEngine.J()) {
                MapsEngine.c(false);
                Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setAnimationListener(new AnimationListener(this) {
                    final /* synthetic */ fb a;

                    {
                        this.a = r1;
                    }

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        this.a.b.setVisibility(8);
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                alphaAnimation.setDuration(10000);
                alphaAnimation.setRepeatMode(2);
                alphaAnimation.setRepeatCount(1);
                this.b.startAnimation(alphaAnimation);
                return;
            }
            this.b = null;
        }
    }
}
