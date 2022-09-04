package com.portfolio.DiningReviewAPI.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USER")
@RequiredArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", unique = true)
    private String name;


    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE")
    private String state;
    @Column(name = "ZIPCODE")
    private String zipcode;


    @Column(name = "PEANUTALLERGIES")
    private Boolean peanutAllergies;
    @Column(name = "EGGALLERGIES")
    private Boolean eggAllergies;
    @Column(name = "DAIRYALLERGIES")
    private Boolean dairyAllergies;

}
