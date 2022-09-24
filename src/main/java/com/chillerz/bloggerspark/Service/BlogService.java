package com.chillerz.bloggerspark.Service;

import com.chillerz.bloggerspark.Repository.BlogRepo;
import com.chillerz.bloggerspark.Repository.CommentsRepo;
import com.chillerz.bloggerspark.Repository.EndUserRepo;
import com.chillerz.bloggerspark.Repository.LikesRepo;
import com.chillerz.bloggerspark.dto.BlogByUser;
import com.chillerz.bloggerspark.entity.Blog;
import com.chillerz.bloggerspark.entity.Comment;
import com.chillerz.bloggerspark.entity.EndUser;
import com.chillerz.bloggerspark.entity.Likes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepo blogRepo;
    @Autowired
    EndUserRepo endUserRepo;
    @Autowired
    LikesRepo likesRepo;
    @Autowired
    CommentsRepo commentsRepo;

    public Blog getBlog(int blogId) {
        return blogRepo.getReferenceById(blogId);
    }

    public List<Blog> getAllBlogs() {
        return blogRepo.findAll();
    }

    public List<Blog> getBlogsByEndUser(String userName) {
        return blogRepo
                .findBlogsByEndUser(endUserRepo.findEndUserByUserName(userName).get());
    }

    public List<Blog> getBlogsOfFollowings(String userName) {
        return blogRepo.findBlogsByEndUser_Following(endUserRepo.findEndUserByUserName(userName).get());
    }

    public Blog saveBlog(BlogByUser blog) {
        return blogRepo.save(Blog.builder()
                .blogDescription(blog.getBlogDescription())
                .blogTitle(blog.getBlogTitle())
                .endUser(endUserRepo.findEndUserByUserName(blog.getEndUser()).get())
                .build());
    }

    public void deleteBlog(int blogId) {
        blogRepo.delete(getBlog(blogId));
    }

    public void addUserLikeOnBlog(String blogTitle, String userName) {
        Blog blog = blogRepo.findBlogsByBlogTitle(blogTitle);
        EndUser user = endUserRepo.findEndUserByUserName(userName).get();

        List<Likes> likes = new ArrayList<>();
        likes.add(Likes.builder().blogs(blog).user(user).build());
        blog.setLikes(likes);
        likes.forEach(likes1 -> likes1.setBlogs(blog));

        likesRepo.save(likes.get(0));
    }

    public void addUserCommentOnBlog(String blogTitle, String userName, String comment) {
        Blog blog = blogRepo.findBlogsByBlogTitle(blogTitle);
        EndUser user = endUserRepo.findEndUserByUserName(userName).get();

        List<Comment> comments = new ArrayList<>();
        comments.add(Comment.builder().blogs(blog).user(user).description(comment).build());
        blog.setComments(comments);
        comments.forEach(likes1 -> likes1.setBlogs(blog));

        commentsRepo.save(comments.get(0));
    }

}
