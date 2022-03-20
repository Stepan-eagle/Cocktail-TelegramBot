package com.stepa.spring.telegrambot.cocktailbot.command;

import com.stepa.spring.telegrambot.cocktailbot.command.cocktails.NameCommand;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandName.NAME;
import static com.stepa.spring.telegrambot.cocktailbot.command.cocktails.NameCommand.MESSAGE;

public class NameCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return NAME.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NameCommand(sendBotMessageService);
    }
}
