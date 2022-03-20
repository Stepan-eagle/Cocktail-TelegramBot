package com.stepa.spring.telegrambot.cocktailbot.command;

import com.stepa.spring.telegrambot.cocktailbot.command.cocktails.IngrCommand;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandName.INGR;
import static com.stepa.spring.telegrambot.cocktailbot.command.cocktails.IngrCommand.MESSAGE;

public class IngrCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return INGR.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return MESSAGE;
    }

    @Override
    Command getCommand() {
        return new IngrCommand(sendBotMessageService);
    }
}
