package android.databinding.a;

import android.databinding.c;
import android.databinding.m;
import android.databinding.n;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class ac {
    @m(a = "android:currentTab")
    public static int a(TabHost tabHost) {
        return tabHost.getCurrentTab();
    }

    @m(a = "android:currentTab")
    public static String b(TabHost tabHost) {
        return tabHost.getCurrentTabTag();
    }

    @c(a = {"android:currentTab"})
    public static void a(TabHost tabHost, int i) {
        if (tabHost.getCurrentTab() != i) {
            tabHost.setCurrentTab(i);
        }
    }

    @c(a = {"android:currentTab"})
    public static void a(TabHost tabHost, String str) {
        if (tabHost.getCurrentTabTag() != str) {
            tabHost.setCurrentTabByTag(str);
        }
    }

    @c(a = {"android:onTabChanged", "android:currentTabAttrChanged"}, b = false)
    public static void a(TabHost tabHost, final OnTabChangeListener onTabChangeListener, final n nVar) {
        if (nVar == null) {
            tabHost.setOnTabChangedListener(onTabChangeListener);
        } else {
            tabHost.setOnTabChangedListener(new OnTabChangeListener() {
                public void onTabChanged(String str) {
                    if (onTabChangeListener != null) {
                        onTabChangeListener.onTabChanged(str);
                    }
                    nVar.a();
                }
            });
        }
    }
}
