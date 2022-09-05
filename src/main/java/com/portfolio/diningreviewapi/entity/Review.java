package com.portfolio.diningreviewapi.entity;

import com.portfolio.diningreviewapi.enums.ReviewStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String submittedBy;
    private Long restaurantId;
    private String comment;

    private Integer peanut;
    private Integer egg;
    private Integer dairy;

    private ReviewStatus status;
}
