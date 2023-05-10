package com.app.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="post")
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name ="post_title",length = 100,nullable = false)
    private String title;
    @Column(name ="post_content",length = 10000)
    private String content;
    @Column(name ="post_image")
    private String imageName;
    private Date date;

    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;


}
