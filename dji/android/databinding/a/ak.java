package android.databinding.a;

import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.databinding.f;
import android.databinding.g;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

@g(a = {@f(a = ViewGroup.class, b = "android:alwaysDrawnWithCache", c = "setAlwaysDrawnWithCacheEnabled"), @f(a = ViewGroup.class, b = "android:animationCache", c = "setAnimationCacheEnabled"), @f(a = ViewGroup.class, b = "android:splitMotionEvents", c = "setMotionEventSplittingEnabled")})
public class ak {

    public interface a {
        void a(Animation animation);
    }

    public interface b {
        void a(Animation animation);
    }

    public interface c {
        void a(Animation animation);
    }

    public interface d {
        void a(View view, View view2);
    }

    public interface e {
        void a(View view, View view2);
    }

    @android.databinding.c(a = {"android:animateLayoutChanges"})
    @TargetApi(11)
    public static void a(ViewGroup viewGroup, boolean z) {
        if (z) {
            viewGroup.setLayoutTransition(new LayoutTransition());
        } else {
            viewGroup.setLayoutTransition(null);
        }
    }

    @android.databinding.c(a = {"android:onChildViewAdded", "android:onChildViewRemoved"}, b = false)
    public static void a(ViewGroup viewGroup, final d dVar, final e eVar) {
        if (dVar == null && eVar == null) {
            viewGroup.setOnHierarchyChangeListener(null);
        } else {
            viewGroup.setOnHierarchyChangeListener(new OnHierarchyChangeListener() {
                public void onChildViewAdded(View view, View view2) {
                    if (dVar != null) {
                        dVar.a(view, view2);
                    }
                }

                public void onChildViewRemoved(View view, View view2) {
                    if (eVar != null) {
                        eVar.a(view, view2);
                    }
                }
            });
        }
    }

    @android.databinding.c(a = {"android:onAnimationStart", "android:onAnimationEnd", "android:onAnimationRepeat"}, b = false)
    public static void a(ViewGroup viewGroup, final c cVar, final a aVar, final b bVar) {
        if (cVar == null && aVar == null && bVar == null) {
            viewGroup.setLayoutAnimationListener(null);
        } else {
            viewGroup.setLayoutAnimationListener(new AnimationListener() {
                public void onAnimationStart(Animation animation) {
                    if (cVar != null) {
                        cVar.a(animation);
                    }
                }

                public void onAnimationEnd(Animation animation) {
                    if (aVar != null) {
                        aVar.a(animation);
                    }
                }

                public void onAnimationRepeat(Animation animation) {
                    if (bVar != null) {
                        bVar.a(animation);
                    }
                }
            });
        }
    }
}
