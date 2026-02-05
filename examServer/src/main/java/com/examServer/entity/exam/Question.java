package com.examServer.entity.exam;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qId;
    private String image;
    private String content;
    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Quiz quiz;
}
