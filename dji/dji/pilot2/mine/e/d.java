package dji.pilot2.mine.e;

import dji.pilot2.nativeexplore.model.ExploreListModel.ExploreItemModel;
import dji.pilot2.utils.k;

public class d extends a {
    public String r;
    private int s;

    public d(ExploreItemModel exploreItemModel) {
        super(exploreItemModel);
        this.s = exploreItemModel.duration;
        this.r = exploreItemModel.video_status;
        a(this.j);
        c(exploreItemModel.title);
        d(exploreItemModel.title);
        b(k.d("video", exploreItemModel.id));
        a(exploreItemModel.created_at);
    }

    public int i() {
        return this.s;
    }

    public void a(int i) {
        this.s = i;
    }

    public String a() {
        return "video";
    }
}
