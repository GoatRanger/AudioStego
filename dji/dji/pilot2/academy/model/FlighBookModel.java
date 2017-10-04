package dji.pilot2.academy.model;

import java.util.List;

public class FlighBookModel {
    public List<FlightBookData> data;
    public int result;

    public static class FlightBookData {
        public String content;
        public String created_at;
        public int id;
        public String language;
        public int resolve;
        public boolean status;
        public String title;
        public int type;
        public int unresolved;
        public String updated_at;
    }
}
