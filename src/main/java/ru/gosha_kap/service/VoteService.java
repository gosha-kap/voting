package ru.gosha_kap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gosha_kap.model.Vote;
import ru.gosha_kap.model.VoteEntity;
import ru.gosha_kap.repository.RestaurantRepository;
import ru.gosha_kap.repository.VoteJPARepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service("voteService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(readOnly = true)
public class VoteService {


    @Autowired
    private VoteJPARepository voteJPARepository;

    @Autowired
    private RestaurantRepository restaurantRepository;


    @Value( "${vote.border.hour}" )
    private Integer voteLimitHour;

    @Value( "${vote.border.minutes}" )
    private Integer voteLimitMinutes;

    public boolean checkTime(int restaurantId){

        String restaurantTZ = restaurantRepository.getTZ(restaurantId);
        ZonedDateTime restaurantTime = ZonedDateTime.now(ZoneId.of(restaurantTZ));
        ZonedDateTime voteLimitTime = ZonedDateTime.of(LocalDate.now(),LocalTime.of(voteLimitHour,voteLimitMinutes),ZoneId.of(restaurantTZ));
        return restaurantTime.isAfter(voteLimitTime);
    }

    @Transactional
    public void vote(int authUserId, int id) {
         voteJPARepository.save(new VoteEntity(new Vote(authUserId),id));
    }

    public List<VoteEntity> getHistoryForUser(int authUserId) {
         return voteJPARepository.showHistory(authUserId);
    }
}
