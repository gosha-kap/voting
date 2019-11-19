package ru.gosha_kap.to;

public class RestrntMonthRating {

    private  Integer id;

    private  String name;

    private  int monthRate;

    public RestrntMonthRating(Integer id, String name, int monthRate) {
        this.id = id;
        this.name = name;
        this.monthRate = monthRate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMonthRate() {
        return monthRate;
    }
}
