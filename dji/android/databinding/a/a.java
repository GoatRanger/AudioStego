package android.databinding.a;

import android.databinding.c;
import android.databinding.f;
import android.databinding.g;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

@g(a = {@f(a = AbsListView.class, b = "android:listSelector", c = "setSelector"), @f(a = AbsListView.class, b = "android:scrollingCache", c = "setScrollingCacheEnabled"), @f(a = AbsListView.class, b = "android:smoothScrollbar", c = "setSmoothScrollbarEnabled"), @f(a = AbsListView.class, b = "android:onMovedToScrapHeap", c = "setRecyclerListener")})
public class a {

    public interface a {
        void a(AbsListView absListView, int i, int i2, int i3);
    }

    public interface b {
        void a(AbsListView absListView, int i);
    }

    @c(a = {"android:onScroll", "android:onScrollStateChanged"}, b = false)
    public static void a(AbsListView absListView, final a aVar, final b bVar) {
        absListView.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (bVar != null) {
                    bVar.a(absListView, i);
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (aVar != null) {
                    aVar.a(absListView, i, i2, i3);
                }
            }
        });
    }
}
