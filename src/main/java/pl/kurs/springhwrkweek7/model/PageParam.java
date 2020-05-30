package pl.kurs.springhwrkweek7.model;

public class PageParam {
    int yearFrom = 2000;
    int yearTo = 2020;

    public PageParam(int yearFrom, int yearTo) {
        this.yearFrom = yearFrom;
        this.yearTo = yearTo;
    }

    public PageParam() {

    }

    public int getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(int yearFrom) {
        this.yearFrom = yearFrom;
    }

    public int getYearTo() {
        return yearTo;
    }

    public void setYearTo(int yearTo) {
        this.yearTo = yearTo;
    }
}
