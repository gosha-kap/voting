package ru.gosha_kap.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="meals")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="description")
    @NotBlank
    private String description;

    @Column(name="price")
    @NotNull
    private int price;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public Meal() {
    }

    public Meal(String description, int price) {
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "Meal{" +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
