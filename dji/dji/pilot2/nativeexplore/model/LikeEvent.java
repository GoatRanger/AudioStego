package dji.pilot2.nativeexplore.model;

public class LikeEvent {
    public a action;
    public String id;
    public int likeCount;

    public enum a {
        LIKE,
        DISLIKE
    }

    public LikeEvent(a aVar, String str, int i) {
        this.action = aVar;
        this.id = str;
        this.likeCount = i;
    }
}
