package dji.pilot.countrycode.model;

public class CountryCodeModel {
    public String msg;
    public Result result;
    public int status;

    public class Result {
        public String country_code;
        public String country_name;
    }
}
