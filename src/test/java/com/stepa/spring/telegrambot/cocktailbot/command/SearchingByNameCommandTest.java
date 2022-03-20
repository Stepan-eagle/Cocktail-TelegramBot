package com.stepa.spring.telegrambot.cocktailbot.command;

import com.stepa.spring.telegrambot.cocktailbot.command.cocktails.SearchingByNameCommand;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandName.SEARCHBYNAME;
import static com.stepa.spring.telegrambot.cocktailbot.command.cocktails.SearchingByNameCommand.MESSAGE;

public class SearchingByNameCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return SEARCHBYNAME.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return MESSAGE;
    }

    @Override
    Command getCommand() {
        return new SearchingByNameCommand(sendBotMessageService, dbCocktailsService);
    }
}
