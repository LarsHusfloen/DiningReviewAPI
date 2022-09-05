package com.portfolio.diningreviewapi.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;
    private String city;
    private String state;
    private String zipcode;

    private Integer peanut;
    private Integer egg;
    private Integer dairy;

    private Integer average;

}
