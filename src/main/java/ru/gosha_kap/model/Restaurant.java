package ru.gosha_kap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZoneId;
import java.util.Objects;
import java.util.TimeZone;

@Entity
@Table(name = "restaurants")
public class Restaurant implements hasID {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "timezone")
    @NotNull
    private String timezone;



    public Restaurant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Restaurant(String name, String timezone) {
        this.name = name;
        this.timezone = timezone;
    }

    public Restaurant() {
    }


    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
