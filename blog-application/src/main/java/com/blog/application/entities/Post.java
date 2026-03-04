package com.blog.application.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@Getter
@Setter
public class Post {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer postId;

        @Column(name = "post_title", length = 100, nullable = false)
        private String title;

        @Column(name = "post_content", length = 10000, nullable = false)
        private String content;

        private Boolean isLive;
        private String image;
        private Date createdDate;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "category_id")
        private Category category;

        @ManyToOne()
        @JoinColumn(name = "user_id")
        private User user;

        @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private Set<Comment> comment = new HashSet<>();
}
