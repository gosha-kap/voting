package ru.gosha_kap.to;

import java.time.LocalDate;
import java.util.List;

public class RestrntVoteHistory {

    private String date;
    private List<RestrntVoteInfo> voteResult;

    public RestrntVoteHistory(LocalDate date, List<RestrntVoteInfo> tops) {
        this.date = date.toString();
        this.voteResult = tops;
    }


    public String getDate() {
        return date;
    }

    public List<RestrntVoteInfo> getVoteResult() {
        return voteResult;
    }

    @Override
    public String toString() {
        return "RestrntVoteHistory{" +
                "date=" + date +
                ", voteResult=" + voteResult +
                '}';
    }
}
