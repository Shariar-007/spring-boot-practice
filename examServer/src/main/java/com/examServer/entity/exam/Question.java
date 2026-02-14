package com.examServer.entity.exam;

import com.examServer.helper.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Public.class)
    private Long quesId;

    @JsonView(Views.Public.class)
    private String image;

    @JsonView(Views.Public.class)
    private String content;

    @JsonView(Views.Public.class)
    private String title;

    @Column(length = 2000)
    @JsonView(Views.Public.class)
    private String description;

    @JsonView(Views.Public.class)
    @Transient
    private Set<Long> givenAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Quiz quiz;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(Views.Public.class)
    private Set<OptionOfQuestion> option = new HashSet<>();
}
