package com.stepa.spring.telegrambot.cocktailbot.command;

import com.stepa.spring.telegrambot.cocktailbot.command.subscription.UnsubscribeCommand;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandName.UNSUBSCRIBE;
import static com.stepa.spring.telegrambot.cocktailbot.command.subscription.UnsubscribeCommand.MESSAGE;

public class UnsubscribeCommandTest  extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return UNSUBSCRIBE.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnsubscribeCommand(sendBotMessageService, telegramUserService);
    }
}
