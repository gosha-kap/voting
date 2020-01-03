package ru.gosha_kap.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.time.LocalDate;

@Entity
@IdClass(Vote.class)
public class VoteEntity {

    @Id
    private int user_id;

    @Id
    private LocalDate votedDate = LocalDate.now();


    private int restaurant_id;

    public VoteEntity() {
    }

    public VoteEntity(int user_id, int restaurant_id) {
        this.user_id = user_id;
        this.restaurant_id = restaurant_id;

    }

    public int getUser_id() {
        return user_id;
    }

    public LocalDate getDate() {
        return votedDate;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }
}
