package com.portfolio.diningreviewapi.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "RESTAURANT")
@RequiredArgsConstructor
@Getter
@Setter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE")
    private String state;
    @Column(name = "ZIPCODE")
    private String zipcode;

    @Column(name = "PEANUT")
    private Integer peanut;
    @Column(name = "EGG")
    private Integer egg;
    @Column(name = "DAIRY")
    private Integer dairy;

    @Column(name = "AVERAGE")
    private Integer average;

}
