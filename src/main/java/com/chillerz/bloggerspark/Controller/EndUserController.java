package com.chillerz.bloggerspark.Controller;

import com.chillerz.bloggerspark.Service.BlogService;
import com.chillerz.bloggerspark.Service.EndUserService;
import com.chillerz.bloggerspark.dto.FollowUserRequest;
import com.chillerz.bloggerspark.dto.NewEndUser;
import com.chillerz.bloggerspark.entity.EndUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enduser")
public class EndUserController {

    private EndUserService endUserService;

    public EndUserController(EndUserService endUserService) {
        this.endUserService = endUserService;
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<EndUser> getUserByUserName(@PathVariable String username) {
        return ResponseEntity
                .ok(endUserService.getEndUserByUserName(username));
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<EndUser>> getAllUsers() {
        return ResponseEntity.ok(endUserService.getAllEndUsers());
    }

    @PostMapping("/adduser")
    public ResponseEntity<EndUser> addNewUser(@RequestBody NewEndUser enduser) {
        EndUser user = endUserService.addUser(enduser);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @PostMapping("/followuser")
    public ResponseEntity followUser(@RequestBody FollowUserRequest follow) {
        endUserService.doUserFollowsUser(follow.getFollowingId(), follow.getFollowerId());
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
