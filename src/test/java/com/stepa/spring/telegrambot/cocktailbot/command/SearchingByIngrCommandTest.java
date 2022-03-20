package com.stepa.spring.telegrambot.cocktailbot.command;

import com.stepa.spring.telegrambot.cocktailbot.command.cocktails.SearchingByIngrCommand;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandName.SEARCHBYINGR;
import static com.stepa.spring.telegrambot.cocktailbot.command.cocktails.SearchingByIngrCommand.MESSAGE;

public class SearchingByIngrCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return SEARCHBYINGR.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return "Введите ингредиенты. " +
                "Для смены условий поиска воспользуйтесь командным списком.";
    }

    @Override
    Command getCommand() {
        return new SearchingByIngrCommand(sendBotMessageService, dbCocktailsService);
    }
}