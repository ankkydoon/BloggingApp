package com.chillerz.bloggerspark.Repository;

import com.chillerz.bloggerspark.entity.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EndUserRepo extends JpaRepository<EndUser,Integer> {

    public abstract Optional<EndUser> findEndUserByUserName(String userName);

    public abstract EndUser findEndUserByUserId(Integer userId);

    public abstract List<EndUser> findAllByFollowing(EndUser following);


}
