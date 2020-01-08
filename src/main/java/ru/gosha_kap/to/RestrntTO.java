package ru.gosha_kap.to;

public class RestrntTO {

    private Integer id;

    private String name;

    private String timezone;

    private Boolean isMenuUpdated;

    public RestrntTO() {
    }

    public RestrntTO(Integer id, String name, String timezone, Boolean isMenuUpdated) {
        this.id = id;
        this.name = name;
        this.timezone = timezone;
        this.isMenuUpdated = isMenuUpdated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Boolean getMenuUpdated() {
        return isMenuUpdated;
    }

    public void setMenuUpdated(Boolean menuUpdated) {
        isMenuUpdated = menuUpdated;
    }
}
