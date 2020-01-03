package ru.gosha_kap.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="meals")
public class Meal implements hasID{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="description")
    @NotBlank
    private String description;

    @Column(name="price")
    @NotNull
    private int price;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Menu menu;

    public Meal() {
    }

    public Meal(String description, int price) {
        this.description = description;
        this.price = price;
    }

    public Meal(Integer id, String description, int price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public Meal(@NotBlank String description, @NotNull int price, Menu menu) {
        this.description = description;
        this.price = price;
        this.menu = menu;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }



    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", menu=" + menu +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return id == meal.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
