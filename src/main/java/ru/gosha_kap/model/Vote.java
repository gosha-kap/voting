package ru.gosha_kap.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Vote implements Serializable {


    private Integer user_id;

    private  Integer restaurant_id;

    private LocalDate votedDate;

    public Vote() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Integer restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public LocalDate getVotedDate() {
        return votedDate;
    }

    public void setVotedDate(LocalDate votedDate) {
        this.votedDate = votedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return Objects.equals(user_id, vote.user_id) &&
                Objects.equals(restaurant_id, vote.restaurant_id) &&
                Objects.equals(votedDate, vote.votedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, restaurant_id, votedDate);
    }

    @Override
    public String toString() {
        return "Vote{" +
                "user_id=" + user_id +
                ", restaurant_id=" + restaurant_id +
                ", date=" + votedDate +
                '}';
    }
}
