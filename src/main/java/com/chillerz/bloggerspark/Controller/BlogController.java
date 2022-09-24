package com.chillerz.bloggerspark.Controller;

import com.chillerz.bloggerspark.Service.BlogService;
import com.chillerz.bloggerspark.Service.EndUserService;
import com.chillerz.bloggerspark.dto.BlogByUser;
import com.chillerz.bloggerspark.entity.Blog;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/allblogs")
    ResponseEntity<List<Blog>> getAllBlogs(){
       return  ResponseEntity.ok(blogService.getAllBlogs());
    }

    @GetMapping("/{id}")
    ResponseEntity<Blog> getAllBlogs(@PathVariable int id){
        return  ResponseEntity.ok(blogService.getBlog(id));
    }

    @GetMapping("/blogsbyuser/{endUser}")
    ResponseEntity<List<Blog>> getBlogsByEndUser(@PathVariable String endUser){
        return  ResponseEntity.ok(blogService.getBlogsByEndUser(endUser));
    }

    @GetMapping("/blogsbyuserfollowing/{endUser}")
    ResponseEntity<List<Blog>> getBlogsByEndUserFollowing(@PathVariable String endUser){
        return  ResponseEntity.ok(blogService.getBlogsOfFollowings(endUser));
    }

    @PostMapping("/addblog")
    ResponseEntity<Blog> addBlog(@RequestBody BlogByUser blog){
        return new ResponseEntity(blogService.saveBlog(blog), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteblog/{id}")
    ResponseEntity deleteBlog(@PathVariable int id){
        blogService.deleteBlog(id);
        return new ResponseEntity( HttpStatus.ACCEPTED);
    }

    @PostMapping("/addliketoblog/{blogTitle}/{userName}")
    ResponseEntity postLikeToBlog(@PathVariable String blogTitle, @PathVariable String userName){
        blogService.addUserLikeOnBlog(blogTitle,userName);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("/addcommenttoblog/{blogTitle}/{userName}/{comment}")
    ResponseEntity postCommentToBlog(@PathVariable String blogTitle, @PathVariable String userName,@PathVariable String comment){
        blogService.addUserCommentOnBlog(blogTitle,userName,comment);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
