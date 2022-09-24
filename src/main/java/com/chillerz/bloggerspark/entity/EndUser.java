package com.chillerz.bloggerspark.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "EndUser")
public class EndUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userName;
    private String passWord;

    @OneToMany(mappedBy = "blogId",cascade = CascadeType.ALL)
    private List<Blog> blog;

    @OneToMany(mappedBy = "id")
    private List<Likes> likes;

    @ManyToMany
    @JoinTable(name = "followers",
            joinColumns = @JoinColumn(name = "following_id"),
            inverseJoinColumns = @JoinColumn(name = "followers_id")   )
    private Set<EndUser> followers;

    @ManyToMany(mappedBy = "followers")
    private Set<EndUser> following;


}
