package dji.pilot2.library;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;
import com.dji.videouploadsdk.a.b;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.pilot.storage.a;
import dji.pilot.usercenter.f.c;
import dji.pilot.usercenter.f.d;
import dji.pilot.usercenter.mode.g;
import dji.pilot2.library.model.DJISycAlbumModel;
import dji.pilot2.media.activity.DJIPhotoPreveiwActivity;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class f {
    private static f b = null;
    private HashMap<String, String> a;

    private f() {
        this.a = null;
        this.a = new HashMap();
    }

    public static f getInstance() {
        if (b == null) {
            b = new f();
        }
        return b;
    }

    private void b(ArrayList<DJISycAlbumModel> arrayList, HashMap<String, DJISycAlbumModel> hashMap, Context context) {
        String[] e = a.e(context);
        int length = e.length;
        int i = 0;
        while (i < length) {
            Object obj = e[i];
            if (!TextUtils.isEmpty(obj)) {
                File file = new File(obj);
                if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        for (int i2 = 0; i2 < listFiles.length; i2++) {
                            g a = g.a(listFiles[i2], false);
                            if (a != null && d.c(a.g)) {
                                DJISycAlbumModel dJISycAlbumModel = new DJISycAlbumModel(a, true);
                                dJISycAlbumModel.sortTime = listFiles[i2].lastModified();
                                dJISycAlbumModel.fileLevel = 1;
                                synchronized (arrayList) {
                                    arrayList.add(dJISycAlbumModel);
                                }
                                hashMap.put(b.a(listFiles[i2]), dJISycAlbumModel);
                            }
                        }
                        continue;
                    } else {
                        return;
                    }
                }
                i++;
            } else {
                return;
            }
        }
    }

    public void a(ArrayList<DJISycAlbumModel> arrayList, HashMap<String, DJISycAlbumModel> hashMap, List<String> list, Context context) {
        File[] listFiles;
        g a;
        String[] split;
        Object obj;
        String replaceAll;
        String f = b.f(context);
        String g = b.g(context);
        synchronized (arrayList) {
            arrayList.clear();
        }
        hashMap.clear();
        File file = new File(f);
        if (file.isDirectory()) {
            listFiles = file.listFiles();
            if (listFiles != null) {
                for (int i = 0; i < listFiles.length; i++) {
                    a = g.a(listFiles[i], false);
                    if (a != null && d.c(a.g)) {
                        split = a.f.split("_");
                        if (split[0].equals("screen")) {
                            obj = split[1];
                            if (list.contains(obj)) {
                                continue;
                            } else {
                                DJISycAlbumModel dJISycAlbumModel = new DJISycAlbumModel(a, true);
                                try {
                                    dJISycAlbumModel.sortTime = Long.parseLong(a(split[2]));
                                } catch (Exception e) {
                                    dJISycAlbumModel.sortTime = listFiles[i].lastModified();
                                }
                                dJISycAlbumModel.fileLevel = 1;
                                replaceAll = a.e.replaceAll("screen", "thumb");
                                if (new File(replaceAll).exists()) {
                                    dJISycAlbumModel.isThumb = true;
                                    dJISycAlbumModel.mThumbmAbsPath = replaceAll;
                                }
                                synchronized (arrayList) {
                                    arrayList.add(dJISycAlbumModel);
                                }
                                hashMap.put(obj, dJISycAlbumModel);
                            }
                        } else {
                            continue;
                        }
                    }
                }
            } else {
                return;
            }
        }
        file = new File(g);
        if (file.isDirectory()) {
            listFiles = file.listFiles();
            if (listFiles != null) {
                for (int i2 = 0; i2 < listFiles.length; i2++) {
                    a = g.a(listFiles[i2], false);
                    if (a != null && d.c(a.g)) {
                        split = a.f.split("_");
                        DJISycAlbumModel dJISycAlbumModel2;
                        if (split[0].equals(DJIPhotoPreveiwActivity.E)) {
                            obj = split[1];
                            if (hashMap.containsKey(obj)) {
                                dJISycAlbumModel2 = (DJISycAlbumModel) hashMap.get(obj);
                                dJISycAlbumModel2.isOrg = true;
                                dJISycAlbumModel2.orgPath = a.e;
                            } else {
                                dJISycAlbumModel2 = new DJISycAlbumModel(a, true);
                                try {
                                    dJISycAlbumModel2.sortTime = Long.parseLong(a(split[2]));
                                } catch (Exception e2) {
                                    dJISycAlbumModel2.sortTime = listFiles[i2].lastModified();
                                }
                                dJISycAlbumModel2.isOrg = true;
                                dJISycAlbumModel2.orgPath = a.e;
                                dJISycAlbumModel2.fileLevel = 4;
                                File file2 = new File(f + dji.pilot.usercenter.protocol.d.t + a.f.replaceAll(DJIPhotoPreveiwActivity.E, "screen"));
                                if (file2.exists()) {
                                    dJISycAlbumModel2 = new DJISycAlbumModel(g.a(file2, false), true);
                                    try {
                                        dJISycAlbumModel2.sortTime = Long.parseLong(a(split[2]));
                                    } catch (Exception e3) {
                                        dJISycAlbumModel2.sortTime = listFiles[i2].lastModified();
                                    }
                                    dJISycAlbumModel2.fileLevel = 1;
                                    dJISycAlbumModel2.isOrg = true;
                                    dJISycAlbumModel2.orgPath = a.e;
                                }
                                replaceAll = f + dji.pilot.usercenter.protocol.d.t + a.f.replaceAll(DJIPhotoPreveiwActivity.E, "thumb");
                                if (new File(replaceAll).exists()) {
                                    dJISycAlbumModel2.isThumb = true;
                                    dJISycAlbumModel2.mThumbmAbsPath = replaceAll;
                                }
                                synchronized (arrayList) {
                                    arrayList.add(dJISycAlbumModel2);
                                }
                                hashMap.put(obj, dJISycAlbumModel2);
                            }
                        } else {
                            dJISycAlbumModel2 = new DJISycAlbumModel(a, true);
                            dJISycAlbumModel2.sortTime = a.h;
                            dJISycAlbumModel2.isOrg = true;
                            dJISycAlbumModel2.orgPath = a.e;
                            dJISycAlbumModel2.fileLevel = 4;
                            synchronized (arrayList) {
                                arrayList.add(dJISycAlbumModel2);
                            }
                            hashMap.put(split[0], dJISycAlbumModel2);
                        }
                    }
                }
            } else {
                return;
            }
        }
        b(arrayList, hashMap, context);
    }

    public void a(ArrayList<DJIAlbumFileInfo> arrayList, ArrayList<DJISycAlbumModel> arrayList2, HashMap<String, DJISycAlbumModel> hashMap, ArrayList<DJISycAlbumModel> arrayList3, ArrayList<DJISycAlbumModel> arrayList4, ArrayList<DJISycAlbumModel> arrayList5, List<String> list) {
        if (arrayList != null) {
            DJISycAlbumModel dJISycAlbumModel;
            long currentTimeMillis = System.currentTimeMillis();
            int i = 0;
            while (i < arrayList.size()) {
                if (((DJIAlbumFileInfo) arrayList.get(i)).j == dji.logic.album.a.b.f.b || ((DJIAlbumFileInfo) arrayList.get(i)).j == dji.logic.album.a.b.f.a) {
                    String a = ((DJIAlbumFileInfo) arrayList.get(i)).a();
                    if (list.contains(a)) {
                        continue;
                    } else {
                        this.a.put(a, a);
                        DJISycAlbumModel dJISycAlbumModel2;
                        if (hashMap.containsKey(a)) {
                            dJISycAlbumModel = (DJISycAlbumModel) hashMap.get(a);
                            if (dJISycAlbumModel.fileLevel == 1) {
                                dJISycAlbumModel2 = new DJISycAlbumModel((DJIAlbumFileInfo) arrayList.get(i));
                                dJISycAlbumModel.fileLevel = 3;
                                dJISycAlbumModel.mRemoteInfo = dJISycAlbumModel2.mRemoteInfo;
                            }
                        } else {
                            long j = currentTimeMillis - ((DJIAlbumFileInfo) arrayList.get(i)).b;
                            if (j < 43200000 && j > 0) {
                                dJISycAlbumModel2 = new DJISycAlbumModel((DJIAlbumFileInfo) arrayList.get(i));
                                dJISycAlbumModel2.sortTime = ((DJIAlbumFileInfo) arrayList.get(i)).b;
                                dJISycAlbumModel2.fileLevel = 2;
                                dJISycAlbumModel2.isfileLeve2To = true;
                                hashMap.put(a, dJISycAlbumModel2);
                                synchronized (arrayList2) {
                                    arrayList2.add(dJISycAlbumModel2);
                                }
                                arrayList3.add(dJISycAlbumModel2);
                            }
                        }
                    }
                }
                i++;
            }
            int i2 = 0;
            while (i2 < arrayList2.size()) {
                dJISycAlbumModel = (DJISycAlbumModel) arrayList2.get(i2);
                if (dJISycAlbumModel.fileLevel == 2) {
                    if (this.a.containsKey(dJISycAlbumModel.mRemoteInfo.a())) {
                        continue;
                    } else {
                        arrayList4.add(dJISycAlbumModel);
                        hashMap.remove(dJISycAlbumModel.mRemoteInfo.a());
                        synchronized (arrayList2) {
                            arrayList2.remove(i2);
                        }
                        i2--;
                    }
                } else if (dJISycAlbumModel.fileLevel == 3 && !this.a.containsKey(dJISycAlbumModel.mRemoteInfo.a())) {
                    dJISycAlbumModel.fileLevel = 1;
                }
                i2++;
            }
            this.a.clear();
        }
    }

    public void a(ArrayList<DJISycAlbumModel> arrayList, HashMap<String, DJISycAlbumModel> hashMap, Context context) {
        String f = b.f(context);
        File file = new File(b.g(context));
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                g a = g.a(listFiles[i], false);
                if (a != null && d.c(a.g)) {
                    String[] split = a.f.split("_");
                    if (split[0].equals(DJIPhotoPreveiwActivity.E)) {
                        Object obj = split[1];
                        if (hashMap.containsKey(obj)) {
                            DJISycAlbumModel dJISycAlbumModel = (DJISycAlbumModel) hashMap.get(obj);
                            if (!dJISycAlbumModel.isOrg) {
                                dJISycAlbumModel.isOrg = true;
                                dJISycAlbumModel.isDownLoadEnd = true;
                                dJISycAlbumModel.orgPath = a.e;
                            }
                        } else {
                            DJISycAlbumModel dJISycAlbumModel2 = new DJISycAlbumModel(a, true);
                            try {
                                dJISycAlbumModel2.sortTime = Long.parseLong(a(split[2]));
                            } catch (Exception e) {
                                dJISycAlbumModel2.sortTime = listFiles[i].lastModified();
                            }
                            dJISycAlbumModel2.isOrg = true;
                            dJISycAlbumModel2.orgPath = a.e;
                            dJISycAlbumModel2.fileLevel = 4;
                            String str = f + dji.pilot.usercenter.protocol.d.t + a.f.replaceAll(DJIPhotoPreveiwActivity.E, "screen");
                            if (new File(str).exists()) {
                                dJISycAlbumModel2.isScreen = true;
                                dJISycAlbumModel2.mScreenAbsPath = str;
                            }
                            str = f + dji.pilot.usercenter.protocol.d.t + a.f.replaceAll(DJIPhotoPreveiwActivity.E, "thumb");
                            if (new File(str).exists()) {
                                dJISycAlbumModel2.isThumb = true;
                                dJISycAlbumModel2.mThumbmAbsPath = str;
                            }
                            synchronized (arrayList) {
                                arrayList.add(dJISycAlbumModel2);
                            }
                            hashMap.put(obj, dJISycAlbumModel2);
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
    }

    public void a(File file, ArrayList<DJISycAlbumModel> arrayList, HashMap<String, DJISycAlbumModel> hashMap) {
        if (c.a(file)) {
            DJISycAlbumModel dJISycAlbumModel = new DJISycAlbumModel(g.a(file, false), true, true);
            synchronized (arrayList) {
                arrayList.add(dJISycAlbumModel);
            }
            hashMap.put(file.getName(), dJISycAlbumModel);
        }
    }

    public void a(List<DJISycAlbumModel> list, List<dji.pilot.playback.litchi.a> list2) {
        Collections.sort(list, new Comparator<DJISycAlbumModel>(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((DJISycAlbumModel) obj, (DJISycAlbumModel) obj2);
            }

            public int a(DJISycAlbumModel dJISycAlbumModel, DJISycAlbumModel dJISycAlbumModel2) {
                return Long.valueOf(dJISycAlbumModel2.sortTime).compareTo(Long.valueOf(dJISycAlbumModel.sortTime));
            }
        });
        b(list, list2);
    }

    public void b(List<DJISycAlbumModel> list, List<dji.pilot.playback.litchi.a> list2) {
        list2.clear();
        String str = null;
        if (list.size() > 0) {
            dji.pilot.playback.litchi.a aVar;
            List arrayList = new ArrayList();
            int i = 0;
            while (i < list.size()) {
                String str2;
                if (((DJISycAlbumModel) list.get(i)).sortTime == 0) {
                    str2 = str;
                } else if (i <= 0) {
                    str2 = DateFormat.format("yyyy-MM-dd", ((DJISycAlbumModel) list.get(i)).sortTime).toString();
                    arrayList.add(list.get(i));
                } else if (DateFormat.format("yyyy-MM-dd", ((DJISycAlbumModel) list.get(i)).sortTime).toString().equals(DateFormat.format("yyyy-MM-dd", ((DJISycAlbumModel) list.get(i - 1)).sortTime))) {
                    str2 = DateFormat.format("yyyy-MM-dd", ((DJISycAlbumModel) list.get(i)).sortTime).toString();
                    arrayList.add(list.get(i));
                } else {
                    aVar = new dji.pilot.playback.litchi.a();
                    aVar.a(str);
                    aVar.a(0);
                    aVar.b(0);
                    aVar.c(arrayList);
                    list2.add(aVar);
                    arrayList.clear();
                    arrayList.add(list.get(i));
                    str2 = DateFormat.format("yyyy-MM-dd", ((DJISycAlbumModel) list.get(i)).sortTime).toString();
                }
                i++;
                str = str2;
            }
            aVar = new dji.pilot.playback.litchi.a();
            aVar.a(str);
            aVar.a(0);
            aVar.b(0);
            aVar.c(arrayList);
            list2.add(aVar);
        }
    }

    private String a(String str) {
        if (str.endsWith(dji.pilot2.main.a.a.n)) {
            return str.replaceAll("[.][Jj][Pp][Gg]", "");
        }
        return str.replaceAll("[.][Dd][Nn][Gg]", "");
    }
}
