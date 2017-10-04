package dji.pilot2.template;

import java.util.List;

public class SingleTemplateJson {
    public List<Filter> Filters;
    public Animate TextAnimateIn;
    public Animate TextAnimateOut;
    public List<TextInfo> TextArray;
    public String desc;
    public String id;
    public String index;
    public String music_url;
    public String name;
    public String order;
    public String photo_url;
    public String ranges;
    public String tag;
    public long update_ts;

    public class Animate {
        public String animate;
        public double begin;
        public double duration;
        public double end;
        public double real;
    }

    public class Filter {
        public String animate;
        public double begin;
        public double duration;
        public double end;
        public String filteranme;
        public double start;
    }

    public class TextInfo {
        public double centerX;
        public double centerY;
        public String color;
        public double duration;
        public double size;
        public double start;
        public String text;
    }
}
