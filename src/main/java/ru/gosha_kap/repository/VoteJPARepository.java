package ru.gosha_kap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gosha_kap.model.Vote;
import ru.gosha_kap.model.VoteEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VoteJPARepository extends JpaRepository<VoteEntity, Vote> {


    @Query("select m from VoteEntity m where m.vote.user_id=?1 order by m.vote.votedDate desc ")
    List<VoteEntity> showHistory(int id);

    @Query("select m from VoteEntity m where m.vote.votedDate=?1")
    List<VoteEntity> getTodayResult(LocalDate date);


}
