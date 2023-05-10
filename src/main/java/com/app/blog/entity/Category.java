package com.app.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categories")
@Getter @Setter @NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(name="title",length = 100, nullable = false)
    private String categoryTitle;
    @Column(name="description")
    private String categoryDescription;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();
}
