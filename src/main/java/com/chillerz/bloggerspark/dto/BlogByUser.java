package com.chillerz.bloggerspark.dto;

import com.chillerz.bloggerspark.entity.EndUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogByUser {
    private String blogTitle;
    private String blogDescription;
    private String endUser;
}
