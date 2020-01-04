package ru.gosha_kap.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


@Embeddable
public class Vote implements Serializable {

    @NotNull
    private Integer user_id;

    @NotNull
    private LocalDate votedDate;

    public Vote() {
    }

    public Vote(Integer user_id) {
        this.user_id = user_id;
        this.votedDate = LocalDate.now();
      }

    public Vote(@NotNull Integer user_id, @NotNull LocalDate votedDate) {
        this.user_id = user_id;
        this.votedDate = votedDate;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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
        return user_id.equals(vote.user_id) &&
                votedDate.equals(vote.votedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, votedDate);
    }
}
