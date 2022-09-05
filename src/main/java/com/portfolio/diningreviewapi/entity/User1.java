package com.portfolio.diningreviewapi.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USER1")
@RequiredArgsConstructor
@Getter
@Setter
public class User1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`NAME`")
    private String name;


    @Column(name = "`CITY`")
    private String city;
    @Column(name = "`STATE`")
    private String state;
    @Column(name = "`ZIPCODE`")
    private String zipcode;


    @Column(name = "`PEANUTALLERGIES`")
    private Boolean peanutAllergies;
    @Column(name = "`EGGALLERGIES`")
    private Boolean eggAllergies;
    @Column(name = "`DAIRYALLERGIES`")
    private Boolean dairyAllergies;

}
