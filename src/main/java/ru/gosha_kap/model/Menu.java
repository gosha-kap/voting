package ru.gosha_kap.model;



import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "menus")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "votes")
    private int votes;

    @Column(name="date")
    private LocalDate date = LocalDate.now();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private List<Meal> meals;

    public Menu(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Menu(int id, LocalDate date, int votes, Restaurant restaurant, List<Meal> meals) {
        this.id = id;
        this.votes = votes;
        this.date = date;
        this.restaurant = restaurant;
        this.meals = meals;
    }

    public Menu() {
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    @Override
    public String toString() {
        return "Menu{" +
                "rating=" + votes +
                ", date=" + date +
                ", restaurant=" + restaurant.getName() +
                ", meals=" + meals +
                '}';
    }
}
