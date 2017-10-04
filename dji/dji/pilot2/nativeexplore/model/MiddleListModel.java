package dji.pilot2.nativeexplore.model;

import java.util.List;

public class MiddleListModel {
    public List<MiddleItemModel> items;
    public String location;

    public static class MiddleItemModel {
        public double aperture;
        public String description;
        public String image;
        public String image_large;
        public int iso;
        public String model;
        public double shooting_latitude;
        public double shooting_longitude;
        public String shutter;
        public double take_off_latitude;
        public double take_off_longitude;
        public String title;
    }
}
