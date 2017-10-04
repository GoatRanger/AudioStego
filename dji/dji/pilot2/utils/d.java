package dji.pilot2.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout.LayoutParams;
import dji.pilot.R;
import dji.pilot2.library.a.f;
import dji.pilot2.library.a.g;

public class d {
    private int a = 0;
    private int b = 0;
    private Context c;
    private float d = 0.0f;

    public d(Context context) {
        this.c = context;
    }

    public void a(final View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.c, R.anim.c0);
        LayoutParams layoutParams = new LayoutParams(this.a, this.b);
        loadAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ d b;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
            }
        });
        view.startAnimation(loadAnimation);
    }

    public void a(final View view, int i, int i2) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.c, R.anim.c0);
        final LayoutParams layoutParams = new LayoutParams(i, i2);
        loadAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ d c;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                view.setLayoutParams(layoutParams);
            }
        });
        view.startAnimation(loadAnimation);
    }

    public void b(final View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.c, R.anim.by);
        if (this.b == 0 && this.a == 0) {
            this.a = view.getWidth();
            this.b = view.getHeight();
        }
        LayoutParams layoutParams = new LayoutParams((int) (((double) this.a) * 0.9d), (int) (((double) this.b) * 0.9d));
        layoutParams.leftMargin = (this.a - ((int) (((double) this.a) * 0.9d))) / 2;
        layoutParams.topMargin = (this.b - ((int) (((double) this.b) * 0.9d))) / 2;
        layoutParams.rightMargin = (this.a - ((int) (((double) this.a) * 0.9d))) / 2;
        loadAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ d b;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
            }
        });
        view.startAnimation(loadAnimation);
    }

    public void a(final View view, final f fVar, final ExpandableListView expandableListView) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.c, R.anim.c0);
        LayoutParams layoutParams = new LayoutParams(this.a, this.b);
        loadAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ d d;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                fVar.notifyDataSetChanged();
                for (int i = 0; i < fVar.getGroupCount(); i++) {
                    if (expandableListView != null) {
                        expandableListView.expandGroup(i);
                    }
                }
            }
        });
        view.startAnimation(loadAnimation);
    }

    public void b(final View view, final f fVar, final ExpandableListView expandableListView) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.c, R.anim.by);
        if (this.b == 0 && this.a == 0) {
            this.a = view.getWidth();
            this.b = view.getHeight();
        }
        LayoutParams layoutParams = new LayoutParams((int) (((double) this.a) * 0.9d), (int) (((double) this.b) * 0.9d));
        layoutParams.leftMargin = (this.a - ((int) (((double) this.a) * 0.9d))) / 2;
        layoutParams.topMargin = (this.b - ((int) (((double) this.b) * 0.9d))) / 2;
        layoutParams.rightMargin = (this.a - ((int) (((double) this.a) * 0.9d))) / 2;
        loadAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ d d;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                fVar.notifyDataSetChanged();
                for (int i = 0; i < fVar.getGroupCount(); i++) {
                    if (expandableListView != null) {
                        expandableListView.expandGroup(i);
                    }
                }
            }
        });
        view.startAnimation(loadAnimation);
    }

    public void a(final View view, final ExpandableListView expandableListView, final g gVar) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.c, R.anim.by);
        if (this.b == 0 && this.a == 0) {
            this.a = view.getWidth();
            this.b = view.getHeight();
        }
        LayoutParams layoutParams = new LayoutParams((int) (((double) this.a) * 0.9d), (int) (((double) this.b) * 0.9d));
        layoutParams.leftMargin = (this.a - ((int) (((double) this.a) * 0.9d))) / 2;
        layoutParams.topMargin = (this.b - ((int) (((double) this.b) * 0.9d))) / 2;
        layoutParams.rightMargin = (this.a - ((int) (((double) this.a) * 0.9d))) / 2;
        loadAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ d d;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                gVar.notifyDataSetChanged();
                for (int i = 0; i < gVar.getGroupCount(); i++) {
                    expandableListView.expandGroup(i);
                }
            }
        });
        view.startAnimation(loadAnimation);
    }

    public void b(final View view, final ExpandableListView expandableListView, final g gVar) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.c, R.anim.c0);
        LayoutParams layoutParams = new LayoutParams(this.a, this.b);
        loadAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ d d;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                gVar.notifyDataSetChanged();
                for (int i = 0; i < gVar.getGroupCount(); i++) {
                    expandableListView.expandGroup(i);
                }
            }
        });
        view.startAnimation(loadAnimation);
    }

    public void b(View view, int i, int i2) {
        ViewGroup.LayoutParams layoutParams = new LayoutParams((int) (((double) i) * 0.9d), (int) (((double) i2) * 0.9d));
        layoutParams.leftMargin = (i - ((int) (((double) i) * 0.9d))) / 2;
        layoutParams.topMargin = (i2 - ((int) (((double) i2) * 0.9d))) / 2;
        layoutParams.rightMargin = (i - ((int) (((double) i) * 0.9d))) / 2;
        view.setLayoutParams(layoutParams);
    }

    public void c(View view, int i, int i2) {
        view.setLayoutParams(new LayoutParams(i, i2));
    }

    public void d(final View view, final int i, int i2) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.c, R.anim.by);
        this.a = i;
        this.b = i2;
        if (view != null) {
            Log.i("anim", "view is not null!");
        }
        LayoutParams layoutParams = new LayoutParams((int) (((double) this.a) * 0.9d), (int) (((double) this.b) * 0.9d));
        layoutParams.leftMargin = (this.a - ((int) (((double) this.a) * 0.9d))) / 2;
        layoutParams.topMargin = (this.b - ((int) (((double) this.b) * 0.9d))) / 2;
        layoutParams.rightMargin = (this.a - ((int) (((double) this.a) * 0.9d))) / 2;
        loadAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ d c;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                this.c.b(view, i, i);
            }
        });
        view.startAnimation(loadAnimation);
    }

    public void c(final View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.c, R.anim.c1);
        loadAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ d b;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.setVisibility(8);
            }
        });
        view.setVisibility(0);
        view.startAnimation(loadAnimation);
    }
}
