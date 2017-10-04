package dji.pilot2.mine.e;

import dji.pilot2.nativeexplore.model.ExploreListModel.ExploreItemModel;
import dji.pilot2.utils.k;

public class c extends a {
    public c(ExploreItemModel exploreItemModel) {
        super(exploreItemModel);
        a(this.j);
        c(exploreItemModel.title);
        d(exploreItemModel.title);
        b(k.d("photo", exploreItemModel.id));
        a(exploreItemModel.created_at);
    }

    public String a() {
        return "photo";
    }
}
