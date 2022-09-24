package com.chillerz.bloggerspark.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogId;
    private String blogTitle;
    private String blogDescription;

    @ManyToOne(cascade = CascadeType.ALL)
    private EndUser endUser;

    @OneToMany(mappedBy = "blogs")
    private List<Likes> likes;

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
        likes.forEach(like -> like.setBlogs(this));
    }

    @OneToMany(mappedBy = "blogs")
    private List<Comment> comments;

    public void setComments(List<Comment> comments) {
        this.comments = comments;
        comments.forEach(comment -> comment.setBlogs(this));
    }



}
