package com.chillerz.bloggerspark.Repository;

import com.chillerz.bloggerspark.entity.Blog;
import com.chillerz.bloggerspark.entity.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  BlogRepo extends JpaRepository<Blog,Integer> {

    public abstract List<Blog> findBlogsByEndUser(EndUser endUser);

    public abstract List<Blog> findBlogsByEndUser_Following(EndUser endUser_Following);

    public abstract Blog findBlogsByBlogTitle(String blogTitle);
}
