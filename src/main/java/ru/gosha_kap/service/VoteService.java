package ru.gosha_kap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gosha_kap.model.VoteEntity;
import ru.gosha_kap.repository.VoteRepository;

import java.time.LocalTime;
import java.util.List;

@Service("voteService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(readOnly = true)
public class VoteService {

    private final LocalTime MAX_VOTE_TIME = LocalTime.of(23,0,0);

    @Autowired
    private VoteRepository voteRepository;

    public boolean checkTime(){
        if(LocalTime.now().isAfter(MAX_VOTE_TIME)) return false;
        return true;
    }

    @Transactional
    public void vote(int authUserId, int id) {
        voteRepository.makeVote(authUserId,id);
    }

    public List<VoteEntity> getHistoryForUser(int authUserId) {
       return voteRepository.getUserHistory(authUserId);
    }
}
