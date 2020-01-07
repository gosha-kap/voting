package ru.gosha_kap.to;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class RestrntVoteHistory {

    private LocalDate date;
    private List<RestrntVoteInfo> voteResult;

    public RestrntVoteHistory(LocalDate date, List<RestrntVoteInfo> tops) {
        this.date = date;
        this.voteResult = tops;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<RestrntVoteInfo> getVoteResult() {
        return voteResult;
    }


    public RestrntVoteHistory() {
    }


    @Override
    public String toString() {
        return "RestrntVoteHistory{" +
                "date=" + date +
                ", voteResult=" + voteResult +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestrntVoteHistory that = (RestrntVoteHistory) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(voteResult, that.voteResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, voteResult);
    }


}
