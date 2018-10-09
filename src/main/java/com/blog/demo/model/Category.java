package com.blog.demo.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name")
    private String categoryName;//unique

    @OneToMany(mappedBy = "category")
    private Collection<Post> posts;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
