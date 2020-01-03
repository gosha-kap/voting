package ru.gosha_kap.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gosha_kap.model.Vote;
import ru.gosha_kap.model.VoteEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class VoteRepository  {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public void makeVote(int user_id, int restaurant_id){
       entityManager.createNativeQuery("INSERT  into VOTING_HISTORY (user_id, restaurant_id) VALUES (?,?)").
       setParameter(1,user_id).
       setParameter(2,restaurant_id).
       executeUpdate();
    }

/*    public List<Vote> getHistory(int user_id){
        return entityManager.createNativeQuery("select * from VOTING_HISTORY where USER_ID=? order by date desc ",Vote.class).
                setParameter(1,user_id).getResultList();
    }*/

    //https://stackoverflow.com/questions/13012584/jpa-how-to-convert-a-native-query-result-set-to-pojo-class-collection
    public List<VoteEntity> getTodayResult(){
        return entityManager.createNativeQuery("select * from VOTING_HISTORY where VOTEDDATE = curdate()", VoteEntity.class).getResultList();
    }


    public List<VoteEntity> getUserHistory(int authUserId) {
        return entityManager.createNativeQuery("select * from VOTING_HISTORY where USER_ID = ? order by VOTEDDATE desc ")
                .setParameter(1,authUserId).getResultList();
    }
}
