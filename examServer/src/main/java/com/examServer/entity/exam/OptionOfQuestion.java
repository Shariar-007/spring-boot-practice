package com.examServer.entity.exam;

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
    private Long oId;

    private String title;

    @Column(length = 2000)
    private String description;

    private boolean isCorrectAnswer = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;
}
