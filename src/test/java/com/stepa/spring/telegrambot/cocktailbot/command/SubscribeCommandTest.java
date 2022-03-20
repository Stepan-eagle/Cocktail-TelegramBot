package com.stepa.spring.telegrambot.cocktailbot.command;

import com.stepa.spring.telegrambot.cocktailbot.command.subscription.SubscribeCommand;

import static com.stepa.spring.telegrambot.cocktailbot.command.CommandName.SUBSCRIBE;
import static com.stepa.spring.telegrambot.cocktailbot.command.subscription.SubscribeCommand.MESSAGE;

public class SubscribeCommandTest  extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return SUBSCRIBE.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return MESSAGE;
    }

    @Override
    Command getCommand() {
        return new SubscribeCommand(sendBotMessageService, telegramUserService);
    }
}
