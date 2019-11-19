package ru.gosha_kap.model;

import javax.persistence.*;

@Entity
@Table(name="restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "restaurant")
    private Menu menu;

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant() {
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getId() {
        return id;
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
                ", menu=" + menu +
                '}';
    }
}
