package dji.pilot.playback.litchi;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import dji.logic.album.a.b.f;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.pilot.usercenter.f.d;
import dji.pilot.usercenter.mode.g;
import dji.pilot2.main.a.a;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class DJIPlayBackBaseView extends FrameLayout {
    public abstract void clearSelects();

    public abstract void deleteSelects();

    public abstract void selectAllPic();

    public DJIPlayBackBaseView(Context context) {
        this(context, null);
    }

    public DJIPlayBackBaseView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJIPlayBackBaseView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (!isInEditMode()) {
        }
    }

    public void sortPic(List<g> list, List<a> list2) {
        Collections.sort(list, new Comparator<g>(this) {
            final /* synthetic */ DJIPlayBackBaseView a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((g) obj, (g) obj2);
            }

            public int a(g gVar, g gVar2) {
                if (d.c(gVar.g)) {
                    if (d.c(gVar2.g)) {
                        return gVar2.a().h.compareTo(gVar.a().h);
                    }
                    return gVar2.b().h.compareTo(gVar.a().h);
                } else if (d.c(gVar2.g)) {
                    return gVar2.a().h.compareTo(gVar.b().h);
                } else {
                    return gVar2.b().h.compareTo(gVar.b().h);
                }
            }
        });
        initAlbumGroup(list, list2);
    }

    public void sortPic(List<DJIAlbumFileInfo> list, List<a> list2, boolean z) {
        Collections.sort(list, new Comparator<DJIAlbumFileInfo>(this) {
            final /* synthetic */ DJIPlayBackBaseView a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((DJIAlbumFileInfo) obj, (DJIAlbumFileInfo) obj2);
            }

            public int a(DJIAlbumFileInfo dJIAlbumFileInfo, DJIAlbumFileInfo dJIAlbumFileInfo2) {
                return Long.valueOf(dJIAlbumFileInfo2.b).compareTo(Long.valueOf(dJIAlbumFileInfo.b));
            }
        });
        initAlbumGroup(list, list2, true);
    }

    public void initAlbumGroup(List<g> list, List<a> list2) {
        String str = null;
        List arrayList = new ArrayList();
        list2.clear();
        if (list.size() > 0) {
            a aVar;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i < list.size()) {
                int i4;
                String trim;
                if (d.c(((g) list.get(i)).g)) {
                    int i5;
                    if (i <= 0 || !d.c(((g) list.get(i - 1)).g)) {
                        if (i <= 0 || !d.b(((g) list.get(i - 1)).g)) {
                            i4 = i3 + 1;
                            trim = ((g) list.get(i)).a().h.subSequence(0, 10).toString().trim();
                            arrayList.add(list.get(i));
                            i5 = i2;
                            i2 = i4;
                            i4 = i5;
                        } else if (((g) list.get(i)).a().h.subSequence(0, 10).toString().trim().equals(((g) list.get(i - 1)).b().h.subSequence(0, 10).toString().trim())) {
                            i4 = i3 + 1;
                            trim = ((g) list.get(i - 1)).b().h.subSequence(0, 10).toString().trim();
                            arrayList.add(list.get(i));
                            i5 = i2;
                            i2 = i4;
                            i4 = i5;
                        } else {
                            aVar = new a();
                            aVar.a(str);
                            aVar.a(i3);
                            aVar.b(i2);
                            aVar.a(arrayList);
                            list2.add(aVar);
                            trim = ((g) list.get(i)).a().h.subSequence(0, 10).toString().trim();
                            arrayList.clear();
                            arrayList.add(list.get(i));
                            i4 = 0;
                            i2 = 1;
                        }
                    } else if (((g) list.get(i)).a().h.subSequence(0, 10).toString().trim().equals(((g) list.get(i - 1)).a().h.subSequence(0, 10).toString().trim())) {
                        i4 = i3 + 1;
                        trim = ((g) list.get(i - 1)).a().h.subSequence(0, 10).toString().trim();
                        arrayList.add(list.get(i));
                        i5 = i2;
                        i2 = i4;
                        i4 = i5;
                    } else {
                        aVar = new a();
                        aVar.a(str);
                        aVar.a(i3);
                        aVar.b(i2);
                        aVar.a(arrayList);
                        list2.add(aVar);
                        trim = ((g) list.get(i)).a().h.subSequence(0, 10).toString().trim();
                        arrayList.clear();
                        arrayList.add(list.get(i));
                        i4 = 0;
                        i2 = 1;
                    }
                } else if (!d.b(((g) list.get(i)).g)) {
                    trim = str;
                    i4 = i2;
                    i2 = i3;
                } else if (i <= 0 || !d.c(((g) list.get(i - 1)).g)) {
                    if (i <= 0 || !d.b(((g) list.get(i - 1)).g)) {
                        i4 = i2 + 1;
                        trim = ((g) list.get(i)).b().h.subSequence(0, 10).toString().trim();
                        arrayList.add(list.get(i));
                        i2 = i3;
                    } else if (((g) list.get(i)).b().h.subSequence(0, 10).toString().trim().equals(((g) list.get(i - 1)).b().h.subSequence(0, 10).toString().trim())) {
                        i4 = i2 + 1;
                        trim = ((g) list.get(i - 1)).b().h.subSequence(0, 10).toString().trim();
                        arrayList.add(list.get(i));
                        i2 = i3;
                    } else {
                        aVar = new a();
                        aVar.a(str);
                        aVar.a(i3);
                        aVar.b(i2);
                        aVar.a(arrayList);
                        list2.add(aVar);
                        trim = ((g) list.get(i)).b().h.subSequence(0, 10).toString().trim();
                        arrayList.clear();
                        arrayList.add(list.get(i));
                        i4 = 1;
                        i2 = 0;
                    }
                } else if (((g) list.get(i)).b().h.subSequence(0, 10).toString().trim().equals(((g) list.get(i - 1)).a().h.subSequence(0, 10).toString().trim())) {
                    i4 = i2 + 1;
                    trim = ((g) list.get(i - 1)).a().h.subSequence(0, 10).toString().trim();
                    arrayList.add(list.get(i));
                    i2 = i3;
                } else {
                    aVar = new a();
                    aVar.a(str);
                    aVar.a(i3);
                    aVar.b(i2);
                    aVar.a(arrayList);
                    list2.add(aVar);
                    trim = ((g) list.get(i)).b().h.subSequence(0, 10).toString().trim();
                    arrayList.clear();
                    arrayList.add(list.get(i));
                    i4 = 1;
                    i2 = 0;
                }
                i++;
                i3 = i2;
                i2 = i4;
                str = trim;
            }
            aVar = new a();
            aVar.a(str);
            aVar.a(i3);
            aVar.b(i2);
            aVar.a(arrayList);
            list2.add(aVar);
        }
    }

    public void initAlbumGroup(List<DJIAlbumFileInfo> list, List<a> list2, boolean z) {
        list2.clear();
        int i = 0;
        int i2 = 0;
        String str = null;
        if (list.size() > 0) {
            a aVar;
            List arrayList = new ArrayList();
            Object obj = 1;
            int i3 = 0;
            while (i3 < list.size()) {
                Object obj2;
                String str2;
                int i4;
                if (((DJIAlbumFileInfo) list.get(i3)).j == f.e) {
                    if (!new File(h.e + h.d + "/DJIPANO_" + ((DJIAlbumFileInfo) list.get(i3)).c + a.n).exists()) {
                        obj2 = obj;
                        str2 = str;
                        i4 = i2;
                        i2 = i;
                        i3++;
                        i = i2;
                        i2 = i4;
                        str = str2;
                        obj = obj2;
                    }
                }
                f fVar = ((DJIAlbumFileInfo) list.get(i3)).j;
                String charSequence;
                if (obj != null) {
                    if (fVar == f.f || fVar == f.b || fVar == f.a || fVar == f.e) {
                        i4 = i2;
                        i2 = i + 1;
                    } else {
                        i4 = i2 + 1;
                        i2 = i;
                    }
                    charSequence = DateFormat.format("yyyy-MM-dd", ((DJIAlbumFileInfo) list.get(i3)).b).toString();
                    arrayList.add(list.get(i3));
                    str2 = charSequence;
                    obj2 = null;
                } else if (DateFormat.format("yyyy-MM-dd", ((DJIAlbumFileInfo) list.get(i3)).b).toString().equals(DateFormat.format("yyyy-MM-dd", ((DJIAlbumFileInfo) list.get(i3 - 1)).b))) {
                    if (fVar == f.f || fVar == f.b || fVar == f.a || fVar == f.e) {
                        i4 = i2;
                        i2 = i + 1;
                    } else {
                        i4 = i2 + 1;
                        i2 = i;
                    }
                    charSequence = DateFormat.format("yyyy-MM-dd", ((DJIAlbumFileInfo) list.get(i3)).b).toString();
                    arrayList.add(list.get(i3));
                    r12 = obj;
                    str2 = charSequence;
                    obj2 = r12;
                } else {
                    aVar = new a();
                    aVar.a(str);
                    aVar.a(i);
                    aVar.b(i2);
                    aVar.b(arrayList);
                    list2.add(aVar);
                    arrayList.clear();
                    arrayList.add(list.get(i3));
                    if (fVar == f.f || fVar == f.b || fVar == f.a || fVar == f.e) {
                        i2 = 1;
                        i4 = 0;
                    } else {
                        i4 = 1;
                        i2 = 0;
                    }
                    r12 = obj;
                    str2 = DateFormat.format("yyyy-MM-dd", ((DJIAlbumFileInfo) list.get(i3)).b).toString();
                    obj2 = r12;
                }
                i3++;
                i = i2;
                i2 = i4;
                str = str2;
                obj = obj2;
            }
            aVar = new a();
            aVar.a(str);
            aVar.a(i);
            aVar.b(i2);
            aVar.b(arrayList);
            list2.add(aVar);
        }
    }
}
