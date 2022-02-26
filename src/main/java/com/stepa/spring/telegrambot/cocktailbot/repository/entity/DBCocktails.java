package com.stepa.spring.telegrambot.cocktailbot.repository.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "cocktails")
public class DBCocktails {

    @Id
    @Column(name = "id")
    private Integer Id;

    @Column(name = "name")
    private String name;

    @Column(name = "ingr")
    private String ingr;

    @Column(name = "pick")
    private String pick;

    @Column(name = "technique")
    private String technique;

    @Column(name = "comps")
    private String comps;

}
