package android.databinding.a;

import android.annotation.TargetApi;
import android.databinding.f;
import android.databinding.g;
import android.os.Build.VERSION;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SearchView.OnSuggestionListener;

@g(a = {@f(a = SearchView.class, b = "android:onQueryTextFocusChange", c = "setOnQueryTextFocusChangeListener"), @f(a = SearchView.class, b = "android:onSearchClick", c = "setOnSearchClickListener"), @f(a = SearchView.class, b = "android:onClose", c = "setOnCloseListener")})
public class x {

    @TargetApi(11)
    public interface a {
        boolean a(String str);
    }

    @TargetApi(11)
    public interface b {
        boolean a(String str);
    }

    @TargetApi(11)
    public interface c {
        boolean a(int i);
    }

    @TargetApi(11)
    public interface d {
        boolean a(int i);
    }

    @android.databinding.c(a = {"android:onQueryTextSubmit", "android:onQueryTextChange"}, b = false)
    public static void a(SearchView searchView, final b bVar, final a aVar) {
        if (VERSION.SDK_INT < 11) {
            return;
        }
        if (bVar == null && aVar == null) {
            searchView.setOnQueryTextListener(null);
        } else {
            searchView.setOnQueryTextListener(new OnQueryTextListener() {
                public boolean onQueryTextSubmit(String str) {
                    if (bVar != null) {
                        return bVar.a(str);
                    }
                    return false;
                }

                public boolean onQueryTextChange(String str) {
                    if (aVar != null) {
                        return aVar.a(str);
                    }
                    return false;
                }
            });
        }
    }

    @android.databinding.c(a = {"android:onSuggestionSelect", "android:onSuggestionClick"}, b = false)
    public static void a(SearchView searchView, final d dVar, final c cVar) {
        if (VERSION.SDK_INT < 11) {
            return;
        }
        if (dVar == null && cVar == null) {
            searchView.setOnSuggestionListener(null);
        } else {
            searchView.setOnSuggestionListener(new OnSuggestionListener() {
                public boolean onSuggestionSelect(int i) {
                    if (dVar != null) {
                        return dVar.a(i);
                    }
                    return false;
                }

                public boolean onSuggestionClick(int i) {
                    if (cVar != null) {
                        return cVar.a(i);
                    }
                    return false;
                }
            });
        }
    }
}
