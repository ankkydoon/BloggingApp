package com.chillerz.bloggerspark.Repository;

import com.chillerz.bloggerspark.entity.Comment;
import com.chillerz.bloggerspark.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepo extends JpaRepository<Comment, Integer> {

    

}
