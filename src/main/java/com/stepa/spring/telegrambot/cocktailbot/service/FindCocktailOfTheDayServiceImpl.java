package com.stepa.spring.telegrambot.cocktailbot.service;

import com.stepa.spring.telegrambot.cocktailbot.repository.entity.DBCocktails;
import com.stepa.spring.telegrambot.cocktailbot.repository.entity.TelegramUser;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FindCocktailOfTheDayServiceImpl implements FindCocktailOfTheDayService{

    private final DBCocktailsService dbCocktailsService;
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public FindCocktailOfTheDayServiceImpl(DBCocktailsService dbCocktailsService, SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.dbCocktailsService = dbCocktailsService;
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void findCocktail() {
        int id = (int) (Math.random()*300);

        DBCocktails dbCocktails = dbCocktailsService.retrieveCocktail(id);
        String fullRecipe = "Коктейль дня:"
                            +"\n" + dbCocktails.getName()
                            + "\nИнгредиенты: " + dbCocktails.getIngr()
                            + ".\n" + dbCocktails.getPick()
                            + ".\nТехника приготовления: " + dbCocktails.getTechnique();

        List<TelegramUser> subs = telegramUserService.retrieveAllSubsUsers();
        subs.stream().forEach(it -> sendBotMessageService.sendMessage(it.getChatId(), fullRecipe));

    }
}
