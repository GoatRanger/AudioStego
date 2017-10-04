package dji.dbox.upgrade.p4.model;

public class DJIUpUrlModel {
    public Apis apis;
    public String signature;
    public String state;

    public static class Apis {
        public String allfile;
        public String config;
        public String down;
        public String firm;
        public String test;
    }
}
