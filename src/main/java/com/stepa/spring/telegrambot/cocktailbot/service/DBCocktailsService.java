package com.stepa.spring.telegrambot.cocktailbot.service;

import com.stepa.spring.telegrambot.cocktailbot.repository.entity.DBCocktails;

import java.util.List;

public interface DBCocktailsService {

    List<DBCocktails> retrieveCocktailsByName(String request);
    List<DBCocktails> retrieveCocktailsbyTwoIngr(String request1,String request2);
    List<DBCocktails> retrieveCocktailsbyThreeIngr(String request1,String request2,String request3);
    List<DBCocktails> retrieveCocktailsbyFourIngr(String request1,String request2,String request3,String request4);
    List<DBCocktails> retrieveCocktailsbyFiveIngr(String request1,String request2,String request3,String request4, String request5);
    DBCocktails retrieveCocktail(int id);
}
