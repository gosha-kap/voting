package ru.gosha_kap.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "voting_history")
public class VoteEntity {


    @EmbeddedId
    private Vote vote;


    @NotNull
    private int restaurant_id;

    public VoteEntity() {
    }

    public VoteEntity(Vote vote, @NotNull int restaurant_id) {
        this.vote = vote;
        this.restaurant_id = restaurant_id;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
}
