package com.stepa.spring.telegrambot.cocktailbot.service;

import com.stepa.spring.telegrambot.cocktailbot.repository.DBCocktailsUserRepository;
import com.stepa.spring.telegrambot.cocktailbot.repository.entity.DBCocktails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBCocktailsServiceImpl implements DBCocktailsService{

    private final DBCocktailsUserRepository dbCocktailsUserRepository;


    @Autowired
    public DBCocktailsServiceImpl(DBCocktailsUserRepository dbCocktailsUserRepository) {
        this.dbCocktailsUserRepository = dbCocktailsUserRepository;
    }

    @Override
    public List<DBCocktails> retrieveCocktailsByName(String request) {
        System.out.println("Запрос в ServiceImpl" + request);
        return dbCocktailsUserRepository.findByName(request);
    }

    @Override
    public List<DBCocktails> retrieveCocktailsbyTwoIngr(String request1,String request2) {
        System.out.println("Запрос в ServiceImpl" + request1 + ", " + request2);
        return dbCocktailsUserRepository.findByIngrByTwo(request1,request2);
    }

    @Override
    public List<DBCocktails> retrieveCocktailsbyThreeIngr(String request1,String request2,String request3) {
        System.out.println("Запрос в ServiceImpl" + request1 + ", " + request2 + ", " + request3);
        return dbCocktailsUserRepository.findByIngrByThree(request1,request2,request3);
    }

    @Override
    public List<DBCocktails> retrieveCocktailsbyFourIngr(String request1,String request2,String request3,String request4) {
        System.out.println("Запрос в ServiceImpl" + request1 + ", " + request2 + ", " + request3 + ", " + request4);
        return dbCocktailsUserRepository.findByIngrByFour(request1,request2,request3,request4);
    }

    @Override
    public List<DBCocktails> retrieveCocktailsbyFiveIngr(String request1,String request2,String request3,String request4, String request5) {
        System.out.println("Запрос в ServiceImpl" + request1 + ", " + request2 + ", " + request3 + ", " + request4 + ", " + request5);
        return dbCocktailsUserRepository.findByIngrByFive(request1,request2,request3,request4,request5);
    }

    @Override
    public DBCocktails retrieveCocktail(int id) {
        return dbCocktailsUserRepository.findById(id);
    }
}
