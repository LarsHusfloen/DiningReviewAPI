package com.portfolio.DiningReviewAPI.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DININGREVIEW")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiningReview {

    @Column(name="NAME")
    private String name;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PEANUT")
    private Integer peanut;

    @Column(name = "EGG")
    private Integer egg;

    @Column(name = "DAIRY")
    private Integer dairy;

    @Column(name = "COMMENT")
    private String comment;
}
