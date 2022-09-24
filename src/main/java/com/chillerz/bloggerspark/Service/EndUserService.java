package com.chillerz.bloggerspark.Service;

import com.chillerz.bloggerspark.Exception.UserNotFoundException;
import com.chillerz.bloggerspark.Repository.EndUserRepo;
import com.chillerz.bloggerspark.dto.NewEndUser;
import com.chillerz.bloggerspark.entity.EndUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndUserService {
    public EndUserRepo endUserRepo;

    public EndUserService(EndUserRepo endUserRepo) {
        this.endUserRepo = endUserRepo;
    }

    public EndUser addUser(NewEndUser endUser) {
        return endUserRepo
                .save(EndUser.builder().
                        userName(endUser.getUsername()).
                        passWord(endUser.getPassword()).
                        build());
    }

    public EndUser getEndUserByUserName(String userName) {
        return endUserRepo.findEndUserByUserName(userName).orElseThrow(
                () -> new UserNotFoundException(userName)
        );
    }

    public List<EndUser> getFollowersByEndUserUserName(String userName) {
        return endUserRepo.findAllByFollowing(getEndUserByUserName(userName));
    }

    public List<EndUser> getAllEndUsers() {
        return endUserRepo.findAll();
    }

    public void doUserFollowsUser(String followingId, String followerId) {
        EndUser following = endUserRepo.findEndUserByUserId(Integer.parseInt(followingId));
        EndUser follower = endUserRepo.findEndUserByUserId(Integer.parseInt(followerId));
        following.getFollowers().add(follower);
        follower.getFollowing().add(following);
        endUserRepo.save(following);
    }

}
