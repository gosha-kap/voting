package ru.gosha_kap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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
/*
    @OneToOne(mappedBy = "restaurant",fetch = FetchType.LAZY)
    private Menu menu;*/

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Restaurant() {
    }

 /*   public Menu getMenu() {
        return menu;
    }*/

/*    public void setMenu(Menu menu) {
        this.menu = menu;
    }*/

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
    public String toString() {
        return "Restaurant{" +
                " name='" + name + '\'' +
          //      ", menu=" + menu +
                '}';
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
}
