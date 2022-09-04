package com.portfolio.diningreviewapi.entity;

import com.portfolio.diningreviewapi.enums.ReviewStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "REVIEW")
@RequiredArgsConstructor
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SUBMITTEDBY")
    private String submittedBy;
    @Column(name = "RESTAURANTID")
    private Long restaurantId;
    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "PEANUT")
    private Integer peanut;
    @Column(name = "EGG")
    private Integer egg;
    @Column(name = "DAIRY")
    private Integer dairy;

    @Column(name = "STATUS")
    private ReviewStatus status;
}
