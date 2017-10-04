package dji.pilot2.nativeexplore.explorepost;

import android.content.Context;
import android.os.AsyncTask;
import android.text.format.DateFormat;
import dji.log.DJILogHelper;
import dji.pilot2.library.MixAlbumSyncManager;
import dji.pilot2.library.model.DJIScanerMediaManager;
import dji.pilot2.library.model.DJISycAlbumModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class c implements b {
    private DJIScanerMediaManager a;
    private Context b;
    private a c;

    class a extends AsyncTask<Void, ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a>, ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a>> {
        final /* synthetic */ c a;

        a(c cVar) {
            this.a = cVar;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((ArrayList) obj);
        }

        protected void a(ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a> arrayList) {
            if (arrayList != null && this.a.c != null) {
                DJILogHelper.getInstance().LOGI("bob", "ExplorePostPresenter ScannerEditPicThread  onPostExecute");
                this.a.c.a(arrayList);
            }
        }

        protected ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a> a(Void... voidArr) {
            DJILogHelper.getInstance().LOGI("bob", "ExplorePostPresenter ScannerEditPicThread  doInBackground");
            List showList = MixAlbumSyncManager.getInstance(this.a.b).getShowList();
            ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a> arrayList = new ArrayList();
            Iterator it = showList.iterator();
            while (it.hasNext()) {
                if (((DJISycAlbumModel) it.next()).fileLevel == 2) {
                    it.remove();
                }
            }
            List arrayList2 = new ArrayList();
            this.a.a(showList, arrayList2);
            DJILogHelper.getInstance().LOGI("bob", "ExplorePostPresenter ScannerEditPicThread groups.size=" + arrayList2.size());
            for (int i = 0; i < arrayList2.size(); i++) {
                dji.pilot.playback.litchi.a aVar = (dji.pilot.playback.litchi.a) arrayList2.get(i);
                DJILogHelper.getInstance().LOGI("bob", "ExplorePostPresenter ScannerEditPicThread group.ChildAlbum_Mix.size() =" + aVar.c.size() + " group.ChildAlbum.size()= " + aVar.a.size() + " group.ChildAlbum_Remote.size() = " + aVar.b.size());
                if (aVar != null && aVar.c.size() > 0) {
                    dji.pilot2.library.model.DJIScanerMediaManager.a aVar2 = new dji.pilot2.library.model.DJIScanerMediaManager.a();
                    aVar2.b = aVar.a();
                    aVar2.a = aVar.c.size();
                    aVar2.d = new ArrayList();
                    for (int i2 = 0; i2 < aVar.c.size(); i2++) {
                        DJISycAlbumModel dJISycAlbumModel = (DJISycAlbumModel) aVar.c.get(i2);
                        if (dJISycAlbumModel != null) {
                            dji.pilot2.library.model.DJIScanerMediaManager.c cVar = new dji.pilot2.library.model.DJIScanerMediaManager.c();
                            cVar.e = aVar.a();
                            cVar.d = 0;
                            cVar.b = dJISycAlbumModel.mLocalInfo.e;
                            DJILogHelper.getInstance().LOGI("bob", "thumbnailInfo.mThumbnail_absPath = " + cVar.b);
                            aVar2.d.add(cVar);
                        }
                    }
                    arrayList.add(aVar2);
                }
            }
            return arrayList;
        }
    }

    class b extends AsyncTask<Void, ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a>, ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a>> {
        final /* synthetic */ c a;

        b(c cVar) {
            this.a = cVar;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((ArrayList) obj);
        }

        protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
            a((ArrayList[]) objArr);
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a> a(Void... voidArr) {
            if (this.a.a == null) {
                return null;
            }
            DJILogHelper.getInstance().LOGI("bob", "ExplorePostPresenter ScannerOtherPicTheard  doInBackground");
            this.a.a.scaneAllMedias(false);
            return this.a.a.getMediaTimeStamps();
        }

        protected void a(ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a> arrayList) {
            if (arrayList != null && this.a.c != null) {
                DJILogHelper.getInstance().LOGI("bob", "ExplorePostPresenter ScannerOtherPicTheard  onPostExecute");
                this.a.c.a(arrayList);
            }
        }

        protected void a(ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a>... arrayListArr) {
            super.onProgressUpdate(arrayListArr);
        }
    }

    public c(Context context, a aVar) {
        this.b = context;
        this.c = aVar;
    }

    public void a(boolean z) {
        DJILogHelper.getInstance().LOGI("bob", "ExplorePostPresenter edit =" + z);
        if (z) {
            new a(this).execute(new Void[0]);
            return;
        }
        this.a = DJIScanerMediaManager.getInstance(this.b);
        this.a.setFlagVideo(false);
        this.a.resetLists();
        new b(this).execute(new Void[0]);
    }

    protected void a(List<DJISycAlbumModel> list, List<dji.pilot.playback.litchi.a> list2) {
        Collections.sort(list, new Comparator<DJISycAlbumModel>(this) {
            final /* synthetic */ c a;

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

    protected void b(List<DJISycAlbumModel> list, List<dji.pilot.playback.litchi.a> list2) {
        list2.clear();
        if (list.size() > 0) {
            dji.pilot.playback.litchi.a aVar;
            List arrayList = new ArrayList();
            int i = 0;
            String str = null;
            while (i < list.size()) {
                String charSequence;
                if (i <= 0) {
                    charSequence = DateFormat.format("yyyy-MM-dd", ((DJISycAlbumModel) list.get(i)).sortTime).toString();
                    arrayList.add(list.get(i));
                } else if (DateFormat.format("yyyy-MM-dd", ((DJISycAlbumModel) list.get(i)).sortTime).toString().equals(DateFormat.format("yyyy-MM-dd", ((DJISycAlbumModel) list.get(i - 1)).sortTime))) {
                    charSequence = DateFormat.format("yyyy-MM-dd", ((DJISycAlbumModel) list.get(i)).sortTime).toString();
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
                    charSequence = DateFormat.format("yyyy-MM-dd", ((DJISycAlbumModel) list.get(i)).sortTime).toString();
                }
                i++;
                str = charSequence;
            }
            aVar = new dji.pilot.playback.litchi.a();
            aVar.a(str);
            aVar.a(0);
            aVar.b(0);
            aVar.c(arrayList);
            list2.add(aVar);
        }
    }
}
