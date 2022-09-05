package com.portfolio.diningreviewapi.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class User1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    private String city;
    private String state;
    private String zipcode;


    private Boolean peanutAllergies;
    private Boolean eggAllergies;
    private Boolean dairyAllergies;

}
