package com.examServer.entity.exam;

import com.examServer.helper.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class OptionOfQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Public.class)
    private Long oId;

    @JsonView(Views.Public.class)
    private String title;

    @Column(length = 2000)
    @JsonView(Views.Public.class)
    private String description;

    @JsonView(Views.Admin.class)
    private boolean isCorrectAnswer = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Question question;
}
